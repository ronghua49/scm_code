package com.winway.scm.persistence.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmZdKeepingConditionDao;
import com.winway.scm.model.ScmZdGetticket;
import com.winway.scm.model.ScmZdKeepingCondition;
import com.winway.scm.persistence.manager.ScmZdKeepingConditionManager;

/**
 * 
 * <pre> 
 * 描述：贮存条件 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-29 10:31:24
 * 版权：美达项目组
 * </pre>
 */
@Service("scmZdKeepingConditionManager")
public class ScmZdKeepingConditionManagerImpl extends AbstractManagerImpl<String, ScmZdKeepingCondition> implements ScmZdKeepingConditionManager{
	@Resource
	ScmZdKeepingConditionDao scmZdKeepingConditionDao;
	@Override
	protected MyBatisDao<String, ScmZdKeepingCondition> getDao() {
		return scmZdKeepingConditionDao;
	}
	@Override
	public void getByNameAndCodeOrId(ScmZdKeepingCondition scmZdKeepingCondition) {
		//根据名称与ID判断是否已经存在当前使用名称
		List<ScmZdKeepingCondition> sdas = scmZdKeepingConditionDao.getByNameAndCodeOrId(scmZdKeepingCondition);
		if(sdas.size() > 0) {
			throw new RuntimeException("当前名称或编号已经被使用,请修改后提交");
		}
	}
}
