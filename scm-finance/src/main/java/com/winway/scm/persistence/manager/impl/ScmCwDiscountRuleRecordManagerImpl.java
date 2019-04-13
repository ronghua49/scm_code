package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmCwDiscountRuleRecordDao;
import com.winway.scm.model.ScmCwDiscountRuleRecord;
import com.winway.scm.persistence.manager.ScmCwDiscountRuleRecordManager;

/**
 * 
 * <pre> 
 * 描述：scm_cw_discountrulerecord 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-27 14:18:12
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmCwDiscountRuleRecordManager")
public class ScmCwDiscountRuleRecordManagerImpl extends AbstractManagerImpl<String, ScmCwDiscountRuleRecord> implements ScmCwDiscountRuleRecordManager{
	@Resource
	ScmCwDiscountRuleRecordDao scmCwDiscountRuleRecordDao;
	@Override
	protected MyBatisDao<String, ScmCwDiscountRuleRecord> getDao() {
		return scmCwDiscountRuleRecordDao;
	}
}
