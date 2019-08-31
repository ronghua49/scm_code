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
import com.winway.scm.persistence.manager.ScmGspSideEffectsManager;
import com.winway.scm.model.ScmGspSideEffects;
import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;

import java.util.Date;

/**
 * 
 * <pre> 
 * 描述：药品不良反应事件管理 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-18 18:21:20
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmGspSideEffects/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmGspSideEffectsController 药品不良反应事件管理")
public class ScmGspSideEffectsController extends BaseController{
	@Resource
	ScmGspSideEffectsManager scmGspSideEffectsManager;
	@Resource
	UCFeignService ucFeignService;
	
	/**
	 * 药品不良反应事件管理列表(分页条件查询)数据
	 * @param
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="药品不良反应事件管理数据列表", httpMethod = "POST", notes = "获取药品不良反应事件管理列表")
	public PageList<ScmGspSideEffects> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmGspSideEffectsManager.query(queryFilter);
	}
	
	/**
	 * 药品不良反应事件管理明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="药品不良反应事件管理数据详情",httpMethod = "GET",notes = "药品不良反应事件管理数据详情")
	public ScmGspSideEffects get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmGspSideEffectsManager.get(id);
	}

	/**
	 * 药品不良反应事件管理审批明细页面
	 * @param id
	 * @return
	 * @throws Exception
	 * ModelAndView
	 */
	@GetMapping(value="/getDetail/{id}")
	@ApiOperation(value="药品不良反应事件管理数据详情",httpMethod = "GET",notes = "药品不良反应事件管理数据详情")
	public ScmGspSideEffects getDetail(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmGspSideEffectsManager.getByApprovalId(id);
	}

	
    /**
	 * 新增药品不良反应事件管理
	 * @param scmGspSideEffects
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新药品不良反应事件管理数据", httpMethod = "POST", notes = "新增,更新药品不良反应事件管理数据")
	public CommonResult<ScmGspSideEffects> save(@ApiParam(name="scmGspSideEffects",value="药品不良反应事件管理业务对象", required = true)@RequestBody ScmGspSideEffects scmGspSideEffects) throws Exception{
		String msg = "添加药品不良反应事件管理成功";
		if(StringUtil.isEmpty(scmGspSideEffects.getId())){
			scmGspSideEffects.setApprovalState("0");
			setApprovalBase(scmGspSideEffects);
			scmGspSideEffectsManager.create(scmGspSideEffects);
		}else{
			scmGspSideEffectsManager.update(scmGspSideEffects);
			 msg = "更新药品不良反应事件管理成功";
		}
		return new CommonResult<ScmGspSideEffects>(true,msg,scmGspSideEffects);
	}

	/**
	 * 药品不良反应发起申请
	 *
	 * @param scmGspSideEffects
	 * @return
	 * @throws Exception
	 * @throws
	 */
	@PostMapping(value = "sendApply")
	@ApiOperation(value = "药品不良反应发起申请", httpMethod = "POST", notes = "药品不良反应数据")
//	@Workflow(flowKey = "ypblfysp", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {})
	public CommonResult<String> sendApply(@ApiParam(name="scmGspSideEffects",value="药品不良反应事件管理业务对象", required = true)@RequestBody ScmGspSideEffects scmGspSideEffects) throws Exception {
		setApprovalBase(scmGspSideEffects);
		scmGspSideEffectsManager.sendApply(scmGspSideEffects);
		return new CommonResult<String>("申请成功");
	}


	/**
	 * 药品不良反应结束审批
	 *
	 * @param params
	 * @param request
	 * @throws Exception
	 */
	@PostMapping(value = "/endApply")
	@ApiOperation(value = "药品不良反应审批通过及驳回", httpMethod = "POST", notes = "药品不良反应报损通过及驳回")
	public void endApply(@ApiParam(name = "params", value = "流程事件参数", required = true) @RequestBody String params,
						 HttpServletRequest request) throws Exception {
		JsonNode jsonNode = JsonUtil.toJsonNode(params);
		scmGspSideEffectsManager.endApply(jsonNode);
	}




	/**
	 * 删除药品不良反应事件管理记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除药品不良反应事件管理记录", httpMethod = "DELETE", notes = "删除药品不良反应事件管理记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmGspSideEffectsManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除药品不良反应事件管理记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除药品不良反应事件管理记录", httpMethod = "DELETE", notes = "批量删除药品不良反应事件管理记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmGspSideEffectsManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
	private void setApprovalBase(ScmGspSideEffects scmGspSideEffects) {
		JsonNode user = ucFeignService.getUser(current(), "");
		String fullname = user.get("fullname").asText();
		scmGspSideEffects.setCreatePersion(fullname);
		scmGspSideEffects.setCreateDate(new Date());
	}

}
