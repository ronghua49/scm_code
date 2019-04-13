package com.winway.scm.persistence.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmCwPaymentOrder;

/**
 * 
 * <pre> 
 * 描述：付款采购订单表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-23 18:19:25
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmCwPaymentOrderDao extends MyBatisDao<String, ScmCwPaymentOrder> {
	/**
	 * 根据外键获取子表明细列表
	 * @param paymentId
	 * @return
	 */
	public List<ScmCwPaymentOrder> getByMainId(String paymentId);
	
	/**
	 * 根据外键删除子表记录
	 * @param paymentId
	 * @return
	 */
	public void delByMainId(String paymentId);
	
}
