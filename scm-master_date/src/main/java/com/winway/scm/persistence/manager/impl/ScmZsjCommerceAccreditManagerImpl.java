package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmZsjCommerceAccreditDao;
import com.winway.scm.model.ScmZsjCommerceAccredit;
import com.winway.scm.persistence.manager.ScmZsjCommerceAccreditManager;

/**
 * 
 * <pre> 
 * 描述：商业授权销售品种 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmZsjCommerceAccreditManager")
public class ScmZsjCommerceAccreditManagerImpl extends AbstractManagerImpl<String, ScmZsjCommerceAccredit> implements ScmZsjCommerceAccreditManager{
	@Resource
	ScmZsjCommerceAccreditDao scmZsjCommerceAccreditDao;
	@Override
	protected MyBatisDao<String, ScmZsjCommerceAccredit> getDao() {
		return scmZsjCommerceAccreditDao;
	}
}
