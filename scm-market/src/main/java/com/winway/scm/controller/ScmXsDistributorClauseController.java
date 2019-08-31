package com.winway.scm.controller;

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

import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;
import com.winway.scm.model.ScmXsDistributorClause;
import com.winway.scm.persistence.manager.ScmXsDistributorClauseManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <pre>
 * 描述：分销商协议合作条款表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:43
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value = "/scm/scmXsDistributorClause/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "scmXsDistributorClauseController 分销商协议合作条款表")
public class ScmXsDistributorClauseController extends BaseController {
    @Resource
    ScmXsDistributorClauseManager scmXsDistributorClauseManager;

    /**
     * 分销商协议合作条款表列表(分页条件查询)数据
     *
     * @param request
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/list")
    @ApiOperation(value = "分销商协议合作条款表数据列表", httpMethod = "POST", notes = "获取分销商协议合作条款表列表")
    public PageList<ScmXsDistributorClause> list(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
        return scmXsDistributorClauseManager.query(queryFilter);
    }

    /**
     * 分销商协议合作条款表列表带审批等信息
     *
     * @param request
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/firstList")
    @ApiOperation(value = "分销商协议合作条款表列表带审批等信息", httpMethod = "POST", notes = "分销商协议合作条款表列表带审批等信息,支持query查询")
    public PageList<ScmXsDistributorClause> firstList(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
        return scmXsDistributorClauseManager.firstList(queryFilter);
    }

    /**
     * 分销商协议合作条款表明细页面
     *
     * @param id
     * @return
     * @throws Exception ModelAndView
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "分销商协议合作条款表数据详情", httpMethod = "GET", notes = "分销商协议合作条款表数据详情")
    public ScmXsDistributorClause get(@ApiParam(name = "id", value = "业务对象主键", required = true) @PathVariable String id) throws Exception {
        return scmXsDistributorClauseManager.get(id);
    }

    /**
     * 新增分销商协议合作条款表
     *
     * @param scmXsDistributorClause
     * @return
     * @throws Exception
     * @throws
     */
    @PostMapping(value = "save")
    @ApiOperation(value = "新增,更新分销商协议合作条款表数据", httpMethod = "POST", notes = "新增,更新分销商协议合作条款表数据")
    public CommonResult<String> save(@ApiParam(name = "scmXsDistributorClause", value = "分销商协议合作条款表业务对象", required = true) @RequestBody ScmXsDistributorClause scmXsDistributorClause) throws Exception {
        String msg = "添加分销商协议合作条款表成功";
        if (StringUtil.isEmpty(scmXsDistributorClause.getId())) {
            scmXsDistributorClauseManager.create(scmXsDistributorClause);
        } else {
            scmXsDistributorClauseManager.update(scmXsDistributorClause);
            msg = "更新分销商协议合作条款表成功";
        }
        return new CommonResult<String>(msg);
    }

    /**
     * 删除分销商协议合作条款表记录
     *
     * @param id
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "remove/{id}")
    @ApiOperation(value = "删除分销商协议合作条款表记录", httpMethod = "DELETE", notes = "删除分销商协议合作条款表记录")
    public CommonResult<String> remove(@ApiParam(name = "id", value = "业务主键", required = true) @PathVariable String id) throws Exception {
        scmXsDistributorClauseManager.remove(id);
        return new CommonResult<String>(true, "删除成功");
    }

    /**
     * 批量删除分销商协议合作条款表记录
     *
     * @param ids
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "/removes")
    @ApiOperation(value = "批量删除分销商协议合作条款表记录", httpMethod = "DELETE", notes = "批量删除分销商协议合作条款表记录")
    public CommonResult<String> removes(@ApiParam(name = "ids", value = "业务主键数组,多个业务主键之间用逗号分隔", required = true) @RequestParam String... ids) throws Exception {
        scmXsDistributorClauseManager.removeByIds(ids);
        return new CommonResult<String>(true, "批量删除成功");
    }

    /**
     * 根据货主ID获取商业协议合作名单年度下拉框列表
     *
     * @return
     */
    @GetMapping(value = "/getYears/{ownerId}")
    @ApiOperation(value = "根据货主ID获取经销商协议合作名单年度下拉框列表", httpMethod = "GET", notes = "根据货主ID获取经销商协议合作名单年度下拉框列表")
    public CommonResult<List<ScmXsDistributorClause>> getYears(@ApiParam(name = "ownerId", value = "货主ID", required = true) @PathVariable String ownerId) {
        return new CommonResult<List<ScmXsDistributorClause>>(true, "获取成功", scmXsDistributorClauseManager.getYears(ownerId));
    }

    /**
     * 省区协议汇总表列表(分页条件查询)数据
     *
     * @param
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/commerceAndAcceptState")
    @ApiOperation(value = "分销商协议汇总表数据列表", httpMethod = "POST", notes = "获取分销商协议汇总表列表,query必须添加货主ID进行筛选")
    public PageList<ScmXsDistributorClause> commerceAndAcceptState(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
        return scmXsDistributorClauseManager.commerceAndAcceptState(queryFilter);
    }

    /**
     * 分销商信息以新协议相关数据查询
     * *
     *
     * @return
     * @throws Exception ModelAndView
     */
    @PostMapping(value = "/latest")
    @ApiOperation(value = "分销商以及最近签订协议数据推送", httpMethod = "POST", notes = "分销商以及条款相关数据")
    public List<ScmXsDistributorClause> list1(@ApiParam(name = "businessCode", value = "商务大区code", required = false) @RequestParam(required = false) String businessCode,
                                              @ApiParam(name = "provinceCode", value = "省区code", required = false) @RequestParam(required = false) String provinceCode,
                                              @ApiParam(name = "commerceName", value = "商业名称", required = false) @RequestParam(required = false) String commerceName,
                                              @ApiParam(name = "acceptState", value = "商业认可状态", required = false) @RequestParam(required = false) String acceptState,
                                              @ApiParam(name = "commerceCode", value = "商业编码", required = false) @RequestParam(required = false) String commerceCode
    ) throws Exception {
        return scmXsDistributorClauseManager.list1(businessCode, commerceName, acceptState, provinceCode,commerceCode);
    }
}
