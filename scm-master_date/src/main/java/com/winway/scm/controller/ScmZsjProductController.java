package com.winway.scm.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.annotation.Workflow;
import com.hotent.base.controller.BaseController;
import com.hotent.base.feign.UCFeignService;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.JsonUtil;
import com.hotent.base.util.StringUtil;
import com.winway.scm.model.ScmZsjCommerce;
import com.winway.scm.model.ScmZsjProduct;
import com.winway.scm.persistence.manager.ScmZsjProductManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * <pre> 
 * 描述：商品表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmZsjProduct/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmZsjProductController 商品")
public class ScmZsjProductController extends BaseController{
	@Resource
	ScmZsjProductManager scmZsjProductManager;
	@Resource
	UCFeignService ucFeignService;
	/**
	 * 商品表列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="商品表数据列表", httpMethod = "POST", notes = "获取商品表列表")
	public PageList<ScmZsjProduct> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmZsjProductManager.query(queryFilter);
	}
	/**
	 * 商品表列表(分页条件查询)数据
	 * @param
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@GetMapping("/listAndAchage")
	@ApiOperation(value="商品表数据列表", httpMethod = "GET", notes = "获取商品表列表")
	public List<String> listAndAchage(@ApiParam(name="productCode",value="查询对象",required = true)@RequestParam(value = "productCode") String productCode) throws Exception{
		List<String> listAndAchage = scmZsjProductManager.listAndAchage(productCode);
		return listAndAchage;
	}
	
	/**
	 * 商品表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="商品表数据详情",httpMethod = "GET",notes = "商品表数据详情")
	public ScmZsjProduct get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmZsjProductManager.get(id);
	}
	/**
	 * 商品表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/getByApprovalId/{approvalId}")
	@ApiOperation(value="商品表数据详情",httpMethod = "GET",notes = "商品表数据详情")
	public CommonResult<ScmZsjProduct> getByApprovalId(@ApiParam(name="approvalId",value="业务对象主键", required = true)@PathVariable String approvalId) throws Exception{
		ScmZsjProduct byApprovalId = scmZsjProductManager.getByApprovalId(approvalId);
		if(byApprovalId == null) {
			throw new RuntimeException("未查询到数据");
		}else{
			return new CommonResult<ScmZsjProduct>(true,"获取成功",byApprovalId);
		}
	}
	
	/**
	 * 商品表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/getProductByCode")
	@ApiOperation(value="商品表数据详情",httpMethod = "GET",notes = "商品表数据详情")
	public String getProductByCode(@ApiParam(name="productCode",value="业务对象主键", required = true)@RequestParam String productCode) throws Exception{
		ScmZsjProduct s = scmZsjProductManager.getProductByCode(productCode);
		String jsonString = JSON.toJSONString(s);
		return jsonString;
	}
	
    /**
	 * 新增商品表
	 * @param scmZsjProduct
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新商品表数据", httpMethod = "POST", notes = "新增,更新商品表数据")
	public CommonResult<String> save(@ApiParam(name="scmZsjProduct",value="商品表业务对象", required = true)@RequestBody ScmZsjProduct scmZsjProduct) throws Exception{
		String msg = "添加商品表成功";
		scmZsjProductManager.getByNameOrId(scmZsjProduct);
		JsonNode user = ucFeignService.getUser(current(), "");
		String userName = user.get("fullname").asText();
		scmZsjProduct.setOperatorName(userName);
		scmZsjProduct.setOperatorDate(new Date());
		scmZsjProduct.setApprovalState("2");
		if(StringUtil.isEmpty(scmZsjProduct.getId())){
			scmZsjProductManager.create(scmZsjProduct);
		}else{
			scmZsjProductManager.update(scmZsjProduct);
			msg = "更新商品表成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除商品表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除商品表记录", httpMethod = "DELETE", notes = "删除商品表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmZsjProductManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除商品表记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除商品表记录", httpMethod = "DELETE", notes = "批量删除商品表记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmZsjProductManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}

	/**
	 * 商品首营下拉框带首营状态
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/getByOwnerId/{ownerId}")
	@ApiOperation(value="商品首营下拉框",httpMethod = "GET",notes = "商品首营下拉框带首营状态")
	public List<ScmZsjProduct> getByProduct(@ApiParam(name="ownerId",value="业务对象主键", required = true)@PathVariable String ownerId) throws Exception{
		return scmZsjProductManager.getByProduct(ownerId);
	}
	
	/**
	 * 商品表首营列表(分页条件查询)数据
	 * 与商品首营关联查询,并携带首营状态
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/firstList")
	@ApiOperation(value="商品表首营列表", httpMethod = "POST", notes = "与商品首营关联查询,并携带首营状态")
	public PageList<ScmZsjProduct> firstList(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return	scmZsjProductManager.firstList(queryFilter);
	}
	
	/**
	 * 商品信息下拉框,带首营状态
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@GetMapping("/downBox/{ownerId}")
	@ApiOperation(value="商品表首营数据列表", httpMethod = "GET", notes = "获取商业表列表")
	public List<ScmZsjProduct> downBox(@ApiParam(name="ownerId",value="货主ID",required = true)@PathVariable String ownerId) throws Exception{
		return scmZsjProductManager.downBox(ownerId);
	}
	/**
	 * 商品信息下拉框,带首营状态
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@GetMapping("/downBoxApplySuccess/{ownerId}/{SupplierId}")
	@ApiOperation(value="商品信息下拉框,首营审批通过", httpMethod = "GET", notes = "商品信息下拉框,首营审批通过")
	public CommonResult<List<ScmZsjProduct>>  downBoxApplySuccess(@ApiParam(name="ownerId",value="货主ID",required = true)@PathVariable(value="ownerId") String ownerId,
			@ApiParam(name="SupplierId",value="供应商ID",required = true)@PathVariable(value="SupplierId") String SupplierId) throws Exception{
		List<ScmZsjProduct> downBoxApplySuccess = scmZsjProductManager.downBoxApplySuccess(ownerId,SupplierId);
		
		return new CommonResult<List<ScmZsjProduct>>(true,"获取成功",downBoxApplySuccess);
	}
	
	/**
	 * 商品系列与商品二级联动下拉框
	 * 与商品首营关联查询,并携带首营状态
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/LineAndProductDownBox")
	@ApiOperation(value="商品系列与商品二级联动下拉框", httpMethod = "POST", notes = "商品系列与商品二级联动下拉框")
	public CommonResult<List<Map<String,Object>>> LineAndProductDownBox() throws Exception{
		List<Map<String,Object>> lineAndProductDownBox = scmZsjProductManager.LineAndProductDownBox();
		return new CommonResult<List<Map<String,Object>>>(true,"获取成功",lineAndProductDownBox);

	}
	/**
	 * 商品系列与商品二级联动下拉框
	 * 与商品首营关联查询,并携带首营状态
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/LineAndProductDownBoxByfirst")
	@ApiOperation(value="商品系列与商品二级联动下拉框首营通过", httpMethod = "POST", notes = "商品系列与商品二级联动下拉框首营通过")
	public CommonResult<List<Map<String,Object>>> LineAndProductDownBoxByfirst(@ApiParam(name="ownerId",value="货主ID",required = true)@RequestParam String ownerId) throws Exception{
		List<Map<String,Object>> lineAndProductDownBox = scmZsjProductManager.LineAndProductDownBoxByfirst(ownerId);
		return new CommonResult<List<Map<String,Object>>>(true,"获取成功",lineAndProductDownBox);
		
	}


	/**
	 * 根据商品编码获取商品信息
	 * @param
	 * @return
	 * @throws Exception
	 * PageJson
	 * @exception
	 */
	@GetMapping("/getByProductCode")
	@ApiOperation(value="根据商品编码获取商品信息", httpMethod = "GET", notes = "获取商品信息")
	public ScmZsjProduct getByProductCode(@ApiParam(name="productCode",value="商品编码",required = true)@RequestParam(value = "productCode") String productCode) throws Exception{
		return scmZsjProductManager.getProductByCode(productCode);
	}
	
