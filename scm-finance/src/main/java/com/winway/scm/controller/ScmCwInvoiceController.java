package com.winway.scm.controller;

import com.github.pagehelper.PageHelper;
import com.hotent.base.query.*;
import com.winway.purchase.util.ExcelExportUtil;
import com.winway.scm.model.ScmCwReturnMoney;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import com.winway.scm.persistence.manager.ScmCwInvoiceManager;
import com.winway.scm.vo.VInvoice;
import com.winway.scm.model.ScmCwInvoice;
import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.controller.BaseController;
import com.hotent.base.feign.UCFeignService;
import com.hotent.base.model.CommonResult;
import com.hotent.base.util.StringUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * <pre>
 * 描述：商业发票表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-23 18:19:25
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value = "/scm/scmCwInvoice/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "scmCwInvoiceController 商业发票表 控制器类")
public class ScmCwInvoiceController extends BaseController {
    @Resource
    ScmCwInvoiceManager scmCwInvoiceManager;
    @Resource
    UCFeignService ucFeignService;

    /**
     * 商业发票表列表(分页条件查询)数据
     *
     * @param
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/list")
    @ApiOperation(value = "商业发票表数据列表", httpMethod = "POST", notes = "获取商业发票表列表")
    public PageList<ScmCwInvoice> list(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
        return scmCwInvoiceManager.query(queryFilter);
    }

    /**
     * 商业发票表列表(分页条件查询)数据
     *
     * @param
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/history")
    @ApiOperation(value = "商业发票表数据列表", httpMethod = "POST", notes = "获取商业发票表列表")
    public PageList<VInvoice> history(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
        return scmCwInvoiceManager.history(queryFilter);
    }

    /**
     * 确认数据(批量)
     *
     * @param
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/affirmData")
    @ApiOperation(value = "确认数据(批量)", httpMethod = "POST", notes = "确认数据(批量)")
    public CommonResult<String> affirmData(@ApiParam(name = "ids", value = "业务主键数组,多个业务主键之间用逗号分隔", required = true) @RequestParam String... ids) throws Exception {
        JsonNode user = ucFeignService.getUser(current(), "");
        String fullname = user.get("fullname").asText();
        String msg = "数据确认失败！";
        for (String id : ids) {
            ScmCwInvoice invoice = scmCwInvoiceManager.get(id);
            if (invoice == null) {
                throw new RuntimeException("数据参数错误，没有查询到要确认的发票信息");
            }
            invoice.setIsAffirm("1");
            invoice.setAffirmPerson(fullname);
            invoice.setAffirmDate(new Date());
            scmCwInvoiceManager.update(invoice);
            msg = "数据确认成功！";
        }
        return new CommonResult<String>(msg);
    }

    /**
     * 商业发票表明细页面
     *
     * @param id
     * @return
     * @throws Exception ModelAndView
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "商业发票表数据详情", httpMethod = "GET", notes = "商业发票表数据详情")
    public ScmCwInvoice get(@ApiParam(name = "id", value = "业务对象主键", required = true) @PathVariable String id) throws Exception {
        return scmCwInvoiceManager.get(id);
    }

    /**
     * 商业发票表采集 调用SAP
     *
     * @param
     * @return
     * @throws Exception ModelAndView
     */
    @GetMapping(value = "/collect/{ownerId}")
    @ApiOperation(value = "商业发票表采集", httpMethod = "GET", notes = "商业发票表采集")
    public CommonResult<String> collect(@ApiParam(name = "ownerId", value = "货主ID", required = true) @PathVariable String ownerId) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        ScmCwInvoice invoice = new ScmCwInvoice();
        invoice.setVerifyType("0");
        invoice.setBalancePrice(18.3 * 20);
        invoice.setGathererDate(format.parse("2019-05-21 12:23:43"));
        invoice.setIsAffirm("0");
        invoice.setUpdateInvoiceDate(format.parse("2019-09-12 02:23:34"));
        invoice.setBusinessDivision("西南区");
        invoice.setProvince("陕西省");
        invoice.setBusinessDivisionId("0102020403");
        invoice.setProvinceId("610000");
        invoice.setCommerceCode("132153246");
        invoice.setCommerceName("陕西天士力医药有限公司");
        invoice.setContractCode("HTBH9018570931");
        invoice.setInvoiceType("0");
        invoice.setInvoiceCode("FBBM982750342");
        invoice.setCommerceId("a886");
        invoice.setInvoiceDate(format.parse("2019-03-12 04:34:45"));
        invoice.setUnitPrice(2.01);
        invoice.setOwnerId("0102");
        invoice.setMemo("hello");
        invoice.setCommerceCode("2132");
        invoice.setProductState("10ml*150袋/盒");
        invoice.setProductSum(20);
        invoice.setUnitPrice(18.3);
        invoice.setPriceTaxSum(18.3 * 20);
        invoice.setShipmentDate(format.parse("2020-12-12 04:34:45"));
        invoice.setShipmentCode("FHZL19071519558090");
        invoice.setBatchNumber("46533465");
        invoice.setDirectiveCode("ZLBH19071519090024");
        invoice.setCommonName("复方磷酸可待因口服溶液");
        invoice.setProductName("奥亭(10ml*150袋)");
        invoice.setRemittanceType("电邮");
        invoice.setGatherPersion("陆军");
        invoice.setLoseDate(format.parse("2020-12-12 12:00:00"));
        invoice.setGathererDate(new Date());

