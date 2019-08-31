package com.winway.scm.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;

/**
 * 大合同
 * 
 * <pre>
 *  
 * 描述：大合同 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:44
 * 版权：美达开发小组
 * </pre>
 */
@ApiModel(value = "ScmXsBigContract", description = "大合同")
public class ScmXsBigContract extends BaseModel<String> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	protected String id;

	@ApiModelProperty(value = "供货商")
	protected String ownerName;

	@ApiModelProperty(value = "经销商")
	protected String dealer;

	@ApiModelProperty(value = "经销商首营ID")
	protected String commerceFirstId;

	@ApiModelProperty(value = "合同编号")
	protected String contractCode;

	@ApiModelProperty(value = "收货人")
	protected String consignee;

	@ApiModelProperty(value = "联系电话")
	protected String phone;

	@ApiModelProperty(value = "购销方式(0预付款,1赊销)")
	protected String marketingWay;

	@ApiModelProperty(value = "到货地址")
	protected String shipAddress;

	@ApiModelProperty(value = "付款方式")
	protected String payType;

	@ApiModelProperty(value = "签订地址")
	protected String signTheAddress;

	@ApiModelProperty(value = "特求意见")
	protected String specialOpinion;

	@ApiModelProperty(value = "乙方单位")
	protected String YUnit;

	@ApiModelProperty(value = "乙方地址")
	protected String YAddress;

	@ApiModelProperty(value = "乙方开户行")
	protected String YOpenBank;

	@ApiModelProperty(value = "乙方税号")
	protected String YDutyCode;

	@ApiModelProperty(value = "乙方账号")
	protected String YBankCode;

	@ApiModelProperty(value = "乙方电话")
	protected String YPhone;

	@ApiModelProperty(value = "乙方邮编")
	protected String YPostcode;

	@ApiModelProperty(value = "甲方单位")
	protected String JUnit;

	@ApiModelProperty(value = "甲方地址")
	protected String JAddress;

	@ApiModelProperty(value = "甲方开户行")
	protected String JOpenBank;

	@ApiModelProperty(value = "甲方账户")
	protected String JBankCode;

	@ApiModelProperty(value = "甲方税号")
	protected String JDutyCode;

	@ApiModelProperty(value = "甲方电话")
	protected String JPhone;

	@ApiModelProperty(value = "甲方邮编")
	protected String JPostcode;

	@ApiModelProperty(value = "货主ID")
	protected String ownerId;

	@ApiModelProperty(value = "协议ID")
	protected String dealerClauseId;

	@ApiModelProperty(value = "减免合同次数")
	protected Integer reduceContractTime;

	@ApiModelProperty(value = "审批ID")
	protected String approvalId;

	@ApiModelProperty(value = "审批状态")
	protected String approvalState;

	@ApiModelProperty(value = "协议汇总ID")
	protected String agreementSummaryId;

	@ApiModelProperty(value = "药品类型")
	protected String medicineType;

	@ApiModelProperty(value = "总价")
	protected String totalPrice;

	@ApiModelProperty(value = "资信扣减价")
	protected String creditPrice;

	@ApiModelProperty(value = "是否失效")
	protected String invalid;

	public String getInvalid() {
		return invalid;
	}

	public void setInvalid(String invalid) {
		this.invalid = invalid;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getCreditPrice() {
		return creditPrice;
	}

	public void setCreditPrice(String creditPrice) {
		this.creditPrice = creditPrice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@ApiModelProperty(value = "签订时间")
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
	protected Date dateOfSigning;
	@ApiModelProperty(value = "预计发货时间")
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
	protected Date sendProductDate;
	@ApiModelProperty(value = "录入时间")
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
	protected Date entryDate;
	@ApiModelProperty(value = "确认时间")
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
	protected Date confirmDate;
	@ApiModelProperty(value = "录入人")
	protected String entryPeople;

	@ApiModelProperty(value = "v_经销商协议主表Id")
	protected String AgreementSummaryId;


	public Date getSendProductDate() {
		return sendProductDate;
	}

	public void setSendProductDate(Date sendProductDate) {
		this.sendProductDate = sendProductDate;
	}

	public String getAgreementSummaryId() {
		return AgreementSummaryId;
	}

	public void setAgreementSummaryId(String agreementSummaryId) {
		AgreementSummaryId = agreementSummaryId;
	}

	public String getMedicineType() {
		return medicineType;
	}

	public void setMedicineType(String medicineType) {
		this.medicineType = medicineType;
	}

	/**
	 * 大合同商品表列表
	 */
	protected List<ScmXsBigContractProduct> scmXsBigContractProductList = new ArrayList<ScmXsBigContractProduct>();

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 返回 id
	 * 
	 * @return
	 */
	public String getId() {
		return this.id;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	/**
	 * 返回 供货商
	 * 
	 * @return
	 */
	public String getOwnerName() {
		return this.ownerName;
	}

	public void setDealer(String dealer) {
		this.dealer = dealer;
	}

	/**
	 * 返回 经销商
	 * 
	 * @return
	 */
	public String getDealer() {
		return this.dealer;
	}

	public void setCommerceFirstId(String commerceFirstId) {
		this.commerceFirstId = commerceFirstId;
	}

	/**
	 * 返回 经销商首营ID
	 * 
	 * @return
	 */
	public String getCommerceFirstId() {
		return this.commerceFirstId;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	/**
	 * 返回 合同编号
	 * 
	 * @return
	 */
	public String getContractCode() {
		return this.contractCode;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	/**
	 * 返回 收货人
	 * 
	 * @return
	 */
	public String getConsignee() {
		return this.consignee;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 返回 联系电话
	 * 
	 * @return
	 */
	public String getPhone() {
		return this.phone;
	}

	public void setMarketingWay(String marketingWay) {
		this.marketingWay = marketingWay;
	}

	/**
	 * 返回 购销方式(0预付款,1赊销)
	 * 
	 * @return
	 */
	public String getMarketingWay() {
		return this.marketingWay;
	}

	public void setShipAddress(String shipAddress) {
		this.shipAddress = shipAddress;
	}

	/**
	 * 返回 到货地址
	 * 
	 * @return
	 */
	public String getShipAddress() {
		return this.shipAddress;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	/**
	 * 返回 付款方式
	 * 
	 * @return
	 */
	public String getPayType() {
		return this.payType;
	}

	public void setSignTheAddress(String signTheAddress) {
		this.signTheAddress = signTheAddress;
	}

	/**
	 * 返回 签订地址
	 * 
	 * @return
	 */
	public String getSignTheAddress() {
		return this.signTheAddress;
	}

	public void setSpecialOpinion(String specialOpinion) {
		this.specialOpinion = specialOpinion;
	}

	/**
	 * 返回 特求意见
	 * 
	 * @return
	 */
	public String getSpecialOpinion() {
		return this.specialOpinion;
	}

	public void setYUnit(String YUnit) {
		this.YUnit = YUnit;
	}

	/**
	 * 返回 乙方单位
	 * 
	 * @return
	 */
	public String getYUnit() {
		return this.YUnit;
	}

	public void setYAddress(String YAddress) {
		this.YAddress = YAddress;
	}

	/**
	 * 返回 乙方地址
	 * 
	 * @return
	 */
	public String getYAddress() {
		return this.YAddress;
	}

	public void setYOpenBank(String YOpenBank) {
		this.YOpenBank = YOpenBank;
	}

	/**
	 * 返回 乙方开户行
	 * 
	 * @return
	 */
	public String getYOpenBank() {
		return this.YOpenBank;
	}

	public void setYDutyCode(String YDutyCode) {
		this.YDutyCode = YDutyCode;
	}

	/**
	 * 返回 乙方税号
	 * 
	 * @return
	 */
	public String getYDutyCode() {
		return this.YDutyCode;
	}

	public void setYBankCode(String YBankCode) {
		this.YBankCode = YBankCode;
	}

	/**
	 * 返回 乙方账号
	 * 
	 * @return
	 */
	public String getYBankCode() {
		return this.YBankCode;
	}

	public void setYPhone(String YPhone) {
		this.YPhone = YPhone;
	}

	/**
	 * 返回 乙方电话
	 * 
	 * @return
	 */
	public String getYPhone() {
		return this.YPhone;
	}

	public void setYPostcode(String YPostcode) {
		this.YPostcode = YPostcode;
	}

	/**
	 * 返回 乙方邮编
	 * 
	 * @return
	 */
	public String getYPostcode() {
		return this.YPostcode;
	}

	public void setJUnit(String JUnit) {
		this.JUnit = JUnit;
	}

	/**
	 * 返回 甲方单位
	 * 
	 * @return
	 */
	public String getJUnit() {
		return this.JUnit;
	}

	public void setJAddress(String JAddress) {
		this.JAddress = JAddress;
	}

	/**
	 * 返回 甲方地址
	 * 
	 * @return
	 */
	public String getJAddress() {
		return this.JAddress;
	}

	public void setJOpenBank(String JOpenBank) {
		this.JOpenBank = JOpenBank;
	}

	/**
	 * 返回 甲方开户行
	 * 
	 * @return
	 */
	public String getJOpenBank() {
		return this.JOpenBank;
	}

	public void setJBankCode(String JBankCode) {
		this.JBankCode = JBankCode;
	}

	/**
	 * 返回 甲方账户
	 * 
	 * @return
	 */
	public String getJBankCode() {
		return this.JBankCode;
	}

	public void setJDutyCode(String JDutyCode) {
		this.JDutyCode = JDutyCode;
	}

	/**
	 * 返回 甲方税号
	 * 
	 * @return
	 */
	public String getJDutyCode() {
		return this.JDutyCode;
	}

	public void setJPhone(String JPhone) {
		this.JPhone = JPhone;
	}

	/**
	 * 返回 甲方电话
	 * 
	 * @return
	 */
	public String getJPhone() {
		return this.JPhone;
	}

	public void setJPostcode(String JPostcode) {
		this.JPostcode = JPostcode;
	}

	/**
	 * 返回 甲方邮编
	 * 
	 * @return
	 */
	public String getJPostcode() {
		return this.JPostcode;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * 返回 货主ID
	 * 
	 * @return
	 */
	public String getOwnerId() {
		return this.ownerId;
	}

	public String getDealerClauseId() {
		return dealerClauseId;
	}

	public void setDealerClauseId(String dealerClauseId) {
		this.dealerClauseId = dealerClauseId;
	}

	public void setReduceContractTime(Integer reduceContractTime) {
		this.reduceContractTime = reduceContractTime;
	}

	/**
	 * 返回 减免合同次数
	 * 
	 * @return
	 */
	public Integer getReduceContractTime() {
		return this.reduceContractTime;
	}

	public void setApprovalId(String approvalId) {
		this.approvalId = approvalId;
	}

	/**
	 * 返回 审批ID
	 * 
	 * @return
	 */
	public String getApprovalId() {
		return this.approvalId;
	}

	public void setApprovalState(String approvalState) {
		this.approvalState = approvalState;
	}

	/**
	 * 返回 审批状态
	 * 
	 * @return
	 */
	public String getApprovalState() {
		return this.approvalState;
	}

	public void setScmXsBigContractProductList(List<ScmXsBigContractProduct> scmXsBigContractProductList) {
		this.scmXsBigContractProductList = scmXsBigContractProductList;
	}

	/**
	 * 返回 大合同商品表列表
	 * 
	 * @return
	 */
	public List<ScmXsBigContractProduct> getScmXsBigContractProductList() {
		return this.scmXsBigContractProductList;
	}

	/**
	 * 返回 签订时间
	 * 
	 * @return
	 */
	public Date getDateOfSigning() {
		return dateOfSigning;
	}

	public void setDateOfSigning(Date dateOfSigning) {
		this.dateOfSigning = dateOfSigning;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public Date getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}

	public String getEntryPeople() {
		return entryPeople;
	}

	public void setEntryPeople(String entryPeople) {
		this.entryPeople = entryPeople;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).append("ownerName", this.ownerName)
				.append("dealer", this.dealer).append("commerceFirstId", this.commerceFirstId)
				.append("contractCode", this.contractCode).append("consignee", this.consignee)
				.append("phone", this.phone).append("marketingWay", this.marketingWay)
				.append("shipAddress", this.shipAddress).append("payType", this.payType)
				.append("signTheAddress", this.signTheAddress).append("specialOpinion", this.specialOpinion)
				.append("YUnit", this.YUnit).append("YAddress", this.YAddress).append("YOpenBank", this.YOpenBank)
				.append("YDutyCode", this.YDutyCode).append("YBankCode", this.YBankCode).append("YPhone", this.YPhone)
				.append("YPostcode", this.YPostcode).append("JUnit", this.JUnit).append("JAddress", this.JAddress)
				.append("JOpenBank", this.JOpenBank).append("JBankCode", this.JBankCode)
				.append("JDutyCode", this.JDutyCode).append("JPhone", this.JPhone).append("JPostcode", this.JPostcode)
				.append("ownerId", this.ownerId).append("DealerClauseId", this.dealerClauseId)
				.append("reduceContractTime", this.reduceContractTime).append("approvalId", this.approvalId)
				.append("approvalState", this.approvalState).append("dateOfSigning", this.dateOfSigning).toString();
	}

}