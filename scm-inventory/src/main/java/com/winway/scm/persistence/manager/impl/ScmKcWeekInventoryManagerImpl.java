
package com.winway.scm.persistence.manager.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.exception.SystemException;
import com.hotent.base.feign.WWSearchFeignService;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.*;
import com.hotent.base.util.BeanUtils;
import com.hotent.base.util.StringUtil;
import com.hotent.base.util.UniqueIdUtil;
import com.winway.purchase.feign.ScmMasterDateFeignService;
import com.winway.purchase.util.DateFormatter;
import com.winway.purchase.util.POIUtil;
import com.winway.purchase.vo.ScmCgGodownentry;
import com.winway.purchase.vo.ScmZsjCommerce;
import com.winway.purchase.vo.ScmZsjProduct;
import com.winway.scm.model.ScmKcWeekInventory;
import com.winway.scm.persistence.dao.ScmKcWeekInventoryDao;
import com.winway.scm.persistence.manager.ScmKcWeekInventoryManager;
import com.winway.scm.vo.ScmKcMonthInventory;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * <pre>
 * 描述：scm_kc_weekinventory 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-07-08 15:15:30
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmKcWeekInventoryManager")
public class ScmKcWeekInventoryManagerImpl extends AbstractManagerImpl<String, ScmKcWeekInventory> implements ScmKcWeekInventoryManager {
    @Resource
    ScmKcWeekInventoryDao scmKcWeekInventoryDao;

    @Resource
    ScmMasterDateFeignService scmMasterDateFeignService;

    @Resource
    WWSearchFeignService wwSearchFeignService;

    @Value("${system.week.num}")
    private int weekNum;

    @Override
    protected MyBatisDao<String, ScmKcWeekInventory> getDao() {
        return scmKcWeekInventoryDao;
    }

    @Override
    public void create(ScmKcWeekInventory entity) {
        judgeData(entity, 2);
        super.create(entity);
    }

    @Override
    public void update(ScmKcWeekInventory entity) {
        judgeData(entity, 2);
        super.update(entity);
    }

    private void judgeData(ScmKcWeekInventory entity, int line) {
        Date startDate = entity.getStartDate();
        Date endDate = entity.getStopDate();
        String prefix = "第" + line + "行";
        //判断库存日期是否在时间范围
        if (entity.getInventoryDate().before(startDate) || entity.getInventoryDate().after(endDate)) {
            throw new RuntimeException(prefix + "录入的库存日期不在当前周时间范围，请重新录入");
        }
        // 批号必须填写 且只能填写一个
        String batchNum = entity.getBatchNum();
        if (batchNum == null || "".equals(batchNum)) {
            throw new RuntimeException(prefix + "导入数据批号不得为空");
        }

        if (batchNum.split(",").length > 1 || batchNum.split("\\|").length > 1 || batchNum.split(";").length > 1 || batchNum.split(" ").length > 1) {
            throw new RuntimeException(prefix + "一条记录批号只能填写一个");
        }
        //判断同日期 同一家商业 相同商品 批号不得重复
        ScmKcWeekInventory weekInventory = scmKcWeekInventoryDao.getByInventoryAndBatch(entity.getInventoryDate(), entity.getBatchNum(), entity.getCommerceCode(), entity.getProductCode());
        if (weekInventory != null && !weekInventory.getId().equals(entity.getId())) {
            throw new RuntimeException(prefix + "同库存日期下，商品批号重复");
        }
    }

