package com.winway.scm.persistence.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmZdGMPCertiflcationScopeDao;
import com.winway.scm.model.ScmZdAgenttype;
import com.winway.scm.model.ScmZdGMPCertiflcationScope;
import com.winway.scm.persistence.manager.ScmZdGMPCertiflcationScopeManager;

/**
 * 
 * <pre> 
 * 描述：GMP证书认证范围 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-29 10:31:24
 * 版权：美达项目组
 * </pre>
 */
@Service("scmZdGMPCertiflcationScopeManager")
public class ScmZdGMPCertiflcationScopeManagerImpl extends AbstractManagerImpl<String, ScmZdGMPCertiflcationScope> implements ScmZdGMPCertiflcationScopeManager{
	@Resource
	ScmZdGMPCertiflcationScopeDao scmZdGMPCertiflcationScopeDao;
	@Override
	protected MyBatisDao<String, ScmZdGMPCertiflcationScope> getDao() {
		return scmZdGMPCertiflcationScopeDao;
	}
	@Override
	public void getByNameAndCodeOrId(ScmZdGMPCertiflcationScope scmZdGMPCertiflcationScope) {
		//根据名称与ID判断是否已经存在当前使用名称
		List<ScmZdGMPCertiflcationScope> sdas = scmZdGMPCertiflcationScopeDao.getByNameAndCodeOrId(scmZdGMPCertiflcationScope);
		if(sdas.size() > 0) {
			throw new RuntimeException("当前名称或编号已经被使用,请修改后提交");
		}
	}
}
