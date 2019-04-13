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
import com.winway.scm.model.ScmCgProRepProduct;
import com.winway.scm.model.ScmCgProReplacementOrder;
import com.winway.scm.persistence.dao.ScmCgContractProductDao;
import com.winway.scm.persistence.dao.ScmCgProReplacementOrderDao;
import com.winway.scm.persistence.manager.ScmCgContractProductManager;
import com.winway.scm.persistence.manager.ScmCgProRepProductManager;
import com.winway.scm.persistence.manager.ScmCgProReplacementOrderManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * <pre> 
 * 描述：采购补单商品表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-26 13:47:44
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmCgProRepProduct/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmCgProRepProductController 采购补单商品")
public class ScmCgProRepProductController extends BaseController{
	@Resource
	ScmCgProRepProductManager scmCgProRepProductManager;
	@Resource
	ScmCgProReplacementOrderManager scmCgProReplacementOrderManager;
	@Resource
	ScmCgContractProductManager scmCgContractProductManager;
	@Resource
	ScmCgContractProductDao scmCgContractProductDao;
	@Resource
	ScmCgProReplacementOrderDao scmCgProReplacementOrderDao;

	/**
	 * 采购补单商品表列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="采购补单商品表数据列表", httpMethod = "POST", notes = "获取采购补单商品表列表")
	public PageList<ScmCgProRepProduct> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmCgProRepProductManager.query(queryFilter);
	}
	
	/**
	 * 采购补单商品表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="采购补单商品表数据详情",httpMethod = "GET",notes = "采购补单商品表数据详情")
	public ScmCgProRepProduct get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmCgProRepProductManager.get(id);
	}
	
    /**
	 * 新增采购补单商品表
	 * @param scmCgProRepProduct
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新采购补单商品表数据", httpMethod = "POST", notes = "新增,更新采购补单商品表数据")
	public CommonResult<String> save(@ApiParam(name="scmCgProRepProduct",value="采购补单商品表业务对象", required = true)@RequestBody ScmCgProRepProduct scmCgProRepProduct) throws Exception{
		String msg = "添加采购补单商品表成功";
		scmCgProRepProductManager.save(scmCgProRepProduct);
		return new CommonResult<>(msg);
	}
	
	/**
	 * 删除采购补单商品表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除采购补单商品表记录", httpMethod = "DELETE", notes = "删除采购补单商品表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmCgProRepProductManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除采购补单商品表记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除采购补单商品表记录", httpMethod = "DELETE", notes = "批量删除采购补单商品表记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmCgProRepProductManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
}
