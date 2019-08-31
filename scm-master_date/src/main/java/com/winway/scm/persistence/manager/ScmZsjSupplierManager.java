package com.winway.scm.persistence.manager;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.manager.Manager;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.winway.scm.model.ScmZsjCommerce;
import com.winway.scm.model.ScmZsjSupplier;

/**
 * 
 * <pre> 
 * 描述：供应商表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmZsjSupplierManager extends Manager<String, ScmZsjSupplier>{

	void getByNameOrId(ScmZsjSupplier scmZsjSupplier);

	void isExist(ScmZsjSupplier scmZsjSupplier);

	PageList<ScmZsjSupplier> firstList(QueryFilter queryFilter);

	List<ScmZsjSupplier> downBox(String ownerId);

	List<ScmZsjSupplier> downBoxApplySuccess(String ownerId,String managementScope);

	void endApply(JsonNode jsonNode);

	void sendApply(ScmZsjSupplier scmZsjSupplier);

	ScmZsjSupplier getByApprovalId(String approvalId);

	void updateSyn(String id);
	
}
