package com.winway.scm.controller;

import javax.annotation.Resource;

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
import com.hotent.base.controller.BaseController;
import com.hotent.base.feign.UCFeignService;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;
import com.winway.scm.model.ScmZdManageMentScope;
import com.winway.scm.persistence.manager.ScmZdManageMentScopeManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * <pre> 
 * 描述：生产经营范围 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-29 10:31:24
 * 版权：美达项目组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmZdManageMentScope/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmZdManageMentScopeController 生产经营范围")
public class ScmZdManageMentScopeController extends BaseController{
	@Resource
	ScmZdManageMentScopeManager scmZdManageMentScopeManager;
	@Resource
	UCFeignService ucFeignService;  
	
	/**
	 * 生产经营范围列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="生产经营范围数据列表", httpMethod = "POST", notes = "获取生产经营范围列表")
	public PageList<ScmZdManageMentScope> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmZdManageMentScopeManager.query(queryFilter);
	}
	
	/**
	 * 生产经营范围明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="生产经营范围数据详情",httpMethod = "GET",notes = "生产经营范围数据详情")
	public ScmZdManageMentScope get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmZdManageMentScopeManager.get(id);
	}
	
    /**
	 * 新增生产经营范围
	 * @param scmZdManageMentScope
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新生产经营范围数据", httpMethod = "POST", notes = "新增,更新生产经营范围数据")
	public CommonResult<String> save(@ApiParam(name="scmZdManageMentScope",value="生产经营范围业务对象", required = true)@RequestBody ScmZdManageMentScope scmZdManageMentScope) throws Exception{
		String msg = "添加生产经营范围成功";
		scmZdManageMentScopeManager.getByNameAndCodeOrId(scmZdManageMentScope);
		JsonNode user = ucFeignService.getUser(current(), "");
		String fullname = user.get("fullname").asText();
		scmZdManageMentScope.setCreatePersion(fullname);
		if(StringUtil.isEmpty(scmZdManageMentScope.getId())){
			scmZdManageMentScopeManager.create(scmZdManageMentScope);
		}else{
			scmZdManageMentScopeManager.update(scmZdManageMentScope);
			 msg = "更新生产经营范围成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除生产经营范围记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除生产经营范围记录", httpMethod = "DELETE", notes = "删除生产经营范围记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmZdManageMentScopeManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除生产经营范围记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除生产经营范围记录", httpMethod = "DELETE", notes = "批量删除生产经营范围记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmZdManageMentScopeManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
}
