package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmGspRejectionProductDao;
import com.winway.scm.model.ScmGspRejectionProduct;
import com.winway.scm.persistence.manager.ScmGspRejectionProductManager;

/**
 * 
 * <pre> 
 * 描述：药品拒收商品表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-24 12:00:21
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmGspRejectionProductManager")
public class ScmGspRejectionProductManagerImpl extends AbstractManagerImpl<String, ScmGspRejectionProduct> implements ScmGspRejectionProductManager{
	@Resource
	ScmGspRejectionProductDao scmGspRejectionProductDao;
	@Override
	protected MyBatisDao<String, ScmGspRejectionProduct> getDao() {
		return scmGspRejectionProductDao;
	}
}
