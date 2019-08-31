package com.winway.scm.persistence.manager.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import com.hotent.base.feign.BpmRuntimeFeignService;
import com.hotent.base.modelvo.CustomStartResult;
import com.hotent.base.modelvo.StartFlowParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.purchase.feign.ScmMasterDateFeignService;
import com.winway.purchase.util.QuarterUtil;
import com.winway.purchase.util.ScmPuthWmsUtil;
import com.winway.purchase.vo.ReturnWmsVo;
import com.winway.scm.model.ScmGspRejectionMaster;
import com.winway.scm.model.ScmGspRejectionProduct;
import com.winway.scm.model.ScmZsjProduct;
import com.winway.scm.persistence.dao.ScmGspRejectionMasterDao;
import com.winway.scm.persistence.dao.ScmGspRejectionProductDao;
import com.winway.scm.persistence.manager.ScmGspRejectionMasterManager;
import com.winway.scm.persistence.manager.ScmGspRejectionProductManager;
import com.winway.scm.vo.WmsCgjsReceiptVo;
import org.springframework.transaction.annotation.Transactional;

/**
 * <pre>
 * 描述：药品拒收报告单主表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-24 12:00:21
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmGspRejectionMasterManager")
public class ScmGspRejectionMasterManagerImpl extends AbstractManagerImpl<String, ScmGspRejectionMaster> implements ScmGspRejectionMasterManager {
    private Logger logger = LoggerFactory.getLogger(ScmGspRejectionMasterManagerImpl.class);
	@Resource
    ScmGspRejectionMasterDao scmGspRejectionMasterDao;
    @Resource
    ScmGspRejectionProductDao scmGspRejectionProductDao;
    @Resource
    ScmGspRejectionProductManager scmGspRejectionProductManager;
    @Resource
    ScmMasterDateFeignService masterDateFeignService;
    @Resource
    BpmRuntimeFeignService bpmRuntimeFeignService;

    @Override
    protected MyBatisDao<String, ScmGspRejectionMaster> getDao() {
        return scmGspRejectionMasterDao;
    }

    /**
     * 创建实体包含子表实体
     */
    public void create(ScmGspRejectionMaster scmGspRejectionMaster) {
        scmGspRejectionMaster.setRejectionCode(QuarterUtil.getCode("JSDH"));
        super.create(scmGspRejectionMaster);
        String pk = scmGspRejectionMaster.getId();
        List<ScmGspRejectionProduct> scmGspRejectionProductList = scmGspRejectionMaster.getScmGspRejectionProductList();

        for (ScmGspRejectionProduct scmGspRejectionProduct : scmGspRejectionProductList) {
            if (scmGspRejectionProduct.getRejectionSum() == null || scmGspRejectionProduct.getRejectionSum() <= 0) {
                throw new RuntimeException("拒收数量不得为空或小于0");
            }
            if (scmGspRejectionProduct.getRejectionSum() > scmGspRejectionProduct.getPurchaseSum() - scmGspRejectionProduct.getRejectionTotal()) {
                throw new RuntimeException("拒收数量不得大于订购数量");
            }
            //获取退货药品信息 计算总价
            String productByCode = masterDateFeignService.getProductByCode(scmGspRejectionProduct.getProductCode());
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                ScmZsjProduct product = objectMapper.readValue(productByCode, ScmZsjProduct.class);
                scmGspRejectionProduct.setProductName(product.getProductName());
                scmGspRejectionProduct.setCommonName(product.getCommonName());
                scmGspRejectionProduct.setMedicineType(product.getMedicineType());
                scmGspRejectionProduct.setProductState(product.getProductState());
                scmGspRejectionProduct.setUnit(product.getUnit());
                scmGspRejectionProduct.setManufacturer(product.getManufacturer());
                scmGspRejectionProduct.setPriceSum(scmGspRejectionProduct.getPrice() * scmGspRejectionProduct.getRejectionSum());
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("药品信息解析异常");
            }
            scmGspRejectionProduct.setMasterId(pk);
            // 保存已拒数量到该订单下的商品
            masterDateFeignService.updatebyParam(scmGspRejectionMaster.getProcurementCode(), scmGspRejectionProduct.getProductCode(), scmGspRejectionProduct.getRejectionSum().toString());
            scmGspRejectionProductManager.create(scmGspRejectionProduct);
        }
    }

    /**
     * 删除记录包含子表记录
     */
    public void remove(String entityId) {
        super.remove(entityId);
        scmGspRejectionProductDao.delByMainId(entityId);
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
    public ScmGspRejectionMaster get(String entityId) {
        ScmGspRejectionMaster scmGspRejectionMaster = super.get(entityId);
        List<ScmGspRejectionProduct> scmGspRejectionProductList = scmGspRejectionProductDao.getByMainId(entityId);
        for (ScmGspRejectionProduct product : scmGspRejectionProductList) {
            String rejectTotal = masterDateFeignService.getRejectTotalByParam(scmGspRejectionMaster.getProcurementCode(), product.getProductCode());
            product.setRejectionTotal(Integer.parseInt(rejectTotal));
        }
        scmGspRejectionMaster.setScmGspRejectionProductList(scmGspRejectionProductList);
        return scmGspRejectionMaster;
    }

    /**
     * 更新实体同时更新子表记录
     */
    public void update(ScmGspRejectionMaster scmGspRejectionMaster) {
        String id = scmGspRejectionMaster.getId();
        super.update(scmGspRejectionMaster);
        //修复采购该订单商品的已拒数量
        List<ScmGspRejectionProduct> byMainId = scmGspRejectionProductDao.getByMainId(id);
        for (ScmGspRejectionProduct p : byMainId) {
            masterDateFeignService.updatebyParam(scmGspRejectionMaster.getProcurementCode(), p.getProductCode(), String.valueOf(-p.getRejectionSum()));
        }
        scmGspRejectionProductDao.delByMainId(id);
        Double priceSum = 0.0;
        Set<String> set = new HashSet<>();
        Integer totalSum = 0;
        List<ScmGspRejectionProduct> scmGspRejectionProductList = scmGspRejectionMaster.getScmGspRejectionProductList();
        for (ScmGspRejectionProduct scmGspRejectionProduct : scmGspRejectionProductList) {
            scmGspRejectionProduct.setMasterId(id);
            set.add(scmGspRejectionProduct.getProductCode());
            priceSum += scmGspRejectionProduct.getPriceSum();
            totalSum += scmGspRejectionProduct.getRejectionSum();
            // 保存已拒数量到该订单下的商品
            masterDateFeignService.updatebyParam(scmGspRejectionMaster.getProcurementCode(), scmGspRejectionProduct.getProductCode(), scmGspRejectionProduct.getRejectionSum().toString());
            scmGspRejectionProductManager.create(scmGspRejectionProduct);
        }
        scmGspRejectionMaster.setKindSum(String.valueOf(set.size()));
        scmGspRejectionMaster.setPriceSum(priceSum);
        scmGspRejectionMaster.setTotalNum(String.valueOf(totalSum));
    }

    @Transactional
    @Override
    public void sendApply(ScmGspRejectionMaster scmGspRejectionMaster) {
        ScmGspRejectionMaster scmGspRejectionMaster1 = get(scmGspRejectionMaster.getId());
        if (scmGspRejectionMaster1 == null) {
            throw new RuntimeException("数据错误，没有找到要审批的主表数据");
        } else {
            if ("1".equals(scmGspRejectionMaster1.getApprovalState())) {
                throw new RuntimeException("该拒收数据正在审批中，不得二次申请");
            }
            List<ScmGspRejectionProduct> scmGspRejectionProductList = scmGspRejectionMaster.getScmGspRejectionProductList();
            if (scmGspRejectionProductList == null || scmGspRejectionProductList.isEmpty()) {
                throw new RuntimeException("拒收药品不得为空");
            }
            StartFlowParam startFlowParam = new StartFlowParam("ypjssq", "SCM", "approvalId");
            startFlowParam.setFormType("frame");
            CustomStartResult customStartResult = null;
            try {
                System.out.println("发起药品拒收申请");
                customStartResult = bpmRuntimeFeignService.customStart(startFlowParam);
                String approvalId = customStartResult.getInstId();
                scmGspRejectionMaster.setApprovalId(approvalId);
                scmGspRejectionMaster.setApprovalState("1");
                update(scmGspRejectionMaster);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("启动工作流失败");
            }

        }
    }

    @Override
    public void endApply(JsonNode jsonNode) {
        String approvalId = jsonNode.get("instId").asText();
        String actionName = jsonNode.get("actionName").asText();
        //根据审批id查询数据
        ScmGspRejectionMaster byApprovalState = scmGspRejectionMasterDao.getByApprovalState(approvalId);
        if (byApprovalState == null) {
            throw new RuntimeException("未查询到业务数据,处理异常");
        }
        String endEvent = jsonNode.get("eventType").asText();
        if ("agree".equals(actionName) && "endEvent".equals(endEvent)) {
        	byApprovalState.setApprovalState("2");
            try {
                /*TODO whw 1.8 SCM推送采购拒收开始*/
                try {
                	 Map map = new HashMap(10);
                     map.put("rejectionCode", byApprovalState.getRejectionCode());
                     map.put("createPersion", byApprovalState.getCreatePersion());
                     map.put("ownerId", byApprovalState.getOwnerId());
                     map.put("supplierName", byApprovalState.getSupplierName());
                     map.put("supplierCode", byApprovalState.getSupplierCode());
                     map.put("getMoneyWarehouse", byApprovalState.getGetMoneyWarehouse());
                     map.put("getMoneyWarehouseCode", byApprovalState.getGetMoneyWarehouseCode());
                     map.put("contractCode", byApprovalState.getContractCode());
                     map.put("procurementCode", byApprovalState.getProcurementCode());
                     List<ScmGspRejectionProduct> scmGspRejectionProductList = scmGspRejectionProductDao.getByMainId(byApprovalState.getId());
                     List<Map> listT = new ArrayList<Map>();
                     for(ScmGspRejectionProduct scmGspRejectionProduct : scmGspRejectionProductList) {
                    	 Map mapT = new HashMap(10);
                    	 mapT.put("productCode", scmGspRejectionProduct.getProductCode());
                    	 mapT.put("productName", scmGspRejectionProduct.getProductName());
                    	 mapT.put("commonName", scmGspRejectionProduct.getCommonName());
                    	 mapT.put("medicineType", scmGspRejectionProduct.getMedicineType());
                    	 mapT.put("productState", scmGspRejectionProduct.getProductState());
                    	 mapT.put("unit", scmGspRejectionProduct.getUnit());
                    	 mapT.put("manufacturer", scmGspRejectionProduct.getManufacturer());
                    	 mapT.put("rejectionSum", scmGspRejectionProduct.getRejectionSum());
                    	 mapT.put("price", scmGspRejectionProduct.getPrice());
                    	 mapT.put("priceSum", scmGspRejectionProduct.getPriceSum());
                    	 mapT.put("rejectionCause", scmGspRejectionProduct.getRejectionCause());
                    	 mapT.put("memo", scmGspRejectionProduct.getMemo());
                    	 listT.add(mapT);
                     }
                     map.put("productList",listT);
                     boolean puth = ScmPuthWmsUtil.puth(JSONObject.toJSONString(map), "/prolog_aomei_interface/interface/rejection/purchase/scmPurchaseRejectionNotify.json","采购拒收推送wms");
                	if(puth) {
                		byApprovalState.setIsSMS("1");
                		byApprovalState.setIsAffirmWms("0");
             		}else{
             			byApprovalState.setIsSMS("0");
             			byApprovalState.setIsAffirmWms("0");
             		}
                }catch(Exception e) {
                	logger.error("WMS接口异常[1.8 SCM推送采购拒收异常]", e);
                }
                /*1.8 SCM推送采购拒收结束*/
            } catch (Exception e) {
                e.printStackTrace();
            }
            scmGspRejectionMasterDao.update(byApprovalState);
        } else if ("agree".equals(actionName)) {
        } else if ("reject".equals(actionName)) {
        } else if ("backToStart".equals(actionName)) {
        } else if ("opposeTrans".equals(actionName)) {
        } else if ("endProcess".equals(actionName)) {
        	 byApprovalState.setApprovalState("3");
        	 scmGspRejectionMasterDao.update(byApprovalState);
        }
    }

    @Override
    public ScmGspRejectionMaster getbyApprovalId(String id) {
        ScmGspRejectionMaster byApprovalState = scmGspRejectionMasterDao.getByApprovalState(id);
        if (byApprovalState != null) {
            byApprovalState = get(byApprovalState.getId());
        }
        return byApprovalState;
    }

	@Override
	public ReturnWmsVo receipt(WmsCgjsReceiptVo wmsCgjsReceiptVo) {
		/*TODO whw 1.8SCM采购拒收回执开始*/
		ReturnWmsVo returnWmsVo = new ReturnWmsVo();
		if(!"1".equals(wmsCgjsReceiptVo.getTypt())) {
			return null;
		}
		if(wmsCgjsReceiptVo.getOrderCode() == null || wmsCgjsReceiptVo.getOrderCode().length() == 0) {
			returnWmsVo.setStute("500");
			returnWmsVo.setMsg("orderCode参数异常");
			return returnWmsVo;
		}
		try {
			ScmGspRejectionMaster scmGspRejectionMaster = scmGspRejectionMasterDao.getByCode(wmsCgjsReceiptVo.getOrderCode());
			if(scmGspRejectionMaster == null) {
				returnWmsVo.setStute("500");
				returnWmsVo.setMsg("采购补单不存在【补单号："+ wmsCgjsReceiptVo.getOrderCode() +"】");
				return returnWmsVo;
			}
			if(!"1".equals(scmGspRejectionMaster.getIsAffirmWms())) {
				scmGspRejectionMaster.setIsAffirmWms("1");
				scmGspRejectionMasterDao.update(scmGspRejectionMaster);
			}
			returnWmsVo.setStute("200");
			returnWmsVo.setMsg("处理成功");
		}catch(Exception e) {
			returnWmsVo.setStute("500");
			returnWmsVo.setMsg("处理失败");
		}
		return returnWmsVo;
		/*采购拒收回执结束*/
	}
    
}
