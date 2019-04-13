package com.winway.scm.persistence.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.query.PageBean;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.BeanUtils;
import com.winway.scm.persistence.dao.ScmXsBigContractAllotProductDao;
import com.winway.scm.model.ScmXsAgreementList;
import com.winway.scm.model.ScmXsBigContractAllotProduct;
import com.winway.scm.persistence.manager.ScmXsBigContractAllotProductManager;

/**
 * 
 * <pre> 
 * 描述：大合同分配商品表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:44
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmXsBigContractAllotProductManager")
public class ScmXsBigContractAllotProductManagerImpl extends AbstractManagerImpl<String, ScmXsBigContractAllotProduct> implements ScmXsBigContractAllotProductManager{
	@Resource
	ScmXsBigContractAllotProductDao scmXsBigContractAllotProductDao;
	@Override
	protected MyBatisDao<String, ScmXsBigContractAllotProduct> getDao() {
		return scmXsBigContractAllotProductDao;
	}
}
