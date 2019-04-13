package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmZsjCommerceWarehouseDao;
import com.winway.scm.model.ScmZsjCommerceWarehouse;
import com.winway.scm.persistence.manager.ScmZsjCommerceWarehouseManager;

/**
 * 
 * <pre> 
 * 描述：商业生产/仓库地址 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmZsjCommerceWarehouseManager")
public class ScmZsjCommerceWarehouseManagerImpl extends AbstractManagerImpl<String, ScmZsjCommerceWarehouse> implements ScmZsjCommerceWarehouseManager{
	@Resource
	ScmZsjCommerceWarehouseDao scmZsjCommerceWarehouseDao;
	@Override
	protected MyBatisDao<String, ScmZsjCommerceWarehouse> getDao() {
		return scmZsjCommerceWarehouseDao;
	}
}
