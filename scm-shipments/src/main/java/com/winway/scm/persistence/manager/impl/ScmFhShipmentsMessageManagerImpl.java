package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import com.hotent.base.util.UniqueIdUtil;
import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmFhShipmentsMessageDao;
import com.winway.scm.model.ScmFhShipmentsMessage;
import com.winway.scm.persistence.manager.ScmFhShipmentsMessageManager;

/**
 * 
 * <pre> 
 * 描述：物流发运信息表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-02-15 17:31:50
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmFhShipmentsMessageManager")
public class ScmFhShipmentsMessageManagerImpl extends AbstractManagerImpl<String, ScmFhShipmentsMessage> implements ScmFhShipmentsMessageManager{
	@Resource
	ScmFhShipmentsMessageDao scmFhShipmentsMessageDao;
	@Override
	protected MyBatisDao<String, ScmFhShipmentsMessage> getDao() {
		return scmFhShipmentsMessageDao;
	}

	@Override
	public void create(ScmFhShipmentsMessage entity) {
		entity.setId(UniqueIdUtil.getSuid());
		scmFhShipmentsMessageDao.create(entity);
	}
}
