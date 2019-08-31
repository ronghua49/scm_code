package com.winway.scm.persistence.manager;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.manager.Manager;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.winway.purchase.vo.ScmWmsReceipt;
import com.winway.scm.model.ScmFhMarketSelesReturn;
import com.winway.scm.model.ScmFhMarketSelesReturnMaster;
import com.winway.scm.vo.SalesReturnRecord;

import java.text.ParseException;

/**
 * 
 * <pre> 
 * 描述：销售退货主表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-13 15:47:33
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmFhMarketSelesReturnMasterManager extends Manager<String, ScmFhMarketSelesReturnMaster>{

    ScmFhMarketSelesReturnMaster sendApply(ScmFhMarketSelesReturnMaster scmFhMarketSelesReturnMaster);

    void endApply(JsonNode jsonNode) throws ParseException;

    ScmFhMarketSelesReturnMaster getByApprovalId(String approvalId);

	PageList<SalesReturnRecord> salesReturnRecord(QueryFilter queryFilter);

	void receipt(ScmWmsReceipt scmWmsReceipt);

	PageList<ScmFhMarketSelesReturn> marketSelesReturnDatail(String orderCode);
}
