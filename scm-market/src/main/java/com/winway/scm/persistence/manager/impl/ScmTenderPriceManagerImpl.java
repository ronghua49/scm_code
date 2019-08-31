package com.winway.scm.persistence.manager.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.pagehelper.PageHelper;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.exception.SystemException;
import com.hotent.base.feign.BpmRuntimeFeignService;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.modelvo.CustomStartResult;
import com.hotent.base.modelvo.StartFlowParam;
import com.hotent.base.query.FieldSort;
import com.hotent.base.query.PageBean;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.BeanUtils;
import com.winway.scm.model.ScmTenderPrice;
import com.winway.scm.persistence.dao.ScmTenderPriceDao;
import com.winway.scm.persistence.manager.ScmTenderPriceManager;
import com.winway.scm.util.ExcelUtils;
import com.winway.scm.vo.ScmTenderPriceVo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <pre>
 * 描述：中标价管理 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-06-25 11:09:05
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmTenderPriceManager")
public class ScmTenderPriceManagerImpl extends AbstractManagerImpl<String, ScmTenderPrice> implements ScmTenderPriceManager {

    private Logger logger = LoggerFactory.getLogger(ScmTenderProjectManagerImpl.class);

    @Resource
    ScmTenderPriceDao scmTenderPriceDao;

    @Resource
    BpmRuntimeFeignService bpmRuntimeFeignService;

    @Override
    protected MyBatisDao<String, ScmTenderPrice> getDao() {
        return scmTenderPriceDao;
    }

    /**
     * 根据省份Code和商品Code获取中标价列表
     *
     * @param provinceCode
     * @param prodCode
     * @return
     */
    @Override
    public List<ScmTenderPrice> getScmTenderPriceByCode(String provinceCode, String prodCode) {
        return scmTenderPriceDao.getScmTenderPriceByCode(provinceCode, prodCode);
    }

    /**
     * 逻辑删除，标志记录为删除
     *
     * @param id
     */
    @Override
    public void remove(String id) {
        ScmTenderPrice scmTenderPrice = new ScmTenderPrice();
        scmTenderPrice.setIsDelete(1);
        this.update(scmTenderPrice);
    }

    /**
     * 批量逻辑删除
     *
     * @param ids
     */
    @Override
    public void removeByIds(String... ids) {
        for (String id : ids) {
            this.remove(id);
        }
    }

    /**
     * 根据CollectId查询中标价列表
     *
     * @param collectId
     * @return
     */
    @Override
    public PageList<ScmTenderPriceVo> getByCollectId(String collectId) {

        List<ScmTenderPriceVo> scmTenderPrices = scmTenderPriceDao.queryByCollectId(collectId);
        return new PageList<>(scmTenderPrices);
    }

    @Override
    public PageList<ScmTenderPriceVo> getByFlowId(String flowId) {
        List<ScmTenderPriceVo> scmTenderPrices = scmTenderPriceDao.queryByFlowId(flowId);
        return new PageList<>(scmTenderPrices);
    }

    /**
     * 批量插入中标价条目（主要用于中标价申请页面，提交申请时会插入多条记录）
     *
     * @param scmTenderPrices
     */
    @Override
    public void batchCreate(List<ScmTenderPrice> scmTenderPrices) {
        // shu ju ku bu shi wo she ji de
        QueryFilter queryFilter = QueryFilter
                .build().withDefaultPage()
                .withSorter(FieldSort.create("collect_id", "DESC"));
        List<ScmTenderPrice> scmTenderPriceList = scmTenderPriceDao.query(queryFilter.getParams());
        // 聚合Id是用于关联批量插入的数据，以便于查询时可以关联的获取
        String collectId;

        // 宏天BeanUtils.isNotEmpty 判断list为空存在bug
        if (scmTenderPriceList == null || scmTenderPriceList.size() == 0) {
            // 数据库表不存在数据，即将当前collectId置为1
            collectId = "1";
        } else {
            String lastCollectId = scmTenderPriceList.get(0).getCollectId();
            collectId = String.valueOf(Integer.parseInt(lastCollectId) + 1);
        }
        // 给所有项目设置collectId
        scmTenderPrices.forEach(e -> e.setCollectId(collectId));

        StartFlowParam startFlowParam = new StartFlowParam("zbjsq", "SCM", "flowId");
        startFlowParam.setFormType("frame");

        // 启动工作流
        CustomStartResult customStartResult;
        try {
            customStartResult = bpmRuntimeFeignService.customStart(startFlowParam);
            if (customStartResult.getState()) {
                // 启动成功则更新流程状态和流程Id
                String flowId = customStartResult.getInstId();
                scmTenderPrices.forEach(e -> {
                    e.setFlowId(flowId);
                    e.setFlowStatus(2);//2=审批中
                });
            }
        } catch (Exception e) {
            logger.debug(String.valueOf(e));
        }
        // 更新数据库表
        scmTenderPrices.forEach(this::create);
    }

    /**
     * 查询中标价详情（包括商品/招标详情）
     *
     * @param queryFilter
     * @return
     */
    @Override
    public PageList<ScmTenderPriceVo> queryAllInfo(QueryFilter queryFilter) {
        if (BeanUtils.isEmpty(queryFilter)) {
            throw new SystemException("QueryFilter通用查询对象不能为空.");
        }
        PageBean pageBean = queryFilter.getPageBean();
        if (BeanUtils.isEmpty(pageBean)) {
            PageHelper.startPage(1, 1000, true);
        } else {
            PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
        }

        List<ScmTenderPriceVo> query = scmTenderPriceDao.queryAllInfo(queryFilter.getParams());
        return new PageList<>(query);

    }

