package com.winway.scm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import com.winway.scm.persistence.manager.ScmCgProcurementReturnManager;
import com.winway.scm.model.ScmCgProcurementContract;
import com.winway.scm.model.ScmCgProcurementReturn;
import com.hotent.base.annotation.Workflow;
import com.hotent.base.controller.BaseController;
import com.hotent.base.model.CommonResult;
import com.hotent.base.model.WinwayResult;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.StringUtil;

/**
 * 
 * <pre> 
 * 描述：采购退货表 控制器类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-26 13:47:44
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
@RestController
@RequestMapping(value="/scm/scmCgProcurementReturn/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="scmCgProcurementReturnController 采购退货")
public class ScmCgProcurementReturnController extends BaseController{
	@Resource
	ScmCgProcurementReturnManager scmCgProcurementReturnManager;
	
	/**
	 * 采购退货表列表(分页条件查询)数据
	 * @param request
	 * @return
	 * @throws Exception 
	 * PageJson
	 * @exception 
	 */
	@PostMapping("/list")
	@ApiOperation(value="采购退货表数据列表", httpMethod = "POST", notes = "获取采购退货表列表")
	public PageList<ScmCgProcurementReturn> list(@ApiParam(name="queryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws Exception{
		return scmCgProcurementReturnManager.query(queryFilter);
	}
	
	/**
	 * 采购退货表明细页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="采购退货表数据详情",httpMethod = "GET",notes = "采购退货表数据详情")
	public ScmCgProcurementReturn get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception{
		return scmCgProcurementReturnManager.get(id);
	}
	
    /**
	 * 新增采购退货表
	 * @param scmCgProcurementReturn
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新采购退货表数据", httpMethod = "POST", notes = "新增,更新采购退货表数据")
	public CommonResult<String> save(@ApiParam(name="scmCgProcurementReturn",value="采购退货表业务对象", required = true)@RequestBody ScmCgProcurementReturn scmCgProcurementReturn) throws Exception{
		String msg = "添加采购退货表成功";
		if(StringUtil.isEmpty(scmCgProcurementReturn.getId())){
			scmCgProcurementReturnManager.create(scmCgProcurementReturn);
		}else{
			scmCgProcurementReturnManager.update(scmCgProcurementReturn);
			 msg = "更新采购退货表成功";
		}
		return new CommonResult<String>(msg);
	}
	
	/**
	 * 删除采购退货表记录
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除采购退货表记录", httpMethod = "DELETE", notes = "删除采购退货表记录")
	public  CommonResult<String>  remove(@ApiParam(name="id",value="业务主键", required = true)@PathVariable String id) throws Exception{
		scmCgProcurementReturnManager.remove(id);
		return new CommonResult<String>(true, "删除成功");
	}
	
	/**
	 * 批量删除采购退货表记录
	 * @param ids
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="/removes")
	@ApiOperation(value = "批量删除采购退货表记录", httpMethod = "DELETE", notes = "批量删除采购退货表记录")
	public CommonResult<String> removes(@ApiParam(name="ids",value="业务主键数组,多个业务主键之间用逗号分隔", required = true)@RequestParam String...ids) throws Exception{
		scmCgProcurementReturnManager.removeByIds(ids);
		return new CommonResult<String>(true, "批量删除成功");
	}
	
	/**
	 * @param 采购退货申请
	 * @param request
	 * @return
	 * @throws Exception
	 * flowKey:审批类型
	 * sysCode:系统别名
	 * instanceIdField:与实体类审批ID相同,controller层接到对象会有审批ID,处理业务逻辑后,保存对象即可
	 * varKeys:脚本使用参数
	 */
	@PostMapping(value = "sendApply")
    @ApiOperation(value = "采购退货申请", httpMethod = "POST", notes = "采购退货流程发起")
    @Workflow(flowKey = "cgthlc", sysCode = "SCM", instanceIdField = "approvalId", varKeys = { "account", "isFinance" })
    public WinwayResult<String> sendApply(
            @ApiParam(name = "scmCgProcurementReturn", value = "采购退货对象", required = true) @RequestBody ScmCgProcurementReturn scmCgProcurementReturn,
            HttpServletRequest request) throws Exception {
		scmCgProcurementReturnManager.sendApply(scmCgProcurementReturn);
	   return new WinwayResult<>("true", "申请提交成功");
	}
}
