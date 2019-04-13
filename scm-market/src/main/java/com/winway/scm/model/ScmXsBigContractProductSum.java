package com.winway.scm.model;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.base.model.BaseModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


 /**
 * scm_xs_bigcontractproductsum
 * <pre> 
 * 描述：scm_xs_bigcontractproductsum 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-14 18:06:04
 * 版权：美达项目组
 * </pre>
 */
 @ApiModel(value = "ScmXsBigContractProductSum",description = "scm_xs_bigcontractproductsum") 
public class ScmXsBigContractProductSum extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="编号")
	protected String id; 
	
	@ApiModelProperty(value="货主Id")
	protected String ownerId; 
	
	@ApiModelProperty(value="商品首营Id")
	protected String commerceFirstId; 
	
	@ApiModelProperty(value="商品品规")
	protected String productGauge; 
	
	@ApiModelProperty(value="药品类型")
	protected String MedicineType; 
	
	@ApiModelProperty(value="商品常用名称")
	protected String commonName; 
	
	@ApiModelProperty(value="单位")
	protected String unit; 
	
	@ApiModelProperty(value="标准包装数")
	protected Integer aChange; 
	
	@ApiModelProperty(value="药品供应状态")
	protected String suppyType; 
	
	@ApiModelProperty(value="建议本次申请量")
	protected Integer suggestApplyNum; 
	
	@ApiModelProperty(value="上次发货时间")
	protected java.util.Date lastShipmentDate; 
	
	@ApiModelProperty(value="省区本月SMS销量")
	protected Integer SMSCurrentMonthSales; 
	
	@ApiModelProperty(value="药品编号")
	protected String productCode; 
	
	@ApiModelProperty(value="是否SMS")
	protected String isSMS; 
	
	@ApiModelProperty(value="数量")
	protected Integer theNumber; 
	
	@ApiModelProperty(value="件数")
	protected Integer packageNum; 
	
	@ApiModelProperty(value="含税单价")
	protected Double unitPrice; 
	
	@ApiModelProperty(value="总价")
	protected Double sumPrice; 
	
	@ApiModelProperty(value="剩余数量")
	protected Integer surplusSUm; 
	
	@ApiModelProperty(value="剩余数量")
	protected String agreementSummaryId; 
	
	@ApiModelProperty(value="商品名称")
	protected String v_productName;
	
	@ApiModelProperty(value="药品类型")
	protected String v_productState;
	
	@ApiModelProperty(value="大合同商品汇总表ID")
	protected String BigContractProductId;
	
	@ApiModelProperty(value="件装量数组")
	protected String achage;

	public void setAchage(String achage) {
		this.achage = achage;
	}

	public String getV_productName() {
		return v_productName;
	}

	public void setV_productName(String v_productName) {
		this.v_productName = v_productName;
	}

	public String getV_productState() {
		return v_productState;
	}

	public void setV_productState(String v_productState) {
		this.v_productState = v_productState;
	}

	public String getBigContractProductId() {
		return BigContractProductId;
	}

	public void setBigContractProductId(String bigContractProductId) {
		BigContractProductId = bigContractProductId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAgreementSummaryId() {
		return agreementSummaryId;
	}

	public void setAgreementSummaryId(String agreementSummaryId) {
		this.agreementSummaryId = agreementSummaryId;
	}


	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 返回 编号
	 * @return
	 */
	public String getId() {
		return this.id;
	}
	
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	
	/**
	 * 返回 货主Id
	 * @return
	 */
	public String getOwnerId() {
		return this.ownerId;
	}
	
	public void setCommerceFirstId(String commerceFirstId) {
		this.commerceFirstId = commerceFirstId;
	}
	
	/**
	 * 返回 商品首营Id
	 * @return
	 */
	public String getCommerceFirstId() {
		return this.commerceFirstId;
	}
	
	public void setProductGauge(String productGauge) {
		this.productGauge = productGauge;
	}
	
	/**
	 * 返回 商品品规
	 * @return
	 */
	public String getProductGauge() {
		return this.productGauge;
	}
	
	public void setMedicineType(String MedicineType) {
		this.MedicineType = MedicineType;
	}
	
	/**
	 * 返回 药品类型
	 * @return
	 */
	public String getMedicineType() {
		return this.MedicineType;
	}
	
	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}
	
	/**
	 * 返回 商品常用名称
	 * @return
	 */
	public String getCommonName() {
		return this.commonName;
	}
	
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	/**
	 * 返回 单位
	 * @return
	 */
	public String getUnit() {
		return this.unit;
	}
	
	public Integer getaChange() {
		return aChange;
	}

	public void setaChange(Integer aChange) {
		this.aChange = aChange;
	}

	public void setSuppyType(String suppyType) {
		this.suppyType = suppyType;
	}
	
	/**
	 * 返回 药品供应状态
	 * @return
	 */
	public String getSuppyType() {
		return this.suppyType;
	}
	
	public void setSuggestApplyNum(Integer suggestApplyNum) {
		this.suggestApplyNum = suggestApplyNum;
	}
	
	/**
	 * 返回 建议本次申请量
	 * @return
	 */
	public Integer getSuggestApplyNum() {
		return this.suggestApplyNum;
	}
	
	public void setLastShipmentDate(java.util.Date lastShipmentDate) {
		this.lastShipmentDate = lastShipmentDate;
	}
	
	/**
	 * 返回 上次发货时间
	 * @return
	 */
	public java.util.Date getLastShipmentDate() {
		return this.lastShipmentDate;
	}
	
	public void setSMSCurrentMonthSales(Integer SMSCurrentMonthSales) {
		this.SMSCurrentMonthSales = SMSCurrentMonthSales;
	}
	
	/**
	 * 返回 省区本月SMS销量
	 * @return
	 */
	public Integer getSMSCurrentMonthSales() {
		return this.SMSCurrentMonthSales;
	}
	
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	/**
	 * 返回 药品编号
	 * @return
	 */
	public String getProductCode() {
		return this.productCode;
	}
	
	public void setIsSMS(String isSMS) {
		this.isSMS = isSMS;
	}
	
	/**
	 * 返回 是否SMS
	 * @return
	 */
	public String getIsSMS() {
		return this.isSMS;
	}
	
	public void setTheNumber(Integer theNumber) {
		this.theNumber = theNumber;
	}
	
	/**
	 * 返回 数量
	 * @return
	 */
	public Integer getTheNumber() {
		return this.theNumber;
	}
	
	public void setPackageNum(Integer packageNum) {
		this.packageNum = packageNum;
	}
	
	/**
	 * 返回 件数
	 * @return
	 */
	public Integer getPackageNum() {
		return this.packageNum;
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
	
	public void setSumPrice(Double sumPrice) {
		this.sumPrice = sumPrice;
	}
	
	/**
	 * 返回 总价
	 * @return
	 */
	public Double getSumPrice() {
		return this.sumPrice;
	}
	
	public void setSurplusSUm(Integer surplusSUm) {
		this.surplusSUm = surplusSUm;
	}
	
	/**
	 * 返回 剩余数量
	 * @return
	 */
	public Integer getSurplusSUm() {
		return this.surplusSUm;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("ownerId", this.ownerId) 
		.append("commerceFirstId", this.commerceFirstId) 
		.append("productGauge", this.productGauge) 
		.append("MedicineType", this.MedicineType) 
		.append("commonName", this.commonName) 
		.append("unit", this.unit) 
		.append("aChange", this.aChange) 
		.append("suppyType", this.suppyType) 
		.append("suggestApplyNum", this.suggestApplyNum) 
		.append("lastShipmentDate", this.lastShipmentDate) 
		.append("SMSCurrentMonthSales", this.SMSCurrentMonthSales) 
		.append("productCode", this.productCode) 
		.append("isSMS", this.isSMS) 
		.append("theNumber", this.theNumber) 
		.append("packageNum", this.packageNum) 
		.append("unitPrice", this.unitPrice) 
		.append("sumPrice", this.sumPrice) 
		.append("surplusSUm", this.surplusSUm) 
		.toString();
	}
}