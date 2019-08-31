package com.winway.scm.persistence.manager.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.pagehelper.PageHelper;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.feign.BpmRuntimeFeignService;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.modelvo.CustomStartResult;
import com.hotent.base.modelvo.StartFlowParam;
import com.hotent.base.query.PageBean;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.BeanUtils;
import com.winway.purchase.feign.ScmMasterDateFeignService;
import com.winway.purchase.util.DateFormatter;
import com.winway.purchase.util.HttpClientUtil;
import com.winway.purchase.util.QuarterUtil;
import com.winway.purchase.util.ScmPuthWmsUtil;
import com.winway.purchase.vo.ScmWmsReceipt;
import com.winway.purchase.vo.ScmWmsReturnData;
import com.winway.scm.model.ScmFhGradeSelesReturn;
import com.winway.scm.model.ScmFhMarketSelesReturn;
import com.winway.scm.model.ScmFhMarketSelesReturnMaster;
import com.winway.scm.model.ScmFhShipmentsDatail;
import com.winway.scm.persistence.dao.ScmFhMarketSelesReturnDao;
import com.winway.scm.persistence.dao.ScmFhMarketSelesReturnMasterDao;
import com.winway.scm.persistence.dao.ScmFhShipmentsDatailDao;
import com.winway.scm.persistence.manager.ScmFhMarketSelesReturnMasterManager;
import com.winway.scm.persistence.manager.ScmFhShipmentsTaskManager;
import com.winway.scm.vo.SalesReturnRecord;
import com.winway.scm.vo.ScmFhShipmentsDataiAndMessageVo;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <pre>
 * 描述：销售退货主表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-13 15:47:33
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmFhMarketSelesReturnMasterManager")
public class ScmFhMarketSelesReturnMasterManagerImpl extends AbstractManagerImpl<String, ScmFhMarketSelesReturnMaster> implements ScmFhMarketSelesReturnMasterManager {
    @Resource
    ScmFhMarketSelesReturnMasterDao scmFhMarketSelesReturnMasterDao;
    @Resource
    ScmFhMarketSelesReturnDao scmFhMarketSelesReturnDao;

    @Resource
    ScmFhShipmentsDatailDao scmFhShipmentsDatailDao;

    @Resource
    ScmFhMarketSelesReturnManagerImpl scmFhMarketSelesReturnManager;

    @Resource
    ScmMasterDateFeignService masterDateFeignService;

    @Resource
    ScmFhShipmentsTaskManager scmFhShipmentsTaskManager;

    @Resource
    BpmRuntimeFeignService bpmRuntimeFeignService;

    @Override
    protected MyBatisDao<String, ScmFhMarketSelesReturnMaster> getDao() {
        return scmFhMarketSelesReturnMasterDao;
    }

    /**
     * 创建实体包含子表实体
     */
    public void create(ScmFhMarketSelesReturnMaster scmFhMarketSelesReturnMaster) {
        // 判断是否存在该商业和仓库的发货数据
//        String getMoneyWarehouseCode = scmFhMarketSelesReturnMaster.getGetMoneyWarehouseId();
        String commerceFirstId = scmFhMarketSelesReturnMaster.getCommerceFirstId();
        List<ScmFhShipmentsDatail> detailList =  scmFhShipmentsDatailDao.getByCommerceAndWareHouse(commerceFirstId);
        if(detailList==null||detailList.isEmpty()){
            throw new RuntimeException("该商业没有发货数据");
        }
        scmFhMarketSelesReturnMaster.setMarkertCode(QuarterUtil.getCode("SYTH"));
        super.create(scmFhMarketSelesReturnMaster);
        String pk = scmFhMarketSelesReturnMaster.getId();
        List<ScmFhMarketSelesReturn> scmFhMarketSelesReturnList = scmFhMarketSelesReturnMaster.getScmFhMarketSelesReturnList();
        //根据主表id 和子表数据添加 退货子表
        createSon(pk, scmFhMarketSelesReturnList);
    }

