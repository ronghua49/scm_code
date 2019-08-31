package com.winway.scm.persistence.manager.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import com.hotent.base.feign.BpmRuntimeFeignService;
import com.hotent.base.modelvo.CustomStartResult;
import com.hotent.base.modelvo.StartFlowParam;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.purchase.feign.ScmMasterDateFeignService;
import com.winway.scm.model.ScmCwBAPaymentDiscount;
import com.winway.scm.model.ScmCwBAStorageDiscount;
import com.winway.scm.model.ScmCwBusinessAccountRB;
import com.winway.scm.model.ScmCwDiscountPond;
import com.winway.scm.model.ScmCwPAnnualFee;
import com.winway.scm.model.ScmCwPPaymentDiscount;
import com.winway.scm.model.ScmCwPStorageDiscount;
import com.winway.scm.model.ScmCwPaymentRB;
import com.winway.scm.persistence.dao.ScmCwPAnnualFeeDao;
import com.winway.scm.persistence.dao.ScmCwPPaymentDiscountDao;
import com.winway.scm.persistence.dao.ScmCwPStorageDiscountDao;
import com.winway.scm.persistence.dao.ScmCwPaymentRBDao;
import com.winway.scm.persistence.manager.ScmCwBusinessAccountRBManager;
import com.winway.scm.persistence.manager.ScmCwDiscountPondManager;
import com.winway.scm.persistence.manager.ScmCwPPaymentDiscountManager;
import com.winway.scm.persistence.manager.ScmCwPStorageDiscountManager;
import com.winway.scm.persistence.manager.ScmCwPaymentRBManager;
import com.winway.scm.vo.DiscountBase;

