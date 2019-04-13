package com.winway.scm.controller;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.controller.BaseController;
import com.hotent.base.feign.UCFeignService;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;
import com.winway.scm.model.ScmFhFocusShipmentsSet;
import com.winway.scm.persistence.manager.ScmFhFocusShipmentsSetManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * <pre> 
 * 描述：集中发货设置表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-02-15 17:31:50
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmFhFocusShipmentsSet/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmFhFocusShipmentsSetController 集中发货设置")
public class ScmFhFocusShipmentsSetController extends BaseController{
	@Resource
	ScmFhFocusShipmentsSetManager scmFhFocusShipmentsSetManager;
	@Resource
	UCFeignService ucFeignService;
	/**
	 * 集中发货设置表列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="集中发货设置表数据列表", httpMethod = "POST", notes = "获取集中发货设置表列表")
	public PageList<ScmFhFocusShipmentsSet> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmFhFocusShipmentsSetManager.query(queryFilter);
	}
	
	/**
	 * 集中发货设置表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="集中发货设置表数据详情",httpMethod = "GET",notes = "集中发货设置表数据详情")
	public ScmFhFocusShipmentsSet get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmFhFocusShipmentsSetManager.get(id);
	}
	
    /**
	 * 新增集中发货设置表
	 * @param scmFhFocusShipmentsSet
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新集中发货设置表数据", httpMethod = "POST", notes = "新增,更新集中发货设置表数据")
	public CommonResult<String> save(@ApiParam(name="scmFhFocusShipmentsSet",value="集中发货设置表业务对象", required = true)@RequestBody ScmFhFocusShipmentsSet scmFhFocusShipmentsSet) throws Exception{
		String msg = "添加集中发货设置表成功";
		if(StringUtil.isEmpty(scmFhFocusShipmentsSet.getId())){
			scmFhFocusShipmentsSetManager.create(scmFhFocusShipmentsSet);
		}else{
			scmFhFocusShipmentsSetManager.update(scmFhFocusShipmentsSet);
			 msg = "更新集中发货设置表成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除集中发货设置表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除集中发货设置表记录", httpMethod = "DELETE", notes = "删除集中发货设置表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmFhFocusShipmentsSetManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除集中发货设置表记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除集中发货设置表记录", httpMethod = "DELETE", notes = "批量删除集中发货设置表记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmFhFocusShipmentsSetManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
	
	@PostMapping("/saves")
	@ApiOperation(value="集中发货批量保存", httpMethod = "POST", notes = "集中发货批量保存")
	public CommonResult<String> saves(@ApiParam(name="scmFhFocusShipmentsSets",value="集中发货设置列表", required = true)@RequestBody List<ScmFhFocusShipmentsSet> scmFhFocusShipmentsSets) {
		JsonNode user = ucFeignService.getUser(current(), "");
		String userName = user.get("fullname").asText();
		scmFhFocusShipmentsSetManager.saves(scmFhFocusShipmentsSets,userName);
		return new CommonResult<String>(true, "保存成功");
	}
	
	@GetMapping(value="/startAndStop/{id}")
	@ApiOperation(value="集中发货设置启用停用", httpMethod = "POST", notes = "集中发货设置启用停用,后台返回修改后的状态(0:停用,1启用,前端根据返回状态调整数据状态)")
	public CommonResult<String> startAndStop(@ApiParam(name="id",value="数据主键ID", required = true)@PathVariable String id) {
		String startAndStop = scmFhFocusShipmentsSetManager.startAndStop(id);
		return new CommonResult<String>(true, "处理成功",startAndStop);
	}
	
	@GetMapping(value="/listFocusShipmentsSetByComm/{commerceId}/{ownerId}")
	@ApiOperation(value="根据商业ID,货主ID获取集中发货设置列表", httpMethod = "GET", notes = "根据商业ID,货主ID获取集中发货设置列表")
	public String listFocusShipmentsSetByComm(@ApiParam(name="commerceId",value="数据主键ID", required = true)@PathVariable String commerceId,
			@ApiParam(name="ownerId",value="数据主键ID", required = true)@PathVariable String ownerId) throws JsonProcessingException {
		String listFocusShipmentsSetByComm = scmFhFocusShipmentsSetManager.listFocusShipmentsSetByComm(commerceId,ownerId);
		return listFocusShipmentsSetByComm;
	}
}
