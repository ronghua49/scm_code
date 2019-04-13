package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmZsjProductLineDao;
import com.winway.scm.model.ScmZsjProductLine;
import com.winway.scm.persistence.manager.ScmZsjProductLineManager;

/**
 * 
 * <pre> 
 * 描述：产品系列 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 16:15:43
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmZsjProductLineManager")
public class ScmZsjProductLineManagerImpl extends AbstractManagerImpl<String, ScmZsjProductLine> implements ScmZsjProductLineManager{
	@Resource
	ScmZsjProductLineDao scmZsjProductLineDao;
	@Override
	protected MyBatisDao<String, ScmZsjProductLine> getDao() {
		return scmZsjProductLineDao;
	}
	@Override
	public String changeState(String id) {
		ScmZsjProductLine scmZsjProductLine = scmZsjProductLineDao.get(id);
		if("0".equals(scmZsjProductLine.getProductLineState())) {
			//当前状态为0  调整为启用状态
			scmZsjProductLine.setProductLineState("1");
		}else{
			scmZsjProductLine.setProductLineState("0");
		}
		scmZsjProductLineDao.update(scmZsjProductLine);
		return scmZsjProductLine.getProductLineState();
	}
}
