package com.winway.scm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
import com.winway.scm.persistence.manager.ScmZdFunctionTypeManager;
import com.winway.scm.model.ScmZdFunctionType;
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
 * 描述：职能类型 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-16 10:34:19
 * 版权：美达项目组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmZdFunctionType/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmZdFunctionTypeController 职能类型")
public class ScmZdFunctionTypeController extends BaseController{
	@Resource
	ScmZdFunctionTypeManager scmZdFunctionTypeManager;
	@Resource
	UCFeignService ucFeignService;  
	/**
	 * 职能类型列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="职能类型数据列表", httpMethod = "POST", notes = "获取职能类型列表")
	public PageList<ScmZdFunctionType> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmZdFunctionTypeManager.query(queryFilter);
	}
	
	/**
	 * 职能类型明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="职能类型数据详情",httpMethod = "GET",notes = "职能类型数据详情")
	public ScmZdFunctionType get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmZdFunctionTypeManager.get(id);
	}
	
    /**
	 * 新增职能类型
	 * @param scmZdFunctionType
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新职能类型数据", httpMethod = "POST", notes = "新增,更新职能类型数据")
	public CommonResult<String> save(@ApiParam(name="scmZdFunctionType",value="职能类型业务对象", required = true)@RequestBody ScmZdFunctionType scmZdFunctionType) throws Exception{
		String msg = "添加职能类型成功";
		scmZdFunctionTypeManager.getByNameAndCodeOrId(scmZdFunctionType);
		JsonNode user = ucFeignService.getUser(current(), "");
		String fullname = user.get("fullname").asText();
		scmZdFunctionType.setCreatePersion(fullname);
		if(StringUtil.isEmpty(scmZdFunctionType.getId())){
			scmZdFunctionTypeManager.create(scmZdFunctionType);
		}else{
			scmZdFunctionTypeManager.update(scmZdFunctionType);
			 msg = "更新职能类型成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除职能类型记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除职能类型记录", httpMethod = "DELETE", notes = "删除职能类型记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmZdFunctionTypeManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除职能类型记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除职能类型记录", httpMethod = "DELETE", notes = "批量删除职能类型记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmZdFunctionTypeManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
}
