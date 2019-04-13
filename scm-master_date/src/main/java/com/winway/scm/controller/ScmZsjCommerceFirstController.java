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
import com.winway.scm.model.ScmZsjCommerceFirst;
import com.winway.scm.persistence.dao.ScmZsjCommerceFirstDao;
import com.winway.scm.persistence.manager.ScmZsjCommerceFirstManager;
import com.winway.scm.persistence.manager.ScmZsjCommerceManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <pre>
 * 描述：商业首营记录表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value = "/scm/scmZsjCommerceFirst/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "scmZsjCommerceFirstController 商业首营记录表")
public class ScmZsjCommerceFirstController extends BaseController {
    @Resource
    ScmZsjCommerceFirstManager scmZsjCommerceFirstManager;
    @Resource
    ScmZsjCommerceManager scmZsjCommerceManager;
    @Resource
    UCFeignService ucFeignService;
    @Resource
    ScmZsjCommerceFirstDao scmZsjCommerceFirstDao;

    /**
     * 商业首营记录表列表(分页条件查询)数据
     *
     * @param request
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/list")
    @ApiOperation(value = "商业首营记录表数据列表", httpMethod = "POST", notes = "获取商业首营记录表列表")
    public PageList<ScmZsjCommerceFirst> list(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
        return scmZsjCommerceFirstManager.query(queryFilter);
    }

    /**
     * 商业首营记录表明细页面
     * 在供应商实体类 与mapper文件中添加一对多与一对多关系,委托书未生成
     *
     * @param id
     * @return
     * @throws Exception ModelAndView
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "商业首营记录表数据详情", httpMethod = "GET", notes = "商业首营记录表数据详情")
    public ScmZsjCommerceFirst get(@ApiParam(name = "id", value = "业务对象主键", required = true) @PathVariable String id) throws Exception {
        return scmZsjCommerceFirstManager.get(id);
    }

    /**
     * 新增商业首营记录表,要根据名称判断商业信息是否变更
     * 暂存时先判断商业数据是否存在,不存在进行保存,存在不保存
     *
     * @param scmZsjCommerceFirst
     * @return
     * @throws Exception
     * @throws
     */
    @PostMapping(value = "save")
    @ApiOperation(value = "新增,更新商业首营记录表数据", httpMethod = "POST", notes = "新增商业首营记录表,要根据名称判断商业信息是否变更,暂存时先判断商业数据是否存在,不存在进行保存,存在不保存")
    public CommonResult<String> save(@ApiParam(name = "scmZsjCommerceFirst", value = "商业首营记录表业务对象", required = true) @RequestBody ScmZsjCommerceFirst scmZsjCommerceFirst) throws Exception {
        if (StringUtil.isEmpty(scmZsjCommerceFirst.getCommerceId())) {
            throw new RuntimeException("请选择商业后暂存");
        }
        scmZsjCommerceFirst.setOperatorName(getOperatorName());
        String msg = "添加商业首营记录表成功";
        if (StringUtil.isEmpty(scmZsjCommerceFirst.getId())) {
            //判断选中的商业是否已经发起过申请
            scmZsjCommerceFirstManager.isSave(scmZsjCommerceFirst);
            scmZsjCommerceFirst.setApprovalState("0");
            scmZsjCommerceFirstManager.create(scmZsjCommerceFirst);
        } else {
            scmZsjCommerceFirstManager.update(scmZsjCommerceFirst);
            msg = "更新商业首营记录表成功";
        }
        return new CommonResult<String>(true, msg, scmZsjCommerceFirst.getId());
    }

