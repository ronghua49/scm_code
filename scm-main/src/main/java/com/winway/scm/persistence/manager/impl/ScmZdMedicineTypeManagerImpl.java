package com.winway.scm.persistence.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmZdMedicineTypeDao;
import com.winway.scm.model.ScmZdAgenttype;
import com.winway.scm.model.ScmZdMedicineType;
import com.winway.scm.persistence.manager.ScmZdMedicineTypeManager;

/**
 * 
 * <pre> 
 * 描述：药品类型 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-29 10:31:25
 * 版权：美达项目组
 * </pre>
 */
@Service("scmZdMedicineTypeManager")
public class ScmZdMedicineTypeManagerImpl extends AbstractManagerImpl<String, ScmZdMedicineType> implements ScmZdMedicineTypeManager{
	@Resource
	ScmZdMedicineTypeDao scmZdMedicineTypeDao;
	@Override
	protected MyBatisDao<String, ScmZdMedicineType> getDao() {
		return scmZdMedicineTypeDao;
	}
	@Override
	public void getByNameAndCodeOrId(ScmZdMedicineType scmZdMedicineType) {
		//根据名称与ID判断是否已经存在当前使用名称
		List<ScmZdMedicineType> sdas = scmZdMedicineTypeDao.getByNameAndCodeOrId(scmZdMedicineType);
		if(sdas.size() > 0) {
			throw new RuntimeException("当前名称或编号已经被使用,请修改后提交");
		}
	}
}
