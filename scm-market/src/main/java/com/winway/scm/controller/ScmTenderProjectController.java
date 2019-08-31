package com.winway.scm.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.BeanUtils;
import com.hotent.base.util.JsonUtil;
import com.hotent.base.util.StringUtil;
import com.winway.scm.model.ScmTenderProject;
import com.winway.scm.persistence.manager.ScmTenderProjectManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;

/**
 * <pre>
 * 描述：招标项目管理 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-06-25 11:09:05
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value = "/scm/scmTenderProject/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "scmTenderProjectController 招标项目管理接口")
public class ScmTenderProjectController extends BaseController {
    @Resource
    ScmTenderProjectManager scmTenderProjectManager;

    /**
     * 招标项目管理列表(分页条件查询)数据
     *
     * @param queryFilter
     * @return
     * @throws Exception
     */
    @PostMapping("/list")
    @ApiOperation(value = "招标项目管理数据列表", httpMethod = "POST", notes = "获取招标项目管理列表")
    public PageList<ScmTenderProject> list(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
        return scmTenderProjectManager.query(queryFilter);
    }

    /**
     * 招标项目管理明细页面
     *
     * @param id
     * @return
     * @throws Exception ModelAndView
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "招标项目管理数据详情", httpMethod = "GET", notes = "招标项目管理数据详情")
    public ScmTenderProject get(@ApiParam(name = "id", value = "业务对象主键", required = true) @PathVariable String id) throws Exception {
        return scmTenderProjectManager.get(id);
    }

    /**
     * 根据工作流ID获取招标项目详情
     *
     * @param flowId
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/get")
    @ApiOperation(value = "招标项目管理数据详情", httpMethod = "GET", notes = "招标项目管理数据详情")
    public ScmTenderProject getByIdOrFlowId(
            @ApiParam(name = "id", value = "业务对象主键/业务流程ID二选一") @RequestParam(value = "id", required = false) String id,
            @ApiParam(name = "flowId", value = "业务流程ID") @RequestParam(value = "flowId", required = false) String flowId) throws Exception {

        ScmTenderProject scmTenderProject = null;
        if (StringUtil.isNotEmpty(id)) {
            scmTenderProject = scmTenderProjectManager.get(id);
        } else if(StringUtil.isNotEmpty(flowId)) {
            scmTenderProject = scmTenderProjectManager.getByFlowId(flowId);
        }
        if (scmTenderProject == null) {
            throw new RuntimeException("请求的招标项目不存在，请确认ID、FlowId");
        }
        return scmTenderProject;
    }


    /**
     * 新增招标项目管理
     *
     * @param scmTenderProject
     * @return
     * @throws Exception
     * @throws
     */
    @PostMapping(value = "save")
    @ApiOperation(value = "新增,更新招标项目管理数据", httpMethod = "POST", notes = "新增,更新招标项目管理数据")
    public CommonResult<String> save(@ApiParam(name = "scmTenderProject", value = "招标项目管理业务对象", required = true) @RequestBody ScmTenderProject scmTenderProject) throws Exception {
        String msg = "添加招标项目管理成功";
        // 获取当前操作用户ID
        String account = super.current();
        if (StringUtil.isEmpty(scmTenderProject.getId())) {
            // 设置用户创建信息
            scmTenderProject.setCreateBy(account);
            scmTenderProjectManager.create(scmTenderProject);
        } else {
            if (!"0".equals(scmTenderProject.getFlowStatus())) {
                throw new RuntimeException("申请流程已提交，无法修改");
            }
            scmTenderProjectManager.update(scmTenderProject);
            // 设置用户修改信息
            scmTenderProject.setUpdateBy(account);
            msg = "更新招标项目管理成功";
        }
        return new CommonResult<>(msg);
    }

    /**
     * 删除招标项目管理记录
     *
     * @param id
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "remove/{id}")
    @ApiOperation(value = "删除招标项目管理记录", httpMethod = "DELETE", notes = "删除招标项目管理记录")
    public CommonResult<String> remove(@ApiParam(name = "id", value = "业务主键", required = true) @PathVariable String id) throws Exception {
        ScmTenderProject scmTenderProject = scmTenderProjectManager.get(id);
        if (scmTenderProject.getFlowStatus() != -1) {
            // 招标申请状态不为-1则不允许删除
            throw new RuntimeException("只允许删除状态为驳回的流程申请");
        }
        scmTenderProjectManager.remove(id);
        return new CommonResult<>(true, "删除成功");
    }

    /**
     * 批量删除招标项目管理记录
     *
     * @param ids
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "/removes")
    @ApiOperation(value = "批量删除招标项目管理记录", httpMethod = "DELETE", notes = "批量删除招标项目管理记录")
    public CommonResult<String> removes(@ApiParam(name = "ids", value = "业务主键数组,多个业务主键之间用逗号分隔", required = true) @RequestParam String... ids) throws Exception {
        if (ids == null || ids.length == 0) {
            throw new Exception("批量删除Id不能为空");
        }
        scmTenderProjectManager.removeByIds(ids);
        return new CommonResult<String>(true, "批量删除成功");
    }

    /**
     * 审批招标申请流程
     *
     * @param id
     * @param actionName
     * @param opinion
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/approval")
    @ApiOperation(value = "审批招标申请流程", httpMethod = "POST", notes = "审批招标申请流程")
    public CommonResult<String> approval(
            @ApiParam(name = "id", value = "业务主键", required = true) @PathParam(value = "id") String id,
            @ApiParam(name = "actionName", value = "审批动作:agree/reject", required = true) @RequestParam(value = "actionName") String actionName,
            @ApiParam(name = "opinion", value = "审批意见", required = true) @RequestParam(value = "opinion") String opinion) throws Exception {

        ScmTenderProject scmTenderProject = scmTenderProjectManager.get(id);
        if (BeanUtils.isEmpty(scmTenderProject)) {
            throw new RuntimeException("招标申请不存在");
        } else if (2 != scmTenderProject.getFlowStatus()) {
            throw new RuntimeException("无法审批该申请，该申请已完成或已失效");
        }
        scmTenderProjectManager.approval(scmTenderProject, actionName, opinion);

        return new CommonResult<>(true, "批准成功");
    }


    /**
     * 结束审批
     *
     * @param params
     * @throws Exception
     */
    @PostMapping(value = "/endApply")
    @ApiOperation(value = "招标申请审批通过及驳回", httpMethod = "POST", notes = "招标申请审批通过及驳回")
    public void endApply(@ApiParam(name = "params", value = "流程事件参数", required = true) @RequestBody String params) throws Exception {
        JsonNode jsonNode = JsonUtil.toJsonNode(params);
        scmTenderProjectManager.endApply(jsonNode);
    }

}
