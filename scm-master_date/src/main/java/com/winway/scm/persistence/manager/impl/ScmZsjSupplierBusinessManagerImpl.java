package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmZsjSupplierBusinessDao;
import com.winway.scm.model.ScmZsjSupplierBusiness;
import com.winway.scm.persistence.manager.ScmZsjSupplierBusinessManager;

/**
 * 
 * <pre> 
 * 描述：供应商营业执照信息 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmZsjSupplierBusinessManager")
public class ScmZsjSupplierBusinessManagerImpl extends AbstractManagerImpl<String, ScmZsjSupplierBusiness> implements ScmZsjSupplierBusinessManager{
	@Resource
	ScmZsjSupplierBusinessDao scmZsjSupplierBusinessDao;
	@Override
	protected MyBatisDao<String, ScmZsjSupplierBusiness> getDao() {
		return scmZsjSupplierBusinessDao;
	}
}
