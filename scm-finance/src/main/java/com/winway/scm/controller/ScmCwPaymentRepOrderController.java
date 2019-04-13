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
import com.winway.scm.persistence.manager.ScmCwPaymentRepOrderManager;
import com.winway.scm.model.ScmCwPaymentRepOrder;
import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;

/**
 * 
 * <pre> 
 * 描述：付款采购订单补单表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-23 18:19:25
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmCwPaymentRepOrder/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmCwPaymentRepOrderController 付款采购订单补单表 控制器类")
public class ScmCwPaymentRepOrderController extends BaseController{
	@Resource
	ScmCwPaymentRepOrderManager scmCwPaymentRepOrderManager;
	
	/**
	 * 付款采购订单补单表列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="付款采购订单补单表数据列表", httpMethod = "POST", notes = "获取付款采购订单补单表列表")
	public PageList<ScmCwPaymentRepOrder> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmCwPaymentRepOrderManager.query(queryFilter);
	}
	
	/**
	 * 付款采购订单补单表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="付款采购订单补单表数据详情",httpMethod = "GET",notes = "付款采购订单补单表数据详情")
	public ScmCwPaymentRepOrder get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmCwPaymentRepOrderManager.get(id);
	}
	
    /**
	 * 新增付款采购订单补单表
	 * @param scmCwPaymentRepOrder
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新付款采购订单补单表数据", httpMethod = "POST", notes = "新增,更新付款采购订单补单表数据")
	public CommonResult<String> save(@ApiParam(name="scmCwPaymentRepOrder",value="付款采购订单补单表业务对象", required = true)@RequestBody ScmCwPaymentRepOrder scmCwPaymentRepOrder) throws Exception{
		String msg = "添加付款采购订单补单表成功";
		if(StringUtil.isEmpty(scmCwPaymentRepOrder.getId())){
			scmCwPaymentRepOrderManager.create(scmCwPaymentRepOrder);
		}else{
			scmCwPaymentRepOrderManager.update(scmCwPaymentRepOrder);
			 msg = "更新付款采购订单补单表成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除付款采购订单补单表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除付款采购订单补单表记录", httpMethod = "DELETE", notes = "删除付款采购订单补单表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmCwPaymentRepOrderManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除付款采购订单补单表记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除付款采购订单补单表记录", httpMethod = "DELETE", notes = "批量删除付款采购订单补单表记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmCwPaymentRepOrderManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
}
