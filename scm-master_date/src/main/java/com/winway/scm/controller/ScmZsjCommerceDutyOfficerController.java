package com.winway.scm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotent.base.feign.UCFeignService;
import com.winway.scm.model.ScmZsjCommerce;
import com.winway.scm.model.ScmZsjCommerceMerge;
import com.winway.scm.persistence.dao.ScmZsjCommerceDao;
import com.winway.scm.persistence.dao.ScmZsjCommerceDutyOfficerDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.annotation.Workflow;
import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.JsonUtil;
import com.hotent.base.util.StringUtil;
import com.winway.scm.model.ScmZsjCommerceDutyOfficer;
import com.winway.scm.model.ScmZsjCommerceFirst;
import com.winway.scm.persistence.manager.ScmZsjCommerceDutyOfficerManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.client.RestTemplate;

/**
 * <pre>
 * 描述：商业责任人表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-18 14:36:01
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value = "/scm/scmZsjCommerceDutyOfficer/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "scmZsjCommerceDutyOfficerController 商业责任人")
public class ScmZsjCommerceDutyOfficerController extends BaseController {
    @Resource
    ScmZsjCommerceDutyOfficerManager scmZsjCommerceDutyOfficerManager;
    @Resource
    ScmZsjCommerceDutyOfficerDao scmZsjCommerceDutyOfficerDao;
    @Resource
    ScmZsjCommerceDao zsjCommerceDao;
    @Resource
    UCFeignService ucFeignService;

    /**
     * 商业责任人表列表(分页条件查询)数据
     *
     * @param
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/list")
    @ApiOperation(value = "商业责任人表数据列表", httpMethod = "POST", notes = "获取商业责任人表列表")
    public PageList<ScmZsjCommerceDutyOfficer> list(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
        return scmZsjCommerceDutyOfficerManager.query(queryFilter);
    }

    /**
     * 商业责任人表明细页面
     *
     * @param id
     * @return
     * @throws Exception ModelAndView
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "商业责任人表数据详情", httpMethod = "GET", notes = "商业责任人表数据详情")
    public ScmZsjCommerceDutyOfficer get(@ApiParam(name = "id", value = "业务对象主键", required = true) @PathVariable String id) throws Exception {
        return scmZsjCommerceDutyOfficerManager.get(id);
    }

    /**
     * 新增商业责任人表
     *
     * @param scmZsjCommerceDutyOfficer
     * @return
     * @throws Exception
     * @throws
     */
    @PostMapping(value = "save")
    @ApiOperation(value = "新增,更新商业责任人表数据", httpMethod = "POST", notes = "新增,更新商业责任人表数据")
    public CommonResult<String> save(@ApiParam(name = "scmZsjCommerceDutyOfficer", value = "商业责任人表业务对象", required = true) @RequestBody ScmZsjCommerceDutyOfficer scmZsjCommerceDutyOfficer) throws Exception {
        String msg = "添加商业责任人表成功";
        try {
            scmZsjCommerceDutyOfficerManager.createCache(scmZsjCommerceDutyOfficer);
        } catch (Exception e) {
            msg = "添加商业责任人表失败";
        }
        return new CommonResult<String>(msg);
    }

    /**
     * 删除商业责任人表记录
     *
     * @param id
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "remove/{id}")
    @ApiOperation(value = "删除商业责任人表记录", httpMethod = "DELETE", notes = "删除商业责任人表记录")
    public CommonResult<String> remove(@ApiParam(name = "id", value = "业务主键", required = true) @PathVariable String id) throws Exception {
        ScmZsjCommerceDutyOfficer scmZsjCommerceDutyOfficer;
        try {
            scmZsjCommerceDutyOfficer = scmZsjCommerceDutyOfficerManager.get(id);
        } catch (Exception e) {
            return new CommonResult<String>(false, "删除失败");
        }
        try {
            if (scmZsjCommerceDutyOfficer != null) {
                scmZsjCommerceDutyOfficerManager.remove(id);
                return new CommonResult<String>(true, "删除成功");
            }
        } catch (Exception e) {
            return new CommonResult<String>(false, "删除失败，没有此数据");

        }
        return new CommonResult<String>(false, "删除失败，没有此数据");
    }


    /**
     * 批量删除商业责任人表记录
     *
     * @param ids
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "/removes")
    @ApiOperation(value = "批量删除商业责任人表记录", httpMethod = "DELETE", notes = "批量删除商业责任人表记录")
    public CommonResult<String> removes(@ApiParam(name = "ids", value = "业务主键数组,多个业务主键之间用逗号分隔", required = true) @RequestParam String... ids) throws Exception {
        try {
            scmZsjCommerceDutyOfficerManager.removeByIds(ids);
        } catch (Exception e) {
            return new CommonResult<String>(false, "批量删除失败");
        }
        return new CommonResult<String>(true, "批量删除成功");
    }

    /**
     * @param request
     * @return
     * @throws Exception flowKey:审批类型
     *                   sysCode:系统别名
     *                   instanceIdField:与实体类审批ID相同,controller层接到对象会有审批ID,处理业务逻辑后,保存对象即可
     *                   varKeys:脚本使用参数
     */
    @PostMapping(value = "sendApply")
    @ApiOperation(value = "商业责任人首营", httpMethod = "POST", notes = "根据swagger文档传递参数")
    public CommonResult<String> sendApply(
            @ApiParam(name = "scmZsjCommerceDutyOfficers", value = "商业责任人审批对象集合", required = true) HttpServletRequest request, @RequestBody ScmZsjCommerceDutyOfficer... scmZsjCommerceDutyOfficers
    ) throws Exception {
        if (scmZsjCommerceDutyOfficers == null || scmZsjCommerceDutyOfficers.length == 0) {
            throw new RuntimeException("请选择一个商业");
        }
        //判断是否有相同的数据正在审批中
        for (int i = 0; i < scmZsjCommerceDutyOfficers.length; i++) {
            ScmZsjCommerceDutyOfficer dutyOfficer = scmZsjCommerceDutyOfficers[i];
            ScmZsjCommerceDutyOfficer approvalDublicate = scmZsjCommerceDutyOfficerDao.getApprovalDublicate(dutyOfficer.getCommerceId());
            if (approvalDublicate != null) {
                ScmZsjCommerce scmZsjCommerce = zsjCommerceDao.get(approvalDublicate.getCommerceId());
                throw new RuntimeException(scmZsjCommerce.getCommerceName() + " 的责任人调整正在审批中，不得二次申请");
            }
        }
        //获取审批流中的 该对象id 和审批id

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String token = request.getHeader("Authorization");
        headers.add("Authorization", token);
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        String url = "http://localhost:8999/scm/scmZsjCommerceDutyOfficer/v1/sendApplyOne";
        ObjectMapper objectMapper = new ObjectMapper();
        String s = restTemplate.postForObject(url, new HttpEntity<String>(objectMapper.writeValueAsString(scmZsjCommerceDutyOfficers[0]), headers), String.class);
        ScmZsjCommerceDutyOfficer dutyOfficer = objectMapper.readValue(s, ScmZsjCommerceDutyOfficer.class);
        JsonNode user = ucFeignService.getUser(current(), "");
        String fullname = user.get("fullname").asText();
        String id = user.get("id").asText();
        String approvalId = dutyOfficer.getApprovalId();
        for (int i = 0; i < scmZsjCommerceDutyOfficers.length; i++) {
            ScmZsjCommerceDutyOfficer scmZsjCommerceDutyOfficer = scmZsjCommerceDutyOfficers[i];
            scmZsjCommerceDutyOfficer.setOperatorName(fullname);
            scmZsjCommerceDutyOfficer.setOperatorId(id);
            scmZsjCommerceDutyOfficer.setApprovalId(approvalId);
            scmZsjCommerceDutyOfficer.setApprovalState("1");
            scmZsjCommerceDutyOfficer.setIseffect("0");
            scmZsjCommerceDutyOfficer.setNewDutyOfficer(scmZsjCommerceDutyOfficer.getDutyOfficer());
            //获取上次生效的责任人
            ScmZsjCommerceDutyOfficer last =  scmZsjCommerceDutyOfficerManager.getlastDutyOfficer(scmZsjCommerceDutyOfficer.getCommerceId());
            scmZsjCommerceDutyOfficer.setDutyOfficer(last==null ? null : last.getDutyOfficer());
            scmZsjCommerceDutyOfficerManager.create(scmZsjCommerceDutyOfficer);
        }
        return new CommonResult<String>(true, "审批发起成功");
    }

    @PostMapping(value = "sendApplyOne")
    @ApiOperation(value = "商业责任人首营单个", httpMethod = "POST", notes = "根据swagger文档传递参数")
    @Workflow(flowKey = "syzrrdzsq", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {})
    public ScmZsjCommerceDutyOfficer sendApplyOne(
            @ApiParam(name = "scmZsjCommerceDutyOfficer", value = "商业责任人对象", required = true) @RequestBody ScmZsjCommerceDutyOfficer scmZsjCommerceDutyOfficer) {
        return scmZsjCommerceDutyOfficer;
    }


    @PostMapping(value = "/endApply")
    @ApiOperation(value = "商业首营审批流程通过", httpMethod = "POST", notes = "商业首营审批流程通过,修改审批状态")
    public void endApply(@ApiParam(name = "params", value = "流程事件参数", required = true) @RequestBody String params) throws Exception {
        scmZsjCommerceDutyOfficerManager.endApply(params);


    }
}
