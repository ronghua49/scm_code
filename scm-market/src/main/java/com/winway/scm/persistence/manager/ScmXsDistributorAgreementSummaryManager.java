package com.winway.scm.persistence.manager;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.manager.Manager;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.winway.scm.model.ScmXsDistributorAgreementSummary;

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
public interface ScmXsDistributorAgreementSummaryManager extends Manager<String, ScmXsDistributorAgreementSummary>{

	void sendApply(ScmXsDistributorAgreementSummary scmXsDistributorAgreementSummary);

	void endApply(JsonNode jsonNode);
	
}
