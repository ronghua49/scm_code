package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;


 /**
 * 经销商RB支付付款折扣
 * <pre> 
 * 描述：经销商RB支付付款折扣 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-09 18:18:05
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmCwPPaymentDiscount",description = "经销商RB支付付款折扣") 
public class ScmCwPPaymentDiscount extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="主表ID")
	protected String masterId; 
	
	@ApiModelProperty(value="商业ID")
	protected String commerceId; 
	
	@ApiModelProperty(value="商业名称")
	protected String commerceName; 
	
	@ApiModelProperty(value="商业编号")
	protected String commerceCode; 
	
	@ApiModelProperty(value="商务分区")
	protected String businessDivision; 
	
	@ApiModelProperty(value="商务分区ID")
	protected String businessDivisionId; 
	
	@ApiModelProperty(value="省区")
	protected String province; 
	
	@ApiModelProperty(value="省区ID")
	protected String provinceId; 
	
	@ApiModelProperty(value="付款折扣金额")
	protected Double paymentDiscountPrice; 
	
	@ApiModelProperty(value="商业级别")
	protected String commerceLevel; 
	
	@ApiModelProperty(value="结算方式")
	protected String clearForm; 
	
	@ApiModelProperty(value="产品名称")
	protected String productName; 
	
	@ApiModelProperty(value="合同号")
	protected String contractCode; 
	
	@ApiModelProperty(value="发票号码")
	protected String invoiceCode; 
	
	@ApiModelProperty(value="发票备注")
	protected String invoiceMemo; 
	
	@ApiModelProperty(value="是否预付款(0:预付款,1赊销)")
	protected String paymentType; 
	
	@ApiModelProperty(value="回款金额")
	protected Double price; 
	
	@ApiModelProperty(value="回款日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date remittanceDate; 
	
	@ApiModelProperty(value="调整回款日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date affirmDate; 
	
	@ApiModelProperty(value="开票日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date invoiceDate; 
	
	@ApiModelProperty(value="调整开票日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date updateInvoiceDate; 
	
	@ApiModelProperty(value="回款天数")
	protected Integer returnMoneyDay; 
	
	@ApiModelProperty(value="RB率%")
	protected Double rbRate; 
	
	@ApiModelProperty(value="合计RB%")
	protected Double totalRbRate; 
	
	@ApiModelProperty(value="本次计算RB")
	protected Double thisCalculationRb; 
	
	@ApiModelProperty(value="本次应发RB")
	protected Double thisShouldSendRb; 
	
	@ApiModelProperty(value="申请备注")
	protected String memo; 
	
	@ApiModelProperty(value="勾兑记录表id")
	protected String blendId; 
	
	
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 返回 id
	 * @return
	 */
	public String getId() {
		return this.id;
	}
	
	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}
	
	/**
	 * 返回 主表ID
	 * @return
	 */
	public String getMasterId() {
		return this.masterId;
	}
	
	public void setCommerceId(String commerceId) {
		this.commerceId = commerceId;
	}
	
	/**
	 * 返回 商业ID
	 * @return
	 */
	public String getCommerceId() {
		return this.commerceId;
	}
	
	public void setCommerceName(String commerceName) {
		this.commerceName = commerceName;
	}
	
	/**
	 * 返回 商业名称
	 * @return
	 */
	public String getCommerceName() {
		return this.commerceName;
	}
	
	public void setCommerceCode(String commerceCode) {
		this.commerceCode = commerceCode;
	}
	
	/**
	 * 返回 商业编号
	 * @return
	 */
	public String getCommerceCode() {
		return this.commerceCode;
	}
	
	public void setBusinessDivision(String businessDivision) {
		this.businessDivision = businessDivision;
	}
	
	/**
	 * 返回 商务分区
	 * @return
	 */
	public String getBusinessDivision() {
		return this.businessDivision;
	}
	
	public void setBusinessDivisionId(String businessDivisionId) {
		this.businessDivisionId = businessDivisionId;
	}
	
	/**
	 * 返回 商务分区ID
	 * @return
	 */
	public String getBusinessDivisionId() {
		return this.businessDivisionId;
	}
	
	public void setProvince(String province) {
		this.province = province;
	}
	
	/**
	 * 返回 省区
	 * @return
	 */
	public String getProvince() {
		return this.province;
	}
	
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	
	/**
	 * 返回 省区ID
	 * @return
	 */
	public String getProvinceId() {
		return this.provinceId;
	}
	
	public void setPaymentDiscountPrice(Double paymentDiscountPrice) {
		this.paymentDiscountPrice = paymentDiscountPrice;
	}
	
	/**
	 * 返回 付款折扣金额
	 * @return
	 */
	public Double getPaymentDiscountPrice() {
		return this.paymentDiscountPrice;
	}
	
	public void setCommerceLevel(String commerceLevel) {
		this.commerceLevel = commerceLevel;
	}
	
	/**
	 * 返回 商业级别
	 * @return
	 */
	public String getCommerceLevel() {
		return this.commerceLevel;
	}
	
	public void setClearForm(String clearForm) {
		this.clearForm = clearForm;
	}
	
	/**
	 * 返回 结算方式
	 * @return
	 */
	public String getClearForm() {
		return this.clearForm;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	/**
	 * 返回 产品名称
	 * @return
	 */
	public String getProductName() {
		return this.productName;
	}
	
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	
	/**
	 * 返回 合同号
	 * @return
	 */
	public String getContractCode() {
		return this.contractCode;
	}
	
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	
	/**
	 * 返回 发票号码
	 * @return
	 */
	public String getInvoiceCode() {
		return this.invoiceCode;
	}
	
	public void setInvoiceMemo(String invoiceMemo) {
		this.invoiceMemo = invoiceMemo;
	}
	
	/**
	 * 返回 发票备注
	 * @return
	 */
	public String getInvoiceMemo() {
		return this.invoiceMemo;
	}
	
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	
	/**
	 * 返回 是否预付款(0:预付款,1赊销)
	 * @return
	 */
	public String getPaymentType() {
		return this.paymentType;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	/**
	 * 返回 回款金额
	 * @return
	 */
	public Double getPrice() {
		return this.price;
	}
	
	public void setRemittanceDate(java.util.Date remittanceDate) {
		this.remittanceDate = remittanceDate;
	}
	
	/**
	 * 返回 回款日期
	 * @return
	 */
	public java.util.Date getRemittanceDate() {
		return this.remittanceDate;
	}
	
	public void setAffirmDate(java.util.Date affirmDate) {
		this.affirmDate = affirmDate;
	}
	
	/**
	 * 返回 调整回款日期
	 * @return
	 */
	public java.util.Date getAffirmDate() {
		return this.affirmDate;
	}
	
	public void setInvoiceDate(java.util.Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	
	/**
	 * 返回 开票日期
	 * @return
	 */
	public java.util.Date getInvoiceDate() {
		return this.invoiceDate;
	}
	
	public void setUpdateInvoiceDate(java.util.Date updateInvoiceDate) {
		this.updateInvoiceDate = updateInvoiceDate;
	}
	
	/**
	 * 返回 调整开票日期
	 * @return
	 */
	public java.util.Date getUpdateInvoiceDate() {
		return this.updateInvoiceDate;
	}
	
	public void setReturnMoneyDay(Integer returnMoneyDay) {
		this.returnMoneyDay = returnMoneyDay;
	}
	
	/**
	 * 返回 回款天数
	 * @return
	 */
	public Integer getReturnMoneyDay() {
		return this.returnMoneyDay;
	}
	
	public void setRbRate(Double rbRate) {
		this.rbRate = rbRate;
	}
	
	/**
	 * 返回 RB率%
	 * @return
	 */
	public Double getRbRate() {
		return this.rbRate;
	}
	
	public void setTotalRbRate(Double totalRbRate) {
		this.totalRbRate = totalRbRate;
	}
	
	/**
	 * 返回 合计RB%
	 * @return
	 */
	public Double getTotalRbRate() {
		return this.totalRbRate;
	}
	
	public void setThisCalculationRb(Double thisCalculationRb) {
		this.thisCalculationRb = thisCalculationRb;
	}
	
	/**
	 * 返回 本次计算RB
	 * @return
	 */
	public Double getThisCalculationRb() {
		return this.thisCalculationRb;
	}
	
	public void setThisShouldSendRb(Double thisShouldSendRb) {
		this.thisShouldSendRb = thisShouldSendRb;
	}
	
	/**
	 * 返回 本次应发RB
	 * @return
	 */
	public Double getThisShouldSendRb() {
		return this.thisShouldSendRb;
	}
	
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	/**
	 * 返回 申请备注
	 * @return
	 */
	public String getMemo() {
		return this.memo;
	}
	
	public void setBlendId(String blendId) {
		this.blendId = blendId;
	}
	
	/**
	 * 返回 勾兑记录表id
	 * @return
	 */
	public String getBlendId() {
		return this.blendId;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("masterId", this.masterId) 
		.append("commerceId", this.commerceId) 
		.append("commerceName", this.commerceName) 
		.append("commerceCode", this.commerceCode) 
		.append("businessDivision", this.businessDivision) 
		.append("businessDivisionId", this.businessDivisionId) 
		.append("province", this.province) 
		.append("provinceId", this.provinceId) 
		.append("paymentDiscountPrice", this.paymentDiscountPrice) 
		.append("commerceLevel", this.commerceLevel) 
		.append("clearForm", this.clearForm) 
		.append("productName", this.productName) 
		.append("contractCode", this.contractCode) 
		.append("invoiceCode", this.invoiceCode) 
		.append("invoiceMemo", this.invoiceMemo) 
		.append("paymentType", this.paymentType) 
		.append("price", this.price) 
		.append("remittanceDate", this.remittanceDate) 
		.append("affirmDate", this.affirmDate) 
		.append("invoiceDate", this.invoiceDate) 
		.append("updateInvoiceDate", this.updateInvoiceDate) 
		.append("returnMoneyDay", this.returnMoneyDay) 
		.append("rbRate", this.rbRate) 
		.append("totalRbRate", this.totalRbRate) 
		.append("thisCalculationRb", this.thisCalculationRb) 
		.append("thisShouldSendRb", this.thisShouldSendRb) 
		.append("memo", this.memo) 
		.append("blendId", this.blendId) 
		.toString();
	}
}