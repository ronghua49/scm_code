package com.winway.scm.persistence.dao;
import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmKcDrugDestructionMaster;

/**
 * 
 * <pre> 
 * 描述：不合格药品销毁主表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-13 15:06:38
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmKcDrugDestructionMasterDao extends MyBatisDao<String, ScmKcDrugDestructionMaster> {

	ScmKcDrugDestructionMaster getByApprovalId(String approvalId);
}
