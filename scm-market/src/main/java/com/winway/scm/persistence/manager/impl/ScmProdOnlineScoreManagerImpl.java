package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmProdOnlineScoreDao;
import com.winway.scm.model.ScmProdOnlineScore;
import com.winway.scm.persistence.manager.ScmProdOnlineScoreManager;

/**
 * 
 * <pre> 
 * 描述：商品电商积分管理 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-06-27 15:55:07
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmProdOnlineScoreManager")
public class ScmProdOnlineScoreManagerImpl extends AbstractManagerImpl<String, ScmProdOnlineScore> implements ScmProdOnlineScoreManager{
	@Resource
	ScmProdOnlineScoreDao scmProdOnlineScoreDao;
	@Override
	protected MyBatisDao<String, ScmProdOnlineScore> getDao() {
		return scmProdOnlineScoreDao;
	}
}