        ScmCwInvoice invoice2 = new ScmCwInvoice();
        invoice2.setVerifyType("0");
        invoice2.setBalancePrice(16.3 * 23);
        invoice2.setGathererDate(format.parse("2019-05-21 07:09:23"));
        invoice2.setIsAffirm("0");
        invoice2.setUpdateInvoiceDate(format.parse("2019-09-12 18:23:45"));
        invoice2.setBusinessDivision("华东区");
        invoice2.setBusinessDivisionId("132414");
        invoice2.setCommerceCode("1321532465");
        invoice2.setCommerceName("青岛百洋医药股份有限公司");
        invoice2.setContractCode("HTBH90185709311");
        invoice2.setInvoiceType("0");
        invoice2.setInvoiceCode("FBBM9827503421");
        invoice2.setInvoiceDate(format.parse("2019-03-12 20:32:34"));
        invoice2.setUnitPrice(4.01);
        invoice2.setOwnerId("0102");
        invoice2.setMemo("hello2");
        invoice2.setCommerceId("a168");
        invoice2.setProductState("10ml*150袋/盒");
        invoice2.setProductSum(23);
        invoice2.setUnitPrice(16.3);
        invoice2.setPriceTaxSum(16.3 * 23);
        invoice2.setShipmentDate(format.parse("2020-1-12 04:34:45"));
        invoice2.setShipmentCode("FHZL19071519558090");
        invoice2.setBatchNumber("46533465");
        invoice2.setDirectiveCode("ZLBH19071519090024");
        invoice2.setCommonName("复方磷酸可待因口服溶液2");
        invoice2.setProductName("奥亭(10ml*150袋)");
        invoice2.setRemittanceType("电邮");
        invoice2.setGatherPersion("陆军");

