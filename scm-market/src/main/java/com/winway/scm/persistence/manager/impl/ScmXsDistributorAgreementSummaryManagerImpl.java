package com.winway.scm.persistence.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import com.hotent.base.feign.BpmRuntimeFeignService;
import com.hotent.base.modelvo.CustomStartResult;
import com.hotent.base.modelvo.StartFlowParam;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.purchase.util.QuarterUtil;
import com.winway.scm.model.ScmXsDistributorAgreementSummary;
import com.winway.scm.model.ScmXsDistributorClause;
import com.winway.scm.persistence.dao.ScmXsDistributorAgreementSummaryDao;
import com.winway.scm.persistence.dao.ScmXsDistributorClauseDao;
import com.winway.scm.persistence.manager.ScmXsDistributorAgreementSummaryManager;
import com.winway.scm.persistence.manager.ScmXsDistributorClauseManager;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * <pre> 
 * 描述：省区协议汇总表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:43
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmXsDistributorAgreementSummaryManager")
public class ScmXsDistributorAgreementSummaryManagerImpl extends AbstractManagerImpl<String, ScmXsDistributorAgreementSummary> implements ScmXsDistributorAgreementSummaryManager{
	@Resource
	ScmXsDistributorAgreementSummaryDao scmXsDistributorAgreementSummaryDao;
	@Resource
	ScmXsDistributorClauseDao scmXsDistributorClauseDao;
	@Resource
	ScmXsDistributorClauseManager scmXsDistributorClauseManager;
	@Resource
	BpmRuntimeFeignService bpmRuntimeFeignService;
	@Override
	protected MyBatisDao<String, ScmXsDistributorAgreementSummary> getDao() {
		return scmXsDistributorAgreementSummaryDao;
	}
	/**
	 * 创建实体包含子表实体
	 */
	public void create(ScmXsDistributorAgreementSummary scmXsDistributorAgreementSummary){
		super.create(scmXsDistributorAgreementSummary);
    	String pk=scmXsDistributorAgreementSummary.getId();
    	List<ScmXsDistributorClause> scmXsDistributorClauseList=scmXsDistributorAgreementSummary.getScmXsDistributorClauseList();
    	for(ScmXsDistributorClause scmXsDistributorClause:scmXsDistributorClauseList){
    		scmXsDistributorClause.setDistributorAgreementId(pk);
    		String startYear = scmXsDistributorClause.getStartYear();
    		String endYear = scmXsDistributorClause.getEndYear();
    		List<ScmXsDistributorClause> scmXsDistributorClauses = scmXsDistributorClauseDao.findByStartYearAndEndYear(startYear,endYear,scmXsDistributorClause.getCommerceId());
    		if(scmXsDistributorClauses.size() > 0) {
    			throw new RuntimeException(scmXsDistributorClause.getCommerceName() + "已经发起过"+startYear + "至" + endYear + "的申请!");
    		}
    		scmXsDistributorClause.setClauseCode(scmXsDistributorAgreementSummary.getAgreementSummaryCode());
    		scmXsDistributorClauseManager.create(scmXsDistributorClause);
    	}
    }
	
	/**
	 * 删除记录包含子表记录
	 */
	public void remove(String entityId){
		super.remove(entityId);
    	scmXsDistributorClauseDao.delByMainId(entityId);
	}
	
	/**
	 * 批量删除包含子表记录
	 */
	public void removeByIds(String[] entityIds){
		for(String id:entityIds){
			this.remove(id);
		}
	}
    
	/**
	 * 获取实体
	 */
    public ScmXsDistributorAgreementSummary get(String entityId){
    	ScmXsDistributorAgreementSummary scmXsDistributorAgreementSummary=super.get(entityId);
    	List<ScmXsDistributorClause> scmXsDistributorClauseList=scmXsDistributorClauseDao.getByMainId(entityId);
    	scmXsDistributorAgreementSummary.setScmXsDistributorClauseList(scmXsDistributorClauseList);
    	return scmXsDistributorAgreementSummary;
    }
    
