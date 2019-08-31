package com.winway.scm.persistence.manager.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.model.CommonResult;
import com.hotent.base.util.UniqueIdUtil;
import com.winway.purchase.feign.ScmMasterDateFeignService;
import com.winway.purchase.util.QuarterUtil;
import com.winway.scm.model.ScmCwBanDiscount;
import com.winway.scm.model.ScmCwDiscountPond;
import com.winway.scm.model.ScmCwDiscountPondPriceChange;
import com.winway.scm.model.ScmCwDiscountRecord;
import com.winway.scm.model.ScmCwDiscountRuleRecord;
import com.winway.scm.model.ScmCwDynamicDiscount;
import com.winway.scm.model.ScmCwDynamicDiscountType;
import com.winway.scm.model.ScmCwToSapDiscount;
import com.winway.scm.persistence.dao.ScmCwBanDiscountDao;
import com.winway.scm.persistence.dao.ScmCwDiscountPondDao;
import com.winway.scm.persistence.dao.ScmCwDiscountRuleRecordDao;
import com.winway.scm.persistence.dao.ScmCwDynamicDiscountDao;
import com.winway.scm.persistence.manager.ScmCwDiscountPondManager;
import com.winway.scm.persistence.manager.ScmCwDiscountRecordManager;
import com.winway.scm.persistence.manager.ScmCwDiscountRuleRecordManager;
import com.winway.scm.persistence.manager.ScmCwDynamicDiscountManager;
import com.winway.scm.persistence.manager.ScmCwToSapDiscountManager;
import com.winway.scm.vo.SO_DETIAL;
import com.winway.scm.vo.SO_HEADER;
import com.winway.scm.vo.ScmCwCompensationVo;
import com.winway.scm.vo.ScmCwInvoiceVo;
import com.winway.scm.vo.ScmFhShipmentsDatail;
import com.winway.scm.vo.ScmFhShipmentsDatailMaster;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * <pre> 
 * 描述：票折池表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-23 18:19:25
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmCwDiscountPondManager")
public class ScmCwDiscountPondManagerImpl extends AbstractManagerImpl<String, ScmCwDiscountPond> implements ScmCwDiscountPondManager{
	@Resource
	ScmCwDiscountPondDao scmCwDiscountPondDao;
	@Resource
	ScmMasterDateFeignService scmMasterDateFeignService;
	@Resource
	ScmCwBanDiscountDao scmCwBanDiscountDao;
	@Resource
	ScmCwDynamicDiscountManager scmCwDynamicDiscountManager;
	@Resource
	ScmCwDiscountRuleRecordDao scmCwDiscountRuleRecordDao;
	@Resource
	ScmCwDiscountRecordManager scmCwDiscountRecordManager;
	@Resource
	ScmCwToSapDiscountManager scmCwToSapDiscountManager;
	@Resource
	ScmCwDiscountRuleRecordManager scmCwDiscountRuleRecordManager;
	@Override
	protected MyBatisDao<String, ScmCwDiscountPond> getDao() {
		return scmCwDiscountPondDao;
	}
	@Override
	public String freezeManage(String id) {
		ScmCwDiscountPond scmCwDiscountPond = scmCwDiscountPondDao.get(id);
		if(scmCwDiscountPond == null) {
			throw new RuntimeException("未查询到票折数据");
		}
		String freeze = "";
		if("0".equals(scmCwDiscountPond.getIsFreeze())) {
			freeze = "1";
			scmCwDiscountPond.setIsFreeze("1");
		}else if("1".equals(scmCwDiscountPond.getIsFreeze())) {
			freeze = "0";
			scmCwDiscountPond.setIsFreeze("0");
		}else{
			freeze = "0";
			scmCwDiscountPond.setIsFreeze("0");
		}
		scmCwDiscountPondDao.update(scmCwDiscountPond);
		return freeze;
	}
	
	@Override
	public ScmCwToSapDiscount autoDiscount(String id, String discountTypeId) {
		String shipmentsDatailMasterById = scmMasterDateFeignService.getShipmentsDatailMasterById(id);
		if("".equals(shipmentsDatailMasterById)) {
			throw new RuntimeException("未获取到发货数据");
		}
		ScmFhShipmentsDatailMaster parseObject = JSON.parseObject(shipmentsDatailMasterById,ScmFhShipmentsDatailMaster.class);
		if(parseObject == null) {
			throw new RuntimeException("未获取到发货数据");
		}
		SO_HEADER autoDiscount1 = autoDiscount1(id,discountTypeId,parseObject);
		if(autoDiscount1 == null) {
			throw new RuntimeException("票折数据异常,请联系管理员验证");
		}
		String jsonString = JSON.toJSONString(autoDiscount1);
		//封装确认发货数据,并保存
		ScmCwToSapDiscount scmCwToSapDiscount = new ScmCwToSapDiscount();
		scmCwToSapDiscount.setShipmentsdatailmasterId(parseObject.getId());
		scmCwToSapDiscount.setShipmentsCode(parseObject.getShipmentsCode());
		scmCwToSapDiscount.setDiscountJson(jsonString);
		scmCwToSapDiscount.setIsToSap("0");
		scmCwToSapDiscount.setIsAffirmToSap("0");
		scmCwToSapDiscountManager.create(scmCwToSapDiscount);
		//调整发货单的开票状态,已开票设置为开票中
		CommonResult<String> updateShipmentDatailCanInvoice = scmMasterDateFeignService.updateShipmentDatailCanInvoice(id,"3");
		if("10000".equals(updateShipmentDatailCanInvoice.getCode())) {
			return scmCwToSapDiscount;
		}else{
			throw new RuntimeException(updateShipmentDatailCanInvoice.getMessage());
		}
	}
	
