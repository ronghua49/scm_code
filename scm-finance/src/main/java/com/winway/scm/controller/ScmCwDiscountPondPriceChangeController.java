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
import com.winway.scm.persistence.manager.ScmCwDiscountPondPriceChangeManager;
import com.winway.scm.model.ScmCwDiscountPondPriceChange;
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
 * 描述：票折池金额变动记录表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-08-17 10:03:21
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmCwDiscountPondPriceChange/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmCwDiscountPondPriceChangeController")
public class ScmCwDiscountPondPriceChangeController extends BaseController{
	@Resource
	UCFeignService ucFeignService;
	@Resource
	ScmCwDiscountPondPriceChangeManager scmCwDiscountPondPriceChangeManager;
	
	/**
	 * 票折池金额变动记录表列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="票折池金额变动记录表数据列表", httpMethod = "POST", notes = "获取票折池金额变动记录表列表")
	public PageList<ScmCwDiscountPondPriceChange> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmCwDiscountPondPriceChangeManager.query(queryFilter);
	}
	
	/**
	 * 票折池金额变动记录表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="票折池金额变动记录表数据详情",httpMethod = "GET",notes = "票折池金额变动记录表数据详情")
	public ScmCwDiscountPondPriceChange get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmCwDiscountPondPriceChangeManager.get(id);
	}
	
    /**
	 * 新增票折池金额变动记录表
	 * @param scmCwDiscountPondPriceChange
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新票折池金额变动记录表数据", httpMethod = "POST", notes = "新增,更新票折池金额变动记录表数据")
	public CommonResult<String> save(@ApiParam(name="scmCwDiscountPondPriceChange",value="票折池金额变动记录表业务对象", required = true)@RequestBody ScmCwDiscountPondPriceChange scmCwDiscountPondPriceChange) throws Exception{
		String msg = "添加票折池金额变动记录表成功";
		if(StringUtil.isEmpty(scmCwDiscountPondPriceChange.getId())){
			scmCwDiscountPondPriceChangeManager.create(scmCwDiscountPondPriceChange);
		}else{
			scmCwDiscountPondPriceChangeManager.update(scmCwDiscountPondPriceChange);
			 msg = "更新票折池金额变动记录表成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除票折池金额变动记录表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除票折池金额变动记录表记录", httpMethod = "DELETE", notes = "删除票折池金额变动记录表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmCwDiscountPondPriceChangeManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除票折池金额变动记录表记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除票折池金额变动记录表记录", httpMethod = "DELETE", notes = "批量删除票折池金额变动记录表记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmCwDiscountPondPriceChangeManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
	
	/**
	 * 票折金额变动及变动记录
	 * @param scmCwDiscountPond
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="changePoodPrice")
	@ApiOperation(value = "票折金额变动及变动记录", httpMethod = "POST", notes = "票折金额变动及变动记录")
	public CommonResult<String> changePoodPrice(HttpServletRequest request,
			@ApiParam(name="scmCwDiscountPondPriceChange",value="票折变动记录", required = true)@RequestBody ScmCwDiscountPondPriceChange scmCwDiscountPondPriceChange) throws Exception{
		JsonNode user = ucFeignService.getUser(current(), "");
        String fullname = user.get("fullname").asText();
        scmCwDiscountPondPriceChange.setChangePersion(fullname);
		scmCwDiscountPondPriceChangeManager.changePoodPrice(scmCwDiscountPondPriceChange);
		return new CommonResult<>("修改成功");
	}
	
}
