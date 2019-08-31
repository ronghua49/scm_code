package com.winway.scm.persistence.manager;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.manager.Manager;
import com.winway.purchase.vo.ReturnWmsVo;
import com.winway.scm.model.ScmGspRejectionMaster;
import com.winway.scm.vo.WmsCgjsReceiptVo;

/**
 * 
 * <pre> 
 * 描述：药品拒收报告单主表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-24 12:00:21
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmGspRejectionMasterManager extends Manager<String, ScmGspRejectionMaster>{

    void sendApply(ScmGspRejectionMaster scmGspRejectionMaster);

    void endApply(JsonNode jsonNode);

    ScmGspRejectionMaster getbyApprovalId(String id);

	ReturnWmsVo receipt(WmsCgjsReceiptVo wmsCgjsReceiptVo);
}
