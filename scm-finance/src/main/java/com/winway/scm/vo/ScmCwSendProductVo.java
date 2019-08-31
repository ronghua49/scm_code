package com.winway.scm.vo;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "ScmCwBlendDetailVo", description = "勾兑明细视图")
public class ScmCwSendProductVo {

	@ApiModelProperty(value = "勾兑记录ID")
	private String id;
	@ApiModelProperty(value = "商业ID")
	private String commerceId;
	@ApiModelProperty(value = "商业编码")
	private String commerceCode;
	@ApiModelProperty(value = "商业名称")
	private String commerceName;
	@ApiModelProperty(value = "发货日期")
	private Date shipmentDate;
	@ApiModelProperty(value = "失效日期")
	private Date loseDate;
	@ApiModelProperty(value = "发货单号")
	private String shipmentCode;
	@ApiModelProperty(value = "批号")
	private String batchNumber;
	@ApiModelProperty(value = "产品名称")
	private String commonName;
	@ApiModelProperty(value = "商品数量")
	private int productSum;
	@ApiModelProperty(value = "单价")
	private double unitPrice;
	@ApiModelProperty(value = "总价")
	private double priceSum;
	@ApiModelProperty(value = "商业级别")
	private String type;
	@ApiModelProperty(value = "任务类型")
	private String taskType;
	@ApiModelProperty(value = "仓库类型")
	private String warehouseType;

	public Date getLoseDate() {
		return loseDate;
	}

	public void setLoseDate(Date loseDate) {
		this.loseDate = loseDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getWarehouseType() {
		return warehouseType;
	}

	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCommerceId() {
		return commerceId;
	}

	public void setCommerceId(String commerceId) {
		this.commerceId = commerceId;
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

	public Date getShipmentDate() {
		return shipmentDate;
	}

	public void setShipmentDate(Date shipmentDate) {
		this.shipmentDate = shipmentDate;
	}

	public String getShipmentCode() {
		return shipmentCode;
	}

	public void setShipmentCode(String shipmentCode) {
		this.shipmentCode = shipmentCode;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public String getCommonName() {
		return commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public int getProductSum() {
		return productSum;
	}

	public void setProductSum(int productSum) {
		this.productSum = productSum;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getPriceSum() {
		return priceSum;
	}

	public void setPriceSum(double priceSum) {
		this.priceSum = priceSum;
	}

}
