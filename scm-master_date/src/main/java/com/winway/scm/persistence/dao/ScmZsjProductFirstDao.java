package com.winway.scm.persistence.dao;
import java.util.List;
import java.util.Map;

import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmZsjProductFirst;

/**
 * 
 * <pre> 
 * 描述：商品首营记录表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmZsjProductFirstDao extends MyBatisDao<String, ScmZsjProductFirst> {


	List<ScmZsjProductFirst> isSave(ScmZsjProductFirst scmZsjProductFirst);

	ScmZsjProductFirst getProductFirstByApprovalId(String approvalId);

	List<ScmZsjProductFirst> getByMainId(String id);

    List<ScmZsjProductFirst> getByProId(String productId);

    Map printByMainId(String productFirstId);

}
