package com.winway.scm.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 * 当此退货修改页面数据
 */
public class SalesRecordVo {
	
	@ApiModelProperty(value = "发货明细ID")
	private String id;
	@ApiModelProperty(value = "发货单号")
	private String shipmentsCode;
	@ApiModelProperty(value = "销售合同编号")
	private String dateCode;
	@ApiModelProperty(value = "销售日期")
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
	private Date shipmentsAffirmDate;
	@ApiModelProperty(value = "产品类型")
	private String medicineType;
	@ApiModelProperty(value = "产品系列")
	private String productLineName;
	@ApiModelProperty(value = "产品系列ID")
	private String productLineId;
	@ApiModelProperty(value = "商品编号")
	private String productNum;
	@ApiModelProperty(value = "商品通用名")
	private String commonName;

	@ApiModelProperty(value = "商品名")
	private String productName;
	@ApiModelProperty(value = "商品规格")
	private String productState;
	@ApiModelProperty(value = "机型")
	private String agentType;
	@ApiModelProperty(value = "")
	private String manufacturer;
	@ApiModelProperty(value = "商业编号")
	private String commerceCode;
	@ApiModelProperty(value = "批号")
	private String commerceName;
	@ApiModelProperty(value = "销售数量")
	private int deliveringAmount;
	@ApiModelProperty(value = "单位")
	private String unit;
	@ApiModelProperty(value = "批号")
	private String batchNumber;
	@ApiModelProperty(value = "生产日期")
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
	private Date dateOfManufacture;
	@ApiModelProperty(value = "单价")
	private double price;
	@ApiModelProperty(value = "总价")
	private double priceSum;
	@ApiModelProperty(value = "申请人")
	private String createPersion;
	@ApiModelProperty(value = "备注")
	private String memo;
	@ApiModelProperty(value = "批准文号")
	private String approvalCode;
	@ApiModelProperty(value = "类别")
	private String medicineClassify;
	@ApiModelProperty(value = "经营范围")
	private String businessScope;

	@ApiModelProperty(value = "经营范围")
	private String validityMonth;


	public String getValidityMonth() {
		return validityMonth;
	}

	public void setValidityMonth(String validityMonth) {
		this.validityMonth = validityMonth;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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

	public String getShipmentsCode() {
		return shipmentsCode;
	}
	public void setShipmentsCode(String shipmentsCode) {
		this.shipmentsCode = shipmentsCode;
	}
	public String getDateCode() {
		return dateCode;
	}
	public void setDateCode(String dateCode) {
		this.dateCode = dateCode;
	}
	public Date getShipmentsAffirmDate() {
		return shipmentsAffirmDate;
	}
	public void setShipmentsAffirmDate(Date shipmentsAffirmDate) {
		this.shipmentsAffirmDate = shipmentsAffirmDate;
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
	public String getProductNum() {
		return productNum;
	}
	public void setProductNum(String productNum) {
		this.productNum = productNum;
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
	public int getDeliveringAmount() {
		return deliveringAmount;
	}
	public void setDeliveringAmount(int deliveringAmount) {
		this.deliveringAmount = deliveringAmount;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getBatchNumber() {
		return batchNumber;
	}
	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}
	public Date getDateOfManufacture() {
		return dateOfManufacture;
	}
	public void setDateOfManufacture(Date dateOfManufacture) {
		this.dateOfManufacture = dateOfManufacture;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
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
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
