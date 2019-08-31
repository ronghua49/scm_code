package com.winway.scm.persistence.dao;
import java.util.List;

import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmCwDynamicDiscountType;

/**
 * 
 * <pre> 
 * 描述：动态票折商品可票折类型 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-16 17:06:30
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmCwDynamicDiscountTypeDao extends MyBatisDao<String, ScmCwDynamicDiscountType> {


	void delByMainId(String id);

	List<ScmCwDynamicDiscountType> getByMainId(String id);
	
}
