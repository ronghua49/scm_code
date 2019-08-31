package com.winway.scm.persistence.manager;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.manager.Manager;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.winway.purchase.vo.ScmWmsReceipt;
import com.winway.purchase.vo.WmsPageList;
import com.winway.scm.model.InventoryProduct;
import com.winway.scm.model.ScmKcAllotMaster;
import com.winway.scm.vo.ScmGspDrupChenkVo;
import com.winway.scm.vo.ScmGspDrupReplenishVo;
import com.winway.scm.vo.ScmKcProductSumQuery;
import com.winway.scm.vo.ScmWmsFindNearlyEffective;
import com.winway.scm.vo.ScmWmsNearlyEffective;
import com.winway.scm.vo.ScmWmsPage;
import com.winway.scm.vo.ScmWmsProductSum;

/**
 * 
 * <pre> 
 * 描述：库存调拨总表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-23 17:00:46
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmKcAllotMasterManager extends Manager<String, ScmKcAllotMaster>{

    ScmKcAllotMaster sendApply(ScmKcAllotMaster scmKcAllotMaster);

    void endApply(JsonNode jsonNode);

    PageList<InventoryProduct> queryInventory(QueryFilter queryFilter);

    ScmKcAllotMaster getDetail(String approvalId);

	PageList<ScmWmsProductSum> productSumQuery(ScmKcProductSumQuery scmKcProductSumQuery);

	PageList<ScmWmsNearlyEffective> findNearlyEffective(ScmWmsFindNearlyEffective scmWmsFindNearlyEffective);

	void receipt(ScmWmsReceipt scmWmsReceipt);

	WmsPageList<ScmGspDrupReplenishVo> allotInSap(String allotCode);

	WmsPageList<ScmGspDrupReplenishVo> allotOutSap(String allotCode);

}
