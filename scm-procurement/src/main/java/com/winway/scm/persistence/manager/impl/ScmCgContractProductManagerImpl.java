package com.winway.scm.persistence.manager.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.hotent.base.exception.SystemException;
import com.hotent.base.query.*;
import com.hotent.base.util.BeanUtils;
import com.winway.purchase.feign.ScmMasterDateFeignService;
import com.winway.scm.model.ScmCgProcurementContract;
import com.winway.scm.persistence.dao.ScmCgProcurementContractDao;
import org.springframework.stereotype.Service;

import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.model.ScmCgContractProduct;
import com.winway.scm.persistence.dao.ScmCgContractProductDao;
import com.winway.scm.persistence.manager.ScmCgContractProductManager;

import net.bytebuddy.dynamic.scaffold.InstrumentedType.Frozen;

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

	@Resource
	ScmCgProcurementContractDao procurementContractDao;


	@Resource
	ScmMasterDateFeignService scmMasterDateFeignService;

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

	@Override
	public PageList<ScmCgContractProduct> query(QueryFilter queryFilter) throws SystemException {
		PageBean pageBean = queryFilter.getPageBean();
		if (BeanUtils.isEmpty(pageBean)) {
			PageHelper.startPage(1, 2147483647, false);
		} else {
			PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
		}

		String contractId = (String) queryFilter.getParams().get("contractId");
		ScmCgProcurementContract scmCgProcurementContract = procurementContractDao.get(contractId);
		if(scmCgProcurementContract==null){
			throw  new RuntimeException("数据错误，没有查询到采购合同");
		}
		String accreditProCodes = scmMasterDateFeignService.getAccreditProCodes(scmCgProcurementContract.getSupplierId());

		Set<String> set = JSON.parseObject(accreditProCodes, new TypeReference<Set<String>>() {});
		if(!set.isEmpty()){
			StringBuffer sb = new StringBuffer();
			for (String string : set) {
				sb.append(string).append(",");
			}
			String substring = sb.toString().substring(0, sb.toString().length() -1);
			queryFilter.addFilter("productCode",substring,QueryOP.IN,FieldRelation.AND);
		}
		List<ScmCgContractProduct> query = scmCgContractProductDao.query(queryFilter.getParams());
		return new PageList<>(query);
	}
}
