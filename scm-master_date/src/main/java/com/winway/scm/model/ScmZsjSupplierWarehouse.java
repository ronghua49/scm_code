package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 供应商生产/仓库地址
 * <pre> 
 * 描述：供应商生产/仓库地址 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmZsjSupplierWarehouse",description = "供应商生产/仓库地址") 
public class ScmZsjSupplierWarehouse extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="首营ID")
	protected String supplierFirstId; 
	
	@ApiModelProperty(value="地址")
	protected String address; 
	
	@ApiModelProperty(value="是否默认")
	protected String isDefault; 
	
	
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 返回 id
	 * @return
	 */
	public String getId() {
		return this.id;
	}
	
	public void setSupplierFirstId(String supplierFirstId) {
		this.supplierFirstId = supplierFirstId;
	}
	
	/**
	 * 返回 首营ID
	 * @return
	 */
	public String getSupplierFirstId() {
		return this.supplierFirstId;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * 返回 地址
	 * @return
	 */
	public String getAddress() {
		return this.address;
	}
	
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	
	/**
	 * 返回 是否默认
	 * @return
	 */
	public String getIsDefault() {
		return this.isDefault;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("supplierFirstId", this.supplierFirstId) 
		.append("address", this.address) 
		.append("isDefault", this.isDefault) 
		.toString();
	}
}