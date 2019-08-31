package com.winway.scm.persistence.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.hotent.base.feign.BpmRuntimeFeignService;
import com.hotent.base.modelvo.CustomStartResult;
import com.hotent.base.modelvo.StartFlowParam;
import com.hotent.base.util.AuthenticationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.pagehelper.PageHelper;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageBean;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.BeanUtils;
import com.winway.purchase.feign.ScmMasterDateFeignService;
import com.winway.purchase.util.QuarterUtil;
import com.winway.purchase.util.ScmPuthWmsUtil;
import com.winway.purchase.vo.ScmWmsReceipt;
import com.winway.purchase.vo.ScmWmsReturnData;
import com.winway.purchase.vo.WmsPageList;
import com.winway.scm.model.ScmCgProOrderProduct;
import com.winway.scm.model.ScmCgProReplacementOrder;
import com.winway.scm.model.ScmCgProcurementOrder;
import com.winway.scm.persistence.dao.ScmCgProOrderProductDao;
import com.winway.scm.persistence.dao.ScmCgProReplacementOrderDao;
import com.winway.scm.persistence.dao.ScmCgProcurementOrderDao;
import com.winway.scm.persistence.manager.ScmCgProcurementOrderManager;
import com.winway.scm.vo.ScmZsjDrogPuchaseRecords;
import com.winway.scm.vo.ScmZsjOrderProductToWms;
import com.winway.scm.vo.ScmZsjOrderToWms;
import com.winway.scm.vo.WmsfindcgrkDetailVo;
import com.winway.scm.vo.WmsfindcgrkInVo;
import com.winway.scm.vo.WmsfindcgrkVo;
import com.winway.scm.vo.WmsfindcgysInVo;
import com.winway.scm.vo.WmsfindcgysVo;
import com.winway.scm.vo.WmsfindcgysbybillnoVo;

