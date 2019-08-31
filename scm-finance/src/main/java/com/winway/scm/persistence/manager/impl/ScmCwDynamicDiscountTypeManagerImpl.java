package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmCwDynamicDiscountTypeDao;
import com.winway.scm.model.ScmCwDynamicDiscountType;
import com.winway.scm.persistence.manager.ScmCwDynamicDiscountTypeManager;

/**
 * 
 * <pre> 
 * 描述：动态票折商品可票折类型 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-16 17:06:30
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmCwDynamicDiscountTypeManager")
public class ScmCwDynamicDiscountTypeManagerImpl extends AbstractManagerImpl<String, ScmCwDynamicDiscountType> implements ScmCwDynamicDiscountTypeManager{
	@Resource
	ScmCwDynamicDiscountTypeDao scmCwDynamicDiscountTypeDao;
	@Override
	protected MyBatisDao<String, ScmCwDynamicDiscountType> getDao() {
		return scmCwDynamicDiscountTypeDao;
	}
}
