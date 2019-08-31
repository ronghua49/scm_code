package com.winway.scm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.annotation.Workflow;
import com.hotent.base.controller.BaseController;
import com.hotent.base.feign.UCFeignService;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.FieldRelation;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.query.QueryOP;
import com.hotent.base.util.JsonUtil;
import com.hotent.base.util.StringUtil;
import com.winway.purchase.vo.ReturnWmsVo;
import com.winway.purchase.vo.ScmWmsReceipt;
import com.winway.scm.model.ScmFhShipmentsDatail;
import com.winway.scm.model.ScmFhShipmentsDatailMaster;
import com.winway.scm.persistence.manager.ScmFhShipmentsDatailMasterManager;
import com.winway.scm.vo.SalesRecordVo;
import com.winway.scm.vo.ScmFhShipmentsDataiAndMessageVo;
import com.winway.scm.vo.ScmWmsShipReceipt;
import com.winway.scm.vo.ShipmentsDatailVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * <pre> 
 * 描述：发货明细总表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-02-15 17:31:50
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmFhShipmentsDatailMaster/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmFhShipmentsDatailMasterController 发货明细总表")
public class ScmFhShipmentsDatailMasterController extends BaseController{
	@Resource
	ScmFhShipmentsDatailMasterManager scmFhShipmentsDatailMasterManager;
	@Resource
	UCFeignService ucFeignService;
	/**
	 * 当此退货申请查询 接口
	 * @param
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list/filter")
	@ApiOperation(value="发货明细总表数据列表", httpMethod = "POST", notes = "获取发货明细总表列表")
	public PageList<ScmFhShipmentsDatailMaster> listReturn(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		PageList<ScmFhShipmentsDatailMaster> scmFhShipmentsDatailMasterPageList = scmFhShipmentsDatailMasterManager.queryReturnVo(queryFilter);
		return scmFhShipmentsDatailMasterPageList;
	}

    /**
     * 发货明细总表 带详情发货商品明细(分页条件查询)数据
     * @param
     * @return
     * @throws Exception
     * PageJson
     * @exception
     */
    @PostMapping("/list")
    @ApiOperation(value="发货明细总表数据列表", httpMethod = "POST", notes = "获取发货明细总表列表")
    public PageList<ScmFhShipmentsDatailMaster> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
    	//查询可开票的发货单
//    	queryFilter.addFilter("canInvoice","0", QueryOP.NOT_EQUAL, FieldRelation.AND);
        return scmFhShipmentsDatailMasterManager.query(queryFilter);
    }
	
    /**
     * 发货明细总表 带详情发货商品明细(分页条件查询)数据
     * @param
     * @return
     * @throws Exception
     * PageJson
     * @exception
     */
    @PostMapping("/salesRecord")
    @ApiOperation(value="药品销售记录", httpMethod = "POST", notes = "药品销售记录")
    public PageList<SalesRecordVo> salesRecord(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
    	return scmFhShipmentsDatailMasterManager.salesRecord(queryFilter);
    }
    
	/**
	 * 发货详细数据列表
	 * @param
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/allShipmentMessage")
	@ApiOperation(value="发货详细数据列表", httpMethod = "POST", notes = "发货详细数据列表")
	public PageList<ScmFhShipmentsDataiAndMessageVo> allShipmentMessage(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmFhShipmentsDatailMasterManager.allShipmentMessage(queryFilter);
	}
	
	/**
	 * 发货明细总表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="发货明细总表数据详情",httpMethod = "GET",notes = "发货明细总表数据详情")
	public ScmFhShipmentsDatailMaster get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmFhShipmentsDatailMasterManager.get(id);
	}
	
	/**
	 * 修改开票状态
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/updateShipmentDatailCanInvoice/{id}/{type}")
	@ApiOperation(value="发货明细总表数据详情",httpMethod = "GET",notes = "发货明细总表数据详情")
	public CommonResult<String> updateShipmentDatailCanInvoice(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id,
			@ApiParam(name="type",value="开票状态", required = true)@PathVariable String type) throws Exception{
		try{
			scmFhShipmentsDatailMasterManager.updateShipmentDatailCanInvoice(id,type);
		}catch (Exception e) {
			return new CommonResult<String>(false,e.getMessage());
		}
		return new CommonResult<String>("处理成功");
	}
	
	/**
	 * 发货明细总表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/getShipmentsDatailMasterById/{id}")
	@ApiOperation(value="发货明细总表数据详情",httpMethod = "GET",notes = "发货明细总表数据详情")
	public String getShipmentsDatailMasterById(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		ScmFhShipmentsDatailMaster scmFhShipmentsDatailMaster = scmFhShipmentsDatailMasterManager.getShipmentsDatailMasterById(id);
		String jsonString = JSON.toJSONString(scmFhShipmentsDatailMaster);
		return jsonString;
	}
	
    /**
	 * 新增发货明细总表。
	 * @param scmFhShipmentsDatailMaster
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新发货明细总表数据", httpMethod = "POST", notes = "新增,更新发货明细总表数据")
	public CommonResult<String> save(@ApiParam(name="scmFhShipmentsDatailMaster",value="发货明细总表业务对象", required = true)@RequestBody ScmFhShipmentsDatailMaster scmFhShipmentsDatailMaster) throws Exception{
		if(scmFhShipmentsDatailMaster.getBusinessDivision() == null || "".equals(scmFhShipmentsDatailMaster.getBusinessDivision()) || "null".equals(scmFhShipmentsDatailMaster.getBusinessDivision())) {
			throw new RuntimeException("商务分区不能为空");
		}
		if(scmFhShipmentsDatailMaster.getCommerceId() == null || "".equals(scmFhShipmentsDatailMaster.getCommerceId()) || "null".equals(scmFhShipmentsDatailMaster.getCommerceId())) {
			throw new RuntimeException("商业不能为空");
		}
		String msg = "添加发货明细总表成功";
		scmFhShipmentsDatailMaster.setCreateDate(new Date());
		if(StringUtil.isEmpty(scmFhShipmentsDatailMaster.getId())){
			JsonNode user = ucFeignService.getUser(current(), "");
			String fullname = user.get("fullname").asText();
			scmFhShipmentsDatailMaster.setCreatePersion(fullname);
			scmFhShipmentsDatailMaster.setApprovalState("0");
			scmFhShipmentsDatailMasterManager.create(scmFhShipmentsDatailMaster);
		}else{
			scmFhShipmentsDatailMaster.setApprovalState("0");
			scmFhShipmentsDatailMasterManager.update(scmFhShipmentsDatailMaster);
			msg = "更新发货明细总表成功";
		}
		return new CommonResult<String>(true,msg,scmFhShipmentsDatailMaster.getId());
	}




	
	/**
	 * 删除发货明细总表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除发货明细总表记录", httpMethod = "DELETE", notes = "删除发货明细总表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmFhShipmentsDatailMasterManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除发货明细总表记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除发货明细总表记录", httpMethod = "DELETE", notes = "批量删除发货明细总表记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmFhShipmentsDatailMasterManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
	
	/**
	 * @param request
	 * @return
	 * @throws Exception
	 * flowKey:审批类型
	 * sysCode:系统别名
	 * instanceIdField:与实体类审批ID相同,controller层接到对象会有审批ID,处理业务逻辑后,保存对象即可
	 * varKeys:脚本使用参数
	 */
	@PostMapping(value = "/sendApply")
    @ApiOperation(value = "发货明细申请", httpMethod = "POST", notes = "提交时需要在scmFhShipmentsDatail对象中封装好发货明细对象提交")
