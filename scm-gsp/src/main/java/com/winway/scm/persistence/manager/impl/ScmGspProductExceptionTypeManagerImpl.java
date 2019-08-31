package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmGspProductExceptionTypeDao;
import com.winway.scm.model.ScmGspProductExceptionType;
import com.winway.scm.persistence.manager.ScmGspProductExceptionTypeManager;

/**
 * 
 * <pre> 
 * 描述：产品异常状态 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-30 14:46:25
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmGspProductExceptionTypeManager")
public class ScmGspProductExceptionTypeManagerImpl extends AbstractManagerImpl<String, ScmGspProductExceptionType> implements ScmGspProductExceptionTypeManager{
	@Resource
	ScmGspProductExceptionTypeDao scmGspProductExceptionTypeDao;
	@Override
	protected MyBatisDao<String, ScmGspProductExceptionType> getDao() {
		return scmGspProductExceptionTypeDao;
	}
	
	@Override
	public void update(ScmGspProductExceptionType entity) {
		ScmGspProductExceptionType scmGspProductExceptionType = scmGspProductExceptionTypeDao.get(entity.getId());
		if(scmGspProductExceptionType == null) {
			throw new RuntimeException("未查询到历史数据");
		}
		entity.setCreatepersion(scmGspProductExceptionType.getCreatepersion());
		entity.setCreateDate(scmGspProductExceptionType.getCreateDate());
		super.update(entity);
	}
}
