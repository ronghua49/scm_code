package com.winway.scm.persistence.manager.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.hotent.base.query.*;
import com.winway.purchase.feign.ScmMasterDateFeignService;
import com.winway.purchase.persistence.manager.impl.WorkflowTemplate;
import com.winway.purchase.util.DateFormatter;
import com.winway.purchase.util.ExcelExportUtil;
import com.winway.scm.persistence.dao.*;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.pagehelper.PageHelper;
import com.hotent.base.annotation.Workflow;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.exception.SystemException;
import com.hotent.base.feign.BpmRuntimeFeignService;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.model.CommonResult;
import com.hotent.base.modelBpmVo.DefaultFmsBpmCheckTaskOpinion;
import com.hotent.base.modelvo.AgreeFlowParam;
import com.hotent.base.util.BeanUtils;
import com.winway.purchase.util.QuarterUtil;
import com.winway.purchase.util.ScmPuthWmsUtil;
import com.winway.purchase.vo.ScmWmsReceipt;
import com.winway.purchase.vo.ScmWmsReturnData;
import com.winway.scm.model.ScmFhShipmentsDatail;
import com.winway.scm.model.ScmFhShipmentsDatailMaster;
import com.winway.scm.model.ScmFhShipmentsMessage;
import com.winway.scm.model.ScmFhShipmentsTask;
import com.winway.scm.persistence.manager.ScmFhShipmentsDatailManager;
import com.winway.scm.persistence.manager.ScmFhShipmentsDatailMasterManager;
import com.winway.scm.persistence.manager.ScmFhShipmentsMessageManager;
import com.winway.scm.vo.SalesRecordVo;
import com.winway.scm.vo.ScmFhBatchNumber;
import com.winway.scm.vo.ScmFhInventoryPreempted;
import com.winway.scm.vo.ScmFhShipmentsDataiAndMessageVo;
import com.winway.scm.vo.ScmWmsShipReceipt;
import com.winway.scm.vo.ShipmentsDatailVo;

