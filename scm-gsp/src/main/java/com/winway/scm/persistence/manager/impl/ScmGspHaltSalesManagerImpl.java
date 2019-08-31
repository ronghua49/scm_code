package com.winway.scm.persistence.manager.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.hotent.base.feign.BpmRuntimeFeignService;
import com.hotent.base.modelvo.CustomStartResult;
import com.hotent.base.modelvo.StartFlowParam;
import com.hotent.base.util.UniqueIdUtil;
import com.winway.purchase.util.QuarterUtil;
import com.winway.purchase.util.ScmPuthWmsUtil;
import com.winway.purchase.vo.ScmWmsReceipt;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.model.ScmGspHaltSales;
import com.winway.scm.persistence.dao.ScmGspHaltSalesDao;
import com.winway.scm.persistence.manager.ScmGspHaltSalesManager;
import org.springframework.transaction.annotation.Transactional;

/**
 * <pre>
 * 描述：药品停售通知 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-24 12:00:21
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmGspHaltSalesManager")
public class ScmGspHaltSalesManagerImpl extends AbstractManagerImpl<String, ScmGspHaltSales> implements ScmGspHaltSalesManager {
    @Resource
    ScmGspHaltSalesDao scmGspHaltSalesDao;
    @Resource
    BpmRuntimeFeignService bpmRuntimeFeignService;

    @Override
    protected MyBatisDao<String, ScmGspHaltSales> getDao() {
        return scmGspHaltSalesDao;
    }


    @Override
    public void create(ScmGspHaltSales entity) {
    	entity.setHaltCode(QuarterUtil.getCode("TSDH"));
        super.create(entity);
//		一个批号创建一个停售商品
//      String batchNumber = entity.getBatchNumber();
//		不能这么搞,审批详情没法玩
//      String[] split = batchNumber.split(",");
//      for (String s : split) {
//            ScmGspHaltSales haltSales = new ScmGspHaltSales();
//            haltSales.setIsSMS(entity.getIsSMS());
//            haltSales.setRelieveHaltSalesMemo(entity.getRelieveHaltSalesMemo());
//            haltSales.setRelieveHaltSalesState(entity.getRelieveHaltSalesState());
//            haltSales.setRelieveHaltSalesDate(entity.getRelieveHaltSalesDate());
//            haltSales.setApprovalState(entity.getApprovalState());
//            haltSales.setState(entity.getState());
//            haltSales.setCreateDate(entity.getCreateDate());
//            haltSales.setCreatePersion(entity.getCreatePersion());
//            haltSales.setAgentType(entity.getAgentType());
//            haltSales.setBusinessScope(entity.getBusinessScope());
//            haltSales.setCommonName(entity.getCommonName());
//            haltSales.setInventorySum(entity.getInventorySum());
//            haltSales.setApprovalCode(entity.getApprovalCode());
//            haltSales.setManufacturer(entity.getManufacturer());
//            haltSales.setMedicineClassify(entity.getMedicineClassify());
//            haltSales.setMedicineType(entity.getMedicineType());
//            haltSales.setOwnerId(entity.getOwnerId());
//            haltSales.setUnit(entity.getUnit());
//            haltSales.setProductState(entity.getProductState());
//            haltSales.setProductCode(entity.getProductCode());
//            haltSales.setProductName(entity.getProductName());
//            haltSales.setHaltSalesMemo(entity.getHaltSalesMemo());
//            haltSales.setHaltSalesState(entity.getHaltSalesState());
//            haltSales.setHaltSalesDate(entity.getHaltSalesDate());
//            haltSales.setOwnerId(entity.getOwnerId());
//            haltSales.setBatchNumber(s);
//        }
    }

/*    @Override
    public void update(ScmGspHaltSales entity) {
        scmGspHaltSalesDao.removeByProCode(entity.getProductCode());
        create(entity);
    }*/

    @Transactional
    @Override
    public void sendApply(ScmGspHaltSales scmGspHaltSales) {
        ScmGspHaltSales scmGspHaltSales1 = scmGspHaltSalesDao.get(scmGspHaltSales.getId());
        StartFlowParam startFlowParam = new StartFlowParam("yptssq", "SCM", "approvalId");
        startFlowParam.setFormType("frame");
        CustomStartResult customStartResult = null;
        try {
            System.out.println("发起药品停售申请");
            customStartResult = bpmRuntimeFeignService.customStart(startFlowParam);
            String approvalId = customStartResult.getInstId();
            scmGspHaltSales.setApprovalId(approvalId);
            scmGspHaltSales.setApprovalState("1");
            if (scmGspHaltSales1 == null) {
                create(scmGspHaltSales);
            } else {
                update(scmGspHaltSales);
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
        //根据审批id查询数据
        ScmGspHaltSales byApprovalState = scmGspHaltSalesDao.getByApprovalState(approvalId);
        if (byApprovalState == null) {
            throw new RuntimeException("未查询到业务数据,处理异常");
        }
        String endEvent = jsonNode.get("eventType").asText();
        if ("agree".equals(actionName) && "endEvent".equals(endEvent)) {
        	 byApprovalState.setApprovalState("2");
             try {
             	String batchNumber = byApprovalState.getBatchNumber();
             	String[] split = batchNumber.split(",");
             	for (String string : split) {
 					Map<String,Object> map = new HashMap<String,Object>();
 					map.put("productName", byApprovalState.getProductName());
 					map.put("productState", byApprovalState.getProductState());
 					map.put("unit", byApprovalState.getUnit());
 					map.put("manufacturer", byApprovalState.getManufacturer());
 					map.put("quantityInStock", byApprovalState.getInventorySum());
 					map.put("medicineType", byApprovalState.getMedicineType());
 					map.put("batchNum",string );
 					map.put("haltSalesDate", byApprovalState.getHaltSalesDate());
 					map.put("haltSalesExplain", byApprovalState.getHaltSalesState());
 					map.put("haltSalesMemo", byApprovalState.getHaltSalesMemo());
 					map.put("ownerId", byApprovalState.getOwnerId());
 					map.put("productCode", byApprovalState.getCode());
 					String json = JSON.toJSONString(map);
             		boolean puth = ScmPuthWmsUtil.puth(json, "/prolog_aomei_interface/interface/haltSales/item/scmItemHaltSalesNotify.json","停售药品推送wms");
             		if(!puth) {
             			byApprovalState.setIsSMS("1");
             		}else{
             			byApprovalState.setIsSMS("0");
             		}
 				}
             	
             } catch (Exception e) {
             	byApprovalState.setIsSMS("0");
                e.printStackTrace();
             }
             scmGspHaltSalesDao.update(byApprovalState);
        } else if ("agree".equals(actionName)) {
        } else if ("reject".equals(actionName)) {
        } else if ("backToStart".equals(actionName)) {
        } else if ("opposeTrans".equals(actionName)) {
        } else if ("endProcess".equals(actionName)) {
        	byApprovalState.setApprovalState("3");
        	scmGspHaltSalesDao.update(byApprovalState);
        }
    }

    @Transactional
    @Override
    public void sendApplyByRelieve(ScmGspHaltSales scmGspHaltSales) {
        ScmGspHaltSales scmGspHaltSales1 = scmGspHaltSalesDao.get(scmGspHaltSales.getId());
        if (scmGspHaltSales1 == null) {
            throw new RuntimeException("数据传输错误，没有查询到要解除停售的药品");
        }
        if ("1".equals(scmGspHaltSales1.getApprovalState())) {
            throw new RuntimeException("该数据正在审批中，不得二次申请");
        }
        StartFlowParam startFlowParam = new StartFlowParam("jcyptssq", "SCM", "approvalId");
        startFlowParam.setFormType("frame");
        CustomStartResult customStartResult = null;
        try {
            System.out.println("发起解除药品停售申请");
            customStartResult = bpmRuntimeFeignService.customStart(startFlowParam);
            String approvalId = customStartResult.getInstId();
            scmGspHaltSales1.setApprovalState("1");
            scmGspHaltSales1.setApprovalId(approvalId);
            scmGspHaltSales1.setRelieveHaltSalesDate(scmGspHaltSales.getRelieveHaltSalesDate());
            scmGspHaltSales1.setRelieveHaltSalesState(scmGspHaltSales.getRelieveHaltSalesState());
            scmGspHaltSales1.setRelieveHaltSalesMemo(scmGspHaltSales.getRelieveHaltSalesMemo());
            super.update(scmGspHaltSales1);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("启动工作流失败");
        }
    }

    @Override
    public void endApplyByRelieve(JsonNode jsonNode) {
        String approvalId = jsonNode.get("instId").asText();
        String actionName = jsonNode.get("actionName").asText();
        //根据审批id查询数据
        ScmGspHaltSales byApprovalState = scmGspHaltSalesDao.getByApprovalState(approvalId);
        if (byApprovalState == null) {
            throw new RuntimeException("未查询到业务数据,处理异常");
        }
        String endEvent = jsonNode.get("eventType").asText();
        if ("agree".equals(actionName) && "endEvent".equals(endEvent)) {
        	byApprovalState.setState("1");
            byApprovalState.setApprovalState("2");
            try {
            	String batchNumber = byApprovalState.getBatchNumber();
            	String[] split = batchNumber.split(",");
            	for (String string : split) {
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("productName", byApprovalState.getProductName());
					map.put("productState", byApprovalState.getProductState());
					map.put("unit", byApprovalState.getUnit());
					map.put("manufacturer", byApprovalState.getManufacturer());
					map.put("quantityInStock", byApprovalState.getInventorySum());
					map.put("medicineType", byApprovalState.getMedicineType());
					map.put("batchNum",string );
					map.put("haltSalesDate", byApprovalState.getHaltSalesDate());
					map.put("haltSalesExplain", byApprovalState.getHaltSalesState());
					map.put("haltSalesMemo", byApprovalState.getHaltSalesMemo());
					map.put("ownerId", byApprovalState.getOwnerId());
					map.put("relieveHaltSalesDate", byApprovalState.getRelieveHaltSalesDate());
					map.put("relieveHaltSalesExplain", byApprovalState.getRelieveHaltSalesState());
					map.put("relieveHaltSalesMemo", byApprovalState.getRelieveHaltSalesMemo());
					map.put("productCode", byApprovalState.getCode());
					String json = JSON.toJSONString(map);
            		boolean puth = ScmPuthWmsUtil.puth(json, "/prolog_aomei_interface/interface/haltSales/item/scmItemHaltSalesNotify.json","解除药品停售推送wms");
            		if(!puth) {
            			byApprovalState.setIsSMS("1");
            		}else{
            			byApprovalState.setIsSMS("0");
            		}
				}
            } catch (Exception e) {
            	byApprovalState.setIsSMS("0");
                e.printStackTrace();
            }
            scmGspHaltSalesDao.update(byApprovalState);
        } else if ("agree".equals(actionName)) {
        } else if ("reject".equals(actionName)) {
        } else if ("backToStart".equals(actionName)) {
        } else if ("opposeTrans".equals(actionName)) {
        } else if ("endProcess".equals(actionName)) {
        	 byApprovalState.setApprovalState("3");
        	 scmGspHaltSalesDao.update(byApprovalState);
        }
    }

    @Override
    public ScmGspHaltSales getDetail(String approvalId) {
        ScmGspHaltSales byApprovalState = scmGspHaltSalesDao.getByApprovalState(approvalId);
        if (byApprovalState == null) {
            throw new RuntimeException("没有查询到停售审批数据");
        }
        return get(byApprovalState.getId());
    }


	@Override
	public void receipt(ScmWmsReceipt scmWmsReceipt) {
		ScmGspHaltSales scmGspHaltSales = scmGspHaltSalesDao.getByOrderCode(scmWmsReceipt.getOrderCode());
		if(scmGspHaltSales == null) {
			throw new RuntimeException("未查询到单据信息");
		}
		if("1".equals(scmWmsReceipt.getTypt())) {
			scmGspHaltSales.setIsAffirmWms("1");
		}else if("2".equals(scmWmsReceipt.getTypt())) {
			scmGspHaltSales.setIsAffirmWms("2");
		}else{
			throw new RuntimeException("请提交正确的状态码");
		}
		scmGspHaltSalesDao.update(scmGspHaltSales);
	}
    
    
    
}
