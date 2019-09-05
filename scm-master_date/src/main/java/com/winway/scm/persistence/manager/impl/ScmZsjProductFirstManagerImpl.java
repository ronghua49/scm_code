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
import com.winway.purchase.util.QuarterUtil;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.purchase.feign.ScmMasterDateFeignService;
import com.winway.purchase.tool.ScmWmsConfiguration;
import com.winway.purchase.util.ContrasUtil;
import com.winway.purchase.util.HttpClientUtil;
import com.winway.purchase.vo.ScmWmsReturnData;
import com.winway.scm.model.ScmZsjAccessory;
import com.winway.scm.model.ScmZsjAchage;
import com.winway.scm.model.ScmZsjProduct;
import com.winway.scm.model.ScmZsjProductFirst;
import com.winway.scm.persistence.dao.ScmZsjAccessoryDao;
import com.winway.scm.persistence.dao.ScmZsjAchageDao;
import com.winway.scm.persistence.dao.ScmZsjCommerceAccreditDao;
import com.winway.scm.persistence.dao.ScmZsjProductDao;
import com.winway.scm.persistence.dao.ScmZsjProductFirstDao;
import com.winway.scm.persistence.manager.ScmZsjProductFirstManager;
import com.winway.scm.persistence.manager.ScmZsjProductManager;
import com.winway.scm.persistence.vo.ScmProductToWms;
import org.springframework.transaction.annotation.Transactional;

