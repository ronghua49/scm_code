package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmCgReturnProductDao;
import com.winway.scm.model.ScmCgReturnProduct;
import com.winway.scm.persistence.manager.ScmCgReturnProductManager;

/**
 * 
 * <pre> 
 * 描述：采购退货商品表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-26 13:47:44
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
@Service("scmCgReturnProductManager")
public class ScmCgReturnProductManagerImpl extends AbstractManagerImpl<String, ScmCgReturnProduct> implements ScmCgReturnProductManager{
	@Resource
	ScmCgReturnProductDao scmCgReturnProductDao;
	@Override
	protected MyBatisDao<String, ScmCgReturnProduct> getDao() {
		return scmCgReturnProductDao;
	}
}
