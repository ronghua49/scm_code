package com.winway.scm.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.winway.purchase.util.DateFormatter;
import com.winway.purchase.util.ExcelExportUtil;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.controller.BaseController;
import com.hotent.base.feign.UCFeignService;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;
import com.winway.scm.model.ScmKcWeekInventory;
import com.winway.scm.persistence.manager.ScmKcWeekInventoryManager;
import com.winway.scm.vo.ScmKcMonthInventory;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * <pre>
 * 描述：scm_kc_weekinventory 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-07-08 15:15:30
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value = "/scm/scmKcWeekInventory/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "scmKcWeekInventoryController 周库存")
public class ScmKcWeekInventoryController extends BaseController {
    @Resource
    ScmKcWeekInventoryManager scmKcWeekInventoryManager;

    @Resource
    UCFeignService ucFeignService;

    /**
     * scm_kc_weekinventory列表(分页条件查询)数据
     *
     * @param
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/list")
    @ApiOperation(value = "scm_kc_weekinventory数据列表", httpMethod = "POST", notes = "获取scm_kc_weekinventory列表")
    public PageList<ScmKcWeekInventory> list(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
        return scmKcWeekInventoryManager.query(queryFilter);
    }

    /**
     * scm_kc_weekinventory列表(分页条件查询)数据
     *
     * @param
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/monthList")
    @ApiOperation(value = "月库存", httpMethod = "POST", notes = "获取scm_kc_weekinventory列表")
    public PageList<ScmKcMonthInventory> monthList(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
        return scmKcWeekInventoryManager.monthList(queryFilter);
    }

    /**
     * scm_kc_weekinventory明细页面
     *
     * @param id
     * @return
     * @throws Exception ModelAndView
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "scm_kc_weekinventory数据详情", httpMethod = "GET", notes = "scm_kc_weekinventory数据详情")
    public ScmKcWeekInventory get(@ApiParam(name = "id", value = "业务对象主键", required = true) @PathVariable String id) throws Exception {
        return scmKcWeekInventoryManager.get(id);
    }

    /**
     * 新增scm_kc_weekinventory
     *
     * @param scmKcWeekInventory
     * @return
     * @throws Exception
     * @throws
     */
    @PostMapping(value = "save")
    @ApiOperation(value = "新增,更新scm_kc_weekinventory数据", httpMethod = "POST", notes = "新增,更新scm_kc_weekinventory数据")
    public CommonResult<String> save(@ApiParam(name = "scmKcWeekInventory", value = "scm_kc_weekinventory业务对象", required = true) @RequestBody ScmKcWeekInventory scmKcWeekInventory) throws Exception {
        String msg = "添加scm_kc_weekinventory成功";
        JsonNode user = ucFeignService.getUser(current(), "");
        String fullname = user.get("fullname").asText();
        scmKcWeekInventory.setCreateDate(new Date());
        scmKcWeekInventory.setCreatePersion(fullname);
        if (StringUtil.isEmpty(scmKcWeekInventory.getId())) {
            scmKcWeekInventoryManager.create(scmKcWeekInventory);
        } else {
            scmKcWeekInventoryManager.update(scmKcWeekInventory);
            msg = "更新scm_kc_weekinventory成功";
        }
        return new CommonResult<String>(true, msg, scmKcWeekInventory.getId());
    }

