package com.winway.scm.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.annotation.Workflow;
import com.hotent.base.controller.BaseController;
import com.hotent.base.feign.UCFeignService;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.JsonUtil;
import com.hotent.base.util.StringUtil;
import com.winway.purchase.vo.ReturnWmsVo;
import com.winway.purchase.vo.ScmWmsReceipt;
import com.winway.purchase.vo.WmsPageList;
import com.winway.scm.model.InventoryProduct;
import com.winway.scm.model.ScmKcAllotMaster;
import com.winway.scm.persistence.manager.ScmKcAllotMasterManager;
import com.winway.scm.vo.Order;
import com.winway.scm.vo.ScmGspDrupReplenishVo;
import com.winway.scm.vo.ScmKcProductSumQuery;
import com.winway.scm.vo.ScmWmsFindNearlyEffective;
import com.winway.scm.vo.ScmWmsNearlyEffective;
import com.winway.scm.vo.ScmWmsProductSum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <pre>
 * 描述：库存调拨总表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-23 17:00:46
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value = "/scm/scmKcAllotMaster/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "scmKcAllotMasterController 库存调拨总表")
public class ScmKcAllotMasterController extends BaseController {
    @Resource
    ScmKcAllotMasterManager scmKcAllotMasterManager;
    @Resource
    UCFeignService ucFeignService;

    /**
     * 库存调拨总表列表(分页条件查询)数据
     *
     * @param
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/productSumQuery")
    @ApiOperation(value = "商品库存总量查询", httpMethod = "POST", notes = "商品库存总量查询")
    public PageList<ScmWmsProductSum> productSumQuery(@ApiParam(name = "ScmWmsPage", value = "分页对象") @RequestBody ScmKcProductSumQuery scmKcProductSumQuery) throws Exception {
        PageList<ScmWmsProductSum> list = scmKcAllotMasterManager.productSumQuery(scmKcProductSumQuery);
        return list;
    }

    /**
     * 库存调拨总表列表(分页条件查询)数据
     *
     * @param
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/list")
    @ApiOperation(value = "库存调拨总表数据列表", httpMethod = "POST", notes = "获取库存调拨总表列表")
    public PageList<ScmKcAllotMaster> list(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
        return scmKcAllotMasterManager.query(queryFilter);
    }

    /**
     * SAP-库存调拨出库查询
     *
     * @param
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/allotInSap")
    @ApiOperation(value = "库存调拨总表数据列表", httpMethod = "POST", notes = "获取库存调拨总表列表")
    public WmsPageList<ScmGspDrupReplenishVo> allotInSap(@ApiParam(name = "order", value = "查询对象") @RequestBody Order order) throws Exception {
        if (order.getOrderCode() == null || "".equals(order.getOrderCode())) {
            throw new RuntimeException("请传递订单号");
        }
        return scmKcAllotMasterManager.allotInSap(order.getOrderCode());
    }

    /**
     * SAP-库存调拨入库查询
     *
     * @param
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/allotOutSap")
    @ApiOperation(value = "库存调拨总表数据列表", httpMethod = "POST", notes = "获取库存调拨总表列表")
    public WmsPageList<ScmGspDrupReplenishVo> allotOutSap(@ApiParam(name = "order", value = "查询对象") @RequestBody Order order) throws Exception {
        if (order.getOrderCode() == null || "".equals(order.getOrderCode())) {
            throw new RuntimeException("请传递订单号");
        }
        return scmKcAllotMasterManager.allotOutSap(order.getOrderCode());
    }

    /**
     * 库存调拨总表明细页面
     *
     * @param id
     * @return
     * @throws Exception ModelAndView
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "库存调拨总表数据详情", httpMethod = "GET", notes = "库存调拨总表数据详情")
    public ScmKcAllotMaster get(@ApiParam(name = "id", value = "业务对象主键", required = true) @PathVariable String id) throws Exception {
        return scmKcAllotMasterManager.get(id);
    }

    /**
     * @param id
     * @return
     * @throws Exception ModelAndView
     */
    @GetMapping(value = "/getDetail/{approvalId}")
    @ApiOperation(value = "库存调拨总表数据详情", httpMethod = "GET", notes = "库存调拨总表数据详情")
    public ScmKcAllotMaster getDetail(@ApiParam(name = "approvalId", value = "审批id", required = true) @PathVariable String approvalId) throws Exception {
        return scmKcAllotMasterManager.getDetail(approvalId);
    }

    /**
     * 新增库存调拨总表
     *
     * @param scmKcAllotMaster
     * @return
     * @throws Exception
     * @throws
     */
    @PostMapping(value = "save")
    @ApiOperation(value = "新增,更新库存调拨总表数据", httpMethod = "POST", notes = "新增,更新库存调拨总表数据")
    public CommonResult<ScmKcAllotMaster> save(@ApiParam(name = "scmKcAllotMaster", value = "库存调拨总表业务对象", required = true) @RequestBody ScmKcAllotMaster scmKcAllotMaster) throws Exception {
        String msg = "添加库存调拨总表成功";
        scmKcAllotMaster.setApprovalState("0");
        if (StringUtil.isEmpty(scmKcAllotMaster.getId())) {
            setApprovalBase(scmKcAllotMaster);
            scmKcAllotMasterManager.create(scmKcAllotMaster);
        } else {
            scmKcAllotMasterManager.update(scmKcAllotMaster);
            msg = "更新库存调拨总表成功";
        }
        return new CommonResult<ScmKcAllotMaster>(true, msg, scmKcAllotMaster);
    }


