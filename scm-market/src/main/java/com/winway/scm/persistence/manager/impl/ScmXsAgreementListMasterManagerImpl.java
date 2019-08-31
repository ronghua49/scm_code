package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import com.hotent.base.feign.BpmRuntimeFeignService;
import com.hotent.base.modelvo.CustomStartResult;
import com.hotent.base.modelvo.StartFlowParam;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.winway.scm.persistence.dao.ScmXsAgreementListDao;
import com.winway.purchase.util.QuarterUtil;
import com.winway.scm.model.ScmXsAgreementList;
import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmXsAgreementListMasterDao;
import com.winway.scm.model.ScmXsAgreementListMaster;
import com.winway.scm.persistence.manager.ScmXsAgreementListManager;
import com.winway.scm.persistence.manager.ScmXsAgreementListMasterManager;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * <pre> 
 * 描述：商业协议合作名单主表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 15:01:16
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmXsAgreementListMasterManager")
public class ScmXsAgreementListMasterManagerImpl extends AbstractManagerImpl<String, ScmXsAgreementListMaster> implements ScmXsAgreementListMasterManager{
	@Resource
	ScmXsAgreementListMasterDao scmXsAgreementListMasterDao;
	@Resource
	ScmXsAgreementListDao scmXsAgreementListDao;
	@Resource
	ScmXsAgreementListManager scmXsAgreementListManager;
	@Resource
	BpmRuntimeFeignService bpmRuntimeFeignService;
	
	@Override
	protected MyBatisDao<String, ScmXsAgreementListMaster> getDao() {
		return scmXsAgreementListMasterDao;
	}
	/**
	 * 创建实体包含子表实体
	 */
	public void create(ScmXsAgreementListMaster scmXsAgreementListMaster){
		//申请编号
		scmXsAgreementListMaster.setMasterCode(QuarterUtil.getCode("JXMD"));
		super.create(scmXsAgreementListMaster);
		//获取主表id
		String pk=scmXsAgreementListMaster.getId();	
		List<ScmXsAgreementList> scmXsAgreementListList=scmXsAgreementListMaster.getScmXsAgreementListList();   	
		//保存从表中的数据
		for(ScmXsAgreementList scmXsAgreementList:scmXsAgreementListList){
			//生成日期对象        
			Date current_date = new Date();        
			//设置日期格式化样式为：yyyy-MM-dd       
			SimpleDateFormat  df = new SimpleDateFormat("yyyy");
			String year = df.format(current_date);
			scmXsAgreementList.setYear(year);
			scmXsAgreementList.setMasterId(pk);
			scmXsAgreementListManager.create(scmXsAgreementList);
		}
		
    }
	
