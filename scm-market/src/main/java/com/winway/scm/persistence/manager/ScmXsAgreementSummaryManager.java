package com.winway.scm.persistence.manager;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.manager.Manager;
import com.winway.scm.model.ScmXsAgreementSummary;

/**
 * 
 * <pre> 
 * 描述：省区协议汇总表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:43
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmXsAgreementSummaryManager extends Manager<String, ScmXsAgreementSummary>{

	void sendApply(ScmXsAgreementSummary scmXsAgreementSummary);

	void endApply(JsonNode jsonNode);

	void saveByDealerClause(ScmXsAgreementSummary scmXsAgreementSummary);

	void saveByAgreement(ScmXsAgreementSummary scmXsAgreementSummary);

	ScmXsAgreementSummary getDealerClauseAndProductDetail(String id);

	
}
