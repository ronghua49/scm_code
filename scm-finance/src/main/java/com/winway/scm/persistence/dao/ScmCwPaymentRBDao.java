package com.winway.scm.persistence.dao;
import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmCwPaymentRB;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * <pre> 
 * 描述：经销商RB支付申请总表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-09 18:18:05
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmCwPaymentRBDao extends MyBatisDao<String, ScmCwPaymentRB> {
    ScmCwPaymentRB getByParam(@Param(value = "ownerId") String ownerId,@Param(value = "businessDivisionId") String businessDivisionId, @Param(value = "provinceId") String provinceId, @Param(value = "year1") String year1, @Param(value = "section") String section);

    ScmCwPaymentRB getByApprovalState(String approvalId);
}
