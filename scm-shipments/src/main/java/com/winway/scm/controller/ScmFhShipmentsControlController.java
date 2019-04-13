package com.winway.scm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.winway.scm.persistence.manager.ScmFhShipmentsControlManager;
import com.winway.scm.model.ScmFhShipmentsControl;
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
 * 描述：发货控制 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-02-15 17:31:50
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmFhShipmentsControl/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmFhShipmentsControlController 发货控制")
public class ScmFhShipmentsControlController extends BaseController{
	@Resource
	ScmFhShipmentsControlManager scmFhShipmentsControlManager;
	@Resource
	UCFeignService ucFeignService;

	/**
	 * 发货控制列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="发货控制数据列表", httpMethod = "POST", notes = "获取发货控制列表")
	public PageList<ScmFhShipmentsControl> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmFhShipmentsControlManager.query(queryFilter);
	}
	
	/**
	 * 发货控制明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="发货控制数据详情",httpMethod = "GET",notes = "发货控制数据详情")
	public ScmFhShipmentsControl get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmFhShipmentsControlManager.get(id);
	}
	
    /**
	 * 新增发货控制
	 * @param scmFhShipmentsControl
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新发货控制数据", httpMethod = "POST", notes = "新增,更新发货控制数据")
	public CommonResult<String> save(@ApiParam(name="scmFhShipmentsControl",value="发货控制业务对象", required = true)@RequestBody ScmFhShipmentsControl scmFhShipmentsControl) throws Exception{
		String msg = "添加发货控制成功";
		JsonNode user = ucFeignService.getUser(current(), "");
		String fullname = user.get("fullname").asText();
		scmFhShipmentsControl.setCreatePersion(fullname);
		scmFhShipmentsControl.setCreateDate(new Date());
		if(StringUtil.isEmpty(scmFhShipmentsControl.getId())){
			scmFhShipmentsControlManager.create(scmFhShipmentsControl);
		}else{
			scmFhShipmentsControlManager.update(scmFhShipmentsControl);
			 msg = "更新发货控制成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除发货控制记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除发货控制记录", httpMethod = "DELETE", notes = "删除发货控制记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		JsonNode user = ucFeignService.getUser(current(), "");
		String fullname = user.get("fullname").asText();
		scmFhShipmentsControlManager.remove(id,fullname);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除发货控制记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除发货控制记录", httpMethod = "DELETE", notes = "批量删除发货控制记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmFhShipmentsControlManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
	
	
	/**
	 * 获取指定商业不可发货的数据列表
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/ListCommerceBanProduct/{commerceId}")
	@ApiOperation(value="发货控制数据详情",httpMethod = "GET",notes = "发货控制数据详情")
	public Map<String,String> ListCommerceBanProduct(@ApiParam(name="commerceId",value="商业ID", required = true)@PathVariable String commerceId) throws Exception{
		Map<String,String> map = scmFhShipmentsControlManager.ListCommerceBanProduct(commerceId);
		return map;
	}
	
	
	
}
