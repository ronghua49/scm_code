package com.winway.scm.persistence.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.query.PageBean;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.BeanUtils;
import com.hotent.base.util.UniqueIdUtil;
import com.winway.scm.persistence.dao.ScmXsBigContractProductSumDao;
import com.winway.purchase.feign.ScmMasterDateFeignService;
import com.winway.scm.model.ScmXsBigContractProduct;
import com.winway.scm.model.ScmXsBigContractProductSum;
import com.winway.scm.persistence.manager.ScmXsBigContractProductSumManager;

/**
 * 
 * <pre> 
 * 描述：scm_xs_bigcontractproductsum 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-14 18:06:05
 * 版权：美达项目组
 * </pre>
 */
@Service("scmXsBigContractProductSumManager")
public class ScmXsBigContractProductSumManagerImpl extends AbstractManagerImpl<String, ScmXsBigContractProductSum> implements ScmXsBigContractProductSumManager{
	@Resource
	ScmXsBigContractProductSumDao scmXsBigContractProductSumDao;
	@Resource
	ScmMasterDateFeignService scmMasterDateFeignService;
	@Override
	protected MyBatisDao<String, ScmXsBigContractProductSum> getDao() {
		return scmXsBigContractProductSumDao;
	}
	@Override
	public PageList<ScmXsBigContractProductSum> listConProductSumBySummaryID(QueryFilter queryFilter) {
		PageBean pageBean = queryFilter.getPageBean();
    	if(BeanUtils.isEmpty(pageBean)){
    		PageHelper.startPage(1, Integer.MAX_VALUE, false);
    	}
    	else{
    		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
    	}
    	List<ScmXsBigContractProductSum> listConProductSumBySummaryID = scmXsBigContractProductSumDao.listConProductSumBySummaryID(queryFilter.getParams());
//		for (ScmXsBigContractProductSum scmXsBigContractProductSum : listConProductSumBySummaryID) {
//			String productCode = scmXsBigContractProductSum.getProductCode();
//			if(productCode != null && productCode.length() != 0 && !"null".equals(productCode)){
//				//调用fegin获取件装量
//				List<String> listAndAchage = scmMasterDateFeignService.listAndAchage(productCode);
//				scmXsBigContractProductSum.setAchage(listAndAchage);
//			}
//		}
    	return new PageList<ScmXsBigContractProductSum>(listConProductSumBySummaryID);
	}
	@Override
	public List<ScmXsBigContractProductSum> listConProductSumByUpdate(String bigContractAllotId,String agreementSummaryId) {
		
    	List<ScmXsBigContractProductSum> listConProductSumBySummaryID = scmXsBigContractProductSumDao.listConProductSumByUpdate(bigContractAllotId,agreementSummaryId);
    	return listConProductSumBySummaryID;
	}
	
	@Override
	public void create(ScmXsBigContractProductSum entity) {
		entity.setId(UniqueIdUtil.getSuid());
		scmXsBigContractProductSumDao.create(entity);
	}

}
