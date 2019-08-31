package com.winway.scm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.Date;

import javax.annotation.Resource;
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
import com.winway.scm.persistence.manager.ScmGspProductExceptionTypeManager;
import com.winway.scm.model.ScmGspProductExceptionType;
import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.controller.BaseController;
import com.hotent.base.feign.UCFeignService;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;

/**
 * 
 * <pre> 
 * 描述：产品异常状态 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-30 14:46:25
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmGspProductExceptionType/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmGspProductExceptionTypeController 产品异常状态")
public class ScmGspProductExceptionTypeController extends BaseController{
	@Resource
	ScmGspProductExceptionTypeManager scmGspProductExceptionTypeManager;
	@Resource
	UCFeignService ucFeignService;
	/**
	 * 产品异常状态列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="产品异常状态数据列表", httpMethod = "POST", notes = "获取产品异常状态列表")
	public PageList<ScmGspProductExceptionType> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmGspProductExceptionTypeManager.query(queryFilter);
	}
	
	/**
	 * 产品异常状态明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="产品异常状态数据详情",httpMethod = "GET",notes = "产品异常状态数据详情")
	public ScmGspProductExceptionType get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmGspProductExceptionTypeManager.get(id);
	}
	
    /**
	 * 新增产品异常状态
	 * @param scmGspProductExceptionType
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新产品异常状态数据", httpMethod = "POST", notes = "新增,更新产品异常状态数据")
	public CommonResult<String> save(@ApiParam(name="scmGspProductExceptionType",value="产品异常状态业务对象", required = true)@RequestBody ScmGspProductExceptionType scmGspProductExceptionType) throws Exception{
		String msg = "添加产品异常状态成功";
		JsonNode user = ucFeignService.getUser(current(), "");
		String userName = user.get("fullname").asText();
		if(StringUtil.isEmpty(scmGspProductExceptionType.getId())){
			scmGspProductExceptionType.setCreatepersion(userName);
			scmGspProductExceptionType.setCreateDate(new Date());
			scmGspProductExceptionTypeManager.create(scmGspProductExceptionType);
		}else{
			scmGspProductExceptionTypeManager.update(scmGspProductExceptionType);
			msg = "更新产品异常状态成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除产品异常状态记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除产品异常状态记录", httpMethod = "DELETE", notes = "删除产品异常状态记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmGspProductExceptionTypeManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除产品异常状态记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除产品异常状态记录", httpMethod = "DELETE", notes = "批量删除产品异常状态记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmGspProductExceptionTypeManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
}
