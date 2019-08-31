package com.winway.scm.persistence.manager;

import com.hotent.base.manager.Manager;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.winway.scm.model.ScmZsjCommerceAcceptState;

import java.util.Map;

/**
 * 
 * <pre> 
 * 描述：商业认可状态表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-18 14:36:01
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmZsjCommerceAcceptStateManager extends Manager<String, ScmZsjCommerceAcceptState>{

	PageList<ScmZsjCommerceAcceptState> firstList(QueryFilter queryFilter);

	Map<String, Object> getQuater();
}
