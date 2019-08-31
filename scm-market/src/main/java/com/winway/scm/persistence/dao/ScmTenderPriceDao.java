package com.winway.scm.persistence.dao;

import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmTenderPrice;
import com.winway.scm.vo.ScmTenderPriceVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 描述：中标价管理 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-06-25 11:09:05
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmTenderPriceDao extends MyBatisDao<String, ScmTenderPrice> {
    /**
     * 根据外键获取子表明细列表
     *
     * @param tenderProjectCode
     * @return
     */
    List<ScmTenderPrice> getByMainId(String tenderProjectCode);

    /**
     * 根据外键删除子表记录
     *
     * @param tenderProjectCode
     * @return
     */
    void delByMainId(String tenderProjectCode);

    /**
     * 根据省区及品规编码查询是否中标
     *
     * @param provinceCode
     * @param prodCode
     * @return
     */
    List<ScmTenderPrice> getScmTenderPriceByCode(@Param(value = "provinceCode") String provinceCode, @Param(value = "prodCode") String prodCode);


    /**
     * 根据流程ID获取中标价列表
     *
     * @param flowId
     * @return
     */
    List<ScmTenderPrice> getByFlowId(String flowId);


    /**
     * 根据collectId获取中标价详情
     *
     * @param collectId
     * @return
     */
    List<ScmTenderPriceVo> queryByCollectId(String collectId);

    /**
     * 查询中标价详情
     *
     * @param params
     * @return
     */
    List<ScmTenderPriceVo> queryAllInfo(Map<String, Object> params);

    /**
     * 根据flowId获取中标价详情
     *
     * @param flowId
     * @return
     */
    List<ScmTenderPriceVo> queryByFlowId(String flowId);
}
