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
import com.winway.scm.persistence.manager.ScmCwDiscountRecordManager;
import com.winway.scm.model.ScmCwDiscountRecord;
import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;
/**
 * 
 * <pre> 
 * 描述：票折记录表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-23 18:19:25
 * 版权：美达开发小组
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmCwDiscountRecord/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmCwDiscountRecordController 票折记录表 控制器类")
public class ScmCwDiscountRecordController extends BaseController{

	@Resource
	ScmCwDiscountRecordManager scmCwDiscountRecordManager;
	/**
	 * 票折记录表列表(分页条件查询)数据
	 * @param
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="票折记录表数据列表", httpMethod = "POST", notes = "获取票折记录表列表")
	public PageList<ScmCwDiscountRecord> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmCwDiscountRecordManager.query(queryFilter);
	}
	
	/**
	 * 票折记录表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="票折记录表数据详情",httpMethod = "GET",notes = "票折记录表数据详情")
	public ScmCwDiscountRecord get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmCwDiscountRecordManager.get(id);
	}
	
    /**
	 * 新增票折记录表
	 * @param scmCwDiscountRecord
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新票折记录表数据", httpMethod = "POST", notes = "新增,更新票折记录表数据")
	public CommonResult<String> save(@ApiParam(name="scmCwDiscountRecord",value="票折记录表业务对象", required = true)@RequestBody ScmCwDiscountRecord scmCwDiscountRecord) throws Exception{
		String msg = "添加票折记录表成功";
		if(StringUtil.isEmpty(scmCwDiscountRecord.getId())){
			scmCwDiscountRecordManager.create(scmCwDiscountRecord);
		}else{
			scmCwDiscountRecordManager.update(scmCwDiscountRecord);
			 msg = "更新票折记录表成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除票折记录表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除票折记录表记录", httpMethod = "DELETE", notes = "删除票折记录表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmCwDiscountRecordManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除票折记录表记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除票折记录表记录", httpMethod = "DELETE", notes = "批量删除票折记录表记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmCwDiscountRecordManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
}
