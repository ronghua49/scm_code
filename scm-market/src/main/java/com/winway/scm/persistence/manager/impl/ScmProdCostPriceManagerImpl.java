package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmProdCostPriceDao;
import com.winway.scm.model.ScmProdCostPrice;
import com.winway.scm.persistence.manager.ScmProdCostPriceManager;

/**
 * 
 * <pre> 
 * 描述：商品成本价管理 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-06-27 15:55:07
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmProdCostPriceManager")
public class ScmProdCostPriceManagerImpl extends AbstractManagerImpl<String, ScmProdCostPrice> implements ScmProdCostPriceManager{
	@Resource
	ScmProdCostPriceDao scmProdCostPriceDao;
	@Override
	protected MyBatisDao<String, ScmProdCostPrice> getDao() {
		return scmProdCostPriceDao;
	}
}
