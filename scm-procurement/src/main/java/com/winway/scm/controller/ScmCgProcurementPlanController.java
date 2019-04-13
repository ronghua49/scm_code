package com.winway.scm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.winway.scm.persistence.manager.ScmCgProcurementPlanManager;
import com.winway.scm.model.ScmCgProcurementOrder;
import com.winway.scm.model.ScmCgProcurementPlan;
import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;

/**
 * 
 * <pre> 
 * 描述：采购计划表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-26 13:47:44
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmCgProcurementPlan/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmCgProcurementPlanController 采购计划表")
public class ScmCgProcurementPlanController extends BaseController{
	@Resource
	ScmCgProcurementPlanManager scmCgProcurementPlanManager;
	
	/**
	 * 采购计划表列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="采购计划表数据列表", httpMethod = "POST", notes = "获取采购计划表列表")
	public PageList<ScmCgProcurementPlan> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmCgProcurementPlanManager.query(queryFilter);
	}
	
	/**
	 * 采购计划表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="采购计划表数据详情",httpMethod = "GET",notes = "采购计划表数据详情")
	public ScmCgProcurementPlan get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmCgProcurementPlanManager.get(id);
	}
	
    /**
	 * 新增采购计划表
	 * @param scmCgProcurementPlan
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新采购计划表数据", httpMethod = "POST", notes = "新增,更新采购计划表数据")
	public CommonResult<String> save(@ApiParam(name="scmCgProcurementPlan",value="采购计划表业务对象", required = true)@RequestBody ScmCgProcurementPlan scmCgProcurementPlan) throws Exception{
		String msg = "添加采购计划表成功";
		if(StringUtil.isEmpty(scmCgProcurementPlan.getId())){
			scmCgProcurementPlanManager.create(scmCgProcurementPlan);
		}else{
			scmCgProcurementPlanManager.update(scmCgProcurementPlan);
			 msg = "更新采购计划表成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除采购计划表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除采购计划表记录", httpMethod = "DELETE", notes = "删除采购计划表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmCgProcurementPlanManager.isRemove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除采购计划表记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除采购计划表记录", httpMethod = "DELETE", notes = "批量删除采购计划表记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmCgProcurementPlanManager.isRemoveByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
	
	/**
	 * 采购计划确认
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/affirm/{id}")
	@ApiOperation(value="采购计划表数据详情",httpMethod = "GET",notes = "采购计划确认")
	public ScmCgProcurementPlan affirm(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmCgProcurementPlanManager.get(id);
	}
	/**
	 * 采购计划导入	 
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 * 从reqeust中获取上传的文件
	 */
	@PostMapping("/toLoad")
	@ApiOperation(value=" 采购计划导入", httpMethod = "POST", notes = " 采购计划导入")
	public CommonResult<String> toLoad(@ApiParam(name="queryFilter",value="查询对象")
	@RequestBody String filePath, HttpServletRequest request,HttpServletResponse response) throws Exception{
		scmCgProcurementPlanManager.toLoad(filePath, request, response);
		return new CommonResult<String>(true, "导入成功");
	}
	
	/**
	 * 采购计划报表月度
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/planReport")
	@ApiOperation(value="采购计划报表", httpMethod = "POST", notes = "获取采购订单表列表")
	public PageList<ScmCgProcurementOrder> planReport(@ApiParam(name="queryFilter",value="查询对象")
		@RequestBody QueryFilter queryFilter) throws Exception{
		return null;
	}
	
}
