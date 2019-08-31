package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import com.hotent.base.feign.BpmRuntimeFeignService;
import com.hotent.base.modelvo.CustomStartResult;
import com.hotent.base.modelvo.StartFlowParam;
import org.springframework.stereotype.Service;
import java.util.List;
import com.winway.scm.persistence.dao.ScmCwDistributorFlowCostDao;
import com.winway.purchase.feign.ScmMasterDateFeignService;
import com.winway.scm.model.ScmCwDiscountPond;
import com.winway.scm.model.ScmCwDistributorFlowCost;
import com.winway.scm.persistence.dao.ScmCwDistributorRebateDao;
import com.winway.scm.model.ScmCwDistributorRebate;
import com.winway.scm.persistence.dao.ScmCwDistributorRbCollectDao;
import com.winway.scm.model.ScmCwDistributorRbCollect;
import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.model.CommonResult;
import com.winway.scm.persistence.dao.ScmCwDistributorRBDao;
import com.winway.scm.model.ScmCwDistributorRB;
import com.winway.scm.persistence.manager.ScmCwDiscountPondManager;
import com.winway.scm.persistence.manager.ScmCwDistributorFlowCostManager;
import com.winway.scm.persistence.manager.ScmCwDistributorRBManager;
import com.winway.scm.persistence.manager.ScmCwDistributorRbCollectManager;
import com.winway.scm.persistence.manager.ScmCwDistributorRebateManager;
import com.winway.scm.vo.ScmCwDistributorAgreementVo;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * <pre> 
 * 描述：分销商RB申请主表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-14 16:38:31
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmCwDistributorRBManager")
public class ScmCwDistributorRBManagerImpl extends AbstractManagerImpl<String, ScmCwDistributorRB> implements ScmCwDistributorRBManager{
	@Resource
	ScmCwDistributorRBDao scmCwDistributorRBDao;
	@Resource
	ScmCwDistributorFlowCostDao scmCwDistributorFlowCostDao;
	@Resource
	ScmCwDistributorFlowCostManager scmCwDistributorFlowCostManager;
	@Resource
	ScmCwDistributorRebateDao scmCwDistributorRebateDao;
	@Resource
	ScmCwDistributorRebateManager scmCwDistributorRebateManager;
	@Resource
	ScmCwDistributorRbCollectDao scmCwDistributorRbCollectDao;
	@Resource
	ScmCwDistributorRbCollectManager scmCwDistributorRbCollectManager;
	@Resource
	ScmMasterDateFeignService scmMasterDateFeignService;
	@Resource
	ScmCwDiscountPondManager scmCwDiscountPondManager;
	@Resource
	BpmRuntimeFeignService bpmRuntimeFeignService;
	@Override
	protected MyBatisDao<String, ScmCwDistributorRB> getDao() {
		return scmCwDistributorRBDao;
	}
	/**
	 * 创建实体包含子表实体
	 */
	public void create(ScmCwDistributorRB scmCwDistributorRB){
		String ownerId = scmCwDistributorRB.getOwnerId();
		String year = scmCwDistributorRB.getYear();
		String businessDivisionId = scmCwDistributorRB.getBusinessDivisionId();
		String provinceId = scmCwDistributorRB.getProvinceId();
		List<ScmCwDistributorRB> listByYear = scmCwDistributorRBDao.listByYear(businessDivisionId,provinceId,year);
		if(listByYear.size() > 0) {
			throw new RuntimeException(scmCwDistributorRB.getBusinessDivision() + scmCwDistributorRB.getProvince() + "已经发起"+year+"年的申请,请勿重复发起!");
		}
    	super.create(scmCwDistributorRB);
    	String pk=scmCwDistributorRB.getId();
    	//根据年度查询分销商协议,遍历分销商协议,生成流向信息管理费与折扣及汇总
    	CommonResult<String> rb = scmMasterDateFeignService.getRB(ownerId);
    	double d = Double.parseDouble(rb.getValue());
    	//查询分销商协议
    	List<ScmCwDistributorAgreementVo> scmCwDistributorAgreementVos =  scmCwDistributorRBDao.ListDistributorAgreement(ownerId,year,businessDivisionId,provinceId);
    	if(scmCwDistributorAgreementVos.size() == 0) {
    		throw new RuntimeException("当前省区下未查询到分销商协议");
    	}
    	for (ScmCwDistributorAgreementVo scmCwDistributorAgreementVo : scmCwDistributorAgreementVos) {
			//生成流向管理费
    		ScmCwDistributorFlowCost ScmCwDistributorFlowCost = new ScmCwDistributorFlowCost();
    		ScmCwDistributorFlowCost.setMasterId(pk);
    		ScmCwDistributorFlowCost.setBusinessDivision(scmCwDistributorRB.getBusinessDivision());
    		ScmCwDistributorFlowCost.setBusinessDivisionId(scmCwDistributorRB.getBusinessDivisionId());
    		ScmCwDistributorFlowCost.setProvince(scmCwDistributorRB.getProvince());
    		ScmCwDistributorFlowCost.setProvinceId(scmCwDistributorRB.getProvinceId());
    		ScmCwDistributorFlowCost.setCommerceId(scmCwDistributorAgreementVo.getCommerceId());
    		ScmCwDistributorFlowCost.setCommerceCode(scmCwDistributorAgreementVo.getCommerceCode());
    		ScmCwDistributorFlowCost.setCommerceName(scmCwDistributorAgreementVo.getCommerceName());
    		ScmCwDistributorFlowCost.setAcceptState(scmCwDistributorAgreementVo.getAcceptStateStr());
    		ScmCwDistributorFlowCost.setSignType("普通");
    		ScmCwDistributorFlowCost.setFlowManagementGrade(scmCwDistributorAgreementVo.getIntegral());
    		ScmCwDistributorFlowCost.setSystemCount(scmCwDistributorAgreementVo.getMessageCost() == null ? 0.0:scmCwDistributorAgreementVo.getMessageCost());
    		ScmCwDistributorFlowCost.setThisTimeApply(ScmCwDistributorFlowCost.getSystemCount());
    		ScmCwDistributorFlowCost.setMemo("");
    		//生成返利明细
    		ScmCwDistributorRebate scmCwDistributorRebate = new ScmCwDistributorRebate();
    		scmCwDistributorRebate.setMasterId(pk);
    		scmCwDistributorRebate.setBusinessDivision(scmCwDistributorRB.getBusinessDivision());
    		scmCwDistributorRebate.setBusinessDivisionId(scmCwDistributorRB.getBusinessDivisionId());
    		scmCwDistributorRebate.setProvince(scmCwDistributorRB.getProvince());
    		scmCwDistributorRebate.setProvinceId(scmCwDistributorRB.getProvinceId());
    		scmCwDistributorRebate.setCommerceId(scmCwDistributorAgreementVo.getCommerceId());
    		scmCwDistributorRebate.setCommerceCode(scmCwDistributorAgreementVo.getCommerceCode());
    		scmCwDistributorRebate.setCommerceName(scmCwDistributorAgreementVo.getCommerceName());
    		scmCwDistributorRebate.setAcceptState(scmCwDistributorAgreementVo.getAcceptStateStr());
    		scmCwDistributorRebate.setIsSign("1");
    		//从财务上传的数据中查询促销折让金额
    		scmCwDistributorRebate.setPromotionMoney(0.0);
    		scmCwDistributorRebate.setPromotionProportion(2.0);
    		scmCwDistributorRebate.setThisTimeCount(scmCwDistributorRebate.getPromotionMoney() * (scmCwDistributorRebate.getPromotionProportion() / 100) / d);
    		scmCwDistributorRebate.setThisTimeShell(scmCwDistributorRebate.getThisTimeCount());
    		scmCwDistributorRebate.setMemo("");
    		//生成汇总
    		ScmCwDistributorRbCollect scmCwDistributorRbCollect = new ScmCwDistributorRbCollect();
    		scmCwDistributorRbCollect.setMasterId(pk);
    		scmCwDistributorRbCollect.setBusinessDivision(scmCwDistributorRB.getBusinessDivision());
    		scmCwDistributorRbCollect.setBusinessDivisionId(scmCwDistributorRB.getBusinessDivisionId());
    		scmCwDistributorRbCollect.setProvince(scmCwDistributorRB.getProvince());
    		scmCwDistributorRbCollect.setProvinceId(scmCwDistributorRB.getProvinceId());
    		scmCwDistributorRbCollect.setCommerceId(scmCwDistributorAgreementVo.getCommerceId());
    		scmCwDistributorRbCollect.setCommerceCode(scmCwDistributorAgreementVo.getCommerceCode());
    		scmCwDistributorRbCollect.setCommerceName(scmCwDistributorAgreementVo.getCommerceName());
    		scmCwDistributorRbCollect.setThisCalculationRb(ScmCwDistributorFlowCost.getThisTimeApply() + scmCwDistributorRebate.getThisTimeShell());
    		scmCwDistributorRbCollect.setDeduct(0.0);
    		scmCwDistributorRbCollect.setTheRealHair(scmCwDistributorRbCollect.getThisCalculationRb() - scmCwDistributorRbCollect.getDeduct());
    		scmCwDistributorRbCollect.setPaymentMethod("票面折让");
    		if(scmCwDistributorAgreementVo.getProvincePlatform() != null) {
    			scmCwDistributorRbCollect.setGetMoneyCommerce(scmCwDistributorAgreementVo.getProvincePlatform());
    			scmCwDistributorRbCollect.setGetMoneyCommerceCode(scmCwDistributorAgreementVo.getProvincePlatformCode());
    			scmCwDistributorRbCollect.setGetMoneyCommerceId(scmCwDistributorAgreementVo.getProvincePlatformId());
    		}else{
    			scmCwDistributorRbCollect.setGetMoneyCommerce(scmCwDistributorAgreementVo.getUpCommerce());
    			scmCwDistributorRbCollect.setGetMoneyCommerce(scmCwDistributorAgreementVo.getUpCommerceCode());
    			scmCwDistributorRbCollect.setGetMoneyCommerceId(scmCwDistributorAgreementVo.getUpCommerceId());
    		}
    		scmCwDistributorRbCollect.setMemo("");
    		scmCwDistributorFlowCostManager.create(ScmCwDistributorFlowCost);
    		scmCwDistributorRebateManager.create(scmCwDistributorRebate);
    		scmCwDistributorRbCollectManager.create(scmCwDistributorRbCollect);
		}
    	
    	List<ScmCwDistributorRebate> scmCwDistributorRebateList=scmCwDistributorRB.getScmCwDistributorRebateList();
    	
    	for(ScmCwDistributorRebate scmCwDistributorRebate:scmCwDistributorRebateList){
    		scmCwDistributorRebate.setMasterId(pk);
    		scmCwDistributorRebateDao.create(scmCwDistributorRebate);
    	}
    	
    	List<ScmCwDistributorRbCollect> scmCwDistributorRbCollectList=scmCwDistributorRB.getScmCwDistributorRbCollectList();
    	
    	for(ScmCwDistributorRbCollect scmCwDistributorRbCollect:scmCwDistributorRbCollectList){
    		scmCwDistributorRbCollect.setMasterId(pk);
    		scmCwDistributorRbCollectDao.create(scmCwDistributorRbCollect);
    	}
    }
	
