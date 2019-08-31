package com.winway.scm.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.annotation.Workflow;
import com.hotent.base.feign.UCFeignService;
import com.hotent.base.query.FieldRelation;
import com.hotent.base.query.QueryOP;
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
import com.winway.scm.persistence.manager.ScmGspHaltSalesManager;
import com.winway.purchase.vo.ReturnWmsVo;
import com.winway.purchase.vo.ScmWmsReceipt;
import com.winway.scm.model.ScmGspHaltSales;
import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;

import java.util.Date;

/**
 * <pre>
 * 描述：药品停售通知 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-24 12:00:21
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value = "/scm/scmGspHaltSales/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "scmGspHaltSalesController 药品停售通知")
public class ScmGspHaltSalesController extends BaseController {
    @Resource
    ScmGspHaltSalesManager scmGspHaltSalesManager;

    @Resource
    UCFeignService ucFeignService;

    /**
     * 药品停售通知列表(分页条件查询)数据
     *
     * @param
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/list")
    @ApiOperation(value = "药品停售通知数据列表", httpMethod = "POST", notes = "获取药品停售通知列表")
    public PageList<ScmGspHaltSales> list(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
        queryFilter.addFilter("state", "0", QueryOP.EQUAL, FieldRelation.AND);
        return scmGspHaltSalesManager.query(queryFilter);
    }

    /**
     * 药品停售通知明细页面
     *
     * @param id
     * @return
     * @throws Exception ModelAndView
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "药品停售通知数据详情", httpMethod = "GET", notes = "药品停售通知数据详情")
    public ScmGspHaltSales get(@ApiParam(name = "id", value = "业务对象主键", required = true) @PathVariable String id) throws Exception {
        return scmGspHaltSalesManager.get(id);
    }

    /**
     * 药品停售通知明细页面
     *
     * @param approvalId
     * @return
     * @throws Exception ModelAndView
     */
    @GetMapping(value = "/getDetail/{approvalId}")
    @ApiOperation(value = "药品停售通知数据详情", httpMethod = "GET", notes = "药品停售通知数据详情")
    public ScmGspHaltSales getDetail(@ApiParam(name = "id", value = "业务对象主键", required = true) @PathVariable String approvalId) throws Exception {
        return scmGspHaltSalesManager.getDetail(approvalId);
    }

    /**
     * 新增药品停售通知
     *
     * @param scmGspHaltSales
     * @return
     * @throws Exception
     * @throws
     */
    @PostMapping(value = "save")
    @ApiOperation(value = "新增,更新药品停售通知数据", httpMethod = "POST", notes = "新增,更新药品停售通知数据")
    public CommonResult<ScmGspHaltSales> save(@ApiParam(name = "scmGspHaltSales", value = "药品停售通知业务对象", required = true) @RequestBody ScmGspHaltSales scmGspHaltSales) throws Exception {
        String msg = "添加药品停售通知成功";
        setApprovalBase(scmGspHaltSales);
        scmGspHaltSales.setApprovalState("0");
        if (StringUtil.isEmpty(scmGspHaltSales.getId())) {
            scmGspHaltSalesManager.create(scmGspHaltSales);
        } else {
            scmGspHaltSalesManager.update(scmGspHaltSales);
            msg = "更新药品停售通知成功";
        }
        return new CommonResult<ScmGspHaltSales>(true, msg, scmGspHaltSales);
    }

    /**
     * 新增药品停售发起申请
     *
     * @param scmGspHaltSales
     * @return
     * @throws Exception
     * @throws
     */
    @PostMapping(value = "sendApply")
        @ApiOperation(value = "药品停售发起申请", httpMethod = "POST", notes = "申请药品停售通知数据")
