package com.winway.scm.persistence.dao;
import java.util.List;

import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmXsFocusSendProduct;

/**
 * 
 * <pre> 
 * 描述：集中发货商品表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:44
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmXsFocusSendProductDao extends MyBatisDao<String, ScmXsFocusSendProduct> {
	/**
	 * 根据外键获取子表明细列表
	 * @param FocusSendId
	 * @return
	 */
	public List<ScmXsFocusSendProduct> getByMainId(String FocusSendId);
	
	/**
	 * 根据外键删除子表记录
	 * @param FocusSendId
	 * @return
	 */
	public void delByMainId(String FocusSendId);


	public List<ScmXsFocusSendProduct> getFocussendToTask(String id, String string);
	
}
