package com.winway.scm.persistence.manager;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.manager.Manager;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.winway.scm.model.ScmZsjCommerce;
import com.winway.scm.model.ScmZsjCommerceFirst;

import java.util.Set;

/**
 * 
 * <pre> 
 * 描述：商业首营记录表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmZsjCommerceFirstManager extends Manager<String, ScmZsjCommerceFirst>{

	void sendApply(ScmZsjCommerceFirst scmZsjCommerceFirst);

	void isSave(ScmZsjCommerceFirst scmZsjCommerceFirst);

	ScmZsjCommerceFirst getCommerceFirstByApprovalId(String approvalId);

	void endApply(JsonNode jsonNode);

	void updateSendApply(ScmZsjCommerceFirst scmZsjCommerceFirst);

    PageList<ScmZsjCommerce> licenseWaring(QueryFilter queryFilter);

	PageList<ScmZsjCommerce> gsplist(QueryFilter queryFilter);

	PageList<ScmZsjCommerce> creditlist(QueryFilter queryFilter);

	PageList<ScmZsjCommerce> qlist(QueryFilter queryFilter);

	String startOrStop(String id);

	boolean getCommerceStateByFirstId(String commerceFirstId);

    Set<String> getAccreditProCodes(String commerceFirstId);

}
