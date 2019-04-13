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
import com.winway.scm.model.ScmCwBlend;
import com.winway.scm.model.ScmCwInvoice;
import com.winway.scm.model.ScmCwReturnMoney;
import com.winway.scm.persistence.manager.ScmCwBlendManager;
import com.winway.scm.persistence.manager.ScmCwInvoiceManager;
import com.winway.scm.persistence.manager.ScmCwReturnMoneyManager;

/**
 * 
 * <pre> 
 * 描述：勾兑记录表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-23 18:19:25
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmCwBlendManager")
public class ScmCwBlendManagerImpl extends AbstractManagerImpl<String, ScmCwBlend> implements ScmCwBlendManager{
	@Resource
	ScmCwBlendDao scmCwBlendDao;
	@Resource
	ScmCwReturnMoneyManager scmCwReturnMoneyManager;
	@Resource
	ScmCwInvoiceManager scmCwInvoiceManager;
	
	@Override
	protected MyBatisDao<String, ScmCwBlend> getDao() {
		return scmCwBlendDao;
	}
	@Override
	public String cancel(ScmCwBlend blend) {
		String msg="取消失败";
		try {
			//发票表查询以及金额更改
			ScmCwInvoice invoice = scmCwInvoiceManager.get(blend.getInvoiceId());
			//invoice.setPriceTaxSum(invoice.getPriceTaxSum()+blend.getBlendprice());
			invoice.setBalancePrice(invoice.getBalancePrice()+blend.getBlendprice());
			invoice.setVerifyType("1");
			scmCwInvoiceManager.update(invoice);
			//回款表查询以及金额更改
			ScmCwReturnMoney money = scmCwReturnMoneyManager.get(blend.getReturnMoneyId());
			money.setBalancePrice(money.getBalancePrice()+blend.getBlendprice());
			money.setVerifyType("1");
			scmCwReturnMoneyManager.update(money);
			//勾兑记录表更改
			blend.setBlendType("0");
			
			super.update(blend);
			msg="取消成功";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	@Override
	public String charge(List<ScmCwBlend> scmCwBlend, String fullname) {
		String msg="核销失败！";
		try {
			for (ScmCwBlend s : scmCwBlend) {
				//发票表查询以及金额更改
				ScmCwInvoice invoice = scmCwInvoiceManager.get(s.getInvoiceId());
				double moneys=invoice.getBalancePrice()-s.getBlendprice();
				if(moneys > 0 ){
					invoice.setBalancePrice(moneys);
					invoice.setVerifyType("1");
				}else if(moneys == 0){
					invoice.setBalancePrice(moneys);
					invoice.setVerifyType("2");
				}else{
					throw new RuntimeException("核销金额大于发票实际金额！");
				}
				scmCwInvoiceManager.update(invoice);
				//回款表查询以及金额更改
				ScmCwReturnMoney money = scmCwReturnMoneyManager.get(s.getReturnMoneyId());
				double price=money.getBalancePrice()-s.getBlendprice();
				if(price >0){
					money.setBalancePrice(price);
					money.setVerifyType("1");
				}else if(price == 0){
					money.setBalancePrice(price);
					money.setVerifyType("2");
				}else{
					throw new RuntimeException("金额不足！");
				}
				scmCwReturnMoneyManager.update(money);
				//对记录表的操作
				ScmCwBlend blend = super.get(s.getId());
				s.setBlendType("2");
				s.setBlendPersion(fullname);
				s.setBlendDate(new Date());
				if(blend == null){
					super.create(s);
				}else{
					super.update(s);
				}
			}
			msg="核销成功！";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	@Override
	public String giveCharge(String[] ids, String fullname) {
		String msg="";
		try {
			for (String id : ids) {
				ScmCwBlend blend = scmCwBlendDao.get(id);
				if(blend == null){
					continue;
				}
				blend.setBlendType("1");
				blend.setAffirmDate(new Date());
				blend.setAffirmPersion(fullname);
				scmCwBlendDao.update(blend);
			}
			msg="已经批准核销！";
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("核销批准出现错误！");
			// TODO: handle exception
		}
		
		return msg;
	}
	
}