	/**
	 * 删除记录包含子表记录
	 */
	public void remove(String entityId){
		super.remove(entityId);
    	scmCwDistributorFlowCostDao.delByMainId(entityId);
    	scmCwDistributorRebateDao.delByMainId(entityId);
    	scmCwDistributorRbCollectDao.delByMainId(entityId);
	}
	
	/**
	 * 批量删除包含子表记录
	 */
	public void removeByIds(String[] entityIds){
		for(String id:entityIds){
			this.remove(id);
		}
	}
    
	/**
	 * 获取实体
	 */
    public ScmCwDistributorRB get(String entityId){
    	ScmCwDistributorRB scmCwDistributorRB=super.get(entityId);
    	List<ScmCwDistributorFlowCost> scmCwDistributorFlowCostList=scmCwDistributorFlowCostDao.getByMainId(entityId);
    	scmCwDistributorRB.setScmCwDistributorFlowCostList(scmCwDistributorFlowCostList);
    	List<ScmCwDistributorRebate> scmCwDistributorRebateList=scmCwDistributorRebateDao.getByMainId(entityId);
    	scmCwDistributorRB.setScmCwDistributorRebateList(scmCwDistributorRebateList);
    	List<ScmCwDistributorRbCollect> scmCwDistributorRbCollectList=scmCwDistributorRbCollectDao.getByMainId(entityId);
    	scmCwDistributorRB.setScmCwDistributorRbCollectList(scmCwDistributorRbCollectList);
    	return scmCwDistributorRB;
    }
    
