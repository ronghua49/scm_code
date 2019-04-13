package com.winway.scm.persistence.manager;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.hotent.base.manager.Manager;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.winway.scm.model.ScmFhShipmentsTask;

/**
 * 
 * <pre> 
 * 描述：发货任务表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-02-15 17:31:50
 * 版权：美达开发小组Z
 * </pre>
 */
public interface ScmFhShipmentsTaskManager extends Manager<String, ScmFhShipmentsTask>{

	void theDeliveryTask(String jsonStr) throws ParseException;

	PageList<ScmFhShipmentsTask> firstList(QueryFilter queryFilter) throws Exception;

	List<Map<String,String>> commerceList(QueryFilter queryFilter);

	List<String> proList(String ownerId);

	List<String> codeList(String ownerId);
}
