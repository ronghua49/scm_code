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
import com.winway.scm.model.ScmXsCreditApply;
import com.hotent.base.model.WinwayResult;
import com.winway.scm.persistence.dao.ScmXsCreditApplyDao;
import com.winway.scm.persistence.manager.ScmXsCreditApplyManager;

/**
 * 
 * <pre> 
 * 描述：商业资信申请表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:43
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmXsCreditApplyManager")
public class ScmXsCreditApplyManagerImpl extends AbstractManagerImpl<String, ScmXsCreditApply> implements ScmXsCreditApplyManager{
	@Resource
	ScmXsCreditApplyDao scmXsCreditApplyDao;
	@Override
	protected MyBatisDao<String, ScmXsCreditApply> getDao() {
		return scmXsCreditApplyDao;
	}
	@Override
	public PageList<ScmXsCreditApply> firstList(QueryFilter queryFilter) {
		PageBean pageBean = queryFilter.getPageBean();
    	if(BeanUtils.isEmpty(pageBean)){
    		PageHelper.startPage(1, Integer.MAX_VALUE, false);
    	}
    	else{
    		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
    	}
    	List<ScmXsCreditApply> query = scmXsCreditApplyDao.firstList(queryFilter.getParams());
        return new PageList<ScmXsCreditApply>(query);
	}
	@Override
	public List<ScmXsCreditApply> getYears(String ownerId) {
		// TODO Auto-generated method stub
		List<ScmXsCreditApply> scmXsCreditApply = scmXsCreditApplyDao.getYears(ownerId);
		return scmXsCreditApply;
	}
	@Override
	public ScmXsCreditApply getStatisticalDate(String commerceId,String ownerId) {
		ScmXsCreditApply sxca = new ScmXsCreditApply();
		sxca.setBeforeYearPrice(0);
		sxca.setLastYearPrice(0);
		ScmXsCreditApply selectLastTimePrice = scmXsCreditApplyDao.selectLastTimePrice(commerceId,ownerId);
		if(selectLastTimePrice == null) {
			sxca.setLastTimePrice(0);
		}else{
			sxca.setLastTimePrice(selectLastTimePrice.getPrice());
		}
		sxca.setSysPrice(0);
		return sxca;
	}
}
