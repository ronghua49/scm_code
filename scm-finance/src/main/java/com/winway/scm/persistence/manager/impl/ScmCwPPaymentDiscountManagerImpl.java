package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmCwPPaymentDiscountDao;
import com.winway.scm.model.ScmCwPPaymentDiscount;
import com.winway.scm.persistence.manager.ScmCwPPaymentDiscountManager;

/**
 * 
 * <pre> 
 * 描述：经销商RB支付付款折扣 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-09 18:18:05
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmCwPPaymentDiscountManager")
public class ScmCwPPaymentDiscountManagerImpl extends AbstractManagerImpl<String, ScmCwPPaymentDiscount> implements ScmCwPPaymentDiscountManager{
	@Resource
	ScmCwPPaymentDiscountDao scmCwPPaymentDiscountDao;
	@Override
	protected MyBatisDao<String, ScmCwPPaymentDiscount> getDao() {
		return scmCwPPaymentDiscountDao;
	}
}
