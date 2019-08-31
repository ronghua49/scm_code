package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmGspAndUseProductDao;
import com.winway.scm.model.ScmGspAndUseProduct;
import com.winway.scm.persistence.manager.ScmGspAndUseProductManager;

/**
 * 
 * <pre> 
 * 描述：并用药品 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-18 18:21:20
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmGspAndUseProductManager")
public class ScmGspAndUseProductManagerImpl extends AbstractManagerImpl<String, ScmGspAndUseProduct> implements ScmGspAndUseProductManager{
	@Resource
	ScmGspAndUseProductDao scmGspAndUseProductDao;
	@Override
	protected MyBatisDao<String, ScmGspAndUseProduct> getDao() {
		return scmGspAndUseProductDao;
	}
}
