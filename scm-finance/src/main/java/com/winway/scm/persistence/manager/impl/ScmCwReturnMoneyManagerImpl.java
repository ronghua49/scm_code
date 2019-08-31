package com.winway.scm.persistence.manager.impl;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.exception.SystemException;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.query.PageBean;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.BeanUtils;
import com.winway.purchase.feign.ScmMasterDateFeignService;
import com.winway.scm.model.ScmCwReturnMoney;
import com.winway.scm.persistence.dao.ScmCwReturnMoneyDao;
import com.winway.scm.persistence.manager.ScmCwReturnMoneyManager;
import com.winway.scm.util.POIUtil;

/**
 * <pre>
 * 描述：商业回款表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-23 18:19:25
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmCwReturnMoneyManager")
public class ScmCwReturnMoneyManagerImpl extends AbstractManagerImpl<String, ScmCwReturnMoney> implements ScmCwReturnMoneyManager {
    @Resource
    ScmCwReturnMoneyDao scmCwReturnMoneyDao;
    @Resource
    ScmMasterDateFeignService scmMasterDateFeignService;

    @Override
    protected MyBatisDao<String, ScmCwReturnMoney> getDao() {
        return scmCwReturnMoneyDao;
    }

    @Override
    public String readExcelFile(MultipartFile file, ScmCwReturnMoney product) {
        try {
            String businessDivisionId = null;
            String businessDivision = null;
            String provinceId = null;
            String province = null;
            String commerceName = null;
            String commerceId = null;
            String commerceCode = null;
            //查询商业表信息数据
            String res = scmMasterDateFeignService.findByOwnerId(product.getOwnerId());
            JSONObject data = JSON.parseObject(res);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            List<String[]> list = POIUtil.readExcel(file);
            for (String[] s : list) {
                ScmCwReturnMoney product1 = new ScmCwReturnMoney();

                product1.setRemittanceDate(sdf.parse(s[0]));
                String totle = s[1];
                if (data.getString(totle) != null) {
                    JSONObject object = JSON.parseObject(data.getString(totle));
                    businessDivisionId = object.getString("businessDivisionId");
                    businessDivision = object.getString("businessDivision");
                    //省区Id
                    provinceId = object.getString("provinceId");
                    province = object.getString("province");
                    //商业名称
                    commerceName = object.getString("commerceName");
                    commerceId = object.getString("id");
                    commerceCode = object.getString("commerceCode");

                    product1.setMatchResult("匹配成功");
                } else {
                    businessDivisionId = null;
                    businessDivision = null;
                    //省区Id
                    provinceId = null;
                    province = null;
                    //商业名称
                    commerceName = null;
                    commerceId = null;
                    commerceCode = null;
                    product1.setMatchResult("商业名称匹配错误！");
                }
                product1.setToLeadPersion(product.getToLeadPersion());
                product1.setToLeadDate(new Date());
                product1.setCommerceName(totle);
                product1.setCommerceId(commerceId);
                product1.setCommerceCode(commerceCode);
                product1.setProvince(province);//省区
                product1.setProvinceId(provinceId);
                product1.setVerifyType("0");
                product1.setMatchCommerceName(commerceName);
                product1.setBusinessDivisionId(businessDivisionId);
                product1.setRemittanceType(s[2]);
                product1.setAcceptIsTrue(s[3]);
                if(s[4] == null || "".equals(s[4])) {
                	product1.setAcceptExpire(null);
                }else{
                	product1.setAcceptExpire(sdf.parse(s[4]));
                }
                String s1 = s[5];
                if ("预付款".equals(s1)) {
                    s1 = "0";
                } else if ("赊销".equals(s1)) {
                    s1 = "1";
                } else {
                	s1 = "";
                }
                product1.setPaymentType(s1);
                product1.setPrice(Double.valueOf(s[6]));
                product1.setBusinessDivision(businessDivision);//商务分区
                product1.setMemo(s[7]);
                product1.setOwnerId(product.getOwnerId());
                product1.setIsAffirm("0");
                product1.setBalancePrice(product1.getPrice());
                //商业时间金额相同时，则提示存在相同数据，是否导入
                List<ScmCwReturnMoney> scmCwReturnMoney = scmCwReturnMoneyDao.getByParam(commerceCode, product1.getRemittanceDate(), product1.getPrice());
                if (scmCwReturnMoney != null&&!scmCwReturnMoney.isEmpty()) {
                    product1.setMatchResult("存在商业、时间、金额相同数据，是否导入");
                }
                super.create(product1);
            }
            return "导入成功";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("导入失败");
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("日期格式错误，请设置文件日期格式(2011-11-11)");
            //e.printStackTrace();
        }
    }

    @Override
    public void downloadExcel(HttpServletResponse response) {
        org.springframework.core.io.Resource resource = new ClassPathResource("/商业回款模板.xlsx");
        try {
            File file = resource.getFile();

            String name = file.getName();
            String[] split = name.split("\\.");
            InputStream in = new FileInputStream(file);
            OutputStream out = response.getOutputStream();
            System.out.println(split[0]);
            String s = new String(split[0].getBytes(StandardCharsets.UTF_8), "ISO8859-1");
            System.out.println(s);
            response.addHeader("Content-disposition", "attachment;filename=" + new String(split[0].getBytes(StandardCharsets.UTF_8), "ISO8859-1") + ".xlsx");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8");
            response.setHeader("Content-Length", String.valueOf((in.available())));
            byte[] b = new byte[2048];
            int len;
            while ((len = in.read(b)) != -1) {
                out.write(b, 0, len);
            }
            out.flush();
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("下载失败，未找到文件路径");
        }


    }

    @Override
    public PageList<ScmCwReturnMoney> query(QueryFilter queryFilter) throws SystemException {
        PageBean pageBean = queryFilter.getPageBean();
        if (BeanUtils.isEmpty(pageBean)) {
            PageHelper.startPage(1, Integer.MAX_VALUE, false);
        } else {
            PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
        }
        List<ScmCwReturnMoney> query = scmCwReturnMoneyDao.query(queryFilter.getParams());
        return new PageList<ScmCwReturnMoney>(query);
    }

    @Override
    public void affirmData(String[] ids, String fullname) {
        for (String id : ids) {
            ScmCwReturnMoney money = get(id);
            money.setToLeadPersion(fullname);
            money.setToLeadDate(new Date());
            money.setIsAffirm("1");
            update(money);
            //插入数据到资信记录表
            scmMasterDateFeignService.saveByContractAndReturnMoney(money.getId(), 0 - money.getPrice(), money.getCommerceId(), money.getOwnerId(), "3");
        }
    }

    @Override
    public void updatePrice(String fullname, String id, String updatePrice, String memo) {
        ScmCwReturnMoney money = get(id);
        if (money.getVerifyType().equals("1") || money.getVerifyType().equals("2")) {
            throw new RuntimeException("该数据已经开始核销，不能再进行调整了！");
        }
        if (StringUtils.isEmpty(updatePrice)) {
            throw new RuntimeException("调整金额不能为空，请输入金额！");
        }
        money.setMemo(memo);
        money.setUpdatePrice(Double.valueOf(updatePrice));
        money.setBalancePrice(Double.valueOf(updatePrice));
        money.setAffirmPersion(fullname);
        money.setAffirmDate(new Date());
        update(money);
        //修改金额
        scmMasterDateFeignService.saveByContractAndReturnMoney(money.getId(), 0 - money.getPrice(), money.getCommerceId(), money.getOwnerId(), "3");
    }

    @Override
    public PageList<Map<String, Object>> sumList(QueryFilter queryFilter) {
        PageBean pageBean = queryFilter.getPageBean();
        if (BeanUtils.isEmpty(pageBean)) {
            PageHelper.startPage(1, 1000, true);
        } else {
            PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
        }
        List<Map<String, Object>> list = new ArrayList<>();
        List<ScmCwReturnMoney> scmCwReturnMoneyList = scmCwReturnMoneyDao.sumList(queryFilter.getParams());
        for (ScmCwReturnMoney returnMoney : scmCwReturnMoneyList) {
            Map<String, Object> map = new HashMap<>();
            map.put("commerceName", returnMoney.getCommerceName());
            map.put("price", returnMoney.getPrice());
            map.put("isAffirm",returnMoney.getIsAffirm());
            list.add(map);
        }
        return new PageList<Map<String, Object>>(list);
    }
}
