package com.winway.scm.persistence.manager.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.purchase.util.QuarterUtil;
import com.winway.scm.model.ScmCgProRepProduct;
import com.winway.scm.model.ScmCgProReplacementOrder;
import com.winway.scm.model.ScmCgProcurementOrder;
import com.winway.scm.persistence.dao.ScmCgProRepProductDao;
import com.winway.scm.persistence.dao.ScmCgProReplacementOrderDao;
import com.winway.scm.persistence.dao.ScmCgProcurementOrderDao;
import com.winway.scm.persistence.manager.ScmCgProReplacementOrderManager;

/**
 * 
 * <pre> 
 * 描述：采购订单补单表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-26 13:47:44
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
@Service("scmCgProReplacementOrderManager")
public class ScmCgProReplacementOrderManagerImpl extends AbstractManagerImpl<String, ScmCgProReplacementOrder> implements ScmCgProReplacementOrderManager{
	@Resource
	ScmCgProReplacementOrderDao scmCgProReplacementOrderDao;
	@Resource
	ScmCgProRepProductDao scmCgProRepProductDao;
	@Resource
	ScmCgProcurementOrderDao scmCgProcurementOrderDao;
	
	@Override
	protected MyBatisDao<String, ScmCgProReplacementOrder> getDao() {
		return scmCgProReplacementOrderDao;
	}
	/**
	 * 创建实体包含子表实体
	 */
	public void create(ScmCgProReplacementOrder scmCgProReplacementOrder){
		String orderId = scmCgProReplacementOrder.getOrderId();
		ScmCgProcurementOrder scmCgProcurementOrder = scmCgProcurementOrderDao.get(orderId);
		if(scmCgProcurementOrder == null) {
			throw new RuntimeException("请选择订单后发起补单 ");
		}
		scmCgProReplacementOrder.setReplacementOrderCode(QuarterUtil.getCode("CGBD"));
		scmCgProReplacementOrder.setContractId(scmCgProcurementOrder.getContractId());
		scmCgProReplacementOrder.setCurrency(scmCgProcurementOrder.getCurrency());
		scmCgProReplacementOrder.setOrderDate(new Date());
		scmCgProReplacementOrder.setContractCode(scmCgProcurementOrder.getContractCode());
		scmCgProReplacementOrder.setIsToWMS('0');
		scmCgProReplacementOrder.setSpeciesSum(0);
		scmCgProReplacementOrder.setProductSum(0);
		scmCgProReplacementOrder.setSumPrice(0.0);
    	super.create(scmCgProReplacementOrder);
    }
	
	/**
	 * 删除记录包含子表记录
	 */
	public void remove(String entityId){
		super.remove(entityId);
    	scmCgProRepProductDao.delByMainId(entityId);
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
    public ScmCgProReplacementOrder get(String entityId){
    	ScmCgProReplacementOrder scmCgProReplacementOrder=super.get(entityId);
    	List<ScmCgProRepProduct> scmCgProRepProductList=scmCgProRepProductDao.getByMainId(entityId);
    	scmCgProReplacementOrder.setScmCgProRepProductList(scmCgProRepProductList);
    	return scmCgProReplacementOrder;
    }
    
    /**
     * 更新实体同时更新子表记录
     */
    public void update(ScmCgProReplacementOrder scmCgProReplacementOrder){
    	Map listProductByProRepOrderId = scmCgProRepProductDao.listProductByProRepOrderId(scmCgProReplacementOrder.getId());
    	scmCgProReplacementOrder.setSpeciesSum(Integer.parseInt(listProductByProRepOrderId.get("speciesSum").toString()));
    	scmCgProReplacementOrder.setProductSum(Integer.parseInt(listProductByProRepOrderId.get("productSum").toString()));
    	scmCgProReplacementOrder.setSumPrice(Double.parseDouble(listProductByProRepOrderId.get("sumPrice").toString()));
    	super.update(scmCgProReplacementOrder);
    }
	@Override
	public void sendApply(ScmCgProReplacementOrder scmCgProReplacementOrder) {
		ScmCgProReplacementOrder scmCgProReplacementOrder2 = scmCgProReplacementOrderDao.get(scmCgProReplacementOrder.getId());
		scmCgProReplacementOrder2.setApprovalState("1");
		scmCgProReplacementOrder2.setApprovalId(scmCgProReplacementOrder.getApprovalId());
		scmCgProReplacementOrderDao.update(scmCgProReplacementOrder);
	}
	@Override
	public ScmCgProReplacementOrder getProOrderFirstByApprovalId(String approvalId) {
		return scmCgProReplacementOrderDao.getProOrderFirstByApprovalId(approvalId);
	}
	@Override
	public void endApply(JsonNode jsonNode) {
    	String approvalId = jsonNode.get("instId").asText();
    	ScmCgProReplacementOrder scmCgProReplacementOrder = scmCgProReplacementOrderDao.getProOrderFirstByApprovalId(approvalId);
		if(scmCgProReplacementOrder == null) {
			throw new RuntimeException("未查询到业务数据,处理异常");
		}
		//审批状态调整为通过
		scmCgProReplacementOrder.setApprovalState("2");
		scmCgProReplacementOrderDao.update(scmCgProReplacementOrder);
	}
	
}