	//处理票折,返回自动票折的开票数据
	public SO_HEADER autoDiscount1(String id,String discountTypeId, ScmFhShipmentsDatailMaster parseObject) {
		//每个map表示一个商品  如果这个商品有折扣,map中的list就是两条数据
		SO_HEADER so_header = new SO_HEADER();
		List<SO_DETIAL> so_detial = so_header.getSO_DETIAL();
		//取到发货单
		String commerceId = parseObject.getCommerceId();
		String businessDivisionId = parseObject.getBusinessDivisionId();
		String provinceId = parseObject.getProvinceId();
		String ownerId = parseObject.getOwnerId();
		String getcommerceFirstById = scmMasterDateFeignService.getcommerceFirstById(commerceId);
		JSONObject parseObject2 = JSON.parseObject(getcommerceFirstById);
		String commerceCode = parseObject2.getString("commerceCode");
		if(commerceCode == null || "".equals(commerceCode)) {
			throw new RuntimeException("当前商业数据异常,无法获取商业编号,请确认商业首营信息");
		}
		so_header.setPURCH_NO_C(parseObject.getShipmentsCode());
		so_header.setPARTN_NUMB(commerceCode);
		so_header.setORD_REASON("Z04");
		so_header.setHEAD_TXT("无");
		so_header.setPDATE(new Date());
		List<ScmFhShipmentsDatail> scmFhShipmentsDatailList = parseObject.getScmFhShipmentsDatailList();
		//查询选中类型是否存在票折数据
		List<ScmCwDiscountPond> scmCwDiscountPondList = scmCwDiscountPondDao.listCommerceIdByAnddiscountTypeId1(commerceCode,discountTypeId,ownerId);
		if(scmCwDiscountPondList.size() == 0) {
			//不存在票折数据,直接生成发票数据
			makeInvoice(scmFhShipmentsDatailList,so_detial);
			return so_header;
		}
		//判断商务大区,省区,商业是否禁止票折
		ScmCwBanDiscount scmCwBanDiscount1 = scmCwBanDiscountDao.getByBusinessDivisionId(businessDivisionId,ownerId);
		if(scmCwBanDiscount1 != null) {
			//当前商务大区禁止票折,直接进行开票处理,拿到发票数据,直接返回
			makeInvoice(scmFhShipmentsDatailList,so_detial);
			return so_header;
		}else{
			ScmCwBanDiscount scmCwBanDiscount2 = scmCwBanDiscountDao.getByProvinceId(provinceId,ownerId);
			if(scmCwBanDiscount2 != null) {
				//当前省区禁止票折,直接进行开票处理,拿到发票数据,直接返回
				makeInvoice(scmFhShipmentsDatailList,so_detial);
				return so_header;
			}else{
				//判断商业是否可以票折
				ScmCwBanDiscount scmCwBanDiscount3 = scmCwBanDiscountDao.getByCommerceId(commerceCode,ownerId);
				if(scmCwBanDiscount3 != null) {
					//当前商业禁止票折,直接进行开票处理,拿到发票数据,直接返回
					makeInvoice(scmFhShipmentsDatailList,so_detial);
					return so_header;
				}
			}
		}
		//当前发货单的商业可以进行票折
		//根据票折类型取到票折池数据
		//遍历发货单商品
		for (ScmFhShipmentsDatail scmFhShipmentsDatail : scmFhShipmentsDatailList) {
			if("3".equals(scmFhShipmentsDatail.getTaskType())) {
				//当次退货
//				makeInvoice(scmFhShipmentsDatail,so_detial);
				continue;
			}
			if("4".equals(scmFhShipmentsDatail.getTaskType())) {
				//商业退货
//				makeInvoice(scmFhShipmentsDatail,so_detial);
				continue;
			}
			//验证商品可否票折情况
			String productCode = scmFhShipmentsDatail.getProductNum();
			//验证是否禁止票折
			ScmCwBanDiscount scmCwBanDiscount4 = scmCwBanDiscountDao.getByProductCodeAndBusinessDivisionId(productCode,businessDivisionId,ownerId);
			if(scmCwBanDiscount4 != null) {
				//查询到禁止票折商品,直接添加至发票列表
				makeInvoice(scmFhShipmentsDatail,so_detial);
				continue;
			}else{
				ScmCwBanDiscount scmCwBanDiscount5 = scmCwBanDiscountDao.getByProductCodeAndProvinceId(productCode,provinceId,ownerId);
				if(scmCwBanDiscount5 != null) {
					//查询到禁止票折商品,直接添加至发票列表
					makeInvoice(scmFhShipmentsDatail,so_detial);
					continue;
				}else{
					ScmCwBanDiscount scmCwBanDiscount6 = scmCwBanDiscountDao.getByProductCodeAndCommerceId(productCode,commerceCode,ownerId);
					if(scmCwBanDiscount6 != null) {
						//查询到禁止票折商品,直接添加至发票列表
						makeInvoice(scmFhShipmentsDatail,so_detial);
						continue;
					}else{
						ScmCwBanDiscount scmCwBanDiscount7 = scmCwBanDiscountDao.getByProductCode(productCode,ownerId);
						if(scmCwBanDiscount7 != null) {
							//查询到禁止票折商品,直接添加至发票列表
							makeInvoice(scmFhShipmentsDatail,so_detial);
							continue;
						}
					}
				}
			}
			//商品已经确认可以进行票折,查询该商品是否有动态票折规则
			//首先查询商品是否全量动态票折
			ScmCwDynamicDiscount scmCwDynamicDiscount1 = scmCwDynamicDiscountManager.getByProductCode(productCode,ownerId);
			if(scmCwDynamicDiscount1 != null) {
				//查询到商品全量动态票折数据
				//获取到该商品可以票折的类型
				List<ScmCwDynamicDiscountType> scmCwDynamicDiscountTypeList = scmCwDynamicDiscount1.getScmCwDynamicDiscountTypeList();
				boolean b = false;
				for (ScmCwDynamicDiscountType scmCwDynamicDiscountType : scmCwDynamicDiscountTypeList) {
					if(discountTypeId.equals(scmCwDynamicDiscountType.getCode())) {
						b = true;
					}
				}
				if(b) {
					//该商品动态票折规则中的类型包含选中类型,可以进行票折
					dynamicVerifyDiscount(scmCwDynamicDiscount1,scmFhShipmentsDatail,so_detial,scmCwDiscountPondList);
				}else{
					//当前商品不能被选中的类型进行票折,不进行折扣直接加入发票列表
					makeInvoice(scmFhShipmentsDatail,so_detial);
				}
			}else{
				//查询商品是否归于商业动态票折
				ScmCwDynamicDiscount scmCwDynamicDiscount2 = scmCwDynamicDiscountManager.getByProductCodeAndCommerceId(productCode,commerceCode,ownerId);
				if(scmCwDynamicDiscount2 != null) {
					//查询到当前发货单商业下该商品有动态票折规则,根据动态票折规则生成发票
					//获取到该商品可以票折的类型
					boolean b = false;
					List<ScmCwDynamicDiscountType> scmCwDynamicDiscountTypeList = scmCwDynamicDiscount2.getScmCwDynamicDiscountTypeList();
					for (ScmCwDynamicDiscountType scmCwDynamicDiscountType : scmCwDynamicDiscountTypeList) {
						if(discountTypeId.equals(scmCwDynamicDiscountType.getCode())) {
							b = true;
						}
					}
					if(b) {
						//该商品动态票折规则中的类型包含选中类型,可以进行票折
						dynamicVerifyDiscount(scmCwDynamicDiscount2,scmFhShipmentsDatail,so_detial,scmCwDiscountPondList);
					}else{
						//当前商品不能被选中的类型进行票折,不进行折扣直接加入发票列表
						makeInvoice(scmFhShipmentsDatail,so_detial);
					}
				}else{
					//没有查询到商品全量动态票折与商业下商品动态票折数据,按正常票折执行
					ticketDiscount(scmFhShipmentsDatail,so_detial,scmCwDiscountPondList);
				}
			}
		}
		return so_header;
	}
	
