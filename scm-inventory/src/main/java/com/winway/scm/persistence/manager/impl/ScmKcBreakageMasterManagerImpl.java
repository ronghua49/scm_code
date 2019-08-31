package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.pagehelper.PageHelper;
import com.winway.purchase.util.QuarterUtil;
import com.winway.purchase.util.ScmPuthWmsUtil;
import com.winway.purchase.vo.ScmWmsReceipt;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.winway.scm.persistence.dao.ScmKcBreakageDao;
import com.winway.scm.model.ScmKcBreakage;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.exception.SystemException;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.query.PageBean;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.BeanUtils;
import com.winway.scm.persistence.dao.ScmKcBreakageMasterDao;
import com.winway.scm.model.ScmKcBreakageMaster;
import com.winway.scm.persistence.manager.ScmKcBreakageManager;
import com.winway.scm.persistence.manager.ScmKcBreakageMasterManager;
import com.winway.scm.vo.BreakageRecord;
import com.winway.scm.vo.ScmKcBreakageMasterToWms;
import com.winway.scm.vo.ScmKcBreakageToWms;

/**
 * <pre>
 * 描述：不合格产品报损主表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-23 17:00:46
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmKcBreakageMasterManager")
public class ScmKcBreakageMasterManagerImpl extends AbstractManagerImpl<String, ScmKcBreakageMaster> implements ScmKcBreakageMasterManager {
    @Resource
    ScmKcBreakageMasterDao scmKcBreakageMasterDao;
    @Resource
    ScmKcBreakageDao scmKcBreakageDao;
    @Resource
    ScmKcBreakageManager scmKcBreakageManager;

    @Override
    protected MyBatisDao<String, ScmKcBreakageMaster> getDao() {
        return scmKcBreakageMasterDao;
    }

    /**
     * 创建实体包含子表实体
     */
    public void create(ScmKcBreakageMaster scmKcBreakageMaster) {
        super.create(scmKcBreakageMaster);
        String pk = scmKcBreakageMaster.getId();
        List<ScmKcBreakage> scmKcBreakageList = scmKcBreakageMaster.getScmKcBreakageList();
        // todo 是否控制报损数量
        for (ScmKcBreakage scmKcBreakage : scmKcBreakageList) {
            scmKcBreakage.setMasterId(pk);
            scmKcBreakageManager.create(scmKcBreakage);
        }
    }


    /**
     * 删除记录包含子表记录
     */
    public void remove(String entityId) {
        super.remove(entityId);
        scmKcBreakageDao.delByMainId(entityId);
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
    public ScmKcBreakageMaster get(String entityId) {
        ScmKcBreakageMaster scmKcBreakageMaster = scmKcBreakageMasterDao.get(entityId);
        if (scmKcBreakageMaster == null) {
            throw new RuntimeException("没有查询到历史数据");
        }
        List<ScmKcBreakage> scmKcBreakageList = scmKcBreakageDao.getByMainId(entityId);
        scmKcBreakageMaster.setScmKcBreakageList(scmKcBreakageList);
        return scmKcBreakageMaster;
    }

    /**
     * 更新实体同时更新子表记录
     */
    public void update(ScmKcBreakageMaster scmKcBreakageMaster) {
        super.update(scmKcBreakageMaster);
        String id = scmKcBreakageMaster.getId();
        scmKcBreakageDao.delByMainId(id);
        List<ScmKcBreakage> scmKcBreakageList = scmKcBreakageMaster.getScmKcBreakageList();
        for (ScmKcBreakage scmKcBreakage : scmKcBreakageList) {
            scmKcBreakage.setMasterId(id);
            scmKcBreakageManager.create(scmKcBreakage);
        }
    }

    @Override
    public void sendApply(ScmKcBreakageMaster scmKcBreakageMaster) {
        ScmKcBreakageMaster scmKcBreakageMaster1 = scmKcBreakageMasterDao.get(scmKcBreakageMaster.getId());
        if (scmKcBreakageMaster1 == null) {
            create(scmKcBreakageMaster);
        } else {
            if ("1".equals(scmKcBreakageMaster1.getApprovalState())) {
                throw new RuntimeException("该报损数据正在审批中，不得二次申请");
            }
            update(scmKcBreakageMaster);
        }
    }

    @Override
    public void endApply(JsonNode jsonNode) {
        String approvalId = jsonNode.get("instId").asText();
        String actionName = jsonNode.get("actionName").asText();
        //根据审批id查询数据
        ScmKcBreakageMaster byApprovalState = scmKcBreakageMasterDao.getByApprovalState(approvalId);
        if (byApprovalState == null) {
            throw new RuntimeException("未查询到业务数据,处理异常");
        }
        String endEvent = jsonNode.get("eventType").asText();
        if ("agree".equals(actionName) && "endEvent".equals(endEvent)) {
        	byApprovalState.setApprovalState("2");
            //todo 推送到wms
            try {
            	List<ScmKcBreakage> byMainId = scmKcBreakageDao.getByMainId(byApprovalState.getId());
            	List<ScmKcBreakageToWms> list = new ArrayList<ScmKcBreakageToWms>();
            	for (ScmKcBreakage scmKcBreakage : byMainId) {
            		ScmKcBreakageToWms scmKcBreakageToWms = new ScmKcBreakageToWms();
            		scmKcBreakageToWms.setBatchNum(scmKcBreakage.getBatchNum());
            		scmKcBreakageToWms.setProductName(scmKcBreakage.getProductName());
            		scmKcBreakageToWms.setMedicineType(scmKcBreakage.getMedicineType());
            		scmKcBreakageToWms.setProductState(scmKcBreakage.getProductState());
            		scmKcBreakageToWms.setSupplier(scmKcBreakage.getSupplier());
            		scmKcBreakageToWms.setExpiryDate(scmKcBreakage.getExpiryDate());
            		scmKcBreakageToWms.setPurchasingCost(scmKcBreakage.getPurchasingCost());
            		scmKcBreakageToWms.setBreakageSum(scmKcBreakage.getBreakageSum());
            		scmKcBreakageToWms.setDutyPersion(scmKcBreakage.getDutyPersion());
            		scmKcBreakageToWms.setMemo(scmKcBreakage.getMemo());
            		list.add(scmKcBreakageToWms);
				}
            	ScmKcBreakageMasterToWms scmKcBreakageMasterToWms = new ScmKcBreakageMasterToWms();
            	scmKcBreakageMasterToWms.setBreakageCode(byApprovalState.getBreakageCode());
            	scmKcBreakageMasterToWms.setCreatePersion(byApprovalState.getCreatePersion());
            	scmKcBreakageMasterToWms.setCreateDate(byApprovalState.getCreateDate());
            	scmKcBreakageMasterToWms.setVarietySum(byApprovalState.getVarietySum());
            	scmKcBreakageMasterToWms.setProductSum(byApprovalState.getProductSum());
            	scmKcBreakageMasterToWms.setPriceSum(byApprovalState.getPriceSum());
            	scmKcBreakageMasterToWms.setOwnerId(byApprovalState.getOwnerId());
            	scmKcBreakageMasterToWms.setProductList(list);
            	String jsonString = JSON.toJSONString(scmKcBreakageMasterToWms);
            	boolean puth = ScmPuthWmsUtil.puth(jsonString, "/prolog_aomei_interface/interface/unqualified/loss/scmUnqualifiedLossNotify.json","报损药品推送wms");
            	if(puth) {
            		byApprovalState.setIsToWms("1");
            	}else{
            		byApprovalState.setIsToWms("0");
            	}
            } catch (Exception e) {
            	byApprovalState.setIsToWms("0");
            }
            byApprovalState.setIsAffirmWms("0");
            scmKcBreakageMasterDao.update(byApprovalState);
        } else if ("agree".equals(actionName)) {
        } else if ("reject".equals(actionName)) {
        } else if ("backToStart".equals(actionName)) {
        } else if ("opposeTrans".equals(actionName)) {
        } else if ("endProcess".equals(actionName)) {
        	byApprovalState.setApprovalState("3");
        	scmKcBreakageMasterDao.update(byApprovalState);
        }
    }

    @Override
    public PageList<BreakageRecord> breakageRecord(QueryFilter queryFilter) {
        PageBean pageBean = queryFilter.getPageBean();
        if (BeanUtils.isEmpty(pageBean)) {
            PageHelper.startPage(1, Integer.MAX_VALUE, false);
        } else {
            PageHelper.startPage(pageBean.getPage().intValue(), pageBean.getPageSize().intValue(),
                    pageBean.showTotal());
        }
        List<BreakageRecord> query = scmKcBreakageMasterDao.breakageRecord(queryFilter.getParams());
        return new PageList(query);
    }

    @Override
    public ScmKcBreakageMaster getDetail(String approvalId) {
        ScmKcBreakageMaster byApprovalState = scmKcBreakageMasterDao.getByApprovalState(approvalId);
        if(byApprovalState==null){
            throw  new RuntimeException("没有查询到报损审批数据");
        }
        return get(byApprovalState.getId());
    }

	@Override
	public void receipt(ScmWmsReceipt scmWmsReceipt) {
		ScmKcBreakageMaster scmKcBreakageMaster = scmKcBreakageMasterDao.getByOrderCode(scmWmsReceipt.getOrderCode());
		if(scmKcBreakageMaster == null) {
			throw new RuntimeException("未查询到报损单");
		}
		if("1".equals(scmWmsReceipt.getTypt())) {
			scmKcBreakageMaster.setIsAffirmWms("1");
			scmKcBreakageMasterDao.update(scmKcBreakageMaster);
		}else {
			throw new RuntimeException("请传递正确的状态码");
		}
		
	}
}
