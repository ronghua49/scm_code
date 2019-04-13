package com.winway.scm.controller;

import java.util.Date;
import java.util.List;

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
import com.winway.scm.model.ScmZdCommerceLevel;
import com.winway.scm.persistence.manager.ScmZdCommerceLevelManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * <pre> 
 * 描述：商业级别 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-16 10:34:19
 * 版权：美达项目组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmZdCommerceLevel/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmZdCommerceLevelController 商业级别")
public class ScmZdCommerceLevelController extends BaseController{
	@Resource
	ScmZdCommerceLevelManager scmZdCommerceLevelManager;
	@Resource
	UCFeignService ucFeignService;  
	/**
	 * scm_zd_commercelevel列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="商业级别数据列表", httpMethod = "POST", notes = "获取商业级别列表")
	public PageList<ScmZdCommerceLevel> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmZdCommerceLevelManager.query(queryFilter);
	}
	
	/**
	 * scm_zd_commercelevel明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="商业级别数据详情",httpMethod = "GET",notes = "商业级别数据详情")
	public ScmZdCommerceLevel get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmZdCommerceLevelManager.get(id);
	}
	
    /**
	 * 新增scm_zd_commercelevel
	 * @param scmZdCommerceLevel
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新商业级别数据", httpMethod = "POST", notes = "新增,更新商业级别数据")
	public CommonResult<String> save(@ApiParam(name="scmZdCommerceLevel",value="商业级别业务对象", required = true)@RequestBody ScmZdCommerceLevel scmZdCommerceLevel) throws Exception{
		String msg = "添加商业级别成功";
		scmZdCommerceLevelManager.getByNameAndCodeOrId(scmZdCommerceLevel);
		JsonNode user = ucFeignService.getUser(current(), "");
		String fullname = user.get("fullname").asText();
		scmZdCommerceLevel.setCreatePersion(fullname);
		if(StringUtil.isEmpty(scmZdCommerceLevel.getId())){
			scmZdCommerceLevelManager.create(scmZdCommerceLevel);
		}else{
			scmZdCommerceLevelManager.update(scmZdCommerceLevel);
			 msg = "更新商业级别成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除商业级别记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除商业级别记录", httpMethod = "DELETE", notes = "删除商业级别记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmZdCommerceLevelManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除scm_zd_commercelevel记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除商业级别记录", httpMethod = "DELETE", notes = "批量删除商业级别记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmZdCommerceLevelManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
	
	/**
	 * scm_zd_commercelevel列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/downBoxList")
	@ApiOperation(value="商业级别下拉框", httpMethod = "POST", notes = "获取商业级别列表")
	public CommonResult<List<ScmZdCommerceLevel>> downBoxList() throws Exception{
		List<ScmZdCommerceLevel> downBoxList = scmZdCommerceLevelManager.downBoxList();
		return new CommonResult<List<ScmZdCommerceLevel>>(true,"获取成功",downBoxList);
	}
	
}
