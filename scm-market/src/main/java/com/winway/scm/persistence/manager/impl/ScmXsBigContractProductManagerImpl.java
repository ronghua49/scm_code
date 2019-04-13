package com.winway.scm.persistence.manager.impl;

import com.github.pagehelper.PageHelper;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.query.PageBean;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.BeanUtils;
import com.winway.scm.model.ScmXsBigContractProduct;
import com.winway.scm.persistence.dao.ScmXsBigContractProductDao;
import com.winway.scm.persistence.manager.ScmXsBigContractProductManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 
 * <pre> 
 * 描述：大合同商品表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:44
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmXsBigContractProductManager")
public class ScmXsBigContractProductManagerImpl extends AbstractManagerImpl<String, ScmXsBigContractProduct> implements ScmXsBigContractProductManager{
	@Resource
	ScmXsBigContractProductDao scmXsBigContractProductDao;
	@Override
	protected MyBatisDao<String, ScmXsBigContractProduct> getDao() {
		return scmXsBigContractProductDao;
	}
	@Override
	public PageList<ScmXsBigContractProduct> groupByList(QueryFilter queryFilter) {
		PageBean pageBean = queryFilter.getPageBean();
    	if(BeanUtils.isEmpty(pageBean)){
    		PageHelper.startPage(1, Integer.MAX_VALUE, false);
    	}
    	else{
    		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
    	}
    	List<ScmXsBigContractProduct> query = scmXsBigContractProductDao.groupByList(queryFilter.getParams());
		return new PageList<ScmXsBigContractProduct>(query);
	}
	@Override
	public PageList<ScmXsBigContractProduct> bigcontractProductList(QueryFilter queryFilter) {
		PageBean pageBean = queryFilter.getPageBean();
		if(BeanUtils.isEmpty(pageBean)){
			PageHelper.startPage(1, Integer.MAX_VALUE, false);
		}
		else{
			PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
		}
		List<ScmXsBigContractProduct> query = scmXsBigContractProductDao.bigcontractProductList(queryFilter.getParams());
		return new PageList<ScmXsBigContractProduct>(query);
	}
	@Override
	public PageList<ScmXsBigContractProduct> groupList(QueryFilter queryFilter) {
		PageBean pageBean = queryFilter.getPageBean();
    	if(BeanUtils.isEmpty(pageBean)){
    		PageHelper.startPage(1, Integer.MAX_VALUE, false);
    	}
    	else{
    		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
    	}
    	List<ScmXsBigContractProduct> query = scmXsBigContractProductDao.groupList(queryFilter.getParams());
		return new PageList<ScmXsBigContractProduct>(query);
	}
	
	@Override
	public void create(ScmXsBigContractProduct arg0) {
		if(arg0.getSMSCurrentMonthSales() == null || arg0.getSMSCurrentMonthSales() == 0) {
			arg0.setSMSCurrentMonthSales(0);
		}
		if(arg0.getSuggestApplyNum() == null || arg0.getSuggestApplyNum() == 0) {
			arg0.setSuggestApplyNum(0);
		}
		super.create(arg0);
	}
}
