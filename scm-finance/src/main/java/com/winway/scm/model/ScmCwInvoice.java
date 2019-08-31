package com.winway.scm.model;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;


 /**
 * 商业发票表
 * <pre> 
 * 描述：商业发票表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-23 18:19:25
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmCwInvoice",description = "商业发票表") 
public class ScmCwInvoice extends BaseModel<String>{

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
	
	@ApiModelProperty(value="发票类型(0:原始发票1:剩余发票)")
	protected String invoiceType; 
	
	@ApiModelProperty(value="开票日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date invoiceDate; 
	
	@ApiModelProperty(value="调整后开票日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date updateInvoiceDate; 
	
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
	protected java.util.Date shipmentDate; 
	
	@ApiModelProperty(value="发货单号")
	protected String shipmentCode; 
	
	@ApiModelProperty(value="合同号")
	protected String contractCode; 
	
	@ApiModelProperty(value="回款方式")
	protected String remittanceType; 
	
	@ApiModelProperty(value="备注")
	protected String memo; 
	
	@ApiModelProperty(value="采集人")
	protected String gatherPersion; 
	
	@ApiModelProperty(value="采集时间")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date gathererDate; 
	
	@ApiModelProperty(value="调整人")
	protected String setUpPersion;

	 @ApiModelProperty(value="确认时间")
	 @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	 protected java.util.Date affirmDate;

	 @ApiModelProperty(value="确认人")
	 protected String affirmPerson;



	 @ApiModelProperty(value="调整时间")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date setUpDate; 
	
	@ApiModelProperty(value="货主ID")
	protected String ownerId; 
	@ApiModelProperty(value="是否确认(0:未确认,1:已确认)")
	protected String isAffirm;

	 @ApiModelProperty(value="批号")
	 protected String batchNumber;

	 @ApiModelProperty(value="指令编号")
	 protected String directiveCode;

	 @ApiModelProperty(value="失效日期")
	 @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	 protected Date loseDate;

	 @ApiModelProperty(value="到款日期")
	 @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	 protected String paymentDate;

	 @ApiModelProperty(value="商品名")
	 protected String productName;

	 @ApiModelProperty(value="匹配结果")
	 protected String matchResult;


	 @ApiModelProperty(value="一级商")
	 protected String firstClassCommerce;

	 @ApiModelProperty(value="商业级别")
	 protected String commerceLevel;

	 @ApiModelProperty(value="到款日期")
	 @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	 protected Date remittanceDate;
	 @ApiModelProperty(value="供应商")
	 protected String supplier;
	 @ApiModelProperty(value="系列名")
	 protected String ProductLineName;
	 @ApiModelProperty(value="核销金额")
	 protected String blendprice;


	 public String getFirstClassCommerce() {
		 return firstClassCommerce;
	 }

	 public void setFirstClassCommerce(String firstClassCommerce) {
		 this.firstClassCommerce = firstClassCommerce;
	 }

	 public Date getAffirmDate() {
		 return affirmDate;
	 }

	 public void setAffirmDate(Date affirmDate) {
		 this.affirmDate = affirmDate;
	 }

	 public String getAffirmPerson() {
		 return affirmPerson;
	 }

	 public void setAffirmPerson(String affirmPerson) {
		 this.affirmPerson = affirmPerson;
	 }

	 public String getMatchResult() {
		 return matchResult;
	 }

	 public void setMatchResult(String matchResult) {
		 this.matchResult = matchResult;
	 }

	 public Date getRemittanceDate() {
		 return remittanceDate;
	 }

	 public void setRemittanceDate(Date remittanceDate) {
		 this.remittanceDate = remittanceDate;
	 }

	 public String getSupplier() {
		 return supplier;
	 }

	 public void setSupplier(String supplier) {
		 this.supplier = supplier;
	 }

	 public String getProductLineName() {
		 return ProductLineName;
	 }

	 public void setProductLineName(String productLineName) {
		 ProductLineName = productLineName;
	 }

	 public String getBlendprice() {
		 return blendprice;
	 }

	 public void setBlendprice(String blendprice) {
		 this.blendprice = blendprice;
	 }

	 public String getCommerceLevel() {
		 return commerceLevel;
	 }

	 public void setCommerceLevel(String commerceLevel) {
		 this.commerceLevel = commerceLevel;
	 }

	 public String getPaymentDate() {
		 return paymentDate;
	 }

	 public void setPaymentDate(String paymentDate) {
		 this.paymentDate = paymentDate;
	 }

	 public String getBatchNumber() {
		 return batchNumber;
	 }

	 public void setBatchNumber(String batchNumber) {
		 this.batchNumber = batchNumber;
	 }

	 public String getDirectiveCode() {
		 return directiveCode;
	 }

	 public void setDirectiveCode(String directiveCode) {
		 this.directiveCode = directiveCode;
	 }

	 public Date getLoseDate() {
		 return loseDate;
	 }

	 public void setLoseDate(Date loseDate) {
		 this.loseDate = loseDate;
	 }

	 public void setGetMoth(String getMoth) {
		 this.getMoth = getMoth;
	 }

	 //回款月份
	protected String getMoth;
	
	@ApiModelProperty(value="剩余金额")
	protected Double balancePrice;

	 public String getProductName() {
		 return productName;
	 }

	 public void setProductName(String productName) {
		 this.productName = productName;
	 }

	 public Double getBalancePrice() {
		return balancePrice;
	}

	public void setBalancePrice(Double balancePrice) {
		this.balancePrice = balancePrice;
	}

	public String getGetMoth() {
		//this.getMoth=date(this.getRemittanceDate());
		return this.getMoth;
	}

	public String getIsAffirm() {
		return isAffirm;
	}

	public void setIsAffirm(String isAffirm) {
		this.isAffirm = isAffirm;
	}

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
	
	public void setVerifyType(String verifyType) {
		this.verifyType = verifyType;
	}
	
	/**
	 * 返回 核销状态(0:未核销,1 核销中 2:已核销)
	 * @return
	 */
	public String getVerifyType() {
		return this.verifyType;
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
	
	public void setCommerceCode(String commerceCode) {
		this.commerceCode = commerceCode;
	}
	
	/**
	 * 返回 商品编号
	 * @return
	 */
	public String getCommerceCode() {
		return this.commerceCode;
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
	
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	
	/**
	 * 返回 省区id
	 * @return
	 */
	public String getProvinceId() {
		return this.provinceId;
	}
	
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	
	/**
	 * 返回 发票类型(0:原始发票)
	 * @return
	 */
	public String getInvoiceType() {
		return this.invoiceType;
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
	 * 返回 调整后开票日期
	 * @return
	 */
	public java.util.Date getUpdateInvoiceDate() {
		return this.updateInvoiceDate;
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
	
	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}
	
	/**
	 * 返回 产品通用名称
	 * @return
	 */
	public String getCommonName() {
		return this.commonName;
	}
	
	public void setProductState(String productState) {
		this.productState = productState;
	}
	
	/**
	 * 返回 规格
	 * @return
	 */
	public String getProductState() {
		return this.productState;
	}
	
	public void setProductSum(Integer productSum) {
		this.productSum = productSum;
	}
	
	/**
	 * 返回 数量
	 * @return
	 */
	public Integer getProductSum() {
		return this.productSum;
	}
	
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	/**
	 * 返回 含税单价
	 * @return
	 */
	public Double getUnitPrice() {
		return this.unitPrice;
	}
	
	public void setPriceTaxSum(Double priceTaxSum) {
		this.priceTaxSum = priceTaxSum;
	}
	
	/**
	 * 返回 价税合计
	 * @return
	 */
	public Double getPriceTaxSum() {
		return this.priceTaxSum;
	}
	
	public void setShipmentDate(java.util.Date shipmentDate) {
		this.shipmentDate = shipmentDate;
	}
	
	/**
	 * 返回 发货日期
	 * @return
	 */
	public java.util.Date getShipmentDate() {
		return this.shipmentDate;
	}
	
	public void setShipmentCode(String shipmentCode) {
		this.shipmentCode = shipmentCode;
	}
	
	/**
	 * 返回 发货单号
	 * @return
	 */
	public String getShipmentCode() {
		return this.shipmentCode;
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
	
	public void setRemittanceType(String remittanceType) {
		this.remittanceType = remittanceType;
	}
	
	/**
	 * 返回 汇款方式
	 * @return
	 */
	public String getRemittanceType() {
		return this.remittanceType;
	}
	
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	/**
	 * 返回 备注
	 * @return
	 */
	public String getMemo() {
		return this.memo;
	}
	
	public void setGatherPersion(String gatherPersion) {
		this.gatherPersion = gatherPersion;
	}
	
	/**
	 * 返回 采集人
	 * @return
	 */
	public String getGatherPersion() {
		return this.gatherPersion;
	}
	
	public void setGathererDate(java.util.Date gathererDate) {
		this.gathererDate = gathererDate;
	}
	
	/**
	 * 返回 采集时间
	 * @return
	 */
	public java.util.Date getGathererDate() {
		return this.gathererDate;
	}
	
	public void setSetUpPersion(String setUpPersion) {
		this.setUpPersion = setUpPersion;
	}
	
	/**
	 * 返回 调整人
	 * @return
	 */
	public String getSetUpPersion() {
		return this.setUpPersion;
	}
	
	public void setSetUpDate(java.util.Date setUpDate) {
		this.setUpDate = setUpDate;
	}
	
	/**
	 * 返回 调整时间
	 * @return
	 */
	public java.util.Date getSetUpDate() {
		return this.setUpDate;
	}
	
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	
	/**
	 * 返回 货主ID
	 * @return
	 */
	public String getOwnerId() {
		return this.ownerId;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("verifyType", this.verifyType) 
		.append("commerceId", this.commerceId) 
		.append("commerceCode", this.commerceCode) 
		.append("commerceName", this.commerceName) 
		.append("businessDivision", this.businessDivision) 
		.append("province", this.province) 
		.append("businessDivisionId", this.businessDivisionId) 
		.append("provinceId", this.provinceId) 
		.append("invoiceType", this.invoiceType) 
		.append("invoiceDate", this.invoiceDate) 
		.append("updateInvoiceDate", this.updateInvoiceDate) 
		.append("invoiceCode", this.invoiceCode) 
		.append("commonName", this.commonName) 
		.append("productState", this.productState) 
		.append("productSum", this.productSum) 
		.append("unitPrice", this.unitPrice) 
		.append("priceTaxSum", this.priceTaxSum) 
		.append("shipmentDate", this.shipmentDate) 
		.append("shipmentCode", this.shipmentCode) 
		.append("contractCode", this.contractCode) 
		.append("remittanceType", this.remittanceType) 
		.append("memo", this.memo) 
		.append("gatherPersion", this.gatherPersion) 
		.append("gathererDate", this.gathererDate) 
		.append("setUpPersion", this.setUpPersion) 
		.append("setUpDate", this.setUpDate) 
		.append("ownerId", this.ownerId) 
		.toString();
	}
	
	//根据时间计算出年月份
	public String date(Date d){
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		String y=year+"年"+(month+1)+"月";
		return y;
	}
}