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
import com.winway.scm.model.ScmZdDutyAffirm;
import com.winway.scm.persistence.manager.ScmZdDutyAffirmManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * <pre> 
 * 描述：责任认定 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-29 10:31:24
 * 版权：美达项目组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmZdDutyAffirm/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmZdDutyAffirmController 责任认定")
public class ScmZdDutyAffirmController extends BaseController{
	@Resource
	ScmZdDutyAffirmManager scmZdDutyAffirmManager;
	
	@Resource
	UCFeignService ucFeignService;  
	/**
	 * 责任认定列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="责任认定数据列表", httpMethod = "POST", notes = "获取责任认定列表,query中必须添加货主ID查询条件")
	public PageList<ScmZdDutyAffirm> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmZdDutyAffirmManager.query(queryFilter);
	}
	
	/**
	 * 责任认定明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="责任认定数据详情",httpMethod = "GET",notes = "责任认定数据详情")
	public ScmZdDutyAffirm get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmZdDutyAffirmManager.get(id);
	}
	
    /**
	 * 新增责任认定
	 * @param scmZdDutyAffirm
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新责任认定数据", httpMethod = "POST", notes = "新增,更新责任认定数据")
	public CommonResult<String> save(@ApiParam(name="scmZdDutyAffirm",value="责任认定业务对象", required = true)@RequestBody ScmZdDutyAffirm scmZdDutyAffirm) throws Exception{
		String msg = "添加责任认定成功";
		
		scmZdDutyAffirmManager.getByNameAndCodeOrId(scmZdDutyAffirm);
		
		JsonNode user = ucFeignService.getUser(current(), "");
		String userName = user.get("fullname").asText();
		
		scmZdDutyAffirm.setCreatePersion(userName);
		if(StringUtil.isEmpty(scmZdDutyAffirm.getId())){
//			String account = user.get("account").asText();
//			OwnerMessage ownerId = scmFeignService.getOwnerId(account);
//			scmZdDutyAffirm.setOwnerId(ownerId.getOwnerId());
			scmZdDutyAffirmManager.create(scmZdDutyAffirm);
		}else{
			scmZdDutyAffirmManager.update(scmZdDutyAffirm);
			 msg = "更新责任认定成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除责任认定记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除责任认定记录", httpMethod = "DELETE", notes = "删除责任认定记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmZdDutyAffirmManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除责任认定记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除责任认定记录", httpMethod = "DELETE", notes = "批量删除责任认定记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmZdDutyAffirmManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
}
