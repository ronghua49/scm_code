package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmXsDealerPayStyleDao;
import com.winway.scm.model.ScmXsDealerPayStyle;
import com.winway.scm.persistence.manager.ScmXsDealerPayStyleManager;

import java.util.List;

/**
 * 
 * <pre> 
 * 描述：经销商协议付款方式 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-07-01 15:24:12
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmXsDealerPayStyleManager")
public class ScmXsDealerPayStyleManagerImpl extends AbstractManagerImpl<String, ScmXsDealerPayStyle> implements ScmXsDealerPayStyleManager{
	@Resource
	ScmXsDealerPayStyleDao scmXsDealerPayStyleDao;
	@Override
	protected MyBatisDao<String, ScmXsDealerPayStyle> getDao() {
		return scmXsDealerPayStyleDao;
	}

	@Override
	public List<ScmXsDealerPayStyle> getByMainId(String id) {
		return scmXsDealerPayStyleDao.getByMainId(id);
	}
}
