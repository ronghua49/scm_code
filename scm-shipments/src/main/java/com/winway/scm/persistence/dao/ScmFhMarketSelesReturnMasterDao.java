package com.winway.scm.persistence.dao;
import java.util.List;
import java.util.Map;

import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmFhMarketSelesReturn;
import com.winway.scm.model.ScmFhMarketSelesReturnMaster;
import com.winway.scm.model.ScmFhShipmentsDatailMaster;
import com.winway.scm.vo.SalesReturnRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.parameters.P;

/**
 * 
 * <pre> 
 * 描述：销售退货主表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-13 15:47:33
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmFhMarketSelesReturnMasterDao extends MyBatisDao<String, ScmFhMarketSelesReturnMaster> {
    ScmFhMarketSelesReturnMaster getByApprovalState(String approvalId);

	List<SalesReturnRecord> salesReturnRecord(Map<String, Object> params);

	ScmFhMarketSelesReturnMaster getByOrderCode(String orderCode);

	// 退货申请表打印
	// 表头和汇总数据
	Map printPart1(@Param(value = "id") String id, @Param(value = "ownerId") String ownerId);
	//退货商品信息
	List<Map> printPart2(String id);

	List<SalesReturnRecord> getSalesBackRecode(Map<String,Object> params);

    List<Map> getSalesBackRecodeAsMap(@Param(value = "params") Map<String,Object> params);

}
