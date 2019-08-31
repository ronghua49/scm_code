package com.winway.scm.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ScmFhBatchNumber {

	@ApiModelProperty(value = "批号")
	protected String batchNum;
	@ApiModelProperty(value = "件装量")
	protected String aChange;
	@ApiModelProperty(value = "仓库")
	protected String warehouse;
	@ApiModelProperty(value = "仓库类型")
	protected String warehouseType;
	@ApiModelProperty(value = "仓库编号")
	protected String warehouseCode;
	@ApiModelProperty(value = "数量")
	protected String quantityInStock;
	@ApiModelProperty(value = "箱体类型(Y:原箱,N:非原箱)")
	protected String xtlx;
	@ApiModelProperty(value = "生产日期")
	protected String productionDate;
	@ApiModelProperty(value = "失效日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	protected Date expiryDate;
    @ApiModelProperty(value = "单价")
    protected String price;
	public String getBatchNum() {
		return batchNum;
	}
	public String getXtlx() {
		return xtlx;
	}

	public void setXtlx(String xtlx) {
		this.xtlx = xtlx;
	}

	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}
	public String getaChange() {
		return aChange;
	}
	public void setaChange(String aChange) {
		this.aChange = aChange;
	}
	public String getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}
	public String getWarehouseType() {
		return warehouseType;
	}
	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
	}
	public String getWarehouseCode() {
		return warehouseCode;
	}
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
	public String getQuantityInStock() {
		return quantityInStock;
	}
	public void setQuantityInStock(String quantityInStock) {
		this.quantityInStock = quantityInStock;
	}
	public String getProductionDate() {
		return productionDate;
	}
	public void setProductionDate(String productionDate) {
		this.productionDate = productionDate;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
 
	
}
