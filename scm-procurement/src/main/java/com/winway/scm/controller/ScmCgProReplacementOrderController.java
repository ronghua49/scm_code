package com.winway.scm.controller;

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
import com.hotent.base.model.WinwayResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.JsonUtil;
import com.hotent.base.util.StringUtil;
import com.winway.purchase.vo.ReturnWmsVo;
import com.winway.scm.model.ScmCgProReplacementOrder;
import com.winway.scm.model.ScmCgProcurementContract;
import com.winway.scm.persistence.manager.ScmCgProReplacementOrderManager;
import com.winway.scm.persistence.manager.ScmCgProcurementContractManager;
import com.winway.scm.vo.WmsCgbdReceiptVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <pre>
 * 描述：采购订单补单表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-26 13:47:44
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
@RestController
@RequestMapping(value = "/scm/scmCgProReplacementOrder/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "scmCgProReplacementOrderController 采购订单补单")
public class ScmCgProReplacementOrderController extends BaseController {
    @Resource
    ScmCgProReplacementOrderManager scmCgProReplacementOrderManager;
    @Resource
    ScmCgProcurementContractManager scmCgProcurementContractManager;
    @Resource
    UCFeignService ucFeignService;

    /**
     * 采购订单补单表列表(分页条件查询)数据
     *
     * @param request
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/list")
    @ApiOperation(value = "采购订单补单表数据列表", httpMethod = "POST", notes = "获取采购订单补单表列表")
    public PageList<ScmCgProReplacementOrder> list(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
        return scmCgProReplacementOrderManager.query(queryFilter);
    }

    /**
     * 采购订单补单表明细页面
     *
     * @param id
     * @return
     * @throws Exception ModelAndView
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "采购订单补单表数据详情", httpMethod = "GET", notes = "采购订单补单表数据详情")
    public ScmCgProReplacementOrder get(@ApiParam(name = "id", value = "业务对象主键", required = true) @PathVariable String id) throws Exception {
        return scmCgProReplacementOrderManager.get(id);
    }

    /**
     * 新增采购订单补单表
     *
     * @param scmCgProReplacementOrder
     * @return
     * @throws Exception
     * @throws
     */
    @PostMapping(value = "save")
    @ApiOperation(value = "新增,更新采购订单补单表数据", httpMethod = "POST", notes = "新增,更新采购订单补单表数据")
    public CommonResult<ScmCgProReplacementOrder> save(@ApiParam(name = "scmCgProReplacementOrder", value = "采购订单补单表业务对象", required = true) @RequestBody ScmCgProReplacementOrder scmCgProReplacementOrder) throws Exception {
        String msg = "添加采购订单补单表成功";
        JsonNode user = ucFeignService.getUser(current(), "");
        String fullname = user.get("fullname").asText();
        String id = user.get("id").asText();
        scmCgProReplacementOrder.setOperatorName(fullname);
        scmCgProReplacementOrder.setOperatorId(id);
        scmCgProReplacementOrder.setApprovalState("0");
        ScmCgProcurementContract scmCgProcurementContract = scmCgProcurementContractManager.get(scmCgProReplacementOrder.getContractId());
        if (scmCgProcurementContract == null) {
            throw new RuntimeException("没有查询到采购合同");
        }
        scmCgProcurementContractManager.judgeSupplierValidity(scmCgProcurementContract.getSupplierId());
        if (StringUtil.isEmpty(scmCgProReplacementOrder.getId())) {
            scmCgProReplacementOrderManager.create(scmCgProReplacementOrder);
        } else {
            scmCgProReplacementOrderManager.update(scmCgProReplacementOrder);
            msg = "更新采购订单补单表成功";
        }
        return new CommonResult<ScmCgProReplacementOrder>(true, msg, scmCgProReplacementOrder);
    }

    /**
     * 删除采购订单补单表记录
     *
     * @param id
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "remove/{id}")
    @ApiOperation(value = "删除采购订单补单表记录", httpMethod = "DELETE", notes = "删除采购订单补单表记录")
    public CommonResult<String> remove(@ApiParam(name = "id", value = "业务主键", required = true) @PathVariable String id) throws Exception {
        scmCgProReplacementOrderManager.remove(id);
        return new CommonResult<String>(true, "删除成功");
    }

    /**
     * 批量删除采购订单补单表记录
     *
     * @param ids
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "/removes")
    @ApiOperation(value = "批量删除采购订单补单表记录", httpMethod = "DELETE", notes = "批量删除采购订单补单表记录")
    public CommonResult<String> removes(@ApiParam(name = "ids", value = "业务主键数组,多个业务主键之间用逗号分隔", required = true) @RequestParam String... ids) throws Exception {
        scmCgProReplacementOrderManager.removeByIds(ids);
        return new CommonResult<String>(true, "批量删除成功");
    }

    /**
     * @param 采购补单申请
     * @param request
     * @return
     * @throws Exception flowKey:审批类型
     *                   sysCode:系统别名
     *                   instanceIdField:与实体类审批ID相同,controller层接到对象会有审批ID,处理业务逻辑后,保存对象即可
     *                   varKeys:脚本使用参数
     */
    @PostMapping(value = "sendApply")
    @ApiOperation(value = "采购补单申请", httpMethod = "POST", notes = "采购补单流程申请")
//    @Workflow(flowKey = "cgbdgl", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {})
    public WinwayResult<String> sendApply(
            @ApiParam(name = "scmCgProReplacementOrder", value = "采购补单对象", required = true) @RequestBody ScmCgProReplacementOrder scmCgProReplacementOrder,
            HttpServletRequest request) throws Exception {
        scmCgProReplacementOrderManager.sendApply(scmCgProReplacementOrder);
        return new WinwayResult<>("true", "申请提交成功");
    }

    @PostMapping(value = "/endApply")
    @ApiOperation(value = "采购订单审批流程通过", httpMethod = "POST", notes = "采购订单审批流程通过,修改审批状态")
    public void endApply(@ApiParam(name = "params", value = "流程事件参数", required = true) @RequestBody String params,
                         HttpServletRequest request) throws Exception {
        JsonNode jsonNode = JsonUtil.toJsonNode(params);
        scmCgProReplacementOrderManager.endApply(jsonNode);
    }

    /**
     * 根据审批ID获取详细数据
     * 在供应商实体类 与mapper文件中添加一对多与一对多关系,委托书未生成
     *
     * @param id
     * @return
     * @throws Exception ModelAndView
     */
    @GetMapping(value = "/getProOrderFirstByApprovalId/{approvalId}")
    @ApiOperation(value = "根据审批ID获取详细数据", httpMethod = "GET", notes = "根据审批ID获取详细数据")
    public CommonResult<ScmCgProReplacementOrder> getProOrderFirstByApprovalId(@ApiParam(name = "approvalId", value = "审批ID", required = true) @PathVariable String approvalId) throws Exception {
        ScmCgProReplacementOrder scmCgProReplacementOrder = scmCgProReplacementOrderManager.getProOrderFirstByApprovalId(approvalId);
        return new CommonResult<ScmCgProReplacementOrder>(true, "获取成功", scmCgProReplacementOrder);
    }
	/**
	 * 采购补单回执
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/receipt")
	@ApiOperation(value="采购补单回执", httpMethod = "POST", notes = "采购补单回执")
	public ReturnWmsVo receipt(@RequestBody WmsCgbdReceiptVo wmsCgbdReceiptVo) throws Exception{
		return scmCgProReplacementOrderManager.receipt(wmsCgbdReceiptVo);
	}
}
