package com.winway.scm.controller;

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
import com.winway.scm.model.ScmCgContractProduct;
import com.winway.scm.model.ScmCgProcurementContract;
import com.winway.scm.persistence.manager.ScmCgContractProductManager;
import com.winway.scm.persistence.manager.ScmCgProcurementContractManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * <pre> 
 * 描述：采购合同商品表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-26 13:47:44
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmCgContractProduct/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmCgContractProductController 采购合同商品")
public class ScmCgContractProductController extends BaseController{
	@Resource
	ScmCgContractProductManager scmCgContractProductManager;
	@Resource
	ScmCgProcurementContractManager scmCgProcurementContractManager;
	
	/**
	 * 采购合同商品表列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="采购合同商品表数据列表", httpMethod = "POST", notes = "获取采购合同商品表列表")
	public PageList<ScmCgContractProduct> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmCgContractProductManager.query(queryFilter);
	}
	
	/**
	 * 采购合同商品表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="采购合同商品表数据详情",httpMethod = "GET",notes = "采购合同商品表数据详情")
	public ScmCgContractProduct get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmCgContractProductManager.get(id);
	}
	
    /**
	 * 新增采购合同商品表
	 * @param scmCgContractProduct
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新采购合同商品表数据", httpMethod = "POST", notes = "新增,更新采购合同商品表数据")
	public CommonResult<String> save(@ApiParam(name="scmCgContractProduct",value="采购合同商品表业务对象", required = true)@RequestBody ScmCgContractProduct scmCgContractProduct) throws Exception{
		String msg = "添加采购合同商品表成功";
		//验证合同当前状态
		String contractId = scmCgContractProduct.getContractId();
		if(StringUtil.isNotEmpty(contractId)) {
			ScmCgProcurementContract scmCgProcurementContract = scmCgProcurementContractManager.get(contractId);
			if(scmCgProcurementContract == null) {
				throw new RuntimeException("当前合同不存在或已经被删除");
			}else{
				if("1".equals(scmCgProcurementContract.getApprovalState()) || "2".equals(scmCgProcurementContract.getApprovalState())) {
					throw new RuntimeException("当前合同已提交审批或已通过审批,不可修改数据");
				}
			}
			if(StringUtil.isEmpty(scmCgContractProduct.getId())){
				scmCgContractProductManager.create(scmCgContractProduct);
			}else{
				scmCgContractProductManager.update(scmCgContractProduct);
				msg = "更新采购合同商品表成功";
			}
			//更新合同下的商品总数及商品总价
			scmCgProcurementContractManager.update(scmCgProcurementContract);
			return new CommonResult<String>(true,msg,scmCgContractProduct.getId());
		}else{
			throw new RuntimeException("合同ID不存在");
		}
	}
	
	/**
	 * 删除采购合同商品表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除采购合同商品表记录", httpMethod = "DELETE", notes = "删除采购合同商品表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		ScmCgContractProduct scmCgContractProduct = scmCgContractProductManager.get(id);
		if(scmCgContractProduct == null) {
			return new CommonResult<String>(true, "删除成功");
		}
		//验证合同当前状态
		String contractId = scmCgContractProduct.getContractId();
		if(StringUtil.isEmpty(contractId)) {
			ScmCgProcurementContract scmCgProcurementContract = scmCgProcurementContractManager.get(contractId);
			if(scmCgProcurementContract == null) {
				throw new RuntimeException("当前合同不存在或已经被删除");
			}else{
				if("1".equals(scmCgProcurementContract.getApprovalState()) || "2".equals(scmCgProcurementContract.getApprovalState())) {
					throw new RuntimeException("当前合同已提交审批或已通过审批,不可修改数据");
				}
			}
		}
		scmCgContractProductManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除采购合同商品表记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除采购合同商品表记录", httpMethod = "DELETE", notes = "批量删除采购合同商品表记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		for (String id : ids) {
			ScmCgContractProduct scmCgContractProduct = scmCgContractProductManager.get(id);
			if(scmCgContractProduct == null) {
				continue;
			}else{
				String contractId = scmCgContractProduct.getContractId();
				ScmCgProcurementContract scmCgProcurementContract = scmCgProcurementContractManager.get(contractId);
				if(scmCgProcurementContract == null) {
					throw new RuntimeException("当前合同不存在或已经被删除");
				}else{
					if("1".equals(scmCgProcurementContract.getApprovalState()) || "2".equals(scmCgProcurementContract.getApprovalState())) {
						throw new RuntimeException("当前合同已提交审批或已通过审批,不可修改数据");
					}
				}
			}
		}
		scmCgContractProductManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
}
