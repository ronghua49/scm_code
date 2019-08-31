package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmTenderPriceRangeDao;
import com.winway.scm.model.ScmTenderPriceRange;
import com.winway.scm.persistence.manager.ScmTenderPriceRangeManager;

/**
 * 
 * <pre> 
 * 描述：中标价区间管理 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-06-25 11:09:05
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmTenderPriceRangeManager")
public class ScmTenderPriceRangeManagerImpl extends AbstractManagerImpl<String, ScmTenderPriceRange> implements ScmTenderPriceRangeManager{
	@Resource
	ScmTenderPriceRangeDao scmTenderPriceRangeDao;
	@Override
	protected MyBatisDao<String, ScmTenderPriceRange> getDao() {
		return scmTenderPriceRangeDao;
	}
}
