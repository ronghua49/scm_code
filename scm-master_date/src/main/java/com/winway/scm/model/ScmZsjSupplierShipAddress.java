package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 收货地址
 * <pre> 
 * 描述：收货地址 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmZsjSupplierShipAddress",description = "收货地址") 
public class ScmZsjSupplierShipAddress extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="首营ID")
	protected String supplierFirstId; 
	
	@ApiModelProperty(value="是否默认(0:否,1:是)")
	protected String isDufault; 
	
	@ApiModelProperty(value="联系人")
	protected String contactPersion; 
	
	@ApiModelProperty(value="电话")
	protected String phone; 
	
	@ApiModelProperty(value="手机")
	protected String headPhone; 
	
	@ApiModelProperty(value="省")
	protected String province; 
	
	@ApiModelProperty(value="市")
	protected String city; 
	
	@ApiModelProperty(value="区")
	protected String district; 
	
	@ApiModelProperty(value="街道")
	protected String street; 
	
	@ApiModelProperty(value="邮编")
	protected String postcode; 
	
	@ApiModelProperty(value="地址")
	protected String address; 
	
	
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
	
	public void setIsDufault(String isDufault) {
		this.isDufault = isDufault;
	}
	
	/**
	 * 返回 是否默认(0:否,1:是)
	 * @return
	 */
	public String getIsDufault() {
		return this.isDufault;
	}
	
	public void setContactPersion(String contactPersion) {
		this.contactPersion = contactPersion;
	}
	
	/**
	 * 返回 联系人
	 * @return
	 */
	public String getContactPersion() {
		return this.contactPersion;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * 返回 电话
	 * @return
	 */
	public String getPhone() {
		return this.phone;
	}
	
	public void setHeadPhone(String headPhone) {
		this.headPhone = headPhone;
	}
	
	/**
	 * 返回 手机
	 * @return
	 */
	public String getHeadPhone() {
		return this.headPhone;
	}
	
	public void setProvince(String province) {
		this.province = province;
	}
	
	/**
	 * 返回 省
	 * @return
	 */
	public String getProvince() {
		return this.province;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	/**
	 * 返回 市
	 * @return
	 */
	public String getCity() {
		return this.city;
	}
	
	public void setDistrict(String district) {
		this.district = district;
	}
	
	/**
	 * 返回 区
	 * @return
	 */
	public String getDistrict() {
		return this.district;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	/**
	 * 返回 街道
	 * @return
	 */
	public String getStreet() {
		return this.street;
	}
	
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	/**
	 * 返回 邮编
	 * @return
	 */
	public String getPostcode() {
		return this.postcode;
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
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("supplierFirstId", this.supplierFirstId) 
		.append("isDufault", this.isDufault) 
		.append("contactPersion", this.contactPersion) 
		.append("phone", this.phone) 
		.append("headPhone", this.headPhone) 
		.append("province", this.province) 
		.append("city", this.city) 
		.append("district", this.district) 
		.append("street", this.street) 
		.append("postcode", this.postcode) 
		.append("address", this.address) 
		.toString();
	}
}