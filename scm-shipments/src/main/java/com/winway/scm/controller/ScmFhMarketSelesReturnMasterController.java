package com.winway.scm.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.annotation.Workflow;
import com.hotent.base.controller.BaseController;
import com.hotent.base.feign.UCFeignService;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.JsonUtil;
import com.hotent.base.util.StringUtil;
import com.winway.purchase.vo.ReturnWmsVo;
import com.winway.purchase.vo.ScmWmsReceipt;
import com.winway.scm.model.ScmFhMarketSelesReturn;
import com.winway.scm.model.ScmFhMarketSelesReturnMaster;
import com.winway.scm.persistence.manager.ScmFhMarketSelesReturnMasterManager;
import com.winway.scm.vo.SalesReturnRecord;
import com.winway.scm.vo.WmsfindcgrkInVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * <pre>
 * 描述：销售退货主表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-13 15:47:33
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value = "/scm/scmFhMarketSelesReturnMaster/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "scmFhMarketSelesReturnMasterController 商业退货总表")
public class ScmFhMarketSelesReturnMasterController extends BaseController {
    @Resource
    ScmFhMarketSelesReturnMasterManager scmFhMarketSelesReturnMasterManager;

    @Resource
    UCFeignService ucFeignService;

    /**
     * 销售退货主表列表(分页条件查询)数据
     *
     * @param
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/list")
    @ApiOperation(value = "销售退货主表数据列表", httpMethod = "POST", notes = "获取销售退货主表列表")
    public PageList<ScmFhMarketSelesReturnMaster> list(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
        return scmFhMarketSelesReturnMasterManager.query(queryFilter);
    }
    /**
     * 销售退货记录
     *
     * @param
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/salesReturnRecord")
    @ApiOperation(value = "销售退货记录", httpMethod = "POST", notes = "销售退货记录")
    public PageList<SalesReturnRecord> salesReturnRecord(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
    	return scmFhMarketSelesReturnMasterManager.salesReturnRecord(queryFilter);
    }

    /**
     * 销售退货主表明细页面
     *
     * @param id
     * @return
     * @throws Exception ModelAndView
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "销售退货主表数据详情", httpMethod = "GET", notes = "销售退货主表数据详情")
    public ScmFhMarketSelesReturnMaster get(@ApiParam(name = "id", value = "业务对象主键", required = true) @PathVariable String id) throws Exception {
        return scmFhMarketSelesReturnMasterManager.get(id);
    }


    /**
     * 销售退货审批页面
     *
     * @param
     * @return
     * @throws Exception ModelAndView
     */
    @GetMapping(value = "/detail/{approvalId}")
    @ApiOperation(value = "销售退货主表数据详情", httpMethod = "GET", notes = "销售退货主表数据详情")
    public ScmFhMarketSelesReturnMaster getByApprovalId(@ApiParam(name = "id", value = "业务对象主键", required = true) @PathVariable String approvalId) throws Exception {
        return scmFhMarketSelesReturnMasterManager.getByApprovalId(approvalId);
    }

    /**
     * 新增销售退货主表
     *
     * @param scmFhMarketSelesReturnMaster
     * @return
     * @throws Exception
     * @throws
     */
    @PostMapping(value = "save")
    @ApiOperation(value = "新增,更新销售退货主表数据", httpMethod = "POST", notes = "新增,更新销售退货主表数据")
    public CommonResult<ScmFhMarketSelesReturnMaster> save(@ApiParam(name = "scmFhMarketSelesReturnMaster", value = "销售退货主表业务对象", required = true) @RequestBody ScmFhMarketSelesReturnMaster scmFhMarketSelesReturnMaster) throws Exception {
        String msg = "添加销售退货主表成功";
        JsonNode user = ucFeignService.getUser(current(), "");
        String fullname = user.get("fullname").asText();
        scmFhMarketSelesReturnMaster.setCreatePersion(fullname);
        scmFhMarketSelesReturnMaster.setApprovalState("0");
        scmFhMarketSelesReturnMaster.setCreateDate(new Date());
        if (StringUtil.isEmpty(scmFhMarketSelesReturnMaster.getId())) {
            scmFhMarketSelesReturnMasterManager.create(scmFhMarketSelesReturnMaster);
        } else {
            scmFhMarketSelesReturnMasterManager.update(scmFhMarketSelesReturnMaster);
            msg = "更新销售退货主表成功";
        }
        return new CommonResult<ScmFhMarketSelesReturnMaster>(true, msg, scmFhMarketSelesReturnMaster);
    }

