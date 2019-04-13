package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmZsjAccessoryDao;
import com.winway.scm.model.ScmZsjAccessory;
import com.winway.scm.persistence.manager.ScmZsjAccessoryManager;

/**
 * 
 * <pre> 
 * 描述：附件表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmZsjAccessoryManager")
public class ScmZsjAccessoryManagerImpl extends AbstractManagerImpl<String, ScmZsjAccessory> implements ScmZsjAccessoryManager{
	@Resource
	ScmZsjAccessoryDao scmZsjAccessoryDao;
	@Override
	protected MyBatisDao<String, ScmZsjAccessory> getDao() {
		return scmZsjAccessoryDao;
	}
}
