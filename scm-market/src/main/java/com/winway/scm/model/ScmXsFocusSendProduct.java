package com.winway.scm.model;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;

/**
 * 集中发货商品表
 * 
 * <pre>
 *  
 * 描述：集中发货商品表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:44
 * 版权：美达开发小组
 * </pre>
 */
@ApiModel(value = "ScmXsFocusSendProduct", description = "集中发货商品表")
public class ScmXsFocusSendProduct extends BaseModel<String> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	protected String id;

	@ApiModelProperty(value = "数量")
	protected Integer theNumber;

	@ApiModelProperty(value = "件数")
	protected double packageNum;

	@ApiModelProperty(value = "含税单价")
	protected Double unitPrice;

	@ApiModelProperty(value = "总价")
	protected Double sumPrice;

	@ApiModelProperty(value = "集中发货ID")
	protected String FocusSendId;
	
	@ApiModelProperty(value = "单位")
	protected String unit;
	@ApiModelProperty(value = "规格")
	protected String productGauge;

	@ApiModelProperty(value = "大合同商品ID")
	protected String BigContractProductId;

	@ApiModelProperty(value = "件装量")
	protected Integer achage;

	@ApiModelProperty(value = "年月")
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM")
	protected java.util.Date yearAndMonth;

	@ApiModelProperty(value = "药品类型")
	protected String MedicineType;
	@ApiModelProperty(value = "商品常用名称")
	protected String commonName;
	@ApiModelProperty(value = "药品供应状态")
	protected String suppyType;
	@ApiModelProperty(value = "建议本次申请量")
	protected int suggestApplyNum;
	@ApiModelProperty(value = "上次发货时间")
	protected Date lastShipmentDate;
	@ApiModelProperty(value = "省区本月SMS销量")
	protected int smscurrentMonthSales;
	
	@ApiModelProperty(value = "药品编号")
	protected String productCode;

	@ApiModelProperty(value = "商品名")
	protected String productName;
	@ApiModelProperty(value = "剂型")
	protected String AgentType;
	@ApiModelProperty(value = "生产厂家")
	protected String manufacturer;
	@ApiModelProperty(value = "批准文号")
	protected String approvalCode;
	@ApiModelProperty(value = "经营范围")
	protected String medicineClassify;
	@ApiModelProperty(value = "经营范围")
	protected String businessScope;
	@ApiModelProperty(value = "商品子码")
	protected String code;

	@ApiModelProperty(value = "货主ID")
	protected String v_ownerId;
	@ApiModelProperty(value = "集中发货编号")
	protected String v_FocusSendCode;
	@ApiModelProperty(value = "是否SMS")
	protected String v_isSMS;
	@ApiModelProperty(value = "商品常用名称")
	protected String v_commonName;
	@ApiModelProperty(value = "药品类型")
	protected String v_MedicineType;
	@ApiModelProperty(value = "商品品规")
	protected String v_productGauge;
	@ApiModelProperty(value = "特求意见")
	protected String v_specialOpinion;
	@ApiModelProperty(value = "企业类别(0药品经营企业,1医疗机构)")
	protected String v_commerceState;
	@ApiModelProperty(value = "职能类型")
	protected String v_functionType;
	@ApiModelProperty(value = "联系人")
	protected String v_contactPersion;
	@ApiModelProperty(value = "联系方式")
	protected String v_phone;
	@ApiModelProperty(value = "商业名称")
	protected String v_commerceName;
	@ApiModelProperty(value = "商业首营ID")
	protected String v_commerceFirstId;
	@ApiModelProperty(value = "购销方式")
	protected String v_marketingWay;
	@ApiModelProperty(value = "商品编号")
	protected String v_productCode;

	
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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

	public String getSuppyType() {
		return suppyType;
	}

	public void setSuppyType(String suppyType) {
		this.suppyType = suppyType;
	}

	
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getProductGauge() {
		return productGauge;
	}

	public void setProductGauge(String productGauge) {
		this.productGauge = productGauge;
	}

	public int getSuggestApplyNum() {
		return suggestApplyNum;
	}

	public void setSuggestApplyNum(int suggestApplyNum) {
		this.suggestApplyNum = suggestApplyNum;
	}

	public Date getLastShipmentDate() {
		return lastShipmentDate;
	}

	public void setLastShipmentDate(Date lastShipmentDate) {
		this.lastShipmentDate = lastShipmentDate;
	}

	public int getSmscurrentMonthSales() {
		return smscurrentMonthSales;
	}

	public void setSmscurrentMonthSales(int smscurrentMonthSales) {
		this.smscurrentMonthSales = smscurrentMonthSales;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getV_productCode() {
		return v_productCode;
	}

	public void setV_productCode(String v_productCode) {
		this.v_productCode = v_productCode;
	}

	public String getV_ownerId() {
		return v_ownerId;
	}

	public void setV_ownerId(String v_ownerId) {
		this.v_ownerId = v_ownerId;
	}

	public String getV_FocusSendCode() {
		return v_FocusSendCode;
	}

	public void setV_FocusSendCode(String v_FocusSendCode) {
		this.v_FocusSendCode = v_FocusSendCode;
	}

	public String getV_isSMS() {
		return v_isSMS;
	}

	public void setV_isSMS(String v_isSMS) {
		this.v_isSMS = v_isSMS;
	}

	public String getV_commonName() {
		return v_commonName;
	}

	public void setV_commonName(String v_commonName) {
		this.v_commonName = v_commonName;
	}

	public String getV_MedicineType() {
		return v_MedicineType;
	}

	public void setV_MedicineType(String v_MedicineType) {
		this.v_MedicineType = v_MedicineType;
	}

	public String getV_productGauge() {
		return v_productGauge;
	}

	public void setV_productGauge(String v_productGauge) {
		this.v_productGauge = v_productGauge;
	}

	public String getV_specialOpinion() {
		return v_specialOpinion;
	}

	public void setV_specialOpinion(String v_specialOpinion) {
		this.v_specialOpinion = v_specialOpinion;
	}

	public String getV_commerceState() {
		return v_commerceState;
	}

	public void setV_commerceState(String v_commerceState) {
		this.v_commerceState = v_commerceState;
	}

	public String getV_functionType() {
		return v_functionType;
	}

	public void setV_functionType(String v_functionType) {
		this.v_functionType = v_functionType;
	}

	public String getV_contactPersion() {
		return v_contactPersion;
	}

	public void setV_contactPersion(String v_contactPersion) {
		this.v_contactPersion = v_contactPersion;
	}

	public String getV_phone() {
		return v_phone;
	}

	public void setV_phone(String v_phone) {
		this.v_phone = v_phone;
	}

	public String getV_commerceName() {
		return v_commerceName;
	}

	public void setV_commerceName(String v_commerceName) {
		this.v_commerceName = v_commerceName;
	}

	public String getV_commerceFirstId() {
		return v_commerceFirstId;
	}

	public void setV_commerceFirstId(String v_commerceFirstId) {
		this.v_commerceFirstId = v_commerceFirstId;
	}

	public String getV_marketingWay() {
		return v_marketingWay;
	}

	public void setV_marketingWay(String v_marketingWay) {
		this.v_marketingWay = v_marketingWay;
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

	public void setTheNumber(Integer theNumber) {
		this.theNumber = theNumber;
	}

	/**
	 * 返回 数量
	 * 
	 * @return
	 */
	public Integer getTheNumber() {
		return this.theNumber;
	}

	public void setPackageNum(double packageNum) {
		this.packageNum = packageNum;
	}

	/**
	 * 返回 件数
	 * 
	 * @return
	 */
	public double getPackageNum() {
		return this.packageNum;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	/**
	 * 返回 含税单价
	 * 
	 * @return
	 */
	public Double getUnitPrice() {
		return this.unitPrice;
	}

	public void setSumPrice(Double sumPrice) {
		this.sumPrice = sumPrice;
	}

	/**
	 * 返回 总价
	 * 
	 * @return
	 */
	public Double getSumPrice() {
		return this.sumPrice;
	}

	public void setFocusSendId(String FocusSendId) {
		this.FocusSendId = FocusSendId;
	}

	/**
	 * 返回 集中发货ID
	 * 
	 * @return
	 */
	public String getFocusSendId() {
		return this.FocusSendId;
	}

	public void setBigContractProductId(String BigContractProductId) {
		this.BigContractProductId = BigContractProductId;
	}

	/**
	 * 返回 大合同商品ID
	 * 
	 * @return
	 */
	public String getBigContractProductId() {
		return this.BigContractProductId;
	}

	public void setYearAndMonth(java.util.Date yearAndMonth) {
		this.yearAndMonth = yearAndMonth;
	}

	public Integer getAchage() {
		return achage;
	}

	public void setAchage(Integer achage) {
		this.achage = achage;
	}

	/**
	 * 返回 年月
	 * 
	 * @return
	 */
	public java.util.Date getYearAndMonth() {
		return this.yearAndMonth;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).append("theNumber", this.theNumber)
				.append("packageNum", this.packageNum).append("unitPrice", this.unitPrice)
				.append("sumPrice", this.sumPrice).append("FocusSendId", this.FocusSendId)
				.append("BigContractProductId", this.BigContractProductId).append("yearAndMonth", this.yearAndMonth)
				.toString();
	}
}