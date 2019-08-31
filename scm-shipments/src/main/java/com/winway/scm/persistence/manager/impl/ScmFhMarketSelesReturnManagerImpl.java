package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmFhMarketSelesReturnDao;
import com.winway.scm.model.ScmFhMarketSelesReturn;
import com.winway.scm.persistence.manager.ScmFhMarketSelesReturnManager;

/**
 * 
 * <pre> 
 * 描述：销售退货表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-13 15:47:33
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmFhMarketSelesReturnManager")
public class ScmFhMarketSelesReturnManagerImpl extends AbstractManagerImpl<String, ScmFhMarketSelesReturn> implements ScmFhMarketSelesReturnManager{
	@Resource
	ScmFhMarketSelesReturnDao scmFhMarketSelesReturnDao;
	@Override
	protected MyBatisDao<String, ScmFhMarketSelesReturn> getDao() {
		return scmFhMarketSelesReturnDao;
	}
}
