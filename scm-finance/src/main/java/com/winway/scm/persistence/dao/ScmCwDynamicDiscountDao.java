package com.winway.scm.persistence.dao;
import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmCwDynamicDiscount;

/**
 * 
 * <pre> 
 * 描述：动态票折规则 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-23 18:19:26
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmCwDynamicDiscountDao extends MyBatisDao<String, ScmCwDynamicDiscount> {
    ScmCwDynamicDiscount getByProductIdAndOwnerId(String productId, String ownerId);

	ScmCwDynamicDiscount getByProductCode(String productCode, String ownerId);

	ScmCwDynamicDiscount getByProductCodeAndCommerceId(String productCode, String commerceId, String ownerId);
}
