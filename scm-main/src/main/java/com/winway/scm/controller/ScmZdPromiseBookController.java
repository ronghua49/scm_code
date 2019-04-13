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
import com.winway.scm.model.ScmZdPromiseBook;
import com.winway.scm.persistence.manager.ScmZdPromiseBookManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * <pre> 
 * 描述：承诺书 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-29 10:31:25
 * 版权：美达项目组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmZdPromiseBook/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmZdPromiseBookController 承诺书")
public class ScmZdPromiseBookController extends BaseController{
	@Resource
	ScmZdPromiseBookManager scmZdPromiseBookManager;
	@Resource
	UCFeignService ucFeignService;
	@Resource
	ScmMasterDateFeignService scmFeignService;
	
	/**
	 * 承诺书列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="承诺书数据列表", httpMethod = "POST", notes = "获取承诺书列表,query中必须添加货主ID查询条件")
	public PageList<ScmZdPromiseBook> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmZdPromiseBookManager.query(queryFilter);
	}
	
	/**
	 * 承诺书明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="承诺书数据详情",httpMethod = "GET",notes = "承诺书数据详情")
	public ScmZdPromiseBook get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmZdPromiseBookManager.get(id);
	}
	
    /**
	 * 新增承诺书
	 * @param scmZdPromiseBook
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新承诺书数据", httpMethod = "POST", notes = "新增,更新承诺书数据")
	public CommonResult<String> save(@ApiParam(name="scmZdPromiseBook",value="承诺书业务对象", required = true)@RequestBody ScmZdPromiseBook scmZdPromiseBook) throws Exception{
		String msg = "添加承诺书成功";
		scmZdPromiseBookManager.getByNameAndCodeOrId(scmZdPromiseBook);
		JsonNode user = ucFeignService.getUser(current(), "");
		String fullname = user.get("fullname").asText();
		scmZdPromiseBook.setCreatePersion(fullname);
		if(StringUtil.isEmpty(scmZdPromiseBook.getId())){
//			String account = user.get("account").asText();
//			OwnerMessage ownerId = scmFeignService.getOwnerId(account);
//			scmZdPromiseBook.setOwnerId(ownerId.getOwnerId());
			scmZdPromiseBookManager.create(scmZdPromiseBook);
		}else{
			scmZdPromiseBookManager.update(scmZdPromiseBook);
			 msg = "更新承诺书成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除承诺书记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除承诺书记录", httpMethod = "DELETE", notes = "删除承诺书记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmZdPromiseBookManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除承诺书记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除承诺书记录", httpMethod = "DELETE", notes = "批量删除承诺书记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmZdPromiseBookManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
}
