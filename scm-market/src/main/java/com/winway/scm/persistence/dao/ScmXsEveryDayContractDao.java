package com.winway.scm.persistence.dao;
import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmXsEveryDayContract;

/**
 * 
 * <pre> 
 * 描述：日常合同 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:44
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmXsEveryDayContractDao extends MyBatisDao<String, ScmXsEveryDayContract> {

    ScmXsEveryDayContract getContractByApprovalId(String approvalId);

	ScmXsEveryDayContract getByApplyId(String applyId);
}
