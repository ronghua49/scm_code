package com.winway.scm.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.annotation.Workflow;
import com.hotent.base.controller.BaseController;
import com.hotent.base.feign.UCFeignService;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.JsonUtil;
import com.hotent.base.util.StringUtil;
import com.winway.scm.model.ScmFhShipmentsDatailMaster;
import com.winway.scm.persistence.manager.ScmFhShipmentsDatailMasterManager;
import com.winway.scm.vo.ScmFhShipmentsDataiAndMessageVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 * <pre> 
 * 描述：发货明细总表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-02-15 17:31:50
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmFhShipmentsDatailMaster/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmFhShipmentsDatailMasterController 发货明细总表")
public class ScmFhShipmentsDatailMasterController extends BaseController{
	@Resource
	ScmFhShipmentsDatailMasterManager scmFhShipmentsDatailMasterManager;
	@Resource
	UCFeignService ucFeignService;
	/**
	 * 发货明细总表列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="发货明细总表数据列表", httpMethod = "POST", notes = "获取发货明细总表列表")
	public PageList<ScmFhShipmentsDatailMaster> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmFhShipmentsDatailMasterManager.query(queryFilter);
	}
	
	/**
	 * 发货详细数据列表
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/allShipmentMessage")
	@ApiOperation(value="发货详细数据列表", httpMethod = "POST", notes = "发货详细数据列表")
	public PageList<ScmFhShipmentsDataiAndMessageVo> allShipmentMessage(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmFhShipmentsDatailMasterManager.allShipmentMessage(queryFilter);
	}
	
	/**
	 * 发货明细总表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="发货明细总表数据详情",httpMethod = "GET",notes = "发货明细总表数据详情")
	public ScmFhShipmentsDatailMaster get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmFhShipmentsDatailMasterManager.get(id);
	}
	
    /**
	 * 新增发货明细总表。
	 * @param scmFhShipmentsDatailMaster
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新发货明细总表数据", httpMethod = "POST", notes = "新增,更新发货明细总表数据")
	public CommonResult<String> save(@ApiParam(name="scmFhShipmentsDatailMaster",value="发货明细总表业务对象", required = true)@RequestBody ScmFhShipmentsDatailMaster scmFhShipmentsDatailMaster) throws Exception{
		if(scmFhShipmentsDatailMaster.getBusinessDivision() == null || "".equals(scmFhShipmentsDatailMaster.getBusinessDivision()) || "null".equals(scmFhShipmentsDatailMaster.getBusinessDivision())) {
			throw new RuntimeException("商务分区不能为空");
		}
		if(scmFhShipmentsDatailMaster.getCommerceId() == null || "".equals(scmFhShipmentsDatailMaster.getCommerceId()) || "null".equals(scmFhShipmentsDatailMaster.getCommerceId())) {
			throw new RuntimeException("商业不能为空");
		}
		String msg = "添加发货明细总表成功";
		if(StringUtil.isEmpty(scmFhShipmentsDatailMaster.getId())){
			JsonNode user = ucFeignService.getUser(current(), "");
			String fullname = user.get("fullname").asText();
			scmFhShipmentsDatailMaster.setCreatePersion(fullname);
			scmFhShipmentsDatailMaster.setApprovalState("0");
			scmFhShipmentsDatailMasterManager.create(scmFhShipmentsDatailMaster);
		}else{
			scmFhShipmentsDatailMasterManager.update(scmFhShipmentsDatailMaster);
			 msg = "更新发货明细总表成功";
		}
		return new CommonResult<String>(msg);
	}




	
	/**
	 * 删除发货明细总表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除发货明细总表记录", httpMethod = "DELETE", notes = "删除发货明细总表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmFhShipmentsDatailMasterManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除发货明细总表记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除发货明细总表记录", httpMethod = "DELETE", notes = "批量删除发货明细总表记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmFhShipmentsDatailMasterManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
	
	/**
	 * @param request
	 * @return
	 * @throws Exception
	 * flowKey:审批类型
	 * sysCode:系统别名
	 * instanceIdField:与实体类审批ID相同,controller层接到对象会有审批ID,处理业务逻辑后,保存对象即可
	 * varKeys:脚本使用参数
	 */
	@PostMapping(value = "sendApply")
    @ApiOperation(value = "发货明细申请", httpMethod = "POST", notes = "提交时需要在scmFhShipmentsDatail对象中封装好发货明细对象提交")
    @Workflow(flowKey = "fhzlsp", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {})
    public CommonResult<ScmFhShipmentsDatailMaster> sendApply(
            @ApiParam(name = "scmFhShipmentsDatail", value = "发货明细主表及子表数据", required = true) @RequestBody ScmFhShipmentsDatailMaster scmFhShipmentsDatailMaster,
            HttpServletRequest request) throws Exception {
		JsonNode user = ucFeignService.getUser(current(), "");
		String fullname = user.get("fullname").asText();
		scmFhShipmentsDatailMaster.setCreatePersion(fullname);
		scmFhShipmentsDatailMasterManager.sendApply(scmFhShipmentsDatailMaster);
	   return new CommonResult<ScmFhShipmentsDatailMaster>(true, "审批发起成功", scmFhShipmentsDatailMaster);
	}

	/**
	 * 结束审批
	 * @param params
	 * @param request
	 * @throws Exception
	 */
    @PostMapping(value = "/endApply")
    @ApiOperation(value = "发货明细审批通过及驳回", httpMethod = "POST", notes = "发货明细审批通过及驳回")
    public void endApply(@ApiParam(name = "params", value = "流程事件参数", required = true) @RequestBody String params, 
    		HttpServletRequest request) throws Exception {
    	JsonNode jsonNode = JsonUtil.toJsonNode(params);
    	scmFhShipmentsDatailMasterManager.endApply(jsonNode);
    }

	/**
	 * 发货指令明细页面
	 * @param applyid 审批id
	 * @return
	 * @throws Exception
	 * ModelAndView
	 */
	@GetMapping(value="/getByApplyId/{applyid}")
	@ApiOperation(value="发货指令数据详情",httpMethod = "GET",notes = "发货指令数据详情")
	public CommonResult<ScmFhShipmentsDatailMaster> getByApplyId(@ApiParam(name="applyid",value="审批id", required = true)@PathVariable String applyid) throws Exception{
		return new CommonResult<ScmFhShipmentsDatailMaster>(true,"获取成功",scmFhShipmentsDatailMasterManager.getByApplyId(applyid));
	}



}
