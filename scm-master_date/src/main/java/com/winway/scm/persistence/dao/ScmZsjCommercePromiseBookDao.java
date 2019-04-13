package com.winway.scm.persistence.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmZsjCommercePromiseBook;

/**
 * 
 * <pre> 
 * 描述：商业承诺书 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmZsjCommercePromiseBookDao extends MyBatisDao<String, ScmZsjCommercePromiseBook> {
	/**
	 * 根据外键获取子表明细列表
	 * @param commerceFirstId
	 * @return
	 */
	public List<ScmZsjCommercePromiseBook> getByMainId(String commerceFirstId);
	
	/**
	 * 根据外键删除子表记录
	 * @param commerceFirstId
	 * @return
	 */
	public void delByMainId(String commerceFirstId);
	
}
