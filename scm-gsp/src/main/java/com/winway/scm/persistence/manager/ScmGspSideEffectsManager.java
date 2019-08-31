package com.winway.scm.persistence.manager;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.manager.Manager;
import com.winway.scm.model.ScmGspSideEffects;

/**
 * 
 * <pre> 
 * 描述：药品不良反应事件管理 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-18 18:21:20
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmGspSideEffectsManager extends Manager<String, ScmGspSideEffects>{

    void sendApply(ScmGspSideEffects scmGspSideEffects);

    void endApply(JsonNode jsonNode);

    ScmGspSideEffects getByApprovalId(String id);
}
