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
import com.winway.scm.model.ScmCwBAStorageDiscount;
import com.winway.scm.persistence.dao.ScmCwBAStorageDiscountDao;
import com.winway.scm.persistence.manager.ScmCwBAStorageDiscountManager;

/**
 * 
 * <pre> 
 * 描述：经销商RB核算储运折扣 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-09 18:18:05
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmCwBAStorageDiscountManager")
public class ScmCwBAStorageDiscountManagerImpl extends AbstractManagerImpl<String, ScmCwBAStorageDiscount> implements ScmCwBAStorageDiscountManager{
	@Resource
	ScmCwBAStorageDiscountDao scmCwBAStorageDiscountDao;
	@Override
	protected MyBatisDao<String, ScmCwBAStorageDiscount> getDao() {
		return scmCwBAStorageDiscountDao;
	}
	@Override
	public PageList<ScmCwBAStorageDiscount> affirmList(QueryFilter queryFilter) {
		PageBean pageBean = queryFilter.getPageBean();
    	if(BeanUtils.isEmpty(pageBean)){
    		PageHelper.startPage(1, Integer.MAX_VALUE, false);
    	}
    	else{
    		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
    	}
    	List<ScmCwBAStorageDiscount> query = scmCwBAStorageDiscountDao.affirmList(queryFilter.getParams());
        return new PageList<ScmCwBAStorageDiscount>(query);
	}
}
