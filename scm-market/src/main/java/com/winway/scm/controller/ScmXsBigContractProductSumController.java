package com.winway.scm.controller;

import io.swagger.annotations.Api;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import com.winway.scm.persistence.manager.ScmXsBigContractProductSumManager;
import com.winway.scm.model.ScmXsBigContractProductSum;
import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;

/**
 * 
 * <pre> 
 * 描述：scm_xs_bigcontractproductsum 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-14 18:06:05
 * 版权：美达项目组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmXsBigContractProductSum/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmXsBigContractProductSumController")
public class ScmXsBigContractProductSumController extends BaseController{
	@Resource
	ScmXsBigContractProductSumManager scmXsBigContractProductSumManager;
	
	/**
	 * scm_xs_bigcontractproductsum列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/listConProductSumBySummaryID")
	@ApiOperation(value="scm_xs_bigcontractproductsum数据列表", httpMethod = "POST", notes = "获取scm_xs_bigcontractproductsum列表")
	public PageList<ScmXsBigContractProductSum> listConProductSumBySummaryID(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmXsBigContractProductSumManager.listConProductSumBySummaryID(queryFilter);
	}
	
	/**
	 * scm_xs_bigcontractproductsum列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/listConProductSumByUpdate")
	@ApiOperation(value="scm_xs_bigcontractproductsum数据列表", httpMethod = "POST", notes = "获取scm_xs_bigcontractproductsum列表")
	public List<ScmXsBigContractProductSum> listConProductSumByUpdate(@ApiParam(name="bigContractAllotId",value="大合同分配ID")@RequestParam String bigContractAllotId,
			@ApiParam(name="agreementSummaryId",value="协议ID")@RequestParam String agreementSummaryId) throws Exception{
		return scmXsBigContractProductSumManager.listConProductSumByUpdate(bigContractAllotId,agreementSummaryId);
		
	}
	
	/**
	 * scm_xs_bigcontractproductsum列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="scm_xs_bigcontractproductsum数据列表", httpMethod = "POST", notes = "获取scm_xs_bigcontractproductsum列表")
	public PageList<ScmXsBigContractProductSum> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmXsBigContractProductSumManager.query(queryFilter);
	}
	
	/**
	 * scm_xs_bigcontractproductsum明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="scm_xs_bigcontractproductsum数据详情",httpMethod = "GET",notes = "scm_xs_bigcontractproductsum数据详情")
	public ScmXsBigContractProductSum get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmXsBigContractProductSumManager.get(id);
	}
	
    /**
	 * 新增scm_xs_bigcontractproductsum
	 * @param scmXsBigContractProductSum
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新scm_xs_bigcontractproductsum数据", httpMethod = "POST", notes = "新增,更新scm_xs_bigcontractproductsum数据")
	public CommonResult<String> save(@ApiParam(name="scmXsBigContractProductSum",value="scm_xs_bigcontractproductsum业务对象", required = true)@RequestBody ScmXsBigContractProductSum scmXsBigContractProductSum) throws Exception{
		String msg = "添加scm_xs_bigcontractproductsum成功";
		if(StringUtil.isEmpty(scmXsBigContractProductSum.getId())){
			scmXsBigContractProductSumManager.create(scmXsBigContractProductSum);
		}else{
			scmXsBigContractProductSumManager.update(scmXsBigContractProductSum);
			 msg = "更新scm_xs_bigcontractproductsum成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除scm_xs_bigcontractproductsum记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除scm_xs_bigcontractproductsum记录", httpMethod = "DELETE", notes = "删除scm_xs_bigcontractproductsum记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmXsBigContractProductSumManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除scm_xs_bigcontractproductsum记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除scm_xs_bigcontractproductsum记录", httpMethod = "DELETE", notes = "批量删除scm_xs_bigcontractproductsum记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmXsBigContractProductSumManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
}
