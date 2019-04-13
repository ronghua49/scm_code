package com.winway.scm.persistence.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmZdPromiseBookDao;
import com.winway.scm.model.ScmZdKeepingCondition;
import com.winway.scm.model.ScmZdPromiseBook;
import com.winway.scm.persistence.manager.ScmZdPromiseBookManager;

/**
 * 
 * <pre> 
 * 描述：承诺书 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-29 10:31:25
 * 版权：美达项目组
 * </pre>
 */
@Service("scmZdPromiseBookManager")
public class ScmZdPromiseBookManagerImpl extends AbstractManagerImpl<String, ScmZdPromiseBook> implements ScmZdPromiseBookManager{
	@Resource
	ScmZdPromiseBookDao scmZdPromiseBookDao;
	@Override
	protected MyBatisDao<String, ScmZdPromiseBook> getDao() {
		return scmZdPromiseBookDao;
	}
	@Override
	public void getByNameAndCodeOrId(ScmZdPromiseBook scmZdPromiseBook) {
		//根据名称与ID判断是否已经存在当前使用名称
		List<ScmZdPromiseBook> sdas = scmZdPromiseBookDao.getByNameAndCodeOrId(scmZdPromiseBook);
		if(sdas.size() > 0) {
			throw new RuntimeException("当前名称或编号已经被使用,请修改后提交");
		}
	}
}
