package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmZdDiscountTypeDao;
import com.winway.scm.model.ScmZdDiscountType;
import com.winway.scm.persistence.manager.ScmZdDiscountTypeManager;

/**
 * 
 * <pre> 
 * 描述：票折类型表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-27 11:25:26
 * 版权：美达项目组
 * </pre>
 */
@Service("scmZdDiscountTypeManager")
public class ScmZdDiscountTypeManagerImpl extends AbstractManagerImpl<String, ScmZdDiscountType> implements ScmZdDiscountTypeManager{
	@Resource
	ScmZdDiscountTypeDao scmZdDiscountTypeDao;
	@Override
	protected MyBatisDao<String, ScmZdDiscountType> getDao() {
		return scmZdDiscountTypeDao;
	}
}
