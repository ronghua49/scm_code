package com.winway.scm.controller;

import com.alibaba.fastjson.JSONArray;
import com.winway.purchase.feign.ScmMasterDateFeignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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
import com.winway.scm.persistence.manager.ScmGspEarlyWarningSetManager;
import com.winway.scm.vo.ScmGspDrugtransportVo;
import com.winway.scm.vo.ScmGspDrupBreakageVo;
import com.winway.scm.vo.ScmGspDrupChenkVo;
import com.winway.scm.vo.ScmGspDrupDestroyVo;
import com.winway.scm.vo.ScmGspDrupRecheckVo;
import com.winway.scm.vo.ScmGspDrupReplenishVo;
import com.winway.scm.vo.ScmGspDrupReturnMsgVo;
import com.winway.scm.vo.ScmGspDrupReturnVo;
import com.winway.scm.vo.ScmGspDrupSpotCheckVo;
import com.winway.scm.vo.ScmGspNonconformingstockVo;
import com.winway.scm.vo.ScmGspdrugReleasereviewrecordVo;
import com.winway.purchase.util.ScmPuthWmsUtil;
import com.winway.purchase.vo.ScmWmsReturnData;
import com.winway.purchase.vo.WmsPageList;
import com.winway.scm.model.ScmGspEarlyWarningSet;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageBean;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;

