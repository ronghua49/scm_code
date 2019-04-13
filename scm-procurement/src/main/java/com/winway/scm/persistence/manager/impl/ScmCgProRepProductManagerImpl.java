package com.winway.scm.persistence.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.model.CommonResult;
import com.hotent.base.util.StringUtil;
import com.winway.scm.model.ScmCgContractProduct;
import com.winway.scm.model.ScmCgProRepProduct;
import com.winway.scm.model.ScmCgProReplacementOrder;
import com.winway.scm.persistence.dao.ScmCgContractProductDao;
import com.winway.scm.persistence.dao.ScmCgProRepProductDao;
import com.winway.scm.persistence.dao.ScmCgProReplacementOrderDao;
import com.winway.scm.persistence.manager.ScmCgContractProductManager;
import com.winway.scm.persistence.manager.ScmCgProRepProductManager;
import com.winway.scm.persistence.manager.ScmCgProReplacementOrderManager;

/**
 * 
 * <pre> 
 * 描述：采购补单商品表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-26 13:47:44
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
@Service("scmCgProRepProductManager")
public class ScmCgProRepProductManagerImpl extends AbstractManagerImpl<String, ScmCgProRepProduct> implements ScmCgProRepProductManager{
	@Resource
	ScmCgProRepProductDao scmCgProRepProductDao;
	
	@Resource
	ScmCgContractProductDao scmCgContractProductDao;
	@Resource
	ScmCgProReplacementOrderDao scmCgProReplacementOrderDao;
	@Resource
	ScmCgContractProductManager scmCgContractProductManager;
	@Resource
	ScmCgProReplacementOrderManager scmCgProReplacementOrderManager;
	@Resource
	ScmCgProRepProductManager scmCgProRepProductManager;
	
	@Override
	protected MyBatisDao<String, ScmCgProRepProduct> getDao() {
		return scmCgProRepProductDao;
	}
	@Override
	public void update(ScmCgProRepProduct arg0) {
		ScmCgProRepProduct scmCgProRepProduct = scmCgProRepProductDao.get(arg0.getId());
		if(scmCgProRepProduct == null) {
			throw new RuntimeException("当前商品不存在或已经被删除");
		}
		scmCgProRepProduct.setProductSum(arg0.getProductSum());
		scmCgProRepProduct.setSumPrice(arg0.getProductSum() * scmCgProRepProduct.getUnitPrice());
		super.update(scmCgProRepProduct);
	}
	
	@Override
	public void remove(String entityId) {
		ScmCgProRepProduct scmCgProRepProduct = scmCgProRepProductDao.get(entityId);
		ScmCgContractProduct scmCgContractProduct = scmCgContractProductDao.get(scmCgProRepProduct.getContractProductId());
		scmCgContractProduct.setSurplusAmount(scmCgContractProduct.getSurplusAmount() + scmCgProRepProduct.getProductSum());
		scmCgContractProductDao.update(scmCgContractProduct);
		super.remove(entityId);
	}
	@Override
	public void listProductByOrderId(ScmCgProRepProduct scmCgProRepProduct) {
		List<ScmCgProRepProduct> scmCgProRepProducts = scmCgProRepProductDao.listProductByOrderId(scmCgProRepProduct);
		if(scmCgProRepProducts.size() >0) {
			throw new RuntimeException("当前订单已经存在该商品,请在列表中编辑");
		}
	}
	@Override
	public void save(ScmCgProRepProduct scmCgProRepProduct) {
		if(StringUtil.isNotEmpty(scmCgProRepProduct.getReplacementOrderId())) {
			ScmCgProReplacementOrder ScmCgProReplacementOrder = scmCgProReplacementOrderDao.get(scmCgProRepProduct.getReplacementOrderId());
			if(ScmCgProReplacementOrder == null) {
				throw new RuntimeException("当前补单不存在或已经被删除");
			}else{
				if("1".equals(ScmCgProReplacementOrder.getApprovalState()) || "2".equals(ScmCgProReplacementOrder.getApprovalState())) {
					throw new RuntimeException("当前补单已提交审批或已通过审批,不可修改数据");
				}
			}
			ScmCgContractProduct scmCgContractProduct = scmCgContractProductManager.get(scmCgProRepProduct.getContractProductId());
			if(StringUtil.isEmpty(scmCgProRepProduct.getId())){
				//验证是否存在
				scmCgProRepProductManager.listProductByOrderId(scmCgProRepProduct);
				//验证数量
				if(scmCgContractProduct.getSurplusAmount() - scmCgProRepProduct.getProductSum() >= 0) {
					create(scmCgProRepProduct);
					scmCgContractProduct.setSurplusAmount(scmCgContractProduct.getSurplusAmount() - scmCgProRepProduct.getProductSum());
					scmCgContractProductDao.update(scmCgContractProduct);
				}else{
					throw new RuntimeException("数量已超出余量,请调整");
				}
			}else{
				//验证数量
				ScmCgProRepProduct scmCgProRepProduct2 = scmCgProRepProductManager.get(scmCgProRepProduct.getId());
				if(scmCgContractProduct.getSurplusAmount()+scmCgProRepProduct2.getProductSum()- scmCgProRepProduct.getProductSum() >= 0) {
					scmCgProRepProductManager.update(scmCgProRepProduct);
					scmCgContractProduct.setSurplusAmount(scmCgContractProduct.getSurplusAmount()+scmCgProRepProduct2.getProductSum() - scmCgProRepProduct.getProductSum());
					scmCgContractProductDao.update(scmCgContractProduct);
				}else{
					throw new RuntimeException("数量已超出余量,请调整");
				}
			}
			scmCgProReplacementOrderManager.update(ScmCgProReplacementOrder);
		}else{
			throw new RuntimeException("补单ID不存在");
		}
		
	}
	
}
