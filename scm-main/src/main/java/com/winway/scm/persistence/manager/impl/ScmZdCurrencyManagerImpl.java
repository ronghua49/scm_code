package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmZdCurrencyDao;
import com.winway.scm.model.ScmZdCurrency;
import com.winway.scm.persistence.manager.ScmZdCurrencyManager;

/**
 * 
 * <pre> 
 * 描述：币种 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-06-16 22:35:42
 * 版权：美达项目组
 * </pre>
 */
@Service("scmZdCurrencyManager")
public class ScmZdCurrencyManagerImpl extends AbstractManagerImpl<String, ScmZdCurrency> implements ScmZdCurrencyManager{
	@Resource
	ScmZdCurrencyDao scmZdCurrencyDao;
	@Override
	protected MyBatisDao<String, ScmZdCurrency> getDao() {
		return scmZdCurrencyDao;
	}
}