    /**
     * 更新实体同时更新子表记录
     */
    public void update(ScmXsDistributorAgreementSummary scmXsDistributorAgreementSummary){
    	super.update(scmXsDistributorAgreementSummary);
    	String id=scmXsDistributorAgreementSummary.getId();
    	scmXsDistributorClauseDao.delByMainId(id);
    	List<ScmXsDistributorClause> scmXsDistributorClauseList=scmXsDistributorAgreementSummary.getScmXsDistributorClauseList();
    	for(ScmXsDistributorClause scmXsDistributorClause:scmXsDistributorClauseList){
    		scmXsDistributorClause.setDistributorAgreementId(id);
    		scmXsDistributorClauseManager.create(scmXsDistributorClause);
    	}
    }

    @Transactional
	@Override
	public void sendApply(ScmXsDistributorAgreementSummary scmXsDistributorAgreementSummary) {
		// TODO Auto-generated method stub
		//修改主表申请状态
		ScmXsDistributorAgreementSummary scmXsDistributorAgreementSummaryById = scmXsDistributorAgreementSummaryDao.get(scmXsDistributorAgreementSummary.getId());
		if (scmXsDistributorAgreementSummaryById == null) {	
			scmXsDistributorAgreementSummary.setApprovalState("1");
			String code = QuarterUtil.getCode("FXXY");
			scmXsDistributorAgreementSummary.setAgreementSummaryCode(code);
			create(scmXsDistributorAgreementSummary);
		}else{
			//有数据,先判断是否已经有正在审核中的数据
			if ("1".equals(scmXsDistributorAgreementSummaryById.getApprovalState())) {
				//数据在审批中,不可修改编辑
				throw new RuntimeException("当前数据已经在审批中,不可重复提交");
			}else{
				//修改主表，删除从表数据
				scmXsDistributorAgreementSummary.setAgreementSummaryCode(scmXsDistributorAgreementSummaryById.getAgreementSummaryCode());
				scmXsDistributorAgreementSummary.setApprovalState("1");
				update(scmXsDistributorAgreementSummary);
			}
		}
		StartFlowParam startFlowParam = new StartFlowParam("fxsxyhztk", "SCM", "approvalId");
		startFlowParam.setFormType("frame");
		CustomStartResult customStartResult = null;
		try {
			System.out.println("分销商协议合作条款申请");
			customStartResult = bpmRuntimeFeignService.customStart(startFlowParam);
			String approvalId = customStartResult.getInstId();
			scmXsDistributorAgreementSummary.setApprovalId(approvalId);
			super.update(scmXsDistributorAgreementSummary);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("启动工作流失败");
		}
	}
	@Override
	public void endApply(JsonNode jsonNode) {
		// TODO Auto-generated method stub
		String approvalId = jsonNode.get("instId").asText();
		String actionName = jsonNode.get("actionName").asText();
		ScmXsDistributorAgreementSummary scmXsDistributorAgreementSummaryId = scmXsDistributorAgreementSummaryDao.getSupplierFirstByApprovalId(approvalId);
		if(scmXsDistributorAgreementSummaryId == null) {
			throw new RuntimeException("未查询到业务数据,处理异常");
		}
		String endEvent = jsonNode.get("eventType").asText();
		if ("agree".equals(actionName) && "endEvent".equals(endEvent)) {
			//审批状态调整为通过
			scmXsDistributorAgreementSummaryId.setApprovalState("2");
			scmXsDistributorAgreementSummaryDao.update(scmXsDistributorAgreementSummaryId);
		} else if ("agree".equals(actionName)) {
		} else if ("reject".equals(actionName)) {
		} else if ("backToStart".equals(actionName)) {
		} else if ("opposeTrans".equals(actionName)) {
		} else if ("endProcess".equals(actionName)) {
			//审批状态调整为拒绝
			scmXsDistributorAgreementSummaryId.setApprovalState("3");
			scmXsDistributorAgreementSummaryDao.update(scmXsDistributorAgreementSummaryId);
		}	
	}
	
}
