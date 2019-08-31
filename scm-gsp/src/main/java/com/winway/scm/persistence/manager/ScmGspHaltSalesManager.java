package com.winway.scm.persistence.manager;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.manager.Manager;
import com.winway.purchase.vo.ScmWmsReceipt;
import com.winway.scm.model.ScmGspHaltSales;

/**
 * 
 * <pre> 
 * 描述：药品停售通知 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-24 12:00:21
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmGspHaltSalesManager extends Manager<String, ScmGspHaltSales>{

    void sendApply(ScmGspHaltSales scmGspHaltSales);

    void endApply(JsonNode jsonNode);

    void sendApplyByRelieve(ScmGspHaltSales scmGspHaltSales);

    void endApplyByRelieve(JsonNode jsonNode);

    ScmGspHaltSales getDetail(String approvalId);

	void receipt(ScmWmsReceipt scmWmsReceipt);
}
