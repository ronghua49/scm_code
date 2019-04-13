package com.winway.scm.persistence.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmZdFunctionTypeDao;
import com.winway.scm.model.ScmZdCommerceLevel;
import com.winway.scm.model.ScmZdFunctionType;
import com.winway.scm.persistence.manager.ScmZdFunctionTypeManager;

/**
 * 
 * <pre> 
 * 描述：scm_zd_functiontype 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-16 10:34:19
 * 版权：美达项目组
 * </pre>
 */
@Service("scmZdFunctionTypeManager")
public class ScmZdFunctionTypeManagerImpl extends AbstractManagerImpl<String, ScmZdFunctionType> implements ScmZdFunctionTypeManager{
	@Resource
	ScmZdFunctionTypeDao scmZdFunctionTypeDao;
	@Override
	protected MyBatisDao<String, ScmZdFunctionType> getDao() {
		return scmZdFunctionTypeDao;
	}
	@Override
	public void getByNameAndCodeOrId(ScmZdFunctionType scmZdFunctionType) {
		// TODO Auto-generated method stub
		//根据名称与ID判断是否已经存在当前使用名称
		List<ScmZdFunctionType> sdas = scmZdFunctionTypeDao.getByNameAndCodeOrId(scmZdFunctionType);
		if(sdas.size() > 0) {
			throw new RuntimeException("当前名称或编号已经被使用,请修改后提交");
		}
	}
}
