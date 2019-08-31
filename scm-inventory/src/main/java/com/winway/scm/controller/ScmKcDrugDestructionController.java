package com.winway.scm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.annotation.Resource;

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
import com.winway.scm.persistence.manager.ScmKcDrugDestructionManager;
import com.winway.scm.vo.ScmFhBatchNumber;
import com.winway.scm.vo.ScmGspNonconformingstockVo;
import com.winway.purchase.feign.ScmMasterDateFeignService;
import com.winway.purchase.util.ScmPuthWmsUtil;
import com.winway.purchase.vo.ScmWmsReturnData;
import com.winway.scm.model.ScmKcDrugDestruction;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 描述：不合格药品销毁明细表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-13 15:06:38
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value = "/scm/scmKcDrugDestruction/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "scmKcDrugDestructionController")
public class ScmKcDrugDestructionController extends BaseController {
    @Resource
    ScmKcDrugDestructionManager scmKcDrugDestructionManager;
    @Resource
    ScmMasterDateFeignService scmMasterDateFeignService;

    /**
     * 不合格药品销毁明细表列表(分页条件查询)数据
     *
     * @param request
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/list")
    @ApiOperation(value = "不合格药品销毁明细表数据列表", httpMethod = "POST", notes = "获取不合格药品销毁明细表列表")
    public PageList<ScmKcDrugDestruction> list(@ApiParam(name = "queryFilter", value = "查询对象") @RequestBody QueryFilter queryFilter) throws Exception {
        return scmKcDrugDestructionManager.query(queryFilter);
    }

    /**
     * 不合格药品销毁明细表明细页面
     *
     * @param id
     * @return
     * @throws Exception ModelAndView
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "不合格药品销毁明细表数据详情", httpMethod = "GET", notes = "不合格药品销毁明细表数据详情")
    public ScmKcDrugDestruction get(@ApiParam(name = "id", value = "业务对象主键", required = true) @PathVariable String id) throws Exception {
        return scmKcDrugDestructionManager.get(id);
    }

    /**
     * 新增不合格药品销毁明细表
     *
     * @param scmKcDrugDestruction
     * @return
     * @throws Exception
     * @throws
     */
    @PostMapping(value = "save")
    @ApiOperation(value = "新增,更新不合格药品销毁明细表数据", httpMethod = "POST", notes = "新增,更新不合格药品销毁明细表数据")
    public CommonResult<String> save(@ApiParam(name = "scmKcDrugDestruction", value = "不合格药品销毁明细表业务对象", required = true) @RequestBody ScmKcDrugDestruction scmKcDrugDestruction) throws Exception {
        String msg = "添加不合格药品销毁明细表成功";
        if (StringUtil.isEmpty(scmKcDrugDestruction.getId())) {
            scmKcDrugDestructionManager.create(scmKcDrugDestruction);
        } else {
            scmKcDrugDestructionManager.update(scmKcDrugDestruction);
            msg = "更新不合格药品销毁明细表成功";
        }
        return new CommonResult<String>(msg);
    }

    /**
     * 删除不合格药品销毁明细表记录
     *
     * @param id
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "remove/{id}")
    @ApiOperation(value = "删除不合格药品销毁明细表记录", httpMethod = "DELETE", notes = "删除不合格药品销毁明细表记录")
    public CommonResult<String> remove(@ApiParam(name = "id", value = "业务主键", required = true) @PathVariable String id) throws Exception {
        scmKcDrugDestructionManager.remove(id);
        return new CommonResult<String>(true, "删除成功");
    }

    /**
     * 批量删除不合格药品销毁明细表记录
     *
     * @param ids
     * @return
     * @throws Exception
     * @throws
     */
    @DeleteMapping(value = "/removes")
    @ApiOperation(value = "批量删除不合格药品销毁明细表记录", httpMethod = "DELETE", notes = "批量删除不合格药品销毁明细表记录")
    public CommonResult<String> removes(@ApiParam(name = "ids", value = "业务主键数组,多个业务主键之间用逗号分隔", required = true) @RequestParam String... ids) throws Exception {
        scmKcDrugDestructionManager.removeByIds(ids);
        return new CommonResult<String>(true, "批量删除成功");
    }

