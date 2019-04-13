package com.winway.scm.persistence.dao;
import java.util.List;

import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmZdDutyAffirm;
import com.winway.scm.model.ScmZdGetMoneyWarehouse;

/**
 * 
 * <pre> 
 * 描述：收货仓库 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-29 10:31:24
 * 版权：美达项目组
 * </pre>
 */
public interface ScmZdGetMoneyWarehouseDao extends MyBatisDao<String, ScmZdGetMoneyWarehouse> {
	List<ScmZdGetMoneyWarehouse> getByNameAndCodeOrId(ScmZdGetMoneyWarehouse scmZdGetMoneyWarehouse);
}
