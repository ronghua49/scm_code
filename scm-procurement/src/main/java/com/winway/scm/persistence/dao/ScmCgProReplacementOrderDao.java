package com.winway.scm.persistence.dao;
import java.util.List;

import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmCgProReplacementOrder;

/**
 * 
 * <pre> 
 * 描述：采购订单补单表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-26 13:47:44
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
public interface ScmCgProReplacementOrderDao extends MyBatisDao<String, ScmCgProReplacementOrder> {

	List<ScmCgProReplacementOrder> getProReplacementOrderByOrderId(String orderId);

	ScmCgProReplacementOrder getProOrderFirstByApprovalId(String approvalId);
}
