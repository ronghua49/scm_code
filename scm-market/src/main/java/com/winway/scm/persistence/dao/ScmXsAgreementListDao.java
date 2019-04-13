package com.winway.scm.persistence.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.query.PageList;
import com.winway.scm.model.ScmXsAgreementList;

/**
 * 
 * <pre> 
 * 描述：经销商协议名单申请表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 15:01:16
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmXsAgreementListDao extends MyBatisDao<String, ScmXsAgreementList> {
	/**
	 * 根据外键获取子表明细列表
	 * @param masterId
	 * @return
	 */
	public List<ScmXsAgreementList> getByMainId(String masterId);
	
	/**
	 * 根据外键删除子表记录
	 * @param masterId
	 * @return
	 */
	public void delByMainId(String masterId);


	public List<ScmXsAgreementList> masterList(Map<String, Object> params);

	public List<ScmXsAgreementList> commerceAndSales(Map<String, Object> params);

	public List<ScmXsAgreementList> getYears(String org0);

	public ScmXsAgreementList getType(String org0);

	public List<ScmXsAgreementList> addAgreementlist(Map<String, Object> params);

	public List<ScmXsAgreementList> updateAgreementlist(String masterId, String ownerid, String businessDivisionId);
	
}
