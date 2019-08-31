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
import com.winway.purchase.util.QuarterUtil;
import com.winway.scm.model.ScmXsBigContractAllot;
import com.winway.scm.persistence.dao.ScmXsBigContractAllotDao;
import com.winway.scm.persistence.manager.ScmXsBigContractAllotManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <pre> 
 * 描述：大合同分配表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:44
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmXsBigContractAllot/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmXsBigContractAllotController 大合同分配表")
public class ScmXsBigContractAllotController extends BaseController{
	@Resource
	ScmXsBigContractAllotManager scmXsBigContractAllotManager;
	@Resource
	ScmXsBigContractAllotDao scmXsBigContractAllotDao;
	@Resource
	UCFeignService ucFeignService;
	/**
	 * 大合同分配表列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="大合同分配表数据列表", httpMethod = "POST", notes = "获取大合同分配表列表,query必须添加货主ID进行筛选")
	public PageList<ScmXsBigContractAllot> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmXsBigContractAllotManager.query(queryFilter);
	}
	
	/**
	 * 大合同分配表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="大合同分配表数据详情",httpMethod = "GET",notes = "大合同分配表数据详情")
	public ScmXsBigContractAllot get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmXsBigContractAllotManager.get(id);
	}
	
	/**
	 * 大合同分配表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/getByApplyId/{applyId}")
	@ApiOperation(value="大合同分配表数据详情",httpMethod = "GET",notes = "大合同分配表数据详情")
	public ScmXsBigContractAllot getByApplyId(@ApiParam(name="applyId",value="业务对象主键", required = true)@PathVariable String applyId) throws Exception{
		return scmXsBigContractAllotManager.getByApplyId(applyId);
	}
	
	
    /**
	 * 新增大合同分配表
	 * @param scmXsBigContractAllot
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新大合同分配表数据", httpMethod = "POST", notes = "新增,更新大合同分配表数据")
	public CommonResult<String> save(@ApiParam(name="scmXsBigContractAllot",value="大合同分配表业务对象", required = true)@RequestBody ScmXsBigContractAllot scmXsBigContractAllot) throws Exception{
		String msg = "添加大合同分配表成功";
		scmXsBigContractAllot.setApprovalState("0");
		JsonNode user = ucFeignService.getUser(current(), "");
		String userName = user.get("fullname").asText();
		scmXsBigContractAllot.setCreatePersion(userName);
		scmXsBigContractAllot.setCreateDate(new Date());
		
		if(StringUtil.isEmpty(scmXsBigContractAllot.getId())){
			scmXsBigContractAllotManager.create(scmXsBigContractAllot);
		}else{
			scmXsBigContractAllotManager.update(scmXsBigContractAllot);
			 msg = "更新大合同分配表成功";
		}
		return new CommonResult<String>(true,msg,scmXsBigContractAllot.getId());
	}
	
	/**
	 * 删除大合同分配表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除大合同分配表记录", httpMethod = "DELETE", notes = "删除大合同分配表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmXsBigContractAllotManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除大合同分配表记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除大合同分配表记录", httpMethod = "DELETE", notes = "批量删除大合同分配表记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmXsBigContractAllotManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
	
	@PostMapping(value = "sendApply")
    @ApiOperation(value = "大合同分配申请", httpMethod = "POST", notes = "提交时需要在ScmXsBigContractAllot对象中封装好详细数据以及列表数据")
//    @Workflow(flowKey = "dhtfpsp", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {"totalPrice","creditPrice"})
    public CommonResult<String> sendApply(
            @ApiParam(name = "ScmXsAgreementSummary", value = "经销商协议合作名单总表对象", required = true) @RequestBody ScmXsBigContractAllot scmXsBigContractAllot,
            HttpServletRequest request) throws Exception {
		JsonNode user = ucFeignService.getUser(current(), "");
		String userName = user.get("fullname").asText();
		scmXsBigContractAllot.setCreatePersion(userName);
		scmXsBigContractAllot.setCreateDate(new Date());
		String id = scmXsBigContractAllotManager.sendApply(scmXsBigContractAllot);
	   return new CommonResult<String>(true, "发起申请成功", id);
	}
	
    @PostMapping(value = "/endApply")
    @ApiOperation(value = "大合同分配审批通过及驳回", httpMethod = "POST", notes = "经销商协议合作名单审批通过及驳回")
    public void endApply(@ApiParam(name = "params", value = "流程事件参数", required = true) @RequestBody String params, 
    		HttpServletRequest request) throws Exception {
    	JsonNode jsonNode = JsonUtil.toJsonNode(params);
    	scmXsBigContractAllotManager.endApply(jsonNode);
    }
    
	/**
	 * 大合同分配获取编号
	 * @param id
	 * @return
	 * @throws Exception
	 * ModelAndView
	 */
	@GetMapping(value = "/getCode")
	@ApiOperation(value = "大合同分配获取编号", httpMethod = "GET", notes = "大合同分配获取编号")
	public CommonResult<String> getCode()throws Exception {
		String code = QuarterUtil.getCode("HTFP");
		return new CommonResult(true,"获取成功",code);
	}
    
}
