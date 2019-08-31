package com.winway.scm.controller;

import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;
import com.winway.scm.model.ScmFhShipmentsTask;
import com.winway.scm.persistence.manager.ScmFhShipmentsTaskManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 
 * <pre> 
 * 描述：发货任务表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-02-15 17:31:50
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmFhShipmentsTask/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmFhShipmentsTaskController 发货任务")
public class ScmFhShipmentsTaskController extends BaseController{

	@Resource
	ScmFhShipmentsTaskManager scmFhShipmentsTaskManager;
	
	/**
	 * 发货任务表列表(分页条件查询)数据
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="发货任务表数据列表", httpMethod = "POST", notes = "获取发货任务表列表")
	public PageList<ScmFhShipmentsTask> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmFhShipmentsTaskManager.query(queryFilter);
	}
	
	/**
	 * 发货指令申请首页
	 * @param
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/firstList")
	@ApiOperation(value="发货指令申请首页", httpMethod = "POST", notes = "发货指令申请首页")
	public PageList<ScmFhShipmentsTask> firstList(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmFhShipmentsTaskManager.firstList(queryFilter);
	}
	
	/**
	 * 发货任务表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="发货任务表数据详情",httpMethod = "GET",notes = "发货任务表数据详情")
	public ScmFhShipmentsTask get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmFhShipmentsTaskManager.get(id);
	}
    /**
	 * 新增发货任务表
	 * @param scmFhShipmentsTask
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新发货任务表数据", httpMethod = "POST", notes = "新增,更新发货任务表数据")
	public CommonResult<String> save(@ApiParam(name="scmFhShipmentsTask",value="发货任务表业务对象", required = true)@RequestBody ScmFhShipmentsTask scmFhShipmentsTask) throws Exception{
		String msg = "添加发货任务表成功";
		if(StringUtil.isEmpty(scmFhShipmentsTask.getId())){
			scmFhShipmentsTaskManager.create(scmFhShipmentsTask);
		}else{
			scmFhShipmentsTaskManager.update(scmFhShipmentsTask);
			 msg = "更新发货任务表成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除发货任务表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除发货任务表记录", httpMethod = "DELETE", notes = "删除发货任务表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmFhShipmentsTaskManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}


	/**
	 * 批量删除发货任务
	 *
	 * @param ids
	 * @return
	 * @throws Exception
	 * @throws
	 */
	@DeleteMapping(value = "/removes")
	@ApiOperation(value = "批量删除发货明细表记录", httpMethod = "DELETE", notes = "批量删除发货明细表记录")
	public CommonResult<String> removes(@ApiParam(name = "ids", value = "业务主键数组,多个业务主键之间用逗号分隔", required = true) @RequestParam String... ids) throws Exception {
		scmFhShipmentsTaskManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
	 /**
     * 创建发货任务
     *
     * @return
     * @throws Exception ModelAndView
     */
    @PostMapping(value = "/theDeliveryTask")
    @ApiOperation(value = "创建发货任务", httpMethod = "POST", notes = "创建发货任务")
    public CommonResult<String> theDeliveryTask(@ApiParam(name = "jsonStr", value = "数据解析字符串", required = true) @RequestParam(value = "jsonStr") String jsonStr) throws Exception {
    	String msg = "添加发货任务表成功";
    	scmFhShipmentsTaskManager.theDeliveryTask(jsonStr);
    	return new CommonResult<String>(msg);
    }


	/**
	 * 获取发货任务所有的商业名称 和商业id
	 * @param
	 * @return
	 * @throws Exception
	 * ModelAndView
	 */
	@PostMapping(value="/commerceList")
	@ApiOperation(value="发货任务商业名称列表",httpMethod = "POST",notes = "获取发货任务所有的商业名称列表")
	public List<Map<String,String>> commerceList(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmFhShipmentsTaskManager.commerceList(queryFilter);
	}

	/**
	 * 获取发货任务所有的产品名称
	 * @param
	 * @return
	 * @throws Exception
	 * ModelAndView
	 */
	@GetMapping(value="/proList/{ownerId}")
	@ApiOperation(value="发货任务商品名列表",httpMethod = "GET",notes = "发货任务商品名列表")
	public List<String> proList(@ApiParam(name = "ownerId", value = "货主id", required = true) @PathVariable(value = "ownerId") String ownerId) throws Exception{
		return scmFhShipmentsTaskManager.proList(ownerId);
	}
	/**
	 * 获取发货任务所有的合同编号
	 * @param
	 * @return
	 * @throws Exception
	 * ModelAndView
	 */
	@GetMapping(value="/codeList/{ownerId}")
	@ApiOperation(value="发货任务合同编号列表",httpMethod = "GET",notes = "获取发货任务表合同编号")
	public List<String> codeList(@ApiParam(name = "ownerId", value = "货主id", required = true) @PathVariable(value = "ownerId") String ownerId) throws Exception{
		return scmFhShipmentsTaskManager.codeList(ownerId);
	}

	/**
	 * 全部导出
	 * @param
	 * @return
	 * @throws Exception
	 * ModelAndView
	 */
	@PostMapping(value="/exportAll")
//	@ApiOperation(value="全部导出发货任务数据",httpMethod = "POST",notes = "全部导出发货任务数据")
	public void exportAll(@RequestBody QueryFilter queryFilter, HttpServletResponse response) throws Exception{
		 scmFhShipmentsTaskManager.export(queryFilter,false,response);
	}


	/**
	 * 当页导出
	 * @param
	 * @return
	 * @throws Exception
	 * ModelAndView
	 */
	@PostMapping(value="/exportPage")
//	@ApiOperation(value="发货任务数据当页导出",httpMethod = "POST",notes = "发货任务数据当页导出")
	public void  exportPage(@RequestBody QueryFilter queryFilter,HttpServletResponse response) throws Exception{
		 scmFhShipmentsTaskManager.export(queryFilter,true,response);
	}

}
