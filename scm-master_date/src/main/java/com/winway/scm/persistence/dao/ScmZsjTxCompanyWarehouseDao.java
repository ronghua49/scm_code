package com.winway.scm.persistence.dao;
import java.util.List;

import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmZsjTxCompanyWarehouse;

/**
 * 
 * <pre> 
 * 描述：公司收回仓库 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-29 11:18:31
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmZsjTxCompanyWarehouseDao extends MyBatisDao<String, ScmZsjTxCompanyWarehouse> {

	List<ScmZsjTxCompanyWarehouse> getByOwnerId(String ownerId);
}
