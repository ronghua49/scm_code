package com.winway.scm.persistence.manager.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmCwDiscountPondPriceChangeDao;
import com.winway.scm.model.ScmCwDiscountPond;
import com.winway.scm.model.ScmCwDiscountPondPriceChange;
import com.winway.scm.persistence.manager.ScmCwDiscountPondManager;
import com.winway.scm.persistence.manager.ScmCwDiscountPondPriceChangeManager;

/**
 * 
 * <pre> 
 * 描述：票折池金额变动记录表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-08-17 10:03:21
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmCwDiscountPondPriceChangeManager")
public class ScmCwDiscountPondPriceChangeManagerImpl extends AbstractManagerImpl<String, ScmCwDiscountPondPriceChange> implements ScmCwDiscountPondPriceChangeManager{
	@Resource
	ScmCwDiscountPondPriceChangeDao scmCwDiscountPondPriceChangeDao;
	@Resource
	ScmCwDiscountPondManager scmCwDiscountPondManager;
	@Override
	protected MyBatisDao<String, ScmCwDiscountPondPriceChange> getDao() {
		return scmCwDiscountPondPriceChangeDao;
	}
	@Override
	public void changePoodPrice(ScmCwDiscountPondPriceChange scmCwDiscountPondPriceChange) {
		scmCwDiscountPondManager.changePoodPrice(scmCwDiscountPondPriceChange);
		scmCwDiscountPondPriceChange.setChangeDate(new Date());
		create(scmCwDiscountPondPriceChange);
	}
}
