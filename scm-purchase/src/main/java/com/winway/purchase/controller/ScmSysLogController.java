package com.winway.purchase.controller;

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
import com.winway.purchase.persistence.manager.ScmSysLogManager;
import com.winway.purchase.model.ScmSysLog;
import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;

/**
 * 
 * <pre> 
 * 描述：scm_sys_log 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-07-24 17:36:05
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/purchase/scmSysLog/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmSysLogController")
public class ScmSysLogController extends BaseController{
	@Resource
	ScmSysLogManager scmSysLogManager;
	
	/**
	 * scm_sys_log列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	public PageList<ScmSysLog> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmSysLogManager.query(queryFilter);
	}
	
	/**
	 * scm_sys_log明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="scm_sys_log数据详情",httpMethod = "GET",notes = "scm_sys_log数据详情")
	public ScmSysLog get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmSysLogManager.get(id);
	}
	
    /**
	 * 新增scm_sys_log
	 * @param scmSysLog
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新scm_sys_log数据", httpMethod = "POST", notes = "新增,更新scm_sys_log数据")
	public CommonResult<String> save(@ApiParam(name="scmSysLog",value="scm_sys_log业务对象", required = true)@RequestBody ScmSysLog scmSysLog) throws Exception{
		String msg = "添加scm_sys_log成功";
		if(StringUtil.isEmpty(scmSysLog.getId())){
			scmSysLogManager.create(scmSysLog);
		}else{
			scmSysLogManager.update(scmSysLog);
			 msg = "更新scm_sys_log成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除scm_sys_log记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除scm_sys_log记录", httpMethod = "DELETE", notes = "删除scm_sys_log记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmSysLogManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除scm_sys_log记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除scm_sys_log记录", httpMethod = "DELETE", notes = "批量删除scm_sys_log记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmSysLogManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
}
