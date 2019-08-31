package com.winway.scm.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "VInvoice",description = "商业发票表视图") 
public class VInvoice {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="核销状态(0:未核销,1:已核销)")
	protected String verifyType; 
	
	@ApiModelProperty(value="商业ID")
	protected String commerceId; 
	
	@ApiModelProperty(value="商品编号")
	protected String commerceCode; 
	
	@ApiModelProperty(value="商业名称")
	protected String commerceName; 
	
	@ApiModelProperty(value="商务分区")
	protected String businessDivision; 
	
	@ApiModelProperty(value="省区")
	protected String province; 
	
	@ApiModelProperty(value="商务分区ID")
	protected String businessDivisionId; 
	
	@ApiModelProperty(value="省区id")
	protected String provinceId; 
	
	@ApiModelProperty(value="发票类型(0:原始发票)")
	protected String invoiceType; 
	
	@ApiModelProperty(value="开票日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected Date invoiceDate; 
	
	@ApiModelProperty(value="调整后开票日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected Date updateInvoiceDate; 
	
	@ApiModelProperty(value="发票号码")
	protected String invoiceCode; 
	
	@ApiModelProperty(value="产品通用名称")
	protected String commonName; 
	
	@ApiModelProperty(value="规格")
	protected String productState; 
	
	@ApiModelProperty(value="数量")
	protected Integer productSum; 
	
	@ApiModelProperty(value="含税单价")
	protected Double unitPrice; 
	
	@ApiModelProperty(value="价税合计")
	protected Double priceTaxSum; 
	
	@ApiModelProperty(value="发货日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected Date shipmentDate; 
	
	@ApiModelProperty(value="发货单号")
	protected String shipmentCode; 
	
	@ApiModelProperty(value="合同号")
	protected String contractCode; 
	
	@ApiModelProperty(value="汇款方式")
	protected String remittanceType; 
	
	@ApiModelProperty(value="备注")
	protected String memo; 
	
	@ApiModelProperty(value="采集人")
	protected String gatherPersion; 
	
	@ApiModelProperty(value="采集时间")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected Date gathererDate; 
	
	@ApiModelProperty(value="调整人")
	protected String setUpPersion; 
	
	@ApiModelProperty(value="调整时间")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected Date setUpDate; 
	
	@ApiModelProperty(value="货主ID")
	protected String ownerId; 
	@ApiModelProperty(value="是否确认(0:未确认,1:已确认)")
	protected String isAffirm;
	
	@ApiModelProperty(value="记录表主键")
	protected String blendId;
	
	@ApiModelProperty(value="勾兑状态(0:取消,1:同意,2操作中)")
	protected String blendType;
	
	@ApiModelProperty(value="回款表主键")
	protected String returnMoneyId;
	
	@ApiModelProperty(value="勾兑时间")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected Date blendDate;

	@ApiModelProperty(value="确认人")
	protected String blendPersion;


	@ApiModelProperty(value="勾兑人")
	protected String affirmPersion;



	@ApiModelProperty(value="确认日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected Date affirmDate;
	
	@ApiModelProperty(value="勾兑金额")
	protected String blendprice;


	@ApiModelProperty(value="剩余金额")
	protected String balancePrice;

	public String getBalancePrice() {
		return balancePrice;
	}

	public void setBalancePrice(String balancePrice) {
		this.balancePrice = balancePrice;
	}

	public String getBlendPersion() {
		return blendPersion;
	}

	public void setBlendPersion(String blendPersion) {
		this.blendPersion = blendPersion;
	}

	public String getAffirmPersion() {
		return affirmPersion;
	}

	public void setAffirmPersion(String affirmPersion) {
		this.affirmPersion = affirmPersion;
	}

	public Date getBlendDate() {
		return blendDate;
	}
	public void setBlendDate(Date blendDate) {
		this.blendDate = blendDate;
	}
	public Date getAffirmDate() {
		return affirmDate;
	}
	public void setAffirmDate(Date affirmDate) {
		this.affirmDate = affirmDate;
	}
	public String getBlendprice() {
		return blendprice;
	}
	public void setBlendprice(String blendprice) {
		this.blendprice = blendprice;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVerifyType() {
		return verifyType;
	}
	public void setVerifyType(String verifyType) {
		this.verifyType = verifyType;
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
	public String getBusinessDivision() {
		return businessDivision;
	}
	public void setBusinessDivision(String businessDivision) {
		this.businessDivision = businessDivision;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getBusinessDivisionId() {
		return businessDivisionId;
	}
	public void setBusinessDivisionId(String businessDivisionId) {
		this.businessDivisionId = businessDivisionId;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	public java.util.Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(java.util.Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public java.util.Date getUpdateInvoiceDate() {
		return updateInvoiceDate;
	}
	public void setUpdateInvoiceDate(java.util.Date updateInvoiceDate) {
		this.updateInvoiceDate = updateInvoiceDate;
	}
	public String getInvoiceCode() {
		return invoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
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
	public Integer getProductSum() {
		return productSum;
	}
	public void setProductSum(Integer productSum) {
		this.productSum = productSum;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Double getPriceTaxSum() {
		return priceTaxSum;
	}
	public void setPriceTaxSum(Double priceTaxSum) {
		this.priceTaxSum = priceTaxSum;
	}
	public java.util.Date getShipmentDate() {
		return shipmentDate;
	}
	public void setShipmentDate(java.util.Date shipmentDate) {
		this.shipmentDate = shipmentDate;
	}
	public String getShipmentCode() {
		return shipmentCode;
	}
	public void setShipmentCode(String shipmentCode) {
		this.shipmentCode = shipmentCode;
	}
	public String getContractCode() {
		return contractCode;
	}
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	public String getRemittanceType() {
		return remittanceType;
	}
	public void setRemittanceType(String remittanceType) {
		this.remittanceType = remittanceType;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getGatherPersion() {
		return gatherPersion;
	}
	public void setGatherPersion(String gatherPersion) {
		this.gatherPersion = gatherPersion;
	}
	public java.util.Date getGathererDate() {
		return gathererDate;
	}
	public void setGathererDate(java.util.Date gathererDate) {
		this.gathererDate = gathererDate;
	}
	public String getSetUpPersion() {
		return setUpPersion;
	}
	public void setSetUpPersion(String setUpPersion) {
		this.setUpPersion = setUpPersion;
	}
	public java.util.Date getSetUpDate() {
		return setUpDate;
	}
	public void setSetUpDate(java.util.Date setUpDate) {
		this.setUpDate = setUpDate;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getIsAffirm() {
		return isAffirm;
	}
	public void setIsAffirm(String isAffirm) {
		this.isAffirm = isAffirm;
	}
	public String getBlendId() {
		return blendId;
	}
	public void setBlendId(String blendId) {
		this.blendId = blendId;
	}
	public String getBlendType() {
		return blendType;
	}
	public void setBlendType(String blendType) {
		this.blendType = blendType;
	}
	public String getReturnMoneyId() {
		return returnMoneyId;
	}
	public void setReturnMoneyId(String returnMoneyId) {
		this.returnMoneyId = returnMoneyId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
