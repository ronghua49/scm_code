package com.winway.scm.persistence.manager.impl;

import java.util.List;

import javax.annotation.Resource;

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
 * 
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
public class ScmXsCreditCollectManagerImpl extends AbstractManagerImpl<String, ScmXsCreditCollect> implements ScmXsCreditCollectManager{
	@Resource
	ScmXsCreditCollectDao scmXsCreditCollectDao;
	@Resource
	ScmXsCreditApplyDao scmXsCreditApplyDao;
	@Resource
	ScmXsCreditApplyManager scmXsCreditApplyManager;
	@Override
	protected MyBatisDao<String, ScmXsCreditCollect> getDao() {
		return scmXsCreditCollectDao;
	}
	/**
	 * 创建实体包含子表实体
	 */
	public void create(ScmXsCreditCollect scmXsCreditCollect){
		scmXsCreditCollect.setRequestCode(QuarterUtil.getCode(""));
    	super.create(scmXsCreditCollect);
    	String pk=scmXsCreditCollect.getId();
    	
    	List<ScmXsCreditApply> scmXsCreditApplyList=scmXsCreditCollect.getScmXsCreditApplyList();
    	for(ScmXsCreditApply scmXsCreditApply:scmXsCreditApplyList){
    		scmXsCreditApply.setCreditCollectId(pk);
    		scmXsCreditApplyManager.create(scmXsCreditApply);
    	}
    }
	
	/**
	 * 删除记录包含子表记录
	 */
	public void remove(String entityId){
		super.remove(entityId);
    	scmXsCreditApplyDao.delByMainId(entityId);
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
    public ScmXsCreditCollect get(String entityId){
    	ScmXsCreditCollect scmXsCreditCollect=super.get(entityId);
    	List<ScmXsCreditApply> scmXsCreditApplyList=scmXsCreditApplyDao.getByMainId(entityId);
    	scmXsCreditCollect.setScmXsCreditApplyList(scmXsCreditApplyList);
    	return scmXsCreditCollect;
    }
    
    /**
     * 更新实体同时更新子表记录
     */
    public void update(ScmXsCreditCollect scmXsCreditCollect){
    	super.update(scmXsCreditCollect);
    	String id=scmXsCreditCollect.getId();
    	scmXsCreditApplyDao.delByMainId(id);
    	List<ScmXsCreditApply> scmXsCreditApplyList=scmXsCreditCollect.getScmXsCreditApplyList();
    	for(ScmXsCreditApply scmXsCreditApply:scmXsCreditApplyList){
    		scmXsCreditApply.setCreditCollectId(id);
    		scmXsCreditApplyManager.create(scmXsCreditApply);
    	}
    }
	@Override
	public void sendApply(ScmXsCreditCollect scmXsCreditCollect) {
		scmXsCreditCollect.setApprovalState("1");
		//根据ID查询是否存在数据，如果存在，表示已有暂存过，没有表示未暂存过
		ScmXsCreditCollect scmXsCreditCollect1 = scmXsCreditCollectDao.get(scmXsCreditCollect.getId());
		if(scmXsCreditCollect1 == null) {
			create(scmXsCreditCollect);
		}else{
			update(scmXsCreditCollect);
		}
	}
	@Override
	public void endApply(JsonNode jsonNode) {
		String approvalId = jsonNode.get("instId").asText();
		String actionName = jsonNode.get("actionName").asText();
		ScmXsCreditCollect byApprovalId = scmXsCreditCollectDao.getByApprovalId(approvalId);
		if ("agree".equals(actionName)) {			
			byApprovalId.setApprovalState("2");
		}else if("oppose".equals(actionName)){
			byApprovalId.setApprovalState("3");
		}
		scmXsCreditCollectDao.update(byApprovalId);
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
