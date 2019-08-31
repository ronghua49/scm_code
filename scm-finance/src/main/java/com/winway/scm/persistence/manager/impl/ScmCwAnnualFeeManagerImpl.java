package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmCwAnnualFeeDao;
import com.winway.scm.model.ScmCwAnnualFee;
import com.winway.scm.persistence.manager.ScmCwAnnualFeeManager;

/**
 * 
 * <pre> 
 * 描述：年费信息管理费详情 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-28 09:38:34
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmCwAnnualFeeManager")
public class ScmCwAnnualFeeManagerImpl extends AbstractManagerImpl<String, ScmCwAnnualFee> implements ScmCwAnnualFeeManager{
	@Resource
	ScmCwAnnualFeeDao scmCwAnnualFeeDao;
	@Override
	protected MyBatisDao<String, ScmCwAnnualFee> getDao() {
		return scmCwAnnualFeeDao;
	}
}
