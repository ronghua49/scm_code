package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmFhShipmentsControlLogDao;
import com.winway.scm.model.ScmFhShipmentsControlLog;
import com.winway.scm.persistence.manager.ScmFhShipmentsControlLogManager;

/**
 * 
 * <pre> 
 * 描述：发货控制日志记录 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-02-15 17:31:50
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmFhShipmentsControlLogManager")
public class ScmFhShipmentsControlLogManagerImpl extends AbstractManagerImpl<String, ScmFhShipmentsControlLog> implements ScmFhShipmentsControlLogManager{
	@Resource
	ScmFhShipmentsControlLogDao scmFhShipmentsControlLogDao;
	@Override
	protected MyBatisDao<String, ScmFhShipmentsControlLog> getDao() {
		return scmFhShipmentsControlLogDao;
	}
}
