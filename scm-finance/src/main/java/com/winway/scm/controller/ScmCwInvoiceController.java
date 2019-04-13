package com.winway.scm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.Date;
import java.util.List;

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
import com.winway.scm.persistence.manager.ScmCwInvoiceManager;
import com.winway.scm.vo.VInvoice;
import com.winway.scm.model.ScmCwInvoice;
import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.controller.BaseController;
import com.hotent.base.feign.UCFeignService;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;

/**
 * 
 * <pre> 
 * 描述：商业发票表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-23 18:19:25
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmCwInvoice/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmCwInvoiceController 商业发票表 控制器类")
public class ScmCwInvoiceController extends BaseController{
	@Resource
	ScmCwInvoiceManager scmCwInvoiceManager;
	@Resource
	UCFeignService ucFeignService;
	/**
	 * 商业发票表列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="商业发票表数据列表", httpMethod = "POST", notes = "获取商业发票表列表")
	public PageList<ScmCwInvoice> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmCwInvoiceManager.query(queryFilter);
	}
	/**
	 * 商业发票表列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/history")
	@ApiOperation(value="商业发票表数据列表", httpMethod = "POST", notes = "获取商业发票表列表")
	public PageList<VInvoice> history(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmCwInvoiceManager.history(queryFilter);
	}
	/**
	 * 确认数据(批量)
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/affirmData")
	@ApiOperation(value="确认数据(批量)", httpMethod = "POST", notes = "确认数据(批量)")
	public CommonResult<String> affirmData(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		JsonNode user = ucFeignService.getUser(current(), "");
		String fullname = user.get("fullname").asText();
		String msg="数据确认失败！";
		for (String id : ids) {
			ScmCwInvoice invoice = scmCwInvoiceManager.get(id);
			invoice.setIsAffirm("1");
			invoice.setGatherPersion(fullname);
			invoice.setGathererDate(new Date());
			scmCwInvoiceManager.update(invoice);
			msg="数据确认成功！";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 商业发票表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="商业发票表数据详情",httpMethod = "GET",notes = "商业发票表数据详情")
	public ScmCwInvoice get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmCwInvoiceManager.get(id);
	}
	/**
	 * 商业发票表采集
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/collect/{ownerId}")
	@ApiOperation(value="商业发票表采集",httpMethod = "GET",notes = "商业发票表采集")
	public ScmCwInvoice collect(@ApiParam(name="ownerId",value="货主ID", required = true)@PathVariable String ownerId) throws Exception{
		return scmCwInvoiceManager.get(ownerId);
	}
	
    /**
	 * 新增商业发票表
	 * @param scmCwInvoice
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新商业发票表数据", httpMethod = "POST", notes = "新增,更新商业发票表数据")
	public CommonResult<String> save(@ApiParam(name="scmCwInvoice",value="商业发票表业务对象", required = true)@RequestBody ScmCwInvoice scmCwInvoice) throws Exception{
		String msg = "添加商业发票表成功";
		JsonNode user = ucFeignService.getUser(current(), "");
		String fullname = user.get("fullname").asText();
		String ids = user.get("id").asText();
		ScmCwInvoice invoice = scmCwInvoiceManager.get(scmCwInvoice.getId());
		if(invoice== null){
			scmCwInvoiceManager.create(scmCwInvoice);
		}else{
			invoice.setSetUpPersion(fullname);
			invoice.setSetUpDate(new Date());
			invoice.setUpdateInvoiceDate(scmCwInvoice.getUpdateInvoiceDate());
			scmCwInvoiceManager.update(scmCwInvoice);
			 msg = "更新商业发票表成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除商业发票表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除商业发票表记录", httpMethod = "DELETE", notes = "删除商业发票表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmCwInvoiceManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除商业发票表记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除商业发票表记录", httpMethod = "DELETE", notes = "批量删除商业发票表记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmCwInvoiceManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
	
	@PostMapping("/findAll")
	@ApiOperation(value="回款勾兑申请发票数据列表", httpMethod = "POST", notes = "回款勾兑申请发票数据列表")
	public PageList<ScmCwInvoice> findAll(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmCwInvoiceManager.findAll(queryFilter);
	}
}
