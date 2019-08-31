package com.winway.scm.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.hotent.base.feign.UCFeignService;
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
import com.hotent.base.model.CommonResult;
import com.hotent.base.model.WinwayResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.JsonUtil;
import com.hotent.base.util.StringUtil;
import com.winway.purchase.util.QuarterUtil;
import com.winway.scm.model.ScmXsBigContractProductSum;
import com.winway.scm.model.ScmXsFocusSend;
import com.winway.scm.model.ScmXsFocusSendProduct;
import com.winway.scm.persistence.manager.ScmXsFocusSendManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * <pre>
 *  
 * 描述：集中发货合同 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:44
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value = "/scm/scmXsFocusSend/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "scmXsFocusSendController 集中发货合同")
public class ScmXsFocusSendController extends BaseController {
	@Resource
	ScmXsFocusSendManager scmXsFocusSendManager;

	@Resource
	UCFeignService ucFeignService;
	/**
	 * 集中发货合同列表(分页条件查询)数据 @param request @return @throws Exception
	 * PageJson @exception
	 */
	@PostMapping("/list")
	@ApiOperation(value = "集中发货合同数据列表", httpMethod = "POST", notes = "获取集中发货合同列表,query必须添加货主ID进行筛选")
	public PageList<ScmXsFocusSend> list(
			@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
		return scmXsFocusSendManager.query(queryFilter);
	}

	/**
	 * 集中发货合同明细页面
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@GetMapping(value = "/get/{id}")
	@ApiOperation(value = "集中发货合同数据详情", httpMethod = "GET", notes = "集中发货合同数据详情")
	public ScmXsFocusSend get(@ApiParam(name = "id", value = "业务对象主键", required = true) @PathVariable String id)
			throws Exception {
		return scmXsFocusSendManager.get(id);
	}

	/**
	 * 集中发货商品表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/getApplyId/{applyId}")
	@ApiOperation(value="根据审批ID获取集中发货数据详情",httpMethod = "GET",notes = "集中发货商品表数据详情")
	public ScmXsFocusSend getApplyId(@ApiParam(name="applyId",value="审批ID", required = true)@PathVariable String applyId) throws Exception{
		return scmXsFocusSendManager.getApplyId(applyId);
	}
	
	/**
	 * 新增集中发货合同 @param scmXsFocusSend @throws Exception @return @exception
	 */
	@PostMapping(value = "save")
	@ApiOperation(value = "新增,更新集中发货合同数据", httpMethod = "POST", notes = "新增,更新集中发货合同数据")
	public CommonResult<String> save(
			@ApiParam(name = "scmXsFocusSend", value = "集中发货合同业务对象", required = true) @RequestBody ScmXsFocusSend scmXsFocusSend)
			throws Exception {
		String msg = "添加集中发货合同成功";
		scmXsFocusSend.setApprovalState("0");
		JsonNode user = ucFeignService.getUser(current(), "");
		String fullname = user.get("fullname").asText();
		scmXsFocusSend.setEntryPeople(fullname);
		scmXsFocusSend.setCreateDate(new Date());
		scmXsFocusSend.setCreatePerson(fullname);
		if(scmXsFocusSend.getFocusSendCode() == null || "".equals(scmXsFocusSend.getFocusSendCode()) || "null".equals(scmXsFocusSend.getFocusSendCode())) {
			throw new RuntimeException("集中发货合同编号不能为空");
		}
		if (StringUtil.isEmpty(scmXsFocusSend.getId())) {
			scmXsFocusSendManager.create(scmXsFocusSend);
		} else {
			scmXsFocusSendManager.update(scmXsFocusSend);
			msg = "更新集中发货合同成功";
		}
		return new CommonResult<String>(true,msg,scmXsFocusSend.getId());
	}

	/**
	 * 删除集中发货合同记录 @param id @throws Exception @return @exception
	 */
	@DeleteMapping(value = "remove/{id}")
	@ApiOperation(value = "删除集中发货合同记录", httpMethod = "DELETE", notes = "删除集中发货合同记录")
	public CommonResult<String> remove(@ApiParam(name = "id", value = "业务主键", required = true) @PathVariable String id)
			throws Exception {
		scmXsFocusSendManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}

