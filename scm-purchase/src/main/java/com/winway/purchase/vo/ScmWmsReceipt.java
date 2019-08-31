package com.winway.purchase.vo;

import io.swagger.annotations.ApiModelProperty;

public class ScmWmsReceipt {
	@ApiModelProperty(value="订单号")
	private String orderCode;
	@ApiModelProperty(value="订单号")
	private String shipmentsCode;
	@ApiModelProperty(value="状态")
	private String typt;
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getTypt() {
		return typt;
	}
	public void setTypt(String typt) {
		this.typt = typt;
	}
	public String getShipmentsCode() {
		return shipmentsCode;
	}
	public void setShipmentsCode(String shipmentsCode) {
		this.shipmentsCode = shipmentsCode;
	}
	

}
