package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmZsjCommerceBusinessDao;
import com.winway.scm.model.ScmZsjCommerceBusiness;
import com.winway.scm.persistence.manager.ScmZsjCommerceBusinessManager;

/**
 * 
 * <pre> 
 * 描述：商业营业执照信息 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmZsjCommerceBusinessManager")
public class ScmZsjCommerceBusinessManagerImpl extends AbstractManagerImpl<String, ScmZsjCommerceBusiness> implements ScmZsjCommerceBusinessManager{
	@Resource
	ScmZsjCommerceBusinessDao scmZsjCommerceBusinessDao;
	@Override
	protected MyBatisDao<String, ScmZsjCommerceBusiness> getDao() {
		return scmZsjCommerceBusinessDao;
	}
}
