package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmZsjCommerceAcceptStateTSDao;
import com.winway.scm.model.ScmZsjCommerceAcceptStateTS;
import com.winway.scm.persistence.manager.ScmZsjCommerceAcceptStateTSManager;

/**
 * 
 * <pre> 
 * 描述：商业认可状态暂存表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-18 14:36:01
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmZsjCommerceAcceptStateTSManager")
public class ScmZsjCommerceAcceptStateTSManagerImpl extends AbstractManagerImpl<String, ScmZsjCommerceAcceptStateTS> implements ScmZsjCommerceAcceptStateTSManager{
	@Resource
	ScmZsjCommerceAcceptStateTSDao scmZsjCommerceAcceptStateTSDao;
	@Override
	protected MyBatisDao<String, ScmZsjCommerceAcceptStateTS> getDao() {
		return scmZsjCommerceAcceptStateTSDao;
	}
}