    /**
     * 删除销售退货主表记录
     *
     * @param id
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "remove/{id}")
    @ApiOperation(value = "删除销售退货主表记录", httpMethod = "DELETE", notes = "删除销售退货主表记录")
    public CommonResult<String> remove(@ApiParam(name = "id", value = "业务主键", required = true) @PathVariable String id) throws Exception {
        scmFhMarketSelesReturnMasterManager.remove(id);
        return new CommonResult<String>(true, "删除成功");
    }

    /**
     * 批量删除销售退货主表记录
     *
     * @param ids
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "/removes")
    @ApiOperation(value = "批量删除销售退货主表记录", httpMethod = "DELETE", notes = "批量删除销售退货主表记录")
    public CommonResult<String> removes(@ApiParam(name = "ids", value = "业务主键数组,多个业务主键之间用逗号分隔", required = true) @RequestParam String... ids) throws Exception {
        scmFhMarketSelesReturnMasterManager.removeByIds(ids);
        return new CommonResult<String>(true, "批量删除成功");
    }


    /**
     * 发起申请
     *
     * @param scmFhMarketSelesReturnMaster
     * @return
     * @throws Exception
     * @throws
     */
    @PostMapping(value = "sendApply")
    @ApiOperation(value = "商业退货发起申请", httpMethod = "POST", notes = "发起申请时需要将退货商品封装到主表 一并提交")
//    @Workflow(flowKey = "sythsp", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {})
    public CommonResult<ScmFhMarketSelesReturnMaster> sendApply(@ApiParam(name = "scmFhMarketSelesReturnMaster", value = "销售退货主表业务对象", required = true) @RequestBody ScmFhMarketSelesReturnMaster scmFhMarketSelesReturnMaster) throws Exception {
        JsonNode user = ucFeignService.getUser(current(), "");
        String fullname = user.get("fullname").asText();
        scmFhMarketSelesReturnMaster.setCreatePersion(fullname);
        
        scmFhMarketSelesReturnMaster.setCreateDate(new Date());
        ScmFhMarketSelesReturnMaster master = scmFhMarketSelesReturnMasterManager.sendApply(scmFhMarketSelesReturnMaster);
        return new CommonResult<ScmFhMarketSelesReturnMaster>(true,"商业退货审批发起成功",master);
    }

    /**
     * 结束审批
     *
     * @param params
     * @param request
     * @throws Exception
     */
    @PostMapping(value = "/endApply")
    @ApiOperation(value = "商业退货审批通过及驳回", httpMethod = "POST", notes = "退货审批通过及驳回")
    public void endApply(@ApiParam(name = "params", value = "流程事件参数", required = true) @RequestBody String params,
                         HttpServletRequest request) throws Exception {
        JsonNode jsonNode = JsonUtil.toJsonNode(params);
        scmFhMarketSelesReturnMasterManager.endApply(jsonNode);
    }
    
	/**
	 * 商业退货回执
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/receipt")
	@ApiOperation(value="商业退货回执", httpMethod = "POST", notes = "商业退货回执")
	public ReturnWmsVo receipt(@ApiParam(name="ScmWmsReceipt",value="查询对象")@RequestBody ScmWmsReceipt scmWmsReceipt) throws Exception{
		if(scmWmsReceipt == null) {
			return new ReturnWmsVo("500","回执参数不能为空");
		}
		if(scmWmsReceipt.getOrderCode() == null || "".equals(scmWmsReceipt.getOrderCode())) {
			return new ReturnWmsVo("500","调拨单号不能为空");
		}
		if(scmWmsReceipt.getTypt() == null || "".equals(scmWmsReceipt.getTypt())) {
			return new ReturnWmsVo("500","状态码不能为空");
		}
		try{
			scmFhMarketSelesReturnMasterManager.receipt(scmWmsReceipt);
		}catch (Exception e) {
			return new ReturnWmsVo("500",e.getMessage());
		}
		return new ReturnWmsVo("200","处理成功");
	}
	/**
	 * 商业退货明细数据获取sap
	 * @param
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/marketSelesReturnDatail")
	@ApiOperation(value="sap根据商业退货单号获取商业退货明细", httpMethod = "POST", notes = "sap根据商业退货单号获取商业退货明细")
	public PageList<ScmFhMarketSelesReturn> marketSelesReturnDatail(@ApiParam(name="wmsfindcgrkInVo",value="订单号")@RequestBody WmsfindcgrkInVo wmsfindcgrkInVo) throws Exception{
		return scmFhMarketSelesReturnMasterManager.marketSelesReturnDatail(wmsfindcgrkInVo.getOrderCode());
	}

}
