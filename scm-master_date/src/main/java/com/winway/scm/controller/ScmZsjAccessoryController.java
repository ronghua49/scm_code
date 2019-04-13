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
import com.winway.scm.persistence.manager.ScmZsjAccessoryManager;
import com.winway.scm.model.ScmZsjAccessory;
import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;

/**
 * 
 * <pre> 
 * 描述：附件表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmZsjAccessory/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmZsjAccessoryController 附件")
public class ScmZsjAccessoryController extends BaseController{
	@Resource
	ScmZsjAccessoryManager scmZsjAccessoryManager;
	
	/**
	 * 附件表列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="附件表数据列表", httpMethod = "POST", notes = "获取附件表列表")
	public PageList<ScmZsjAccessory> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmZsjAccessoryManager.query(queryFilter);
	}
	
	/**
	 * 附件表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="附件表数据详情",httpMethod = "GET",notes = "附件表数据详情")
	public ScmZsjAccessory get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmZsjAccessoryManager.get(id);
	}
	
    /**
	 * 新增附件表
	 * @param scmZsjAccessory
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新附件表数据", httpMethod = "POST", notes = "新增,更新附件表数据")
	public CommonResult<String> save(@ApiParam(name="scmZsjAccessory",value="附件表业务对象", required = true)@RequestBody ScmZsjAccessory scmZsjAccessory) throws Exception{
		String msg = "添加附件表成功";
		if(StringUtil.isEmpty(scmZsjAccessory.getId())){
			scmZsjAccessoryManager.create(scmZsjAccessory);
		}else{
			scmZsjAccessoryManager.update(scmZsjAccessory);
			 msg = "更新附件表成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除附件表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除附件表记录", httpMethod = "DELETE", notes = "删除附件表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmZsjAccessoryManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除附件表记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除附件表记录", httpMethod = "DELETE", notes = "批量删除附件表记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmZsjAccessoryManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
}
