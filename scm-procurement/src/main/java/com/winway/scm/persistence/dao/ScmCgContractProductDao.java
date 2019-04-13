package com.winway.scm.persistence.dao;
import java.util.List;
import java.util.Map;

import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmCgContractProduct;

/**
 * 
 * <pre> 
 * 描述：采购合同商品表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-26 13:47:44
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
public interface ScmCgContractProductDao extends MyBatisDao<String, ScmCgContractProduct> {
	/**
	 * 根据外键获取子表明细列表
	 * @param entityId
	 * @return
	 */
	public List<ScmCgContractProduct> getByMainId(String entityId);
	
	/**
	 * 根据外键删除子表记录
	 * @param entityId
	 * @return
	 */
	public void delByMainId(String entityId);

	public Map listProductByContractId(String id);

	public List<ScmCgContractProduct> getByProductId(String productId, String contractId);

}