	/**
	 * @param 商品申请
	 * @param request
	 * @return
	 * @throws Exception
	 * flowKey:审批类型
	 * sysCode:系统别名
	 * instanceIdField:与实体类审批ID相同,controller层接到对象会有审批ID,处理业务逻辑后,保存对象即可
	 * varKeys:脚本使用参数
	 */
	@PostMapping(value = "sendApply")
    @ApiOperation(value = "商品申请", httpMethod = "POST", notes = "商品申请")
//    @Workflow(flowKey = "sp", sysCode = "SCM", instanceIdField = "approvalId", varKeys = {})
    public CommonResult<String> sendApply(
            @ApiParam(name = "ScmZsjProduct", value = "商品对象", required = true) @RequestBody ScmZsjProduct scmZsjProduct,
            HttpServletRequest request) throws Exception {
		JsonNode user = ucFeignService.getUser(current(), "");
		String userName = user.get("fullname").asText();
		scmZsjProduct.setOperatorName(userName);
		scmZsjProduct.setOperatorDate(new Date());
		scmZsjProductManager.sendApply(scmZsjProduct);
		return new CommonResult<String>(true,"商品审批发起成功");
	}
	
	
	/***
	 *
	 * ┌───┐   ┌───┬───┬───┬───┐ ┌───┬───┬───┬───┐ ┌───┬───┬───┬───┐ ┌───┬───┬───┐
	 * │Esc│   │ F1│ F2│ F3│ F4│ │ F5│ F6│ F7│ F8│ │ F9│F10│F11│F12│ │P/S│S L│P/B│  ┌┐    ┌┐    ┌┐
	 * └───┘   └───┴───┴───┴───┘ └───┴───┴───┴───┘ └───┴───┴───┴───┘ └───┴───┴───┘  └┘    └┘    └┘
	 * ┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───────┐ ┌───┬───┬───┐ ┌───┬───┬───┬───┐
	 * │~ `│! 1│@ 2│# 3│$ 4│% 5│^ 6│& 7│* 8│( 9│) 0│_ -│+ =│ BacSp │ │Ins│Hom│PUp│ │N L│ / │ * │ - │
	 * ├───┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─────┤ ├───┼───┼───┤ ├───┼───┼───┼───┤
	 * │ Tab │ Q │ W │ E │ R │ T │ Y │ U │ I │ O │ P │{ [│} ]│ | \ │ │Del│End│PDn│ │ 7 │ 8 │ 9 │   │
	 * ├─────┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴─────┤ └───┴───┴───┘ ├───┼───┼───┤ + │
	 * │ Caps │ A │ S │ D │ F │ G │ H │ J │ K │ L │: ;│" '│ Enter  │               │ 4 │ 5 │ 6 │   │
	 * ├──────┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴────────┤     ┌───┐     ├───┼───┼───┼───┤
	 * │ Shift  │ Z │ X │ C │ V │ B │ N │ M │< ,│> .│? /│  Shift   │     │ ↑ │     │ 1 │ 2 │ 3 │   │
	 * ├─────┬──┴─┬─┴──┬┴───┴───┴───┴───┴───┴──┬┴───┼───┴┬────┬────┤ ┌───┼───┼───┐ ├───┴───┼───┤ E││
	 * │ Ctrl│    │Alt │         Space         │ Alt│ FN │    │Ctrl│ │ ← │ ↓ │ → │ │   0   │ . │←─┘│
	 * └─────┴────┴────┴───────────────────────┴────┴────┴────┴────┘ └───┴───┴───┘ └───────┴───┴───┘
	 * 键盘给你,你来
	 */
    @PostMapping(value = "/endApply")
    @ApiOperation(value = "商品申请审批流程通过", httpMethod = "POST", notes = "商品申请审批流程通过")
    public void endApply(@ApiParam(name = "params", value = "流程事件参数", required = true) @RequestBody String params, 
    		HttpServletRequest request) throws Exception {
    	JsonNode jsonNode = JsonUtil.toJsonNode(params);
    	scmZsjProductManager.endApply(jsonNode);
    }


