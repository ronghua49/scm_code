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
import com.winway.scm.model.ScmXsBigContract;
import com.winway.scm.persistence.manager.ScmXsBigContractManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <pre> 
 * 描述：大合同 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:44
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmXsBigContract/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmXsBigContractController 大合同")
public class ScmXsBigContractController extends BaseController{
	@Resource
	ScmXsBigContractManager scmXsBigContractManager;
	@Resource
	UCFeignService ucFeignService;
	
	/**
	 * 大合同列表(分页条件查询)数据
	 * @param
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="大合同数据列表", httpMethod = "POST", notes = "获取大合同列表,query必须添加货主ID进行筛选")
	public PageList<ScmXsBigContract> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmXsBigContractManager.query(queryFilter);
	}
	
	/**
	 * 大合同明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="大合同数据详情",httpMethod = "GET",notes = "大合同数据详情")
	public ScmXsBigContract get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmXsBigContractManager.get(id);
	}
	/**
	 * 大合同明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/getByApplyId/{applyid}")
	@ApiOperation(value="大合同数据详情",httpMethod = "GET",notes = "大合同数据详情")
	public CommonResult<ScmXsBigContract> getByApplyId(@ApiParam(name="applyid",value="业务对象主键", required = true)@PathVariable String applyid) throws Exception{
		return new CommonResult<ScmXsBigContract>(true,"获取成功",scmXsBigContractManager.getByApplyId(applyid));
	}
	
    /**
	 * 新增大合同
	 * @param scmXsBigContract
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新大合同数据", httpMethod = "POST", notes = "新增,更新大合同数据")
	public CommonResult<String> save(@ApiParam(name="scmXsBigContract",value="大合同业务对象", required = true)@RequestBody ScmXsBigContract scmXsBigContract) throws Exception{
		String msg = "添加大合同成功";
		JsonNode user = ucFeignService.getUser(current(), "");
		String fullname = user.get("fullname").asText();
		scmXsBigContract.setEntryPeople(fullname);
		scmXsBigContract.setApprovalState("0");
		scmXsBigContract.setEntryDate(new Date());
		if(StringUtil.isEmpty(scmXsBigContract.getId())){
			scmXsBigContractManager.create(scmXsBigContract);
		}else{
			scmXsBigContractManager.update(scmXsBigContract);
			 msg = "更新大合同成功";
		}
		return new CommonResult<String>(true,msg,scmXsBigContract.getId());
	}
	
	/**
	 * 删除大合同记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除大合同记录", httpMethod = "DELETE", notes = "删除大合同记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmXsBigContractManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除大合同记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除大合同记录", httpMethod = "DELETE", notes = "批量删除大合同记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmXsBigContractManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
	
	/**
	 * @param 大合同申请
	 * @param request
	 * @return
	 * @throws Exception
	 * flowKey:审批类型
	 * sysCode:系统别名
	 * instanceIdField:与实体类审批ID相同,controller层接到对象会有审批ID,处理业务逻辑后,保存对象即可
	 * varKeys:脚本使用参数
	 */
	@PostMapping(value = "sendApply")
    @ApiOperation(value = "大合同申请", httpMethod = "POST", notes = "提交时需要把商品数据封装至大合同对象中一起提交")
    @Workflow(flowKey = "dhtsq", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {})
    public  CommonResult<String> sendApply(
            @ApiParam(name = "scmXsBigContract", value = "经销商协议合作名单总表对象", required = true) @RequestBody ScmXsBigContract scmXsBigContract,
            HttpServletRequest request) throws Exception {
		JsonNode user = ucFeignService.getUser(current(), "");
		String fullname = user.get("fullname").asText();
		scmXsBigContract.setEntryPeople(fullname);
		String contractCode = scmXsBigContractManager.sendApply(scmXsBigContract);
	   return new CommonResult<String>(true, "contractCode", contractCode);
	}
	
	@PostMapping(value = "/endApply")
    @ApiOperation(value = "经销商协议合作名单审批通过及驳回", httpMethod = "POST", notes = "经销商协议合作名单审批通过及驳回")
    public void endApply(@ApiParam(name = "params", value = "流程事件参数", required = true) @RequestBody String params, 
    		HttpServletRequest request) throws Exception {
    	JsonNode jsonNode = JsonUtil.toJsonNode(params);
    	scmXsBigContractManager.endApply(jsonNode);
    }
	
	/**
	 * 大合同下拉框
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/getDownBox/{ownerId}")
	@ApiOperation(value="大合同下拉框",httpMethod = "GET",notes = "大合同下拉框")
	public PageList<ScmXsBigContract> getDownBox(@ApiParam(name="ownerId",value="货主id", required = true)@PathVariable String ownerId) throws Exception{
		return scmXsBigContractManager.getDownBox(ownerId);
	}
	/**
	 * 大合同申请页获取乙方信息
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/bigcontractGetYDetails")
	@ApiOperation(value="大合同申请页获取乙方信息", httpMethod = "POST", notes = "大合同申请页获取乙方信息,query必须添加协议id（DealerClauseId）进行筛选")
	public PageList<ScmXsBigContract> bigcontractGetYDetails(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmXsBigContractManager.bigcontractGetYDetails(queryFilter);
	}
	
	/**
	 * 大合同获取编号
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@GetMapping(value = "/getCode")
	@ApiOperation(value = "大合同获取编号", httpMethod = "GET", notes = "大合同获取编号")
	public CommonResult<String> getCode()throws Exception {
		String code = QuarterUtil.getCode("DHT");
		return new CommonResult(true,"获取成功",code);
	}
	
}
