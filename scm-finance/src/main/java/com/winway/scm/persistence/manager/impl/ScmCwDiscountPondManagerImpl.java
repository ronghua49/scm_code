package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmCwDiscountPondDao;
import com.winway.scm.model.ScmCwDiscountPond;
import com.winway.scm.persistence.manager.ScmCwDiscountPondManager;

/**
 * 
 * <pre> 
 * 描述：票折池表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-23 18:19:25
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmCwDiscountPondManager")
public class ScmCwDiscountPondManagerImpl extends AbstractManagerImpl<String, ScmCwDiscountPond> implements ScmCwDiscountPondManager{
	@Resource
	ScmCwDiscountPondDao scmCwDiscountPondDao;
	@Override
	protected MyBatisDao<String, ScmCwDiscountPond> getDao() {
		return scmCwDiscountPondDao;
	}
}
