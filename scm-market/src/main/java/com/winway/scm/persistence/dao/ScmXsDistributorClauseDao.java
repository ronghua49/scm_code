package com.winway.scm.persistence.dao;
import java.util.List;
import java.util.Map;

import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmXsDistributorAgreementSummary;
import com.winway.scm.model.ScmXsDistributorClause;

/**
 * 
 * <pre> 
 * 描述：分销商协议合作条款表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:43
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmXsDistributorClauseDao extends MyBatisDao<String, ScmXsDistributorClause> {
	/**
	 * 根据外键获取子表明细列表
	 * @param DistributorAgreementId
	 * @return
	 */
	public List<ScmXsDistributorClause> getByMainId(String DistributorAgreementId);
	
	/**
	 * 根据外键删除子表记录
	 * @param DistributorAgreementId
	 * @return
	 */
	public void delByMainId(String DistributorAgreementId);

	public List<ScmXsDistributorClause> firstList(Map<String, Object> params);
	
	List<ScmXsDistributorClause> commerceAndAcceptState(Map<String, Object> params);

	public List<ScmXsDistributorClause> getYears(String ownerId);

}
