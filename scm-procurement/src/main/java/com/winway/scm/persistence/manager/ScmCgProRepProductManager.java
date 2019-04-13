package com.winway.scm.persistence.manager;

import com.hotent.base.manager.Manager;
import com.winway.scm.model.ScmCgProRepProduct;

/**
 * 
 * <pre> 
 * 描述：采购补单商品表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-26 13:47:44
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
public interface ScmCgProRepProductManager extends Manager<String, ScmCgProRepProduct>{

	void listProductByOrderId(ScmCgProRepProduct scmCgProRepProduct);

	void save(ScmCgProRepProduct scmCgProRepProduct);
	
}
