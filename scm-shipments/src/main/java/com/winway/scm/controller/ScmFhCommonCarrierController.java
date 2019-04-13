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
import com.winway.scm.persistence.manager.ScmFhCommonCarrierManager;
import com.winway.scm.model.ScmFhCommonCarrier;
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
 * 描述：承运商表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-02-15 17:31:50
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmFhCommonCarrier/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmFhCommonCarrierController 承运商")
public class ScmFhCommonCarrierController extends BaseController{
	@Resource
	ScmFhCommonCarrierManager scmFhCommonCarrierManager;
	
	@Resource
	UCFeignService ucFeignService;
	
	/**
	 * 承运商表列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="承运商表数据列表", httpMethod = "POST", notes = "获取承运商表列表")
	public PageList<ScmFhCommonCarrier> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmFhCommonCarrierManager.query(queryFilter);
	}
	
	/**
	 * 承运商表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="承运商表数据详情",httpMethod = "GET",notes = "承运商表数据详情")
	public ScmFhCommonCarrier get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmFhCommonCarrierManager.get(id);
	}
	
    /**
	 * 新增承运商表
	 * @param scmFhCommonCarrier
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新承运商表数据", httpMethod = "POST", notes = "新增,更新承运商表数据")
	public CommonResult<String> save(@ApiParam(name="scmFhCommonCarrier",value="承运商表业务对象", required = true)@RequestBody ScmFhCommonCarrier scmFhCommonCarrier) throws Exception{
		String msg = "添加承运商表成功";
		JsonNode user = ucFeignService.getUser(current(), "");
		String fullname = user.get("fullname").asText();
		scmFhCommonCarrier.setCreatePersion(fullname);
		if(StringUtil.isEmpty(scmFhCommonCarrier.getId())){
			scmFhCommonCarrierManager.create(scmFhCommonCarrier);
		}else{
			scmFhCommonCarrierManager.update(scmFhCommonCarrier);
			 msg = "更新承运商表成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除承运商表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除承运商表记录", httpMethod = "DELETE", notes = "删除承运商表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmFhCommonCarrierManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除承运商表记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除承运商表记录", httpMethod = "DELETE", notes = "批量删除承运商表记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmFhCommonCarrierManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
}