	//商品在动态票折中已经设置,走该方法
	public void dynamicVerifyDiscount(ScmCwDynamicDiscount scmCwDynamicDiscount,ScmFhShipmentsDatail scmFhShipmentsDatail,List<SO_DETIAL> so_detial, List<ScmCwDiscountPond> scmCwDiscountPondList){
		//该商品动态票折票折类型与选中类型相同,需要根据动态票折规则进行票折
		//开始时间
		Date deductionDateStart = scmCwDynamicDiscount.getDeductionDateStart();
		//结束时间
		Date deductionDateStop = scmCwDynamicDiscount.getDeductionDateStop();
		//可以折扣数量
		Integer productSum = scmCwDynamicDiscount.getProductSum();
		//规则使用结束后是否正常票折（0：正常票折，1：禁止票折）
		String state = scmCwDynamicDiscount.getState();
		Map<String,Object> productCountMap = scmCwDiscountRuleRecordDao.getProductCountByDiscountId(scmCwDynamicDiscount.getId(),scmCwDynamicDiscount.getProductId(),scmFhShipmentsDatail.getCommerceId());
		//已折扣数量
		int parseInt = 0;
		Object object = productCountMap.get("discountNum");
		if(object != null) {
			parseInt = Integer.parseInt(object.toString());
		}
		//剩余折扣数量
		int residue = 0;
		if(productSum != null) {
			residue = productSum - parseInt;	
		}
		//默认今天日期在设置日期外
		boolean effectiveDate = false;
		if(deductionDateStart != null  || deductionDateStop != null) {
			effectiveDate = QuarterUtil.isEffectiveDate(new Date(), deductionDateStart, deductionDateStop);
			if(effectiveDate){
				//票折日期控制在设置日期内
				if(productSum != null) {
					//有数量控制,查询已使用数量
					if(residue > 0) {
						//可折数量小于已折数量可以进行折扣
						if(scmFhShipmentsDatail.getDeliveringAmount() > residue) {
							//数量超过可票折数量,进行区分票折,计算可按规则票折数量,并根据票折结束后规则进行剩余的票折
							ticketDiscount(residue,scmFhShipmentsDatail,scmCwDynamicDiscount,so_detial,scmCwDiscountPondList);
						}else{
							//发货数量没有超过可票折数量,进行票折并记录减少数量
							//发货数据,商品列表,票折百分比
							ticketDiscount(scmFhShipmentsDatail,scmCwDynamicDiscount,so_detial,scmCwDiscountPondList);
						}
						
					}else{
						//不可以折扣,判断当前规则票折完成后是禁止票折还是正常票折
						if("0".equals(state)) {
							//正常票折
							ticketDiscount(scmFhShipmentsDatail,so_detial,scmCwDiscountPondList);
						}else{
							//禁止票折
							makeInvoice(scmFhShipmentsDatail,so_detial);
						}
					}
				}else{
					//没有数量控制直接按照票折比例进行票折
					ticketDiscount(scmFhShipmentsDatail,scmCwDynamicDiscount,so_detial,scmCwDiscountPondList);
				}
			}else{
				//票折日期控制在设置日期外
				if("0".equals(state)) {
					//票折结束后走正常票折
					ticketDiscount(scmFhShipmentsDatail,so_detial,scmCwDiscountPondList);
				}else{
					//票折结束后禁止票折
					makeInvoice(scmFhShipmentsDatail,so_detial);
				}
			}
		}else{
			//票折没有加日期验证
			if(productSum != null) {
				//有数量控制,查询已使用数量
				if(scmFhShipmentsDatail.getDeliveringAmount() > residue) {
					//数量超过可票折数量,进行区分票折,计算可按规则票折数量,并根据票折结束后规则进行剩余的票折
					ticketDiscount(residue,scmFhShipmentsDatail,scmCwDynamicDiscount,so_detial,scmCwDiscountPondList);
				}else{
					//发货数量没有超过可票折数量,进行票折并记录减少数量
					//发货数据,商品列表,票折百分比
					ticketDiscount(scmFhShipmentsDatail,scmCwDynamicDiscount,so_detial,scmCwDiscountPondList);
				}
			}else{
				//没有数量控制,根据票折百分比进行票折
				ticketDiscount(scmFhShipmentsDatail,scmCwDynamicDiscount,so_detial,scmCwDiscountPondList);
			}
		}
	}
	
