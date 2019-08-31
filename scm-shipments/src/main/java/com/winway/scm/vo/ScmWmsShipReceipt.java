package com.winway.scm.vo;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class ScmWmsShipReceipt {
	@ApiModelProperty(value="发货单号")
	private String orderCode;
	@ApiModelProperty(value="1(发货单完成)")
	private String type;
	@ApiModelProperty(value="物流单号")
	private String logisticsCode;
	@ApiModelProperty(value="仓库实际发货日期")
	private Date actualDeliveryDate;
	@ApiModelProperty(value="预计到货日期")
	private Date expectedArrivalDate;
	@ApiModelProperty(value="运输方式")
	private String transportationMethod;
	@ApiModelProperty(value="异常情况")
	private String exceptionCase;
	@ApiModelProperty(value="说明")
	private String explain;
	@ApiModelProperty(value="驾驶员")
	private String driver;
	@ApiModelProperty(value="车牌号")
	private String licencePlate;
	@ApiModelProperty(value="委托经办人")
	private String entrustPersion;
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLogisticsCode() {
		return logisticsCode;
	}
	public void setLogisticsCode(String logisticsCode) {
		this.logisticsCode = logisticsCode;
	}
	public Date getActualDeliveryDate() {
		return actualDeliveryDate;
	}
	public void setActualDeliveryDate(Date actualDeliveryDate) {
		this.actualDeliveryDate = actualDeliveryDate;
	}
	public Date getExpectedArrivalDate() {
		return expectedArrivalDate;
	}
	public void setExpectedArrivalDate(Date expectedArrivalDate) {
		this.expectedArrivalDate = expectedArrivalDate;
	}
	public String getTransportationMethod() {
		return transportationMethod;
	}
	public void setTransportationMethod(String transportationMethod) {
		this.transportationMethod = transportationMethod;
	}
	public String getExceptionCase() {
		return exceptionCase;
	}
	public void setExceptionCase(String exceptionCase) {
		this.exceptionCase = exceptionCase;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getLicencePlate() {
		return licencePlate;
	}
	public void setLicencePlate(String licencePlate) {
		this.licencePlate = licencePlate;
	}
	public String getEntrustPersion() {
		return entrustPersion;
	}
	public void setEntrustPersion(String entrustPersion) {
		this.entrustPersion = entrustPersion;
	}
	
}
