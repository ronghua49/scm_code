package com.winway.scm.persistence.manager.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.feign.BpmRuntimeFeignService;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.modelvo.CustomStartResult;
import com.hotent.base.modelvo.StartFlowParam;
import com.winway.purchase.util.QuarterUtil;
import com.winway.scm.model.ScmGspReviewCondition;
import com.winway.scm.persistence.dao.ScmGspReviewConditionDao;
import com.winway.scm.persistence.manager.ScmGspReviewConditionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 
 * <pre> 
 * 描述：药品复检通知单 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-24 12:00:21
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmGspReviewConditionManager")


public class ScmGspReviewConditionManagerImpl extends AbstractManagerImpl<String, ScmGspReviewCondition> implements ScmGspReviewConditionManager{
	@Resource
	ScmGspReviewConditionDao scmGspReviewConditionDao;
	@Resource
	BpmRuntimeFeignService bpmRuntimeFeignService;
	@Override
	protected MyBatisDao<String, ScmGspReviewCondition> getDao() {
		return scmGspReviewConditionDao;
	}

	@Transactional
	@Override
	public void sendApply(ScmGspReviewCondition scmGspReviewCondition) {
		ScmGspReviewCondition scmGspReviewCondition1 = scmGspReviewConditionDao.get(scmGspReviewCondition.getId());
		StartFlowParam startFlowParam = new StartFlowParam("ypzlfjfqsq", "SCM", "approvalId");
		startFlowParam.setFormType("frame");
		CustomStartResult customStartResult = null;
		try {
			System.out.println("发起药品复检通知单申请");
			customStartResult = bpmRuntimeFeignService.customStart(startFlowParam);
			String approvalId = customStartResult.getInstId();
			scmGspReviewCondition.setApprovalId(approvalId);
			scmGspReviewCondition.setApprovalState("1");
			if (scmGspReviewCondition1 == null) {
				scmGspReviewCondition.setReviewCode(QuarterUtil.getCode("FJDH"));
				super.create(scmGspReviewCondition);
			} else {
				super.update(scmGspReviewCondition);
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
		ScmGspReviewCondition byApprovalState = scmGspReviewConditionDao.getByApprovalState(approvalId);
		if (byApprovalState == null) {
			throw new RuntimeException("未查询到业务数据,处理异常");
		}
		String endEvent = jsonNode.get("eventType").asText();
		if ("agree".equals(actionName) && "endEvent".equals(endEvent)) {
			byApprovalState.setApprovalState("2");
			scmGspReviewConditionDao.update(byApprovalState);
		} else if ("agree".equals(actionName)) {
		} else if ("reject".equals(actionName)) {
		} else if ("backToStart".equals(actionName)) {
		} else if ("opposeTrans".equals(actionName)) {
		} else if ("endProcess".equals(actionName)) {
			byApprovalState.setApprovalState("3");
			scmGspReviewConditionDao.update(byApprovalState);
		}
	}

	@Override
	public ScmGspReviewCondition getDetailByApprovalId(String approvalId) {
		return scmGspReviewConditionDao.getByApprovalState(approvalId);
	}

}
