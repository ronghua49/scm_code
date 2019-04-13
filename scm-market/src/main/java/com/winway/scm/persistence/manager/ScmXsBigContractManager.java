package com.winway.scm.persistence.manager;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.manager.Manager;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.winway.scm.model.ScmXsBigContract;

/**
 * 
 * <pre> 
 * 描述：大合同 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:44
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmXsBigContractManager extends Manager<String, ScmXsBigContract>{

	PageList<ScmXsBigContract> getDownBox(String ownerId);

	PageList<ScmXsBigContract> bigcontractGetYDetails(QueryFilter queryFilter);

	ScmXsBigContract getIsContract(String ownerName,String dealer);

	String sendApply(ScmXsBigContract scmXsBigContract);

	void endApply(JsonNode jsonNode);

	ScmXsBigContract getByApplyId(String applyid);
}
