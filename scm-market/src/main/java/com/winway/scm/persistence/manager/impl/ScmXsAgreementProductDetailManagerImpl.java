package com.winway.scm.persistence.manager.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.model.ScmCommercePlaceRate;
import com.winway.scm.model.ScmProdPrice;
import com.winway.scm.model.ScmTenderPrice;
import com.winway.scm.model.ScmXsAgreementProductDetail;
import com.winway.scm.persistence.dao.ScmCommercePlaceRateDao;
import com.winway.scm.persistence.dao.ScmProdPriceDao;
import com.winway.scm.persistence.dao.ScmTenderPriceDao;
import com.winway.scm.persistence.dao.ScmXsAgreementProductDetailDao;
import com.winway.scm.persistence.manager.ScmTenderPriceManager;
import com.winway.scm.persistence.manager.ScmXsAgreementProductDetailManager;
import com.winway.scm.vo.ScmXsBigContractProductByUpdateVo;

/**
 * 
 * <pre>
 *  
 * 描述：协议品规明细 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:43
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmXsAgreementProductDetailManager")
public class ScmXsAgreementProductDetailManagerImpl extends AbstractManagerImpl<String, ScmXsAgreementProductDetail>
		implements ScmXsAgreementProductDetailManager {
	@Resource
	ScmXsAgreementProductDetailDao scmXsAgreementProductDetailDao;
	@Resource
	ScmTenderPriceDao scmTenderPriceDao;
	@Resource
	ScmCommercePlaceRateDao scmCommercePlaceRateDao;
	@Resource
	ScmProdPriceDao scmProdPriceDao;

	@Override
	protected MyBatisDao<String, ScmXsAgreementProductDetail> getDao() {
		return scmXsAgreementProductDetailDao;
	}

	@Override
	public List<ScmXsAgreementProductDetail> getAgreementProduct(String businessDivisionId, String provinceId,
			String ownerId, String masterId) {
		if ("-1".equals(provinceId)) {
			provinceId = null;
		}
		List<ScmXsAgreementProductDetail> list = scmXsAgreementProductDetailDao
				.getProductDetailByOrovinceId1(businessDivisionId, provinceId, ownerId, masterId);
		for (ScmXsAgreementProductDetail scmXsAgreementProductDetail : list) {
			// 所有查询到的数据是否中标并返回相应数据
			List<ScmTenderPrice> scmTenderPriceBycode = scmTenderPriceDao.getScmTenderPriceByCode(provinceId,
					scmXsAgreementProductDetail.getCommerceCode());
			if (scmTenderPriceBycode.size() > 0) {
				// 中标 默认返回实施 返回实际中标价 纯销供货价 商业供货价
				scmXsAgreementProductDetail.setIsBidding("1");
				scmXsAgreementProductDetail.setIsImplement("1");
				// 中标价格获取
				for (ScmTenderPrice scmTenderPrice : scmTenderPriceBycode) {
					// 毛利获取
					ScmCommercePlaceRate scmCommerce = scmCommercePlaceRateDao.getScmCommerce(provinceId,
							scmXsAgreementProductDetail.getCommerceCode());
					Double commerceProfitRate = 0.0;
					if (scmCommerce != null) {
						commerceProfitRate = scmCommerce.getCommerceProfitRate();
					} else {
						commerceProfitRate = scmCommercePlaceRateDao.getScmCommerceAvg(provinceId);
						if (commerceProfitRate > 0.07) {
							commerceProfitRate = 0.07;
						}
					}
					Double supplyPrice = this.getSupplyPrice(Double.parseDouble(scmTenderPrice.getTenderPrice()),
							commerceProfitRate);
					scmTenderPrice.setSupplyPrice(supplyPrice);// 商业供货单价
					scmTenderPrice.setPurePinGross(commerceProfitRate);// 纯销毛利
				}
				scmXsAgreementProductDetail.setScmTenderPrices(scmTenderPriceBycode);
			} else {
				// 非中标 返回纯销供货价 本次申请价 纯销毛利率
				scmXsAgreementProductDetail.setIsBidding("0");
				scmXsAgreementProductDetail.setIsImplement("0");
				ScmProdPrice scmprodprice = null;
				scmprodprice = scmProdPriceDao.getScmprodprice(provinceId,
						scmXsAgreementProductDetail.getCommerceCode());
				if (scmprodprice == null) {
					scmprodprice = scmProdPriceDao
							.getScmprodpriceBycountry(scmXsAgreementProductDetail.getCommerceCode());
				}
				if (scmprodprice != null) {
					scmXsAgreementProductDetail.setPurePinPrice(scmprodprice.getTerminalPrice());
					scmXsAgreementProductDetail.setSupplyPrice(scmprodprice.getCommercePrice());
					Double purePinGross = this.getPurePinGross(scmprodprice.getTerminalPrice(),
							scmprodprice.getCommercePrice());
					scmXsAgreementProductDetail.setPurePinGross(purePinGross);
				}
			}
		}
		return list;
	}

	@Override
	public List<ScmXsBigContractProductByUpdateVo> listAgrProductByBigUpdate(String id, String agreementSummaryId) {
		return scmXsAgreementProductDetailDao.listAgrProductByBigUpdate(id, agreementSummaryId);
	}

	@Override
	public ScmXsAgreementProductDetail getProductDetailBycode(String businessDivisionId, String provinceId,
			String ownerId, String masterId, String productId) {
		ScmXsAgreementProductDetail scmXsAgreementProductDetail = scmXsAgreementProductDetailDao
				.getProductDetailBycode(businessDivisionId, provinceId, ownerId, masterId, productId);
		scmXsAgreementProductDetail.setIsImplement("0");
		ScmProdPrice scmprodprice = null;
		scmprodprice = scmProdPriceDao.getScmprodprice(provinceId, scmXsAgreementProductDetail.getCommerceCode());
		if (scmprodprice == null) {
			scmprodprice = scmProdPriceDao.getScmprodpriceBycountry(scmXsAgreementProductDetail.getCommerceCode());
		}
		if (scmprodprice != null) {
			scmXsAgreementProductDetail.setPurePinPrice(scmprodprice.getTerminalPrice());
			scmXsAgreementProductDetail.setSupplyPrice(scmprodprice.getCommercePrice());
			Double purePinGross = this.getPurePinGross(scmprodprice.getTerminalPrice(),
					scmprodprice.getCommercePrice());
			scmXsAgreementProductDetail.setPurePinGross(purePinGross);
		}
		return scmXsAgreementProductDetail;
	}

	@Override
	public ScmXsAgreementProductDetail getIsImplement(String id, String provinceId, String productId) {
		ScmXsAgreementProductDetail scmXsAgreementProductDetail = new ScmXsAgreementProductDetail();
		ScmTenderPrice sPrice = scmTenderPriceDao.get(id);
		scmXsAgreementProductDetail.setBiddingPrice(Double.parseDouble(sPrice.getBiddingPrice())); // 中标价
		scmXsAgreementProductDetail.setPurePinPrice(Double.parseDouble(sPrice.getBiddingPrice())); // 纯销供货价
		ScmCommercePlaceRate scmCommerce = scmCommercePlaceRateDao.getScmCommerce(provinceId, productId);
		if (scmCommerce != null) {
			Double commerceProfitRate = scmCommerce.getCommerceProfitRate();
			Double supplyPrice = this.getSupplyPrice(scmXsAgreementProductDetail.getPurePinPrice(), commerceProfitRate);
			scmXsAgreementProductDetail.setSupplyPrice(supplyPrice);// 商业供货单价
			scmXsAgreementProductDetail.setPurePinGross(commerceProfitRate);// 纯销毛利
		}
		return scmXsAgreementProductDetail;
	}

	public Double getSupplyPrice(Double purePinPrice, Double gross) {
		return (double) Math.round((purePinPrice * (1 - gross)) * 100) / 100;
	}

	public double getPurePinGross(Double purePinPrice, Double SupplyPrice) {
		double d = (purePinPrice - SupplyPrice) / purePinPrice;
		BigDecimal b = new BigDecimal(d);
		d = b.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
		return d;
	}

}
