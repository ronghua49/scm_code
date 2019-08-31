package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import com.winway.scm.persistence.dao.ScmCwPaymentOrderDao;
import com.winway.scm.model.ScmCwPaymentOrder;
import com.winway.scm.persistence.dao.ScmCwPaymentRepOrderDao;
import com.winway.scm.model.ScmCwPaymentRepOrder;
import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmCwPaymentDao;
import com.winway.scm.model.ScmCwPayment;
import com.winway.scm.persistence.manager.ScmCwPaymentManager;
import com.winway.scm.persistence.manager.ScmCwPaymentOrderManager;

/**
 * 
 * <pre> 
 * 描述：付款总表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-23 18:19:25
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmCwPaymentManager")
public class ScmCwPaymentManagerImpl extends AbstractManagerImpl<String, ScmCwPayment> implements ScmCwPaymentManager{
	@Resource
	ScmCwPaymentDao scmCwPaymentDao;
	
	@Resource
	ScmCwPaymentOrderManager scmCwPaymentOrderManager;
	
	@Resource
	ScmCwPaymentOrderDao scmCwPaymentOrderDao;
	@Override
	protected MyBatisDao<String, ScmCwPayment> getDao() {
		return scmCwPaymentDao;
	}

	/**
	 * 删除记录包含子表记录
	 */
	public void remove(String entityId){
		super.remove(entityId);
    	scmCwPaymentOrderDao.delByMainId(entityId);
    	
	}
	
	/**
	 * 批量删除包含子表记录
	 */
	public void removeByIds(String[] entityIds){
		for(String id:entityIds){
			this.remove(id);
		}
	}
    
	
   
	
	@Override
	public void endApply(JsonNode jsonNode) {
		String approvalId = jsonNode.get("instId").asText();
		String actionName=jsonNode.get("actionName").asText();
		ScmCwPayment scmCwPayment=scmCwPaymentDao.findByApprovalId(approvalId);
		if(scmCwPayment == null){
			throw new RuntimeException("未查询到业务数据,处理异常");
		}
		String endEvent = jsonNode.get("eventType").asText();
		if ("agree".equals(actionName) && "endEvent".equals(endEvent)) {
			scmCwPayment.setApprovalState("2");//通过
			scmCwPaymentDao.update(scmCwPayment);
		} else if ("agree".equals(actionName)) {
		} else if ("reject".equals(actionName)) {
		} else if ("backToStart".equals(actionName)) {
		} else if ("opposeTrans".equals(actionName)) {
		} else if ("endProcess".equals(actionName)) {
			scmCwPayment.setApprovalState("3");//驳回
			scmCwPaymentDao.update(scmCwPayment);
		}
	}
	@Override
	public void creates(ScmCwPayment scmCwPayment) {
		if(scmCwPayment.getDue()-scmCwPayment.getDeductMoney()!=scmCwPayment.getPayment()){
			scmCwPayment.setPayment(scmCwPayment.getDue()-scmCwPayment.getDeductMoney());
		}
    	super.create(scmCwPayment);
    	String pk=scmCwPayment.getId();
    	
    	List<ScmCwPaymentOrder> scmCwPaymentOrderList=scmCwPayment.getScmCwPaymentOrderList();
    	
    	for(ScmCwPaymentOrder scmCwPaymentOrder:scmCwPaymentOrderList){
    		scmCwPaymentOrder.setPaymentId(pk);
    		scmCwPaymentOrder.setOrderDate(new Date());
    		scmCwPaymentOrderManager.create(scmCwPaymentOrder);
    	}
		
	}
	@Override
	public void updates(ScmCwPayment scmCwPayment) {
		super.update(scmCwPayment);
    	List<ScmCwPaymentOrder> scmCwPaymentOrderList=scmCwPayment.getScmCwPaymentOrderList();
    	for(ScmCwPaymentOrder scmCwPaymentOrder:scmCwPaymentOrderList){
    		scmCwPaymentOrder.setOrderDate(new Date());
    		if(StringUtils.isEmpty(scmCwPaymentOrder.getId())){
    			scmCwPaymentOrder.setPaymentId(scmCwPayment.getId());
    			scmCwPaymentOrderManager.create(scmCwPaymentOrder);
    		}
    		scmCwPaymentOrderManager.update(scmCwPaymentOrder);
    	}
		
	}
	
}
