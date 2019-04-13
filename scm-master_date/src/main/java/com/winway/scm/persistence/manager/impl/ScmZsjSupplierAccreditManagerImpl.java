package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmZsjSupplierAccreditDao;
import com.winway.scm.model.ScmZsjSupplierAccredit;
import com.winway.scm.persistence.manager.ScmZsjSupplierAccreditManager;

/**
 * 
 * <pre> 
 * 描述：授权销售品种 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmZsjSupplierAccreditManager")
public class ScmZsjSupplierAccreditManagerImpl extends AbstractManagerImpl<String, ScmZsjSupplierAccredit> implements ScmZsjSupplierAccreditManager{
	@Resource
	ScmZsjSupplierAccreditDao scmZsjSupplierAccreditDao;
	@Override
	protected MyBatisDao<String, ScmZsjSupplierAccredit> getDao() {
		return scmZsjSupplierAccreditDao;
	}
}
