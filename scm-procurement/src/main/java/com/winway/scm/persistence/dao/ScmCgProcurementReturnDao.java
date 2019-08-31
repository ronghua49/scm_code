package com.winway.scm.persistence.dao;
import java.util.List;
import java.util.Map;

import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmCgProcurementReturn;
import com.winway.scm.vo.ScmZsjDrogPuchaseReturn;

/**
 * 
 * <pre> 
 * 描述：采购退货表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-26 13:47:44
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
public interface ScmCgProcurementReturnDao extends MyBatisDao<String, ScmCgProcurementReturn> {
    ScmCgProcurementReturn getByApprovalState(String approvalId);

	List<ScmZsjDrogPuchaseReturn> drugPuchaseReturn(Map<String, Object> params);
	
	Map getById(String id);

	ScmCgProcurementReturn getByCode(String orderCode);
}
