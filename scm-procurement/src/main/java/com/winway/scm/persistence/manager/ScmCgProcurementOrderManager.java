package com.winway.scm.persistence.manager;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.manager.Manager;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.winway.purchase.vo.ScmWmsReceipt;
import com.winway.purchase.vo.WmsPageList;
import com.winway.scm.model.ScmCgProcurementOrder;
import com.winway.scm.vo.ScmZsjDrogPuchaseRecords;
import com.winway.scm.vo.WmsfindcgrkDetailVo;
import com.winway.scm.vo.WmsfindcgrkInVo;
import com.winway.scm.vo.WmsfindcgrkVo;
import com.winway.scm.vo.WmsfindcgysInVo;
import com.winway.scm.vo.WmsfindcgysVo;
import com.winway.scm.vo.WmsfindcgysbybillnoVo;

/**
 * 
 * <pre> 
 * 描述：采购订单表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-26 13:47:44
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
public interface ScmCgProcurementOrderManager extends Manager<String, ScmCgProcurementOrder>{

	ScmCgProcurementOrder getOrderAndRepOrder(String id);

	void sendApply(ScmCgProcurementOrder scmCgProcurementOrder);

	ScmCgProcurementOrder getOrderFirstByApprovalId(String approvalId);

	void endApply(JsonNode jsonNode);

	PageList<ScmZsjDrogPuchaseRecords> drogPuchaseRecords(QueryFilter queryFilter);

	WmsPageList<WmsfindcgysVo> wmsfindcgys(WmsfindcgysInVo queryFilter);

	List<WmsfindcgysbybillnoVo> wmsfindcgysbybillno(String billno);

	WmsPageList<WmsfindcgrkVo> wmsfindcgrk(WmsfindcgrkInVo queryFilter);

	List<WmsfindcgrkDetailVo> wmsfindcgrkbybillno(String billno);

	WmsPageList<WmsfindcgrkDetailVo> sapfindcgrkbyordercode(WmsfindcgrkInVo queryFilter);

	void receipt(ScmWmsReceipt scmWmsReceipt);
	
}
