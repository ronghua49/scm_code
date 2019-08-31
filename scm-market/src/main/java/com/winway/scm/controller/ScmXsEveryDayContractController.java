package com.winway.scm.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
import com.hotent.base.query.QueryOP;
import com.hotent.base.util.JsonUtil;
import com.winway.purchase.persistence.manager.impl.WorkflowTemplate;
import com.winway.purchase.util.QuarterUtil;
import com.winway.scm.model.ScmXsEveryDayContract;
import com.winway.scm.persistence.manager.ScmXsEveryDayContractManager;
import com.winway.scm.vo.ScmXsBigContractProductByUpdateVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <pre>
 * 描述：日常合同 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:44
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value = "/scm/scmXsEveryDayContract/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "scmXsEveryDayContractController 日常合同")
public class ScmXsEveryDayContractController extends BaseController {

    @Resource
    ScmXsEveryDayContractManager scmXsEveryDayContractManager;
    @Resource
    UCFeignService ucFeignService;


    /**
     * 日常合同列表(分页条件查询)数据
     *
     * @return
     * @throws Exception PageJson
     * @throws
     * @paramrequest
     */
    @PostMapping("/list")
    @ApiOperation(value = "日常合同数据列表", httpMethod = "POST", notes = "获取日常合同列表,query必须添加货主ID进行筛选")
    public PageList<ScmXsEveryDayContract> list(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
        queryFilter.addFilter("isDelete", "0", QueryOP.EQUAL);
        return scmXsEveryDayContractManager.query(queryFilter);
    }

    /**
     * 日常合同明细页面
     *
     * @param id
     * @return
     * @throws Exception ModelAndView
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "日常合同数据详情", httpMethod = "GET", notes = "日常合同数据详情")
    public ScmXsEveryDayContract get(@ApiParam(name = "id", value = "业务对象主键", required = true) @PathVariable String id) throws Exception {
        return scmXsEveryDayContractManager.get(id);
    }
    /**
     * 根据审批ID获取数据详情
     *
     * @param id
     * @return
     * @throws Exception ModelAndView
     */
    @GetMapping(value = "/getByApplyId/{applyId}")
    @ApiOperation(value = "日常合同数据详情", httpMethod = "GET", notes = "日常合同数据详情")
    public ScmXsEveryDayContract getByApplyId(@ApiParam(name = "applyId", value = "业务对象主键", required = true) @PathVariable String applyId) throws Exception {
    	return scmXsEveryDayContractManager.getByApplyId(applyId);
    }

