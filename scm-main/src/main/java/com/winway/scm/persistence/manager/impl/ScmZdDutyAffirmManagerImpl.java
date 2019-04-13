package com.winway.scm.persistence.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmZdDutyAffirmDao;
import com.winway.scm.model.ScmZdDutyAffirm;
import com.winway.scm.persistence.manager.ScmZdDutyAffirmManager;

/**
 * 
 * <pre> 
 * 描述：责任认定 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-29 10:31:24
 * 版权：美达项目组
 * </pre>
 */
@Service("scmZdDutyAffirmManager")
public class ScmZdDutyAffirmManagerImpl extends AbstractManagerImpl<String, ScmZdDutyAffirm> implements ScmZdDutyAffirmManager{
	@Resource
	ScmZdDutyAffirmDao scmZdDutyAffirmDao;
	@Override
	protected MyBatisDao<String, ScmZdDutyAffirm> getDao() {
		return scmZdDutyAffirmDao;
	}
	@Override
	public void getByNameAndCodeOrId(ScmZdDutyAffirm scmZdDutyAffirm) {
		//根据名称与ID判断是否已经存在当前使用名称
		List<ScmZdDutyAffirm> sdas = scmZdDutyAffirmDao.getByNameAndCodeOrId(scmZdDutyAffirm);
		if(sdas.size() > 0) {
			throw new RuntimeException("当前名称或编号已经被使用,请修改后提交");
		}
		
	}
}
