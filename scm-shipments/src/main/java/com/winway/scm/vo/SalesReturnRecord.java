package com.winway.scm.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 当此退货修改页面数据
 */
public class SalesReturnRecord {
	@ApiModelProperty(value = "商业退货ID")
	private String id;
	@ApiModelProperty(value = "退货编码")
	private String returnCode;
	@ApiModelProperty(value = "商业名称")
	private String commerceName;
	@ApiModelProperty(value = "商品名称")
	private String productName;
	@ApiModelProperty(value = "商品编码")
	private String productCode;
	@ApiModelProperty(value = "系列名称")
	private String productLineName;

	@ApiModelProperty(value = "通用名")
	private String commonName;
	@ApiModelProperty(value = "规格")
	private String productState;
	@ApiModelProperty(value = "剂型")
	private String agentType;
	@ApiModelProperty(value = "生产厂商")
	private String manufacturer;
	@ApiModelProperty(value = "退货数量")
	private String productSum;
	@ApiModelProperty(value = "单位")
	private String unit;
	@ApiModelProperty(value = "批号")
	private String batchNumber;
	@ApiModelProperty(value = "有效期")
	private String expiryDate;

	@ApiModelProperty(value = "有效期")
	private String validityMonth;
	@ApiModelProperty(value = "批准文号")
	private String approvalCode;
	@ApiModelProperty(value = "备注")
	private String memo;
	@ApiModelProperty(value = "仓库")
	private String getMoneyWarehouse;

	@ApiModelProperty(value = "商品字码")
	private String code;
	@ApiModelProperty(value = "药品类型")
	private String medicineType ;
	@ApiModelProperty(value = "药品分类")
	private String medicineClassify;

	@ApiModelProperty(value = "经营范围")
	private String businessScope;


	@ApiModelProperty(value = "是否推送至wms")
	private String isToWms;

	public String getBusinessScope() {
		return businessScope;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}

	public String getIsToWms() {
		return isToWms;
	}

	public void setIsToWms(String isToWms) {
		this.isToWms = isToWms;
	}

	@ApiModelProperty(value = "退货日期")
	private Date createDate;

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMedicineType() {
		return medicineType;
	}

	public void setMedicineType(String medicineType) {
		this.medicineType = medicineType;
	}

	public String getMedicineClassify() {
		return medicineClassify;
	}

	public void setMedicineClassify(String medicineClassify) {
		this.medicineClassify = medicineClassify;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getCommerceName() {
		return commerceName;
	}

	public void setCommerceName(String commerceName) {
		this.commerceName = commerceName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductLineName() {
		return productLineName;
	}

	public void setProductLineName(String productLineName) {
		this.productLineName = productLineName;
	}

	public String getCommonName() {
		return commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public String getProductState() {
		return productState;
	}

	public void setProductState(String productState) {
		this.productState = productState;
	}

	public String getAgentType() {
		return agentType;
	}

	public void setAgentType(String agentType) {
		this.agentType = agentType;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getProductSum() {
		return productSum;
	}

	public void setProductSum(String productSum) {
		this.productSum = productSum;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getValidityMonth() {
		return validityMonth;
	}

	public void setValidityMonth(String validityMonth) {
		this.validityMonth = validityMonth;
	}

	public String getApprovalCode() {
		return approvalCode;
	}

	public void setApprovalCode(String approvalCode) {
		this.approvalCode = approvalCode;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getGetMoneyWarehouse() {
		return getMoneyWarehouse;
	}

	public void setGetMoneyWarehouse(String getMoneyWarehouse) {
		this.getMoneyWarehouse = getMoneyWarehouse;
	}
}