    /**
     * 删除scm_kc_weekinventory记录
     *
     * @param id
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "remove/{id}")
    @ApiOperation(value = "删除scm_kc_weekinventory记录", httpMethod = "DELETE", notes = "删除scm_kc_weekinventory记录")
    public CommonResult<String> remove(@ApiParam(name = "id", value = "业务主键", required = true) @PathVariable String id) throws Exception {
        scmKcWeekInventoryManager.remove(id);
        return new CommonResult<String>(true, "删除成功");
    }

    /**
     * 批量删除scm_kc_weekinventory记录
     *
     * @param ids
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "/removes")
    @ApiOperation(value = "批量删除scm_kc_weekinventory记录", httpMethod = "DELETE", notes = "批量删除scm_kc_weekinventory记录")
    public CommonResult<String> removes(@ApiParam(name = "ids", value = "业务主键数组,多个业务主键之间用逗号分隔", required = true) @RequestParam String... ids) throws Exception {
        scmKcWeekInventoryManager.removeByIds(ids);
        return new CommonResult<String>(true, "批量删除成功");
    }

    /**
     * 返回商业库存首页 年度 周次 时间范围
     *
     * @param
     * @return
     * @throws Exception
     * @throws
     */
    @GetMapping(value = "/base")
    @ApiOperation(value = "商业库存首页 年度 周次 时间范围", httpMethod = "GET", notes = "商业库存首页 年度 周次 时间范围")
    public CommonResult<List<Map<String, Object>>> base() throws Exception {
        List<Map<String, Object>> list = scmKcWeekInventoryManager.base();
        return new CommonResult<>(true, "获取成功", list);
    }


    /**
     * 周库存模板下载
     *
     * @param
     * @param
     * @return
     * @throws Exception
     */
    @GetMapping("/downloadExcel")
//  @ApiOperation(value = "下载模板", httpMethod = "GET", notes = "下载周库存导入模板")
    public void downloadExcel(HttpServletResponse response) throws Exception {
        String[] tableName = {"库存日期", "商业名称", "商品通用名", "规格", "批号", "本周库存", "备注"};
        List<List<String>> data = new ArrayList<List<String>>();
        List<String> br = new ArrayList<>();
        List<String> br2 = new ArrayList<>();
        br.add("2019-07-17");
        br.add("华润新龙（北京）医药有限公司");
        br.add("氨苄西林氯唑西林钠胶囊");
        br.add("0.5g*10粒/盒");
        br.add("213535");
        br.add("1500");
        br.add("测试数据");
        br2.add("备注：导入前所有数据需要加上内外边框");
        data.add(br);
        data.add(br2);
        ExcelExportUtil.download(response, "商业周库存录入模板", "商业周库存录入模板", tableName, data);
    }


    /**
     * 商业周库存表导入表格数据
     *
     * @param file excel文件
     * @param id   货主Id
     * @return
     * @throws Exception
     */
    @PostMapping("/leadExcel")
    @ApiOperation(value = "导入表格数据", httpMethod = "POST", notes = "导入表格数据")
    public CommonResult<Map<String, String>> leadExcel(@ApiParam(name = "file", value = "导入excel文件") @RequestParam(name = "file") MultipartFile file,
                                                       @ApiParam(name = "id", value = "货主id ") @RequestParam String id,
                                                       @ApiParam(name = "startDate", value = "开始日期") @RequestParam String startDate,
                                                       @ApiParam(name = "stopDate", value = "结束日期") @RequestParam String stopDate,
                                                       @ApiParam(name = "year", value = "年") @RequestParam String year,
                                                       @ApiParam(name = "month", value = "月") @RequestParam String month,
                                                       @ApiParam(name = "weekTime", value = "周次") @RequestParam String weekTime
    ) throws Exception {
        if (file.isEmpty()) {
            throw new RuntimeException("文件不存在，请重新上传");
        }
        JsonNode user = ucFeignService.getUser(current(), "");
        String fullname = user.get("fullname").asText();
        String ids = user.get("id").asText();
        ScmKcWeekInventory weekInventory = new ScmKcWeekInventory();
        weekInventory.setOwnerId(id);
        weekInventory.setStartDate(DateFormatter.parse(DateFormatter.PATTERN_yyyy_MM_dd, startDate));
        weekInventory.setStopDate(DateFormatter.parse(DateFormatter.PATTERN_yyyy_MM_dd, stopDate));
        weekInventory.setYear(year);
        weekInventory.setMonth(month);
        weekInventory.setWeek(Integer.valueOf(weekTime));
        weekInventory.setCreateDate(new Date());
        weekInventory.setCreatePersion(fullname);
        Map<String, String> s = scmKcWeekInventoryManager.readExcelFile(file, weekInventory);
        return new CommonResult<Map<String, String>>(true, "导入成功", s);
    }

}
