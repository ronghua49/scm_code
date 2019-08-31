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
import com.winway.scm.persistence.manager.ScmGspDoubtProductManager;
import com.winway.scm.model.ScmGspDoubtProduct;
import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;

/**
 * 
 * <pre> 
 * 描述：怀疑药品 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-18 18:21:20
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmGspDoubtProduct/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmGspDoubtProductController 怀疑药品")
public class ScmGspDoubtProductController extends BaseController{
	@Resource
	ScmGspDoubtProductManager scmGspDoubtProductManager;
	
	/**
	 * Scm_Gsp_DoubtProduct列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="Scm_Gsp_DoubtProduct数据列表", httpMethod = "POST", notes = "获取Scm_Gsp_DoubtProduct列表")
	public PageList<ScmGspDoubtProduct> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmGspDoubtProductManager.query(queryFilter);
	}
	
	/**
	 * Scm_Gsp_DoubtProduct明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="Scm_Gsp_DoubtProduct数据详情",httpMethod = "GET",notes = "Scm_Gsp_DoubtProduct数据详情")
	public ScmGspDoubtProduct get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmGspDoubtProductManager.get(id);
	}
	
    /**
	 * 新增Scm_Gsp_DoubtProduct
	 * @param scmGspDoubtProduct
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新Scm_Gsp_DoubtProduct数据", httpMethod = "POST", notes = "新增,更新Scm_Gsp_DoubtProduct数据")
	public CommonResult<String> save(@ApiParam(name="scmGspDoubtProduct",value="Scm_Gsp_DoubtProduct业务对象", required = true)@RequestBody ScmGspDoubtProduct scmGspDoubtProduct) throws Exception{
		String msg = "添加Scm_Gsp_DoubtProduct成功";
		if(StringUtil.isEmpty(scmGspDoubtProduct.getId())){
			scmGspDoubtProductManager.create(scmGspDoubtProduct);
		}else{
			scmGspDoubtProductManager.update(scmGspDoubtProduct);
			 msg = "更新Scm_Gsp_DoubtProduct成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除Scm_Gsp_DoubtProduct记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除Scm_Gsp_DoubtProduct记录", httpMethod = "DELETE", notes = "删除Scm_Gsp_DoubtProduct记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmGspDoubtProductManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除Scm_Gsp_DoubtProduct记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除Scm_Gsp_DoubtProduct记录", httpMethod = "DELETE", notes = "批量删除Scm_Gsp_DoubtProduct记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmGspDoubtProductManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
}
