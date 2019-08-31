package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmGspDoubtProductDao;
import com.winway.scm.model.ScmGspDoubtProduct;
import com.winway.scm.persistence.manager.ScmGspDoubtProductManager;

/**
 * 
 * <pre> 
 * 描述：Scm_Gsp_DoubtProduct 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-18 18:21:20
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmGspDoubtProductManager")
public class ScmGspDoubtProductManagerImpl extends AbstractManagerImpl<String, ScmGspDoubtProduct> implements ScmGspDoubtProductManager{
	@Resource
	ScmGspDoubtProductDao scmGspDoubtProductDao;
	@Override
	protected MyBatisDao<String, ScmGspDoubtProduct> getDao() {
		return scmGspDoubtProductDao;
	}
}
