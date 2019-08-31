package com.winway.scm.persistence.manager.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.query.*;
import com.hotent.base.util.BeanUtils;
import com.winway.purchase.feign.ScmMasterDateFeignService;
import com.winway.scm.model.ScmXsBigContractProduct;
import com.winway.scm.persistence.dao.ScmXsBigContractProductDao;
import com.winway.scm.persistence.manager.ScmXsBigContractProductManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

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

	@Resource
	ScmMasterDateFeignService scmMasterDateFeignService;

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

		//传递该商业的首营id
		String  commerceFirstId = (String) queryFilter.getParams().get("commerceFirstId");
		String accreditProCodes1 = scmMasterDateFeignService.getAccreditProCodes1(commerceFirstId);
		Set<String> set = JSON.parseObject(accreditProCodes1, new TypeReference<Set<String>>() {
		});
		StringBuffer sb = new StringBuffer();
		for (String string : set) {
			sb.append(string);
			sb.append(",");
		}
		String string = sb.toString();
		if(string.length() > 0) {
			string = string.substring(0, string.length() - 1);
		}
		if(!set.isEmpty()){
			queryFilter.addFilter("productCode",string, QueryOP.IN, FieldRelation.AND);
		}
		queryFilter.getParams().remove("commerceFirstId");
		List<QueryField> querys = queryFilter.getQuerys();
		for (int i = 0; i < querys.size(); i++) {
			QueryField queryField = querys.get(i);
			if("commerceFirstId".equals(queryField.getProperty())) {
				querys.remove(i);
			}
		}
		QueryFilter build = QueryFilter.build();
		build.setQuerys(querys);
		List<ScmXsBigContractProduct> query = scmXsBigContractProductDao.bigcontractProductList(queryFilter.getParams());
		return new PageList<>(query);
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
	public PageList<ScmXsBigContractProduct> everyDayContractProductList(QueryFilter queryFilter) {
		PageBean pageBean = queryFilter.getPageBean();
		if(BeanUtils.isEmpty(pageBean)){
			PageHelper.startPage(1, Integer.MAX_VALUE, false);
		}
		else{
			PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
		}

		//传递该商业的首营id
		String  commerceFirstId = (String) queryFilter.getParams().get("commerceFirstId");
		String accreditProCodes1 = scmMasterDateFeignService.getAccreditProCodes1(commerceFirstId);
		Set<String> set = JSON.parseObject(accreditProCodes1, new TypeReference<Set<String>>() {
		});
		StringBuffer sb = new StringBuffer();
		for (String string : set) {
			sb.append(string);
			sb.append(",");
		}
		String string = sb.toString();
//		string = string.substring(0, string.length() - 1);
		if(!set.isEmpty()){
			queryFilter.addFilter("productCode",string, QueryOP.IN, FieldRelation.AND);
		}
		queryFilter.getParams().remove("commerceFirstId");
		List<QueryField> querys = queryFilter.getQuerys();
		for (int i = 0; i < querys.size(); i++) {
			QueryField queryField = querys.get(i);
			if("commerceFirstId".equals(queryField.getProperty())) {
				querys.remove(i);
			}
		}
		QueryFilter build = QueryFilter.build();
		build.setQuerys(querys);
		List<ScmXsBigContractProduct> query = scmXsBigContractProductDao.everyDayContractProductList(queryFilter.getParams());
		return new PageList<>(query);
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
