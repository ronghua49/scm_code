package com.winway.scm.persistence.manager;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.manager.Manager;
import com.winway.scm.model.ScmCwDistributorRB;

/**
 * 
 * <pre> 
 * 描述：分销商RB申请主表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-14 16:38:31
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmCwDistributorRBManager extends Manager<String, ScmCwDistributorRB>{

	void sendApply(ScmCwDistributorRB scmCwDistributorRB);

	void endApply(JsonNode jsonNode);

	ScmCwDistributorRB getByApprovalId(String approvalId);
	
}
