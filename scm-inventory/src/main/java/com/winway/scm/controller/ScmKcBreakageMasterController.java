package com.winway.scm.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.annotation.Workflow;
import com.hotent.base.feign.UCFeignService;
import com.hotent.base.util.JsonUtil;
import com.winway.purchase.util.QuarterUtil;
import com.winway.purchase.vo.ReturnWmsVo;
import com.winway.purchase.vo.ScmWmsReceipt;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
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
import com.winway.scm.persistence.manager.ScmKcBreakageMasterManager;
import com.winway.scm.vo.BreakageRecord;
import com.winway.scm.model.ScmKcBreakageMaster;
import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;

import java.util.Date;
import java.util.Map;

/**
 * 
 * <pre> 
 * 描述：不合格产品报损主表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-23 17:00:46
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmKcBreakageMaster/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmKcBreakageMasterController 不合格产品报损主表")
public class ScmKcBreakageMasterController extends BaseController{
	@Resource
	ScmKcBreakageMasterManager scmKcBreakageMasterManager;

	@Resource
	UCFeignService ucFeignService;
	/**
	 * 不合格产品报损主表列表(分页条件查询)数据
	 * @param
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="不合格产品报损主表数据列表", httpMethod = "POST", notes = "获取不合格产品报损主表列表")
	public PageList<ScmKcBreakageMaster> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmKcBreakageMasterManager.query(queryFilter);
	}
	/**
	 * 不合格产品报损记录
	 * @param
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/breakageRecord")
	@ApiOperation(value="不合格产品报损主表数据列表", httpMethod = "POST", notes = "获取不合格产品报损主表列表")
	public PageList<BreakageRecord> breakageRecord(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmKcBreakageMasterManager.breakageRecord(queryFilter);
	}
	
	/**
	 * 不合格产品报损主表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="不合格产品报损主表数据详情",httpMethod = "GET",notes = "不合格产品报损主表数据详情")
	public ScmKcBreakageMaster get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmKcBreakageMasterManager.get(id);
	}
	/**
	 * 不合格产品报损主表明细页面
	 * @param approvalId
	 * @return
	 * @throws Exception
	 * ModelAndView
	 */
	@GetMapping(value="/getDetail/{approvalId}")
	@ApiOperation(value="不合格产品报损主表数据详情",httpMethod = "GET",notes = "不合格产品报损主表数据详情")
	public ScmKcBreakageMaster getDetail(@ApiParam(name="approvalId",value="业务对象主键", required = true)@PathVariable String approvalId) throws Exception{
		return scmKcBreakageMasterManager.getDetail(approvalId);
	}

    /**
	 * 新增不合格产品报损主表
	 * @param scmKcBreakageMaster
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新不合格产品报损主表数据", httpMethod = "POST", notes = "新增,更新不合格产品报损主表数据")
	public CommonResult<ScmKcBreakageMaster> save(@ApiParam(name="scmKcBreakageMaster",value="不合格产品报损主表业务对象", required = true)@RequestBody ScmKcBreakageMaster scmKcBreakageMaster) throws Exception{
		String msg = "添加不合格产品报损主表成功";
		setApprovalBase(scmKcBreakageMaster);
		scmKcBreakageMaster.setApprovalState("0");
		if(StringUtil.isEmpty(scmKcBreakageMaster.getId())){
			scmKcBreakageMasterManager.create(scmKcBreakageMaster);
		}else{
			scmKcBreakageMasterManager.update(scmKcBreakageMaster);
			 msg = "更新不合格产品报损主表成功";
		}
		return new CommonResult<>(true,msg,scmKcBreakageMaster);
	}

	/**
	 * 报损主表发起申请
	 *
	 * @param scmKcBreakageMaster
	 * @return
	 * @throws Exception
	 * @throws
	 */
	@PostMapping(value = "sendApply")
	@ApiOperation(value = "不合格商品报损发起申请", httpMethod = "POST", notes = "报损数据")
	@Workflow(flowKey = "bhgbs", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {})
	public CommonResult<String> sendApply(@ApiParam(name = "scmKcBreakageMaster", value = "不合格产品报损主表业务对象", required = true) @RequestBody ScmKcBreakageMaster scmKcBreakageMaster) throws Exception {
		setApprovalBase(scmKcBreakageMaster);
		scmKcBreakageMaster.setApprovalState("1");
		scmKcBreakageMasterManager.sendApply(scmKcBreakageMaster);
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
	@ApiOperation(value = "不合格商品报损审批通过及驳回", httpMethod = "POST", notes = "不合格商品报损通过及驳回")
	public void endApply(@ApiParam(name = "params", value = "流程事件参数", required = true) @RequestBody String params,
						 HttpServletRequest request) throws Exception {
		JsonNode jsonNode = JsonUtil.toJsonNode(params);
		scmKcBreakageMasterManager.endApply(jsonNode);
	}

	/**
	 * 删除不合格产品报损主表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除不合格产品报损主表记录", httpMethod = "DELETE", notes = "删除不合格产品报损主表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmKcBreakageMasterManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除不合格产品报损主表记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除不合格产品报损主表记录", httpMethod = "DELETE", notes = "批量删除不合格产品报损主表记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmKcBreakageMasterManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
	/**获取不合格药品审批 报损单号和报损人员
	 *
	 * @param
	 * @throws Exception
	 * @return
	 * @exception
	 */
	@PostMapping(value="/getCode")
	@ApiOperation(value = "生成报损单号和报损人员", httpMethod = "POST", notes = "生成当前审批的报损单号和报损人员")
	public Map<String,String> getCode() throws Exception{
		Map<String,String> map = new HashedMap();
		String fullname = ucFeignService.getUser(current(), "").get("fullname").asText();
		String bsdh = QuarterUtil.getCode("BSDH");
		map.put("breakageCode",bsdh);
		map.put("createPersion",fullname);
		return map;
	}

	private void setApprovalBase(ScmKcBreakageMaster scmKcBreakageMaster) {
		JsonNode user = ucFeignService.getUser(current(), "");
		String fullname = user.get("fullname").asText();
		scmKcBreakageMaster.setBreakageDate(new Date());
		scmKcBreakageMaster.setCreatePersion(fullname);
		scmKcBreakageMaster.setCreateDate(new Date());
	}
	
	/**
	 * 报损回执
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/receipt")
	@ApiOperation(value="报损回执", httpMethod = "POST", notes = "报损回执")
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
			scmKcBreakageMasterManager.receipt(scmWmsReceipt);
		}catch (Exception e) {
			return new ReturnWmsVo("500",e.getMessage());
		}
		return new ReturnWmsVo("200","处理成功");
	}
    
}
