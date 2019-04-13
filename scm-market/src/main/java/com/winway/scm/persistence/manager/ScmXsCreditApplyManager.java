package com.winway.scm.persistence.manager;

import java.util.List;

import com.hotent.base.manager.Manager;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.model.WinwayResult;
import com.winway.scm.model.ScmXsCreditApply;

/**
 * 
 * <pre> 
 * 描述：商业资信申请表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:43
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmXsCreditApplyManager extends Manager<String, ScmXsCreditApply>{

	PageList<ScmXsCreditApply> firstList(QueryFilter queryFilter);

	List<ScmXsCreditApply> getYears(String ownerId);

	ScmXsCreditApply getStatisticalDate(String commerceId, String ownerId);

	
}
