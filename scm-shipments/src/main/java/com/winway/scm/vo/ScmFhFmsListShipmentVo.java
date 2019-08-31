package com.winway.scm.vo;

import io.swagger.annotations.ApiModelProperty;

public class ScmFhFmsListShipmentVo {

	@ApiModelProperty(value = "商业编码")
	protected String businessCode;
	@ApiModelProperty(value = "商业名称")
	protected String businessName;
	@ApiModelProperty(value = "发货确认月份")
	protected String Monthly;
	@ApiModelProperty(value = "产品编码")
	protected String prodCode;
	@ApiModelProperty(value = "产品名称")
	protected String prod;
	@ApiModelProperty(value = "发货数量")
	protected String salesVolume;
	@ApiModelProperty(value = "发货时间")
	protected String salesDate;
	public String getBusinessCode() {
		return businessCode;
	}
	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getMonthly() {
		return Monthly;
	}
	public void setMonthly(String monthly) {
		Monthly = monthly;
	}
	public String getProdCode() {
		return prodCode;
	}
	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}
	public String getProd() {
		return prod;
	}
	public void setProd(String prod) {
		this.prod = prod;
	}
	public String getSalesVolume() {
		return salesVolume;
	}
	public void setSalesVolume(String salesVolume) {
		this.salesVolume = salesVolume;
	}
	public String getSalesDate() {
		return salesDate;
	}
	public void setSalesDate(String salesDate) {
		this.salesDate = salesDate;
	}

}
