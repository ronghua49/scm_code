package com.winway.scm.persistence.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.manager.Manager;
import com.winway.scm.model.ScmXsAgreementSummary;
import com.winway.scm.model.ScmXsBigContractAllot;

/**
 * 
 * <pre> 
 * 描述：大合同分配表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:44
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmXsBigContractAllotManager extends Manager<String, ScmXsBigContractAllot>{

	String sendApply(ScmXsBigContractAllot scmXsBigContractAllot) throws Exception;

	void endApply(JsonNode jsonNode) throws JsonProcessingException;

	ScmXsBigContractAllot getByApplyId(String applyId);




}
