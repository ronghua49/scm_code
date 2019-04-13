package com.winway.scm.persistence.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmZsjCommerceAccredit;

/**
 * 
 * <pre> 
 * 描述：商业授权销售品种 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmZsjCommerceAccreditDao extends MyBatisDao<String, ScmZsjCommerceAccredit> {
	/**
	 * 根据外键获取子表明细列表
	 * @param entrustId
	 * @return
	 */
	public List<ScmZsjCommerceAccredit> getByMainId(String entrustId);
	
	public List<ScmZsjCommerceAccredit> getScmZsjCommerceAccreditList(String entrustId);
	
	/**
	 * 根据外键删除子表记录
	 * @param entrustId
	 * @return
	 */
	public void delByMainId(String entrustId);
	
}
