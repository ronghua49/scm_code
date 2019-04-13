package com.winway.scm.persistence.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmCgProOrderProduct;

/**
 * 
 * <pre> 
 * 描述：采购订单商品表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-26 13:47:44
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
public interface ScmCgProOrderProductDao extends MyBatisDao<String, ScmCgProOrderProduct> {
	/**
	 * 根据外键获取子表明细列表
	 * @param proOrderId
	 * @return
	 */
	public List<ScmCgProOrderProduct> getByMainId(String proOrderId);
	
	/**
	 * 根据外键删除子表记录
	 * @param proOrderId
	 * @return
	 */
	public void delByMainId(String proOrderId);
	/**
	 * 统计商品总数
	 * @param proOrderId
	 * @return
	 */
	public Map listProductByProOrderId(String proOrderId);

	public List<ScmCgProOrderProduct> listProductByOrderId(ScmCgProOrderProduct scmCgProOrderProduct);
	
}
