package com.winway.scm.persistence.manager;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.manager.Manager;
import com.winway.purchase.vo.ReturnWmsVo;
import com.winway.scm.model.ScmCgProReplacementOrder;
import com.winway.scm.vo.WmsCgbdReceiptVo;

/**
 * 
 * <pre> 
 * 描述：采购订单补单表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-26 13:47:44
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
public interface ScmCgProReplacementOrderManager extends Manager<String, ScmCgProReplacementOrder>{

	void sendApply(ScmCgProReplacementOrder scmCgProReplacementOrder);

	ScmCgProReplacementOrder getProOrderFirstByApprovalId(String approvalId);

	void endApply(JsonNode jsonNode);

	ReturnWmsVo receipt(WmsCgbdReceiptVo wmsCgbdReceiptVo);
	
}