    /**
     * 删除记录包含子表记录
     */
    public void remove(String entityId) {
        super.remove(entityId);
        scmFhMarketSelesReturnDao.delByMainId(entityId);
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
    public ScmFhMarketSelesReturnMaster get(String entityId) {
        ScmFhMarketSelesReturnMaster scmFhMarketSelesReturnMaster = super.get(entityId);
        List<ScmFhMarketSelesReturn> scmFhMarketSelesReturnList = scmFhMarketSelesReturnDao.getByMainId(entityId);
        for (ScmFhMarketSelesReturn selesReturn : scmFhMarketSelesReturnList) {
            ScmFhShipmentsDatail scmFhShipmentsDatail = scmFhShipmentsDatailDao.get(selesReturn.getShipmentsId());
            if (scmFhShipmentsDatail == null) {
                throw new RuntimeException("数据错误");
            }
            selesReturn.setDeliveringAmount(scmFhShipmentsDatail.getDeliveringAmount());
            selesReturn.setReturnSum(scmFhShipmentsDatail.getReturnSum());
        }
        scmFhMarketSelesReturnMaster.setScmFhMarketSelesReturnList(scmFhMarketSelesReturnList);
        return scmFhMarketSelesReturnMaster;
    }

    /**
     * 更新实体同时更新子表记录
     */
    public void update(ScmFhMarketSelesReturnMaster scmFhMarketSelesReturnMaster) {
        ScmFhMarketSelesReturnMaster marketSelesReturnMaster = scmFhMarketSelesReturnMasterDao.get(scmFhMarketSelesReturnMaster.getId());
        if (marketSelesReturnMaster == null) {
            throw new RuntimeException("数据传输错误，未查询到要更新的退货数据");
        }
        super.update(scmFhMarketSelesReturnMaster);
        String id = scmFhMarketSelesReturnMaster.getId();
        //获取子表数据
        List<ScmFhMarketSelesReturn> byMainId = scmFhMarketSelesReturnDao.getByMainId(scmFhMarketSelesReturnMaster.getId());
        ScmFhShipmentsDatail scmFhShipmentsDatail;
        //根据发货表id查询发货详情，修改已经退货数量
        for (ScmFhMarketSelesReturn selesReturn : byMainId) {
            scmFhShipmentsDatail = scmFhShipmentsDatailDao.get(selesReturn.getShipmentsId());
            if (scmFhShipmentsDatail == null) {
                throw new RuntimeException("数据异常");
            }
            scmFhShipmentsDatail.setReturnSum(scmFhShipmentsDatail.getReturnSum() - selesReturn.getProductSum());
            scmFhShipmentsDatailDao.update(scmFhShipmentsDatail);
        }
        scmFhMarketSelesReturnDao.delByMainId(id);
        List<ScmFhMarketSelesReturn> scmFhMarketSelesReturnList = scmFhMarketSelesReturnMaster.getScmFhMarketSelesReturnList();
        createSon(id, scmFhMarketSelesReturnList);
    }

    private void createSon(String pk, List<ScmFhMarketSelesReturn> scmFhMarketSelesReturnList) {
        ScmFhShipmentsDatail scmFhShipmentsDatail;
        for (ScmFhMarketSelesReturn scmFhMarketSelesReturn : scmFhMarketSelesReturnList) {
            String shipmentsId = scmFhMarketSelesReturn.getShipmentsId();
            scmFhShipmentsDatail = scmFhShipmentsDatailDao.get(scmFhMarketSelesReturn.getShipmentsId());
            if (scmFhMarketSelesReturn.getShipmentsId() != null && (scmFhShipmentsDatail = scmFhShipmentsDatailDao.get(scmFhMarketSelesReturn.getShipmentsId())) != null) {
                // 判断退货数量大于零 小于剩余发货数量
                if (scmFhMarketSelesReturn.getProductSum() == null || scmFhMarketSelesReturn.getProductSum() == 0) {
                    throw new RuntimeException("退货数量不得为空");
                }
                if (scmFhMarketSelesReturn.getProductSum() > (scmFhShipmentsDatail.getDeliveringAmount() - scmFhShipmentsDatail.getReturnSum())) {
                    throw new RuntimeException("退货数量不得大于发货量");
                }
                // 保存已经退货的数量到发货详情表
                scmFhShipmentsDatail.setReturnSum(scmFhShipmentsDatail.getReturnSum() + scmFhMarketSelesReturn.getProductSum());
                scmFhShipmentsDatailDao.update(scmFhShipmentsDatail);
                scmFhMarketSelesReturn.setMasterId(pk);
                scmFhMarketSelesReturnManager.create(scmFhMarketSelesReturn);
            } else {
                throw new RuntimeException("数据传输错误，未查询到要退货的商品");
            }
        }
    }

    @Override
    public ScmFhMarketSelesReturnMaster sendApply(ScmFhMarketSelesReturnMaster scmFhMarketSelesReturnMaster) {
        ScmFhMarketSelesReturnMaster master = scmFhMarketSelesReturnMasterDao.get(scmFhMarketSelesReturnMaster.getId());
        //判断是首次发起申请 还是暂存后申请
        if (master == null) {
            throw new RuntimeException("主表数据不存在");
        }
        if ("1".equals(scmFhMarketSelesReturnMaster.getApprovalState())) {
            throw new RuntimeException("该数据正在审批中，不得二次申请");
        }
        StartFlowParam startFlowParam = new StartFlowParam("sythsp", "SCM", "approvalId");
        startFlowParam.setFormType("frame");
        CustomStartResult customStartResult = null;
        try {
            System.out.println("发起当次退货申请");
            customStartResult = bpmRuntimeFeignService.customStart(startFlowParam);
            String approvalId = customStartResult.getInstId();
            scmFhMarketSelesReturnMaster.setApprovalId(approvalId);
            scmFhMarketSelesReturnMaster.setApprovalState("1");
            update(scmFhMarketSelesReturnMaster);
            scmFhMarketSelesReturnMaster = scmFhMarketSelesReturnMasterDao.get(scmFhMarketSelesReturnMaster.getId());
            return scmFhMarketSelesReturnMaster;
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
        ScmFhMarketSelesReturnMaster byApprovalState = scmFhMarketSelesReturnMasterDao.getByApprovalState(approvalId);
        if (byApprovalState == null) {
            throw new RuntimeException("未查询到业务数据,处理异常");
        }
        byApprovalState = get(byApprovalState.getId());
        List<ScmFhMarketSelesReturn> byMainId = scmFhMarketSelesReturnDao.getByMainId(byApprovalState.getId());
        String endEvent = jsonNode.get("eventType").asText();
        if ("agree".equals(actionName) && "endEvent".equals(endEvent)) {
        	byApprovalState.setApprovalState("2");
            //通过之后创建物流发运信息记录
            //创建当此退货发货任务
            List<Map<String, Object>> tasks = new ArrayList<>();
            for (ScmFhMarketSelesReturn scmFhMarketSelesReturn : byMainId) {
                if("换货".equals(scmFhMarketSelesReturn.getFinanceDisposeMethod())){
                    Map<String, Object> map = new HashedMap();
                    map.put("ownerId", byApprovalState.getOwnerId());
                    map.put("type", "0");
                    map.put("taskType", "4");
                    map.put("dateCode", byApprovalState.getMarkertCode());
                    map.put("taskCreateDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                    //商品信息
                    map.put("isSms", "");
                    map.put("productName", scmFhMarketSelesReturn.getProductName());
                    map.put("productNum", scmFhMarketSelesReturn.getProductCode());
                    map.put("MedicineType", scmFhMarketSelesReturn.getMedicineType());
                    map.put("productState", scmFhMarketSelesReturn.getProductState());
                    map.put("price", scmFhMarketSelesReturn.getPrice());
                    map.put("sumPrice", scmFhMarketSelesReturn.getPriceSum());
                    map.put("aChange", scmFhMarketSelesReturn.getAChange());
                    map.put("packageNum",Math.round(scmFhMarketSelesReturn.getProductSum().doubleValue()*1000/scmFhMarketSelesReturn.getAChange().doubleValue())/1000);
                    map.put("memo", scmFhMarketSelesReturn.getMemo());
                    map.put("planShipmentsSum", scmFhMarketSelesReturn.getProductSum());
                    map.put("code", scmFhMarketSelesReturn.getCode());
                    map.put("commonName",scmFhMarketSelesReturn.getCommonName());
                    map.put("unit",scmFhMarketSelesReturn.getUnit());
                    map.put("AgentType",scmFhMarketSelesReturn.getAgentType());
                    map.put("manufacturer",scmFhMarketSelesReturn.getManufacturer());
                    map.put("approvalCode",scmFhMarketSelesReturn.getApprovalCode());
                    map.put("medicineClassify",scmFhMarketSelesReturn.getMedicineClassify());
                    map.put("businessScope",scmFhMarketSelesReturn.getBusinessScope());
                    //获取客户信息
                    String s = masterDateFeignService.getcommerceFirstByCode(byApprovalState.getCommerceCode(),byApprovalState.getOwnerId());
                    JSONObject jsonObject = JSON.parseObject(s);
                    map.put("commercetype", jsonObject.get("commerceLevel"));
                    map.put("marketType", jsonObject.get("functionType"));
                    map.put("contactPersion", jsonObject.get("contactPersion"));
                    map.put("phone", jsonObject.get("phone"));
                    map.put("commerceName", jsonObject.get("commerceName"));
                    map.put("commerceId", jsonObject.get("commerceFirstId"));
                    map.put("paymentType","");
                    map.put("sendProductDate","");
                    map.put("focusShipmentsMonth", "");
                    tasks.add(map);
                }
            }
            //处理发货任务
            scmFhShipmentsTaskManager.theDeliveryTask(JSON.toJSONString(tasks));
            try {
            	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
            	for (ScmFhMarketSelesReturn scmFhMarketSelesReturn : byMainId) {
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("batchNumber", scmFhMarketSelesReturn.getBatchNumber());
					map.put("productName", scmFhMarketSelesReturn.getProductName());
					map.put("productCode", scmFhMarketSelesReturn.getCode());
					map.put("productState", scmFhMarketSelesReturn.getProductState());
					map.put("medicineType", scmFhMarketSelesReturn.getMedicineType());
					map.put("productSum", scmFhMarketSelesReturn.getProductSum());
					map.put("price", scmFhMarketSelesReturn.getPrice());
					map.put("priceSum", scmFhMarketSelesReturn.getProductSum());
					map.put("expiryDate", scmFhMarketSelesReturn.getExpiryDate());
					map.put("actualDeliveryDate", scmFhMarketSelesReturn.getActualDeliveryDate());
					if(scmFhMarketSelesReturn.getShipmentsId() == null || "".equals(scmFhMarketSelesReturn.getShipmentsId())) {
						map.put("memo", "EIP3历史数据,无发货编号");
					}else{
						ScmFhShipmentsDatail scmFhShipmentsDatail = scmFhShipmentsDatailDao.get(scmFhMarketSelesReturn.getShipmentsId());
						if(scmFhShipmentsDatail == null) {
							map.put("memo", "EIP3历史数据,无发货编号");
						}else{
							map.put("memo", scmFhShipmentsDatail.getDirectiveCode());
						}
					}
					list.add(map);
				}
            	Map<String,Object> MasterMap = new HashMap<String,Object>();
            	MasterMap.put("markertCode", byApprovalState.getMarkertCode());
            	MasterMap.put("supplierName", byApprovalState.getSupplierName());
            	MasterMap.put("supplierCode", byApprovalState.getSupplierCode());
            	MasterMap.put("commerceName", byApprovalState.getCommerceName());
            	MasterMap.put("commerceCode", byApprovalState.getCommerceCode());
                String format = DateFormatter.format(DateFormatter.PATTERN_yyyy_MM_dd_HH_mm_ss, byApprovalState.getCreateDate());
                MasterMap.put("createDate", format);
            	MasterMap.put("type", "Do_SaleReturn");
            	MasterMap.put("getMoneyWarehouse", byApprovalState.getGetMoneyWarehouse());
            	MasterMap.put("getMoneyWarehouseCode", byApprovalState.getGetMoneyWarehouseId());
            	MasterMap.put("oownerId", byApprovalState.getOwnerId());
            	MasterMap.put("productList",list);
                byApprovalState.setIsSMS("1");
                String jsonString = JSON.toJSONString(MasterMap);
                boolean puth = ScmPuthWmsUtil.puth(jsonString, "/prolog_aomei_interface/interface/outReturn/outbound/scmOutboundReturnNotify.json","商业退货推送wms");
            	if(puth) {
            		byApprovalState.setIsSMS("1");
            	}else{
            		byApprovalState.setIsSMS("0");
            	}
            } catch (Exception e) {
            	byApprovalState.setIsSMS("0");
            }
            byApprovalState.setIsManageSap("0");
            scmFhMarketSelesReturnMasterDao.update(byApprovalState);
        } else if ("agree".equals(actionName)) {
        } else if ("reject".equals(actionName)) {
        } else if ("backToStart".equals(actionName)) {
        } else if ("opposeTrans".equals(actionName)) {
        } else if ("endProcess".equals(actionName)) {
            // 修复已经退货数量到发货详情
            for (ScmFhMarketSelesReturn scmFhMarketSelesReturn : byMainId) {
                ScmFhShipmentsDatail scmFhShipmentsDatail = scmFhShipmentsDatailDao.get(scmFhMarketSelesReturn.getShipmentsId());
                scmFhShipmentsDatail.setReturnSum(scmFhShipmentsDatail.getReturnSum() - scmFhMarketSelesReturn.getProductSum());
                scmFhShipmentsDatailDao.update(scmFhShipmentsDatail);
            }
        	 byApprovalState.setApprovalState("3");
        	 byApprovalState.setIsManageSap("0");
        	 scmFhMarketSelesReturnMasterDao.update(byApprovalState);
        }
    }

    @Override
    public ScmFhMarketSelesReturnMaster getByApprovalId(String approvalId) {
        ScmFhMarketSelesReturnMaster scmFhMarketSelesReturnMaster = scmFhMarketSelesReturnMasterDao.getByApprovalState(approvalId);

        if (scmFhMarketSelesReturnMaster == null) {
            throw new RuntimeException("没有找到要审批的商业退货数据");
        }
        List<ScmFhMarketSelesReturn> scmFhMarketSelesReturnList = scmFhMarketSelesReturnDao.getByMainId(scmFhMarketSelesReturnMaster.getId());
        scmFhMarketSelesReturnMaster.setScmFhMarketSelesReturnList(scmFhMarketSelesReturnList);
        return scmFhMarketSelesReturnMaster;
    }

    @Override
    public PageList<SalesReturnRecord> salesReturnRecord(QueryFilter queryFilter) {
        PageBean pageBean = queryFilter.getPageBean();
        if (BeanUtils.isEmpty(pageBean)) {
            PageHelper.startPage(1, Integer.MAX_VALUE, false);
        } else {
            PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
        }
        List<SalesReturnRecord> firstList = scmFhMarketSelesReturnMasterDao.getSalesBackRecode(queryFilter.getParams());
        return new PageList<SalesReturnRecord>(firstList);
    }

	@Override
	public void receipt(ScmWmsReceipt scmWmsReceipt) {
		ScmFhMarketSelesReturnMaster ScmFhMarketSelesReturnMaster = scmFhMarketSelesReturnMasterDao.getByOrderCode(scmWmsReceipt.getOrderCode());
		if(ScmFhMarketSelesReturnMaster == null) {
			throw new RuntimeException("请提交正确的订单号");
		}
		if("1".equals(scmWmsReceipt.getTypt())) {
			ScmFhMarketSelesReturnMaster.setIsAffirmWms("1");
			ScmFhMarketSelesReturnMaster.setWmsAffirmDate(new Date());
		}else{
			throw new RuntimeException("请提交正确的状态码");
		}
		scmFhMarketSelesReturnMasterDao.update(ScmFhMarketSelesReturnMaster);
		
	}

	@Override
	public PageList<ScmFhMarketSelesReturn> marketSelesReturnDatail(String orderCode) {
		ScmFhMarketSelesReturnMaster byOrderCode = scmFhMarketSelesReturnMasterDao.getByOrderCode(orderCode);
		List<ScmFhMarketSelesReturn> byMainId = scmFhMarketSelesReturnDao.getByMainId(byOrderCode.getId());
		PageList<ScmFhMarketSelesReturn> pageList = new PageList<ScmFhMarketSelesReturn>();
		pageList.setPage(1);
		pageList.setPageSize(10);
		pageList.setRows(byMainId);
		pageList.setTotal(byMainId.size());
		return pageList;
	}

}
