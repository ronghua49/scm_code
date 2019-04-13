package com.winway.scm.persistence.manager.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.query.PageBean;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.BeanUtils;
import com.winway.scm.model.ScmZsjCommerce;
import com.winway.scm.model.ScmZsjSupplier;
import com.winway.scm.persistence.dao.ScmZsjCommerceDao;
import com.winway.scm.persistence.manager.ScmZsjCommerceManager;

/**
 * 
 * <pre> 
 * 描述：商业表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmZsjCommerceManager")
public class ScmZsjCommerceManagerImpl extends AbstractManagerImpl<String, ScmZsjCommerce> implements ScmZsjCommerceManager{
	@Resource
	ScmZsjCommerceDao scmZsjCommerceDao;
	@Override
	protected MyBatisDao<String, ScmZsjCommerce> getDao() {
		return scmZsjCommerceDao;
	}
	@Override
	public void getByNameOrId(ScmZsjCommerce scmZsjCommerce) {
		//根据名称与ID判断是否已经存在当前使用名称
		List<ScmZsjCommerce> sdas = scmZsjCommerceDao.getByNameOrId(scmZsjCommerce);
		if(sdas.size() > 0) {
			throw new RuntimeException("当前名称已经被使用,请修改后提交");
		}
	}
	@Override
	public List<ScmZsjCommerce> getByName(String commerceName) {
		List<ScmZsjCommerce> byNames = scmZsjCommerceDao.getByName(commerceName);
		return byNames;
	}
	//判断商业是否存在,不存在直接保存
	@Override
	public void isExist(ScmZsjCommerce scmZsjCommerce) {
		if(scmZsjCommerce == null) {
			throw new RuntimeException("商业信息不能为空");
		}
		//有商业ID 说明该商业已经存在
		if(scmZsjCommerce.getId() != null) {
			return;
		}
		if(scmZsjCommerce.getCommerceName() == null) {
			throw new RuntimeException("商业名称不能为空");
		}
		//根据名称查询该商业
		List<ScmZsjCommerce> byName = scmZsjCommerceDao.getByName(scmZsjCommerce.getCommerceName());
		//可以查询到,直接返回
		if(byName.size() == 0) {
			//没有查询到,保存
			scmZsjCommerceDao.create(scmZsjCommerce);
		}
	}
	@Override
	public PageList<ScmZsjCommerce> firstList(QueryFilter queryFilter) {
		PageBean pageBean = queryFilter.getPageBean();
    	if(BeanUtils.isEmpty(pageBean)){
    		PageHelper.startPage(1, Integer.MAX_VALUE, false);
    	}
    	else{
    		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
    	}
    	List<ScmZsjCommerce> query = scmZsjCommerceDao.firstList(queryFilter.getParams());
        return new PageList<ScmZsjCommerce>(query);
	}
	@Override
	public List<ScmZsjCommerce> downBox(String ownerId) {
		
    	List<ScmZsjCommerce> query = scmZsjCommerceDao.downBox(ownerId);
        return query;
	}
	@Override
	public PageList<ScmZsjCommerce> listCommerceAcceptState(QueryFilter queryFilter) {
		PageBean pageBean = queryFilter.getPageBean();
    	if(BeanUtils.isEmpty(pageBean)){
    		PageHelper.startPage(1, Integer.MAX_VALUE, false);
    	}
    	else{
    		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
    	}
    	List<ScmZsjCommerce> query = scmZsjCommerceDao.listCommerceAcceptState(queryFilter.getParams());
        return new PageList<ScmZsjCommerce>(query);
	}
	@Override
	public PageList<ScmZsjCommerce> listCommerceAcceptApprova(QueryFilter queryFilter) {
		PageBean pageBean = queryFilter.getPageBean();
    	if(BeanUtils.isEmpty(pageBean)){
    		PageHelper.startPage(1, Integer.MAX_VALUE, false);
    	}
    	else{
    		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
    	}
    	List<ScmZsjCommerce> query = scmZsjCommerceDao.listCommerceAcceptApprova(queryFilter.getParams());
        return new PageList<ScmZsjCommerce>(query);
	}
	@Override
	public PageList<ScmZsjCommerce> listCommerceAcceptTS(QueryFilter queryFilter) {
		PageBean pageBean = queryFilter.getPageBean();
    	if(BeanUtils.isEmpty(pageBean)){
    		PageHelper.startPage(1, Integer.MAX_VALUE, false);
    	}
    	else{
    		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
    	}
    	List<ScmZsjCommerce> query = scmZsjCommerceDao.listCommerceAcceptTS(queryFilter.getParams());
        return new PageList<ScmZsjCommerce>(query);
	}
	@Override
	public List<ScmZsjCommerce> downBoxApplySuccess(String ownerId) {
    	List<ScmZsjCommerce> query = scmZsjCommerceDao.downBoxApplySuccess(ownerId);
        return query;
	}
	@Override
	public PageList<ScmZsjCommerce> agreementSendApplySuccessList(QueryFilter queryFilter) {
		PageBean pageBean = queryFilter.getPageBean();
    	if(BeanUtils.isEmpty(pageBean)){
    		PageHelper.startPage(1, Integer.MAX_VALUE, false);
    	}
    	else{
    		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
    	}
    	List<ScmZsjCommerce> query = scmZsjCommerceDao.agreementSendApplySuccessList(queryFilter.getParams());
        return new PageList<ScmZsjCommerce>(query);
	}

	@Override
	public List<String> getCommerceName() {
		return scmZsjCommerceDao.getCommerceName();
	}
	@Override
	public String findByOwnerId(String ownerId) {
		List<ScmZsjCommerce> ScmZsjCommerce=null;
		try {
			ScmZsjCommerce=scmZsjCommerceDao.findByOwnerId(ownerId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Map map=new HashMap();
		for (ScmZsjCommerce x : ScmZsjCommerce) {
			map.put(x.getCommerceName(), x);
		}
		
		return JSON.toJSONString(map);
	}
}