//    @Workflow(flowKey = "yptssq", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {})
    public CommonResult<String> sendApply(@ApiParam(name = "scmGspHaltSales", value = "药品停售通知业务对象", required = true) @RequestBody ScmGspHaltSales scmGspHaltSales) throws Exception {
        setApprovalBase(scmGspHaltSales);
        scmGspHaltSalesManager.sendApply(scmGspHaltSales);
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
    @ApiOperation(value = "药品停售审批通过及驳回", httpMethod = "POST", notes = "药品停售审批通过及驳回")
    public void endApply(@ApiParam(name = "params", value = "流程事件参数", required = true) @RequestBody String params,
                         HttpServletRequest request) throws Exception {
        JsonNode jsonNode = JsonUtil.toJsonNode(params);
        scmGspHaltSalesManager.endApply(jsonNode);
    }


    /**
     * 新增解除药品停售通知
     *
     * @param scmGspHaltSales
     * @return
     * @throws Exception
     * @throws
     */
    @PostMapping(value = "saveByRelieve")
    @ApiOperation(value = "新增,更新解除药品停售通知数据", httpMethod = "POST", notes = "新增,更新解除药品停售通知数据")
    public CommonResult<String> saveByRelieve(@ApiParam(name = "scmGspHaltSales", value = "解除药品停售通知业务对象", required = true) @RequestBody ScmGspHaltSales scmGspHaltSales) throws Exception {
        if (StringUtil.isEmpty(scmGspHaltSales.getId()) || scmGspHaltSalesManager.get(scmGspHaltSales.getId()) == null) {
            throw new RuntimeException("数据错误传输错误，没有查询到要解除的药品");
        }
        ScmGspHaltSales scmGspHaltSales1 = scmGspHaltSalesManager.get(scmGspHaltSales.getId());
        scmGspHaltSales1.setApprovalState("0");
        scmGspHaltSales1.setRelieveHaltSalesDate(scmGspHaltSales.getRelieveHaltSalesDate());
        scmGspHaltSales1.setRelieveHaltSalesState(scmGspHaltSales.getRelieveHaltSalesState());
        scmGspHaltSales1.setRelieveHaltSalesMemo(scmGspHaltSales.getRelieveHaltSalesMemo());
        scmGspHaltSalesManager.update(scmGspHaltSales1);

        return new CommonResult<String>("解除药品停售通知暂存成功");
    }

    /**
     * 新增药品停售发起申请
     *
     * @param scmGspHaltSales
     * @return
     * @throws Exception
     * @throws
     */
    @PostMapping(value = "sendApplyByRelieve")
    @ApiOperation(value = "解除药品停售发起申请", httpMethod = "POST", notes = "解除申请药品停售通知数据")
//    @Workflow(flowKey = "jcyptssq", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {})
    public CommonResult<String> sendApplyByRelieve(@ApiParam(name = "scmGspHaltSales", value = "解除药品停售通知业务对象", required = true) @RequestBody ScmGspHaltSales scmGspHaltSales) throws Exception {
        setApprovalBase(scmGspHaltSales);
        scmGspHaltSalesManager.sendApplyByRelieve(scmGspHaltSales);
        return new CommonResult<String>("发起申请成功");
    }

    /**
     * 结束审批
     *
     * @param params
     * @param request
     * @throws Exception
     */
    @PostMapping(value = "/endApplyByRelieve")
    @ApiOperation(value = "解除药品停售审批通过及驳回", httpMethod = "POST", notes = "解除药品停售审批通过及驳回")
    public void endApplyByRelieve(@ApiParam(name = "params", value = "流程事件参数", required = true) @RequestBody String params,
                                  HttpServletRequest request) throws Exception {
        JsonNode jsonNode = JsonUtil.toJsonNode(params);
        scmGspHaltSalesManager.endApplyByRelieve(jsonNode);
    }

    /**
     * 删除药品停售通知记录
     *
     * @param id
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "remove/{id}")
    @ApiOperation(value = "删除药品停售通知记录", httpMethod = "DELETE", notes = "删除药品停售通知记录")
    public CommonResult<String> remove(@ApiParam(name = "id", value = "业务主键", required = true) @PathVariable String id) throws Exception {
        scmGspHaltSalesManager.remove(id);
        return new CommonResult<String>(true, "删除成功");
    }

    /**
     * 批量删除药品停售通知记录
     *
     * @param ids
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "/removes")
    @ApiOperation(value = "批量删除药品停售通知记录", httpMethod = "DELETE", notes = "批量删除药品停售通知记录")
    public CommonResult<String> removes(@ApiParam(name = "ids", value = "业务主键数组,多个业务主键之间用逗号分隔", required = true) @RequestParam String... ids) throws Exception {
        scmGspHaltSalesManager.removeByIds(ids);
        return new CommonResult<String>(true, "批量删除成功");
    }


    private void setApprovalBase(ScmGspHaltSales scmGspHaltSales) {
        JsonNode user = ucFeignService.getUser(current(), "");
        String fullname = user.get("fullname").asText();
        scmGspHaltSales.setCreatePersion(fullname);
        scmGspHaltSales.setCreateDate(new Date());
    }
	/**
	 * 停售回执
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/receipt")
	@ApiOperation(value="停售回执", httpMethod = "POST", notes = "停售回执")
	public ReturnWmsVo receipt(@ApiParam(name="ScmWmsReceipt",value="查询对象")@RequestBody ScmWmsReceipt scmWmsReceipt) throws Exception{
		if(scmWmsReceipt == null) {
			return new ReturnWmsVo("500","回执参数不能为空");
		}
		if(scmWmsReceipt.getOrderCode() == null || "".equals(scmWmsReceipt.getOrderCode())) {
			return new ReturnWmsVo("500","调拨单号不能为空");
		}
		if(scmWmsReceipt.getTypt() == null || "".equals(scmWmsReceipt.getTypt())) {
			return new ReturnWmsVo("500","状态码不能为空");
		}
		try{
			scmGspHaltSalesManager.receipt(scmWmsReceipt);
		}catch (Exception e) {
			return new ReturnWmsVo("500",e.getMessage());
		}
		return new ReturnWmsVo("200","处理成功");
	}
    
}
