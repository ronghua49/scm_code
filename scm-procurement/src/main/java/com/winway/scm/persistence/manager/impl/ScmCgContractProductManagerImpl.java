package com.winway.scm.persistence.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.model.ScmCgContractProduct;
import com.winway.scm.persistence.dao.ScmCgContractProductDao;
import com.winway.scm.persistence.manager.ScmCgContractProductManager;

/**
 * 
 * <pre> 
 * 描述：采购合同商品表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-26 13:47:44
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
@Service("scmCgContractProductManager")
public class ScmCgContractProductManagerImpl extends AbstractManagerImpl<String, ScmCgContractProduct> implements ScmCgContractProductManager{
	@Resource
	ScmCgContractProductDao scmCgContractProductDao;
	@Override
	protected MyBatisDao<String, ScmCgContractProduct> getDao() {
		return scmCgContractProductDao;
	}
	
	@Override
	public void create(ScmCgContractProduct arg0) {
		List<ScmCgContractProduct> scmCgContractProducts = scmCgContractProductDao.getByProductId(arg0.getProductId(),arg0.getContractId());
		if(scmCgContractProducts.size()!= 0 ) {
			throw new RuntimeException("商品已经存在,如需修改数量请在列表中编辑当前商品");
		}else{
			arg0.setSurplusAmount(arg0.getProductSum());
			super.create(arg0);
		}
	}
	@Override
	public void update(ScmCgContractProduct arg0) {
		arg0.setSurplusAmount(arg0.getProductSum());
		ScmCgContractProduct scmCgContractProduct = scmCgContractProductDao.get(arg0.getId());
		scmCgContractProduct.setUnitPrice(arg0.getUnitPrice());
		scmCgContractProduct.setProductSum(arg0.getProductSum());
		scmCgContractProduct.setSumPrice(arg0.getUnitPrice() * arg0.getProductSum());
		super.update(scmCgContractProduct);
	}
}
