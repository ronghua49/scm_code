package com.winway.scm.persistence.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.model.ScmZsjCommerceBlackList;
import com.winway.scm.persistence.dao.ScmZsjCommerceBlackListDao;
import com.winway.scm.persistence.manager.ScmZsjCommerceBlackListManager;

/**
 * 
 * <pre> 
 * 描述：商业黑名单表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-18 14:36:01
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmZsjCommerceBlackListManager")
public class ScmZsjCommerceBlackListManagerImpl extends AbstractManagerImpl<String, ScmZsjCommerceBlackList> implements ScmZsjCommerceBlackListManager{
	@Resource
	ScmZsjCommerceBlackListDao scmZsjCommerceBlackListDao;
	@Override
	protected MyBatisDao<String, ScmZsjCommerceBlackList> getDao() {
		return scmZsjCommerceBlackListDao;
	}
	@Override
	public boolean getCommerceIsBlack(String id, String ownerId) {
		List<ScmZsjCommerceBlackList> scmZsjCommerceBlackList = scmZsjCommerceBlackListDao.getCommerceIsBlack(id,ownerId);
		if(scmZsjCommerceBlackList.size() == 0) {
			return false;
		}else{
			return true;
		}
	}

	@Override
	public void endApply(JsonNode jsonNode) {
		String approvalId = jsonNode.get("instId").asText();
		ScmZsjCommerceBlackList scmZsjCommerceBlackList=scmZsjCommerceBlackListDao.getScmZsjCommerceBlackListByApprovalId(approvalId);
		if (scmZsjCommerceBlackList == null) {
			throw new RuntimeException("未查询到业务数据,处理异常");
		}
		scmZsjCommerceBlackList.setApprovalState("2");
		scmZsjCommerceBlackListDao.update(scmZsjCommerceBlackList);
	}
	@Override
	public ScmZsjCommerceBlackList getByApplId(String applyId) {
		return scmZsjCommerceBlackListDao.getByApplId(applyId);
	}
}
