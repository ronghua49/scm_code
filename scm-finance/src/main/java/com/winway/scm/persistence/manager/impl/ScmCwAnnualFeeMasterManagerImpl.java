package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.winway.scm.persistence.dao.ScmCwAnnualFeeDao;
import com.winway.scm.model.ScmCwAnnualFee;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmCwAnnualFeeMasterDao;
import com.winway.scm.model.ScmCwAnnualFeeMaster;
import com.winway.scm.persistence.manager.ScmCwAnnualFeeMasterManager;

/**
 * <pre>
 * 描述：商业年费信息管理费主表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-28 09:38:34
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmCwAnnualFeeMasterManager")
public class ScmCwAnnualFeeMasterManagerImpl extends AbstractManagerImpl<String, ScmCwAnnualFeeMaster> implements ScmCwAnnualFeeMasterManager {
    @Resource
    ScmCwAnnualFeeMasterDao scmCwAnnualFeeMasterDao;
    @Resource
    ScmCwAnnualFeeDao scmCwAnnualFeeDao;

    @Override
    protected MyBatisDao<String, ScmCwAnnualFeeMaster> getDao() {
        return scmCwAnnualFeeMasterDao;
    }

    /**
     * 创建实体包含子表实体
     */
    public void create(ScmCwAnnualFeeMaster scmCwAnnualFeeMaster) {
        super.create(scmCwAnnualFeeMaster);
        String pk = scmCwAnnualFeeMaster.getId();

        List<ScmCwAnnualFee> scmCwAnnualFeeList = scmCwAnnualFeeMaster.getScmCwAnnualFeeList();

        for (ScmCwAnnualFee scmCwAnnualFee : scmCwAnnualFeeList) {
            scmCwAnnualFee.setMasterId(pk);
            scmCwAnnualFeeDao.create(scmCwAnnualFee);
        }
    }

    /**
     * 删除记录包含子表记录
     */
    public void remove(String entityId) {
        super.remove(entityId);
        scmCwAnnualFeeDao.delByMainId(entityId);
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
    public ScmCwAnnualFeeMaster get(String entityId) {
        ScmCwAnnualFeeMaster scmCwAnnualFeeMaster = super.get(entityId);
        List<ScmCwAnnualFee> scmCwAnnualFeeList = scmCwAnnualFeeDao.getByMainId(entityId);
        scmCwAnnualFeeMaster.setScmCwAnnualFeeList(scmCwAnnualFeeList);
        return scmCwAnnualFeeMaster;
    }

    /**
     * 更新实体同时更新子表记录
     */
    public void update(ScmCwAnnualFeeMaster scmCwAnnualFeeMaster) {
        super.update(scmCwAnnualFeeMaster);
        String id = scmCwAnnualFeeMaster.getId();
        scmCwAnnualFeeDao.delByMainId(id);
        List<ScmCwAnnualFee> scmCwAnnualFeeList = scmCwAnnualFeeMaster.getScmCwAnnualFeeList();
        for (ScmCwAnnualFee scmCwAnnualFee : scmCwAnnualFeeList) {
            scmCwAnnualFee.setMasterId(id);
            scmCwAnnualFeeDao.create(scmCwAnnualFee);
        }
    }

    @Override
    public void sendApply(ScmCwAnnualFeeMaster scmCwAnnualFeeMaster) {
        ScmCwAnnualFeeMaster scmCwAnnualFeeMaster1 = scmCwAnnualFeeMasterDao.get(scmCwAnnualFeeMaster.getId());
        if (scmCwAnnualFeeMaster1 == null) {
            throw new RuntimeException("数据异常，没有查询到主表数据");
        } else {
            if ("1".equals(scmCwAnnualFeeMaster1.getApprovalState())) {
                throw new RuntimeException("该数据正在审批中，不得二次申请");
            }
            update(scmCwAnnualFeeMaster1);
        }
    }

    @Override
    public void endApply(JsonNode jsonNode) {
        String approvalId = jsonNode.get("instId").asText();
        String actionName = jsonNode.get("actionName").asText();
        //根据审批id查询数据
        ScmCwAnnualFeeMaster byApprovalState = scmCwAnnualFeeMasterDao.getByApprovalState(approvalId);
        if (byApprovalState == null) {
            throw new RuntimeException("未查询到业务数据,处理异常");
        }
        String endEvent = jsonNode.get("eventType").asText();
        if ("agree".equals(actionName) && "endEvent".equals(endEvent)) {
        	byApprovalState.setApprovalState("2");
        	 scmCwAnnualFeeMasterDao.update(byApprovalState);
        	//将年费信息管理费添加到票折池
        } else if ("agree".equals(actionName)) {
        } else if ("reject".equals(actionName)) {
        } else if ("backToStart".equals(actionName)) {
        } else if ("opposeTrans".equals(actionName)) {
        } else if ("endProcess".equals(actionName)) {
        	 byApprovalState.setApprovalState("3");
        	 scmCwAnnualFeeMasterDao.update(byApprovalState);
        }
    }
}
