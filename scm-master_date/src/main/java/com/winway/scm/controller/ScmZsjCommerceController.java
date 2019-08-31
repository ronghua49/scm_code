package com.winway.scm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.Date;
import java.util.List;

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
import org.bouncycastle.crypto.RuntimeCryptoException;
import org.springframework.http.MediaType;
import com.winway.scm.persistence.manager.ScmZsjCommerceManager;
import com.winway.purchase.util.QuarterUtil;
import com.winway.scm.model.ScmZsjCommerce;
import com.winway.scm.model.ScmZsjSupplierFirst;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
 * 描述：商业表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmZsjCommerce/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmZsjCommerceController 商业表")
public class ScmZsjCommerceController extends BaseController{
	@Resource
	ScmZsjCommerceManager scmZsjCommerceManager;
	@Resource
	UCFeignService ucFeignService;
	/**
	 * 商业表列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="商业表数据列表", httpMethod = "POST", notes = "获取商业表列表")
	public PageList<ScmZsjCommerce> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmZsjCommerceManager.query(queryFilter);
	}



	/**
	 * 商业表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="商业表数据详情",httpMethod = "GET",notes = "商业表数据详情")
	public ScmZsjCommerce get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmZsjCommerceManager.get(id);
	}
	/**
	 * 商业表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/getcommerceByCode/{commerceCode}")
	@ApiOperation(value="商业表数据详情",httpMethod = "GET",notes = "商业表数据详情")
	public String getcommerceByCode(@ApiParam(name="commerceCode",value="商业编码", required = true)@PathVariable String commerceCode) throws Exception{
		ScmZsjCommerce scmZsjCommerce = scmZsjCommerceManager.getcommerceByCode(commerceCode);
		String jsonString = JSON.toJSONString(scmZsjCommerce);
		return jsonString;
	}
	
	/**
	 * 商业表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/getByApprovalId/{approvalId}")
	@ApiOperation(value="商业表数据详情",httpMethod = "GET",notes = "商业表数据详情")
	public CommonResult<ScmZsjCommerce> getByApprovalId(@ApiParam(name="approvalId",value="业务对象主键", required = true)@PathVariable String approvalId) throws Exception{
		ScmZsjCommerce byApprovalId = scmZsjCommerceManager.getByApprovalId(approvalId);
		return new CommonResult<ScmZsjCommerce>(true,"获取成功",byApprovalId);
	}
	
	/**
	 * 商业表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@PostMapping(value="/acceptCommerceList")
	@ApiOperation(value="商业表数据详情",httpMethod = "POST",notes = "商业表数据详情")
	public CommonResult<List<ScmZsjCommerce>> acceptCommerceList(
			@ApiParam(name="ownerId",value="货主ID", required = true) @RequestParam String ownerId,
			@ApiParam(name="businessDivisionId",value="商务分区ID")@RequestParam(required = false) String businessDivisionId,
			@ApiParam(name="provinceId",value="省区ID")@RequestParam(required = false) String provinceId) throws Exception{
		List<ScmZsjCommerce> acceptCommerceList =  scmZsjCommerceManager.acceptCommerceList(ownerId,businessDivisionId,provinceId);
		return new CommonResult<List<ScmZsjCommerce>>(true,"获取成功",acceptCommerceList);
	}
	
	/**
	 * 商业表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/getcommerceById/{commerceId}")
	@ApiOperation(value="商业表数据详情",httpMethod = "GET",notes = "商业表数据详情")
	public String getcommerceById(@ApiParam(name="commerceId",value="业务对象主键", required = true)@PathVariable(value="commerceId") String commerceId) throws Exception{
		ScmZsjCommerce scmZsjCommerce = scmZsjCommerceManager.get(commerceId);
		if(scmZsjCommerce == null) {
			throw new RuntimeCryptoException("数据不存在");
		}
		String jsonString = JSON.toJSONString(scmZsjCommerceManager.get(commerceId));
		return jsonString;
	}
	
    /**
	 * 新增商业表
	 * @param scmZsjCommerce
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新商业表数据", httpMethod = "POST", notes = "新增,更新商业表数据")
	public CommonResult<String> save(@ApiParam(name="scmZsjCommerce",value="商业表业务对象", required = true)@RequestBody ScmZsjCommerce scmZsjCommerce) throws Exception{
		String msg = "添加商业表成功";
		scmZsjCommerceManager.getByNameOrId(scmZsjCommerce);
		JsonNode user = ucFeignService.getUser(current(), "");
		String userName = user.get("fullname").asText();
		scmZsjCommerce.setEnterPersion(userName);
		scmZsjCommerce.setApprovalState("2");
		if(StringUtil.isEmpty(scmZsjCommerce.getId())){
			scmZsjCommerce.setFileCode(QuarterUtil.getCode("DA"));
			
			scmZsjCommerceManager.create(scmZsjCommerce);
		}else{
			
			scmZsjCommerceManager.update(scmZsjCommerce);
			msg = "更新商业表成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除商业表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除商业表记录", httpMethod = "DELETE", notes = "删除商业表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmZsjCommerceManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除商业表记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除商业表记录", httpMethod = "DELETE", notes = "批量删除商业表记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmZsjCommerceManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
	
	/**
	 * 商业表列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/firstList")
	@ApiOperation(value="商业表首营数据列表", httpMethod = "POST", notes = "获取商业表列表")
	public PageList<ScmZsjCommerce> firstList(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmZsjCommerceManager.firstList(queryFilter);
	}
	
	/**
	 * 商业首营成功和商业协议合作申请成功联查
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/agreementSendApplySuccessList")
	@ApiOperation(value="商业表首营数据列表", httpMethod = "POST", notes = "获取商业表列表")
	public PageList<ScmZsjCommerce> agreementSendApplySuccessList(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmZsjCommerceManager.agreementSendApplySuccessList(queryFilter);
	}
	
	/**
	 * 商业信息下拉框,带首营状态
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@GetMapping("/downBox/{ownerId}")
	@ApiOperation(value="商业表首营数据列表", httpMethod = "GET", notes = "获取商业表列表")
	public List<ScmZsjCommerce> downBox(@ApiParam(name="ownerId",value="货主ID",required = true)@PathVariable String ownerId) throws Exception{
		return scmZsjCommerceManager.downBox(ownerId);
	}
	/**
	 * 商业信息下拉框,首营审批通过
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@GetMapping("/downBoxApplySuccess/{ownerId}")
	@ApiOperation(value="商业信息下拉框,首营审批通过", httpMethod = "GET", notes = "商业信息下拉框,首营审批通过")
	public List<ScmZsjCommerce> downBoxApplySuccess(@ApiParam(name="ownerId",value="货主ID",required = true)@PathVariable String ownerId,@RequestParam(required = false) String businessDivisionId) throws Exception{
		return scmZsjCommerceManager.downBoxApplySuccess(ownerId,businessDivisionId);
	}

	/**
	 * 商业+认可状态+责任人
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/listCommerceAcceptState")
	@ApiOperation(value="商业+认可状态+责任人", httpMethod = "POST", notes = "商业+认可状态+责任人")
	public PageList<ScmZsjCommerce> listCommerceAcceptState(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmZsjCommerceManager.listCommerceAcceptState(queryFilter);
	}
	/**
	 * 商业认可审批数据列表
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/listCommerceAcceptApprova")
	@ApiOperation(value="商业认可审批数据列表", httpMethod = "POST", notes = "商业认可审批数据列表")
	public PageList<ScmZsjCommerce> listCommerceAcceptApprova(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmZsjCommerceManager.listCommerceAcceptApprova(queryFilter);
	}
	/**
	 * 商业认可暂存数据列表
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/listCommerceAcceptTS")
	@ApiOperation(value="商业认可暂存数据列表", httpMethod = "POST", notes = "商业认可暂存数据列表")
	public PageList<ScmZsjCommerce> listCommerceAcceptTS(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmZsjCommerceManager.listCommerceAcceptTS(queryFilter);
	}
	
	/**
	 * 商业认可暂存数据列表
	 * @param
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/findByOwnerId")
	@ApiOperation(value="根据货主Id查询商业表数据列表", httpMethod = "POST", notes = "根据货主Id查询商业表数据列表")
	public String findByOwnerId(@ApiParam(name="ownerId",value="货主Id")@RequestParam String ownerId) throws Exception{
		String result=scmZsjCommerceManager.findByOwnerId(ownerId);
		return result;
	}
	
	/**
	 * @param 商业申请
	 * @param request
	 * @return
	 * @throws Exception
	 * flowKey:审批类型
	 * sysCode:系统别名
	 * instanceIdField:与实体类审批ID相同,controller层接到对象会有审批ID,处理业务逻辑后,保存对象即可
	 * varKeys:脚本使用参数
	 */
	@PostMapping(value = "sendApply")
    @ApiOperation(value = "商业申请", httpMethod = "POST", notes = "商业申请")
//    @Workflow(flowKey = "sy", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {})
    public CommonResult<String> sendApply(
            @ApiParam(name = "ScmZsjCommerce", value = "商业对象", required = true) @RequestBody ScmZsjCommerce scmZsjCommerce,
            HttpServletRequest request) throws Exception {
		JsonNode user = ucFeignService.getUser(current(), "");
		String userName = user.get("fullname").asText();
		scmZsjCommerce.setEnterPersion(userName);
		scmZsjCommerce.setEnterDate(new Date());
		scmZsjCommerceManager.sendApply(scmZsjCommerce);
		return new CommonResult<String>(true,"商业审批发起成功");
	}
	
