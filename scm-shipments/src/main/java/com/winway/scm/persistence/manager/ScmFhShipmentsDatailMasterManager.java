package com.winway.scm.persistence.manager;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.manager.Manager;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.winway.scm.model.ScmFhShipmentsDatail;
import com.winway.scm.model.ScmFhShipmentsDatailMaster;
import com.winway.scm.vo.ScmFhShipmentsDataiAndMessageVo;

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

	 ScmFhShipmentsDatailMaster getByApplyId(String applyid);

	void sendApply(ScmFhShipmentsDatailMaster scmFhShipmentsDatailMaster);

	void endApply(JsonNode jsonNode);

	PageList<ScmFhShipmentsDataiAndMessageVo> allShipmentMessage(QueryFilter queryFilter);
	
}
