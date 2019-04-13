package com.winway.scm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import com.winway.scm.persistence.manager.ScmXsCreditApplyManager;
import com.winway.scm.model.ScmXsAgreementList;
import com.winway.scm.model.ScmXsBigContract;
import com.winway.scm.model.ScmXsCreditApply;
import com.winway.scm.model.ScmXsDistributorClause;
import com.hotent.base.annotation.Workflow;
import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.model.WinwayResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;

/**
 * 
 * <pre> 
 * 描述：商业资信申请表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:43
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmXsCreditApply/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmXsCreditApplyController 商业资信申请表")
public class ScmXsCreditApplyController extends BaseController{

	@Resource
	ScmXsCreditApplyManager scmXsCreditApplyManager;
	
	/**
	 * 商业资信申请表列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="商业资信申请表数据列表", httpMethod = "POST", notes = "获取商业资信申请表列表")
	public PageList<ScmXsCreditApply> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmXsCreditApplyManager.query(queryFilter);
	}
	
	/**
	 * 商业资信申请表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="商业资信申请表数据详情",httpMethod = "GET",notes = "商业资信申请表数据详情")
	public ScmXsCreditApply get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmXsCreditApplyManager.get(id);
	}
	
    /**
	 * 新增商业资信申请表
	 * @param scmXsCreditApply
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新商业资信申请表数据", httpMethod = "POST", notes = "新增,更新商业资信申请表数据")
	public CommonResult<String> save(@ApiParam(name="scmXsCreditApply",value="商业资信申请表业务对象", required = true)@RequestBody ScmXsCreditApply scmXsCreditApply) throws Exception{
		String msg = "添加商业资信申请表成功";
		if(StringUtil.isEmpty(scmXsCreditApply.getId())){
			scmXsCreditApplyManager.create(scmXsCreditApply);
		}else{
			scmXsCreditApplyManager.update(scmXsCreditApply);
			 msg = "更新商业资信申请表成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除商业资信申请表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除商业资信申请表记录", httpMethod = "DELETE", notes = "删除商业资信申请表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmXsCreditApplyManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除商业资信申请表记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除商业资信申请表记录", httpMethod = "DELETE", notes = "批量删除商业资信申请表记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmXsCreditApplyManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
	
	/**
	 * 根据货主ID获取商业协议合作名单年度下拉框列表
	 * @return
	 */
	@GetMapping(value ="/getYears/{ownerId}")
	@ApiOperation(value="根据货主ID获取资信申请年度下拉框列表", httpMethod="GET" ,notes="")
	public CommonResult<List<ScmXsCreditApply>> getYears(@ApiParam(name="ownerId",value="货主ID", required = true)@PathVariable String ownerId) {		   
		List<ScmXsCreditApply> scmXsCreditApply = scmXsCreditApplyManager.getYears(ownerId);
		return new CommonResult<List<ScmXsCreditApply>>(true, "year", scmXsCreditApply);
	}
	
	
	/**
	 * 分销商协议合作条款表列表(需要连接经销商主数据表,根据选择年份计算出当前商业是否已经申请,给出状态,连接本表,查询出上次申请额度,并查询出当前年份去年的月均发货额与前年的月均发货额)
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/creditApplicationTypeList")
	@ApiOperation(value="获取出选择年份带申请状态的资信申请数据", httpMethod = "POST", notes = "需要连接经销商主数据表,根据选择年份计算出当前商业是否已经申请,给出状态,连接本表,查询出上次申请额度,并查询出当前年份去年的月均发货额与前年的月均发货额")
	public PageList<ScmXsCreditApply> firstList(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmXsCreditApplyManager.firstList(queryFilter);
	}
	
    /**
	 * 资信申请导入
	 * @param scmXsCreditApply
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="toLead")
	@ApiOperation(value = "资信申请导入", httpMethod = "POST", notes = "从request中获取到上次文件,并获取上传的数据后验证并保存")
	public CommonResult<String> toLead(@ApiParam(name="scmXsCreditApply",value="商业资信申请表业务对象", required = true)@RequestBody HttpServletRequest request) throws Exception{
		return null;
	}
	
	/**
	 * 根据商业ID获取统计数据
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/getStatisticalDate/{commerceId}/{ownerId}")
	@ApiOperation(value="根据商业ID获取统计数据",httpMethod = "GET",notes = "根据商业ID获取去年,前年的月均发货额度,上次资信额度与系统预测资信额度")
	public ScmXsCreditApply getStatisticalDate(@ApiParam(name="commerceId",value="商业ID", required = true)@PathVariable String commerceId,
			@ApiParam(name="ownerId",value="货主ID", required = true)@PathVariable String ownerId) throws Exception{
		return scmXsCreditApplyManager.getStatisticalDate(commerceId,ownerId);
	}



}
