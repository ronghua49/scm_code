package com.winway.scm.persistence.dao;
import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmFhGradeSelesReturnMaster;
import com.winway.scm.model.ScmFhMarketSelesReturnMaster;

/**
 * 
 * <pre> 
 * 描述：当次退货主表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-13 15:47:33
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmFhGradeSelesReturnMasterDao extends MyBatisDao<String, ScmFhGradeSelesReturnMaster> {
    ScmFhGradeSelesReturnMaster getByApprovalState(String approvalId);

	ScmFhGradeSelesReturnMaster getByOrderCode(String orderCode);
}
