package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmCwSpecialTicketDiscountDao;
import com.winway.scm.model.ScmCwSpecialTicketDiscount;
import com.winway.scm.persistence.manager.ScmCwSpecialTicketDiscountManager;

/**
 * 
 * <pre> 
 * 描述：特殊票折(导入) 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-18 15:28:17
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmCwSpecialTicketDiscountManager")
public class ScmCwSpecialTicketDiscountManagerImpl extends AbstractManagerImpl<String, ScmCwSpecialTicketDiscount> implements ScmCwSpecialTicketDiscountManager{
	@Resource
	ScmCwSpecialTicketDiscountDao scmCwSpecialTicketDiscountDao;
	@Override
	protected MyBatisDao<String, ScmCwSpecialTicketDiscount> getDao() {
		return scmCwSpecialTicketDiscountDao;
	}
}
