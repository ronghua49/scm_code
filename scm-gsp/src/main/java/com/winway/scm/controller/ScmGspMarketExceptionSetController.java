package com.winway.scm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.Date;
import java.util.List;

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
import com.winway.scm.persistence.manager.ScmGspMarketExceptionSetManager;
import com.winway.scm.vo.ScmGspMarketExceptionMonitorListVo;
import com.winway.scm.model.ScmGspMarketExceptionSet;
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
 * 描述：销售异动商品设置表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-24 12:00:21
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmGspMarketExceptionSet/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmGspMarketExceptionSetController 销售异动商品设置表")
public class ScmGspMarketExceptionSetController extends BaseController{
	@Resource
	ScmGspMarketExceptionSetManager scmGspMarketExceptionSetManager;
	@Resource 
	UCFeignService ucFeignService;
	/**
	 * 销售异动商品设置表列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="销售异动商品设置表数据列表", httpMethod = "POST", notes = "获取销售异动商品设置表列表")
	public PageList<ScmGspMarketExceptionSet> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmGspMarketExceptionSetManager.query(queryFilter);
	}
	/**
	 * 销售异动监控数据列表
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@GetMapping("/monitorList/{ownerId}")
	@ApiOperation(value="销售异动商品设置表数据列表", httpMethod = "GET", notes = "获取销售异动商品设置表列表")
	public CommonResult<List<ScmGspMarketExceptionMonitorListVo>> monitorList(@ApiParam(name="ownerId",value="查询对象")@PathVariable String ownerId) throws Exception{
		List<ScmGspMarketExceptionMonitorListVo> monitorList = scmGspMarketExceptionSetManager.monitorList(ownerId);
		return new CommonResult<List<ScmGspMarketExceptionMonitorListVo>>(true, "查询成功", monitorList);
	}
	
	/**
	 * 销售异动商品设置表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="销售异动商品设置表数据详情",httpMethod = "GET",notes = "销售异动商品设置表数据详情")
	public ScmGspMarketExceptionSet get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmGspMarketExceptionSetManager.get(id);
	}
	
    /**
	 * 新增销售异动商品设置表
	 * @param scmGspMarketExceptionSet
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新销售异动商品设置表数据", httpMethod = "POST", notes = "新增,更新销售异动商品设置表数据")
	public CommonResult<String> save(@ApiParam(name="scmGspMarketExceptionSet",value="销售异动商品设置表业务对象", required = true)@RequestBody ScmGspMarketExceptionSet scmGspMarketExceptionSet) throws Exception{
		String msg = "添加销售异动商品设置表成功";
		if(StringUtil.isEmpty(scmGspMarketExceptionSet.getId())){
			JsonNode user = ucFeignService.getUser(current(), "");
			String fullname = user.get("fullname").asText();
			scmGspMarketExceptionSet.setCreatePersion(fullname);
			scmGspMarketExceptionSet.setCreateDate(new Date());
			scmGspMarketExceptionSetManager.create(scmGspMarketExceptionSet);
		}else{
			scmGspMarketExceptionSetManager.update(scmGspMarketExceptionSet);
			 msg = "更新销售异动商品设置表成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除销售异动商品设置表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除销售异动商品设置表记录", httpMethod = "DELETE", notes = "删除销售异动商品设置表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmGspMarketExceptionSetManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除销售异动商品设置表记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除销售异动商品设置表记录", httpMethod = "DELETE", notes = "批量删除销售异动商品设置表记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmGspMarketExceptionSetManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
}
