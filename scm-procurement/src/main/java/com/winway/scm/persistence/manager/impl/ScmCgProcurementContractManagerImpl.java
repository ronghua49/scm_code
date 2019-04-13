package com.winway.scm.persistence.manager.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.purchase.util.QuarterUtil;
import com.winway.scm.model.ScmCgContractProduct;
import com.winway.scm.model.ScmCgProcurementContract;
import com.winway.scm.persistence.dao.ScmCgContractProductDao;
import com.winway.scm.persistence.dao.ScmCgProcurementContractDao;
import com.winway.scm.persistence.manager.ScmCgProcurementContractManager;

/**
 * 
 * <pre> 
 * 描述：SCM_CG_ProcurementContract 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-26 13:47:44
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
@Service("scmCgProcurementContractManager")
public class ScmCgProcurementContractManagerImpl extends AbstractManagerImpl<String, ScmCgProcurementContract> implements ScmCgProcurementContractManager{
	@Resource
	ScmCgProcurementContractDao scmCgProcurementContractDao;
	@Resource
	ScmCgContractProductDao scmCgContractProductDao;
	@Override
	protected MyBatisDao<String, ScmCgProcurementContract> getDao() {
		return scmCgProcurementContractDao;
	}
	/**
	 * 创建实体包含子表实体
	 */
	public void create(ScmCgProcurementContract scmCgProcurementContract){
		scmCgProcurementContract.setApprovalState("0");
		scmCgProcurementContract.setContractCode(QuarterUtil.getCode("CGHT"));
    	scmCgProcurementContract.setSpeciesSum(0);
    	scmCgProcurementContract.setProductSum(0);
    	scmCgProcurementContract.setSumPrice(0.0);
		super.create(scmCgProcurementContract);
		
    }
	
	/**
	 * 删除记录包含子表记录
	 */
	public void remove(String entityId){
		super.remove(entityId);
    	scmCgContractProductDao.delByMainId(entityId);
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
    public ScmCgProcurementContract get(String entityId){
    	ScmCgProcurementContract scmCgProcurementContract=super.get(entityId);
    	List<ScmCgContractProduct> scmCgContractProductList=scmCgContractProductDao.getByMainId(entityId);
    	scmCgProcurementContract.setScmCgContractProductList(scmCgContractProductList);
    	return scmCgProcurementContract;
    }
    
    /**
     * 更新实体同时更新子表记录
     */
    public void update(ScmCgProcurementContract scmCgProcurementContract){
    	//计算商品总价  品种数等
    	//计算合同下商品总数等信息
    	Map<?, ?> listProductByContractId = scmCgContractProductDao.listProductByContractId(scmCgProcurementContract.getId());
    	scmCgProcurementContract.setSpeciesSum(Integer.parseInt(listProductByContractId.get("speciesSum").toString()));
    	scmCgProcurementContract.setProductSum(Integer.parseInt(listProductByContractId.get("productSum").toString()));
    	scmCgProcurementContract.setSumPrice(Double.parseDouble(listProductByContractId.get("sumPrice").toString()));
    	scmCgProcurementContract.setApprovalState("0");
    	super.update(scmCgProcurementContract);
    	
    }

	@Override
	public ScmCgProcurementContract getContractFirstByApprovalId(String approvalId) {
		ScmCgProcurementContract contractFirstByApprovalId = scmCgProcurementContractDao.getContractFirstByApprovalId(approvalId);
		List<ScmCgContractProduct> byMainId = scmCgContractProductDao.getByMainId(contractFirstByApprovalId.getId());
		contractFirstByApprovalId.setScmCgContractProductList(byMainId);
		return contractFirstByApprovalId;
	}
	@Override
	public void endApply(JsonNode jsonNode) {
    	String approvalId = jsonNode.get("instId").asText();
    	ScmCgProcurementContract scmCgProcurementContract = scmCgProcurementContractDao.getContractFirstByApprovalId(approvalId);
		if(scmCgProcurementContract == null) {
			throw new RuntimeException("未查询到业务数据,处理异常");
		}
		//审批状态调整为通过
		scmCgProcurementContract.setApprovalState("2");
		scmCgProcurementContractDao.update(scmCgProcurementContract);
	}
	
}
