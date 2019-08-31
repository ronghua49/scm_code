package com.winway.scm.persistence.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import com.hotent.base.feign.BpmRuntimeFeignService;
import com.hotent.base.modelvo.CustomStartResult;
import com.hotent.base.modelvo.StartFlowParam;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.pagehelper.PageHelper;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.query.PageBean;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.BeanUtils;
import com.winway.purchase.util.QuarterUtil;
import com.winway.purchase.util.ScmPuthWmsUtil;
import com.winway.purchase.vo.ScmWmsReceipt;
import com.winway.purchase.vo.ScmWmsReturnData;
import com.winway.purchase.vo.WmsPageList;
import com.winway.scm.model.InventoryProduct;
import com.winway.scm.model.ScmKcAllot;
import com.winway.scm.model.ScmKcAllotMaster;
import com.winway.scm.persistence.dao.ScmKcAllotDao;
import com.winway.scm.persistence.dao.ScmKcAllotMasterDao;
import com.winway.scm.persistence.manager.ScmKcAllotManager;
import com.winway.scm.persistence.manager.ScmKcAllotMasterManager;
import com.winway.scm.vo.ScmGspDrupChenkVo;
import com.winway.scm.vo.ScmGspDrupReplenishVo;
import com.winway.scm.vo.ScmKcProductSumQuery;
import com.winway.scm.vo.ScmWmsFindNearlyEffective;
import com.winway.scm.vo.ScmWmsNearlyEffective;
import com.winway.scm.vo.ScmWmsProductSum;
import org.springframework.transaction.annotation.Transactional;

