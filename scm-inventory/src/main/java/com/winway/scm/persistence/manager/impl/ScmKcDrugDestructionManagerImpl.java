package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmKcDrugDestructionDao;
import com.winway.scm.model.ScmKcDrugDestruction;
import com.winway.scm.persistence.manager.ScmKcDrugDestructionManager;

/**
 * 
 * <pre> 
 * 描述：不合格药品销毁明细表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-13 15:06:38
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmKcDrugDestructionManager")
public class ScmKcDrugDestructionManagerImpl extends AbstractManagerImpl<String, ScmKcDrugDestruction> implements ScmKcDrugDestructionManager{
	@Resource
	ScmKcDrugDestructionDao scmKcDrugDestructionDao;
	@Override
	protected MyBatisDao<String, ScmKcDrugDestruction> getDao() {
		return scmKcDrugDestructionDao;
	}
}
