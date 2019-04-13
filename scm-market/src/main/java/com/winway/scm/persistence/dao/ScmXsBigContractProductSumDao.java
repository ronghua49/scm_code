package com.winway.scm.persistence.dao;
import java.util.List;
import java.util.Map;

import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmXsBigContractProductSum;

/**
 * 
 * <pre> 
 * 描述：scm_xs_bigcontractproductsum DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-14 18:06:05
 * 版权：美达项目组
 * </pre>
 */
public interface ScmXsBigContractProductSumDao extends MyBatisDao<String, ScmXsBigContractProductSum> {

	List<ScmXsBigContractProductSum> getByOwnerIdAndcommerId(String arg0, String arg1, String agreementSummaryId);
	
	List<ScmXsBigContractProductSum> listConProductSumBySummaryID(Map<String, Object> params);

	ScmXsBigContractProductSum getByProductCode(String arg2);

	List<ScmXsBigContractProductSum> listByFhFocusShipmentsSet(String commerceId, String ownerId, String agreementSummaryId, List lendReco);

	List<ScmXsBigContractProductSum> listConProductSumByUpdate(String bigContractAllotId, String agreementSummaryId);

}
