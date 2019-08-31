package com.winway.scm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.hotent.base.feign.UCFeignService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.annotation.Workflow;
import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.JsonUtil;
import com.hotent.base.util.StringUtil;
import com.winway.purchase.util.QuarterUtil;
import com.winway.scm.model.ScmXsAgreementSummary;
import com.winway.scm.persistence.manager.ScmXsAgreementSummaryManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * <pre> 
 * 描述：经销商协议汇总表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:43
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmXsAgreementSummary/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmXsAgreementSummaryController 经销商协议汇总")
public class ScmXsAgreementSummaryController extends BaseController{
	@Resource
	ScmXsAgreementSummaryManager scmXsAgreementSummaryManager;


	@Resource
	UCFeignService ucFeignService;

	/**
	 * 省区协议汇总表列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="经销商协议汇总表数据列表", httpMethod = "POST", notes = "获取经销商协议汇总表列表,query必须添加货主ID进行筛选")
	public PageList<ScmXsAgreementSummary> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmXsAgreementSummaryManager.query(queryFilter);
	}
	
	/**
	 * 省区协议汇总表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="经销商协议汇总表数据详情",httpMethod = "GET",notes = "经销商协议汇总表数据详情")
	public ScmXsAgreementSummary get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmXsAgreementSummaryManager.get(id);
	}
	
    /**
	 * 新增省区协议汇总表
	 * @param scmXsAgreementSummary
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新经销商协议汇总表数据", httpMethod = "POST", notes = "新增,更新经销商协议汇总表数据")
	public CommonResult<ScmXsAgreementSummary> save(@ApiParam(name="scmXsAgreementSummary",value="经销商协议汇总表业务对象", required = true)@RequestBody ScmXsAgreementSummary scmXsAgreementSummary) throws Exception{
		String msg = "添加经销商协议汇总表成功";
		//申请状态
		scmXsAgreementSummary.setApprovalState("0");
		JsonNode user = ucFeignService.getUser(current(), "");
		String fullname = user.get("fullname").asText();
		scmXsAgreementSummary.setCreateDate(new Date());
		scmXsAgreementSummary.setCreatePerson(fullname);
		if(StringUtil.isEmpty(scmXsAgreementSummary.getId())){
			//申请编码
			scmXsAgreementSummary.setAgreementSummaryCode(QuarterUtil.getCode("JXTK"));
			scmXsAgreementSummaryManager.create(scmXsAgreementSummary);
		}else{
			scmXsAgreementSummaryManager.update(scmXsAgreementSummary);
			 msg = "更新经销商协议汇总表成功";
		}
		return new CommonResult<ScmXsAgreementSummary>(true,msg,scmXsAgreementSummary);
	}
	
   /**
	 * 根据主表ID创建协议条款子表数据
	 * @param scmXsAgreementSummary
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="saveByDealerClause")
	@ApiOperation(value = "根据主表ID创建协议条款子表数据", httpMethod = "POST", notes = "根据主表ID创建协议条款子表数据")
	public CommonResult<ScmXsAgreementSummary> saveByDealerClause(@ApiParam(name="scmXsAgreementSummary",value="经销商协议汇总表业务对象", required = true)@RequestBody ScmXsAgreementSummary scmXsAgreementSummary) throws Exception{
		scmXsAgreementSummaryManager.saveByDealerClause(scmXsAgreementSummary);
		return new CommonResult<ScmXsAgreementSummary>(true,"处理成功");
	}
	/**
	 * 根据主表ID创建协议品规明细子表字段
	 * @param scmXsAgreementSummary
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="saveByAgreement")
	@ApiOperation(value = "根据主表ID创建协议品规明细子表字段", httpMethod = "POST", notes = "根据主表ID创建协议品规明细子表字段")
	public CommonResult<ScmXsAgreementSummary> saveByAgreement(@ApiParam(name="scmXsAgreementSummary",value="经销商协议汇总表业务对象", required = true)@RequestBody ScmXsAgreementSummary scmXsAgreementSummary) throws Exception{
		scmXsAgreementSummaryManager.saveByAgreement(scmXsAgreementSummary);
		return new CommonResult<ScmXsAgreementSummary>(true,"处理成功");
	}
	
	/**
	 * 删除省区协议汇总表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除经销商协议汇总表记录", httpMethod = "DELETE", notes = "删除经销商协议汇总表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmXsAgreementSummaryManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除经销商协议汇总表记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除经销商协议汇总表记录", httpMethod = "DELETE", notes = "批量删除经销商协议汇总表记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmXsAgreementSummaryManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
	
	/**
	 * @param 经销商协议合作条款申请
	 * @param request
	 * @return
	 * @throws Exception
	 * flowKey:审批类型
	 * sysCode:系统别名
	 * instanceIdField:与实体类审批ID相同,controller层接到对象会有审批ID,处理业务逻辑后,保存对象即可
	 * varKeys:脚本使用参数
	 */
	@PostMapping(value = "sendApply")
    @ApiOperation(value = "经销商协议合作条款申请", httpMethod = "POST", notes = "提交时需要在ScmXsAgreementSummary对象中封装好详细数据以及列表数据")
//    @Workflow(flowKey = "jxsxyhztksq", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {})
    public CommonResult<ScmXsAgreementSummary> sendApply(
            @ApiParam(name = "ScmXsAgreementSummary", value = "经销商协议合作名单总表对象", required = true) @RequestBody ScmXsAgreementSummary scmXsAgreementSummary,
            HttpServletRequest request) throws Exception {
		JsonNode user = ucFeignService.getUser(current(), "");
		String fullname = user.get("fullname").asText();
		scmXsAgreementSummary.setCreateDate(new Date());
		scmXsAgreementSummary.setCreatePerson(fullname);
		scmXsAgreementSummaryManager.sendApply(scmXsAgreementSummary);
	   return new CommonResult<ScmXsAgreementSummary>(true, "审批发起成功");
	}
	
