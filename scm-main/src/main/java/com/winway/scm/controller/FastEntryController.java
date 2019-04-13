package com.winway.scm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotent.base.controller.BaseController;
import com.hotent.base.feign.PortalFeignService;
import com.hotent.base.model.CommonResult;
import com.hotent.base.model.WinwayResult;
import com.hotent.base.util.BeanUtils;
import com.hotent.base.util.JsonUtil;
import com.hotent.base.util.StringUtil;
import com.hotent.sys.persistence.model.SysMenu;
import com.winway.scm.model.FastEntryData;
import com.winway.scm.model.FastEntryVo;
import com.winway.scm.persistence.manager.FastEntryManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


/** 
* @Description: 快速入口的控制类
* @author: 贺军
* @date: 2018年10月15日 下午5:46:17 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年10月15日     贺军          			 v1	          		新增
 */
@RestController
@RequestMapping(value="/fms/fastentry/v1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="FastEntryController 快捷入口")
public class FastEntryController extends BaseController{
	
	@Autowired
	FastEntryManager fastEntryManager;
	private WinwayResult result = null;
	
	@Autowired
	PortalFeignService portalFeign;
	/**
	 * 快速入口明细页面
	 * @param ID
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 */
	@GetMapping(value="/get/{id}")
	@ApiOperation(value="快速入口数据详情",httpMethod = "GET",notes = "快速入口数据详情")
	public FastEntryData get(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable String id) throws Exception {
		return fastEntryManager.get(id);
	}
	
    /**
	 * 新增快速入口
	 * @param fastEntry
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@PostMapping(value="save")
	@ApiOperation(value = "新增,更新快速入口数据", httpMethod = "POST", notes = "新增,更新快速入口数据")
	public CommonResult<String> save(@ApiParam(name="fastEntry",value="快速入口业务对象", required = true)@RequestBody FastEntryData fastEntry) throws Exception {
		String msg = "添加快速入口成功";
		if(StringUtil.isEmpty(fastEntry.getId())){
			fastEntryManager.create(fastEntry);
		}else{
			fastEntryManager.update(fastEntry);
			 msg = "更新快速入口成功";
		}
		return new CommonResult<String>(msg);
	}
	/**
	 * 
	* @Description: 获取用户已配置的快速入口数据
	* @param:当前登录的account
	* @return：返回对象封装快速入口集合
	* @author: 贺军
	* @date: 2018年10月16日 下午1:50:56 
	 */
	@GetMapping(value="queryFastEntry")
	@ApiOperation(value = "获取用户已配置的快速入口数据", httpMethod = "GET", notes = "获取用户已配置的快速入口数据")
	public WinwayResult<List<FastEntryData>> queryFastEntry(@ApiParam(name="account",value="当前用户的登录名", required = true)@RequestParam String account,
									   HttpServletRequest request) throws Exception {
		List<FastEntryData> datas = fastEntryManager.queryFastEntryByAccount(account); 
		return new WinwayResult<List<FastEntryData>>(request.getRequestURI(), "查询成功","10000", datas);
	}
	
	/**
	 * 删除快速入口数据
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@DeleteMapping(value="remove/{id}")
	@ApiOperation(value = "删除快速入口数据", httpMethod = "DELETE", notes = "删除快速入口数据")
	public WinwayResult<String> remove( @ApiParam(name="id",value="业务主键", required = true)
	                                    @PathVariable String id,HttpServletRequest request) throws Exception {
		fastEntryManager.remove(id);
		return new WinwayResult<String>(request.getRequestURI(),"删除成功");
	}
	
	/**
	 * 获取fms系统菜单配置接口
	 * @param id
	 * @throws Exception 
	 * @return
	 * @exception 
	 */
	@RequestMapping(value="getFMSCurrentUserMenu", method=RequestMethod.GET, produces = { "application/json; charset=utf-8" })
	@ApiOperation(value = "获取fms系统菜单配置接口", httpMethod = "GET", notes = "获取fms系统菜单配置接口")
	public WinwayResult<List<SysMenu>> getFMSCurrentUserMenu(@ApiParam(name="account",value="当前登录人", required = true)
    														 @RequestParam String account,HttpServletRequest request) throws Exception {
		
		List<FastEntryData> datas = fastEntryManager.queryFastEntryByAccount(account);
		String configMenuIds ="";
		if(datas.size()>0){
			for (FastEntryData data : datas) {
				configMenuIds = configMenuIds.concat(",").concat(data.getMenuId());
			}
		}
		List<Object>  menusObject = portalFeign.getMyMenu("-1");
		List<SysMenu> totalMenus = new java.util.ArrayList<SysMenu>();
		for(Object menuObject : menusObject){
			String menuStr = JsonUtil.toJson(menuObject);
			SysMenu menu = JsonUtil.toBean(menuStr, SysMenu.class);
			String menuId = menu.getId();
			if(configMenuIds.indexOf(menuId) >= 0){
				menu.setChecked("true");
			}
			totalMenus.add(menu);
		}
		
		List<SysMenu> front = new ArrayList<SysMenu>();
		BeanUtils.listByPid(totalMenus, portalFeign.getIdByMenuAlias("fmscdgl"), front);	
		return new WinwayResult<List<SysMenu>>(request.getRequestURI(),"查询成功","10000",BeanUtils.listToTree(front));
	}
	/**
	 * 
	* @Description: 该函数的功能描述
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	* @author: 贺军
	* @date: 2018年10月17日 上午11:35:08 
	* @history
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2018年10月17日      贺军         	  		v1					 修改原因
	 */
	@PostMapping(value="saveFastEntry")
	@ApiOperation(value = "保存快速入口", httpMethod = "POST", notes = "保存快速入口")	
	public WinwayResult<String> saveFastEntry(@ApiParam(name="paramData",value="当前登录人配置的快速入口", required = true) 
											  @RequestBody FastEntryVo paramData,HttpServletRequest request) throws Exception{
		
		fastEntryManager.saveAll(paramData);
		return new WinwayResult<String>(request.getRequestURI(),"保存成功");
	}
}
