package com.winway.scm.persistence.manager;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.manager.Manager;
import com.winway.scm.model.ScmZsjCommerceBlackList;
/**
 * 
 * <pre> 
 * 描述：商业黑名单表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-18 14:36:01
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmZsjCommerceBlackListManager extends Manager<String, ScmZsjCommerceBlackList>{

	boolean getCommerceIsBlack(String id, String ownerId);

	void endApply(JsonNode jsonNode);

	ScmZsjCommerceBlackList getByApplId(String applyId);


}
