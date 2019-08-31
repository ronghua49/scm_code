package com.winway.scm.persistence.manager.impl;


import java.text.DecimalFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.purchase.feign.ScmMasterDateFeignService;
import com.winway.purchase.util.DateFormatter;
import com.winway.scm.model.ScmXsCreditApply;
import com.winway.scm.model.ScmXsCreditApplyRecord;
import com.winway.scm.persistence.dao.ScmXsCreditApplyDao;
import com.winway.scm.persistence.dao.ScmXsCreditApplyRecordDao;
import com.winway.scm.persistence.manager.ScmXsCreditApplyRecordManager;

/**
 * 
 * <pre> 
 * 描述：Scm_Xs_CreditApplyRecord 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-07-16 11:39:59
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmXsCreditApplyRecordManager")
public class ScmXsCreditApplyRecordManagerImpl extends AbstractManagerImpl<String, ScmXsCreditApplyRecord> implements ScmXsCreditApplyRecordManager{
	@Resource
	ScmXsCreditApplyRecordDao scmXsCreditApplyRecordDao;
	@Resource
	ScmXsCreditApplyDao scmXsCreditApplyDao;
	@Resource
	ScmMasterDateFeignService scmMasterDateFeignService;
	@Override
	protected MyBatisDao<String, ScmXsCreditApplyRecord> getDao() {
		return scmXsCreditApplyRecordDao;
	}
	@Override
	public String getResidueCreditByCommerceCode(String commerceFirstId,String ownerId) {
		//查询商业资信
		try{
			String getcommerceFirstById = scmMasterDateFeignService.getcommerceFirstById(commerceFirstId);
			JSONObject parseObject = JSON.parseObject(getcommerceFirstById);
			String commerceCode = parseObject.getString("commerceCode");
			if(commerceCode == null || "".equals(commerceCode)) {
				return "0.00";
			}
			ScmXsCreditApply ScmXsCreditApply = scmXsCreditApplyDao.getByCommerceCode(commerceCode,ownerId);
			if(ScmXsCreditApply == null) {
				return "0.00";
			}
			if("0".equals(ScmXsCreditApply.getMarketWay())) {
				return "0.00";
			}
			if(null == ScmXsCreditApply.getMarketWay()) {
				return "0.00";
			}
			String format = DateFormatter.format(DateFormatter.PATTERN_yyyy_MM_dd_HH_mm_ss, new Date());
			Map<String,Object> map = scmXsCreditApplyRecordDao.getResidueCreditByCommerceCode(commerceCode,ownerId,format);
			String string = map.get("price").toString();
			double parseInt = Double.parseDouble(string);
			double parseInt2 = ScmXsCreditApply.getPrice() * 10000.00d - parseInt;
			DecimalFormat df = new DecimalFormat("#.00");
			return df.format(parseInt2);
		}catch (Exception e) {
			return "0.00";
		}
	}
	
	@Override
	public void saveByContractAndReturnMoney(String dataId,double price,String commerceId,String ownerId,String recordType){
		//根据商品编码,类型,数据ID查询数据
		ScmXsCreditApplyRecord getByDataId = scmXsCreditApplyRecordDao.getByDataId(dataId,ownerId,recordType);
		if(getByDataId != null) {
			//回款数据已经存在,修改回款数据
			getByDataId.setPrice(price);
			getByDataId.setCommerceId(commerceId);
			getByDataId.setOwnerId(ownerId);
			getByDataId.setRecordType(recordType);
			update(getByDataId);
			return;
		}
		String getcommerceById = scmMasterDateFeignService.getcommerceById(commerceId);
		JSONObject parseObject = JSON.parseObject(getcommerceById);
		String commerceCode = parseObject.getString("commerceCode");
		ScmXsCreditApplyRecord scmXsCreditApplyRecord = new ScmXsCreditApplyRecord();
		scmXsCreditApplyRecord.setCommerceCode(commerceCode);
		scmXsCreditApplyRecord.setCommerceId(commerceId);
		scmXsCreditApplyRecord.setRecordType(recordType);
		scmXsCreditApplyRecord.setDataId(dataId);
		scmXsCreditApplyRecord.setPrice(price);
		scmXsCreditApplyRecord.setRecordDate(new Date());
		scmXsCreditApplyRecord.setOwnerId(ownerId);
		create(scmXsCreditApplyRecord);
	}
	
	@Override
	public void saveByFocusSend(String dataId,double price,String commerceId,String ownerId,Date focusSendDate){
		String getcommerceById = scmMasterDateFeignService.getcommerceById(commerceId);
		JSONObject parseObject = JSON.parseObject(getcommerceById);
		String commerceCode = parseObject.getString("commerceCode");
		ScmXsCreditApplyRecord scmXsCreditApplyRecord = new ScmXsCreditApplyRecord();
		scmXsCreditApplyRecord.setCommerceCode(commerceCode);
		scmXsCreditApplyRecord.setCommerceId(commerceId);
		scmXsCreditApplyRecord.setRecordType("2");
		scmXsCreditApplyRecord.setDataId(dataId);
		scmXsCreditApplyRecord.setPrice(price);
		scmXsCreditApplyRecord.setRecordDate(focusSendDate);
		scmXsCreditApplyRecord.setFocusSendDate(focusSendDate);
		scmXsCreditApplyRecord.setOwnerId(ownerId);
		create(scmXsCreditApplyRecord);
	}
	
}
