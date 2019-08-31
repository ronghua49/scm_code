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
import com.winway.scm.persistence.manager.ScmCgGodownentryMasterManager;
import com.winway.purchase.vo.ReturnWmsVo;
import com.winway.scm.model.ScmCgGodownentryMaster;
import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;

/**
 * 
 * <pre> 
 * 描述：入库单主表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-06-28 14:54:15
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmCgGodownentryMaster/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmCgGodownentryMasterController")
public class ScmCgGodownentryMasterController extends BaseController{
	@Resource
	ScmCgGodownentryMasterManager scmCgGodownentryMasterManager;
	
	/**
	 * 入库单主表列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="入库单主表数据列表", httpMethod = "POST", notes = "获取入库单主表列表")
	public PageList<ScmCgGodownentryMaster> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmCgGodownentryMasterManager.query(queryFilter);
	}
	
	/**
	 * 入库单主表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="入库单主表数据详情",httpMethod = "GET",notes = "入库单主表数据详情")
	public ScmCgGodownentryMaster get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmCgGodownentryMasterManager.get(id);
	}
	
    /**
	 * 新增入库单主表
	 * @param scmCgGodownentryMaster
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新入库单主表数据", httpMethod = "POST", notes = "新增,更新入库单主表数据")
	public CommonResult<String> save(@ApiParam(name="scmCgGodownentryMaster",value="入库单主表业务对象", required = true)@RequestBody ScmCgGodownentryMaster scmCgGodownentryMaster) throws Exception{
		String msg = "添加入库单主表成功";
		if(StringUtil.isEmpty(scmCgGodownentryMaster.getId())){
			scmCgGodownentryMasterManager.create(scmCgGodownentryMaster);
		}else{
			scmCgGodownentryMasterManager.update(scmCgGodownentryMaster);
			 msg = "更新入库单主表成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除入库单主表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除入库单主表记录", httpMethod = "DELETE", notes = "删除入库单主表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmCgGodownentryMasterManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除入库单主表记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除入库单主表记录", httpMethod = "DELETE", notes = "批量删除入库单主表记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmCgGodownentryMasterManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
	
    /**
	 * 新增入库单
	 * @param scmCgGodownEntry
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="/acceptDate")
	@ApiOperation(value = "从WMS接受数据", httpMethod = "POST", notes = "新增,更新入库单数据")
	public ReturnWmsVo acceptDate(@ApiParam(name="scmCgGodownEntry",value="入库单数组", required = true)@RequestBody ScmCgGodownentryMaster scmCgGodownentryMaster) throws Exception{
		ReturnWmsVo returnWmsVo = scmCgGodownentryMasterManager.acceptDate(scmCgGodownentryMaster);
		return returnWmsVo;
	}
}
