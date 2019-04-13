package com.winway.scm.persistence.dao;
import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmXsAgreementListMaster;

/**
 * 
 * <pre> 
 * 描述：商业协议合作名单主表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 15:01:16
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmXsAgreementListMasterDao extends MyBatisDao<String, ScmXsAgreementListMaster> {

	ScmXsAgreementListMaster getSupplierFirstByApprovalId(String approvalId);

	void updateIsEffect(String businessDivisionId, String id);
}
