package com.winway.scm.persistence.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.hotent.base.exception.SystemException;
import com.hotent.base.feign.BpmRuntimeFeignService;
import com.hotent.base.modelvo.CustomStartResult;
import com.hotent.base.modelvo.StartFlowParam;
import com.hotent.base.query.PageBean;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.BeanUtils;
import com.winway.purchase.util.HttpClientUtil;
import com.winway.purchase.util.QuarterUtil;
import com.winway.purchase.util.ScmPuthWmsUtil;
import com.winway.scm.persistence.manager.ScmKcDrugDestructionManager;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.model.ScmKcDrugDestruction;
import com.winway.scm.model.ScmKcDrugDestructionMaster;
import com.winway.scm.persistence.dao.ScmKcDrugDestructionDao;
import com.winway.scm.persistence.dao.ScmKcDrugDestructionMasterDao;
import com.winway.scm.persistence.manager.ScmKcDrugDestructionMasterManager;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * <pre> 
 * 描述：不合格药品销毁主表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-13 15:06:38
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmKcDrugDestructionMasterManager")
public class ScmKcDrugDestructionMasterManagerImpl extends AbstractManagerImpl<String, ScmKcDrugDestructionMaster> implements ScmKcDrugDestructionMasterManager{
	@Resource
	ScmKcDrugDestructionMasterDao scmKcDrugDestructionMasterDao;
	@Resource
	ScmKcDrugDestructionDao scmKcDrugDestructionDao;

	@Resource
	ScmKcDrugDestructionManager scmKcDrugDestructionManager;
	@Resource
	BpmRuntimeFeignService bpmRuntimeFeignService;

	@Override
	protected MyBatisDao<String, ScmKcDrugDestructionMaster> getDao() {
		return scmKcDrugDestructionMasterDao;
	}
	
	
	/**
	 * 创建实体包含子表实体
	 */
	public void create(ScmKcDrugDestructionMaster scmKcDrugDestructionMaster){
    	super.create(scmKcDrugDestructionMaster);
    	String pk=scmKcDrugDestructionMaster.getId();
    	
    	List<ScmKcDrugDestruction> scmKcDrugDestructionList=scmKcDrugDestructionMaster.getScmKcDrugDestructionList();
    	scmKcDrugDestructionMaster.setVarietySum(scmKcDrugDestructionList.size());
    	int  count = 0;
    	double priceCount= 0.0;
    	for(ScmKcDrugDestruction scmKcDrugDestruction:scmKcDrugDestructionList){
    		scmKcDrugDestruction.setMasterId(pk);
    		count += scmKcDrugDestruction.getProductSum();
    		priceCount += scmKcDrugDestruction.getPriceSum();
			scmKcDrugDestructionManager.create(scmKcDrugDestruction);
    	}
    	scmKcDrugDestructionMaster.setPriceSum(priceCount);
    	scmKcDrugDestructionMaster.setProductNum(count);
    	scmKcDrugDestructionMasterDao.update(scmKcDrugDestructionMaster);
    }
	
