package com.winway.scm.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;
import com.winway.purchase.feign.ScmMasterDateFeignService;
import com.winway.purchase.util.ContrasUtil;
import com.winway.scm.model.ScmXsAgreementList;
import com.winway.scm.persistence.manager.ScmXsAgreementListManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * <pre> 
 * 描述：经销商协议名单申请表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 15:01:16
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmXsAgreementList/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmXsAgreementListController 经销商协议名单申请")
public class ScmXsAgreementListController extends BaseController{
	@Resource
	ScmXsAgreementListManager scmXsAgreementListManager;
	@Resource
	ScmMasterDateFeignService scmMasterDateFeignService;	
	/**
	 * 经销商协议名单申请表列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="经销商协议名单申请表数据列表", httpMethod = "POST", notes = "获取经销商协议名单申请表列表,带分页筛选")
	public PageList<ScmXsAgreementList> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmXsAgreementListManager.query(queryFilter);
	}
	
	/**
	 * 经销商协议名单申请表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="经销商协议名单申请表数据详情",httpMethod = "GET",notes = "经销商协议名单申请表数据详情")
	public ScmXsAgreementList get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmXsAgreementListManager.get(id);
	}
	
    /**
	 * 新增经销商协议名单申请表
	 * @param scmXsAgreementList
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新经销商协议名单申请表数据", httpMethod = "POST", notes = "新增,更新经销商协议名单申请表数据")
	public CommonResult<String> save(@ApiParam(name="scmXsAgreementList",value="经销商协议名单申请表业务对象", required = true)@RequestBody ScmXsAgreementList scmXsAgreementList) throws Exception{
		String msg = "添加经销商协议名单申请表成功";
		if(StringUtil.isEmpty(scmXsAgreementList.getId())){
			scmXsAgreementListManager.create(scmXsAgreementList);
		}else{
			scmXsAgreementListManager.update(scmXsAgreementList);
			 msg = "更新经销商协议名单申请表成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除经销商协议名单申请表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除经销商协议名单申请表记录", httpMethod = "DELETE", notes = "删除经销商协议名单申请表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmXsAgreementListManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除经销商协议名单申请表记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除经销商协议名单申请表记录", httpMethod = "DELETE", notes = "批量删除经销商协议名单申请表记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmXsAgreementListManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
	
	/**
	 * 根据货主ID获取商业协议合作名单年度下拉框列表
	 * @return
	 */
	@GetMapping(value = "/getYears/{ownerId}")
	@ApiOperation(value="根据货主ID获取商业协议合作名单年度下拉框列表",httpMethod="GET",notes="")
	public CommonResult<List<ScmXsAgreementList>> getYears(@ApiParam(name="ownerId",value="货主ID", required = true)@PathVariable String ownerId) {
		List<ScmXsAgreementList> scmXsAgreementList = scmXsAgreementListManager.getYears(ownerId);
		return new CommonResult<List<ScmXsAgreementList>>(true, "year", scmXsAgreementList);
	}
	
	
	
	/**
	 * 经销商协议名单申请表与主表联查列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/masterList")
	@ApiOperation(value="经销商协议名单申请表与主表联查列表(分页条件查询)数据", httpMethod = "POST", notes = "获取经销商协议名单申请表列表,要根据原型连表查询出字段,并需要展示出审批状态,审批状态中文,货主ID,审批ID,带分页筛选")
	public PageList<ScmXsAgreementList> masterList(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmXsAgreementListManager.masterList(queryFilter);
	}
	
	/**
	 * 经销商协议名单申请表与主表联查列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/addAgreementlist")
	@ApiOperation(value="新增时商业信息列表", httpMethod = "POST", notes = "查询出商务大区下的商业,携带商业级别,认可状态,以及销量,上年度签约状态,并支持queryFilter查询")
	public PageList<ScmXsAgreementList> addAgreementlist(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmXsAgreementListManager.addAgreementlist(queryFilter);
	}
	/**
	 * 经销商协议名单申请表与主表联查列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@GetMapping("/updateAgreementlist/{masterId}/{ownerid}/{businessDivisionId}")
	@ApiOperation(value="编辑时商业信息列表", httpMethod = "GET", notes = "查询出商务大区下的商业,携带商业级别,认可状态,以及销量,上年度签约状态,并支持queryFilter查询")
	public CommonResult<List<ScmXsAgreementList>> updateAgreementlist(
			@ApiParam(name="masterId",value="主表ID")@PathVariable String masterId,
			@ApiParam(name="ownerid",value="货主ID")@PathVariable String ownerid,
			@ApiParam(name="businessDivisionId",value="商务分区ID")@PathVariable String businessDivisionId) throws Exception{
		List<ScmXsAgreementList> updateAgreementlist = scmXsAgreementListManager.updateAgreementlist(masterId,ownerid,businessDivisionId);
		return new CommonResult<>(true,"获取成功",updateAgreementlist);
	}
	/**
	 * 经销商协议名单申请表与主表联查列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/commerceAndSales")
	@ApiOperation(value="经销商协议合作名单修改列表数据", httpMethod = "POST", notes = "查询出商务大区下的商业,携带商业级别,认可状态,以及销量,上年度签约状态,并支持queryFilter查询")
	public PageList<ScmXsAgreementList> commerceAndSales(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmXsAgreementListManager.commerceAndSales(queryFilter);
	}
	
	
	
	
	
	/**
	 * 根据货主ID获取商业协议合作名单年度下拉框列表
	 * @return
	 * @throws Exception 
	 */
	@GetMapping(value = "/testLog/{id}")
	@ApiOperation(value="log测试",httpMethod="GET",notes="")
	public CommonResult<String> testLog(@ApiParam(name="id",value="货主ID", required = true)@PathVariable String id) throws Exception {
		ScmXsAgreementList s1 = new  ScmXsAgreementList();
		ScmXsAgreementList s2 = new  ScmXsAgreementList();
		s1.setApprovalId("123");
		s2.setApprovalId("321");
		String test = ContrasUtil.Contrast(s1, s2, "123", "TEST");
		scmMasterDateFeignService.inserScmLogRecord(test);
		return new  CommonResult<String>("成功");
	}
	
	
	
}
