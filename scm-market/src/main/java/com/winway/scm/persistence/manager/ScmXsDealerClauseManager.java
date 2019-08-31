package com.winway.scm.persistence.manager;

import java.util.List;
import java.util.Map;

import com.hotent.base.manager.Manager;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.winway.scm.model.ScmXsAgreementList;
import com.winway.scm.model.ScmXsDealerClause;

/**
 * 
 * <pre> 
 * 描述：经销商协议合作条款表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:44
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmXsDealerClauseManager extends Manager<String, ScmXsDealerClause>{

	PageList<ScmXsDealerClause> commerceList(String ownerId, String provinceId);

	PageList<ScmXsDealerClause> firstQuery(QueryFilter queryFilter);

	List<Map<String, Object>> getYears(String ownerId);
	
}
