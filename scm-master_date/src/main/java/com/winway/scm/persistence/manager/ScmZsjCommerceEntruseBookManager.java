package com.winway.scm.persistence.manager;

import java.util.List;

import com.hotent.base.manager.Manager;
import com.winway.scm.model.ScmZsjCommerceEntruseBook;

/**
 * 
 * <pre> 
 * 描述：商业委托书 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmZsjCommerceEntruseBookManager extends Manager<String, ScmZsjCommerceEntruseBook>{

	List<ScmZsjCommerceEntruseBook> getByCommerceFirstId(String id);
	
}