/**
 * <pre>
 * 描述：商品首营记录表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmZsjProductFirstManager")
public class ScmZsjProductFirstManagerImpl extends AbstractManagerImpl<String, ScmZsjProductFirst> implements ScmZsjProductFirstManager {
    @Resource
    ScmZsjProductFirstDao scmZsjProductFirstDao;
    @Resource
    ScmZsjProductDao scmZsjProductDao;
    @Resource
    ScmZsjAchageDao scmZsjAchageDao;
    @Resource
    ScmZsjAccessoryDao scmZsjAccessoryDao;
    @Resource
    ScmZsjAccessoryManagerImpl scmZsjAccessoryManagerImpl;
    @Resource
    ScmZsjProductManager scmZsjProductManager;
    @Resource
    ScmMasterDateFeignService scmMasterDateFeignService;
    @Resource
    BpmRuntimeFeignService bpmRuntimeFeignService;

    @Override
    protected MyBatisDao<String, ScmZsjProductFirst> getDao() {
        return scmZsjProductFirstDao;
    }

    @Override
    public void sendApply(ScmZsjProductFirst scmZsjProductFirst, String flowKey) {
        //判断商品是否存在
    	//scmZsjProductManager.isExist(scmZsjProductFirst.getScmZsjProduct());
        //保存首营审批信息
        scmZsjProductFirst.setProductId(scmZsjProductFirst.getScmZsjProduct().getId());
        //判断当前商品是否已经首营
        List<ScmZsjProductFirst> isSave = scmZsjProductFirstDao.isSave(scmZsjProductFirst);
        if (isSave.size() != 0) {
            throw new RuntimeException("当前商品已经发起审批或审批已经通过,请勿重复提交");
        }
        scmZsjProductFirst.setOperatorDate(new Date());
        if(scmZsjProductFirst.getApprovalValidity()!=null && scmZsjProductFirst.getApprovalValidity().getTime()<=new Date().getTime()) {
            throw new RuntimeException("批准文号/注册证号有效期不可小于今天日期");
        }
        if(scmZsjProductFirst.getGmpValidity()!=null && scmZsjProductFirst.getGmpValidity().getTime()<=new Date().getTime()) {
            throw new RuntimeException("GMP证书有效期不可小于今天日期");
        }
        StartFlowParam startFlowParam = new StartFlowParam(flowKey, "SCM", "approvalId");
        startFlowParam.setFormType("frame");
        CustomStartResult customStartResult = null;
        try {
            System.out.println("发起商品首营申请");
            customStartResult = bpmRuntimeFeignService.customStart(startFlowParam);
            String approvalId = customStartResult.getInstId();
            scmZsjProductFirst.setApprovalId(approvalId);
            scmZsjProductFirst.setApprovalState("1");
            super.create(scmZsjProductFirst);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("发起流程失败");
        }

    }
    
    @Override
    public void create(ScmZsjProductFirst product) {
    	 if(product.getApprovalValidity()!=null && product.getApprovalValidity().getTime()<=new Date().getTime()) {
     		throw new RuntimeException("批准文号/注册证号有效期不可小于今天日期");
     	}
     	if(product.getGmpValidity()!=null && product.getGmpValidity().getTime()<=new Date().getTime()) {
     		throw new RuntimeException("GMP证书有效期不可小于今天日期");
     	}
     	product.setFileCode(QuarterUtil.getCode("DABM"));
    	super.create(product);
        //附件
        List<ScmZsjAccessory> scmZsjAccessoryList = product.getScmZsjAccessoryList();
        for (ScmZsjAccessory scmZsjAccessory : scmZsjAccessoryList) {
        	scmZsjAccessory.setType("2");
        	scmZsjAccessory.setUpdateDate(new Date());
        	scmZsjAccessory.setDateId(product.getId());
        	scmZsjAccessoryManagerImpl.create(scmZsjAccessory);
		}
    }
    
    @Override
    public void isSave(ScmZsjProductFirst scmZsjProductFirst) {
        List<ScmZsjProductFirst> scmZsjProductFirsts = scmZsjProductFirstDao.isSave(scmZsjProductFirst);
        if (scmZsjProductFirsts.size() > 0) {
            throw new RuntimeException("当前商品已经发起审批或审批已经通过,请勿重复提交");
        }
    }

    @Override
    public void endApply(JsonNode jsonNode) {
        String approvalId = jsonNode.get("instId").asText();
        String actionName = jsonNode.get("actionName").asText();
        ScmZsjProductFirst productFirstByApprovalId = scmZsjProductFirstDao.getProductFirstByApprovalId(approvalId);
        if (productFirstByApprovalId == null) {
            throw new RuntimeException("未查询到业务数据,处理异常");
        }
        String endEvent = jsonNode.get("eventType").asText();
        if ("agree".equals(actionName) && "endEvent".equals(endEvent)) {
        	productFirstByApprovalId.setApprovalState("2");
            productFirstByApprovalId.setIsPassed("1");
        	 //推送至wms
            try{
            	List<ScmZsjProductFirst> list = new ArrayList<>();
            	list.add(productFirstByApprovalId);
            	productToWms(list);
            }catch (Exception e) {
            	productFirstByApprovalId.setIsToWms("0");
    		}
            scmZsjProductFirstDao.update(productFirstByApprovalId);
        } else if ("agree".equals(actionName)) {
        } else if ("reject".equals(actionName)) {
        } else if ("backToStart".equals(actionName)) {
        } else if ("opposeTrans".equals(actionName)) {
        } else if ("endProcess".equals(actionName)) {
        	productFirstByApprovalId.setApprovalState("3");
        	scmZsjProductFirstDao.update(productFirstByApprovalId);
        }
    }

    @Override
    public ScmZsjProductFirst getProductFirstByApprovalId(String approvalId) {
        ScmZsjProductFirst scmZsjProductFirst = scmZsjProductFirstDao.getProductFirstByApprovalId(approvalId);
        if (scmZsjProductFirst == null) {
            throw new RuntimeException("查询数据不存在或已经被删除");
        }
        ScmZsjProductFirst scmZsjProductFirst2 = get(scmZsjProductFirst.getId());
        return scmZsjProductFirst2;
    }

    @Override
    public ScmZsjProductFirst get(String entityId) {
        ScmZsjProductFirst scmZsjProductFirst = super.get(entityId);
        scmZsjProductFirst.setScmZsjProduct(scmZsjProductDao.get(scmZsjProductFirst.getProductId()));
        List<ScmZsjAccessory> byTypeAndDataId = scmZsjAccessoryDao.getByTypeAndDataId("2", entityId);
        scmZsjProductFirst.setScmZsjAccessoryList(byTypeAndDataId);
        return scmZsjProductFirst;
    }

    @Transactional
    @Override
    public void updateSendApply(ScmZsjProductFirst scmZsjProductFirst, String flowKey) {
        ScmZsjProductFirst scmZsjProductFirst2 = scmZsjProductFirstDao.get(scmZsjProductFirst.getId());
        if ("1".equals(scmZsjProductFirst2.getApprovalState())) {
            //数据在审批中,不可修改编辑
            throw new RuntimeException("当前数据已经在审批中,不可重复提交");
        } else {
//            if ("2".equals(scmZsjProductFirst2.getApprovalState())) {
//                throw new RuntimeException("改商品已经首营通过不可重复首营");
//            }
            //修改其他关联性数据
        	 if(scmZsjProductFirst.getApprovalValidity()!=null && scmZsjProductFirst.getApprovalValidity().getTime()<=new Date().getTime()) {
         		throw new RuntimeException("批准文号/注册证号有效期不可小于今天日期");
         	}
         	if(scmZsjProductFirst.getGmpValidity()!=null && scmZsjProductFirst.getGmpValidity().getTime()<=new Date().getTime()) {
         		throw new RuntimeException("GMP证书有效期不可小于今天日期");
         	}
            update(scmZsjProductFirst);
            StartFlowParam startFlowParam = new StartFlowParam(flowKey, "SCM", "approvalId");
            startFlowParam.setFormType("frame");
            CustomStartResult customStartResult = null;
            try {
                System.out.println("发起商品首营修改申请");
                customStartResult = bpmRuntimeFeignService.customStart(startFlowParam);
                String approvalId = customStartResult.getInstId();
                scmZsjProductFirst.setApprovalId(approvalId);
                scmZsjProductFirst.setApprovalState("1");
                scmZsjProductFirstDao.update(scmZsjProductFirst);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("发起流程失败");
            }
        }
    }

    @Override
    public void update(ScmZsjProductFirst product) {
    	 if(product.getApprovalValidity()!=null && product.getApprovalValidity().getTime()<=new Date().getTime()) {
      		throw new RuntimeException("批准文号/注册证号有效期不可小于今天日期");
      	}
      	if(product.getGmpValidity()!=null && product.getGmpValidity().getTime()<=new Date().getTime()) {
      		throw new RuntimeException("GMP证书有效期不可小于今天日期");
      	}
        String id = product.getId();
        List<ScmZsjProductFirst> list = scmZsjProductFirstDao.getByMainId(id);
        try {
            if (list.size() > 0) {
                String contrast = ContrasUtil.Contrast(list.get(0), product, id, "spxg");
                if (!StringUtils.isEmpty(contrast)) {
                    scmMasterDateFeignService.inserScmLogRecord(contrast);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        product.setOperatorDate(list.get(0).getOperatorDate());
        super.update(product);
        //附件
        List<ScmZsjAccessory> scmZsjAccessoryList = product.getScmZsjAccessoryList();
        scmZsjAccessoryDao.delByMainId(product.getId());
        for (ScmZsjAccessory scmZsjAccessory : scmZsjAccessoryList) {
        	scmZsjAccessory.setType("2");
        	scmZsjAccessory.setUpdateDate(new Date());
        	scmZsjAccessory.setDateId(product.getId());
        	scmZsjAccessoryManagerImpl.create(scmZsjAccessory);
		}
        
    }

    @Override
    public String startOrStop(String id) {
        ScmZsjProductFirst scmZsjProductFirst = scmZsjProductFirstDao.get(id);
        if (scmZsjProductFirst == null) {
            throw new RuntimeException("首营数据获取异常");
        }
        scmZsjProductFirst.setApprovalState("0");
        scmZsjProductFirst.setIsPassed("0");
        scmZsjProductFirstDao.update(scmZsjProductFirst);
        return "0";
    }

	@Override
	public void productToWms(List<ScmZsjProductFirst> firstList) {
		if(firstList.size() == 0) {
			return;
		}
        List<ScmProductToWms> list = new ArrayList<ScmProductToWms>();
		for (ScmZsjProductFirst scmZsjProductFirst : firstList) {
			if(scmZsjProductFirst == null) {
				continue;
			}
	        ScmZsjProduct scmZsjProduct = scmZsjProductDao.get(scmZsjProductFirst.getProductId());
	        if(scmZsjProduct == null) {
	        	continue;
	        }
	        List<ScmZsjAchage> byMainId = scmZsjAchageDao.getByMainId(scmZsjProduct.getId());
	        if(byMainId.size() == 0) {
	        	continue;
	        }
	
	        for (ScmZsjAchage scmZsjAchage : byMainId) {
	        	ScmProductToWms scmProductToWms = new ScmProductToWms();
	        	scmProductToWms.setProductName(scmZsjProduct.getProductName());
	        	scmProductToWms.setProductLineName(scmZsjProduct.getProductLineName());
	        	scmProductToWms.setProductCode(scmZsjAchage.getCode());
	        	scmProductToWms.setCommonName(scmZsjProduct.getCommonName());
	        	scmProductToWms.setProductState(scmZsjProduct.getProductState());
	        	scmProductToWms.setAgentType(scmZsjProduct.getAgentType());
	        	scmProductToWms.setUnit(scmZsjProduct.getUnit());
	        	scmProductToWms.setOTC(scmZsjProduct.getOtc());
	        	scmProductToWms.setMedicineType(scmZsjProduct.getMedicineType());
	        	scmProductToWms.setMedicineClassify(scmZsjProduct.getExternalUse());
	        	scmProductToWms.setKeepingCondition(scmZsjProduct.getKeepingCondition());
	        	scmProductToWms.setSuggestWarehouse(scmZsjProduct.getSuggestWarehouse());
	        	scmProductToWms.setManufacturer(scmZsjProduct.getManufacturer());
	        	scmProductToWms.setInventoryCode(scmZsjProduct.getInventoryCode());
	        	scmProductToWms.setaChange(scmZsjAchage.getAchage());
	        	scmProductToWms.setLength(scmZsjAchage.getLength());
	        	scmProductToWms.setWide(scmZsjAchage.getWide());
	        	scmProductToWms.setHigh(scmZsjAchage.getHigh());
	        	scmProductToWms.setPlaceOfOrigin(scmZsjProduct.getPlaceOfOrigin());
	        	scmProductToWms.setOwnerId(scmZsjProductFirst.getOwnerId());
	        	scmProductToWms.setApprovalCode(scmZsjProductFirst.getApprovalCode());
	        	list.add(scmProductToWms);
			}
		}
        String jsonString = JSON.toJSONString(list);
        Map<String,String> header = new HashMap<String,String>();
		header.put("accept", "application/json;charset=UTF-8");
		header.put("Authorization", ScmWmsConfiguration.token);
		header.put("Content-Type", "application/json");
		ScmWmsReturnData parseObject = new ScmWmsReturnData();
        try{
        	String doPostJson = HttpClientUtil.doPostJson(ScmWmsConfiguration.url + "/prolog_aomei_interface/interface/item/info/scmItemInfoNotify.json", jsonString, header);
        	System.out.println("推送至WMS********************************");
        	System.out.println(jsonString);
        	System.out.println("WMS返回*********************************");
        	System.out.println(doPostJson);
        	System.out.println("结束*********************************");
        	parseObject = JSON.parseObject(doPostJson, ScmWmsReturnData.class);
        }catch (Exception e) {
        	//失败
        	for (ScmZsjProductFirst scmZsjProductFirst : firstList) {
        		scmZsjProductFirst.setIsToWms("0");
        		scmZsjProductFirstDao.update(scmZsjProductFirst);
			}
        }
        if("200".equals(parseObject.getStute())) {
        	//成功
        	for (ScmZsjProductFirst scmZsjProductFirst : firstList) {
        		scmZsjProductFirst.setIsToWms("1");
        		scmZsjProductFirstDao.update(scmZsjProductFirst);
			}
        }else{
        	//失败
        	for (ScmZsjProductFirst scmZsjProductFirst : firstList) {
        		scmZsjProductFirst.setIsToWms("0");
        		scmZsjProductFirstDao.update(scmZsjProductFirst);
			}
        }
	}
   
}
