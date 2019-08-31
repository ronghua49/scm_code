package com.winway.scm.persistence.manager;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.manager.Manager;
import com.winway.scm.model.ScmCwPaymentRB;

/**
 * 
 * <pre> 
 * 描述：经销商RB支付申请总表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-09 18:18:05
 * 版权：美达开发小组
 * </pre>ScmFhMarketSelesReturnMasterDao
 */
public interface ScmCwPaymentRBManager extends Manager<String, ScmCwPaymentRB>{

    void sendApply(ScmCwPaymentRB scmCwPaymentRB);

    void endApply(JsonNode jsonNode);

    ScmCwPaymentRB getDetail(String approvalId);
}
