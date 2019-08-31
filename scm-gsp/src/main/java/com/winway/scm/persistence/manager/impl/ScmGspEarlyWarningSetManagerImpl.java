package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmGspEarlyWarningSetDao;
import com.winway.scm.model.ScmGspEarlyWarningSet;
import com.winway.scm.persistence.manager.ScmGspEarlyWarningSetManager;

/**
 * 
 * <pre> 
 * 描述：预警设置 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-18 15:11:34
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmGspEarlyWarningSetManager")
public class ScmGspEarlyWarningSetManagerImpl extends AbstractManagerImpl<String, ScmGspEarlyWarningSet> implements ScmGspEarlyWarningSetManager{
	@Resource
	ScmGspEarlyWarningSetDao scmGspEarlyWarningSetDao;
	@Override
	protected MyBatisDao<String, ScmGspEarlyWarningSet> getDao() {
		return scmGspEarlyWarningSetDao;
	}
	
	@Override
	public ScmGspEarlyWarningSet get(String entityId) {
		ScmGspEarlyWarningSet scmGspEarlyWarningSet = scmGspEarlyWarningSetDao.get(entityId);
		if(scmGspEarlyWarningSet == null){
			scmGspEarlyWarningSet = new ScmGspEarlyWarningSet();
			scmGspEarlyWarningSet.setLicenceDay(0);
			scmGspEarlyWarningSet.setBusinessDay(0);
			scmGspEarlyWarningSet.setGmpDay(0);
			scmGspEarlyWarningSet.setGspDay(0);
			scmGspEarlyWarningSet.setEntrustBookDay(0);
			scmGspEarlyWarningSet.setDataGuaranteeDay(0);
			scmGspEarlyWarningSet.setCommerceStockDay(0);
			scmGspEarlyWarningSet.setOwnerId(entityId);
			super.create(scmGspEarlyWarningSet);
		}
		return scmGspEarlyWarningSet;
	}
}
