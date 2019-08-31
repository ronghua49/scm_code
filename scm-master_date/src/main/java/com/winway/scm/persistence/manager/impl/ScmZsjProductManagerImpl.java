package com.winway.scm.persistence.manager.impl;

import java.util.*;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.hotent.base.feign.BpmRuntimeFeignService;
import com.hotent.base.modelvo.CustomStartResult;
import com.hotent.base.modelvo.StartFlowParam;
import com.hotent.base.util.AuthenticationUtil;
import com.winway.scm.model.*;
import com.winway.scm.persistence.dao.ScmZsjProductFirstDao;

import org.apache.http.auth.AUTH;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.pagehelper.PageHelper;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.model.CommonResult;
import com.hotent.base.model.WinwayResult;
import com.hotent.base.query.PageBean;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.BeanUtils;
import com.winway.purchase.feign.ScmMasterDateFeignService;
import com.winway.purchase.tool.ScmWmsConfiguration;
import com.winway.purchase.util.ContrasUtil;
import com.winway.purchase.util.HttpClientUtil;
import com.winway.purchase.util.QuarterUtil;
import com.winway.scm.persistence.dao.ScmZsjAchageDao;
import com.winway.scm.persistence.dao.ScmZsjProductDao;
import com.winway.scm.persistence.dao.ScmZsjProductLineDao;
import com.winway.scm.persistence.manager.ScmZsjProductManager;
import com.winway.scm.persistence.vo.ProductVo;

import springfox.documentation.spring.web.json.Json;

