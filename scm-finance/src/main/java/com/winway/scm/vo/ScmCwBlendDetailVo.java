package com.winway.scm.vo;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "ScmCwBlendDetailVo", description = "勾兑明细视图")
public class ScmCwBlendDetailVo {
	@ApiModelProperty(value = "勾兑记录ID")
	private String id;
	@ApiModelProperty(value = "商业ID")
	private String commerceId;
	@ApiModelProperty(value = "商业编码")
	private String commerceCode;
	@ApiModelProperty(value = "商业名称")
	private String commerceName;
	@ApiModelProperty(value = "商业级别字符串")
	private String typeStr;
	@ApiModelProperty(value = "商业级别")
	private String type;
	@ApiModelProperty(value = "结算方式")
	private String remittanceType;
	@ApiModelProperty(value = "产品名称")
	private String commonName;
	@ApiModelProperty(value = "合同号")
	private String contractCode;
	@ApiModelProperty(value = "发票号码")
	private String invoiceCode;
	@ApiModelProperty(value = "发票备注")
	private String memo;
	@ApiModelProperty(value = "是否预付款")
	private String paymentType;
	@ApiModelProperty(value = "回款金额")
	private double blendprice;
	@ApiModelProperty(value = "回款日期")
	private Date blendDate;
	@ApiModelProperty(value = "开票日期")
	private Date invoiceDate;

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

	public String getTypeStr() {
		return typeStr;
	}

	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemittanceType() {
		return remittanceType;
	}

	public void setRemittanceType(String remittanceType) {
		this.remittanceType = remittanceType;
	}

	public String getCommonName() {
		return commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public double getBlendprice() {
		return blendprice;
	}

	public void setBlendprice(double blendprice) {
		this.blendprice = blendprice;
	}

	public Date getBlendDate() {
		return blendDate;
	}

	public void setBlendDate(Date blendDate) {
		this.blendDate = blendDate;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

}
