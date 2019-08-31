package com.winway.scm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**采购补单回执*/
@ApiModel(value = "WmsCgbdReceiptVo",description = "采购补单回执VO") 
public class WmsCgbdReceiptVo {
	@ApiModelProperty(name="采购补单号")
	private String orderCode;
	 
	@ApiModelProperty(name="1(订单处理完成)")
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
	 
	 
}
