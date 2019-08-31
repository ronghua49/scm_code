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
import com.winway.scm.persistence.manager.ScmZsjSupplierAccreditManager;
import com.winway.scm.model.ScmZsjCommerceAccredit;
import com.winway.scm.model.ScmZsjSupplierAccredit;
import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;

/**
 * 
 * <pre> 
 * 描述：授权销售品种 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmZsjSupplierAccredit/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmZsjSupplierAccreditController 授权销售品种")
public class ScmZsjSupplierAccreditController extends BaseController{
	@Resource
	ScmZsjSupplierAccreditManager scmZsjSupplierAccreditManager;
	
	/**
	 * 授权销售品种列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="授权销售品种数据列表", httpMethod = "POST", notes = "获取授权销售品种列表")
	public PageList<ScmZsjSupplierAccredit> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmZsjSupplierAccreditManager.query(queryFilter);
	}
	
	/**
	 * 授权销售品种明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="授权销售品种数据详情",httpMethod = "GET",notes = "授权销售品种数据详情")
	public ScmZsjSupplierAccredit get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmZsjSupplierAccreditManager.get(id);
	}
	
    /**
	 * 新增授权销售品种
	 * @param scmZsjSupplierAccredit
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新授权销售品种数据", httpMethod = "POST", notes = "新增,更新授权销售品种数据")
	public CommonResult<String> save(@ApiParam(name="scmZsjSupplierAccredit",value="授权销售品种业务对象", required = true)@RequestBody ScmZsjSupplierAccredit scmZsjSupplierAccredit) throws Exception{
		String msg = "添加授权销售品种成功";
		if(StringUtil.isEmpty(scmZsjSupplierAccredit.getId())){
			scmZsjSupplierAccreditManager.create(scmZsjSupplierAccredit);
		}else{
			scmZsjSupplierAccreditManager.update(scmZsjSupplierAccredit);
			 msg = "更新授权销售品种成功";
		}
		return new CommonResult<String>(msg);
	}
	
    /**
     * 新增商业授权销售品种
     *
     * @param scmZsjCommerceAccredit
     * @return
     * @throws Exception
     * @throws
     */
    @PostMapping(value = "saveList")
    @ApiOperation(value = "新增,更新商业授权销售品种数据", httpMethod = "POST", notes = "新增,更新商业授权销售品种数据")
    public CommonResult<String> saveList(@ApiParam(name = "scmZsjSupplierAccredit", value = "商业授权销售品种业务对象数组", required = true) @RequestBody ScmZsjSupplierAccredit... scmZsjSupplierAccredit) throws Exception {
    	String msg = "添加商业授权销售品种成功";
    	if(scmZsjSupplierAccredit.length == 0) {
    		return new CommonResult<String>(msg);
    	}
    	scmZsjSupplierAccreditManager.saveList(scmZsjSupplierAccredit);
    	return new CommonResult<String>(msg);
    }
	
	/**
	 * 删除授权销售品种记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除授权销售品种记录", httpMethod = "DELETE", notes = "删除授权销售品种记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmZsjSupplierAccreditManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除授权销售品种记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除授权销售品种记录", httpMethod = "DELETE", notes = "批量删除授权销售品种记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmZsjSupplierAccreditManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
}