	/**
	 * 批量删除集中发货合同记录 @param ids @throws Exception @return @exception
	 */
	@DeleteMapping(value = "/removes")
	@ApiOperation(value = "批量删除集中发货合同记录", httpMethod = "DELETE", notes = "批量删除集中发货合同记录")
	public CommonResult<String> removes(
			@ApiParam(name = "ids", value = "业务主键数组,多个业务主键之间用逗号分隔", required = true) @RequestParam String... ids)
			throws Exception {
		scmXsFocusSendManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}

	/**
	 * @param 集中发货申请
	 * @param request
	 * @return
	 * @throws Exception
	 *             flowKey:审批类型 sysCode:系统别名
	 *             instanceIdField:与实体类审批ID相同,controller层接到对象会有审批ID,处理业务逻辑后,保存对象即可
	 *             varKeys:脚本使用参数
	 */
	@PostMapping(value = "sendApply")
	@ApiOperation(value = "集中发货申请", httpMethod = "POST", notes = "选择经销商后,展示选中合同,点击新增按钮添加集中发货商品,添加完成后,提交时需要封装至集中发货对象中一起提交")
//	@Workflow(flowKey = "jzfhlc", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {"totalPrice","creditPrice"})
	public CommonResult<String> sendApply(
			@ApiParam(name = "ScmXsFocusSend", value = "经销商协议合作名单总表对象", required = true) @RequestBody ScmXsFocusSend scmXsFocusSend,
			HttpServletRequest request) throws Exception {
		JsonNode user = ucFeignService.getUser(current(), "");
		String fullname = user.get("fullname").asText();
		scmXsFocusSend.setEntryPeople(fullname);
		scmXsFocusSend.setCreateDate(new Date());
		scmXsFocusSend.setCreatePerson(fullname);
		scmXsFocusSendManager.sendApply(scmXsFocusSend);
		return new CommonResult<String>("申请成功");
	}

	@PostMapping(value = "/endApply")
	@ApiOperation(value = "集中发货通过处理", httpMethod = "POST", notes = "集中发货通过处理")
	public void endApply(@ApiParam(name = "params", value = "流程事件参数", required = true) @RequestBody String params,
			HttpServletRequest request) throws Exception {
		JsonNode jsonNode = JsonUtil.toJsonNode(params);
		scmXsFocusSendManager.endApply(jsonNode);
	}
	@PostMapping(value = "/rejectApply")
	@ApiOperation(value = "集中发货驳回处理", httpMethod = "POST", notes = "集中发货驳回处理")
	public void rejectApply(@ApiParam(name = "params", value = "流程事件参数", required = true) @RequestBody String params,
			HttpServletRequest request) throws Exception {
		
	}
	
	/**
	 * 集中发货合同明细页面
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@GetMapping(value = "/getCode")
	@ApiOperation(value = "获取集中发货单号", httpMethod = "GET", notes = "获取集中发货单号")
	public CommonResult<String> getCode()throws Exception {
		String code = QuarterUtil.getCode("JZFH");
		return new CommonResult(true,"获取成功",code);
	}
	
	/**
	 * 集中发货合同明细页面
	 * @param
	 * @return
	 * @throws Exception
	 * ModelAndView
	 */
	@GetMapping(value = "/getProduct/{commerceCode}/{ownerId}/{agreementSummaryId}/{medicineType}")
	@ApiOperation(value = "获取集中发货可发货商品列表", httpMethod = "GET", notes = "获取集中发货可发货商品列表")
	public CommonResult<List<ScmXsBigContractProductSum>> getProduct(@ApiParam(name = "commerceCode", value = "商业编号", required = true)@PathVariable String commerceCode,
			@ApiParam(name = "ownerId", value = "货主ID", required = true)@PathVariable String ownerId,
			@ApiParam(name = "agreementSummaryId", value = "协议ID", required = true)@PathVariable String agreementSummaryId,
			@ApiParam(name = "medicineType", value = "协议ID", required = true)@PathVariable String medicineType)throws Exception {
		List<ScmXsBigContractProductSum> product = scmXsFocusSendManager.getProduct(commerceCode,ownerId,agreementSummaryId,medicineType);
		return new CommonResult<List<ScmXsBigContractProductSum>>(true,"获取成功",product) ;
	}
}
