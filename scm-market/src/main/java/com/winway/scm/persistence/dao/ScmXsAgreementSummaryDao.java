package com.winway.scm.persistence.dao;
import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmXsAgreementSummary;

/**
 * 
 * <pre> 
 * 描述：省区协议汇总表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:43
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmXsAgreementSummaryDao extends MyBatisDao<String, ScmXsAgreementSummary> {

	ScmXsAgreementSummary getSupplierFirstByApprovalId(String approvalId);

	ScmXsAgreementSummary getByApprovalId(String arg0);
}
