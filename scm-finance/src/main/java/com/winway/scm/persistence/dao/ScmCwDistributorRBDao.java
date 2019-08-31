package com.winway.scm.persistence.dao;
import java.util.List;

import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmCwDistributorRB;
import com.winway.scm.vo.ScmCwDistributorAgreementVo;

/**
 * 
 * <pre> 
 * 描述：分销商RB申请主表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-14 16:38:31
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmCwDistributorRBDao extends MyBatisDao<String, ScmCwDistributorRB> {

	List<ScmCwDistributorAgreementVo> ListDistributorAgreement(String ownerId, String year, String businessDivisionId,
			String provinceId);

	ScmCwDistributorRB getByApprovalId(String approvalId);

	List<ScmCwDistributorRB> listByYear(String businessDivisionId, String provinceId, String year);
}
