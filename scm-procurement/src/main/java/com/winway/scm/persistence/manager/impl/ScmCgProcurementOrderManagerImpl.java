package com.winway.scm.persistence.manager.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.feign.UCFeignService;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.purchase.util.QuarterUtil;
import com.winway.scm.model.ScmCgProOrderProduct;
import com.winway.scm.model.ScmCgProReplacementOrder;
import com.winway.scm.model.ScmCgProcurementContract;
import com.winway.scm.model.ScmCgProcurementOrder;
import com.winway.scm.persistence.dao.ScmCgProOrderProductDao;
import com.winway.scm.persistence.dao.ScmCgProReplacementOrderDao;
import com.winway.scm.persistence.dao.ScmCgProcurementOrderDao;
import com.winway.scm.persistence.manager.ScmCgProcurementOrderManager;

/**
 * 
 * <pre> 
 * 描述：采购订单表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-26 13:47:44
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
@Service("scmCgProcurementOrderManager")
public class ScmCgProcurementOrderManagerImpl extends AbstractManagerImpl<String, ScmCgProcurementOrder> implements ScmCgProcurementOrderManager{
	@Resource
	ScmCgProcurementOrderDao scmCgProcurementOrderDao;
	@Resource
	ScmCgProOrderProductDao scmCgProOrderProductDao;
	@Resource
	ScmCgProReplacementOrderDao scmCgProReplacementOrderDao;
 
	
	
	@Override
	protected MyBatisDao<String, ScmCgProcurementOrder> getDao() {
		return scmCgProcurementOrderDao;
	}
	/**
	 * 创建实体包含子表实体
	 */
	public void create(ScmCgProcurementOrder scmCgProcurementOrder){
		scmCgProcurementOrder.setProcurementCode(QuarterUtil.getCode("CGDD"));
		scmCgProcurementOrder.setApprovalState("0");
		scmCgProcurementOrder.setIsToWMS('0');
		scmCgProcurementOrder.setSpeciesSum(0);
		scmCgProcurementOrder.setProductSum(0);
		scmCgProcurementOrder.setSumPrice(0.0);
    	super.create(scmCgProcurementOrder);
    }
	
	/**
	 * 删除记录包含子表记录
	 */
	public void remove(String entityId){
		super.remove(entityId);
    	scmCgProOrderProductDao.delByMainId(entityId);
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
    public ScmCgProcurementOrder get(String entityId){
    	ScmCgProcurementOrder scmCgProcurementOrder=super.get(entityId);
    	List<ScmCgProOrderProduct> scmCgProOrderProductList=scmCgProOrderProductDao.getByMainId(entityId);
    	scmCgProcurementOrder.setScmCgProOrderProductList(scmCgProOrderProductList);
    	return scmCgProcurementOrder;
    }
    
    /**
     * 更新实体同时更新子表记录
     */
    public void update(ScmCgProcurementOrder scmCgProcurementOrder){
    	//查询订单商品信息
    	scmCgProOrderProductDao.listProductByProOrderId(scmCgProcurementOrder.getId());
    	//统计数据,存入采购订单数据中
    	Map<?, ?> listProductByContractId = scmCgProOrderProductDao.listProductByProOrderId(scmCgProcurementOrder.getId());
    	scmCgProcurementOrder.setSpeciesSum(Integer.parseInt(listProductByContractId.get("speciesSum").toString()));
    	scmCgProcurementOrder.setProductSum(Integer.parseInt(listProductByContractId.get("productSum").toString()));
    	scmCgProcurementOrder.setSumPrice(Double.parseDouble(listProductByContractId.get("sumPrice").toString()));
    	super.update(scmCgProcurementOrder);
    }
	@Override
	public ScmCgProcurementOrder getOrderAndRepOrder(String id) {
		// TODO Auto-generated method stub
		ScmCgProcurementOrder scmCgProcurementOrder = scmCgProcurementOrderDao.get(id);
		List<ScmCgProReplacementOrder> scmCgProReplacementOrderList = scmCgProReplacementOrderDao.getProReplacementOrderByOrderId(id);
		scmCgProcurementOrder.setScmCgProReplacementOrderList(scmCgProReplacementOrderList);
	    return scmCgProcurementOrder;
	}
	@Override
	public void sendApply(ScmCgProcurementOrder scmCgProcurementOrder) {
		// TODO Auto-generated method stub
		//0:待提交， 1:待审核，2：已审核。3：已驳回
		ScmCgProcurementOrder scmCgProcurementOrder2 = scmCgProcurementOrderDao.get(scmCgProcurementOrder.getId());
		scmCgProcurementOrder2.setApprovalState("1");
		scmCgProcurementOrder2.setApprovalId(scmCgProcurementOrder.getApprovalId());
		update(scmCgProcurementOrder2);	
	}
	@Override
	public ScmCgProcurementOrder getOrderFirstByApprovalId(String approvalId) {
		return scmCgProcurementOrderDao.getOrderFirstByApprovalId(approvalId);
	}
	@Override
	public void endApply(JsonNode jsonNode) {
    	String approvalId = jsonNode.get("instId").asText();
    	ScmCgProcurementOrder scmCgProcurementOrder = scmCgProcurementOrderDao.getOrderFirstByApprovalId(approvalId);
		if(scmCgProcurementOrder == null) {
			throw new RuntimeException("未查询到业务数据,处理异常");
		}
		//审批状态调整为通过
		scmCgProcurementOrder.setApprovalState("2");
		scmCgProcurementOrderDao.update(scmCgProcurementOrder);
	}
	
}