    /**
     * 获取合同编号 用户进入申请页面后通过ajax获取到此ID展示到页面
     *
     * @param
     * @return
     * @throws Exception ModelAndView
     */
    @GetMapping("/getContracteCode")
    @ApiOperation(value = "获取合同ID", httpMethod = "GET", notes = "获取合同ID")
    public String getContracteCode() throws Exception {
        return QuarterUtil.getCode("RCHT");
    }
    /**
     * 新增日常合同 这里需要注意  添加合同的时候同时要
     * 添加日常合同商品列表
     *
     * @param scmXsEveryDayContract
     * @return
     * @throws Exception
     * @throws
     */
    @PostMapping(value = "save")
    @ApiOperation(value = "新增,更新日常合同数据", httpMethod = "POST", notes = "新增,更新日常合同数据")
    public CommonResult<String> save(@ApiParam(name = "scmXsEveryDayContract", value = "日常合同业务对象", required = true) @RequestBody ScmXsEveryDayContract scmXsEveryDayContract) throws Exception {
        JsonNode user = ucFeignService.getUser(current(), "");
        String fullname = user.get("fullname").asText();
        scmXsEveryDayContract.setEntryPeople(fullname);
        scmXsEveryDayContract.setEntryDate(new Date());
        scmXsEveryDayContract.setApprovalState("0");
        String msg = scmXsEveryDayContractManager.save(scmXsEveryDayContract);
        return new CommonResult<String>(true,msg,scmXsEveryDayContract.getId());
    }
    /**
     * 删除日常合同记录
     *
     * @param id
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "remove/{id}")
    @ApiOperation(value = "删除日常合同记录", httpMethod = "DELETE", notes = "删除日常合同记录")
    public CommonResult<String> remove(@ApiParam(name = "id", value = "业务主键", required = true) @PathVariable String id) throws Exception {
        scmXsEveryDayContractManager.remove(id);
        return new CommonResult<String>(true, "删除成功");
    }
    /**
     * 批量删除日常合同记录
     *
     * @param ids
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "/removes")
    @ApiOperation(value = "批量删除日常合同记录", httpMethod = "DELETE", notes = "批量删除日常合同记录")
    public CommonResult<String> removes(@ApiParam(name = "ids", value = "业务主键数组,多个业务主键之间用逗号分隔", required = true) @RequestParam String... ids) throws Exception {
        scmXsEveryDayContractManager.removeByIds(ids);
        return new CommonResult<String>(true, "批量删除成功");
    }
    /**
     * @param
     * @return
     * @throws Exception flowKey:审批类型
     *                   sysCode:系统别名
     *                   instanceIdField:与实体类审批ID相同,controller层接到对象会有审批ID,处理业务逻辑后,保存对象即可
     *                   varKeys:脚本使用参数
     * @param日常合同流程申请
     */
    @PostMapping(value = "sendApply")
    @ApiOperation(value = "日常合同流程申请", httpMethod = "POST", notes = "选择经销商后自动展示合同下拉框,需要选择,用于减免合同"
            + "理由,展示协议商品列表,需要签订的输入签订数量,保存时需要把商品列表封装至日常合同对象一起提交")                
//    @Workflow(flowKey = "rchtsp", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {"totalPrice","creditPrice","businessDivisionId"})
    public CommonResult<String> sendApply(
            @ApiParam(name = "scmXsEveryDayContract", value = "经销商协议合作名单总表对象", required = true) @RequestBody ScmXsEveryDayContract scmXsEveryDayContract) throws Exception {
        try{
	    	JsonNode user = ucFeignService.getUser(current(), "");
	        String fullname = user.get("fullname").asText();
	        scmXsEveryDayContract.setEntryPeople(fullname);
	        scmXsEveryDayContract.setEntryDate(new Date());
	        scmXsEveryDayContract.setApprovalState("1");
	        scmXsEveryDayContractManager.sendApply(scmXsEveryDayContract);
        }catch(Exception e) {
        	throw new RuntimeException(e.getMessage());
        }
        return new CommonResult<String>(true, "审批发起成功",scmXsEveryDayContract.getId());
        
    }

    /**
     * 结束审批日常合同，改变合同状态，添加发货任务
     * @param params
     * @throws Exception
     */
    @PostMapping(value = "endApply")
    @ApiOperation(value = "日常合同审批通过", httpMethod = "POST", notes = "改变合同审批状态，添加到发货任务")
    public void endApply(@ApiParam(name = "params", value = "流程事件参数", required = true) @RequestBody String params) throws Exception {
        JsonNode jsonNode = JsonUtil.toJsonNode(params);
         scmXsEveryDayContractManager.endApply(jsonNode);
    }

    /**
     *根据当前日常合同id 返回 该经销商签订协议的商品 列表
     *
     * @param
     * @return
     * @throws Exception
     * @throws
     */
    @PostMapping(value = "/listAgrProductByEveryDayUpdate/{id}/{agreementSummaryId}")
    @ApiOperation(value = "查询签订协议得分商品列表", httpMethod = "POST", notes = "根据合同id查询出当前合同商品列表和该协议下商品里列表")
    public List<ScmXsBigContractProductByUpdateVo> getProList(@ApiParam(name = "id", value = "当前合同id", required = true) @PathVariable String id,@ApiParam(name = "agreementSummaryId", value = "当前合同对应的协议id", required = true)  @PathVariable String agreementSummaryId) throws Exception {
        return scmXsEveryDayContractManager.getAllProListByContractId(id,agreementSummaryId);
    }
}
