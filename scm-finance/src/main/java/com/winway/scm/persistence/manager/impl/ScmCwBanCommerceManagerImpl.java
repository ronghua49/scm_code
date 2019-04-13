package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmCwBanCommerceDao;
import com.winway.scm.model.ScmCwBanCommerce;
import com.winway.scm.persistence.manager.ScmCwBanCommerceManager;

/**
 * 
 * <pre> 
 * 描述：禁止票折商业表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-23 18:19:26
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmCwBanCommerceManager")
public class ScmCwBanCommerceManagerImpl extends AbstractManagerImpl<String, ScmCwBanCommerce> implements ScmCwBanCommerceManager{
	@Resource
	ScmCwBanCommerceDao scmCwBanCommerceDao;
	@Override
	protected MyBatisDao<String, ScmCwBanCommerce> getDao() {
		return scmCwBanCommerceDao;
	}
}
