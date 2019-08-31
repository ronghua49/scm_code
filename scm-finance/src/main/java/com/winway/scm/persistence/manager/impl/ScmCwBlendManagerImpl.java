package com.winway.scm.persistence.manager.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.feign.UCFeignService;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmCwBlendDao;
import com.winway.scm.persistence.dao.ScmCwInvoiceDao;
import com.winway.scm.persistence.dao.ScmCwReturnMoneyDao;
import com.winway.scm.model.ScmCwBlend;
import com.winway.scm.model.ScmCwInvoice;
import com.winway.scm.model.ScmCwReturnMoney;
import com.winway.scm.persistence.manager.ScmCwBlendManager;
import com.winway.scm.persistence.manager.ScmCwInvoiceManager;
import com.winway.scm.persistence.manager.ScmCwReturnMoneyManager;

/**
 * 
 * <pre>
 *  
 * 描述：勾兑记录表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-23 18:19:25
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmCwBlendManager")
public class ScmCwBlendManagerImpl extends AbstractManagerImpl<String, ScmCwBlend> implements ScmCwBlendManager {
	@Resource
	ScmCwBlendDao scmCwBlendDao;
	@Resource
	ScmCwReturnMoneyManager scmCwReturnMoneyManager;
	@Resource
	ScmCwInvoiceManager scmCwInvoiceManager;
	@Resource
	ScmCwReturnMoneyDao scmCwReturnMoneyDao;
	@Resource
	ScmCwInvoiceDao scmCwInvoiceDao;

	@Override
	protected MyBatisDao<String, ScmCwBlend> getDao() {
		return scmCwBlendDao;
	}

	@Override
	public String cancel(String id) {
		ScmCwReturnMoney scmCwReturnMoney = scmCwReturnMoneyManager.get(id);
		if (scmCwReturnMoney == null) {
			throw new RuntimeException("未查询到回款数据");
		}
		// 查询该回款的勾兑记录
		List<ScmCwBlend> scmCwBlends = scmCwBlendDao.listByReturnMoneyId(scmCwReturnMoney.getId());
		for (ScmCwBlend scmCwBlend : scmCwBlends) {
			if (!"2".equals(scmCwBlend.getBlendType())) {
				continue;
			}
			String invoiceId = scmCwBlend.getInvoiceId();
			ScmCwInvoice scmCwInvoice = scmCwInvoiceManager.get(invoiceId);
			// 添加发票金额及发票状态
			scmCwInvoice.setBalancePrice(scmCwInvoice.getBalancePrice() + scmCwBlend.getBlendprice());
			scmCwInvoice.setVerifyType("1");
			// 添加回款金额及回款状态
			scmCwReturnMoney.setBalancePrice(scmCwReturnMoney.getBalancePrice() + scmCwBlend.getBlendprice());
			scmCwReturnMoney.setVerifyType("1");
			// 处理勾兑记录状态
			scmCwBlend.setBlendType("0");
			scmCwInvoiceDao.update(scmCwInvoice);
			scmCwBlendDao.update(scmCwBlend);
		}
		scmCwReturnMoneyDao.update(scmCwReturnMoney);
		return "勾兑成功";
	}

	@Override
	public String charge(List<ScmCwBlend> scmCwBlend, String fullname) {
		try {
			ScmCwReturnMoney money = null;
			if (scmCwBlend.get(0) != null) {
				if (scmCwBlend.get(0).getReturnMoneyId() != null) {
					money = scmCwReturnMoneyManager.get(scmCwBlend.get(0).getReturnMoneyId());
				}
			}
			if (money == null) {
				throw new RuntimeException("汇款数据未查询到");
			}
			if (money.getVerifyType().equals(2)) {
				throw new RuntimeException("当前回款数据已核销");
			}
			double priceSum = 0.0;
			for (ScmCwBlend scmCwBlend2 : scmCwBlend) {
				priceSum += scmCwBlend2.getBlendprice();
			}
			if (money.getBalancePrice() < priceSum) {
				throw new RuntimeException("勾兑金额大于回款金额,请检查数据后提交");
			}
			// 目前情况 回款金额肯定是大于等于勾兑金额的
			for (ScmCwBlend s : scmCwBlend) {
				// 发票表查询以及金额更改
				ScmCwInvoice invoice = scmCwInvoiceManager.get(s.getInvoiceId());
				if (s.getBlendprice() > invoice.getBalancePrice()) {
					throw new RuntimeException("发票" + invoice.getInvoiceCode() + "勾兑金额大于发票剩余金额");
				}
				if ("2".equals(invoice.getVerifyType())) {
					throw new RuntimeException("发票" + invoice.getInvoiceCode() + "已经勾兑完成,请勿重复提交");
				}
				// 设置发票金额
				double d1 = invoice.getBalancePrice() - s.getBlendprice();
				invoice.setBalancePrice(d1);
				// 设置回款金额
				double d2 = money.getBalancePrice() - s.getBlendprice();
				money.setBalancePrice(d2);
				if (d1 > 0) {
					// 核销中
					invoice.setVerifyType("1");
				} else if (d1 == 0) {
					// 核销完成
					invoice.setVerifyType("2");
				}
				scmCwInvoiceManager.update(invoice);
				// 回款表查询以及金额更改
				if (d2 > 0) {
					// 核销中
					money.setVerifyType("1");
				} else if (d2 == 0) {
					// 核销完成
					money.setVerifyType("2");
				}
				// 对记录表的操作
				ScmCwBlend blend = super.get(s.getId());
				s.setBlendType("2");
				s.setBlendPersion(fullname);
				s.setBlendDate(new Date());
				if (blend == null) {
					super.create(s);
				} else {
					super.update(s);
				}
			}
			scmCwReturnMoneyManager.update(money);
		} catch (Exception e) {
			throw new RuntimeException("核销失败");
		}
		return "核销成功！";
	}

	@Override
	public String giveCharge(String id, String fullname) {
		ScmCwReturnMoney scmCwReturnMoney = scmCwReturnMoneyDao.get(id);
		if(scmCwReturnMoney == null) {
			throw new RuntimeException("未查询到回款数据");
		}
		List<ScmCwBlend> scmCwBlends = scmCwBlendDao.listByReturnMoneyId(scmCwReturnMoney.getId());
		for (ScmCwBlend blend : scmCwBlends) {
			if (blend == null) {
				continue;
			}
			if(!"2".equals(blend.getBlendType())) {
				continue;
			}
			blend.setBlendType("1");
			blend.setAffirmDate(new Date());
			blend.setAffirmPersion(fullname);
			scmCwBlendDao.update(blend);
		}
		return "已经批准核销！";
	}
}
