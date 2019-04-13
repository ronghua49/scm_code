package com.winway.scm.persistence.manager;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.manager.Manager;
import com.hotent.base.model.CommonResult;
import com.winway.scm.model.ScmXsBigContractProductSum;
import com.winway.scm.model.ScmXsFocusSend;

/**
 * 
 * <pre> 
 * 描述：集中发货合同 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:44
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmXsFocusSendManager extends Manager<String, ScmXsFocusSend>{

	List<ScmXsBigContractProductSum> getProduct(String commerceCode, String ownerId, String agreementSummaryId) throws IOException;

	void sendApply(ScmXsFocusSend scmXsFocusSend);

	void endApply(JsonNode jsonNode);

	CommonResult<String> createFocussend();

	ScmXsFocusSend getApplyId(String applyId);

	
}
