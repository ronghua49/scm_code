package com.winway.scm.controller;

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
import com.winway.scm.persistence.manager.ScmCwPaymentManager;
import com.winway.scm.model.ScmCwPayment;
import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.annotation.Workflow;
import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.JsonUtil;
import com.hotent.base.util.StringUtil;

/**
 * 
 * <pre>
 *  
 * 描述：付款总表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-23 18:19:25
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value = "/scm/scmCwPayment/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "scmCwPaymentController 付款总表 控制器类")
public class ScmCwPaymentController extends BaseController {
	@Resource
	ScmCwPaymentManager scmCwPaymentManager;

	/**
	 * 付款总表列表(分页条件查询)数据 @param request @return @throws Exception
	 * PageJson @exception
	 */
	@PostMapping("/list")
	@ApiOperation(value = "付款总表数据列表", httpMethod = "POST", notes = "获取付款总表列表")
	public PageList<ScmCwPayment> list(
			@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
		return scmCwPaymentManager.query(queryFilter);
	}

	/**
	 * 付款总表明细页面
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@GetMapping(value = "/get/{id}")
	@ApiOperation(value = "付款总表数据详情", httpMethod = "GET", notes = "付款总表数据详情")
	public ScmCwPayment get(@ApiParam(name = "id", value = "业务对象主键", required = true) @PathVariable String id)
			throws Exception {
		return scmCwPaymentManager.get(id);
	}

	/**
	 * 新增付款总表 @param scmCwPayment @throws Exception @return @exception
	 */
	@PostMapping(value = "save")
	@ApiOperation(value = "新增,更新,暂存付款总表数据", httpMethod = "POST", notes = "新增,更新，暂存付款总表数据")
	public CommonResult<String> save(
			@ApiParam(name = "scmCwPayment", value = "付款总表业务对象", required = true) @RequestBody ScmCwPayment scmCwPayment)
			throws Exception {
		String msg = "添加付款总表成功";
		ScmCwPayment payment = scmCwPaymentManager.get(scmCwPayment.getId());
		if (payment == null) {
			scmCwPayment.setApprovalState("0");
			scmCwPaymentManager.creates(scmCwPayment);
		} else {
			scmCwPayment.setApprovalState("0");
			scmCwPaymentManager.updates(scmCwPayment);
			msg = "更新付款总表成功";
		}
		return new CommonResult<String>(msg);
	}

	/**
	 * 删除付款总表记录 @param id @throws Exception @return @exception
	 */
	@DeleteMapping(value = "remove/{id}")
	@ApiOperation(value = "删除付款总表记录", httpMethod = "DELETE", notes = "删除付款总表记录")
	public CommonResult<String> remove(@ApiParam(name = "id", value = "业务主键", required = true) @PathVariable String id)
			throws Exception {
		scmCwPaymentManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}

	/**
	 * 批量删除付款总表记录 @param ids @throws Exception @return @exception
	 */
	@DeleteMapping(value = "/removes")
	@ApiOperation(value = "批量删除付款总表记录", httpMethod = "DELETE", notes = "批量删除付款总表记录")
	public CommonResult<String> removes(
			@ApiParam(name = "ids", value = "业务主键数组,多个业务主键之间用逗号分隔", required = true) @RequestParam String... ids)
			throws Exception {
		scmCwPaymentManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}

	/**
	 * 付款发起申请
	 * 
	 * @param scmCwPayment
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/sendApply")
	@ApiOperation(value = "付款发起申请审批", httpMethod = "POST", notes = "付款发起申请审批")
	@Workflow(flowKey = "fksqsp", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {})
	public CommonResult<String> sendApply(
			@ApiParam(name = "scmCwPayment", value = "付款总表业务对象", required = true) @RequestBody ScmCwPayment scmCwPayment)
			throws Exception {
		String msg = "申请发起成功";
		ScmCwPayment payment = scmCwPaymentManager.get(scmCwPayment.getId());
		if(payment == null){
			scmCwPayment.setApprovalState("1");
			scmCwPaymentManager.creates(scmCwPayment);
		}else{
			if(payment.getApprovalState().equals("1")){
				throw new RuntimeException("当前数据已经在审批中,不可重复提交");
			}else{
			scmCwPayment.setApprovalState("1");
			scmCwPaymentManager.updates(scmCwPayment);
			}
		}
		return new CommonResult<String>(msg);
	}

	@PostMapping(value = "/endApply")
	@ApiOperation(value = "付款审批流程通过", httpMethod = "POST", notes = "付款审批流程通过,修改审批状态")
	public void endApply(@ApiParam(name = "params", value = "流程事件参数", required = true) @RequestBody String params,
			HttpServletRequest request) throws Exception {
		JsonNode jsonNode = JsonUtil.toJsonNode(params);
		scmCwPaymentManager.endApply(jsonNode);
	}
}
