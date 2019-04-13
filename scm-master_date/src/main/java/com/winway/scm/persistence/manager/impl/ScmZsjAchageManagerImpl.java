package com.winway.scm.persistence.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.exception.SystemException;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.model.ScmZsjAchage;
import com.winway.scm.persistence.dao.ScmZsjAchageDao;
import com.winway.scm.persistence.manager.ScmZsjAchageManager;

/**
 * 
 * <pre> 
 * 描述：件装量 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-18 14:36:01
 * 版权：美达开发小组
 * </pre>
 */
@Service("ScmZsjAchageManager")
public class ScmZsjAchageManagerImpl extends AbstractManagerImpl<String, ScmZsjAchage> implements ScmZsjAchageManager {

	@Resource
	ScmZsjAchageDao scmZsjAchageDao;
	@Override
	protected MyBatisDao<String, ScmZsjAchage> getDao() {
		return scmZsjAchageDao;
	}
	@Override
	public void create(ScmZsjAchage scmZsjAchage){
		List<ScmZsjAchage>  scmZsjAchage1 = scmZsjAchageDao.getByachage(scmZsjAchage.getAchage(),scmZsjAchage.getProductId());
		if (scmZsjAchage1.size() == 0) {
			super.create(scmZsjAchage);
		}else{
			throw new RuntimeException("数据重复无法保存");
		}
	}

}
