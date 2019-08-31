package com.winway.scm.persistence.dao;
import java.util.Map;

import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmXsBigContractAllot;

/**
 * 
 * <pre> 
 * 描述：大合同分配表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:44
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmXsBigContractAllotDao extends MyBatisDao<String, ScmXsBigContractAllot> {

	ScmXsBigContractAllot getAllotByApprovalId(String approvalId);

	ScmXsBigContractAllot getByApplyId(String applyId);

	Map getMain(String id);
}
