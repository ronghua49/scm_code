package com.winway.scm.controller;

import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;
import com.winway.scm.model.ScmZdTenderType;
import com.winway.scm.persistence.manager.ScmZdTenderTypeManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <pre>
 * 描述：招标性质 控制器类
 * 构建组：x7
 * 作者:FangGan
 * 邮箱:libayiv@163.com
 * 日期:2019-08-20 14:47:48
 * 版权：深圳万威科技有限公司
 * </pre>
 */
@RestController
@RequestMapping(value = "/scm/scmZdTenderType/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "scmZdTenderTypeController 招标性质管理")
public class ScmZdTenderTypeController extends BaseController {
    @Resource
    ScmZdTenderTypeManager scmZdTenderTypeManager;

    /**
     * 招标性质列表(分页条件查询)数据
     *
     * @return
     * @throws Exception
     * @throws
     */
    @PostMapping("/list")
    @ApiOperation(value = "招标性质数据列表", httpMethod = "POST", notes = "获取招标性质列表")
    public PageList<ScmZdTenderType> list(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
        return scmZdTenderTypeManager.query(queryFilter);
    }

    /**
     * 招标性质明细页面
     *
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "招标性质数据详情", httpMethod = "GET", notes = "招标性质数据详情")
    public ScmZdTenderType get(@ApiParam(name = "id", value = "业务对象主键", required = true) @PathVariable String id) throws Exception {
        return scmZdTenderTypeManager.get(id);
    }

    /**
     * 新增招标性质
     *
     * @param scmZdTenderType
     * @return
     * @throws Exception
     * @throws
     */
    @PostMapping(value = "save")
    @ApiOperation(value = "新增,更新招标性质数据", httpMethod = "POST", notes = "新增,更新招标性质数据")
    public CommonResult<String> save(@ApiParam(name = "scmZdTenderType", value = "招标性质业务对象", required = true) @RequestBody ScmZdTenderType scmZdTenderType) throws Exception {
        String msg = "添加招标性质成功";
        if (StringUtil.isEmpty(scmZdTenderType.getId())) {
            scmZdTenderTypeManager.create(scmZdTenderType);
        } else {
            scmZdTenderTypeManager.update(scmZdTenderType);
            msg = "更新招标性质成功";
        }
        return new CommonResult<>(msg);
    }

    /**
     * 删除招标性质记录
     *
     * @param id
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "remove/{id}")
    @ApiOperation(value = "删除招标性质记录", httpMethod = "DELETE", notes = "删除招标性质记录")
    public CommonResult<String> remove(@ApiParam(name = "id", value = "业务主键", required = true) @PathVariable String id) throws Exception {
        scmZdTenderTypeManager.remove(id);
        return new CommonResult<>(true, "删除成功");
    }

    /**
     * 批量删除招标性质记录
     *
     * @param ids
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "/removes")
    @ApiOperation(value = "批量删除招标性质记录", httpMethod = "DELETE", notes = "批量删除招标性质记录")
    public CommonResult<String> removes(@ApiParam(name = "ids", value = "业务主键数组,多个业务主键之间用逗号分隔", required = true) @RequestParam String... ids) throws Exception {
        scmZdTenderTypeManager.removeByIds(ids);
        return new CommonResult<>(true, "批量删除成功");
    }
}
