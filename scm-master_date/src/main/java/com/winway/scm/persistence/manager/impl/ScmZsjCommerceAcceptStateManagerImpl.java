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
import com.winway.scm.persistence.dao.ScmZsjCommerceAcceptStateDao;
import com.winway.scm.model.ScmZsjCommerceAcceptState;
import com.winway.scm.model.ScmZsjProduct;
import com.winway.scm.persistence.manager.ScmZsjCommerceAcceptStateManager;

/**
 * 
 * <pre> 
 * 描述：商业认可状态表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-18 14:36:01
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmZsjCommerceAcceptStateManager")
public class ScmZsjCommerceAcceptStateManagerImpl extends AbstractManagerImpl<String, ScmZsjCommerceAcceptState> implements ScmZsjCommerceAcceptStateManager{
	@Resource
	ScmZsjCommerceAcceptStateDao scmZsjCommerceAcceptStateDao;
	@Override
	protected MyBatisDao<String, ScmZsjCommerceAcceptState> getDao() {
		return scmZsjCommerceAcceptStateDao;
	}
	@Override
	public PageList<ScmZsjCommerceAcceptState> firstList(QueryFilter queryFilter) {
		PageBean pageBean = queryFilter.getPageBean();
    	if(BeanUtils.isEmpty(pageBean)){
    		PageHelper.startPage(1, Integer.MAX_VALUE, false);
    	}
    	else{
    		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
    	}
    	List<ScmZsjCommerceAcceptState> query = scmZsjCommerceAcceptStateDao.firstList(queryFilter.getParams());
        return new PageList<ScmZsjCommerceAcceptState>(query);
	}
}
