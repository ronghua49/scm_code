package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmProdAgencyScoreDao;
import com.winway.scm.model.ScmProdAgencyScore;
import com.winway.scm.persistence.manager.ScmProdAgencyScoreManager;

/**
 * 
 * <pre> 
 * 描述：商品代理制积分管理 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-06-27 15:55:07
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmProdAgencyScoreManager")
public class ScmProdAgencyScoreManagerImpl extends AbstractManagerImpl<String, ScmProdAgencyScore> implements ScmProdAgencyScoreManager{
	@Resource
	ScmProdAgencyScoreDao scmProdAgencyScoreDao;
	@Override
	protected MyBatisDao<String, ScmProdAgencyScore> getDao() {
		return scmProdAgencyScoreDao;
	}
}