    @Override
    public List<Map<String, Object>> base() throws ParseException {
        List<Map<String, Object>> list = new ArrayList<>();
        Calendar instance = Calendar.getInstance();
        for (int i = 0; i < weekNum; i++) {
            Map<String, Object> map = new HashMap<>();
            instance.setTime(DateFormatter.startOfDay(new Date()));
            instance.setMinimalDaysInFirstWeek(4);
            instance.set(Calendar.DATE, instance.get(Calendar.DATE) - 7 * i);
            if (instance.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                instance.add(Calendar.DATE, -1);
            }
            int weekTime = instance.get(Calendar.WEEK_OF_YEAR);
            instance.set(Calendar.WEEK_OF_YEAR, weekTime);
            int year = instance.getWeekYear();
            int month = instance.get(Calendar.MONTH) + 1;
            instance.setFirstDayOfWeek(Calendar.MONDAY);
            instance.set(Calendar.DAY_OF_WEEK, instance.getFirstDayOfWeek());
            Date firstDayOfWeek = instance.getTime();
            instance.set(Calendar.DAY_OF_WEEK, instance.getFirstDayOfWeek() + 6);
            Date lastDayOfWeek = instance.getTime();
            lastDayOfWeek = DateFormatter.endOfDay(lastDayOfWeek);
            String firstDayOfWeek1 = DateFormatter.format(DateFormatter.PATTERN_yyyy_MM_dd, firstDayOfWeek);
            String firstDayOfWeek2 = DateFormatter.format(DateFormatter.PATTERN_yyyy_MM_dd, lastDayOfWeek);
            map.put("year", year);
            map.put("month", month);
            map.put("weekTime", weekTime);
            map.put("startDate", firstDayOfWeek1);
            map.put("endDate", firstDayOfWeek2);
            map.put("period", firstDayOfWeek1 + " - " + firstDayOfWeek2);
            list.add(map);
        }
        return list;
    }

