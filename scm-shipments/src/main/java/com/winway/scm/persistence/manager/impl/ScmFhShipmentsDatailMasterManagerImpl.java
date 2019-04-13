package com.winway.scm.persistence.manager.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.winway.scm.model.ScmFhShipmentsTask;
import com.winway.scm.persistence.dao.ScmFhShipmentsMessageDao;
import com.winway.scm.persistence.dao.ScmFhShipmentsTaskDao;
import com.winway.scm.persistence.manager.ScmFhShipmentsMessageManager;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.pagehelper.PageHelper;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.query.PageBean;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.BeanUtils;
import com.winway.purchase.util.QuarterUtil;
import com.winway.scm.model.ScmFhShipmentsDatail;
import com.winway.scm.model.ScmFhShipmentsDatailMaster;
import com.winway.scm.model.ScmFhShipmentsMessage;
import com.winway.scm.persistence.dao.ScmFhShipmentsDatailDao;
import com.winway.scm.persistence.dao.ScmFhShipmentsDatailMasterDao;
import com.winway.scm.persistence.manager.ScmFhShipmentsDatailManager;
import com.winway.scm.persistence.manager.ScmFhShipmentsDatailMasterManager;
import com.winway.scm.vo.ScmFhShipmentsDataiAndMessageVo;

