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
import com.winway.scm.persistence.manager.ScmZsjCommerceMergeManager;
import com.winway.scm.model.ScmZsjCommerceFirst;
import com.winway.scm.model.ScmZsjCommerceMerge;
import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.annotation.Workflow;
import com.hotent.base.controller.BaseController;
import com.hotent.base.feign.UCFeignService;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.JsonUtil;
import com.hotent.base.util.StringUtil;

/**
 * 
 * <pre> 
 * 描述：商业合并表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-18 14:36:01
 * 版权：美达开发小组
 * </pre>
 */

@RestController
@RequestMapping(value="/scm/scmZsjCommerceMerge/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmZsjCommerceMergeController 商业合并")
public class ScmZsjCommerceMergeController extends BaseController{
	@Resource
	ScmZsjCommerceMergeManager scmZsjCommerceMergeManager;
	@Resource
	UCFeignService ucFeignService;
	/**
	 * 商业合并表列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="商业合并表数据列表", httpMethod = "POST", notes = "获取商业合并表列表")
	public PageList<ScmZsjCommerceMerge> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmZsjCommerceMergeManager.query(queryFilter);
	}
	
	/**
	 * 商业合并表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="商业合并表数据详情",httpMethod = "GET",notes = "商业合并表数据详情")
	public ScmZsjCommerceMerge get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmZsjCommerceMergeManager.get(id);
	}
	
    /**
	 * 新增商业合并表
	 * @param scmZsjCommerceMerge
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新商业合并表数据", httpMethod = "POST", notes = "新增,更新商业合并表数据")
	public CommonResult<String> save(@ApiParam(name="scmZsjCommerceMerge",value="商业合并表业务对象", required = true)@RequestBody ScmZsjCommerceMerge scmZsjCommerceMerge) throws Exception{
		String msg = "添加商业合并表成功";
		if(StringUtil.isEmpty(scmZsjCommerceMerge.getId())){
			JsonNode user = ucFeignService.getUser(current(), "");
			String fullname = user.get("fullname").asText();
			String id = user.get("id").asText();
			scmZsjCommerceMerge.setOperatorName(fullname);
			scmZsjCommerceMerge.setOperatorId(id);
			scmZsjCommerceMerge.setApprovalState("0");
			scmZsjCommerceMergeManager.create(scmZsjCommerceMerge);
		}else{
			scmZsjCommerceMergeManager.update(scmZsjCommerceMerge);
			 msg = "更新商业合并表成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除商业合并表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除商业合并表记录", httpMethod = "DELETE", notes = "删除商业合并表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmZsjCommerceMergeManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除商业合并表记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除商业合并表记录", httpMethod = "DELETE", notes = "批量删除商业合并表记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmZsjCommerceMergeManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
	
	/**
	 * @param 商业合并申请
	 * 需要调整保存方法,添加委托书数据保存
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 * flowKey:审批类型
	 * sysCode:系统别名
	 * instanceIdField:与实体类审批ID相同,controller层接到对象会有审批ID,处理业务逻辑后,保存对象即可
	 * varKeys:脚本使用参数
	 */
	@PostMapping(value = "/sendApply")
    @ApiOperation(value = "商业合并申请", httpMethod = "POST", notes = "将合并列表数据除全部封装提交")
    @Workflow(flowKey = "sysy", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {})
    public CommonResult<ScmZsjCommerceMerge> sendApply(
            @ApiParam(name = "ScmZsjCommerceMerge", value = "商业合并对象", required = true) @RequestBody ScmZsjCommerceMerge scmZsjCommerceMerge,
            HttpServletRequest request) throws Exception {
		JsonNode user = ucFeignService.getUser(current(), "");
		String fullname = user.get("fullname").asText();
		String id = user.get("id").asText();
		scmZsjCommerceMerge.setApprovalState("1");
		ScmZsjCommerceMerge merge = scmZsjCommerceMergeManager.get(scmZsjCommerceMerge.getId());
		if(merge == null) {
			scmZsjCommerceMerge.setOperatorName(fullname);
			scmZsjCommerceMerge.setOperatorId(id);
			scmZsjCommerceMergeManager.create(scmZsjCommerceMerge);
		}else{
			scmZsjCommerceMergeManager.update(scmZsjCommerceMerge);
		}
		return new CommonResult<ScmZsjCommerceMerge>(true, "审批发起成功");
	}
	//localhost:8997/scm/scmZsjCommerceMerge/v1/cacheApply
	@PostMapping(value = "/cacheApply")
    @ApiOperation(value = "商业合并暂存申请", httpMethod = "POST", notes = "将合并列表数据除全部封装提交")
    public CommonResult<ScmZsjCommerceMerge> cacheApply(
            @ApiParam(name = "ScmZsjCommerceMerge", value = "商业合并对象", required = true) @RequestBody ScmZsjCommerceMerge scmZsjCommerceMerge,
            HttpServletRequest request) throws Exception {
		JsonNode user = ucFeignService.getUser(current(), "");
		String fullname = user.get("fullname").asText();
		String id = user.get("id").asText();
		scmZsjCommerceMerge.setOperatorName(fullname);
		scmZsjCommerceMerge.setOperatorId(id);
		scmZsjCommerceMerge.setApprovalState("0");		
		scmZsjCommerceMergeManager.create(scmZsjCommerceMerge);
		return new CommonResult<ScmZsjCommerceMerge>(true, "暂存申请成功");
	}
	
    @PostMapping(value = "/endApply")
    @ApiOperation(value = "商业合并申请审批流程通过", httpMethod = "POST", notes = "商业首营审批流程通过,修改审批状态")
    public void endApply(@ApiParam(name = "params", value = "流程事件参数", required = true) @RequestBody String params, 
    		HttpServletRequest request) throws Exception {
    	JsonNode jsonNode = JsonUtil.toJsonNode(params);
    	scmZsjCommerceMergeManager.endApply(jsonNode);
    	
    }
  
}
