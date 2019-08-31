package com.winway.scm.persistence.manager;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.manager.Manager;
import com.winway.scm.model.ScmKcDrugDestructionMaster;

/**
 * 
 * <pre> 
 * 描述：不合格药品销毁主表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-13 15:06:38
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmKcDrugDestructionMasterManager extends Manager<String, ScmKcDrugDestructionMaster>{

	void sendApply(ScmKcDrugDestructionMaster scmKcDrugDestructionMaster);

	void endApply(JsonNode jsonNode);

	ScmKcDrugDestructionMaster getByApprovalId(String approvalId);
	
}
