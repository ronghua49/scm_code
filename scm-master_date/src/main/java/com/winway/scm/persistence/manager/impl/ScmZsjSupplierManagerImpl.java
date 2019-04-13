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
import com.winway.scm.persistence.dao.ScmZsjSupplierDao;
import com.winway.scm.model.ScmZdDutyAffirm;
import com.winway.scm.model.ScmZsjCommerce;
import com.winway.scm.model.ScmZsjSupplier;
import com.winway.scm.persistence.manager.ScmZsjSupplierManager;

/**
 * 
 * <pre> 
 * 描述：供应商表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmZsjSupplierManager")
public class ScmZsjSupplierManagerImpl extends AbstractManagerImpl<String, ScmZsjSupplier> implements ScmZsjSupplierManager{
	@Resource
	ScmZsjSupplierDao scmZsjSupplierDao;
	@Override
	protected MyBatisDao<String, ScmZsjSupplier> getDao() {
		return scmZsjSupplierDao;
	}
	@Override
	public void getByNameOrId(ScmZsjSupplier scmZsjSupplier) {
		//根据名称与ID判断是否已经存在当前使用名称
		List<ScmZdDutyAffirm> sdas = scmZsjSupplierDao.getByNameOrId(scmZsjSupplier);
		if(sdas.size() > 0) {
			throw new RuntimeException("当前名称已经被使用,请修改后提交");
		}
	}
	@Override
	public void isExist(ScmZsjSupplier scmZsjSupplier) {
		if(scmZsjSupplier == null) {
			throw new RuntimeException("商业信息不能为空");
		}
		//有商业ID 说明该商业已经存在
		if(scmZsjSupplier.getId() != null) {
			return;
		}
		if(scmZsjSupplier.getSupplierName() == null) {
			throw new RuntimeException("商业名称不能为空");
		}
		//根据名称查询该商业
		List<ScmZsjCommerce> byName = scmZsjSupplierDao.getByName(scmZsjSupplier.getSupplierName());
		//可以查询到,直接返回
		if(byName.size() == 0) {
			//没有查询到,保存
			scmZsjSupplierDao.create(scmZsjSupplier);
		}
		
	}
	@Override
	public PageList<ScmZsjSupplier> firstList(QueryFilter queryFilter) {
    	PageBean pageBean = queryFilter.getPageBean();
    	if(BeanUtils.isEmpty(pageBean)){
    		PageHelper.startPage(1, Integer.MAX_VALUE, false);
    	}
    	else{
    		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
    	}
    	List<ScmZsjSupplier> query = scmZsjSupplierDao.firstList(queryFilter.getParams());
        return new PageList<ScmZsjSupplier>(query);
	}
	@Override
	public List<ScmZsjSupplier> downBox(String ownerId) {
		List<ScmZsjSupplier> downBox = scmZsjSupplierDao.downBox(ownerId);
		return downBox;
	}
	@Override
	public List<ScmZsjSupplier> downBoxApplySuccess(String ownerId) {
		List<ScmZsjSupplier> downBox = scmZsjSupplierDao.downBoxApplySuccess(ownerId);
		return downBox;
	}
}