	/**
	 * 删除记录包含子表记录
	 */
	public void remove(String entityId){
		super.remove(entityId);
    	scmXsAgreementListDao.delByMainId(entityId);
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
    public ScmXsAgreementListMaster get(String entityId){
    	ScmXsAgreementListMaster scmXsAgreementListMaster=super.get(entityId);
    	List<ScmXsAgreementList> scmXsAgreementListList=scmXsAgreementListDao.getByMainId(entityId);
    	for (ScmXsAgreementList scmXsAgreementList : scmXsAgreementListList) {
    		scmXsAgreementList.setBusinessDivisionId(scmXsAgreementListMaster.getBusinessDivisionId());
    		scmXsAgreementList.setBusinessDivision(scmXsAgreementListMaster.getBusinessDivision());
		}
    	scmXsAgreementListMaster.setScmXsAgreementListList(scmXsAgreementListList);
    	return scmXsAgreementListMaster;
    }
    
    /**
     * 更新实体同时更新子表记录
     */
    public void update(ScmXsAgreementListMaster scmXsAgreementListMaster){
    	String id=scmXsAgreementListMaster.getId();
    	ScmXsAgreementListMaster  master = scmXsAgreementListMasterDao.get(id);
    	scmXsAgreementListMaster.setMasterCode(master.getMasterCode());
    	super.update(scmXsAgreementListMaster);
    	scmXsAgreementListDao.delByMainId(id);
    	List<ScmXsAgreementList> scmXsAgreementListList=scmXsAgreementListMaster.getScmXsAgreementListList();
    	for(ScmXsAgreementList scmXsAgreementList:scmXsAgreementListList){
    		//生成日期对象        
			Date current_date = new Date();        
			//设置日期格式化样式为：yyyy-MM-dd       
			SimpleDateFormat  df = new SimpleDateFormat("yyyy");
			String year = df.format(current_date);
			scmXsAgreementList.setYear(year);
			scmXsAgreementList.setMasterId(id);
			scmXsAgreementListManager.create(scmXsAgreementList);
    	}
    }
	@Override
	public void endApply(JsonNode jsonNode) {
		// TODO Auto-generated method stub
		String approvalId = jsonNode.get("instId").asText();
		String actionName = jsonNode.get("actionName").asText();
		ScmXsAgreementListMaster scmXsAgreementListMasterId = scmXsAgreementListMasterDao.getSupplierFirstByApprovalId(approvalId);
		if(scmXsAgreementListMasterId == null) {
			throw new RuntimeException("未查询到业务数据,处理异常");
		}
		String endEvent = jsonNode.get("eventType").asText();
		if ("agree".equals(actionName) && "endEvent".equals(endEvent)) {
			//把当前商务大区下的数据调整为失效
			scmXsAgreementListMasterDao.updateIsEffect(scmXsAgreementListMasterId.getBusinessDivisionId(),scmXsAgreementListMasterId.getId());
			//审批状态调整为通过
			scmXsAgreementListMasterId.setApprovalState("2");
			scmXsAgreementListMasterId.setIsEffect("1");
			scmXsAgreementListMasterDao.update(scmXsAgreementListMasterId);	
		} else if ("agree".equals(actionName)) {
		} else if ("reject".equals(actionName)) {
		} else if ("backToStart".equals(actionName)) {
		} else if ("opposeTrans".equals(actionName)) {
		} else if ("endProcess".equals(actionName)) {
			scmXsAgreementListMasterId.setApprovalState("3");
			scmXsAgreementListMasterId.setIsEffect("0");
			scmXsAgreementListMasterDao.update(scmXsAgreementListMasterId);	
		}
	}

	@Transactional
	@Override
	public void sendApply(ScmXsAgreementListMaster scmXsAgreementListMaster) {
		// TODO Auto-generated method stub
		//修改主表申请状态
		scmXsAgreementListMaster.setApprovalState("1");
		//是否失效。未通过审批都为失效
		scmXsAgreementListMaster.setIsEffect("0");
		ScmXsAgreementListMaster scmXsAgreementListMasterById = scmXsAgreementListMasterDao.get(scmXsAgreementListMaster.getId());
		StartFlowParam startFlowParam = new StartFlowParam("syxyhzmdsq", "SCM", "approvalId");
		startFlowParam.setFormType("frame");
		CustomStartResult customStartResult = null;
		try {
			System.out.println("发起商业协议合作名单申请");
			customStartResult = bpmRuntimeFeignService.customStart(startFlowParam);
			String approvalId = customStartResult.getInstId();
			scmXsAgreementListMaster.setApprovalId(approvalId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("启动工作流失败");
		}
		if (scmXsAgreementListMasterById == null) {	
			create(scmXsAgreementListMaster);
		}else{
			//有数据,先判断是否已经有正在审核中的数据
			if ("1".equals(scmXsAgreementListMasterById.getApprovalState())) {
				//数据在审批中,不可修改编辑
				throw new RuntimeException("当前数据已经在审批中,不可重复提交");
			}else{
				//修改主表，删除从表数据
				update(scmXsAgreementListMaster);
			}
		}

		
	}
	
}