/**
 * <pre>
 * 描述：发货明细总表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-02-15 17:31:50
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmFhShipmentsDatailMasterManager")
public class ScmFhShipmentsDatailMasterManagerImpl extends AbstractManagerImpl<String, ScmFhShipmentsDatailMaster> implements ScmFhShipmentsDatailMasterManager {
    @Resource
    ScmFhShipmentsDatailMasterDao scmFhShipmentsDatailMasterDao;
    @Resource
    ScmFhShipmentsDatailDao scmFhShipmentsDatailDao;
    @Resource
    ScmFhShipmentsDatailManager scmFhShipmentsDatailManager;
    @Resource
    ScmFhShipmentsTaskDao scmFhShipmentsTaskDao;
    @Resource
    ScmFhShipmentsMessageManager scmFhShipmentsMessageManager;
    @Override
    protected MyBatisDao<String, ScmFhShipmentsDatailMaster> getDao() {
        return scmFhShipmentsDatailMasterDao;
    }

    /**
     * 创建实体包含子表实体
     */
    public void create(ScmFhShipmentsDatailMaster scmFhShipmentsDatailMaster) {
        scmFhShipmentsDatailMaster.setShipmentsCode(QuarterUtil.getCode("FHZL"));
        scmFhShipmentsDatailMaster.setCreateDate(new Date());
        super.create(scmFhShipmentsDatailMaster);
        String pk = scmFhShipmentsDatailMaster.getId();
        List<ScmFhShipmentsDatail> scmFhShipmentsDatailList = scmFhShipmentsDatailMaster.getScmFhShipmentsDatailList();
        for (ScmFhShipmentsDatail scmFhShipmentsDatail : scmFhShipmentsDatailList) {
            if (scmFhShipmentsDatail.getDeliveringAmount() > scmFhShipmentsDatail.getPlanShipmentsSum()) {
                throw new RuntimeException("实际量不得大于发货量");
            }
            scmFhShipmentsDatail.setMasterId(pk);
            scmFhShipmentsDatail.setCommerceId(scmFhShipmentsDatailMaster.getCommerceId());
            scmFhShipmentsDatail.setDirectiveCode(QuarterUtil.getCode("ZLBH"));
            scmFhShipmentsDatailManager.create(scmFhShipmentsDatail);
        }
    }
    /**
     * 删除记录包含子表记录
     */
    public void remove(String entityId) {
        super.remove(entityId);
        scmFhShipmentsDatailDao.delByMainId(entityId);
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
    public ScmFhShipmentsDatailMaster get(String entityId) {
        ScmFhShipmentsDatailMaster scmFhShipmentsDatailMaster = super.get(entityId);
        if(scmFhShipmentsDatailMaster==null){
            return null;
        }
        List<ScmFhShipmentsDatail> scmFhShipmentsDatailList = scmFhShipmentsDatailDao.getByMainId(entityId);
        scmFhShipmentsDatailMaster.setScmFhShipmentsDatailList(scmFhShipmentsDatailList);
        return scmFhShipmentsDatailMaster;
    }

    /**
     * 更新实体同时更新子表记录
     */
    public void update(ScmFhShipmentsDatailMaster scmFhShipmentsDatailMaster) {
        super.update(scmFhShipmentsDatailMaster);
        String id = scmFhShipmentsDatailMaster.getId();
        scmFhShipmentsDatailDao.delByMainId(id);
        //修复发货任务对应的计划发货量
        List<ScmFhShipmentsDatail> byMainId = scmFhShipmentsDatailDao.getByMainId(id);
        for (ScmFhShipmentsDatail detail : byMainId) {
            ScmFhShipmentsTask scmFhShipmentsTask = scmFhShipmentsTaskDao.get(detail.getShipmentsTaskId());
            if (scmFhShipmentsTask == null) {
                throw new RuntimeException("数据错误，没有查询到发货任务");
            }
            scmFhShipmentsTask.setPlanShipmentsSum(scmFhShipmentsTask.getPlanShipmentsSum() + detail.getDeliveringAmount());
            scmFhShipmentsTaskDao.update(scmFhShipmentsTask);
        }
        List<ScmFhShipmentsDatail> scmFhShipmentsDatailList = scmFhShipmentsDatailMaster.getScmFhShipmentsDatailList();
        for (ScmFhShipmentsDatail scmFhShipmentsDatail : scmFhShipmentsDatailList) {
            if (scmFhShipmentsDatail.getDeliveringAmount() > scmFhShipmentsDatail.getPlanShipmentsSum()) {
                throw new RuntimeException("实际量不得大于发货量");
            }
            scmFhShipmentsDatail.setMasterId(id);
            scmFhShipmentsDatail.setCommerceId(scmFhShipmentsDatailMaster.getCommerceId());
            scmFhShipmentsDatail.setDirectiveCode(QuarterUtil.getCode("ZLBH"));
            scmFhShipmentsDatailManager.create(scmFhShipmentsDatail);
        }
    }


    @Override
    public ScmFhShipmentsDatailMaster getByApplyId(String applyid) {
        ScmFhShipmentsDatailMaster byApprovalState = scmFhShipmentsDatailMasterDao.getByApprovalState(applyid);
        List<ScmFhShipmentsDatail> byMainId = scmFhShipmentsDatailDao.getByMainId(byApprovalState.getId());
        byApprovalState.setScmFhShipmentsDatailList(byMainId);
        return byApprovalState;
    }

    @Override
    public void sendApply(ScmFhShipmentsDatailMaster scmFhShipmentsDatailMaster) {
        //状态改为审批中
        scmFhShipmentsDatailMaster.setApprovalState("1");
        //判断主表中是否有数据
        ScmFhShipmentsDatailMaster sfdm = scmFhShipmentsDatailMasterDao.get(scmFhShipmentsDatailMaster.getId());
        if (sfdm == null) {
            create(scmFhShipmentsDatailMaster);
        } else {
            //有数据,先判断是否已经有正在审核中的数据
            if ("1".equals(sfdm.getApprovalState())) {
                //数据在审批中,不可修改编辑
                throw new RuntimeException("当前数据已经在审批中,不可重复提交");
            } else {
                update(scmFhShipmentsDatailMaster);
            }
        }
    }

    @Override
    public void endApply(JsonNode jsonNode) {
        String approvalId = jsonNode.get("instId").asText();
        String actionName = jsonNode.get("actionName").asText();
        //根据审批id查询数据
        ScmFhShipmentsDatailMaster sfdm = scmFhShipmentsDatailMasterDao.getByApprovalState(approvalId);
        if (sfdm == null) {
            throw new RuntimeException("未查询到业务数据,处理异常");
        }
        if ("agree".equals(actionName)) {
            sfdm.setApprovalState("2");
            //通过之后创建物流发运信息记录
            List<ScmFhShipmentsDatail> byMainId = scmFhShipmentsDatailDao.getByMainId(sfdm.getId());
            List<ScmFhShipmentsMessage> messageList = new ArrayList<>();
            for (ScmFhShipmentsDatail scmFhShipmentsDatail : byMainId) {
                //每条发货商品记录生成一个物流发运信息记录
                ScmFhShipmentsMessage scmFhShipmentsMessage = new ScmFhShipmentsMessage();
                scmFhShipmentsMessage.setShipmentsDatailId(scmFhShipmentsDatail.getId());
                //查询供应商地址

                //查询经销商地址

                //根据两个地址查询协议到货天数

                scmFhShipmentsMessage.setAgreementDays(7);
                messageList.add(scmFhShipmentsMessage);
                scmFhShipmentsMessageManager.create(scmFhShipmentsMessage);
                //确定发货时间 更新详情
                scmFhShipmentsDatail.setShipmentsAffirmDate(new Date());
                scmFhShipmentsDatailDao.update(scmFhShipmentsDatail);
            }
        } else if ("reject".equals(actionName)) {
            sfdm.setApprovalState("3");
        }
        scmFhShipmentsDatailMasterDao.update(sfdm);
    }

    @Override
    public PageList<ScmFhShipmentsDataiAndMessageVo> allShipmentMessage(QueryFilter queryFilter) {
        PageBean pageBean = queryFilter.getPageBean();
        if (BeanUtils.isEmpty(pageBean)) {
            PageHelper.startPage(1, Integer.MAX_VALUE, false);
        } else {
            PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
        }
        List<ScmFhShipmentsDataiAndMessageVo> query = scmFhShipmentsDatailMasterDao.allShipmentMessage(queryFilter.getParams());
        return new PageList<ScmFhShipmentsDataiAndMessageVo>(query);
    }




}
