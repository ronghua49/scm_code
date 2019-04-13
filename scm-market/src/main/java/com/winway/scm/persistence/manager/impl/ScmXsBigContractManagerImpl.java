package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.winway.scm.persistence.dao.ScmXsBigContractProductDao;
import com.winway.scm.persistence.dao.ScmXsBigContractProductSumDao;
import com.winway.scm.model.ScmXsBigContractProduct;
import com.winway.scm.model.ScmXsBigContractProductSum;
import com.winway.scm.model.ScmXsDealerClause;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.pagehelper.PageHelper;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.exception.SystemException;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.query.PageBean;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.BeanUtils;
import com.winway.scm.persistence.dao.ScmXsBigContractDao;
import com.winway.purchase.util.QuarterUtil;
import com.winway.scm.model.ScmXsAgreementListMaster;
import com.winway.scm.model.ScmXsBigContract;
import com.winway.scm.persistence.manager.ScmXsBigContractManager;
import com.winway.scm.persistence.manager.ScmXsBigContractProductManager;
import com.winway.scm.persistence.manager.ScmXsBigContractProductSumManager;

/**
 * 
 * <pre> 
 * 描述：大合同 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:44
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmXsBigContractManager")
public class ScmXsBigContractManagerImpl extends AbstractManagerImpl<String, ScmXsBigContract> implements ScmXsBigContractManager{
	@Resource
	ScmXsBigContractDao scmXsBigContractDao;
	@Resource
	ScmXsBigContractProductDao scmXsBigContractProductDao;
	@Resource
	ScmXsBigContractProductManager scmXsBigContractProductManager;
	@Resource
	ScmXsBigContractProductSumDao scmXsBigContractProductSumDao;
	
	@Resource
	ScmXsBigContractProductSumManager scmXsBigContractProductSumManager;
	
	@Override
	protected MyBatisDao<String, ScmXsBigContract> getDao() {
		return scmXsBigContractDao;
	}
	/**
	 * 创建实体包含子表实体
	 */
	public void create(ScmXsBigContract scmXsBigContract){
		//获取大合同编号
		scmXsBigContract.setContractCode(QuarterUtil.getCode("DHT"));
		scmXsBigContract.setEntryDate(new Date());
    	super.create(scmXsBigContract);
    	//获取主表主键
    	String pk=scmXsBigContract.getId();
   	    //大合同商品
    	List<ScmXsBigContractProduct> scmXsBigContractProductList=scmXsBigContract.getScmXsBigContractProductList();    	
    	for(ScmXsBigContractProduct scmXsBigContractProduct:scmXsBigContractProductList){
    		scmXsBigContractProduct.setBigContractId(pk);
    		scmXsBigContractProduct.setPackageNum(0);
    		scmXsBigContractProduct.setAchange(0);
    		scmXsBigContractProduct.setLastShipmentDate(new Date());
    		scmXsBigContractProduct.setSumPrice(scmXsBigContractProduct.getTheNumber() * scmXsBigContractProduct.getUnitPrice());
    		scmXsBigContractProductManager.create(scmXsBigContractProduct);
    	}  	
    }
	
	private ScmXsBigContractProductSum toScmXsBigContractProductSum(ScmXsBigContractProduct scmXsBigContractProduct,String ownerId,String commerceId,String agreementSummaryId) {
		ScmXsBigContractProductSum scmXsBigContractProductSum = new ScmXsBigContractProductSum();
		scmXsBigContractProductSum.setOwnerId(ownerId);
		scmXsBigContractProductSum.setCommerceFirstId(commerceId);
		scmXsBigContractProductSum.setProductGauge(scmXsBigContractProduct.getProductGauge());
		scmXsBigContractProductSum.setMedicineType(scmXsBigContractProduct.getMedicineType());
		scmXsBigContractProductSum.setCommonName(scmXsBigContractProduct.getCommonName());
		scmXsBigContractProductSum.setUnit(scmXsBigContractProduct.getUnit());
		scmXsBigContractProductSum.setaChange(scmXsBigContractProduct.getAchange());
		scmXsBigContractProductSum.setSuppyType(scmXsBigContractProduct.getSuppyType());
		scmXsBigContractProductSum.setSuggestApplyNum(scmXsBigContractProduct.getSuggestApplyNum());
		scmXsBigContractProductSum.setLastShipmentDate(scmXsBigContractProduct.getLastShipmentDate());
		scmXsBigContractProductSum.setSMSCurrentMonthSales(scmXsBigContractProduct.getSMSCurrentMonthSales());
		scmXsBigContractProductSum.setProductCode(scmXsBigContractProduct.getProductCode());
		scmXsBigContractProductSum.setIsSMS(scmXsBigContractProduct.getIsSMS());
		scmXsBigContractProductSum.setTheNumber(scmXsBigContractProduct.getTheNumber());
		scmXsBigContractProductSum.setPackageNum(scmXsBigContractProduct.getPackageNum());
		scmXsBigContractProductSum.setUnitPrice(scmXsBigContractProduct.getUnitPrice());
		
		scmXsBigContractProductSum.setSumPrice(scmXsBigContractProduct.getSumPrice());
		//剩余数量
		scmXsBigContractProductSum.setSurplusSUm(scmXsBigContractProduct.getTheNumber());		
		scmXsBigContractProductSum.setAgreementSummaryId(agreementSummaryId);	
		return scmXsBigContractProductSum;
	}
	/**
	 * 删除记录包含子表记录
	 */
	public void remove(String entityId){
		super.remove(entityId);
    	scmXsBigContractProductDao.delByMainId(entityId);
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
    public ScmXsBigContract get(String entityId){
    	ScmXsBigContract scmXsBigContract=super.get(entityId);
    	List<ScmXsBigContractProduct> scmXsBigContractProductList=scmXsBigContractProductDao.getByMainId(entityId);
    	scmXsBigContract.setScmXsBigContractProductList(scmXsBigContractProductList);
    	return scmXsBigContract;
    }
    
    /**
     * 更新实体同时更新子表记录
     */
    public void update(ScmXsBigContract scmXsBigContract){
    	ScmXsBigContract scmXsBigContract2 = scmXsBigContractDao.get(scmXsBigContract.getId());
    	scmXsBigContract.setContractCode(scmXsBigContract2.getContractCode());
    	super.update(scmXsBigContract);
    	String id=scmXsBigContract.getId();
    	scmXsBigContractProductDao.delByMainId(id);
    	List<ScmXsBigContractProduct> scmXsBigContractProductList=scmXsBigContract.getScmXsBigContractProductList();
    	for(ScmXsBigContractProduct scmXsBigContractProduct:scmXsBigContractProductList){
    		scmXsBigContractProduct.setBigContractId(id);
    		scmXsBigContractProduct.setPackageNum(0);
    		scmXsBigContractProduct.setIsSMS("0");
    		scmXsBigContractProduct.setAchange(0);
    		scmXsBigContractProduct.setLastShipmentDate(new Date());
    		scmXsBigContractProduct.setSumPrice(scmXsBigContractProduct.getTheNumber() * scmXsBigContractProduct.getUnitPrice());
    		scmXsBigContractProductManager.create(scmXsBigContractProduct);
    	}
    }
	@Override
	public PageList<ScmXsBigContract> getDownBox(String ownerId) {
		List<ScmXsBigContract> scmXsBigContract = scmXsBigContractDao.getDownBox(ownerId);
		return new PageList<ScmXsBigContract>(scmXsBigContract);
	}
	@Override
	public PageList<ScmXsBigContract> bigcontractGetYDetails(QueryFilter queryFilter) {
		PageBean pageBean = queryFilter.getPageBean();
		if(BeanUtils.isEmpty(pageBean)){
			PageHelper.startPage(1, Integer.MAX_VALUE, false);
		}
		else{
			PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
		}
		List<ScmXsBigContract> scmXsBigContract = scmXsBigContractDao.bigcontractGetYDetails(queryFilter.getParams());
		return new PageList<ScmXsBigContract>(scmXsBigContract);
	}	

	@Override
	public String sendApply(ScmXsBigContract scmXsBigContract) {
		ScmXsBigContract scmXsBigContractById = scmXsBigContractDao.get(scmXsBigContract.getId());

		//修改审批状态
	    //判断是修改还是保存
		if (scmXsBigContractById == null) {
			scmXsBigContract.setApprovalState("1");
			create(scmXsBigContract);
		}else{
			if("1".equals(scmXsBigContractById.getApprovalId())) {
				throw new RuntimeException("当前数据已在审批中,请勿重复提交");
			}
			scmXsBigContract.setApprovalState("1");
			scmXsBigContract.setEntryDate(scmXsBigContractById.getEntryDate());
			update(scmXsBigContract);
		}
		return scmXsBigContract.getContractCode();
	}
	@Override
	public void endApply(JsonNode jsonNode) {
		String approvalId = jsonNode.get("instId").asText();
		String actionName = jsonNode.get("actionName").asText();
		ScmXsBigContract scmXsBigContractById = scmXsBigContractDao.getSupplierFirstByApprovalId(approvalId);
		
		if(scmXsBigContractById == null) {
			throw new RuntimeException("未查询到业务数据,处理异常");
		}
		if ("agree".equals(actionName)) {			
			//审批状态调整为通过
			scmXsBigContractById.setConfirmDate(new Date());
			scmXsBigContractById.setApprovalState("2");
			//根据大合同id获取大合同商品表
			List<ScmXsBigContractProduct> scmXsBigContractProductList = scmXsBigContractProductDao.getByMainId(scmXsBigContractById.getId());
			Map<String, ScmXsBigContractProductSum> map = new HashMap<>();
			//根据经销商,货主id查询汇总
			List<ScmXsBigContractProductSum> scmXsBigContractProductSum = scmXsBigContractProductSumDao.getByOwnerIdAndcommerId(scmXsBigContractById.getOwnerId(), scmXsBigContractById.getCommerceFirstId(),scmXsBigContractById.getDealerClauseId());
		
			//以药品编码为键，对象为值
			for (ScmXsBigContractProductSum scmXsBigContractProductSum2 : scmXsBigContractProductSum) {
				map.put(scmXsBigContractProductSum2.getProductCode(), scmXsBigContractProductSum2);
			}
			//根据大合同中的药品编码，获取map中的汇总对象
			for (ScmXsBigContractProduct scmXsBigContractProduct : scmXsBigContractProductList) {
				ScmXsBigContractProductSum sbcps = map.get(scmXsBigContractProduct.getProductCode());
				if (sbcps == null) {
					//汇总表中无数据，在map中添加
	    			ScmXsBigContractProductSum sxbcp = toScmXsBigContractProductSum(scmXsBigContractProduct,scmXsBigContractById.getOwnerId(),scmXsBigContractById.getCommerceFirstId(),scmXsBigContractById.getDealerClauseId());
	    			map.put(scmXsBigContractProduct.getProductCode(), sxbcp);
				}else{
					//用数据，
					sbcps.setSurplusSUm(sbcps.getSurplusSUm() + scmXsBigContractProduct.getTheNumber());
					sbcps.setTheNumber(sbcps.getTheNumber() + scmXsBigContractProduct.getTheNumber());
				}
			}
			//保存
			Set<String> keySet = map.keySet();
			for (String string : keySet) {
				ScmXsBigContractProductSum sbcps = map.get(string);
				if (sbcps.getId() == null) {					
					scmXsBigContractProductSumManager.create(sbcps);
				}else{
					scmXsBigContractProductSumManager.update(sbcps);
				}
			}
		}else if("oppose".equals(actionName)){
			scmXsBigContractById.setApprovalState("3");
		}
		scmXsBigContractDao.update(scmXsBigContractById);	
	}
	@Override
	public ScmXsBigContract getIsContract(String ownerName, String dealer) {
		return null;
	}
	@Override
	public PageList<ScmXsBigContract> query(QueryFilter queryFilter) throws SystemException {
		// TODO Auto-generated method stub
		PageBean pageBean = queryFilter.getPageBean();
		if(BeanUtils.isEmpty(pageBean)){
			PageHelper.startPage(1, Integer.MAX_VALUE, false);
		}
		else{
			PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
		}
		List<ScmXsBigContract> scmXsBigContract = scmXsBigContractDao.query(queryFilter.getParams());
		for (ScmXsBigContract scmXsBigContract2 : scmXsBigContract) {
			List<ScmXsBigContractProduct> scmXsBigContractProduct = scmXsBigContractProductDao.getByMainId(scmXsBigContract2.getId());
			scmXsBigContract2.setScmXsBigContractProductList(scmXsBigContractProduct);
		}
		return new PageList<ScmXsBigContract>(scmXsBigContract);
	}
	
	@Override
	public ScmXsBigContract getByApplyId(String applyid) {
		ScmXsBigContract s = scmXsBigContractDao.getByApplyId(applyid);
		List<ScmXsBigContractProduct> byMainId = scmXsBigContractProductDao.getByMainId(s.getId());
		s.setScmXsBigContractProductList(byMainId);
		return s;
	}
}
