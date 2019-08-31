package com.winway.scm.persistence.manager.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.hotent.base.feign.BpmRuntimeFeignService;
import com.hotent.base.modelvo.CustomStartResult;
import com.hotent.base.modelvo.StartFlowParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.purchase.util.QuarterUtil;
import com.winway.purchase.util.ScmPuthWmsUtil;
import com.winway.purchase.vo.ReturnWmsVo;
import com.winway.scm.model.ScmCgContractProduct;
import com.winway.scm.model.ScmCgProRepProduct;
import com.winway.scm.model.ScmCgProReplacementOrder;
import com.winway.scm.model.ScmCgProcurementOrder;
import com.winway.scm.persistence.dao.ScmCgContractProductDao;
import com.winway.scm.persistence.dao.ScmCgProRepProductDao;
import com.winway.scm.persistence.dao.ScmCgProReplacementOrderDao;
import com.winway.scm.persistence.dao.ScmCgProcurementOrderDao;
import com.winway.scm.persistence.manager.ScmCgProReplacementOrderManager;
import com.winway.scm.vo.WmsCgbdReceiptVo;

/**
 * <pre>
 * 描述：采购订单补单表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-26 13:47:44
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
@Service("scmCgProReplacementOrderManager")
public class ScmCgProReplacementOrderManagerImpl extends AbstractManagerImpl<String, ScmCgProReplacementOrder> implements ScmCgProReplacementOrderManager {
	private Logger logger  =  LoggerFactory.getLogger(ScmCgProReplacementOrderManagerImpl.class);
	
	@Resource
    ScmCgProReplacementOrderDao scmCgProReplacementOrderDao;
    @Resource
    ScmCgProRepProductDao scmCgProRepProductDao;
    @Resource
    ScmCgContractProductDao scmCgContractProductDao;

    @Resource
    ScmCgProcurementOrderDao scmCgProcurementOrderDao;
    @Resource
    BpmRuntimeFeignService bpmRuntimeFeignService;


    @Override
    protected MyBatisDao<String, ScmCgProReplacementOrder> getDao() {
        return scmCgProReplacementOrderDao;
    }

    /**
     * 创建实体包含子表实体
     */
    public void create(ScmCgProReplacementOrder scmCgProReplacementOrder) {
        String orderId = scmCgProReplacementOrder.getOrderId();
        ScmCgProcurementOrder scmCgProcurementOrder = scmCgProcurementOrderDao.get(orderId);
        if (scmCgProcurementOrder == null) {
            throw new RuntimeException("请选择订单后发起补单 ");
        }
        scmCgProReplacementOrder.setGetMoneyWarehouse(scmCgProcurementOrder.getGetMoneyWarehouse());
        scmCgProReplacementOrder.setWarehouseCode(scmCgProcurementOrder.getWarehouseCode());
        scmCgProReplacementOrder.setReplacementOrderCode(QuarterUtil.getCode("CGBD"));
        scmCgProReplacementOrder.setContractId(scmCgProcurementOrder.getContractId());
        scmCgProReplacementOrder.setCurrency(scmCgProcurementOrder.getCurrency());
        scmCgProReplacementOrder.setOrderDate(new Date());
        scmCgProReplacementOrder.setContractCode(scmCgProcurementOrder.getContractCode());
        scmCgProReplacementOrder.setIsToWMS('0');
        scmCgProReplacementOrder.setSpeciesSum(0);
        scmCgProReplacementOrder.setProductSum(0);
        scmCgProReplacementOrder.setSumPrice(0.0);
        super.create(scmCgProReplacementOrder);
    }

    /**
     * 删除记录包含子表记录
     */
    public void remove(String entityId) {
        super.remove(entityId);
        scmCgProRepProductDao.delByMainId(entityId);
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
    public ScmCgProReplacementOrder get(String entityId) {
        ScmCgProReplacementOrder scmCgProReplacementOrder = super.get(entityId);
        List<ScmCgProRepProduct> scmCgProRepProductList = scmCgProRepProductDao.getByMainId(entityId);
        for (ScmCgProRepProduct product : scmCgProRepProductList) {
            ScmCgContractProduct scmCgContractProduct = scmCgContractProductDao.get(product.getContractProductId());
            product.setSurplusAmount(scmCgContractProduct.getSurplusAmount());
        }
        scmCgProReplacementOrder.setScmCgProRepProductList(scmCgProRepProductList);
        return scmCgProReplacementOrder;
    }

    /**
     * 更新实体同时更新子表记录
     */
    public void update(ScmCgProReplacementOrder scmCgProReplacementOrder) {
        Map listProductByProRepOrderId = scmCgProRepProductDao.listProductByProRepOrderId(scmCgProReplacementOrder.getId());
        scmCgProReplacementOrder.setSpeciesSum(Integer.parseInt(listProductByProRepOrderId.get("speciesSum").toString()));
        scmCgProReplacementOrder.setProductSum(Integer.parseInt(listProductByProRepOrderId.get("productSum").toString()));
        scmCgProReplacementOrder.setSumPrice(Double.parseDouble(listProductByProRepOrderId.get("sumPrice").toString()));
        super.update(scmCgProReplacementOrder);
    }

    @Override
    public void sendApply(ScmCgProReplacementOrder scmCgProReplacementOrder) {
        ScmCgProReplacementOrder scmCgProReplacementOrder2 = scmCgProReplacementOrderDao.get(scmCgProReplacementOrder.getId());
        StartFlowParam startFlowParam = new StartFlowParam("cgbdgl", "SCM", "approvalId");
        startFlowParam.setFormType("frame");
        CustomStartResult customStartResult = null;
        try {
            System.out.println("采购补单申请");
            customStartResult = bpmRuntimeFeignService.customStart(startFlowParam);
            String approvalId = customStartResult.getInstId();
            scmCgProReplacementOrder2.setApprovalId(approvalId);
            scmCgProReplacementOrder2.setApprovalState("1");
            scmCgProReplacementOrderDao.update(scmCgProReplacementOrder2);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("启动工作流失败");
        }
    }

    @Override
    public ScmCgProReplacementOrder getProOrderFirstByApprovalId(String approvalId) {
        ScmCgProReplacementOrder proOrderFirstByApprovalId = scmCgProReplacementOrderDao.getProOrderFirstByApprovalId(approvalId);
        if (proOrderFirstByApprovalId == null) {
            throw new RuntimeException("未查询到审批数据");
        }
        ScmCgProReplacementOrder scmCgProReplacementOrder = get(proOrderFirstByApprovalId.getId());
        return scmCgProReplacementOrder;
    }

    @Override
    public void endApply(JsonNode jsonNode) {
        String approvalId = jsonNode.get("instId").asText();
        String actionName = jsonNode.get("actionName").asText();
        ScmCgProReplacementOrder scmCgProReplacementOrder = scmCgProReplacementOrderDao.getProOrderFirstByApprovalId(approvalId);
        if (scmCgProReplacementOrder == null) {
            throw new RuntimeException("未查询到业务数据,处理异常");
        }
        String endEvent = jsonNode.get("eventType").asText();
        if ("agree".equals(actionName) && "endEvent".equals(endEvent)) {
        	scmCgProReplacementOrder.setApprovalState("2");
            /*TODO whw 1.2推送采购补单开始*/
            try {
                Map map = new HashMap(10);
                Map mapMain = scmCgProReplacementOrderDao.getProReplacementOrderById(scmCgProReplacementOrder.getId());
                if(mapMain == null) {
                	throw new RuntimeException("采购补单信息不存在");
                }
                map.putAll(mapMain);
                map.put("type", "Po_Norm");
                List<Map> orderProductList = scmCgProRepProductDao.getScmCgProRepProductListM(scmCgProReplacementOrder.getId());
                for(Map mapT : orderProductList) {
                	mapT.put("procurementCode", mapMain.get("replacementOrderCode"));
                }
                map.put("orderProduct", orderProductList);
                boolean puth = ScmPuthWmsUtil.puth(JSONObject.toJSONString(map), "/prolog_aomei_interface/interface/repair/purchase/scmPurchaseRepairNotify.json","推送采购补单到wms");
                if(puth) {
                	scmCgProReplacementOrder.setIsToWMS('1');
                	scmCgProReplacementOrder.setIsAffirmWms("0");
        		}else{
        			scmCgProReplacementOrder.setIsToWMS('0');
        			scmCgProReplacementOrder.setIsAffirmWms("0");
        		}
            }catch(Exception e) {
            	scmCgProReplacementOrder.setIsToWMS('0');
            	scmCgProReplacementOrder.setIsAffirmWms("0");
            	logger.error("WMS接口异常[1.2推送采购补单异常]", e);
            }
            scmCgProReplacementOrder.setIsManageSap('0');
            scmCgProReplacementOrder.setIsAffirmWms("0");
            scmCgProReplacementOrderDao.update(scmCgProReplacementOrder);
            /*1.2推送采购补单结束*/
        } else if ("agree".equals(actionName)) {
        } else if ("reject".equals(actionName)) {
        } else if ("backToStart".equals(actionName)) {
        } else if ("opposeTrans".equals(actionName)) {
        } else if ("endProcess".equals(actionName)) {
        	scmCgProReplacementOrder.setApprovalState("3");
        	scmCgProReplacementOrder.setIsManageSap('0');
            scmCgProReplacementOrder.setIsAffirmWms("0");
            scmCgProReplacementOrderDao.update(scmCgProReplacementOrder);
        }
    }

	@Override
	public ReturnWmsVo receipt(WmsCgbdReceiptVo wmsCgbdReceiptVo) {
		/*TODO whw 1.2采购补单回执开始*/
		ReturnWmsVo returnWmsVo = new ReturnWmsVo();
		if(!"1".equals(wmsCgbdReceiptVo.getTypt())) {
			return null;
		}
		if(wmsCgbdReceiptVo.getOrderCode() == null || wmsCgbdReceiptVo.getOrderCode().length() == 0) {
			returnWmsVo.setStute("500");
			returnWmsVo.setMsg("orderCode参数异常");
			return returnWmsVo;
		}
		try {
			ScmCgProReplacementOrder scmCgProReplacementOrder = scmCgProReplacementOrderDao.getProOrderFirstByCode(wmsCgbdReceiptVo.getOrderCode());
			if(scmCgProReplacementOrder == null) {
				returnWmsVo.setStute("500");
				returnWmsVo.setMsg("采购补单不存在【补单号："+ wmsCgbdReceiptVo.getOrderCode() +"】");
				return returnWmsVo;
			}
			if(!"1".equals(scmCgProReplacementOrder.getIsAffirmWms())) {
				scmCgProReplacementOrder.setIsAffirmWms("2");
				scmCgProReplacementOrderDao.update(scmCgProReplacementOrder);
			}
			returnWmsVo.setStute("200");
			returnWmsVo.setMsg("处理成功");
		}catch(Exception e) {
			returnWmsVo.setStute("500");
			returnWmsVo.setMsg("处理失败");
		}
		return returnWmsVo;
		/*采购补单回执结束*/
	}
    
}
