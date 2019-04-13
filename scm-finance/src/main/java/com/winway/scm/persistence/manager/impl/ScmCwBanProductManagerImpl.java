package com.winway.scm.persistence.manager.impl;


import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.feign.UCFeignService;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmCwBanProductDao;
import com.winway.scm.model.ScmCwBanProduct;
import com.winway.scm.persistence.manager.ScmCwBanProductManager;
import com.winway.scm.util.POIUtil;

/**
 * 
 * <pre> 
 * 描述：禁止票折商品表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-23 18:19:26
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmCwBanProductManager")
public class ScmCwBanProductManagerImpl extends AbstractManagerImpl<String, ScmCwBanProduct> implements ScmCwBanProductManager{
	@Resource
	ScmCwBanProductDao scmCwBanProductDao;
	
	@Override
	protected MyBatisDao<String, ScmCwBanProduct> getDao() {
		return scmCwBanProductDao;
	}
}
