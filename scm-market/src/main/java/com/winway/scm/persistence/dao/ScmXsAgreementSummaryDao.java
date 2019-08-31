package com.winway.scm.persistence.dao;

import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmXsAgreementSummary;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 描述：省区协议汇总表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:43
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmXsAgreementSummaryDao extends MyBatisDao<String, ScmXsAgreementSummary> {

    ScmXsAgreementSummary getSupplierFirstByApprovalId(String approvalId);

    ScmXsAgreementSummary getByApprovalId(String arg0);

    // 协议变更打印
    //协议生效日期 和 签订日期 和 经销商
    Map printAgreementPart1(@Param(value = "AgreementSummaryId") String AgreementSummaryId, @Param(value = "commerceCode") String commerceCode);

    // 调整前品规
    List<Map> printAgreementPart2(String AgreementSummaryId);

    // 调整后拼柜
    List<Map> printAgreementPart3(String AgreementSummaryId);

    // 调整前付款方式
    List<Map> printAgreementPart4(@Param(value = "AgreementSummaryId") String AgreementSummaryId, @Param(value = "commerceCode") String commerceCode);

    // 调整后付款方式
    List<Map> printAgreementPart5(@Param(value = "AgreementSummaryId") String AgreementSummaryId, @Param(value = "commerceCode") String commerceCode);


}
