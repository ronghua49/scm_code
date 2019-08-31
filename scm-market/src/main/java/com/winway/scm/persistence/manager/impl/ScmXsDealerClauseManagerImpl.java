package com.winway.scm.persistence.manager.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.query.PageBean;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.BeanUtils;
import com.winway.scm.model.ScmXsAgreementSpecialProduct;
import com.winway.scm.model.ScmXsAgreementSubsidiary;
import com.winway.scm.model.ScmXsAgreementSummary;
import com.winway.scm.model.ScmXsDealerClause;
import com.winway.scm.persistence.dao.ScmXsAgreementSpecialProductDao;
import com.winway.scm.persistence.dao.ScmXsAgreementSubsidiaryDao;
import com.winway.scm.persistence.dao.ScmXsAgreementSummaryDao;
import com.winway.scm.persistence.dao.ScmXsDealerClauseDao;
import com.winway.scm.persistence.manager.ScmXsAgreementListManager;
import com.winway.scm.persistence.manager.ScmXsAgreementSpecialProductManager;
import com.winway.scm.persistence.manager.ScmXsAgreementSubsidiaryManager;
import com.winway.scm.persistence.manager.ScmXsDealerClauseManager;

/**
 * 
 * <pre> 
 * 描述：经销商协议合作条款表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:44
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmXsDealerClauseManager")
public class ScmXsDealerClauseManagerImpl extends AbstractManagerImpl<String, ScmXsDealerClause> implements ScmXsDealerClauseManager{
	@Resource
	ScmXsDealerClauseDao scmXsDealerClauseDao;
	@Resource
	ScmXsAgreementSpecialProductDao scmXsAgreementSpecialProductDao;
	@Resource
	ScmXsAgreementSubsidiaryDao scmXsAgreementSubsidiaryDao;
	@Resource
	ScmXsAgreementSpecialProductManager scmXsAgreementSpecialProductManager;
	@Resource
	ScmXsAgreementSubsidiaryManager scmXsAgreementSubsidiaryManager;
	@Resource
	ScmXsAgreementSummaryDao scmXsAgreementSummaryDao;
	@Resource
	ScmXsAgreementListManager scmXsAgreementListManager;
	
	@Override
	protected MyBatisDao<String, ScmXsDealerClause> getDao() {
		return scmXsDealerClauseDao;
	}
	/**
	 * 创建实体包含子表实体
	 */
	@Override
	public void create(ScmXsDealerClause scmXsDealerClause){
		//获取主表数据
		ScmXsAgreementSummary ScmXsAgreementSummary = scmXsAgreementSummaryDao.get(scmXsDealerClause.getAgreementSummaryId());
//		ScmXsDealerClause scmXsDealerClauseByCode = scmXsDealerClauseDao.lastPaymentType(ScmXsAgreementSummary.getBusinessDivisionId(),ScmXsAgreementSummary.getProvinceId().equals("") ? null : ScmXsAgreementSummary.getProvinceId(),ScmXsAgreementSummary.getOwnerId(), scmXsDealerClause.getCommerceCode());
//		if (scmXsDealerClauseByCode == null) {			
			//查询上次付款方式
//			scmXsDealerClause.setLastPaymentType("-");
			//查询上次回款天数
//			scmXsDealerClause.setLastReturnMoneyDay(0);
			//查询上次申请折扣
//			scmXsDealerClause.setLastTheApplication(0);
//		}else{
			//查询上次付款方式
//			scmXsDealerClause.setLastPaymentType(scmXsDealerClauseByCode.getLastPaymentType());
			//查询上次回款天数
//			scmXsDealerClause.setLastReturnMoneyDay(scmXsDealerClauseByCode.getLastReturnMoneyDay());
			//查询上次申请折扣
//			scmXsDealerClause.setLastTheApplication(scmXsDealerClauseByCode.getLastTheApplication());
//		}
		//查询季度预付款占比
		scmXsDealerClause.setQuarterPaymentPercent(0.25);
    	super.create(scmXsDealerClause);
    	String pk=scmXsDealerClause.getId();  
    	scmXsAgreementSpecialProductManager.delByMainId(pk);
    	scmXsAgreementSubsidiaryManager.delByMainId(pk);
    	List<ScmXsAgreementSpecialProduct> scmXsAgreementSpecialProductList=scmXsDealerClause.getScmXsAgreementSpecialProductList();
    	for(ScmXsAgreementSpecialProduct scmXsAgreementSpecialProduct:scmXsAgreementSpecialProductList){
    		scmXsAgreementSpecialProduct.setAgreementId(pk);
    		scmXsAgreementSpecialProductManager.create(scmXsAgreementSpecialProduct);
    	}
    	List<ScmXsAgreementSubsidiary> scmXsAgreementSubsidiaryList=scmXsDealerClause.getScmXsAgreementSubsidiaryList();
    	for(ScmXsAgreementSubsidiary scmXsAgreementSubsidiary:scmXsAgreementSubsidiaryList){
    		scmXsAgreementSubsidiary.setAgreementId(pk);
    		scmXsAgreementSubsidiaryManager.create(scmXsAgreementSubsidiary);
    	}
    }
	
	/**
	 * 删除记录包含子表记录
	 */
	public void remove(String entityId){
		super.remove(entityId);
    	scmXsAgreementSpecialProductDao.delByMainId(entityId);
    	scmXsAgreementSubsidiaryDao.delByMainId(entityId);
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
    public ScmXsDealerClause get(String entityId){
    	ScmXsDealerClause scmXsDealerClause=super.get(entityId);
    	List<ScmXsAgreementSpecialProduct> scmXsAgreementSpecialProductList=scmXsAgreementSpecialProductDao.getByMainId(entityId);
    	scmXsDealerClause.setScmXsAgreementSpecialProductList(scmXsAgreementSpecialProductList);
    	List<ScmXsAgreementSubsidiary> scmXsAgreementSubsidiaryList=scmXsAgreementSubsidiaryDao.getByMainId(entityId);
    	scmXsDealerClause.setScmXsAgreementSubsidiaryList(scmXsAgreementSubsidiaryList);
    	return scmXsDealerClause;
    }
    
    /**
     * 更新实体同时更新子表记录
     */
    public void update(ScmXsDealerClause scmXsDealerClause){
    	super.update(scmXsDealerClause);
    	String id=scmXsDealerClause.getId();
    	scmXsAgreementSpecialProductDao.delByMainId(id);
    	List<ScmXsAgreementSpecialProduct> scmXsAgreementSpecialProductList=scmXsDealerClause.getScmXsAgreementSpecialProductList();
    	for(ScmXsAgreementSpecialProduct scmXsAgreementSpecialProduct:scmXsAgreementSpecialProductList){
    		scmXsAgreementSpecialProduct.setAgreementId(id);
    		scmXsAgreementSpecialProductManager.create(scmXsAgreementSpecialProduct);
    	}
    	scmXsAgreementSubsidiaryDao.delByMainId(id);
    	List<ScmXsAgreementSubsidiary> scmXsAgreementSubsidiaryList=scmXsDealerClause.getScmXsAgreementSubsidiaryList();
    	for(ScmXsAgreementSubsidiary scmXsAgreementSubsidiary:scmXsAgreementSubsidiaryList){
    		scmXsAgreementSubsidiary.setAgreementId(id);
    		scmXsAgreementSubsidiaryManager.create(scmXsAgreementSubsidiary);
    	}
    }
	@Override
	public PageList<ScmXsDealerClause> commerceList(String ownerId, String provinceId) {
		// TODO Auto-generated method stub
		List<ScmXsDealerClause> scmXsDealerClause = scmXsDealerClauseDao.commerceList(ownerId,provinceId);
		return new PageList<ScmXsDealerClause>(scmXsDealerClause);
	}
	@Override
	public PageList<ScmXsDealerClause> firstQuery(QueryFilter queryFilter) {
		// TODO Auto-generated method stub
		PageBean pageBean = queryFilter.getPageBean();
		if(BeanUtils.isEmpty(pageBean)){
			PageHelper.startPage(1, Integer.MAX_VALUE, false);
		}
		else{
			PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
		}
		List<ScmXsDealerClause> scmXsDealerClause = scmXsDealerClauseDao.firstQuery(queryFilter.getParams());
		for (ScmXsDealerClause scmXsDealerClause2 : scmXsDealerClause) {
			List<ScmXsAgreementSpecialProduct> scmXsAgreementSpecialProduct = scmXsAgreementSpecialProductDao.getByMainId(scmXsDealerClause2.getId());
			List<ScmXsAgreementSubsidiary> ScmXsAgreementSubsidiary = scmXsAgreementSubsidiaryDao.getByMainId(scmXsDealerClause2.getId());
			scmXsDealerClause2.setScmXsAgreementSpecialProductList(scmXsAgreementSpecialProduct);
			scmXsDealerClause2.setScmXsAgreementSubsidiaryList(ScmXsAgreementSubsidiary);
		}
		return new PageList<ScmXsDealerClause>(scmXsDealerClause);
	}
	@Override
	public List<Map<String, Object>> getYears(String ownerId) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> scmXsDealerClause = scmXsDealerClauseDao.getYears(ownerId);
		
		return scmXsDealerClause;
	}
	
}
