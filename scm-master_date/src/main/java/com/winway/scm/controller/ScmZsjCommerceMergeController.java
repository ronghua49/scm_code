package com.winway.scm.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotent.base.annotation.Workflow;
import com.hotent.base.controller.BaseController;
import com.hotent.base.feign.BpmRuntimeFeignService;
import com.hotent.base.feign.UCFeignService;
import com.hotent.base.model.CommonResult;
import com.hotent.base.modelvo.CustomStartResult;
import com.hotent.base.modelvo.StartFlowParam;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.AuthenticationUtil;
import com.hotent.base.util.JsonUtil;
import com.hotent.base.util.StringUtil;
import com.winway.scm.model.ScmZsjCommerce;
import com.winway.scm.model.ScmZsjCommerceMerge;
import com.winway.scm.persistence.dao.ScmZsjCommerceMergeDao;
import com.winway.scm.persistence.manager.ScmZsjCommerceMergeManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.nio.file.ReadOnlyFileSystemException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

/**
 * <pre>
 * 描述：商业合并表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-18 14:36:01
 * 版权：美达开发小组
 * </pre>
 */

@RestController
@RequestMapping(value = "/scm/scmZsjCommerceMerge/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "scmZsjCommerceMergeController 商业合并")
public class ScmZsjCommerceMergeController extends BaseController {
    @Resource
    ScmZsjCommerceMergeManager scmZsjCommerceMergeManager;
    @Resource
    UCFeignService ucFeignService;
    @Resource
    ScmZsjCommerceMergeDao scmZsjCommerceMergeDao;
    @Resource
    BpmRuntimeFeignService bpmRuntimeFeignService;

    /**
     * 商业合并表列表(分页条件查询)数据
     *
     * @param
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/list")
    @ApiOperation(value = "商业合并表数据列表", httpMethod = "POST", notes = "获取商业合并表列表")
    public PageList<ScmZsjCommerceMerge> list(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
        return scmZsjCommerceMergeManager.query(queryFilter);
    }

    /**
     * 商业合并审批详情列表
     *
     * @param
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/detail/{approvalId}")
    @ApiOperation(value = "商业合并详情", httpMethod = "POST", notes = "获取商业合并详情")
    public ScmZsjCommerceMerge detail(@ApiParam(name = "id", value = "业务对象主键", required = true) @PathVariable String approvalId) throws Exception {
        return scmZsjCommerceMergeManager.detail(approvalId);
    }

    /**
     * 历史商业合并表明细页面
     *
     * @param id
     * @return
     * @throws Exception ModelAndView
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "商业合并表数据详情", httpMethod = "GET", notes = "商业合并表数据详情")
    public ScmZsjCommerceMerge get(@ApiParam(name = "id", value = "业务对象主键", required = true) @PathVariable String id) throws Exception {
        return scmZsjCommerceMergeManager.get(id);
    }

    /**
     * 新增商业合并表
     *
     * @param scmZsjCommerceMergeList
     * @return
     * @throws Exception
     * @throws
     */
    @PostMapping(value = "save")
    @ApiOperation(value = "新增,更新商业合并表数据", httpMethod = "POST", notes = "新增,更新商业合并表数据")
    public CommonResult<List<ScmZsjCommerceMerge>> save(@ApiParam(name = "scmZsjCommerceMerge", value = "商业合并表业务对象", required = true) @RequestBody ScmZsjCommerceMerge... scmZsjCommerceMergeList) throws Exception {
        JsonNode user = ucFeignService.getUser(current(), "");
        String fullname = user.get("fullname").asText();
        String id = user.get("id").asText();
        for (int i = 0; i < scmZsjCommerceMergeList.length; i++) {
            scmZsjCommerceMergeList[i].setOperatorName(fullname);
            scmZsjCommerceMergeList[i].setOperatorId(id);
            scmZsjCommerceMergeList[i].setApprovalState("0");
            if (StringUtil.isEmpty(scmZsjCommerceMergeList[i].getId())) {
                scmZsjCommerceMergeManager.create(scmZsjCommerceMergeList[i]);
            } else {
                scmZsjCommerceMergeManager.update(scmZsjCommerceMergeList[i]);
            }
        }
        return new CommonResult<List<ScmZsjCommerceMerge>>(true, "暂存成功", Arrays.asList(scmZsjCommerceMergeList));
    }

    /**
     * 删除商业合并表记录
     *
     * @param id
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "remove/{id}")
    @ApiOperation(value = "删除商业合并表记录", httpMethod = "DELETE", notes = "删除商业合并表记录")
    public CommonResult<String> remove(@ApiParam(name = "id", value = "业务主键", required = true) @PathVariable String id) throws Exception {
        scmZsjCommerceMergeManager.remove(id);
        return new CommonResult<String>(true, "删除成功");
    }

    /**
     * 批量删除商业合并表记录
     *
     * @param ids
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "/removes")
    @ApiOperation(value = "批量删除商业合并表记录", httpMethod = "DELETE", notes = "批量删除商业合并表记录")
    public CommonResult<String> removes(@ApiParam(name = "ids", value = "业务主键数组,多个业务主键之间用逗号分隔", required = true) @RequestParam String... ids) throws Exception {
        scmZsjCommerceMergeManager.removeByIds(ids);
        return new CommonResult<String>(true, "批量删除成功");
    }

    /**
     * @param scmZsjCommerceMergeList 商业合并申请 只有发起申请按钮
     */
    @PostMapping(value = "/sendApply")
    @ApiOperation(value = "商业合并申请", httpMethod = "POST", notes = "将合并列表数据除全部封装提交")
    public CommonResult<String> sendApply(
            @ApiParam(name = "scmZsjCommerceMergeList", value = "商业合并对象集合", required = true) @RequestBody ScmZsjCommerceMerge... scmZsjCommerceMergeList) throws Exception {
        if (scmZsjCommerceMergeList == null || scmZsjCommerceMergeList.length == 0) {
            throw new RuntimeException("数据不存在");
        }
        //判断是否有相同的数据正在审批中
        for (ScmZsjCommerceMerge merge : scmZsjCommerceMergeList) {
            ScmZsjCommerceMerge dublicateMerge = scmZsjCommerceMergeDao.getApprovalDublicate(merge.getHostCommerceId(), merge.getViceCommerceId());
            if (dublicateMerge != null) {
                throw new RuntimeException(dublicateMerge.getHostCommerceName() + " 的合并审批正在进行中不得二次申请");
            }
        }
        //获取审批流中的 审批id
        /*RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String token = request.getHeader("Authorization");
        headers.add("Authorization", token);
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        String url = "http://localhost:8095/scm/scmZsjCommerceMerge/v1/sendApplyOne";
        ObjectMapper objectMapper = new ObjectMapper();
        String s = restTemplate.postForObject(url, new HttpEntity<String>(objectMapper.writeValueAsString(scmZsjCommerceMergeList[0]), headers), String.class);
        ScmZsjCommerceMerge scmZsjCommerceMerge = objectMapper.readValue(s, ScmZsjCommerceMerge.class);
        JsonNode user = ucFeignService.getUser(current(), "");
        String fullname = user.get("fullname").asText();
        String id = user.get("id").asText();
        String approvalId = scmZsjCommerceMerge.getApprovalId();*/
        StartFlowParam startFlowParam = new StartFlowParam("syhbsp", "SCM", "approvalId");
        startFlowParam.setFormType("frame");
        CustomStartResult customStartResult = null;
        String approvalId = null;
        try {
            System.out.println("发起商业合并申请");
            customStartResult = bpmRuntimeFeignService.customStart(startFlowParam);
            approvalId = customStartResult.getInstId();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("启动工作流失败");
        }
        for (ScmZsjCommerceMerge scmZsjCommerceMerge : scmZsjCommerceMergeList) {
            scmZsjCommerceMerge.setOperatorName(AuthenticationUtil.getCurrentUserFullname());
            scmZsjCommerceMerge.setOperatorId(AuthenticationUtil.getCurrentUserId());
            scmZsjCommerceMerge.setApprovalId(approvalId);
            scmZsjCommerceMerge.setApprovalState("1");
            scmZsjCommerceMergeManager.create(scmZsjCommerceMerge);
            //更新该被合并商业的合并装装态
            scmZsjCommerceMergeManager.changeMergeState(scmZsjCommerceMerge.getViceCommerceId(), "1");
        }
        return new CommonResult<String>(true, "审批发起成功");
    }

    @PostMapping(value = "/sendApplyOne")
    @ApiOperation(value = "商业合并申请", httpMethod = "POST", notes = "将合并列表数据除全部封装提交")
    @Workflow(flowKey = "syhbsp", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {})
    public ScmZsjCommerceMerge sendApplyOne(
            @ApiParam(name = "ScmZsjCommerceMerge", value = "商业合并对象", required = true) @RequestBody ScmZsjCommerceMerge scmZsjCommerceMerge) throws Exception {
        return scmZsjCommerceMerge;
    }

    @PostMapping(value = "/endApply")
    @ApiOperation(value = "商业合并申请审批流程通过", httpMethod = "POST", notes = "商业首营审批流程通过,修改审批状态")
    public void endApply(@ApiParam(name = "params", value = "流程事件参数", required = true) @RequestBody String params,
                         HttpServletRequest request) throws Exception {
        JsonNode jsonNode = JsonUtil.toJsonNode(params);
        scmZsjCommerceMergeManager.endApply(jsonNode);
    }

    /**
     * 商业合并申请 信息列表
     *
     * @param
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/listCommerceMerge")
    @ApiOperation(value = "商业合并表数据列表", httpMethod = "POST", notes = "获取商业合并表列表")
    public PageList<ScmZsjCommerce> listCommerceMerge(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
        return scmZsjCommerceMergeManager.listCommerceMerge(queryFilter);
    }
}
