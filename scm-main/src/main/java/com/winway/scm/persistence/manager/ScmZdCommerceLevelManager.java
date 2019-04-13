package com.winway.scm.persistence.manager;

import java.util.List;

import com.hotent.base.manager.Manager;
import com.winway.scm.model.ScmZdCommerceLevel;
import com.winway.scm.model.ScmZdMedicineType;

/**
 * 
 * <pre> 
 * 描述：scm_zd_commercelevel 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-16 10:34:19
 * 版权：美达项目组
 * </pre>
 */
public interface ScmZdCommerceLevelManager extends Manager<String, ScmZdCommerceLevel>{

	List<ScmZdCommerceLevel> downBoxList();
	
	void getByNameAndCodeOrId(ScmZdCommerceLevel scmZdCommerceLevel);
	
	
}
