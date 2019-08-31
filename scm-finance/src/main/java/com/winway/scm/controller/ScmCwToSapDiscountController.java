package com.winway.scm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.annotation.Resource;
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
import com.winway.scm.persistence.manager.ScmCwToSapDiscountManager;
import com.winway.scm.model.ScmCwToSapDiscount;
import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;

/**
 * 
 * <pre> 
 * 描述：推送sap开票确认表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-07-05 23:55:03
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmCwToSapDiscount/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmCwToSapDiscountController")
public class ScmCwToSapDiscountController extends BaseController{
	@Resource
	ScmCwToSapDiscountManager scmCwToSapDiscountManager;
	
	/**
	 * 推送sap开票确认表列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="推送sap开票确认表数据列表", httpMethod = "POST", notes = "获取推送sap开票确认表列表")
	public PageList<ScmCwToSapDiscount> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmCwToSapDiscountManager.query(queryFilter);
	}
	
	/**
	 * 推送sap开票确认表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/makeInvoice/{id}")
	@ApiOperation(value="推送sap开票确认表数据详情",httpMethod = "GET",notes = "推送sap开票确认表数据详情")
	public CommonResult<ScmCwToSapDiscount> makeInvoice(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		try{
			ScmCwToSapDiscount makeInvoice = scmCwToSapDiscountManager.makeInvoice(id);
			return new CommonResult<ScmCwToSapDiscount>(true,"开票成功",makeInvoice);
		}catch (Exception e) {
			return new CommonResult<ScmCwToSapDiscount>(false,e.getMessage());
		}
	}
	/**
	 * 推送sap开票确认表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="推送sap开票确认表数据详情",httpMethod = "GET",notes = "推送sap开票确认表数据详情")
	public ScmCwToSapDiscount get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmCwToSapDiscountManager.get(id);
	}
	/**
	 * 推送sap开票确认表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/getByShipId/{shipId}")
	@ApiOperation(value="推送sap开票确认表数据详情",httpMethod = "GET",notes = "推送sap开票确认表数据详情")
	public CommonResult<ScmCwToSapDiscount> getByShipId(@ApiParam(name="shipId",value="业务对象主键", required = true)@PathVariable String shipId) throws Exception{
		ScmCwToSapDiscount ScmCwToSapDiscount = scmCwToSapDiscountManager.getByShipId(shipId);
		return new CommonResult<ScmCwToSapDiscount>(true,"获取成功",ScmCwToSapDiscount);
	}
	
    /**
	 * 新增推送sap开票确认表
	 * @param scmCwToSapDiscount
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新推送sap开票确认表数据", httpMethod = "POST", notes = "新增,更新推送sap开票确认表数据")
	public CommonResult<String> save(@ApiParam(name="scmCwToSapDiscount",value="推送sap开票确认表业务对象", required = true)@RequestBody ScmCwToSapDiscount scmCwToSapDiscount) throws Exception{
		String msg = "添加推送sap开票确认表成功";
		if(StringUtil.isEmpty(scmCwToSapDiscount.getId())){
			scmCwToSapDiscountManager.create(scmCwToSapDiscount);
		}else{
			scmCwToSapDiscountManager.update(scmCwToSapDiscount);
			 msg = "更新推送sap开票确认表成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除推送sap开票确认表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除推送sap开票确认表记录", httpMethod = "DELETE", notes = "删除推送sap开票确认表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmCwToSapDiscountManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除推送sap开票确认表记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除推送sap开票确认表记录", httpMethod = "DELETE", notes = "批量删除推送sap开票确认表记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmCwToSapDiscountManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
}
