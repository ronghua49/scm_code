package com.winway.scm.persistence.dao;
import java.util.List;
import java.util.Map;

import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmFhShipmentsControl;

/**
 * 
 * <pre> 
 * 描述：发货控制 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-02-15 17:31:50
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmFhShipmentsControlDao extends MyBatisDao<String, ScmFhShipmentsControl> {

	List<ScmFhShipmentsControl> ListShipmentsByAll();

	List<ScmFhShipmentsControl> ListShipmentsByBusinessDivisionId(String businessDivisionId);

	List<ScmFhShipmentsControl> ListShipmentsByProvinceId(String businessDivisionId, String provinceId);

	List<ScmFhShipmentsControl> ListShipmentsByCommerceId(String businessDivisionId, String provinceId,
			String commerceId);

	Map<String, String> getCurrentArea(String commerceId);
}
