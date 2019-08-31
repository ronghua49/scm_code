package com.winway.scm.persistence.dao;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmFhShipmentsDatail;
import com.winway.scm.model.ScmFhShipmentsDatailMaster;
import com.winway.scm.vo.SalesRecordVo;
import com.winway.scm.vo.ScmFhInventoryPreempted;
import com.winway.scm.vo.ScmFhShipmentsDataiAndMessageVo;
import com.winway.scm.vo.ShipmentsDatailVo;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * <pre> 
 * 描述：发货明细总表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-02-15 17:31:50
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmFhShipmentsDatailMasterDao extends MyBatisDao<String, ScmFhShipmentsDatailMaster> {

	ScmFhShipmentsDatailMaster getByApprovalState(String arg0);

	List<ScmFhShipmentsDataiAndMessageVo> allShipmentMessage(Map<String, Object> params);

	List<ShipmentsDatailVo> queryReturnVo(Map<String, Object> params);

	List<ScmFhShipmentsDatailMaster> getByIds(@Param(value = "set") Set<String> ids);

	List<ScmFhShipmentsDatailMaster> getByShipmentCodes(@Param(value = "list") List<String> shipmentsCodes);

	List<SalesRecordVo> salesRecord(Map<String, Object> params);

	List<Map> salesRecordMap(@Param(value = "params") Map<String, Object> params);

    ScmFhShipmentsDatailMaster getByCode(String shipmentsCode);

	ScmFhShipmentsDatailMaster getShipmentsDatailMasterById(String id);

    List<ScmFhShipmentsDatail> getShipmentDatailByCommerceIdAndOwnerId(String commerceId, String ownerId);

	ScmFhShipmentsDatailMaster getByOrderCode(String orderCode);

	ScmFhInventoryPreempted inventoryPreempted(String wareHouseCode, String string, String string2, String string3, String string4, String string5);
}
