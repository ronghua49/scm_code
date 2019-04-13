package com.winway.scm.persistence.manager;

import com.hotent.base.manager.Manager;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.winway.scm.model.ScmXsBigContractProduct;

/**
 * 
 * <pre> 
 * 描述：大合同商品表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:44
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmXsBigContractProductManager extends Manager<String, ScmXsBigContractProduct>{

	PageList<ScmXsBigContractProduct> groupByList(QueryFilter queryFilter);

	PageList<ScmXsBigContractProduct> bigcontractProductList(QueryFilter queryFilter);

	PageList<ScmXsBigContractProduct> groupList(QueryFilter queryFilter);
	
}
