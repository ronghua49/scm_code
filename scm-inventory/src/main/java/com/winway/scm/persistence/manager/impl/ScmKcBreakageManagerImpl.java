package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmKcBreakageDao;
import com.winway.scm.model.ScmKcBreakage;
import com.winway.scm.persistence.manager.ScmKcBreakageManager;

/**
 * 
 * <pre> 
 * 描述：不合格产品报损商品表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-23 17:00:46
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmKcBreakageManager")
public class ScmKcBreakageManagerImpl extends AbstractManagerImpl<String, ScmKcBreakage> implements ScmKcBreakageManager{
	@Resource
	ScmKcBreakageDao scmKcBreakageDao;
	@Override
	protected MyBatisDao<String, ScmKcBreakage> getDao() {
		return scmKcBreakageDao;
	}
}
