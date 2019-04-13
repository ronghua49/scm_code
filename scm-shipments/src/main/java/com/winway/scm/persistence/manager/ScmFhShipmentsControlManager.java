package com.winway.scm.persistence.manager;

import java.util.Map;

import com.hotent.base.manager.Manager;
import com.winway.scm.model.ScmFhShipmentsControl;

/**
 * 
 * <pre> 
 * 描述：发货控制 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-02-15 17:31:50
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmFhShipmentsControlManager extends Manager<String, ScmFhShipmentsControl>{

	void remove(String id, String fullname);

	Map<String, String> ListCommerceBanProduct(String commerceId) throws Exception;
	
}
