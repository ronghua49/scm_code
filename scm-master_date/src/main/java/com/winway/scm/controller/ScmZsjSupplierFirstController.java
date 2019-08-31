package com.winway.scm.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.annotation.Workflow;
import com.hotent.base.controller.BaseController;
import com.hotent.base.feign.UCFeignService;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.JsonUtil;
import com.hotent.base.util.StringUtil;
import com.winway.scm.model.ScmZsjCommerce;
import com.winway.scm.model.ScmZsjSupplier;
import com.winway.scm.model.ScmZsjSupplierFirst;
import com.winway.scm.persistence.dao.ScmZsjSupplierFirstDao;
import com.winway.scm.persistence.manager.ScmZsjSupplierFirstManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
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
@RequestMapping(value = "/scm/scmZsjSupplierFirst/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "scmZsjSupplierFirstController 供应商首营记录")
public class ScmZsjSupplierFirstController extends BaseController {
    @Resource
    ScmZsjSupplierFirstManager scmZsjSupplierFirstManager;
    @Resource
    UCFeignService ucFeignService;
    @Resource
    ScmZsjSupplierFirstDao scmZsjSupplierFirstDao;

    /**
     * 供应商首营记录表列表(分页条件查询)数据
     *
     * @param request
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/list")
    @ApiOperation(value = "供应商首营记录表数据列表", httpMethod = "POST", notes = "获取供应商首营记录表列表,query中必须添加货主ID筛选查询")
    public PageList<ScmZsjSupplierFirst> list(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
        return scmZsjSupplierFirstManager.query(queryFilter);
    }

    /**
     * 供应商首营记录表明细页面
     * 在供应商实体类 与mapper文件中添加一对多与一对多关系,委托书未生成
     *
     * @param id
     * @return
     * @throws Exception ModelAndView
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "供应商首营记录表数据详情", httpMethod = "GET", notes = "供应商首营记录表数据详情")
    public ScmZsjSupplierFirst get(@ApiParam(name = "id", value = "业务对象主键", required = true) @PathVariable String id) throws Exception {
        return scmZsjSupplierFirstManager.get(id);
    }

    /**
     * 新增供应商首营记录表
     * 暂存时先判断供应商是否存在,不存在保存,存在则跳过保存
     *
     * @param scmZsjSupplierFirst
     * @return
     * @throws Exception
     * @throws
     */
    @PostMapping(value = "save")
    @ApiOperation(value = "新增,更新供应商首营记录表数据", httpMethod = "POST", notes = "新增,更新供应商首营记录表数据")
    public CommonResult<String> save(@ApiParam(name = "scmZsjSupplierFirst", value = "供应商首营记录表业务对象", required = true) @RequestBody ScmZsjSupplierFirst scmZsjSupplierFirst) throws Exception {
        String msg = "添加供应商首营记录表成功";
        if (StringUtil.isEmpty(scmZsjSupplierFirst.getSupplierId())) {
            throw new RuntimeException("请选择供应商后暂存");
        }
        JsonNode user = ucFeignService.getUser(current(), "");
        String fullname = user.get("fullname").asText();
        scmZsjSupplierFirst.setOperatorName(fullname);
        if (StringUtil.isEmpty(scmZsjSupplierFirst.getId())) {
            scmZsjSupplierFirstManager.isSave(scmZsjSupplierFirst);
            scmZsjSupplierFirst.setApprovalState("0");
            scmZsjSupplierFirstManager.create(scmZsjSupplierFirst);
        } else {
            scmZsjSupplierFirst.setApprovalState("0");
            scmZsjSupplierFirstManager.update(scmZsjSupplierFirst);
            msg = "更新供应商首营记录表成功";
        }
        return new CommonResult<String>(true, msg, scmZsjSupplierFirst.getId());
    }

    /**
     * 删除供应商首营记录表记录
     *
     * @param id
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "remove/{id}")
    @ApiOperation(value = "删除供应商首营记录表记录", httpMethod = "DELETE", notes = "删除供应商首营记录表记录")
    public CommonResult<String> remove(@ApiParam(name = "id", value = "业务主键", required = true) @PathVariable String id) throws Exception {
        scmZsjSupplierFirstManager.remove(id);
        return new CommonResult<String>(true, "删除成功");
    }

    /**
     * 批量删除供应商首营记录表记录
     *
     * @param ids
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "/removes")
    @ApiOperation(value = "批量删除供应商首营记录表记录", httpMethod = "DELETE", notes = "批量删除供应商首营记录表记录")
    public CommonResult<String> removes(@ApiParam(name = "ids", value = "业务主键数组,多个业务主键之间用逗号分隔", required = true) @RequestParam String... ids) throws Exception {
        scmZsjSupplierFirstManager.removeByIds(ids);
        return new CommonResult<String>(true, "批量删除成功");
    }

    /**
     * @param 供应商首营申请 在供应商首营类中添加供应商对象,在提交审批时先判断供应商是否存在,不存在保存,存在则跳过保存
     * @param request
     * @return
     * @throws Exception flowKey:审批类型
     *                   sysCode:系统别名
     *                   instanceIdField:与实体类审批ID相同,controller层接到对象会有审批ID,处理业务逻辑后,保存对象即可
     *                   varKeys:脚本使用参数
     */
    @PostMapping(value = "sendApply")
    @ApiOperation(value = "供应商首营申请", httpMethod = "POST", notes = "在供应商首营类中添加供应商对象,在提交审批时先判断供应商是否存在,不存在保存,存在则跳过保存")
//    @Workflow(flowKey = "gyssy", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {})
    public CommonResult<String> sendApply(
            @ApiParam(name = "scmZsjSupplierFirst", value = "供应商首营对象", required = true) @RequestBody ScmZsjSupplierFirst scmZsjSupplierFirst,
            HttpServletRequest request) throws Exception {
        JsonNode user = ucFeignService.getUser(current(), "");
        String fullname = user.get("fullname").asText();
        scmZsjSupplierFirst.setOperatorName(fullname);
        ScmZsjSupplierFirst scmZsjSupplierFirst2 = scmZsjSupplierFirstDao.get(scmZsjSupplierFirst.getId());
        if (scmZsjSupplierFirst2 == null) {
            scmZsjSupplierFirstManager.sendApply(scmZsjSupplierFirst);
        } else {
            scmZsjSupplierFirstManager.updateSendApply(scmZsjSupplierFirst);
        }
        return new CommonResult<String>(true, "供应商首营通过");
    }


    /**
     * @param    供应商首营编辑页面进来发起申请
     * @param request
     * @return
     * @throws Exception flowKey:审批类型
     *                   sysCode:系统别名
     *                   instanceIdField:与实体类审批ID相同,controller层接到对象会有审批ID,处理业务逻辑后,保存对象即可
     *                   varKeys:脚本使用参数
     */
    @PostMapping(value = "sendApplyEdit")
    @ApiOperation(value = "供应商首营申请", httpMethod = "POST", notes = "在供应商首营类中添加供应商对象,在提交审批时先判断供应商是否存在,不存在保存,存在则跳过保存")
//    @Workflow(flowKey = "gyssyxgsq", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {})
    public CommonResult<String> sendApplyEdit(
            @ApiParam(name = "scmZsjSupplierFirst", value = "供应商首营对象", required = true) @RequestBody ScmZsjSupplierFirst scmZsjSupplierFirst,
            HttpServletRequest request) throws Exception {
        JsonNode user = ucFeignService.getUser(current(), "");
        String fullname = user.get("fullname").asText();
        scmZsjSupplierFirst.setOperatorName(fullname);
        ScmZsjSupplierFirst scmZsjSupplierFirst2 = scmZsjSupplierFirstDao.get(scmZsjSupplierFirst.getId());
        if (scmZsjSupplierFirst2 == null) {
            scmZsjSupplierFirstManager.sendApply(scmZsjSupplierFirst);
        } else {
            scmZsjSupplierFirstManager.updateSendApply(scmZsjSupplierFirst);
        }
        return new CommonResult<String>(true, "供应商首营通过");
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
     *
     * @param id
     * @return
     * @throws Exception ModelAndView
     */
    @GetMapping(value = "/getSupplierFirstByApprovalId/{approvalId}")
    @ApiOperation(value = "根据审批ID获取详细数据", httpMethod = "GET", notes = "根据审批ID获取详细数据")
    public CommonResult<ScmZsjSupplierFirst> getSupplierFirstByApprovalId(@ApiParam(name = "approvalId", value = "审批ID", required = true) @PathVariable String approvalId) throws Exception {
        ScmZsjSupplierFirst ScmZsjSupplierFirst = scmZsjSupplierFirstManager.getSupplierFirstByApprovalId(approvalId);
        return new CommonResult<ScmZsjSupplierFirst>(true, "获取成功", ScmZsjSupplierFirst);
    }

    /**
     * @param 商业首营申请  需要调整保存方法,添加委托书数据保存
     *                在首营类中添加商业数据对象,发起审批时先判断商业数据是否存在,不存在进行保存,存在不保存
     * @param request
     * @return
     * @throws Exception flowKey:审批类型
     *                   sysCode:系统别名
     *                   instanceIdField:与实体类审批ID相同,controller层接到对象会有审批ID,处理业务逻辑后,保存对象即可
     *                   varKeys:脚本使用参数
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


    /**
     * 供应商许可证有效期预警
     *
     * @param
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/licenceValidity")
    @ApiOperation(value = "供应商许可证有效期预警", httpMethod = "POST", notes = "供应商许可证有效期预警")
    public PageList<ScmZsjCommerce> licenceValidity(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
        return scmZsjSupplierFirstManager.licenseWaring(queryFilter);
    }


    /**
     * 供应商营业执照有效期预警
     *
     * @param
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/businessValidity")
    @ApiOperation(value = "营业执照有效期预警", httpMethod = "POST", notes = "营业执照信息列表")
    public PageList<ScmZsjCommerce> businessValidity(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
        return scmZsjSupplierFirstManager.licenseWaring(queryFilter);
    }


    /**
     * 供应商gmp证书列表
     *
     * @param
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/gmpValidity")
    @ApiOperation(value = "供应商gsp证书", httpMethod = "POST", notes = "供应商gsp 证书列表")
    public PageList<ScmZsjSupplier> gmpValidity(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
        return scmZsjSupplierFirstManager.gmplist(queryFilter);
    }

    /**
     * 供应商委托书列表
     *
     * @param
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/commerceEntruseBookValidity")
    @ApiOperation(value = "供应商委托书列表", httpMethod = "POST", notes = "供应商委托书列表")
    public PageList<ScmZsjSupplier> creditlist(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
        return scmZsjSupplierFirstManager.creditlist(queryFilter);
    }


    /**
     * 供应商药品质量保证协议有效期列表     *
     *
     * @param
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/qlist")
    @ApiOperation(value = "供应商药品质量保证协议有效期列表", httpMethod = "POST", notes = "供应商药品质量保证协议有效期列表")
    public PageList<ScmZsjSupplier> qlist(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
        return scmZsjSupplierFirstManager.qlist(queryFilter);
    }

    /**
     * 停用启用接口
     *
     * @param
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/startOrStop/{id}")
    @ApiOperation(value = "停用启用接口", httpMethod = "POST", notes = "停用启用接口")
    public CommonResult<String> startOrStop(@ApiParam(name = "id", value = "查询对象") @PathVariable String id) throws Exception {
        String type = scmZsjSupplierFirstManager.startOrStop(id);
        return new CommonResult<String>(true, "停用成功");
    }
    /**
     * 采购时判断供应商证照有效期。
     *
     * @param
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @GetMapping("/isInForce/{supplierId}")
    @ApiOperation(value = "证照期限是否在有效期期限", httpMethod = "GET", notes = "供应商证照期限判断，是否可以下采购单和采购补单")
    public CommonResult<String> isInForce(@ApiParam(name = "supplierId", value = "供应商id") @PathVariable String supplierId) {
        try {
            boolean a = scmZsjSupplierFirstManager.isInForce(supplierId);
            return new CommonResult<String>(true, "调用成功",Boolean.toString(a));
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult<String>(false, "系统异常");
        }
    }


    /**
     * 获取供应商授权销售品种药品编码
     *
     * @param
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @GetMapping("/getAccreditProCodes/{supplierId}")
    @ApiOperation(value = "获取供应商授权销售品种药品编码", httpMethod = "GET", notes = "获取供应商授权销售品种商品编码")
    public String getAccreditProCodes(@ApiParam(name = "supplierId", value = "供应商id") @PathVariable String supplierId) {
        Set<String> codes = scmZsjSupplierFirstManager.getAccreditProCodes(supplierId);
        return  JSON.toJSONString(codes);
    }





}

