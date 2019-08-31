package com.winway.scm.persistence.dao;
import java.util.List;
import java.util.Map;

import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmZdDutyAffirm;
import com.winway.scm.model.ScmZsjCommerce;
import com.winway.scm.model.ScmZsjSupplier;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * <pre> 
 * 描述：供应商表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmZsjSupplierDao extends MyBatisDao<String, ScmZsjSupplier> {

	List<ScmZdDutyAffirm> getByNameOrId(ScmZsjSupplier scmZsjSupplier);

	List<ScmZsjCommerce> getByName(String supplierName);

	List<ScmZsjSupplier> firstList(Map<String, Object> params);

	List<ScmZsjSupplier> downBox(String ownerId);

	List<ScmZsjSupplier> downBoxApplySuccess(@Param(value = "ownerId") String ownerId,@Param(value = "managementScope") String managementScope);

	List<ScmZsjSupplier> getByMainId(String id);

	ScmZsjSupplier getByApprovalId(String approvalId);

	String getSupplierCode();
}
