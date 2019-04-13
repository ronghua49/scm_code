package com.winway.scm.model;

import com.hotent.base.model.BaseModel;

import io.swagger.annotations.ApiModel;
/**
 * 
* @Description: FMS-快速入口配置 实体
* @author: 贺军
* @date: 2018年10月15日 下午6:01:01 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年10月15日     贺军           		v1.0.0           		    新增
 */
@ApiModel(value = "FastEntryData",description = "FMS-快速入口配置表") 
public class FastEntryData extends BaseModel<String> {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String account;
	private String menuId;
	private String menuName;
	private String url;
	private String alias;
	
	
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	
	
}
