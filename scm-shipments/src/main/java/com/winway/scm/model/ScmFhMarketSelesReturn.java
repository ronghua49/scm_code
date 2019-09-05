package com.winway.scm.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;

import java.util.Date;

/**
 * 销售退货表
 * 
 * <pre>
 *  
 * 描述：销售退货表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-13 15:47:33
 * 版权：美达开发小组
 * </pre>
 */
@ApiModel(value = "ScmFhMarketSelesReturn", description = "销售退货表")
public class ScmFhMarketSelesReturn extends BaseModel<String> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	protected String id;

	@ApiModelProperty(value = "主表ID")
	protected String masterId;

	@ApiModelProperty(value = "批号", required = true)
	protected String batchNumber;

	@ApiModelProperty(value = "药品名称", required = true)
	protected String productName;

	@ApiModelProperty(value = "药品编号", required = true)
	protected String productCode;

	@ApiModelProperty(value = "药品类型", required = true)
	protected String medicineType;

	@ApiModelProperty(value = "件装量", required = true)
	protected Integer aChange;

	@ApiModelProperty(value = "效期", required = true)
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
	protected Date expiryDate;

	@ApiModelProperty(value = "退货数量", required = true)
	protected Integer productSum;

	@ApiModelProperty(value = "单价", required = true)
	protected Double price;

	@ApiModelProperty(value = "退货金额", required = true)
	protected Double priceSum;

	@ApiModelProperty(value = "实际发货日期", required = true)
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	protected java.util.Date actualDeliveryDate;

	@ApiModelProperty(value = "退货原因", required = true)
	protected String returnMessage;

	@ApiModelProperty(value = "备注")
	protected String memo;

	@ApiModelProperty(value = "责任认定")
	protected String dutyAffirm;

	@ApiModelProperty(value = "实物处理方案")
	protected String entityDisposeMethod;

	@ApiModelProperty(value = "财务处理方案")
	protected String financeDisposeMethod;

	@ApiModelProperty(value = "责任办事处")
	protected String dutyOffice;

	@ApiModelProperty(value = "责任省区")
	protected String dutyPrivate;

	@ApiModelProperty(value = "发货表ID")
	protected String shipmentsId;

	@ApiModelProperty(value = "实际量")
	protected Integer deliveringAmount;

	@ApiModelProperty(value = "已退数量")
	protected Integer returnSum;

	@ApiModelProperty(value = "单位")
	protected String unit;

	@ApiModelProperty(value = "剂型")
	protected String AgentType;
	@ApiModelProperty(value = "规格")
	protected String productState;
	@ApiModelProperty(value = "生产厂家")
	protected String manufacturer;
	@ApiModelProperty(value = "批准文号")
	protected String approvalCode;
	@ApiModelProperty(value = "药品分类")
	protected String medicineClassify;
	@ApiModelProperty(value = "经营范围")
	protected String businessScope;
	@ApiModelProperty(value = "通用名")
	protected String commonName;

	@ApiModelProperty(value = "商品字码")
	protected String code;

	@ApiModelProperty(value = "距今效期月")
	protected String monthLeft;

	@ApiModelProperty(value = "物料编号")
	protected String sapCode;

	public String getSapCode() {
		return sapCode;
	}

	public void setSapCode(String sapCode) {
		this.sapCode = sapCode;
	}

	public String getMonthLeft() {
		return monthLeft;
	}

	public void setMonthLeft(String monthLeft) {
		this.monthLeft = monthLeft;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCommonName() {
		return commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
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

	public String getProductState() {
		return productState;
	}

	public void setProductState(String productState) {
		this.productState = productState;
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

	public Integer getDeliveringAmount() {
		return deliveringAmount;
	}

	public void setDeliveringAmount(Integer deliveringAmount) {
		this.deliveringAmount = deliveringAmount;
	}

	public Integer getReturnSum() {
		return returnSum;
	}

	public void setReturnSum(Integer returnSum) {
		this.returnSum = returnSum;
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

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	/**
	 * 返回 主表ID
	 * 
	 * @return
	 */
	public String getMasterId() {
		return this.masterId;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	/**
	 * 返回 批号
	 * 
	 * @return
	 */
	public String getBatchNumber() {
		return this.batchNumber;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * 返回 药品名称
	 * 
	 * @return
	 */
	public String getProductName() {
		return this.productName;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	/**
	 * 返回 药品编号
	 * 
	 * @return
	 */
	public String getProductCode() {
		return this.productCode;
	}

	public void setMedicineType(String medicineType) {
		this.medicineType = medicineType;
	}

	/**
	 * 返回 药品类型
	 * 
	 * @return
	 */
	public String getMedicineType() {
		return this.medicineType;
	}

	public void setAChange(Integer aChange) {
		this.aChange = aChange;
	}

	/**
	 * 返回 件装量
	 * 
	 * @return
	 */
	public Integer getAChange() {
		return this.aChange;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * 返回 效期
	 * 
	 * @return
	 */
	public Date getExpiryDate() {
		return this.expiryDate;
	}

	public void setProductSum(Integer productSum) {
		this.productSum = productSum;
	}

	/**
	 * 返回 退货数量
	 * 
	 * @return
	 */
	public Integer getProductSum() {
		return this.productSum;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * 返回 单价
	 * 
	 * @return
	 */
	public Double getPrice() {
		return this.price;
	}

	public void setPriceSum(Double priceSum) {
		this.priceSum = priceSum;
	}

	/**
	 * 返回 退货金额
	 * 
	 * @return
	 */
	public Double getPriceSum() {
		return this.priceSum;
	}

	public void setActualDeliveryDate(java.util.Date actualDeliveryDate) {
		this.actualDeliveryDate = actualDeliveryDate;
	}

	/**
	 * 返回 实际发货日期
	 * 
	 * @return
	 */
	public java.util.Date getActualDeliveryDate() {
		return this.actualDeliveryDate;
	}

	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}

	/**
	 * 返回 退货原因
	 * 
	 * @return
	 */
	public String getReturnMessage() {
		return this.returnMessage;
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

	public void setDutyAffirm(String dutyAffirm) {
		this.dutyAffirm = dutyAffirm;
	}

	/**
	 * 返回 责任认定
	 * 
	 * @return
	 */
	public String getDutyAffirm() {
		return this.dutyAffirm;
	}

	public void setEntityDisposeMethod(String entityDisposeMethod) {
		this.entityDisposeMethod = entityDisposeMethod;
	}

	/**
	 * 返回 实物处理方案
	 * 
	 * @return
	 */
	public String getEntityDisposeMethod() {
		return this.entityDisposeMethod;
	}

	public void setFinanceDisposeMethod(String financeDisposeMethod) {
		this.financeDisposeMethod = financeDisposeMethod;
	}

	/**
	 * 返回 财务处理方案
	 * 
	 * @return
	 */
	public String getFinanceDisposeMethod() {
		return this.financeDisposeMethod;
	}

	public void setDutyOffice(String dutyOffice) {
		this.dutyOffice = dutyOffice;
	}

	/**
	 * 返回 责任办事处
	 * 
	 * @return
	 */
	public String getDutyOffice() {
		return this.dutyOffice;
	}

	public void setDutyPrivate(String dutyPrivate) {
		this.dutyPrivate = dutyPrivate;
	}

	/**
	 * 返回 责任省区
	 * 
	 * @return
	 */
	public String getDutyPrivate() {
		return this.dutyPrivate;
	}

	public void setShipmentsId(String shipmentsId) {
		this.shipmentsId = shipmentsId;
	}

	/**
	 * 返回 发货表ID
	 * 
	 * @return
	 */
	public String getShipmentsId() {
		return this.shipmentsId;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).append("masterId", this.masterId)
				.append("batchNumber", this.batchNumber).append("productName", this.productName)
				.append("productCode", this.productCode).append("medicineType", this.medicineType)
				.append("aChange", this.aChange).append("expiryDate", this.expiryDate)
				.append("productSum", this.productSum).append("price", this.price).append("priceSum", this.priceSum)
				.append("actualDeliveryDate", this.actualDeliveryDate).append("returnMessage", this.returnMessage)
				.append("memo", this.memo).append("dutyAffirm", this.dutyAffirm)
				.append("entityDisposeMethod", this.entityDisposeMethod)
				.append("financeDisposeMethod", this.financeDisposeMethod).append("dutyOffice", this.dutyOffice)
				.append("dutyPrivate", this.dutyPrivate).append("shipmentsId", this.shipmentsId).toString();
	}
}