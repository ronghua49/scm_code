package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmZdTenderTypeDao;
import com.winway.scm.model.ScmZdTenderType;
import com.winway.scm.persistence.manager.ScmZdTenderTypeManager;

/**
 * 
 * <pre> 
 * 描述：scm_zd_tendertype 处理实现类
 * 构建组：x7
 * 作者:FangGan
 * 邮箱:libayiv@163.com
 * 日期:2019-08-20 14:47:48
 * 版权：深圳万威科技有限公司
 * </pre>
 */
@Service("scmZdTenderTypeManager")
public class ScmZdTenderTypeManagerImpl extends AbstractManagerImpl<String, ScmZdTenderType> implements ScmZdTenderTypeManager{
	@Resource
	ScmZdTenderTypeDao scmZdTenderTypeDao;
	@Override
	protected MyBatisDao<String, ScmZdTenderType> getDao() {
		return scmZdTenderTypeDao;
	}
}
