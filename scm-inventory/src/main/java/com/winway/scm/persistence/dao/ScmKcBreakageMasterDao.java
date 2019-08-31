package com.winway.scm.persistence.dao;
import java.util.List;
import java.util.Map;

import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmKcBreakageMaster;
import com.winway.scm.vo.BreakageRecord;

/**
 * 
 * <pre> 
 * 描述：不合格产品报损主表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-23 17:00:46
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmKcBreakageMasterDao extends MyBatisDao<String, ScmKcBreakageMaster> {
    ScmKcBreakageMaster getByApprovalState(String approvalId);

	List<BreakageRecord> breakageRecord(Map<String, Object> params);

	ScmKcBreakageMaster getByOrderCode(String orderCode);
}