    @Override
    public Map<String, String> readExcelFile(MultipartFile file, ScmKcWeekInventory weekInventory) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //读取excel数据
        Set<String> ids = new HashSet<>();
        List<ScmKcWeekInventory> weekInventories = new ArrayList<>();
        try {
            List<String[]> data = POIUtil.readExcel(file);
            Set<String> commerceSet = new HashSet<>(20);
            Set<String> productSet = new HashSet<>(20);
            Set<String> batchNumSet = new HashSet<>(20);
            data.forEach(s -> {
                commerceSet.add(s[1].trim());
                productSet.add(s[2].trim());
                batchNumSet.add(s[4].trim());
            });

            //获取直连商业数据
            /*CommonResult result = wwSearchFeignService.getDirect(new ArrayList<>(commerceSet));
            List<String> directCommerces = (List<String>)result.getValue();
            //直连商业判断
            if (directCommerces.size() > 0) {
                StringBuilder sb = new StringBuilder();
                for (String directCommerce : directCommerces) {
                    sb.append(directCommerce).append(",");
                }
                String names = sb.toString().substring(0, sb.length() - 1);
                throw new RuntimeException(names + " 是直连商业，不允许导入库存！");
            }*/

            PageBean pageBean = new PageBean(1, Integer.MAX_VALUE);
            //获取商业列表
            PageList<ScmZsjCommerce> commercePageList =
                    scmMasterDateFeignService.commerceList(QueryFilter.build().addFilter("commerceName", StringUtil.join(commerceSet, ","), QueryOP.IN).withPage(pageBean));
            Map<String, String> commerceMap = commercePageList.getRows().stream()
                    .collect(Collectors.toMap(ScmZsjCommerce::getCommerceName, ScmZsjCommerce::getCommerceCode));
            List<String> commerceList = commercePageList.getRows().stream().map(ScmZsjCommerce::getCommerceCode).collect(Collectors.toList());
            //获取商品列表
            PageList<ScmZsjProduct> productPageList =
                    scmMasterDateFeignService.productList(QueryFilter.build().addFilter("commonName", StringUtil.join(productSet, ","), QueryOP.IN).withPage(pageBean));
            Map<String, List<ScmZsjProduct>> productMap = productPageList.getRows().stream()
                    .collect(Collectors.groupingBy(ScmZsjProduct::getCommonName));

            //获取入库单列表
            PageList<ScmCgGodownentry> godownentryPageList =
                    scmMasterDateFeignService.godownEntryList(QueryFilter.build().addFilter("lotno", StringUtil.join(batchNumSet, ","), QueryOP.IN).withPage(pageBean));
            Map<String, List<ScmCgGodownentry>> godownentryMap = godownentryPageList.getRows().stream()
                    .collect(Collectors.groupingBy(ScmCgGodownentry::getLotno));

            int i = 1;
            for (String[] s : data) {
                String prefix = "";
                try {
                    i++;
                    prefix = "第" + i + "行";
                    ScmKcWeekInventory weekInventory1 = new ScmKcWeekInventory();
                    weekInventory1.setCreatePersion(weekInventory.getCreatePersion());
                    weekInventory1.setCreateDate(weekInventory.getCreateDate());
                    weekInventory1.setOwnerId(weekInventory.getOwnerId());
                    if (s.length > 0 && !"".equals(s[0])) {
                        if (s[0].contains("/")) {
                            s[0] = s[0].replaceAll("/", "-");
                        }
                        weekInventory1.setInventoryDate(format.parse(s[0]));
                    } else {
                        throw new RuntimeException(prefix + "导入库存日期不得为空");
                    }
                    if (s.length > 1 && !"".equals(s[1])) {
                        weekInventory1.setCommerceName(s[1].trim());
                    } else {
                        throw new RuntimeException(prefix + "导入商业名称不得为空");
                    }
                    if (s.length > 2 && !"".equals(s[2])) {
                        weekInventory1.setCommonName(s[2].trim());
                    } else {
                        throw new RuntimeException(prefix + "导入商品通用名不得为空");
                    }
                    if (s.length > 3 && !"".equals(s[3])) {
                        weekInventory1.setProductState(s[3].trim());
                    } else {
                        throw new RuntimeException(prefix + "导入商品规格不得为空");
                    }
                    if (s.length > 4 && !"".equals(s[4])) {
                        Double parseDouble = Double.parseDouble(s[4].trim());
                        weekInventory1.setBatchNum(String.valueOf(parseDouble.intValue()));
                    } else {
                        throw new RuntimeException(prefix + "导入商品批号不得为空");
                    }

                    //匹配商品
                    List<ScmZsjProduct> products = productMap.get(weekInventory1.getCommonName());
                    if (products == null || products.size() == 0) {
                        throw new RuntimeException(prefix + "商品通用名未匹配上");
                    }
                    List<ScmZsjProduct> products1 = products.stream()
                            .filter(p -> weekInventory1.getProductState().equals(p.getProductState())).collect(Collectors.toList());
                    if (products1.size() == 0) {
                        throw new RuntimeException(prefix + "商品规格未匹配上");
                    }
                    String productCode = products1.get(0).getProductCode();
                    String productName = products1.get(0).getProductName();
                    String unit = products1.get(0).getUnit();
                    weekInventory1.setProductCode(productCode);
                    weekInventory1.setProductName(productName);
                    weekInventory1.setUnit(unit);

                    //获取入库单商品的有效期
                    List<ScmCgGodownentry> godownentries = godownentryMap.get(weekInventory1.getBatchNum());
                    if (godownentries == null || godownentries.size() == 0) {
                        weekInventory1.setExpiryDate(null);
                    }else {
                        weekInventory1.setExpiryDate(godownentries.get(0).getExpdate());
                    }
//                    }
                    if (s.length > 5 && !"".equals(s[5])) {
                        Double parseDouble = Double.parseDouble(s[5]);
                        weekInventory1.setThisWeekInventory(parseDouble.intValue());
                    } else {
                        throw new RuntimeException(prefix + "导入本周库存数量不得为空");
                    }
                    if (s.length > 6 && !"".equals(s[6])) {
                        weekInventory1.setMemo(s[6]);
                    }

                    //匹配商业
                    String commerceCode = commerceMap.get(weekInventory1.getCommerceName());
                    if (StringUtil.isEmpty(commerceCode)) {
                        throw new RuntimeException(prefix + "商业名称未批匹配上");
                    }
                    weekInventory1.setCommerceCode(commerceCode);

                    weekInventory1.setStartDate(weekInventory.getStartDate());
                    weekInventory1.setStopDate(weekInventory.getStopDate());
                    weekInventory1.setYear(weekInventory.getYear());
                    weekInventory1.setMonth(weekInventory.getMonth());
                    weekInventory1.setWeek(weekInventory.getWeek());
                    //判断库存日期是否在时间范围
                    if (weekInventory1.getInventoryDate().before(weekInventory.getStartDate())
                            || weekInventory1.getInventoryDate().after(weekInventory.getStopDate())) {
                        throw new RuntimeException(prefix + "录入的库存日期不在当前周时间范围，请重新录入");
                    }
//                    judgeData(weekInventory1, i);
                    //获取上周库存
                    List<ScmKcWeekInventory> lastWeekStock = scmKcWeekInventoryDao.getLastWeekStock(productCode, commerceCode, weekInventory1.getBatchNum(), weekInventory1.getWeek() - 1);
                    if (lastWeekStock == null || lastWeekStock.isEmpty()) {
                        weekInventory1.setUpWeekInventory(0);
                    } else {
                        int sum = 0;
                        for (ScmKcWeekInventory stock : lastWeekStock) {
                            sum += stock.getThisWeekInventory();
                        }
                        weekInventory1.setUpWeekInventory(sum);
                    }
//                    super.create(weekInventory1);
                    weekInventory1.setId(UniqueIdUtil.getSuid());
                    weekInventories.add(weekInventory1);
                    ids.add(weekInventory1.getId());
                } catch (ParseException e) {
                    e.printStackTrace();
                    throw new RuntimeException("请调整" + prefix + "日期列格式为2001/3/7格式");
                }
            }
            //覆盖已导入数据
            scmKcWeekInventoryDao.deleteByWeekAndCommerce(weekInventory.getWeek(), commerceList);
            //保存导入数据到数据库
            scmKcWeekInventoryDao.createAll(weekInventories);
            Map<String, String> returnmap = new HashMap<>();
            returnmap.put("ids", ids.toString());
            returnmap.put("total", String.valueOf(data.size()));
            return returnmap;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("导入失败");
        }
    }

    @Override
    public PageList<ScmKcMonthInventory> monthList(QueryFilter queryFilter) {
        PageBean pageBean = queryFilter.getPageBean();
        if (BeanUtils.isEmpty(pageBean)) {
            PageHelper.startPage(1, 2147483647, false);
        } else {
            PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
        }
        List<ScmKcMonthInventory> list = scmKcWeekInventoryDao.monthList(queryFilter.getParams());
        return new PageList<ScmKcMonthInventory>(list);
    }

    @Override
    public PageList<ScmKcWeekInventory> query(QueryFilter queryFilter) throws SystemException {
        FieldSort f1 = new FieldSort();
        f1.setDirection(Direction.DESC);
        f1.setProperty("year");
        FieldSort f2 = new FieldSort();
        f2.setDirection(Direction.DESC);
        f2.setProperty("month");
        FieldSort f3 = new FieldSort();
        f3.setDirection(Direction.DESC);
        f3.setProperty("week");
        List<FieldSort> sorter = queryFilter.getSorter();
        sorter.add(f1);
        sorter.add(f2);
        sorter.add(f3);
        QueryFilter build = QueryFilter.build();
        build.setQuerys(queryFilter.getQuerys());
        build.setSorter(queryFilter.getSorter());
        PageBean pageBean = queryFilter.getPageBean();
        if (BeanUtils.isEmpty(pageBean)) {
            PageHelper.startPage(1, 1000, true);
        } else {
            PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
        }
        List<ScmKcWeekInventory> query = scmKcWeekInventoryDao.query(queryFilter.getParams());
        return new PageList(query);
    }
}
