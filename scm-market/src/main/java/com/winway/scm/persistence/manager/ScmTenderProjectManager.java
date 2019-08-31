package com.winway.scm.persistence.manager;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.manager.Manager;
import com.winway.scm.model.ScmTenderProject;

/**
 * 
 * <pre> 
 * 描述：招标项目管理 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-06-25 11:09:05
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmTenderProjectManager extends Manager<String, ScmTenderProject>{
    /**
     * 根据流程ID获取业务详情
     *
     * @param flowId
     * @return
     * @throws Exception
     */
    ScmTenderProject getByFlowId(String flowId) throws Exception;


    /**
     * 招标项目申请审批
     *
     * @param scmTenderProject
     * @param actionName
     * @param opinion
     */
    void approval(ScmTenderProject scmTenderProject, String actionName, String opinion) throws Exception;


    /**
     * 招标申请回调
     *
     * @param jsonNode
     * @throws Exception
     */
    void endApply(JsonNode jsonNode) throws Exception;
}
