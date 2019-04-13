package com.winway.scm.persistence.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmZdManufacturerDao;
import com.winway.scm.model.ScmZdAgenttype;
import com.winway.scm.model.ScmZdManufacturer;
import com.winway.scm.persistence.manager.ScmZdManufacturerManager;

/**
 * 
 * <pre> 
 * 描述：生产厂家 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-29 10:31:25
 * 版权：美达项目组
 * </pre>
 */
@Service("scmZdManufacturerManager")
public class ScmZdManufacturerManagerImpl extends AbstractManagerImpl<String, ScmZdManufacturer> implements ScmZdManufacturerManager{
	@Resource
	ScmZdManufacturerDao scmZdManufacturerDao;
	@Override
	protected MyBatisDao<String, ScmZdManufacturer> getDao() {
		return scmZdManufacturerDao;
	}
	@Override
	public void getByNameAndCodeOrId(ScmZdManufacturer scmZdManufacturer) {
		//根据名称与ID判断是否已经存在当前使用名称
		List<ScmZdManufacturer> sdas = scmZdManufacturerDao.getByNameAndCodeOrId(scmZdManufacturer);
		if(sdas.size() > 0) {
			throw new RuntimeException("当前名称或编号已经被使用,请修改后提交");
		}
	}
}
