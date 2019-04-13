package com.winway.scm.persistence.manager.impl;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.model.CommonResult;
import com.hotent.base.util.JsonUtil;
import com.hotent.base.util.StringUtil;
import com.winway.scm.persistence.dao.ScmZsjCommerceDutyOfficerDao;
import com.winway.scm.model.ScmZsjCommerceDutyOfficer;
import com.winway.scm.model.ScmZsjCommerceFirst;
import com.winway.scm.persistence.manager.ScmZsjCommerceDutyOfficerManager;

/**
 * 
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
public class ScmZsjCommerceDutyOfficerManagerImpl extends AbstractManagerImpl<String, ScmZsjCommerceDutyOfficer> implements ScmZsjCommerceDutyOfficerManager{
	@Resource
	ScmZsjCommerceDutyOfficerDao scmZsjCommerceDutyOfficerDao;
	@Override
	protected MyBatisDao<String, ScmZsjCommerceDutyOfficer> getDao() {
		return scmZsjCommerceDutyOfficerDao;
	}
	@Override
	public ScmZsjCommerceDutyOfficer getByApprovalId(String approvalId) {
		ScmZsjCommerceDutyOfficer zsjCommerceDutyOfficer = scmZsjCommerceDutyOfficerDao.getByApprovalId(approvalId);
		return zsjCommerceDutyOfficer;
	}
	@Override
	public void sendApply(ScmZsjCommerceDutyOfficer scmZsjCommerceDutyOfficer) {
		scmZsjCommerceDutyOfficer.setApprovalState("1");
	if(StringUtils.isBlank(scmZsjCommerceDutyOfficer.getId())) {
		create(scmZsjCommerceDutyOfficer);
		return;
	}
		ScmZsjCommerceDutyOfficer scmZsjCommerceDutyOfficer2 = get(scmZsjCommerceDutyOfficer.getId());
		if(scmZsjCommerceDutyOfficer2 == null) {
			create(scmZsjCommerceDutyOfficer);
		}
		if(scmZsjCommerceDutyOfficer2 != null) {
			update(scmZsjCommerceDutyOfficer);
		}
				
	}
	@Override
	public void endApply(String params) throws IOException {
		JsonNode jsonNode = JsonUtil.toJsonNode(params);
    	String approvalId = jsonNode.get("instId").textValue();
    	 String actionName=jsonNode.get("actionName").textValue();
    	if(StringUtils.isNotBlank(approvalId)) {
    		ScmZsjCommerceDutyOfficer scmZsjCommerceDutyOfficer =getByApprovalId(approvalId);
    		if(scmZsjCommerceDutyOfficer!=null) {
    			if(StringUtils.isNotBlank(actionName)) {
    				if(actionName.equals("agree")) {
    					scmZsjCommerceDutyOfficer.setApprovalState("2");
    				}
    				if(actionName.equals("oppose")) {
    					scmZsjCommerceDutyOfficer.setApprovalState("3");
    				}
    				
    				
    				
    			}
    			update(scmZsjCommerceDutyOfficer);
    		}
    		
    	}
    	
		
	}
	@Override
	public void createCache(ScmZsjCommerceDutyOfficer scmZsjCommerceDutyOfficer) {
		scmZsjCommerceDutyOfficer.setApprovalState("0");
		if(StringUtil.isEmpty(scmZsjCommerceDutyOfficer.getId())){
			create(scmZsjCommerceDutyOfficer);
		}else{
			ScmZsjCommerceDutyOfficer scmZsjCommerceDutyOfficer2 = get(scmZsjCommerceDutyOfficer.getId());
				if(scmZsjCommerceDutyOfficer2==null) {
					create(scmZsjCommerceDutyOfficer);
				}else {
					update(scmZsjCommerceDutyOfficer);
				}
		}
			
			
		
		
		}		
	
	
	
	
}
