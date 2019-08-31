package com.winway.scm.persistence.manager;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.manager.Manager;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.winway.scm.model.ScmZsjCommerce;

/**
 * 
 * <pre> 
 * 描述：商业表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmZsjCommerceManager extends Manager<String, ScmZsjCommerce>{

	void getByNameOrId(ScmZsjCommerce scmZsjCommerce);

	List<ScmZsjCommerce> getByName(String commerceName);

	void isExist(ScmZsjCommerce scmZsjCommerce);

	PageList<ScmZsjCommerce> firstList(QueryFilter queryFilter);

	List<ScmZsjCommerce> downBox(String ownerId);

	PageList<ScmZsjCommerce> listCommerceAcceptState(QueryFilter queryFilter);

	PageList<ScmZsjCommerce> listCommerceAcceptApprova(QueryFilter queryFilter);

	PageList<ScmZsjCommerce> listCommerceAcceptTS(QueryFilter queryFilter);

	List<ScmZsjCommerce> downBoxApplySuccess(String ownerId,String businessDivisionId);

	PageList<ScmZsjCommerce> agreementSendApplySuccessList(QueryFilter queryFilter);

	List<String>  getCommerceName();

	String findByOwnerId(String ownerId);

	void sendApply(ScmZsjCommerce cmZsjCommerce);

	void endApply(JsonNode jsonNode);

	ScmZsjCommerce getByApprovalId(String approvalId);

	List<ScmZsjCommerce> acceptCommerceList(String ownerId, String businessDivisionId, String provinceId);

	ScmZsjCommerce getcommerceByCode(String commerceCode);

	void updateSyn(String id);

	ScmZsjCommerce getcommerceFirstByCode(String commerceCode, String ownerId);

    ScmZsjCommerce getcommerceByName(String commerceName);
}
