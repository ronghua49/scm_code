package com.winway.scm.persistence.manager;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.manager.Manager;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.winway.scm.model.ScmTenderPrice;
import com.winway.scm.vo.ScmTenderPriceVo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;


/**
 * <pre>
 * 描述：中标价管理 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-06-25 11:09:05
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmTenderPriceManager extends Manager<String, ScmTenderPrice> {


    List<ScmTenderPrice> getScmTenderPriceByCode(String provinceCode, String prodCode);

    /**
     * 根据collectId获取中标价详情列表
     *
     * @param collectId
     * @return
     */
    PageList<ScmTenderPriceVo> getByCollectId(String collectId);

    /**
     * 根据flowId获取中标价详情列表
     *
     * @param flowId
     * @return
     */
    PageList<ScmTenderPriceVo> getByFlowId(String flowId);

    /**
     * 中标价流程申请回调接口
     *
     * @param jsonNode
     */
    void endApply(JsonNode jsonNode);

    /**
     * 中标价申请插入接口（批量插入多条中标商品记录）
     *
     * @param scmTenderPrices
     */
    void batchCreate(List<ScmTenderPrice> scmTenderPrices);

    /**
     * 导出查询出的所有结果
     *
     * @param queryFilter
     * @return
     */
    HSSFWorkbook export(QueryFilter queryFilter);

    /**
     * 查询中标价详情列表
     *
     * @param queryFilter
     * @return
     */
    PageList<ScmTenderPriceVo> queryAllInfo(QueryFilter queryFilter);

    byte[] getExportData(QueryFilter queryFilter);
}
