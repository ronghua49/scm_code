package com.winway.scm.persistence.dao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmZdDutyAffirm;
import com.winway.scm.model.ScmZsjCommerce;

/**
 * 
 * <pre> 
 * 描述：商业表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmZsjCommerceDao extends MyBatisDao<String, ScmZsjCommerce> {

	List<ScmZsjCommerce> getByNameOrId(ScmZsjCommerce scmZsjCommerce);

	List<ScmZsjCommerce> getByName(String commerceName);

	List<ScmZsjCommerce> firstList(Map<String, Object> params);

	List<ScmZsjCommerce> downBox(String ownerId);

	List<ScmZsjCommerce> listCommerceAcceptState(Map<String, Object> params);

	List<ScmZsjCommerce> listCommerceAcceptApprova(Map<String, Object> params);

	List<ScmZsjCommerce> listCommerceAcceptTS(Map<String, Object> params);

	List<ScmZsjCommerce> downBoxApplySuccess(String ownerId);

	List<ScmZsjCommerce> agreementSendApplySuccessList(Map<String, Object> params);

	List<String>  getCommerceName();
	
	List<ScmZsjCommerce> findByOwnerId(String ownerId);
}
