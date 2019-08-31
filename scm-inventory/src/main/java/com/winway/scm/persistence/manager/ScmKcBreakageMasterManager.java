package com.winway.scm.persistence.manager;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.manager.Manager;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.winway.purchase.vo.ScmWmsReceipt;
import com.winway.scm.model.ScmKcBreakageMaster;
import com.winway.scm.vo.BreakageRecord;

/**
 * 
 * <pre> 
 * 描述：不合格产品报损主表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-23 17:00:46
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmKcBreakageMasterManager extends Manager<String, ScmKcBreakageMaster>{

    void sendApply(ScmKcBreakageMaster scmKcBreakageMaster);

    void endApply(JsonNode jsonNode);

	PageList<BreakageRecord> breakageRecord(QueryFilter queryFilter);

    ScmKcBreakageMaster getDetail(String approvalId);

	void receipt(ScmWmsReceipt scmWmsReceipt);
}
