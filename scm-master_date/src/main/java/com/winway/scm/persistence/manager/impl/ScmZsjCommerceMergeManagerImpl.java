package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.base.Strings;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.model.ScmZsjCommerceMerge;
import com.winway.scm.persistence.dao.ScmZsjCommerceMergeDao;
import com.winway.scm.persistence.manager.ScmZsjCommerceMergeManager;

/**
 * 
 * <pre> 
 * 描述：商业合并表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-18 14:36:01
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmZsjCommerceMergeManager")
public class ScmZsjCommerceMergeManagerImpl extends AbstractManagerImpl<String, ScmZsjCommerceMerge> implements ScmZsjCommerceMergeManager{
	@Resource
	ScmZsjCommerceMergeDao scmZsjCommerceMergeDao;
	@Override
	protected MyBatisDao<String, ScmZsjCommerceMerge> getDao() {
		return scmZsjCommerceMergeDao;
	}
	@Override
	public void endApply(JsonNode jsonNode) {
		String approvalId = jsonNode.get("instId").asText();
		String actionName = jsonNode.get("actionName").asText();
		ScmZsjCommerceMerge scmZsjCommerceMerge=scmZsjCommerceMergeDao.getCommerceFirstByApprovalId(approvalId);
		if(scmZsjCommerceMerge == null){
			throw new RuntimeException("未查询到业务数据,处理异常");
		}
		if("agree".equals(actionName)){
			scmZsjCommerceMerge.setApprovalState("2");
			scmZsjCommerceMergeDao.update(scmZsjCommerceMerge);
		}else if("oppose".equals(actionName) || StringUtils.isEmpty(actionName)){
			scmZsjCommerceMerge.setApprovalState("3");
			scmZsjCommerceMergeDao.update(scmZsjCommerceMerge);
		}
	}
}
