package com.winway.scm.persistence.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmZdMedicineClassifyDao;
import com.winway.scm.model.ScmZdAgenttype;
import com.winway.scm.model.ScmZdMedicineClassify;
import com.winway.scm.persistence.manager.ScmZdMedicineClassifyManager;

/**
 * 
 * <pre> 
 * 描述：药品分类 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-29 10:31:25
 * 版权：美达项目组
 * </pre>
 */
@Service("scmZdMedicineClassifyManager")
public class ScmZdMedicineClassifyManagerImpl extends AbstractManagerImpl<String, ScmZdMedicineClassify> implements ScmZdMedicineClassifyManager{
	@Resource
	ScmZdMedicineClassifyDao scmZdMedicineClassifyDao;
	@Override
	protected MyBatisDao<String, ScmZdMedicineClassify> getDao() {
		return scmZdMedicineClassifyDao;
	}
	@Override
	public void getByNameAndCodeOrId(ScmZdMedicineClassify scmZdMedicineClassify) {
		//根据名称与ID判断是否已经存在当前使用名称
		List<ScmZdMedicineClassify> sdas = scmZdMedicineClassifyDao.getByNameAndCodeOrId(scmZdMedicineClassify);
		if(sdas.size() > 0) {
			throw new RuntimeException("当前名称或编号已经被使用,请修改后提交");
		}
	}
}
