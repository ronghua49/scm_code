package com.winway.scm.persistence.manager;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.manager.Manager;
import com.winway.scm.model.ScmCgProcurementOrder;

/**
 * 
 * <pre> 
 * 描述：采购订单表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-26 13:47:44
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
public interface ScmCgProcurementOrderManager extends Manager<String, ScmCgProcurementOrder>{

	ScmCgProcurementOrder getOrderAndRepOrder(String id);

	void sendApply(ScmCgProcurementOrder scmCgProcurementOrder);

	ScmCgProcurementOrder getOrderFirstByApprovalId(String approvalId);

	void endApply(JsonNode jsonNode);
	
}
