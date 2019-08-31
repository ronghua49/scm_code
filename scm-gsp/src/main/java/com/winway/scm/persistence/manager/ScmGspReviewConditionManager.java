package com.winway.scm.persistence.manager;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.manager.Manager;
import com.winway.scm.model.ScmGspReviewCondition;

/**
 * 
 * <pre> 
 * 描述：药品复检通知单 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-24 12:00:21
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmGspReviewConditionManager extends Manager<String, ScmGspReviewCondition>{

    void sendApply(ScmGspReviewCondition scmGspReviewCondition);

    void endApply(JsonNode jsonNode);

    ScmGspReviewCondition getDetailByApprovalId(String approvalId);
}
