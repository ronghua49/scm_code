package com.winway.scm.persistence.manager;

import com.hotent.base.manager.Manager;
import com.winway.scm.model.ScmCwDiscountPondPriceChange;

/**
 * 
 * <pre> 
 * 描述：票折池金额变动记录表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-08-17 10:03:21
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmCwDiscountPondPriceChangeManager extends Manager<String, ScmCwDiscountPondPriceChange>{

	void changePoodPrice(ScmCwDiscountPondPriceChange scmCwDiscountPondPriceChange);
	
}
