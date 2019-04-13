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
import com.winway.scm.model.ScmZdReturnRequestCause;
import com.winway.scm.persistence.manager.ScmZdReturnRequestCauseManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * <pre> 
 * 描述：退货申请原因 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-29 10:31:25	
 * 版权：美达项目组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmZdReturnRequestCause/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmZdReturnRequestCauseController 退货申请原因")
public class ScmZdReturnRequestCauseController extends BaseController{
	@Resource
	ScmZdReturnRequestCauseManager scmZdReturnRequestCauseManager;
	@Resource
	UCFeignService ucFeignService;
	@Resource
	ScmMasterDateFeignService scmFeignService;
	
	/**
	 * 退货申请原因列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="退货申请原因数据列表", httpMethod = "POST", notes = "获取退货申请原因列表,query中必须添加货主ID查询条件")
	public PageList<ScmZdReturnRequestCause> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmZdReturnRequestCauseManager.query(queryFilter);
	}
	
	/**
	 * 退货申请原因明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="退货申请原因数据详情",httpMethod = "GET",notes = "退货申请原因数据详情")
	public ScmZdReturnRequestCause get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmZdReturnRequestCauseManager.get(id);
	}
	
    /**
	 * 新增退货申请原因
	 * @param scmZdReturnRequestCause
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新退货申请原因数据", httpMethod = "POST", notes = "新增,更新退货申请原因数据")
	public CommonResult<String> save(@ApiParam(name="scmZdReturnRequestCause",value="退货申请原因业务对象", required = true)@RequestBody ScmZdReturnRequestCause scmZdReturnRequestCause) throws Exception{
		String msg = "添加退货申请原因成功";
		scmZdReturnRequestCauseManager.getByNameAndCodeOrId(scmZdReturnRequestCause);
		JsonNode user = ucFeignService.getUser(current(), "");
		String fullname = user.get("fullname").asText();
		scmZdReturnRequestCause.setCreatePersion(fullname);
		if(StringUtil.isEmpty(scmZdReturnRequestCause.getId())){
//			String account = user.get("account").asText();
//			OwnerMessage ownerId = scmFeignService.getOwnerId(account);
//			scmZdReturnRequestCause.setOwnerId(ownerId.getOwnerId());
			scmZdReturnRequestCauseManager.create(scmZdReturnRequestCause);
		}else{
			scmZdReturnRequestCauseManager.update(scmZdReturnRequestCause);
			 msg = "更新退货申请原因成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除退货申请原因记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除退货申请原因记录", httpMethod = "DELETE", notes = "删除退货申请原因记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmZdReturnRequestCauseManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除退货申请原因记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除退货申请原因记录", httpMethod = "DELETE", notes = "批量删除退货申请原因记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmZdReturnRequestCauseManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
}
