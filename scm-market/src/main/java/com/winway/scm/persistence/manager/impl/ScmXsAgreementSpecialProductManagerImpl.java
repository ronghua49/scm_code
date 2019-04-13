package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmXsAgreementSpecialProductDao;
import com.winway.scm.model.ScmXsAgreementSpecialProduct;
import com.winway.scm.persistence.manager.ScmXsAgreementSpecialProductManager;

/**
 * 
 * <pre> 
 * 描述：经销商协议特殊商品表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:44
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmXsAgreementSpecialProductManager")
public class ScmXsAgreementSpecialProductManagerImpl extends AbstractManagerImpl<String, ScmXsAgreementSpecialProduct> implements ScmXsAgreementSpecialProductManager{
	@Resource
	ScmXsAgreementSpecialProductDao scmXsAgreementSpecialProductDao;
	@Override
	protected MyBatisDao<String, ScmXsAgreementSpecialProduct> getDao() {
		return scmXsAgreementSpecialProductDao;
	}
	@Override
	public void delByMainId(String arg0) {
		// TODO Auto-generated method stub		
		scmXsAgreementSpecialProductDao.delByMainId(arg0);
	}
}
