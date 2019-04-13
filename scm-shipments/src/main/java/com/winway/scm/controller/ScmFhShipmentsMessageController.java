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
import com.winway.scm.persistence.manager.ScmFhShipmentsMessageManager;
import com.winway.scm.model.ScmFhShipmentsMessage;
import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;

/**
 * 
 * <pre> 
 * 描述：物流发运信息表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-02-15 17:31:50
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmFhShipmentsMessage/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmFhShipmentsMessageController 物流发运信息")
public class ScmFhShipmentsMessageController extends BaseController{
	@Resource
	ScmFhShipmentsMessageManager scmFhShipmentsMessageManager;
	
	/**
	 * 物流发运信息表列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="物流发运信息表数据列表", httpMethod = "POST", notes = "获取物流发运信息表列表")
	public PageList<ScmFhShipmentsMessage> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmFhShipmentsMessageManager.query(queryFilter);
	}
	
	/**
	 * 物流发运信息表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="物流发运信息表数据详情",httpMethod = "GET",notes = "物流发运信息表数据详情")
	public ScmFhShipmentsMessage get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmFhShipmentsMessageManager.get(id);
	}
	
    /**
	 * 新增物流发运信息表
	 * @param scmFhShipmentsMessage
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新物流发运信息表数据", httpMethod = "POST", notes = "新增,更新物流发运信息表数据")
	public CommonResult<String> save(@ApiParam(name="scmFhShipmentsMessage",value="物流发运信息表业务对象", required = true)@RequestBody ScmFhShipmentsMessage scmFhShipmentsMessage) throws Exception{
		String msg = "添加物流发运信息表成功";
		if(StringUtil.isEmpty(scmFhShipmentsMessage.getId())){
			scmFhShipmentsMessageManager.create(scmFhShipmentsMessage);
		}else{
			scmFhShipmentsMessageManager.update(scmFhShipmentsMessage);
			 msg = "更新物流发运信息表成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除物流发运信息表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除物流发运信息表记录", httpMethod = "DELETE", notes = "删除物流发运信息表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmFhShipmentsMessageManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除物流发运信息表记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除物流发运信息表记录", httpMethod = "DELETE", notes = "批量删除物流发运信息表记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmFhShipmentsMessageManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
}
