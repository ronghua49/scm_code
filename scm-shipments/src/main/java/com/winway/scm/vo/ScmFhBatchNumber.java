package com.winway.scm.vo;

import io.swagger.annotations.ApiModelProperty;

public class ScmFhBatchNumber {

	@ApiModelProperty(value = "批号")
	protected String batchNumber;
	@ApiModelProperty(value = "件装量")
	protected String aChange;
	@ApiModelProperty(value = "仓库")
	protected String warehouse;
	@ApiModelProperty(value = "仓库编号")
	protected String warehouseCode;
	@ApiModelProperty(value = "数量")
	protected String inventoryNum;
	@ApiModelProperty(value = "效期")
	protected String expiryDate;
	public String getBatchNumber() {
		return batchNumber;
	}
	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
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
	public String getWarehouseCode() {
		return warehouseCode;
	}
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	public String getInventoryNum() {
		return inventoryNum;
	}

	public void setInventoryNum(String inventoryNum) {
		this.inventoryNum = inventoryNum;
	}

	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	
	
}
