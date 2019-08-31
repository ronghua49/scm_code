package com.winway.scm.controller;
import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.feign.UCFeignService;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.winway.scm.util.ConversioneUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import com.winway.scm.persistence.manager.ScmLogModifyLogManager;
import com.winway.scm.model.ScmLogModifyLog;
import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import java.util.List;

/**
 * 
 * <pre> 
 * 描述：变动日志记录 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-02-23 11:33:08
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmLogModifyLog/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmLogModifyLogController 变动日志记录")
public class ScmLogModifyLogController extends BaseController{

	@Resource
	ScmLogModifyLogManager scmLogModifyLogManager;

	@Resource
	UCFeignService ucFeignService;
	/**
	 * scm_log_modifylog列表(分页条件查询)数据
	 * @param request
	 * @return  查询条件 类型 修改数据ID
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */

	@PostMapping("/list")
	@ApiOperation(value="变动日志记录数据列表", httpMethod = "POST", notes = "获取scm_log_modifylog列表")
	public PageList<ScmLogModifyLog> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmLogModifyLogManager.query(queryFilter);
	}

	/**
	 * scm_log_modifylog明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */

	@GetMapping(value="/get/{id}")
	@ApiOperation(value="变动日志记录数据详情",httpMethod = "GET",notes = "scm_log_modifylog数据详情")
	public ScmLogModifyLog get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmLogModifyLogManager.get(id);
	}

    /**
	 * 新增scm_log_modifylog
	 * @param scmLogModifyLog
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新变动日志记录数据", httpMethod = "POST", notes = "新增,更新scm_log_modifylog数据")
	public void save(@ApiParam(name="scmLogModifyLog",value="scm_log_modifylog业务对象", required = true)@RequestParam("updateMassge") String updateMassge) throws Exception{
		JsonNode user = ucFeignService.getUser(current(), "");
		String userName = user.get("fullname").asText();
		List<ScmLogModifyLog> scmLogModifyLogs=ConversioneUtil.getObject(updateMassge,userName);
		for (ScmLogModifyLog scmLogModifyLog : scmLogModifyLogs) {
			scmLogModifyLogManager.create(scmLogModifyLog);
		}
	}

	/**
	 * 删除scm_log_modifylog记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除变动日志记录记录", httpMethod = "DELETE", notes = "删除scm_log_modifylog记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmLogModifyLogManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除scm_log_modifylog记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除变动日志记录记录", httpMethod = "DELETE", notes = "批量删除scm_log_modifylog记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmLogModifyLogManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}

}
