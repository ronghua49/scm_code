package com.winway.purchase.persistence.manager.impl;

import javax.annotation.Resource;

import com.hotent.base.exception.SystemException;
import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.purchase.persistence.dao.ScmSysLogDao;
import com.winway.purchase.model.ScmSysLog;
import com.winway.purchase.persistence.manager.ScmSysLogManager;

/**
 * 
 * <pre> 
 * 描述：scm_sys_log 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-07-24 17:36:05
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmSysLogManager")
public class ScmSysLogManagerImpl extends AbstractManagerImpl<String, ScmSysLog> implements ScmSysLogManager{
	@Resource
	ScmSysLogDao scmSysLogDao;
	@Override
	protected MyBatisDao<String, ScmSysLog> getDao() {
		return scmSysLogDao;
	}


}
