package com.winway.scm.controller;

import com.winway.purchase.util.ExcelExportUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import com.winway.scm.persistence.manager.ScmCwReturnMoneyManager;
import com.winway.scm.model.ScmCwReturnMoney;
import com.alibaba.druid.util.StringUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.controller.BaseController;
import com.hotent.base.feign.UCFeignService;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;

/**
 * 
 * <pre> 
 * 描述：商业回款表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-23 18:19:25
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmCwReturnMoney/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmCwReturnMoneyController 商业回款表 控制器类")
public class ScmCwReturnMoneyController extends BaseController{
	@Resource
	ScmCwReturnMoneyManager scmCwReturnMoneyManager;
	@Resource
	UCFeignService ucFeignService;
	
	
	/**
	 * 商业回款表列表(分页条件查询)数据
	 * @param
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="商业回款表数据列表", httpMethod = "POST", notes = "获取商业回款表列表")
	public PageList<ScmCwReturnMoney> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		 PageList<ScmCwReturnMoney> query = scmCwReturnMoneyManager.query(queryFilter);
		 return query;
	}
	
	/**
	 * 商业回款表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="商业回款表数据详情",httpMethod = "GET",notes = "商业回款表数据详情")
	public ScmCwReturnMoney get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmCwReturnMoneyManager.get(id);
	}
	
    /**
	 * 新增商业回款表
	 * @param scmCwReturnMoney
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新商业回款表数据", httpMethod = "POST", notes = "新增,更新商业回款表数据")
	public CommonResult<String> save(@ApiParam(name="scmCwReturnMoney",value="商业回款表业务对象", required = true)@RequestBody ScmCwReturnMoney scmCwReturnMoney) throws Exception{
		String msg = "添加商业回款表成功";
		if(StringUtil.isEmpty(scmCwReturnMoney.getId())){
			scmCwReturnMoney.setBalancePrice(scmCwReturnMoney.getPrice());
			scmCwReturnMoneyManager.create(scmCwReturnMoney);
		}else{
			scmCwReturnMoney.setBalancePrice(scmCwReturnMoney.getUpdatePrice());
			scmCwReturnMoneyManager.update(scmCwReturnMoney);
			 msg = "更新商业回款表成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除商业回款表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除商业回款表记录", httpMethod = "DELETE", notes = "删除商业回款表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmCwReturnMoneyManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除商业回款表记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除商业回款表记录", httpMethod = "DELETE", notes = "批量删除商业回款表记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmCwReturnMoneyManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
	/**
	 * 商业表导入表格数据
	 * @param file excel文件
	 * @param id 货主Id
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/leadExcel")
//	@ApiOperation(value="导入表格数据", httpMethod = "POST", notes = "导入表格数据")
	public CommonResult<String> leadExcel(@ApiParam(name="file",value="导入excel文件") MultipartFile file,
			@ApiParam(name="id",value="货主Id") @RequestParam String id) throws Exception{
		JsonNode user = ucFeignService.getUser(current(), "");
		String fullname = user.get("fullname").asText();
		String ids = user.get("id").asText();
		ScmCwReturnMoney product = new ScmCwReturnMoney();
		product.setToLeadPersion(fullname);
		product.setToLeadDate(new Date());
		product.setOwnerId(id);
		String s = scmCwReturnMoneyManager.readExcelFile(file, product);
		return  new CommonResult<>(true,s);

	}

	/**
	 * 商业回款模板下载ex
	 * @param
	 * @param
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/downloadExcel")
//	@ApiOperation(value="下载模板", httpMethod = "GET", notes = "下载商业回款模板")
	public void downloadExcel(HttpServletResponse response) throws Exception {
		String[] tableName = { "到款日期(格式2018-09-26)", "商业名称", "回款方式", "承兑汇票是否验真伪", "承兑汇票到款日", "是否预付款","回款金额", "备注"};
		List<List<String>> data = new ArrayList<List<String>>();
		List<String> br = new ArrayList<>();
		br.add("2018-09-26");
		br.add("九州药业有限公司");
		br.add("电邮");
		br.add("是");
		br.add("2018-09-26");
		br.add("预付款");
		br.add("15000");
		br.add("测试");
		data.add(br);
		ExcelExportUtil.download(response, "商业回款模板", "商业回款模板", tableName,data);
	}

	/**
	 * 确认数据
	 * @param
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/affirmData")
	@ApiOperation(value="确认数据", httpMethod = "POST", notes = "确认数据")
	public CommonResult<String> affirmData(
			@ApiParam(name="ids",value="数据主键") @RequestParam String...ids) throws Exception{
		JsonNode user = ucFeignService.getUser(current(), "");
		String fullname = user.get("fullname").asText();
		scmCwReturnMoneyManager.affirmData(ids,fullname);
		return new CommonResult<String>(true,"确认成功！");
	}
	
	@PostMapping("/updatePrice")
	@ApiOperation(value="保存修改后金额", httpMethod = "POST", notes = "保存修改后金额")
	public CommonResult<String> updatePrice(
			@ApiParam(name="id",value="数据主键")@RequestParam String id,
			@ApiParam(name="updatePrice",value="修改金额")@RequestParam String updatePrice,
			@ApiParam(name="memo",value="备注")@RequestParam String memo) throws Exception{
		JsonNode user = ucFeignService.getUser(current(), "");
		String fullname = user.get("fullname").asText();
		scmCwReturnMoneyManager.updatePrice(fullname,id,updatePrice,memo);
		return new CommonResult<String>(true,"修改成功！");
	}

	/**
	 * 商业回款导入汇总列表
	 * @param
	 * @return
	 * @throws Exception
	 * PageJson
	 * @exception
	 */
	@PostMapping("/sumList")
//	@ApiOperation(value="商业回款导入汇总", httpMethod = "POST", notes = "商业回款导入汇总")
	public PageList<Map<String,Object>> sumList(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		PageList<Map<String,Object>> query = scmCwReturnMoneyManager.sumList(queryFilter);
		return query;
	}


}
