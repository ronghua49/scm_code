package com.winway.scm.controller;

import java.time.LocalDateTime;
import java.util.Date;

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
import com.winway.scm.model.ScmZdMedicineClassify;
import com.winway.scm.persistence.manager.ScmZdMedicineClassifyManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * <pre> 
 * 描述：药品分类 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-29 10:31:25
 * 版权：美达项目组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmZdMedicineClassify/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmZdMedicineClassifyController 药品分类")
public class ScmZdMedicineClassifyController extends BaseController{
	@Resource
	ScmZdMedicineClassifyManager scmZdMedicineClassifyManager;
	@Resource
	UCFeignService ucFeignService; 
	
	/**
	 * 药品分类列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="药品分类数据列表", httpMethod = "POST", notes = "获取药品分类列表")
	public PageList<ScmZdMedicineClassify> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmZdMedicineClassifyManager.query(queryFilter);
	}
	
	/**
	 * 药品分类明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="药品分类数据详情",httpMethod = "GET",notes = "药品分类数据详情")
	public ScmZdMedicineClassify get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmZdMedicineClassifyManager.get(id);
	}
	
    /**
	 * 新增药品分类
	 * @param scmZdMedicineClassify
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新药品分类数据", httpMethod = "POST", notes = "新增,更新药品分类数据")
	public CommonResult<String> save(@ApiParam(name="scmZdMedicineClassify",value="药品分类业务对象", required = true)@RequestBody ScmZdMedicineClassify scmZdMedicineClassify) throws Exception{
		String msg = "添加药品分类成功";
		scmZdMedicineClassifyManager.getByNameAndCodeOrId(scmZdMedicineClassify);
		JsonNode user = ucFeignService.getUser(current(), "");
		String fullname = user.get("fullname").asText();
		scmZdMedicineClassify.setCreatePersion(fullname);		
		if(StringUtil.isEmpty(scmZdMedicineClassify.getId())){
			scmZdMedicineClassifyManager.create(scmZdMedicineClassify);
		}else{
			scmZdMedicineClassifyManager.update(scmZdMedicineClassify);
			 msg = "更新药品分类成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除药品分类记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除药品分类记录", httpMethod = "DELETE", notes = "删除药品分类记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmZdMedicineClassifyManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除药品分类记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除药品分类记录", httpMethod = "DELETE", notes = "批量删除药品分类记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmZdMedicineClassifyManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
}
