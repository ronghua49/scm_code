package com.winway.scm.persistence.manager;

import com.hotent.base.manager.Manager;
import com.winway.scm.model.ScmZsjSupplierAccredit;

/**
 * 
 * <pre> 
 * 描述：授权销售品种 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmZsjSupplierAccreditManager extends Manager<String, ScmZsjSupplierAccredit>{

	void saveList(ScmZsjSupplierAccredit[] scmZsjSupplierAccredit);
	
}
