package com.winway.scm.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.BeanUtils;
import com.hotent.base.util.JsonUtil;
import com.hotent.base.util.StringUtil;
import com.winway.scm.model.ScmTenderPrice;
import com.winway.scm.persistence.manager.ScmTenderPriceManager;
import com.winway.scm.vo.ScmTenderPriceVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * <pre>
 * 描述：中标价管理 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-06-25 11:09:05
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value = "/scm/scmTenderPrice/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "scmTenderPriceController  中标价格管理")
public class ScmTenderPriceController extends BaseController {
    @Resource
    ScmTenderPriceManager scmTenderPriceManager;

    private Logger logger = LoggerFactory.getLogger(ScmTenderPriceController.class);

    /**
     * 中标价管理列表(分页条件查询)数据
     *
     * @param queryFilter
     * @return
     * @throws Exception
     * @throws
     */
    @PostMapping("/list")
    @ApiOperation(value = "中标价管理数据列表", httpMethod = "POST", notes = "获取中标价管理列表")
    public PageList<ScmTenderPrice> list(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
        return scmTenderPriceManager.query(queryFilter);
    }

    @PostMapping("/listAll")
    @ApiOperation(value = "中标价管理数据列表(包括招标/商品等信息)", httpMethod = "POST", notes = "获取中标价管理列表")
    public PageList<ScmTenderPriceVo> listAll(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) {
        return scmTenderPriceManager.queryAllInfo(queryFilter);
    }

    @GetMapping(value = "/getImplementPrice/{provinceId}/{productId}")
    @ApiOperation(value = "中标时获取中标价格", httpMethod = "GET", notes = "中标时获取中标价格")
    public CommonResult<List<ScmTenderPrice>> getImplementPrice(
            @ApiParam(name = "provinceId", value = "省区ID", required = true) @PathVariable String provinceId,
            @ApiParam(name = "productId", value = "品规编码", required = true) @PathVariable String productId) {

        String msg = "获取中标价成功";
        List<ScmTenderPrice> scmTenderPrices = scmTenderPriceManager.getScmTenderPriceByCode(provinceId, productId);
        return new CommonResult<>(true, msg, scmTenderPrices);
    }

    /**
     * 中标价管理明细页面
     *
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "中标价管理单条数据详情", httpMethod = "GET", notes = "中标价管理单条数据详情")
    public ScmTenderPrice get(@ApiParam(name = "id", value = "业务对象主键", required = true) @PathVariable String id) throws Exception {
        return scmTenderPriceManager.get(id);
    }

    /**
     * 根据聚合Id或流程Id获取中标价详情
     *
     * @param collectId
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/get")
    @ApiOperation(value = "根据申请的聚合Id或流程Id获取申请详情", httpMethod = "GET", notes = "根据申请的聚合Id或流程Id获取申请详情")
    public PageList<ScmTenderPriceVo> getByCollectId(
            @ApiParam(name = "collectId", value = "聚合Id") @RequestParam(value = "collectId", required = false) String collectId,
            @ApiParam(name = "flowId", value = "流程Id") @RequestParam(value = "flowId", required = false) String flowId) throws Exception {

        PageList<ScmTenderPriceVo> results = null;

        if (StringUtil.isNotEmpty(collectId)) {
            results = scmTenderPriceManager.getByCollectId(collectId);
        } else if (StringUtil.isNotEmpty(flowId)) {
            results = scmTenderPriceManager.getByFlowId(flowId);
        }
        if (results == null) {
            throw new RuntimeException("请求的中标价项目不存在，请确认collectId和FlowId");
        }
        return results;
    }


    /**
     * 新增中标价管理
     *
     * @param scmTenderPrice
     * @return
     * @throws Exception
     * @throws
     */
    @PostMapping(value = "save")
    @ApiOperation(value = "新增,更新中标价管理数据", httpMethod = "POST", notes = "新增,更新中标价管理数据")
    public CommonResult<String> save(@ApiParam(name = "scmTenderPrice", value = "中标价管理业务对象", required = true) @RequestBody ScmTenderPrice scmTenderPrice) throws Exception {

        String msg = "添加中标价管理成功";
        if (StringUtil.isEmpty(scmTenderPrice.getId())) {
            scmTenderPriceManager.create(scmTenderPrice);
        } else {
            ScmTenderPrice result = scmTenderPriceManager.get(scmTenderPrice.getId());
            if (result.getFlowStatus() != -1) {
                throw new RuntimeException("仅允许编辑状态为驳回的中标价申请");
            }
            scmTenderPriceManager.update(scmTenderPrice);
            msg = "更新中标价管理成功";
        }
        return new CommonResult<>(msg);
    }

