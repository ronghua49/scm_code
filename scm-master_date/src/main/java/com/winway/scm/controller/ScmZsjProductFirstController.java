package com.winway.scm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.hotent.base.feign.UCFeignService;
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
import com.winway.purchase.feign.ScmMasterDateFeignService;
import com.winway.scm.model.ScmZsjProduct;
import com.winway.scm.model.ScmZsjProductFirst;
import com.winway.scm.persistence.dao.ScmZsjProductFirstDao;
import com.winway.scm.persistence.manager.ScmZsjProductFirstManager;
import com.winway.scm.persistence.manager.ScmZsjProductManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * <pre> 
 * 描述：商品首营记录表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmZsjProductFirst/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmZsjProductFirstController 商品首营记录")
public class ScmZsjProductFirstController extends BaseController{
	@Resource
	ScmZsjProductFirstManager scmZsjProductFirstManager;
	
	@Resource
	ScmZsjProductManager scmZsjProductManager;
	
	@Resource
	ScmZsjProductFirstDao scmZsjProductFirstDao;

	@Resource
	UCFeignService ucFeignService;



	/**
	 * 商品首营记录表列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="商品首营记录表数据列表", httpMethod = "POST", notes = "获取商品首营记录表列表")
	public PageList<ScmZsjProductFirst> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmZsjProductFirstManager.query(queryFilter);
	}
	
	/**
	 * 商品首营记录表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="商品首营记录表数据详情",httpMethod = "GET",notes = "商品首营记录表数据详情")
	public CommonResult<ScmZsjProductFirst> get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return new CommonResult<ScmZsjProductFirst>(true,"获取成功",scmZsjProductFirstManager.get(id));
	}
	
    /**
	 * 新增商品首营记录表
	 * 在商品首营类中加入商品对象
	 * 保存首营数据时判断是否存在该商品,如果存在则不保存,不存在则保存商品数据
	 * @param scmZsjProductFirst
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新商品首营记录表数据", httpMethod = "POST", notes = "新增,更新商品首营记录表数据")
	public CommonResult<String> save(@ApiParam(name="scmZsjProductFirst",value="商品首营记录表业务对象", required = true)@RequestBody ScmZsjProductFirst scmZsjProductFirst) throws Exception{
		String msg = "添加商品首营记录表成功";
		if(StringUtil.isEmpty(scmZsjProductFirst.getProductId())) {
			throw new RuntimeException("请选择商品后暂存");
		}
		JsonNode user = ucFeignService.getUser(current(), "");
		String fullname = user.get("fullname").asText();
		scmZsjProductFirst.setOperatorPerson(fullname);
		scmZsjProductFirst.setOperatorDate(new Date());
		if(StringUtil.isEmpty(scmZsjProductFirst.getId())){
			//判断当前商品在当前商业是否已经申请
			scmZsjProductFirstManager.isSave(scmZsjProductFirst);
			scmZsjProductFirst.setApprovalState("0");
//			scmZsjProductManager.isExist(scmZsjProductFirst.getScmZsjProduct());
			scmZsjProductFirstManager.create(scmZsjProductFirst);
		}else{
			scmZsjProductFirst.setApprovalState("0");
			scmZsjProductFirstManager.update(scmZsjProductFirst);
			msg = "更新商品首营记录表成功";
		}
		return new CommonResult<String>(true,msg,scmZsjProductFirst.getId());
	}
	
	/**
	 * 删除商品首营记录表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除商品首营记录表记录", httpMethod = "DELETE", notes = "删除商品首营记录表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmZsjProductFirstManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除商品首营记录表记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除商品首营记录表记录", httpMethod = "DELETE", notes = "批量删除商品首营记录表记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmZsjProductFirstManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
	
	/**
	 * @param 商品首营
	 * 在商品首营类中加入商品对象
	 * 商品数据在发起审批时判断是否存在该商品,如果存在则不保存,不存在则保存商品数据
	 * @param request
	 * @return
	 * @throws Exception
	 * flowKey:审批类型
	 * sysCode:系统别名
	 * instanceIdField:与实体类审批ID相同,controller层接到对象会有审批ID,处理业务逻辑后,保存对象即可
	 * varKeys:脚本使用参数
	 */
	@PostMapping(value = "/sendApply")
    @ApiOperation(value = "商品首营申请", httpMethod = "POST", notes = "在商品首营类中加入商品对象,商品数据在发起审批时判断是否存在该商品,如果存在则不保存,不存在则保存商品数据")
//    @Workflow(flowKey = "spsy", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {})
    public CommonResult<String> sendApply(
            @ApiParam(name = "scmZsjCommerceFirst", value = "采购合同对象", required = true) @RequestBody ScmZsjProductFirst scmZsjProductFirst,
            HttpServletRequest request) throws Exception {
		ScmZsjProductFirst scmZsjProductFirst2 = scmZsjProductFirstDao.get(scmZsjProductFirst.getId());
		JsonNode user = ucFeignService.getUser(current(), "");
		String fullname = user.get("fullname").asText();
		scmZsjProductFirst.setOperatorPerson(fullname);
		scmZsjProductFirst.setOperatorDate(new Date());
		if(scmZsjProductFirst2 == null) {
			scmZsjProductFirstManager.sendApply(scmZsjProductFirst, "spsy");
		}else{
			scmZsjProductFirstManager.updateSendApply(scmZsjProductFirst, "spsy");
		}
		return new CommonResult<String>(true, "商品首营申请提交成功");
	}


