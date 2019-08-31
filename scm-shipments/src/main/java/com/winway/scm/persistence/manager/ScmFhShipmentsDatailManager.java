package com.winway.scm.persistence.manager;

import java.util.List;
import java.util.Map;

import com.hotent.base.manager.Manager;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.winway.scm.model.ScmFhShipmentsDatail;
import com.winway.scm.vo.ScmFhFmsListShipmentVo;
import com.winway.scm.vo.ScmFhShipMentsCountVo;
import com.winway.scm.vo.ScmFhShipmentsDataiAndMessageVo;

/**
 * 
 * <pre> 
 * 描述：发货明细表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-02-15 17:31:50
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmFhShipmentsDatailManager extends Manager<String, ScmFhShipmentsDatail>{

	List<Map<String, Object>> getYears(String id);

	PageList<ScmFhShipmentsDatail> messageList(QueryFilter queryFilter);

	PageList<ScmFhShipmentsDataiAndMessageVo> firstList(QueryFilter queryFilter);

	List<ScmFhFmsListShipmentVo> fmsListShipments(String startMonth, String endMonth, String ownerId,
			String[] businessCodeList);

	PageList<ScmFhShipMentsCountVo> shipMentsCountlist(QueryFilter queryFilter);

	Map<String, Object> dataDownBox();

	boolean verifyDeliveryAmount(String commerceFirstId, Double priceSum);
}
