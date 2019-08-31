package com.winway.scm.vo;

import io.swagger.annotations.ApiModelProperty;

public class ScmKcMonthInventory {
	
    @ApiModelProperty(value = "商务分区")
	private String businessDivision;
    @ApiModelProperty(value = "省区")
	private String province;
    @ApiModelProperty(value = "商务分区ID")
	private String businessDivisionId;
    @ApiModelProperty(value = "省区ID")
	private String provinceId;
    @ApiModelProperty(value = "年度")
	private String year;
    @ApiModelProperty(value = "月度")
	private String month;
    @ApiModelProperty(value = "商业编号")
	private String commerceCode;
    @ApiModelProperty(value = "商业名称")
	private String commerceName;
    @ApiModelProperty(value = "产品编码")
	private String productCode;
    @ApiModelProperty(value = "产品名称")
	private String productName;
    @ApiModelProperty(value = "批号")
	private String batchNum;
    @ApiModelProperty(value = "创建人")
	private String createPersion;
    @ApiModelProperty(value = "本月库存")
	private String monthInventory;
    
	public String getBusinessDivision() {
		return businessDivision;
	}
	public void setBusinessDivision(String businessDivision) {
		this.businessDivision = businessDivision;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getBusinessDivisionId() {
		return businessDivisionId;
	}
	public void setBusinessDivisionId(String businessDivisionId) {
		this.businessDivisionId = businessDivisionId;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getCommerceCode() {
		return commerceCode;
	}
	public void setCommerceCode(String commerceCode) {
		this.commerceCode = commerceCode;
	}
	public String getCommerceName() {
		return commerceName;
	}
	public void setCommerceName(String commerceName) {
		this.commerceName = commerceName;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getBatchNum() {
		return batchNum;
	}
	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}
	public String getCreatePersion() {
		return createPersion;
	}
	public void setCreatePersion(String createPersion) {
		this.createPersion = createPersion;
	}
	public String getMonthInventory() {
		return monthInventory;
	}
	public void setMonthInventory(String monthInventory) {
		this.monthInventory = monthInventory;
	}

}
