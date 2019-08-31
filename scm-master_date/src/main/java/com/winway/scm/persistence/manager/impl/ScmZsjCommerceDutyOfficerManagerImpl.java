package com.winway.scm.persistence.manager.impl;
import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.util.JsonUtil;
import com.hotent.base.util.StringUtil;
import com.winway.scm.model.ScmZsjCommerceDutyOfficer;
import com.winway.scm.persistence.dao.ScmZsjCommerceDutyOfficerDao;
import com.winway.scm.persistence.manager.ScmZsjCommerceDutyOfficerManager;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * <pre>
 * 描述：商业责任人表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-18 14:36:01
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmZsjCommerceDutyOfficerManager")
public class ScmZsjCommerceDutyOfficerManagerImpl extends AbstractManagerImpl<String, ScmZsjCommerceDutyOfficer> implements ScmZsjCommerceDutyOfficerManager {
    @Resource
    ScmZsjCommerceDutyOfficerDao scmZsjCommerceDutyOfficerDao;

    @Override
    protected MyBatisDao<String, ScmZsjCommerceDutyOfficer> getDao() {
        return scmZsjCommerceDutyOfficerDao;
    }

    @Override
    public void sendApply(ScmZsjCommerceDutyOfficer scmZsjCommerceDutyOfficer) {
        ScmZsjCommerceDutyOfficer scmZsjCommerceDutyOfficer2 = get(scmZsjCommerceDutyOfficer.getId());
        if (scmZsjCommerceDutyOfficer2 == null) {
            create(scmZsjCommerceDutyOfficer);
        }
        if (scmZsjCommerceDutyOfficer2 != null) {
            if (scmZsjCommerceDutyOfficer2.getApprovalState().equals("1")) {
                throw new RuntimeException("该数据正在审批中，不得二次申请");
            }
            update(scmZsjCommerceDutyOfficer);
        }
    }

    @Override
    public void endApply(String params) throws IOException {
        JsonNode jsonNode = JsonUtil.toJsonNode(params);
        String approvalId = jsonNode.get("instId").textValue();
        String actionName = jsonNode.get("actionName").textValue();
        List<ScmZsjCommerceDutyOfficer> byApprovalId = scmZsjCommerceDutyOfficerDao.getByApprovalId(approvalId);
        if (byApprovalId == null || byApprovalId.isEmpty()) {
            throw new RuntimeException("未查询到业务数据,处理异常");
        }
        String endEvent = jsonNode.get("eventType").asText();
        if ("agree".equals(actionName) && "endEvent".equals(endEvent)) {
        	 for (ScmZsjCommerceDutyOfficer officer : byApprovalId) {
                 //将历史审批数据设为失效
                 List<ScmZsjCommerceDutyOfficer> dutyOfficerList = scmZsjCommerceDutyOfficerDao.getByCommerceId(officer.getCommerceId());
                 if (dutyOfficerList != null && !dutyOfficerList.isEmpty()) {
                     for (ScmZsjCommerceDutyOfficer history : dutyOfficerList) {
                         history.setIseffect("0");
                         update(history);
                     }
                 }
                 officer.setApprovalState("2");
                 officer.setIseffect("1");
                 officer.setDutyOfficer(officer.getNewDutyOfficer());
                 officer.setNewDutyOfficer(null);
                 officer.setMemo(null);
                 update(officer);
             }
        } else if ("agree".equals(actionName)) {
        } else if ("reject".equals(actionName)) {
        } else if ("backToStart".equals(actionName)) {
        } else if ("opposeTrans".equals(actionName)) {
        } else if ("endProcess".equals(actionName)) {
        	 for (ScmZsjCommerceDutyOfficer officer : byApprovalId) {
                 officer.setApprovalState("3");
                 officer.setIseffect("0");
                 update(officer);
             }
        }
    }

    @Override
    public void createCache(ScmZsjCommerceDutyOfficer scmZsjCommerceDutyOfficer) {
        scmZsjCommerceDutyOfficer.setApprovalState("0");
        if (StringUtil.isEmpty(scmZsjCommerceDutyOfficer.getId())) {
            create(scmZsjCommerceDutyOfficer);
        } else {
            ScmZsjCommerceDutyOfficer scmZsjCommerceDutyOfficer2 = get(scmZsjCommerceDutyOfficer.getId());
            if (scmZsjCommerceDutyOfficer2 == null) {
                create(scmZsjCommerceDutyOfficer);
            } else {
                update(scmZsjCommerceDutyOfficer);
            }
        }
    }

    @Override
    public ScmZsjCommerceDutyOfficer getlastDutyOfficer(String commerceId) {
        return scmZsjCommerceDutyOfficerDao.getlastDutyOfficer(commerceId);
    }

}
