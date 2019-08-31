package com.winway.scm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.Date;

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
import com.winway.scm.persistence.manager.ScmCwDistributorRBManager;
import com.winway.scm.model.ScmCwDistributorRB;
import com.winway.scm.model.ScmCwPaymentRB;
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
 * 描述：分销商RB申请主表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-14 16:38:31
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmCwDistributorRB/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmCwDistributorRBController")
public class ScmCwDistributorRBController extends BaseController{
	@Resource
	ScmCwDistributorRBManager scmCwDistributorRBManager;
	@Resource
	UCFeignService ucFeignService;
	
	/**
	 * 分销商RB申请主表列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="分销商RB申请主表数据列表", httpMethod = "POST", notes = "获取分销商RB申请主表列表")
	public PageList<ScmCwDistributorRB> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmCwDistributorRBManager.query(queryFilter);
	}
	
	/**
	 * 分销商RB申请主表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="分销商RB申请主表数据详情",httpMethod = "GET",notes = "分销商RB申请主表数据详情")
	public ScmCwDistributorRB get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmCwDistributorRBManager.get(id);
	}
	/**
	 * 分销商RB申请主表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/getByApprovalId/{approvalId}")
	@ApiOperation(value="根据审批ID获取分销商审批数据",httpMethod = "GET",notes = "根据审批ID获取分销商审批数据")
	public ScmCwDistributorRB getByApprovalId(@ApiParam(name="approvalId",value="审批ID", required = true)@PathVariable String approvalId) throws Exception{
		return scmCwDistributorRBManager.getByApprovalId(approvalId);
	}
	
    /**
	 * 新增分销商RB申请主表
	 * @param scmCwDistributorRB
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新分销商RB申请主表数据", httpMethod = "POST", notes = "新增,更新分销商RB申请主表数据")
	public CommonResult<String> save(@ApiParam(name="scmCwDistributorRB",value="分销商RB申请主表业务对象", required = true)@RequestBody ScmCwDistributorRB scmCwDistributorRB) throws Exception{
		String msg = "添加分销商RB申请主表成功";
		if(StringUtil.isEmpty(scmCwDistributorRB.getId())){
			if("".equals(scmCwDistributorRB.getYear()) || scmCwDistributorRB.getYear() == null || "null".equals(scmCwDistributorRB.getYear())) {
				throw new RuntimeException("年度不能为空");
			}
			JsonNode user = ucFeignService.getUser(current(), "");
		    String fullname = user.get("fullname").asText();
		    scmCwDistributorRB.setCreatePsersion(fullname);
		    scmCwDistributorRB.setCreateDate(new Date());
		    scmCwDistributorRB.setApprovalState("0");
			scmCwDistributorRBManager.create(scmCwDistributorRB);
		}else{
			scmCwDistributorRBManager.update(scmCwDistributorRB);
			msg = "更新分销商RB申请主表成功";
		}
		return new CommonResult<String>(true,msg,scmCwDistributorRB.getId());
	}
	
	/**
	 * 删除分销商RB申请主表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除分销商RB申请主表记录", httpMethod = "DELETE", notes = "删除分销商RB申请主表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmCwDistributorRBManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除分销商RB申请主表记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除分销商RB申请主表记录", httpMethod = "DELETE", notes = "批量删除分销商RB申请主表记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmCwDistributorRBManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
	
    /**
     * 新增经销商RB支付申请总表发起申请
     * @param scmCwPaymentRB
     * @return
     * @throws Exception
     * @throws
     */
    @PostMapping(value = "sendApply")
    @ApiOperation(value = "分销商RB申请", httpMethod = "POST", notes = "分销商RB申请")
//    @Workflow(flowKey = "fxsRBgl", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {})
    public CommonResult<String> sendApply(@ApiParam(name = "scmCwPaymentRB", value = "经销商RB支付申请总表业务对象", required = true) @RequestBody ScmCwDistributorRB scmCwDistributorRB) throws Exception {
    	scmCwDistributorRBManager.sendApply(scmCwDistributorRB);
        return new CommonResult<String>("申请成功");
    }

    /**
     * 结束审批
     *
     * @param params
     * @param request
     * @throws Exception
     */
    @PostMapping(value = "/endApply")
    @ApiOperation(value = "分销商RB申请审批通过及驳回", httpMethod = "POST", notes = "新增经销商RB支付申请审批通过及驳回")
    public void endApply(@ApiParam(name = "params", value = "流程事件参数", required = true) @RequestBody String params,
                         HttpServletRequest request) throws Exception {
        JsonNode jsonNode = JsonUtil.toJsonNode(params);
        scmCwDistributorRBManager.endApply(jsonNode);
    }

	
}
