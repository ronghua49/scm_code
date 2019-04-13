package com.winway.scm.persistence.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmZdDutyOfficeDao;
import com.winway.scm.model.ScmZdDutyAffirm;
import com.winway.scm.model.ScmZdDutyOffice;
import com.winway.scm.persistence.manager.ScmZdDutyOfficeManager;

/**
 * 
 * <pre> 
 * 描述：责任办事处 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-29 10:31:24
 * 版权：美达项目组
 * </pre>
 */
@Service("scmZdDutyOfficeManager")
public class ScmZdDutyOfficeManagerImpl extends AbstractManagerImpl<String, ScmZdDutyOffice> implements ScmZdDutyOfficeManager{
	@Resource
	ScmZdDutyOfficeDao scmZdDutyOfficeDao;
	@Override
	protected MyBatisDao<String, ScmZdDutyOffice> getDao() {
		return scmZdDutyOfficeDao;
	}
	@Override
	public void getByNameAndCodeOrId(ScmZdDutyOffice scmZdDutyOffice) {
		//根据名称与ID判断是否已经存在当前使用名称
		List<ScmZdDutyOffice> sdas = scmZdDutyOfficeDao.getByNameAndCodeOrId(scmZdDutyOffice);
		if(sdas.size() > 0) {
			throw new RuntimeException("当前名称或编号已经被使用,请修改后提交");
		}
		
	}
}