/**
 * <pre>
 * 描述：商品表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmZsjProductManager")
public class ScmZsjProductManagerImpl extends AbstractManagerImpl<String, ScmZsjProduct> implements ScmZsjProductManager {
    @Resource
    ScmZsjProductDao scmZsjProductDao;
    @Resource
    ScmZsjProductFirstDao productFirstDao;
    @Resource
    ScmZsjAchageDao scmZsjAchageDao;
    @Resource
    ScmZsjProductLineDao scmZsjProductLineDao;
    @Resource
    ScmMasterDateFeignService scmMasterDateFeignService;
    @Resource
    BpmRuntimeFeignService bpmRuntimeFeignService;

    @Override
    protected MyBatisDao<String, ScmZsjProduct> getDao() {
        return scmZsjProductDao;
    }

    @Override
    public List<ScmZsjProduct> getByProduct(String ownerId) {
        return scmZsjProductDao.getByProduct(ownerId);
    }

    @Override
    public void getByNameOrId(ScmZsjProduct scmZsjProduct) {
        //根据名称与ID判断是否已经存在当前使用名称
        List<ScmZdDutyAffirm> sdas = scmZsjProductDao.getByNameOrId(scmZsjProduct);
        if (sdas.size() > 0) {
            throw new RuntimeException("当前名称已经被使用,请修改后提交");
        }

    }

    @Override
    public PageList<ScmZsjProduct> firstList(QueryFilter queryFilter) {
        PageBean pageBean = queryFilter.getPageBean();
        if (BeanUtils.isEmpty(pageBean)) {
            PageHelper.startPage(1, Integer.MAX_VALUE, false);
        } else {
            PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
        }
        List<ScmZsjProduct> query = scmZsjProductDao.firstList(queryFilter.getParams());
        return new PageList<ScmZsjProduct>(query);
    }

    @Override
    public List<ScmZsjProduct> downBox(String ownerId) {
        List<ScmZsjProduct> query = scmZsjProductDao.downBox(ownerId);
        List<ScmZsjProduct> scmZsjProduct = new ArrayList<>();
        if (query.size() != 0) {
            for (ScmZsjProduct scmZsjProduct1 : query) {
                List<ScmZsjAchage> scmZsjAchage = scmZsjAchageDao.getByMainId(scmZsjProduct1.getId());
                scmZsjProduct1.setScmZsjAchageList(scmZsjAchage);
                scmZsjProduct.add(scmZsjProduct1);
            }
        }
        return scmZsjProduct;
    }

    @Override
    public void isExist(ScmZsjProduct scmZsjProduct) {
        if (scmZsjProduct == null) {
            throw new RuntimeException("商品信息不能为空");
        }
        //有商业ID 说明该商品已经存在
        if (scmZsjProduct.getId() != null) {
            return;
        }
        if (scmZsjProduct.getProductName() == null) {
            throw new RuntimeException("商品名称不能为空");
        }
        //根据名称查询该商品
        List<ScmZsjProduct> byName = scmZsjProductDao.getByName(scmZsjProduct.getProductName());
        //可以查询到,直接返回
        if (byName.size() == 0) {
            //没有查询到,保存
            scmZsjProductDao.create(scmZsjProduct);
        }
    }

    @Override
    public List<ScmZsjProduct> downBoxApplySuccess(String ownerId, String SupplierId) {
        String accreditProCodes = scmMasterDateFeignService.getAccreditProCodes(SupplierId);
        Set<String> set = JSON.parseObject(accreditProCodes, new TypeReference<Set<String>>() {
        });
        return scmZsjProductDao.downBoxApplySuccess(ownerId, SupplierId, set);
    }

    @Override
    public PageList<ScmZsjProduct> query(QueryFilter queryFilter) {
        PageBean pageBean = queryFilter.getPageBean();
        if (BeanUtils.isEmpty(pageBean)) {
            PageHelper.startPage(1, Integer.MAX_VALUE, false);
        } else {
            PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
        }
        List<ScmZsjProduct> query = scmZsjProductDao.query(queryFilter.getParams());
        if (query.size() != 0) {
            for (ScmZsjProduct scmZsjProduct1 : query) {
                List<ScmZsjAchage> scmZsjAchage = scmZsjAchageDao.getByMainId(scmZsjProduct1.getId());
                scmZsjProduct1.setScmZsjAchageList(scmZsjAchage);
            }
        }
        return new PageList<ScmZsjProduct>(query);
    }


    @Override
    public List<Map<String, Object>> LineAndProductDownBox() {
        List<ScmZsjProductLine> query = scmZsjProductLineDao.query(new HashMap<>());
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (ScmZsjProductLine scmZsjProductLine : query) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", scmZsjProductLine.getId());
            map.put("lineName", scmZsjProductLine.getProductLineName());
            List<ScmZsjProduct> listByLineId = scmZsjProductDao.listByLineId(scmZsjProductLine.getId());
            map.put("productList", listByLineId);
            list.add(map);
        }
        return list;
    }

    @Override
    public List<String> listAndAchage(String productCode) {
        List<Map<String, Object>> achages = scmZsjProductDao.listAndAchage(productCode);
        List<String> list = new ArrayList<>();
        for (Map<String, Object> map : achages) {
            String string = map.get("achage").toString();
            list.add(string);
        }
        return list;
    }

    @Override
    public ScmZsjProduct getProductByCode(String productCode) {
        return scmZsjProductDao.getProductByCode(productCode);

    }

    @Override
    public void create(ScmZsjProduct arg0) {
        String code = scmZsjProductDao.getProductCode();
        arg0.setProductCode(code);
        //同步至主数据中心
        ScmZsjProductLine scmZsjProductLine = scmZsjProductLineDao.get(arg0.getProductLineId());
        ProductVo productVo = new ProductVo();
        productVo.setAchangeList(new ArrayList());
        productVo.setId(arg0.getId());
        productVo.setProdCode(arg0.getProductCode());
        productVo.setProdName(arg0.getProductName());
        productVo.setProdCatCode(scmZsjProductLine.getProductLineCode());
        productVo.setProdCatName(scmZsjProductLine.getProductLineName());
        productVo.setProdGenName(arg0.getCommonName());
        productVo.setProdSize(arg0.getProductState());
        productVo.setProdDosage(arg0.getAgentType());
        productVo.setProdUnit(arg0.getUnit());
        productVo.setOTC(arg0.getOtc());
        productVo.setDrugType(arg0.getMedicineType());
        productVo.setDrugCategory(arg0.getMedicineClassify());
        productVo.setStorageRange(arg0.getKeepingCondition());
        productVo.setProducer(arg0.getManufacturer());
        productVo.setStoreId(arg0.getSuggestWarehouse());
        productVo.setInventoryCode(arg0.getInventoryCode());
        productVo.setDrugTypeId(arg0.getMedicineTypeId());
        productVo.setDrugCategoryId(arg0.getMedicineClassifyId());
        productVo.setProdDosageId(arg0.getAgentType());
        productVo.setIsDelete(0);
        productVo.setStatus(1);
        productVo.setCreateBy(AuthenticationUtil.getCurrentUsername());
        productVo.setCreateName(AuthenticationUtil.getCurrentUserFullname());
        List<ProductVo> list = new ArrayList<ProductVo>();
        list.add(productVo);
        String jsonString = JSON.toJSONString(list);
        Map<String, String> header = new HashMap<String, String>();
        header.put("accept", "application/json;charset=UTF-8");
		header.put("Authorization", ScmWmsConfiguration.MASTER_TOKEN);
        header.put("Content-Type", "application/json");
        String returnStr = HttpClientUtil.doPostJson(ScmWmsConfiguration.MASTER_DATE_URL + "/masterdata/product/v1/saveList", jsonString, header);
        CommonResult parseObject = JSON.parseObject(returnStr, CommonResult.class);
        String code2 = parseObject.getCode();
        if ("10000".equals(code2)) {
            arg0.setIsToMasterData("1");
            super.create(arg0);
        } else {
            arg0.setIsToMasterData("0");
            super.create(arg0);
        }
    }

    @Override
    public void update(ScmZsjProduct scmZsjProduct) {
        String id = scmZsjProduct.getId();
        List<ScmZsjProduct> list = scmZsjProductDao.getbyMainId(id);
        try {
            if (list.size() > 0) {
                ScmZsjProduct scmZsjProduct2 = list.get(0);
                scmZsjProduct.setProductCode(scmZsjProduct2.getProductCode());
                String contrast = ContrasUtil.Contrast(list.get(0), scmZsjProduct, id, "sp");
                if (!StringUtils.isEmpty(contrast)) {
                    scmMasterDateFeignService.inserScmLogRecord(contrast);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.update(scmZsjProduct);
    }

    @Override
    public List<Map<String, Object>> LineAndProductDownBoxByfirst(String ownerId) {
        List<ScmZsjProductLine> query = scmZsjProductLineDao.query(new HashMap<>());
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (ScmZsjProductLine scmZsjProductLine : query) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", scmZsjProductLine.getId());
            map.put("lineName", scmZsjProductLine.getProductLineName());
            List<ScmZsjProduct> listByLineId = scmZsjProductDao.listByLineIdAndFirst(scmZsjProductLine.getId(), ownerId);
            for (ScmZsjProduct product : listByLineId) {
                List<Map<String, String>> achageAndCodes = new ArrayList<>();
                List<ScmZsjAchage> allByMainId = scmZsjAchageDao.getAllByMainId(product.getId());
                for (ScmZsjAchage achage : allByMainId) {
                    Map<String, String> map1 = new HashMap<>();
                    map1.put("achage", achage.getAchage());
                    map1.put("code", achage.getCode());
                    achageAndCodes.add(map1);
                }
                product.setAchageList(achageAndCodes);
            }
            map.put("productList", listByLineId);
            list.add(map);
        }
        return list;
    }

    @Override
    public void endApply(JsonNode jsonNode) {
        String approvalId = jsonNode.get("instId").asText();
        String actionName = jsonNode.get("actionName").asText();
        ScmZsjProduct arg0 = scmZsjProductDao.getByApprovalId(approvalId);
        if (arg0 == null) {
            throw new RuntimeException("未查询到业务数据,处理异常");
        }
        String endEvent = jsonNode.get("eventType").asText();
        if ("agree".equals(actionName) && "endEvent".equals(endEvent)) {
        	 //审批状态调整为通过
        	arg0.setApprovalState("2");
        	scmZsjProductDao.update(arg0);
            ScmZsjProductLine scmZsjProductLine = scmZsjProductLineDao.get(arg0.getProductLineId());
            ProductVo productVo = new ProductVo();
            productVo.setAchangeList(new ArrayList());
            productVo.setId(arg0.getId());
            productVo.setProdCode(arg0.getProductCode());
            productVo.setProdName(arg0.getProductName());
            productVo.setProdCatCode(scmZsjProductLine.getProductLineCode());
            productVo.setProdCatName(scmZsjProductLine.getProductLineName());
            productVo.setProdGenName(arg0.getCommonName());
            productVo.setProdSize(arg0.getProductState());
            productVo.setProdDosage(arg0.getAgentType());
            productVo.setProdUnit(arg0.getUnit());
            productVo.setOTC(arg0.getOtc());
            productVo.setDrugType(arg0.getMedicineType());
            productVo.setDrugCategory(arg0.getMedicineClassify());
            productVo.setStorageRange(arg0.getKeepingCondition());
            productVo.setProducer(arg0.getManufacturer());
            productVo.setStoreId(arg0.getSuggestWarehouse());
            productVo.setInventoryCode(arg0.getInventoryCode());
            productVo.setDrugTypeId(arg0.getMedicineTypeId());
            productVo.setDrugCategoryId(arg0.getMedicineClassifyId());
            productVo.setProdDosageId(arg0.getAgentType());
            productVo.setIsDelete(0);
            productVo.setStatus(1);
            productVo.setUpdateBy(AuthenticationUtil.getCurrentUsername());
            productVo.setUpdateName(AuthenticationUtil.getCurrentUserFullname());
            List<ProductVo> list = new ArrayList<ProductVo>();
            list.add(productVo);
            String jsonString = JSON.toJSONString(list);
            Map<String, String> header = new HashMap<String, String>();
            header.put("accept", "application/json;charset=UTF-8");
    		header.put("Authorization", ScmWmsConfiguration.MASTER_TOKEN);
            header.put("Content-Type", "application/json");
            try {
                String returnStr = HttpClientUtil.doPostJson(ScmWmsConfiguration.MASTER_DATE_URL + "/masterdata/product/v1/saveList", jsonString, header);
                CommonResult parseObject = JSON.parseObject(returnStr, CommonResult.class);
                String code2 = parseObject.getCode();
                if ("10000".equals(code2)) {
                    arg0.setIsToMasterData("1");
                } else {
                    arg0.setIsToMasterData("1");
                }
            } catch (Exception e) {
                arg0.setIsToMasterData("0");
            }
            scmZsjProductDao.update(arg0);
        } else if ("agree".equals(actionName)) {
        } else if ("reject".equals(actionName)) {
        } else if ("backToStart".equals(actionName)) {
        } else if ("opposeTrans".equals(actionName)) {
        } else if ("endProcess".equals(actionName)) {
        	arg0.setApprovalState("3");
        	scmZsjProductDao.update(arg0);
        }
    }

    @Override
    public void sendApply(ScmZsjProduct scmZsjProduct) {
        ScmZsjProduct scmZsjProduct1 = scmZsjProductDao.get(scmZsjProduct.getId());
        if (scmZsjProduct1 == null) {
            //新增
            scmZsjProduct.setApprovalState("1");
            create(scmZsjProduct);
        } else {
            //修改
            if ("1".equals(scmZsjProduct.getApprovalState())) {
                throw new RuntimeException("当前数据已提交审批,请勿重复提交");
            }
            scmZsjProduct.setOperatorDate(scmZsjProduct.getOperatorDate());
            scmZsjProduct.setOperatorName(scmZsjProduct.getOperatorName());
            scmZsjProduct.setApprovalState("1");
            update(scmZsjProduct);
            StartFlowParam startFlowParam = new StartFlowParam("sp", "SCM", "approvalId");
            startFlowParam.setFormType("frame");
            CustomStartResult customStartResult = null;
            try {
                System.out.println("发起商品修改申请");
                customStartResult = bpmRuntimeFeignService.customStart(startFlowParam);
                String approvalId = customStartResult.getInstId();
                scmZsjProduct.setApprovalId(approvalId);
                super.update(scmZsjProduct);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("发起流程失败");
            }
        }
    }

    @Override
    public ScmZsjProduct getByApprovalId(String approvalId) {
        ScmZsjProduct byApprovalId = scmZsjProductDao.getByApprovalId(approvalId);
        return byApprovalId;
    }

    @Override
    public List<ScmZsjProduct> productAndAchage(QueryFilter queryFilter) {
        PageBean pageBean = queryFilter.getPageBean();
        if (BeanUtils.isEmpty(pageBean)) {
            PageHelper.startPage(1, Integer.MAX_VALUE, false);
        } else {
            PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
        }
        List<ScmZsjProduct> query = scmZsjProductDao.productAndAchage(queryFilter.getParams());
        for (ScmZsjProduct product : query) {
            product.setProductNameStr(product.getProductName() + "|" + product.getAchage() + "件");
        }
        return query;
    }

    @Override
    public void remove(String entityId) {
        List<ScmZsjProductFirst> productFirsts = productFirstDao.getByProId(entityId);
        if (productFirsts != null && !productFirsts.isEmpty()) {
            throw new RuntimeException("改商品已经首营不得删除");
        }
        ScmZsjProduct arg0 = scmZsjProductDao.get(entityId);
        //同步数据
        ScmZsjProductLine scmZsjProductLine = scmZsjProductLineDao.get(arg0.getProductLineId());
        ProductVo productVo = new ProductVo();
        productVo.setAchangeList(new ArrayList());
        productVo.setId(arg0.getId());
        productVo.setProdCode(arg0.getProductCode());
        productVo.setProdName(arg0.getProductName());
        productVo.setProdCatCode(scmZsjProductLine.getProductLineCode());
        productVo.setProdCatName(scmZsjProductLine.getProductLineName());
        productVo.setProdGenName(arg0.getCommonName());
        productVo.setProdSize(arg0.getProductState());
        productVo.setProdDosage(arg0.getAgentType());
        productVo.setProdUnit(arg0.getUnit());
        productVo.setOTC(arg0.getOtc());
        productVo.setDrugType(arg0.getMedicineType());
        productVo.setDrugCategory(arg0.getMedicineClassify());
        productVo.setStorageRange(arg0.getKeepingCondition());
        productVo.setProducer(arg0.getManufacturer());
        productVo.setStoreId(arg0.getSuggestWarehouse());
        productVo.setInventoryCode(arg0.getInventoryCode());
        productVo.setDrugTypeId(arg0.getMedicineTypeId());
        productVo.setDrugCategoryId(arg0.getMedicineClassifyId());
        productVo.setProdDosageId(arg0.getAgentType());
        productVo.setIsDelete(1);
        productVo.setStatus(1);
        List<ProductVo> list = new ArrayList<ProductVo>();
        list.add(productVo);
        String jsonString = JSON.toJSONString(list);
        Map<String, String> header = new HashMap<String, String>();
        header.put("accept", "application/json;charset=UTF-8");
		header.put("Authorization", ScmWmsConfiguration.MASTER_TOKEN);
        header.put("Content-Type", "application/json");
        String returnStr = HttpClientUtil.doPostJson(ScmWmsConfiguration.MASTER_DATE_URL + "/masterdata/product/v1/saveList", jsonString, header);
        CommonResult parseObject = JSON.parseObject(returnStr, CommonResult.class);
        String code2 = parseObject.getCode();
        if ("10000".equals(code2)) {
            super.remove(entityId);
        } else {
            throw new RuntimeException(parseObject.getMessage());
        }
    }

    @Override
    public void updateSyn(String id) {
        ScmZsjProduct arg0 = scmZsjProductDao.get(id);
        if (arg0 == null) {
            throw new RuntimeException("数据不存在");
        }
        if ("1".equals(arg0.getIsToMasterData())) {
            throw new RuntimeException("当前数据已经推送至主数据中心");
        }
        CommonResult parseObject = new CommonResult<>();
        try {
            ScmZsjProductLine scmZsjProductLine = scmZsjProductLineDao.get(arg0.getProductLineId());
            ProductVo productVo = new ProductVo();
            productVo.setAchangeList(new ArrayList());
            productVo.setId(arg0.getId());
            productVo.setProdCode(arg0.getProductCode());
            productVo.setProdName(arg0.getProductName());
            productVo.setProdCatCode(scmZsjProductLine.getProductLineCode());
            productVo.setProdCatName(scmZsjProductLine.getProductLineName());
            productVo.setProdGenName(arg0.getCommonName());
            productVo.setProdSize(arg0.getProductState());
            productVo.setProdDosage(arg0.getAgentType());
            productVo.setProdUnit(arg0.getUnit());
            productVo.setOTC(arg0.getOtc());
            productVo.setDrugType(arg0.getMedicineType());
            productVo.setDrugCategory(arg0.getMedicineClassify());
            productVo.setStorageRange(arg0.getKeepingCondition());
            productVo.setProducer(arg0.getManufacturer());
            productVo.setStoreId(arg0.getSuggestWarehouse());
            productVo.setInventoryCode(arg0.getInventoryCode());
            productVo.setDrugTypeId(arg0.getMedicineTypeId());
            productVo.setDrugCategoryId(arg0.getMedicineClassifyId());
            productVo.setProdDosageId(arg0.getAgentType());
            productVo.setIsDelete(0);
            productVo.setStatus(1);
            List<ProductVo> list = new ArrayList<ProductVo>();
            list.add(productVo);
            String jsonString = JSON.toJSONString(list);
            Map<String, String> header = new HashMap<String, String>();
            header.put("accept", "application/json;charset=UTF-8");
    		header.put("Authorization", ScmWmsConfiguration.MASTER_TOKEN);
            header.put("Content-Type", "application/json");
            String returnStr = HttpClientUtil.doPostJson(ScmWmsConfiguration.MASTER_DATE_URL + "/masterdata/product/v1/saveList", jsonString, header);
            parseObject = JSON.parseObject(returnStr, CommonResult.class);
        } catch (Exception e) {
            // TODO: handle exception
        }
        if ("10000".equals(parseObject.getCode())) {
            arg0.setIsToMasterData("1");
            scmZsjProductDao.update(arg0);
        } else {
            arg0.setIsToMasterData("0");
            scmZsjProductDao.update(arg0);
        }
    }

    @Override
    public ScmZsjProduct getProductByName(String productName) {
        return scmZsjProductDao.getProductByName(productName);
    }

    @Override
    public List<ScmZsjProduct> getProductByCommonName(String commonName) {
        return scmZsjProductDao.getProductByCommonName(commonName);
    }

    @Override
    public List<ScmZsjProduct> getProductByCommonNameAndState(String commonName, String productState) {
        return scmZsjProductDao.getProductByCommonNameAndState(commonName,productState);
    }
}
