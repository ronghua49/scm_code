package com.winway.scm.persistence.dao;
import java.util.List;

import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.query.QueryFilter;
import com.winway.scm.model.ScmZsjCommerceMerge;

/**
 * 
 * <pre> 
 * 描述：商业合并表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-18 14:36:01
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmZsjCommerceMergeDao extends MyBatisDao<String, ScmZsjCommerceMerge> {

	List<ScmZsjCommerceMerge> firstList(QueryFilter queryFilter);

	ScmZsjCommerceMerge getCommerceFirstByApprovalId(String approvalId);
}
