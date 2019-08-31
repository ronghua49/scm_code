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

import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;
import com.winway.scm.model.ScmCwAnnualFee;
import com.winway.scm.persistence.manager.ScmCwAnnualFeeManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * <pre> 
 * 描述：年费信息管理费详情 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-28 09:38:34
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmCwAnnualFee/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmCwAnnualFeeController 年费信息管理费详情")
public class ScmCwAnnualFeeController extends BaseController{
	@Resource
	ScmCwAnnualFeeManager scmCwAnnualFeeManager;
	
	/**
	 * 年费信息管理费详情列表(分页条件查询)数据
	 * @param
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="年费信息管理费详情数据列表", httpMethod = "POST", notes = "获取年费信息管理费详情列表")
	public PageList<ScmCwAnnualFee> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmCwAnnualFeeManager.query(queryFilter);
	}
	
	/**
	 * 年费信息管理费详情明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="年费信息管理费详情数据详情",httpMethod = "GET",notes = "年费信息管理费详情数据详情")
	public ScmCwAnnualFee get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmCwAnnualFeeManager.get(id);
	}
	
    /**
	 * 新增年费信息管理费详情
	 * @param scmCwAnnualFee
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新年费信息管理费详情数据", httpMethod = "POST", notes = "新增,更新年费信息管理费详情数据")
	public CommonResult<String> save(@ApiParam(name="scmCwAnnualFee",value="年费信息管理费详情业务对象", required = true)@RequestBody ScmCwAnnualFee scmCwAnnualFee) throws Exception{
		String msg = "添加年费信息管理费详情成功";
		if(StringUtil.isEmpty(scmCwAnnualFee.getId())){
			scmCwAnnualFeeManager.create(scmCwAnnualFee);
		}else{
			scmCwAnnualFeeManager.update(scmCwAnnualFee);
			 msg = "更新年费信息管理费详情成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除年费信息管理费详情记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除年费信息管理费详情记录", httpMethod = "DELETE", notes = "删除年费信息管理费详情记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmCwAnnualFeeManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除年费信息管理费详情记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除年费信息管理费详情记录", httpMethod = "DELETE", notes = "批量删除年费信息管理费详情记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmCwAnnualFeeManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
}
