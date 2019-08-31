package com.winway.scm.persistence.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.model.ScmZsjCommerceBlackList;
import com.winway.scm.persistence.dao.ScmZsjCommerceBlackListDao;
import com.winway.scm.persistence.manager.ScmZsjCommerceBlackListManager;

/**
 * <pre>
 * 描述：商业黑名单表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-18 14:36:01
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmZsjCommerceBlackListManager")
public class ScmZsjCommerceBlackListManagerImpl extends AbstractManagerImpl<String, ScmZsjCommerceBlackList> implements ScmZsjCommerceBlackListManager {
    @Resource
    ScmZsjCommerceBlackListDao scmZsjCommerceBlackListDao;

    @Override
    protected MyBatisDao<String, ScmZsjCommerceBlackList> getDao() {
        return scmZsjCommerceBlackListDao;
    }

    @Override
    public boolean getCommerceIsBlack(String id, String ownerId) {
        List<ScmZsjCommerceBlackList> scmZsjCommerceBlackList = scmZsjCommerceBlackListDao.getCommerceIsBlack(id, ownerId);
        return scmZsjCommerceBlackList.size() != 0;
    }

    @Override
    public void endApply(JsonNode jsonNode) {
        String approvalId = jsonNode.get("instId").asText();
        String actionName = jsonNode.get("actionName").asText();
        ScmZsjCommerceBlackList scmZsjCommerceBlackList = scmZsjCommerceBlackListDao.getByApplId(approvalId);
        if (scmZsjCommerceBlackList == null) {
            throw new RuntimeException("未查询到业务数据,处理异常");
        }
        String endEvent = jsonNode.get("eventType").asText();
        if ("agree".equals(actionName) && "endEvent".equals(endEvent)) {
        	scmZsjCommerceBlackList.setApprovalState("2");
        	scmZsjCommerceBlackListDao.update(scmZsjCommerceBlackList);
        } else if ("agree".equals(actionName)) {
        } else if ("reject".equals(actionName)) {
        } else if ("backToStart".equals(actionName)) {
        } else if ("opposeTrans".equals(actionName)) {
        } else if ("endProcess".equals(actionName)) {
        	scmZsjCommerceBlackList.setApprovalState("3");
        	scmZsjCommerceBlackListDao.update(scmZsjCommerceBlackList);
        }
    }

    @Override
    public ScmZsjCommerceBlackList getByApplId(String applyId) {
        return scmZsjCommerceBlackListDao.getByApplId(applyId);
    }

    @Override
    public void create(ScmZsjCommerceBlackList entity) {
        ScmZsjCommerceBlackList  commerceBlackList= scmZsjCommerceBlackListDao.getByCommerceCode(entity.getCommerceCode());
        if(commerceBlackList!=null&&commerceBlackList.getApprovalState().equals("0")){
            throw  new RuntimeException("该商业黑名单申请数据还未提交，请在编辑中修改后申请");
        }else  if (commerceBlackList!=null && commerceBlackList.getApprovalState().equals("1")){
            throw  new RuntimeException("该商业黑名单的申请正在审批中，不可重复申请");
        }else  if(commerceBlackList!=null && commerceBlackList.getApprovalState().equals("2")){
            throw  new RuntimeException("该行业已经在黑名单中，不可重复添加");
        }
        super.create(entity);
    }
}
