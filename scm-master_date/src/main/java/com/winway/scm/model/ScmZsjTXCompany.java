package com.winway.scm.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;

/**
 * 天祥信息
 * 
 * <pre>
 *  
 * 描述：天祥信息 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
@ApiModel(value = "ScmZsjTXCompany", description = "天祥信息")
public class ScmZsjTXCompany extends BaseModel<String> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "单位名")
	protected String name;

	@ApiModelProperty(value = "地址")
	protected String address;

	@ApiModelProperty(value = "开户行")
	protected String openBank;

	@ApiModelProperty(value = "账号")
	protected String bankCode;

	@ApiModelProperty(value = "税号")
	protected String dutyParagraph;

	@ApiModelProperty(value = "电话")
	protected String phone;

	@ApiModelProperty(value = "邮政编码")
	protected String postcode;

	@ApiModelProperty(value = "id")
	protected String id;

	@ApiModelProperty(value = "货主ID")
	protected String ownerId;

	@ApiModelProperty(value = "经营范围")
	protected String managementScope;

	@ApiModelProperty(value = "联系人")
	protected String contactPersion;

	@ApiModelProperty(value = "联系电话")
	protected String contactPhone;

	@ApiModelProperty(value = "RB税点")
	protected Double taxPoint;
	@ApiModelProperty(value = "信息直连费用")
	protected Double informationCost;
	@ApiModelProperty(value = "流向折扣")
	protected Double flowDiscount;
	@ApiModelProperty(value = "储运管理折扣")
	protected Double storageManager;

	@ApiModelProperty(value = "货主供应仓库")
	List<ScmZsjTxCompanyWarehouse> scmZsjTxCompanyWarehouseList = new ArrayList<ScmZsjTxCompanyWarehouse>();

	public Double getInformationCost() {
		return informationCost;
	}

	public void setInformationCost(Double informationCost) {
		this.informationCost = informationCost;
	}

	public Double getFlowDiscount() {
		return flowDiscount;
	}

	public void setFlowDiscount(Double flowDiscount) {
		this.flowDiscount = flowDiscount;
	}

	public Double getStorageManager() {
		return storageManager;
	}

	public void setStorageManager(Double storageManager) {
		this.storageManager = storageManager;
	}

	public List<ScmZsjTxCompanyWarehouse> getScmZsjTxCompanyWarehouseList() {
		return scmZsjTxCompanyWarehouseList;
	}

	public void setScmZsjTxCompanyWarehouseList(List<ScmZsjTxCompanyWarehouse> scmZsjTxCompanyWarehouseList) {
		this.scmZsjTxCompanyWarehouseList = scmZsjTxCompanyWarehouseList;
	}

	public Double getTaxPoint() {
		return taxPoint;
	}

	public void setTaxPoint(Double taxPoint) {
		this.taxPoint = taxPoint;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManagementScope() {
		return managementScope;
	}

	public void setManagementScope(String managementScope) {
		this.managementScope = managementScope;
	}

	public String getContactPersion() {
		return contactPersion;
	}

	public void setContactPersion(String contactPersion) {
		this.contactPersion = contactPersion;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 返回 单位名
	 * 
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 返回 地址
	 * 
	 * @return
	 */
	public String getAddress() {
		return this.address;
	}

	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}

	/**
	 * 返回 开户行
	 * 
	 * @return
	 */
	public String getOpenBank() {
		return this.openBank;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	/**
	 * 返回 账号
	 * 
	 * @return
	 */
	public String getBankCode() {
		return this.bankCode;
	}

	public void setDutyParagraph(String dutyParagraph) {
		this.dutyParagraph = dutyParagraph;
	}

	/**
	 * 返回 税号
	 * 
	 * @return
	 */
	public String getDutyParagraph() {
		return this.dutyParagraph;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 返回 电话
	 * 
	 * @return
	 */
	public String getPhone() {
		return this.phone;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	/**
	 * 返回 邮政编码
	 * 
	 * @return
	 */
	public String getPostcode() {
		return this.postcode;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 返回 id
	 * 
	 * @return
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * 返回货主id
	 * 
	 * @return
	 */
	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("name", this.name).append("address", this.address)
				.append("openBank", this.openBank).append("bankCode", this.bankCode)
				.append("dutyParagraph", this.dutyParagraph).append("phone", this.phone)
				.append("postcode", this.postcode).append("id", this.id).toString();
	}
}