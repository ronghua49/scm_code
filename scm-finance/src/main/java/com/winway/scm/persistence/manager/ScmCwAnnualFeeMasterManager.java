package com.winway.scm.persistence.manager;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.manager.Manager;
import com.winway.scm.model.ScmCwAnnualFeeMaster;

/**
 * 
 * <pre> 
 * 描述：商业年费信息管理费主表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-28 09:38:34
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmCwAnnualFeeMasterManager extends Manager<String, ScmCwAnnualFeeMaster>{

    void sendApply(ScmCwAnnualFeeMaster scmCwAnnualFeeMaster);

    void endApply(JsonNode jsonNode);
}