    /**
     * 更新实体同时更新子表记录
     */
    public void update(ScmCwDistributorRB scmCwDistributorRB){
    	super.update(scmCwDistributorRB);
    	String id=scmCwDistributorRB.getId();
    	scmCwDistributorFlowCostDao.delByMainId(id);
    	List<ScmCwDistributorFlowCost> scmCwDistributorFlowCostList=scmCwDistributorRB.getScmCwDistributorFlowCostList();
    	for(ScmCwDistributorFlowCost scmCwDistributorFlowCost:scmCwDistributorFlowCostList){
    		scmCwDistributorFlowCost.setMasterId(id);
    		scmCwDistributorFlowCostDao.create(scmCwDistributorFlowCost);
    	}
    	scmCwDistributorRebateDao.delByMainId(id);
    	List<ScmCwDistributorRebate> scmCwDistributorRebateList=scmCwDistributorRB.getScmCwDistributorRebateList();
    	for(ScmCwDistributorRebate scmCwDistributorRebate:scmCwDistributorRebateList){
    		scmCwDistributorRebate.setMasterId(id);
    		scmCwDistributorRebateDao.create(scmCwDistributorRebate);
    	}
    	scmCwDistributorRbCollectDao.delByMainId(id);
    	List<ScmCwDistributorRbCollect> scmCwDistributorRbCollectList=scmCwDistributorRB.getScmCwDistributorRbCollectList();
    	for(ScmCwDistributorRbCollect scmCwDistributorRbCollect:scmCwDistributorRbCollectList){
    		scmCwDistributorRbCollect.setMasterId(id);
    		scmCwDistributorRbCollectDao.create(scmCwDistributorRbCollect);
    	}
    }

