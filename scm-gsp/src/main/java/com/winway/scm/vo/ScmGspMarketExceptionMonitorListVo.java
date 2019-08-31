package com.winway.scm.vo;

import io.swagger.annotations.ApiModelProperty;

public class ScmGspMarketExceptionMonitorListVo {

	@ApiModelProperty(value = "商品类型:1:普通药品,2特殊药品(必传)", required = true)
	private Integer itemtype;

	@ApiModelProperty(value = "商品编码", required = true)
	protected String productCode;

	@ApiModelProperty(value = "商品系列", required = true)
	protected String productLine;

	@ApiModelProperty(value = "商品名", required = true)
	protected String productName;

	@ApiModelProperty(value = "规格", required = true)
	protected String productState;

	@ApiModelProperty(value = "销售预警数量", required = true)
	protected Integer warningNum;

	@ApiModelProperty(value = "本月销量", required = true)
	protected Integer oneMonth;

	@ApiModelProperty(value = "上月销量", required = true)
	protected Integer towMonth;

	@ApiModelProperty(value = "货主ID", required = true)
	protected String ownerId;
	
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductLine() {
		return productLine;
	}

	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductState() {
		return productState;
	}

	public void setProductState(String productState) {
		this.productState = productState;
	}

	public Integer getWarningNum() {
		return warningNum;
	}

	public void setWarningNum(Integer warningNum) {
		this.warningNum = warningNum;
	}

	public Integer getOneMonth() {
		return oneMonth;
	}

	public void setOneMonth(Integer oneMonth) {
		this.oneMonth = oneMonth;
	}

	public Integer getTowMonth() {
		return towMonth;
	}

	public void setTowMonth(Integer towMonth) {
		this.towMonth = towMonth;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

}
