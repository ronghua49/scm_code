package com.winway.scm.persistence.dao;
import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmCwToSapDiscount;

/**
 * 
 * <pre> 
 * 描述：推送sap开票确认表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-07-05 23:55:03
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmCwToSapDiscountDao extends MyBatisDao<String, ScmCwToSapDiscount> {

	ScmCwToSapDiscount getByShipId(String id);
}