	private void ticketDiscount(ScmFhShipmentsDatail scmFhShipmentsDatail,List<SO_DETIAL> so_detial, List<ScmCwDiscountPond> scmCwDiscountPondList){
		Double mayDeductionPercent = 50d;
		//发货价格
		Double priceSum = scmFhShipmentsDatail.getPriceSum();
		//可票折金额
		double discountPrice = priceSum * (mayDeductionPercent/100);
		for (ScmCwDiscountPond scmCwDiscountPond : scmCwDiscountPondList) {
			if(discountPrice == 0) {
				break;
			}
			if(discountPrice > scmCwDiscountPond.getBalance()) {
				//商品可票折金额大于票折池数据余额,进行扣减
				Double balance = scmCwDiscountPond.getBalance();
				scmCwDiscountPond.setBalance(0.0);
				scmCwDiscountPond.setYetDeduction(scmCwDiscountPond.getYetDeduction() + balance);
				scmCwDiscountPond.setDiscountType("2");
				ScmCwDiscountRecord ScmCwDiscountRecord = new ScmCwDiscountRecord();
				ScmCwDiscountRecord.setDiscountId(scmCwDiscountPond.getId());
				ScmCwDiscountRecord.setDiscountCode(scmCwDiscountPond.getDiscountCode());
				ScmCwDiscountRecord.setDiscountDate(new Date());
				ScmCwDiscountRecord.setInvoiceCode(scmFhShipmentsDatail.getShipmentsCode());
				ScmCwDiscountRecord.setInvoiceId(scmFhShipmentsDatail.getId());
				ScmCwDiscountRecord.setDiscountPrice(balance);
				ScmCwDiscountRecord.setCommerceName(scmFhShipmentsDatail.getCommerceName());
				discountPrice = discountPrice - balance;
				scmCwDiscountRecordManager.create(ScmCwDiscountRecord);
				scmCwDiscountPondDao.update(scmCwDiscountPond);
			}else{
				//商品可票折金额小于票折池数据,进行票折
				Double balance = scmCwDiscountPond.getBalance();
				double poodBalance = balance - discountPrice;
				scmCwDiscountPond.setBalance(balance - discountPrice);
				scmCwDiscountPond.setYetDeduction(scmCwDiscountPond.getYetDeduction() + poodBalance);
				if(poodBalance == 0) {
					scmCwDiscountPond.setDiscountType("2");
				}else{
					scmCwDiscountPond.setDiscountType("1");
				}
				ScmCwDiscountRecord scmCwDiscountRecord = new ScmCwDiscountRecord();
				scmCwDiscountRecord.setDiscountId(scmCwDiscountPond.getId());
				scmCwDiscountRecord.setDiscountCode(scmCwDiscountPond.getDiscountCode());
				scmCwDiscountRecord.setDiscountDate(new Date());
				scmCwDiscountRecord.setInvoiceCode(scmFhShipmentsDatail.getShipmentsCode());
				scmCwDiscountRecord.setInvoiceId(scmFhShipmentsDatail.getId());
				scmCwDiscountRecord.setDiscountPrice(discountPrice);
				scmCwDiscountRecord.setCommerceName(scmFhShipmentsDatail.getCommerceName());
				scmCwDiscountRecordManager.create(scmCwDiscountRecord);
				scmCwDiscountPondDao.update(scmCwDiscountPond);
				discountPrice = 0;
			}
		}
		//折扣金额
		double d = priceSum * (mayDeductionPercent/100) - discountPrice;
		BigDecimal bd = new BigDecimal(d);
		d = bd.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		String discountGenre = scmCwDiscountPondList.get(0).getDiscountGenre();
		//票折数据已经使用完
		//票折池数据不足以根据票折比例完成票折
		SO_DETIAL SO_DETIAL = new SO_DETIAL();
		SO_DETIAL.setMATERIAL(scmFhShipmentsDatail.getCode());
		SO_DETIAL.setBATCH(scmFhShipmentsDatail.getBatchNumber());
		SO_DETIAL.setSTORE_LOC(scmFhShipmentsDatail.getHoustWareCode());
		SO_DETIAL.setREQ_QTY(scmFhShipmentsDatail.getDeliveringAmount());
		BigDecimal bd3 = new BigDecimal(scmFhShipmentsDatail.getPrice());
		SO_DETIAL.setPRICE(bd3.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
		SO_DETIAL.setPRODUCTCODE(scmFhShipmentsDatail.getProductNum());
		SO_DETIAL.setPRODUCTNAME(scmFhShipmentsDatail.getProductName());
		if("BXL".equals(discountGenre)) {
			//补差
			SO_DETIAL.setAMT1(0.0d);
			SO_DETIAL.setAMT2(0.0d);
			SO_DETIAL.setAMT3(d);
		}else if("FLL".equals(discountGenre)) {
			//返利
			SO_DETIAL.setAMT1(0.0d);
			SO_DETIAL.setAMT2(d);
			SO_DETIAL.setAMT3(0.0d);
		}else{
			//促销
			SO_DETIAL.setAMT1(d);
			SO_DETIAL.setAMT2(0.0d);
			SO_DETIAL.setAMT3(0.0d);

		}
		SO_DETIAL.setTXT1(scmFhShipmentsDatail.getDirectiveCode());
		SO_DETIAL.setTXT2(scmFhShipmentsDatail.getDateCode());
		so_detial.add(SO_DETIAL);
	}
	
	//动态规则中商品数量不足时的自动票折
	private void ticketDiscount(int residue,ScmFhShipmentsDatail scmFhShipmentsDatail, ScmCwDynamicDiscount scmCwDynamicDiscount,List<SO_DETIAL> so_detial, List<ScmCwDiscountPond> scmCwDiscountPondList){
		//进行剩余数量的票折
		Integer deliveringAmount = scmFhShipmentsDatail.getDeliveringAmount();
		Double price = scmFhShipmentsDatail.getPrice();
		//票折规则剩余商品的商品总价,用来计算票折金额
		double zkprice = residue * price;
		Double mayDeductionPercent = scmCwDynamicDiscount.getMayDeductionPercent();
		if(mayDeductionPercent == null) {
			//如果票折比例为null,则使用默认百分之50的票折率
			mayDeductionPercent = 50d;
		}
		//票折规则剩余商品的票折金额
		double discountPrice = zkprice * (mayDeductionPercent/100);
		//遍历票折池,进行金额扣减
		for (ScmCwDiscountPond scmCwDiscountPond : scmCwDiscountPondList) {
			if(discountPrice == 0) {
				break;
			}
			if("2".equals(scmCwDiscountPond.getDiscountType())) {
				continue;
			}
			if(discountPrice > scmCwDiscountPond.getBalance()) {
				//商品可票折金额大于票折池数据余额,进行扣减
				Double balance = scmCwDiscountPond.getBalance();
				scmCwDiscountPond.setBalance(0.0);
				scmCwDiscountPond.setDiscountType("2");
				ScmCwDiscountRecord ScmCwDiscountRecord = new ScmCwDiscountRecord();
				ScmCwDiscountRecord.setDiscountId(scmCwDiscountPond.getId());
				ScmCwDiscountRecord.setDiscountCode(scmCwDiscountPond.getDiscountCode());
				ScmCwDiscountRecord.setDiscountDate(new Date());
				ScmCwDiscountRecord.setInvoiceCode(scmFhShipmentsDatail.getShipmentsCode());
				ScmCwDiscountRecord.setInvoiceId(scmFhShipmentsDatail.getId());
				ScmCwDiscountRecord.setDiscountPrice(balance);
				ScmCwDiscountRecord.setCommerceName(scmFhShipmentsDatail.getCommerceName());
				discountPrice = discountPrice - balance;
				scmCwDiscountRecordManager.create(ScmCwDiscountRecord);
				scmCwDiscountPondDao.update(scmCwDiscountPond);
			}else{
				//商品可票折金额小于票折池数据,进行票折
				Double balance = scmCwDiscountPond.getBalance();
				double poodBalance = balance - discountPrice;
				scmCwDiscountPond.setBalance(balance - discountPrice);
				if(poodBalance == 0) {
					scmCwDiscountPond.setDiscountType("2");
				}else{
					scmCwDiscountPond.setDiscountType("1");
				}
				ScmCwDiscountRecord scmCwDiscountRecord = new ScmCwDiscountRecord();
				scmCwDiscountRecord.setDiscountId(scmCwDiscountPond.getId());
				scmCwDiscountRecord.setDiscountCode(scmCwDiscountPond.getDiscountCode());
				scmCwDiscountRecord.setDiscountDate(new Date());
				scmCwDiscountRecord.setInvoiceCode(scmFhShipmentsDatail.getShipmentsCode());
				scmCwDiscountRecord.setInvoiceId(scmFhShipmentsDatail.getId());
				scmCwDiscountRecord.setDiscountPrice(discountPrice);
				scmCwDiscountRecord.setCommerceName(scmFhShipmentsDatail.getCommerceName());
				scmCwDiscountRecordManager.create(scmCwDiscountRecord);
				scmCwDiscountPondDao.update(scmCwDiscountPond);
				discountPrice = 0;
			}
		}		
		//票折已经完成,扣减数量(插入票折规则记录表数据)
		ScmCwDiscountRuleRecord scmCwDiscountRuleRecord = new ScmCwDiscountRuleRecord();
		scmCwDiscountRuleRecord.setCommerceId(scmFhShipmentsDatail.getCommerceId());
		scmCwDiscountRuleRecord.setProductId(scmFhShipmentsDatail.getProductNum());
		scmCwDiscountRuleRecord.setDiscountid(scmCwDynamicDiscount.getId());
		scmCwDiscountRuleRecord.setDiscountNum(residue);
		scmCwDiscountRuleRecordManager.create(scmCwDiscountRuleRecord); 
		//票折类型
		String discountGenre = scmCwDiscountPondList.get(0).getDiscountGenre();
		//已经计算取到票折金额(票折池计算已经结束,可以继续计算剩余的票折规则)
		double d = zkprice * (mayDeductionPercent/100) - discountPrice;
		BigDecimal bd = new BigDecimal(d);
		d = bd.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		//票折规则剩余数量已经使用完,进行数量使用完成后的票折
		//发货指令中在使用完票折规则的数量后的剩余数量
		int sy = deliveringAmount - residue;
		//验证票折使用结束后的票折规则
		if("0".equals(scmCwDynamicDiscount.getState())) {
			//商品数量被使用完后进行正常数量票折
			//计算剩余商品数量的票折金额,进行票折,票折结束后,两次金额相加,进行数据封装
			//剩余商品的总价
			double d1 = sy * price;
			//可以票折的金额
			double d2 = d1 * 0.5;
			for (ScmCwDiscountPond scmCwDiscountPond : scmCwDiscountPondList) {
				if(d2 == 0) {
					break;
				}
				if("2".equals(scmCwDiscountPond.getDiscountType())) {
					continue;
				}
				if(d2 > scmCwDiscountPond.getBalance()) {
					//商品可票折金额大于票折池数据余额,进行扣减
					Double balance = scmCwDiscountPond.getBalance();
					scmCwDiscountPond.setBalance(0.0);
					scmCwDiscountPond.setDiscountType("2");
					ScmCwDiscountRecord ScmCwDiscountRecord = new ScmCwDiscountRecord();
					ScmCwDiscountRecord.setDiscountId(scmCwDiscountPond.getId());
					ScmCwDiscountRecord.setDiscountCode(scmCwDiscountPond.getDiscountCode());
					ScmCwDiscountRecord.setDiscountDate(new Date());
					ScmCwDiscountRecord.setInvoiceCode(scmFhShipmentsDatail.getShipmentsCode());
					ScmCwDiscountRecord.setInvoiceId(scmFhShipmentsDatail.getId());
					ScmCwDiscountRecord.setDiscountPrice(balance);
					ScmCwDiscountRecord.setCommerceName(scmFhShipmentsDatail.getCommerceName());
					d2 = d2 - balance;
					scmCwDiscountRecordManager.create(ScmCwDiscountRecord);
					scmCwDiscountPondDao.update(scmCwDiscountPond);
				}else{
					//商品可票折金额小于票折池数据,进行票折
					Double balance = scmCwDiscountPond.getBalance();
					//票折后票折池剩余的金额
					double poodBalance = balance - d2;
					scmCwDiscountPond.setBalance(balance - d2);
					if(poodBalance == 0) {
						scmCwDiscountPond.setDiscountType("2");
					}else{
						scmCwDiscountPond.setDiscountType("1");
					}
					ScmCwDiscountRecord scmCwDiscountRecord = new ScmCwDiscountRecord();
					scmCwDiscountRecord.setDiscountId(scmCwDiscountPond.getId());
					scmCwDiscountRecord.setDiscountCode(scmCwDiscountPond.getDiscountCode());
					scmCwDiscountRecord.setDiscountDate(new Date());
					scmCwDiscountRecord.setInvoiceCode(scmFhShipmentsDatail.getShipmentsCode());
					scmCwDiscountRecord.setInvoiceId(scmFhShipmentsDatail.getId());
					scmCwDiscountRecord.setDiscountPrice(discountPrice);
					scmCwDiscountRecord.setCommerceName(scmFhShipmentsDatail.getCommerceName());
					scmCwDiscountRecordManager.create(scmCwDiscountRecord);
					scmCwDiscountPondDao.update(scmCwDiscountPond);
					d2 = 0;
				}
			}		
			//计算两次折扣的折扣金额
			double d3 = d1 * 0.5 - d2;
			//两次票折总金额
			double d4 = d3 + d;
			BigDecimal bd1 = new BigDecimal(d4);
			d4 = bd1.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
			//合并两次票折的折扣金额,封装数据
			SO_DETIAL SO_DETIAL = new SO_DETIAL();
			SO_DETIAL.setMATERIAL(scmFhShipmentsDatail.getCode());
			SO_DETIAL.setBATCH(scmFhShipmentsDatail.getBatchNumber());
			SO_DETIAL.setSTORE_LOC(scmFhShipmentsDatail.getHoustWareCode());
			SO_DETIAL.setREQ_QTY(scmFhShipmentsDatail.getDeliveringAmount());
			BigDecimal bd3 = new BigDecimal(scmFhShipmentsDatail.getPrice());
			SO_DETIAL.setPRICE(bd3.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
			SO_DETIAL.setPRODUCTCODE(scmFhShipmentsDatail.getProductNum());
			SO_DETIAL.setPRODUCTNAME(scmFhShipmentsDatail.getProductName());
			if("BXL".equals(discountGenre)) {
				//补差
				SO_DETIAL.setAMT1(0.0d);
				SO_DETIAL.setAMT2(0.0d);
				SO_DETIAL.setAMT3(d4);

			}else if("FLL".equals(discountGenre)) {
				//返利
				SO_DETIAL.setAMT1(0.0d);
				SO_DETIAL.setAMT2(d4);
				SO_DETIAL.setAMT3(0.0d);
			}else{
				//促销
				SO_DETIAL.setAMT1(d4);
				SO_DETIAL.setAMT2(0.0d);
				SO_DETIAL.setAMT3(0.0d);
			}
			SO_DETIAL.setTXT1(scmFhShipmentsDatail.getDirectiveCode());
			SO_DETIAL.setTXT2(scmFhShipmentsDatail.getDateCode());
			so_detial.add(SO_DETIAL);
		}else{ 
			//商品数量使用完后禁止票折
			//剩余商品不可以进行票折,票折结束,封装数据
			SO_DETIAL SO_DETIAL = new SO_DETIAL();
			SO_DETIAL.setMATERIAL(scmFhShipmentsDatail.getCode());
			SO_DETIAL.setBATCH(scmFhShipmentsDatail.getBatchNumber());
			SO_DETIAL.setSTORE_LOC(scmFhShipmentsDatail.getHoustWareCode());
			SO_DETIAL.setREQ_QTY(scmFhShipmentsDatail.getDeliveringAmount());
			BigDecimal bd1 = new BigDecimal(scmFhShipmentsDatail.getPrice());
			SO_DETIAL.setPRICE(bd1.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
			SO_DETIAL.setPRODUCTCODE(scmFhShipmentsDatail.getProductNum());
			SO_DETIAL.setPRODUCTNAME(scmFhShipmentsDatail.getProductName());
			if("BXL".equals(discountGenre)) {
				//补差
				SO_DETIAL.setAMT1(0.0d);
				SO_DETIAL.setAMT2(0.0d);
				SO_DETIAL.setAMT3(d);
			}else if("FLL".equals(discountGenre)) {
				//返利
				SO_DETIAL.setAMT1(0.0d);
				SO_DETIAL.setAMT2(d);
				SO_DETIAL.setAMT3(0.0d);
			}else{
				//促销
				SO_DETIAL.setAMT1(d);
				SO_DETIAL.setAMT2(0.0d);
				SO_DETIAL.setAMT3(0.0d);

			}
			SO_DETIAL.setTXT1(scmFhShipmentsDatail.getDirectiveCode());
			SO_DETIAL.setTXT2(scmFhShipmentsDatail.getDateCode());
			so_detial.add(SO_DETIAL);
		}
	}
	//根据票折规则进行自动票折1(票折规则中数量足够,不考虑数量票折)
	private void ticketDiscount(ScmFhShipmentsDatail scmFhShipmentsDatail, ScmCwDynamicDiscount scmCwDynamicDiscount,List<SO_DETIAL> so_detial, List<ScmCwDiscountPond> scmCwDiscountPondList) {
		Double mayDeductionPercent = scmCwDynamicDiscount.getMayDeductionPercent();
		if(mayDeductionPercent == null) {
			//如果票折比例为null,则使用默认百分之50的票折率
			mayDeductionPercent = 50d;
		}
		//发货价格
		Double priceSum = scmFhShipmentsDatail.getPriceSum();
		//可票折金额
		double discountPrice = priceSum * (mayDeductionPercent/100);
		for (ScmCwDiscountPond scmCwDiscountPond : scmCwDiscountPondList) {
			if(discountPrice == 0) {
				break;
			}
			if(discountPrice > scmCwDiscountPond.getBalance()) {
				//商品可票折金额大于票折池数据余额,进行扣减
				Double balance = scmCwDiscountPond.getBalance();
				scmCwDiscountPond.setBalance(0.0);
				scmCwDiscountPond.setDiscountType("2");
				ScmCwDiscountRecord ScmCwDiscountRecord = new ScmCwDiscountRecord();
				ScmCwDiscountRecord.setDiscountId(scmCwDiscountPond.getId());
				ScmCwDiscountRecord.setDiscountCode(scmCwDiscountPond.getDiscountCode());
				ScmCwDiscountRecord.setDiscountDate(new Date());
				ScmCwDiscountRecord.setInvoiceCode(scmFhShipmentsDatail.getShipmentsCode());
				ScmCwDiscountRecord.setInvoiceId(scmFhShipmentsDatail.getId());
				ScmCwDiscountRecord.setDiscountPrice(balance);
				ScmCwDiscountRecord.setCommerceName(scmFhShipmentsDatail.getCommerceName());
				discountPrice = discountPrice - balance;
				scmCwDiscountRecordManager.create(ScmCwDiscountRecord);
				scmCwDiscountPondDao.update(scmCwDiscountPond);
			}else{
				//商品可票折金额小于票折池数据,进行票折
				Double balance = scmCwDiscountPond.getBalance();
				double poodBalance = balance - discountPrice;
				scmCwDiscountPond.setBalance(balance - discountPrice);
				if(poodBalance == 0) {
					scmCwDiscountPond.setDiscountType("2");
				}else{
					scmCwDiscountPond.setDiscountType("1");
				}
				ScmCwDiscountRecord scmCwDiscountRecord = new ScmCwDiscountRecord();
				scmCwDiscountRecord.setDiscountId(scmCwDiscountPond.getId());
				scmCwDiscountRecord.setDiscountCode(scmCwDiscountPond.getDiscountCode());
				scmCwDiscountRecord.setDiscountDate(new Date());
				scmCwDiscountRecord.setInvoiceCode(scmFhShipmentsDatail.getShipmentsCode());
				scmCwDiscountRecord.setInvoiceId(scmFhShipmentsDatail.getId());
				scmCwDiscountRecord.setDiscountPrice(discountPrice);
				scmCwDiscountRecord.setCommerceName(scmFhShipmentsDatail.getCommerceName());
				scmCwDiscountRecordManager.create(scmCwDiscountRecord);
				scmCwDiscountPondDao.update(scmCwDiscountPond);
				discountPrice = 0;
			}
			
		}
		
		//折扣金额
		double d = priceSum * (mayDeductionPercent/100) - discountPrice;
		BigDecimal bd = new BigDecimal(d);
		d = bd.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		String discountGenre = scmCwDiscountPondList.get(0).getDiscountGenre();
		//票折数据已经使用完
		//票折池数据不足以根据票折比例完成票折
		SO_DETIAL SO_DETIAL = new SO_DETIAL();
		SO_DETIAL.setMATERIAL(scmFhShipmentsDatail.getCode());
		SO_DETIAL.setBATCH(scmFhShipmentsDatail.getBatchNumber());
		SO_DETIAL.setSTORE_LOC(scmFhShipmentsDatail.getHoustWareCode());
		SO_DETIAL.setREQ_QTY(scmFhShipmentsDatail.getDeliveringAmount());
		BigDecimal bd1 = new BigDecimal(scmFhShipmentsDatail.getPrice());
		SO_DETIAL.setPRICE(bd1.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
		SO_DETIAL.setPRODUCTCODE(scmFhShipmentsDatail.getProductNum());
		SO_DETIAL.setPRODUCTNAME(scmFhShipmentsDatail.getProductName());
		if("BXL".equals(discountGenre)) {
			//补差类
			SO_DETIAL.setAMT1(0.0d);
			SO_DETIAL.setAMT2(0.0d);
			SO_DETIAL.setAMT3(d);
		}else if("FLL".equals(discountGenre)) {
			//返利
			SO_DETIAL.setAMT1(0.0d);
			SO_DETIAL.setAMT2(d);
			SO_DETIAL.setAMT3(0.0d);
		}else{
			//促销
			SO_DETIAL.setAMT1(d);
			SO_DETIAL.setAMT2(0.0d);
			SO_DETIAL.setAMT3(0.0d);

		}
		SO_DETIAL.setTXT1(scmFhShipmentsDatail.getDirectiveCode());
		so_detial.add(SO_DETIAL);
		//票折已经完成,扣减数量(插入票折规则记录表数据)
		Integer deliveringAmount = scmFhShipmentsDatail.getDeliveringAmount();
		ScmCwDiscountRuleRecord scmCwDiscountRuleRecord = new ScmCwDiscountRuleRecord();
		scmCwDiscountRuleRecord.setCommerceId(scmFhShipmentsDatail.getCommerceId());
		scmCwDiscountRuleRecord.setProductId(scmFhShipmentsDatail.getProductNum());
		scmCwDiscountRuleRecord.setDiscountid(scmCwDynamicDiscount.getId());
		scmCwDiscountRuleRecord.setDiscountNum(deliveringAmount);
		scmCwDiscountRuleRecordManager.create(scmCwDiscountRuleRecord); 
	}
	//数组商品添加
	public void makeInvoice(List<ScmFhShipmentsDatail> scmFhShipmentsDatailList, List<SO_DETIAL> so_detial){
		for (ScmFhShipmentsDatail scmFhShipmentsDatail : scmFhShipmentsDatailList) {
			if("3".equals(scmFhShipmentsDatail.getTaskType())) {
				//当次退货
				continue;
			}
			if("4".equals(scmFhShipmentsDatail.getTaskType())) {
				//商业退货
				continue;
			}
			SO_DETIAL SO_DETIAL = new SO_DETIAL();
			SO_DETIAL.setMATERIAL(scmFhShipmentsDatail.getCode());
			SO_DETIAL.setBATCH(scmFhShipmentsDatail.getBatchNumber());
			SO_DETIAL.setSTORE_LOC(scmFhShipmentsDatail.getHoustWareCode());
			SO_DETIAL.setREQ_QTY(scmFhShipmentsDatail.getDeliveringAmount());
			BigDecimal bd1 = new BigDecimal(scmFhShipmentsDatail.getPrice());
			SO_DETIAL.setPRICE(bd1.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
			SO_DETIAL.setPRODUCTCODE(scmFhShipmentsDatail.getProductNum());
			SO_DETIAL.setPRODUCTNAME(scmFhShipmentsDatail.getProductName());
			SO_DETIAL.setAMT1(0.0d);
			SO_DETIAL.setAMT2(0.0d);
			SO_DETIAL.setAMT3(0.0d);
			SO_DETIAL.setTXT1(scmFhShipmentsDatail.getDirectiveCode());
			SO_DETIAL.setTXT2(scmFhShipmentsDatail.getDateCode());
			so_detial.add(SO_DETIAL);
		}
	}
	//单个商品添加
	public void makeInvoice(ScmFhShipmentsDatail scmFhShipmentsDatail, List<SO_DETIAL> so_detial){
			SO_DETIAL SO_DETIAL = new SO_DETIAL();
			SO_DETIAL.setMATERIAL(scmFhShipmentsDatail.getCode());
			SO_DETIAL.setBATCH(scmFhShipmentsDatail.getBatchNumber());
			SO_DETIAL.setSTORE_LOC(scmFhShipmentsDatail.getHoustWareCode());
			SO_DETIAL.setREQ_QTY(scmFhShipmentsDatail.getDeliveringAmount());
			BigDecimal bd1 = new BigDecimal(scmFhShipmentsDatail.getPrice());
			SO_DETIAL.setPRICE(bd1.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
			SO_DETIAL.setPRODUCTCODE(scmFhShipmentsDatail.getProductNum());
			SO_DETIAL.setPRODUCTNAME(scmFhShipmentsDatail.getProductName());
			SO_DETIAL.setAMT1(0.0d);
			SO_DETIAL.setAMT2(0.0d);
			SO_DETIAL.setAMT3(0.0d);
			SO_DETIAL.setTXT1(scmFhShipmentsDatail.getDirectiveCode());
			SO_DETIAL.setTXT2(scmFhShipmentsDatail.getDateCode());
			so_detial.add(SO_DETIAL);
	}
	
	
	//查询到商品禁止票折时调用
	public void addInvoice(List<Map<String, List<ScmCwInvoiceVo>>> list,ScmFhShipmentsDatail scmFhShipmentsDatail){
		//查询到禁止票折商品,直接添加至发票列表
		Map<String,List<ScmCwInvoiceVo>> map = new HashMap<String,List<ScmCwInvoiceVo>>();
		ScmCwInvoiceVo scmCwInvoiceVo = new ScmCwInvoiceVo();
		List<ScmCwInvoiceVo> list1 = new ArrayList<ScmCwInvoiceVo>();
		list1.add(scmCwInvoiceVo);
		map.put(scmFhShipmentsDatail.getDirectiveCode(), list1);
		list.add(map);
	}
	
	//发货明细遍历完成后,把发票数据格式调整
	public List<List<ScmCwInvoiceVo>> disposeInvoice(List<Map<String,List<ScmCwInvoiceVo>>> list){
		List<List<ScmCwInvoiceVo>> list1 = new ArrayList<List<ScmCwInvoiceVo>>();
		List<ScmCwInvoiceVo> list2 = new ArrayList<ScmCwInvoiceVo>();
		for (Map<String, List<ScmCwInvoiceVo>> map : list) {
			Set<String> keySet = map.keySet();
			for (String string : keySet) {
				List<ScmCwInvoiceVo> list3 = map.get(string);
				if(list3.size() == 1) {
					//没有折扣的发票
					if(list2.size() < 8){
						//放进去
						list2.add(list3.get(0));
					}else{
						//重新创建一个
						List<ScmCwInvoiceVo> list4 = new ArrayList<ScmCwInvoiceVo>();
						Collections.copy(list2,list4);
						list1.add(list4);
						list2 = new ArrayList<ScmCwInvoiceVo>();
						//把发票数据放进去
						list2.add(list3.get(0));
					}
				}else{
					//有折扣的发票
					if(list2.size() < 7){
						//放进去
						list2.add(list3.get(0));
						list2.add(list3.get(1));
					}else{
						//重新创建一个
						List<ScmCwInvoiceVo> list4 = new ArrayList<ScmCwInvoiceVo>();
						Collections.copy(list2,list4);
						list1.add(list4);
						list2 = new ArrayList<ScmCwInvoiceVo>();
						//把发票数据放进去
						list2.add(list3.get(0));
						list2.add(list3.get(1));
					}
				}
			}
		}
		list1.add(list2);
		return list1;
	}
	@Override
	public List<ScmCwCompensationVo> saveByCompensation(List<ScmCwCompensationVo> scmCwCompensationVo) {
		List<ScmCwCompensationVo> list = new ArrayList();
		for (ScmCwCompensationVo scmCwCompensationVo2 : scmCwCompensationVo) {
			String commerceCode = scmCwCompensationVo2.getCommerceCode();
			String commerceJson = scmMasterDateFeignService.getcommerceByCode(commerceCode);
			JSONObject parseObject = JSON.parseObject(commerceJson);
			String businessDivisionId = parseObject.getString("businessDivisionId");
			String businessDivision = parseObject.getString("businessDivision");
			String provinceId = parseObject.getString("provinceId");
			String province = parseObject.getString("province");
			String commerceName = parseObject.getString("commerceName");
			String commerceLevel = parseObject.getString("commerceLevel");
			ScmCwDiscountPond scmCwDiscountPond = new ScmCwDiscountPond();
			scmCwDiscountPond.setId(UniqueIdUtil.getSuid());
			scmCwDiscountPond.setDiscountCode(QuarterUtil.getCode("BCPZ"));
			scmCwDiscountPond.setDiscountDate(null);
			scmCwDiscountPond.setDiscountSource("5");
			scmCwDiscountPond.setBusinessDivisionId(businessDivisionId);
			scmCwDiscountPond.setBusinessDivision(businessDivision);
			scmCwDiscountPond.setProvince(province);
			scmCwDiscountPond.setProvinceId(provinceId);
			scmCwDiscountPond.setCommerceCode(commerceCode);
			scmCwDiscountPond.setCommerceName(commerceName);
			scmCwDiscountPond.setCommerceLevel(commerceLevel);
			scmCwDiscountPond.setTreminal(scmCwCompensationVo2.getTreminal());
			scmCwDiscountPond.setMayDeduction(scmCwCompensationVo2.getMayDeduction());
			scmCwDiscountPond.setIsFreeze("0");
			scmCwDiscountPond.setIsDeduction(scmCwCompensationVo2.getIsDeduction());
			scmCwDiscountPond.setDeductionPrice(scmCwCompensationVo2.getDeductionPrice());
			scmCwDiscountPond.setYetDeduction(0.00);
			scmCwDiscountPond.setBalance(scmCwCompensationVo2.getDeductionPrice());
			scmCwDiscountPond.setDiscountCommerce(null);
			scmCwDiscountPond.setDiscountType("0");
			scmCwDiscountPond.setOwnerId(scmCwCompensationVo2.getOwnerId());
			scmCwDiscountPond.setDiscountGenre(scmCwCompensationVo2.getDiscountGenre());
			scmCwDiscountPond.setBu(scmCwCompensationVo2.getBu());
			scmCwDiscountPond.setProductName(scmCwCompensationVo2.getProductName());
			scmCwDiscountPondDao.create(scmCwDiscountPond);
			
			ScmCwCompensationVo ScmCwCompensationVo = new ScmCwCompensationVo();
			ScmCwCompensationVo.setPaymentId(scmCwCompensationVo2.getPaymentId());
			ScmCwCompensationVo.setPayNoticeCode(scmCwDiscountPond.getDiscountCode());
			list.add(ScmCwCompensationVo);
		}
		return list;
	}
	@Override
	public void changePoodPrice(ScmCwDiscountPondPriceChange scmCwDiscountPondPriceChange) {
		ScmCwDiscountPond scmCwDiscountPond = scmCwDiscountPondDao.get(scmCwDiscountPondPriceChange.getDiscountpondId());
		if(scmCwDiscountPond  == null) {
			throw new RuntimeException("当前票折数据不存在");
		}
		Double changePrice = scmCwDiscountPondPriceChange.getChangePrice();
		//修改数据
		if(scmCwDiscountPond.getBalance() + changePrice < 0) {
			throw new RuntimeException("修改后金额小于0,请调整金额");
		}
		scmCwDiscountPondPriceChange.setChangeFrontPrice(scmCwDiscountPond.getBalance());
		scmCwDiscountPond.setBalance(scmCwDiscountPond.getBalance() + scmCwDiscountPondPriceChange.getChangePrice());
		if(scmCwDiscountPond.getDeductionPrice() == null || scmCwDiscountPond.getDeductionPrice() == 0) {
			scmCwDiscountPond.setDeductionPrice(0 + scmCwDiscountPondPriceChange.getChangePrice());
		}else{
			scmCwDiscountPond.setDeductionPrice(scmCwDiscountPond.getDeductionPrice() + scmCwDiscountPondPriceChange.getChangePrice());
		}
		scmCwDiscountPondPriceChange.setChangeBackPrice(scmCwDiscountPond.getBalance());
		scmCwDiscountPondDao.update(scmCwDiscountPond);
	}
}

