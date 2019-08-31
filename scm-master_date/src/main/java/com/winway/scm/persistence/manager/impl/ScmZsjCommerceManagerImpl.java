package com.winway.scm.persistence.manager.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.hotent.base.feign.BpmRuntimeFeignService;
import com.hotent.base.modelvo.CustomStartResult;
import com.hotent.base.modelvo.StartFlowParam;
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
import com.winway.purchase.feign.ScmMasterDateFeignService;
import com.winway.purchase.tool.ScmWmsConfiguration;
import com.winway.purchase.util.ContrasUtil;
import com.winway.purchase.util.HttpClientUtil;
import com.winway.purchase.util.QuarterUtil;
import com.winway.scm.model.ScmZsjCommerce;
import com.winway.scm.model.ScmZsjCommerceFirst;
import com.winway.scm.model.ScmZsjProductLine;
import com.winway.scm.persistence.dao.ScmZsjCommerceDao;
import com.winway.scm.persistence.dao.ScmZsjCommerceFirstDao;
import com.winway.scm.persistence.manager.ScmZsjCommerceManager;
import com.winway.scm.persistence.vo.Commerce;
import com.winway.scm.persistence.vo.ProdCategory;
import org.springframework.transaction.annotation.Transactional;

/**
 * <pre>
 * 描述：商业表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmZsjCommerceManager")
public class ScmZsjCommerceManagerImpl extends AbstractManagerImpl<String, ScmZsjCommerce> implements ScmZsjCommerceManager {
    @Resource
    ScmZsjCommerceDao scmZsjCommerceDao;

    @Resource
    ScmZsjCommerceFirstDao commerceFirstDao;

    @Resource
    ScmMasterDateFeignService scmMasterDateFeignService;
    @Resource
    BpmRuntimeFeignService bpmRuntimeFeignService;

    @Override
    protected MyBatisDao<String, ScmZsjCommerce> getDao() {
        return scmZsjCommerceDao;
    }

    @Override
    public void getByNameOrId(ScmZsjCommerce scmZsjCommerce) {
        //根据名称与ID判断是否已经存在当前使用名称
        List<ScmZsjCommerce> sdas = scmZsjCommerceDao.getByNameOrId(scmZsjCommerce);
        if (sdas.size() > 0) {
            throw new RuntimeException("当前名称已经被使用,请修改后提交");
        }
    }

    @Override
    public List<ScmZsjCommerce> getByName(String commerceName) {
        List<ScmZsjCommerce> byNames = scmZsjCommerceDao.getByName(commerceName);
        return byNames;
    }

    //判断商业是否存在,不存在直接保存
    @Override
    public void isExist(ScmZsjCommerce scmZsjCommerce) {
        if (scmZsjCommerce == null) {
            throw new RuntimeException("商业信息不能为空");
        }
        //有商业ID 说明该商业已经存在
        if (scmZsjCommerce.getId() != null) {
            return;
        }
        if (scmZsjCommerce.getCommerceName() == null) {
            throw new RuntimeException("商业名称不能为空");
        }
        //根据名称查询该商业
        List<ScmZsjCommerce> byName = scmZsjCommerceDao.getByName(scmZsjCommerce.getCommerceName());
        //可以查询到,直接返回
        if (byName.size() == 0) {
            //没有查询到,保存
            scmZsjCommerceDao.create(scmZsjCommerce);
        }
    }

    @Override
    public PageList<ScmZsjCommerce> firstList(QueryFilter queryFilter) {
        PageBean pageBean = queryFilter.getPageBean();
        if (BeanUtils.isEmpty(pageBean)) {
            PageHelper.startPage(1, Integer.MAX_VALUE, false);
        } else {
            PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
        }
        List<ScmZsjCommerce> query = scmZsjCommerceDao.firstList(queryFilter.getParams());
        return new PageList<ScmZsjCommerce>(query);
    }

    @Override
    public List<ScmZsjCommerce> downBox(String ownerId) {
        List<ScmZsjCommerce> query = scmZsjCommerceDao.downBox(ownerId);
        return query;
    }

    @Override
    public PageList<ScmZsjCommerce> listCommerceAcceptState(QueryFilter queryFilter) {
        PageBean pageBean = queryFilter.getPageBean();
        if (BeanUtils.isEmpty(pageBean)) {
            PageHelper.startPage(1, Integer.MAX_VALUE, false);
        } else {
            PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
        }
        List<ScmZsjCommerce> query = scmZsjCommerceDao.listCommerceAcceptState(queryFilter.getParams());
        return new PageList<ScmZsjCommerce>(query);
    }

    @Override
    public PageList<ScmZsjCommerce> listCommerceAcceptApprova(QueryFilter queryFilter) {
        PageBean pageBean = queryFilter.getPageBean();
        if (BeanUtils.isEmpty(pageBean)) {
            PageHelper.startPage(1, Integer.MAX_VALUE, false);
        } else {
            PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
        }
        List<ScmZsjCommerce> query = scmZsjCommerceDao.listCommerceAcceptApprova(queryFilter.getParams());
        return new PageList<ScmZsjCommerce>(query);
    }

    @Override
    public PageList<ScmZsjCommerce> listCommerceAcceptTS(QueryFilter queryFilter) {
        PageBean pageBean = queryFilter.getPageBean();
        if (BeanUtils.isEmpty(pageBean)) {
            PageHelper.startPage(1, Integer.MAX_VALUE, false);
        } else {
            PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
        }
        List<ScmZsjCommerce> query = scmZsjCommerceDao.listCommerceAcceptTS(queryFilter.getParams());
        return new PageList<ScmZsjCommerce>(query);
    }

    @Override
    public List<ScmZsjCommerce> downBoxApplySuccess(String ownerId,String businessDivisionId) {
        List<ScmZsjCommerce> query = scmZsjCommerceDao.downBoxApplySuccess(ownerId,businessDivisionId);
        return query;
    }

    @Override
    public PageList<ScmZsjCommerce> agreementSendApplySuccessList(QueryFilter queryFilter) {
        PageBean pageBean = queryFilter.getPageBean();
        if (BeanUtils.isEmpty(pageBean)) {
            PageHelper.startPage(1, Integer.MAX_VALUE, false);
        } else {
            PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
        }
        List<ScmZsjCommerce> query = scmZsjCommerceDao.agreementSendApplySuccessList(queryFilter.getParams());
        return new PageList<ScmZsjCommerce>(query);
    }

    @Override
    public List<String> getCommerceName() {
        return scmZsjCommerceDao.getCommerceName();
    }

    @Override
    public String findByOwnerId(String ownerId) {
        List<ScmZsjCommerce> ScmZsjCommerce = null;
        try {
            ScmZsjCommerce = scmZsjCommerceDao.findByOwnerId(ownerId);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        Map map = new HashMap();
        for (ScmZsjCommerce x : ScmZsjCommerce) {
            map.put(x.getCommerceName(), x);
        }

        return JSON.toJSONString(map);
    }

    @Override
    public void update(ScmZsjCommerce scmZsjCommerce) {
        String id = scmZsjCommerce.getId();
        ScmZsjCommerce scmZsjCommerce2 = scmZsjCommerceDao.get(id);
        if (scmZsjCommerce2 == null) {
            throw new RuntimeException("历史数据获取异常");
        }
        scmZsjCommerce.setCommerceCode(scmZsjCommerce2.getCommerceCode());
        scmZsjCommerce.setEnterDate(scmZsjCommerce2.getEnterDate());
        try {
            String contrast = ContrasUtil.Contrast(scmZsjCommerce2, scmZsjCommerce, id, "sy");
            if (!StringUtils.isEmpty(contrast)) {
                scmMasterDateFeignService.inserScmLogRecord(contrast);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.update(scmZsjCommerce);
    }

    @Override
    public void create(ScmZsjCommerce arg0) {
        String code = scmZsjCommerceDao.getCommerceCode();
        arg0.setCommerceCode(code);
        //推送至主数据中心
        Commerce commerce = new Commerce();
        commerce.setCommerceName(arg0.getCommerceName());
        commerce.setFileCode(arg0.getFileCode());
        commerce.setCommerceState(arg0.getCommerceState());
        commerce.setCommerceCode(arg0.getCommerceCode());
        commerce.setCompanyPersion(arg0.getConpanyPersion());
        commerce.setOpenBank(arg0.getOpenBank());
        commerce.setBankCode(arg0.getBankCode());
        commerce.setDutyParagraph(arg0.getDutyParagraph());
        commerce.setBusinessDivision(arg0.getBusinessDivision());
        commerce.setBusinessDivisionid(arg0.getBusinessDivisionId());
        commerce.setProvince(arg0.getProvince());
        commerce.setProvinceId(arg0.getProvinceId());
        commerce.setRegisterAddress(arg0.getRegisterAddress());
        commerce.setEnterPersion(arg0.getEnterPersion());
        commerce.setCommerceLevel(arg0.getCommerceLevel());
        commerce.setFunctionType(arg0.getFunctionType());
        commerce.setIsDelete("0");
        List<Commerce> list = new ArrayList<Commerce>();
        list.add(commerce);
		Map<String,String> header = new HashMap<String,String>();
		header.put("accept", "application/json;charset=UTF-8");
		header.put("Authorization", ScmWmsConfiguration.MASTER_TOKEN);
		header.put("Content-Type", "application/json");
        String jsonString = JSON.toJSONString(list);
        String doPostJson = HttpClientUtil.doPostJson(ScmWmsConfiguration.MASTER_DATE_URL+"/masterdata/commerce/v1/saveList", jsonString,header);
        CommonResult parseObject = JSON.parseObject(doPostJson, CommonResult.class);
		String code2 = parseObject.getCode();
		if("10000".equals(code2)) {
			arg0.setIsToMasterData("1");
			super.create(arg0);
		}else{
			arg0.setIsToMasterData("0");
			super.create(arg0);
		}
    }

    @Transactional
    @Override
    public void sendApply(ScmZsjCommerce scmZsjCommerce) {
        ScmZsjCommerce scmZsjCommerce1 = scmZsjCommerceDao.get(scmZsjCommerce.getId());
        if (scmZsjCommerce1 == null) {
            //新增
            scmZsjCommerce.setFileCode(QuarterUtil.getCode("DA"));
            scmZsjCommerce.setApprovalState("1");
            create(scmZsjCommerce);
        } else {
            //修改
            if ("1".equals(scmZsjCommerce1.getApprovalState())) {
                throw new RuntimeException("当前数据已提交审批,请勿重复提交");
            }
            scmZsjCommerce.setCommerceCode(scmZsjCommerce1.getCommerceCode());
            scmZsjCommerce.setFileCode(scmZsjCommerce1.getFileCode());
            scmZsjCommerce.setEnterDate(scmZsjCommerce1.getEnterDate());
            scmZsjCommerce.setEnterPersion(scmZsjCommerce1.getEnterPersion());
            scmZsjCommerce.setApprovalState("1");
            update(scmZsjCommerce);
            StartFlowParam startFlowParam = new StartFlowParam("sy", "SCM", "approvalId");
            startFlowParam.setFormType("frame");
            CustomStartResult customStartResult = null;
            try {
                System.out.println("发起商业修改申请");
                customStartResult = bpmRuntimeFeignService.customStart(startFlowParam);
                String approvalId = customStartResult.getInstId();
                scmZsjCommerce.setApprovalId(approvalId);
                super.update(scmZsjCommerce);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("发起流程失败");
            }
        }
    }

    @Override
    public void endApply(JsonNode jsonNode) {
        String approvalId = jsonNode.get("instId").asText();
        String actionName = jsonNode.get("actionName").asText();
        ScmZsjCommerce arg0 = scmZsjCommerceDao.getByApprovalId(approvalId);
        if (arg0 == null) {
            throw new RuntimeException("未查询到业务数据,处理异常");
        }
        String endEvent = jsonNode.get("eventType").asText();
        if ("agree".equals(actionName) && "endEvent".equals(endEvent)) {
        	//审批状态调整为通过
            arg0.setApprovalState("2");
            scmZsjCommerceDao.update(arg0);
        	//推送数据
            Commerce commerce = new Commerce();
            commerce.setCommerceName(arg0.getCommerceName());
            commerce.setFileCode(arg0.getFileCode());
            commerce.setCommerceState(arg0.getCommerceState());
            commerce.setCommerceCode(arg0.getCommerceCode());
            commerce.setCompanyPersion(arg0.getConpanyPersion());
            commerce.setOpenBank(arg0.getOpenBank());
            commerce.setBankCode(arg0.getBankCode());
            commerce.setDutyParagraph(arg0.getDutyParagraph());
            commerce.setBusinessDivision(arg0.getBusinessDivision());
            commerce.setBusinessDivisionid(arg0.getBusinessDivisionId());
            commerce.setProvince(arg0.getProvince());
            commerce.setProvinceId(arg0.getProvinceId());
            commerce.setRegisterAddress(arg0.getRegisterAddress());
            commerce.setEnterPersion(arg0.getEnterPersion());
            commerce.setCommerceLevel(arg0.getCommerceLevel());
            commerce.setFunctionType(arg0.getFunctionType());
            commerce.setIsDelete("0");
            List<Commerce> list = new ArrayList<Commerce>();
            list.add(commerce);
    		Map<String,String> header = new HashMap<String,String>();
    		header.put("accept", "application/json;charset=UTF-8");
    		header.put("Authorization", ScmWmsConfiguration.MASTER_TOKEN);
    		header.put("Content-Type", "application/json");
            String jsonString = JSON.toJSONString(list);
            try{
            	String doPostJson = HttpClientUtil.doPostJson(ScmWmsConfiguration.MASTER_DATE_URL+"/masterdata/commerce/v1/saveList", jsonString,header);
            	CommonResult parseObject = JSON.parseObject(doPostJson, CommonResult.class);
            	String code2 = parseObject.getCode();
            	if("10000".equals(code2)) {
            		arg0.setIsToMasterData("1");
            	}else{
            		arg0.setIsToMasterData("0");
            	}
            }catch (Exception e) {
            	arg0.setIsToMasterData("0");
            }
            scmZsjCommerceDao.update(arg0);
        } else if ("agree".equals(actionName)) {
        } else if ("reject".equals(actionName)) {
        } else if ("backToStart".equals(actionName)) {
        } else if ("opposeTrans".equals(actionName)) {
        } else if ("endProcess".equals(actionName)) {
        	arg0.setApprovalState("3");
        	scmZsjCommerceDao.update(arg0);
        }
    }

    @Override
    public ScmZsjCommerce getByApprovalId(String approvalId) {
        ScmZsjCommerce byApprovalId = scmZsjCommerceDao.getByApprovalId(approvalId);
        return byApprovalId;
    }

    @Override
    public List<ScmZsjCommerce> acceptCommerceList(String ownerId, String businessDivisionId, String provinceId) {
        List<ScmZsjCommerce> list = scmZsjCommerceDao.acceptCommerceList(ownerId, businessDivisionId, provinceId);
        return list;
    }

    @Override
    public ScmZsjCommerce getcommerceByCode(String commerceCode) {
        ScmZsjCommerce scmZsjCommerce = scmZsjCommerceDao.getcommerceByCode(commerceCode);
        return scmZsjCommerce;
    }

    @Override
    public void remove(String entityId) {
        ScmZsjCommerceFirst byCommerceId = commerceFirstDao.getByCommerceId(entityId);
        if (byCommerceId != null) {
            throw new RuntimeException("该商业已经首营不得删除");
        }
        ScmZsjCommerce arg0 = scmZsjCommerceDao.get(entityId);
        if(arg0 == null) {
        	return;
        }
        //同步数据
        Commerce commerce = new Commerce();
        commerce.setCommerceName(arg0.getCommerceName());
        commerce.setFileCode(arg0.getFileCode());
        commerce.setCommerceState(arg0.getCommerceState());
        commerce.setCommerceCode(arg0.getCommerceCode());
        commerce.setCompanyPersion(arg0.getConpanyPersion());
        commerce.setOpenBank(arg0.getOpenBank());
        commerce.setBankCode(arg0.getBankCode());
        commerce.setDutyParagraph(arg0.getDutyParagraph());
        commerce.setBusinessDivision(arg0.getBusinessDivision());
        commerce.setBusinessDivisionid(arg0.getBusinessDivisionId());
        commerce.setProvince(arg0.getProvince());
        commerce.setProvinceId(arg0.getProvinceId());
        commerce.setRegisterAddress(arg0.getRegisterAddress());
        commerce.setEnterPersion(arg0.getEnterPersion());
        commerce.setCommerceLevel(arg0.getCommerceLevel());
        commerce.setFunctionType(arg0.getFunctionType());
        commerce.setIsDelete("1");
        List<Commerce> list = new ArrayList<Commerce>();
        list.add(commerce);
		Map<String,String> header = new HashMap<String,String>();
		header.put("accept", "application/json;charset=UTF-8");
		header.put("Authorization", ScmWmsConfiguration.MASTER_TOKEN);
		header.put("Content-Type", "application/json");
        String jsonString = JSON.toJSONString(list);
        String doPostJson = HttpClientUtil.doPostJson(ScmWmsConfiguration.MASTER_DATE_URL+"/masterdata/commerce/v1/saveList", jsonString,header);
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
		ScmZsjCommerce arg0 = scmZsjCommerceDao.get(id);
        Commerce commerce = new Commerce();
        commerce.setCommerceName(arg0.getCommerceName());
        commerce.setFileCode(arg0.getFileCode());
        commerce.setCommerceState(arg0.getCommerceState());
        commerce.setCommerceCode(arg0.getCommerceCode());
        commerce.setCompanyPersion(arg0.getConpanyPersion());
        commerce.setOpenBank(arg0.getOpenBank());
        commerce.setBankCode(arg0.getBankCode());
        commerce.setDutyParagraph(arg0.getDutyParagraph());
        commerce.setBusinessDivision(arg0.getBusinessDivision());
        commerce.setBusinessDivisionid(arg0.getBusinessDivisionId());
        commerce.setProvince(arg0.getProvince());
        commerce.setProvinceId(arg0.getProvinceId());
        commerce.setRegisterAddress(arg0.getRegisterAddress());
        commerce.setEnterPersion(arg0.getEnterPersion());
        commerce.setCommerceLevel(arg0.getCommerceLevel());
        commerce.setFunctionType(arg0.getFunctionType());
        List<Commerce> list = new ArrayList<Commerce>();
        list.add(commerce);
		Map<String,String> header = new HashMap<String,String>();
		header.put("accept", "application/json;charset=UTF-8");
		header.put("Authorization", ScmWmsConfiguration.MASTER_TOKEN);
		header.put("Content-Type", "application/json");
        String jsonString = JSON.toJSONString(list);
        String doPostJson = HttpClientUtil.doPostJson(ScmWmsConfiguration.MASTER_DATE_URL+"/masterdata/commerce/v1/saveList", jsonString,header);
        CommonResult parseObject = JSON.parseObject(doPostJson, CommonResult.class);
		String code2 = parseObject.getCode();
		if("10000".equals(code2)) {
			arg0.setIsToMasterData("1");
			scmZsjCommerceDao.update(arg0);
		}else{
			arg0.setIsToMasterData("0");
			scmZsjCommerceDao.update(arg0);
		}
	}

    @Override
    public ScmZsjCommerce getcommerceFirstByCode(String commerceCode,String ownerId) {
    	ScmZsjCommerce getcommerceFirstByCode = scmZsjCommerceDao.getcommerceFirstByCode(commerceCode,ownerId);
        return getcommerceFirstByCode;
    }

    @Override
    public ScmZsjCommerce getcommerceByName(String commerceName) {
        return scmZsjCommerceDao.getcommerceByName(commerceName);
    }

}
