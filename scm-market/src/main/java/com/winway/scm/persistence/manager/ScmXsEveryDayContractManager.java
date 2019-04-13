package com.winway.scm.persistence.manager;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.manager.Manager;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.winway.scm.model.ScmXsEveryDayContract;
import com.winway.scm.vo.ScmXsBigContractProductByUpdateVo;

import java.text.ParseException;
import java.util.List;

/**
 * 
 * <pre> 
 * 描述：日常合同 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:44
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmXsEveryDayContractManager extends Manager<String, ScmXsEveryDayContract>{

    void sendApply(ScmXsEveryDayContract scmXsEveryDayContract);

    String save(ScmXsEveryDayContract scmXsEveryDayContract);

    void endApply(JsonNode jsonNode) throws ParseException;

    List<ScmXsBigContractProductByUpdateVo> getAllProListByContractId(String id, String agreementSummaryId );

	ScmXsEveryDayContract getByApplyId(String applyId);
}
