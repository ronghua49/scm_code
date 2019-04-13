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
import com.hotent.base.feign.UCFeignService;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.JsonUtil;
import com.hotent.base.util.StringUtil;
import com.winway.scm.model.ScmZsjSupplierFirst;
import com.winway.scm.persistence.dao.ScmZsjSupplierFirstDao;
import com.winway.scm.persistence.manager.ScmZsjSupplierFirstManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * <pre> 
 * 描述：供应商首营记录表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmZsjSupplierFirst/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmZsjSupplierFirstController 供应商首营记录")
public class ScmZsjSupplierFirstController extends BaseController{
	@Resource
	ScmZsjSupplierFirstManager scmZsjSupplierFirstManager;
	@Resource
	UCFeignService ucFeignService;
	@Resource
	ScmZsjSupplierFirstDao scmZsjSupplierFirstDao;
	
	/**
	 * 供应商首营记录表列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="供应商首营记录表数据列表", httpMethod = "POST", notes = "获取供应商首营记录表列表,query中必须添加货主ID筛选查询")
	public PageList<ScmZsjSupplierFirst> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmZsjSupplierFirstManager.query(queryFilter);
	}
	
	/**
	 * 供应商首营记录表明细页面
	 * 在供应商实体类 与mapper文件中添加一对多与一对多关系,委托书未生成
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="供应商首营记录表数据详情",httpMethod = "GET",notes = "供应商首营记录表数据详情")
	public ScmZsjSupplierFirst get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmZsjSupplierFirstManager.get(id);
	}
	
    /**
	 * 新增供应商首营记录表
	 * 暂存时先判断供应商是否存在,不存在保存,存在则跳过保存
	 * @param scmZsjSupplierFirst
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新供应商首营记录表数据", httpMethod = "POST", notes = "新增,更新供应商首营记录表数据")
	public CommonResult<String> save(@ApiParam(name="scmZsjSupplierFirst",value="供应商首营记录表业务对象", required = true)@RequestBody ScmZsjSupplierFirst scmZsjSupplierFirst) throws Exception{
		String msg = "添加供应商首营记录表成功";
		if(StringUtil.isEmpty(scmZsjSupplierFirst.getSupplierId())) {
			throw new RuntimeException("请选择供应商后暂存");
		}
		if(StringUtil.isEmpty(scmZsjSupplierFirst.getId())){
			scmZsjSupplierFirstManager.isSave(scmZsjSupplierFirst);
			scmZsjSupplierFirst.setApprovalState("0");
			scmZsjSupplierFirstManager.create(scmZsjSupplierFirst);
		}else{
			scmZsjSupplierFirst.setApprovalState("0");
			scmZsjSupplierFirstManager.update(scmZsjSupplierFirst);
			msg = "更新供应商首营记录表成功";
		}
		return new CommonResult<String>(true,msg,scmZsjSupplierFirst.getId());
	}
	
	/**
	 * 删除供应商首营记录表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除供应商首营记录表记录", httpMethod = "DELETE", notes = "删除供应商首营记录表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmZsjSupplierFirstManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除供应商首营记录表记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除供应商首营记录表记录", httpMethod = "DELETE", notes = "批量删除供应商首营记录表记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmZsjSupplierFirstManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
	
	/**
	 * @param 供应商首营申请
	 * 在供应商首营类中添加供应商对象,在提交审批时先判断供应商是否存在,不存在保存,存在则跳过保存
	 * @param request
	 * @return
	 * @throws Exception
	 * flowKey:审批类型
	 * sysCode:系统别名
	 * instanceIdField:与实体类审批ID相同,controller层接到对象会有审批ID,处理业务逻辑后,保存对象即可
	 * varKeys:脚本使用参数
	 */
	@PostMapping(value = "sendApply")
    @ApiOperation(value = "供应商首营申请", httpMethod = "POST", notes = "在供应商首营类中添加供应商对象,在提交审批时先判断供应商是否存在,不存在保存,存在则跳过保存")
    @Workflow(flowKey = "gyssy", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {})
    public CommonResult<String> sendApply(
            @ApiParam(name = "scmZsjSupplierFirst", value = "供应商首营对象", required = true) @RequestBody ScmZsjSupplierFirst scmZsjSupplierFirst,
            HttpServletRequest request) throws Exception {
		JsonNode user = ucFeignService.getUser(current(), "");
		String fullname = user.get("fullname").asText();
		scmZsjSupplierFirst.setOperatorName(fullname);
		ScmZsjSupplierFirst scmZsjSupplierFirst2 = scmZsjSupplierFirstDao.get(scmZsjSupplierFirst.getId());
		if(scmZsjSupplierFirst2 == null) {
			scmZsjSupplierFirstManager.sendApply(scmZsjSupplierFirst);
		}else{
			scmZsjSupplierFirstManager.updateSendApply(scmZsjSupplierFirst);
		}
		return new CommonResult<String>(true,"供应商首营通过");
	}
	
    @PostMapping(value = "/endApply")
    @ApiOperation(value = "供应商首营审批流程通过", httpMethod = "POST", notes = "供应商首营审批流程通过,修改审批状态")
    public void endApply(@ApiParam(name = "params", value = "流程事件参数", required = true) @RequestBody String params, 
    		HttpServletRequest request) throws Exception {
    	JsonNode jsonNode = JsonUtil.toJsonNode(params);
    	scmZsjSupplierFirstManager.endApply(jsonNode);
    }
    
	/**
	 * 根据审批ID获取详细数据
	 * 在供应商实体类 与mapper文件中添加一对多与一对多关系,委托书未生成
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/getSupplierFirstByApprovalId/{approvalId}")
	@ApiOperation(value="根据审批ID获取详细数据",httpMethod = "GET",notes = "根据审批ID获取详细数据")
	public CommonResult<ScmZsjSupplierFirst> getSupplierFirstByApprovalId(@ApiParam(name="approvalId",value="审批ID", required = true)@PathVariable String approvalId) throws Exception{
		ScmZsjSupplierFirst ScmZsjSupplierFirst = scmZsjSupplierFirstManager.getSupplierFirstByApprovalId(approvalId);
		return new CommonResult<ScmZsjSupplierFirst>(true,"获取成功",ScmZsjSupplierFirst);
	}
	
	/**
	 * @param 商业首营申请
	 * 需要调整保存方法,添加委托书数据保存
	 * 在首营类中添加商业数据对象,发起审批时先判断商业数据是否存在,不存在进行保存,存在不保存
	 * @param request
	 * @return
	 * @throws Exception
	 * flowKey:审批类型
	 * sysCode:系统别名
	 * instanceIdField:与实体类审批ID相同,controller层接到对象会有审批ID,处理业务逻辑后,保存对象即可
	 * varKeys:脚本使用参数
	 */
	@PostMapping(value = "updateSendApply")
    @ApiOperation(value = "商业修改首营", httpMethod = "POST", notes = "商业修改首营")
    @Workflow(flowKey = "syxgsy", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {})
    public CommonResult<ScmZsjSupplierFirst> updateSendApply(
            @ApiParam(name = "scmZsjCommerceFirst", value = "商业首营对象", required = true) @RequestBody ScmZsjSupplierFirst scmZsjSupplierFirst,
            HttpServletRequest request) throws Exception {
		JsonNode user = ucFeignService.getUser(current(), "");
		String fullname = user.get("fullname").asText();
		scmZsjSupplierFirst.setOperatorName(fullname);
		scmZsjSupplierFirstManager.updateSendApply(scmZsjSupplierFirst);
		return new CommonResult<ScmZsjSupplierFirst>(true, "审批发起成功");
	}
    
	
	
}