    @Override
    public byte[] getExportData(QueryFilter queryFilter) {
        return this.export(queryFilter).getBytes();
    }


    /**
     * 根据查询结果导出数据（全部）
     *
     * @param queryFilter
     * @return
     */
    @Override
    public HSSFWorkbook export(QueryFilter queryFilter) {
        if (BeanUtils.isEmpty(queryFilter)) {
            throw new SystemException("QueryFilter通用查询对象不能为空.");
        }
        // 配置为查询所有信息
        PageHelper.startPage(1, Integer.MAX_VALUE, true);
        queryFilter.withSorter(FieldSort.create("collect_id", "DESC"));

        List<ScmTenderPriceVo> rows = scmTenderPriceDao.queryAllInfo(queryFilter.getParams());

        String[] columnNames = {
                "审批状态", "省区", "地市", "区县", "医院编码", "医院名称",
                "招标全称", "招标年份", "项目启动时间", "项目计划失效时间",
                "执行状态", "中标价生效时间", "中标价失效时间",
                "商品名", "通用名", "品规", "剂型", "规格", "包装单位",
                "转换系数",
                "投标价", "本次中标价格", "中标名称", "中标分类",
                "质量层次", "生产企业", "投标企业",
                "备注"
        };
        // 将中标价记录转换为List<List<String>>列表
        List<List<String>> data = rows.stream()
                .map(this::castScmTenderPrice2List)
                .collect(Collectors.toList());
        HSSFWorkbook workbook = ExcelUtils.generateExcel(data, "中标价详情", columnNames);

        return workbook;
    }

    /**
     * 流程申请回调接口（回调时批量修改中标价条目的审批状态）
     *
     * @param jsonNode
     */
    @Override
    public void endApply(JsonNode jsonNode) {
        String flowId = jsonNode.get("instId").asText();
        String actionName = jsonNode.get("actionName").asText();

        List<ScmTenderPrice> lists = scmTenderPriceDao.getByFlowId(flowId);
        if (lists == null || lists.size() == 0) {
            logger.debug("工作流数据对应业务数据不存在");
            throw new RuntimeException("工作流对应业务数据不存在");
        }

        // 根据返回状态判断是否修改招标项目数据，agree则修改状态为1，reject则修改为-1
        String endEvent = jsonNode.get("eventType").asText();

        int status = -1;
        if ("agree".equals(actionName) && "endEvent".equals(endEvent)) {
            status = 1;
        }
        ScmTenderPrice scmTenderPrice = new ScmTenderPrice();
        scmTenderPrice.setFlowId(flowId);
        scmTenderPrice.setFlowStatus(status);

        super.update(new ScmTenderPrice());
    }

    /**
     * 将中标价条目转换成List
     *
     * @param stp 中标价详情条目
     * @return
     */
    private List<String> castScmTenderPrice2List(ScmTenderPriceVo stp) {
        List<String> row = new ArrayList<>();
        // 审批状态
        if (null != stp.getFlowStatus()) {
            switch (stp.getFlowStatus()) {
                case 0:
                    row.add("未提交");
                    break;
                case 1:
                    row.add("审批中");
                    break;
                case 2:
                    row.add("已通过");
                    break;
                case 3:
                    row.add("已驳回");
                    break;
            }
        } else {
            row.add("");
        }
        // 省区/地市/区县
        row.add(stp.getProvinceName());
        row.add(stp.getCityName());
        row.add(stp.getAreaName());
        // 医院编码/医院
        row.add(stp.getTerminalCode());
        row.add(stp.getTerminalName());
        // 招标全称/招标年份/项目启动时间/项目失效时间
        row.add(stp.getTenderName());
        row.add(stp.getTenderYear());
        row.add(stp.getTenderStartDate() != null ? stp.getTenderStartDate().toString() : "");
        row.add(stp.getTenderEndDate() != null ? stp.getTenderEndDate().toString() : "");
        // 执行状态
        if (null != stp.getExecStatus()) {
            switch (stp.getExecStatus()) {
                case 10:
                    row.add("未执行");
                    break;
                case 20:
                    row.add("执行中");
                    break;
                case 40:
                    row.add("已执行");
                    break;
            }
        } else {
            row.add("");
        }
        // 中标价生效时间/中标价失效时间
        row.add(stp.getAcceptedEffectDate() != null ? stp.getAcceptedEffectDate().toString() : "");
        row.add(stp.getAcceptedEndDate() != null ? stp.getAcceptedEndDate().toString() : "");
        // 商品名/通用名/品规/剂型/规格/包装单位
        row.add(stp.getProductName());
        row.add(stp.getCommonName());
        row.add(stp.getProductState());
        row.add(stp.getAgentType());
        row.add(stp.getProductState());
        row.add(stp.getUnit());
        // 转换系数
        row.add(stp.getConversionFactor() != null ? stp.getConversionFactor().toString() : "");
        // 投标价/本次中标价格/中标名称/中标分类/质量层次/生产企业/投标企业/备注
        row.add(stp.getBiddingPrice());
        row.add(stp.getTenderPrice());
        row.add(stp.getAreaName());
        row.add(stp.getTenderProperty());
        row.add(stp.getQuality());
        row.add(stp.getProducer());
        row.add(stp.getBiddingCompany());
        row.add(stp.getRemarks());

        return row;
    }

}
