package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmGspRelevantImportantMessageDao;
import com.winway.scm.model.ScmGspRelevantImportantMessage;
import com.winway.scm.persistence.manager.ScmGspRelevantImportantMessageManager;

/**
 * 
 * <pre> 
 * 描述：相关重要信息 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-18 18:21:20
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmGspRelevantImportantMessageManager")
public class ScmGspRelevantImportantMessageManagerImpl extends AbstractManagerImpl<String, ScmGspRelevantImportantMessage> implements ScmGspRelevantImportantMessageManager{
	@Resource
	ScmGspRelevantImportantMessageDao scmGspRelevantImportantMessageDao;
	@Override
	protected MyBatisDao<String, ScmGspRelevantImportantMessage> getDao() {
		return scmGspRelevantImportantMessageDao;
	}
}
