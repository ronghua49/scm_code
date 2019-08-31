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
import com.winway.scm.model.ScmXsDistributorClause;
import com.winway.scm.persistence.dao.ScmXsDistributorClauseDao;
import com.winway.scm.persistence.manager.ScmXsDistributorClauseManager;

/**
 * 
 * <pre> 
 * 描述：分销商协议合作条款表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:43
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmXsDistributorClauseManager")
public class ScmXsDistributorClauseManagerImpl extends AbstractManagerImpl<String, ScmXsDistributorClause> implements ScmXsDistributorClauseManager{
	@Resource
	ScmXsDistributorClauseDao scmXsDistributorClauseDao;
	@Override
	protected MyBatisDao<String, ScmXsDistributorClause> getDao() {
		return scmXsDistributorClauseDao;
	}
	@Override
	public PageList<ScmXsDistributorClause> firstList(QueryFilter queryFilter) {
		PageBean pageBean = queryFilter.getPageBean();
    	if(BeanUtils.isEmpty(pageBean)){
    		PageHelper.startPage(1, Integer.MAX_VALUE, false);
    	}
    	else{
    		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
    	}
    	List<ScmXsDistributorClause> query = scmXsDistributorClauseDao.firstList(queryFilter.getParams());
        return new PageList<ScmXsDistributorClause>(query);
	}
	
	@Override
	public PageList<ScmXsDistributorClause> commerceAndAcceptState(QueryFilter queryFilter) {
		PageBean pageBean = queryFilter.getPageBean();
    	if(BeanUtils.isEmpty(pageBean)){
    		PageHelper.startPage(1, Integer.MAX_VALUE, false);
    	}
    	else{
    		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
    	}
    	List<ScmXsDistributorClause> query = scmXsDistributorClauseDao.commerceAndAcceptState(queryFilter.getParams());
        return new PageList<ScmXsDistributorClause>(query);
	}
	@Override
	public List<ScmXsDistributorClause> getYears(String ownerId) {
		return scmXsDistributorClauseDao.getYears(ownerId);
	}

	@Override
	public List<ScmXsDistributorClause> list1(String businessDivisionId, String commerceName, String acceptState,String provinceId,String commerceCode) {
		return scmXsDistributorClauseDao.list1(businessDivisionId,commerceName,acceptState,provinceId,commerceCode);
	}
}
