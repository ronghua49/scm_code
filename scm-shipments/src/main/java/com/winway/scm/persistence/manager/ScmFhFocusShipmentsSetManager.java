package com.winway.scm.persistence.manager;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hotent.base.manager.Manager;
import com.winway.scm.model.ScmFhFocusShipmentsSet;

/**
 * 
 * <pre> 
 * 描述：集中发货设置表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-02-15 17:31:50
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmFhFocusShipmentsSetManager extends Manager<String, ScmFhFocusShipmentsSet>{

	void saves(List<ScmFhFocusShipmentsSet> scmFhFocusShipmentsSets, String userName);

	String startAndStop(String id);

	String listFocusShipmentsSetByComm(String commerceId, String ownerId) throws JsonProcessingException;
	
}
