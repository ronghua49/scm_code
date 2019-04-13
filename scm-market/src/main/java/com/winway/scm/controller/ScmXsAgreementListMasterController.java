package com.winway.scm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.annotation.Workflow;
import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.model.WinwayResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.JsonUtil;
import com.hotent.base.util.StringUtil;
import com.winway.scm.model.ScmXsAgreementListMaster;
import com.winway.scm.persistence.manager.ScmXsAgreementListMasterManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * <pre> 
 * 描述：商业协议合作名单主表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 15:01:16
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmXsAgreementListMaster/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmXsAgreementListMasterController 商业协议合作名单主表")
public class ScmXsAgreementListMasterController extends BaseController{
	@Resource
	ScmXsAgreementListMasterManager scmXsAgreementListMasterManager;
	
	/**
	 * 商业协议合作名单主表列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="商业协议合作名单主表数据列表", httpMethod = "POST", notes = "获取商业协议合作名单主表列表,query必须添加货主id进行筛选查询")
	public PageList<ScmXsAgreementListMaster> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmXsAgreementListMasterManager.query(queryFilter);
	}
	
	/**
	 * 商业协议合作名单主表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="商业协议合作名单主表数据详情",httpMethod = "GET",notes = "商业协议合作名单主表数据详情")
	public ScmXsAgreementListMaster get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmXsAgreementListMasterManager.get(id);
	}
	
    /**
	 * 新增商业协议合作名单主表
	 * @param scmXsAgreementListMaster
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新商业协议合作名单主表数据", httpMethod = "POST", notes = "新增,更新商业协议合作名单主表数据,包含子表数据")
	public CommonResult<ScmXsAgreementListMaster> save(@ApiParam(name="scmXsAgreementListMaster",value="商业协议合作名单主表业务对象", required = true)@RequestBody ScmXsAgreementListMaster scmXsAgreementListMaster) throws Exception{
		String msg = "添加商业协议合作名单主表成功";
		//申请状态
		scmXsAgreementListMaster.setApprovalState("0");
		scmXsAgreementListMaster.setIsEffect("0");
		if(StringUtil.isEmpty(scmXsAgreementListMaster.getId())){
			scmXsAgreementListMasterManager.create(scmXsAgreementListMaster);
		}else{
			scmXsAgreementListMasterManager.update(scmXsAgreementListMaster);
			 msg = "更新商业协议合作名单主表成功";
		}
		return new CommonResult<ScmXsAgreementListMaster>(true,msg,scmXsAgreementListMaster);
	}
	
	/**
	 * 删除商业协议合作名单主表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除商业协议合作名单主表记录", httpMethod = "DELETE", notes = "删除商业协议合作名单主表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmXsAgreementListMasterManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除商业协议合作名单主表记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除商业协议合作名单主表记录", httpMethod = "DELETE", notes = "批量删除商业协议合作名单主表记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmXsAgreementListMasterManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
	
	/**
	 * @param 经销商协议合作名单申请
	 * @param request
	 * @return
	 * @throws Exception
	 * flowKey:审批类型
	 * sysCode:系统别名
	 * instanceIdField:与实体类审批ID相同,controller层接到对象会有审批ID,处理业务逻辑后,保存对象即可
	 * varKeys:脚本使用参数
	 */
	@PostMapping(value = "sendApply")
    @ApiOperation(value = "经销商协议合作名单申请", httpMethod = "POST", notes = "提交时需要在scmXsAgreementListMaster对象中封装好名单对象提交")
    @Workflow(flowKey = "syxyhzmdsq", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {})
    public  CommonResult<ScmXsAgreementListMaster> sendApply(
            @ApiParam(name = "scmXsAgreementListMaster", value = "经销商协议合作名单总表对象", required = true) @RequestBody ScmXsAgreementListMaster scmXsAgreementListMaster,
            HttpServletRequest request) throws Exception {
		scmXsAgreementListMasterManager.sendApply(scmXsAgreementListMaster);
	   return new CommonResult<ScmXsAgreementListMaster>(true, "审批发起成功",scmXsAgreementListMaster);
	}
	
    @PostMapping(value = "/endApply")
    @ApiOperation(value = "经销商协议合作名单审批通过及驳回", httpMethod = "POST", notes = "经销商协议合作名单审批通过及驳回")
    public void endApply(@ApiParam(name = "params", value = "流程事件参数", required = true) @RequestBody String params, 
    		HttpServletRequest request) throws Exception {
    	JsonNode jsonNode = JsonUtil.toJsonNode(params);
    	scmXsAgreementListMasterManager.endApply(jsonNode);
    }
	
}
