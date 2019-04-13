package com.winway.scm.persistence.manager;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.manager.Manager;
import com.winway.scm.model.ScmCwPayment;

/**
 * 
 * <pre> 
 * 描述：付款总表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-23 18:19:25
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmCwPaymentManager extends Manager<String, ScmCwPayment>{

	
	void endApply(JsonNode jsonNode);

	void creates(ScmCwPayment scmCwPayment);

	void updates(ScmCwPayment scmCwPayment);
	
}
