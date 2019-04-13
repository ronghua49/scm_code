package com.winway.scm.persistence.manager;

import com.hotent.base.manager.Manager;
import com.winway.scm.model.ScmZdDutyOffice;

/**
 * 
 * <pre> 
 * 描述：责任办事处 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-29 10:31:24
 * 版权：美达项目组
 * </pre>
 */
public interface ScmZdDutyOfficeManager extends Manager<String, ScmZdDutyOffice>{

	void getByNameAndCodeOrId(ScmZdDutyOffice scmZdDutyOffice);
	
}
