package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmZsjSupplierWarehouseDao;
import com.winway.scm.model.ScmZsjSupplierWarehouse;
import com.winway.scm.persistence.manager.ScmZsjSupplierWarehouseManager;

/**
 * 
 * <pre> 
 * 描述：供应商生产/仓库地址 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmZsjSupplierWarehouseManager")
public class ScmZsjSupplierWarehouseManagerImpl extends AbstractManagerImpl<String, ScmZsjSupplierWarehouse> implements ScmZsjSupplierWarehouseManager{
	@Resource
	ScmZsjSupplierWarehouseDao scmZsjSupplierWarehouseDao;
	@Override
	protected MyBatisDao<String, ScmZsjSupplierWarehouse> getDao() {
		return scmZsjSupplierWarehouseDao;
	}
}