    /**
     * 从wms 获取不合格药品
     *
     * @param ownerId
     * @return
     * @throws Exception ModelAndView
     */
    @GetMapping(value = "/firstList/{ownerId}")
    @ApiOperation(value = "不合格药品数据", httpMethod = "GET", notes = "不合格药品销数据详情")
    public List<ScmKcDrugDestruction> firstList(@ApiParam(name = "id", value = "业务对象主键", required = true) @PathVariable String ownerId) throws Exception {
        ScmKcDrugDestruction destruction = new ScmKcDrugDestruction();
        destruction.setBatchNum("001");
        destruction.setExpiryDate(new SimpleDateFormat("yyyy-MM-dd").parse("2019-09-23"));
        destruction.setManufacturer("澳美制药");
        destruction.setMedicineType("普通药品");
        destruction.setProductCode("YP001");
        destruction.setProductName("双黄连胶囊");
        destruction.setProductState("20支/盒");
        destruction.setUnitPrice(12.5);
        destruction.setaChage(4);
        destruction.setNumber(2);
        destruction.setDestroyWay("火烧");
        destruction.setUnqualifiedReason("质量问题");
        destruction.setCommonName("双环连");
        destruction.setAgentType("胶囊类");
        destruction.setApprovalCode("84327dfb");
        destruction.setMedicineClassify("感冒类");
        destruction.setBusinessScope("儿童，妇女");
        destruction.setUnit("盒");
        ArrayList<ScmKcDrugDestruction> scmKcDrugDestructions = new ArrayList<>();
        scmKcDrugDestructions.add(destruction);
        return scmKcDrugDestructions;
    }
    
    /**
     * 根据商品编号获取不合格品库存信息
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/getBelowInventory/{code}/{ownerId}/{warehouseCode}")
    @ApiOperation(value = "查询合同批号列表", httpMethod = "POST", notes = "调用远程接口查询合同批号列表")
    public CommonResult<List<ScmFhBatchNumber>> batchNumbers(@ApiParam(name = "code", value = "商品编号字码") @PathVariable(value = "code") String code,
    		@ApiParam(name = "ownerId", value = "货主ID") @PathVariable(value = "ownerId") String ownerId,
    		@ApiParam(name = "warehouseCode", value = "仓库编号") @PathVariable(value = "warehouseCode") String warehouseCode) throws Exception {
    	//根据产品编号查询药品类型
    	CommonResult<String> productMessageByCode = scmMasterDateFeignService.getProductMessageByCode(code);
    	if(productMessageByCode == null) {
    		throw new RuntimeException("商品信息异常");
    	}
    	if(productMessageByCode.getShortMessage() == null ||"".equals(productMessageByCode.getShortMessage())) {
    		throw new RuntimeException("商品信息异常");
    	}
    	JSONObject parseObject2 = JSON.parseObject(productMessageByCode.getShortMessage());
    	String MedicineType = parseObject2.getString("MedicineType");
    	String itemType = "1";
    	if("特殊管理药品".equals(MedicineType)) {
    		itemType = "2";
    	}else{
    		itemType = "1";
    	}
    	ScmGspNonconformingstockVo scmGspNonconformingstockVo = new ScmGspNonconformingstockVo();
    	scmGspNonconformingstockVo.setPage(1);
    	scmGspNonconformingstockVo.setPagesize(Integer.MAX_VALUE);
    	scmGspNonconformingstockVo.setState("2");
    	//特药普药
    	scmGspNonconformingstockVo.setItemtype(itemType);
    	scmGspNonconformingstockVo.setItemcode(code);
    	scmGspNonconformingstockVo.setOwnerId(ownerId);
		ScmWmsReturnData datPputh = ScmPuthWmsUtil.getDatPputh(JSONObject.toJSONString(scmGspNonconformingstockVo), "/prolog_aomei_interface/interface/itemLedger/unqualified/scmItemUnqualifiedLedgerQuery.json");
		if(datPputh==null) {
			throw new RuntimeException("WMS返回数据异常");
		}
    	List<String> data = datPputh.getData();
    	List<ScmFhBatchNumber> list = new ArrayList<ScmFhBatchNumber>();
    	for (String string : data) {
    		ScmGspNonconformingstockVo parseObject = JSON.parseObject(string,ScmGspNonconformingstockVo.class);
    		ScmFhBatchNumber scmFhBatchNumber = new ScmFhBatchNumber();
    		//件装量
    		scmFhBatchNumber.setaChange("0");
    		//批号
    		scmFhBatchNumber.setBatchNum(parseObject.getLotno());
    		//失效日期
    		scmFhBatchNumber.setExpiryDate(parseObject.getExpdate());
    		//生产日期
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		scmFhBatchNumber.setProductionDate(sdf.format(parseObject.getYielddate()));
    		//数量
    		scmFhBatchNumber.setQuantityInStock(parseObject.getBhgqty());
    		//仓库
    		scmFhBatchNumber.setWarehouse("");
    		//仓库编号
    		scmFhBatchNumber.setWarehouseCode("");
    		//仓库类型
    		scmFhBatchNumber.setWarehouseType("");
    		//箱体类型(Y:原箱,N:非原箱)
    		scmFhBatchNumber.setXtlx("");
			list.add(scmFhBatchNumber);
		}
    	return new CommonResult<List<ScmFhBatchNumber>>(true,"批号获取成功",list);
    }
    
    

}
