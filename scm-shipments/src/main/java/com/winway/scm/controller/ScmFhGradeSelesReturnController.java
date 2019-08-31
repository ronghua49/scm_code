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
import com.winway.scm.persistence.manager.ScmFhGradeSelesReturnManager;
import com.winway.scm.model.ScmFhGradeSelesReturn;
import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;

/**
 * 
 * <pre> 
 * 描述：当次退货 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-13 15:47:33
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmFhGradeSelesReturn/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmFhGradeSelesReturnController 当次退货明细")
public class ScmFhGradeSelesReturnController extends BaseController{
	@Resource
	ScmFhGradeSelesReturnManager scmFhGradeSelesReturnManager;
	
	/**
	 * 当次退货列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="当次退货数据列表", httpMethod = "POST", notes = "获取当次退货列表")
	public PageList<ScmFhGradeSelesReturn> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmFhGradeSelesReturnManager.query(queryFilter);
	}
	
	/**
	 * 当次退货明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="当次退货数据详情",httpMethod = "GET",notes = "当次退货数据详情")
	public ScmFhGradeSelesReturn get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmFhGradeSelesReturnManager.get(id);
	}
	
    /**
	 * 新增当次退货
	 * @param scmFhGradeSelesReturn
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新当次退货数据", httpMethod = "POST", notes = "新增,更新当次退货数据")
	public CommonResult<String> save(@ApiParam(name="scmFhGradeSelesReturn",value="当次退货业务对象", required = true)@RequestBody ScmFhGradeSelesReturn scmFhGradeSelesReturn) throws Exception{
		String msg = "添加当次退货成功";
		if(StringUtil.isEmpty(scmFhGradeSelesReturn.getId())){
			scmFhGradeSelesReturnManager.create(scmFhGradeSelesReturn);
		}else{
			scmFhGradeSelesReturnManager.update(scmFhGradeSelesReturn);
			 msg = "更新当次退货成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除当次退货记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除当次退货记录", httpMethod = "DELETE", notes = "删除当次退货记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmFhGradeSelesReturnManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除当次退货记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除当次退货记录", httpMethod = "DELETE", notes = "批量删除当次退货记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmFhGradeSelesReturnManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
}
