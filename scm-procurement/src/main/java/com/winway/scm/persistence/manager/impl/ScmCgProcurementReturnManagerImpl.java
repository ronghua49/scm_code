package com.winway.scm.persistence.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
import com.winway.purchase.vo.ReturnWmsVo;
import com.winway.scm.model.ScmCgProcurementReturn;
import com.winway.scm.model.ScmCgReturnProduct;
import com.winway.scm.persistence.dao.ScmCgProcurementReturnDao;
import com.winway.scm.persistence.dao.ScmCgReturnProductDao;
import com.winway.scm.persistence.manager.ScmCgProcurementReturnManager;
import com.winway.scm.persistence.manager.ScmCgReturnProductManager;
import com.winway.scm.vo.ScmZsjDrogPuchaseReturn;
import com.winway.scm.vo.WmsCgthReceiptVo;

/**
 * <pre>
 * 描述：采购退货表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-26 13:47:44
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
@Service("scmCgProcurementReturnManager")
public class ScmCgProcurementReturnManagerImpl extends AbstractManagerImpl<String, ScmCgProcurementReturn> implements ScmCgProcurementReturnManager {
    private Logger logger = LoggerFactory.getLogger(ScmCgProcurementReturnManagerImpl.class);
	@Resource
    ScmCgProcurementReturnDao scmCgProcurementReturnDao;
    @Resource
    ScmCgReturnProductDao scmCgReturnProductDao;

    @Resource
    ScmCgReturnProductManager scmCgReturnProductManager;

    @Override
    protected MyBatisDao<String, ScmCgProcurementReturn> getDao() {
        return scmCgProcurementReturnDao;
    }

    /**
     * 创建实体包含子表实体
     */
    public void create(ScmCgProcurementReturn scmCgProcurementReturn) {
        List<ScmCgReturnProduct> scmCgReturnProductList = scmCgProcurementReturn.getScmCgReturnProductList();
        scmCgProcurementReturn.setReturnCode(QuarterUtil.getCode("CGTH"));
        scmCgProcurementReturn.setReturnDate(new Date());
        calculate(scmCgProcurementReturn);
        super.create(scmCgProcurementReturn);
        String pk = scmCgProcurementReturn.getId();
        createSon(pk, scmCgReturnProductList);
    }

    private void calculate(ScmCgProcurementReturn scmCgProcurementReturn) {
        List<ScmCgReturnProduct> scmCgReturnProductList = scmCgProcurementReturn.getScmCgReturnProductList();
        Double sumPrice = 0.0;
        Integer returnTotal = 0;
        for (ScmCgReturnProduct product : scmCgReturnProductList) {
            sumPrice+=product.getSumPrice();
            returnTotal+=product.getReturnNumber();
        }
        scmCgProcurementReturn.setSpeciesSum(scmCgReturnProductList.size());
        scmCgProcurementReturn.setSumPrice(sumPrice);
        scmCgProcurementReturn.setReturnTotal(returnTotal);
    }

    /**
     * 删除记录包含子表记录
     */
    public void remove(String entityId) {
        super.remove(entityId);
        scmCgReturnProductDao.delByMainId(entityId);
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
    public ScmCgProcurementReturn get(String entityId) {
        ScmCgProcurementReturn scmCgProcurementReturn = super.get(entityId);
        List<ScmCgReturnProduct> scmCgReturnProductList = scmCgReturnProductDao.getByMainId(entityId);
        if(scmCgProcurementReturn!=null){
            scmCgProcurementReturn.setScmCgReturnProductList(scmCgReturnProductList);
        }
        return scmCgProcurementReturn;
    }

    /**
     * 更新实体同时更新子表记录
     */
    public void update(ScmCgProcurementReturn scmCgProcurementReturn) {
        super.update(scmCgProcurementReturn);
        String id = scmCgProcurementReturn.getId();
        scmCgReturnProductDao.delByMainId(id);
        calculate(scmCgProcurementReturn);
        List<ScmCgReturnProduct> scmCgReturnProductList = scmCgProcurementReturn.getScmCgReturnProductList();
        createSon(id, scmCgReturnProductList);
    }

    @Override
    public void endApply(JsonNode jsonNode) {
        String approvalId = jsonNode.get("instId").asText();
        String actionName = jsonNode.get("actionName").asText();
        //根据审批id查询数据
        ScmCgProcurementReturn byApprovalState = scmCgProcurementReturnDao.getByApprovalState(approvalId);
        if (byApprovalState == null) {
            return;
        }
        byApprovalState = get(byApprovalState.getId());
        String endEvent = jsonNode.get("eventType").asText();
        if ("agree".equals(actionName) && "endEvent".equals(endEvent)) {
        	byApprovalState.setApprovalState('2');
            /*TODO whw 1.9 SCM推送采购退货开始*/
            try {
            	Map map = new HashMap(10);
            	Map mapmain = scmCgProcurementReturnDao.getById(byApprovalState.getId());
            	if(mapmain == null) {
                 	throw new RuntimeException("采购退货单信息不存在");
                }
            	map.putAll(mapmain);
            	map.put("type", "Do_Return");
            	
            	List<Map> listT = new ArrayList<Map>();
            	List<ScmCgReturnProduct> scmCgReturnProductList = scmCgReturnProductDao.getByMainId(byApprovalState.getId());
            	for(ScmCgReturnProduct scmCgReturnProduct : scmCgReturnProductList) {
            		Map mapT = new HashMap(10);
            		mapT.put("batchNum", scmCgReturnProduct.getBatchNum());
            		mapT.put("productName", scmCgReturnProduct.getProductName());
            		mapT.put("medicineType", scmCgReturnProduct.getMedicineType());
            		mapT.put("productState", scmCgReturnProduct.getProductState());
            		mapT.put("unit", scmCgReturnProduct.getUnit());
            		mapT.put("manufacturer", scmCgReturnProduct.getManufacturer());
            		mapT.put("producedDate", scmCgReturnProduct.getProducedDate());
            		mapT.put("validity", scmCgReturnProduct.getValidity());
            		mapT.put("getMoneyWarehouse", mapmain.get("getMoneyWarehouse"));
            		mapT.put("inboundDate", scmCgReturnProduct.getInboundDate());
            		mapT.put("currInventoryNum", scmCgReturnProduct.getCurrInventoryNum());//库存数量
            		mapT.put("returnNumber", scmCgReturnProduct.getReturnNumber());
            		mapT.put("unitPrice", scmCgReturnProduct.getUnitPrice());
            		mapT.put("sumPrice", scmCgReturnProduct.getSumPrice());
            		mapT.put("returnRequestCause", scmCgReturnProduct.getReturnRequestCause());
            		mapT.put("memo", scmCgReturnProduct.getMemo());
            		mapT.put("productCode", scmCgReturnProduct.getCode());
            		
            		listT.add(mapT);
            	}
            	map.put("prooductList", listT);
            	
                boolean puth = ScmPuthWmsUtil.puth(JSONObject.toJSONString(map), "/prolog_aomei_interface/interface/return/purchase/scmPurchaseReturnNotify.json","推送采购退货到wms");
                if(puth) {
                	byApprovalState.setIsSMS("1");
                	byApprovalState.setIsAffirmWms("0");
        		}else{
        			byApprovalState.setIsSMS("0");
        			byApprovalState.setIsAffirmWms("0");
        		}
            } catch (Exception e) {
                logger.error("WMS接口异常[1.9 SCM推送采购退货异常]", e);
            }
            scmCgProcurementReturnDao.update(byApprovalState);
            /*1.9 SCM推送采购退货结束*/
        } else if ("agree".equals(actionName)) {
        } else if ("reject".equals(actionName)) {
        } else if ("backToStart".equals(actionName)) {
        } else if ("opposeTrans".equals(actionName)) {
        } else if ("endProcess".equals(actionName)) {
        	 byApprovalState.setApprovalState('3');
        	 scmCgProcurementReturnDao.update(byApprovalState);
        }
    }

    private void createSon(String pk, List<ScmCgReturnProduct> scmCgReturnProductList) {
        for (ScmCgReturnProduct scmCgReturnProduct : scmCgReturnProductList) {
            if (scmCgReturnProduct.getCurrInventoryNum() == null || scmCgReturnProduct.getReturnNumber() == null) {
                throw new RuntimeException("参数错误，退货数量或者库存量不得为空");
            }
            if (scmCgReturnProduct.getReturnNumber() > scmCgReturnProduct.getCurrInventoryNum()) {
                throw new RuntimeException("退货数量不得大于库存量");
            }
            scmCgReturnProduct.setProReturnId(pk);
            scmCgReturnProduct.setMasterId(pk);
            scmCgReturnProductManager.create(scmCgReturnProduct);
        }
    }

    @Override
    public PageList<ScmZsjDrogPuchaseReturn> drugPuchaseReturn(QueryFilter queryFilter) {
        PageBean pageBean = queryFilter.getPageBean();
        if (BeanUtils.isEmpty(pageBean)) {
            PageHelper.startPage(1, Integer.MAX_VALUE, false);
        } else {
            PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
        }
        List<ScmZsjDrogPuchaseReturn> query = scmCgProcurementReturnDao.drugPuchaseReturn(queryFilter.getParams());
        return new PageList<ScmZsjDrogPuchaseReturn>(query);
    }

    @Override
    public ScmCgProcurementReturn getDetail(String approvalId) {
        ScmCgProcurementReturn byApprovalState = scmCgProcurementReturnDao.getByApprovalState(approvalId);
        if(byApprovalState==null){
            throw  new RuntimeException("未查询到采购退货退回审批数据");
        }
       byApprovalState = get(byApprovalState.getId());
        return byApprovalState;
    }

	@Override
	public ReturnWmsVo receipt(WmsCgthReceiptVo smsCgthReceiptVo) {
		/*TODO whw 1.9 SCM采购退货回执开始*/
		ReturnWmsVo returnWmsVo = new ReturnWmsVo();
		if(!"1".equals(smsCgthReceiptVo.getTypt())) {
			return null;
		}
		if(smsCgthReceiptVo.getOrderCode() == null || smsCgthReceiptVo.getOrderCode().length() == 0) {
			returnWmsVo.setStute("500");
			returnWmsVo.setMsg("orderCode参数异常");
			return returnWmsVo;
		}
		try {
			ScmCgProcurementReturn scmCgProcurementReturn = scmCgProcurementReturnDao.getByCode(smsCgthReceiptVo.getOrderCode());
			if(scmCgProcurementReturn == null) {
				returnWmsVo.setStute("500");
				returnWmsVo.setMsg("采购补单不存在【补单号："+ smsCgthReceiptVo.getOrderCode() +"】");
				return returnWmsVo;
			}
			if(!"1".equals(scmCgProcurementReturn.getIsAffirmWms())) {
				scmCgProcurementReturn.setIsAffirmWms("1");
				scmCgProcurementReturnDao.update(scmCgProcurementReturn);
			}
			returnWmsVo.setStute("200");
			returnWmsVo.setMsg("处理成功");
		}catch(Exception e) {
			returnWmsVo.setStute("500");
			returnWmsVo.setMsg("处理失败");
		}
		return returnWmsVo;
		/*采购退货回执结束*/
	}
    
    
}
