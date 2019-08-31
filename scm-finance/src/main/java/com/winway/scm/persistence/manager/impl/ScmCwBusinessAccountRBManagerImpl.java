package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import com.github.pagehelper.PageHelper;
import com.hotent.base.exception.SystemException;
import com.hotent.base.feign.BpmRuntimeFeignService;
import com.hotent.base.modelvo.CustomStartResult;
import com.hotent.base.modelvo.StartFlowParam;
import com.hotent.base.query.PageBean;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

import com.winway.scm.persistence.dao.ScmCwBAPaymentDiscountDao;
import com.winway.purchase.feign.ScmMasterDateFeignService;
import com.winway.scm.model.ScmCwBAPaymentDiscount;
import com.winway.scm.persistence.dao.ScmCwBAStorageDiscountDao;
import com.winway.scm.model.ScmCwBAStorageDiscount;
import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.model.CommonResult;
import com.winway.scm.persistence.dao.ScmCwBusinessAccountRBDao;
import com.winway.scm.model.ScmCwBusinessAccountRB;
import com.winway.scm.persistence.manager.ScmCwBAPaymentDiscountManager;
import com.winway.scm.persistence.manager.ScmCwBAStorageDiscountManager;
import com.winway.scm.persistence.manager.ScmCwBusinessAccountRBManager;
import com.winway.scm.vo.ScmCwBlendDetailVo;
import com.winway.scm.vo.ScmCwSendProductVo;

