package com.winway.scm.persistence.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.model.ScmXsAgreementProductDetail;
import com.winway.scm.persistence.dao.ScmXsAgreementProductDetailDao;
import com.winway.scm.persistence.manager.ScmXsAgreementProductDetailManager;
import com.winway.scm.vo.ScmXsBigContractProductByUpdateVo;

/**
 * 
 * <pre> 
 * 描述：协议品规明细 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:43
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmXsAgreementProductDetailManager")
public class ScmXsAgreementProductDetailManagerImpl extends AbstractManagerImpl<String, ScmXsAgreementProductDetail> implements ScmXsAgreementProductDetailManager{
	@Resource
	ScmXsAgreementProductDetailDao scmXsAgreementProductDetailDao;
	@Override
	protected MyBatisDao<String, ScmXsAgreementProductDetail> getDao() {
		return scmXsAgreementProductDetailDao;
	}

	@Override
	public List<ScmXsAgreementProductDetail> getAgreementProduct(String businessDivisionId, String provinceId,String ownerId,String masterId) {
		if("-1".equals(provinceId)) {
			provinceId = null;
		}
		List<ScmXsAgreementProductDetail> list = scmXsAgreementProductDetailDao.getProductDetailByOrovinceId1(businessDivisionId,provinceId,ownerId,masterId);
		return list;
	}

	@Override
	public List<ScmXsBigContractProductByUpdateVo> listAgrProductByBigUpdate(String id, String agreementSummaryId) {
		return scmXsAgreementProductDetailDao.listAgrProductByBigUpdate(id,agreementSummaryId);
	}
}
