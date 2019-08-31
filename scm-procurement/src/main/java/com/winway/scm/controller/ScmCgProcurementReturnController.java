package com.winway.scm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.hotent.base.feign.BpmRuntimeFeignService;
import com.hotent.base.modelvo.CustomStartResult;
import com.hotent.base.modelvo.StartFlowParam;
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
import com.winway.scm.model.ScmCgProcurementReturn;
import com.winway.scm.persistence.manager.ScmCgProcurementReturnManager;
import com.winway.scm.vo.ScmZsjDrogPuchaseReturn;
import com.winway.scm.vo.WmsCgthReceiptVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <pre>
 * 描述：采购退货表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-26 13:47:44
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
@RestController
@RequestMapping(value = "/scm/scmCgProcurementReturn/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "scmCgProcurementReturnController 采购退货")
public class ScmCgProcurementReturnController extends BaseController {
    @Resource
    ScmCgProcurementReturnManager scmCgProcurementReturnManager;
    @Resource
    UCFeignService ucFeignService;
    @Resource
    BpmRuntimeFeignService bpmRuntimeFeignService;

    /**
     * 采购退货表列表(分页条件查询)数据
     *
     * @param
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/list")
    @ApiOperation(value = "采购退货表数据列表", httpMethod = "POST", notes = "获取采购退货表列表")
    public PageList<ScmCgProcurementReturn> list(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
        return scmCgProcurementReturnManager.query(queryFilter);
    }

    /**
     * 采购退货表记录
     *
     * @param
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/drugPuchaseReturn")
    @ApiOperation(value = "采购退货表记录", httpMethod = "POST", notes = "采购退货表记录")
    public PageList<ScmZsjDrogPuchaseReturn> drugPuchaseReturn(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
        return scmCgProcurementReturnManager.drugPuchaseReturn(queryFilter);
    }

    /**
     * 采购退货表明细页面
     *
     * @param id
     * @return
     * @throws Exception ModelAndView
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "采购退货表数据详情", httpMethod = "GET", notes = "采购退货表数据详情")
    public ScmCgProcurementReturn get(@ApiParam(name = "id", value = "业务对象主键", required = true) @PathVariable String id) throws Exception {
        return scmCgProcurementReturnManager.get(id);
    }

    /**
     * 采购退货表审批页面
     *
     * @param approvalId
     * @return
     * @throws Exception ModelAndView
     */
    @GetMapping(value = "/getDetail/{approvalId}")
    @ApiOperation(value = "采购退货表数据详情", httpMethod = "GET", notes = "采购退货表数据详情")
    public ScmCgProcurementReturn getDetail(@ApiParam(name = "approvalId", value = "业务对象主键", required = true) @PathVariable String approvalId) throws Exception {
        return scmCgProcurementReturnManager.getDetail(approvalId);
    }


    /**
     * 新增采购退货表
     *
     * @param scmCgProcurementReturn
     * @return
     * @throws Exception
     * @throws
     */
    @PostMapping(value = "save")
    @ApiOperation(value = "新增,更新采购退货表数据", httpMethod = "POST", notes = "新增,更新采购退货表数据")
    public CommonResult<ScmCgProcurementReturn> save(@ApiParam(name = "scmCgProcurementReturn", value = "采购退货表业务对象", required = true) @RequestBody ScmCgProcurementReturn scmCgProcurementReturn) throws Exception {
        String msg = "添加采购退货表成功";
        if (StringUtil.isEmpty(scmCgProcurementReturn.getId())) {
            JsonNode user = ucFeignService.getUser(current(), "");
            String fullname = user.get("fullname").asText();
            String id = user.get("id").asText();
            scmCgProcurementReturn.setApprovalState('0');
            scmCgProcurementReturn.setOperatorName(fullname);
            scmCgProcurementReturn.setOperatorId(id);
            scmCgProcurementReturnManager.create(scmCgProcurementReturn);
        } else {
            scmCgProcurementReturnManager.update(scmCgProcurementReturn);
            msg = "更新采购退货表成功";
        }
        return new CommonResult<ScmCgProcurementReturn>(true, "暂存成功", scmCgProcurementReturn);
    }

    /**
     * 删除采购退货表记录
     *
     * @param id
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "remove/{id}")
    @ApiOperation(value = "删除采购退货表记录", httpMethod = "DELETE", notes = "删除采购退货表记录")
    public CommonResult<String> remove(@ApiParam(name = "id", value = "业务主键", required = true) @PathVariable String id) throws Exception {
        scmCgProcurementReturnManager.remove(id);
        return new CommonResult<String>(true, "删除成功");
    }

    /**
     * 批量删除采购退货表记录
     *
     * @param ids
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "/removes")
    @ApiOperation(value = "批量删除采购退货表记录", httpMethod = "DELETE", notes = "批量删除采购退货表记录")
    public CommonResult<String> removes(@ApiParam(name = "ids", value = "业务主键数组,多个业务主键之间用逗号分隔", required = true) @RequestParam String... ids) throws Exception {
        scmCgProcurementReturnManager.removeByIds(ids);
        return new CommonResult<String>(true, "批量删除成功");
    }

    /**
     * @param
     * @param request
     * @return
     * @throws Exception flowKey:审批类型
     *                   sysCode:系统别名
     *                   instanceIdField:与实体类审批ID相同,controller层接到对象会有审批ID,处理业务逻辑后,保存对象即可
     *                   varKeys:脚本使用参数
     */
    @PostMapping(value = "sendApply")
    @ApiOperation(value = "采购退货申请", httpMethod = "POST", notes = "采购退货流程发起")
//    @Workflow(flowKey = "cgthsq", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {"account", "isFinance"})
    public WinwayResult<ScmCgProcurementReturn> sendApply(
            @ApiParam(name = "scmCgProcurementReturn", value = "采购退货对象", required = true) @RequestBody ScmCgProcurementReturn scmCgProcurementReturn,
            HttpServletRequest request) throws Exception {
        JsonNode user = ucFeignService.getUser(current(), "");
        String fullname = user.get("fullname").asText();
        scmCgProcurementReturn.setOperatorName(fullname);
        String id = user.get("id").asText();
        scmCgProcurementReturn.setOperatorId(id);

        StartFlowParam startFlowParam = new StartFlowParam("cgthsq", "SCM", "approvalId");
        startFlowParam.setFormType("frame");
        CustomStartResult customStartResult = null;
        try {
            System.out.println("采购退货申请");
            customStartResult = bpmRuntimeFeignService.customStart(startFlowParam);
            String approvalId = customStartResult.getInstId();
            scmCgProcurementReturn.setApprovalId(approvalId);
            if (StringUtil.isEmpty(scmCgProcurementReturn.getId())) {
                scmCgProcurementReturn.setApprovalState('1');
                scmCgProcurementReturnManager.create(scmCgProcurementReturn);
            } else {
                if ('1' == scmCgProcurementReturn.getApprovalState() || '2' == scmCgProcurementReturn.getApprovalState()) {
                    throw new RuntimeException("该数据正在审批中或已审批完成，不得二次申请");
                }
                scmCgProcurementReturn.setApprovalState('1');
                scmCgProcurementReturnManager.update(scmCgProcurementReturn);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("启动工作流失败");
        }
        return new WinwayResult<>("true", "申请提交成功", scmCgProcurementReturn);
    }


    /**
     * 采购退货结束审批
     *
     * @param params
     * @param request
     * @throws Exception
     */
    @PostMapping(value = "/endApply")
    @ApiOperation(value = "采购退货审批通过及驳回", httpMethod = "POST", notes = "采购退货审批通过及驳回")
    public void endApply(@ApiParam(name = "params", value = "流程事件参数", required = true) @RequestBody String params,
                         HttpServletRequest request) throws Exception {
        JsonNode jsonNode = JsonUtil.toJsonNode(params);
        scmCgProcurementReturnManager.endApply(jsonNode);
    }
    
	/**
	 * 采购退货回执
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/receipt")
	@ApiOperation(value="采购退货回执", httpMethod = "POST", notes = "采购退货回执")
	public ReturnWmsVo receipt(@RequestBody WmsCgthReceiptVo smsCgthReceiptVo) throws Exception{
		return scmCgProcurementReturnManager.receipt(smsCgthReceiptVo);
	}
    
    
}
