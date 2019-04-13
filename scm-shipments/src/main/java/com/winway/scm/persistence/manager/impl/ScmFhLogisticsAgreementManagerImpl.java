package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmFhLogisticsAgreementDao;
import com.winway.scm.model.ScmFhLogisticsAgreement;
import com.winway.scm.persistence.manager.ScmFhLogisticsAgreementManager;

/**
 * 
 * <pre> 
 * 描述：物流协议表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-02-15 17:31:50
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmFhLogisticsAgreementManager")
public class ScmFhLogisticsAgreementManagerImpl extends AbstractManagerImpl<String, ScmFhLogisticsAgreement> implements ScmFhLogisticsAgreementManager{
	@Resource
	ScmFhLogisticsAgreementDao scmFhLogisticsAgreementDao;
	@Override
	protected MyBatisDao<String, ScmFhLogisticsAgreement> getDao() {
		return scmFhLogisticsAgreementDao;
	}
}
