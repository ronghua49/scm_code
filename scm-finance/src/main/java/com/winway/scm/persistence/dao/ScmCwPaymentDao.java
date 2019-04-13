package com.winway.scm.persistence.dao;
import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmCwPayment;

/**
 * 
 * <pre> 
 * 描述：付款总表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-23 18:19:25
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmCwPaymentDao extends MyBatisDao<String, ScmCwPayment> {

	ScmCwPayment findByApprovalId(String approvalId);
}
