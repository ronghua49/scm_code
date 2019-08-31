package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmCwDistributorRebateDao;
import com.winway.scm.model.ScmCwDistributorRebate;
import com.winway.scm.persistence.manager.ScmCwDistributorRebateManager;

/**
 * 
 * <pre> 
 * 描述：分销商返利明细 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-14 16:38:31
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmCwDistributorRebateManager")
public class ScmCwDistributorRebateManagerImpl extends AbstractManagerImpl<String, ScmCwDistributorRebate> implements ScmCwDistributorRebateManager{
	@Resource
	ScmCwDistributorRebateDao scmCwDistributorRebateDao;
	@Override
	protected MyBatisDao<String, ScmCwDistributorRebate> getDao() {
		return scmCwDistributorRebateDao;
	}
}
