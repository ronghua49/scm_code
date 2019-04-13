package com.winway.scm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
import com.winway.scm.persistence.manager.ScmXsDealerClauseManager;
import com.winway.scm.model.ScmXsAgreementList;
import com.winway.scm.model.ScmXsDealerClause;
import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.model.WinwayResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;

/**
 * 
 * <pre> 
 * 描述：经销商协议合作条款表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:44
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmXsDealerClause/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmXsDealerClauseController 经销商协议合作条款表")
public class ScmXsDealerClauseController extends BaseController{
	@Resource
	ScmXsDealerClauseManager scmXsDealerClauseManager;
	
	/**
	 * 经销商协议合作条款表列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="经销商协议合作条款表数据列表", httpMethod = "POST", notes = "获取经销商协议合作条款表列表")
	public PageList<ScmXsDealerClause> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmXsDealerClauseManager.query(queryFilter);
	}
	/**
	 * 经销商协议合作条款表列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/firstList")
	@ApiOperation(value="经销商协议合作条款表数据列表(带审批状态及主表等信息)", httpMethod = "POST", notes = "与主表进行关联展示,需要携带主表信息,审批中文状态")
	public PageList<ScmXsDealerClause> firstList(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmXsDealerClauseManager.firstQuery(queryFilter);
	}
	
	/**
	 * 经销商协议合作条款表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="经销商协议合作条款表数据详情",httpMethod = "GET",notes = "经销商协议合作条款表数据详情")
	public ScmXsDealerClause get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmXsDealerClauseManager.get(id);
	}
	
    /**
	 * 新增经销商协议合作条款表
	 * @param scmXsDealerClause
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新经销商协议合作条款表数据", httpMethod = "POST", notes = "新增,更新经销商协议合作条款表数据")
	public CommonResult<String> save(@ApiParam(name="scmXsDealerClause",value="经销商协议合作条款表业务对象", required = true)@RequestBody ScmXsDealerClause scmXsDealerClause) throws Exception{
		String msg = "添加经销商协议合作条款表成功";
		if(StringUtil.isEmpty(scmXsDealerClause.getId())){
			scmXsDealerClauseManager.create(scmXsDealerClause);
		}else{
			scmXsDealerClauseManager.update(scmXsDealerClause);
			 msg = "更新经销商协议合作条款表成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除经销商协议合作条款表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除经销商协议合作条款表记录", httpMethod = "DELETE", notes = "删除经销商协议合作条款表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmXsDealerClauseManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除经销商协议合作条款表记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除经销商协议合作条款表记录", httpMethod = "DELETE", notes = "批量删除经销商协议合作条款表记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmXsDealerClauseManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
	
	/**
	 * 根据货主ID获取经销商协议年度下拉框列表
	 * @return
	 */
	@GetMapping(value = "/getYears/{ownerId}")
	@ApiOperation(value="根据货主ID分销商协议合作条款年度下拉框列表",httpMethod="GET",notes="")
	public CommonResult<List<ScmXsDealerClause>> getYears(@ApiParam(name="ownerId",value="货主ID", required = true)@PathVariable String ownerId) {
		List<ScmXsDealerClause> scmXsDealerClause = scmXsDealerClauseManager.getYears(ownerId);
		return new CommonResult<List<ScmXsDealerClause>>(true, "year", scmXsDealerClause);
	}
	
	/**
	 * 经销商信息与合作名单关联查询
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@GetMapping("/commerceAndAgreementList/{ownerId}/{provinceId}")
	@ApiOperation(value="经销商信息与合作名单关联查询", httpMethod = "GET", notes = "经销商信息与合作名单关联查询,后台"
			+ "人员经销数据表关联,并返回相应字段,根据省区ID与货主ID筛选")
	public PageList<ScmXsDealerClause> commerceList(
			@ApiParam(name="id",value="货主ID", required = true)@PathVariable String ownerId,
			@ApiParam(name="id",value="省区ID", required = true)@PathVariable String provinceId) throws Exception{
		return scmXsDealerClauseManager.commerceList(ownerId,provinceId);
	}
}
