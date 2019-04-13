package com.winway.scm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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
import com.winway.scm.persistence.manager.ScmCwBlendManager;
import com.winway.scm.model.ScmCwBlend;
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
 * 描述：勾兑记录表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-23 18:19:25
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmCwBlend/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmCwBlendController  勾兑记录表 控制器类")
public class ScmCwBlendController extends BaseController{
	@Resource
	ScmCwBlendManager scmCwBlendManager;
	@Resource
	UCFeignService ucFeignService;
	/**
	 * 勾兑记录表列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="勾兑记录表数据列表", httpMethod = "POST", notes = "获取勾兑记录表列表")
	public PageList<ScmCwBlend> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmCwBlendManager.query(queryFilter);
	}
	
	/**
	 * 勾兑记录表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="勾兑记录表数据详情",httpMethod = "GET",notes = "勾兑记录表数据详情")
	public ScmCwBlend get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmCwBlendManager.get(id);
	}
	
    /**
	 * 新增勾兑记录表
	 * @param scmCwBlend
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新勾兑记录表数据", httpMethod = "POST", notes = "新增,更新勾兑记录表数据")
	public CommonResult<String> save(@ApiParam(name="scmCwBlend",value="勾兑记录表业务对象", required = true)@RequestBody ScmCwBlend scmCwBlend) throws Exception{
		String msg = "添加勾兑记录表成功";
		if(StringUtil.isEmpty(scmCwBlend.getId())){
			scmCwBlendManager.create(scmCwBlend);
		}else{
			scmCwBlendManager.update(scmCwBlend);
			 msg = "更新勾兑记录表成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除勾兑记录表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除勾兑记录表记录", httpMethod = "DELETE", notes = "删除勾兑记录表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmCwBlendManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除勾兑记录表记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除勾兑记录表记录", httpMethod = "DELETE", notes = "批量删除勾兑记录表记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmCwBlendManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
	@PostMapping(value="/cancel")
	@ApiOperation(value = "勾兑取消", httpMethod = "POST", notes = "勾兑取消")
	public CommonResult<String> cancel(@ApiParam(name="id",value="记录表主键", required = true)@RequestParam String id) throws Exception{
		String msg = "添加勾兑记录表成功";
		ScmCwBlend blend = scmCwBlendManager.get(id);
		String result=scmCwBlendManager.cancel(blend);
		return new CommonResult<String>(result);
	}
	@PostMapping(value="/charge")
	@ApiOperation(value = "勾兑申请确认核销", httpMethod = "POST", notes = "勾兑申请确认核销")
	public CommonResult<String> charge(@ApiParam(name="scmCwBlend",value="确认核销", required = true)@RequestBody List<ScmCwBlend> scmCwBlend) throws Exception{
		String result = "核销未完成！";
		JsonNode user = ucFeignService.getUser(current(), "");
		String fullname = user.get("fullname").asText();
		result=scmCwBlendManager.charge(scmCwBlend,fullname);
		return new CommonResult<String>(result);
	}
	@PostMapping(value="/giveCharge")
	@ApiOperation(value = "勾兑批准确认核销", httpMethod = "POST", notes = "勾兑批准确认核销")
	public CommonResult<String> giveCharge(@ApiParam(name="ids",value="发票表主键", required = true)@RequestParam String...ids) throws Exception{
		String result = "未批准核销！";
		JsonNode user = ucFeignService.getUser(current(), "");
		String fullname = user.get("fullname").asText();
		result=scmCwBlendManager.giveCharge(ids,fullname);
		return new CommonResult<String>(result);
	}
}
