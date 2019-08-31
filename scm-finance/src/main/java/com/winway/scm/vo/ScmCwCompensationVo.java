package com.winway.scm.vo;

import io.swagger.annotations.ApiModelProperty;

public class ScmCwCompensationVo {

	@ApiModelProperty(value="费控主键" ,required = true)
    private String paymentId;
	@ApiModelProperty(value="SCM主键")
    private String payNoticeCode;
	@ApiModelProperty(value="票折来源:固定值5",required = true)
    private String discountSource;
	@ApiModelProperty(value="商业编号",required = true)
    private String commerceCode;
	@ApiModelProperty(value="商业名称")
	private String commerceName;
	@ApiModelProperty(value="终端名称",required = true)
	private String treminal;
	@ApiModelProperty(value="可折金额",required = true)    
    private double mayDeduction;
	@ApiModelProperty(value="是否扣减(0:否,1:是)",required = true)
	private String isDeduction;
	@ApiModelProperty(value="扣减金额(0.00/实际扣减金额)",required = true)
	private double deductionPrice;
	@ApiModelProperty(value="货主ID(无多货主,传递固定值)",required = true)
	private String ownerId;
	@ApiModelProperty(value="商品名称",required = true)
	private String productName;
	@ApiModelProperty(value="序列号")
	private String serialNum;
	@ApiModelProperty(value="票折类型(固定:补差类)",required = true)
	private String discountGenre;
	@ApiModelProperty(value="bu大区",required = true)
	private String bu;
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getPayNoticeCode() {
		return payNoticeCode;
	}
	public void setPayNoticeCode(String payNoticeCode) {
		this.payNoticeCode = payNoticeCode;
	}
	public String getDiscountSource() {
		return discountSource;
	}
	public void setDiscountSource(String discountSource) {
		this.discountSource = discountSource;
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
	public String getTreminal() {
		return treminal;
	}
	public void setTreminal(String treminal) {
		this.treminal = treminal;
	}
	public double getMayDeduction() {
		return mayDeduction;
	}
	public void setMayDeduction(double mayDeduction) {
		this.mayDeduction = mayDeduction;
	}
	public String getIsDeduction() {
		return isDeduction;
	}
	public void setIsDeduction(String isDeduction) {
		this.isDeduction = isDeduction;
	}
	public double getDeductionPrice() {
		return deductionPrice;
	}
	public void setDeductionPrice(double deductionPrice) {
		this.deductionPrice = deductionPrice;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}
	public String getDiscountGenre() {
		return discountGenre;
	}
	public void setDiscountGenre(String discountGenre) {
		this.discountGenre = discountGenre;
	}
	public String getBu() {
		return bu;
	}
	public void setBu(String bu) {
		this.bu = bu;
	}

}
