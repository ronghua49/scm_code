package com.winway.scm.persistence.manager.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.feign.BpmRuntimeFeignService;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.modelvo.CustomStartResult;
import com.hotent.base.modelvo.StartFlowParam;
import com.winway.scm.model.ScmGspAndUseProduct;
import com.winway.scm.model.ScmGspDoubtProduct;
import com.winway.scm.model.ScmGspRelevantImportantMessage;
import com.winway.scm.model.ScmGspSideEffects;
import com.winway.scm.persistence.dao.ScmGspAndUseProductDao;
import com.winway.scm.persistence.dao.ScmGspDoubtProductDao;
import com.winway.scm.persistence.dao.ScmGspRelevantImportantMessageDao;
import com.winway.scm.persistence.dao.ScmGspSideEffectsDao;
import com.winway.scm.persistence.manager.ScmGspAndUseProductManager;
import com.winway.scm.persistence.manager.ScmGspDoubtProductManager;
import com.winway.scm.persistence.manager.ScmGspRelevantImportantMessageManager;
import com.winway.scm.persistence.manager.ScmGspSideEffectsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <pre>
 * 描述：药品不良反应事件管理 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-18 18:21:20
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmGspSideEffectsManager")
public class ScmGspSideEffectsManagerImpl extends AbstractManagerImpl<String, ScmGspSideEffects> implements ScmGspSideEffectsManager {
    @Resource
    ScmGspSideEffectsDao scmGspSideEffectsDao;
    @Resource
    ScmGspDoubtProductDao scmGspDoubtProductDao;
    @Resource
    ScmGspAndUseProductDao scmGspAndUseProductDao;
    @Resource
    ScmGspRelevantImportantMessageDao scmGspRelevantImportantMessageDao;

    @Resource
    ScmGspDoubtProductManager scmGspDoubtProductManager;
    @Resource
    ScmGspAndUseProductManager scmGspAndUseProductManager;
    @Resource
    ScmGspRelevantImportantMessageManager scmGspRelevantImportantMessageManager;
    @Resource
    BpmRuntimeFeignService bpmRuntimeFeignService;


    @Override
    protected MyBatisDao<String, ScmGspSideEffects> getDao() {
        return scmGspSideEffectsDao;
    }

    /**
     * 创建实体包含子表实体
     */
    public void create(ScmGspSideEffects scmGspSideEffects) {
        super.create(scmGspSideEffects);
        String pk = scmGspSideEffects.getId();
        createSon(pk, scmGspSideEffects);
    }

    private void createSon(String pk, ScmGspSideEffects scmGspSideEffects) {

        List<ScmGspDoubtProduct> scmGspDoubtProductList = scmGspSideEffects.getScmGspDoubtProductList();

        for (ScmGspDoubtProduct scmGspDoubtProduct : scmGspDoubtProductList) {
            scmGspDoubtProduct.setMasterId(pk);
            scmGspDoubtProductManager.create(scmGspDoubtProduct);
        }

        List<ScmGspAndUseProduct> scmGspAndUseProductList = scmGspSideEffects.getScmGspAndUseProductList();

        for (ScmGspAndUseProduct scmGspAndUseProduct : scmGspAndUseProductList) {
            scmGspAndUseProduct.setMasterId(pk);
            scmGspAndUseProductManager.create(scmGspAndUseProduct);
        }

        List<ScmGspRelevantImportantMessage> scmGspRelevantImportantMessageList = scmGspSideEffects.getScmGspRelevantImportantMessageList();

        for (ScmGspRelevantImportantMessage scmGspRelevantImportantMessage : scmGspRelevantImportantMessageList) {
            scmGspRelevantImportantMessage.setMasterId(pk);
            scmGspRelevantImportantMessageManager.create(scmGspRelevantImportantMessage);
        }
    }

    /**
     * 删除记录包含子表记录
     */
    public void remove(String entityId) {
        super.remove(entityId);
        scmGspDoubtProductDao.delByMainId(entityId);
        scmGspAndUseProductDao.delByMainId(entityId);
        scmGspRelevantImportantMessageDao.delByMainId(entityId);
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
    public ScmGspSideEffects get(String entityId) {
        ScmGspSideEffects scmGspSideEffects = super.get(entityId);
        if(scmGspSideEffects!=null){
            List<ScmGspDoubtProduct> scmGspDoubtProductList = scmGspDoubtProductDao.getByMainId(entityId);
            scmGspSideEffects.setScmGspDoubtProductList(scmGspDoubtProductList);
            List<ScmGspAndUseProduct> scmGspAndUseProductList = scmGspAndUseProductDao.getByMainId(entityId);
            scmGspSideEffects.setScmGspAndUseProductList(scmGspAndUseProductList);
            List<ScmGspRelevantImportantMessage> scmGspRelevantImportantMessageList = scmGspRelevantImportantMessageDao.getByMainId(entityId);
            scmGspSideEffects.setScmGspRelevantImportantMessageList(scmGspRelevantImportantMessageList);
        }
        return scmGspSideEffects;
    }

    /**
     * 更新实体同时更新子表记录
     */
    public void update(ScmGspSideEffects scmGspSideEffects) {
        super.update(scmGspSideEffects);
        String id = scmGspSideEffects.getId();
        scmGspDoubtProductDao.delByMainId(id);
        scmGspAndUseProductDao.delByMainId(id);
        scmGspRelevantImportantMessageDao.delByMainId(id);
        createSon(id, scmGspSideEffects);
    }

    @Transactional
    @Override
    public void sendApply(ScmGspSideEffects scmGspSideEffects) {
        ScmGspSideEffects scmGspSideEffects1 = get(scmGspSideEffects.getId());
        StartFlowParam startFlowParam = new StartFlowParam("ypblfysp", "SCM", "approvalId");
        startFlowParam.setFormType("frame");
        CustomStartResult customStartResult = null;
        try {
            System.out.println("发起药品不良反应申请");
            customStartResult = bpmRuntimeFeignService.customStart(startFlowParam);
            String approvalId = customStartResult.getInstId();
            scmGspSideEffects.setApprovalId(approvalId);
            scmGspSideEffects.setApprovalState("1");
            if (scmGspSideEffects1 == null) {
                create(scmGspSideEffects);
            } else {
                update(scmGspSideEffects);
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
        //根据审批id查询数据
        ScmGspSideEffects byApprovalState = scmGspSideEffectsDao.getByApprovalState(approvalId);
        if (byApprovalState == null) {
            throw new RuntimeException("未查询到业务数据,处理异常");
        }
        String endEvent = jsonNode.get("eventType").asText();
        if ("agree".equals(actionName) && "endEvent".equals(endEvent)) {
        	 byApprovalState.setApprovalState("2");
        	 scmGspSideEffectsDao.update(byApprovalState);
        } else if ("agree".equals(actionName)) {
        } else if ("reject".equals(actionName)) {
        } else if ("backToStart".equals(actionName)) {
        } else if ("opposeTrans".equals(actionName)) {
        } else if ("endProcess".equals(actionName)) {
        	 byApprovalState.setApprovalState("3");
        	 scmGspSideEffectsDao.update(byApprovalState);
        }
    }

    @Override
    public ScmGspSideEffects getByApprovalId(String id) {
        ScmGspSideEffects byApprovalState = scmGspSideEffectsDao.getByApprovalState(id);
        if(byApprovalState!=null){
            byApprovalState = get(byApprovalState.getId());
        }
        return byApprovalState;
    }
}