/**
 * 
 * <pre> 
 * 描述：采购订单表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-26 13:47:44
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
@Service("scmCgProcurementOrderManager")
public class ScmCgProcurementOrderManagerImpl extends AbstractManagerImpl<String, ScmCgProcurementOrder> implements ScmCgProcurementOrderManager{
	private Logger logger = LoggerFactory.getLogger(ScmCgProcurementOrderManagerImpl.class);
	@Resource
	ScmCgProcurementOrderDao scmCgProcurementOrderDao;
	@Resource
	ScmCgProOrderProductDao scmCgProOrderProductDao;
	@Resource
	ScmCgProReplacementOrderDao scmCgProReplacementOrderDao;
	@Resource
	ScmMasterDateFeignService scmMasterDateFeignService;
	@Resource
	BpmRuntimeFeignService bpmRuntimeFeignService;
	
	
	@Override
	protected MyBatisDao<String, ScmCgProcurementOrder> getDao() {
		return scmCgProcurementOrderDao;
	}
	/**
	 * 创建实体包含子表实体
	 */
	public void create(ScmCgProcurementOrder scmCgProcurementOrder){
		scmCgProcurementOrder.setProcurementCode(QuarterUtil.getCode("CGDD"));
		scmCgProcurementOrder.setApprovalState("0");
		scmCgProcurementOrder.setIsToWMS('0');
		scmCgProcurementOrder.setSpeciesSum(0);
		scmCgProcurementOrder.setProductSum(0);
		scmCgProcurementOrder.setSumPrice(0.0);
    	super.create(scmCgProcurementOrder);
    }
	
	/**
	 * 删除记录包含子表记录
	 */
	public void remove(String entityId){
		super.remove(entityId);
    	scmCgProOrderProductDao.delByMainId(entityId);
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
    public ScmCgProcurementOrder get(String entityId){
    	ScmCgProcurementOrder scmCgProcurementOrder=super.get(entityId);
    	List<ScmCgProOrderProduct> scmCgProOrderProductList=scmCgProOrderProductDao.getByMainId(entityId);
		Map<?, ?> listProductByContractId = scmCgProOrderProductDao.listProductByProOrderId(scmCgProcurementOrder.getId());
		scmCgProcurementOrder.setSpeciesSum(Integer.parseInt(listProductByContractId.get("speciesSum").toString()));
		scmCgProcurementOrder.setProductSum(Integer.parseInt(listProductByContractId.get("productSum").toString()));
		scmCgProcurementOrder.setSumPrice(Double.parseDouble(listProductByContractId.get("sumPrice").toString()));
    	scmCgProcurementOrder.setScmCgProOrderProductList(scmCgProOrderProductList);
    	return scmCgProcurementOrder;
    }
    
    /**
     * 更新实体同时更新子表记录
     */
    public void update(ScmCgProcurementOrder scmCgProcurementOrder){
    	ScmCgProcurementOrder scmCgProcurementOrder2 = scmCgProcurementOrderDao.get(scmCgProcurementOrder.getId());
    	if(scmCgProcurementOrder2 == null) {
    		throw new RuntimeException("数据异常");
    	}
    	scmCgProcurementOrder.setProcurementCode(scmCgProcurementOrder2.getProcurementCode());
    	scmCgProcurementOrder.setIsToWMS('0');
    	scmCgProcurementOrder.setApprovalState("0");
		//统计数据,存入采购订单数据中
		Map<?, ?> listProductByContractId = scmCgProOrderProductDao.listProductByProOrderId(scmCgProcurementOrder.getId());
		scmCgProcurementOrder.setSpeciesSum(Integer.parseInt(listProductByContractId.get("speciesSum").toString()));
		scmCgProcurementOrder.setProductSum(Integer.parseInt(listProductByContractId.get("productSum").toString()));
		scmCgProcurementOrder.setSumPrice(Double.parseDouble(listProductByContractId.get("sumPrice").toString()));
		super.update(scmCgProcurementOrder);

    }
	@Override
	public ScmCgProcurementOrder getOrderAndRepOrder(String id) {
		// TODO Auto-generated method stub
		ScmCgProcurementOrder scmCgProcurementOrder = scmCgProcurementOrderDao.get(id);
		List<ScmCgProReplacementOrder> scmCgProReplacementOrderList = scmCgProReplacementOrderDao.getProReplacementOrderByOrderId(id);
		scmCgProcurementOrder.setScmCgProReplacementOrderList(scmCgProReplacementOrderList);
	    return scmCgProcurementOrder;
	}
	@Override
	public void sendApply(ScmCgProcurementOrder scmCgProcurementOrder) {
		// TODO Auto-generated method stub
		//0:待提交， 1:待审核，2：已审核。3：已驳回
		ScmCgProcurementOrder scmCgProcurementOrder2 = scmCgProcurementOrderDao.get(scmCgProcurementOrder.getId());
		StartFlowParam startFlowParam = new StartFlowParam("cgdd", "SCM", "approvalId");
		startFlowParam.setFormType("frame");
		CustomStartResult customStartResult = null;
		try {
			System.out.println("发起采购订单申请");
			customStartResult = bpmRuntimeFeignService.customStart(startFlowParam);
			String approvalId = customStartResult.getInstId();
			scmCgProcurementOrder2.setApprovalId(approvalId);
			scmCgProcurementOrder2.setApprovalState("1");
			scmCgProcurementOrderDao.update(scmCgProcurementOrder2);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("启动工作流失败");
		}
	}
	@Override
	public ScmCgProcurementOrder getOrderFirstByApprovalId(String approvalId) {
		ScmCgProcurementOrder orderFirstByApprovalId = scmCgProcurementOrderDao.getOrderFirstByApprovalId(approvalId);
		if(orderFirstByApprovalId == null) {
			throw new RuntimeException("未查询到数据");
		}
		ScmCgProcurementOrder scmCgProcurementOrder = get(orderFirstByApprovalId.getId());
		return scmCgProcurementOrder;
	}
	@Override
	public void endApply(JsonNode jsonNode) {
    	String approvalId = jsonNode.get("instId").asText();
		String actionName = jsonNode.get("actionName").asText();
		ScmCgProcurementOrder scmCgProcurementOrder = scmCgProcurementOrderDao.getOrderFirstByApprovalId(approvalId);
		if(scmCgProcurementOrder == null) {
			throw new RuntimeException("未查询到业务数据,处理异常");
		}
		String endEvent = jsonNode.get("eventType").asText();
		if ("agree".equals(actionName) && "endEvent".equals(endEvent)) {
			scmCgProcurementOrder.setApprovalState("2");
			scmCgProcurementOrder.setOrderDate(new Date());
			//推送至WMS
			List<ScmCgProOrderProduct> byMainId = scmCgProOrderProductDao.getByMainId(scmCgProcurementOrder.getId());
			List<ScmZsjOrderProductToWms> list = new ArrayList<ScmZsjOrderProductToWms>();
			for (ScmCgProOrderProduct scmCgProOrderProduct : byMainId) {
				ScmZsjOrderProductToWms scmZsjOrderProductToWms = new ScmZsjOrderProductToWms();
				scmZsjOrderProductToWms.setProductName(scmCgProOrderProduct.getProductName());
				scmZsjOrderProductToWms.setProductCode(scmCgProOrderProduct.getCode());
				scmZsjOrderProductToWms.setProductCname(scmCgProOrderProduct.getProductCname());
				scmZsjOrderProductToWms.setProductGauge(scmCgProOrderProduct.getProductGauge());
				scmZsjOrderProductToWms.setMedicineType(scmCgProOrderProduct.getMedicineType());
				scmZsjOrderProductToWms.setUnit(scmCgProOrderProduct.getUnit());
				scmZsjOrderProductToWms.setUnitPrice(scmCgProOrderProduct.getUnitPrice());
				scmZsjOrderProductToWms.setSumPrice(scmCgProOrderProduct.getSumPrice());
				scmZsjOrderProductToWms.setCurrency(scmCgProOrderProduct.getCurrency());
				scmZsjOrderProductToWms.setaChange(scmCgProOrderProduct.getAchage());
				scmZsjOrderProductToWms.setProductSum(scmCgProOrderProduct.getProductSum());
				scmZsjOrderProductToWms.setProcurementCode(scmCgProOrderProduct.getProcurementCode());
				list.add(scmZsjOrderProductToWms);
			}
			//拼装数据
			String scmZsjSupplier = scmMasterDateFeignService.getScmZsjSupplier(scmCgProcurementOrder.getSupplierId());
			JSONObject parseObject = JSON.parseObject(scmZsjSupplier);
			String string = parseObject.getString("supplierCode");
			ScmZsjOrderToWms scmZsjOrderToWms = new ScmZsjOrderToWms();
			scmZsjOrderToWms.setProcurementCode(scmCgProcurementOrder.getProcurementCode());
			scmZsjOrderToWms.setSupplierCode(string);
			scmZsjOrderToWms.setSupplier(scmCgProcurementOrder.getSupplier());
			scmZsjOrderToWms.setContractCode(scmCgProcurementOrder.getContractCode());
			scmZsjOrderToWms.setSpeciesSum(scmCgProcurementOrder.getSpeciesSum());
			scmZsjOrderToWms.setProductSum(scmCgProcurementOrder.getProductSum());
			scmZsjOrderToWms.setSumPrice(scmCgProcurementOrder.getSumPrice());
			scmZsjOrderToWms.setCurrency(scmCgProcurementOrder.getCurrency());
			scmZsjOrderToWms.setOrderDate(scmCgProcurementOrder.getOrderDate());
			scmZsjOrderToWms.setType("Po_Norm");
			scmZsjOrderToWms.setGetTicket(scmCgProcurementOrder.getGetTicket());
			scmZsjOrderToWms.setOwnerId(scmCgProcurementOrder.getOwnerId());
			scmZsjOrderToWms.setGetMoneyWarehouse(scmCgProcurementOrder.getWarehouseCode());
			scmZsjOrderToWms.setOwnerId(scmCgProcurementOrder.getOwnerId());
			scmZsjOrderToWms.setOrderProduct(list);
			String jsonString = JSON.toJSONString(scmZsjOrderToWms);
			boolean puth = ScmPuthWmsUtil.puth(jsonString, "/prolog_aomei_interface/interface/purchase/order/scmPurchaseOrderNotify.json","采购订单推送wms");
			if(puth) {
				scmCgProcurementOrder.setIsToWMS('1');
			}else{
				scmCgProcurementOrder.setIsToWMS('0');
			}
			scmCgProcurementOrder.setIsManageSap('0');
			scmCgProcurementOrder.setIsAffirmWms("0");
			scmCgProcurementOrderDao.update(scmCgProcurementOrder);
		} else if ("agree".equals(actionName)) {
		} else if ("reject".equals(actionName)) {
		} else if ("backToStart".equals(actionName)) {
		} else if ("opposeTrans".equals(actionName)) {
		} else if ("endProcess".equals(actionName)) {
			scmCgProcurementOrder.setApprovalState("3");
			scmCgProcurementOrderDao.update(scmCgProcurementOrder);
		}
	}
	
	@Override
	public PageList<ScmZsjDrogPuchaseRecords> drogPuchaseRecords(QueryFilter queryFilter) {
		PageBean pageBean = queryFilter.getPageBean();
    	if(BeanUtils.isEmpty(pageBean)){
    		PageHelper.startPage(1, Integer.MAX_VALUE, false);
    	}
    	else{
    		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
    	}
    	List<ScmZsjDrogPuchaseRecords> query = scmCgProcurementOrderDao.drogPuchaseRecords(queryFilter.getParams());
        return new PageList<ScmZsjDrogPuchaseRecords>(query);
	}
	@Override
	public WmsPageList<WmsfindcgysVo> wmsfindcgys(WmsfindcgysInVo queryFilter) {
		/*TODO whw 1.4采购验收查询开始*/
		try {
			Integer page = (queryFilter.getPage()!=null ? queryFilter.getPage() : 1);
			Integer pagesize = (queryFilter.getPagesize()!=null ? queryFilter.getPagesize() : 200);
			queryFilter.setPage(page);
			queryFilter.setPagesize(pagesize);
			ScmWmsReturnData scmWmsReturnData = ScmPuthWmsUtil.getDatPputh(JSONObject.toJSONString(queryFilter), "/prolog_aomei_interface/interface/check/purchase/scmPurchaseCheckQuery.json");
			if(scmWmsReturnData == null) {
				logger.error("WMS接口异常[1.4采购验收查询]");
				throw new RuntimeException("WMS接口异常");
			}
			Integer total = scmWmsReturnData.getTotal()!=null ? scmWmsReturnData.getTotal() : scmWmsReturnData.getData().size();
			List<WmsfindcgysVo> listRows = new ArrayList<WmsfindcgysVo>();
			for(String text : scmWmsReturnData.getData()) {
				listRows.add(JSON.parseObject(text, WmsfindcgysVo.class));
			}
			
			WmsPageList<WmsfindcgysVo> wmsPageList = new WmsPageList<WmsfindcgysVo>();
			wmsPageList.setRows(listRows);
			wmsPageList.setMap(new HashMap(10));
			wmsPageList.setPage(page);
			wmsPageList.setPageSize(pagesize);
			wmsPageList.setTotal(total);
			return wmsPageList;
		}catch(Exception e) {
			logger.error("WMS接口异常[1.4采购验收查询]");
			throw new RuntimeException("WMS接口异常");
		}
		/*1.4采购验收查询结束*/
	}
	
	@Override
	public List<WmsfindcgysbybillnoVo> wmsfindcgysbybillno(String billno) {
		/*TODO whw 1.5采购验收单详情开始*/
		try {
			Map mapQuerry = new HashMap(10);
			mapQuerry.put("billno", billno);
			ScmWmsReturnData scmWmsReturnData = ScmPuthWmsUtil.getDatPputh(JSONObject.toJSONString(mapQuerry), "/prolog_aomei_interface/interface/checkDetails/purchase/scmPurchaseCheckDetailsQuery.json");
			if(scmWmsReturnData == null) {
				logger.error("WMS接口异常[1.5采购验收单详情]");
				throw new RuntimeException("WMS接口异常");
			}
			List<WmsfindcgysbybillnoVo> listRows = new ArrayList<WmsfindcgysbybillnoVo>();
			for(String text : scmWmsReturnData.getData()) {
				listRows.add(JSON.parseObject(text, WmsfindcgysbybillnoVo.class));
			}
			return listRows;
		}catch(Exception e) {
			logger.error("WMS接口异常[1.5采购验收单详情]");
			throw new RuntimeException("WMS接口异常");
		}
		/*1.5采购验收单详情结束*/
	}
	
	@Override
	public WmsPageList<WmsfindcgrkVo> wmsfindcgrk(WmsfindcgrkInVo queryFilter) {
		/*TODO whw 1.6,采购入库单查询开始*/
		try {
			Integer page = (queryFilter.getPage()!=null ? queryFilter.getPage() : 1);
			Integer pagesize = (queryFilter.getPagesize()!=null ? queryFilter.getPagesize() : 200);
			queryFilter.setPage(page);
			queryFilter.setPagesize(pagesize);
			ScmWmsReturnData scmWmsReturnData = ScmPuthWmsUtil.getDatPputh(JSONObject.toJSONString(queryFilter), "/prolog_aomei_interface/interface/order/purchase/scmPurchaseOrderQuery.json");
			if(scmWmsReturnData == null) {
				logger.error("WMS接口异常[1.4采购验收查询]");
				throw new RuntimeException("WMS接口异常");
			}
			Integer total = scmWmsReturnData.getTotal()!=null ? scmWmsReturnData.getTotal() : scmWmsReturnData.getData().size();
			List<WmsfindcgrkVo> listRows = new ArrayList<WmsfindcgrkVo>();
			for(String text : scmWmsReturnData.getData()) {
				listRows.add(JSON.parseObject(text, WmsfindcgrkVo.class));
			}
			
			WmsPageList<WmsfindcgrkVo> wmsPageList = new WmsPageList<WmsfindcgrkVo>();
			wmsPageList.setRows(listRows);
			wmsPageList.setMap(new HashMap(10));
			wmsPageList.setPage(page);
			wmsPageList.setPageSize(pagesize);
			wmsPageList.setTotal(total);
			return wmsPageList;
		}catch(Exception e) {
			logger.error("WMS接口异常[1.6,采购入库单查询]");
			throw new RuntimeException("WMS接口异常");
		}
		/*1.6,采购入库单查询结束*/
	}
	
	@Override
	public List<WmsfindcgrkDetailVo> wmsfindcgrkbybillno(String billno) {
		/*TODO whw 1.7采购入库单详情开始*/
		try {
			Map mapQuerry = new HashMap(10);
			mapQuerry.put("billno", billno);
			ScmWmsReturnData scmWmsReturnData = ScmPuthWmsUtil.getDatPputh(JSONObject.toJSONString(mapQuerry), "/prolog_aomei_interface/interface/orderDetails/purchase/scmPurchaseOrderDetailsQuery.json");
			if(scmWmsReturnData == null) {
				logger.error("WMS接口异常[1.7采购入库单详情]");
				throw new RuntimeException("WMS接口异常");
			}
			List<WmsfindcgrkDetailVo> listRows = new ArrayList<WmsfindcgrkDetailVo>();
			for(String text : scmWmsReturnData.getData()) {
				listRows.add(JSON.parseObject(text, WmsfindcgrkDetailVo.class));
			}
			return listRows;
		}catch(Exception e) {
			logger.error("WMS接口异常[1.7采购入库单详情]");
			throw new RuntimeException("WMS接口异常");
		}
		/*1.7采购入库单详情结束*/
	}

	public List<WmsfindcgrkDetailVo> wmsfindcgrkbybillno(String billno,String date) {
		/*TODO whw 1.7采购入库单详情开始*/
		try {
			Map mapQuerry = new HashMap(10);
			mapQuerry.put("billno", billno);
			ScmWmsReturnData scmWmsReturnData = ScmPuthWmsUtil.getDatPputh(JSONObject.toJSONString(mapQuerry), "/prolog_aomei_interface/interface/orderDetails/purchase/scmPurchaseOrderDetailsQuery.json");
			if(scmWmsReturnData == null) {
				logger.error("WMS接口异常[1.7采购入库单详情]");
				throw new RuntimeException("WMS接口异常");
			}
			List<WmsfindcgrkDetailVo> listRows = new ArrayList<WmsfindcgrkDetailVo>();
			for(String text : scmWmsReturnData.getData()) {
				WmsfindcgrkDetailVo parseObject = JSON.parseObject(text, WmsfindcgrkDetailVo.class);
				parseObject.setArrivedate(date);
				listRows.add(parseObject);
			}
			return listRows;
		}catch(Exception e) {
			logger.error("WMS接口异常[1.7采购入库单详情]");
			throw new RuntimeException("WMS接口异常");
		}
		/*1.7采购入库单详情结束*/
	}
	@Override
	public WmsPageList<WmsfindcgrkDetailVo> sapfindcgrkbyordercode(WmsfindcgrkInVo queryFilter) {
		/*TODO whw SAP查询WMS入库单信息*/
		if(queryFilter.getOrderCode() == null || queryFilter.getOrderCode().length() == 0) {
			throw new RuntimeException("orderCode【采购单号】参数异常");
		}
		ScmCgProcurementOrder scmCgProcurementOrder = scmCgProcurementOrderDao.getByOrdercode(queryFilter.getOrderCode());
		String orderCode = "";
		String ownerId = "";
		if(scmCgProcurementOrder == null) {
			ScmCgProReplacementOrder proOrderFirstByCode = scmCgProReplacementOrderDao.getProOrderFirstByCode(queryFilter.getOrderCode());
			if(proOrderFirstByCode == null) {
				throw new RuntimeException("采购单号["+ queryFilter.getOrderCode() +"在系统中未匹配到数据]");
			}else{
				orderCode = proOrderFirstByCode.getReplacementOrderCode();
				ownerId = proOrderFirstByCode.getOwnerId();
			}
		}else{
			orderCode =scmCgProcurementOrder.getProcurementCode();
			ownerId = scmCgProcurementOrder.getOwnerId();
		}
		if("".equals(ownerId) || "".equals(orderCode)) {
			throw new RuntimeException("采购单号["+ queryFilter.getOrderCode() +"在系统中未匹配到数据]");
		}
		Integer page = (queryFilter.getPage()!=null ? queryFilter.getPage() : 1);
		Integer pagesize = (queryFilter.getPagesize()!=null ? queryFilter.getPagesize() : Integer.MAX_VALUE);
		queryFilter.setPage(page);
		queryFilter.setPagesize(pagesize);
		queryFilter.setOrderCode(orderCode);
		queryFilter.setConsignor(ownerId);
		ScmWmsReturnData scmWmsReturnData = ScmPuthWmsUtil.getDatPputh(JSONObject.toJSONString(queryFilter), "/prolog_aomei_interface/interface/order/purchase/scmPurchaseOrderQuery.json");
		if(scmWmsReturnData == null) {
			logger.error("WMS接口异常[1.4采购验收查询]");
			throw new RuntimeException("WMS接口异常");
		}
		Integer total = scmWmsReturnData.getTotal()!=null ? scmWmsReturnData.getTotal() : scmWmsReturnData.getData().size();
		List<WmsfindcgrkVo> listRows = new ArrayList<WmsfindcgrkVo>();
		List<WmsfindcgrkDetailVo> productList = new ArrayList<>();
		for(String text : scmWmsReturnData.getData()) {
			WmsfindcgrkVo wmsfindcgrkVo = JSON.parseObject(text, WmsfindcgrkVo.class);
			productList.addAll(wmsfindcgrkbybillno(wmsfindcgrkVo.getBillno(),wmsfindcgrkVo.getArrivedate()));
			wmsfindcgrkVo.setProductList(productList);
		}
		
		for (WmsfindcgrkDetailVo wmsfindcgrkDetailVo : productList) {
			String itemcode = wmsfindcgrkDetailVo.getItemcode();
			CommonResult<String> productNumByCode = scmMasterDateFeignService.getProductNumByCode(itemcode);
			wmsfindcgrkDetailVo.setItemcode(productNumByCode.getShortMessage());
		}
//		listRows.add(wmsfindcgrkVo);
		WmsPageList<WmsfindcgrkDetailVo> wmsPageList = new WmsPageList<WmsfindcgrkDetailVo>();
		wmsPageList.setRows(productList);
		wmsPageList.setMap(new HashMap(10));
		wmsPageList.setPage(page);
		wmsPageList.setPageSize(pagesize);
		wmsPageList.setTotal(total);
		return wmsPageList;
	}
	@Override
	public void receipt(ScmWmsReceipt scmWmsReceipt) {
		ScmCgProcurementOrder byOrdercode = scmCgProcurementOrderDao.getByOrdercode(scmWmsReceipt.getOrderCode());
		if(byOrdercode == null) {
			throw new RuntimeException("请传入正确单号");
		}
		if("1".equals(scmWmsReceipt.getTypt())) {
			byOrdercode.setIsAffirmWms("2");
		}else{
			throw new RuntimeException("请传入正确的状态");
		}
		
		scmCgProcurementOrderDao.update(byOrdercode);
	}
	
	
}
