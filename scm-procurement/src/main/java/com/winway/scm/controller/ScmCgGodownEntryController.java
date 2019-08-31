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
import com.winway.scm.persistence.manager.ScmCgGodownentryManager;
import com.winway.scm.model.ScmCgGodownentry;
import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 
 * <pre> 
 * 描述：入库单商品表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-06-28 14:54:15
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmCgGodownentry/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmCgGodownentryController")
public class ScmCgGodownEntryController extends BaseController{
	@Resource
	ScmCgGodownentryManager scmCgGodownentryManager;
	
	/**
	 * 入库单商品表列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="入库单商品表数据列表", httpMethod = "POST", notes = "获取入库单商品表列表")
	public PageList<ScmCgGodownentry> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmCgGodownentryManager.query(queryFilter);
	}
	
	/**
	 * 入库单商品表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="入库单商品表数据详情",httpMethod = "GET",notes = "入库单商品表数据详情")
	public ScmCgGodownentry get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmCgGodownentryManager.get(id);
	}
	
    /**
	 * 新增入库单商品表
	 * @param scmCgGodownentry
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新入库单商品表数据", httpMethod = "POST", notes = "新增,更新入库单商品表数据")
	public CommonResult<String> save(@ApiParam(name="scmCgGodownentry",value="入库单商品表业务对象", required = true)@RequestBody ScmCgGodownentry scmCgGodownentry) throws Exception{
		String msg = "添加入库单商品表成功";
		if(StringUtil.isEmpty(scmCgGodownentry.getId())){
			scmCgGodownentryManager.create(scmCgGodownentry);
		}else{
			scmCgGodownentryManager.update(scmCgGodownentry);
			 msg = "更新入库单商品表成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除入库单商品表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除入库单商品表记录", httpMethod = "DELETE", notes = "删除入库单商品表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmCgGodownentryManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除入库单商品表记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除入库单商品表记录", httpMethod = "DELETE", notes = "批量删除入库单商品表记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmCgGodownentryManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}


	@GetMapping(value ="/getExpdate/{productCode}")
	public String getGodownentyByCode(@ApiParam(name="productCode",value="商品批号", required = true) @PathVariable(value = "productCode") String  productCode)throws Exception {
		List<ScmCgGodownentry> cgGodownentryList = scmCgGodownentryManager.getGodownentyByCode(productCode);
		if(!cgGodownentryList.isEmpty()){
			ScmCgGodownentry scmCgGodownentry = cgGodownentryList.get(0);
			Date expdate = scmCgGodownentry.getExpdate();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			return  format.format(expdate);
		}else {
			return  "";
		}
	}
}
