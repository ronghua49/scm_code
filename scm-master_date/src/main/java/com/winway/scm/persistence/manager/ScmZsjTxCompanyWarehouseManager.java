package com.winway.scm.persistence.manager;

import com.hotent.base.manager.Manager;
import com.winway.scm.model.ScmZsjTxCompanyWarehouse;

/**
 * 
 * <pre> 
 * 描述：公司收回仓库 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-29 11:18:31
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmZsjTxCompanyWarehouseManager extends Manager<String, ScmZsjTxCompanyWarehouse>{

	void setDefault(String id);
	
}