	/**
	 * 商品带件装量商品列表
	 * @param
	 * @return
	 * @throws Exception
	 * ModelAndView
	 */
	@PostMapping(value="/productAndAchage")
	@ApiOperation(value="商品首营下拉框",httpMethod = "GET",notes = "商品和件装量列表")
	public List<ScmZsjProduct> productAndAchage(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmZsjProductManager.productAndAchage(queryFilter);
	}
	/**
	 * 商品数据修改同步
	 * @param
	 * @return
	 * @throws Exception
	 * ModelAndView
	 */
	@GetMapping(value="/updateSyn/{id}")
	@ApiOperation(value="商品数据修改同步",httpMethod = "GET",notes = "商品数据修改同步")
	public CommonResult<String> updateSyn(@ApiParam(name="id",value="商品主键")@PathVariable String id) throws Exception{
		scmZsjProductManager.updateSyn(id);
		return new CommonResult<>(true, "同步成功");
	}

	/**
	 * 根据商品名称查询商品信息
	 * @param
	 * @return
	 * @throws Exception
	 * ModelAndView
	 */
	@PostMapping(value="/getProductByName")
	@ApiOperation(value="根据商品名称查询商品信息",httpMethod = "POST",notes = "根据商品名称查询商品信息")
	public String getProductByName(@ApiParam(name="productName",value="商品名")@RequestParam(value="productName") String productName) throws Exception{
		ScmZsjProduct  product = scmZsjProductManager.getProductByName(productName);
		return  JSON.toJSONString(product);

	}

	/**
	 * 根据商品名称查询商品信息
	 * @param
	 * @return
	 * @throws Exception
	 * ModelAndView
	 */
	@PostMapping(value="/getProductByCommonName")
	@ApiOperation(value="根据商品名称查询商品信息",httpMethod = "POST",notes = "根据商品名称查询商品信息")
	public String getProductByCommonName(@ApiParam(name="commonName",value="商品名")@RequestParam(value="commonName") String commonName) throws Exception{
		List<ScmZsjProduct>  product = scmZsjProductManager.getProductByCommonName(commonName);
		return  JSON.toJSONString(product);

	}


	/**
	 * 根据商品名称查询商品信息
	 * @param
	 * @return
	 * @throws Exception
	 * ModelAndView
	 */
	@PostMapping(value="/getProductByCommonNameAndState")
	@ApiOperation(value="根据商品名称查询商品信息",httpMethod = "POST",notes = "根据商品名称查询商品信息")
	public String getProductByCommonNameAndState(@RequestParam(value = "commonName")String commonName,@RequestParam(value = "productState") String productState) throws Exception{
		List<ScmZsjProduct>  productList = scmZsjProductManager.getProductByCommonNameAndState(commonName,productState);
		return  JSON.toJSONString(productList);

	}

}
