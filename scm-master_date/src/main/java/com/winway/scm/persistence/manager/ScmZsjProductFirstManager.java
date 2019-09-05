package com.winway.scm.persistence.manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.manager.Manager;
import com.winway.scm.model.ScmZsjProductFirst;
import com.winway.scm.model.ScmZsjSupplierFirst;

/**
 * 
 * <pre> 
 * 描述：商品首营记录表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmZsjProductFirstManager extends Manager<String, ScmZsjProductFirst>{

	void sendApply(ScmZsjProductFirst scmZsjProductFirst, String flowKey);

	void isSave(ScmZsjProductFirst scmZsjProductFirst);

	void endApply(JsonNode jsonNode);

	ScmZsjProductFirst getProductFirstByApprovalId(String approvalId);

	void updateSendApply(ScmZsjProductFirst scmZsjProductFirst, String flowKey);

	String startOrStop(String id);

	void productToWms(List<ScmZsjProductFirst> list);

}
