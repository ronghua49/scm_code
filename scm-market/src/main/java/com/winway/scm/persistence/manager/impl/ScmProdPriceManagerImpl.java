package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmProdPriceDao;
import com.winway.scm.model.ScmProdPrice;
import com.winway.scm.persistence.manager.ScmProdPriceManager;

/**
 * 
 * <pre> 
 * 描述：商品价格 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-06-27 15:55:07
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmProdPriceManager")
public class ScmProdPriceManagerImpl extends AbstractManagerImpl<String, ScmProdPrice> implements ScmProdPriceManager{
	@Resource
	ScmProdPriceDao scmProdPriceDao;
	@Override
	protected MyBatisDao<String, ScmProdPrice> getDao() {
		return scmProdPriceDao;
	}
}
