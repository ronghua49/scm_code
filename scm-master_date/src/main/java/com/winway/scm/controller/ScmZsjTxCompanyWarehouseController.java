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
import com.winway.scm.persistence.manager.ScmZsjTxCompanyWarehouseManager;
import com.winway.scm.model.ScmZsjTxCompanyWarehouse;
import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;

/**
 * 
 * <pre> 
 * 描述：公司收货仓库 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-29 11:18:31
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmZsjTxCompanyWarehouse/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmZsjTxCompanyWarehouseController 公司收货仓库")
public class ScmZsjTxCompanyWarehouseController extends BaseController{
	@Resource
	ScmZsjTxCompanyWarehouseManager scmZsjTxCompanyWarehouseManager;
	
	/**
	 * 公司收回仓库列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="公司收回仓库数据列表", httpMethod = "POST", notes = "获取公司收回仓库列表")
	public PageList<ScmZsjTxCompanyWarehouse> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmZsjTxCompanyWarehouseManager.query(queryFilter);
	}
	
	/**
	 * 公司收回仓库明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="公司收回仓库数据详情",httpMethod = "GET",notes = "公司收回仓库数据详情")
	public ScmZsjTxCompanyWarehouse get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmZsjTxCompanyWarehouseManager.get(id);
	}
	
    /**
	 * 新增公司收回仓库
	 * @param scmZsjTxCompanyWarehouse
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新公司收回仓库数据", httpMethod = "POST", notes = "新增,更新公司收回仓库数据")
	public CommonResult<String> save(@ApiParam(name="scmZsjTxCompanyWarehouse",value="公司收回仓库业务对象", required = true)@RequestBody ScmZsjTxCompanyWarehouse scmZsjTxCompanyWarehouse) throws Exception{
		String msg = "添加公司收回仓库成功";
		if(StringUtil.isEmpty(scmZsjTxCompanyWarehouse.getId())){
			scmZsjTxCompanyWarehouseManager.create(scmZsjTxCompanyWarehouse);
		}else{
			scmZsjTxCompanyWarehouseManager.update(scmZsjTxCompanyWarehouse);
			 msg = "更新公司收回仓库成功";
		}
		return new CommonResult<String>(true,msg,scmZsjTxCompanyWarehouse.getId());
	}
	
	/**
	 * 删除公司收回仓库记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除公司收回仓库记录", httpMethod = "DELETE", notes = "删除公司收回仓库记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmZsjTxCompanyWarehouseManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除公司收回仓库记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除公司收回仓库记录", httpMethod = "DELETE", notes = "批量删除公司收回仓库记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmZsjTxCompanyWarehouseManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
	
	/**
	 * 公司收回仓库明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/setDefault/{id}")
	@ApiOperation(value="设为默认地址",httpMethod = "GET",notes = "设为默认地址")
	public CommonResult<String> setDefault(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		scmZsjTxCompanyWarehouseManager.setDefault(id);
		return new CommonResult<String>("设置成功");
	}
	
}
