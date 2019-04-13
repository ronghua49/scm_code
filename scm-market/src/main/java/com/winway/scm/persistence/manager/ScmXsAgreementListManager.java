package com.winway.scm.persistence.manager;

import java.util.List;

import com.hotent.base.manager.Manager;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.winway.scm.model.ScmXsAgreementList;

/**
 * 
 * <pre> 
 * 描述：经销商协议名单申请表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 15:01:16
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmXsAgreementListManager extends Manager<String, ScmXsAgreementList>{

	PageList<ScmXsAgreementList> masterList(QueryFilter queryFilter);

	PageList<ScmXsAgreementList> commerceAndSales(QueryFilter queryFilter);

	List<ScmXsAgreementList> getYears(String ownerId);

	ScmXsAgreementList getType(String commerceCode);

	PageList<ScmXsAgreementList> addAgreementlist(QueryFilter queryFilter);

	List<ScmXsAgreementList> updateAgreementlist(String masterId, String ownerid, String businessDivisionId);
	
}