/**
 * <pre>
 * 描述：库存调拨总表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-23 17:00:46
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmKcAllotMasterManager")
public class ScmKcAllotMasterManagerImpl extends AbstractManagerImpl<String, ScmKcAllotMaster> implements ScmKcAllotMasterManager {
	@Resource
    ScmKcAllotMasterDao scmKcAllotMasterDao;
    @Resource
    ScmKcAllotDao scmKcAllotDao;
    @Resource
    ScmKcAllotManager scmKcAllotManager;
	@Resource
	BpmRuntimeFeignService bpmRuntimeFeignService;

    @Override
    protected MyBatisDao<String, ScmKcAllotMaster> getDao() {
        return scmKcAllotMasterDao;
    }

    /**
     * 创建实体包含子表实体
     */
    public void create(ScmKcAllotMaster scmKcAllotMaster) {
        setkindAndPriceSum(scmKcAllotMaster);
        scmKcAllotMaster.setAllotCode(QuarterUtil.getCode("DBDH"));
        super.create(scmKcAllotMaster);
        String pk = scmKcAllotMaster.getId();
        List<ScmKcAllot> scmKcAllotList = scmKcAllotMaster.getScmKcAllotList();
        createSon(pk, scmKcAllotList);
    }


    /**
     * 删除记录包含子表记录
     */
    public void remove(String entityId) {
        super.remove(entityId);
        scmKcAllotDao.delByMainId(entityId);
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
    public ScmKcAllotMaster get(String entityId) {
        ScmKcAllotMaster scmKcAllotMaster = super.get(entityId);
        List<ScmKcAllot> scmKcAllotList = scmKcAllotDao.getByMainId(entityId);
        scmKcAllotMaster.setScmKcAllotList(scmKcAllotList);
        return scmKcAllotMaster;
    }

    /**
     * 更新实体同时更新子表记录
     */
    public void update(ScmKcAllotMaster scmKcAllotMaster) {
        setkindAndPriceSum(scmKcAllotMaster);
        super.update(scmKcAllotMaster);
        String id = scmKcAllotMaster.getId();
        scmKcAllotDao.delByMainId(id);
        List<ScmKcAllot> scmKcAllotList = scmKcAllotMaster.getScmKcAllotList();
        createSon(id, scmKcAllotList);
    }

    private void setkindAndPriceSum(ScmKcAllotMaster scmKcAllotMaster) {
        List<ScmKcAllot> scmKcAllotList = scmKcAllotMaster.getScmKcAllotList();
        //获取本次调拨的总金额和品种数
        Double priceSum = 0.0;
        Set<String> set = new HashSet<>();
        for (ScmKcAllot scmKcAllot : scmKcAllotList) {
            priceSum += scmKcAllot.getPriceSum();
            set.add(scmKcAllot.getProductCode());
        }
        scmKcAllotMaster.setPriceSum(priceSum);
        scmKcAllotMaster.setVarietySum(set.size());
    }


    private void createSon(String pk, List<ScmKcAllot> scmKcAllotList) {
        for (ScmKcAllot scmKcAllot : scmKcAllotList) {
            if (scmKcAllot.getAllotSum() == null || scmKcAllot.getQuantityInStock() == null) {
                throw new RuntimeException("参数错误，调拨量和库存量不得为空");
            }
            if (scmKcAllot.getAllotSum() > scmKcAllot.getQuantityInStock()) {
                throw new RuntimeException("调拨数量不得大于库存数量");
            }
            scmKcAllot.setMasterId(pk);
            scmKcAllotManager.create(scmKcAllot);
        }
    }

    @Transactional
    @Override
    public ScmKcAllotMaster sendApply(ScmKcAllotMaster scmKcAllotMaster) {
        ScmKcAllotMaster scmKcAllotMaster1 = get(scmKcAllotMaster.getId());
		StartFlowParam startFlowParam = new StartFlowParam("kcdb", "SCM", "approvalId");
		startFlowParam.setFormType("frame");
		CustomStartResult customStartResult = null;
		try {
			System.out.println("发起库存调拨发起申请");
			customStartResult = bpmRuntimeFeignService.customStart(startFlowParam);
			String approvalId = customStartResult.getInstId();
			scmKcAllotMaster.setApprovalState("1");
			scmKcAllotMaster.setApprovalId(approvalId);
			if (scmKcAllotMaster1 == null) {
				create(scmKcAllotMaster);
			} else {
				update(scmKcAllotMaster);
			}
			return get(scmKcAllotMaster.getId());
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
        ScmKcAllotMaster byApprovalState = scmKcAllotMasterDao.getByApprovalState(approvalId);
        if (byApprovalState == null) {
            throw new RuntimeException("未查询到业务数据,处理异常");
        }
        String endEvent = jsonNode.get("eventType").asText();
        if ("agree".equals(actionName) && "endEvent".equals(endEvent)) {
        	 byApprovalState.setSuccessDate(new Date());
             byApprovalState.setApprovalState("2");
             try {
             	List<ScmKcAllot> byMainId = scmKcAllotDao.getByMainId(byApprovalState.getId());
             	List<Map<String,Object>> list = new ArrayList<>();
             	for (ScmKcAllot scmKcAllot : byMainId) {
 					Map<String,Object> map= new HashMap<String,Object>();
 					map.put("batchNum", scmKcAllot.getBatchNum());
 					map.put("productName", scmKcAllot.getProductName());
 					map.put("medicineType", scmKcAllot.getMedicineType());
 					map.put("productState", scmKcAllot.getProductState());
 					map.put("unit", scmKcAllot.getUnit());
 					map.put("manufacturer", scmKcAllot.getManufacturer());
 					map.put("manufactureDate", scmKcAllot.getManufactureDate());
 					map.put("expiryDate", scmKcAllot.getExpiryDate());
 					map.put("inboundDate", scmKcAllot.getInboundDate());
 					map.put("quantityInStock", scmKcAllot.getQuantityInStock());
 					map.put("allotSum", scmKcAllot.getAllotSum());
 					map.put("price", scmKcAllot.getPrice());
 					map.put("priceSum", scmKcAllot.getPriceSum());
 					map.put("memo", scmKcAllot.getMemo());
 					map.put("productCode", scmKcAllot.getCode());
 					list.add(map);
 				}
             	//调用wms 推送数据
             	Map map = new HashMap<>();
             	map.put("allotCode", byApprovalState.getAllotCode());
             	map.put("outboundArea", byApprovalState.getOutboundArea());
             	map.put("outboundCode", byApprovalState.getOutboundCode());
             	map.put("outboundDate", byApprovalState.getOutboundDate());
             	map.put("varietySum", byApprovalState.getVarietySum());
             	map.put("priceSum", byApprovalState.getPriceSum());
             	map.put("storageArea", byApprovalState.getStorageArea());
             	map.put("storageCode", byApprovalState.getStorageCode());
             	map.put("storageDate", byApprovalState.getStorageDate());
             	map.put("ownerId", byApprovalState.getOwnerId());
             	map.put("productList", list);
             	String jsonString = JSON.toJSONString(map);
             	boolean puth = ScmPuthWmsUtil.puth(jsonString, "/prolog_aomei_interface/interface/inventory/allocation/scmInventoryAllocationNotify.json","库存调拨推送wms");
             	if(puth) {
             		byApprovalState.setIsToWms("1");
             	}else{
             		byApprovalState.setIsToWms("0");
             	}
             	byApprovalState.setIsAffirmWms("0");
             } catch (Exception e) {
                 e.printStackTrace();
             }
             byApprovalState.setIsManageSap("0");
             byApprovalState.setSapManageType("0");
             scmKcAllotMasterDao.update(byApprovalState);
        } else if ("agree".equals(actionName)) {
        } else if ("reject".equals(actionName)) {
        } else if ("backToStart".equals(actionName)) {
        } else if ("opposeTrans".equals(actionName)) {
        } else if ("endProcess".equals(actionName)) {
        	byApprovalState.setApprovalState("3");
        	byApprovalState.setIsManageSap("0");
            byApprovalState.setSapManageType("0");
            scmKcAllotMasterDao.update(byApprovalState);
        }
    }

    @Override
    public PageList<InventoryProduct> queryInventory(QueryFilter queryFilter) {
        PageBean pageBean = queryFilter.getPageBean();
        if (BeanUtils.isEmpty(pageBean)) {
            PageHelper.startPage(1, 2147483647, false);
        } else {
            PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
        }
        Map<String, String> map = new HashMap<>();
        Map<String, Object> params = queryFilter.getParams();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            map.put(entry.getKey(), (String) entry.getValue());
        }
        // todo 调用wms 获取库存信息
//       String s = HttpClientUtil.doPost(url, map);
        InventoryProduct product = new InventoryProduct();
        product.setMedicineType("普通药品");
        product.setBatchNumber("12498325");
        product.setProductName("帕罗西汀");
        product.setExpiryDate("2021-09-10");
        product.setInboundDate("2012-02-12");
        product.setCurrInventoryNum("4500");
        product.setPrice("12");
        product.setWarehouse("海口仓库");
        product.setGuiGe("20粒/盒");
        product.setProduceDate("2001-12-23");
        product.setManufacturer("澳美制药厂");
        product.setUnit("盒");
        product.setCommonName("镇痛剂");
        product.setProductCode("sdvf324");
        product.setAgentType("冲剂");
        product.setApprovalCode("23432");
        product.setMedicineClassify("精神类");
        product.setBusinessScope("青年");
        product.setProductId("1983274");
        InventoryProduct product1 = new InventoryProduct();
        product1.setMedicineType("普通药品");
        product1.setBatchNumber("1243425");
        product1.setProductName("帕罗西汀2");
        product1.setExpiryDate("2021-10-10");
        product1.setInboundDate("2012-03-12");
        product1.setCurrInventoryNum("5500");
        product1.setPrice("13");
        product1.setWarehouse("海南仓库");
        product1.setGuiGe("50粒/盒");
        product1.setProduceDate("2001-12-23");
        product1.setManufacturer("澳美制药厂2");
        product1.setUnit("支2");
        product1.setCommonName("镇痛剂2");
        product1.setProductCode("sdvf3242");
        product1.setAgentType("冲剂2");
        product1.setApprovalCode("234322");
        product1.setMedicineClassify("精神类2");
        product1.setBusinessScope("青年2");
        product1.setProductId("19832");
        List<InventoryProduct> inventoryProducts = new ArrayList<>();
        inventoryProducts.add(product);
        inventoryProducts.add(product1);
        String s = JSONObject.toJSONString(inventoryProducts);
        List<InventoryProduct> inventoryProductList = JSONObject.parseArray(s, InventoryProduct.class);
        return new PageList(inventoryProductList);
    }

    @Override
    public ScmKcAllotMaster getDetail(String approvalId) {
        ScmKcAllotMaster byApprovalState = scmKcAllotMasterDao.getByApprovalState(approvalId);
        if (byApprovalState == null) {
            throw new RuntimeException("没有查询到审批数据");
        }
        ScmKcAllotMaster scmKcAllotMaster = get(byApprovalState.getId());
        return scmKcAllotMaster;
    }

	@Override
	public PageList<ScmWmsProductSum> productSumQuery(ScmKcProductSumQuery scmKcProductSumQuery) {
		if(scmKcProductSumQuery.getPage() == 0) {
			scmKcProductSumQuery.setPage(1);
		}
		if(scmKcProductSumQuery.getPagesize() == 0) {
			scmKcProductSumQuery.setPagesize(10);
		}
		if(scmKcProductSumQuery.getOwnerId() == null || "".equals(scmKcProductSumQuery.getOwnerId())) {
			throw new RuntimeException("用户登入信息异常");
		}
		if("".equals(scmKcProductSumQuery.getProductName())) {
			scmKcProductSumQuery.setProductName(null);
		}
		if("".equals(scmKcProductSumQuery.getProductCode())) {
			scmKcProductSumQuery.setProductCode(null);
		}
		PageList<ScmWmsProductSum> pageList = new PageList<ScmWmsProductSum>();
		String jsonString = JSON.toJSONString(scmKcProductSumQuery);
		List<ScmWmsProductSum> list = new ArrayList<ScmWmsProductSum>();
		ScmWmsReturnData datPputh = ScmPuthWmsUtil.getDatPputh(jsonString, "/prolog_aomei_interface/interface/totalItem/inventory/scmItemInventoryTotalQuery.json");
		if(datPputh == null) {
			throw new RuntimeException("从WMS查询库存失败");
		}else{
			List<String> data = datPputh.getData();
			for (String string : data) {
				ScmWmsProductSum parseObject = JSON.parseObject(string,ScmWmsProductSum.class);
				list.add(parseObject);
			}
		}
		pageList.setPage(datPputh.getPage());
		pageList.setPageSize(datPputh.getPagesize());
		pageList.setRows(list);
		pageList.setTotal(datPputh.getTotal());
		return pageList;
	}

	@Override
	public PageList<ScmWmsNearlyEffective> findNearlyEffective(ScmWmsFindNearlyEffective scmWmsFindNearlyEffective) {
		if(scmWmsFindNearlyEffective.getPage() == 0) {
			scmWmsFindNearlyEffective.setPage(1);
		}
		if(scmWmsFindNearlyEffective.getPagesize() == 0) {
			scmWmsFindNearlyEffective.setPagesize(10);
		}
		if(scmWmsFindNearlyEffective.getOwnerId() == null || "".equals(scmWmsFindNearlyEffective.getOwnerId())) {
			throw new RuntimeException("用户登入信息异常");
		}
		if(scmWmsFindNearlyEffective.getExpiryDate() == null || "".equals(scmWmsFindNearlyEffective.getExpiryDate())) {
			scmWmsFindNearlyEffective.setExpiryDate("6");
		}
		if(scmWmsFindNearlyEffective.getProductCode() == null || "".equals(scmWmsFindNearlyEffective.getProductCode())) {
			scmWmsFindNearlyEffective.setExpiryDate(null);
		}
		if(scmWmsFindNearlyEffective.getProductName() == null || "".equals(scmWmsFindNearlyEffective.getProductName())) {
			scmWmsFindNearlyEffective.setExpiryDate(null);
		}
		
		String jsonString = JSON.toJSONString(scmWmsFindNearlyEffective);
		ScmWmsReturnData datPputh = ScmPuthWmsUtil.getDatPputh(jsonString, "/prolog_aomei_interface/interface/itemExpdate/inventory/scmItemExpdateInventoryQuery.json");
		if(datPputh == null) {
			throw new RuntimeException("WMS接口异常");
		}
		List<ScmWmsNearlyEffective> list = new ArrayList<ScmWmsNearlyEffective>();
		List<String> data = datPputh.getData();
		for (String string : data) {
			ScmWmsNearlyEffective parseObject = JSON.parseObject(string,ScmWmsNearlyEffective.class);
			list.add(parseObject);
		}
		PageList<ScmWmsNearlyEffective> pageList = new PageList<ScmWmsNearlyEffective>();
		pageList.setPage(datPputh.getPage());
		pageList.setPageSize(datPputh.getPagesize());
		pageList.setTotal(datPputh.getTotal());
		pageList.setRows(list);
		return pageList;
	}

	@Override
	public void receipt(ScmWmsReceipt scmWmsReceipt) {
		ScmKcAllotMaster scmKcAllotMaster = scmKcAllotMasterDao.getByOrderCode(scmWmsReceipt.getOrderCode());
		if(scmKcAllotMaster == null) {
			throw new RuntimeException("未查询到单据信息");
		}
		if("1".equals(scmWmsReceipt.getTypt())) {
			scmKcAllotMaster.setIsAffirmWms("1");
		}else if("2".equals(scmWmsReceipt.getTypt())) {
			scmKcAllotMaster.setIsAffirmWms("2");
		}else if ("3".equals(scmWmsReceipt.getTypt())) {
			scmKcAllotMaster.setIsAffirmWms("3");
		}else{
			throw new RuntimeException("请传递正确的状态码");
		}
		scmKcAllotMasterDao.update(scmKcAllotMaster);
		
	}

	@Override
	public WmsPageList<ScmGspDrupReplenishVo> allotInSap(String allotCode) {
		ScmKcAllotMaster byOrderCode = scmKcAllotMasterDao.getByOrderCode(allotCode);
		if(byOrderCode == null) {
			throw new RuntimeException("根据单号未查询到对应调拨单,请确认单号是否正确");
		}
		ScmGspDrupReplenishVo scmGspDrupReplenishVo = new ScmGspDrupReplenishVo();
		scmGspDrupReplenishVo.setPage(1);
		scmGspDrupReplenishVo.setPagesize(1000);
		scmGspDrupReplenishVo.setBillno(allotCode);
		scmGspDrupReplenishVo.setItemtype("1");
		scmGspDrupReplenishVo.setOwnerId(byOrderCode.getOwnerId());
		JSON.toJSONString(scmGspDrupReplenishVo);
		ScmWmsReturnData datPputh = ScmPuthWmsUtil.getDatPputh(JSONObject.toJSONString(scmGspDrupReplenishVo), "/prolog_aomei_interface/interface/itemSales/purchase/scmItemPurchaseSaleInventoryQuery.json");
		List<String> data = datPputh.getData();

		scmGspDrupReplenishVo.setItemtype("2");
		ScmWmsReturnData datPputh2 = ScmPuthWmsUtil.getDatPputh(JSONObject.toJSONString(scmGspDrupReplenishVo), "/prolog_aomei_interface/interface/itemSales/purchase/scmItemPurchaseSaleInventoryQuery.json");
		List<String> data2 = datPputh2.getData();
		
		List<ScmGspDrupReplenishVo> list = new ArrayList<ScmGspDrupReplenishVo>();
		for (String string : data) {
			ScmGspDrupReplenishVo parseObject = JSON.parseObject(string, ScmGspDrupReplenishVo.class);
			if("调拨入库".equals(parseObject.getBusinesstype())) {
				list.add(parseObject);
			}
		}
		for (String string : data2) {
			ScmGspDrupReplenishVo parseObject = JSON.parseObject(string, ScmGspDrupReplenishVo.class);
			if("调拨入库".equals(parseObject.getBusinesstype())) {
				list.add(parseObject);
			}
		}
		WmsPageList<ScmGspDrupReplenishVo> wmsPageList = new WmsPageList<ScmGspDrupReplenishVo>();
		wmsPageList.setPage(datPputh.getPage());
		wmsPageList.setPageSize(datPputh.getPagesize());
		wmsPageList.setTotal(datPputh.getTotal());
		wmsPageList.setRows(list);
		return wmsPageList;
	}

	@Override
	public WmsPageList<ScmGspDrupReplenishVo> allotOutSap(String allotCode) {
		ScmKcAllotMaster byOrderCode = scmKcAllotMasterDao.getByOrderCode(allotCode);
		if(byOrderCode == null) {
			throw new RuntimeException("根据单号未查询到对应调拨单,请确认单号是否正确");
		}
		ScmGspDrupReplenishVo scmGspDrupReplenishVo = new ScmGspDrupReplenishVo();
		scmGspDrupReplenishVo.setPage(1);
		scmGspDrupReplenishVo.setPagesize(1000);
		scmGspDrupReplenishVo.setBillno(allotCode);
		scmGspDrupReplenishVo.setItemtype("1");
		scmGspDrupReplenishVo.setOwnerId(byOrderCode.getOwnerId());
		ScmWmsReturnData datPputh = ScmPuthWmsUtil.getDatPputh(JSONObject.toJSONString(scmGspDrupReplenishVo), "/prolog_aomei_interface/interface/itemSales/purchase/scmItemPurchaseSaleInventoryQuery.json");
		List<String> data = datPputh.getData();
		scmGspDrupReplenishVo.setItemtype("2");
		ScmWmsReturnData datPputh2 = ScmPuthWmsUtil.getDatPputh(JSONObject.toJSONString(scmGspDrupReplenishVo), "/prolog_aomei_interface/interface/itemSales/purchase/scmItemPurchaseSaleInventoryQuery.json");
		List<String> data2 = datPputh2.getData();
		
		List<ScmGspDrupReplenishVo> list = new ArrayList<ScmGspDrupReplenishVo>();
		for (String string : data) {
			ScmGspDrupReplenishVo parseObject = JSON.parseObject(string, ScmGspDrupReplenishVo.class);
			if("调拨出库".equals(parseObject.getBusinesstype())) {
				list.add(parseObject);
			}
		}
		for (String string : data2) {
			ScmGspDrupReplenishVo parseObject = JSON.parseObject(string, ScmGspDrupReplenishVo.class);
			if("调拨出库".equals(parseObject.getBusinesstype())) {
				list.add(parseObject);
			}
		}
		WmsPageList<ScmGspDrupReplenishVo> wmsPageList = new WmsPageList<ScmGspDrupReplenishVo>();
		wmsPageList.setPage(datPputh.getPage());
		wmsPageList.setPageSize(datPputh.getPagesize());
		wmsPageList.setTotal(datPputh.getTotal());
		wmsPageList.setRows(list);
		return wmsPageList;
	}
}
