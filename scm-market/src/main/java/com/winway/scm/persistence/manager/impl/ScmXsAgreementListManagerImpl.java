package com.winway.scm.persistence.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.query.PageBean;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.BeanUtils;
import com.winway.scm.model.ScmXsAgreementList;
import com.winway.scm.persistence.dao.ScmXsAgreementListDao;
import com.winway.scm.persistence.manager.ScmXsAgreementListManager;

/**
 * 
 * <pre> 
 * 描述：经销商协议名单申请表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 15:01:16
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmXsAgreementListManager")
public class ScmXsAgreementListManagerImpl extends AbstractManagerImpl<String, ScmXsAgreementList> implements ScmXsAgreementListManager{
	@Resource
	ScmXsAgreementListDao scmXsAgreementListDao;
	@Override
	protected MyBatisDao<String, ScmXsAgreementList> getDao() {
		return scmXsAgreementListDao;
	}
	@Override
	public PageList<ScmXsAgreementList> masterList(QueryFilter queryFilter) {
		PageBean pageBean = queryFilter.getPageBean();
    	if(BeanUtils.isEmpty(pageBean)){
    		PageHelper.startPage(1, Integer.MAX_VALUE, false);
    	}
    	else{
    		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
    	}
    	List<ScmXsAgreementList> query = scmXsAgreementListDao.masterList(queryFilter.getParams());
		return new PageList<ScmXsAgreementList>(query);
	}
	@Override
	public PageList<ScmXsAgreementList> commerceAndSales(QueryFilter queryFilter) {
		// TODO Auto-generated method stub
		PageBean pageBean = queryFilter.getPageBean();
    	if(BeanUtils.isEmpty(pageBean)){
    		PageHelper.startPage(1, Integer.MAX_VALUE, false);
    	}
    	else{
    		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
    	}
    	List<ScmXsAgreementList> query = scmXsAgreementListDao.commerceAndSales(queryFilter.getParams());
		return new PageList<ScmXsAgreementList>(query);
	}
	@Override
	public List<ScmXsAgreementList> getYears(String ownerId) {
		// TODO Auto-generated method stub
		List<ScmXsAgreementList> scmXsAgreementList = scmXsAgreementListDao.getYears(ownerId);
		return scmXsAgreementList;
	}
	@Override
	public ScmXsAgreementList getType(String commerceCode) {
		// TODO Auto-generated method stub
		ScmXsAgreementList scmXsAgreementList = scmXsAgreementListDao.getType(commerceCode);
		return scmXsAgreementList;
	}
	@Override
	public PageList<ScmXsAgreementList> addAgreementlist(QueryFilter queryFilter) {
		PageBean pageBean = queryFilter.getPageBean();
    	if(BeanUtils.isEmpty(pageBean)){
    		PageHelper.startPage(1, Integer.MAX_VALUE, false);
    	}
    	else{
    		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
    	}
    	List<ScmXsAgreementList> query = scmXsAgreementListDao.addAgreementlist(queryFilter.getParams());
    	return new PageList<ScmXsAgreementList>(query);
	}
	@Override
	public List<ScmXsAgreementList> updateAgreementlist(String masterId, String ownerid,String businessDivisionId) {
		List<ScmXsAgreementList> query = scmXsAgreementListDao.updateAgreementlist(masterId,ownerid,businessDivisionId);
		return query;
	}
}
