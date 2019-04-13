package com.winway.scm.persistence.manager.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.model.CommonResult;
import com.winway.purchase.feign.ScmMasterDateFeignService;
import com.winway.scm.model.*;
import com.winway.scm.persistence.dao.*;
import com.winway.scm.persistence.manager.ScmXsFocusSendManager;
import com.winway.scm.persistence.manager.ScmXsFocusSendProductManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * <pre> 
 * 描述：集中发货合同 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:44
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmXsFocusSendManager")
public class ScmXsFocusSendManagerImpl extends AbstractManagerImpl<String, ScmXsFocusSend> implements ScmXsFocusSendManager{
	@Resource
	ScmXsFocusSendDao scmXsFocusSendDao;
	@Resource
	ScmXsFocusSendProductDao scmXsFocusSendProductDao;
	@Resource
	ScmXsFocusSendProductManager scmXsFocusSendProductManager;
	@Resource
	ScmMasterDateFeignService scmMasterDateFeignService;
	@Resource
	ScmXsBigContractProductSumDao scmXsBigContractProductSumDao;
	@Resource
	ScmXsBigContractDao scmXsBigContractDao;
	@Resource
	ScmXsDealerClauseDao scmXsDealerClauseDao;
	
	@Override
	protected MyBatisDao<String, ScmXsFocusSend> getDao() {
		return scmXsFocusSendDao;
	}
	/**
	 * 创建实体包含子表实体
	 */
	public void create(ScmXsFocusSend scmXsFocusSend){
    	super.create(scmXsFocusSend);
    	String pk=scmXsFocusSend.getId();
    	List<ScmXsFocusSendProduct> scmXsFocusSendProductList=scmXsFocusSend.getScmXsFocusSendProductList();
    	for(ScmXsFocusSendProduct scmXsFocusSendProduct:scmXsFocusSendProductList){
    		scmXsFocusSendProduct.setFocusSendId(pk);
    		String bigContractProductId = scmXsFocusSendProduct.getBigContractProductId();
    		ScmXsBigContractProductSum scmXsBigContractProductSum = scmXsBigContractProductSumDao.get(bigContractProductId);
    		scmXsBigContractProductSum.setSurplusSUm(scmXsBigContractProductSum.getSurplusSUm() - scmXsFocusSendProduct.getTheNumber());
    		scmXsBigContractProductSumDao.update(scmXsBigContractProductSum);
    		scmXsFocusSendProductManager.create(scmXsFocusSendProduct);
    	}
    }
	
	/**
	 * 删除记录包含子表记录
	 */
	public void remove(String entityId){
		super.remove(entityId);
    	scmXsFocusSendProductDao.delByMainId(entityId);
	}
	
	/**
	 * 批量删除包含子表记录
	 */
	public void removeByIds(String[] entityIds){
		for(String id:entityIds){
			this.remove(id);
		}
	}
    
	/**
	 * 获取实体
	 */
    public ScmXsFocusSend get(String entityId){
    	ScmXsFocusSend scmXsFocusSend=super.get(entityId);
    	List<ScmXsFocusSendProduct> scmXsFocusSendProductList=scmXsFocusSendProductDao.getByMainId(entityId);
    	scmXsFocusSend.setScmXsFocusSendProductList(scmXsFocusSendProductList);
    	return scmXsFocusSend;
    }
    
    /**
     * 更新实体同时更新子表记录
     */
    public void update(ScmXsFocusSend scmXsFocusSend){
    	super.update(scmXsFocusSend);
    	String id=scmXsFocusSend.getId();
    	List<ScmXsFocusSendProduct> byMainId = scmXsFocusSendProductDao.getByMainId(id);
    	for (ScmXsFocusSendProduct scmXsFocusSendProduct : byMainId) {
    		String bigContractProductId = scmXsFocusSendProduct.getBigContractProductId();
    		ScmXsBigContractProductSum scmXsBigContractProductSum = scmXsBigContractProductSumDao.get(bigContractProductId);
    		scmXsBigContractProductSum.setSurplusSUm(scmXsBigContractProductSum.getSurplusSUm() + scmXsFocusSendProduct.getTheNumber());
    		scmXsBigContractProductSumDao.update(scmXsBigContractProductSum);
		}
    	scmXsFocusSendProductDao.delByMainId(id);
    	List<ScmXsFocusSendProduct> scmXsFocusSendProductList=scmXsFocusSend.getScmXsFocusSendProductList();
    	for(ScmXsFocusSendProduct scmXsFocusSendProduct:scmXsFocusSendProductList){
    		scmXsFocusSendProduct.setFocusSendId(id);
    		String bigContractProductId = scmXsFocusSendProduct.getBigContractProductId();
    		ScmXsBigContractProductSum scmXsBigContractProductSum = scmXsBigContractProductSumDao.get(bigContractProductId);
    		scmXsBigContractProductSum.setSurplusSUm(scmXsBigContractProductSum.getSurplusSUm() - scmXsFocusSendProduct.getTheNumber());
    		scmXsBigContractProductSumDao.update(scmXsBigContractProductSum);
    		scmXsFocusSendProductManager.create(scmXsFocusSendProduct);
    	}
    }
    
