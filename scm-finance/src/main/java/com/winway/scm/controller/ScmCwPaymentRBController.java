package com.winway.scm.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.annotation.Workflow;
import com.hotent.base.feign.UCFeignService;
import com.hotent.base.util.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import com.winway.scm.persistence.manager.ScmCwPaymentRBManager;
import com.winway.scm.model.ScmCwPaymentRB;
import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;

import java.util.Date;

/**
 * <pre>
 * 描述：经销商RB支付申请总表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-09 18:18:05
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value = "/scm/scmCwPaymentRB/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "scmCwPaymentRBController 经销商RB支付申请总表")
public class ScmCwPaymentRBController extends BaseController {
    @Resource
    ScmCwPaymentRBManager scmCwPaymentRBManager;

    @Resource
    UCFeignService ucFeignService;


    /**
     * 经销商RB支付申请总表列表(分页条件查询)数据
     * @param
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/list")
    @ApiOperation(value = "经销商RB支付申请总表数据列表", httpMethod = "POST", notes = "获取经销商RB支付申请总表列表")
    public PageList<ScmCwPaymentRB> list(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
        return scmCwPaymentRBManager.query(queryFilter);
    }

    /**
     * 经销商RB支付申请总表明细页面
     *
     * @param id
     * @return
     * @throws Exception ModelAndView
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "经销商RB支付申请总表数据详情", httpMethod = "GET", notes = "经销商RB支付申请总表数据详情")
    public ScmCwPaymentRB get(@ApiParam(name = "id", value = "业务对象主键", required = true) @PathVariable String id) throws Exception {
        return scmCwPaymentRBManager.get(id);
    }

    /**
     * 经销商RB支付申请总表明细页面
     *
     * @param approvalId
     * @return
     * @throws Exception ModelAndView
     */
    @GetMapping(value = "/getDetail/{approvalId}")
    @ApiOperation(value = "经销商RB支付申请总表数据详情", httpMethod = "GET", notes = "经销商RB支付申请总表数据详情")
    public ScmCwPaymentRB getDetail(@ApiParam(name = "approvalId", value = "业务对象主键", required = true) @PathVariable String approvalId) throws Exception {
        return scmCwPaymentRBManager.getDetail(approvalId);
    }



    /**
     * 新增经销商RB支付申请总表
     *
     * @param scmCwPaymentRB
     * @return
     * @throws Exception
     * @throws
     */
    @PostMapping(value = "save")
    @ApiOperation(value = "新增,更新经销商RB支付申请总表数据", httpMethod = "POST", notes = "新增,更新经销商RB支付申请总表数据")
    public CommonResult<ScmCwPaymentRB> save(@ApiParam(name = "scmCwPaymentRB", value = "经销商RB支付申请总表业务对象", required = true) @RequestBody ScmCwPaymentRB scmCwPaymentRB) throws Exception {
        String msg = "添加经销商RB支付申请总表成功";
        setApprovalBase(scmCwPaymentRB);
        scmCwPaymentRB.setApprovalState("0");
        if (StringUtil.isEmpty(scmCwPaymentRB.getId())) {
            scmCwPaymentRBManager.create(scmCwPaymentRB);
        } else {
            scmCwPaymentRBManager.update(scmCwPaymentRB);
            msg = "更新经销商RB支付申请总表成功";
        }
        return new CommonResult<>(true,msg,scmCwPaymentRB);
    }

    /**
     * 新增经销商RB支付申请总表发起申请
     * @param scmCwPaymentRB
     * @return
     * @throws Exception
     * @throws
     */
    @PostMapping(value = "sendApply")
    @ApiOperation(value = "新增经销商RB支付申请总表发起申请", httpMethod = "POST", notes = "新增经销商RB支付申请总表发起申请")
//    @Workflow(flowKey = "jxsRBzf", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {})
    public CommonResult<String> sendApply(@ApiParam(name = "scmCwPaymentRB", value = "经销商RB支付申请总表业务对象", required = true) @RequestBody ScmCwPaymentRB scmCwPaymentRB) throws Exception {
        setApprovalBase(scmCwPaymentRB);
        scmCwPaymentRBManager.sendApply(scmCwPaymentRB);
        return new CommonResult<String>("申请成功");
    }

    /**
     * 结束审批
     *
     * @param params
     * @param request
     * @throws Exception
     */
    @PostMapping(value = "/endApply")
    @ApiOperation(value = "新增经销商RB支付申请审批通过及驳回", httpMethod = "POST", notes = "新增经销商RB支付申请审批通过及驳回")
    public void endApply(@ApiParam(name = "params", value = "流程事件参数", required = true) @RequestBody String params,
                         HttpServletRequest request) throws Exception {
        JsonNode jsonNode = JsonUtil.toJsonNode(params);
        scmCwPaymentRBManager.endApply(jsonNode);
    }


    /**
     * 删除经销商RB支付申请总表记录
     *
     * @param id
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "remove/{id}")
    @ApiOperation(value = "删除经销商RB支付申请总表记录", httpMethod = "DELETE", notes = "删除经销商RB支付申请总表记录")
    public CommonResult<String> remove(@ApiParam(name = "id", value = "业务主键", required = true) @PathVariable String id) throws Exception {
        scmCwPaymentRBManager.remove(id);
        return new CommonResult<String>(true, "删除成功");
    }

    /**
     * 批量删除经销商RB支付申请总表记录
     *
     * @param ids
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "/removes")
    @ApiOperation(value = "批量删除经销商RB支付申请总表记录", httpMethod = "DELETE", notes = "批量删除经销商RB支付申请总表记录")
    public CommonResult<String> removes(@ApiParam(name = "ids", value = "业务主键数组,多个业务主键之间用逗号分隔", required = true) @RequestParam String... ids) throws Exception {
        scmCwPaymentRBManager.removeByIds(ids);
        return new CommonResult<String>(true, "批量删除成功");
    }

    private void setApprovalBase(ScmCwPaymentRB scmCwPaymentRB) {
        JsonNode user = ucFeignService.getUser(current(), "");
        String fullname = user.get("fullname").asText();
        scmCwPaymentRB.setCreatePsersion(fullname);
        scmCwPaymentRB.setCreateDate(new Date());
    }

}
