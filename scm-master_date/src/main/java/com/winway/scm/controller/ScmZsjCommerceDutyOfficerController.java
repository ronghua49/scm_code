package com.winway.scm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
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
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.JsonUtil;
import com.hotent.base.util.StringUtil;
import com.winway.scm.model.ScmZsjCommerceDutyOfficer;
import com.winway.scm.model.ScmZsjCommerceFirst;
import com.winway.scm.persistence.manager.ScmZsjCommerceDutyOfficerManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * <pre> 
 * 描述：商业责任人表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-18 14:36:01
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmZsjCommerceDutyOfficer/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmZsjCommerceDutyOfficerController 商业责任人")
public class ScmZsjCommerceDutyOfficerController extends BaseController{
	@Resource
	ScmZsjCommerceDutyOfficerManager scmZsjCommerceDutyOfficerManager;
	
	/**
	 * 商业责任人表列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="商业责任人表数据列表", httpMethod = "POST", notes = "获取商业责任人表列表")
	public PageList<ScmZsjCommerceDutyOfficer> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		
		return scmZsjCommerceDutyOfficerManager.query(queryFilter);
	}
	
	/**
	 * 商业责任人表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="商业责任人表数据详情",httpMethod = "GET",notes = "商业责任人表数据详情")
	public ScmZsjCommerceDutyOfficer get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmZsjCommerceDutyOfficerManager.get(id);
	}
	
    /**
	 * 新增商业责任人表
	 * @param scmZsjCommerceDutyOfficer
	 * @throws Exception 
	 * @return
	 * @exception 
	 * 对商业责任人数据的暂存
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新商业责任人表数据", httpMethod = "POST", notes = "新增,更新商业责任人表数据")
	public CommonResult<String> save(@ApiParam(name="scmZsjCommerceDutyOfficer",value="商业责任人表业务对象", required = true)@RequestBody ScmZsjCommerceDutyOfficer scmZsjCommerceDutyOfficer) throws Exception{
		String msg = "添加商业责任人表成功";
		try {
		scmZsjCommerceDutyOfficerManager.createCache(scmZsjCommerceDutyOfficer);
		}catch(Exception e) {
			 msg = "添加商业责任人表失败";
		}
		return new CommonResult<String>(msg);
	}
	
	
	
	
	
	
	
	/**
	 * 删除商业责任人表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除商业责任人表记录", httpMethod = "DELETE", notes = "删除商业责任人表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		ScmZsjCommerceDutyOfficer scmZsjCommerceDutyOfficer;
		try {
		scmZsjCommerceDutyOfficer = scmZsjCommerceDutyOfficerManager.get(id);
		}catch(Exception e) {
			return new CommonResult<String>(false,"删除失败");
		}
		try {
		if(scmZsjCommerceDutyOfficer!=null) {
			scmZsjCommerceDutyOfficerManager.remove(id);
			return new CommonResult<String>(true, "删除成功");
		}
		}catch(Exception e) {
		return new CommonResult<String>(false,"删除失败，没有此数据");
		
		}
		return new CommonResult<String>(false,"删除失败，没有此数据");
	}
	
	
	/**
	 * 批量删除商业责任人表记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除商业责任人表记录", httpMethod = "DELETE", notes = "批量删除商业责任人表记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		try {
		scmZsjCommerceDutyOfficerManager.removeByIds(ids);
		}catch(Exception e) {
			return new CommonResult<String>(false, "批量删除失败");
		}
		return new CommonResult<String>(true, "批量删除成功");
	}
	
	/**
	 * @param 商业责任人
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
    @ApiOperation(value = "商业责任人首营", httpMethod = "POST", notes = "根据swagger文档传递参数")
    @Workflow(flowKey = "sysy", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {})
    public CommonResult<ScmZsjCommerceFirst> sendApply(
            @ApiParam(name = "scmZsjCommerceDutyOfficer", value = "商业责任人对象", required = true) @RequestBody ScmZsjCommerceDutyOfficer scmZsjCommerceDutyOfficer,
            HttpServletRequest request) throws Exception {
		try {
		scmZsjCommerceDutyOfficerManager.sendApply(scmZsjCommerceDutyOfficer);
		}catch(Exception e) {
			return new CommonResult<ScmZsjCommerceFirst>(false, "审批发起失败");
		}
		return new CommonResult<ScmZsjCommerceFirst>(true, "审批发起成功");

		
	}
	
	
	
    @PostMapping(value = "/endApply")
    @ApiOperation(value = "商业首营审批流程通过", httpMethod = "POST", notes = "商业首营审批流程通过,修改审批状态")
    public void endApply(@ApiParam(name = "params", value = "流程事件参数", required = true) @RequestBody String params) throws Exception {
    	scmZsjCommerceDutyOfficerManager.endApply(params);
    	
    	
    	
}
}
