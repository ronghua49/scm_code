package com.winway.scm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.Date;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import com.winway.scm.persistence.manager.ScmCwSpecialTicketDiscountManager;
import com.winway.scm.model.ScmCwReturnMoney;
import com.winway.scm.model.ScmCwSpecialTicketDiscount;
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
 * 描述：特殊票折(导入) 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-18 15:28:17
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmCwSpecialTicketDiscount/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmCwSpecialTicketDiscountController 特殊票折")
public class ScmCwSpecialTicketDiscountController extends BaseController{
	@Resource
	ScmCwSpecialTicketDiscountManager scmCwSpecialTicketDiscountManager;
	@Resource
	UCFeignService ucFeignService;
	/**
	 * 特殊票折(导入)列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="特殊票折(导入)数据列表", httpMethod = "POST", notes = "获取特殊票折(导入)列表")
	public PageList<ScmCwSpecialTicketDiscount> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmCwSpecialTicketDiscountManager.query(queryFilter);
	}
	
	/**
	 * 确认数据
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/affirm/{id}")
	@ApiOperation(value="特殊票折(导入)数据详情",httpMethod = "GET",notes = "特殊票折(导入)数据确认")
	public ScmCwSpecialTicketDiscount affirm(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return null;
	}
	/**
	 * 特殊票折(导入)明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="特殊票折(导入)数据详情",httpMethod = "GET",notes = "特殊票折(导入)数据详情")
	public ScmCwSpecialTicketDiscount get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmCwSpecialTicketDiscountManager.get(id);
	}
	
    /**
	 * 新增特殊票折(导入)
	 * @param scmCwSpecialTicketDiscount
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新特殊票折(导入)数据", httpMethod = "POST", notes = "新增,更新特殊票折(导入)数据")
	public CommonResult<String> save(@ApiParam(name="scmCwSpecialTicketDiscount",value="特殊票折(导入)业务对象", required = true)@RequestBody ScmCwSpecialTicketDiscount scmCwSpecialTicketDiscount) throws Exception{
		String msg = "添加特殊票折(导入)成功";
		if(StringUtil.isEmpty(scmCwSpecialTicketDiscount.getId())){
			scmCwSpecialTicketDiscountManager.create(scmCwSpecialTicketDiscount);
		}else{
			scmCwSpecialTicketDiscountManager.update(scmCwSpecialTicketDiscount);
			 msg = "更新特殊票折(导入)成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除特殊票折(导入)记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除特殊票折(导入)记录", httpMethod = "DELETE", notes = "删除特殊票折(导入)记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmCwSpecialTicketDiscountManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除特殊票折(导入)记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除特殊票折(导入)记录", httpMethod = "DELETE", notes = "批量删除特殊票折(导入)记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmCwSpecialTicketDiscountManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
	
	/**
	 * 商业表导入表格数据
	 * @param file excel文件
	 * @param id 货主Id
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/leadExcel")
	@ApiOperation(value="导入表格数据", httpMethod = "POST", notes = "导入表格数据")
	public CommonResult<String> leadExcel(@ApiParam(name="file",value="导入excel文件") MultipartFile file,
			@ApiParam(name="id",value="货主Id") @RequestParam String id) throws Exception{
		JsonNode user = ucFeignService.getUser(current(), "");
		String fullname = user.get("fullname").asText();
		String ids = user.get("id").asText();
		return new CommonResult<String>("");
	}
	
}
