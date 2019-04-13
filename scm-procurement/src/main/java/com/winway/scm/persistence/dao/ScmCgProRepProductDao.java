package com.winway.scm.persistence.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmCgProRepProduct;

/**
 * 
 * <pre> 
 * 描述：采购补单商品表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-26 13:47:44
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
public interface ScmCgProRepProductDao extends MyBatisDao<String, ScmCgProRepProduct> {
	/**
	 * 根据外键获取子表明细列表
	 * @param replacementOrderId
	 * @return
	 */
	public List<ScmCgProRepProduct> getByMainId(String ReplacementOrderId);
	
	/**
	 * 根据外键删除子表记录
	 * @param replacementOrderId
	 * @return
	 */
	public void delByMainId(String replacementOrderId);

	public Map listProductByProRepOrderId(String id);

	public List<ScmCgProRepProduct> listProductByOrderId(ScmCgProRepProduct scmCgProRepProduct);
	
}
