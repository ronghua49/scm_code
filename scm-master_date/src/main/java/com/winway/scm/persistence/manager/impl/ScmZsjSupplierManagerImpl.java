package com.winway.scm.persistence.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.hotent.base.feign.BpmRuntimeFeignService;
import com.hotent.base.modelvo.CustomStartResult;
import com.hotent.base.modelvo.StartFlowParam;
import com.winway.scm.model.*;
import com.winway.scm.persistence.dao.ScmZsjSupplierFirstDao;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.pagehelper.PageHelper;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageBean;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.BeanUtils;
import com.winway.scm.persistence.dao.ScmZsjSupplierDao;
import com.winway.purchase.feign.ScmMasterDateFeignService;
import com.winway.purchase.tool.ScmWmsConfiguration;
import com.winway.purchase.util.ContrasUtil;
import com.winway.purchase.util.HttpClientUtil;
import com.winway.purchase.util.QuarterUtil;
import com.winway.scm.persistence.manager.ScmZsjSupplierManager;
import com.winway.scm.persistence.vo.Commerce;
import com.winway.scm.persistence.vo.ProductVo;
import com.winway.scm.persistence.vo.Supplier;

/**
 * 
 * <pre> 
 * 描述：供应商表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmZsjSupplierManager")
public class ScmZsjSupplierManagerImpl extends AbstractManagerImpl<String, ScmZsjSupplier> implements ScmZsjSupplierManager{
	@Resource
	ScmZsjSupplierDao scmZsjSupplierDao;

	@Resource
	ScmZsjSupplierFirstDao supplierFirstDao;
	@Resource
	ScmMasterDateFeignService scmMasterDateFeignService;
	@Resource
	BpmRuntimeFeignService bpmRuntimeFeignService;

	@Override
	protected MyBatisDao<String, ScmZsjSupplier> getDao() {
		return scmZsjSupplierDao;
	}
	@Override
	public void getByNameOrId(ScmZsjSupplier scmZsjSupplier) {
		//根据名称与ID判断是否已经存在当前使用名称
		List<ScmZdDutyAffirm> sdas = scmZsjSupplierDao.getByNameOrId(scmZsjSupplier);
		if(sdas.size() > 0) {
			throw new RuntimeException("当前名称已经被使用,请修改后提交");
		}
	}
	@Override
	public void isExist(ScmZsjSupplier scmZsjSupplier) {
		if(scmZsjSupplier == null) {
			throw new RuntimeException("商业信息不能为空");
		}
		//有商业ID 说明该商业已经存在
		if(scmZsjSupplier.getId() != null) {
			return;
		}
		if(scmZsjSupplier.getSupplierName() == null) {
			throw new RuntimeException("商业名称不能为空");
		}
		//根据名称查询该商业
		List<ScmZsjCommerce> byName = scmZsjSupplierDao.getByName(scmZsjSupplier.getSupplierName());
		//可以查询到,直接返回
		if(byName.size() == 0) {
			//没有查询到,保存
			scmZsjSupplierDao.create(scmZsjSupplier);
		}
		
	}
	@Override
	public PageList<ScmZsjSupplier> firstList(QueryFilter queryFilter) {
    	PageBean pageBean = queryFilter.getPageBean();
    	if(BeanUtils.isEmpty(pageBean)){
    		PageHelper.startPage(1, Integer.MAX_VALUE, false);
    	}
    	else{
    		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
    	}
    	List<ScmZsjSupplier> query = scmZsjSupplierDao.firstList(queryFilter.getParams());
        return new PageList<ScmZsjSupplier>(query);
	}
	@Override
	public List<ScmZsjSupplier> downBox(String ownerId) {
		List<ScmZsjSupplier> downBox = scmZsjSupplierDao.downBox(ownerId);
		return downBox;
	}
	@Override
	public List<ScmZsjSupplier> downBoxApplySuccess(String ownerId,String managementScope) {
		List<ScmZsjSupplier> downBox = scmZsjSupplierDao.downBoxApplySuccess(ownerId,managementScope);
		return downBox;
	}
	
	@Override
	public void update(ScmZsjSupplier scmZsjSupplier) {
		String id = scmZsjSupplier.getId();
		ScmZsjSupplier scmZsjSupplier2 = scmZsjSupplierDao.get(id);
		if(scmZsjSupplier2 == null) {
			throw new RuntimeException("历史数据获取异常");
		}
		scmZsjSupplier.setSupplierCode(scmZsjSupplier2.getSupplierCode());
		scmZsjSupplier.setFileCode(scmZsjSupplier2.getFileCode());
		scmZsjSupplier.setEnterDate(scmZsjSupplier2.getEnterDate());
		try {
			String contrast = ContrasUtil.Contrast(scmZsjSupplier2, scmZsjSupplier, id, "gys");
			if (!StringUtils.isEmpty(contrast)) {
				scmMasterDateFeignService.inserScmLogRecord(contrast);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.update(scmZsjSupplier);
	}
	
	@Override
	public void create(ScmZsjSupplier entity) {
		String code = scmZsjSupplierDao.getSupplierCode();
		entity.setSupplierCode(code);
		try{
			
			Supplier supplier = new Supplier();
			supplier.setSupplierName(entity.getSupplierName());
			supplier.setFileCode(entity.getFileCode());
			supplier.setSupplierCode(entity.getSupplierCode());
			supplier.setConpanyPersion(entity.getConpanyPersion());
			supplier.setOpenBank(entity.getOpenBank());
			supplier.setBankCode(entity.getBankCode());
			supplier.setDutyParagraph(entity.getDutyParagraph());
			supplier.setRegisterAddress(entity.getRegisterAddress());
			supplier.setEnterPersion(entity.getEnterPersion());
			supplier.setIsDelete("0");
			List<Supplier> list = new ArrayList<Supplier>();
	        list.add(supplier);
			Map<String,String> header = new HashMap<String,String>();
			header.put("accept", "application/json;charset=UTF-8");
			header.put("Authorization", ScmWmsConfiguration.MASTER_TOKEN);
			header.put("Content-Type", "application/json");
	        String jsonString = JSON.toJSONString(list);
	        String doPostJson = HttpClientUtil.doPostJson(ScmWmsConfiguration.MASTER_DATE_URL+"/supplier/supplier/v1/saveList", jsonString,header);
	        CommonResult parseObject = JSON.parseObject(doPostJson, CommonResult.class);
			String code2 = parseObject.getCode();
			if("10000".equals(code2)) {
				entity.setIsToMasterData("1");
			}else{
				entity.setIsToMasterData("0");
			}
		}catch (Exception e) {
			entity.setIsToMasterData("0");
		}
		super.create(entity);
		
	}
	
	@Override
	public void sendApply(ScmZsjSupplier scmZsjSupplier) {
		ScmZsjSupplier scmZsjSupplier1 = scmZsjSupplierDao.get(scmZsjSupplier.getId());
		StartFlowParam startFlowParam = new StartFlowParam("gys", "SCM", "approvalId");
		startFlowParam.setFormType("frame");
		CustomStartResult customStartResult = null;
		try {
			System.out.println("供应商修改申请");
			customStartResult = bpmRuntimeFeignService.customStart(startFlowParam);
			String approvalId = customStartResult.getInstId();
			if(scmZsjSupplier1 == null) {
				//新增
				scmZsjSupplier.setApprovalId(approvalId);
				scmZsjSupplier.setApprovalState("1");
				scmZsjSupplier.setFileCode(QuarterUtil.getCode("GYS"));
				create(scmZsjSupplier);
			}else{
				//修改
				if("1".equals(scmZsjSupplier.getApprovalState())) {
					throw new RuntimeException("当前数据已提交审批,请勿重复提交");
				}
				scmZsjSupplier.setApprovalId(approvalId);
				scmZsjSupplier.setApprovalState("1");
				scmZsjSupplier.setFileCode(scmZsjSupplier1.getFileCode());
				scmZsjSupplier.setOperatorDate(scmZsjSupplier1.getOperatorDate());
				scmZsjSupplier.setOperatorName(scmZsjSupplier1.getOperatorName());
				update(scmZsjSupplier);
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
		ScmZsjSupplier entity = scmZsjSupplierDao.getByApprovalId(approvalId);
    	if(entity == null) {
			throw new RuntimeException("未查询到业务数据,处理异常");
		}
    	String endEvent = jsonNode.get("eventType").asText();
    	if ("agree".equals(actionName) && "endEvent".equals(endEvent)) {
            entity.setApprovalState("2");
            scmZsjSupplierDao.update(entity);
    		Supplier supplier = new Supplier();
    		supplier.setSupplierName(entity.getSupplierName());
    		supplier.setFileCode(entity.getFileCode());
    		supplier.setSupplierCode(entity.getSupplierCode());
    		supplier.setConpanyPersion(entity.getConpanyPersion());
    		supplier.setOpenBank(entity.getOpenBank());
    		supplier.setBankCode(entity.getBankCode());
    		supplier.setDutyParagraph(entity.getDutyParagraph());
    		supplier.setRegisterAddress(entity.getRegisterAddress());
    		supplier.setEnterPersion(entity.getEnterPersion());
    		supplier.setIsDelete("0");
    		List<Supplier> list = new ArrayList<Supplier>();
            list.add(supplier);
    		Map<String,String> header = new HashMap<String,String>();
    		header.put("accept", "application/json;charset=UTF-8");
    		header.put("Authorization", ScmWmsConfiguration.MASTER_TOKEN);
    		header.put("Content-Type", "application/json");
            String jsonString = JSON.toJSONString(list);
            try{
	            String doPostJson = HttpClientUtil.doPostJson(ScmWmsConfiguration.MASTER_DATE_URL+"/supplier/supplier/v1/saveList", jsonString,header);
	            CommonResult parseObject = JSON.parseObject(doPostJson, CommonResult.class);
	    		String code2 = parseObject.getCode();
	    		if("10000".equals(code2)) {
	    			entity.setIsToMasterData("1");
	    		}else{
	    			entity.setIsToMasterData("0");
	    		}
            }catch (Exception e) {
            	entity.setIsToMasterData("0");
            }
    	} else if ("agree".equals(actionName)) {
    	} else if ("reject".equals(actionName)) {
    	} else if ("backToStart".equals(actionName)) {
    	} else if ("opposeTrans".equals(actionName)) {
    	} else if ("endProcess".equals(actionName)) {
        	entity.setApprovalState("3");
        	scmZsjSupplierDao.update(entity);
    	}
	}
	@Override
	public ScmZsjSupplier getByApprovalId(String approvalId) {
		ScmZsjSupplier byApprovalId = scmZsjSupplierDao.getByApprovalId(approvalId);
		return byApprovalId;
	}


	@Override
	public void remove(String entityId) {
		ScmZsjSupplierFirst supplierFirst = supplierFirstDao.getBySupplierId(entityId);
		if(supplierFirst!=null){
			throw  new RuntimeException("该供应商已经发起首营，不得删除");
		}
		//同步数据
		ScmZsjSupplier entity = scmZsjSupplierDao.get(entityId);
		Supplier supplier = new Supplier();
		supplier.setSupplierName(entity.getSupplierName());
		supplier.setFileCode(entity.getFileCode());
		supplier.setSupplierCode(entity.getSupplierCode());
		supplier.setConpanyPersion(entity.getConpanyPersion());
		supplier.setOpenBank(entity.getOpenBank());
		supplier.setBankCode(entity.getBankCode());
		supplier.setDutyParagraph(entity.getDutyParagraph());
		supplier.setRegisterAddress(entity.getRegisterAddress());
		supplier.setEnterPersion(entity.getEnterPersion());
		supplier.setIsDelete("1");
		List<Supplier> list = new ArrayList<Supplier>();
        list.add(supplier);
		Map<String,String> header = new HashMap<String,String>();
		header.put("accept", "application/json;charset=UTF-8");
		header.put("Authorization", ScmWmsConfiguration.MASTER_TOKEN);
		header.put("Content-Type", "application/json");
        String jsonString = JSON.toJSONString(list);
        String doPostJson = HttpClientUtil.doPostJson(ScmWmsConfiguration.MASTER_DATE_URL+"/supplier/supplier/v1/saveList", jsonString,header);
        CommonResult parseObject = JSON.parseObject(doPostJson, CommonResult.class);
		String code2 = parseObject.getCode();
		if("10000".equals(code2)) {
			super.remove(entityId);
		}else{
			throw new RuntimeException(parseObject.getMessage());
		}
	}
	
	@Override
	public void updateSyn(String id) {
		ScmZsjSupplier entity = scmZsjSupplierDao.get(id);
		Supplier supplier = new Supplier();
		supplier.setSupplierName(entity.getSupplierName());
		supplier.setFileCode(entity.getFileCode());
		supplier.setSupplierCode(entity.getSupplierCode());
		supplier.setConpanyPersion(entity.getConpanyPersion());
		supplier.setOpenBank(entity.getOpenBank());
		supplier.setBankCode(entity.getBankCode());
		supplier.setDutyParagraph(entity.getDutyParagraph());
		supplier.setRegisterAddress(entity.getRegisterAddress());
		supplier.setEnterPersion(entity.getEnterPersion());
		List<Supplier> list = new ArrayList<Supplier>();
        list.add(supplier);
		Map<String,String> header = new HashMap<String,String>();
		header.put("accept", "application/json;charset=UTF-8");
		header.put("Authorization", ScmWmsConfiguration.MASTER_TOKEN);
		header.put("Content-Type", "application/json");
        String jsonString = JSON.toJSONString(list);
        String doPostJson = HttpClientUtil.doPostJson(ScmWmsConfiguration.MASTER_DATE_URL+"/supplier/supplier/v1/saveList", jsonString,header);
        CommonResult parseObject = JSON.parseObject(doPostJson, CommonResult.class);
		String code2 = parseObject.getCode();
		if("10000".equals(code2)) {
			entity.setIsToMasterData("1");
			scmZsjSupplierDao.update(entity);
		}else{
			entity.setIsToMasterData("0");
			scmZsjSupplierDao.update(entity);
		}
	}
}
