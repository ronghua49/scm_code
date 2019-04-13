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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.model.CommonResult;
import com.winway.purchase.feign.ScmMasterDateFeignService;
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
import com.winway.scm.persistence.dao.ScmXsDealerClauseDao;
import com.winway.scm.persistence.manager.ScmXsBigContractAllotManager;
import com.winway.scm.persistence.manager.ScmXsBigContractAllotProductManager;

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
    		scmXsBigContractAllotProduct.setBigContractAllotId(pk);
    		scmXsBigContractAllotProductManager.create(scmXsBigContractAllotProduct);
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
    		scmXsBigContractProductSum.setSurplusSUm(scmXsBigContractProductSum.getSurplusSUm() - scmXsBigContractAllotProduct.getTheNumber());
    		scmXsBigContractProductSumDao.update(scmXsBigContractProductSum);
    		scmXsBigContractAllotProductManager.create(scmXsBigContractAllotProduct);
    	}
    	super.update(scmXsBigContractAllot);
    }
	@Override
	public String sendApply(ScmXsBigContractAllot scmXsBigContractAllot) {
		//添加审批状态
		scmXsBigContractAllot.setApprovalState("1");
		//判断保存状态
		ScmXsBigContractAllot scmXsBigContractAllotById = scmXsBigContractAllotDao.get(scmXsBigContractAllot.getId());
		if (scmXsBigContractAllotById == null) {
			create(scmXsBigContractAllot);
		}else{
			scmXsBigContractAllot.setCreatePersion(scmXsBigContractAllotById.getCreatePersion());
			scmXsBigContractAllot.setCreateDate(scmXsBigContractAllotById.getCreateDate());
			update(scmXsBigContractAllot);
		}
		return scmXsBigContractAllot.getId();
	}
	@Override
	public void endApply(JsonNode jsonNode) throws JsonProcessingException {
		String approvalId = jsonNode.get("instId").asText();
		String actionName = jsonNode.get("actionName").asText();
		ScmXsBigContractAllot scmXsBigContractAllotByApprovalId = scmXsBigContractAllotDao.getAllotByApprovalId(approvalId);
		if(scmXsBigContractAllotByApprovalId == null) {
			throw new RuntimeException("未查询到业务数据,处理异常");
		}
		if ("agree".equals(actionName)) {
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
				map.put("productName", scmXsBigContractProductSum.getCommonName());
				map.put("productNum", scmXsBigContractProductSum.getProductCode());
				map.put("MedicineType", scmXsBigContractProductSum.getMedicineType());
				map.put("productState", scmXsBigContractProductSum.getProductGauge());
				map.put("price", sxbap.getUnitPrice());
				map.put("aChange", sxbap.getaChange());
				map.put("packageNum",sxbap.getPackageNum());
				map.put("memo", scmXsBigContractAllotByApprovalId.getSpecialOpinion());
				map.put("planShipmentsSum", sxbap.getTheNumber());
				map.put("sumPrice", sxbap.getSumPrice());
				map.put("focusShipmentsMonth", "");
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
		}else if("oppose".equals(actionName)){
			scmXsBigContractAllotByApprovalId.setApprovalState("3");
		}
		scmXsBigContractAllotDao.update(scmXsBigContractAllotByApprovalId);
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
