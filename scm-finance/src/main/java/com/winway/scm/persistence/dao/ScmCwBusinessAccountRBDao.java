package com.winway.scm.persistence.dao;
import java.util.List;

import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmCwBusinessAccountRB;
import com.winway.scm.vo.ScmCwBlendDetailVo;
import com.winway.scm.vo.ScmCwSendProductVo;

import java.util.List;
import java.util.Map;

/**
 * 
 * <pre> 
 * 描述：经销商RB核算总表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-09 18:18:05
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmCwBusinessAccountRBDao extends MyBatisDao<String, ScmCwBusinessAccountRB> {
    List<ScmCwBusinessAccountRB> queryByParamMap(Map<String, Object> map);

	List<ScmCwBlendDetailVo> ListBlendDetail(String ownerId, String startDate, String endDate, String string, String string2);

	List<ScmCwBusinessAccountRB> getByOwnerIdAndYearAndQuarter(String ownerId, String year, String quarter, String string, String string2);

	ScmCwBusinessAccountRB getByApprovalId(String approvalId);

	List<ScmCwSendProductVo> ListSendProduct(String ownerId, String startDate, String endDate, String string, String string2);
}
