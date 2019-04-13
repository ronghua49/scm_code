package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmFhCommonCarrierDao;
import com.winway.scm.model.ScmFhCommonCarrier;
import com.winway.scm.persistence.manager.ScmFhCommonCarrierManager;

/**
 * 
 * <pre> 
 * 描述：承运商表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-02-15 17:31:50
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmFhCommonCarrierManager")
public class ScmFhCommonCarrierManagerImpl extends AbstractManagerImpl<String, ScmFhCommonCarrier> implements ScmFhCommonCarrierManager{
	@Resource
	ScmFhCommonCarrierDao scmFhCommonCarrierDao;
	@Override
	protected MyBatisDao<String, ScmFhCommonCarrier> getDao() {
		return scmFhCommonCarrierDao;
	}
}
