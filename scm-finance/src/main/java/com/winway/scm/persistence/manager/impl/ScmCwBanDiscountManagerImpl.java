package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmCwBanDiscountDao;
import com.winway.scm.model.ScmCwBanDiscount;
import com.winway.scm.persistence.manager.ScmCwBanDiscountManager;

/**
 * 
 * <pre> 
 * 描述：禁止票折规则表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-27 11:30:57
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmCwBanDiscountManager")
public class ScmCwBanDiscountManagerImpl extends AbstractManagerImpl<String, ScmCwBanDiscount> implements ScmCwBanDiscountManager{
	@Resource
	ScmCwBanDiscountDao scmCwBanDiscountDao;
	@Override
	protected MyBatisDao<String, ScmCwBanDiscount> getDao() {
		return scmCwBanDiscountDao;
	}
}