    /**
     * 删除商业首营记录表记录
     *
     * @param id
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "remove/{id}")
    @ApiOperation(value = "删除商业首营记录表记录", httpMethod = "DELETE", notes = "删除商业首营记录表记录")
    public CommonResult<String> remove(@ApiParam(name = "id", value = "业务主键", required = true) @PathVariable String id) throws Exception {
        scmZsjCommerceFirstManager.remove(id);
        return new CommonResult<String>(true, "删除成功");
    }

    /**
     * 批量删除商业首营记录表记录
     *
     * @param ids
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "/removes")
    @ApiOperation(value = "批量删除商业首营记录表记录", httpMethod = "DELETE", notes = "批量删除商业首营记录表记录")
    public CommonResult<String> removes(@ApiParam(name = "ids", value = "业务主键数组,多个业务主键之间用逗号分隔", required = true) @RequestParam String... ids) throws Exception {
        scmZsjCommerceFirstManager.removeByIds(ids);
        return new CommonResult<String>(true, "批量删除成功");
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
    @PostMapping(value = "sendApply")
    @ApiOperation(value = "商业首营", httpMethod = "POST", notes = "需要调整保存方法,添加委托书数据保存,在首营类中添加商业数据对象,发起审批时先判断商业数据是否存在,不存在进行保存,存在不保存")
    @Workflow(flowKey = "sysy", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {})
    public CommonResult<ScmZsjCommerceFirst> sendApply(
            @ApiParam(name = "scmZsjCommerceFirst", value = "商业首营对象", required = true) @RequestBody ScmZsjCommerceFirst scmZsjCommerceFirst,
            HttpServletRequest request) throws Exception {
        String operatorName = getOperatorName();
        scmZsjCommerceFirst.setOperatorName(operatorName);
        ScmZsjCommerceFirst scmZsjCommerceFirst2 = scmZsjCommerceFirstDao.get(scmZsjCommerceFirst.getId());
        if (scmZsjCommerceFirst2 == null) {
            scmZsjCommerceFirstManager.sendApply(scmZsjCommerceFirst);
        } else {
            scmZsjCommerceFirstManager.updateSendApply(scmZsjCommerceFirst);
        }
        return new CommonResult<ScmZsjCommerceFirst>(true, "审批发起成功");
    }


    @PostMapping(value = "/endApply")
    @ApiOperation(value = "商业首营审批流程通过", httpMethod = "POST", notes = "商业首营审批流程通过,修改审批状态")
    public void endApply(@ApiParam(name = "params", value = "流程事件参数", required = true) @RequestBody String params,
                         HttpServletRequest request) throws Exception {
        JsonNode jsonNode = JsonUtil.toJsonNode(params);
        scmZsjCommerceFirstManager.endApply(jsonNode);
    }

    /**
     * 根据审批ID获取详细数据
     * 在供应商实体类 与mapper文件中添加一对多与一对多关系,委托书未生成
     *
     * @param id
     * @return
     * @throws Exception ModelAndView
     */
    @GetMapping(value = "/getCommerceFirstByApprovalId/{approvalId}")
    @ApiOperation(value = "根据审批ID获取详细数据", httpMethod = "GET", notes = "根据审批ID获取详细数据")
    public CommonResult<ScmZsjCommerceFirst> getCommerceFirstByApprovalId(@ApiParam(name = "approvalId", value = "审批ID", required = true) @PathVariable String approvalId) throws Exception {
        ScmZsjCommerceFirst productFirstByApprovalId = scmZsjCommerceFirstManager.getCommerceFirstByApprovalId(approvalId);
        return new CommonResult<ScmZsjCommerceFirst>(true, "获取成功", productFirstByApprovalId);
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
    public CommonResult<ScmZsjCommerceFirst> updateSendApply(
            @ApiParam(name = "scmZsjCommerceFirst", value = "商业首营对象", required = true) @RequestBody ScmZsjCommerceFirst scmZsjCommerceFirst,
            HttpServletRequest request) throws Exception {
        scmZsjCommerceFirst.setOperatorName(getOperatorName());
        scmZsjCommerceFirstManager.updateSendApply(scmZsjCommerceFirst);
        return new CommonResult<ScmZsjCommerceFirst>(true, "审批发起成功");
    }

    private String getOperatorName() {
        JsonNode user = ucFeignService.getUser(current(), "");
        String fullname = user.get("fullname").asText();
        return fullname;
    }

}
