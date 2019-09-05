package com.winway.scm.persistence.manager.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotent.base.annotation.Workflow;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.feign.BpmRuntimeFeignService;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.model.CommonResult;
import com.hotent.base.modelBpmVo.DefaultFmsBpmCheckTaskOpinion;
import com.hotent.base.modelvo.AgreeFlowParam;
import com.winway.purchase.feign.ScmMasterDateFeignService;
import com.winway.purchase.persistence.manager.impl.WorkflowTemplate;
import com.winway.purchase.util.QuarterUtil;
import com.winway.scm.model.ScmXsBigContract;
import com.winway.scm.model.ScmXsBigContractAllot;
import com.winway.scm.model.ScmXsBigContractAllotProduct;
import com.winway.scm.model.ScmXsBigContractProductSum;
import com.winway.scm.model.ScmXsDealerClause;
import com.winway.scm.persistence.dao.ScmXsBigContractAllotDao;
import com.winway.scm.persistence.dao.ScmXsBigContractAllotProductDao;
import com.winway.scm.persistence.dao.ScmXsBigContractDao;
import com.winway.scm.persistence.dao.ScmXsBigContractProductSumDao;
import com.winway.scm.persistence.dao.ScmXsCreditApplyRecordDao;
import com.winway.scm.persistence.dao.ScmXsDealerClauseDao;
import com.winway.scm.persistence.manager.ScmXsBigContractAllotManager;
import com.winway.scm.persistence.manager.ScmXsBigContractAllotProductManager;
import com.winway.scm.persistence.manager.ScmXsCreditApplyRecordManager;
import io.swagger.annotations.ApiOperation;

