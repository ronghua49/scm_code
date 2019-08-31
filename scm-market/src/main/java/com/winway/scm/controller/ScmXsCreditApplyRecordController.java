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
import com.winway.scm.persistence.manager.ScmXsCreditApplyRecordManager;
import com.winway.scm.model.ScmXsCreditApplyRecord;
import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;

/**
 * 
 * <pre> 
 * 描述：Scm_Xs_CreditApplyRecord 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-07-16 11:39:59
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmXsCreditApplyRecord/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmXsCreditApplyRecordController")
public class ScmXsCreditApplyRecordController extends BaseController{
	@Resource
	ScmXsCreditApplyRecordManager scmXsCreditApplyRecordManager;
	
	/**
	 * Scm_Xs_CreditApplyRecord列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="Scm_Xs_CreditApplyRecord数据列表", httpMethod = "POST", notes = "获取Scm_Xs_CreditApplyRecord列表")
	public PageList<ScmXsCreditApplyRecord> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmXsCreditApplyRecordManager.query(queryFilter);
	}
	
	/**
	 * Scm_Xs_CreditApplyRecord明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="Scm_Xs_CreditApplyRecord数据详情",httpMethod = "GET",notes = "Scm_Xs_CreditApplyRecord数据详情")
	public ScmXsCreditApplyRecord get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmXsCreditApplyRecordManager.get(id);
	}
	
	/**
	 * 根据商业编号获取剩余资信额度
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/getResidueCreditByCommerceCode/{commerceFirstId}/{ownerId}")
	@ApiOperation(value="Scm_Xs_CreditApplyRecord数据详情",httpMethod = "GET",notes = "Scm_Xs_CreditApplyRecord数据详情")
	public CommonResult<String> getResidueCreditByCommerceCode(
			@ApiParam(name="commerceFirstId",value="业务对象主键", required = true)@PathVariable String commerceFirstId,
			@ApiParam(name="ownerId",value="业务对象主键", required = true)@PathVariable String ownerId) throws Exception{
		String residueCredit = scmXsCreditApplyRecordManager.getResidueCreditByCommerceCode(commerceFirstId,ownerId);
		return new CommonResult<String>(true,"获取成功",residueCredit);
	}
	
	/**
	 * 新增Scm_Xs_CreditApplyRecord
	 * @param scmXsCreditApplyRecord
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新Scm_Xs_CreditApplyRecord数据", httpMethod = "POST", notes = "新增,更新Scm_Xs_CreditApplyRecord数据")
	public CommonResult<String> save(@ApiParam(name="scmXsCreditApplyRecord",value="Scm_Xs_CreditApplyRecord业务对象", required = true)@RequestBody ScmXsCreditApplyRecord scmXsCreditApplyRecord) throws Exception{
		String msg = "添加Scm_Xs_CreditApplyRecord成功";
		if(StringUtil.isEmpty(scmXsCreditApplyRecord.getId())){
			scmXsCreditApplyRecordManager.create(scmXsCreditApplyRecord);
		}else{
			scmXsCreditApplyRecordManager.update(scmXsCreditApplyRecord);
			 msg = "更新Scm_Xs_CreditApplyRecord成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 新增Scm_Xs_CreditApplyRecord
	 * @param scmXsCreditApplyRecord
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="saveByContractAndReturnMoney")
	@ApiOperation(value = "新增,更新Scm_Xs_CreditApplyRecord数据", httpMethod = "POST", notes = "新增,更新Scm_Xs_CreditApplyRecord数据")
	public CommonResult<String> saveByContractAndReturnMoney(
			@ApiParam(name="dataId",value="数据ID", required = true)@RequestParam String dataId,
			@ApiParam(name="price",value="价格", required = true)@RequestParam double price,
			@ApiParam(name="commerceId",value="商业id", required = true)@RequestParam String commerceId,
			@ApiParam(name="ownerId",value="货主id", required = true)@RequestParam String ownerId,
			@ApiParam(name="recordType",value="记录类型(0:大合同分配,1:日常合同,2:集中发货,3:商业回款)", required = true)@RequestParam String recordType) throws Exception{
		scmXsCreditApplyRecordManager.saveByContractAndReturnMoney(dataId,price,commerceId,ownerId,recordType);
		return new CommonResult<String>("新增成功");
	}
	
	/**
	 * 删除Scm_Xs_CreditApplyRecord记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除Scm_Xs_CreditApplyRecord记录", httpMethod = "DELETE", notes = "删除Scm_Xs_CreditApplyRecord记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmXsCreditApplyRecordManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除Scm_Xs_CreditApplyRecord记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除Scm_Xs_CreditApplyRecord记录", httpMethod = "DELETE", notes = "批量删除Scm_Xs_CreditApplyRecord记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmXsCreditApplyRecordManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
}
