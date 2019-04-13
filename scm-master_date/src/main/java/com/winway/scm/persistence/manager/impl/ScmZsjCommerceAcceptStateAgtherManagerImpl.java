package com.winway.scm.persistence.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.purchase.util.QuarterUtil;
import com.winway.scm.model.ScmZsjCommerceAcceptState;
import com.winway.scm.model.ScmZsjCommerceAcceptStateAgther;
import com.winway.scm.persistence.dao.ScmZsjCommerceAcceptStateAgtherDao;
import com.winway.scm.persistence.dao.ScmZsjCommerceAcceptStateDao;
import com.winway.scm.persistence.manager.ScmZsjCommerceAcceptStateAgtherManager;
import com.winway.scm.persistence.manager.ScmZsjCommerceAcceptStateManager;


/**
 * 
 * <pre> 
 * 描述：商业认可状态总表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-18 14:36:01
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmZsjCommerceAcceptStateAgtherManager")
public class ScmZsjCommerceAcceptStateAgtherManagerImpl extends AbstractManagerImpl<String, ScmZsjCommerceAcceptStateAgther> implements ScmZsjCommerceAcceptStateAgtherManager{
	@Resource
	ScmZsjCommerceAcceptStateAgtherDao scmZsjCommerceAcceptStateAgtherDao;
	
	@Resource
	ScmZsjCommerceAcceptStateDao scmZsjCommerceAcceptStateDao;
	
	@Resource
	ScmZsjCommerceAcceptStateManager scmZsjCommerceAcceptStateManager;
	
	@Override
	protected MyBatisDao<String, ScmZsjCommerceAcceptStateAgther> getDao() {
		return scmZsjCommerceAcceptStateAgtherDao;
	}
	
	@Override	
	public void create(ScmZsjCommerceAcceptStateAgther arg0) {
		super.create(arg0);
		String id = arg0.getId();
		List<ScmZsjCommerceAcceptState> commerceAcceptStateList = arg0.getCommerceAcceptStateList();
		for (ScmZsjCommerceAcceptState scmZsjCommerceAcceptState : commerceAcceptStateList) {
			scmZsjCommerceAcceptState.setAgtherId(id);
			scmZsjCommerceAcceptState.setIsEffect("0");
			scmZsjCommerceAcceptStateManager.create(scmZsjCommerceAcceptState);
		}
	}
	@Override
	public void update(ScmZsjCommerceAcceptStateAgther arg0) {
		ScmZsjCommerceAcceptStateAgther scmZsjCommerceAcceptStateAgther2 = scmZsjCommerceAcceptStateAgtherDao.get(arg0.getId());
		arg0.setApprovalCode(scmZsjCommerceAcceptStateAgther2.getApprovalCode());
		super.update(arg0);
		String id = arg0.getId();
		scmZsjCommerceAcceptStateDao.deleteByMainId(id);
		List<ScmZsjCommerceAcceptState> commerceAcceptStateList = arg0.getCommerceAcceptStateList();
		for (ScmZsjCommerceAcceptState scmZsjCommerceAcceptState : commerceAcceptStateList) {
			scmZsjCommerceAcceptState.setAgtherId(id);
			scmZsjCommerceAcceptStateManager.create(scmZsjCommerceAcceptState);
		}
	}

	@Override
	public void sendApply(ScmZsjCommerceAcceptStateAgther scmZsjCommerceAcceptStateAgther) {
		ScmZsjCommerceAcceptStateAgther scmZsjCommerceAcceptStateAgther2 = scmZsjCommerceAcceptStateAgtherDao.get(scmZsjCommerceAcceptStateAgther.getId());
		if(scmZsjCommerceAcceptStateAgther2 == null) {
			//没有 创建
			//生成单号
			scmZsjCommerceAcceptStateAgther.setApprovalCode(QuarterUtil.getCode("SYRK"));
			create(scmZsjCommerceAcceptStateAgther);
		}else{
			//有 更新
			scmZsjCommerceAcceptStateAgther.setApprovalCode(scmZsjCommerceAcceptStateAgther2.getApprovalCode());
			update(scmZsjCommerceAcceptStateAgther);
		}
	}

	@Override
	public void endApply(JsonNode jsonNode) {
		String approvalId = jsonNode.get("instId").asText();
		ScmZsjCommerceAcceptStateAgther scmZsjCommerceAcceptStateAgther = scmZsjCommerceAcceptStateAgtherDao.getByApprovalId(approvalId);
		if(scmZsjCommerceAcceptStateAgther == null) {
			throw new RuntimeException("数据不存在");
		}
		List<ScmZsjCommerceAcceptState> list =  scmZsjCommerceAcceptStateDao.getByMainId(scmZsjCommerceAcceptStateAgther.getId());
		//查询到认可列表,并把历史数据调整为失效
		for (ScmZsjCommerceAcceptState scmZsjCommerceAcceptState : list) {
			scmZsjCommerceAcceptStateDao.updateIsEffectTo0(scmZsjCommerceAcceptState.getCommerceId());
			scmZsjCommerceAcceptState.setIsEffect("1");
			scmZsjCommerceAcceptStateDao.update(scmZsjCommerceAcceptState);
		}
		
		scmZsjCommerceAcceptStateAgther.setApprovalState("2");
		scmZsjCommerceAcceptStateAgtherDao.update(scmZsjCommerceAcceptStateAgther);
	}
}
