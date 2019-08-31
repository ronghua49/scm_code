package com.winway.scm.controller;

import com.winway.scm.model.ScmCwBAPaymentDiscount;
import com.winway.scm.model.ScmCwBAStorageDiscount;
import com.winway.scm.model.ScmCwPaymentRB;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import com.winway.scm.persistence.manager.ScmCwBusinessAccountRBManager;
import com.winway.scm.model.ScmCwBusinessAccountRB;
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
 * 
 * <pre> 
 * 描述：经销商RB核算总表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-09 18:18:05
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmCwBusinessAccountRB/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmCwBusinessAccountRBController 经销商RB核算总表 ")
public class ScmCwBusinessAccountRBController extends BaseController{
	@Resource
	ScmCwBusinessAccountRBManager scmCwBusinessAccountRBManager;
	@Resource
    UCFeignService ucFeignService;
	/**
	 * 经销商RB核算总表列表(分页条件查询)数据
	 * @param
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="经销商RB核算总表数据列表", httpMethod = "POST", notes = "获取经销商RB核算总表列表")
	public PageList<ScmCwBusinessAccountRB> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmCwBusinessAccountRBManager.query(queryFilter);
	}
	
	/**
	 * 经销商RB核算总表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="经销商RB核算总表数据详情",httpMethod = "GET",notes = "经销商RB核算总表数据详情")
	public ScmCwBusinessAccountRB get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmCwBusinessAccountRBManager.get(id);
	}
	
	/**
	 * 经销商RB核算总表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/getByApprovalId/{approvalId}")
	@ApiOperation(value="根据审批ID获取数据详情",httpMethod = "GET",notes = "根据审批ID获取数据详情")
	public ScmCwBusinessAccountRB getByApprovalId(@ApiParam(name="approvalId",value="业务对象主键", required = true)@PathVariable String approvalId) throws Exception{
		return scmCwBusinessAccountRBManager.getByApprovalId(approvalId);
	}
	
    /**
	 * 新增经销商RB核算总表
	 * @param scmCwBusinessAccountRB
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新经销商RB核算总表数据", httpMethod = "POST", notes = "新增,更新经销商RB核算总表数据")
	public CommonResult<String> save(@ApiParam(name="scmCwBusinessAccountRB",value="经销商RB核算总表业务对象", required = true)@RequestBody ScmCwBusinessAccountRB scmCwBusinessAccountRB) throws Exception{
		String msg = "添加经销商RB核算总表成功";
		if(StringUtil.isEmpty(scmCwBusinessAccountRB.getId())){
			JsonNode user = ucFeignService.getUser(current(), "");
			String userName = user.get("fullname").asText();
			scmCwBusinessAccountRB.setCreatePsersion(userName);
			scmCwBusinessAccountRB.setCreateDate(new Date());
			scmCwBusinessAccountRB.setApprovalState("0");
			scmCwBusinessAccountRBManager.create(scmCwBusinessAccountRB);
		}else{
			scmCwBusinessAccountRBManager.update(scmCwBusinessAccountRB);
			 msg = "更新经销商RB核算总表成功";
		}
		return new CommonResult<String>(true,msg,scmCwBusinessAccountRB.getId());
	}
	/**
	 * 新增经销商RB核算总表
	 * @param scmCwBusinessAccountRB
	 * @throws Exception 
	 * @return
	 * @exception 
	 *
	 */
	@PostMapping(value="/sendApply")
	@ApiOperation(value = "RB核算发起申请", httpMethod = "POST", notes = "新增,更新经销商RB核算总表数据")
//    @Workflow(flowKey = "jxsRBhssp", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {})
	public CommonResult<String> sendApply(@ApiParam(name="scmCwBusinessAccountRB",value="经销商RB核算总表业务对象", required = true)@RequestBody ScmCwBusinessAccountRB scmCwBusinessAccountRB) throws Exception{
		scmCwBusinessAccountRBManager.sendApply(scmCwBusinessAccountRB);
		return new CommonResult<String>("发起成功");
	}
	
	/**
	 * 新增经销商RB核算总表
	 * @param
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	 @PostMapping(value = "/endApply")
	 @ApiOperation(value = "商业首营审批流程通过", httpMethod = "POST", notes = "商业首营审批流程通过,修改审批状态")
	 public void endApply(@ApiParam(name = "params", value = "流程事件参数", required = true) @RequestBody String params,HttpServletRequest request) throws Exception {
        JsonNode jsonNode = JsonUtil.toJsonNode(params);
        scmCwBusinessAccountRBManager.endApply(jsonNode);
	 }
	
	/**
	 * 删除经销商RB核算总表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除经销商RB核算总表记录", httpMethod = "DELETE", notes = "删除经销商RB核算总表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmCwBusinessAccountRBManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除经销商RB核算总表记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除经销商RB核算总表记录", httpMethod = "DELETE", notes = "批量删除经销商RB核算总表记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmCwBusinessAccountRBManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
	/**
	 * 获取付款折扣和储运管理折扣 数据
	 * @param
	 * @throws Exception
	 * @return
	 * @exception
	 */
	@PostMapping(value="/discountData")
	@ApiOperation(value = "获取核算折扣明细", httpMethod = "POST", notes = "付款折扣和储运管理折扣")
	public CommonResult<Map<String,List>> discountData(@ApiParam(name = "scmCwPaymentRB", value = "经销商RB支付申请总表业务对象", required = true) @RequestBody ScmCwPaymentRB scmCwPaymentRB) throws Exception{
		Map<String,Object> paramMap = new HashedMap();
		paramMap.put("businessDivisionId", scmCwPaymentRB.getBusinessDivisionId());
		paramMap.put("provinceId", scmCwPaymentRB.getProvinceId());
		paramMap.put("year", scmCwPaymentRB.getYear());
		paramMap.put("section", scmCwPaymentRB.getSection());
		paramMap.put("ownerId",scmCwPaymentRB.getOwnerId());
		List<ScmCwBusinessAccountRB> scmCwBusinessAccountRBS = scmCwBusinessAccountRBManager.queryByParamMap(paramMap);
		Map<String,List> map = new HashedMap();
		for(ScmCwBusinessAccountRB rb:scmCwBusinessAccountRBS){
			List<ScmCwBAPaymentDiscount> scmCwBAPaymentDiscountList = rb.getScmCwBAPaymentDiscountList();
			map.put("ScmCwBAPaymentDiscount",scmCwBAPaymentDiscountList);
			List<ScmCwBAStorageDiscount> scmCwBAStorageDiscountList = rb.getScmCwBAStorageDiscountList();
			map.put("ScmCwBAStorageDiscount",scmCwBAStorageDiscountList);
		}
		return new CommonResult<>(true, "获取折扣列表数据成功",map);
	}
}
