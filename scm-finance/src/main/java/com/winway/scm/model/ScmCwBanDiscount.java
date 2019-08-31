package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;


 /**
 * 禁止票折规则表
 * <pre> 
 * 描述：禁止票折规则表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-27 11:30:57
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmCwBanDiscount",description = "禁止票折规则表") 
public class ScmCwBanDiscount extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="省区")
	protected String province; 
	
	@ApiModelProperty(value="商务分区ID")
	protected String businessDivisionId; 
	
	@ApiModelProperty(value="省区ID")
	protected String provinceId; 
	
	@ApiModelProperty(value="货主ID")
	protected String ownerId; 
	
	@ApiModelProperty(value="商业id")
	protected String commerceId; 
	
	@ApiModelProperty(value="商业编号")
	protected String commerceCode; 
	
	@ApiModelProperty(value="商品名称")
	protected String commerceName; 
	
	@ApiModelProperty(value="商务分区")
	protected String businessDivision; 
	
	@ApiModelProperty(value="商品ID")
	protected String productId; 
	
	@ApiModelProperty(value="商品编号")
	protected String productCode; 
	
	@ApiModelProperty(value="商品名称")
	protected String productName; 
	
	@ApiModelProperty(value="设置人")
	protected String setPersion; 
	
	@ApiModelProperty(value="产品系列ID")
	protected String productLineId; 
	
	@ApiModelProperty(value="产品系列名称")
	protected String productLineName; 
	
	@ApiModelProperty(value="设置时间")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date setTime; 
	
	
	public String getProductLineId() {
		return productLineId;
	}

	public void setProductLineId(String productLineId) {
		this.productLineId = productLineId;
	}

	public String getProductLineName() {
		return productLineName;
	}

	public void setProductLineName(String productLineName) {
		this.productLineName = productLineName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

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
	
	public void setProvince(String province) {
		this.province = province;
	}
	
	/**
	 * 返回 省区
	 * @return
	 */
	public String getProvince() {
		return this.province;
	}
	
	public void setBusinessDivisionId(String businessDivisionId) {
		this.businessDivisionId = businessDivisionId;
	}
	
	/**
	 * 返回 商务分区ID
	 * @return
	 */
	public String getBusinessDivisionId() {
		return this.businessDivisionId;
	}
	
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	
	/**
	 * 返回 省区ID
	 * @return
	 */
	public String getProvinceId() {
		return this.provinceId;
	}
	
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	
	/**
	 * 返回 货主ID
	 * @return
	 */
	public String getOwnerId() {
		return this.ownerId;
	}
	
	public void setCommerceId(String commerceId) {
		this.commerceId = commerceId;
	}
	
	/**
	 * 返回 商业id
	 * @return
	 */
	public String getCommerceId() {
		return this.commerceId;
	}
	
	public void setCommerceCode(String commerceCode) {
		this.commerceCode = commerceCode;
	}
	
	/**
	 * 返回 商业编号
	 * @return
	 */
	public String getCommerceCode() {
		return this.commerceCode;
	}
	
	public void setCommerceName(String commerceName) {
		this.commerceName = commerceName;
	}
	
	/**
	 * 返回 商品名称
	 * @return
	 */
	public String getCommerceName() {
		return this.commerceName;
	}
	
	public void setBusinessDivision(String businessDivision) {
		this.businessDivision = businessDivision;
	}
	
	/**
	 * 返回 商务分区
	 * @return
	 */
	public String getBusinessDivision() {
		return this.businessDivision;
	}
	
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	/**
	 * 返回 商品ID
	 * @return
	 */
	public String getProductId() {
		return this.productId;
	}
	
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	/**
	 * 返回 商品编号
	 * @return
	 */
	public String getProductCode() {
		return this.productCode;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	/**
	 * 返回 商品名称
	 * @return
	 */
	public String getProductName() {
		return this.productName;
	}
	
	public void setSetPersion(String setPersion) {
		this.setPersion = setPersion;
	}
	
	/**
	 * 返回 设置人
	 * @return
	 */
	public String getSetPersion() {
		return this.setPersion;
	}
	
	public void setSetTime(java.util.Date setTime) {
		this.setTime = setTime;
	}
	
	/**
	 * 返回 设置时间
	 * @return
	 */
	public java.util.Date getSetTime() {
		return this.setTime;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("province", this.province) 
		.append("businessDivisionId", this.businessDivisionId) 
		.append("provinceId", this.provinceId) 
		.append("ownerId", this.ownerId) 
		.append("commerceId", this.commerceId) 
		.append("commerceCode", this.commerceCode) 
		.append("commerceName", this.commerceName) 
		.append("businessDivision", this.businessDivision) 
		.append("productId", this.productId) 
		.append("productCode", this.productCode) 
		.append("productName", this.productName) 
		.append("setPersion", this.setPersion) 
		.append("setTime", this.setTime) 
		.toString();
	}
}