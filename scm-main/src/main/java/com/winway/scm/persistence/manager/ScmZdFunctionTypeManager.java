package com.winway.scm.persistence.manager;

import com.hotent.base.manager.Manager;
import com.winway.scm.model.ScmZdCommerceLevel;
import com.winway.scm.model.ScmZdFunctionType;

/**
 * 
 * <pre> 
 * 描述：scm_zd_functiontype 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-16 10:34:19
 * 版权：美达项目组
 * </pre>
 */
public interface ScmZdFunctionTypeManager extends Manager<String, ScmZdFunctionType>{
	void getByNameAndCodeOrId(ScmZdFunctionType scmZdFunctionType);
}