	/**
	 * 删除记录包含子表记录
	 */
	public void remove(String entityId){
		super.remove(entityId);
    	scmKcDrugDestructionDao.delByMainId(entityId);
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
    public ScmKcDrugDestructionMaster get(String entityId){
    	ScmKcDrugDestructionMaster scmKcDrugDestructionMaster=super.get(entityId);
    	List<ScmKcDrugDestruction> scmKcDrugDestructionList=scmKcDrugDestructionDao.getByMainId(entityId);
    	scmKcDrugDestructionMaster.setScmKcDrugDestructionList(scmKcDrugDestructionList);
    	return scmKcDrugDestructionMaster;
    }
    
    /**
     * 更新实体同时更新子表记录
     */
    public void update(ScmKcDrugDestructionMaster scmKcDrugDestructionMaster){
    	String id=scmKcDrugDestructionMaster.getId();
    	scmKcDrugDestructionDao.delByMainId(id);
    	List<ScmKcDrugDestruction> scmKcDrugDestructionList=scmKcDrugDestructionMaster.getScmKcDrugDestructionList();
    	scmKcDrugDestructionMaster.setVarietySum(scmKcDrugDestructionList.size());
    	int  count = 0;
    	double priceCount= 0.0;
    	for(ScmKcDrugDestruction scmKcDrugDestruction:scmKcDrugDestructionList){
    		scmKcDrugDestruction.setMasterId(id);
    		count += scmKcDrugDestruction.getProductSum();
    		priceCount += scmKcDrugDestruction.getPriceSum();
			scmKcDrugDestructionManager.create(scmKcDrugDestruction);
    	}
    	scmKcDrugDestructionMaster.setPriceSum(priceCount);
    	scmKcDrugDestructionMaster.setProductNum(count);
    	super.update(scmKcDrugDestructionMaster);
    }

    @Transactional
	@Override
	public void sendApply(ScmKcDrugDestructionMaster scmKcDrugDestructionMaster) {
		ScmKcDrugDestructionMaster scmKcDrugDestructionMaster2 = scmKcDrugDestructionMasterDao.get(scmKcDrugDestructionMaster.getId());
		StartFlowParam startFlowParam = new StartFlowParam("bhgypxh", "SCM", "approvalId");
		startFlowParam.setFormType("frame");
		CustomStartResult customStartResult = null;
		try {
			System.out.println("发起不合格药品销毁申请");
			customStartResult = bpmRuntimeFeignService.customStart(startFlowParam);
			String approvalId = customStartResult.getInstId();
			scmKcDrugDestructionMaster.setApprovalId(approvalId);
			scmKcDrugDestructionMaster.setApprovalState("1");
			if(scmKcDrugDestructionMaster2 == null) {
				//直接发起申请
				create(scmKcDrugDestructionMaster);
			}else{
				//暂存过发起申请
				scmKcDrugDestructionMaster.setApprovalState("1");
				scmKcDrugDestructionMaster.setCreateDate(scmKcDrugDestructionMaster2.getCreateDate());
				scmKcDrugDestructionMaster.setCreatepersion(scmKcDrugDestructionMaster2.getCreatepersion());
				update(scmKcDrugDestructionMaster);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("启动工作流失败");
		}

	}
	@Override
	public void endApply(JsonNode jsonNode) {
		String approvalId = jsonNode.get("instId").asText();
		String actionName = jsonNode.get("actionName").asText();
		ScmKcDrugDestructionMaster scmKcDrugDestructionMaster = scmKcDrugDestructionMasterDao.getByApprovalId(approvalId);
		if(scmKcDrugDestructionMaster == null) {
			throw new RuntimeException("未查询到审批数据");
		}
		String endEvent = jsonNode.get("eventType").asText();
		if ("agree".equals(actionName) && "endEvent".equals(endEvent)) {
			//审批状态调整为通过
			scmKcDrugDestructionMaster.setApprovalState("2");
			List<ScmKcDrugDestruction> byMainId = scmKcDrugDestructionDao.getByMainId(scmKcDrugDestructionMaster.getId());
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			for (ScmKcDrugDestruction scmKcDrugDestruction : byMainId) {
				Map map = new HashMap<>();
				map.put("productName", scmKcDrugDestruction.getProductName());
				map.put("productCode", scmKcDrugDestruction.getProductCode());
				map.put("productState", scmKcDrugDestruction.getProductState());
				map.put("productSum", scmKcDrugDestruction.getProductSum());
				map.put("unitPrice", scmKcDrugDestruction.getUnitPrice());
				map.put("medicineType", scmKcDrugDestruction.getMedicineType());
				map.put("manufacturer", scmKcDrugDestruction.getManufacturer());
				map.put("batchNum", scmKcDrugDestruction.getBatchNum());
				map.put("expiryDate", scmKcDrugDestruction.getExpiryDate());
				map.put("priceSum", scmKcDrugDestruction.getPriceSum());
				map.put("aChage", scmKcDrugDestruction.getaChage());
				map.put("number", scmKcDrugDestruction.getNumber());
				map.put("unqualifiedReason", scmKcDrugDestruction.getUnqualifiedReason());
				map.put("agentType", scmKcDrugDestruction.getAgentType());
				map.put("approvalCode", scmKcDrugDestruction.getApprovalCode());
				map.put("businessScope", scmKcDrugDestruction.getBusinessScope());
				map.put("medicineClassify", scmKcDrugDestruction.getMedicineClassify());
				map.put("commonName", scmKcDrugDestruction.getCommonName());
				map.put("unit", scmKcDrugDestruction.getUnit());
				list.add(map);
			}
			Map<String,Object> masterMap = new HashMap<String,Object>();
			masterMap.put("destructionCode", scmKcDrugDestructionMaster.getDestructionCode());
			masterMap.put("varietySum", scmKcDrugDestructionMaster.getVarietySum());
			masterMap.put("priceSum", scmKcDrugDestructionMaster.getPriceSum());
			masterMap.put("productNum", scmKcDrugDestructionMaster.getProductNum());
			masterMap.put("destructionMethod", scmKcDrugDestructionMaster.getDestructionMethod());
			masterMap.put("destructionDate", scmKcDrugDestructionMaster.getDestructionDate());
			masterMap.put("destructionAddress", scmKcDrugDestructionMaster.getDestructionAddress());
			masterMap.put("conveyance", scmKcDrugDestructionMaster.getConveyance());
			masterMap.put("qualityIdea", scmKcDrugDestructionMaster.getQualityIdea());
			masterMap.put("companyPrincipalIdea", scmKcDrugDestructionMaster.getCompanyPrincipalIdea());
			masterMap.put("superintendent", scmKcDrugDestructionMaster.getSuperintendent());
			masterMap.put("destructionPersion", scmKcDrugDestructionMaster.getDestructionPersion());
			masterMap.put("createDate", scmKcDrugDestructionMaster.getCreateDate());
			masterMap.put("createPersion", scmKcDrugDestructionMaster.getCreatepersion());
			masterMap.put("memo", scmKcDrugDestructionMaster.getMemo());
			masterMap.put("ownerId", scmKcDrugDestructionMaster.getOwnerId());
			String jsonString = JSON.toJSONString(masterMap);
			ScmPuthWmsUtil.puth(jsonString, "未提供接口","不合格药品销毁推送wms");
			scmKcDrugDestructionMaster = get(scmKcDrugDestructionMaster.getId());
			scmKcDrugDestructionMaster.setIsManageSap("0");
			update(scmKcDrugDestructionMaster);
		} else if ("agree".equals(actionName)) {
		} else if ("reject".equals(actionName)) {
		} else if ("backToStart".equals(actionName)) {
		} else if ("opposeTrans".equals(actionName)) {
		} else if ("endProcess".equals(actionName)) {
			scmKcDrugDestructionMaster.setApprovalState("3");
			scmKcDrugDestructionMaster = get(scmKcDrugDestructionMaster.getId());
			scmKcDrugDestructionMaster.setIsManageSap("0");
			update(scmKcDrugDestructionMaster);
		}

	}


	@Override
	public ScmKcDrugDestructionMaster getByApprovalId(String approvalId) {
		ScmKcDrugDestructionMaster scmKcDrugDestructionMaster = scmKcDrugDestructionMasterDao.getByApprovalId(approvalId);
		if(scmKcDrugDestructionMaster == null) {
			throw new RuntimeException("未查询到审批数据");
		}
		ScmKcDrugDestructionMaster scmKcDrugDestructionMaster2 = get(scmKcDrugDestructionMaster.getId());
		return scmKcDrugDestructionMaster2;
	}

}