        scmCwInvoiceManager.create(invoice);
        scmCwInvoiceManager.create(invoice2);
        return new CommonResult<>("采集成功");
    }

    /**
     * 新增商业发票表
     *
     * @param scmCwInvoice
     * @return
     * @throws Exception
     * @throws
     */
    @PostMapping(value = "save")
    @ApiOperation(value = "新增,更新商业发票表数据", httpMethod = "POST", notes = "新增,更新商业发票表数据")
    public CommonResult<String> save(@ApiParam(name = "scmCwInvoice", value = "商业发票表业务对象", required = true) @RequestBody ScmCwInvoice scmCwInvoice) throws Exception {
        String msg = "添加商业发票表成功";
        JsonNode user = ucFeignService.getUser(current(), "");
        String fullname = user.get("fullname").asText();
        String ids = user.get("id").asText();
        ScmCwInvoice invoice = scmCwInvoiceManager.get(scmCwInvoice.getId());
        if (invoice == null) {
            scmCwInvoiceManager.create(scmCwInvoice);
        } else {
            invoice.setSetUpPersion(fullname);
            invoice.setSetUpDate(new Date());
            invoice.setUpdateInvoiceDate(scmCwInvoice.getUpdateInvoiceDate());
            scmCwInvoiceManager.update(scmCwInvoice);
            msg = "更新商业发票表成功";
        }
        return new CommonResult<String>(msg);
    }

    /**
     * 删除商业发票表记录
     *
     * @param id
     * @return
     * @throws Exception
     * @throws
     */
    @GetMapping(value = "remove/{id}")
    @ApiOperation(value = "删除商业发票表记录", httpMethod = "GET", notes = "删除商业发票表记录")
    public CommonResult<String> remove(@ApiParam(name = "id", value = "业务主键", required = true) @PathVariable String id) throws Exception {
        scmCwInvoiceManager.remove(id);
        return new CommonResult<String>(true, "删除成功");
    }

    /**
     * 批量删除商业发票表记录
     *
     * @param ids
     * @return
     * @throws Exception
     * @throws
     */
    @GetMapping(value = "/removes")
    @ApiOperation(value = "批量删除商业发票表记录", httpMethod = "GET", notes = "批量删除商业发票表记录")
    public CommonResult<String> removes(@ApiParam(name = "ids", value = "业务主键数组,多个业务主键之间用逗号分隔", required = true) @RequestParam String... ids) throws Exception {
        scmCwInvoiceManager.removeByIds(ids);
        return new CommonResult<String>(true, "批量删除成功");
    }

    @PostMapping("/findAll")
    @ApiOperation(value = "回款勾兑申请发票数据列表", httpMethod = "POST", notes = "回款勾兑申请发票数据列表")
    public PageList<ScmCwInvoice> findAll(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
        return scmCwInvoiceManager.findAll(queryFilter);
    }


    /**
     * 全部导出
     *
     * @param
     * @return
     * @throws Exception ModelAndView
     */
    @PostMapping(value = "/exportAll")
//	@ApiOperation(value="销售发票管理导出全部",httpMethod = "POST",notes = "销售发票管理导出全部")
    public void exportAll(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter, @ApiParam(name = "response", value = "响应对象") HttpServletResponse response) throws Exception {
        scmCwInvoiceManager.export(queryFilter, false, response);
    }


    /**
     * 当页导出
     *
     * @param
     * @return
     * @throws Exception ModelAndView
     */
    @PostMapping(value = "/exportPage")
//	@ApiOperation(value="销售发票管理当页导出",httpMethod = "POST",notes = "销售发票管理当页导出")
    public void exportPage(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter, @ApiParam(name = "response", value = "响应对象") HttpServletResponse response) throws Exception {
        scmCwInvoiceManager.export(queryFilter, true, response);
    }


    /**
     * 销售发票采集统计
     *
     * @param
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/sumList")
    @ApiOperation(value = "销售发票采集统计", httpMethod = "POST", notes = "销售发票采集统计")
    public PageList<Map<String, Object>> sumList(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
        PageList<Map<String, Object>> query = scmCwInvoiceManager.sumList(queryFilter);
        return query;
    }


    /**
     * 勾兑发票全部导出
     *
     * @param
     * @return
     * @throws Exception ModelAndView
     */
    @PostMapping(value = "/exportAll2")
//	@ApiOperation(value="全部导出发货任务数据",httpMethod = "POST",notes = "全部导出发货任务数据")
    public void exportAll2(@RequestBody QueryFilter queryFilter, HttpServletResponse response) throws Exception {
        scmCwInvoiceManager.export2(queryFilter, false, response);
    }


    /**
     * 勾兑发票当页导出
     *
     * @param
     * @return
     * @throws Exception ModelAndView
     */
    @PostMapping(value = "/exportPage2")
