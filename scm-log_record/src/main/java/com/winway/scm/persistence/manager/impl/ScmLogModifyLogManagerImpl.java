package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import com.hotent.base.query.PageList;
import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmLogModifyLogDao;
import com.winway.scm.model.ScmLogModifyLog;
import com.winway.scm.persistence.manager.ScmLogModifyLogManager;

import java.util.List;

/**
 * 
 * <pre> 
 * 描述：scm_log_modifylog 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-02-23 11:33:08
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmLogModifyLogManager")
public class ScmLogModifyLogManagerImpl extends AbstractManagerImpl<String, ScmLogModifyLog> implements ScmLogModifyLogManager{
	@Resource
	ScmLogModifyLogDao scmLogModifyLogDao;

	@Override
	protected MyBatisDao<String, ScmLogModifyLog> getDao() {
		return scmLogModifyLogDao;
	}


}
