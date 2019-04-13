package com.winway.scm.persistence.manager;

import java.util.List;

import com.hotent.base.manager.Manager;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.winway.scm.model.ScmXsAgreementProductDetail;
import com.winway.scm.vo.ScmXsBigContractProductByUpdateVo;

/**
 * 
 * <pre> 
 * 描述：协议品规明细 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:43
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmXsAgreementProductDetailManager extends Manager<String, ScmXsAgreementProductDetail>{


	List<ScmXsAgreementProductDetail> getAgreementProduct(String businessDivisionId, String provinceId,
			String ownerId,String masterId);

	List<ScmXsBigContractProductByUpdateVo> listAgrProductByBigUpdate(String id,
			String agreementSummaryId);

	
}
