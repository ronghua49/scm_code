package com.winway.scm.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.annotation.Workflow;
import com.hotent.base.feign.UCFeignService;
import com.hotent.base.util.JsonUtil;
import com.winway.purchase.util.QuarterUtil;
import com.winway.scm.model.ScmGspHaltSales;
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
import com.winway.scm.persistence.manager.ScmGspReviewConditionManager;
import com.winway.scm.model.ScmGspReviewCondition;
import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;

import java.util.Date;

/**
 * <pre>
 * 描述：药品复检通知单 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-24 12:00:21
 * 版权：美达开发小组
 * </pre>scmZsjCommerceFirst/v1/licenceValidity
 */
@RestController
@RequestMapping(value = "/scm/scmGspReviewCondition/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "scmGspReviewConditionController 药品复检通知单")
public class ScmGspReviewConditionController extends BaseController {
    @Resource
    ScmGspReviewConditionManager scmGspReviewConditionManager;

    @Resource
    UCFeignService ucFeignService;

    /**
     * 药品复检通知单列表(分页条件查询)数据
     *
     * @param
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/list")
    @ApiOperation(value = "药品复检通知单数据列表", httpMethod = "POST", notes = "获取药品复检通知单列表")
    public PageList<ScmGspReviewCondition> list(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
        return scmGspReviewConditionManager.query(queryFilter);
    }

    /**
     * 药品复检通知单明细页面
     *
     * @param id
     * @return
     * @throws Exception ModelAndView
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "药品复检通知单数据详情", httpMethod = "GET", notes = "药品复检通知单数据详情")
    public ScmGspReviewCondition get(@ApiParam(name = "id", value = "业务对象主键", required = true) @PathVariable String id) throws Exception {
        return scmGspReviewConditionManager.get(id);
    }

    /**
     * 药品复检通审批详情接口
     *
     * @param approvalId
     * @return
     * @throws Exception ModelAndView
     */
    @GetMapping(value = "/getDetail/{approvalId}")
    @ApiOperation(value = "药品复检通知单数据详情", httpMethod = "GET", notes = "药品复检通知单数据详情")
    public ScmGspReviewCondition getDetail(@ApiParam(name = "approvalId", value = "审批id", required = true) @PathVariable String approvalId) throws Exception {
        return scmGspReviewConditionManager.getDetailByApprovalId(approvalId);
    }

    /**
     * 新增药品复检通知单
     *
     * @param scmGspReviewCondition
     * @return
     * @throws Exception
     * @throws
     */
    @PostMapping(value = "save")
    @ApiOperation(value = "新增,更新药品复检通知单数据", httpMethod = "POST", notes = "新增,更新药品复检通知单数据")
    public CommonResult<ScmGspReviewCondition> save(@ApiParam(name = "scmGspReviewCondition", value = "scmGspReviewCondition", required = true) @RequestBody ScmGspReviewCondition scmGspReviewCondition) throws Exception {
        String msg = "添加药品复检通知单成功";
        setApprovalBase(scmGspReviewCondition);
        scmGspReviewCondition.setApprovalState("0");
        if (StringUtil.isEmpty(scmGspReviewCondition.getId())) {
            scmGspReviewCondition.setReviewCode(QuarterUtil.getCode("FJDH"));
            scmGspReviewConditionManager.create(scmGspReviewCondition);
        } else {
            scmGspReviewConditionManager.update(scmGspReviewCondition);
            msg = "更新药品复检通知单成功";
        }
        return new CommonResult<ScmGspReviewCondition>(true, msg, scmGspReviewCondition);
    }

    /**
     * 新增药品停售发起申请
     *
     * @param scmGspReviewCondition
     * @return
     * @throws Exception
     * @throws
     */
    @PostMapping(value = "sendApply")
    @ApiOperation(value = "药品复检通知单发起申请", httpMethod = "POST", notes = "药品复检数据")
//    @Workflow(flowKey = "ypzlfjfqsq", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {})
    public CommonResult<String> sendApply(@ApiParam(name = "scmGspReviewCondition", value = "scmGspReviewCondition", required = true) @RequestBody ScmGspReviewCondition scmGspReviewCondition) throws Exception {
        setApprovalBase(scmGspReviewCondition);
        scmGspReviewCondition.setApprovalState("1");
        scmGspReviewConditionManager.sendApply(scmGspReviewCondition);
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
    @ApiOperation(value = "药品复检通知单审批通过及驳回", httpMethod = "POST", notes = "药品复检审批通过及驳回")
    public void endApply(@ApiParam(name = "params", value = "流程事件参数", required = true) @RequestBody String params,
                         HttpServletRequest request) throws Exception {
        JsonNode jsonNode = JsonUtil.toJsonNode(params);
        scmGspReviewConditionManager.endApply(jsonNode);
    }

    /**
     * 删除药品复检通知单记录
     *
     * @param id
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "remove/{id}")
    @ApiOperation(value = "删除药品复检通知单记录", httpMethod = "DELETE", notes = "删除药品复检通知单记录")
    public CommonResult<String> remove(@ApiParam(name = "id", value = "业务主键", required = true) @PathVariable String id) throws Exception {
        scmGspReviewConditionManager.remove(id);
        return new CommonResult<String>(true, "删除成功");
    }

    /**
     * 批量删除药品复检通知单记录
     *
     * @param ids
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "/removes")
    @ApiOperation(value = "批量删除药品复检通知单记录", httpMethod = "DELETE", notes = "批量删除药品复检通知单记录")
    public CommonResult<String> removes(@ApiParam(name = "ids", value = "业务主键数组,多个业务主键之间用逗号分隔", required = true) @RequestParam String... ids) throws Exception {
        scmGspReviewConditionManager.removeByIds(ids);
        return new CommonResult<String>(true, "批量删除成功");
    }

    private void setApprovalBase(ScmGspReviewCondition scmGspReviewCondition) {
        JsonNode user = ucFeignService.getUser(current(), "");
        String fullname = user.get("fullname").asText();
        scmGspReviewCondition.setCreatePersion(fullname);
        scmGspReviewCondition.setCreateDate(new Date());
    }
}
