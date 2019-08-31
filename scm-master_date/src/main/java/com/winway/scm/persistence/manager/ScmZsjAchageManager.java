package com.winway.scm.persistence.manager;

import com.hotent.base.manager.Manager;
import com.winway.scm.model.ScmZsjAccessory;
import com.winway.scm.model.ScmZsjAchage;

/**
 * 
 * <pre> 
 * 描述：件装量 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmZsjAchageManager extends Manager<String, ScmZsjAchage>{

	String getProductNumByCode(String code);

	String getProductMessageByCode(String code);

}
