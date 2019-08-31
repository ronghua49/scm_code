package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmCommercePlaceRateDao;
import com.winway.scm.model.ScmCommercePlaceRate;
import com.winway.scm.persistence.manager.ScmCommercePlaceRateManager;

/**
 * 
 * <pre> 
 * 描述：商品渠道毛利率管理 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-06-27 15:55:07
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmCommercePlaceRateManager")
public class ScmCommercePlaceRateManagerImpl extends AbstractManagerImpl<String, ScmCommercePlaceRate> implements ScmCommercePlaceRateManager{
	@Resource
	ScmCommercePlaceRateDao scmCommercePlaceRateDao;
	@Override
	protected MyBatisDao<String, ScmCommercePlaceRate> getDao() {
		return scmCommercePlaceRateDao;
	}
}
