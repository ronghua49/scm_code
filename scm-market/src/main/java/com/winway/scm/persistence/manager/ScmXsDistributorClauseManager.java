package com.winway.scm.persistence.manager;

import java.util.List;
import java.util.Map;

import com.hotent.base.manager.Manager;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.winway.scm.model.ScmXsDistributorAgreementSummary;
import com.winway.scm.model.ScmXsDistributorClause;

/**
 * 
 * <pre> 
 * 描述：分销商协议合作条款表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:43
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmXsDistributorClauseManager extends Manager<String, ScmXsDistributorClause>{

	PageList<ScmXsDistributorClause> firstList(QueryFilter queryFilter);
	PageList<ScmXsDistributorClause> commerceAndAcceptState(QueryFilter queryFilter);
	List<ScmXsDistributorClause> getYears(String ownerId);

    List<ScmXsDistributorClause> list1(String businessDivisionId, String commerceName, String acceptState,String provinceId,String commerceCode);
}
