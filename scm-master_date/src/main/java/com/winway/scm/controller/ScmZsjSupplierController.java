package com.winway.scm.controller;

import java.util.List;

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
import com.winway.purchase.util.QuarterUtil;
import com.winway.scm.model.ScmZsjCommerce;
import com.winway.scm.model.ScmZsjSupplier;
import com.winway.scm.persistence.manager.ScmZsjSupplierManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * <pre> 
 * 描述：供应商表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmZsjSupplier/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmZsjSupplierController 供应商")
public class ScmZsjSupplierController extends BaseController{
	@Resource
	ScmZsjSupplierManager scmZsjSupplierManager;
	@Resource
	UCFeignService ucFeignService;
	/**
	 * 供应商表列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="供应商表数据列表", httpMethod = "POST", notes = "获取供应商表列表")
	public PageList<ScmZsjSupplier> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmZsjSupplierManager.query(queryFilter);
	}
	
	/**
	 * 供应商表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="供应商表数据详情",httpMethod = "GET",notes = "供应商表数据详情")
	public ScmZsjSupplier get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmZsjSupplierManager.get(id);
	}
	
    /**
	 * 新增供应商表
	 * @param scmZsjSupplier
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新供应商表数据", httpMethod = "POST", notes = "新增,更新供应商表数据")
	public CommonResult<String> save(@ApiParam(name="scmZsjSupplier",value="供应商表业务对象", required = true)@RequestBody ScmZsjSupplier scmZsjSupplier) throws Exception{
		String msg = "添加供应商表成功";
		scmZsjSupplierManager.getByNameOrId(scmZsjSupplier);
		JsonNode user = ucFeignService.getUser(current(), "");
		String userName = user.get("fullname").asText();
		scmZsjSupplier.setEnterPersion(userName);
		if(StringUtil.isEmpty(scmZsjSupplier.getId())){
			scmZsjSupplier.setFileCode(QuarterUtil.getCode("GYS"));
			scmZsjSupplierManager.create(scmZsjSupplier);
		}else{
			scmZsjSupplierManager.update(scmZsjSupplier);
			 msg = "更新供应商表成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除供应商表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除供应商表记录", httpMethod = "DELETE", notes = "删除供应商表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmZsjSupplierManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除供应商表记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除供应商表记录", httpMethod = "DELETE", notes = "批量删除供应商表记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmZsjSupplierManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
	/**
	 * 供应商首营记录与供应商表关联
	 * 在实体类中添加首营记录表字段,在mapper中添加结果集参数,并编写左连接查询语句
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/firstList")
	@ApiOperation(value="当前货主首营记录", httpMethod = "POST", notes = "需要与供应商表链接,并区分出是否首营的状态")
	public PageList<ScmZsjSupplier> firstList(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmZsjSupplierManager.firstList(queryFilter);
	}
	
	/**
	 * 供应商信息下拉框,带首营状态
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 * 
	 */
	@GetMapping("/downBox/{ownerId}")
	@ApiOperation(value="供应商信息下拉框", httpMethod = "GET", notes = "获取供应商信息下拉框")
	public List<ScmZsjSupplier> downBox(@ApiParam(name="ownerId",value="货主ID")@PathVariable String ownerId) throws Exception{
		return scmZsjSupplierManager.downBox(ownerId);
	}
	/**
	 * 供应商信息下拉框,首营审批通过
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 * 
	 */
	@GetMapping("/downBoxApplySuccess/{ownerId}")
	@ApiOperation(value="供应商信息下拉框,首营审批通过", httpMethod = "GET", notes = "供应商信息下拉框,首营审批通过")
	public List<ScmZsjSupplier> downBoxApplySuccess(@ApiParam(name="ownerId",value="货主ID")@PathVariable String ownerId) throws Exception{
		return scmZsjSupplierManager.downBoxApplySuccess(ownerId);
	}
	
}
