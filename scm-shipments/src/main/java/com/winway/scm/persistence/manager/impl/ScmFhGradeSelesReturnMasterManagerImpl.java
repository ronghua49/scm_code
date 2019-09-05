package com.winway.scm.persistence.manager.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;
import com.hotent.base.feign.BpmRuntimeFeignService;
import com.hotent.base.modelvo.CustomStartResult;
import com.hotent.base.modelvo.StartFlowParam;
import com.winway.purchase.feign.ScmMasterDateFeignService;
import com.winway.scm.model.*;
import com.winway.scm.persistence.manager.ScmFhShipmentsTaskManager;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.winway.purchase.util.DateFormatter;
import com.winway.purchase.util.QuarterUtil;
import com.winway.purchase.util.ScmPuthWmsUtil;
import com.winway.purchase.vo.ScmWmsReceipt;
import com.winway.scm.persistence.dao.ScmFhGradeSelesReturnDao;
import com.winway.scm.persistence.dao.ScmFhGradeSelesReturnMasterDao;
import com.winway.scm.persistence.dao.ScmFhShipmentsDatailDao;
import com.winway.scm.persistence.dao.ScmFhShipmentsDatailMasterDao;
import com.winway.scm.persistence.manager.ScmFhGradeSelesReturnManager;
import com.winway.scm.persistence.manager.ScmFhGradeSelesReturnMasterManager;
import com.winway.scm.vo.GradeSelesReturnUpdateVo;

