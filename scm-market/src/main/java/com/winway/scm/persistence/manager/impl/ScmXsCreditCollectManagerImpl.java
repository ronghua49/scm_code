package com.winway.scm.persistence.manager.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hotent.base.feign.BpmRuntimeFeignService;
import com.hotent.base.feign.MasterdataFeignService;
import com.hotent.base.modelvo.CustomStartResult;
import com.hotent.base.modelvo.StartFlowParam;
import com.winway.purchase.feign.ScmMasterDateFeignService;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.purchase.util.QuarterUtil;
import com.winway.scm.model.ScmXsCreditApply;
import com.winway.scm.model.ScmXsCreditCollect;
import com.winway.scm.persistence.dao.ScmXsCreditApplyDao;
import com.winway.scm.persistence.dao.ScmXsCreditCollectDao;
import com.winway.scm.persistence.manager.ScmXsCreditApplyManager;
import com.winway.scm.persistence.manager.ScmXsCreditCollectManager;

/**
 * <pre>
 * 描述：商业资信申请总表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:43
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmXsCreditCollectManager")
public class ScmXsCreditCollectManagerImpl extends AbstractManagerImpl<String, ScmXsCreditCollect> implements ScmXsCreditCollectManager {
    @Resource
    ScmXsCreditCollectDao scmXsCreditCollectDao;
    @Resource
    ScmXsCreditApplyDao scmXsCreditApplyDao;
    @Resource
    ScmXsCreditApplyManager scmXsCreditApplyManager;
    @Resource
    ScmMasterDateFeignService scmMasterDateFeignService;
    @Resource
    BpmRuntimeFeignService bpmRuntimeFeignService;


    @Override
    protected MyBatisDao<String, ScmXsCreditCollect> getDao() {
        return scmXsCreditCollectDao;
    }

    /**
     * 创建实体包含子表实体
     */
    public void create(ScmXsCreditCollect scmXsCreditCollect) {
        scmXsCreditCollect.setRequestCode(QuarterUtil.getCode(""));
        super.create(scmXsCreditCollect);
        String pk = scmXsCreditCollect.getId();

        List<ScmXsCreditApply> scmXsCreditApplyList = scmXsCreditCollect.getScmXsCreditApplyList();
        for (ScmXsCreditApply scmXsCreditApply : scmXsCreditApplyList) {
            scmXsCreditApply.setCreditCollectId(pk);
            scmXsCreditApplyManager.create(scmXsCreditApply);
        }
    }

    /**
     * 删除记录包含子表记录
     */
    public void remove(String entityId) {
        super.remove(entityId);
        scmXsCreditApplyDao.delByMainId(entityId);
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
    public ScmXsCreditCollect get(String entityId) {
        ScmXsCreditCollect scmXsCreditCollect = super.get(entityId);
        List<ScmXsCreditApply> scmXsCreditApplyList = scmXsCreditApplyDao.getByMainId(entityId);
        for (ScmXsCreditApply creditApply : scmXsCreditApplyList) {
            String s = scmMasterDateFeignService.getcommerceByCode(creditApply.getCommerceCode());
            JSONObject jsonObject = JSON.parseObject(s);
            String commerceId = (String) jsonObject.get("id");
            ScmXsCreditApply statisticalDate = scmXsCreditApplyManager.getStatisticalDate(commerceId, scmXsCreditCollect.getOwnerId());
            creditApply.setLastYearPrice(statisticalDate.getLastYearPrice());
            creditApply.setBeforeYearPrice(statisticalDate.getBeforeYearPrice());
            creditApply.setLastTimePrice(statisticalDate.getLastTimePrice());
            creditApply.setBeforeYearPrice(statisticalDate.getBeforeYearPrice());
            creditApply.setSysPrice(statisticalDate.getSysPrice());
        }
        scmXsCreditCollect.setScmXsCreditApplyList(scmXsCreditApplyList);
        return scmXsCreditCollect;
    }

    /**
     * 更新实体同时更新子表记录
     */
    public void update(ScmXsCreditCollect scmXsCreditCollect) {
        super.update(scmXsCreditCollect);
        String id = scmXsCreditCollect.getId();
        scmXsCreditApplyDao.delByMainId(id);
        List<ScmXsCreditApply> scmXsCreditApplyList = scmXsCreditCollect.getScmXsCreditApplyList();
        for (ScmXsCreditApply scmXsCreditApply : scmXsCreditApplyList) {
            scmXsCreditApply.setCreditCollectId(id);
            scmXsCreditApplyManager.create(scmXsCreditApply);
        }
    }

    @Override
    public void sendApply(ScmXsCreditCollect scmXsCreditCollect) {
        //根据ID查询是否存在数据，如果存在，表示已有暂存过，没有表示未暂存过
        ScmXsCreditCollect scmXsCreditCollect1 = scmXsCreditCollectDao.get(scmXsCreditCollect.getId());
        StartFlowParam startFlowParam = new StartFlowParam("syzxlc", "SCM", "approvalId");
        startFlowParam.setFormType("frame");
        CustomStartResult customStartResult = null;
        try {
            System.out.println("发起商业资信申请");
            customStartResult = bpmRuntimeFeignService.customStart(startFlowParam);
            String approvalId = customStartResult.getInstId();
            scmXsCreditCollect.setApprovalId(approvalId);
            scmXsCreditCollect.setApprovalState("1");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("启动工作流失败");
        }
        if (scmXsCreditCollect1 == null) {
            create(scmXsCreditCollect);
        } else {
            update(scmXsCreditCollect);
        }
    }

    @Override
    public void endApply(JsonNode jsonNode) {
        String approvalId = jsonNode.get("instId").asText();
        String actionName = jsonNode.get("actionName").asText();
        ScmXsCreditCollect byApprovalId = scmXsCreditCollectDao.getByApprovalId(approvalId);
        String endEvent = jsonNode.get("eventType").asText();
        if ("agree".equals(actionName) && "endEvent".equals(endEvent)) {
        	byApprovalId.setApprovalState("2");
        	scmXsCreditCollectDao.update(byApprovalId);
        } else if ("agree".equals(actionName)) {
        } else if ("reject".equals(actionName)) {
        } else if ("backToStart".equals(actionName)) {
        } else if ("opposeTrans".equals(actionName)) {
        } else if ("endProcess".equals(actionName)) {
        	byApprovalId.setApprovalState("3");
        	scmXsCreditCollectDao.update(byApprovalId);
        }
    }

    @Override
    public ScmXsCreditCollect getCreditApplyByApprovalId(String id) {
        // TODO Auto-generated method stub
        //根据审批Id获取主表主键
        ScmXsCreditCollect scmXsCreditCollect = scmXsCreditCollectDao.getByApprovalId(id);
        if (scmXsCreditCollect == null) {
            throw new RuntimeException("获取数据异常");
        }
        String masterId = scmXsCreditCollect.getId();
        //根据主键id获取从表的数据
        List<ScmXsCreditApply> ScmXsCreditApply = scmXsCreditApplyDao.getByMainId(masterId);
        scmXsCreditCollect.setScmXsCreditApplyList(ScmXsCreditApply);
        return scmXsCreditCollect;
    }
}