	@Override
	public void sendApply(ScmXsFocusSend scmXsFocusSend) {
		//首先判断是否保存过,如果没有保存过,需要先保存
		scmXsFocusSend.setApprovalState("1");
		//查询数据库
		ScmXsFocusSend scmXsFocusSend2 = scmXsFocusSendDao.get(scmXsFocusSend.getId());
		if(scmXsFocusSend2 == null) {
			//没有保存过
			create(scmXsFocusSend);
		}else{
			//保存过哦 更新数据
			update(scmXsFocusSend);
		}
		
		
	}
	@Override
	public void endApply(JsonNode jsonNode) {
		String approvalId = jsonNode.get("instId").asText();
		String actionName = jsonNode.get("actionName").asText();
		ScmXsFocusSend scmXsFocusSend = scmXsFocusSendDao.getByApprovalId(approvalId);
		if(scmXsFocusSend == null) {
			throw new RuntimeException("集中发货审批数据未找到");
		}
		//创建发货任务数组
		ScmXsFocusSend byApprovalId = scmXsFocusSendDao.getByApprovalId(approvalId);
		if ("agree".equals(actionName)) {
			
			//获取客户信息
			List<ScmXsBigContract> scmXsBigContract = scmXsBigContractDao.getByCommerceFirstId(byApprovalId.getCommerceFirstId());
			ScmXsBigContract sxbc = scmXsBigContract.get(0);
			ScmXsDealerClause scmXsDealerClause = scmXsDealerClauseDao.get(sxbc.getDealerClauseId());
			if(byApprovalId == null) {
				throw new RuntimeException("集中发货任务未找到");
			}
			//根据集中发货ID查询集中发货商品列表
			List<ScmXsFocusSendProduct> scmXsFocusSendProducts = scmXsFocusSendProductDao.getFocussendToTask(byApprovalId.getId(),byApprovalId.getOwnerId());
			byApprovalId.setApprovalState("2");
			//加入发货任务
			List<Map<String, Object>> list = new ArrayList<>();
			for (ScmXsFocusSendProduct scmXsFocusSendProduct: scmXsFocusSendProducts) {
				String productByCode = scmMasterDateFeignService.getProductByCode(scmXsFocusSendProduct.getProductCode());
				JSONObject parseObject = JSONObject.parseObject(productByCode);
				Map<String, Object> map = new HashMap<>();
				map.put("ownerId", byApprovalId.getOwnerId());
				map.put("type", "0");
				map.put("taskType", "0");
				map.put("dateCode", byApprovalId.getFocusSendCode());
				map.put("taskCreateDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				//商品信息
				map.put("isSms", "0");
				map.put("productName",parseObject.getString("productName"));
				map.put("productNum",parseObject.getString("productCode"));
				map.put("MedicineType", parseObject.getString("medicineType"));
				map.put("productState", parseObject.getString("productState"));
				map.put("price", scmXsFocusSendProduct.getUnitPrice());
				map.put("sumPrice", scmXsFocusSendProduct.getSumPrice());
				map.put("aChange", scmXsFocusSendProduct.getAchange());
				map.put("packageNum",scmXsFocusSendProduct.getPackageNum());
				map.put("memo", byApprovalId.getSpecialOpinion());
				map.put("planShipmentsSum", scmXsFocusSendProduct.getTheNumber());
				map.put("focusShipmentsMonth", new SimpleDateFormat("MM").format(scmXsFocusSendProduct.getYearAndMonth()).replaceFirst("0",""));
				//获取客户信息
				map.put("commercetype", scmXsDealerClause.getActualLevel());
				map.put("marketType", scmXsDealerClause.getFunctionType());
				map.put("contactPersion", sxbc.getConsignee());
				map.put("phone", sxbc.getPhone());
				map.put("commerceName", sxbc.getDealer());
				map.put("commerceId", sxbc.getCommerceFirstId());
				map.put("paymentType", sxbc.getMarketingWay());
				list.add(map);
			}
			String jsonString = JSON.toJSONString(list);
			scmXsFocusSendDao.update(byApprovalId);
			scmMasterDateFeignService.theDeliveryTask(jsonString);
		}else if("oppose".equals(actionName)) {
			//审批驳回
			byApprovalId.setApprovalState("3");
			scmXsFocusSendDao.update(byApprovalId);
		}
		//生成发货任务
	}
	@Override
	public CommonResult<String> createFocussend() {
		//定时生成集中发货任务
		System.out.println("定时器执行******");
		return null;
	}
	@Override
	public List<ScmXsBigContractProductSum> getProduct(String commerceCode, String ownerId,String agreementSummaryId) throws IOException {
		//根据商业编号与货主ID查询商业首营ID与商业ID
		List<Map<String,Object>> commerceMessageByCodeAndOwnerId = scmXsFocusSendDao.getCommerceMessageByCodeAndOwnerId(commerceCode,ownerId);
		if(commerceMessageByCodeAndOwnerId.size() == 0) {
			throw new RuntimeException("未查询到商业数据");
		}
		Map map = commerceMessageByCodeAndOwnerId.get(0);
		String commerceFirstId = map.get("commerceFirstId").toString();
		String commerceId = map.get("commerceId").toString();
		//根据商业及货主ID查询出集中发货设置的商品数据
		String listFocusShipmentsSetByComm = scmMasterDateFeignService.listFocusShipmentsSetByComm(commerceId, ownerId);
		//根据商业及货主ID与集中发货设置商品数据获取大合同中可以发货的数据
		ObjectMapper mapper = new ObjectMapper();
        List lendReco = mapper.readValue(listFocusShipmentsSetByComm,new TypeReference<List>(){});
		//返回页面
        List<ScmXsBigContractProductSum> listByFhFocusShipmentsSet = scmXsBigContractProductSumDao.listByFhFocusShipmentsSet(commerceFirstId,ownerId,agreementSummaryId,lendReco);
		return listByFhFocusShipmentsSet;
	}
	@Override
	public ScmXsFocusSend getApplyId(String applyId) {
		ScmXsFocusSend byApprovalId = scmXsFocusSendDao.getByApprovalId(applyId);
		if(byApprovalId == null) {
			throw new RuntimeException("未查询到审批数据");
		}
		ScmXsFocusSend scmXsFocusSend = get(byApprovalId.getId());
		return scmXsFocusSend;
	}
}
