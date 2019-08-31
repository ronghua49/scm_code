package com.winway.scm.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class InventoryProduct {

	@ApiModelProperty(value = "批号")
	protected String batchNumber;
	@ApiModelProperty(value = "件装量")
	protected String aChange;
	@ApiModelProperty(value = "仓库")
	protected String warehouse;
	@ApiModelProperty(value = "仓库类型")
	protected String warehouseType;
	@ApiModelProperty(value = "仓库编号")
	protected String warehouseCode;
	@ApiModelProperty(value = "数量")
	protected String currInventoryNum;
	@ApiModelProperty(value = "效期")
	protected String expiryDate;
	@ApiModelProperty(value = "失效日期")
	protected Date loseDate;
	@ApiModelProperty(value = "商品名称")
	protected String productName;
	@ApiModelProperty(value="药品类型")
	protected String MedicineType;
	@ApiModelProperty(value="规格")
	protected String productState;
	@ApiModelProperty(value = "单价")
	protected String price;
	@ApiModelProperty(value = "入库日期")
	protected String InboundDate;

	@ApiModelProperty(value = "采购订单ID")
	protected String procurementId;

	@ApiModelProperty(value = "采购订单号")
	protected String procurementCode;

	@ApiModelProperty(value = "生产日期")
	protected String produceDate;

	@ApiModelProperty(value = "包装规格")
	protected String guiGe;

	@ApiModelProperty(value = "商品id")
	protected String productId;


	@ApiModelProperty(value = "单位")
	protected String unit;

	@ApiModelProperty(value = "生产厂商")
	protected String manufacturer;
	@ApiModelProperty(value = "通用名称")
	protected String commonName;

	@ApiModelProperty(value = "商品编码")
	protected String productCode;

	@ApiModelProperty(value = "剂型")
	protected String agentType;

	@ApiModelProperty(value = "批准文号")
	protected String approvalCode;

	@ApiModelProperty(value = "药品分类")
	protected String medicineClassify;

	@ApiModelProperty(value = "经营范围")
	protected String businessScope;


	public String getProcurementId() {
		return procurementId;
	}

	public void setProcurementId(String procurementId) {
		this.procurementId = procurementId;
	}

	public String getProcurementCode() {
		return procurementCode;
	}

	public void setProcurementCode(String procurementCode) {
		this.procurementCode = procurementCode;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getCommonName() {
		return commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getAgentType() {
		return agentType;
	}

	public void setAgentType(String agentType) {
		this.agentType = agentType;
	}

	public String getApprovalCode() {
		return approvalCode;
	}

	public void setApprovalCode(String approvalCode) {
		this.approvalCode = approvalCode;
	}

	public String getMedicineClassify() {
		return medicineClassify;
	}

	public void setMedicineClassify(String medicineClassify) {
		this.medicineClassify = medicineClassify;
	}

	public String getBusinessScope() {
		return businessScope;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getProduceDate() {
		return produceDate;
	}

	public void setProduceDate(String produceDate) {
		this.produceDate = produceDate;
	}

	public String getGuiGe() {
		return guiGe;
	}

	public void setGuiGe(String guiGe) {
		this.guiGe = guiGe;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getMedicineType() {
		return MedicineType;
	}

	public void setMedicineType(String medicineType) {
		MedicineType = medicineType;
	}

	public String getProductState() {
		return productState;
	}

	public void setProductState(String productState) {
		this.productState = productState;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getInboundDate() {
		return InboundDate;
	}

	public void setInboundDate(String inboundDate) {
		InboundDate = inboundDate;
	}

	public String getWarehouseType() {
		return warehouseType;
	}

	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
	}

	public Date getLoseDate() {
		return loseDate;
	}

	public void setLoseDate(Date loseDate) {
		this.loseDate = loseDate;
	}

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

	public String getCurrInventoryNum() {
		return currInventoryNum;
	}

	public void setCurrInventoryNum(String currInventoryNum) {
		this.currInventoryNum = currInventoryNum;
	}

	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	
	
}
