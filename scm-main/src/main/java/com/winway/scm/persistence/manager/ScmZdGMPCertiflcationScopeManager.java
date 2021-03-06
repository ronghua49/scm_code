package com.winway.scm.persistence.manager;

import com.hotent.base.manager.Manager;
import com.winway.scm.model.ScmZdGMPCertiflcationScope;

/**
 * 
 * <pre> 
 * 描述：GMP证书认证范围 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-29 10:31:24
 * 版权：美达项目组
 * </pre>
 */
public interface ScmZdGMPCertiflcationScopeManager extends Manager<String, ScmZdGMPCertiflcationScope>{

	void getByNameAndCodeOrId(ScmZdGMPCertiflcationScope scmZdGMPCertiflcationScope);
	
}