//    @Workflow(flowKey = "fhzlsp", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {})
    public CommonResult<ScmFhShipmentsDatailMaster> sendApply(
            @ApiParam(name = "scmFhShipmentsDatail", value = "发货明细主表及子表数据", required = true) @RequestBody ScmFhShipmentsDatailMaster scmFhShipmentsDatailMaster,
            HttpServletRequest request) throws Exception {
		if(scmFhShipmentsDatailMaster.getScmFhShipmentsDatailList().size() == 0) {
			throw new RuntimeException("发货数据为空,不可发起申请");
		}
		ScmFhShipmentsDatail scmFhShipmentsDatail = scmFhShipmentsDatailMaster.getScmFhShipmentsDatailList().get(0);
		String houstWareCode = scmFhShipmentsDatail.getHoustWareCode();
		String marketType = scmFhShipmentsDatail.getMarketType();
		List<ScmFhShipmentsDatail> scmFhShipmentsDatailList = scmFhShipmentsDatailMaster.getScmFhShipmentsDatailList();
		for (ScmFhShipmentsDatail scmFhShipmentsDatail2 : scmFhShipmentsDatailList) {
			if(!scmFhShipmentsDatail2.getHoustWareCode().equals(houstWareCode)) {
				throw new RuntimeException("两个仓库不可同时发货");
			}
			if("特殊管理药品".equals(marketType)) {
				//特殊药品
				if(!scmFhShipmentsDatail2.equals(marketType)) {
					throw new RuntimeException("特殊管理药品不可与普通药品同时发货,请区分下单");
				}
			}else{
				//普通药品与麻黄碱
				if("特殊管理药品".equals(scmFhShipmentsDatail2.getMarketType())) {
					throw new RuntimeException("特殊管理药品不可与普通药品同时发货,请区分下单");
				}
			}
		}
		JsonNode user = ucFeignService.getUser(current(), "");
		String fullname = user.get("fullname").asText();
		scmFhShipmentsDatailMaster.setCreatePersion(fullname);
		scmFhShipmentsDatailMasterManager.sendApply(scmFhShipmentsDatailMaster);
		return new CommonResult<ScmFhShipmentsDatailMaster>(true, "审批发起成功", scmFhShipmentsDatailMaster);
	}

	/**
	 * 结束审批
	 * @param params
	 * @param request
	 * @throws Exception
	 */
    @PostMapping(value = "/endApply")
    @ApiOperation(value = "发货明细审批通过及驳回", httpMethod = "POST", notes = "发货明细审批通过及驳回")
    public void endApply(@ApiParam(name = "params", value = "流程事件参数", required = true) @RequestBody String params, 
    		HttpServletRequest request) throws Exception {
    	JsonNode jsonNode = JsonUtil.toJsonNode(params);
    	scmFhShipmentsDatailMasterManager.endApply(jsonNode);
    }

	/**
	 * 发货指令明细页面
	 * @param applyid 审批id
	 * @return
	 * @throws Exception
	 * ModelAndView
	 */
	@GetMapping(value="/getByApplyId/{applyid}")
	@ApiOperation(value="发货指令数据详情",httpMethod = "GET",notes = "发货指令数据详情")
	public CommonResult<ScmFhShipmentsDatailMaster> getByApplyId(@ApiParam(name="applyid",value="审批id", required = true)@PathVariable String applyid) throws Exception{
		return new CommonResult<ScmFhShipmentsDatailMaster>(true,"获取成功",scmFhShipmentsDatailMasterManager.getByApplyId(applyid));
	}

	/**
	 * 从wwms 获取发货单开票认可
	 * @param shipmentsCode
	 * @return
	 * @throws Exception
	 * ModelAndView
	 */
	@PostMapping(value="/canInvoice/{shipmentsCode:FHZL\\d+}")
	@ApiOperation(value="获取发货单开票认可",httpMethod = "POST",notes = "wms确认发货单后调此接口")
	public String canInvoice(@ApiParam(name="shipmentsCode",value="发货单号", required = true)@PathVariable String shipmentsCode) throws Exception{
		return scmFhShipmentsDatailMasterManager.canInvoice(shipmentsCode);
	}
	
	/**
	 * 发货单确认
	 * @param
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/affirm")
	@ApiOperation(value="发货单确认", httpMethod = "POST", notes = "发货单确认")
	public ReturnWmsVo affirm(@ApiParam(name="ScmWmsReceipt",value="查询对象")@RequestBody ScmWmsReceipt scmWmsReceipt) throws Exception{
		if(scmWmsReceipt == null) {
			return new ReturnWmsVo("500","回执参数不能为空");
		}
		if(scmWmsReceipt.getShipmentsCode() == null || "".equals(scmWmsReceipt.getShipmentsCode())) {
			return new ReturnWmsVo("500","发货单号不能为空");
		}
		try{
			scmFhShipmentsDatailMasterManager.affirm(scmWmsReceipt);
		}catch (Exception e) {
			return new ReturnWmsVo("500",e.getMessage());
		}
		return new ReturnWmsVo("200","处理成功");
	}
	
	/**
	 * 发货回执
	 * @param
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/receipt")
	@ApiOperation(value="发货通过回执", httpMethod = "POST", notes = "发货通过回执")
	public ReturnWmsVo receipt(@ApiParam(name="ScmWmsReceipt",value="查询对象")@RequestBody ScmWmsShipReceipt... scmWmsShipReceipt) throws Exception{
		for (ScmWmsShipReceipt scmWmsShipReceipt2 : scmWmsShipReceipt) {
			if(scmWmsShipReceipt == null) {
				return new ReturnWmsVo("500","回执参数不能为空");
			}
			if(scmWmsShipReceipt2.getOrderCode() == null || "".equals(scmWmsShipReceipt2.getOrderCode())) {
				return new ReturnWmsVo("500","调拨单号不能为空");
			}
			if(scmWmsShipReceipt2.getType() == null || "".equals(scmWmsShipReceipt2.getType())) {
				return new ReturnWmsVo("500","状态码不能为空");
			}
			try{
			}catch (Exception e) {
				return new ReturnWmsVo("500",e.getMessage());
			}
		}
		scmFhShipmentsDatailMasterManager.receipt(scmWmsShipReceipt);
		return new ReturnWmsVo("200","处理成功");
	}


	/**
	 * 根据商业和货主id获取 前年和去年月均发货金额
	 * @param commerceId
	 * @param ownerId
	 * @return
	 */
	@GetMapping("/getShipmentsHistory/{commerceId}/{ownerId}")
	public String getShipmentsHistory(@PathVariable(value = "commerceId")String commerceId, @PathVariable(value = "ownerId")String ownerId) throws ParseException {
		Map<String,Integer>  map = scmFhShipmentsDatailMasterManager.getShipmentsHistory(commerceId,ownerId);
		return  JSON.toJSONString(map);
	}


    /**
	 * 全部导出
	 * @param
	 * @return
	 * @throws Exception
	 * ModelAndView
	 */
	@PostMapping(value="/logistic/exportAll")
