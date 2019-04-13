package com.winway.scm.persistence.manager;

import com.hotent.base.manager.Manager;
import com.winway.scm.model.ScmZdMedicineType;

/**
 * 
 * <pre> 
 * 描述：药品类型 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-29 10:31:25
 * 版权：美达项目组
 * </pre>
 */
public interface ScmZdMedicineTypeManager extends Manager<String, ScmZdMedicineType>{

	void getByNameAndCodeOrId(ScmZdMedicineType scmZdMedicineType);
	
}
