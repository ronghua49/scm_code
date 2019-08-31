package com.winway.scm.persistence.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmZsjTxCompanyWarehouseDao;
import com.winway.scm.model.ScmZsjTxCompanyWarehouse;
import com.winway.scm.persistence.manager.ScmZsjTxCompanyWarehouseManager;

/**
 * 
 * <pre> 
 * 描述：公司收回仓库 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-29 11:18:31
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmZsjTxCompanyWarehouseManager")
public class ScmZsjTxCompanyWarehouseManagerImpl extends AbstractManagerImpl<String, ScmZsjTxCompanyWarehouse> implements ScmZsjTxCompanyWarehouseManager{
	@Resource
	ScmZsjTxCompanyWarehouseDao scmZsjTxCompanyWarehouseDao;
	@Override
	protected MyBatisDao<String, ScmZsjTxCompanyWarehouse> getDao() {
		return scmZsjTxCompanyWarehouseDao;
	}
	@Override
	public void create(ScmZsjTxCompanyWarehouse entity) {
			if(entity.getOwnerId() == null || "".equals(entity.getOwnerId()) || "null".equals(entity.getOwnerId())) {
				throw new RuntimeException("货主ID未获取到");
			}
			super.create(entity);
	}
	@Override
	public void update(ScmZsjTxCompanyWarehouse entity) {
			super.update(entity);
	}
	@Override
	public void setDefault(String id) {
		return;
	}
}
