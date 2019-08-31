package com.winway.scm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.Date;

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
import com.winway.scm.persistence.manager.ScmKcDrugDestructionMasterManager;
import com.winway.purchase.util.QuarterUtil;
import com.winway.scm.model.ScmKcBreakageMaster;
import com.winway.scm.model.ScmKcDrugDestructionMaster;
import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.annotation.Workflow;
import com.hotent.base.controller.BaseController;
import com.hotent.base.feign.UCFeignService;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.JsonUtil;
import com.hotent.base.util.StringUtil;

/**
 * <pre>
 * 描述：不合格药品销毁主表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-13 15:06:38
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value = "/scm/scmKcDrugDestructionMaster/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "scmKcDrugDestructionMasterController 不合格药品销毁主表 ")
public class ScmKcDrugDestructionMasterController extends BaseController {
    @Resource
    ScmKcDrugDestructionMasterManager scmKcDrugDestructionMasterManager;
    @Resource
    UCFeignService ucFeignService;

    /**
     * 不合格药品销毁主表列表(分页条件查询)数据
     *
     * @param
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/list")
    @ApiOperation(value = "不合格药品销毁主表数据列表", httpMethod = "POST", notes = "获取不合格药品销毁主表列表")
    public PageList<ScmKcDrugDestructionMaster> list(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
        return scmKcDrugDestructionMasterManager.query(queryFilter);
    }

    /**
     * 不合格药品销毁主表明细页面
     *
     * @param id
     * @return
     * @throws Exception ModelAndView
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "不合格药品销毁主表数据详情", httpMethod = "GET", notes = "不合格药品销毁主表数据详情")
    public ScmKcDrugDestructionMaster get(@ApiParam(name = "id", value = "业务对象主键", required = true) @PathVariable String id) throws Exception {
        return scmKcDrugDestructionMasterManager.get(id);
    }

    /**
     * 根据审批ID获取数据
     *
     * @param approvalId
     * @return
     * @throws Exception ModelAndView
     */
    @GetMapping(value = "/getDetail/{approvalId}")
    @ApiOperation(value = "根据审批ID获取数据", httpMethod = "GET", notes = "根据审批ID获取数据")
    public ScmKcDrugDestructionMaster getByApprovalId(@ApiParam(name = "approvalId", value = "审批ID", required = true) @PathVariable String approvalId) throws Exception {
        return scmKcDrugDestructionMasterManager.getByApprovalId(approvalId);
    }

    /**
     * 不合格药品销毁主表明细页面
     *
     * @param
     * @return
     * @throws Exception ModelAndView
     */
    @GetMapping(value = "/getCode")
    @ApiOperation(value = "获取订单号", httpMethod = "GET", notes = "获取订单号")
    public CommonResult<String> getCode() throws Exception {
        return new CommonResult<String>(true, "获取成功", QuarterUtil.getCode("XH"));

    }

    /**
     * 新增不合格药品销毁主表
     *
     * @param scmKcDrugDestructionMaster
     * @return
     * @throws Exception
     * @throws
     */
    @PostMapping(value = "save")
    @ApiOperation(value = "新增,更新不合格药品销毁主表数据", httpMethod = "POST", notes = "新增,更新不合格药品销毁主表数据")
    public CommonResult<String> save(@ApiParam(name = "scmKcDrugDestructionMaster", value = "不合格药品销毁主表业务对象", required = true) @RequestBody ScmKcDrugDestructionMaster scmKcDrugDestructionMaster) throws Exception {
        String msg = "添加不合格药品销毁主表成功";
        if (scmKcDrugDestructionMaster.getProductNum() == null || scmKcDrugDestructionMaster.getPriceSum() == null) {
            throw new RuntimeException("数量和总价不得为空");
        }
        JsonNode user = ucFeignService.getUser(current(), "");
        String userName = user.get("fullname").asText();
        scmKcDrugDestructionMaster.setApprovalState("0");
        if (StringUtil.isEmpty(scmKcDrugDestructionMaster.getId())) {
            scmKcDrugDestructionMaster.setCreatepersion(userName);
            scmKcDrugDestructionMaster.setCreateDate(new Date());
            scmKcDrugDestructionMasterManager.create(scmKcDrugDestructionMaster);
        } else {
            scmKcDrugDestructionMasterManager.update(scmKcDrugDestructionMaster);
            msg = "更新不合格药品销毁主表成功";
        }
        return new CommonResult<String>(true, msg, scmKcDrugDestructionMaster.getId());
    }

    /**
     * 删除不合格药品销毁主表记录
     *
     * @param id
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "remove/{id}")
    @ApiOperation(value = "删除不合格药品销毁主表记录", httpMethod = "DELETE", notes = "删除不合格药品销毁主表记录")
    public CommonResult<String> remove(@ApiParam(name = "id", value = "业务主键", required = true) @PathVariable String id) throws Exception {
        scmKcDrugDestructionMasterManager.remove(id);
        return new CommonResult<String>(true, "删除成功");
    }

    /**
     * 批量删除不合格药品销毁主表记录
     *
     * @param ids
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "/removes")
    @ApiOperation(value = "批量删除不合格药品销毁主表记录", httpMethod = "DELETE", notes = "批量删除不合格药品销毁主表记录")
    public CommonResult<String> removes(@ApiParam(name = "ids", value = "业务主键数组,多个业务主键之间用逗号分隔", required = true) @RequestParam String... ids) throws Exception {
        scmKcDrugDestructionMasterManager.removeByIds(ids);
        return new CommonResult<String>(true, "批量删除成功");
    }

    /**
     * 报损主表发起申请
     *
     * @param scmKcDrugDestructionMaster
     * @return
     * @throws Exception
     * @throws
     */
    @PostMapping(value = "sendApply")
    @ApiOperation(value = "不合格药品销毁发起申请", httpMethod = "POST", notes = "不合格药品销毁发起申请")
//    @Workflow(flowKey = "bhgypxh", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {})
    public CommonResult<String> sendApply(@ApiParam(name = "scmKcBreakageMaster", value = "不合格产品报损主表业务对象", required = true) @RequestBody ScmKcDrugDestructionMaster scmKcDrugDestructionMaster) throws Exception {
        JsonNode user = ucFeignService.getUser(current(), "");
        String userName = user.get("fullname").asText();
        scmKcDrugDestructionMaster.setCreatepersion(userName);
        scmKcDrugDestructionMasterManager.sendApply(scmKcDrugDestructionMaster);
        return new CommonResult<String>("申请成功");
    }


    /**
     * 报损结束审批
     *
     * @param params
     * @param request
     * @throws Exception
     */
    @PostMapping(value = "/endApply")
    @ApiOperation(value = "不合格药品销毁通过及驳回", httpMethod = "POST", notes = "不合格药品销毁通过及驳回")
    public void endApply(@ApiParam(name = "params", value = "流程事件参数", required = true) @RequestBody String params,
                         HttpServletRequest request) throws Exception {
        JsonNode jsonNode = JsonUtil.toJsonNode(params);
        scmKcDrugDestructionMasterManager.endApply(jsonNode);
    }
    
	/**
	 * 销毁回执
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/receipt")
	@ApiOperation(value="销毁回执", httpMethod = "POST", notes = "销毁回执")
	public CommonResult<String> receipt() throws Exception{
		return new CommonResult<String>(true,"处理成功");
	}
}
