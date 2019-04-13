package com.winway.scm.persistence.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotent.base.manager.Manager;
import com.winway.scm.model.ScmCgProcurementPlan;

/**
 * 
 * <pre> 
 * 描述：采购计划表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-26 13:47:44
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
public interface ScmCgProcurementPlanManager extends Manager<String, ScmCgProcurementPlan>{

	void isRemove(String id);

	void isRemoveByIds(String[] ids);

	void toLoad(String filePath, HttpServletRequest request,HttpServletResponse response);
	
}
