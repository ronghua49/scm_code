package com.winway.scm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import com.winway.scm.persistence.manager.ScmZsjProductLineManager;
import com.winway.scm.model.ScmZsjProductLine;
import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.controller.BaseController;
import com.hotent.base.feign.UCFeignService;
import com.hotent.base.model.CommonResult;
import com.hotent.base.model.WinwayResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;

/**
 * 
 * <pre> 
 * 描述：产品系列 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 16:15:43
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmZsjProductLine/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmZsjProductLineController 产品系列")
public class ScmZsjProductLineController extends BaseController{
	@Resource
	ScmZsjProductLineManager scmZsjProductLineManager;
	
	@Resource
	UCFeignService ucFeignService;
	
	/**
	 * 产品系列列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="产品系列数据列表", httpMethod = "POST", notes = "获取产品系列列表")
	public PageList<ScmZsjProductLine> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmZsjProductLineManager.query(queryFilter);
	}
	
	/**
	 * 产品系列明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="产品系列数据详情",httpMethod = "GET",notes = "产品系列数据详情")
	public ScmZsjProductLine get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmZsjProductLineManager.get(id);
	}
	
    /**
	 * 新增产品系列
	 * @param scmZsjProductLine
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新产品系列数据", httpMethod = "POST", notes = "新增,更新产品系列数据")
	public CommonResult<String> save(@ApiParam(name="scmZsjProductLine",value="产品系列业务对象", required = true)@RequestBody ScmZsjProductLine scmZsjProductLine) throws Exception{
		String msg = "添加产品系列成功";
		JsonNode user = ucFeignService.getUser(current(), "");
		String userName = user.get("fullname").asText();
		scmZsjProductLine.setCreatePersion(userName);
		if(StringUtil.isEmpty(scmZsjProductLine.getId())){
			scmZsjProductLineManager.create(scmZsjProductLine);
		}else{
			scmZsjProductLineManager.update(scmZsjProductLine);
			 msg = "更新产品系列成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除产品系列记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除产品系列记录", httpMethod = "DELETE", notes = "删除产品系列记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmZsjProductLineManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除产品系列记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除产品系列记录", httpMethod = "DELETE", notes = "批量删除产品系列记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmZsjProductLineManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
	
	/**
	 * 产品系列状态调整
	 * 调整状态并返回调整后的状态
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/changeState/{id}")
	@ApiOperation(value="产品系列状态调整",httpMethod = "GET",notes = "产品系列状态调整")
	public CommonResult<String> changeState(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id,HttpServletRequest request) throws Exception{
		String changeState = scmZsjProductLineManager.changeState(id);
		return  new CommonResult<String>(true, "批量删除成功",changeState);
	}
	
	/**
	 * 商品系列数据修改同步
	 * @param
	 * @return
	 * @throws Exception
	 * ModelAndView
	 */
	@GetMapping(value="/updateSyn/{id}")
	@ApiOperation(value="商品系列数据修改同步",httpMethod = "GET",notes = "商品系列数据修改同步")
	public CommonResult<String> updateSyn(@ApiParam(name="id",value="商品主键")@PathVariable String id) throws Exception{
		scmZsjProductLineManager.updateSyn(id);
		return new CommonResult<>(true, "同步成功");
	}
	
}