    /**
     * 新增中标价管理集合
     *
     * @param scmTenderPrices
     * @return
     */
    @PostMapping(value = "saveAll")
    @ApiOperation(value = "新增中标价管理数据(多条记录,用于中标申请)", httpMethod = "POST", notes = "新增中标价管理数据(多条记录的数组)")
    public CommonResult<String> save(@RequestBody List<ScmTenderPrice> scmTenderPrices) {
        String msg = "添加中标价管理成功";
        if (BeanUtils.isEmpty(scmTenderPrices)) {
            throw new RuntimeException("中标价商品条目不能为空");
        }
        scmTenderPriceManager.batchCreate(scmTenderPrices);
        return new CommonResult<>(msg);
    }

    /**
     * 删除中标价管理记录
     *
     * @param id
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "remove/{id}")
    @ApiOperation(value = "删除中标价管理记录", httpMethod = "DELETE", notes = "删除中标价管理记录")
    public CommonResult<String> remove(@ApiParam(name = "id", value = "业务主键", required = true) @PathVariable String id) throws Exception {
        scmTenderPriceManager.remove(id);
        return new CommonResult<>(true, "删除成功");
    }

    /**
     * 批量删除中标价管理记录
     *
     * @param ids
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "/removes")
    @ApiOperation(value = "批量删除中标价管理记录", httpMethod = "DELETE", notes = "批量删除中标价管理记录")
    public CommonResult<String> removes(@ApiParam(name = "ids", value = "业务主键数组,多个业务主键之间用逗号分隔", required = true) @RequestParam String... ids) throws Exception {
        scmTenderPriceManager.removeByIds(ids);
        return new CommonResult<>(true, "批量删除成功");
    }

    /**
     * 导出符合条件的数据（xls格式）
     *
     * @param queryFilter
     * @param response
     * @throws Exception
     */
    @PostMapping(value = "/export")
    @ApiOperation(value = "导出全部符合条件的数据", httpMethod = "POST", notes = "导出全部符合条件的数据")
    public void exportXML(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter, HttpServletResponse response) throws Exception {
        Workbook workBook = scmTenderPriceManager.export(queryFilter);
        if (workBook == null) {
            throw new RuntimeException("未获取到数据，无法导出");
        }
        // 重置response
        response.reset();
        try (OutputStream out = response.getOutputStream()) {
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Expose-Headers", " Content-Disposition");
            response.addHeader("Content-Disposition", "attachment;filename="
                    + URLEncoder.encode("中标价详情", "utf-8") + ".xls");
            response.setContentType("application/vnd.ms-excel");
            workBook.write(out);
            out.flush();
        } catch (Exception e) {
            logger.debug(String.valueOf(e));
            throw new RuntimeException("导出数据失败");
        }
    }

    /**
     * 导出符合条件的数据（二进制）
     *
     * @param queryFilter
     * @throws Exception
     */
    @PostMapping(value = "/getExportData")
    @ApiOperation(value = "导出全部符合条件的数据", httpMethod = "POST", notes = "导出全部符合条件的数据")
    public byte[] exportXMLData(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
        byte[] result = scmTenderPriceManager.getExportData(queryFilter);
        if (result == null || result.length == 0) {
            throw new RuntimeException("未获取到数据，无法导出");
        }
        return result;
    }


    /**
     * 中标价流程审批回调
     *
     * @param params
     * @throws Exception
     */
    @PostMapping(value = "/endApply")
    @ApiOperation(value = "中标价审批回调接口", httpMethod = "POST", notes = "中标价审批回调")
    public void endApply(@ApiParam(name = "params", value = "流程事件参数", required = true) @RequestBody String params) throws Exception {
        JsonNode jsonNode = JsonUtil.toJsonNode(params);
        scmTenderPriceManager.endApply(jsonNode);
    }
}
