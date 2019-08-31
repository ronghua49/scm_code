package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmCwDistributorRbCollectDao;
import com.winway.scm.model.ScmCwDistributorRbCollect;
import com.winway.scm.persistence.manager.ScmCwDistributorRbCollectManager;

/**
 * 
 * <pre> 
 * 描述：分销商RB汇总 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-14 16:38:31
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmCwDistributorRbCollectManager")
public class ScmCwDistributorRbCollectManagerImpl extends AbstractManagerImpl<String, ScmCwDistributorRbCollect> implements ScmCwDistributorRbCollectManager{
	@Resource
	ScmCwDistributorRbCollectDao scmCwDistributorRbCollectDao;
	@Override
	protected MyBatisDao<String, ScmCwDistributorRbCollect> getDao() {
		return scmCwDistributorRbCollectDao;
	}
}
