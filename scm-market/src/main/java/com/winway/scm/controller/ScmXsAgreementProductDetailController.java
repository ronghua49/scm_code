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
import com.winway.scm.model.ScmXsAgreementProductDetail;
import com.winway.scm.persistence.manager.ScmXsAgreementProductDetailManager;
import com.winway.scm.vo.ScmXsBigContractProductByUpdateVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * <pre> 
 * 描述：协议品规明细 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:43
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmXsAgreementProductDetail/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmXsAgreementProductDetailController 协议品规明细")
public class ScmXsAgreementProductDetailController extends BaseController{
	@Resource
	ScmXsAgreementProductDetailManager scmXsAgreementProductDetailManager;
	
	/**
	 * 协议品规明细列表(分页条件查询)数据
	 * @param request
	 * 
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="协议品规明细数据列表", httpMethod = "POST", notes = "获取协议品规明细列表")
	public PageList<ScmXsAgreementProductDetail> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmXsAgreementProductDetailManager.query(queryFilter);
	}
	/**
	 * 协议品规明细列表(分页条件查询)数据
	 * @param request
	 * 
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@GetMapping("/getAgreementProduct/{businessDivisionId}/{provinceId}/{ownerId}/{masterId}")
	@ApiOperation(value="协议品规明细列表第三步", httpMethod = "GET", notes = "根据原型创建视图,并可支持query查询")
	public CommonResult<List<ScmXsAgreementProductDetail>> getAgreementProduct(
			@ApiParam(name="businessDivisionId",value="商务分区ID", required = true)@PathVariable String businessDivisionId,
			@ApiParam(name="provinceId",value="省区ID", required = true)@PathVariable String provinceId,
			@ApiParam(name="ownerId",value="货主ID", required = true)@PathVariable String ownerId,
			@ApiParam(name="masterId",value="主表ID", required = true)@PathVariable String masterId ) throws Exception{
		List<ScmXsAgreementProductDetail> agreementProduct = scmXsAgreementProductDetailManager.getAgreementProduct(businessDivisionId,provinceId,ownerId,masterId);
		return new CommonResult<List<ScmXsAgreementProductDetail>>(true,"获取成功",agreementProduct);
	}
	
	/**
	 * 协议品规明细明细页面
	 * @param ID
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{ID}")
	@ApiOperation(value="协议品规明细数据详情",httpMethod = "GET",notes = "协议品规明细数据详情")
	public ScmXsAgreementProductDetail get(@ApiParam(name="ID",value="业务对象主键", required = true)@PathVariable String ID) throws Exception{
		return scmXsAgreementProductDetailManager.get(ID);
	}
	
    /**
	 * 新增协议品规明细
	 * @param scmXsAgreementProductDetail
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新协议品规明细数据", httpMethod = "POST", notes = "新增,更新协议品规明细数据")
	public CommonResult<String> save(@ApiParam(name="scmXsAgreementProductDetail",value="协议品规明细业务对象", required = true)@RequestBody ScmXsAgreementProductDetail scmXsAgreementProductDetail) throws Exception{
		String msg = "添加协议品规明细成功";
		if(StringUtil.isEmpty(scmXsAgreementProductDetail.getId())){
			scmXsAgreementProductDetailManager.create(scmXsAgreementProductDetail);
		}else{
			scmXsAgreementProductDetailManager.update(scmXsAgreementProductDetail);
			 msg = "更新协议品规明细成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除协议品规明细记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{ID}")
	@ApiOperation(value = "删除协议品规明细记录", httpMethod = "DELETE", notes = "删除协议品规明细记录")
	public  CommonResult<String>  remove(@ApiParam(name="ID",value="业务主键", required = true)@PathVariable String ID) throws Exception{
		scmXsAgreementProductDetailManager.remove(ID);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除协议品规明细记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除协议品规明细记录", httpMethod = "DELETE", notes = "批量删除协议品规明细记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmXsAgreementProductDetailManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
	
	@GetMapping(value="/listAgrProductByBigUpdate/{id}/{agreementSummaryId}")
	@ApiOperation(value = "大合同获取商品列表", httpMethod = "GET", notes = "批量删除协议品规明细记录")
	public CommonResult<List<ScmXsBigContractProductByUpdateVo>> listAgrProductByBigUpdate(
			@ApiParam(name="id",value="大合同ID", required = true)@PathVariable String id,
			@ApiParam(name="agreementSummaryId",value="协议id,从供应商下拉框获取", required = true)@PathVariable String agreementSummaryId){
		List<ScmXsBigContractProductByUpdateVo> listAgrProductByBigUpdate = scmXsAgreementProductDetailManager.listAgrProductByBigUpdate(id,agreementSummaryId);
		return new CommonResult<>(true,"获取成功",listAgrProductByBigUpdate);
	}
	
}
