package com.winway.scm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import com.winway.scm.persistence.manager.ScmXsCreditCollectManager;
import com.winway.scm.model.ScmXsBigContract;
import com.winway.scm.model.ScmXsCreditCollect;
import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.annotation.Workflow;
import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.model.WinwayResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.JsonUtil;
import com.hotent.base.util.StringUtil;

/**
 * 
 * <pre> 
 * 描述：商业资信申请总表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:43
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmXsCreditCollect/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmXsCreditCollectController 商业资信申请总表")
public class ScmXsCreditCollectController extends BaseController{
	@Resource
	ScmXsCreditCollectManager scmXsCreditCollectManager;
	
	/**
	 * 商业资信申请总表列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="商业资信申请总表数据列表", httpMethod = "POST", notes = "获取商业资信申请总表列表,query必须添加货主ID进行筛选")
	public PageList<ScmXsCreditCollect> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmXsCreditCollectManager.query(queryFilter);
	}
	
	/**
	 * 商业资信申请总表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="商业资信申请总表数据详情",httpMethod = "GET",notes = "商业资信申请总表数据详情")
	public ScmXsCreditCollect get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmXsCreditCollectManager.get(id);
	}
	
    /**
	 * 新增商业资信申请总表
	 * @param scmXsCreditCollect
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新商业资信申请总表数据", httpMethod = "POST", notes = "新增,更新商业资信申请总表数据")
	public CommonResult<ScmXsCreditCollect> save(@ApiParam(name="scmXsCreditCollect",value="商业资信申请总表业务对象", required = true)@RequestBody ScmXsCreditCollect scmXsCreditCollect) throws Exception{
		String msg = "添加商业资信申请总表成功";
		if(StringUtil.isEmpty(scmXsCreditCollect.getId())){
			scmXsCreditCollect.setApprovalState("0");
			scmXsCreditCollectManager.create(scmXsCreditCollect);
		}else{
			scmXsCreditCollectManager.update(scmXsCreditCollect);
			 msg = "更新商业资信申请总表成功";
		}
		return new CommonResult<ScmXsCreditCollect>(true,msg,scmXsCreditCollect);
	}
	
	/**
	 * 删除商业资信申请总表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除商业资信申请总表记录", httpMethod = "DELETE", notes = "删除商业资信申请总表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmXsCreditCollectManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除商业资信申请总表记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除商业资信申请总表记录", httpMethod = "DELETE", notes = "批量删除商业资信申请总表记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmXsCreditCollectManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
	
	/**
	 * @param 商业资信申请
	 * @param request
	 * @return
	 * @throws Exception
	 * flowKey:审批类型
	 * sysCode:系统别名
	 * instanceIdField:与实体类审批ID相同,controller层接到对象会有审批ID,处理业务逻辑后,保存对象即可
	 * varKeys:脚本使用参数
	 */
	@PostMapping(value = "sendApply")
    @ApiOperation(value = "商业资信申请", httpMethod = "POST", notes = "提交时需要将商业资信详情封装至总表一起提交")
    @Workflow(flowKey = "syzxlc", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {})
    public CommonResult<ScmXsCreditCollect> sendApply(
            @ApiParam(name = "ScmXsCreditCollect", value = "经销商协议合作名单总表对象", required = true) @RequestBody ScmXsCreditCollect scmXsCreditCollect,
            HttpServletRequest request) throws Exception {
		scmXsCreditCollectManager.sendApply(scmXsCreditCollect);
		return new CommonResult<ScmXsCreditCollect>(true, "审批发起成功",scmXsCreditCollect);
	}
	
	@PostMapping(value = "/endApply")
    @ApiOperation(value = "商业资信申请审批通过及驳回", httpMethod = "POST", notes = "经销商协议合作名单审批通过及驳回")
    public void endApply(@ApiParam(name = "params", value = "流程事件参数", required = true) @RequestBody String params, 
    		HttpServletRequest request) throws Exception {
    	JsonNode jsonNode = JsonUtil.toJsonNode(params);
    	scmXsCreditCollectManager.endApply(jsonNode);
    }
	
	/**
	 * 根据审批id获取商业资信详情
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/getCreditApplyByApprovalId/{id}")
	@ApiOperation(value="商业资信申请总表数据详情",httpMethod = "GET",notes = "商业资信申请总表数据详情")
	public ScmXsCreditCollect getCreditApplyByApprovalId(@ApiParam(name="id",value="审批Id", required = true)@PathVariable String id) throws Exception{
		return scmXsCreditCollectManager.getCreditApplyByApprovalId(id);
	}
}
