package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmXsAgreementSubsidiaryDao;
import com.winway.scm.model.ScmXsAgreementSubsidiary;
import com.winway.scm.persistence.manager.ScmXsAgreementSubsidiaryManager;

/**
 * 
 * <pre> 
 * 描述：商业协议分子公司表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:44
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmXsAgreementSubsidiaryManager")
public class ScmXsAgreementSubsidiaryManagerImpl extends AbstractManagerImpl<String, ScmXsAgreementSubsidiary> implements ScmXsAgreementSubsidiaryManager{
	@Resource
	ScmXsAgreementSubsidiaryDao scmXsAgreementSubsidiaryDao;
	@Override
	protected MyBatisDao<String, ScmXsAgreementSubsidiary> getDao() {
		return scmXsAgreementSubsidiaryDao;
	}
	@Override
	public void delByMainId(String arg0) {
		// TODO Auto-generated method stub
		scmXsAgreementSubsidiaryDao.delByMainId(arg0);
	}
}
