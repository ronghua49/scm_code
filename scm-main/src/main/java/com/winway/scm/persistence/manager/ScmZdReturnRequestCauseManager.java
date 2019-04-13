package com.winway.scm.persistence.manager;

import com.hotent.base.manager.Manager;
import com.winway.scm.model.ScmZdReturnRequestCause;

/**
 * 
 * <pre> 
 * 描述：退货申请原因 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-29 10:31:25
 * 版权：美达项目组
 * </pre>
 */
public interface ScmZdReturnRequestCauseManager extends Manager<String, ScmZdReturnRequestCause>{

	void getByNameAndCodeOrId(ScmZdReturnRequestCause scmZdReturnRequestCause);
	
}