	/**
	 *  商品首映编辑发起申请
	 * @param scmZsjProductFirst
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/sendApplyEdit")
	@ApiOperation(value = "商品首营申请", httpMethod = "POST", notes = "在商品首营类中加入商品对象,商品数据在发起审批时判断是否存在该商品,如果存在则不保存,不存在则保存商品数据")
//	@Workflow(flowKey = "spsyxgsq", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {})
	public CommonResult<String> sendApplyEdit(
			@ApiParam(name = "scmZsjCommerceFirst", value = "采购合同对象", required = true) @RequestBody ScmZsjProductFirst scmZsjProductFirst,
			HttpServletRequest request) throws Exception {
		ScmZsjProductFirst scmZsjProductFirst2 = scmZsjProductFirstDao.get(scmZsjProductFirst.getId());
		JsonNode user = ucFeignService.getUser(current(), "");
		String fullname = user.get("fullname").asText();
		scmZsjProductFirst.setOperatorPerson(fullname);
		scmZsjProductFirst.setOperatorDate(new Date());
		if(scmZsjProductFirst2 == null) {
			scmZsjProductFirstManager.sendApply(scmZsjProductFirst, "spsyxgsq");
		}else{
			scmZsjProductFirstManager.updateSendApply(scmZsjProductFirst, "spsyxgsq");
		}
		return new CommonResult<String>(true, "商品首营申请提交成功");
	}

	
    @PostMapping(value = "/endApply")
    @ApiOperation(value = "商品首营审批流程通过", httpMethod = "POST", notes = "商品首营审批流程通过,修改审批状态")
    public void endApply(@ApiParam(name = "params", value = "流程事件参数", required = true) @RequestBody String params, 
    		HttpServletRequest request) throws Exception {
    	JsonNode jsonNode = JsonUtil.toJsonNode(params);
    	scmZsjProductFirstManager.endApply(jsonNode);
    }
    
	/**
	 * 根据审批ID获取详细数据
	 * 在供应商实体类 与mapper文件中添加一对多与一对多关系,委托书未生成
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/getProductFirstByApprovalId/{approvalId}")
	@ApiOperation(value="根据审批ID获取详细数据",httpMethod = "GET",notes = "根据审批ID获取详细数据")
	public CommonResult<ScmZsjProductFirst> getProductFirstByApprovalId(@ApiParam(name="approvalId",value="审批ID", required = true)@PathVariable String approvalId) throws Exception{
		ScmZsjProductFirst scmZsjProductFirst = scmZsjProductFirstManager.getProductFirstByApprovalId(approvalId);
		return new CommonResult<ScmZsjProductFirst>(true,"获取成功",scmZsjProductFirst);
	}
    
	/**
	 * @param
	 * @param request
	 * @return
	 * @throws Exception
	 * flowKey:审批类型
	 * sysCode:系统别名
	 * instanceIdField:与实体类审批ID相同,controller层接到对象会有审批ID,处理业务逻辑后,保存对象即可
	 * varKeys:脚本使用参数
	 */
	@PostMapping(value = "updateSendApply")
    @ApiOperation(value = "商品修改首营", httpMethod = "POST", notes = "商品修改首营")
//    @Workflow(flowKey = "spsy", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {})
    public CommonResult<String> updateSendApply(
            @ApiParam(name = "scmZsjProductFirst", value = "商品对象", required = true) @RequestBody ScmZsjProductFirst scmZsjProductFirst,
            HttpServletRequest request) throws Exception {
		scmZsjProductFirstManager.updateSendApply(scmZsjProductFirst, "spsy");
		return new CommonResult<String>(true, "审批发起成功");
	}
	
    /**
     * 停用启用接口
     * @param
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/startOrStop/{id}")
    @ApiOperation(value = "停用启用接口", httpMethod = "POST", notes = "停用启用接口")
    public CommonResult<String> startOrStop(@ApiParam(name = "id", value = "查询对象") @PathVariable String id) throws Exception {
    	String type = scmZsjProductFirstManager.startOrStop(id);
    	return new CommonResult<String>(true, "停用成功");
    }
	
    

}
