package com.winway.scm.vo;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class ScmXsBigContractProductByUpdateVo implements Serializable{

	@ApiModelProperty(value = "商品ID")
	protected String productId;

	@ApiModelProperty(value = "商品名称")
	protected String productName;

	@ApiModelProperty(value = "药品类型")
	protected String MedicineType;

	@ApiModelProperty(value = "通用名称")
	protected String commonName;

	@ApiModelProperty(value = "规格")
	protected String productState;

	@ApiModelProperty(value = "单位")
	protected String unit;

	@ApiModelProperty(value = "协议品规ID")
	protected String agreementproductdetailId;

	@ApiModelProperty(value = "协议汇总ID")
	protected String agreementSummaryId;

	@ApiModelProperty(value = "品规")
	protected String productGauge;

	@ApiModelProperty(value = "件装量")
	protected int achage;

	@ApiModelProperty(value = "药品编码")
	protected String productCode;

	@ApiModelProperty(value = "数量")
	protected int theNumber;

	@ApiModelProperty(value = "件数")
	protected int packageNum;

	@ApiModelProperty(value = "单价")
	protected double unitPrice;

	@ApiModelProperty(value = "总数")
	protected double sumPrice;

	@ApiModelProperty(value = "供应状态")
	protected String suppyType;

    @ApiModelProperty(value = "是否SMS")
    protected String isSMS;

	@ApiModelProperty(value = "上次发货时间")
	protected Date lastShipmentDate;


	@ApiModelProperty(value = "剂型")
	protected String AgentType;
	@ApiModelProperty(value = "厂商")
	protected String manufacturer;
	@ApiModelProperty(value = "药品类型")
	protected String medicineClassify;
	@ApiModelProperty(value = "营业范围")
	protected String businessScope;
	@ApiModelProperty(value = "批准文号")
	protected String approvalCode;

	@ApiModelProperty(value = "字码")
	protected String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getApprovalCode() {
		return approvalCode;
	}

	public void setApprovalCode(String approvalCode) {
		this.approvalCode = approvalCode;
	}

	public String getAgentType() {
		return AgentType;
	}

	public void setAgentType(String agentType) {
		AgentType = agentType;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
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

	public Date getLastShipmentDate() {
		return lastShipmentDate;
	}

	public void setLastShipmentDate(Date lastShipmentDate) {
		this.lastShipmentDate = lastShipmentDate;
	}

	public String getIsSMS() {
        return isSMS;
    }

    public void setIsSMS(String isSMS) {
        this.isSMS = isSMS;
    }

    public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
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

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getAgreementproductdetailId() {
		return agreementproductdetailId;
	}

	public void setAgreementproductdetailId(String agreementproductdetailId) {
		this.agreementproductdetailId = agreementproductdetailId;
	}

	public String getAgreementSummaryId() {
		return agreementSummaryId;
	}

	public void setAgreementSummaryId(String agreementSummaryId) {
		this.agreementSummaryId = agreementSummaryId;
	}

	public String getProductGauge() {
		return productGauge;
	}

	public void setProductGauge(String productGauge) {
		this.productGauge = productGauge;
	}

	public int getAchage() {
		return achage;
	}

	public void setAchage(int achage) {
		this.achage = achage;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public int getTheNumber() {
		return theNumber;
	}

	public void setTheNumber(int theNumber) {
		this.theNumber = theNumber;
	}

	public int getPackageNum() {
		return packageNum;
	}

	public void setPackageNum(int packageNum) {
		this.packageNum = packageNum;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(double sumPrice) {
		this.sumPrice = sumPrice;
	}

	public String getSuppyType() {
		return suppyType;
	}

	public void setSuppyType(String suppyType) {
		this.suppyType = suppyType;
	}

}
