package com.winway.scm.persistence.dao;
import java.util.List;
import java.util.Map;

import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.query.PageList;
import com.winway.scm.model.ScmZsjCommerceAcceptState;

/**
 * 
 * <pre> 
 * 描述：商业认可状态表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-18 14:36:01
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmZsjCommerceAcceptStateDao extends MyBatisDao<String, ScmZsjCommerceAcceptState> {

	List<ScmZsjCommerceAcceptState> firstList(Map<String, Object> params);

	void deleteByMainId(String id);

	List<ScmZsjCommerceAcceptState> getByMainId(String id);

	void updateIsEffectTo0(String commerceId);

    ScmZsjCommerceAcceptState getLastByCommerceId(String commerceId);

}
