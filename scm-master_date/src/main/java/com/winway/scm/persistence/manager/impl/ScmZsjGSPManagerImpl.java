package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmZsjGSPDao;
import com.winway.scm.model.ScmZsjGSP;
import com.winway.scm.persistence.manager.ScmZsjGSPManager;

/**
 * 
 * <pre> 
 * 描述：商业GSP证书信息 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmZsjGSPManager")
public class ScmZsjGSPManagerImpl extends AbstractManagerImpl<String, ScmZsjGSP> implements ScmZsjGSPManager{
	@Resource
	ScmZsjGSPDao scmZsjGSPDao;
	@Override
	protected MyBatisDao<String, ScmZsjGSP> getDao() {
		return scmZsjGSPDao;
	}
}