    @Transactional
	@Override
	public void sendApply(ScmCwDistributorRB scmCwDistributorRB) {
		
		ScmCwDistributorRB scmCwDistributorRB2 = scmCwDistributorRBDao.get(scmCwDistributorRB.getId());
		if(scmCwDistributorRB2 == null) {
			throw new RuntimeException("未查询到分销商RB数据");
		}
		StartFlowParam startFlowParam = new StartFlowParam("fxsRBgl", "SCM", "approvalId");
		startFlowParam.setFormType("frame");
		CustomStartResult customStartResult = null;
		try {
			System.out.println("发起分销商RB申请");
			customStartResult = bpmRuntimeFeignService.customStart(startFlowParam);
			String approvalId = customStartResult.getInstId();
			scmCwDistributorRB2.setApprovalState("1");
			scmCwDistributorRB2.setApprovalId(approvalId);
			scmCwDistributorRBDao.update(scmCwDistributorRB2);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("启动工作流失败");
		}
//		scmCwDistributorRB2.setApprovalState("1");
//		scmCwDistributorRB2.setApprovalId(scmCwDistributorRB.getApprovalId());
//		scmCwDistributorRBDao.update(scmCwDistributorRB2);
		List<ScmCwDistributorFlowCost> scmCwDistributorFlowCostList = scmCwDistributorRB.getScmCwDistributorFlowCostList();
		List<ScmCwDistributorRbCollect> scmCwDistributorRbCollectList = scmCwDistributorRB.getScmCwDistributorRbCollectList();
		List<ScmCwDistributorRebate> scmCwDistributorRebateList = scmCwDistributorRB.getScmCwDistributorRebateList();
		
		List<ScmCwDistributorFlowCost> byMainId = scmCwDistributorFlowCostDao.getByMainId(scmCwDistributorRB.getId());
		List<ScmCwDistributorRebate> byMainId2 = scmCwDistributorRebateDao.getByMainId(scmCwDistributorRB.getId());
		List<ScmCwDistributorRbCollect> byMainId3 = scmCwDistributorRbCollectDao.getByMainId(scmCwDistributorRB.getId());
		
		for (ScmCwDistributorFlowCost scmCwDistributorFlowCost : byMainId) {
			for (ScmCwDistributorFlowCost scmCwDistributorFlowCost1 : scmCwDistributorFlowCostList) {
				if(scmCwDistributorFlowCost.getId().equals(scmCwDistributorFlowCost1.getId())) {
					//匹配到数据,更新前端传递数据
					scmCwDistributorFlowCost.setThisTimeApply(scmCwDistributorFlowCost1.getThisTimeApply());
					scmCwDistributorFlowCost.setMemo(scmCwDistributorFlowCost1.getMemo());
					scmCwDistributorFlowCostManager.update(scmCwDistributorFlowCost);
				}
			}
		}
		for (ScmCwDistributorRebate scmCwDistributorRebate : byMainId2) {
			for (ScmCwDistributorRebate scmCwDistributorRebate1 : scmCwDistributorRebateList) {
				if(scmCwDistributorRebate.getId().equals(scmCwDistributorRebate1.getId())) {
					scmCwDistributorRebate.setThisTimeShell(scmCwDistributorRebate1.getThisTimeShell());
					scmCwDistributorRebateManager.update(scmCwDistributorRebate);
				}
				for (ScmCwDistributorFlowCost scmCwDistributorFlowCost : byMainId) {
					if(scmCwDistributorRebate.getCommerceId().equals(scmCwDistributorFlowCost.getCommerceId())) {
						//在匹配汇总表
						for (ScmCwDistributorRbCollect scmCwDistributorRbCollect : byMainId3) {
							if(scmCwDistributorRebate.getCommerceId().equals(scmCwDistributorRbCollect.getCommerceId())) {
								//三个表匹配完成,重新计算汇总表数据
								scmCwDistributorRbCollect.setThisCalculationRb(scmCwDistributorFlowCost.getThisTimeApply() + scmCwDistributorRebate.getThisTimeShell());
								scmCwDistributorRbCollect.setTheRealHair(scmCwDistributorRbCollect.getThisCalculationRb() - scmCwDistributorRbCollect.getDeduct());
								scmCwDistributorRbCollectManager.update(scmCwDistributorRbCollect);
							}
						}
					}
				}
			}
		}
		for (ScmCwDistributorRbCollect scmCwDistributorRbCollect : byMainId3) {
			for (ScmCwDistributorRbCollect scmCwDistributorRbCollect1 : scmCwDistributorRbCollectList) {
				if(scmCwDistributorRbCollect.getId().equals(scmCwDistributorRbCollect1.getId())) {
					scmCwDistributorRbCollect.setGetMoneyCommerce(scmCwDistributorRbCollect1.getGetMoneyCommerce());
					scmCwDistributorRbCollect.setGetMoneyCommerceId(scmCwDistributorRbCollect1.getGetMoneyCommerceId());
					scmCwDistributorRbCollect.setMemo(scmCwDistributorRbCollect1.getMemo());
					scmCwDistributorRbCollectManager.update(scmCwDistributorRbCollect);
				}
			}
		}
	}
	@Override
	public void endApply(JsonNode jsonNode) {
		String approvalId = jsonNode.get("instId").asText();
		String actionName = jsonNode.get("actionName").asText();
		ScmCwDistributorRB scmCwDistributorRB = scmCwDistributorRBDao.getByApprovalId(approvalId);
		if(scmCwDistributorRB == null) {
			throw new RuntimeException("未查询到审批数据");
		}
		String endEvent = jsonNode.get("eventType").asText();
		if ("agree".equals(actionName) && "endEvent".equals(endEvent)) {
			scmCwDistributorRB.setApprovalState("2");
			List<ScmCwDistributorRbCollect> byMainId = scmCwDistributorRbCollectDao.getByMainId(scmCwDistributorRB.getId());
			//调整审批状态,把rb汇总统计至票折池
			for (ScmCwDistributorRbCollect scmCwDistributorRbCollect : byMainId) {
				ScmCwDiscountPond pond = new ScmCwDiscountPond();
                pond.setDiscountSource("1");
                pond.setBusinessDivision(scmCwDistributorRbCollect.getBusinessDivision());
                pond.setProvince(scmCwDistributorRbCollect.getProvince());
                pond.setBusinessDivisionId(scmCwDistributorRbCollect.getBusinessDivisionId());
                pond.setProvinceId(scmCwDistributorRbCollect.getProvinceId());
                pond.setCommerceId(scmCwDistributorRbCollect.getGetMoneyCommerceId());
                pond.setCommerceCode(scmCwDistributorRbCollect.getGetMoneyCommerceCode());
                pond.setCommerceName(scmCwDistributorRbCollect.getGetMoneyCommerce());
//                pond.setCommerceLevel(entry.getValue().getCommerceLevel());
                pond.setMayDeduction(scmCwDistributorRbCollect.getTheRealHair());
                pond.setBalance(scmCwDistributorRbCollect.getTheRealHair());
                pond.setOwnerId(scmCwDistributorRB.getOwnerId());
                pond.setDiscountCommerce(scmCwDistributorRbCollect.getGetMoneyCommerce());
                scmCwDiscountPondManager.create(pond);
			}
			scmCwDistributorRBDao.update(scmCwDistributorRB);
		} else if ("agree".equals(actionName)) {
		} else if ("reject".equals(actionName)) {
		} else if ("backToStart".equals(actionName)) {
		} else if ("opposeTrans".equals(actionName)) {
		} else if ("endProcess".equals(actionName)) {
			scmCwDistributorRB.setApprovalState("3");
			scmCwDistributorRBDao.update(scmCwDistributorRB);
		}
	}
	@Override
	public ScmCwDistributorRB getByApprovalId(String approvalId) {
		ScmCwDistributorRB scmCwDistributorRB = scmCwDistributorRBDao.getByApprovalId(approvalId);
		if(scmCwDistributorRB == null) {
			throw new RuntimeException("未查询到审批数据");
		}
		ScmCwDistributorRB scmCwDistributorRB2 = get(scmCwDistributorRB.getId());
		return scmCwDistributorRB2;
	}
	
}
