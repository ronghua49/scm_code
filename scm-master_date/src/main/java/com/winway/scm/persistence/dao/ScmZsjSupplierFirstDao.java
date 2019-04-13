package com.winway.scm.persistence.dao;
import java.util.List;

import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmZsjCommerceFirst;
import com.winway.scm.model.ScmZsjSupplierFirst;

/**
 * 
 * <pre> 
 * 描述：供应商首营记录表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmZsjSupplierFirstDao extends MyBatisDao<String, ScmZsjSupplierFirst> {

	List<ScmZsjSupplierFirst> getSupplierFirstByOwnerIdAndCommerceId(ScmZsjSupplierFirst scmZsjSupplierFirst);

	List<ScmZsjSupplierFirst> isSave(ScmZsjSupplierFirst scmZsjSupplierFirst);

	ScmZsjSupplierFirst getSupplierFirstByApprovalId(String approvalId);
}
