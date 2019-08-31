package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;


 /**
 * 经销商RB核算储运折扣
 * <pre> 
 * 描述：经销商RB核算储运折扣 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-09 18:18:05
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmCwBAStorageDiscount",description = "经销商RB核算储运折扣") 
public class ScmCwBAStorageDiscount extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="主表ID")
	protected String masterId; 
	
	@ApiModelProperty(value="商业ID")
	protected String commerceId; 
	
	@ApiModelProperty(value="商业名称")
	protected String commerceName; 
	
	@ApiModelProperty(value="商业编号")
	protected String commerceCode; 
	
	@ApiModelProperty(value="储运折扣金额")
	protected Double storageDiscountPrice; 
	
	@ApiModelProperty(value="商务分区")
	protected String businessDivision; 
	
	@ApiModelProperty(value="商务分区ID")
	protected String businessDivisionId; 
	
	@ApiModelProperty(value="省区")
	protected String province; 
	
	@ApiModelProperty(value="省区ID")
	protected String provinceId; 
	
	@ApiModelProperty(value="折扣类型")
	protected String discountType; 
	
	@ApiModelProperty(value="发货日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date shipmentDate; 
	
	@ApiModelProperty(value="发货单号")
	protected String shipmentCode; 
	
	@ApiModelProperty(value="失效日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date loseEfficacyDate; 
	
	@ApiModelProperty(value="批号")
	protected String batchNum; 
	
	@ApiModelProperty(value="产品名称")
	protected String productName; 
	
	@ApiModelProperty(value="数量")
	protected Integer productNum; 
	
	@ApiModelProperty(value="可调整数量")
	protected Integer updateProductNum; 
	
	@ApiModelProperty(value="单价")
	protected Double unitPrice; 
	
	@ApiModelProperty(value="可调整单价")
	protected Double updateUnitPrice; 
	
	@ApiModelProperty(value="金额")
	protected Double price; 
	
	@ApiModelProperty(value="RB率")
	protected Double rbRate; 
	
	@ApiModelProperty(value="本次计算RB")
	protected Double thisCalculationRb; 
	
	@ApiModelProperty(value="申请备注")
	protected String memo; 
	
	@ApiModelProperty(value="勾兑记录表id")
	protected String blendId; 
	
	
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
	
	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}
	
	/**
	 * 返回 主表ID
	 * @return
	 */
	public String getMasterId() {
		return this.masterId;
	}
	
	public void setCommerceId(String commerceId) {
		this.commerceId = commerceId;
	}
	
	/**
	 * 返回 商业ID
	 * @return
	 */
	public String getCommerceId() {
		return this.commerceId;
	}
	
	public void setCommerceName(String commerceName) {
		this.commerceName = commerceName;
	}
	
	/**
	 * 返回 商业名称
	 * @return
	 */
	public String getCommerceName() {
		return this.commerceName;
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
	
	public void setStorageDiscountPrice(Double storageDiscountPrice) {
		this.storageDiscountPrice = storageDiscountPrice;
	}
	
	/**
	 * 返回 储运折扣金额
	 * @return
	 */
	public Double getStorageDiscountPrice() {
		return this.storageDiscountPrice;
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
	
	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}
	
	/**
	 * 返回 折扣类型
	 * @return
	 */
	public String getDiscountType() {
		return this.discountType;
	}
	
	public void setShipmentDate(java.util.Date shipmentDate) {
		this.shipmentDate = shipmentDate;
	}
	
	/**
	 * 返回 发货日期
	 * @return
	 */
	public java.util.Date getShipmentDate() {
		return this.shipmentDate;
	}
	
	public void setShipmentCode(String shipmentCode) {
		this.shipmentCode = shipmentCode;
	}
	
	/**
	 * 返回 发货单号
	 * @return
	 */
	public String getShipmentCode() {
		return this.shipmentCode;
	}
	
	public void setLoseEfficacyDate(java.util.Date loseEfficacyDate) {
		this.loseEfficacyDate = loseEfficacyDate;
	}
	
	/**
	 * 返回 失效日期
	 * @return
	 */
	public java.util.Date getLoseEfficacyDate() {
		return this.loseEfficacyDate;
	}
	
	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}
	
	/**
	 * 返回 批号
	 * @return
	 */
	public String getBatchNum() {
		return this.batchNum;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	/**
	 * 返回 产品名称
	 * @return
	 */
	public String getProductName() {
		return this.productName;
	}
	
	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}
	
	/**
	 * 返回 数量
	 * @return
	 */
	public Integer getProductNum() {
		return this.productNum;
	}
	
	public void setUpdateProductNum(Integer updateProductNum) {
		this.updateProductNum = updateProductNum;
	}
	
	/**
	 * 返回 可调整数量
	 * @return
	 */
	public Integer getUpdateProductNum() {
		return this.updateProductNum;
	}
	
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	/**
	 * 返回 单价
	 * @return
	 */
	public Double getUnitPrice() {
		return this.unitPrice;
	}
	
	public void setUpdateUnitPrice(Double updateUnitPrice) {
		this.updateUnitPrice = updateUnitPrice;
	}
	
	/**
	 * 返回 可调整单价
	 * @return
	 */
	public Double getUpdateUnitPrice() {
		return this.updateUnitPrice;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	/**
	 * 返回 金额
	 * @return
	 */
	public Double getPrice() {
		return this.price;
	}
	
	public void setRbRate(Double rbRate) {
		this.rbRate = rbRate;
	}
	
	/**
	 * 返回 RB率
	 * @return
	 */
	public Double getRbRate() {
		return this.rbRate;
	}
	
	public void setThisCalculationRb(Double thisCalculationRb) {
		this.thisCalculationRb = thisCalculationRb;
	}
	
	/**
	 * 返回 本次计算RB
	 * @return
	 */
	public Double getThisCalculationRb() {
		return this.thisCalculationRb;
	}
	
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	/**
	 * 返回 申请备注
	 * @return
	 */
	public String getMemo() {
		return this.memo;
	}
	
	public void setBlendId(String blendId) {
		this.blendId = blendId;
	}
	
	/**
	 * 返回 勾兑记录表id
	 * @return
	 */
	public String getBlendId() {
		return this.blendId;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("masterId", this.masterId) 
		.append("commerceId", this.commerceId) 
		.append("commerceName", this.commerceName) 
		.append("commerceCode", this.commerceCode) 
		.append("storageDiscountPrice", this.storageDiscountPrice) 
		.append("businessDivision", this.businessDivision) 
		.append("businessDivisionId", this.businessDivisionId) 
		.append("province", this.province) 
		.append("provinceId", this.provinceId) 
		.append("discountType", this.discountType) 
		.append("shipmentDate", this.shipmentDate) 
		.append("shipmentCode", this.shipmentCode) 
		.append("loseEfficacyDate", this.loseEfficacyDate) 
		.append("batchNum", this.batchNum) 
		.append("productName", this.productName) 
		.append("productNum", this.productNum) 
		.append("updateProductNum", this.updateProductNum) 
		.append("unitPrice", this.unitPrice) 
		.append("updateUnitPrice", this.updateUnitPrice) 
		.append("price", this.price) 
		.append("rbRate", this.rbRate) 
		.append("thisCalculationRb", this.thisCalculationRb) 
		.append("memo", this.memo) 
		.append("blendId", this.blendId) 
		.toString();
	}
}