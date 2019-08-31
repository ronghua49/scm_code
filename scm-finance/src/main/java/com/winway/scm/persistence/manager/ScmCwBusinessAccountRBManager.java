package com.winway.scm.persistence.manager;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.manager.Manager;
import com.winway.scm.model.ScmCwBusinessAccountRB;

import java.util.List;
import java.util.Map;

/**
 * 
 * <pre> 
 * 描述：经销商RB核算总表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-09 18:18:05
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmCwBusinessAccountRBManager extends Manager<String, ScmCwBusinessAccountRB>{

    List<ScmCwBusinessAccountRB> queryByParamMap(Map<String, Object> map);

	void sendApply(ScmCwBusinessAccountRB scmCwBusinessAccountRB);

	void endApply(JsonNode jsonNode);

	ScmCwBusinessAccountRB getByApprovalId(String approvalId);
	
}
