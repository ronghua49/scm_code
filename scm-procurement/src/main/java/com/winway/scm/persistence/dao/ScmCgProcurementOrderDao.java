package com.winway.scm.persistence.dao;
import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmCgProcurementOrder;

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
}
