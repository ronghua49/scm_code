package com.winway.scm.persistence.dao;
import java.util.List;
import java.util.Map;

import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmXsBigContract;

/**
 * 
 * <pre> 
 * 描述：大合同 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:44
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmXsBigContractDao extends MyBatisDao<String, ScmXsBigContract> {

	List<ScmXsBigContract> getDownBox(String ownerId);

	List<ScmXsBigContract> bigcontractGetYDetails(Map<String, Object> map);


	ScmXsBigContract getSupplierFirstByApprovalId(String approvalId);

	List<ScmXsBigContract> getByCommerceFirstId(String commerceFirstId);

	ScmXsBigContract getByApplyId(String applyid);

}
