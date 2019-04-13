package com.winway.scm.controller;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;
import com.winway.purchase.util.QuarterUtil;
import com.winway.scm.model.ScmZsjAchage;
import com.winway.scm.persistence.manager.ScmZsjAchageManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * <pre> 
 * 描述：件装量 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/ScmZsjAchage/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="ScmZsjAchageController 件装量")
public class ScmZsjAchageController {
	@Resource
	ScmZsjAchageManager scmZsjAchageManager;
	/**
	 * 件装量列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="件装量数据列表", httpMethod = "POST", notes = "件装量列表")
	public PageList<ScmZsjAchage> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmZsjAchageManager.query(queryFilter);
	}
	
	/**
	 * 件装量表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="件装量数据详情",httpMethod = "GET",notes = "件装量数据详情")
	public ScmZsjAchage get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmZsjAchageManager.get(id);
	}

	
    /**
	 * 新增件装量
	 * @param scmZsjCommerceAcceptStateAgther
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新商业认可状态总表数据", httpMethod = "POST", notes = "新增,更新商业认可状态总表数据")
	public CommonResult<ScmZsjAchage> save(@ApiParam(name="ScmZsjAchage",value="商业认可状态总表业务对象", required = true)@RequestBody ScmZsjAchage scmZsjAchage) throws Exception{
		String msg = "暂存成功";
		if(StringUtil.isEmpty(scmZsjAchage.getId())){
			scmZsjAchageManager.create(scmZsjAchage);
		}else{
			scmZsjAchageManager.update(scmZsjAchage);
		}
		return new CommonResult<ScmZsjAchage>(true,msg,scmZsjAchage);
	}
	
	/**
	 * 删除件装量
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除件装量", httpMethod = "DELETE", notes = "删除件装量")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmZsjAchageManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除件装量
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除件装量", httpMethod = "DELETE", notes = "批量删除件装量")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmZsjAchageManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
	
	

}