    @PostMapping(value = "/endApply")
    @ApiOperation(value = "商业申请审批流程通过", httpMethod = "POST", notes = "商业申请审批流程通过,修改审批状态")
    public void endApply(@ApiParam(name = "params", value = "流程事件参数", required = true) @RequestBody String params, 
    		HttpServletRequest request) throws Exception {
    	JsonNode jsonNode = JsonUtil.toJsonNode(params);
    	scmZsjCommerceManager.endApply(jsonNode);
    }
    
	/**
	 * 商业数据修改同步
	 * @param
	 * @return
	 * @throws Exception
	 * ModelAndView
	 */
    @GetMapping(value="/updateSyn/{id}")
	@ApiOperation(value="商业数据修改同步",httpMethod = "GET",notes = "商业数据修改同步")
	public CommonResult<String> updateSyn(@ApiParam(name="id",value="商品主键")@PathVariable String id) throws Exception{
		scmZsjCommerceManager.updateSyn(id);
		return new CommonResult<>(true, "同步成功");
	}


	/**
	 * 返回商业首营和商业数据
	 * @param
	 * @return
	 * @throws Exception
	 * ModelAndView
	 */
	@GetMapping(value="/getFirstList/{commerceCode}/{ownerId}")
	@ApiOperation(value="返回商业首营和商业数据",httpMethod = "GET",notes = "返回商业首营和商业数据")
	public String getcommerceFirstByCode(@ApiParam(name="id",value="商品编码")@PathVariable String commerceCode,
			@ApiParam(name="ownerId",value="商品编码")@PathVariable String ownerId) throws Exception{
		ScmZsjCommerce zsjCommerce = scmZsjCommerceManager.getcommerceFirstByCode(commerceCode,ownerId);
		return  JSON.toJSONString(zsjCommerce);
	}


	/**
	 * 返回商业数据
	 * @param
	 * @return
	 * @throws Exception
	 * ModelAndView
	 */
	@PostMapping(value="/getcommerceByName")
	@ApiOperation(value="返回商业",httpMethod = "POST",notes = "返回商业")
	public String getcommerceByName(@ApiParam(name="commerceName",value="商业名") @RequestParam(value="commerceName") String commerceName) throws Exception{
		ScmZsjCommerce zsjCommerce = scmZsjCommerceManager.getcommerceByName(commerceName);
		return  JSON.toJSONString(zsjCommerce);
	}


}
