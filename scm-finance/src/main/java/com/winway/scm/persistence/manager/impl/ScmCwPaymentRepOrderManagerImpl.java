package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmCwPaymentRepOrderDao;
import com.winway.scm.model.ScmCwPaymentRepOrder;
import com.winway.scm.persistence.manager.ScmCwPaymentRepOrderManager;

/**
 * 
 * <pre> 
 * 描述：付款采购订单补单表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-23 18:19:25
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmCwPaymentRepOrderManager")
public class ScmCwPaymentRepOrderManagerImpl extends AbstractManagerImpl<String, ScmCwPaymentRepOrder> implements ScmCwPaymentRepOrderManager{
	@Resource
	ScmCwPaymentRepOrderDao scmCwPaymentRepOrderDao;
	@Override
	protected MyBatisDao<String, ScmCwPaymentRepOrder> getDao() {
		return scmCwPaymentRepOrderDao;
	}
}
