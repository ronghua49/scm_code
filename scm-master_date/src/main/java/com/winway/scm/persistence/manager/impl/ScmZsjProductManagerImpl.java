package com.winway.scm.persistence.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.winway.scm.model.ScmZdDutyAffirm;
import com.winway.scm.model.ScmZsjAchage;
import com.winway.scm.model.ScmZsjProduct;
import com.winway.scm.model.ScmZsjProductLine;
import com.winway.scm.persistence.dao.ScmZsjAchageDao;
import com.winway.scm.persistence.dao.ScmZsjProductDao;
import com.winway.scm.persistence.dao.ScmZsjProductLineDao;
import com.winway.scm.persistence.manager.ScmZsjProductManager;

/**
 * 
 * <pre> 
 * 描述：商品表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmZsjProductManager")
public class ScmZsjProductManagerImpl extends AbstractManagerImpl<String, ScmZsjProduct> implements ScmZsjProductManager{
	@Resource
	ScmZsjProductDao scmZsjProductDao;
	@Resource
	ScmZsjAchageDao scmZsjAchageDao;
	@Resource
	ScmZsjProductLineDao scmZsjProductLineDao;
	
	
	@Override
	protected MyBatisDao<String, ScmZsjProduct> getDao() {
		return scmZsjProductDao;
	}
	@Override
	public List<ScmZsjProduct> getByProduct(String ownerId) {
		return scmZsjProductDao.getByProduct(ownerId);
	}
	@Override
	public void getByNameOrId(ScmZsjProduct scmZsjProduct) {
		//根据名称与ID判断是否已经存在当前使用名称
		List<ScmZdDutyAffirm> sdas = scmZsjProductDao.getByNameOrId(scmZsjProduct);
		if(sdas.size() > 0) {
			throw new RuntimeException("当前名称已经被使用,请修改后提交");
		}
		
	}
	@Override
	public PageList<ScmZsjProduct> firstList(QueryFilter queryFilter) {
		PageBean pageBean = queryFilter.getPageBean();
    	if(BeanUtils.isEmpty(pageBean)){
    		PageHelper.startPage(1, Integer.MAX_VALUE, false);
    	}
    	else{
    		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
    	}
    	List<ScmZsjProduct> query = scmZsjProductDao.firstList(queryFilter.getParams());
        return new PageList<ScmZsjProduct>(query);
	}
	@Override
	public List<ScmZsjProduct> downBox(String ownerId) {
		List<ScmZsjProduct> query = scmZsjProductDao.downBox(ownerId);
    	List<ScmZsjProduct> scmZsjProduct = new ArrayList<>();
    	if (query.size() != 0) {			
    		for (ScmZsjProduct scmZsjProduct1 : query) {
    			List<ScmZsjAchage> scmZsjAchage = scmZsjAchageDao.getByMainId(scmZsjProduct1.getId());
    			scmZsjProduct1.setScmZsjAchageList(scmZsjAchage);
    			scmZsjProduct.add(scmZsjProduct1);
    		}
		}
		return scmZsjProduct;
	}
	@Override
	public void isExist(ScmZsjProduct scmZsjProduct) {
		if(scmZsjProduct == null) {
			throw new RuntimeException("商品信息不能为空");
		}
		//有商业ID 说明该商品已经存在
		if(scmZsjProduct.getId() != null) {
			return;
		}
		if(scmZsjProduct.getProductName() == null) {
			throw new RuntimeException("商品名称不能为空");
		}
		//根据名称查询该商品
		List<ScmZsjProduct> byName = scmZsjProductDao.getByName(scmZsjProduct.getProductName());
		//可以查询到,直接返回
		if(byName.size() == 0) {
			//没有查询到,保存
			scmZsjProductDao.create(scmZsjProduct);
		}
	}
	@Override
	public List<ScmZsjProduct> downBoxApplySuccess(String ownerId, String SupplierId) {
		return scmZsjProductDao.downBoxApplySuccess(ownerId,SupplierId);
	}
	
	
	@Override
	public PageList<ScmZsjProduct> query(QueryFilter queryFilter) {
		PageBean pageBean = queryFilter.getPageBean();
    	if(BeanUtils.isEmpty(pageBean)){
    		PageHelper.startPage(1, Integer.MAX_VALUE, false);
    	}
    	else{
    		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
    	}
    	List<ScmZsjProduct> query = scmZsjProductDao.query(queryFilter.getParams());
    	if (query.size() != 0) {			
    		for (ScmZsjProduct scmZsjProduct1 : query) {
    			List<ScmZsjAchage> scmZsjAchage = scmZsjAchageDao.getByMainId(scmZsjProduct1.getId());
    			scmZsjProduct1.setScmZsjAchageList(scmZsjAchage);
    		}
		}
        return new PageList<ScmZsjProduct>(query);
	}
	
	
	@Override
	public List<Map<String,Object>> LineAndProductDownBox() {
		List<ScmZsjProductLine> query = scmZsjProductLineDao.query(new HashMap<>());
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for (ScmZsjProductLine scmZsjProductLine : query) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id", scmZsjProductLine.getId());
			map.put("lineName", scmZsjProductLine.getProductLineName());
			List<ScmZsjProduct> listByLineId = scmZsjProductDao.listByLineId(scmZsjProductLine.getId());
			map.put("productList", listByLineId);
			list.add(map);
		}
		return list;
	}
	@Override
	public List<String> listAndAchage(String productCode) {
		List<Map<String,Object>> achages =  scmZsjProductDao.listAndAchage(productCode);
		List<String> list = new ArrayList<>();
		for (Map<String, Object> map : achages) {
			String string = map.get("achage").toString();
			list.add(string);
		}
		return list;
	}
	@Override
	public ScmZsjProduct getProductByCode(String productCode) {
		return scmZsjProductDao.getProductByCode(productCode);
		
	}
}
