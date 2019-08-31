package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmKcAllotDao;
import com.winway.scm.model.ScmKcAllot;
import com.winway.scm.persistence.manager.ScmKcAllotManager;

/**
 * 
 * <pre> 
 * 描述：库存调拨商品表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-23 17:00:46
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmKcAllotManager")
public class ScmKcAllotManagerImpl extends AbstractManagerImpl<String, ScmKcAllot> implements ScmKcAllotManager{
	@Resource
	ScmKcAllotDao scmKcAllotDao;
	@Override
	protected MyBatisDao<String, ScmKcAllot> getDao() {
		return scmKcAllotDao;
	}
}
