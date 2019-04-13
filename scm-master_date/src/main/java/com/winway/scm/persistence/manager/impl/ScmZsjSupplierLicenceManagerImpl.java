package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmZsjSupplierLicenceDao;
import com.winway.scm.model.ScmZsjSupplierLicence;
import com.winway.scm.persistence.manager.ScmZsjSupplierLicenceManager;

/**
 * 
 * <pre> 
 * 描述：供应商许可证信息 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmZsjSupplierLicenceManager")
public class ScmZsjSupplierLicenceManagerImpl extends AbstractManagerImpl<String, ScmZsjSupplierLicence> implements ScmZsjSupplierLicenceManager{
	@Resource
	ScmZsjSupplierLicenceDao scmZsjSupplierLicenceDao;
	@Override
	protected MyBatisDao<String, ScmZsjSupplierLicence> getDao() {
		return scmZsjSupplierLicenceDao;
	}
}
