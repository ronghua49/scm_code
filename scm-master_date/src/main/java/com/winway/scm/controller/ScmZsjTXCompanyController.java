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
import org.bouncycastle.crypto.RuntimeCryptoException;
import org.springframework.http.MediaType;
import com.winway.scm.persistence.manager.ScmZsjTXCompanyManager;
import com.winway.scm.model.ScmZsjTXCompany;
import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;

/**
 * 
 * <pre> 
 * 描述：公司信息 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmZsjTXCompany/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmZsjTXCompanyController 公司信息")
public class ScmZsjTXCompanyController extends BaseController{
	@Resource
	ScmZsjTXCompanyManager scmZsjTXCompanyManager;
	
	/**
	 * 公司信息列表(分页条件查询)数据
	 * @param
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="公司信息数据列表", httpMethod = "POST", notes = "获取公司信息列表")
	public PageList<ScmZsjTXCompany> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmZsjTXCompanyManager.query(queryFilter);
	}
	
	/**
	 * 公司信息明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="公司信息数据详情",httpMethod = "GET",notes = "公司信息数据详情")
	public ScmZsjTXCompany get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmZsjTXCompanyManager.get(id);
	}
	
	/**
	 * 公司信息明细页面
	 * @param ownerId
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/getByOwnerId/{ownerId}")
	@ApiOperation(value="公司信息数据详情",httpMethod = "GET",notes = "公司信息数据详情")
	public CommonResult<ScmZsjTXCompany> getByOwnerId(@ApiParam(name="ownerId",value="货主id", required = true)@PathVariable String ownerId) throws Exception{
		if(StringUtil.isEmpty(ownerId)) {
			throw new RuntimeException("货主ID不能为空");
		}
		ScmZsjTXCompany byOwnerId = scmZsjTXCompanyManager.getByOwnerId(ownerId);
		if(byOwnerId == null) {
			throw new RuntimeException("未获取到公司信息");
		}
		return new CommonResult<ScmZsjTXCompany>(true,"获取成功",byOwnerId);
	}
	
    /**
	 * 新增公司信息
	 * @param scmZsjTXCompany
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新公司信息数据", httpMethod = "POST", notes = "新增,更新公司信息数据")
	public CommonResult<String> save(@ApiParam(name="scmZsjTXCompany",value="天祥信息业务对象", required = true)@RequestBody ScmZsjTXCompany scmZsjTXCompany) throws Exception{
		String msg = "更新公司信息成功";
		scmZsjTXCompanyManager.create(scmZsjTXCompany);
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除公司信息记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除公司信息记录", httpMethod = "DELETE", notes = "删除公司信息记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmZsjTXCompanyManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除公司信息记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除公司信息记录", httpMethod = "DELETE", notes = "批量删除公司信息记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmZsjTXCompanyManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
	/**
	 * 根据货主id获取RB税点
	 * @param ownerId
	 * @return
	 * @throws Exception
	 * ModelAndView
	 */
	@GetMapping(value="/rb/{ownerId:\\d+}")
	@ApiOperation(value="公司信息数据详情",httpMethod = "GET",notes = "公司信息数据详情")
	public CommonResult<ScmZsjTXCompany> getRB(@ApiParam(name="ownerId",value="货主id", required = true)@PathVariable String ownerId) throws Exception{
		ScmZsjTXCompany company = scmZsjTXCompanyManager.getRB(ownerId);
		return new CommonResult<>(true,"获取rb税点成功",company);
	}
	/**
	 * 根据货主id获取RB税点
	 * @param ownerId
	 * @return
	 * @throws Exception
	 * ModelAndView
	 */
	@GetMapping(value="/getRb/{ownerId}")
	@ApiOperation(value="公司信息数据详情",httpMethod = "GET",notes = "公司信息数据详情")
	public CommonResult<String> getFignRB(@ApiParam(name="ownerId",value="货主id", required = true)@PathVariable String ownerId) throws Exception{
		String rb = scmZsjTXCompanyManager.getFignRB(ownerId);
		return new CommonResult<String>(true,"获取rb税点成功",rb);
	}
	/**
	 * 根据货主id和RB税点 修改RB税点
	 * @param ownerId
	 * @return
	 * @throws Exception
	 * ModelAndView
	 */
	@PostMapping(value="/updateRB")
	@ApiOperation(value="rb税点设置",httpMethod = "POST",notes = "rb税点设置")
	public CommonResult<String> updateRB(@ApiParam(name="scmZsjTXCompany",value="公司信息对象", required = true)@RequestBody ScmZsjTXCompany scmZsjTXCompany ) throws Exception{
		scmZsjTXCompanyManager.updateRB(scmZsjTXCompany);
		return new CommonResult<>("修改RB税点成功");
	}
}
