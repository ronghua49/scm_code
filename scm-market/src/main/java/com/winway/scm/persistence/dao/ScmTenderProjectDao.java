package com.winway.scm.persistence.dao;
import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmTenderProject;

/**
 * 
 * <pre> 
 * 描述：招标项目管理 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-06-25 11:09:05
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmTenderProjectDao extends MyBatisDao<String, ScmTenderProject> {

    ScmTenderProject getByFlowId(String flowId);
}
