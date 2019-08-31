package com.winway.scm.persistence.dao;
import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmCwDiscountRuleRecord;

import java.util.List;
import java.util.Map;

/**
 * 
 * <pre> 
 * 描述：scm_cw_discountrulerecord DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-27 14:18:12
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmCwDiscountRuleRecordDao extends MyBatisDao<String, ScmCwDiscountRuleRecord> {
    List<ScmCwDiscountRuleRecord> getByIdInfo(String commerceId, String productId, String id);

	Map<String, Object> getProductCountByDiscountId(String id, String commerceId, String productId);
}
