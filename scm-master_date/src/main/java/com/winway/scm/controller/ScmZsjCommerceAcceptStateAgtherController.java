package com.winway.scm.controller;

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
import com.winway.scm.model.ScmZsjCommerceAcceptStateAgther;
import com.winway.scm.model.ScmZsjCommerceFirst;
import com.winway.scm.persistence.manager.ScmZsjCommerceAcceptStateAgtherManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * <pre> 
 * 描述：商业认可状态总表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-18 14:36:01
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmZsjCommerceAcceptStateAgther/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmZsjCommerceAcceptStateAgtherController 商业认可状态总表 ")
public class ScmZsjCommerceAcceptStateAgtherController extends BaseController{
	@Resource
	ScmZsjCommerceAcceptStateAgtherManager scmZsjCommerceAcceptStateAgtherManager;
	
	@Resource
	UCFeignService ucFeignService;
	
	/**
	 * 商业认可状态总表列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="商业认可状态总表数据列表", httpMethod = "POST", notes = "获取商业认可状态总表列表")
	public PageList<ScmZsjCommerceAcceptStateAgther> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmZsjCommerceAcceptStateAgtherManager.query(queryFilter);
	}
	
	/**
	 * 商业认可状态总表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="商业认可状态总表数据详情",httpMethod = "GET",notes = "商业认可状态总表数据详情")
	public ScmZsjCommerceAcceptStateAgther get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmZsjCommerceAcceptStateAgtherManager.get(id);
	}
	
    /**
	 * 新增商业认可状态总表
	 * @param scmZsjCommerceAcceptStateAgther
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新商业认可状态总表数据", httpMethod = "POST", notes = "新增,更新商业认可状态总表数据")
	public CommonResult<ScmZsjCommerceAcceptStateAgther> save(@ApiParam(name="scmZsjCommerceAcceptStateAgther",value="商业认可状态总表业务对象", required = true)@RequestBody ScmZsjCommerceAcceptStateAgther scmZsjCommerceAcceptStateAgther) throws Exception{
		String msg = "暂存成功";
		JsonNode user = ucFeignService.getUser(current(), "");
		String userName = user.get("fullname").asText();
		scmZsjCommerceAcceptStateAgther.setOperatorName(userName);
		scmZsjCommerceAcceptStateAgther.setApprovalState("0");
		if(StringUtil.isEmpty(scmZsjCommerceAcceptStateAgther.getId())){
			scmZsjCommerceAcceptStateAgther.setApprovalCode(QuarterUtil.getCode("SYRK"));
			scmZsjCommerceAcceptStateAgtherManager.create(scmZsjCommerceAcceptStateAgther);
		}else{
			scmZsjCommerceAcceptStateAgtherManager.update(scmZsjCommerceAcceptStateAgther);
		}
		return new CommonResult<ScmZsjCommerceAcceptStateAgther>(true,msg,scmZsjCommerceAcceptStateAgther);
	}
	
	/**
	 * 删除商业认可状态总表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除商业认可状态总表记录", httpMethod = "DELETE", notes = "删除商业认可状态总表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmZsjCommerceAcceptStateAgtherManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除商业认可状态总表记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除商业认可状态总表记录", httpMethod = "DELETE", notes = "批量删除商业认可状态总表记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmZsjCommerceAcceptStateAgtherManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
	
	/**
	 * @param 商业合并申请
	 * 需要调整保存方法,添加委托书数据保存
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 * flowKey:审批类型
	 * sysCode:系统别名
	 * instanceIdField:与实体类审批ID相同,controller层接到对象会有审批ID,处理业务逻辑后,保存对象即可
	 * varKeys:脚本使用参数
	 */
	@PostMapping(value = "sendApply")
    @ApiOperation(value = "商业认可状态申请", httpMethod = "POST", notes = "商业认可状态申请")
//    @Workflow(flowKey = "syrkztsq", sysCode = "SCM", instanceIdField = "approvalId", varKeys = { "account", "isFinance" })
    public CommonResult<ScmZsjCommerceAcceptStateAgther> sendApply(
            @ApiParam(name = "scmZsjCommerceBlackList", value = "采购合同对象", required = true) @RequestBody ScmZsjCommerceAcceptStateAgther scmZsjCommerceAcceptStateAgther,
            HttpServletRequest request) throws Exception {
		JsonNode user = ucFeignService.getUser(current(), "");
		String userName = user.get("fullname").asText();
		scmZsjCommerceAcceptStateAgther.setOperatorName(userName);
		scmZsjCommerceAcceptStateAgtherManager.sendApply(scmZsjCommerceAcceptStateAgther);
		return new CommonResult<ScmZsjCommerceAcceptStateAgther>(true, "审批发起成功",scmZsjCommerceAcceptStateAgther);
	}
	
    @PostMapping(value = "/endApply")
    @ApiOperation(value = "商业认可审批通过或驳回", httpMethod = "POST", notes = "商业认可审批通过或驳回")
    public void endApply(@ApiParam(name = "params", value = "流程事件参数", required = true) @RequestBody String params, 
    		HttpServletRequest request) throws Exception {
    	JsonNode jsonNode = JsonUtil.toJsonNode(params);
    	scmZsjCommerceAcceptStateAgtherManager.endApply(jsonNode);
    }
}
