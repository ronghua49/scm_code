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
import com.winway.scm.model.ScmCgContractProduct;
import com.winway.scm.model.ScmCgProOrderProduct;
import com.winway.scm.model.ScmCgProcurementOrder;
import com.winway.scm.persistence.dao.ScmCgContractProductDao;
import com.winway.scm.persistence.manager.ScmCgContractProductManager;
import com.winway.scm.persistence.manager.ScmCgProOrderProductManager;
import com.winway.scm.persistence.manager.ScmCgProcurementOrderManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * <pre> 
 * 描述：采购订单商品表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-26 13:47:44
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmCgProOrderProduct/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmCgProOrderProductController 采购订单商品")
public class ScmCgProOrderProductController extends BaseController{
	@Resource
	ScmCgProOrderProductManager scmCgProOrderProductManager;
	@Resource
	ScmCgProcurementOrderManager scmCgProcurementOrderManager ;
	@Resource
	ScmCgContractProductManager scmCgContractProductManager;
	@Resource
	ScmCgContractProductDao scmCgContractProductDao;
	
	/**
	 * 采购订单商品表列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="采购订单商品表数据列表", httpMethod = "POST", notes = "获取采购订单商品表列表")
	public PageList<ScmCgProOrderProduct> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmCgProOrderProductManager.query(queryFilter);
	}
	
	/**
	 * 采购订单商品表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="采购订单商品表数据详情",httpMethod = "GET",notes = "采购订单商品表数据详情")
	public ScmCgProOrderProduct get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmCgProOrderProductManager.get(id);
	}
	
    /**
	 * 新增采购订单商品表
	 * @param scmCgProOrderProduct
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新采购订单商品表数据", httpMethod = "POST", notes = "新增,更新采购订单商品表数据")
	public CommonResult<String> save(@ApiParam(name="scmCgProOrderProduct",value="采购订单商品表业务对象", required = true)@RequestBody ScmCgProOrderProduct scmCgProOrderProduct) throws Exception{
		String msg = "添加采购订单商品表成功";
		if(StringUtil.isNotEmpty(scmCgProOrderProduct.getProOrderId())) {
			ScmCgProcurementOrder scmCgProcurementOrder = scmCgProcurementOrderManager.get(scmCgProOrderProduct.getProOrderId());
			if(scmCgProcurementOrder == null) {
				throw new RuntimeException("当前订单不存在或已经被删除");
			}else{
				if("1".equals(scmCgProcurementOrder.getApprovalState()) || "2".equals(scmCgProcurementOrder.getApprovalState())) {
					throw new RuntimeException("当前订单已提交审批或已通过审批,不可修改数据");
				}
			}
			ScmCgContractProduct scmCgContractProduct = scmCgContractProductManager.get(scmCgProOrderProduct.getContractProductId());
			if(StringUtil.isEmpty(scmCgProOrderProduct.getId())){
				//验证是否已经存在
				scmCgProOrderProductManager.listProductByOrderId(scmCgProOrderProduct);
				//验证数量
				if(scmCgContractProduct.getSurplusAmount() - scmCgProOrderProduct.getProductSum() >= 0) {
					scmCgProOrderProductManager.create(scmCgProOrderProduct);
					scmCgContractProduct.setSurplusAmount(scmCgContractProduct.getSurplusAmount() - scmCgProOrderProduct.getProductSum());
					scmCgContractProductDao.update(scmCgContractProduct);
				}else{
					throw new RuntimeException("数量已超出余量,请调整");
				}
			}else{
				//验证数量
				ScmCgProOrderProduct scmCgProOrderProduct2 = scmCgProOrderProductManager.get(scmCgProOrderProduct.getId());
				if(scmCgContractProduct.getSurplusAmount()+scmCgProOrderProduct2.getProductSum()- scmCgProOrderProduct.getProductSum() >= 0) {
					scmCgProOrderProductManager.update(scmCgProOrderProduct);
					scmCgContractProduct.setSurplusAmount(scmCgContractProduct.getSurplusAmount()+scmCgProOrderProduct2.getProductSum() - scmCgProOrderProduct.getProductSum());
					scmCgContractProductDao.update(scmCgContractProduct);
					msg = "更新采购订单商品表成功";
				}else{
					throw new RuntimeException("数量已超出余量,请调整");
				}
			}
			scmCgProcurementOrderManager.update(scmCgProcurementOrder);
			return new CommonResult<String>(msg);
		}else{
			throw new RuntimeException("订单ID不存在");
		}
		
	}
	
	/**
	 * 删除采购订单商品表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除采购订单商品表记录", httpMethod = "DELETE", notes = "删除采购订单商品表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmCgProOrderProductManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除采购订单商品表记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除采购订单商品表记录", httpMethod = "DELETE", notes = "批量删除采购订单商品表记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmCgProOrderProductManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
}
