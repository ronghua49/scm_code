package com.winway.scm.persistence.manager;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.exception.SystemException;
import com.hotent.base.manager.Manager;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.winway.purchase.vo.ScmWmsReceipt;
import com.winway.scm.model.ScmFhShipmentsDatail;
import com.winway.scm.model.ScmFhShipmentsDatailMaster;
import com.winway.scm.vo.SalesRecordVo;
import com.winway.scm.vo.ScmFhShipmentsDataiAndMessageVo;
import com.winway.scm.vo.ScmWmsShipReceipt;
import com.winway.scm.vo.ShipmentsDatailVo;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Map;

/**
 * 
 * <pre> 
 * 描述：发货明细总表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-02-15 17:31:50
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmFhShipmentsDatailMasterManager extends Manager<String, ScmFhShipmentsDatailMaster>{

	PageList<ScmFhShipmentsDatailMaster> queryReturnVo(QueryFilter queryFilter) throws SystemException;

	ScmFhShipmentsDatailMaster getByApplyId(String applyid);

	void sendApply(ScmFhShipmentsDatailMaster scmFhShipmentsDatailMaster) throws Exception;

	void endApply(JsonNode jsonNode);

	PageList<ScmFhShipmentsDataiAndMessageVo> allShipmentMessage(QueryFilter queryFilter);

	PageList<SalesRecordVo> salesRecord(QueryFilter queryFilter);

	String canInvoice(String shipmentsCode);

	ScmFhShipmentsDatailMaster getShipmentsDatailMasterById(String id);

    Map<String, Integer> getShipmentsHistory(String commerceId, String ownerId) throws ParseException;

	void receipt(ScmWmsShipReceipt[] scmWmsShipReceipt);

	void affirm(ScmWmsReceipt scmWmsReceipt);

	void updateShipmentDatailCanInvoice(String id, String type);

    void export(QueryFilter queryFilter, boolean b, HttpServletResponse response);

	void export2(QueryFilter queryFilter, boolean b, HttpServletResponse response);
}
