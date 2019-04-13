package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmCwDiscountRecordDao;
import com.winway.scm.model.ScmCwDiscountRecord;
import com.winway.scm.persistence.manager.ScmCwDiscountRecordManager;

/**
 * 
 * <pre> 
 * 描述：票折记录表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-23 18:19:25
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmCwDiscountRecordManager")
public class ScmCwDiscountRecordManagerImpl extends AbstractManagerImpl<String, ScmCwDiscountRecord> implements ScmCwDiscountRecordManager{
	@Resource
	ScmCwDiscountRecordDao scmCwDiscountRecordDao;
	@Override
	protected MyBatisDao<String, ScmCwDiscountRecord> getDao() {
		return scmCwDiscountRecordDao;
	}
}
