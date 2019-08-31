package com.winway.scm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.annotation.Workflow;
import com.hotent.base.feign.UCFeignService;
import com.hotent.base.util.JsonUtil;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;
import com.winway.scm.model.ScmCwAnnualFeeMaster;
import com.winway.scm.persistence.manager.ScmCwAnnualFeeMasterManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.Date;

/**
 * <pre>
 * 描述：商业年费信息管理费主表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-28 09:38:34
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value = "/scm/scmCwAnnualFeeMaster/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "scmCwAnnualFeeMasterController 商业年费信息管理费主表")
public class ScmCwAnnualFeeMasterController extends BaseController {
    @Resource
    ScmCwAnnualFeeMasterManager scmCwAnnualFeeMasterManager;

    @Resource
    UCFeignService ucFeignService;

    /**
     * 商业年费信息管理费主表列表(分页条件查询)数据
     *
     * @param request
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/list")
    @ApiOperation(value = "商业年费信息管理费主表数据列表", httpMethod = "POST", notes = "获取商业年费信息管理费主表列表")
    public PageList<ScmCwAnnualFeeMaster> list(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
        return scmCwAnnualFeeMasterManager.query(queryFilter);
    }

    /**
     * 商业年费信息管理费主表明细页面
     *
     * @param id
     * @return
     * @throws Exception ModelAndView
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "商业年费信息管理费主表数据详情", httpMethod = "GET", notes = "商业年费信息管理费主表数据详情")
    public ScmCwAnnualFeeMaster get(@ApiParam(name = "id", value = "业务对象主键", required = true) @PathVariable String id) throws Exception {
        return scmCwAnnualFeeMasterManager.get(id);
    }

    /**
     * 新增商业年费信息管理费主表
     *
     * @param scmCwAnnualFeeMaster
     * @return
     * @throws Exception
     * @throws
     */
    @PostMapping(value = "save")
    @ApiOperation(value = "新增,更新商业年费信息管理费主表数据", httpMethod = "POST", notes = "新增,更新商业年费信息管理费主表数据")
    public CommonResult<ScmCwAnnualFeeMaster> save(@ApiParam(name = "scmCwAnnualFeeMaster", value = "商业年费信息管理费主表业务对象", required = true) @RequestBody ScmCwAnnualFeeMaster scmCwAnnualFeeMaster) throws Exception {
        String msg = "添加商业年费信息管理费主表成功";
        if (StringUtil.isEmpty(scmCwAnnualFeeMaster.getId())) {
            setApprovalBase(scmCwAnnualFeeMaster);
            scmCwAnnualFeeMaster.setApprovalState("0");
            scmCwAnnualFeeMasterManager.create(scmCwAnnualFeeMaster);
        } else {
            scmCwAnnualFeeMasterManager.update(scmCwAnnualFeeMaster);
            msg = "更新商业年费信息管理费主表成功";
        }
        return new CommonResult<ScmCwAnnualFeeMaster>(true,msg,scmCwAnnualFeeMaster);
    }

    /**
     * 商业年费信息管理费主表发起申请
     *
     * @param scmCwAnnualFeeMaster
     * @return
     * @throws Exception
     * @throws
     */
    @PostMapping(value = "sendApply")
    @ApiOperation(value = "商业年费信息管理费主表数据发起申请", httpMethod = "POST", notes = "商业年费信息管理费主表数据发起申请")
    @Workflow(flowKey = "synfxxglsp", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {})
    public CommonResult<String> sendApply(@ApiParam(name = "scmCwAnnualFeeMaster", value = "商业年费信息管理费主表业务对象", required = true) @RequestBody ScmCwAnnualFeeMaster scmCwAnnualFeeMaster) throws Exception {
        setApprovalBase(scmCwAnnualFeeMaster);
        scmCwAnnualFeeMaster.setApprovalState("1");
        scmCwAnnualFeeMasterManager.sendApply(scmCwAnnualFeeMaster);
        return new CommonResult<String>("申请成功");
    }


    /**
     * 商业年费信息管理费主表 审批结束
     *
     * @param params
     * @return
     * @throws Exception
     * @throws
     */
    @PostMapping(value = "/endApply")
    @ApiOperation(value = "商业年费信息管理费主表数据发起申请", httpMethod = "POST", notes = "商业年费信息管理费主表数据审批通过及驳回")
    public void endApply(@ApiParam(name = "params", value = "流程事件参数", required = true) @RequestBody String params,
                         HttpServletRequest request) throws Exception {
        JsonNode jsonNode = JsonUtil.toJsonNode(params);
        scmCwAnnualFeeMasterManager.endApply(jsonNode);
    }



    /**
     * 删除商业年费信息管理费主表记录
     *
     * @param id
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "remove/{id}")
    @ApiOperation(value = "删除商业年费信息管理费主表记录", httpMethod = "DELETE", notes = "删除商业年费信息管理费主表记录")
    public CommonResult<String> remove(@ApiParam(name = "id", value = "业务主键", required = true) @PathVariable String id) throws Exception {
        scmCwAnnualFeeMasterManager.remove(id);
        return new CommonResult<String>(true, "删除成功");
    }

    /**
     * 批量删除商业年费信息管理费主表记录
     *
     * @param ids
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "/removes")
    @ApiOperation(value = "批量删除商业年费信息管理费主表记录", httpMethod = "DELETE", notes = "批量删除商业年费信息管理费主表记录")
    public CommonResult<String> removes(@ApiParam(name = "ids", value = "业务主键数组,多个业务主键之间用逗号分隔", required = true) @RequestParam String... ids) throws Exception {
        scmCwAnnualFeeMasterManager.removeByIds(ids);
        return new CommonResult<String>(true, "批量删除成功");
    }


    private void setApprovalBase(ScmCwAnnualFeeMaster scmCwAnnualFeeMaster) {
        JsonNode user = ucFeignService.getUser(current(), "");
        String fullname = user.get("fullname").asText();
        scmCwAnnualFeeMaster.setCreatepersion(fullname);
        scmCwAnnualFeeMaster.setCreateDate(new Date());
    }
}
