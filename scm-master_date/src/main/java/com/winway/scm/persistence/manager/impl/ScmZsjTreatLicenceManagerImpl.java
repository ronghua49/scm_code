package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmZsjTreatLicenceDao;
import com.winway.scm.model.ScmZsjTreatLicence;
import com.winway.scm.persistence.manager.ScmZsjTreatLicenceManager;

/**
 * 
 * <pre> 
 * 描述：商业医疗许可证 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmZsjTreatLicenceManager")
public class ScmZsjTreatLicenceManagerImpl extends AbstractManagerImpl<String, ScmZsjTreatLicence> implements ScmZsjTreatLicenceManager{
	@Resource
	ScmZsjTreatLicenceDao scmZsjTreatLicenceDao;
	@Override
	protected MyBatisDao<String, ScmZsjTreatLicence> getDao() {
		return scmZsjTreatLicenceDao;
	}
}
