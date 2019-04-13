package com.winway.scm.controller;

import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;
import com.winway.scm.model.ScmCwBanCommerce;
import com.winway.scm.persistence.manager.ScmCwBanCommerceManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 
 * <pre> 
 * 描述：禁止票折商业表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-23 18:19:26
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmCwBanCommerce/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmCwBanCommerceController 禁止票折商业表 控制器类")
public class ScmCwBanCommerceController extends BaseController{
	@Resource
	ScmCwBanCommerceManager scmCwBanCommerceManager;

	
	/**
	 * 禁止票折商业表列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="禁止票折商业表数据列表", httpMethod = "POST", notes = "获取禁止票折商业表列表")
	public PageList<ScmCwBanCommerce> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmCwBanCommerceManager.query(queryFilter);
	}
	
	/**
	 * 禁止票折商业表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="禁止票折商业表数据详情",httpMethod = "GET",notes = "禁止票折商业表数据详情")
	public ScmCwBanCommerce get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmCwBanCommerceManager.get(id);
	}
	
    /**
	 * 新增禁止票折商业表
	 * @param scmCwBanCommerce
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新禁止票折商业表数据", httpMethod = "POST", notes = "新增,更新禁止票折商业表数据")
	public CommonResult<String> save(@ApiParam(name="scmCwBanCommerce",value="禁止票折商业表业务对象", required = true)@RequestBody ScmCwBanCommerce scmCwBanCommerce) throws Exception{
		String msg = "添加禁止票折商业表成功";
		if(StringUtil.isEmpty(scmCwBanCommerce.getId())){
			scmCwBanCommerceManager.create(scmCwBanCommerce);
		}else{
			scmCwBanCommerceManager.update(scmCwBanCommerce);
			 msg = "更新禁止票折商业表成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除禁止票折商业表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除禁止票折商业表记录", httpMethod = "DELETE", notes = "删除禁止票折商业表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmCwBanCommerceManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除禁止票折商业表记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除禁止票折商业表记录", httpMethod = "DELETE", notes = "批量删除禁止票折商业表记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmCwBanCommerceManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}


}