/**
 * <pre>
 * 描述：经销商RB支付申请总表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-09 18:18:05
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmCwPaymentRBManager")
public class ScmCwPaymentRBManagerImpl extends AbstractManagerImpl<String, ScmCwPaymentRB> implements ScmCwPaymentRBManager {
    @Resource
    ScmCwPaymentRBDao scmCwPaymentRBDao;
    @Resource
    ScmCwPPaymentDiscountDao scmCwPPaymentDiscountDao;
    @Resource
    ScmCwPPaymentDiscountManager scmCwPPaymentDiscountManager;
    @Resource
    ScmCwPStorageDiscountDao scmCwPStorageDiscountDao;
    @Resource
    ScmCwPStorageDiscountManager scmCwPStorageDiscountManager;
    @Resource
    ScmCwPAnnualFeeDao scmCwPAnnualFeeDao;
    @Resource
    ScmCwBusinessAccountRBManager scmCwBusinessAccountRBManager;
    @Resource
    ScmCwDiscountPondManager scmCwDiscountPondManager;
    @Resource
    ScmMasterDateFeignService masterDateFeignService;
    @Resource
    BpmRuntimeFeignService bpmRuntimeFeignService;


    @Override
    protected MyBatisDao<String, ScmCwPaymentRB> getDao() {
        return scmCwPaymentRBDao;
    }

    /**
     * 创建实体包含子表实体
     */
    public void create(ScmCwPaymentRB scmCwPaymentRB) {
        String year1 = scmCwPaymentRB.getYear();
        String section = scmCwPaymentRB.getSection();
        if (StringUtils.isEmpty(year1) || StringUtils.isEmpty(section)) {
            throw new RuntimeException("年份和区间不得为空");
        }
        //校验是否多次重复申请
        ScmCwPaymentRB scmCwPaymentRB1 = scmCwPaymentRBDao.getByParam(scmCwPaymentRB.getOwnerId(), scmCwPaymentRB.getBusinessDivisionId(), scmCwPaymentRB.getProvinceId(), year1, section);
        if (scmCwPaymentRB1 != null) {
            throw new RuntimeException("同一个商务分区和省区半年内只能发起一次支付申请");
        }
        //校验年份和区间是否可发起支付申请
        if (Integer.parseInt(year1) > Calendar.getInstance().get(Calendar.YEAR)) {
            throw new RuntimeException("年份不得大于本年度");
        }
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        Date firstHalfYear = null;
        Date secondHalfYear = null;
        try {
            date = f.parse(f.format(date));
            firstHalfYear = f.parse(year1 + "-07-01 00:00:00");
            secondHalfYear = f.parse(Integer.parseInt(year1) + 1 + "-01-01 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("年份日期转换错误");
        }
        if (section.equals("0") && date.before(firstHalfYear)) {
            throw new RuntimeException(year1 + "年7月1日后可申请上半年");
        }
        if (section.equals("1") && date.before(secondHalfYear)) {
            throw new RuntimeException(Integer.parseInt(year1) + 1 + "年1月1日后可申请下半年");
        }
        super.create(scmCwPaymentRB);
        String pk = scmCwPaymentRB.getId();

        Map<String, Object> map = new HashedMap();
        map.put("businessDivisionId", scmCwPaymentRB.getBusinessDivisionId());
        map.put("provinceId", scmCwPaymentRB.getProvinceId());
        map.put("year", scmCwPaymentRB.getYear());
        map.put("section", scmCwPaymentRB.getSection());
        map.put("ownerId", scmCwPaymentRB.getOwnerId());
        //查询核算总表
        List<ScmCwBusinessAccountRB> list = scmCwBusinessAccountRBManager.queryByParamMap(map);
        if (list.isEmpty()) {
            throw new RuntimeException("暂无核算费用，不得发起支付申请");
        }
        for (ScmCwBusinessAccountRB accountRB : list) {
            List<ScmCwBAPaymentDiscount> scmCwBAPaymentDiscountList = accountRB.getScmCwBAPaymentDiscountList();
            for (ScmCwBAPaymentDiscount bap : scmCwBAPaymentDiscountList) {
                ScmCwPPaymentDiscount pp = new ScmCwPPaymentDiscount();
                pp.setMasterId(pk);
                pp.setCommerceId(bap.getCommerceId());
                pp.setCommerceName(bap.getCommerceName());
                pp.setCommerceCode(bap.getCommerceCode());
                pp.setBusinessDivision(bap.getBusinessDivision());
                pp.setBusinessDivisionId(bap.getBusinessDivisionId());
                pp.setProvince(bap.getProvince());
                pp.setProvinceId(bap.getProvinceId());
                pp.setCommerceLevel(bap.getCommerceLevel());
                pp.setClearForm(bap.getClearForm());
                pp.setProductName(bap.getProductName());
                pp.setContractCode(bap.getContractCode());
                pp.setInvoiceCode(bap.getInvoiceCode());
                pp.setPaymentType(bap.getPaymentType());
                pp.setPrice(bap.getPrice());
                pp.setRemittanceDate(bap.getRemittanceDate());
                pp.setAffirmDate(bap.getAffirmDate());
                pp.setInvoiceDate(bap.getInvoiceDate());
                pp.setUpdateInvoiceDate(bap.getUpdateInvoiceDate());
                pp.setReturnMoneyDay(bap.getReturnMoneyDay());
                pp.setRbRate(bap.getRbRate());
                pp.setTotalRbRate(bap.getTotalRbRate());
                pp.setThisCalculationRb(bap.getThisCalculationRb());
                pp.setInvoiceMemo(bap.getInvoiceMemo());
                pp.setThisShouldSendRb(bap.getThisShouldSendRb());
                pp.setMemo(bap.getMemo());
                pp.setBlendId(bap.getBlendId());
                scmCwPPaymentDiscountManager.create(pp);
            }
            List<ScmCwBAStorageDiscount> scmCwBAStorageDiscountList = accountRB.getScmCwBAStorageDiscountList();
            for (ScmCwBAStorageDiscount b : scmCwBAStorageDiscountList) {
                ScmCwPStorageDiscount p = new ScmCwPStorageDiscount();
                p.setMasterId(pk);
                p.setCommerceId(b.getCommerceId());
                p.setCommerceName(b.getCommerceName());
                p.setCommerceCode(b.getCommerceCode());
                p.setStorageDiscountPrice(b.getStorageDiscountPrice());
                p.setBusinessDivisionId(b.getBusinessDivisionId());
                p.setBusinessDivision(b.getBusinessDivision());
                p.setProvince(b.getProvince());
                p.setProvinceId(b.getProvinceId());
                p.setDiscountType(b.getDiscountType());
                p.setShipmentDate(b.getShipmentDate());
                p.setShipmentCode(b.getShipmentCode());
                p.setLoseEfficacyDate(b.getLoseEfficacyDate());
                p.setBatchNum(b.getBatchNum());
                p.setProductName(b.getProductName());
                p.setProductNum(b.getProductNum());
                p.setUpdateProductNum(b.getUpdateProductNum());
                p.setUnitPrice(b.getUnitPrice());
                p.setUpdateUnitPrice(b.getUpdateUnitPrice());
                p.setPrice(b.getPrice());
                p.setRbRate(b.getRbRate());
                p.setBlendId(b.getBlendId());
                scmCwPStorageDiscountManager.create(p);
            }
        }
        //todo 下半年计算年费
        if ("1".equals(scmCwPaymentRB.getSection())) {
            //获取商务分区和省区下企业年费列表
           String feeObjectList =  masterDateFeignService.getPAnnualFee(scmCwPaymentRB.getBusinessDivisionId(),scmCwPaymentRB.getProvinceId(),scmCwPaymentRB.getYear());
//            for (ScmCwPAnnualFee scmCwPAnnualFee : scmCwPAnnualFeeList) {
//                scmCwPAnnualFee.setMasterId(pk);
//                scmCwPAnnualFeeDao.create(scmCwPAnnualFee);
//            }
        }
    }

    /**
     * 删除记录包含子表记录
     */
    public void remove(String entityId) {
        super.remove(entityId);
        scmCwPPaymentDiscountDao.delByMainId(entityId);
        scmCwPStorageDiscountDao.delByMainId(entityId);
        scmCwPAnnualFeeDao.delByMainId(entityId);
    }

    /**
     * 批量删除包含子表记录
     */
    public void removeByIds(String[] entityIds) {
        for (String id : entityIds) {
            this.remove(id);
        }
    }

    /**
     * 获取实体
     */
    public ScmCwPaymentRB get(String entityId) {
        ScmCwPaymentRB scmCwPaymentRB = super.get(entityId);
        List<ScmCwPPaymentDiscount> scmCwPPaymentDiscountList = scmCwPPaymentDiscountDao.getByMainId(entityId);
        scmCwPaymentRB.setScmCwPPaymentDiscountList(scmCwPPaymentDiscountList);
        List<ScmCwPStorageDiscount> scmCwPStorageDiscountList = scmCwPStorageDiscountDao.getByMainId(entityId);
        scmCwPaymentRB.setScmCwPStorageDiscountList(scmCwPStorageDiscountList);
        List<ScmCwPAnnualFee> scmCwPAnnualFeeList = scmCwPAnnualFeeDao.getByMainId(entityId);
        scmCwPaymentRB.setScmCwPAnnualFeeList(scmCwPAnnualFeeList);
        return scmCwPaymentRB;
    }

    /**
     * 更新实体同时更新子表记录
     */
    public void update(ScmCwPaymentRB scmCwPaymentRB) {
        super.update(scmCwPaymentRB);
        String id = scmCwPaymentRB.getId();
        scmCwPPaymentDiscountDao.delByMainId(id);
        List<ScmCwPPaymentDiscount> scmCwPPaymentDiscountList = scmCwPaymentRB.getScmCwPPaymentDiscountList();
        for (ScmCwPPaymentDiscount scmCwPPaymentDiscount : scmCwPPaymentDiscountList) {
            scmCwPPaymentDiscount.setMasterId(id);
            scmCwPPaymentDiscountDao.create(scmCwPPaymentDiscount);
        }
        scmCwPStorageDiscountDao.delByMainId(id);
        List<ScmCwPStorageDiscount> scmCwPStorageDiscountList = scmCwPaymentRB.getScmCwPStorageDiscountList();
        for (ScmCwPStorageDiscount scmCwPStorageDiscount : scmCwPStorageDiscountList) {
            scmCwPStorageDiscount.setMasterId(id);
            scmCwPStorageDiscountDao.create(scmCwPStorageDiscount);
        }
        scmCwPAnnualFeeDao.delByMainId(id);
        List<ScmCwPAnnualFee> scmCwPAnnualFeeList = scmCwPaymentRB.getScmCwPAnnualFeeList();
        for (ScmCwPAnnualFee scmCwPAnnualFee : scmCwPAnnualFeeList) {
            scmCwPAnnualFee.setMasterId(id);
            scmCwPAnnualFeeDao.create(scmCwPAnnualFee);
        }
    }


    @Override
    public void sendApply(ScmCwPaymentRB scmCwPaymentRB) {
        ScmCwPaymentRB scmCwPaymentRB1 = scmCwPaymentRBDao.get(scmCwPaymentRB.getId());
        if (scmCwPaymentRB1 == null) {
            throw new RuntimeException("数据不存在");
        }
        StartFlowParam startFlowParam = new StartFlowParam("jxsRBzf", "SCM", "approvalId");
        startFlowParam.setFormType("frame");
        CustomStartResult customStartResult = null;
        try {
            System.out.println("发起经销商RB支付申请");
            customStartResult = bpmRuntimeFeignService.customStart(startFlowParam);
            String approvalId = customStartResult.getInstId();
            scmCwPaymentRB1.setApprovalState("1");
            scmCwPaymentRB1.setApprovalId(approvalId);
            super.update(scmCwPaymentRB1);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("启动工作流失败");
        }
    }

    @Override
    public void endApply(JsonNode jsonNode) {
        String approvalId = jsonNode.get("instId").asText();
        String actionName = jsonNode.get("actionName").asText();
        //根据审批id查询数据
        ScmCwPaymentRB scmCwPaymentRB1 = scmCwPaymentRBDao.getByApprovalState(approvalId);
        if (scmCwPaymentRB1 == null) {
            throw new RuntimeException("未查询到业务数据,处理异常");
        }
        String endEvent = jsonNode.get("eventType").asText();
        if ("agree".equals(actionName) && "endEvent".equals(endEvent)) {
        	 scmCwPaymentRB1.setApprovalState("2");
             //汇总经销商rb折扣
             List<ScmCwPPaymentDiscount> scmCwPPaymentDiscountList = scmCwPPaymentDiscountDao.getByMainId(scmCwPaymentRB1.getId());
             Map<String, DiscountBase> map = new HashMap<>();
             for (ScmCwPPaymentDiscount paymentDiscount : scmCwPPaymentDiscountList) {
                 if (map.containsKey(paymentDiscount.getCommerceId())) {
                     DiscountBase discountBase = map.get(paymentDiscount.getCommerceId());
                     discountBase.setRb(discountBase.getRb() + paymentDiscount.getThisShouldSendRb());
                     map.put(paymentDiscount.getCommerceId(), discountBase);
                 } else {
                     DiscountBase discountBase = new DiscountBase();
                     discountBase.setRb(paymentDiscount.getThisShouldSendRb());
                     discountBase.setCommerceCode(paymentDiscount.getCommerceCode());
                     discountBase.setCommerceId(paymentDiscount.getCommerceId());
                     discountBase.setCommerceLevel(paymentDiscount.getCommerceLevel());
                     discountBase.setCommerceName(paymentDiscount.getCommerceName());
                     map.put(paymentDiscount.getCommerceId(), discountBase);
                 }
             }
             List<ScmCwPStorageDiscount> scmCwPStorageDiscountList = scmCwPStorageDiscountDao.getByMainId(scmCwPaymentRB1.getId());
             Map<String, DiscountBase> map1 = new HashMap<>();
             for (ScmCwPStorageDiscount storageDiscount : scmCwPStorageDiscountList) {
                 if (map1.containsKey(storageDiscount.getCommerceId())) {
                     DiscountBase discountBase = map1.get(storageDiscount.getCommerceId());
                     discountBase.setRb(discountBase.getRb() + storageDiscount.getThisCalculationRb());
                     map.put(storageDiscount.getCommerceId(), discountBase);
                 } else {
                     DiscountBase discountBase = new DiscountBase();
                     discountBase.setRb(storageDiscount.getThisCalculationRb());
                     discountBase.setCommerceCode(storageDiscount.getCommerceCode());
                     discountBase.setCommerceId(storageDiscount.getCommerceId());
                     discountBase.setCommerceName(storageDiscount.getCommerceName());
                     map.put(storageDiscount.getCommerceId(), discountBase);
                 }
             }
             //合并到票折池
             Set<String> set = map1.keySet();
             for (String commerceId : set) {
                 if (map.containsKey(commerceId)) {
                     DiscountBase discountBase = map.get(commerceId);
                     discountBase.setRb(discountBase.getRb() + map1.get(commerceId).getRb());
                     map.put(commerceId, discountBase);
                 } else {
                     map.put(commerceId, map1.get(commerceId));
                 }
             }
             for (Map.Entry<String, DiscountBase> entry : map.entrySet()) {
                 ScmCwDiscountPond pond = new ScmCwDiscountPond();
                 pond.setDiscountSource("0");
                 pond.setBusinessDivision(scmCwPaymentRB1.getBusinessDivision());
                 pond.setProvince(scmCwPaymentRB1.getProvince());
                 pond.setBusinessDivisionId(scmCwPaymentRB1.getBusinessDivisionId());
                 pond.setProvinceId(scmCwPaymentRB1.getProvinceId());
                 pond.setCommerceId(entry.getKey());
                 pond.setCommerceCode(entry.getValue().getCommerceCode());
                 pond.setCommerceName(entry.getValue().getCommerceName());
                 pond.setCommerceLevel(entry.getValue().getCommerceLevel());
                 pond.setMayDeduction(entry.getValue().getRb());
                 pond.setBalance(entry.getValue().getRb());
                 pond.setOwnerId(scmCwPaymentRB1.getOwnerId());
                 pond.setDiscountCommerce(entry.getValue().getCommerceName());
                 scmCwDiscountPondManager.create(pond);
             }
         	super.update(scmCwPaymentRB1);
        } else if ("agree".equals(actionName)) {
        } else if ("reject".equals(actionName)) {
        } else if ("backToStart".equals(actionName)) {
        } else if ("opposeTrans".equals(actionName)) {
        } else if ("endProcess".equals(actionName)) {
        	scmCwPaymentRB1.setApprovalState("3");
        	super.update(scmCwPaymentRB1);
        }

    }

    @Override
    public ScmCwPaymentRB getDetail(String approvalId) {
        ScmCwPaymentRB byApprovalState = scmCwPaymentRBDao.getByApprovalState(approvalId);
        if (byApprovalState == null) {
            throw new RuntimeException("没有查询到审批数据");
        }
        return get(byApprovalState.getId());
    }
}
