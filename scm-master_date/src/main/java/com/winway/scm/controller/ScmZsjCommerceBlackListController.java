package com.winway.scm.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.feign.UCFeignService;
import com.hotent.base.util.JsonUtil;
import com.winway.purchase.util.QuarterUtil;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import com.winway.scm.persistence.manager.ScmZsjCommerceBlackListManager;
import com.winway.scm.model.ScmZsjCommerceBlackList;
import com.winway.scm.model.ScmZsjCommerceFirst;
import com.hotent.base.annotation.Workflow;
import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;

import java.util.Date;

/**
 * <pre>
 * 描述：商业黑名单表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-18 14:36:01
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value = "/scm/scmZsjCommerceBlackList/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "scmZsjCommerceBlackListController 商业黑名单")
public class ScmZsjCommerceBlackListController extends BaseController {

    @Resource
    ScmZsjCommerceBlackListManager scmZsjCommerceBlackListManager;

    @Resource
    UCFeignService ucFeignService;

    /**
     * 商业黑名单表列表(分页条件查询)数据
     *
     * @return
     * @throws Exception PageJson
     * @throws
     * @paramrequest
     */
    @PostMapping("/list")
    @ApiOperation(value = "商业黑名单表数据列表", httpMethod = "POST", notes = "获取商业黑名单表列表")
    public PageList<ScmZsjCommerceBlackList> list(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
        return scmZsjCommerceBlackListManager.query(queryFilter);
    }

    /**
     * 商业黑名单表明细页面
     *
     * @param id
     * @return
     * @throws Exception ModelAndView
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "商业黑名单表数据详情", httpMethod = "GET", notes = "商业黑名单表数据详情")
    public ScmZsjCommerceBlackList get(@ApiParam(name = "id", value = "业务对象主键", required = true) @PathVariable String id) throws Exception {
        return scmZsjCommerceBlackListManager.get(id);
    }
    
    /**
     * 商业黑名单表明细页面
     *
     * @param id
     * @return
     * @throws Exception ModelAndView
     */
    @GetMapping(value = "/getByApplyId/{applyId}")
    @ApiOperation(value = "根据审批ID获取数据详情", httpMethod = "GET", notes = "根据审批ID获取数据详情")
    public CommonResult<ScmZsjCommerceBlackList> getByApplyId(@ApiParam(name = "applyId", value = "审批ID", required = true) @PathVariable String applyId) throws Exception {
    	ScmZsjCommerceBlackList scmZsjCommerceBlackList = scmZsjCommerceBlackListManager.getByApplId(applyId);
    	return new CommonResult<>(true,"获取成功",scmZsjCommerceBlackList);
    }

    /**
     * 新增商业黑名单表
     * @param scmZsjCommerceBlackList
     * @return
     * @throws Exception
     * @throws
     */

    @PostMapping(value = "save")
    @ApiOperation(value = "新增,更新商业黑名单表数据", httpMethod = "POST", notes = "新增,更新商业黑名单表数据")
    public CommonResult<String> save(@ApiParam(name = "scmZsjCommerceBlackList", value = "商业黑名单表业务对象", required = true) @RequestBody ScmZsjCommerceBlackList scmZsjCommerceBlackList) throws Exception {
        String msg = "添加商业黑名单表成功";
        if (StringUtil.isEmpty(scmZsjCommerceBlackList.getId())) {
            scmZsjCommerceBlackList.setApprovalState("0");
            JsonNode user = ucFeignService.getUser(current(), "");
            String userName = user.get("fullname").asText();
            scmZsjCommerceBlackList.setOperatorName(userName);
            scmZsjCommerceBlackList.setApprovalCode(QuarterUtil.getCode("HMD"));
            scmZsjCommerceBlackList.setApprovalDate(new Date());
            scmZsjCommerceBlackListManager.create(scmZsjCommerceBlackList);
        } else {
            scmZsjCommerceBlackListManager.update(scmZsjCommerceBlackList);
            msg = "更新商业黑名单表成功";
        }
        return new CommonResult<String>(msg);
    }

    /**
     * 删除商业黑名单表记录
     *
     * @param id
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "remove/{id}")
    @ApiOperation(value = "删除商业黑名单表记录", httpMethod = "DELETE", notes = "删除商业黑名单表记录")
    public CommonResult<String> remove(@ApiParam(name = "id", value = "业务主键", required = true) @PathVariable String id) throws Exception {
        scmZsjCommerceBlackListManager.remove(id);
        return new CommonResult<String>(true, "删除成功");
    }

    /**
     * 批量删除商业黑名单表记录
     *
     * @param ids
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "/removes")
    @ApiOperation(value = "批量删除商业黑名单表记录", httpMethod = "DELETE", notes = "批量删除商业黑名单表记录")
    public CommonResult<String> removes(@ApiParam(name = "ids", value = "业务主键数组,多个业务主键之间用逗号分隔", required = true) @RequestParam String... ids) throws Exception {
        scmZsjCommerceBlackListManager.removeByIds(ids);
        return new CommonResult<String>(true, "批量删除成功");
    }
    /**
     * @param request
     * @return
     * @throws Exception flowKey:审批类型
     *                   sysCode:系统别名
     *                   instanceIdField:与实体类审批ID相同,controller层接到对象会有审批ID,处理业务逻辑后,保存对象即可
     *                   varKeys:脚本使用参数
     * @param商业合并申请 需要调整保存方法, 添加委托书数据保存
     */

    @PostMapping(value = "sendApply")
    @ApiOperation(value = "商业黑名单申请", httpMethod = "POST", notes = "商业黑名单数据提交")
    @Workflow(flowKey = "syhmdsp", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {})
    public CommonResult<ScmZsjCommerceFirst> sendApply(
            @ApiParam(name = "scmZsjCommerceBlackList", value = "采购合同对象", required = true) @RequestBody ScmZsjCommerceBlackList scmZsjCommerceBlackList,
            HttpServletRequest request) throws Exception {
        JsonNode user = ucFeignService.getUser(current(), "");
        String userName = user.get("fullname").asText();
        scmZsjCommerceBlackList.setOperatorName(userName);
        scmZsjCommerceBlackList.setApprovalState("1");
        scmZsjCommerceBlackList.setApprovalDate(new Date());
        if (scmZsjCommerceBlackListManager.get(scmZsjCommerceBlackList.getId()) == null) {
            scmZsjCommerceBlackList.setApprovalCode(QuarterUtil.getCode("HMD"));
            scmZsjCommerceBlackListManager.create(scmZsjCommerceBlackList);
        } else {
            scmZsjCommerceBlackListManager.update(scmZsjCommerceBlackList);
        }
        return new CommonResult<ScmZsjCommerceFirst>(true, "审批发起成功");
    }

    /**
     * 处理流程审批结束，更改状态码等
     *
     * @param params
     * @param request
     * @throws Exception
     */
    @PostMapping(value = "/endApply")
    @ApiOperation(value = "商业黑名单审批流程通过", httpMethod = "POST", notes = "商业黑名单审批流程通过,修改审批状态")
    public void endApply(@ApiParam(name = "params", value = "流程事件参数", required = true) @RequestBody String params,
                         HttpServletRequest request) throws Exception {
        JsonNode jsonNode = JsonUtil.toJsonNode(params);
        scmZsjCommerceBlackListManager.endApply(jsonNode);
    }

    /**
     * 商业黑名单表明细页面
     *
     * @param id
     * @return
     * @throws Exception ModelAndView
     */
    @GetMapping(value = "/getCommerceIsBlack/{id}/{ownerId}")
    @ApiOperation(value = "商业黑名单表数据详情", httpMethod = "GET", notes = "商业黑名单表数据详情")
    public boolean getCommerceIsBlack(@ApiParam(name = "id", value = "业务对象主键", required = true) @PathVariable(value = "id") String id, @ApiParam(name = "ownerId", value = "货主ID", required = true) @PathVariable(value = "ownerId") String ownerId) throws Exception {
        boolean commerceIsBlack = scmZsjCommerceBlackListManager.getCommerceIsBlack(id, ownerId);
        return false;
    }

}
