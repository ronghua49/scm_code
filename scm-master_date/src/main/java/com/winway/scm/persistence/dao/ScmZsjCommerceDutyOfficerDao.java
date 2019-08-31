package com.winway.scm.persistence.dao;
import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmZsjCommerceDutyOfficer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * <pre> 
 * 描述：商业责任人表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-18 14:36:01
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmZsjCommerceDutyOfficerDao extends MyBatisDao<String, ScmZsjCommerceDutyOfficer> {

	List<ScmZsjCommerceDutyOfficer> getByApprovalId(String approvalId);

    ScmZsjCommerceDutyOfficer getApprovalDublicate(@Param(value = "commerceId") String commerceId);

    List<ScmZsjCommerceDutyOfficer> getByCommerceId(String commerceId);

    ScmZsjCommerceDutyOfficer getlastDutyOfficer(String commerceId);
}
