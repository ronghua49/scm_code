package com.winway.scm.persistence.dao;
import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmGspRejectionMaster;

/**
 * 
 * <pre> 
 * 描述：药品拒收报告单主表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-24 12:00:21
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmGspRejectionMasterDao extends MyBatisDao<String, ScmGspRejectionMaster> {
    ScmGspRejectionMaster getByApprovalState(String approvalId);

	ScmGspRejectionMaster getByCode(String orderCode);
}
