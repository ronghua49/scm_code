package com.winway.scm.persistence.manager;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.manager.Manager;
import com.hotent.base.query.PageList;
import com.winway.purchase.vo.ScmWmsReceipt;
import com.winway.scm.model.ScmFhGradeSelesReturn;
import com.winway.scm.model.ScmFhGradeSelesReturnMaster;
import com.winway.scm.vo.GradeSelesReturnUpdateVo;

import java.text.ParseException;

/**
 * 
 * <pre> 
 * 描述：当次退货主表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-13 15:47:33
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmFhGradeSelesReturnMasterManager extends Manager<String, ScmFhGradeSelesReturnMaster>{
    @Override
    void create(ScmFhGradeSelesReturnMaster scmFhGradeSelesReturnMaster);

    ScmFhGradeSelesReturnMaster sendApply(ScmFhGradeSelesReturnMaster scmFhGradeSelesReturnMaster);

    void endApply(JsonNode jsonNode) throws ParseException;

    GradeSelesReturnUpdateVo getUpdateInfo(String id);

    GradeSelesReturnUpdateVo getDatailByApprovalId(String approvalId);

	void receipt(ScmWmsReceipt scmWmsReceipt);

	PageList<ScmFhGradeSelesReturn> gradeSelesReturnDatail(String orderCode);
}
