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
import com.winway.scm.persistence.manager.ScmZdCurrencyManager;
import com.winway.scm.model.ScmZdCurrency;
import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;

/**
 * 
 * <pre> 
 * 描述：币种 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-06-16 22:35:42
 * 版权：美达项目组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmZdCurrency/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmZdCurrencyController 币种 控制器类")
public class ScmZdCurrencyController extends BaseController{
	@Resource
	ScmZdCurrencyManager scmZdCurrencyManager;
	
	/**
	 * 币种列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="币种数据列表", httpMethod = "POST", notes = "获取币种列表")
	public PageList<ScmZdCurrency> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmZdCurrencyManager.query(queryFilter);
	}
	
	/**
	 * 币种明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="币种数据详情",httpMethod = "GET",notes = "币种数据详情")
	public ScmZdCurrency get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmZdCurrencyManager.get(id);
	}
	
    /**
	 * 新增币种
	 * @param scmZdCurrency
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新币种数据", httpMethod = "POST", notes = "新增,更新币种数据")
	public CommonResult<String> save(@ApiParam(name="scmZdCurrency",value="币种业务对象", required = true)@RequestBody ScmZdCurrency scmZdCurrency) throws Exception{
		String msg = "添加币种成功";
		if(StringUtil.isEmpty(scmZdCurrency.getId())){
			scmZdCurrencyManager.create(scmZdCurrency);
		}else{
			scmZdCurrencyManager.update(scmZdCurrency);
			 msg = "更新币种成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除币种记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除币种记录", httpMethod = "DELETE", notes = "删除币种记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmZdCurrencyManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除币种记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除币种记录", httpMethod = "DELETE", notes = "批量删除币种记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmZdCurrencyManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
}
