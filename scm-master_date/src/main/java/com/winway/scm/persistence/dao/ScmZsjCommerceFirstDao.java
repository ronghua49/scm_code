package com.winway.scm.persistence.dao;
import java.util.List;

import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmZsjCommerceFirst;

/**
 * 
 * <pre> 
 * 描述：商业首营记录表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmZsjCommerceFirstDao extends MyBatisDao<String, ScmZsjCommerceFirst> {

	List<ScmZsjCommerceFirst> getCommerceFirstByOwnerIdAndCommerceId(ScmZsjCommerceFirst scmZsjCommerceFirst);

	List<ScmZsjCommerceFirst> isSave(ScmZsjCommerceFirst scmZsjCommerceFirst);

	ScmZsjCommerceFirst getCommerceFirstByApprovalId(String approvalId);
}
