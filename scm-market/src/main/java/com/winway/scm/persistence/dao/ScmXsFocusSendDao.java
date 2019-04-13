package com.winway.scm.persistence.dao;
import java.util.List;
import java.util.Map;

import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmXsFocusSend;

/**
 * 
 * <pre> 
 * 描述：集中发货合同 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:44
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmXsFocusSendDao extends MyBatisDao<String, ScmXsFocusSend> {

	ScmXsFocusSend getByApprovalId(String approvalId);

	List<Map<String,Object>> getCommerceMessageByCodeAndOwnerId(String commerceCode, String ownerId);
}
