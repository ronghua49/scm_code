package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmCwPAnnualFeeDao;
import com.winway.scm.model.ScmCwPAnnualFee;
import com.winway.scm.persistence.manager.ScmCwPAnnualFeeManager;

/**
 * 
 * <pre> 
 * 描述：经销商RB支付年费信息管理费 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-09 18:18:05
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmCwPAnnualFeeManager")
public class ScmCwPAnnualFeeManagerImpl extends AbstractManagerImpl<String, ScmCwPAnnualFee> implements ScmCwPAnnualFeeManager{
	@Resource
	ScmCwPAnnualFeeDao scmCwPAnnualFeeDao;
	@Override
	protected MyBatisDao<String, ScmCwPAnnualFee> getDao() {
		return scmCwPAnnualFeeDao;
	}
}
