package com.winway.scm.persistence.manager;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.manager.Manager;
import com.winway.scm.model.ScmZsjCommerceFirst;
import com.winway.scm.model.ScmZsjSupplierFirst;

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

	void sendApply(ScmZsjSupplierFirst scmZsjSupplierFirst);

	void isSave(ScmZsjSupplierFirst scmZsjSupplierFirst);

	void endApply(JsonNode jsonNode);

	ScmZsjSupplierFirst getSupplierFirstByApprovalId(String approvalId);

	void updateSendApply(ScmZsjSupplierFirst scmZsjSupplierFirst);

	
}
