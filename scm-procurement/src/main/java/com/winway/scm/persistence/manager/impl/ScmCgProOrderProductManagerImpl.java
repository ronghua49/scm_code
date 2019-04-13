package com.winway.scm.persistence.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.model.ScmCgContractProduct;
import com.winway.scm.model.ScmCgProOrderProduct;
import com.winway.scm.persistence.dao.ScmCgContractProductDao;
import com.winway.scm.persistence.dao.ScmCgProOrderProductDao;
import com.winway.scm.persistence.manager.ScmCgProOrderProductManager;

/**
 * 
 * <pre> 
 * 描述：采购订单商品表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-26 13:47:44
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
@Service("scmCgProOrderProductManager")
public class ScmCgProOrderProductManagerImpl extends AbstractManagerImpl<String, ScmCgProOrderProduct> implements ScmCgProOrderProductManager{
	@Resource
	ScmCgProOrderProductDao scmCgProOrderProductDao;
	@Resource
	ScmCgContractProductDao scmCgContractProductDao;
	@Override
	protected MyBatisDao<String, ScmCgProOrderProduct> getDao() {
		return scmCgProOrderProductDao;
	}
	@Override
	public void update(ScmCgProOrderProduct arg0) {
		ScmCgProOrderProduct scmCgProOrderProduct = scmCgProOrderProductDao.get(arg0.getId());
		if(scmCgProOrderProduct == null) {
			throw new RuntimeException("当前商品不存在或已经被删除");
		}
		scmCgProOrderProduct.setProductSum(arg0.getProductSum());
		scmCgProOrderProduct.setSumPrice(scmCgProOrderProduct.getProductSum() * scmCgProOrderProduct.getUnitPrice());
		super.update(scmCgProOrderProduct);
	}
	@Override
	public void remove(String entityId) {
		ScmCgProOrderProduct scmCgProOrderProduct = scmCgProOrderProductDao.get(entityId);
		ScmCgContractProduct scmCgContractProduct = scmCgContractProductDao.get(scmCgProOrderProduct.getContractProductId());
		scmCgContractProduct.setSurplusAmount(scmCgContractProduct.getSurplusAmount() + scmCgProOrderProduct.getProductSum());
		scmCgContractProductDao.update(scmCgContractProduct);
		super.remove(entityId);
	}
	@Override
	public void listProductByOrderId(ScmCgProOrderProduct scmCgProOrderProduct) {
		List<ScmCgProOrderProduct> scmCgProOrderProducts = scmCgProOrderProductDao.listProductByOrderId(scmCgProOrderProduct);
		if(scmCgProOrderProducts.size() >0) {
			throw new RuntimeException("当前订单已经存在该商品,请在列表中编辑");
		}
	}
	@Override
	public List<ScmCgProOrderProduct> getByMainId(String id) {
		List<ScmCgProOrderProduct> byMainId = scmCgProOrderProductDao.getByMainId(id);
		return byMainId;
	}
}
