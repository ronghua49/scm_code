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
import com.winway.scm.persistence.manager.ScmXsDistributorAgreementSummaryManager;
import com.winway.purchase.util.QuarterUtil;
import com.winway.scm.model.ScmXsBigContract;
import com.winway.scm.model.ScmXsDistributorAgreementSummary;
import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.annotation.Workflow;
import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.model.WinwayResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.JsonUtil;
import com.hotent.base.util.StringUtil;

/**
 * 
 * <pre> 
 * 描述：分销商协议汇总表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:43
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmXsDistributorAgreementSummary/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmXsDistributorAgreementSummaryController 分销商协议汇总表")
public class ScmXsDistributorAgreementSummaryController extends BaseController{
	@Resource
	ScmXsDistributorAgreementSummaryManager scmXsDistributorAgreementSummaryManager;
	
	/**
	 * 省区协议汇总表列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="分销商协议汇总表数据列表", httpMethod = "POST", notes = "获取分销商协议汇总表列表,query必须添加货主ID进行筛选")
	public PageList<ScmXsDistributorAgreementSummary> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmXsDistributorAgreementSummaryManager.query(queryFilter);
	}
	
	/**
	 * 省区协议汇总表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="分销商协议汇总表数据详情",httpMethod = "GET",notes = "省区协议汇总表数据详情")
	public ScmXsDistributorAgreementSummary get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmXsDistributorAgreementSummaryManager.get(id);
	}
	
    /**
	 * 新增省区协议汇总表
	 * @param scmXsDistributorAgreementSummary
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新省区协议汇总表数据", httpMethod = "POST", notes = "新增,更新省区协议汇总表数据")
	public CommonResult<ScmXsDistributorAgreementSummary> save(@ApiParam(name="scmXsDistributorAgreementSummary",value="省区协议汇总表业务对象", required = true)@RequestBody ScmXsDistributorAgreementSummary scmXsDistributorAgreementSummary) throws Exception{
		String msg = "添加省区协议汇总表成功";
		scmXsDistributorAgreementSummary.setApprovalState("0");
		if(StringUtil.isEmpty(scmXsDistributorAgreementSummary.getId())){
			String code = QuarterUtil.getCode("FXXY");
			scmXsDistributorAgreementSummary.setAgreementSummaryCode(code);
			scmXsDistributorAgreementSummaryManager.create(scmXsDistributorAgreementSummary);
		}else{
			scmXsDistributorAgreementSummaryManager.update(scmXsDistributorAgreementSummary);
			 msg = "更新省区协议汇总表成功";
		}
		return new CommonResult<ScmXsDistributorAgreementSummary>(true,msg,scmXsDistributorAgreementSummary);
	}
	
	/**
	 * 删除省区协议汇总表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除省区协议汇总表记录", httpMethod = "DELETE", notes = "删除省区协议汇总表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmXsDistributorAgreementSummaryManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除省区协议汇总表记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除省区协议汇总表记录", httpMethod = "DELETE", notes = "批量删除省区协议汇总表记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmXsDistributorAgreementSummaryManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
	
	/**
	 * @param 分销商协议汇总记录申请
	 * @param request
	 * @return
	 * @throws Exception
	 * flowKey:审批类型
	 * sysCode:系统别名
	 * instanceIdField:与实体类审批ID相同,controller层接到对象会有审批ID,处理业务逻辑后,保存对象即可
	 * varKeys:脚本使用参数
	 */
	@PostMapping(value = "sendApply")
    @ApiOperation(value = "分销商协议汇总记录申请", httpMethod = "POST", notes = "条款列表需要展示选定省区下的分销商,"
    		+ "设置信息后需要把合作条款列表封装至汇总总表提交")
    @Workflow(flowKey = "fxsxyhztk", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {})
    public CommonResult<ScmXsDistributorAgreementSummary> sendApply(
            @ApiParam(name = "scmXsDistributorAgreementSummary", value = "经销商协议合作名单总表对象", required = true) @RequestBody ScmXsDistributorAgreementSummary scmXsDistributorAgreementSummary,
            HttpServletRequest request) throws Exception {
		scmXsDistributorAgreementSummaryManager.sendApply(scmXsDistributorAgreementSummary);
	   return new CommonResult<ScmXsDistributorAgreementSummary>(true, "审批发起成功", scmXsDistributorAgreementSummary);
	}
	
	 @PostMapping(value = "/endApply")
	    @ApiOperation(value = "分销商协议合作条款通过审批及驳回处理", httpMethod = "POST", notes = "分销商协议合作条款通过审批及驳回处理")
	    public void endApply(@ApiParam(name = "params", value = "流程事件参数", required = true) @RequestBody String params, 
	    		HttpServletRequest request) throws Exception {
	    	JsonNode jsonNode = JsonUtil.toJsonNode(params);
	    	scmXsDistributorAgreementSummaryManager.endApply(jsonNode);
	    }
}