/**
 * <pre>
 * 描述：当次退货主表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-13 15:47:33
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmFhGradeSelesReturnMasterManager")
public class ScmFhGradeSelesReturnMasterManagerImpl extends AbstractManagerImpl<String, ScmFhGradeSelesReturnMaster> implements ScmFhGradeSelesReturnMasterManager {
    @Resource
    ScmFhGradeSelesReturnMasterDao scmFhGradeSelesReturnMasterDao;
    @Resource
    ScmFhGradeSelesReturnDao scmFhGradeSelesReturnDao;
    @Resource
    ScmFhShipmentsDatailDao scmFhShipmentsDatailDao;
    @Resource
    ScmFhShipmentsDatailMasterDao scmFhShipmentsDatailMasterDao;
    @Resource
    ScmFhGradeSelesReturnManager scmFhGradeSelesReturnManager;
    @Resource
    ScmMasterDateFeignService masterDateFeignService;
    @Resource
    ScmFhShipmentsTaskManager scmFhShipmentsTaskManager;
    @Resource
    BpmRuntimeFeignService bpmRuntimeFeignService;


    @Override
    protected MyBatisDao<String, ScmFhGradeSelesReturnMaster> getDao() {
        return scmFhGradeSelesReturnMasterDao;
    }

    /**
     * 创建实体包含子表实体
     */
    public void create(ScmFhGradeSelesReturnMaster scmFhGradeSelesReturnMaster) {
        scmFhGradeSelesReturnMaster.setGradeSelesCode(QuarterUtil.getCode("DCTH"));
        super.create(scmFhGradeSelesReturnMaster);
        String pk = scmFhGradeSelesReturnMaster.getId();
        List<ScmFhGradeSelesReturn> scmFhGradeSelesReturnList = scmFhGradeSelesReturnMaster.getScmFhGradeSelesReturnList();
        createSon(pk, scmFhGradeSelesReturnList);
    }

    /**
     * 删除记录包含子表记录
     */
    public void remove(String entityId) {
        super.remove(entityId);
        scmFhGradeSelesReturnDao.delByMainId(entityId);
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
    public ScmFhGradeSelesReturnMaster get(String entityId) {
        ScmFhGradeSelesReturnMaster scmFhGradeSelesReturnMaster = super.get(entityId);
        List<ScmFhGradeSelesReturn> scmFhGradeSelesReturnList = scmFhGradeSelesReturnDao.getByMainId(entityId);
        scmFhGradeSelesReturnMaster.setScmFhGradeSelesReturnList(scmFhGradeSelesReturnList);
        return scmFhGradeSelesReturnMaster;
    }

    /**
     * 更新实体同时更新子表记录
     */
    public void update(ScmFhGradeSelesReturnMaster scmFhGradeSelesReturnMaster) {
        ScmFhGradeSelesReturnMaster gradeSelesReturnMaster = scmFhGradeSelesReturnMasterDao.get(scmFhGradeSelesReturnMaster.getId());
        if (gradeSelesReturnMaster == null) {
            throw new RuntimeException("数据传输错误，未查询到要更新的退货数据");
        }
        scmFhGradeSelesReturnMaster.setGradeSelesCode(QuarterUtil.getCode("DCTH"));
        super.update(scmFhGradeSelesReturnMaster);
        String id = scmFhGradeSelesReturnMaster.getId();
        //获取子表数据
        List<ScmFhGradeSelesReturn> byMainId = scmFhGradeSelesReturnDao.getByMainId(scmFhGradeSelesReturnMaster.getId());
        ScmFhShipmentsDatail scmFhShipmentsDatail;
        //根据发货表id查询发货详情，修改已经退货数量
        for (ScmFhGradeSelesReturn gradeSelesReturn : byMainId) {
            scmFhShipmentsDatail = scmFhShipmentsDatailDao.get(gradeSelesReturn.getShipmentsId());
            if (scmFhShipmentsDatail == null) {
                throw new RuntimeException("数据异常");
            }
            scmFhShipmentsDatail.setReturnSum(scmFhShipmentsDatail.getReturnSum() - gradeSelesReturn.getProductSum());
            scmFhShipmentsDatailDao.update(scmFhShipmentsDatail);
        }
        scmFhGradeSelesReturnDao.delByMainId(id);
        List<ScmFhGradeSelesReturn> scmFhGradeSelesReturnList = scmFhGradeSelesReturnMaster.getScmFhGradeSelesReturnList();
        createSon(id, scmFhGradeSelesReturnList);
    }


    private void createSon(String pk, List<ScmFhGradeSelesReturn> scmFhMarketSelesReturnList) {
        ScmFhShipmentsDatail scmFhShipmentsDatail;
        for (ScmFhGradeSelesReturn gradeSelesReturn : scmFhMarketSelesReturnList) {
        	if(gradeSelesReturn.getShipmentsId() == null){
        		throw new RuntimeException("数据传输错误，未查询到要退货的商品");
        	}
        	scmFhShipmentsDatail = scmFhShipmentsDatailDao.get(gradeSelesReturn.getShipmentsId());
            if (scmFhShipmentsDatail!= null) {
                // 判断退货数量大于零 小于剩余发货数量
                if (gradeSelesReturn.getProductSum() == null || gradeSelesReturn.getProductSum() == 0) {
                    throw new RuntimeException("退货数量不得为空");
                }
                if (gradeSelesReturn.getProductSum() > (scmFhShipmentsDatail.getDeliveringAmount() - scmFhShipmentsDatail.getReturnSum())) {
                    throw new RuntimeException("退货数量不得大于发货量");
                }
                // 保存已经退货的数量到发货详情表scm/scmFhShipmentsDatail/v1/list
                scmFhShipmentsDatail.setReturnSum(scmFhShipmentsDatail.getReturnSum() + gradeSelesReturn.getProductSum());
                scmFhShipmentsDatailDao.update(scmFhShipmentsDatail);
                gradeSelesReturn.setMasterId(pk);
                scmFhGradeSelesReturnManager.create(gradeSelesReturn);
            } else {
                throw new RuntimeException("数据传输错误，未查询到要退货的商品");
            }
        }
    }

    @Override
    public ScmFhGradeSelesReturnMaster sendApply(ScmFhGradeSelesReturnMaster scmFhGradeSelesReturnMaster) {
        ScmFhGradeSelesReturnMaster master = scmFhGradeSelesReturnMasterDao.get(scmFhGradeSelesReturnMaster.getId());
        if (master == null) {
            throw new RuntimeException("没有查询到主表数据");
        }
        if ("1".equals(master.getApprovalState())) {
            throw new RuntimeException("该数据正在审批中，不得二次申请");
        }
        StartFlowParam startFlowParam = new StartFlowParam("dcthsp", "SCM", "approvalId");
        startFlowParam.setFormType("frame");
        CustomStartResult customStartResult = null;
        try {
            System.out.println("发起商业退货申请");
            customStartResult = bpmRuntimeFeignService.customStart(startFlowParam);
            String approvalId = customStartResult.getInstId();
            scmFhGradeSelesReturnMaster.setApprovalId(approvalId);
            scmFhGradeSelesReturnMaster.setApprovalState("1");
            update(scmFhGradeSelesReturnMaster);
            scmFhGradeSelesReturnMaster = scmFhGradeSelesReturnMasterDao.get(master.getId());
            return scmFhGradeSelesReturnMaster;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("启动工作流失败");
        }
    }

    @Override
    public void endApply(JsonNode jsonNode) throws ParseException {
        String approvalId = jsonNode.get("instId").asText();
        String actionName = jsonNode.get("actionName").asText();
        //根据审批id查询数据
        ScmFhGradeSelesReturnMaster byApprovalState = scmFhGradeSelesReturnMasterDao.getByApprovalState(approvalId);
        if (byApprovalState == null) {
            throw new RuntimeException("未查询到业务数据,处理异常");
        }
        byApprovalState = get(byApprovalState.getId());
        List<ScmFhGradeSelesReturn> byMainId = scmFhGradeSelesReturnDao.getByMainId(byApprovalState.getId());
        String endEvent = jsonNode.get("eventType").asText();
        if ("agree".equals(actionName) && "endEvent".equals(endEvent)) {
        	byApprovalState.setApprovalState("2");
            //todo 推送到wms

            //通过之后创建物流发运信息记录
            //创建当此退货发货任务
            List<Map<String, Object>> tasks = new ArrayList<>();
            for (ScmFhGradeSelesReturn scmFhGradeSelesReturn : byMainId) {
                Map<String, Object> map = new HashedMap();
                map.put("ownerId", byApprovalState.getOwnerId());
                map.put("type", "0");
                map.put("taskType", "3");
                map.put("dateCode", byApprovalState.getGradeSelesCode());
                map.put("taskCreateDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                //商品信息
                map.put("productName", scmFhGradeSelesReturn.getProductName());
                map.put("productNum", scmFhGradeSelesReturn.getProductCode());
                map.put("MedicineType", scmFhGradeSelesReturn.getMedicineType());
                map.put("productState", scmFhGradeSelesReturn.getProductState());
                map.put("price", scmFhGradeSelesReturn.getPrice());
                map.put("sumPrice", scmFhGradeSelesReturn.getPriceSum());
                map.put("aChange", scmFhGradeSelesReturn.getaChange());
                map.put("packageNum", Math.round(scmFhGradeSelesReturn.getProductSum().doubleValue() * 100 / scmFhGradeSelesReturn.getaChange().doubleValue()) / 100);
                map.put("memo", scmFhGradeSelesReturn.getMemo());
                map.put("planShipmentsSum", scmFhGradeSelesReturn.getProductSum());
                map.put("code", scmFhGradeSelesReturn.getCode());

                map.put("commonName", scmFhGradeSelesReturn.getCommonName());
                map.put("unit", scmFhGradeSelesReturn.getUnit());
                map.put("AgentType", scmFhGradeSelesReturn.getAgentType());
                map.put("manufacturer", scmFhGradeSelesReturn.getManufacturer());
                map.put("approvalCode", scmFhGradeSelesReturn.getApprovalCode());
                map.put("medicineClassify", scmFhGradeSelesReturn.getMedicineClassify());
                map.put("businessScope", scmFhGradeSelesReturn.getBusinessScope());


                
                //获取客户信息
                String s = masterDateFeignService.getcommerceFirstByCode(byApprovalState.getCommerceCode(),byApprovalState.getOwnerId());
                JSONObject jsonObject = JSON.parseObject(s);
                map.put("commercetype", jsonObject.get("commerceType"));
                map.put("marketType", jsonObject.get("functionType"));
                map.put("contactPersion", jsonObject.get("contactPersion"));
                map.put("phone", jsonObject.get("phone"));
                map.put("commerceName", byApprovalState.getCommerceName());
                map.put("commerceId", byApprovalState.getCommerceFirstId());
                map.put("paymentType", "");
                map.put("sendProductDate", "");
                map.put("focusShipmentsMonth", "");
                tasks.add(map);
            }
            //处理发货任务
            scmFhShipmentsTaskManager.theDeliveryTask(JSON.toJSONString(tasks));
            try {
                List<Map<String, Object>> list = new ArrayList<>();
                for (ScmFhGradeSelesReturn scmFhGradeSelesReturn : byMainId) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("directiveCode", scmFhGradeSelesReturn.getDirectiveCode());
                    map.put("productName", scmFhGradeSelesReturn.getProductName());
                    map.put("commonName", scmFhGradeSelesReturn.getCommonName());
                    map.put("manufacturer", scmFhGradeSelesReturn.getManufacturer());
                    map.put("productState", scmFhGradeSelesReturn.getProductState());
                    map.put("medicineType", scmFhGradeSelesReturn.getMedicineType());
                    map.put("productSum", scmFhGradeSelesReturn.getProductSum());
                    map.put("price", scmFhGradeSelesReturn.getPrice());
                    map.put("priceSum", scmFhGradeSelesReturn.getPriceSum());
                    map.put("batchNumber", scmFhGradeSelesReturn.getBatchNumber());
                    map.put("returnMessage", scmFhGradeSelesReturn.getReturnMessage());
                    map.put("memo", scmFhGradeSelesReturn.getMemo());
                    map.put("productCode", scmFhGradeSelesReturn.getCode());
                    list.add(map);
                }
                Map<String, Object> masterMap = new HashMap<String, Object>();
                masterMap.put("gradeSelesCode", byApprovalState.getGradeSelesCode());
                masterMap.put("commerceName", byApprovalState.getCommerceName());
                masterMap.put("commerceCode", byApprovalState.getCommerceCode());
                String format = DateFormatter.format(DateFormatter.PATTERN_yyyy_MM_dd_HH_mm_ss, byApprovalState.getCreateDate());
                masterMap.put("createDate", format);
                masterMap.put("type", "Do_SaleReturn");
                masterMap.put("getMoneyWarehouseCode",scmFhShipmentsDatailDao.get(byMainId.get(0).getShipmentsId()).getHoustWareCode());
                masterMap.put("shipmentsCode", byApprovalState.getShipmentsCode());
                masterMap.put("oownerId", byApprovalState.getOwnerId());
                masterMap.put("productList", list);
                String jsonString = JSON.toJSONString(masterMap);
                boolean puth = ScmPuthWmsUtil.puth(jsonString, "/prolog_aomei_interface/interface/outSingleReturn/outbound/scmOutboundSingleReturnNotify.json","当此退货推送wms");
                if (puth) {
                    byApprovalState.setIsSMS("1");
                } else {
                    byApprovalState.setIsSMS("0");
                }
            } catch (Exception e) {
                byApprovalState.setIsSMS("0");
                e.printStackTrace();
            }
            byApprovalState.setIsManageSap("0");
            scmFhGradeSelesReturnMasterDao.update(byApprovalState);
        } else if ("agree".equals(actionName)) {
        } else if ("reject".equals(actionName)) {
        } else if ("backToStart".equals(actionName)) {
        } else if ("opposeTrans".equals(actionName)) {
        } else if ("endProcess".equals(actionName)) {
        	 byApprovalState.setApprovalState("3");
        	 byApprovalState.setIsManageSap("0");
             scmFhGradeSelesReturnMasterDao.update(byApprovalState);
        }
    }

    @Override
    public GradeSelesReturnUpdateVo getUpdateInfo(String id) {
        ScmFhGradeSelesReturnMaster gradeSelesReturnMaster = scmFhGradeSelesReturnMasterDao.get(id);
        if (gradeSelesReturnMaster == null) {
            throw new RuntimeException("数据错误，没有找到要更新的退货主表数据");
        }
        List<ScmFhGradeSelesReturn> gradeSelesReturns = scmFhGradeSelesReturnDao.getByMainId(id);
        //获取单次退货的 发货编号和发货量和已退数量
        for (ScmFhGradeSelesReturn gradeSelesReturn : gradeSelesReturns) {
            ScmFhShipmentsDatail scmFhShipmentsDatail = scmFhShipmentsDatailDao.get(gradeSelesReturn.getShipmentsId());
            if (scmFhShipmentsDatail == null) {
                throw new RuntimeException("数据错误，该退货数据没有发货表记录");
            }
            gradeSelesReturn.setDeliveringAmount(scmFhShipmentsDatail.getDeliveringAmount());
            gradeSelesReturn.setReturnSum(scmFhShipmentsDatail.getReturnSum());
            ScmFhGradeSelesReturnMaster gradeSelesReturnMaster1 = scmFhGradeSelesReturnMasterDao.get(gradeSelesReturn.getMasterId());
            gradeSelesReturn.setShipmentsCode(gradeSelesReturnMaster1.getShipmentsCode());
        }
        String shipmentsCode = gradeSelesReturnMaster.getShipmentsCode();
        List<ScmFhShipmentsDatailMaster> fhShipmentsDatailMasterList = new ArrayList<ScmFhShipmentsDatailMaster>();
        if (shipmentsCode != null) {
            String[] split = shipmentsCode.split(",");
            List<String> shipmentsCodes = Arrays.asList(split);
            fhShipmentsDatailMasterList = scmFhShipmentsDatailMasterDao.getByShipmentCodes(shipmentsCodes);
        }
        GradeSelesReturnUpdateVo gradeSelesReturnUpdateVo = new GradeSelesReturnUpdateVo();
        gradeSelesReturnUpdateVo.setFhGradeSelesReturnList(gradeSelesReturns);
        gradeSelesReturnUpdateVo.setShipmentsDatailMasterList(fhShipmentsDatailMasterList);
        return gradeSelesReturnUpdateVo;
    }

    @Override
    public GradeSelesReturnUpdateVo getDatailByApprovalId(String approvalId) {
        ScmFhGradeSelesReturnMaster gradeSelesReturnMaster = scmFhGradeSelesReturnMasterDao.getByApprovalState(approvalId);
        if (gradeSelesReturnMaster == null) {
            throw new RuntimeException("数据错误，没有找到审批数据");
        }
        List<ScmFhGradeSelesReturn> gradeSelesReturns = scmFhGradeSelesReturnDao.getByMainId(gradeSelesReturnMaster.getId());
        //获取单次退货的 发货编号和发货量和已退数量
        for (ScmFhGradeSelesReturn gradeSelesReturn : gradeSelesReturns) {
            ScmFhShipmentsDatail scmFhShipmentsDatail = scmFhShipmentsDatailDao.get(gradeSelesReturn.getShipmentsId());
            gradeSelesReturn.setDeliveringAmount(scmFhShipmentsDatail.getDeliveringAmount());
            gradeSelesReturn.setReturnSum(scmFhShipmentsDatail.getReturnSum());
            ScmFhGradeSelesReturnMaster gradeSelesReturnMaster1 = scmFhGradeSelesReturnMasterDao.get(gradeSelesReturn.getMasterId());
            gradeSelesReturn.setShipmentsCode(gradeSelesReturnMaster1.getShipmentsCode());
        }
        String shipmentsCode = gradeSelesReturnMaster.getShipmentsCode();
        List<ScmFhShipmentsDatailMaster> fhShipmentsDatailMasterList = new ArrayList<ScmFhShipmentsDatailMaster>();
        if (shipmentsCode != null) {
            String[] split = shipmentsCode.split(",");
            List<String> shipmentsCodes = Arrays.asList(split);
            fhShipmentsDatailMasterList = scmFhShipmentsDatailMasterDao.getByShipmentCodes(shipmentsCodes);
        }
        GradeSelesReturnUpdateVo gradeSelesReturnUpdateVo = new GradeSelesReturnUpdateVo();
        gradeSelesReturnUpdateVo.setFhGradeSelesReturnList(gradeSelesReturns);
        gradeSelesReturnUpdateVo.setShipmentsDatailMasterList(fhShipmentsDatailMasterList);
        return gradeSelesReturnUpdateVo;
    }

    @Override
    public void receipt(ScmWmsReceipt scmWmsReceipt) {
        ScmFhGradeSelesReturnMaster scmFhGradeSelesReturnMaster = scmFhGradeSelesReturnMasterDao.getByOrderCode(scmWmsReceipt.getOrderCode());
        if (scmFhGradeSelesReturnMaster == null) {
            throw new RuntimeException("请传入正确的订单号");
        }
        if ("1".equals(scmWmsReceipt.getTypt())) {
            scmFhGradeSelesReturnMaster.setIsAffirmWms("1");
            scmFhGradeSelesReturnMaster.setWmsAffirmDate(new Date());
        } else {
            throw new RuntimeException("请提交正确的状态码");
        }
        scmFhGradeSelesReturnMasterDao.update(scmFhGradeSelesReturnMaster);
    }

	@Override
	public PageList<ScmFhGradeSelesReturn> gradeSelesReturnDatail(String orderCode) {
		ScmFhGradeSelesReturnMaster byOrderCode = scmFhGradeSelesReturnMasterDao.getByOrderCode(orderCode);
		if(byOrderCode == null) {
			throw new RuntimeException("未查询到订单信息");
		}
		List<ScmFhGradeSelesReturn> byMainId = scmFhGradeSelesReturnDao.getByMainId(byOrderCode.getId());
		for (ScmFhGradeSelesReturn scmFhGradeSelesReturn : byMainId) {
			CommonResult<String> productNumByCode = masterDateFeignService.getProductNumByCode(scmFhGradeSelesReturn.getCode());
			scmFhGradeSelesReturn.setSapCode(productNumByCode.getShortMessage());
		}
		PageList<ScmFhGradeSelesReturn> pageList = new PageList<ScmFhGradeSelesReturn>();
		pageList.setPage(1);
		pageList.setPageSize(10);
		pageList.setTotal(byMainId.size());
		pageList.setRows(byMainId);
		return pageList;
	}

}