/**
 * 
 * <pre> 
 * 描述：预警设置 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-18 15:11:34
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmGspEarlyWarningSet/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmGspEarlyWarningSetController")
public class ScmGspEarlyWarningSetController extends BaseController{
	@Resource
	ScmGspEarlyWarningSetManager scmGspEarlyWarningSetManager;

	@Resource
	ScmMasterDateFeignService masterDateFeignService;

	/**
	 * 预警设置列表(分页条件查询)数据
	 * @param
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="预警设置数据列表", httpMethod = "POST", notes = "获取预警设置列表")
	public PageList<ScmGspEarlyWarningSet> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmGspEarlyWarningSetManager.query(queryFilter);
	}
	
	/**
	 * 预警设置明细页面
	 * @param
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{ownerId}")
	@ApiOperation(value="预警设置数据详情",httpMethod = "GET",notes = "预警设置数据详情")
	public ScmGspEarlyWarningSet get(@ApiParam(name="ownerId",value="业务对象主键", required = true)@PathVariable String ownerId) throws Exception{
		return scmGspEarlyWarningSetManager.get(ownerId);
	}
	
    /**
	 * 新增预警设置
	 * @param scmGspEarlyWarningSet
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新预警设置数据", httpMethod = "POST", notes = "新增,更新预警设置数据")
	public CommonResult<String> save(@ApiParam(name="scmGspEarlyWarningSet",value="预警设置业务对象", required = true)@RequestBody ScmGspEarlyWarningSet scmGspEarlyWarningSet) throws Exception{
		String msg = "添加预警设置成功";
		if(StringUtil.isEmpty(scmGspEarlyWarningSet.getId())){
			scmGspEarlyWarningSetManager.create(scmGspEarlyWarningSet);
		}else{
			scmGspEarlyWarningSetManager.update(scmGspEarlyWarningSet);
			 msg = "更新预警设置成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除预警设置记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除预警设置记录", httpMethod = "DELETE", notes = "删除预警设置记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmGspEarlyWarningSetManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除预警设置记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除预警设置记录", httpMethod = "DELETE", notes = "批量删除预警设置记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmGspEarlyWarningSetManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
	
	
	
	
	/**
	 * 药品抽查记录
	 * @param
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="/getDrugSnapChenk")
	@ApiOperation(value = "药品抽查记录", httpMethod = "POST", notes = "药品抽查记录")
	public WmsPageList<ScmGspDrupSpotCheckVo> getDrugSnapChenk(@ApiParam(name="queryFilter",value="查询对象")@RequestBody ScmGspDrupSpotCheckVo ScmGspDrupSpotCheckVo){
		ScmWmsReturnData datPputh = ScmPuthWmsUtil.getDatPputh(JSONObject.toJSONString(ScmGspDrupSpotCheckVo), "/prolog_aomei_interface/interface/sampling/drug/scmDrugSamplingQuery.json");
		if(datPputh==null) {
			throw new RuntimeException("WMS返回数据异常");
		}
		Integer  total = datPputh.getTotal()!=null?datPputh.getTotal():datPputh.getData().size();
		List<ScmGspDrupSpotCheckVo> list=new ArrayList<ScmGspDrupSpotCheckVo>();
		for (String  text : datPputh.getData()){
			list.add(JSON.parseObject(text,ScmGspDrupSpotCheckVo.class));
		}
		WmsPageList<ScmGspDrupSpotCheckVo> wmsPageList=new WmsPageList<ScmGspDrupSpotCheckVo>();
		wmsPageList.setRows(list);
		wmsPageList.setMap(new HashMap<>(10));
		wmsPageList.setPage(ScmGspDrupSpotCheckVo.getPage()!=null?ScmGspDrupSpotCheckVo.getPage():1);
		wmsPageList.setPageSize(ScmGspDrupSpotCheckVo.getPagesize()!=null?ScmGspDrupSpotCheckVo.getPagesize():10);
		wmsPageList.setTotal(total);
		return wmsPageList;
	}
	
	@PostMapping(value="/getDrugreturnMsG")
	@ApiOperation(value = "药品采购退货记录", httpMethod = "POST", notes = "药品采购退货记录")
	public WmsPageList<ScmGspDrupReturnMsgVo> getDrugreturnMsG(@ApiParam(name="queryFilter",value="查询对象")@RequestBody ScmGspDrupReturnMsgVo scmGspDrupReturnMsgVo){
		ScmWmsReturnData datPputh = ScmPuthWmsUtil.getDatPputh(JSONObject.toJSONString(scmGspDrupReturnMsgVo), "/prolog_aomei_interface/interface/purchaseReturn/drug/scmDrugPurchaseReturnQuery.json");
		if(datPputh==null) {
			throw new RuntimeException("WMS返回数据异常");
		}
		Integer  total = datPputh.getTotal()!=null?datPputh.getTotal():datPputh.getData().size();
		List<ScmGspDrupReturnMsgVo> list=new ArrayList<ScmGspDrupReturnMsgVo>();
		for (String  text : datPputh.getData()){
			list.add(JSON.parseObject(text,ScmGspDrupReturnMsgVo.class));
		}
		WmsPageList<ScmGspDrupReturnMsgVo> wmsPageList=new WmsPageList<ScmGspDrupReturnMsgVo>();
		wmsPageList.setRows(list);
		wmsPageList.setMap(new HashMap<>(10));
		wmsPageList.setPage(scmGspDrupReturnMsgVo.getPage()!=null?scmGspDrupReturnMsgVo.getPage():1);
		wmsPageList.setPageSize(scmGspDrupReturnMsgVo.getPagesize()!=null?scmGspDrupReturnMsgVo.getPagesize():10);
		wmsPageList.setTotal(total);
		return wmsPageList;
	}
	
	
	
	
	/**
	 * 药品出库复核记录
	 * @param
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="/getDrugOutboundreview")
	@ApiOperation(value = "药品出库复核记录", httpMethod = "POST", notes = "药品出库复核记录，对接WMS接口")
	public WmsPageList<ScmGspdrugReleasereviewrecordVo> getDrugOutboundreview(@ApiParam(name="queryFilter",value="查询对象")@RequestBody ScmGspdrugReleasereviewrecordVo scmGspdrugReleasereviewrecordVo){
		ScmWmsReturnData datPputh = ScmPuthWmsUtil.getDatPputh(JSONObject.toJSONString(scmGspdrugReleasereviewrecordVo), "/prolog_aomei_interface/interface/review/outbound/scmOutboundReviewQuery.json");
		if(datPputh==null) {
			throw new RuntimeException("WMS返回数据异常");
		}
		Integer  total = datPputh.getTotal()!=null?datPputh.getTotal():datPputh.getData().size();
		List<ScmGspdrugReleasereviewrecordVo> list=new ArrayList<ScmGspdrugReleasereviewrecordVo>();

		for (String  text : datPputh.getData()){
			ScmGspdrugReleasereviewrecordVo scmGspdrugReleasereviewrecordVo1 = JSON.parseObject(text, ScmGspdrugReleasereviewrecordVo.class);
			String productByName = masterDateFeignService.getProductByName(scmGspdrugReleasereviewrecordVo1.getItemname());
			JSONObject jsonObject = JSON.parseObject(productByName);
			String productLineName = "";
			if(jsonObject!=null){
				productLineName =(String) jsonObject.get("productLineName");
			}
			scmGspdrugReleasereviewrecordVo1.setFmodel(productLineName);
			list.add(scmGspdrugReleasereviewrecordVo1);
		}
		WmsPageList<ScmGspdrugReleasereviewrecordVo> wmsPageList=new WmsPageList<ScmGspdrugReleasereviewrecordVo>();
		wmsPageList.setRows(list);
		wmsPageList.setMap(new HashMap<>(10));
		wmsPageList.setPage(scmGspdrugReleasereviewrecordVo.getPage()!=null?scmGspdrugReleasereviewrecordVo.getPage():1);
		wmsPageList.setPageSize(scmGspdrugReleasereviewrecordVo.getPagesize()!=null?scmGspdrugReleasereviewrecordVo.getPagesize():10);
		wmsPageList.setTotal(total);
		return wmsPageList;
	}
	/**
	 * 药品运输记录
	 * @param
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="/getDrugTransport")
	@ApiOperation(value = "药品运输记录", httpMethod = "POST", notes = "药品运输记录，对接WMS接口")
	public WmsPageList<ScmGspDrugtransportVo> getDrugTransport(@ApiParam(name="queryFilter",value="查询对象")@RequestBody ScmGspDrugtransportVo scmGspDrugtransportVo){
		ScmWmsReturnData datPputh = ScmPuthWmsUtil.getDatPputh(JSONObject.toJSONString(scmGspDrugtransportVo), "/prolog_aomei_interface/interface/transport/records/scmTransportRecordsQuery.json");
		if(datPputh==null) {
			throw new RuntimeException("WMS返回数据异常");
		}
		Integer  total = datPputh.getTotal()!=null?datPputh.getTotal():datPputh.getData().size();
		List<ScmGspDrugtransportVo> list=new ArrayList<ScmGspDrugtransportVo>();
		for (String  text : datPputh.getData()){
			list.add(JSON.parseObject(text,ScmGspDrugtransportVo.class));
		}
		WmsPageList<ScmGspDrugtransportVo> wmsPageList=new WmsPageList<ScmGspDrugtransportVo>();
		wmsPageList.setRows(list);
		wmsPageList.setMap(new HashMap<>(10));
		wmsPageList.setPage(scmGspDrugtransportVo.getPage()!=null?scmGspDrugtransportVo.getPage():1);
		wmsPageList.setPageSize(scmGspDrugtransportVo.getPagesize()!=null?scmGspDrugtransportVo.getPagesize():10);
		wmsPageList.setTotal(total);
		return wmsPageList;
	}
	@PostMapping(value="/getDrugReturn")
	@ApiOperation(value = "药品销售退回验收记录", httpMethod = "POST", notes = "药品销售退回验收记录，对接WMS接口")
	public WmsPageList<ScmGspDrupReturnVo> getDrugReturn(@ApiParam(name="queryFilter",value="查询对象")@RequestBody ScmGspDrupReturnVo scmGspDrupReturnVo){
		ScmWmsReturnData datPputh = ScmPuthWmsUtil.getDatPputh(JSONObject.toJSONString(scmGspDrupReturnVo), "/prolog_aomei_interface/interface/outCheck/outbound/scmOutboundCheckQuery.json");
		if(datPputh==null) {
			throw new RuntimeException("WMS返回数据异常");
		}
		Integer  total = datPputh.getTotal()!=null?datPputh.getTotal():datPputh.getData().size();
		List<ScmGspDrupReturnVo> list=new ArrayList<ScmGspDrupReturnVo>();
		for (String  text : datPputh.getData()){
			ScmGspDrupReturnVo parseObject = JSON.parseObject(text,ScmGspDrupReturnVo.class);
			list.add(JSON.parseObject(text,ScmGspDrupReturnVo.class));
		}
		WmsPageList<ScmGspDrupReturnVo> wmsPageList=new WmsPageList<ScmGspDrupReturnVo>();
		wmsPageList.setRows(list);
		wmsPageList.setMap(new HashMap<>(10));
		wmsPageList.setPage(scmGspDrupReturnVo.getPage()!=null?scmGspDrupReturnVo.getPage():1);
		wmsPageList.setPageSize(scmGspDrupReturnVo.getPagesize()!=null?scmGspDrupReturnVo.getPagesize():10);
		wmsPageList.setTotal(total);
		return wmsPageList;
	}
	
	@PostMapping(value="/getDrugrecheck")
	@ApiOperation(value = "药品质量复检记录", httpMethod = "POST", notes = "药品质量复检记录，对接WMS接口")
	public WmsPageList<ScmGspDrupRecheckVo> getDrugrecheck(@ApiParam(name="queryFilter",value="查询对象")@RequestBody ScmGspDrupRecheckVo scmGspDrupRecheckVo){
		ScmWmsReturnData datPputh = ScmPuthWmsUtil.getDatPputh(JSONObject.toJSONString(scmGspDrupRecheckVo), "/prolog_aomei_interface/interface/quality/reexamination/scmItemQualityReexaminationQuery.json");
		if(datPputh==null) {
			throw new RuntimeException("WMS返回数据异常");
		}
		Integer  total = datPputh.getTotal()!=null?datPputh.getTotal():datPputh.getData().size();
		List<ScmGspDrupRecheckVo> list=new ArrayList<ScmGspDrupRecheckVo>();
		for (String  text : datPputh.getData()){
			list.add(JSON.parseObject(text,ScmGspDrupRecheckVo.class));
		}
		WmsPageList<ScmGspDrupRecheckVo> wmsPageList=new WmsPageList<ScmGspDrupRecheckVo>();
		wmsPageList.setRows(list);
		wmsPageList.setMap(new HashMap<>(10));
		wmsPageList.setPage(scmGspDrupRecheckVo.getPage()!=null?scmGspDrupRecheckVo.getPage():1);
		wmsPageList.setPageSize(scmGspDrupRecheckVo.getPagesize()!=null?scmGspDrupRecheckVo.getPagesize():10);
		wmsPageList.setTotal(total);
		return wmsPageList;
	}
	
	@PostMapping(value="/getDrugNonconformingstock")
	@ApiOperation(value = "药品不合格品库存台帐", httpMethod = "POST", notes = "药品不合格品库存台帐，对接WMS接口")
	public WmsPageList<ScmGspNonconformingstockVo> getDrugNonconformingstock(@ApiParam(name="queryFilter",value="查询对象")@RequestBody ScmGspNonconformingstockVo scmGspNonconformingstockVo){
		scmGspNonconformingstockVo.setState("0");
		ScmWmsReturnData datPputh = ScmPuthWmsUtil.getDatPputh(JSONObject.toJSONString(scmGspNonconformingstockVo), "/prolog_aomei_interface/interface/itemLedger/unqualified/scmItemUnqualifiedLedgerQuery.json");
		if(datPputh==null) {
			throw new RuntimeException("WMS返回数据异常");
		}
		Integer  total = datPputh.getTotal()!=null?datPputh.getTotal():datPputh.getData().size();
		List<ScmGspNonconformingstockVo> list=new ArrayList<ScmGspNonconformingstockVo>();
		for (String  text : datPputh.getData()){
			list.add(JSON.parseObject(text,ScmGspNonconformingstockVo.class));
		}
		WmsPageList<ScmGspNonconformingstockVo> wmsPageList=new WmsPageList<ScmGspNonconformingstockVo>();
		wmsPageList.setRows(list);
		wmsPageList.setMap(new HashMap<>(10));
		wmsPageList.setPage(scmGspNonconformingstockVo.getPage()!=null?scmGspNonconformingstockVo.getPage():1);
		wmsPageList.setPageSize(scmGspNonconformingstockVo.getPagesize()!=null?scmGspNonconformingstockVo.getPagesize():10);
		wmsPageList.setTotal(total);
		return wmsPageList;
	}
	
	
	@PostMapping(value="/getDrugdestroy")
	@ApiOperation(value = "不合格药品销毁记录", httpMethod = "POST", notes = "不合格药品销毁记录，对接WMS接口")
	public WmsPageList<ScmGspDrupDestroyVo> getDrugdestroy(@ApiParam(name="queryFilter",value="查询对象")@RequestBody ScmGspDrupDestroyVo scmGspDrupDestroyVo){
		ScmWmsReturnData datPputh = ScmPuthWmsUtil.getDatPputh(JSONObject.toJSONString(scmGspDrupDestroyVo), "/prolog_aomei_interface/interface/itemUnqualified/destruction/scmItemUnqualifiedDestructionQuery.json");
		if(datPputh==null) {
			throw new RuntimeException("WMS返回数据异常");
		}
		Integer  total = datPputh.getTotal()!=null?datPputh.getTotal():datPputh.getData().size();
		List<ScmGspDrupDestroyVo> list=new ArrayList<ScmGspDrupDestroyVo>();
		for (String  text : datPputh.getData()){
			list.add(JSON.parseObject(text,ScmGspDrupDestroyVo.class));
		}
		WmsPageList<ScmGspDrupDestroyVo> wmsPageList=new WmsPageList<ScmGspDrupDestroyVo>();
		wmsPageList.setRows(list);
		wmsPageList.setMap(new HashMap<>(10));
		wmsPageList.setPage(scmGspDrupDestroyVo.getPage()!=null?scmGspDrupDestroyVo.getPage():1);
		wmsPageList.setPageSize(scmGspDrupDestroyVo.getPagesize()!=null?scmGspDrupDestroyVo.getPagesize():1);
		wmsPageList.setTotal(total);
		return wmsPageList;
	}
	
	@PostMapping(value="/getDrugreplenish")
	@ApiOperation(value = "药品进销存查询", httpMethod = "POST", notes = "药品进销存查询，对接WMS接口")
	public WmsPageList<ScmGspDrupReplenishVo> getDrugreplenish(@ApiParam(name="queryFilter",value="查询对象")@RequestBody ScmGspDrupReplenishVo scmGspDrupReplenishVo){
		ScmWmsReturnData datPputh = ScmPuthWmsUtil.getDatPputh(JSONObject.toJSONString(scmGspDrupReplenishVo), "/prolog_aomei_interface/interface/itemSales/purchase/scmItemPurchaseSaleInventoryQuery.json");
		if(datPputh==null) {
			throw new RuntimeException("WMS返回数据异常");
		}
		Integer  total = datPputh.getTotal()!=null?datPputh.getTotal():datPputh.getData().size();
		List<ScmGspDrupReplenishVo> list=new ArrayList<ScmGspDrupReplenishVo>();
		for (String  text : datPputh.getData()){
			list.add(JSON.parseObject(text,ScmGspDrupReplenishVo.class));
		}
		WmsPageList<ScmGspDrupReplenishVo> wmsPageList=new WmsPageList<ScmGspDrupReplenishVo>();
		wmsPageList.setRows(list);
		wmsPageList.setMap(new HashMap<>(10));
		wmsPageList.setPage(scmGspDrupReplenishVo.getPage()!=null?scmGspDrupReplenishVo.getPage():1);
		wmsPageList.setPageSize(scmGspDrupReplenishVo.getPagesize()!=null?scmGspDrupReplenishVo.getPagesize():10);
		wmsPageList.setTotal(total);
		return wmsPageList;
	}
	
	@PostMapping(value="/getDrugBreakage")
	@ApiOperation(value = "不合格药品报损记录", httpMethod = "POST", notes = "不合格药品报损记录，对接WMS接口")
	public WmsPageList<ScmGspDrupBreakageVo> getDrugBreakage(@ApiParam(name="queryFilter",value="查询对象")@RequestBody ScmGspDrupBreakageVo scmGspDrupBreakageVo){
		ScmWmsReturnData datPputh = ScmPuthWmsUtil.getDatPputh(JSONObject.toJSONString(scmGspDrupBreakageVo), "/prolog_aomei_interface/interface/itemLoss/unqualified/scmItemUnqualifiedLossQuery.json");
		if(datPputh==null) {
			throw new RuntimeException("WMS返回数据异常");
		}
		Integer  total = datPputh.getTotal()!=null?datPputh.getTotal():datPputh.getData().size();
		List<ScmGspDrupBreakageVo> list=new ArrayList<ScmGspDrupBreakageVo>();
		for (String  text : datPputh.getData()){
			list.add(JSON.parseObject(text,ScmGspDrupBreakageVo.class));
		}
		WmsPageList<ScmGspDrupBreakageVo> wmsPageList=new WmsPageList<ScmGspDrupBreakageVo>();
		wmsPageList.setRows(list);
		wmsPageList.setMap(new HashMap<>(10));
		wmsPageList.setPage(scmGspDrupBreakageVo.getPage()!=null?scmGspDrupBreakageVo.getPage():1);
		wmsPageList.setPageSize(scmGspDrupBreakageVo.getPagesize()!=null?scmGspDrupBreakageVo.getPagesize():10);
		wmsPageList.setTotal(total);
		return wmsPageList;
	}
	
	@PostMapping(value="/getDrugCheck")
	@ApiOperation(value = "药品质量养护检查记录", httpMethod = "POST", notes = "药品质量养护检查记录，对接WMS接口")
	public WmsPageList<ScmGspDrupChenkVo> getDrugCheck(@ApiParam(name="queryFilter",value="查询对象")@RequestBody ScmGspDrupChenkVo scmGspDrupChenkVo){
		ScmWmsReturnData datPputh = ScmPuthWmsUtil.getDatPputh(JSONObject.toJSONString(scmGspDrupChenkVo), "/prolog_aomei_interface/interface/curing/check/scmItemCuringCheckQuery.json");
		if(datPputh==null) {
			throw new RuntimeException("WMS返回数据异常");
		}
		Integer  total = datPputh.getTotal()!=null?datPputh.getTotal():datPputh.getData().size();
		List<ScmGspDrupChenkVo> list=new ArrayList<ScmGspDrupChenkVo>();
		for (String  text : datPputh.getData()){
			list.add(JSON.parseObject(text,ScmGspDrupChenkVo.class));
		}
		WmsPageList<ScmGspDrupChenkVo> wmsPageList=new WmsPageList<ScmGspDrupChenkVo>();
		wmsPageList.setRows(list);
		wmsPageList.setMap(new HashMap<>(10));
		wmsPageList.setPage(scmGspDrupChenkVo.getPage()!=null?scmGspDrupChenkVo.getPage():1);
		wmsPageList.setPageSize(scmGspDrupChenkVo.getPagesize()!=null?scmGspDrupChenkVo.getPagesize():10);
		wmsPageList.setTotal(total);
		return wmsPageList;
	}
	
}
