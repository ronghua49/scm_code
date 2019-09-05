package com.winway.scm.persistence.manager;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.manager.Manager;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.winway.scm.model.ScmZsjCommerce;
import com.winway.scm.model.ScmZsjCommerceFirst;
import com.winway.scm.model.ScmZsjSupplier;
import com.winway.scm.model.ScmZsjSupplierFirst;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * <pre> 
 * 描述：供应商首营记录表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmZsjSupplierFirstManager extends Manager<String, ScmZsjSupplierFirst>{

	void sendApply(ScmZsjSupplierFirst scmZsjSupplierFirst, String flowKey);

	void isSave(ScmZsjSupplierFirst scmZsjSupplierFirst);

	void endApply(JsonNode jsonNode);

	ScmZsjSupplierFirst getSupplierFirstByApprovalId(String approvalId);

	void updateSendApply(ScmZsjSupplierFirst scmZsjSupplierFirst, String flowKey);

    PageList<ScmZsjCommerce> licenseWaring(QueryFilter queryFilter);

	PageList<ScmZsjSupplier> gmplist(QueryFilter queryFilter);

    PageList<ScmZsjSupplier> creditlist(QueryFilter queryFilter);

	PageList<ScmZsjSupplier> qlist(QueryFilter queryFilter);

	String startOrStop(String id);

    boolean isInForce(String supplierId);

    Set<String> getAccreditProCodes(String supplierId);


}

