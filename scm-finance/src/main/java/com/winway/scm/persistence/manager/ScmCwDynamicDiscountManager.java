package com.winway.scm.persistence.manager;

import com.hotent.base.manager.Manager;
import com.winway.scm.model.ScmCwDynamicDiscount;

import java.util.Map;

/**
 * 
 * <pre> 
 * 描述：动态票折规则 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-23 18:19:26
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmCwDynamicDiscountManager extends Manager<String, ScmCwDynamicDiscount>{

    //返回商业购买货主的所有商品可票折的数量和百分比 ownerId: commerceId: productIds:[]
    Map<String,Object> getDiscountDetail(Map<String,Object> purchaseDetails);
	
}
