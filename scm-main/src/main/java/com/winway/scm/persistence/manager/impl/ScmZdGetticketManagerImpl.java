package com.winway.scm.persistence.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmZdGetticketDao;
import com.winway.scm.model.ScmZdGetMoneyWarehouse;
import com.winway.scm.model.ScmZdGetticket;
import com.winway.scm.persistence.manager.ScmZdGetticketManager;

/**
 * 
 * <pre> 
 * 描述：收票方 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-29 10:31:24
 * 版权：美达项目组
 * </pre>
 */
@Service("scmZdGetticketManager")
public class ScmZdGetticketManagerImpl extends AbstractManagerImpl<String, ScmZdGetticket> implements ScmZdGetticketManager{
	@Resource
	ScmZdGetticketDao scmZdGetticketDao;
	@Override
	protected MyBatisDao<String, ScmZdGetticket> getDao() {
		return scmZdGetticketDao;
	}
	@Override
	public void getByNameAndCodeOrId(ScmZdGetticket scmZdGetticket) {
		//根据名称与ID判断是否已经存在当前使用名称
		List<ScmZdGetticket> sdas = scmZdGetticketDao.getByNameAndCodeOrId(scmZdGetticket);
		if(sdas.size() > 0) {
			throw new RuntimeException("当前名称或编号已经被使用,请修改后提交");
		}
		
	}
}