    @PostMapping(value = "/endApply")
    @ApiOperation(value = "经销商协议合作名单审批通过及驳回", httpMethod = "POST", notes = "经销商协议合作名单审批通过及驳回")
    public void endApply(@ApiParam(name = "params", value = "流程事件参数", required = true) @RequestBody String params, 
    		HttpServletRequest request) throws Exception {
    	JsonNode jsonNode = JsonUtil.toJsonNode(params);
     	scmXsAgreementSummaryManager.endApply(jsonNode);
    }
    
    /**
	 * 根据审批Id获取经销商协议合作名单及其品规明细
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/getDealerClauseAndProductDetail/{id}")
	@ApiOperation(value="经销商协议汇总表数据详情",httpMethod = "GET",notes = "经销商协议汇总表数据详情")
	public ScmXsAgreementSummary getDealerClauseAndProductDetail(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmXsAgreementSummaryManager.getDealerClauseAndProductDetail(id);
	}

	/**
	 *获取经销商和分销商
	 * @param
	 * @return
	 * @throws Exception
	 * ModelAndView
	 */
	@PostMapping(value="/aunualFee")
	@ApiOperation(value="根据商务分区和省区查经销商协议对应年费",httpMethod = "GET",notes = "根据上午分区和省区查经销商协议对应年费")
	public String getPAnnualFee(@ApiParam(name = "businessDivisionId", value = "商务分区id",required = true) @RequestParam String businessDivisionId,  @ApiParam(name = "provinceId", value = "商务省区id",required = true) @RequestParam  String provinceId,@ApiParam(name = "year", value = "当前年份",required = true) @RequestParam String year){
		List<Map<String,Object>> mapList = scmXsAgreementSummaryManager.aunualFee(businessDivisionId,provinceId,year);
		return JSON.toJSONString(mapList);
	}

    
}

