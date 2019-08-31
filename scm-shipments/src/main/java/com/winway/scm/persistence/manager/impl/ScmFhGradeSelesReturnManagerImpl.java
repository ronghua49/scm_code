package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmFhGradeSelesReturnDao;
import com.winway.scm.model.ScmFhGradeSelesReturn;
import com.winway.scm.persistence.manager.ScmFhGradeSelesReturnManager;

/**
 * 
 * <pre> 
 * 描述：当次退货 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-13 15:47:33
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmFhGradeSelesReturnManager")
public class ScmFhGradeSelesReturnManagerImpl extends AbstractManagerImpl<String, ScmFhGradeSelesReturn> implements ScmFhGradeSelesReturnManager{
	@Resource
	ScmFhGradeSelesReturnDao scmFhGradeSelesReturnDao;
	@Override
	protected MyBatisDao<String, ScmFhGradeSelesReturn> getDao() {
		return scmFhGradeSelesReturnDao;
	}
}
