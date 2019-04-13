package com.winway.scm.persistence.manager;

import com.hotent.base.manager.Manager;
import com.winway.scm.model.ScmXsAgreementSpecialProduct;

/**
 * 
 * <pre> 
 * 描述：经销商协议特殊商品表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:44
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmXsAgreementSpecialProductManager extends Manager<String, ScmXsAgreementSpecialProduct>{

	void delByMainId(String arg0);
	
}
