package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmZdProvinceDao;
import com.winway.scm.model.ScmZdProvince;
import com.winway.scm.persistence.manager.ScmZdProvinceManager;

/**
 * 
 * <pre> 
 * 描述：省区 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-29 10:31:24
 * 版权：美达项目组
 * </pre>
 */
@Service("scmZdProvinceManager")
public class ScmZdProvinceManagerImpl extends AbstractManagerImpl<String, ScmZdProvince> implements ScmZdProvinceManager{
	@Resource
	ScmZdProvinceDao scmZdProvinceDao;
	@Override
	protected MyBatisDao<String, ScmZdProvince> getDao() {
		return scmZdProvinceDao;
	}
}
