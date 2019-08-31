package com.winway.scm.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.feign.UCFeignService;
import com.winway.purchase.util.ExcelExportUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
import com.winway.scm.persistence.manager.ScmKcInTransitProductManager;
import com.winway.scm.model.ScmKcInTransitProduct;
import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <pre> 
 * 描述：在途商品 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-08-17 14:17:53
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmKcInTransitProduct/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmKcInTransitProductController")
public class ScmKcInTransitProductController extends BaseController{
	@Resource
	ScmKcInTransitProductManager scmKcInTransitProductManager;

	@Resource
	UCFeignService ucFeignService;
	
	/**
	 * 在途商品列表(分页条件查询)数据
	 * @param
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="在途商品数据列表", httpMethod = "POST", notes = "获取在途商品列表")
	public PageList<ScmKcInTransitProduct> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmKcInTransitProductManager.query(queryFilter);
	}
	
	/**
	 * 在途商品明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="在途商品数据详情",httpMethod = "GET",notes = "在途商品数据详情")
	public ScmKcInTransitProduct get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmKcInTransitProductManager.get(id);
	}
	
    /**
	 * 新增在途商品
	 * @param scmKcInTransitProduct
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新在途商品数据", httpMethod = "POST", notes = "新增,更新在途商品数据")
	public CommonResult<String> save(@ApiParam(name="scmKcInTransitProduct",value="在途商品业务对象", required = true)@RequestBody ScmKcInTransitProduct scmKcInTransitProduct) throws Exception{
		String msg = "添加在途商品成功";
		if(StringUtil.isEmpty(scmKcInTransitProduct.getId())){
			scmKcInTransitProductManager.create(scmKcInTransitProduct);
		}else{
			scmKcInTransitProductManager.update(scmKcInTransitProduct);
			 msg = "更新在途商品成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除在途商品记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除在途商品记录", httpMethod = "DELETE", notes = "删除在途商品记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmKcInTransitProductManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除在途商品记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除在途商品记录", httpMethod = "DELETE", notes = "批量删除在途商品记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmKcInTransitProductManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}



	/**
	 * 在途商品导入模板下载
	 *
	 * @param
	 * @param
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/downloadExcel")
//  @ApiOperation(value = "下载模板", httpMethod = "GET", notes = "下载周库存导入模板")
	public void downloadExcel(HttpServletResponse response) throws Exception {
		String[] tableName = {"任务ID", "ETD（离港日期）", "ETA（到港日期）", "清关公司", "物料编码", "产品名称", "规格","批号","出货总件数","出货成品数","柜数","精神药物进口许可证"};
		List<List<String>> data = new ArrayList<List<String>>();
		List<String> br = new ArrayList<>();
		List<String> br2 = new ArrayList<>();
		br.add("645902");
		br.add("2019-8-12 13:12:12");
		br.add("2019-9-12 13:12:12");
		br.add("xxx清关有限公司");
		br.add("d245");
		br.add("澳特斯(60ml/瓶)");
		br.add("60ml/瓶");
        br.add("29293");
		br.add("500");
        br.add("4000");
        br.add("300");
        br.add("DAJSHVADF324532");
        br2.add("备注：导入前所有数据需要加上内外边框(导入前删除此备注)");
		data.add(br);
		data.add(br2);
		ExcelExportUtil.download(response, "在途商品导入", "在途商品导入", tableName, data);
	}

	/**
	 * 在途商品的导入
	 * @param file
	 * @throws Exception
	 * @return
	 * @exception
	 */
	@PostMapping(value="/leadExcel")
//	@ApiOperation(value = "批量导入在途商品记录", httpMethod = "POST", notes = "批量导入在途商品记录")
	public CommonResult<String> leadExcel(@ApiParam(name = "file", value = "导入excel文件") @RequestParam(name = "file") MultipartFile file) throws Exception{
		JsonNode user = ucFeignService.getUser(current(), "");
		String fullname = user.get("fullname").asText();
		scmKcInTransitProductManager.leadExcel(file,fullname);
		return new CommonResult<String>(true, "导入成功");
	}
}
