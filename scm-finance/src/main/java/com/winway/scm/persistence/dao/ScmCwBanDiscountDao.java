package com.winway.scm.persistence.dao;
import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmCwBanDiscount;

/**
 * 
 * <pre> 
 * 描述：禁止票折规则表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-27 11:30:57
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmCwBanDiscountDao extends MyBatisDao<String, ScmCwBanDiscount> {

	ScmCwBanDiscount getByBusinessDivisionId(String businessDivisionId, String ownerId);

	ScmCwBanDiscount getByProvinceId(String businessDivisionId, String ownerId);

	ScmCwBanDiscount getByCommerceId(String businessDivisionId, String ownerId);

	ScmCwBanDiscount getByProductCode(String productCode, String ownerId);

	ScmCwBanDiscount getByProductCodeAndBusinessDivisionId(String productCode, String businessDivisionId,String ownerId);

	ScmCwBanDiscount getByProductCodeAndProvinceId(String productCode, String provinceId, String ownerId);

	ScmCwBanDiscount getByProductCodeAndCommerceId(String productCode, String commerceId, String ownerId);
}
