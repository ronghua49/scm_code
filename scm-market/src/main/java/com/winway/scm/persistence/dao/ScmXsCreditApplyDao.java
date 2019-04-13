package com.winway.scm.persistence.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmXsCreditApply;

/**
 * 
 * <pre> 
 * 描述：商业资信申请表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:43
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmXsCreditApplyDao extends MyBatisDao<String, ScmXsCreditApply> {
	/**
	 * 根据外键获取子表明细列表
	 * @param CreditCollectId
	 * @return
	 */
	public List<ScmXsCreditApply> getByMainId(String CreditCollectId);
	
	/**
	 * 根据外键删除子表记录
	 * @param CreditCollectId
	 * @return
	 */
	public void delByMainId(String CreditCollectId);

	public List<ScmXsCreditApply> firstList(Map<String, Object> params);

	public List<ScmXsCreditApply> getYears(String arg0);

	public ScmXsCreditApply selectLastTimePrice(String commerceId, String ownerId);
	
}
