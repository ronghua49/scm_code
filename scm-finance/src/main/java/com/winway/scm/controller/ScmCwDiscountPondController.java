package com.winway.scm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import com.winway.scm.persistence.manager.ScmCwDiscountPondManager;
import com.winway.scm.vo.SO_HEADER;
import com.winway.scm.vo.ScmCwCompensationVo;
import com.winway.scm.vo.ScmCwInvoiceVo;
import com.winway.scm.model.ScmCwDiscountPond;
import com.winway.scm.model.ScmCwDiscountPondPriceChange;
import com.winway.scm.model.ScmCwToSapDiscount;
import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.model.WinwayResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;

/**
 * 
 * <pre> 
 * 描述：票折池表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-23 18:19:25
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmCwDiscountPond/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmCwDiscountPondController  票折池表 控制器类")
public class ScmCwDiscountPondController extends BaseController{
	@Resource
	ScmCwDiscountPondManager scmCwDiscountPondManager;
	
	/**
	 * 票折池表列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="票折池表数据列表", httpMethod = "POST", notes = "获取票折池表列表")
	public PageList<ScmCwDiscountPond> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmCwDiscountPondManager.query(queryFilter);
	}
	
	/**
	 * 票折池表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="票折池表数据详情",httpMethod = "GET",notes = "票折池表数据详情")
	public ScmCwDiscountPond get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmCwDiscountPondManager.get(id);
	}
	
	/**
	 * 票折池表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/autoDiscount/{id}/{discountTypeCode}")
	@ApiOperation(value="自动票折",httpMethod = "GET",notes = "票折池表数据详情")
	public CommonResult<ScmCwToSapDiscount> autoDiscount(@ApiParam(name="id",value="发货单ID", required = true)@PathVariable String id,
			@ApiParam(name="discountTypeCode",value="发货单ID", required = true)@PathVariable String discountTypeCode) throws Exception{
		ScmCwToSapDiscount autoDiscount = scmCwDiscountPondManager.autoDiscount(id,discountTypeCode);
		return new CommonResult<ScmCwToSapDiscount>(true, "开票成功",autoDiscount);
	}
	/**
	 * 票折池表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/freezeManage/{id}")
	@ApiOperation(value="冻结/解冻操作",httpMethod = "GET",notes = "冻结/解冻操作")
	public CommonResult<String> freezeManage(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		String freezeManage = scmCwDiscountPondManager.freezeManage(id);
		return new CommonResult<String>(true,"处理成功",freezeManage);
	}
	
    /**
	 * 新增票折池表
	 * @param scmCwDiscountPond
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新票折池表数据", httpMethod = "POST", notes = "新增,更新票折池表数据")
	public CommonResult<String> save(@ApiParam(name="scmCwDiscountPond",value="票折池表业务对象", required = true)@RequestBody ScmCwDiscountPond scmCwDiscountPond) throws Exception{
		String msg = "添加票折池表成功";
		if(StringUtil.isEmpty(scmCwDiscountPond.getId())){
			scmCwDiscountPondManager.create(scmCwDiscountPond);
		}else{
			scmCwDiscountPondManager.update(scmCwDiscountPond);
			 msg = "更新票折池表成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除票折池表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除票折池表记录", httpMethod = "DELETE", notes = "删除票折池表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmCwDiscountPondManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除票折池表记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除票折池表记录", httpMethod = "DELETE", notes = "批量删除票折池表记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmCwDiscountPondManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
	
  /**
	 * 接受费控系统传递补差票折数据
	 * @param scmCwDiscountPond
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="saveByCompensation")
	@ApiOperation(value = "新增,更新票折池表数据", httpMethod = "POST", notes = "新增,更新票折池表数据")
	public WinwayResult<List<ScmCwCompensationVo>> saveByCompensation(HttpServletRequest request,@ApiParam(name="scmCwCompensationVo",value="补差返回数组", required = true)@RequestBody ScmCwCompensationVo... scmCwCompensationVo) throws Exception{
		List<ScmCwCompensationVo> arrayList = new ArrayList<ScmCwCompensationVo>();
		Collections.addAll(arrayList, scmCwCompensationVo);
		if(arrayList.size() == 0) {
			return new WinwayResult<List<ScmCwCompensationVo>>(request.getRequestURI(), "获取成功", new ArrayList<ScmCwCompensationVo>());
		}
		
		List<ScmCwCompensationVo> scmCwCompensationVos = scmCwDiscountPondManager.saveByCompensation(arrayList);
		return new WinwayResult<List<ScmCwCompensationVo>>(request.getRequestURI(), "获取成功", scmCwCompensationVos);
	}
	
	
}