    /**
     * 库存调拨发起申请
     *
     * @param scmKcAllotMaster
     * @return
     * @throws Exception
     * @throws
     */
    @PostMapping(value = "sendApply")
    @ApiOperation(value = "库存调拨发起申请", httpMethod = "POST", notes = "库存调拨主数据和调拨商品数据")
//    @Workflow(flowKey = "kcdb", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {})
    public CommonResult<ScmKcAllotMaster> sendApply(@ApiParam(name = "scmKcAllotMaster", value = "库存调拨主数据和调拨商品数据", required = true) @RequestBody ScmKcAllotMaster scmKcAllotMaster) throws Exception {
        setApprovalBase(scmKcAllotMaster);
        ScmKcAllotMaster scmKcAllotMaster1 = scmKcAllotMasterManager.sendApply(scmKcAllotMaster);
        return new CommonResult<>(true, "发起申请成功", scmKcAllotMaster1);
    }


    /**
     * 结束审批
     *
     * @param params
     * @param request
     * @throws Exception
     */
    @PostMapping(value = "/endApply")
    @ApiOperation(value = "库存调拨审批通过及驳回", httpMethod = "POST", notes = "库存调拨审批通过及驳回")
    public void endApply(@ApiParam(name = "params", value = "流程事件参数", required = true) @RequestBody String params,
                         HttpServletRequest request) throws Exception {
        JsonNode jsonNode = JsonUtil.toJsonNode(params);
        scmKcAllotMasterManager.endApply(jsonNode);
    }

    /**
     * 删除库存调拨总表记录
     *
     * @param id
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "remove/{id}")
    @ApiOperation(value = "删除库存调拨总表记录", httpMethod = "DELETE", notes = "删除库存调拨总表记录")
    public CommonResult<String> remove(@ApiParam(name = "id", value = "业务主键", required = true) @PathVariable String id) throws Exception {
        scmKcAllotMasterManager.remove(id);
        return new CommonResult<String>(true, "删除成功");
    }

    /**
     * 批量删除库存调拨总表记录
     *
     * @param ids
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "/removes")
    @ApiOperation(value = "批量删除库存调拨总表记录", httpMethod = "DELETE", notes = "批量删除库存调拨总表记录")
    public CommonResult<String> removes(@ApiParam(name = "ids", value = "业务主键数组,多个业务主键之间用逗号分隔", required = true) @RequestParam String... ids) throws Exception {
        scmKcAllotMasterManager.removeByIds(ids);
        return new CommonResult<String>(true, "批量删除成功");
    }

    private void setApprovalBase(ScmKcAllotMaster scmKcAllotMaster) {
        JsonNode user = ucFeignService.getUser(current(), "");
        String fullname = user.get("fullname").asText();
        scmKcAllotMaster.setCreatePersion(fullname);
        scmKcAllotMaster.setCreateDate(new Date());
    }

    /**
     * 库存调拨查询 wms库存商品数据
     *
     * @param
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/inventory")
    @ApiOperation(value = "wms库存商品信息", httpMethod = "POST", notes = "获取wms仓库商品列表")
    public PageList<InventoryProduct> inventory(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
        return scmKcAllotMasterManager.queryInventory(queryFilter);
    }

    /**
     * 查询WMS近效期库存
     *
     * @param
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/findNearlyEffective")
    @ApiOperation(value = "查询WMS近效期库存", httpMethod = "POST", notes = "查询WMS近效期库存")
    public PageList<ScmWmsNearlyEffective> findNearlyEffective(@ApiParam(name = "ScmWmsFindNearlyEffective", value = "查询对象") @RequestBody ScmWmsFindNearlyEffective scmWmsFindNearlyEffective) throws Exception {
        return scmKcAllotMasterManager.findNearlyEffective(scmWmsFindNearlyEffective);
    }

    /**
     * 库存调拨回执
     *
     * @param request
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/receipt")
    @ApiOperation(value = "库存调拨回执", httpMethod = "POST", notes = "库存调拨回执")
    public ReturnWmsVo receipt(@ApiParam(name = "ScmWmsReceipt", value = "查询对象") @RequestBody ScmWmsReceipt scmWmsReceipt) throws Exception {
        if (scmWmsReceipt == null) {
            return new ReturnWmsVo("500", "回执参数不能为空");
        }
        if (scmWmsReceipt.getOrderCode() == null || "".equals(scmWmsReceipt.getOrderCode())) {
            return new ReturnWmsVo("500", "调拨单号不能为空");
        }
        if (scmWmsReceipt.getTypt() == null || "".equals(scmWmsReceipt.getTypt())) {
            return new ReturnWmsVo("500", "状态码不能为空");
        }
        try {
            scmKcAllotMasterManager.receipt(scmWmsReceipt);
        } catch (Exception e) {
            return new ReturnWmsVo("500", e.getMessage());
        }
        return new ReturnWmsVo("200", "处理成功");
    }
}
