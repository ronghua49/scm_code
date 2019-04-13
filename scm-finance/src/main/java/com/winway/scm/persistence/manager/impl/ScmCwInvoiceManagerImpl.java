package com.winway.scm.persistence.manager.impl;

import java.util.Date;
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
import com.winway.scm.model.ScmCwInvoice;
import com.winway.scm.persistence.dao.ScmCwInvoiceDao;
import com.winway.scm.persistence.manager.ScmCwInvoiceManager;
import com.winway.scm.vo.VInvoice;

/**
 * 
 * <pre> 
 * 描述：商业发票表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-23 18:19:25
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmCwInvoiceManager")
public class ScmCwInvoiceManagerImpl extends AbstractManagerImpl<String, ScmCwInvoice> implements ScmCwInvoiceManager{
	@Resource
	ScmCwInvoiceDao scmCwInvoiceDao;
	@Override
	protected MyBatisDao<String, ScmCwInvoice> getDao() {
		return scmCwInvoiceDao;
	}
	@Override
	public PageList<ScmCwInvoice> findAll(QueryFilter queryFilter) {
		// TODO Auto-generated method stub
		PageBean pageBean = queryFilter.getPageBean();
    	if(BeanUtils.isEmpty(pageBean)){
    		PageHelper.startPage(1, Integer.MAX_VALUE, false);
    	}
    	else{
    		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
    	}
    	List<ScmCwInvoice> query = scmCwInvoiceDao.selAll(queryFilter.getParams());
		
		return new PageList<ScmCwInvoice>(query);
	}
	@Override
	public PageList<ScmCwInvoice> affirmData(String[] ids) {
		return null;
	}
	@Override
	public PageList<VInvoice> history(QueryFilter queryFilter) {
		PageBean pageBean = queryFilter.getPageBean();
    	if(BeanUtils.isEmpty(pageBean)){
    		PageHelper.startPage(1, Integer.MAX_VALUE, false);
    	}
    	else{
    		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
    	}
    	List<VInvoice> query = scmCwInvoiceDao.history(queryFilter.getParams());
    	for (VInvoice scmCwInvoice : query) {
			
		}
        return new PageList<VInvoice>(query);
	}
	
	public String date(Date d){
		
		return null;
	}
}
