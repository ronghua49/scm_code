package com.winway.scm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.winway.scm.persistence.manager.ScmKcBreakageManager;
import com.winway.scm.vo.ScmFhBatchNumber;
import com.winway.scm.vo.ScmGspNonconformingstockVo;
import com.winway.purchase.feign.ScmMasterDateFeignService;
import com.winway.purchase.util.ScmPuthWmsUtil;
import com.winway.purchase.vo.ScmWmsReturnData;
import com.winway.scm.model.ScmKcBreakage;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;

/**
 * 
 * <pre> 
 * 描述：不合格产品报损商品表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-23 17:00:46
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmKcBreakage/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmKcBreakageController 不合格产品报损商品表")
public class ScmKcBreakageController extends BaseController{
	@Resource
	ScmKcBreakageManager scmKcBreakageManager;
	@Resource
	ScmMasterDateFeignService scmMasterDateFeignService;
	
	/**
	 * 不合格产品报损商品表列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="不合格产品报损商品表数据列表", httpMethod = "POST", notes = "获取不合格产品报损商品表列表")
	public PageList<ScmKcBreakage> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmKcBreakageManager.query(queryFilter);
	}
	
	/**
	 * 不合格产品报损商品表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="不合格产品报损商品表数据详情",httpMethod = "GET",notes = "不合格产品报损商品表数据详情")
	public ScmKcBreakage get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmKcBreakageManager.get(id);
	}
	
    /**
	 * 新增不合格产品报损商品表
	 * @param scmKcBreakage
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新不合格产品报损商品表数据", httpMethod = "POST", notes = "新增,更新不合格产品报损商品表数据")
	public CommonResult<String> save(@ApiParam(name="scmKcBreakage",value="不合格产品报损商品表业务对象", required = true)@RequestBody ScmKcBreakage scmKcBreakage) throws Exception{
		String msg = "添加不合格产品报损商品表成功";
		if(StringUtil.isEmpty(scmKcBreakage.getId())){
			scmKcBreakageManager.create(scmKcBreakage);
		}else{
			scmKcBreakageManager.update(scmKcBreakage);
			 msg = "更新不合格产品报损商品表成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除不合格产品报损商品表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除不合格产品报损商品表记录", httpMethod = "DELETE", notes = "删除不合格产品报损商品表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmKcBreakageManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除不合格产品报损商品表记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除不合格产品报损商品表记录", httpMethod = "DELETE", notes = "批量删除不合格产品报损商品表记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmKcBreakageManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
	
    /**
     * 根据商品编号获取不合格品库存信息
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @PostMapping("/getDestroyInventory/{code}/{ownerId}/{warehouseCode}")
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
    	scmGspNonconformingstockVo.setState("1");
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
