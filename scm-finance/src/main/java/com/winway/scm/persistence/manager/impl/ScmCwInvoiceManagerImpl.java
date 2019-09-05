package com.winway.scm.persistence.manager.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.feign.UCFeignService;
import com.hotent.base.query.*;
import com.winway.purchase.feign.ScmMasterDateFeignService;
import com.winway.purchase.util.DateFormatter;
import com.winway.purchase.util.ExcelExportUtil;
import com.winway.scm.model.ScmCwReturnMoney;
import com.winway.scm.util.POIUtil;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.util.BeanUtils;
import com.winway.scm.model.ScmCwInvoice;
import com.winway.scm.persistence.dao.ScmCwInvoiceDao;
import com.winway.scm.persistence.manager.ScmCwInvoiceManager;
import com.winway.scm.vo.VInvoice;
import org.springframework.web.multipart.MultipartFile;

/**
 * <pre>
 * 描述：商业发票表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-23 18:19:25
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmCwInvoiceManager")
public class ScmCwInvoiceManagerImpl extends AbstractManagerImpl<String, ScmCwInvoice> implements ScmCwInvoiceManager {
    @Resource
    ScmCwInvoiceDao scmCwInvoiceDao;

    @Resource
    ScmMasterDateFeignService scmMasterDateFeignService;

    @Override
    protected MyBatisDao<String, ScmCwInvoice> getDao() {
        return scmCwInvoiceDao;
    }

    @Override
    public PageList<ScmCwInvoice> findAll(QueryFilter queryFilter) {
        // TODO Auto-generated method stub
        PageBean pageBean = queryFilter.getPageBean();
        if (BeanUtils.isEmpty(pageBean)) {
            PageHelper.startPage(1, Integer.MAX_VALUE, false);
        } else {
            PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
        }
        List<ScmCwInvoice> query = scmCwInvoiceDao.selAll(queryFilter.getParams());

        return new PageList<ScmCwInvoice>(query);
    }

    @Override
    public PageList<ScmCwInvoice> affirmData(String[] ids) {
        return null;
    }

    @Override
    public PageList<VInvoice> history(QueryFilter queryFilter) {
        PageBean pageBean = queryFilter.getPageBean();
        if (BeanUtils.isEmpty(pageBean)) {
            PageHelper.startPage(1, Integer.MAX_VALUE, false);
        } else {
            PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
        }
        List<VInvoice> query = scmCwInvoiceDao.history(queryFilter.getParams());
        return new PageList<VInvoice>(query);
    }

    @Override
    public void export(QueryFilter queryFilter, boolean isPage, HttpServletResponse response) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<List<String>> data = new ArrayList<List<String>>();
        String[] tableName = {"核销状态", "发票类型", "到款日期", "商务分区", "省区", "开票商业名称", "原开票日期", "调整开票日期", "发票号码",
                "商品名称", "通用名", "规格", "数量", "含税单价", "价税合计", "发货日期", "发货单号", "合同号", "回款方式", "备注", "回款到日期", "采集人", "采集时间"};
        PageList<ScmCwInvoice> query = new PageList<>();
        List<FieldSort> sorter = new ArrayList<>();
        FieldSort fieldSort = new FieldSort();
        fieldSort.setProperty("invoiceDate");
        fieldSort.setDirection(Direction.DESC);
        sorter.add(fieldSort);
        // 如果不分页
        if (!isPage) {
            QueryFilter build = QueryFilter.build();
            build.setSorter(sorter);
            PageHelper.startPage(1, Integer.MAX_VALUE, true);
            List<ScmCwInvoice> query1 = scmCwInvoiceDao.query(build.getParams());
            query = new PageList(query1);
        } else {
            queryFilter.setSorter(sorter);
            PageBean pageBean = queryFilter.getPageBean();
            PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
            List<ScmCwInvoice> query1 = scmCwInvoiceDao.query(queryFilter.getParams());
            query = new PageList<>(query1);
        }
        List<ScmCwInvoice> rows = query.getRows();
        for (int i = 0; i < rows.size(); i++) {
            List<String> br = new ArrayList<>();
            ScmCwInvoice invoice = rows.get(i);
            if (invoice.getVerifyType() != null && "".equals(invoice.getVerifyType())) {
                switch (invoice.getVerifyType()) {
                    case "0":
                        br.add("集中发货");
                        break;
                    case "1":
                        br.add("分配");
                        break;
                    case "2":
                        br.add("日常合同");
                        break;
                }
            } else {
                br.add("未核销");
            }
            br.add(invoice.getInvoiceType());
            br.add(invoice.getPaymentDate() == null ? "" : format.format(invoice.getPaymentDate()));
            br.add(invoice.getBusinessDivision());
            br.add(invoice.getProvince());
            br.add(invoice.getCommonName());
            br.add(invoice.getCommerceName());
            br.add(invoice.getInvoiceDate() == null ? "" : format.format(invoice.getInvoiceDate()));
            br.add(invoice.getUpdateInvoiceDate() == null ? "" : format.format(invoice.getUpdateInvoiceDate()));
            br.add(invoice.getInvoiceCode());
            br.add(invoice.getProductName());
            br.add(invoice.getCommonName());
            br.add(invoice.getProductState());
            br.add(String.valueOf(invoice.getProductSum()));
            br.add(String.valueOf(invoice.getUnitPrice()));
            br.add(String.valueOf(invoice.getPriceTaxSum()));
            br.add(invoice.getShipmentDate() == null ? "" : format.format(invoice.getShipmentDate()));
            br.add(invoice.getShipmentCode());
            br.add(invoice.getContractCode());
            br.add(invoice.getRemittanceType());
            br.add(invoice.getMemo());
            br.add(invoice.getGatherPersion());
            br.add(invoice.getGathererDate() == null ? "" : format.format(invoice.getGathererDate()));
            data.add(br);
        }
        ExcelExportUtil.download(response, "销售发货数据", "销售发货数据", tableName, data);
    }

    @Override
    public PageList<Map<String, Object>> sumList(QueryFilter queryFilter) {
        PageBean pageBean = queryFilter.getPageBean();
        if (BeanUtils.isEmpty(pageBean)) {
            PageHelper.startPage(1, 1000, true);
        } else {
            PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
        }
        queryFilter.addFilter("isAffirm", "0", QueryOP.EQUAL, FieldRelation.AND);
        List<Map<String, Object>> query = scmCwInvoiceDao.sumList(queryFilter.getParams());
        return new PageList(query);
    }

    @Override
    public void export2(QueryFilter queryFilter, boolean isPage, HttpServletResponse response) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<List<String>> data = new ArrayList<List<String>>();
        String[] tableName = {"开票日期", "参照", "SAP匹配", "进水发票", "商品名称", "规格", "单价", "数量", "应收金额",
                "发货日期", "合同编号", "回款核销金额", "剩余", "核销备注", "核销人", "核销时间", "确认人", "确认时间"};
        PageList<VInvoice> query;
        List<FieldSort> sorter = new ArrayList<>();
        FieldSort fieldSort = new FieldSort();
        fieldSort.setProperty("affirmDate");
        fieldSort.setDirection(Direction.DESC);
        sorter.add(fieldSort);
        // 如果不分页
        if (!isPage) {
            QueryFilter build = QueryFilter.build();
            build.setSorter(sorter);
            PageHelper.startPage(1, Integer.MAX_VALUE, true);
            List<VInvoice> query1 = scmCwInvoiceDao.history(queryFilter.getParams());
            query = new PageList(query1);
        } else {
            queryFilter.setSorter(sorter);
            PageBean pageBean = queryFilter.getPageBean();
            PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
            List<VInvoice> query1 = scmCwInvoiceDao.history(queryFilter.getParams());
            query = new PageList<>(query1);
        }
        List<VInvoice> rows = query.getRows();
        for (int i = 0; i < rows.size(); i++) {
            List<String> br = new ArrayList<>();
            VInvoice vInvoice = rows.get(i);
            br.add(vInvoice.getInvoiceDate() == null ? "" : format.format(vInvoice.getInvoiceDate()));
            br.add("");
            br.add("");
            br.add("");
            br.add(vInvoice.getCommonName());
            br.add(vInvoice.getProductState());
            br.add(vInvoice.getUnitPrice() == null ? "" : vInvoice.getUnitPrice().toString());
            br.add(vInvoice.getProductSum() == null ? "" : vInvoice.getProductSum().toString());
            br.add(vInvoice.getPriceTaxSum() == null ? "" : vInvoice.getPriceTaxSum().toString());
            br.add(vInvoice.getShipmentDate() == null ? "" : format.format(vInvoice.getShipmentDate()));
            br.add(vInvoice.getContractCode());
            br.add(vInvoice.getBlendprice());
            br.add(vInvoice.getBalancePrice());
            br.add(vInvoice.getMemo());
            br.add(vInvoice.getBlendPersion());
            br.add(vInvoice.getBlendDate() == null ? "" : format.format(vInvoice.getBlendDate()));
            br.add(vInvoice.getAffirmPersion());
            br.add(vInvoice.getAffirmDate() == null ? "" : format.format(vInvoice.getAffirmDate()));
            data.add(br);
        }
        ExcelExportUtil.download(response, "已勾兑数据列表", "已勾兑数据列表", tableName, data);
    }

    @Override
    public PageList<ScmCwInvoice> checklist(QueryFilter queryFilter) {
        PageBean pageBean = queryFilter.getPageBean();
        if (BeanUtils.isEmpty(pageBean)) {
            PageHelper.startPage(1, 1000, true);
        } else {
            PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
        }
        List<ScmCwInvoice> query = scmCwInvoiceDao.checklist(queryFilter.getParams());
        return new PageList(query);
    }

    @Override
    public void export3(QueryFilter queryFilter, boolean b, HttpServletResponse response) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<List<String>> data = new ArrayList<List<String>>();
        String[] tableName = {"核销状态", "开票日期", "到款日期", "商务分区", "省区", "供应商", "商业名称", "产品系列", "产品",
                "合同编号", "发票号", "回款方式", "开票数量", "单价", "开票金额", "核销金额", "剩余金额"};
        PageHelper.startPage(1, Integer.MAX_VALUE, true);

        List<ScmCwInvoice> rows = scmCwInvoiceDao.checklist(queryFilter.getParams());
        for (int i = 0; i < rows.size(); i++) {
            List<String> br = new ArrayList<>();
            ScmCwInvoice invoice = rows.get(i);
            if ("0".equals(invoice.getVerifyType())) {
                br.add("未核销");
            } else if ("1".equals(invoice.getVerifyType())) {
                br.add("核销中");
            } else if ("2".equals(invoice.getVerifyType())) {
                br.add("已核销");
            }
            br.add(invoice.getInvoiceDate() == null ? "" : format.format(invoice.getInvoiceDate()));
            br.add(invoice.getRemittanceDate() == null ? "" : format.format(invoice.getInvoiceDate()));
            br.add(invoice.getBusinessDivision());
            br.add(invoice.getProvince());
            br.add(invoice.getSupplier());
            br.add(invoice.getCommerceName());
            br.add(invoice.getProductLineName());
            br.add(invoice.getProductName());
            br.add(invoice.getContractCode());
            br.add(invoice.getInvoiceCode());
            br.add(invoice.getRemittanceType());
            br.add(invoice.getProductSum().toString());
            br.add(invoice.getUnitPrice().toString());
            br.add(invoice.getPriceTaxSum().toString());
            br.add(invoice.getBlendprice());
            br.add(invoice.getBalancePrice().toString());
            data.add(br);
        }
        ExcelExportUtil.download(response, "商业汇款发票核销", "商业汇款发票核销", tableName, data);
    }

    @Override
    public String readExcelFile(MultipartFile file, ScmCwInvoice invoice) {
        try {
            String businessDivisionId = null;
            String businessDivision = null;
            String provinceId = null;
            String province = null;
            String commerceName = null;
            String commerceId = null;
            String commerceCode = null;
            //查询商业表信息数据
            String res = scmMasterDateFeignService.findByOwnerId(invoice.getOwnerId());

            JSONObject data = JSON.parseObject(res);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            List<String[]> list = POIUtil.readExcel(file);
            for (String[] s : list) {
                ScmCwInvoice product1 = new ScmCwInvoice();
                String totle = s[3];
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

                    product1.setMatchResult("商业名称匹配错误！");
                }

                product1.setGatherPersion(invoice.getGatherPersion());
                product1.setGathererDate(invoice.getGathererDate());
                product1.setOwnerId(invoice.getOwnerId());
                product1.setIsAffirm("0");
                product1.setCommerceName(totle);
                product1.setCommerceId(commerceId);
                product1.setProvince(province);//省区
                product1.setProvinceId(provinceId);
                product1.setBusinessDivision(businessDivision);
                product1.setBusinessDivisionId(businessDivisionId);
                product1.setFirstClassCommerce(s[1]);
                product1.setVerifyType("0");
                product1.setInvoiceType("0");
                product1.setInvoiceDate("".equals(s[4].trim()) ? null : sdf.parse(s[4]));
                product1.setUpdateInvoiceDate("".equals(s[5].trim()) ? null : sdf.parse(s[5]));
                product1.setInvoiceCode(s[6]);
                product1.setCommonName(s[7]);
                product1.setProductState(s[8]);
                product1.setProductSum(Integer.valueOf(s[9]));
                product1.setCommerceCode(s[10]);
                String product = scmMasterDateFeignService.getProductByCode(s[10]);
                if (product != null) {
                    JSONObject pro = JSON.parseObject(product);
                    product1.setProductName(pro.getString("productName"));
                } else {
                    product1.setProductName("商品名称未匹配到");
                }
                product1.setUnitPrice("".equals(s[11].trim()) ? null : Double.valueOf(s[11]));
                product1.setPriceTaxSum("".equals(s[12].trim()) ? null : Double.valueOf(s[12]));
                product1.setBalancePrice(product1.getPriceTaxSum());
                product1.setShipmentDate("".equals(s[13].trim()) ? null : sdf.parse(s[13]));
                product1.setBatchNumber(s[14]);
                product1.setShipmentCode(s[15]);
                product1.setContractCode(s[16]);
                //获取协议付款方式
                Map<String,Object> map  = scmCwInvoiceDao.getClauseInfoByContractCode(product1.getContractCode());
                if (map != null) {
                    product1.setRemittanceType((String) map.get("paymentType"));
                    Integer returnMoneyDay = (Integer) map.get("returnMoneyDay");
                    Date updateInvoiceDate = product1.getUpdateInvoiceDate();
                    if (updateInvoiceDate != null && returnMoneyDay!=null) {
                        Date date = DateFormatter.nextDay(updateInvoiceDate, returnMoneyDay);
                        product1.setRemittanceDate(date);
                    }
                } else {
                    product1.setRemittanceType("未匹配到协议信息");
                    product1.setRemittanceDate(null);
                }
                product1.setMemo(s[17]);
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
    public boolean verifExceedTime(String commerceCode) {
        List<ScmCwInvoice> scmCwInvoices = scmCwInvoiceDao.listByCommerceCode(commerceCode);
        if (scmCwInvoices.size() == 0) {
            return true;
        }
        ScmCwInvoice scmCwInvoice = scmCwInvoices.get(0);
        long time = new Date().getTime();
        long invoiceTime = 0;
        if (scmCwInvoice.getUpdateInvoiceDate() == null) {
            invoiceTime = scmCwInvoice.getInvoiceDate().getTime();
        } else {
            if (scmCwInvoice.getUpdateInvoiceDate() == null) {
                invoiceTime = new Date().getTime();
            } else {
                invoiceTime = scmCwInvoice.getUpdateInvoiceDate().getTime();
            }
        }
        long day = (time - invoiceTime) / 1000 / 60 / 60 / 24;
        return day <= 30;
    }
}
