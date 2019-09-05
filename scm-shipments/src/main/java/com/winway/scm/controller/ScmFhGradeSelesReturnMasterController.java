package com.winway.scm.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.annotation.Workflow;
import com.hotent.base.feign.UCFeignService;
import com.hotent.base.util.JsonUtil;
import com.winway.scm.model.ScmFhMarketSelesReturnMaster;
import com.winway.scm.vo.GradeSelesReturnUpdateVo;
import com.winway.scm.vo.WmsfindcgrkInVo;

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
import com.winway.scm.persistence.manager.ScmFhGradeSelesReturnMasterManager;
import com.winway.purchase.vo.ReturnWmsVo;
import com.winway.purchase.vo.ScmWmsReceipt;
import com.winway.scm.model.ScmFhGradeSelesReturn;
import com.winway.scm.model.ScmFhGradeSelesReturnMaster;
import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;

import java.util.Date;

/**
 * 
 * <pre> 
 * 描述：当次退货主表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-13 15:47:33
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmFhGradeSelesReturnMaster/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmFhGradeSelesReturnMasterController 当次退货总表")
public class ScmFhGradeSelesReturnMasterController extends BaseController{
	@Resource
	ScmFhGradeSelesReturnMasterManager scmFhGradeSelesReturnMasterManager;
	@Resource
	UCFeignService ucFeignService;


	/**
	 * 当次退货主表列表(分页条件查询)数据
	 * @param
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="当次退货主表数据列表", httpMethod = "POST", notes = "获取当次退货主表列表")
	public PageList<ScmFhGradeSelesReturnMaster> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmFhGradeSelesReturnMasterManager.query(queryFilter);
	}
	
	/**
	 * 当次退货主表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="当次退货主表数据详情",httpMethod = "GET",notes = "当次退货主表数据详情")
	public ScmFhGradeSelesReturnMaster get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmFhGradeSelesReturnMasterManager.get(id);
	}



	/**
	 * 当此退货更新页面 数据列表数据
	 * @param id
	 * @return
	 * @throws Exception
	 * ModelAndView
	 */
	@GetMapping(value="/update/{id}")
	@ApiOperation(value="当次退货更新",httpMethod = "GET",notes = "当次退货更新页面数据列表详情")
	public GradeSelesReturnUpdateVo getUpdateInfo(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmFhGradeSelesReturnMasterManager.getUpdateInfo(id);
	}


	/**
	 * 当此退货审批页数据
	 * @param id
	 * @return
	 * @throws Exception
	 * ModelAndView
	 */
	@GetMapping(value="/detail/{approvalId}")
	@ApiOperation(value="当次退货审批",httpMethod = "GET",notes = "当此退货审批页面")
	public GradeSelesReturnUpdateVo getDetail(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String approvalId) throws Exception{
		return scmFhGradeSelesReturnMasterManager.getDatailByApprovalId(approvalId);
	}

    /**
	 * 新增当次退货主表
	 * @param scmFhGradeSelesReturnMaster
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新当次退货主表数据", httpMethod = "POST", notes = "新增,更新当次退货主表数据")
	public CommonResult<ScmFhGradeSelesReturnMaster> save(@ApiParam(name="scmFhGradeSelesReturnMaster",value="当次退货主表业务对象", required = true)@RequestBody ScmFhGradeSelesReturnMaster scmFhGradeSelesReturnMaster) throws Exception{
		String msg = "保存单次退货成功";
		JsonNode user = ucFeignService.getUser(current(), "");
		String fullname = user.get("fullname").asText();
		scmFhGradeSelesReturnMaster.setCreatePersion(fullname);
		scmFhGradeSelesReturnMaster.setApprovalState("0");
		scmFhGradeSelesReturnMaster.setCreateDate(new Date());
		if(StringUtil.isEmpty(scmFhGradeSelesReturnMaster.getId())){
			scmFhGradeSelesReturnMasterManager.create(scmFhGradeSelesReturnMaster);
		}else{
			scmFhGradeSelesReturnMasterManager.update(scmFhGradeSelesReturnMaster);
			 msg = "更新单次退货主表成功";
		}
		return new CommonResult<ScmFhGradeSelesReturnMaster>(true,msg,scmFhGradeSelesReturnMaster);
	}
	
	/**
	 * 删除当次退货主表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除当次退货主表记录", httpMethod = "DELETE", notes = "删除当次退货主表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmFhGradeSelesReturnMasterManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除当次退货主表记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除当次退货主表记录", httpMethod = "DELETE", notes = "批量删除当次退货主表记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmFhGradeSelesReturnMasterManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}


	/**
	 * 发起申请
	 *
	 * @param scmFhGradeSelesReturnMaster
	 * @return
	 * @throws Exception
	 * @throws
	 */
	@PostMapping(value = "sendApply")
	@ApiOperation(value = "单次退货发起申请", httpMethod = "POST", notes = "发起申请时需要将退货商品封装到主表 一并提交")
//	@Workflow(flowKey = "dcthsp", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {})
	public CommonResult<ScmFhGradeSelesReturnMaster> sendApply(@ApiParam(name = "scmFhMarketSelesReturnMaster", value = "销售退货主表业务对象", required = true) @RequestBody ScmFhGradeSelesReturnMaster scmFhGradeSelesReturnMaster) throws Exception {
		JsonNode user = ucFeignService.getUser(current(), "");
		String fullname = user.get("fullname").asText();
		scmFhGradeSelesReturnMaster.setCreatePersion(fullname);
		
		scmFhGradeSelesReturnMaster.setCreateDate(new Date());
		ScmFhGradeSelesReturnMaster gradeSelesReturnMaster = scmFhGradeSelesReturnMasterManager.sendApply(scmFhGradeSelesReturnMaster);
		return new CommonResult<ScmFhGradeSelesReturnMaster>(true,"单次退货审批发起成功",gradeSelesReturnMaster);
	}

	/**
	 * 结束审批
	 *
	 * @param params
	 * @param request
	 * @throws Exception
	 */
	@PostMapping(value = "/endApply")
	@ApiOperation(value = "单次退货审批通过及驳回", httpMethod = "POST", notes = "单次退货审批通过及驳回")
	public void endApply(@ApiParam(name = "params", value = "流程事件参数", required = true) @RequestBody String params,
						 HttpServletRequest request) throws Exception {
		JsonNode jsonNode = JsonUtil.toJsonNode(params);
		scmFhGradeSelesReturnMasterManager.endApply(jsonNode);
	}
	
	/**
	 * 当次退货回执
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/receipt")
	@ApiOperation(value="当次退货回执", httpMethod = "POST", notes = "当次退货回执")
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
			scmFhGradeSelesReturnMasterManager.receipt(scmWmsReceipt);
		}catch (Exception e) {
			return new ReturnWmsVo("500",e.getMessage());
		}
		return new ReturnWmsVo("200","处理成功");
	}
	
	
	/**
	 * 当次退货明细数据获取sap
	 * @param
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/gradeSelesReturnDatail")
	@ApiOperation(value="sap根据当次退货单号获取当次退货明细", httpMethod = "POST", notes = "sap根据当次退货单号获取当次退货明细")
	public PageList<ScmFhGradeSelesReturn> gradeSelesReturnDatail(@ApiParam(name="wmsfindcgrkInVo",value="订单号")@RequestBody WmsfindcgrkInVo wmsfindcgrkInVo) throws Exception{
		return scmFhGradeSelesReturnMasterManager.gradeSelesReturnDatail(wmsfindcgrkInVo.getOrderCode());
	}
	
}