//	@ApiOperation(value="全部导出物流发运信息数据",httpMethod = "POST",notes = "全部导出物流发运信息数据")
	public void exportAll(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter, @ApiParam(name="response",value="响应对象") HttpServletResponse response) throws Exception{
		scmFhShipmentsDatailMasterManager.export(queryFilter,false,response);
	}


	/**
	 * 当页导出
	 * @param
	 * @return
	 * @throws Exception
	 * ModelAndView
	 */
	@PostMapping(value="/logistic/exportPage")
//	@ApiOperation(value="物流发运信息数据当页导出",httpMethod = "POST",notes = "物流发运信息数据当页导出")
	public void  exportPage(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter,@ApiParam(name="response",value="响应对象")HttpServletResponse response) throws Exception{
		scmFhShipmentsDatailMasterManager.export(queryFilter,true,response);
	}

	/**
	 * 全部导出
	 * @param
	 * @return
	 * @throws Exception
	 * ModelAndView
	 */
	@PostMapping(value="/daily/exportAll")
//	@ApiOperation(value="日发货明细全部导出",httpMethod = "POST",notes = "日发货明细全部导出")
	public void exportAll2(@RequestBody QueryFilter queryFilter,HttpServletResponse response) throws Exception{
		scmFhShipmentsDatailMasterManager.export2(queryFilter,false,response);
	}


	/**
	 * 当页导出
	 * @param
	 * @return
	 * @throws Exception
	 * ModelAndView
	 */
	@PostMapping(value="/daily/exportPage")
//	@ApiOperation(value="日发货明细当页导出",httpMethod = "POST",notes = "日发货明细当页导出")
	public void  exportPage2(@RequestBody QueryFilter queryFilter,HttpServletResponse response) throws Exception{
		scmFhShipmentsDatailMasterManager.export2(queryFilter,true,response);
	}






}
