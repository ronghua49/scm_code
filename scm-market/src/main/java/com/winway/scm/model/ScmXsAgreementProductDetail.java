package com.winway.scm.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 协议品规明细
 * 
 * <pre>
 *  
 * 描述：协议品规明细 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:43
 * 版权：美达开发小组
 * </pre>
 */
@ApiModel(value = "ScmXsAgreementProductDetail", description = "协议品规明细")
public class ScmXsAgreementProductDetail extends BaseModel<String> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	protected String id;

	@ApiModelProperty(value = "协议汇总ID")
	protected String agreementSummaryId;

	@ApiModelProperty(value = "商品品规")
	protected String commerceGauge;

	@ApiModelProperty(value = "是否签约")
	protected String isSign;

	@ApiModelProperty(value = "生效日期")
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	protected java.util.Date effectiveDate;

	@ApiModelProperty(value = "品规类型(0:非OTC,,1甲类,2乙类)")
	protected String gaugeType;

	@ApiModelProperty(value = "是否中标")
	protected String isBidding;

	@ApiModelProperty(value = "是否实施")
	protected String isImplement;

	@ApiModelProperty(value = "实际中标价")
	protected double biddingPrice;

	@ApiModelProperty(value = "商业供货单价")
	protected double supplyPrice;

	@ApiModelProperty(value = "调拨毛利")
	protected double allotGross;

	@ApiModelProperty(value = "承兑毛利")
	protected double acceptGross;

	@ApiModelProperty(value = "纯销毛利")
	protected double purePinGross;

	@ApiModelProperty(value = "电汇调拨供货价")
	protected double wireTransferPrice;

	@ApiModelProperty(value = "承兑调拨供货价")
	protected double acceptPrice;

	@ApiModelProperty(value = "纯销供货价")
	protected double purePinPrice;

	@ApiModelProperty(value = "备注")
	protected String memo;

	@ApiModelProperty(value = "药品编码")
	protected String commerceCode;

	@ApiModelProperty(value = "发货额")
	protected double delivery;

	@ApiModelProperty(value = "商品ID")
	protected String productId;
	@ApiModelProperty(value = "商品首营ID")
	protected String productFirstId;
	@ApiModelProperty(value = "上次是否签约")
	protected String lastIsSign;
	@ApiModelProperty(value = "品规类型")
	protected String OTC;
	@ApiModelProperty(value = "是否SMS")
	protected String isSMS;
	@ApiModelProperty(value = "上次商业供货单价")
	protected double lastSupplyPrice;
	@ApiModelProperty(value = "上次调拨毛利")
	protected double lastAllotGross;
	@ApiModelProperty(value = "上次承兑毛利")
	protected double lastAcceptGross;
	@ApiModelProperty(value = "上次纯销毛利")
	protected double lastPurePinGross;

	@ApiModelProperty(value = "是否SMS")
	protected String commonName;
	@ApiModelProperty(value = "是否SMS")
	protected String productName;
	@ApiModelProperty(value = "是否SMS")
	protected String unit;
	@ApiModelProperty(value = "是否SMS")
	protected String AgentType;
	@ApiModelProperty(value = "是否SMS")
	protected String manufacturer;
	@ApiModelProperty(value = "是否SMS")
	protected String approvalCode;
	@ApiModelProperty(value = "是否SMS")
	protected String medicineClassify;
	@ApiModelProperty(value = "是否SMS")
	protected String businessScope;
	
	@ApiModelProperty(value = "中标价格全部信息")
	protected List<ScmTenderPrice> scmTenderPrices = new ArrayList<ScmTenderPrice>();

	public List<ScmTenderPrice> getScmTenderPrices() {
		return scmTenderPrices;
	}

	public void setScmTenderPrices(List<ScmTenderPrice> scmTenderPrices) {
		this.scmTenderPrices = scmTenderPrices;
	}

	public String getCommonName() {
		return commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
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

	public String getIsSMS() {
		return isSMS;
	}

	public void setIsSMS(String isSMS) {
		this.isSMS = isSMS;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductFirstId() {
		return productFirstId;
	}

	public void setProductFirstId(String productFirstId) {
		this.productFirstId = productFirstId;
	}

	public String getLastIsSign() {
		return lastIsSign;
	}

	public void setLastIsSign(String lastIsSign) {
		this.lastIsSign = lastIsSign;
	}

	public String getOTC() {
		return OTC;
	}

	public void setOTC(String oTC) {
		OTC = oTC;
	}

	public double getLastSupplyPrice() {
		return lastSupplyPrice;
	}

	public void setLastSupplyPrice(double lastSupplyPrice) {
		this.lastSupplyPrice = lastSupplyPrice;
	}

	public double getLastAllotGross() {
		return lastAllotGross;
	}

	public void setLastAllotGross(double lastAllotGross) {
		this.lastAllotGross = lastAllotGross;
	}

	public double getLastAcceptGross() {
		return lastAcceptGross;
	}

	public void setLastAcceptGross(double lastAcceptGross) {
		this.lastAcceptGross = lastAcceptGross;
	}

	public double getLastPurePinGross() {
		return lastPurePinGross;
	}

	public void setLastPurePinGross(double lastPurePinGross) {
		this.lastPurePinGross = lastPurePinGross;
	}

	public double getDelivery() {
		return delivery;
	}

	public void setDelivery(double delivery) {
		this.delivery = delivery;
	}

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

	public String getAgreementSummaryId() {
		return agreementSummaryId;
	}

	public void setAgreementSummaryId(String agreementSummaryId) {
		this.agreementSummaryId = agreementSummaryId;
	}

	public void setCommerceGauge(String commerceGauge) {
		this.commerceGauge = commerceGauge;
	}

	/**
	 * 返回 商品品规
	 * 
	 * @return
	 */
	public String getCommerceGauge() {
		return this.commerceGauge;
	}

	public void setIsSign(String isSign) {
		this.isSign = isSign;
	}

	/**
	 * 返回 是否签约
	 * 
	 * @return
	 */
	public String getIsSign() {
		return this.isSign;
	}

	public void setEffectiveDate(java.util.Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 * 返回 生效日期
	 * 
	 * @return
	 */
	public java.util.Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setGaugeType(String gaugeType) {
		this.gaugeType = gaugeType;
	}

	/**
	 * 返回 品规类型(0:非OTC,,1甲类,2乙类)
	 * 
	 * @return
	 */
	public String getGaugeType() {
		return this.gaugeType;
	}

	public void setIsBidding(String isBidding) {
		this.isBidding = isBidding;
	}

	/**
	 * 返回 是否中标
	 * 
	 * @return
	 */
	public String getIsBidding() {
		return this.isBidding;
	}

	public void setIsImplement(String isImplement) {
		this.isImplement = isImplement;
	}

	/**
	 * 返回 是否实施
	 * 
	 * @return
	 */
	public String getIsImplement() {
		return this.isImplement;
	}

	public void setBiddingPrice(double biddingPrice) {
		this.biddingPrice = biddingPrice;
	}

	/**
	 * 返回 实际中标价
	 * 
	 * @return
	 */
	public double getBiddingPrice() {
		return this.biddingPrice;
	}

	public void setSupplyPrice(double supplyPrice) {
		this.supplyPrice = supplyPrice;
	}

	/**
	 * 返回 商业供货单价
	 * 
	 * @return
	 */
	public double getSupplyPrice() {
		return this.supplyPrice;
	}

	public void setAllotGross(double allotGross) {
		this.allotGross = allotGross;
	}

	/**
	 * 返回 调拨毛利
	 * 
	 * @return
	 */
	public double getAllotGross() {
		return this.allotGross;
	}

	public void setAcceptGross(double acceptGross) {
		this.acceptGross = acceptGross;
	}

	/**
	 * 返回 承兑毛利
	 * 
	 * @return
	 */
	public double getAcceptGross() {
		return this.acceptGross;
	}

	public void setPurePinGross(double purePinGross) {
		this.purePinGross = purePinGross;
	}

	/**
	 * 返回 纯销毛利
	 * 
	 * @return
	 */
	public double getPurePinGross() {
		return this.purePinGross;
	}

	public void setWireTransferPrice(double wireTransferPrice) {
		this.wireTransferPrice = wireTransferPrice;
	}

	/**
	 * 返回 电汇调拨供货价
	 * 
	 * @return
	 */
	public double getWireTransferPrice() {
		return this.wireTransferPrice;
	}

	public void setAcceptPrice(double acceptPrice) {
		this.acceptPrice = acceptPrice;
	}

	/**
	 * 返回 承兑调拨供货价
	 * 
	 * @return
	 */
	public double getAcceptPrice() {
		return this.acceptPrice;
	}

	public void setPurePinPrice(double purePinPrice) {
		this.purePinPrice = purePinPrice;
	}

	/**
	 * 返回 纯销供货价
	 * 
	 * @return
	 */
	public double getPurePinPrice() {
		return this.purePinPrice;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * 返回 备注
	 * 
	 * @return
	 */
	public String getMemo() {
		return this.memo;
	}

	public void setCommerceCode(String commerceCode) {
		this.commerceCode = commerceCode;
	}

	/**
	 * 返回 药品编码
	 * 
	 * @return
	 */
	public String getCommerceCode() {
		return this.commerceCode;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).append("agreementSummaryId", this.agreementSummaryId)
				.append("commerceGauge", this.commerceGauge).append("isSign", this.isSign)
				.append("effectiveDate", this.effectiveDate).append("gaugeType", this.gaugeType)
				.append("isBidding", this.isBidding).append("isImplement", this.isImplement)
				.append("biddingPrice", this.biddingPrice).append("supplyPrice", this.supplyPrice)
				.append("allotGross", this.allotGross).append("acceptGross", this.acceptGross)
				.append("purePinGross", this.purePinGross).append("wireTransferPrice", this.wireTransferPrice)
				.append("acceptPrice", this.acceptPrice).append("purePinPrice", this.purePinPrice)
				.append("memo", this.memo).append("commerceCode", this.commerceCode).toString();
	}

}