/**
 * <pre> 
 * 描述：大合同分配表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:44
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmXsBigContractAllotManager")
public class ScmXsBigContractAllotManagerImpl extends AbstractManagerImpl<String, ScmXsBigContractAllot> implements ScmXsBigContractAllotManager{
	@Resource
	ScmXsBigContractAllotDao scmXsBigContractAllotDao;
	@Resource
	ScmXsBigContractAllotProductDao scmXsBigContractAllotProductDao;
	@Resource
	ScmXsBigContractProductSumDao scmXsBigContractProductSumDao;
	@Resource
	ScmXsBigContractDao scmXsBigContractDao;	
	@Resource
	ScmXsDealerClauseDao scmXsDealerClauseDao;
	@Resource
	ScmXsBigContractAllotProductManager scmXsBigContractAllotProductManager;
	@Resource
	ScmMasterDateFeignService scmMasterDateFeignService;
	@Resource
	ScmXsCreditApplyRecordManager scmXsCreditApplyRecordManager;
	@Resource
	ScmXsCreditApplyRecordDao scmXsCreditApplyRecordDao;
	@Resource
	WorkflowTemplate workflowTemplate;
    @Resource
    BpmRuntimeFeignService bpmRuntimeFeignService;
	
	@Override
	protected MyBatisDao<String, ScmXsBigContractAllot> getDao() {
		return scmXsBigContractAllotDao;
	}
	/**
	 * 创建实体包含子表实体
	 */
	public void create(ScmXsBigContractAllot scmXsBigContractAllot){
		//获取大合同分配编号
    	super.create(scmXsBigContractAllot);
    	String pk=scmXsBigContractAllot.getId();   	
    	List<ScmXsBigContractAllotProduct> scmXsBigContractAllotProductList=scmXsBigContractAllot.getScmXsBigContractAllotProductList();  
    	Map<String, ScmXsBigContractProductSum> map = new HashMap<>();
    	boolean b = true;
    	for(ScmXsBigContractAllotProduct scmXsBigContractAllotProduct:scmXsBigContractAllotProductList){
    		if(scmXsBigContractAllotProduct.getTheNumber() == null || scmXsBigContractAllotProduct.getTheNumber() == 0) {
    			continue;
    		}
    		ScmXsBigContractProductSum scmXsBigContractProductSum = scmXsBigContractProductSumDao.get(scmXsBigContractAllotProduct.getBigContractProductId());
    		if(scmXsBigContractProductSum.getSurplusSUm() < scmXsBigContractAllotProduct.getTheNumber()) {
    			throw new RuntimeException("数量超出大合同汇总商品数量");
    		}else{
    			scmXsBigContractProductSum.setSurplusSUm(scmXsBigContractProductSum.getSurplusSUm() - scmXsBigContractAllotProduct.getTheNumber());
    			scmXsBigContractProductSumDao.update(scmXsBigContractProductSum);
    		}
    		b = false;
    		scmXsBigContractAllotProduct.setBigContractAllotId(pk);
			scmXsBigContractAllotProduct.setProductState(scmXsBigContractAllotProduct.getProductGauge());
    		scmXsBigContractAllotProductManager.create(scmXsBigContractAllotProduct);
    	}
        if(b) {
        	throw new RuntimeException("请至少填写一个商品数量");
        }
    }
	
	/**
	 * 删除记录包含子表记录
	 */
	public void remove(String entityId){
		super.remove(entityId);
    	scmXsBigContractAllotProductDao.delByMainId(entityId);
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
    public ScmXsBigContractAllot get(String entityId){
    	ScmXsBigContractAllot scmXsBigContractAllot = scmXsBigContractAllotDao.get(entityId);
    	List<ScmXsBigContractAllotProduct> scmXsBigContractAllotProductList=scmXsBigContractAllotProductDao.getByMainId(entityId);
    	for(ScmXsBigContractAllotProduct allotProduct:scmXsBigContractAllotProductList){
    		allotProduct.setProductGauge(allotProduct.getProductState());
		}
    	scmXsBigContractAllot.setScmXsBigContractAllotProductList(scmXsBigContractAllotProductList);
    	return scmXsBigContractAllot;
    }
    
    /**
     * 更新实体同时更新子表记录
     */
    public void update(ScmXsBigContractAllot scmXsBigContractAllot){
    	String id=scmXsBigContractAllot.getId();
    	List<ScmXsBigContractAllotProduct> scmXsBigContractAllotProductList=scmXsBigContractAllot.getScmXsBigContractAllotProductList();
    	List<ScmXsBigContractAllotProduct> byMainId = scmXsBigContractAllotProductDao.getByMainId(id);
    	for (ScmXsBigContractAllotProduct scmXsBigContractAllotProduct : byMainId) {
    		//把原有商品加回大合同商品汇总表后再删除商品
    		String bigContractProductId = scmXsBigContractAllotProduct.getBigContractProductId();
    		ScmXsBigContractProductSum scmXsBigContractProductSum = scmXsBigContractProductSumDao.get(bigContractProductId);
    		if(scmXsBigContractProductSum == null) {
    			throw new RuntimeException("未找到提交商品");
    		}
    		ScmXsBigContractAllotProduct scmXsBigContractAllotProduct2 = scmXsBigContractAllotProductDao.get(scmXsBigContractAllotProduct.getId());
    		if(scmXsBigContractAllotProduct2 == null) {
    			throw new RuntimeException("历史数据获取异常，请刷新页面重试");
    		}
    		scmXsBigContractProductSum.setSurplusSUm(scmXsBigContractProductSum.getSurplusSUm() + scmXsBigContractAllotProduct2.getTheNumber());
    		scmXsBigContractProductSumDao.update(scmXsBigContractProductSum);
		}
    	scmXsBigContractAllotProductDao.delByMainId(id);
    	boolean b = true;
    	for(ScmXsBigContractAllotProduct scmXsBigContractAllotProduct:scmXsBigContractAllotProductList){
    		if(scmXsBigContractAllotProduct.getTheNumber() == null || scmXsBigContractAllotProduct.getTheNumber() == 0) {
    			continue;
    		}
    		scmXsBigContractAllotProduct.setBigContractAllotId(id);
    		String bigContractProductId = scmXsBigContractAllotProduct.getBigContractProductId();
    		ScmXsBigContractProductSum scmXsBigContractProductSum = scmXsBigContractProductSumDao.get(bigContractProductId);
    		if(scmXsBigContractProductSum == null) {
    			throw new RuntimeException("未找到提交商品");
    		}
			if (scmXsBigContractProductSum.getSurplusSUm() < scmXsBigContractAllotProduct.getTheNumber()) {
				throw new RuntimeException("分配商品数量超过剩余商品数量");
			}
			b = false;
    		scmXsBigContractProductSum.setSurplusSUm(scmXsBigContractProductSum.getSurplusSUm() - scmXsBigContractAllotProduct.getTheNumber());
    		scmXsBigContractProductSumDao.update(scmXsBigContractProductSum);
    		scmXsBigContractAllotProductManager.create(scmXsBigContractAllotProduct);
    	}
        if(b) {
        	throw new RuntimeException("请至少填写一个商品数量");
        }
    	super.update(scmXsBigContractAllot);
    }
    
	@Override
	public String sendApply(ScmXsBigContractAllot scmXsBigContractAllot) throws Exception {
		//添加审批状态
		scmXsBigContractAllot.setApprovalState("1");
		if(scmXsBigContractAllot.getBigContractId() == null ||"".equals(scmXsBigContractAllot.getBigContractId())) {
			throw new RuntimeException("请选择协议编号");
		}
		//判断保存状态
		ScmXsBigContractAllot scmXsBigContractAllotById = scmXsBigContractAllotDao.get(scmXsBigContractAllot.getId());
		if (scmXsBigContractAllotById == null) {
			String commerceFirstId = scmXsBigContractAllot.getCommerceFirstId();
			boolean commerceStateByFirstId = scmMasterDateFeignService.getCommerceStateByFirstId(commerceFirstId);
			if(!commerceStateByFirstId) {
				throw new RuntimeException("当前商业有证照过期,请更新后发起大合同分配审批");
			}
			create(scmXsBigContractAllot);
		}else{
			String commerceFirstId = scmXsBigContractAllot.getCommerceFirstId();
			boolean commerceStateByFirstId = scmMasterDateFeignService.getCommerceStateByFirstId(commerceFirstId);
			if(!commerceStateByFirstId) {
				throw new RuntimeException("当前商业有证照过期,请更新后发起大合同分配审批");
			}
			scmXsBigContractAllot.setCreatePersion(scmXsBigContractAllotById.getCreatePersion());
			scmXsBigContractAllot.setCreateDate(scmXsBigContractAllotById.getCreateDate());
			scmXsBigContractAllot.setBigContractId(scmXsBigContractAllotById.getBigContractId());
		}
		double price = 0.0d;
		List<ScmXsBigContractAllotProduct> scmXsBigContractAllotProductList = scmXsBigContractAllot.getScmXsBigContractAllotProductList();
		for (ScmXsBigContractAllotProduct scmXsBigContractAllotProduct : scmXsBigContractAllotProductList) {
			if(scmXsBigContractAllotProduct.getSumPrice() == null) {
				continue;
			}
			price += scmXsBigContractAllotProduct.getSumPrice();
		}
		ScmXsDealerClause scmXsDealerClause = scmXsDealerClauseDao.get(scmXsBigContractAllot.getBigContractId());
		if(scmXsDealerClause == null) {
			throw new RuntimeException("请选择商业及协议");
		}
		String isOverfulfil = scmXsDealerClause.getIsOverfulfil();
		//判断付款方式		
		if("1".equals(scmXsBigContractAllot.getMarketingWay())) {
			if(!"1".equals(isOverfulfil)) {
				//禁止超资信发货
				//查询资信额度
				String residueCreditByCommerceCode = scmXsCreditApplyRecordManager.getResidueCreditByCommerceCode(scmXsBigContractAllot.getCommerceFirstId(), scmXsBigContractAllot.getOwnerId());
				//判断是否超资信
				if(price > Double.parseDouble(residueCreditByCommerceCode)) {
					throw new RuntimeException("当前商业超资信禁止发货");
				}				
			}
		}
		//资信增加金额
	    try{
	    	String getcommerceFirstById = scmMasterDateFeignService.getcommerceFirstById(scmXsBigContractAllot.getCommerceFirstId());
	    	JSONObject parseObject = JSON.parseObject(getcommerceFirstById);
	    	scmXsCreditApplyRecordManager.saveByContractAndReturnMoney(scmXsBigContractAllot.getId(), price, parseObject.getString("commerceId"), scmXsBigContractAllot.getOwnerId(),"0" );
	    }catch (Exception e) {
	    	throw new RuntimeException("商业信息异常,请验证商业首营与基础信息");
	    }
	    //验证商业是否回款超期
	    boolean verifExceedTime = scmMasterDateFeignService.verifExceedTime(scmXsBigContractAllot.getCommerceCode());
	    if(!verifExceedTime) {
	    	throw new RuntimeException("商业回款超期,禁止发起合同分配");
	    }
	    //验证是否超过近三个月平均值
	    boolean verifyDeliveryAmount = scmMasterDateFeignService.verifyDeliveryAmount(scmXsBigContractAllot.getCommerceFirstId(),scmXsBigContractAllot.getTotalPrice());
	    if(verifyDeliveryAmount) {
	    	scmXsBigContractAllot.setIsoverfuifil("0");
	    }else{
	    	scmXsBigContractAllot.setIsoverfuifil("1");
	    }
	    
	    
	    if((scmXsBigContractAllotById != null && "3".equals(scmXsBigContractAllotById.getApprovalState()))) {
//		      {"formType":"frame","opinion":"驳回后发起","actionName":"agree","taskId":11108798,"jumpType":"","destination":"","nodeUsers":"[]"}
				    List<String> list = new ArrayList<String>();
				    list.add(scmXsBigContractAllotById.getApprovalId());
				    List<DefaultFmsBpmCheckTaskOpinion> instanceFlowHistoryList = bpmRuntimeFeignService.instanceFlowHistoryList(list);
				    if(instanceFlowHistoryList.size() == 0){
				    	//发起审批流
					    //发起审批流 @Workflow(flowKey = "dhtfpsp", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {"totalPrice","creditPrice"})
				        String[] strs = {"totalPrice","creditPrice","isoverfuifil"};
				        workflowTemplate.startWorkflow("dhtfpsp", "SCM", "approvalId",scmXsBigContractAllot, strs);
				        String replace = scmXsBigContractAllot.getApprovalId().replace("\"", "");
				        scmXsBigContractAllot.setApprovalId(replace);
				        update(scmXsBigContractAllot);
				        return scmXsBigContractAllot.getId();
				    }else{
				    	DefaultFmsBpmCheckTaskOpinion defaultFmsBpmCheckTaskOpinion = instanceFlowHistoryList.get(instanceFlowHistoryList.size() -1 );
				    	bpmRuntimeFeignService.autoAgree(new AgreeFlowParam("驳回后发起", "agree", defaultFmsBpmCheckTaskOpinion.getTaskId(), "", "", "[]"));
				    	scmXsBigContractAllot.setApprovalId(scmXsBigContractAllotById.getApprovalId());
				    	update(scmXsBigContractAllot);
				    	return scmXsBigContractAllot.getId();
				    }
			    }else{
				    //发起审批流
				    //发起审批流 @Workflow(flowKey = "dhtfpsp", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {"totalPrice","creditPrice"})
			        String[] strs = {"totalPrice","creditPrice","isoverfuifil"};
			        workflowTemplate.startWorkflow("dhtfpsp", "SCM", "approvalId",scmXsBigContractAllot, strs);
			        String replace = scmXsBigContractAllot.getApprovalId().replace("\"", "");
			        scmXsBigContractAllot.setApprovalId(replace);
			        update(scmXsBigContractAllot);
			        return scmXsBigContractAllot.getId();
			    }
	    
	    

	}
	
	@Override
	public void endApply(JsonNode jsonNode) throws JsonProcessingException {
		String approvalId = jsonNode.get("instId").asText();
		String actionName = jsonNode.get("actionName").asText();
		ScmXsBigContractAllot scmXsBigContractAllotByApprovalId = scmXsBigContractAllotDao.getAllotByApprovalId(approvalId);
		if(scmXsBigContractAllotByApprovalId == null) {
			throw new RuntimeException("未查询到业务数据,处理异常");
		}
		String endEvent = jsonNode.get("eventType").asText();
		if ("agree".equals(actionName) && "endEvent".equals(endEvent)) {
			//判断该


			List<ScmXsBigContractAllotProduct> scmXsBigContractAllotProduct = scmXsBigContractAllotProductDao.getByMainId(scmXsBigContractAllotByApprovalId.getId());
			scmXsBigContractAllotByApprovalId.setApprovalState("2");
			//加入发货任务
			List<Map<String, Object>> list = new ArrayList<>();
			for (ScmXsBigContractAllotProduct sxbap: scmXsBigContractAllotProduct) {
				if(sxbap.getTheNumber() <= 0) {
					continue;
				}
				Map<String, Object> map = new HashMap<>();
				map.put("ownerId", scmXsBigContractAllotByApprovalId.getOwnerId());
				map.put("type", "0");
				map.put("taskType", "1");
				map.put("dateCode", scmXsBigContractAllotByApprovalId.getAllocationCode());
				map.put("taskCreateDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				map.put("allocationCode", scmXsBigContractAllotByApprovalId.getAllocationCode());
				map.put("sendProductDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(scmXsBigContractAllotByApprovalId.getSendProductDate()));
				//商品信息
				ScmXsBigContractProductSum scmXsBigContractProductSum = scmXsBigContractProductSumDao.get(sxbap.getBigContractProductId());
				map.put("isSms", scmXsBigContractProductSum.getIsSMS());
				map.put("productName", scmXsBigContractProductSum.getProductName());
				map.put("productNum", scmXsBigContractProductSum.getProductCode());
				map.put("MedicineType", scmXsBigContractProductSum.getMedicineType());
				map.put("productState", scmXsBigContractProductSum.getProductGauge());
				map.put("commonName",scmXsBigContractProductSum.getCommonName());
				map.put("unit",scmXsBigContractProductSum.getUnit());
				map.put("AgentType",scmXsBigContractProductSum.getAgentType());
				map.put("manufacturer",scmXsBigContractProductSum.getManufacturer());
				map.put("approvalCode",scmXsBigContractProductSum.getApprovalCode());
				map.put("medicineClassify",scmXsBigContractProductSum.getMedicineClassify());
				map.put("businessScope",scmXsBigContractProductSum.getBusinessScope());

				map.put("price", sxbap.getUnitPrice());
				map.put("aChange", sxbap.getAchage());
				map.put("packageNum",sxbap.getPackageNum());
				map.put("memo", scmXsBigContractAllotByApprovalId.getSpecialOpinion());
				map.put("planShipmentsSum", sxbap.getTheNumber());
				map.put("sumPrice", sxbap.getSumPrice());
				map.put("focusShipmentsMonth", "");
				map.put("code", sxbap.getCode());
				//获取客户信息
				List<ScmXsBigContract> ScmXsBigContract = scmXsBigContractDao.getByCommerceFirstId(scmXsBigContractAllotByApprovalId.getCommerceFirstId());
				ScmXsBigContract sxbc = ScmXsBigContract.get(0);
				ScmXsDealerClause ScmXsDealerClause = scmXsDealerClauseDao.get(sxbc.getDealerClauseId());
				map.put("commercetype", ScmXsDealerClause.getActualLevel());
				map.put("marketType", ScmXsDealerClause.getFunctionType());
				map.put("contactPersion", sxbc.getConsignee());
				map.put("phone", sxbc.getPhone());
				map.put("commerceName", sxbc.getDealer());
				map.put("commerceId", sxbc.getCommerceFirstId());
				map.put("paymentType", sxbc.getMarketingWay());

				list.add(map);
			}
			ObjectMapper mapper = new ObjectMapper();
		    String actualObj = mapper.writeValueAsString(list);
			scmMasterDateFeignService.theDeliveryTask(actualObj);
			scmXsBigContractAllotDao.update(scmXsBigContractAllotByApprovalId);
		} else if ("agree".equals(actionName)) {
		} else if ("reject".equals(actionName)) {
		} else if ("backToStart".equals(actionName)) {
			scmXsBigContractAllotByApprovalId.setApprovalState("3");
			//删除资信记录
			scmXsCreditApplyRecordDao.removeByDataIdAndType(scmXsBigContractAllotByApprovalId.getId(),"0");
			scmXsBigContractAllotDao.update(scmXsBigContractAllotByApprovalId);
		} else if ("opposeTrans".equals(actionName)) {
		} else if ("endProcess".equals(actionName)) {
		}
	}
	/**
	 * 发货分配获取编号
	 * @param
	 * @return
	 * @throws 
	 * Exception
	 * ModelAndView
	 */
	@GetMapping(value = "/getCode")
	@ApiOperation(value = "发货分配获取编号", httpMethod = "GET", notes = "发货分配获取编号")
	public CommonResult<String> getCode()throws Exception {
		String code = QuarterUtil.getCode("JZFH");
		return new CommonResult(true,"获取成功",code);
	}

	
	@Override
	public ScmXsBigContractAllot getByApplyId(String applyId) {
		ScmXsBigContractAllot s = scmXsBigContractAllotDao.getByApplyId(applyId);
		if(s == null) {
			throw new RuntimeException("未查询到数据");
		}
		List<ScmXsBigContractAllotProduct> scmXsBigContractAllotProductList=scmXsBigContractAllotProductDao.getByMainId(s.getId());
    	s.setScmXsBigContractAllotProductList(scmXsBigContractAllotProductList);
		return s;
	}
	
	
}
