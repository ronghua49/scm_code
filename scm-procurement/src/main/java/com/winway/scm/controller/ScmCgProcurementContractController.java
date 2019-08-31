package com.winway.scm.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.annotation.Workflow;
import com.hotent.base.controller.BaseController;
import com.hotent.base.feign.BpmRuntimeFeignService;
import com.hotent.base.feign.UCFeignService;
import com.hotent.base.model.CommonResult;
import com.hotent.base.model.WinwayResult;
import com.hotent.base.modelvo.CustomStartResult;
import com.hotent.base.modelvo.StartFlowParam;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.JsonUtil;
import com.hotent.base.util.StringUtil;
import com.winway.scm.model.ScmCgProcurementContract;
import com.winway.scm.persistence.dao.ScmCgProcurementContractDao;
import com.winway.scm.persistence.manager.ScmCgProcurementContractManager;
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
 * 描述：采购合同 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-26 13:47:44
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
@RestController
@RequestMapping(value = "/scm/scmCgProcurementContract/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "scmCgProcurementContractController 采购合同")
public class ScmCgProcurementContractController extends BaseController {
    @Resource
    ScmCgProcurementContractManager scmCgProcurementContractManager;
    @Resource
    ScmCgProcurementContractDao scmCgProcurementContractDao;
    @Resource
    UCFeignService ucFeignService;
    @Resource
    BpmRuntimeFeignService bpmRuntimeFeignService;

    /**
     * SCM_CG_ProcurementContract列表(分页条件查询)数据
     *
     * @param request
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/list")
    @ApiOperation(value = "采购合同数据列表", httpMethod = "POST", notes = "获取采购合同列表,需要根据货主ID筛选")
    public PageList<ScmCgProcurementContract> list(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
        return scmCgProcurementContractManager.query(queryFilter);
    }

    /**
     * SCM_CG_ProcurementContract明细页面
     *
     * @param id
     * @return
     * @throws Exception ModelAndView
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "采购合同数据详情", httpMethod = "GET", notes = "采购合同数据详情")
    public ScmCgProcurementContract get(@ApiParam(name = "id", value = "业务对象主键", required = true) @PathVariable String id) throws Exception {
        return scmCgProcurementContractManager.get(id);
    }

    /**
     * 新增SCM_CG_ProcurementContract
     *
     * @param scmCgProcurementContract
     * @return
     * @throws Exception
     * @throws
     */
    @PostMapping(value = "save")
    @ApiOperation(value = "新增,更新采购合同数据", httpMethod = "POST", notes = "新增,更新采购合同数据")
    public CommonResult<String> save(@ApiParam(name = "scmCgProcurementContract", value = "采购合同业务对象", required = true) @RequestBody ScmCgProcurementContract scmCgProcurementContract) throws Exception {
        String msg = "添加采购合同成功";
        JsonNode user = ucFeignService.getUser(current(), "");
        String fullname = user.get("fullname").asText();
        String id = user.get("id").asText();
        scmCgProcurementContract.setOperatorName(fullname);
        scmCgProcurementContract.setOperatorId(id);
        //判断供应商证照是否在有效期内
        scmCgProcurementContractManager.judgeSupplierValidity(scmCgProcurementContract.getSupplierId());
        if (StringUtil.isEmpty(scmCgProcurementContract.getId())) {
            scmCgProcurementContractManager.create(scmCgProcurementContract);
        } else {
            //验证合同状态
            ScmCgProcurementContract scmCgProcurementContract2 = scmCgProcurementContractManager.get(scmCgProcurementContract.getId());
            if (scmCgProcurementContract2 == null) {
                throw new RuntimeException("当前合同不存在或已经被删除");
            } else {
                //验证合同状态
                if ("1".equals(scmCgProcurementContract2.getApprovalState()) || "2".equals(scmCgProcurementContract2.getApprovalState())) {
                    throw new RuntimeException("当前合同已提交审批或已通过审批,不可修改数据");
                }
            }
            scmCgProcurementContract2.setCurrency(scmCgProcurementContract.getCurrency());
            scmCgProcurementContract2.setSupplierId(scmCgProcurementContract.getSupplierId());
            scmCgProcurementContract2.setSupplier(scmCgProcurementContract.getSupplier());
            scmCgProcurementContractManager.update(scmCgProcurementContract2);
            msg = "更新采购合同成功";
        }
        return new CommonResult<String>(true, msg, scmCgProcurementContract.getId());
    }

    /**
     * 删除SCM_CG_ProcurementContract记录
     *
     * @param id
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "remove/{id}")
    @ApiOperation(value = "删除采购合同记录", httpMethod = "DELETE", notes = "删除采购合同记录")
    public CommonResult<String> remove(@ApiParam(name = "id", value = "业务主键", required = true) @PathVariable String id) throws Exception {
        ScmCgProcurementContract scmCgProcurementContract2 = scmCgProcurementContractManager.get(id);
        if (scmCgProcurementContract2 == null) {
            throw new RuntimeException("当前合同不存在或已经被删除");
        } else {
            //验证合同状态
            if ("1".equals(scmCgProcurementContract2.getApprovalState()) || "2".equals(scmCgProcurementContract2.getApprovalState())) {
                throw new RuntimeException("当前合同已提交审批或已通过审批,不可修改数据");
            }
        }
        scmCgProcurementContractManager.remove(id);
        return new CommonResult<String>(true, "删除成功");
    }

    /**
     * 批量删除SCM_CG_ProcurementContract记录
     *
     * @param ids
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "/removes")
    @ApiOperation(value = "批量删除采购合同记录", httpMethod = "DELETE", notes = "批量删除采购合同记录")
    public CommonResult<String> removes(@ApiParam(name = "ids", value = "业务主键数组,多个业务主键之间用逗号分隔", required = true) @RequestParam String... ids) throws Exception {
        for (String id : ids) {
            ScmCgProcurementContract scmCgProcurementContract2 = scmCgProcurementContractManager.get(id);
            if (scmCgProcurementContract2 == null) {
                throw new RuntimeException("当前合同不存在或已经被删除");
            } else {
                //验证合同状态
                if ("1".equals(scmCgProcurementContract2.getApprovalState()) || "2".equals(scmCgProcurementContract2.getApprovalState())) {
                    throw new RuntimeException("当前合同已提交审批或已通过审批,不可修改数据");
                }
            }
        }
        scmCgProcurementContractManager.removeByIds(ids);
        return new CommonResult<String>(true, "批量删除成功");
    }

    /**
     * @param 采购合同申请
     * @param request
     * @return
     * @throws Exception flowKey:审批类型
     *                   sysCode:系统别名
     *                   instanceIdField:与实体类审批ID相同,controller层接到对象会有审批ID,处理业务逻辑后,保存对象即可
     *                   varKeys:脚本使用参数
     */
    @PostMapping(value = "/sendApply")
    @ApiOperation(value = "采购合同申请", httpMethod = "POST", notes = "采购合同首营申请方法,首营页面首先判断是否有合同ID,"
            + "如果有合同ID 说明当前数据以及提交过,不需要再做二次提交,到审批第二步选择商品,需要单独维护商品,需要用到合同ID,所以在第一步需要保证当前有合同ID,才能进行操作")
//    @Workflow(flowKey = "cght", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {})
    public WinwayResult<String> sendApply(
            @ApiParam(name = "scmCgProcurementContract", value = "采购合同对象", required = true) @RequestBody ScmCgProcurementContract scmCgProcurementContract,
            HttpServletRequest request) throws Exception {
        if (StringUtil.isEmpty(scmCgProcurementContract.getId())) {
            throw new RuntimeException("采购合同ID不可为空");
        } else {
            ScmCgProcurementContract scmCgProcurementContract2 = scmCgProcurementContractManager.get(scmCgProcurementContract.getId());
            if (scmCgProcurementContract2 == null) {
                throw new RuntimeException("当前合同不存在或已经被删除");
            } else {
                //验证合同状态
                if ("1".equals(scmCgProcurementContract2.getApprovalState()) || "2".equals(scmCgProcurementContract2.getApprovalState())) {
                    throw new RuntimeException("当前合同已提交审批或已通过审批,不可重复申请");
                } else {
                    StartFlowParam startFlowParam = new StartFlowParam("cght", "SCM", "approvalId");
                    startFlowParam.setFormType("frame");
                    CustomStartResult customStartResult = null;
                    try {
                        System.out.println("发起采购合同申请");
                        customStartResult = bpmRuntimeFeignService.customStart(startFlowParam);
                        String approvalId = customStartResult.getInstId();
                        scmCgProcurementContract2.setApprovalId(approvalId);
                        scmCgProcurementContract2.setApprovalState("1");
                        scmCgProcurementContract2.setFilingDate(new Date());
                        scmCgProcurementContractDao.update(scmCgProcurementContract2);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException("启动工作流失败");
                    }
                }
            }
        }
        return new WinwayResult<>("true", "申请提交成功");
    }

    @PostMapping(value = "/postApprovalEvent")
    @ApiOperation(value = "审批流程走完，修改流程状态", httpMethod = "POST", notes = "审批流程走完，修改流程状态")
    public void postApprovalEvent(@ApiParam(name = "params", value = "流程事件参数", required = true) @RequestBody String params,
                                  HttpServletRequest request) throws Exception {
        JsonNode jsonNode = JsonUtil.toJsonNode(params);
        scmCgProcurementContractManager.endApply(jsonNode);
    }

    /**
     * 根据审批ID获取详细数据
     * 在供应商实体类 与mapper文件中添加一对多与一对多关系,委托书未生成
     *
     * @param id
     * @return
     * @throws Exception ModelAndView
     */
    @GetMapping(value = "/getContractFirstByApprovalId/{approvalId}")
    @ApiOperation(value = "根据审批ID获取详细数据", httpMethod = "GET", notes = "根据审批ID获取详细数据")
    public CommonResult<ScmCgProcurementContract> getContractFirstByApprovalId(@ApiParam(name = "approvalId", value = "审批ID", required = true) @PathVariable String approvalId) throws Exception {
        ScmCgProcurementContract scmCgProcurementContract = scmCgProcurementContractManager.getContractFirstByApprovalId(approvalId);
        return new CommonResult<ScmCgProcurementContract>(true, "获取成功", scmCgProcurementContract);
    }
}
