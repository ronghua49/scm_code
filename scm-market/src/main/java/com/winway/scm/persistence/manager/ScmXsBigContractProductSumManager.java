package com.winway.scm.persistence.manager;

import java.util.List;

import com.hotent.base.manager.Manager;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.winway.scm.model.ScmXsBigContractProductSum;

/**
 * 
 * <pre> 
 * 描述：scm_xs_bigcontractproductsum 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-14 18:06:05
 * 版权：美达项目组
 * </pre>
 */
public interface ScmXsBigContractProductSumManager extends Manager<String, ScmXsBigContractProductSum>{

	PageList<ScmXsBigContractProductSum> listConProductSumBySummaryID(QueryFilter queryFilter);

	List<ScmXsBigContractProductSum> listConProductSumByUpdate(String bigContractAllotId,String agreementSummaryId);
	
}
