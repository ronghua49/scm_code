package com.winway.scm.persistence.manager;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.manager.Manager;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.winway.purchase.vo.ReturnWmsVo;
import com.winway.scm.model.ScmCgProcurementReturn;
import com.winway.scm.vo.ScmZsjDrogPuchaseReturn;
import com.winway.scm.vo.WmsCgthReceiptVo;

/**
 * 
 * <pre> 
 * 描述：采购退货表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-26 13:47:44
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
public interface ScmCgProcurementReturnManager extends Manager<String, ScmCgProcurementReturn>{

    void endApply(JsonNode jsonNode);

	PageList<ScmZsjDrogPuchaseReturn> drugPuchaseReturn(QueryFilter queryFilter);

	ScmCgProcurementReturn getDetail(String approvalId);

	ReturnWmsVo receipt(WmsCgthReceiptVo smsCgthReceiptVo);
}
