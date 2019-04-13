package com.winway.scm.persistence.dao;
import java.util.List;
import java.util.Map;

import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmFhShipmentsDatailMaster;
import com.winway.scm.vo.ScmFhShipmentsDataiAndMessageVo;

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
}
