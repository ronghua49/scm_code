package com.winway.scm.controller;

import java.util.ArrayList;
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
import com.winway.scm.model.ScmFhShipmentsDatail;
import com.winway.scm.persistence.manager.ScmFhShipmentsDatailManager;
import com.winway.scm.vo.ScmFhBatchNumber;
import com.winway.scm.vo.ScmFhShipmentsDataiAndMessageVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <pre>
 * 描述：发货明细表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-02-15 17:31:50
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value = "/scm/scmFhShipmentsDatail/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "scmFhShipmentsDatailController 发货明细")
public class ScmFhShipmentsDatailController extends BaseController {
    @Resource
    ScmFhShipmentsDatailManager scmFhShipmentsDatailManager;

    /**
     * 发货明细表列表(分页条件查询)数据
     *
     * @param request
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/list")
    @ApiOperation(value = "发货明细表数据列表", httpMethod = "POST", notes = "获取发货明细表列表")
    public PageList<ScmFhShipmentsDatail> list(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
        return scmFhShipmentsDatailManager.query(queryFilter);
    }

    /**
     * 发货明细表明细页面
     * @param id
     * @return
     * @throws Exception ModelAndView
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "发货明细表数据详情", httpMethod = "GET", notes = "发货明细表数据详情")
    public ScmFhShipmentsDatail get(@ApiParam(name = "id", value = "业务对象主键", required = true) @PathVariable String id) throws Exception {
        return scmFhShipmentsDatailManager.get(id);
    }

    /**
     * 新增或者更新发货明细表
     *
     * @param scmFhShipmentsDatail
     * @return
     * @throws Exception
     * @throws
     */
    @PostMapping(value = "save")
    @ApiOperation(value = "新增,更新发货明细表数据", httpMethod = "POST", notes = "新增,更新发货明细表数据")
    public CommonResult<String> save(@ApiParam(name="scmFhShipmentsDatail",value="发货明细表业务对象列表", required = true)@RequestBody ScmFhShipmentsDatail scmFhShipmentsDatail) throws Exception{
        String msg = "添加发货明细表成功";
        if(StringUtil.isEmpty(scmFhShipmentsDatail.getId())){
            scmFhShipmentsDatailManager.create(scmFhShipmentsDatail);
        }else{
            scmFhShipmentsDatailManager.update(scmFhShipmentsDatail);
            msg = "更新发货明细表成功";
        }
        return new CommonResult<String>(true,msg,scmFhShipmentsDatail.getId());
    }


    /**
     * 删除发货明细表记录
     *
     * @param id
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "remove/{id}")
    @ApiOperation(value = "删除发货明细表记录", httpMethod = "DELETE", notes = "删除发货明细表记录")
    public CommonResult<String> remove(@ApiParam(name = "id", value = "业务主键", required = true) @PathVariable String id) throws Exception {
        scmFhShipmentsDatailManager.remove(id);
        return new CommonResult<String>(true, "删除成功");
    }

    /**
     * 批量删除发货明细表记录
     *
     * @param ids
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "/removes")
    @ApiOperation(value = "批量删除发货明细表记录", httpMethod = "DELETE", notes = "批量删除发货明细表记录")
    public CommonResult<String> removes(@ApiParam(name = "ids", value = "业务主键数组,多个业务主键之间用逗号分隔", required = true) @RequestParam String... ids) throws Exception {
        scmFhShipmentsDatailManager.removeByIds(ids);
        return new CommonResult<String>(true, "批量删除成功");
    }

    /**
     * 根据货主ID获取发货明细年度下拉框列表
     *
     * @return
     */
    @GetMapping(value = "/getYears/{ownerId}")
    @ApiOperation(value = "根据货主ID获取发货明细年度下拉框列表", httpMethod = "GET", notes = "")
    public CommonResult<List<Map<String, Object>>> getYears(@ApiParam(name = "ownerId", value = "货主ID", required = true) @PathVariable String ownerId) {
    	List<Map<String,Object>> years = scmFhShipmentsDatailManager.getYears(ownerId);
        return new CommonResult<>(true,"获取成功",years);
    }

    /**
     * 日常发货明细查询
     *
     * @param request
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/firstList")
    @ApiOperation(value = "日常发货明细查询", httpMethod = "POST", notes = "日常发货明细查询")
    public PageList<ScmFhShipmentsDataiAndMessageVo> firstList(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
        return scmFhShipmentsDatailManager.firstList(queryFilter);
    }

    /**
     * 发货运输设置页首页调用
     *
     * @param request
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/messageList")
    @ApiOperation(value = "发货运输设置页首页调用", httpMethod = "POST", notes = "发货运输设置页首页调用")
    public PageList<ScmFhShipmentsDatail> messageList(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
        return scmFhShipmentsDatailManager.messageList(queryFilter);
    }

    /**
     * 根据商品编号获取批号信息
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/batchNumbers/{productNum}")
    @ApiOperation(value = "查询合同批号列表", httpMethod = "POST", notes = "调用远程接口查询合同批号列表")
    public CommonResult<List<ScmFhBatchNumber>> messageList(@ApiParam(name = "productNum", value = "商品编号") @PathVariable(value = "productNum") String productNum) throws Exception {
    	ScmFhBatchNumber sfbn1 = new ScmFhBatchNumber();
    	ScmFhBatchNumber sfbn2 = new ScmFhBatchNumber();
    	ScmFhBatchNumber sfbn3 = new ScmFhBatchNumber();
    	ScmFhBatchNumber sfbn4 = new ScmFhBatchNumber();
    	sfbn1.setaChange("400");
    	sfbn1.setBatchNumber("1512357");
    	sfbn1.setExpiryDate("8");
    	sfbn1.setInventoryNum("100");
    	sfbn1.setWarehouse("海口仓库");
    	sfbn1.setWarehouseCode("HK001");
    	sfbn2.setaChange("400");
    	sfbn2.setBatchNumber("1512123");
    	sfbn2.setExpiryDate("10");
    	sfbn2.setInventoryNum("200");
    	sfbn2.setWarehouse("海口仓库");
    	sfbn2.setWarehouseCode("HK001");
    	sfbn3.setaChange("400");
    	sfbn3.setBatchNumber("1512456");
    	sfbn3.setExpiryDate("19");
    	sfbn3.setInventoryNum("150");
    	sfbn3.setWarehouse("苏州仓库");
    	sfbn3.setWarehouseCode("SZ001");
    	sfbn4.setaChange("400");
    	sfbn4.setBatchNumber("1512963");
    	sfbn4.setExpiryDate("12");
    	sfbn4.setInventoryNum("400");
    	sfbn4.setWarehouse("苏州仓库");
    	sfbn4.setWarehouseCode("SZ001");
    	List<ScmFhBatchNumber> list = new ArrayList<ScmFhBatchNumber>();
    	list.add(sfbn1);
    	list.add(sfbn2);
    	list.add(sfbn3);
    	list.add(sfbn4);
    	return  new CommonResult<List<ScmFhBatchNumber>>(true,"批号获取成功",list);
    }
}
