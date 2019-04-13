package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmZdReturnRequestCauseDao;
import com.winway.scm.model.ScmZdReturnRequestCause;
import com.winway.scm.persistence.manager.ScmZdReturnRequestCauseManager;

/**
 * 
 * <pre> 
 * 描述：退货申请原因 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-29 10:31:25
 * 版权：美达项目组
 * </pre>
 */
@Service("scmZdReturnRequestCauseManager")
public class ScmZdReturnRequestCauseManagerImpl extends AbstractManagerImpl<String, ScmZdReturnRequestCause> implements ScmZdReturnRequestCauseManager{
	@Resource
	ScmZdReturnRequestCauseDao scmZdReturnRequestCauseDao;
	@Override
	protected MyBatisDao<String, ScmZdReturnRequestCause> getDao() {
		return scmZdReturnRequestCauseDao;
	}
	@Override
	public void getByNameAndCodeOrId(ScmZdReturnRequestCause scmZdReturnRequestCause) {
		
	}
}
