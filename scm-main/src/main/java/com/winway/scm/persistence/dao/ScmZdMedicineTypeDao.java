package com.winway.scm.persistence.dao;
import java.util.List;

import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmZdMedicineType;

/**
 * 
 * <pre> 
 * 描述：药品类型 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-29 10:31:25
 * 版权：美达项目组
 * </pre>
 */
public interface ScmZdMedicineTypeDao extends MyBatisDao<String, ScmZdMedicineType> {

	List<ScmZdMedicineType> getByNameAndCodeOrId(ScmZdMedicineType scmZdMedicineType);
}