/**
 * <pre>
 * 描述：发货明细总表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-02-15 17:31:50
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmFhShipmentsDatailMasterManager")
public class ScmFhShipmentsDatailMasterManagerImpl extends AbstractManagerImpl<String, ScmFhShipmentsDatailMaster> implements ScmFhShipmentsDatailMasterManager {
    @Resource
    ScmFhShipmentsDatailMasterDao scmFhShipmentsDatailMasterDao;
    @Resource
    ScmFhShipmentsDatailDao scmFhShipmentsDatailDao;
    @Resource
    ScmFhShipmentsDatailManager scmFhShipmentsDatailManager;
    @Resource
    ScmFhShipmentsTaskDao scmFhShipmentsTaskDao;
    @Resource
    ScmFhShipmentsMessageManager scmFhShipmentsMessageManager;
    @Resource
    ScmFhShipmentsMessageDao scmFhShipmentsMessageDao;
    @Resource
    ScmMasterDateFeignService scmMasterDateFeignService;
    @Resource
    WorkflowTemplate workflowTemplate;
    @Resource
    BpmRuntimeFeignService bpmRuntimeFeignService;

    @Resource
    ScmFhLogisticsAgreementDao logisticsAgreementDao;

    @Override
    protected MyBatisDao<String, ScmFhShipmentsDatailMaster> getDao() {
        return scmFhShipmentsDatailMasterDao;
    }

    /**
     * 创建实体包含子表实体
     */
    public void create(ScmFhShipmentsDatailMaster scmFhShipmentsDatailMaster) {
        scmFhShipmentsDatailMaster.setShipmentsCode(QuarterUtil.getCode("FHZL"));
        scmFhShipmentsDatailMaster.setIsToSap("0");
        scmFhShipmentsDatailMaster.setIsToWms("0");
        scmFhShipmentsDatailMaster.setIsAffirmWms("0");
        super.create(scmFhShipmentsDatailMaster);
        String pk = scmFhShipmentsDatailMaster.getId();
        List<ScmFhShipmentsDatail> scmFhShipmentsDatailList = scmFhShipmentsDatailMaster.getScmFhShipmentsDatailList();
        for (ScmFhShipmentsDatail scmFhShipmentsDatail : scmFhShipmentsDatailList) {
            if (scmFhShipmentsDatail.getStock() == null || scmFhShipmentsDatail.getDeliveringAmount() == null || scmFhShipmentsDatail.getPlanShipmentsSum() == null) {
                throw new RuntimeException("库存量、计划发货量和者实际发货量不得为空");
            }
            if (scmFhShipmentsDatail.getDeliveringAmount() > scmFhShipmentsDatail.getStock()) {
                throw new RuntimeException("实际量不得大于库存数量");
            }
            if (scmFhShipmentsDatail.getDeliveringAmount() > scmFhShipmentsDatail.getPlanShipmentsSum()) {
                throw new RuntimeException("实际量不得大于发货量");
            }
            scmFhShipmentsDatail.setMasterId(pk);
            scmFhShipmentsDatail.setCommerceId(scmFhShipmentsDatailMaster.getCommerceId());
            scmFhShipmentsDatail.setDirectiveCode(QuarterUtil.getCode("ZLBH"));

            scmFhShipmentsDatailManager.create(scmFhShipmentsDatail);
            //todo 推送wms减库存
        }
    }

    /**
     * 删除记录包含子表记录
     */
    public void remove(String entityId) {
        super.remove(entityId);
        scmFhShipmentsDatailDao.delByMainId(entityId);
    }

    /**
     * 批量删除包含子表记录
     */
    public void removeByIds(String[] entityIds) {
        for (String id : entityIds) {
            this.remove(id);
        }
    }

    /**
     * 获取实体
     */
    public ScmFhShipmentsDatailMaster get(String entityId) {
        ScmFhShipmentsDatailMaster scmFhShipmentsDatailMaster = super.get(entityId);
        if (scmFhShipmentsDatailMaster == null) {
            return null;
        }
        List<ScmFhShipmentsDatail> scmFhShipmentsDatailList = scmFhShipmentsDatailDao.getByMainId(entityId);
        scmFhShipmentsDatailMaster.setScmFhShipmentsDatailList(scmFhShipmentsDatailList);
        return scmFhShipmentsDatailMaster;
    }

    /**
     * 更新实体同时更新子表记录
     */
    public void update(ScmFhShipmentsDatailMaster scmFhShipmentsDatailMaster) {
        String id = scmFhShipmentsDatailMaster.getId();
        ScmFhShipmentsDatailMaster shipmentsDatailMaster = scmFhShipmentsDatailMasterDao.get(id);
        if (shipmentsDatailMaster == null) {
            throw new RuntimeException("数据错误，没有查询到更新的数据");
        }
        scmFhShipmentsDatailMaster.setShipmentsCode(shipmentsDatailMaster.getShipmentsCode());
        //判断是发起申请和暂存
        scmFhShipmentsDatailMaster.setIsToSap("0");
        scmFhShipmentsDatailMaster.setIsToWms("0");
        scmFhShipmentsDatailMaster.setIsAffirmWms("0");
        super.update(scmFhShipmentsDatailMaster);
        List<ScmFhShipmentsDatail> byMainId = scmFhShipmentsDatailDao.getByMainId(id);
        for (ScmFhShipmentsDatail detail : byMainId) {
            //修复发货任务对应的计划发货量
            ScmFhShipmentsTask scmFhShipmentsTask = scmFhShipmentsTaskDao.get(detail.getShipmentsTaskId());
            if (scmFhShipmentsTask == null) {
                throw new RuntimeException("数据错误，没有查询到发货任务");
            }
            scmFhShipmentsTask.setPlanShipmentsSum(scmFhShipmentsTask.getPlanShipmentsSum() + detail.getDeliveringAmount());
            if (scmFhShipmentsTask.getPlanShipmentsSum() > 0) {
                scmFhShipmentsTask.setType("1");
            }
            scmFhShipmentsTaskDao.update(scmFhShipmentsTask);
            //todo 调用wms库存接口修复商品库存数量
        }
        scmFhShipmentsDatailDao.delByMainId(id);
        List<ScmFhShipmentsDatail> scmFhShipmentsDatailList = scmFhShipmentsDatailMaster.getScmFhShipmentsDatailList();
        for (ScmFhShipmentsDatail scmFhShipmentsDatail : scmFhShipmentsDatailList) {
            ScmFhShipmentsTask scmFhShipmentsTask = scmFhShipmentsTaskDao.get(scmFhShipmentsDatail.getShipmentsTaskId());
            if (scmFhShipmentsDatail.getDeliveringAmount() == null || scmFhShipmentsDatail.getPlanShipmentsSum() == null) {
                throw new RuntimeException("库存量、计划发货量和者实际发货量不得为空");
            }
            if (scmFhShipmentsDatail.getDeliveringAmount() > scmFhShipmentsTask.getPlanShipmentsSum()) {
                throw new RuntimeException("实际量不得大于发货量");
            }
            scmFhShipmentsDatail.setMasterId(id);
            scmFhShipmentsDatail.setCommerceId(scmFhShipmentsDatailMaster.getCommerceId());
            scmFhShipmentsDatail.setDirectiveCode(QuarterUtil.getCode("ZLBH"));
            scmFhShipmentsDatailManager.create(scmFhShipmentsDatail);
        }
    }

    @Override
    public ScmFhShipmentsDatailMaster getByApplyId(String applyid) {
        ScmFhShipmentsDatailMaster byApprovalState = scmFhShipmentsDatailMasterDao.getByApprovalState(applyid);
        if(byApprovalState==null){
            throw  new RuntimeException("没有查询到审批数据");
        }
        List<ScmFhShipmentsDatail> byMainId = scmFhShipmentsDatailDao.getByMainId(byApprovalState.getId());
        byApprovalState.setScmFhShipmentsDatailList(byMainId);
        return byApprovalState;
    }

    @Override
    public void sendApply(ScmFhShipmentsDatailMaster scmFhShipmentsDatailMaster) throws Exception {
        //判断主表中是否有数据
        ScmFhShipmentsDatailMaster sfdm = scmFhShipmentsDatailMasterDao.get(scmFhShipmentsDatailMaster.getId());
        scmFhShipmentsDatailMaster.setApprovalState("1");
        if (sfdm == null) {
            create(scmFhShipmentsDatailMaster);
        } else {
            //有数据,先判断是否已经有正在审核中的数据
            if ("1".equals(sfdm.getApprovalState())) {
                //数据在审批中,不可修改编辑
                throw new RuntimeException("当前数据已经在审批中,不可重复提交");
            }
            scmFhShipmentsDatailMaster = sfdm;
            scmFhShipmentsDatailMaster.setIsToSap("0");
            scmFhShipmentsDatailMaster.setIsAffirmWms("0");
            update(scmFhShipmentsDatailMaster);
        }
        
        List<ScmFhShipmentsDatail> byMainId = scmFhShipmentsDatailDao.getByMainId(scmFhShipmentsDatailMaster.getId());
        for (ScmFhShipmentsDatail scmFhShipmentsDatail : byMainId) {
            ScmFhInventoryPreempted scmFhInventoryPreempted = new ScmFhInventoryPreempted();
            scmFhInventoryPreempted.setBatchNumber(scmFhShipmentsDatail.getBatchNumber());
            scmFhInventoryPreempted.setCode(scmFhShipmentsDatail.getCode());
            scmFhInventoryPreempted.setOwnerid(scmFhShipmentsDatailMaster.getOwnerId());
            scmFhInventoryPreempted.setProductNumber(scmFhShipmentsDatail.getDeliveringAmount());
            scmFhInventoryPreempted.setWareHouseCode(scmFhShipmentsDatail.getHoustWareCode());
            scmFhInventoryPreempted.setWarehouseType(scmFhShipmentsDatail.getWarehouseType());
            scmFhInventoryPreempted.setXtlx(scmFhShipmentsDatail.getXtlx());
            boolean inventoryPreempted = inventoryPreempted(scmFhInventoryPreempted);
            if (!inventoryPreempted) {
                throw new RuntimeException("连接WMS异常,请稍后重试!");
            }
        }
        if((sfdm != null && "3".equals(sfdm.getApprovalState()))) {
//		      {"formType":"frame","opinion":"驳回后发起","actionName":"agree","taskId":11108798,"jumpType":"","destination":"","nodeUsers":"[]"}
		    List<String> list = new ArrayList<String>();
		    list.add(scmFhShipmentsDatailMaster.getApprovalId());
		    List<DefaultFmsBpmCheckTaskOpinion> instanceFlowHistoryList = bpmRuntimeFeignService.instanceFlowHistoryList(list);
		    if(instanceFlowHistoryList.size() == 0){
		    	//发起审批流
		        String[] strs = {};
		        workflowTemplate.startWorkflow("fhzlsp", "SCM", "approvalId",scmFhShipmentsDatailMaster, strs);
		        String replace = scmFhShipmentsDatailMaster.getApprovalId().replace("\"", "");
		        scmFhShipmentsDatailMaster.setApprovalId(replace);
		        update(scmFhShipmentsDatailMaster);
		    }else{
		    	DefaultFmsBpmCheckTaskOpinion defaultFmsBpmCheckTaskOpinion = instanceFlowHistoryList.get(instanceFlowHistoryList.size() -1 );
		    	bpmRuntimeFeignService.autoAgree(new AgreeFlowParam("驳回后发起", "agree", defaultFmsBpmCheckTaskOpinion.getTaskId(), "", "", "[]"));
		    	scmFhShipmentsDatailMaster.setApprovalId(sfdm.getApprovalId());
		    	update(scmFhShipmentsDatailMaster);
		    }
	    }else{
		    //发起审批流
		    //发起审批流 @Workflow(flowKey = "fhzlsp", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {})
	        String[] strs = {};
	        workflowTemplate.startWorkflow("fhzlsp", "SCM", "approvalId",scmFhShipmentsDatailMaster, strs);
	        String replace = scmFhShipmentsDatailMaster.getApprovalId().replace("\"", "");
	        scmFhShipmentsDatailMaster.setApprovalId(replace);
	        update(scmFhShipmentsDatailMaster);
	    }

    }

    public boolean inventoryPreempted(ScmFhInventoryPreempted scmFhInventoryPreempted) {
        try {
            //汇总库存
            ScmFhInventoryPreempted inventoryPreempted = scmFhShipmentsDatailMasterDao.inventoryPreempted(scmFhInventoryPreempted.getWareHouseCode(),
                    scmFhInventoryPreempted.getOwnerid(), scmFhInventoryPreempted.getXtlx(), scmFhInventoryPreempted.getWarehouseType(),
                    scmFhInventoryPreempted.getCode(), scmFhInventoryPreempted.getBatchNumber());
            Map map = new HashMap<>();
            map.put("productCode", scmFhInventoryPreempted.getCode());
            map.put("ownerId", scmFhInventoryPreempted.getOwnerid());
            map.put("warehouseCode", scmFhInventoryPreempted.getWareHouseCode());
            String jsonString = JSON.toJSONString(map);
            ScmWmsReturnData datPputh = ScmPuthWmsUtil.getDatPputh(jsonString, "/prolog_aomei_interface/interface/singleQuery/itemInventory/scmItemInventorySingleQuery.json");
            if (datPputh == null) {
                throw new RuntimeException("连接WMS异常,请稍后重试!");
            }
            List<String> data = datPputh.getData();
            List<ScmFhBatchNumber> list1 = new ArrayList<ScmFhBatchNumber>();
            for (String string : data) {
                ScmFhBatchNumber parseObject = JSON.parseObject(string, ScmFhBatchNumber.class);
                list1.add(parseObject);
            }
            if (list1.size() == 0) {
                throw new RuntimeException("商品子码:" + scmFhInventoryPreempted.getCode() + ",批号:" + scmFhInventoryPreempted.getBatchNumber() + "未获取到库存");
            }
            //未查询到库存占用
            if (inventoryPreempted == null) {
                return true;
            }
            //查询有预占库存,计算下单加预占库存数量是否大于当前库存量
            int productNumber = inventoryPreempted.getProductNumber();
            for (ScmFhBatchNumber scmFhBatchNumber : list1) {
                if (scmFhBatchNumber.getWarehouseCode().equals(inventoryPreempted.getWareHouseCode())) {
                    //仓库编码相同
                    if (scmFhBatchNumber.getBatchNum().equals(inventoryPreempted.getBatchNumber())) {
                        //批号相同
                        if (scmFhBatchNumber.getXtlx().equals(inventoryPreempted.getXtlx())) {
                            //箱体类型相同
                            if (scmFhBatchNumber.getWarehouseType().equals(inventoryPreempted.getWarehouseType())) {
                                //全部相同
                                if (Integer.parseInt(scmFhBatchNumber.getQuantityInStock()) >= productNumber) {
                                    //wms库存大于发货量
                                    return true;
                                } else {
                                    throw new RuntimeException("商品子码:" + scmFhInventoryPreempted.getCode() + ",批号:" + scmFhInventoryPreempted.getBatchNumber() + "库存被占用");
                                }
                            }
                        }
                    }
                }
            }
            throw new RuntimeException("商品子码:" + scmFhInventoryPreempted.getCode() + ",批号:" + scmFhInventoryPreempted.getBatchNumber() + "未匹配到库存");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void endApply(JsonNode jsonNode) {
        String approvalId = jsonNode.get("instId").asText();
        String actionName = jsonNode.get("actionName").asText();
        //根据审批id查询数据
        ScmFhShipmentsDatailMaster sfdm = scmFhShipmentsDatailMasterDao.getByApprovalState(approvalId);
        if (sfdm == null) {
            throw new RuntimeException("未查询到业务数据,处理异常");
        }
        String endEvent = jsonNode.get("eventType").asText();
        if ("agree".equals(actionName) && "endEvent".equals(endEvent)) {
            List<ScmFhShipmentsDatail> byMainId = scmFhShipmentsDatailDao.getByMainId(sfdm.getId());
            //通过之后创建物流发运信息记录
            sfdm = get(sfdm.getId());
            sfdm.setApprovalState("2");
            sfdm.setSuccessDate(new Date());
            List<ScmFhShipmentsMessage> messageList = new ArrayList<>();
            for (ScmFhShipmentsDatail scmFhShipmentsDatail : byMainId) {
                List<ScmFhShipmentsMessage> byMainId1 = scmFhShipmentsMessageDao.getByMainId(scmFhShipmentsDatail.getId());
                if (byMainId1 == null || byMainId1.isEmpty()) {
                    //每条发货商品记录生成一个物流发运信息记录
                    ScmFhShipmentsMessage scmFhShipmentsMessage = new ScmFhShipmentsMessage();
                    scmFhShipmentsMessage.setShipmentsDatailId(scmFhShipmentsDatail.getId());
                    //查询供应商地址
                    String warehouse = scmFhShipmentsDatail.getWarehouse();
                    if(warehouse.startsWith("海口")){
                        warehouse = "海口市";
                    }else if(warehouse.startsWith("苏州")){
                        warehouse = "苏州市";
                    }
                    //查询经销商地址
                    String shippingAddress = scmFhShipmentsDatail.getShippingAddress();
                    shippingAddress = shippingAddress.substring(shippingAddress.indexOf("省")+1,shippingAddress.indexOf("市")+1);
                    //根据两个地址查询协议到货天数
                    String agerrmentDay  = logisticsAgreementDao.getAgreementDays(warehouse,shippingAddress);
                    if(agerrmentDay!=null){
                        scmFhShipmentsMessage.setAgreementDays(Integer.parseInt(agerrmentDay));
                    }else{
                        scmFhShipmentsMessage.setAgreementDays(7);
                    }
                    messageList.add(scmFhShipmentsMessage);
                    scmFhShipmentsMessageManager.create(scmFhShipmentsMessage);
                }
                //确定发货时间 更新详情
                scmFhShipmentsDatail.setShipmentsAffirmDate(new Date());
                scmFhShipmentsDatailDao.update(scmFhShipmentsDatail);
            }
            //todo 推送发货单到wms
            try {
                List<Map<String, Object>> productList = new ArrayList<Map<String, Object>>();
                for (ScmFhShipmentsDatail scmFhShipmentsDatail : byMainId) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("directiveCode", scmFhShipmentsDatail.getDirectiveCode());
                    map.put("taskType", scmFhShipmentsDatail.getTaskType());
                    map.put("dateCode", scmFhShipmentsDatail.getDateCode());
                    map.put("focusShipmentsMonth", scmFhShipmentsDatail.getFocusShipmentsMonth());
                    map.put("shipmentsAffirmDate", scmFhShipmentsDatail.getShipmentsAffirmDate());
                    map.put("commerceName", scmFhShipmentsDatail.getCommerceName());
                    map.put("commerceCode", sfdm.getCommerceCode());
                    map.put("productName", scmFhShipmentsDatail.getProductName());
                    map.put("productNum", scmFhShipmentsDatail.getCode());
                    map.put("productCode", scmFhShipmentsDatail.getCode());
                    map.put("MedicineType", scmFhShipmentsDatail.getMedicineType());
                    map.put("productState", scmFhShipmentsDatail.getProductState());
                    map.put("aChange", scmFhShipmentsDatail.getaChange());
                    map.put("batchNumber", scmFhShipmentsDatail.getBatchNumber());
                    map.put("price", scmFhShipmentsDatail.getPrice());
                    map.put("priceSum", scmFhShipmentsDatail.getPriceSum());
                    map.put("memo", scmFhShipmentsDatail.getMemo());
                    map.put("planShipmentsSum", scmFhShipmentsDatail.getPlanShipmentsSum());
                    map.put("deliveringAmount", scmFhShipmentsDatail.getDeliveringAmount());
                    map.put("warehouse", scmFhShipmentsDatail.getWarehouse());
                    map.put("contactPersion", scmFhShipmentsDatail.getContactPersion());
                    map.put("phone", scmFhShipmentsDatail.getPhone());
                    map.put("shippingAddress", scmFhShipmentsDatail.getShippingAddress());
                    map.put("houstWareCode", scmFhShipmentsDatail.getHoustWareCode());
                    map.put("xtlx", scmFhShipmentsDatail.getXtlx());
                    map.put("warehouseType", scmFhShipmentsDatail.getWarehouseType());
                    productList.add(map);
                }
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("shipmentsCode", sfdm.getShipmentsCode());
                map.put("businessDivision", sfdm.getBusinessDivision());
                map.put("province", sfdm.getProvince());
                map.put("commerceName", sfdm.getCommerceName());
                String getcommerceFirstById = scmMasterDateFeignService.getcommerceFirstById(sfdm.getCommerceId());
                JSONObject parseObject = JSON.parseObject(getcommerceFirstById);
                map.put("commerceCode", parseObject.getString("commerceCode"));
                map.put("type", "Do_Norm");
                map.put("successDate", sfdm.getSuccessDate());
                map.put("ownerId", sfdm.getOwnerId());
                map.put("productList", productList);
                String jsonString = JSON.toJSONString(map);
                boolean puth = ScmPuthWmsUtil.puth(jsonString, "/prolog_aomei_interface/interface/outOrder/outbound/scmOutboundOrderNotify.json","发货单推送到wms");
                if (puth) {
                    sfdm.setIsToWms("1");
                    sfdm.setCanInvoice("1");
                } else {
                    sfdm.setIsToWms("0");
                    sfdm.setCanInvoice("0");
                }
            } catch (Exception e) {
                sfdm.setIsToWms("0");
                sfdm.setCanInvoice("0");
            }
            scmFhShipmentsDatailMasterDao.update(sfdm);
        } else if ("agree".equals(actionName)) {
        } else if ("reject".equals(actionName)) {
        } else if ("backToStart".equals(actionName)) {
        	sfdm.setApprovalState("3");
            scmFhShipmentsDatailMasterDao.update(sfdm);
        } else if ("opposeTrans".equals(actionName)) {
        } else if ("endProcess".equals(actionName)) {
        }

    }

    @Override
    public PageList<ScmFhShipmentsDataiAndMessageVo> allShipmentMessage(QueryFilter queryFilter) {
        PageBean pageBean = queryFilter.getPageBean();
        if (BeanUtils.isEmpty(pageBean)) {
            PageHelper.startPage(1, Integer.MAX_VALUE, false);
        } else {
            PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
        }
        List<ScmFhShipmentsDataiAndMessageVo> query = scmFhShipmentsDatailMasterDao.allShipmentMessage(queryFilter.getParams());
        return new PageList<ScmFhShipmentsDataiAndMessageVo>(query);
    }


    public PageList<ScmFhShipmentsDatailMaster> queryReturnVo(QueryFilter queryFilter) throws SystemException {
        PageBean pageBean = queryFilter.getPageBean();
        if (BeanUtils.isEmpty(pageBean)) {
            PageHelper.startPage(1, 2147483647, false);
        } else {
            PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
        }
        List<ShipmentsDatailVo> query = scmFhShipmentsDatailMasterDao.queryReturnVo(queryFilter.getParams());
        Set<String> ids = new HashSet<>();
        for (ShipmentsDatailVo vo : query) {
            ids.add(vo.getMasterId());
        }
        List<ScmFhShipmentsDatailMaster> masterList = new ArrayList<>();
        if (ids.size() == 0) {
            return new PageList<ScmFhShipmentsDatailMaster>(masterList);
        } else {
            masterList = scmFhShipmentsDatailMasterDao.getByIds(ids);
            return new PageList<ScmFhShipmentsDatailMaster>(masterList);
        }
    }

    @Override
    public PageList<SalesRecordVo> salesRecord(QueryFilter queryFilter) {
        PageBean pageBean = queryFilter.getPageBean();
        if (BeanUtils.isEmpty(pageBean)) {
            PageHelper.startPage(1, Integer.MAX_VALUE, false);
        } else {
            PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
        }
        List<SalesRecordVo> query = scmFhShipmentsDatailMasterDao.salesRecord(queryFilter.getParams());
        return new PageList<SalesRecordVo>(query);
    }

    @Override
    public String canInvoice(String shipmentsCode) {
//        try {
//            ScmFhShipmentsDatailMaster shipmentsDatailMaster = scmFhShipmentsDatailMasterDao.getByCode(shipmentsCode);
//            shipmentsDatailMaster.setCanInvoice("1");
//            scmFhShipmentsDatailMasterDao.update(shipmentsDatailMaster);
//            return Boolean.toString(true);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Boolean.toString(false);
//        }
        return null;
    }

    @Override
    public ScmFhShipmentsDatailMaster getShipmentsDatailMasterById(String id) {
        ScmFhShipmentsDatailMaster scmFhShipmentsDatailMaster = scmFhShipmentsDatailMasterDao.getShipmentsDatailMasterById(id);
        if (scmFhShipmentsDatailMaster == null) {
            return null;
        }
        List<ScmFhShipmentsDatail> scmFhShipmentsDatailList = scmFhShipmentsDatailDao.getByMainId(id);
        scmFhShipmentsDatailMaster.setScmFhShipmentsDatailList(scmFhShipmentsDatailList);
        return scmFhShipmentsDatailMaster;
    }

    @Override
    public Map<String, Integer> getShipmentsHistory(String commerceId, String ownerId) throws ParseException {
        List<ScmFhShipmentsDatail> scmFhShipmentsDatailList = scmFhShipmentsDatailMasterDao.getShipmentDatailByCommerceIdAndOwnerId(commerceId, ownerId);
        Map<String, Integer> map = new HashMap<>();
        SimpleDateFormat format = new SimpleDateFormat(DateFormatter.PATTERN_yyyy_MM_dd_HH_mm_ss);
        Date parse = format.parse(Calendar.getInstance().get(Calendar.YEAR) + "-01-01 00:00:00");
        Date lastYaer = DateFormatter.previousYear(parse);
        Date beforeYear = DateFormatter.previousYear(lastYaer);
        Date bigBeforeYear = DateFormatter.previousYear(beforeYear);
        Double beforeYearPrice = 0.0;
        Double lastYearPrice = 0.0;
        for (ScmFhShipmentsDatail datail : scmFhShipmentsDatailList) {
            Date shipmentsAffirmDate = datail.getShipmentsAffirmDate();

            if (shipmentsAffirmDate.before(beforeYear) && shipmentsAffirmDate.after(bigBeforeYear)) {
                beforeYearPrice += datail.getPriceSum();
            }
            if (shipmentsAffirmDate.before(new Date()) && shipmentsAffirmDate.after(lastYaer)) {
                lastYearPrice += datail.getPriceSum();
            }
        }
        map.put("beforeYearPrice", (int) Math.round(beforeYearPrice / 120000));
        map.put("lastYearPrice", (int) Math.round(lastYearPrice / 120000));
        return map;
    }

    @Override
    public void affirm(ScmWmsReceipt scmWmsReceipt) {
        ScmFhShipmentsDatailMaster byCode = scmFhShipmentsDatailMasterDao.getByCode(scmWmsReceipt.getShipmentsCode());
        if (byCode == null) {
            throw new RuntimeException("请传入正确的订单号");
        }
        byCode.setIsAffirmWms("1");
        scmFhShipmentsDatailMasterDao.update(byCode);
    }

    @Override
    public void receipt(ScmWmsShipReceipt[] scmWmsShipReceipt) {
        for (ScmWmsShipReceipt scmWmsShipReceipt2 : scmWmsShipReceipt) {
            ScmFhShipmentsDatailMaster scmFhShipmentsDatailMaster = scmFhShipmentsDatailMasterDao.getByOrderCode(scmWmsShipReceipt2.getOrderCode());
            if (scmFhShipmentsDatailMaster == null) {
                throw new RuntimeException("请传入正确的订单号");
            }
            List<ScmFhShipmentsDatail> byMainId2 = scmFhShipmentsDatailDao.getByMainId(scmFhShipmentsDatailMaster.getId());
            for (ScmFhShipmentsDatail scmFhShipmentsDatail2 : byMainId2) {
                if ("1".equals(scmWmsShipReceipt2.getType())) {
                    List<ScmFhShipmentsMessage> byMainId = scmFhShipmentsMessageDao.getByMainId(scmFhShipmentsDatail2.getId());
                    ScmFhShipmentsMessage scmFhShipmentsMessage = byMainId.get(0);
                    scmFhShipmentsMessage.setLogisticsCode(scmWmsShipReceipt2.getLogisticsCode());
                    scmFhShipmentsMessage.setActualDeliveryDate(scmWmsShipReceipt2.getActualDeliveryDate());
                    scmFhShipmentsMessage.setTransportationMethod(scmWmsShipReceipt2.getTransportationMethod());
                    scmFhShipmentsMessage.setExceptionCase(scmWmsShipReceipt2.getExceptionCase());
                    scmFhShipmentsMessage.setExplain(scmWmsShipReceipt2.getExplain());
                    scmFhShipmentsMessage.setDriver(scmWmsShipReceipt2.getDriver());
                    scmFhShipmentsMessage.setLicencePlate(scmWmsShipReceipt2.getLicencePlate());
                    scmFhShipmentsMessage.setEntrustPersion(scmWmsShipReceipt2.getEntrustPersion());
                    scmFhShipmentsMessageDao.update(scmFhShipmentsMessage);
                } else {
                    throw new RuntimeException("请传入正确的状态");
                }
            }
            scmFhShipmentsDatailMaster.setIsAffirmWms("3");
            scmFhShipmentsDatailMasterDao.update(scmFhShipmentsDatailMaster);
        }


    }

    @Override
    public void updateShipmentDatailCanInvoice(String id, String type) {
        ScmFhShipmentsDatailMaster scmFhShipmentsDatailMaster = scmFhShipmentsDatailMasterDao.get(id);
        if (scmFhShipmentsDatailMaster == null) {
            throw new RuntimeException("未查询到发货数据");
        }
        if ("0".equals(scmFhShipmentsDatailMaster.getCanInvoice())) {
            throw new RuntimeException("当前发货单不可开票");
        }
        if ("2".equals(scmFhShipmentsDatailMaster.getCanInvoice())) {
            throw new RuntimeException("当前发货单已开票");
        }
        if ("3".equals(scmFhShipmentsDatailMaster.getCanInvoice())) {
            throw new RuntimeException("当前发货单正在开票中");
        }
        scmFhShipmentsDatailMaster.setCanInvoice(type);
        scmFhShipmentsDatailMasterDao.update(scmFhShipmentsDatailMaster);
    }

    @Override
    public void export(QueryFilter queryFilter, boolean isPage, HttpServletResponse response) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<List<String>> data = new ArrayList<List<String>>();
        String[] tableName = {"审批状态", "合同编码", "指令编号", "发货编号", "仓库名", "发货时间", "销售类型", "商业名称", "收货地址", "联系人", "联系电话", "制单人", "存货代码",
                "存货名称", "发货数量", "件装量", "件数", "批号", "生产日期", "失效日期", "医药注册号", "价税合计", "备注", "物流承运商", "物流单号", "仓库实际发货日期", "预计到货日期", "实际到货日期", "配送确认到货日期",
                "配送确认备注", "实收件数", "异常情况", "说明", "协议到货天数", "延误天数", "运输方式", "委托经办人", "驾驶员", "车牌号"};
        PageList<ScmFhShipmentsDataiAndMessageVo> query = new PageList<>();
        List<FieldSort> sorter = new ArrayList<>();
        FieldSort fieldSort = new FieldSort();
        fieldSort.setProperty("shipmentsAffirmDate");
        fieldSort.setDirection(Direction.DESC);
        sorter.add(fieldSort);
        // 如果不分页
        if (!isPage) {
            QueryFilter build = QueryFilter.build();
            build.setSorter(sorter);
            PageHelper.startPage(1, Integer.MAX_VALUE, true);
            List<ScmFhShipmentsDataiAndMessageVo> query1 = scmFhShipmentsDatailMasterDao.allShipmentMessage(queryFilter.getParams());
            query = new PageList(query1);
        } else {
            queryFilter.setSorter(sorter);
            PageBean pageBean = queryFilter.getPageBean();
            PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
            List<ScmFhShipmentsDataiAndMessageVo> query1 = scmFhShipmentsDatailMasterDao.allShipmentMessage(queryFilter.getParams());
            query = new PageList<>(query1);
        }
        List<ScmFhShipmentsDataiAndMessageVo> rows = query.getRows();
        for (int i = 0; i < rows.size(); i++) {
            List<String> br = new ArrayList<>();
            ScmFhShipmentsDataiAndMessageVo messageVo = rows.get(i);
            if (messageVo.getApprovalState() == null) {
                br.add("");
            } else {
                switch (messageVo.getApprovalState()) {
                    case "0":
                        br.add("未提交");
                        break;
                    case "1":
                        br.add("审批中");
                        break;
                    case "2":
                        br.add("已通过");
                        break;
                    case "3":
                        br.add("已驳回");
                        break;
                }
            }
            br.add(messageVo.getDateCode());
            br.add(messageVo.getDirectiveCode());
            br.add(messageVo.getShipmentsCode());
            br.add(messageVo.getWarehouse());
            br.add(messageVo.getShipmentsAffirmDate() == null ? "" : format.format(messageVo.getShipmentsAffirmDate()));
            if (messageVo.getTaskType() == null) {
                br.add("");
            } else {
                switch (messageVo.getTaskType()) {
                    case "0":
                        br.add("集中发货");
                        break;
                    case "1":
                        br.add("分配");
                        break;
                    case "2":
                        br.add("日常合同");
                        break;
                    case "3":
                        br.add("当此退货");
                        break;
                    case "4":
                        br.add("商业退货");
                        break;
                }
            }
            br.add(messageVo.getCommerceName());
            br.add(messageVo.getShippingAddress());
            br.add(messageVo.getContactPersion());
            br.add(messageVo.getPhone());
            br.add(messageVo.getCreatePersion());
            br.add(messageVo.getInventoryCode());
            br.add(messageVo.getProductName());
            br.add(messageVo.getDeliveringAmount());
            br.add(messageVo.getaChange());
            br.add(messageVo.getPackageSum());
            br.add(messageVo.getBatchNumber());
            br.add(messageVo.getDateOfManufacture());
            br.add(messageVo.getLoseDate() == null ? "" : format.format(messageVo.getLoseDate()));
            br.add(messageVo.getApprovalCode());
            br.add(String.valueOf(messageVo.getPriceSum()));
            br.add(messageVo.getMemo());
            br.add(messageVo.getLogisticsName());
            br.add(messageVo.getLogisticsCode());
            br.add(messageVo.getActualDeliveryDate() == null ? "" : format.format(messageVo.getActualDeliveryDate()));
            br.add(messageVo.getExpectedArrivalDate() == null ? "" : format.format(messageVo.getExpectedArrivalDate()));
            br.add(messageVo.getActualDateOfArrival() == null ? "" : format.format(messageVo.getActualDateOfArrival()));
            br.add(messageVo.getConfirmArrivalDate() == null ? "" : format.format(messageVo.getConfirmArrivalDate()));
            br.add(messageVo.getConfirmMemo());
            br.add(messageVo.getPackageSum());
            br.add(messageVo.getExceptionCase());
            br.add(messageVo.getExplain());
            br.add(messageVo.getAgreementDays());
            br.add(messageVo.getDalayDays());
            br.add(messageVo.getTransportationMethod());
            br.add(messageVo.getEntrustPersion());
            br.add(messageVo.getDriver());
            br.add(messageVo.getLicencePlate());
            data.add(br);
        }
        ExcelExportUtil.download(response, "物流发运信息数据", "物流发运信息数据", tableName, data);
    }

    @Override
    public void export2(QueryFilter queryFilter, boolean isPage, HttpServletResponse response) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<List<String>> data = new ArrayList<List<String>>();
        String[] tableName = {"合同编码", "发货编号", "指令编号", "商务分区", "省区", "月度", "发货日期", "仓库实际发货日期", "销售类型", "集中发货分配月", "客户类型", "客户名称",
                "商品名称", "联系人", "联系电话", "存货代码", "规格", "发货件数", "发货数量", "发货单价", "发货金额", "批号", "生产日期", "医药注册号", "备注", "协议到货天数", "预计到货日期", "实际到货日期", "延误天数", "实际件数", "制单人"};
        PageList<ScmFhShipmentsDataiAndMessageVo> query = new PageList<>();
        List<FieldSort> sorter = new ArrayList<>();
        FieldSort fieldSort = new FieldSort();
        fieldSort.setProperty("shipmentsAffirmDate");
        fieldSort.setDirection(Direction.DESC);
        sorter.add(fieldSort);
        // 如果不分页
        if (!isPage) {
            QueryFilter build = QueryFilter.build();
            build.setSorter(sorter);
            PageHelper.startPage(1, Integer.MAX_VALUE, true);
            List<ScmFhShipmentsDataiAndMessageVo> query1 = scmFhShipmentsDatailMasterDao.allShipmentMessage(queryFilter.getParams());
            query = new PageList(query1);
        } else {
            queryFilter.setSorter(sorter);
            PageBean pageBean = queryFilter.getPageBean();
            PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
            List<ScmFhShipmentsDataiAndMessageVo> query1 = scmFhShipmentsDatailMasterDao.allShipmentMessage(queryFilter.getParams());
            query = new PageList<>(query1);
        }
        List<ScmFhShipmentsDataiAndMessageVo> rows = query.getRows();
        for (int i = 0; i < rows.size(); i++) {
            List<String> br = new ArrayList<>();
            ScmFhShipmentsDataiAndMessageVo messageVo = rows.get(i);
            br.add(messageVo.getDateCode());
            br.add(messageVo.getShipmentsCode());
            br.add(messageVo.getDirectiveCode());
            br.add(messageVo.getBusinessDivision());
            br.add(messageVo.getProvince());
            br.add(messageVo.getMonth());
            br.add(messageVo.getShipmentsAffirmDate() == null ? "" : format.format(messageVo.getShipmentsAffirmDate()));
            br.add(messageVo.getActualDeliveryDate() == null ? "" : format.format(messageVo.getActualDeliveryDate()));
            br.add(messageVo.getMarketType());
            br.add(messageVo.getFocusShipmentsMonth());
            br.add(messageVo.getCommercetype());
            br.add(messageVo.getCommerceName());
            br.add(messageVo.getProductName());
            br.add(messageVo.getContactPersion());
            br.add(messageVo.getPhone());
            br.add(messageVo.getInventoryCode());
            br.add(messageVo.getProductState());
            br.add(messageVo.getPackageSum());
            br.add(messageVo.getDeliveringAmount());
            br.add(String.valueOf(messageVo.getPrice()));
            br.add(String.valueOf(messageVo.getPriceSum()));
            br.add(messageVo.getBatchNumber());
            br.add(messageVo.getDateOfManufacture());
            br.add(messageVo.getApprovalCode());
            br.add(messageVo.getMemo());
            br.add(messageVo.getAgreementDays());
            br.add(messageVo.getExpectedArrivalDate() == null ? "" : format.format(messageVo.getExpectedArrivalDate()));
            br.add(messageVo.getActualDateOfArrival() == null ? "" : format.format(messageVo.getActualDateOfArrival()));
            br.add(messageVo.getDalayDays());
            br.add(messageVo.getOfficialReceiptsSum());
            br.add(messageVo.getCreatePersion());
            data.add(br);
        }
        ExcelExportUtil.download(response, "日发货明细数据", "日发货明细数据", tableName, data);
    }
}

