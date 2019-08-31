package com.winway.scm.vo;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 不合格品报损记录
 */
public class BreakageRecord {
	@ApiModelProperty(value="报损单详情ID")
	private String id;
	@ApiModelProperty(value="报损单号")
	private String breakageCode;
	@ApiModelProperty(value="商品编号")
	private String productCode;
	@ApiModelProperty(value="药品类型")
	private String medicineType;
	@ApiModelProperty(value="产品系列")
	private String productLineName;
	@ApiModelProperty(value="产品系列ID")
	private String productLineId;
	@ApiModelProperty(value="通用名")
	private String commonName;
	@ApiModelProperty(value="规格")
	private String productState;
	@ApiModelProperty(value="剂型")
	private String agentType;
	@ApiModelProperty(value="生产厂商")
	private String manufacturer;
	@ApiModelProperty(value="报损数量")
	private int breakageSum;
	@ApiModelProperty(value="单位")
	private String unit;
	@ApiModelProperty(value="批号")
	private String batchNum;
	@ApiModelProperty(value="有效期")
	private Date expiryDate;
	@ApiModelProperty(value="批准文号")
	private String approvalCode;
	@ApiModelProperty(value="供应商")
	private String supplier;
	@ApiModelProperty(value="不合格原因")
	private String memo;
	@ApiModelProperty(value="责任人")
	private String dutyPersion;
	@ApiModelProperty(value="单价")
	private double purchasingCost;
	@ApiModelProperty(value="总价")
	private double priceSum;
	@ApiModelProperty(value="申请人")
	private String createPersion;
	@ApiModelProperty(value="申请时间")
	private Date createDate;
	@ApiModelProperty(value="货主id")
	private String ownerId;

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBreakageCode() {
		return breakageCode;
	}
	public void setBreakageCode(String breakageCode) {
		this.breakageCode = breakageCode;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getMedicineType() {
		return medicineType;
	}
	public void setMedicineType(String medicineType) {
		this.medicineType = medicineType;
	}
	public String getProductLineName() {
		return productLineName;
	}
	public void setProductLineName(String productLineName) {
		this.productLineName = productLineName;
	}
	public String getProductLineId() {
		return productLineId;
	}
	public void setProductLineId(String productLineId) {
		this.productLineId = productLineId;
	}
	public String getCommonName() {
		return commonName;
	}
	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}
	public String getProductState() {
		return productState;
	}
	public void setProductState(String productState) {
		this.productState = productState;
	}
	public String getAgentType() {
		return agentType;
	}
	public void setAgentType(String agentType) {
		this.agentType = agentType;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public int getBreakageSum() {
		return breakageSum;
	}
	public void setBreakageSum(int breakageSum) {
		this.breakageSum = breakageSum;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getBatchNum() {
		return batchNum;
	}
	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getApprovalCode() {
		return approvalCode;
	}
	public void setApprovalCode(String approvalCode) {
		this.approvalCode = approvalCode;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getDutyPersion() {
		return dutyPersion;
	}
	public void setDutyPersion(String dutyPersion) {
		this.dutyPersion = dutyPersion;
	}
	public double getPurchasingCost() {
		return purchasingCost;
	}
	public void setPurchasingCost(double purchasingCost) {
		this.purchasingCost = purchasingCost;
	}
	public double getPriceSum() {
		return priceSum;
	}
	public void setPriceSum(double priceSum) {
		this.priceSum = priceSum;
	}
	public String getCreatePersion() {
		return createPersion;
	}
	public void setCreatePersion(String createPersion) {
		this.createPersion = createPersion;
	}
	
}
