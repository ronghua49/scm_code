package com.winway.scm.persistence.dao;
import java.util.List;
import java.util.Map;

import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmCgProcurementOrder;
import com.winway.scm.vo.ScmZsjDrogPuchaseRecords;

/**
 * 
 * <pre> 
 * 描述：采购订单表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-26 13:47:44
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
public interface ScmCgProcurementOrderDao extends MyBatisDao<String, ScmCgProcurementOrder> {

	ScmCgProcurementOrder getOrderFirstByApprovalId(String approvalId);

	List<ScmZsjDrogPuchaseRecords> drogPuchaseRecords(Map<String, Object> params);

	List<Map> drogPuchaseRecordsMap(Map<String, Object> params);

	ScmCgProcurementOrder getByOrdercode(String ordercode);
}
