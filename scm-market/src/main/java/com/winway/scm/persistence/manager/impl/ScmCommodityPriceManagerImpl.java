package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmCommodityPriceDao;
import com.winway.scm.model.ScmCommodityPrice;
import com.winway.scm.persistence.manager.ScmCommodityPriceManager;

/**
 * 
 * <pre> 
 * 描述：物价信息管理 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-06-27 15:55:07
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmCommodityPriceManager")
public class ScmCommodityPriceManagerImpl extends AbstractManagerImpl<String, ScmCommodityPrice> implements ScmCommodityPriceManager{
	@Resource
	ScmCommodityPriceDao scmCommodityPriceDao;
	@Override
	protected MyBatisDao<String, ScmCommodityPrice> getDao() {
		return scmCommodityPriceDao;
	}
}