//	@ApiOperation(value="发货任务数据当页导出",httpMethod = "POST",notes = "发货任务数据当页导出")
    public void exportPage2(@RequestBody QueryFilter queryFilter, HttpServletResponse response) throws Exception {
        scmCwInvoiceManager.export2(queryFilter, true, response);
    }

    /**
     * 商业回款核销查询
     *
     * @param
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/checklist")
    @ApiOperation(value = "商业回款核销查询", httpMethod = "POST", notes = "商业回款核销查询")
    public PageList<ScmCwInvoice> checklist(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
        return scmCwInvoiceManager.checklist(queryFilter);
    }

    /**
     * 商业汇款查询导出
     *
     * @param
     * @return
     * @throws Exception ModelAndView
     */
    @PostMapping(value = "/export/checklist")
//	@ApiOperation(value="商业汇款查询导出",httpMethod = "POST",notes = "商业汇款查询导出")
    public void export3(@RequestBody QueryFilter queryFilter, HttpServletResponse response) throws Exception {
        scmCwInvoiceManager.export3(queryFilter, false, response);
    }

    /**
     * 销售发票导入模板下载
     *
     * @param
     * @param
     * @return
     * @throws Exception
     */
    @GetMapping("/downloadExcel")
//	@ApiOperation(value="下载模板", httpMethod = "GET", notes = "下载销售发票模板")
    public void downloadExcel(HttpServletResponse response) throws Exception {
        String[] tableName = { "商务部分区", "一级商", "省区", "开票单位", "原开票日期", "调整开票日期", "发票号码",
                "商品名称（开票）", "规格", "数量（瓶/只/盒）","商品编码","含税单价", "价税合计（元）", "发货日期",  "批号","发货单号", "合同号",
                 "备注"};
        List<List<String>> data = new ArrayList<List<String>>();
        List<String> br = new ArrayList<>();
        br.add("华北区");
        br.add("天祥");
        br.add("河北省");
        br.add("国药乐仁堂医药有限公司");
        br.add("2019-04-12");
        br.add("2019-06-12");

        br.add("02088569");
        br.add("奥泰灵盐酸氨基葡萄糖胶囊");
        br.add("0.75g*20粒");
        br.add("8000");
        br.add("100");

        br.add("34.23");
        br.add("273840");
        br.add("2019-04-09");
        br.add("2634");
        br.add("110038074");
        br.add("D-T1949903-3");
        br.add("6月汇款");
        data.add(br);
        ExcelExportUtil.download(response, "销售发票模板", "销售发票模板", tableName, data);
    }


    /**
     * 销售发票数据导入
     *
     * @param file excel文件
     * @param id   货主Id
     * @return
     * @throws Exception
     */
    @PostMapping("/leadExcel")
//	@ApiOperation(value="导入发票数据", httpMethod = "POST", notes = "导入发票数据")
    public CommonResult<String> leadExcel(@ApiParam(name = "file", value = "导入excel文件") MultipartFile file,
                                          @ApiParam(name = "id", value = "货主Id") @RequestParam String id) throws Exception {
        JsonNode user = ucFeignService.getUser(current(), "");
        String fullname = user.get("fullname").asText();
        String ids = user.get("id").asText();
        ScmCwInvoice invoice = new ScmCwInvoice();
        invoice.setOwnerId(id);
        invoice.setGathererDate(new Date());
        invoice.setGatherPersion(fullname);
        String s = scmCwInvoiceManager.readExcelFile(file, invoice);
        return new CommonResult<>(true, s);

    }
	/**
	 * 判断商业是否超期
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/verifExceedTime/{commerceCode}")
	@ApiOperation(value="商业发票表数据详情",httpMethod = "GET",notes = "商业发票表数据详情")
	public boolean verifExceedTime(@ApiParam(name="commerceCode",value="业务对象主键", required = true)@PathVariable String commerceCode) throws Exception{
		return scmCwInvoiceManager.verifExceedTime(commerceCode);
	}

}
