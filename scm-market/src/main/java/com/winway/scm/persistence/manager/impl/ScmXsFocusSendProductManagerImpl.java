package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmXsFocusSendProductDao;
import com.winway.scm.model.ScmXsFocusSendProduct;
import com.winway.scm.persistence.manager.ScmXsFocusSendProductManager;

/**
 * 
 * <pre> 
 * 描述：集中发货商品表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:44
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmXsFocusSendProductManager")
public class ScmXsFocusSendProductManagerImpl extends AbstractManagerImpl<String, ScmXsFocusSendProduct> implements ScmXsFocusSendProductManager{
	@Resource
	ScmXsFocusSendProductDao scmXsFocusSendProductDao;
	@Override
	protected MyBatisDao<String, ScmXsFocusSendProduct> getDao() {
		return scmXsFocusSendProductDao;
	}
	
}
