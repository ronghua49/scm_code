package com.winway.scm.persistence.dao;
import java.util.List;
import java.util.Map;

import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmXsDealerClause;

/**
 * 
 * <pre> 
 * 描述：经销商协议合作条款表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:44
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmXsDealerClauseDao extends MyBatisDao<String, ScmXsDealerClause> {

	List<ScmXsDealerClause> commerceList(String org0, String org1);

	List<ScmXsDealerClause> firstQuery(Map<String, Object> params);

	List<ScmXsDealerClause> getYears(String org0);
	
	void delByMainId(String arg0);

	ScmXsDealerClause lastPaymentType(String arg0, String arg1, String arg2,String arg3);

	List<ScmXsDealerClause> getByMasterId(String arg0);
}
