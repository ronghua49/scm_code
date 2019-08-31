package com.winway.scm.persistence.manager;

import java.util.Date;

import com.hotent.base.manager.Manager;
import com.winway.scm.model.ScmXsCreditApplyRecord;

/**
 * 
 * <pre> 
 * 描述：Scm_Xs_CreditApplyRecord 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-07-16 11:39:59
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmXsCreditApplyRecordManager extends Manager<String, ScmXsCreditApplyRecord>{

	String getResidueCreditByCommerceCode(String commerceFirstId, String ownerId);
	
	void saveByContractAndReturnMoney(String dataId,double price,String commerceId,String ownerId,String recordType);
	
	void saveByFocusSend(String dataId,double price,String commerceId,String ownerId,Date focusSendDate);
	
}
