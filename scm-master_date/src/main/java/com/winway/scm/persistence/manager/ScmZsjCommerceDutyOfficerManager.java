package com.winway.scm.persistence.manager;

import java.io.IOException;

import com.hotent.base.manager.Manager;
import com.hotent.base.model.CommonResult;
import com.winway.scm.model.ScmZsjCommerceDutyOfficer;
import com.winway.scm.model.ScmZsjCommerceFirst;

/**
 * 
 * <pre> 
 * 描述：商业责任人表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-18 14:36:01
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmZsjCommerceDutyOfficerManager extends Manager<String, ScmZsjCommerceDutyOfficer>{

	ScmZsjCommerceDutyOfficer getByApprovalId(String approvalId);

	void sendApply(ScmZsjCommerceDutyOfficer scmZsjCommerceDutyOfficer);

	void endApply(String params) throws IOException;

	void createCache(ScmZsjCommerceDutyOfficer scmZsjCommerceDutyOfficer);
	
}
