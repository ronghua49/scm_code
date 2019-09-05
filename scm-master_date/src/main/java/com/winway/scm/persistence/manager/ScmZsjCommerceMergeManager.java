package com.winway.scm.persistence.manager;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.manager.Manager;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.winway.scm.model.ScmZsjCommerce;
import com.winway.scm.model.ScmZsjCommerceMerge;

/**
 * 
 * <pre> 
 * 描述：商业合并表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-18 14:36:01
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmZsjCommerceMergeManager extends Manager<String, ScmZsjCommerceMerge>{

	void endApply(JsonNode jsonNode);

    PageList<ScmZsjCommerce> listCommerceMerge(QueryFilter queryFilter);

    void changeMergeState(String viceCommerceId,String mergeState);

    ScmZsjCommerceMerge detail(String approvalId);
}
