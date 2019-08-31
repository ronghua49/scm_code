package com.winway.scm.persistence.dao;
import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmKcAllotMaster;

/**
 * 
 * <pre> 
 * 描述：库存调拨总表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-23 17:00:46
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmKcAllotMasterDao extends MyBatisDao<String, ScmKcAllotMaster> {
    ScmKcAllotMaster getByApprovalState(String approvalId);

	ScmKcAllotMaster getByOrderCode(String orderCode);
}
