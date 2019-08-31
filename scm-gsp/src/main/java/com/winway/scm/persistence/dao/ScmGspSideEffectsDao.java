package com.winway.scm.persistence.dao;
import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmGspSideEffects;

/**
 * 
 * <pre> 
 * 描述：药品不良反应事件管理 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-18 18:21:20
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmGspSideEffectsDao extends MyBatisDao<String, ScmGspSideEffects> {
    ScmGspSideEffects getByApprovalState(String approvalId);
}
