package com.winway.scm.persistence.dao;

import java.util.List;

import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmZsjAchage;
import com.winway.scm.model.ScmZsjCommerceAcceptStateAgther;

/**
 * 
 * <pre> 
 * 描述：件装量 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-18 14:36:01
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmZsjAchageDao extends MyBatisDao<String, ScmZsjAchage>{

	List<ScmZsjAchage> getByMainId(String arg0);

	List<ScmZsjAchage> getByachage(String arg0, String arg1);

}
