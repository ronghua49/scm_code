package com.winway.scm.controller;

import com.winway.scm.persistence.manager.ScmCwBAStorageDiscountManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.annotation.Resource;
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
import com.winway.scm.persistence.manager.ScmCwBAPaymentDiscountManager;
import com.winway.scm.model.ScmCwBAPaymentDiscount;
import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;

/**
 * 
 * <pre> 
 * 描述：经销商RB核算付款折扣 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-09 18:18:05
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmCwBAPaymentDiscount/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmCwBAPaymentDiscountController 经销商RB核算付款折扣 ")
public class ScmCwBAPaymentDiscountController extends BaseController{
	@Resource
	ScmCwBAPaymentDiscountManager scmCwBAPaymentDiscountManager;

	
	/**
	 * 经销商RB核算付款折扣列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="经销商RB核算付款折扣数据列表", httpMethod = "POST", notes = "获取经销商RB核算付款折扣列表")
	public PageList<ScmCwBAPaymentDiscount> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmCwBAPaymentDiscountManager.query(queryFilter);
	}
	/**
	 * 经销商RB核算付款折扣列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/affirmList")
	@ApiOperation(value="经销商RB核算确认付款折扣数据", httpMethod = "POST", notes = "经销商RB核算确认付款折扣数据")
	public PageList<ScmCwBAPaymentDiscount> affirmList(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmCwBAPaymentDiscountManager.affirmList(queryFilter);
	}
	
	/**
	 * 经销商RB核算付款折扣明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="经销商RB核算付款折扣数据详情",httpMethod = "GET",notes = "经销商RB核算付款折扣数据详情")
	public ScmCwBAPaymentDiscount get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmCwBAPaymentDiscountManager.get(id);
	}
	
    /**
	 * 新增经销商RB核算付款折扣
	 * @param scmCwBAPaymentDiscount
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新经销商RB核算付款折扣数据", httpMethod = "POST", notes = "新增,更新经销商RB核算付款折扣数据")
	public CommonResult<String> save(@ApiParam(name="scmCwBAPaymentDiscount",value="经销商RB核算付款折扣业务对象", required = true)@RequestBody ScmCwBAPaymentDiscount scmCwBAPaymentDiscount) throws Exception{
		String msg = "添加经销商RB核算付款折扣成功";
		if(StringUtil.isEmpty(scmCwBAPaymentDiscount.getId())){
			scmCwBAPaymentDiscountManager.create(scmCwBAPaymentDiscount);
		}else{
			scmCwBAPaymentDiscountManager.update(scmCwBAPaymentDiscount);
			 msg = "更新经销商RB核算付款折扣成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除经销商RB核算付款折扣记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除经销商RB核算付款折扣记录", httpMethod = "DELETE", notes = "删除经销商RB核算付款折扣记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmCwBAPaymentDiscountManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除经销商RB核算付款折扣记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除经销商RB核算付款折扣记录", httpMethod = "DELETE", notes = "批量删除经销商RB核算付款折扣记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmCwBAPaymentDiscountManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}

	/**
	 * 经销商Rb核算汇总全部导出
	 * @param
	 * @return
	 * @throws Exception
	 * ModelAndView
	 */
	@PostMapping(value="/exportCheckSum")
	public void exportCheckSum(@RequestBody QueryFilter queryFilter, HttpServletResponse response) throws Exception{
		scmCwBAPaymentDiscountManager.exportCheckSum(queryFilter,response);
	}

}
