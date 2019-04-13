package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmZsjCommerceShipAddressDao;
import com.winway.scm.model.ScmZsjCommerceShipAddress;
import com.winway.scm.persistence.manager.ScmZsjCommerceShipAddressManager;

/**
 * 
 * <pre> 
 * 描述：商业收货地址 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmZsjCommerceShipAddressManager")
public class ScmZsjCommerceShipAddressManagerImpl extends AbstractManagerImpl<String, ScmZsjCommerceShipAddress> implements ScmZsjCommerceShipAddressManager{
	@Resource
	ScmZsjCommerceShipAddressDao scmZsjCommerceShipAddressDao;
	@Override
	protected MyBatisDao<String, ScmZsjCommerceShipAddress> getDao() {
		return scmZsjCommerceShipAddressDao;
	}
}