/**
 * 
 * <pre> 
 * 描述：经销商RB核算总表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-09 18:18:05
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmCwBusinessAccountRBManager")
public class ScmCwBusinessAccountRBManagerImpl extends AbstractManagerImpl<String, ScmCwBusinessAccountRB> implements ScmCwBusinessAccountRBManager{
	@Resource
	ScmCwBusinessAccountRBDao scmCwBusinessAccountRBDao;
	@Resource
	ScmCwBAPaymentDiscountDao scmCwBAPaymentDiscountDao;
	@Resource
	ScmCwBAPaymentDiscountManager scmCwBAPaymentDiscountManager;
	@Resource
	ScmCwBAStorageDiscountDao scmCwBAStorageDiscountDao;
	@Resource
	ScmCwBAStorageDiscountManager scmCwBAStorageDiscountManager;
	@Resource
	ScmMasterDateFeignService scmMasterDateFeignService;
	@Resource
	BpmRuntimeFeignService bpmRuntimeFeignService;
	@Override
	protected MyBatisDao<String, ScmCwBusinessAccountRB> getDao() {
		return scmCwBusinessAccountRBDao;
	}
	/**
	 * 创建实体包含子表实体
	 */
	public void create(ScmCwBusinessAccountRB scmCwBusinessAccountRB){
		String ownerId = scmCwBusinessAccountRB.getOwnerId();
		List<ScmCwBusinessAccountRB> scmCwBusinessAccountRBs = scmCwBusinessAccountRBDao.getByOwnerIdAndYearAndQuarter(scmCwBusinessAccountRB.getOwnerId(),scmCwBusinessAccountRB.getYear(),scmCwBusinessAccountRB.getQuarter(),scmCwBusinessAccountRB.getBusinessDivisionId(),scmCwBusinessAccountRB.getProvinceId());
		if(scmCwBusinessAccountRBs.size() > 0) {
			throw new RuntimeException(scmCwBusinessAccountRB.getYear() + "下,第" + scmCwBusinessAccountRB.getQuarter() +"季度RB核算已存在,请勿重复发起申请");
		}
		CommonResult<String> getRb = scmMasterDateFeignService.getRB(ownerId);
		String rb = getRb.getValue();
		scmCwBusinessAccountRB.setTaxPoint(Double.parseDouble(rb));
    	super.create(scmCwBusinessAccountRB);
    	String pk=scmCwBusinessAccountRB.getId();
    	String startDate = "";
    	String endDate = "";
    	if("1".equals(scmCwBusinessAccountRB.getQuarter())) {
    		startDate = scmCwBusinessAccountRB.getYear() + "-01-01";
    		endDate = scmCwBusinessAccountRB.getYear() + "-04-01";
    	}else if("2".equals(scmCwBusinessAccountRB.getQuarter())) {
    		startDate = scmCwBusinessAccountRB.getYear() + "-04-01";
    		endDate = scmCwBusinessAccountRB.getYear() + "-07-01";
    	}else if("3".equals(scmCwBusinessAccountRB.getQuarter())) {
    		startDate = scmCwBusinessAccountRB.getYear() + "-07-01";
    		endDate = scmCwBusinessAccountRB.getYear() + "-10-01";
    	}else{
    		startDate = scmCwBusinessAccountRB.getYear() + "-10-01";
    		int parseInt = Integer.parseInt(scmCwBusinessAccountRB.getYear()) + 1;
    		endDate = parseInt + "-01-01";
    	}
    	//查询付款勾兑记录
    	List<ScmCwBlendDetailVo> scmCwBlendDetailVos =  scmCwBusinessAccountRBDao.ListBlendDetail(ownerId,startDate,endDate,scmCwBusinessAccountRB.getBusinessDivisionId(),scmCwBusinessAccountRB.getProvinceId());
    	//查询发货记录
    	List<ScmCwSendProductVo> scmCwSendProductVos = scmCwBusinessAccountRBDao.ListSendProduct(ownerId,startDate,endDate,scmCwBusinessAccountRB.getBusinessDivisionId(),scmCwBusinessAccountRB.getProvinceId());
    	if(scmCwBlendDetailVos.size() == 0) {
    		if(scmCwSendProductVos.size() == 0) {
    			throw new RuntimeException("未查询到可生成RB的数据");
    		}
    	}
    	for (ScmCwBlendDetailVo scmCwBlendDetailVo : scmCwBlendDetailVos) {
			//根据勾兑记录创建付款RB核算
    		ScmCwBAPaymentDiscount scmCwBAPaymentDiscount = new ScmCwBAPaymentDiscount();
    		scmCwBAPaymentDiscount.setMasterId(pk);
    		scmCwBAPaymentDiscount.setCommerceId(scmCwBlendDetailVo.getCommerceId());
    		scmCwBAPaymentDiscount.setCommerceCode(scmCwBlendDetailVo.getCommerceCode());
    		scmCwBAPaymentDiscount.setCommerceName(scmCwBlendDetailVo.getCommerceName());
    		scmCwBAPaymentDiscount.setBusinessDivision(scmCwBusinessAccountRB.getBusinessDivision());
    		scmCwBAPaymentDiscount.setBusinessDivisionId(scmCwBusinessAccountRB.getBusinessDivisionId());
    		scmCwBAPaymentDiscount.setProvince(scmCwBusinessAccountRB.getProvince());
    		scmCwBAPaymentDiscount.setProvinceId(scmCwBusinessAccountRB.getProvinceId());
    		scmCwBAPaymentDiscount.setCommerceLevel(scmCwBlendDetailVo.getTypeStr());
    		scmCwBAPaymentDiscount.setClearForm(scmCwBlendDetailVo.getRemittanceType());
    		scmCwBAPaymentDiscount.setProductName(scmCwBlendDetailVo.getCommonName());
    		scmCwBAPaymentDiscount.setContractCode(scmCwBlendDetailVo.getContractCode());
    		scmCwBAPaymentDiscount.setInvoiceCode(scmCwBlendDetailVo.getInvoiceCode());
    		scmCwBAPaymentDiscount.setInvoiceMemo(scmCwBlendDetailVo.getMemo());
    		scmCwBAPaymentDiscount.setPaymentType(scmCwBlendDetailVo.getPaymentType());
    		scmCwBAPaymentDiscount.setPrice(scmCwBlendDetailVo.getBlendprice());
    		scmCwBAPaymentDiscount.setRemittanceDate(scmCwBlendDetailVo.getBlendDate());
    		scmCwBAPaymentDiscount.setAffirmDate(scmCwBlendDetailVo.getBlendDate());
    		scmCwBAPaymentDiscount.setInvoiceDate(scmCwBlendDetailVo.getInvoiceDate());
    		scmCwBAPaymentDiscount.setUpdateInvoiceDate(scmCwBlendDetailVo.getInvoiceDate());
    		scmCwBAPaymentDiscount.setBlendId(scmCwBlendDetailVo.getId());
    		if("0".equals(scmCwBAPaymentDiscount.getPaymentType())) {
    			//预付款
    			scmCwBAPaymentDiscount.setRbRate(3.5);
    		}else{
    			//赊销
    			scmCwBAPaymentDiscount.setRbRate(3.0);
    		}
    		int daysBetween = (int)(scmCwBAPaymentDiscount.getAffirmDate().getTime() - scmCwBAPaymentDiscount.getUpdateInvoiceDate().getTime()+1000000)/(60*60*24*1000);
    		scmCwBAPaymentDiscount.setReturnMoneyDay(daysBetween);
    		if(scmCwBAPaymentDiscount.getReturnMoneyDay() > 30) {
    			double day = scmCwBAPaymentDiscount.getReturnMoneyDay() - 30;
    			double totalRbRate = day * 0.02;
    			scmCwBAPaymentDiscount.setTotalRbRate(totalRbRate);
    		}else{
    			scmCwBAPaymentDiscount.setTotalRbRate(scmCwBAPaymentDiscount.getRbRate());
    		}
    		//scmCwBAPaymentDiscount.getPrice()+"*"+(scmCwBAPaymentDiscount.getTotalRbRate() / 100) + "/" + (scmCwBusinessAccountRB.getTaxPoint()/100)
    		scmCwBAPaymentDiscount.setThisCalculationRb(0.0);
    		double thisShouleSendRB = scmCwBAPaymentDiscount.getPrice() * (scmCwBAPaymentDiscount.getTotalRbRate() / 100) / (scmCwBusinessAccountRB.getTaxPoint() / 100);
    		scmCwBAPaymentDiscount.setThisShouldSendRb(thisShouleSendRB);
    		scmCwBAPaymentDiscount.setMemo("");
    		scmCwBAPaymentDiscountManager.create(scmCwBAPaymentDiscount);
    	}
    	for (ScmCwSendProductVo scmCwSendProductVo : scmCwSendProductVos) {
			//根据查询的发货记录生成储运折扣
    		ScmCwBAStorageDiscount scmCwBAStorageDiscount = new ScmCwBAStorageDiscount();
    		scmCwBAStorageDiscount.setMasterId(pk);
    		scmCwBAStorageDiscount.setCommerceId(scmCwSendProductVo.getCommerceId());
    		scmCwBAStorageDiscount.setCommerceName(scmCwSendProductVo.getCommerceName());
    		scmCwBAStorageDiscount.setCommerceCode(scmCwSendProductVo.getCommerceCode());
    		scmCwBAStorageDiscount.setBusinessDivision(scmCwBusinessAccountRB.getBusinessDivision());
    		scmCwBAStorageDiscount.setBusinessDivisionId(scmCwBusinessAccountRB.getBusinessDivisionId());
    		scmCwBAStorageDiscount.setProvince(scmCwBusinessAccountRB.getProvince());
    		scmCwBAStorageDiscount.setProvinceId(scmCwBusinessAccountRB.getProvinceId());
    		scmCwBAStorageDiscount.setShipmentDate(scmCwSendProductVo.getShipmentDate());
    		scmCwBAStorageDiscount.setShipmentCode(scmCwSendProductVo.getShipmentCode());
    		scmCwBAStorageDiscount.setBatchNum(scmCwSendProductVo.getBatchNumber());
    		scmCwBAStorageDiscount.setProductName(scmCwSendProductVo.getCommonName());
    		scmCwBAStorageDiscount.setProductNum(scmCwSendProductVo.getProductSum());
    		scmCwBAStorageDiscount.setUpdateProductNum(scmCwSendProductVo.getProductSum());
    		scmCwBAStorageDiscount.setUnitPrice(scmCwSendProductVo.getUnitPrice());
    		scmCwBAStorageDiscount.setUpdateUnitPrice(scmCwSendProductVo.getUnitPrice());
			scmCwBAStorageDiscount.setLoseEfficacyDate(scmCwSendProductVo.getLoseDate());
    		scmCwBAStorageDiscount.setPrice(scmCwBAStorageDiscount.getUpdateUnitPrice() * scmCwBAStorageDiscount.getUpdateProductNum());
    		scmCwBAStorageDiscount.setMemo("");
    		scmCwBAStorageDiscount.setBlendId(scmCwSendProductVo.getId());
    		if("0".equals(scmCwSendProductVo.getTaskType())) {
    			//集中发货
    			if("0".equals(scmCwSendProductVo.getType())) {
    				//经销商0.5
    				scmCwBAStorageDiscount.setRbRate(0.5);
    				scmCwBAStorageDiscount.setDiscountType("1");
    			}else{
    				scmCwBAStorageDiscount.setRbRate(0.0);
    				scmCwBAStorageDiscount.setDiscountType("1");
    			}
    		}else{
    			//验证是否散件货
    			if("1".equals(scmCwSendProductVo.getWarehouseType())) {
    				scmCwBAStorageDiscount.setRbRate(3.0);
    				scmCwBAStorageDiscount.setDiscountType("0");
    			}else{
    				scmCwBAStorageDiscount.setRbRate(0.0);
    				scmCwBAStorageDiscount.setDiscountType("3");
    			}
    		}
    		scmCwBAStorageDiscount.setLoseEfficacyDate(scmCwSendProductVo.getLoseDate());
    		double thisCalculationRb = scmCwBAStorageDiscount.getPrice() * (scmCwBAStorageDiscount.getRbRate() / 100);
    		scmCwBAStorageDiscount.setThisCalculationRb(thisCalculationRb);
    		scmCwBAStorageDiscountManager.create(scmCwBAStorageDiscount);
		}
    	//查询促销折扣
    }
	
	/**
	 * 删除记录包含子表记录
	 */
	public void remove(String entityId){
		super.remove(entityId);
    	scmCwBAPaymentDiscountDao.delByMainId(entityId);
    	scmCwBAStorageDiscountDao.delByMainId(entityId);
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
    public ScmCwBusinessAccountRB get(String entityId){
    	ScmCwBusinessAccountRB scmCwBusinessAccountRB=super.get(entityId);
    	List<ScmCwBAPaymentDiscount> scmCwBAPaymentDiscountList=scmCwBAPaymentDiscountDao.getByMainId(entityId);
    	scmCwBusinessAccountRB.setScmCwBAPaymentDiscountList(scmCwBAPaymentDiscountList);
    	List<ScmCwBAStorageDiscount> scmCwBAStorageDiscountList=scmCwBAStorageDiscountDao.getByMainId(entityId);
    	scmCwBusinessAccountRB.setScmCwBAStorageDiscountList(scmCwBAStorageDiscountList);
    	return scmCwBusinessAccountRB;
    }
    
    /**
     * 更新实体同时更新子表记录
     */
    public void update(ScmCwBusinessAccountRB scmCwBusinessAccountRB){
    	super.update(scmCwBusinessAccountRB);
    	String id=scmCwBusinessAccountRB.getId();
    	scmCwBAPaymentDiscountDao.delByMainId(id);
    	List<ScmCwBAPaymentDiscount> scmCwBAPaymentDiscountList=scmCwBusinessAccountRB.getScmCwBAPaymentDiscountList();
    	for(ScmCwBAPaymentDiscount scmCwBAPaymentDiscount:scmCwBAPaymentDiscountList){
    		scmCwBAPaymentDiscount.setMasterId(id);
    		scmCwBAPaymentDiscountDao.create(scmCwBAPaymentDiscount);
    	}
    	scmCwBAStorageDiscountDao.delByMainId(id);
    	List<ScmCwBAStorageDiscount> scmCwBAStorageDiscountList=scmCwBusinessAccountRB.getScmCwBAStorageDiscountList();
    	for(ScmCwBAStorageDiscount scmCwBAStorageDiscount:scmCwBAStorageDiscountList){
    		scmCwBAStorageDiscount.setMasterId(id);
    		scmCwBAStorageDiscountDao.create(scmCwBAStorageDiscount);
    	}
    }

	@Override
	public List<ScmCwBusinessAccountRB> queryByParamMap(Map<String, Object> map) {
		List<ScmCwBusinessAccountRB> scmCwBusinessAccountRBS = scmCwBusinessAccountRBDao.queryByParamMap(map);
		for(ScmCwBusinessAccountRB businessAccountRB:scmCwBusinessAccountRBS){
			List<ScmCwBAPaymentDiscount> byMainId = scmCwBAPaymentDiscountDao.getByMainId(businessAccountRB.getId());
			List<ScmCwBAStorageDiscount> byMainId1 = scmCwBAStorageDiscountDao.getByMainId(businessAccountRB.getId());
			businessAccountRB.setScmCwBAPaymentDiscountList(byMainId);
			businessAccountRB.setScmCwBAStorageDiscountList(byMainId1);
		}
		return scmCwBusinessAccountRBS;
	}

	@Override
	public void sendApply(ScmCwBusinessAccountRB scmCwBusinessAccountRB) {
		ScmCwBusinessAccountRB scmCwBusinessAccountRB2 = scmCwBusinessAccountRBDao.get(scmCwBusinessAccountRB.getId());
		if(scmCwBusinessAccountRB2 == null) {
			throw new RuntimeException("数据未查询到");
		}
		if(!"0".equals(scmCwBusinessAccountRB2.getApprovalState())) {
			throw new RuntimeException("当前数据已发起申请或已通过审批,请勿重复发起审批");
		}
		StartFlowParam startFlowParam = new StartFlowParam("jxsRBhssp", "SCM", "approvalId");
		startFlowParam.setFormType("frame");
		CustomStartResult customStartResult = null;
		try {
			System.out.println("发起RB核算申请");
			customStartResult = bpmRuntimeFeignService.customStart(startFlowParam);
			String approvalId = customStartResult.getInstId();
			scmCwBusinessAccountRB2.setApprovalId(approvalId);
			scmCwBusinessAccountRB2.setApprovalState("1");
			scmCwBusinessAccountRBDao.update(scmCwBusinessAccountRB2);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("启动工作流失败");
		}
	}

	@Override
	public void endApply(JsonNode jsonNode) {
		String approvalId = jsonNode.get("instId").asText();
		String actionName = jsonNode.get("actionName").asText();
		ScmCwBusinessAccountRB scmCwBusinessAccountRB = scmCwBusinessAccountRBDao.getByApprovalId(approvalId);
		if(scmCwBusinessAccountRB == null) {
			throw new RuntimeException("审批数据不存在");
		}
		String endEvent = jsonNode.get("eventType").asText();
		if ("agree".equals(actionName) && "endEvent".equals(endEvent)) {
			scmCwBusinessAccountRB.setApprovalState("2");
			scmCwBusinessAccountRBDao.update(scmCwBusinessAccountRB);
		} else if ("agree".equals(actionName)) {
		} else if ("reject".equals(actionName)) {
		} else if ("backToStart".equals(actionName)) {
		} else if ("opposeTrans".equals(actionName)) {
		} else if ("endProcess".equals(actionName)) {
			scmCwBusinessAccountRB.setApprovalState("3");
			scmCwBusinessAccountRBDao.update(scmCwBusinessAccountRB);
		}
	}
	@Override
	public ScmCwBusinessAccountRB getByApprovalId(String approvalId) {
		ScmCwBusinessAccountRB byApprovalId = scmCwBusinessAccountRBDao.getByApprovalId(approvalId);
		if(byApprovalId == null) {
			throw new RuntimeException("未查询到审批数据");
		}
		ScmCwBusinessAccountRB scmCwBusinessAccountRB = get(byApprovalId.getId());
		return scmCwBusinessAccountRB;
	}
	
}
