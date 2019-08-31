package com.winway.scm.persistence.dao;
import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmCwAnnualFeeMaster;

/**
 * 
 * <pre> 
 * 描述：商业年费信息管理费主表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-28 09:38:34
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmCwAnnualFeeMasterDao extends MyBatisDao<String, ScmCwAnnualFeeMaster> {
    ScmCwAnnualFeeMaster getByApprovalState(String approvalId);
}
