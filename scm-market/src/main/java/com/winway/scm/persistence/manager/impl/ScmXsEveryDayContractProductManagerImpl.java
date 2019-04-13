package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmXsEveryDayContractProductDao;
import com.winway.scm.model.ScmXsEveryDayContractProduct;
import com.winway.scm.persistence.manager.ScmXsEveryDayContractProductManager;

/**
 * 
 * <pre> 
 * 描述：日常合同商品表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:44
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmXsEveryDayContractProductManager")
public class ScmXsEveryDayContractProductManagerImpl extends AbstractManagerImpl<String, ScmXsEveryDayContractProduct> implements ScmXsEveryDayContractProductManager{
	@Resource
	ScmXsEveryDayContractProductDao scmXsEveryDayContractProductDao;
	@Override
	protected MyBatisDao<String, ScmXsEveryDayContractProduct> getDao() {
		return scmXsEveryDayContractProductDao;
	}

	@Override
	public void create(ScmXsEveryDayContractProduct entity) {
		super.create(entity);
	}
}
