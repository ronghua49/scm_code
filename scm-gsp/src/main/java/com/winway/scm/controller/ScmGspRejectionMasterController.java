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
import com.winway.scm.model.ScmGspRejectionMaster;
import com.winway.scm.persistence.manager.ScmGspRejectionMasterManager;
import com.winway.scm.vo.WmsCgjsReceiptVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <pre>
 * 描述：药品拒收报告单主表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-24 12:00:21
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value = "/scm/scmGspRejectionMaster/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "scmGspRejectionMasterController 药品拒收报告单主表")
public class ScmGspRejectionMasterController extends BaseController {
    @Resource
    ScmGspRejectionMasterManager scmGspRejectionMasterManager;
    @Resource
    UCFeignService ucFeignService;


    /**
     * 药品拒收报告单主表列表(分页条件查询)数据
     *
     * @param
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/list")
    @ApiOperation(value = "药品拒收报告单主表数据列表", httpMethod = "POST", notes = "获取药品拒收报告单主表列表")
    public PageList<ScmGspRejectionMaster> list(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
        return scmGspRejectionMasterManager.query(queryFilter);
    }

    /**
     * 药品拒收报告单主表明细页面
     *
     * @param id
     * @return
     * @throws Exception ModelAndView
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "药品拒收报告单主表数据详情", httpMethod = "GET", notes = "药品拒收报告单主表数据详情")
    public ScmGspRejectionMaster get(@ApiParam(name = "id", value = "业务对象主键", required = true) @PathVariable String id) throws Exception {
        return scmGspRejectionMasterManager.get(id);
    }


    /**
     * 药品拒收报告单主表审批明细页面
     *
     * @param id
     * @return
     * @throws Exception ModelAndView
     */
    @GetMapping(value = "/getDetail/{id}")
    @ApiOperation(value = "根据审批id获取详情", httpMethod = "GET", notes = "药品拒收报告单主表数据详情")
    public ScmGspRejectionMaster getDetail(@ApiParam(name = "id", value = "业务对象主键", required = true) @PathVariable String id) throws Exception {
        return scmGspRejectionMasterManager.getbyApprovalId(id);
    }


    /**
     * 新增药品拒收报告单主表
     * @param scmGspRejectionMaster
     * @return
     * @throws Exception
     * @throws
     */
    @PostMapping(value = "save")
    @ApiOperation(value = "新增,更新药品拒收报告单主表数据", httpMethod = "POST", notes = "新增,更新药品拒收报告单主表数据")
    public CommonResult<ScmGspRejectionMaster> save(@ApiParam(name = "scmGspRejectionMaster", value = "药品拒收包含详情药品对象", required = true) @RequestBody ScmGspRejectionMaster scmGspRejectionMaster) throws Exception {
        String msg = "添加药品拒收报告单主表成功";
        if (StringUtil.isEmpty(scmGspRejectionMaster.getId())) {
            scmGspRejectionMaster.setApprovalState("0");
            setApprovalBase(scmGspRejectionMaster);
            scmGspRejectionMasterManager.create(scmGspRejectionMaster);
        } else {
            scmGspRejectionMasterManager.update(scmGspRejectionMaster);
            msg = "更新药品拒收报告单主表成功";
        }
        return new CommonResult<ScmGspRejectionMaster>(true, msg, scmGspRejectionMaster);
    }


    /**
     * 新增药品拒收发起申请
     *
     * @param scmGspRejectionMaster
     * @return
     * @throws Exception
     * @throws
     */
    @PostMapping(value = "sendApply")
    @ApiOperation(value = "药品拒收发起申请", httpMethod = "POST", notes = "申请药品拒收数据")
//    @Workflow(flowKey = "ypjssq", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {})
    public CommonResult<ScmGspRejectionMaster> sendApply(@ApiParam(name = "scmGspRejectionMaster", value = "药品拒收包含详情药品对象", required = true) @RequestBody ScmGspRejectionMaster scmGspRejectionMaster) throws Exception {
        setApprovalBase(scmGspRejectionMaster);
        scmGspRejectionMasterManager.sendApply(scmGspRejectionMaster);
        return new CommonResult<ScmGspRejectionMaster>(true,"发起申请成功",scmGspRejectionMaster);
    }


    /**
     * 结束审批
     *
     * @param params
     * @param request
     * @throws Exception
     */
    @PostMapping(value = "/endApply")
    @ApiOperation(value = "药品拒收审批通过及驳回", httpMethod = "POST", notes = "药品拒收审批通过及驳回")
    public void endApply(@ApiParam(name = "params", value = "流程事件参数", required = true) @RequestBody String params,
                         HttpServletRequest request) throws Exception {
        JsonNode jsonNode = JsonUtil.toJsonNode(params);
        scmGspRejectionMasterManager.endApply(jsonNode);
    }


    /**
     * 删除药品拒收报告单主表记录
     *
     * @param id
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "remove/{id}")
    @ApiOperation(value = "删除药品拒收报告单主表记录", httpMethod = "DELETE", notes = "删除药品拒收报告单主表记录")
    public CommonResult<String> remove(@ApiParam(name = "id", value = "业务主键", required = true) @PathVariable String id) throws Exception {
        scmGspRejectionMasterManager.remove(id);
        return new CommonResult<String>(true, "删除成功");
    }

    /**
     * 批量删除药品拒收报告单主表记录
     *
     * @param ids
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "/removes")
    @ApiOperation(value = "批量删除药品拒收报告单主表记录", httpMethod = "DELETE", notes = "批量删除药品拒收报告单主表记录")
    public CommonResult<String> removes(@ApiParam(name = "ids", value = "业务主键数组,多个业务主键之间用逗号分隔", required = true) @RequestParam String... ids) throws Exception {
        scmGspRejectionMasterManager.removeByIds(ids);
        return new CommonResult<String>(true, "批量删除成功");
    }

    private void setApprovalBase(ScmGspRejectionMaster scmGspRejectionMaster) {
        JsonNode user = ucFeignService.getUser(current(), "");
        String fullname = user.get("fullname").asText();
        scmGspRejectionMaster.setCreatePersion(fullname);
        scmGspRejectionMaster.setCreateDate(new Date());
    }
	/**
	 * 采购拒收回执
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/receipt")
	@ApiOperation(value="采购拒收回执", httpMethod = "POST", notes = "采购拒收回执")
	public ReturnWmsVo receipt(@RequestBody WmsCgjsReceiptVo wmsCgjsReceiptVo) throws Exception{
		return scmGspRejectionMasterManager.receipt(wmsCgjsReceiptVo);
	}
    
}
