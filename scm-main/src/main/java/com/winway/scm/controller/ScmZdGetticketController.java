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

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.controller.BaseController;
import com.hotent.base.feign.UCFeignService;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;
import com.winway.purchase.feign.ScmMasterDateFeignService;
import com.winway.scm.model.ScmZdGetticket;
import com.winway.scm.persistence.manager.ScmZdGetticketManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * <pre> 
 * 描述：收票方 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-29 10:31:24
 * 版权：美达项目组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmZdGetticket/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmZdGetticketController 收票方")
public class ScmZdGetticketController extends BaseController{
	@Resource
	ScmZdGetticketManager scmZdGetticketManager;
	@Resource
	UCFeignService ucFeignService;  
	@Resource
	ScmMasterDateFeignService scmFeignService;
	/**
	 * 收票方列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="收票方数据列表", httpMethod = "POST", notes = "获取收票方列表,query中必须添加货主ID查询条件")
	public PageList<ScmZdGetticket> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmZdGetticketManager.query(queryFilter);
	}
	
	/**
	 * 收票方明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="收票方数据详情",httpMethod = "GET",notes = "收票方数据详情")
	public ScmZdGetticket get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmZdGetticketManager.get(id);
	}
	
    /**
	 * 新增收票方
	 * @param scmZdGetticket
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新收票方数据", httpMethod = "POST", notes = "新增,更新收票方数据")
	public CommonResult<String> save(@ApiParam(name="scmZdGetticket",value="收票方业务对象", required = true)@RequestBody ScmZdGetticket scmZdGetticket) throws Exception{
		String msg = "添加收票方成功";
		scmZdGetticketManager.getByNameAndCodeOrId(scmZdGetticket);
		JsonNode user = ucFeignService.getUser(current(), "");
		String fullname = user.get("fullname").asText();
		scmZdGetticket.setCreatePersion(fullname);
		if(StringUtil.isEmpty(scmZdGetticket.getId())){
//			String account = user.get("account").asText();
//			OwnerMessage ownerId = scmFeignService.getOwnerId(account);
//			scmZdGetticket.setOwnerId(ownerId.getOwnerId());
			scmZdGetticketManager.create(scmZdGetticket);
		}else{
			scmZdGetticketManager.update(scmZdGetticket);
			 msg = "更新收票方成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除收票方记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除收票方记录", httpMethod = "DELETE", notes = "删除收票方记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmZdGetticketManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除收票方记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除收票方记录", httpMethod = "DELETE", notes = "批量删除收票方记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmZdGetticketManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
}
