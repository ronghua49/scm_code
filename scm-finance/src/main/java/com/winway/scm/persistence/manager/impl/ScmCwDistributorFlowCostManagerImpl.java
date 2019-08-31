package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmCwDistributorFlowCostDao;
import com.winway.scm.model.ScmCwDistributorFlowCost;
import com.winway.scm.persistence.manager.ScmCwDistributorFlowCostManager;

/**
 * 
 * <pre> 
 * 描述：分销商流向管理费 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-14 16:38:31
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmCwDistributorFlowCostManager")
public class ScmCwDistributorFlowCostManagerImpl extends AbstractManagerImpl<String, ScmCwDistributorFlowCost> implements ScmCwDistributorFlowCostManager{
	@Resource
	ScmCwDistributorFlowCostDao scmCwDistributorFlowCostDao;
	@Override
	protected MyBatisDao<String, ScmCwDistributorFlowCost> getDao() {
		return scmCwDistributorFlowCostDao;
	}
}
