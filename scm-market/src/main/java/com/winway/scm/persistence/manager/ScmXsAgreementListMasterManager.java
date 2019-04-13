package com.winway.scm.persistence.manager;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.manager.Manager;
import com.winway.scm.model.ScmXsAgreementListMaster;

/**
 * 
 * <pre> 
 * 描述：商业协议合作名单主表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 15:01:16
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmXsAgreementListMasterManager extends Manager<String, ScmXsAgreementListMaster>{

	void endApply(JsonNode jsonNode);
	void sendApply(ScmXsAgreementListMaster scmXsAgreementListMaster);
	
}
