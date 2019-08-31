package com.winway.scm.controller;

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
import com.winway.scm.model.ScmCgProOrderProduct;
import com.winway.scm.persistence.dao.ScmCgContractProductDao;
import com.winway.scm.persistence.manager.ScmCgContractProductManager;
import com.winway.scm.persistence.manager.ScmCgProOrderProductManager;
import com.winway.scm.persistence.manager.ScmCgProcurementOrderManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <pre>
 * 描述：采购订单商品表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-26 13:47:44
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
@RestController
@RequestMapping(value = "/scm/scmCgProOrderProduct/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "scmCgProOrderProductController 采购订单商品")
public class ScmCgProOrderProductController extends BaseController {
    @Resource
    ScmCgProOrderProductManager scmCgProOrderProductManager;
    @Resource
    ScmCgProcurementOrderManager scmCgProcurementOrderManager;
    @Resource
    ScmCgContractProductManager scmCgContractProductManager;
    @Resource
    ScmCgContractProductDao scmCgContractProductDao;

    /**
     * 采购订单商品表列表(分页条件查询)数据
     *
     * @param request
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/list")
    @ApiOperation(value = "采购订单商品表数据列表", httpMethod = "POST", notes = "获取采购订单商品表列表")
    public PageList<ScmCgProOrderProduct> list(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
        return scmCgProOrderProductManager.query(queryFilter);
    }

    /**
     * 采购订单商品表明细页面
     *
     * @param id
     * @return
     * @throws Exception ModelAndView
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "采购订单商品表数据详情", httpMethod = "GET", notes = "采购订单商品表数据详情")
    public ScmCgProOrderProduct get(@ApiParam(name = "id", value = "业务对象主键", required = true) @PathVariable String id) throws Exception {
        return scmCgProOrderProductManager.get(id);
    }

    /**
     * 新增采购订单商品表
     *
     * @param scmCgProOrderProduct
     * @return
     * @throws Exception
     * @throws
     */
    @PostMapping(value = "save")
    @ApiOperation(value = "新增,更新采购订单商品表数据", httpMethod = "POST", notes = "新增,更新采购订单商品表数据")
    public CommonResult<String> save(@ApiParam(name = "scmCgProOrderProduct", value = "采购订单商品表业务对象", required = true) @RequestBody ScmCgProOrderProduct scmCgProOrderProduct) throws Exception {
        String msg = "采购订单商品处理成功";
        if (StringUtil.isNotEmpty(scmCgProOrderProduct.getProOrderId())) {
        	scmCgProOrderProductManager.create(scmCgProOrderProduct);
            return new CommonResult<String>(msg);
        } else {
            throw new RuntimeException("订单ID不存在");
        }

    }

    /**
     * 删除采购订单商品表记录
     *
     * @param id
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "remove/{id}")
    @ApiOperation(value = "删除采购订单商品表记录", httpMethod = "DELETE", notes = "删除采购订单商品表记录")
    public CommonResult<String> remove(@ApiParam(name = "id", value = "业务主键", required = true) @PathVariable String id) throws Exception {
        scmCgProOrderProductManager.remove(id);
        return new CommonResult<String>(true, "删除成功");
    }

    /**
     * 批量删除采购订单商品表记录
     *
     * @param ids
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "/removes")
    @ApiOperation(value = "批量删除采购订单商品表记录", httpMethod = "DELETE", notes = "批量删除采购订单商品表记录")
    public CommonResult<String> removes(@ApiParam(name = "ids", value = "业务主键数组,多个业务主键之间用逗号分隔", required = true) @RequestParam String... ids) throws Exception {
        scmCgProOrderProductManager.removeByIds(ids);
        return new CommonResult<String>(true, "批量删除成功");
    }


    /**
     * 根据订单号，商品编号，已拒数量 更新订单商品中的 已拒数量字段
     *
     * @param
     * @return
     * @throws Exception
     * @throws
     */
    @GetMapping(value = "/update")
    @ApiOperation(value = "根据订单号，商品编号，拒收数量 更新订单商品中的 已拒数量字段", httpMethod = "GET", notes = "根据订单号，商品编号，已拒数量 更新订单商品中的 已拒数量字段")
    public void updatebyParam(@ApiParam(name = "procurementCode", value = "采购单号", required = true) @RequestParam(value = "procurementCode") String procurementCode, @ApiParam(name = "productCode", value = "商品编号", required = true) @RequestParam(value = "productCode") String productCode, @ApiParam(name = "rejectSum", value = "拒收数量", required = true) @RequestParam(value = "rejectSum") String rejectSum) throws Exception {
        scmCgProOrderProductManager.updatebyParam(procurementCode, productCode, rejectSum);
    }

    /**
     * 根据订单号，商品编号，获取该商品的已拒数量
     *
     * @param
     * @return
     * @throws Exception
     * @throws
     */
    @GetMapping(value = "/get/{procurementCode}/{productCode}")
    @ApiOperation(value = "根据订单号，商品编号，获取该商品的已拒数量 ", httpMethod = "GET", notes = "根据订单号，商品编号，获取该商品的已拒数量 ")
    public String get(@ApiParam(name = "procurementCode", value = "采购单号", required = true) @PathVariable(value = "procurementCode") String procurementCode, @ApiParam(name = "productCode", value = "商品编号", required = true) @PathVariable(value = "productCode") String productCode) throws Exception {
        ScmCgProOrderProduct proOrderProduct = scmCgProOrderProductManager.getbyParam(procurementCode, productCode);
        return proOrderProduct.getRejectionTotal().toString();
    }


}
