package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 特殊票折(导入)
 * <pre> 
 * 描述：特殊票折(导入) 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-18 15:28:17
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmCwSpecialTicketDiscount",description = "特殊票折(导入)") 
public class ScmCwSpecialTicketDiscount extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="序列号")
	protected String serialNum; 
	
	@ApiModelProperty(value="票折编号")
	protected String discountCode; 
	
	@ApiModelProperty(value="票折日期")
	protected java.util.Date discountDate; 
	
	@ApiModelProperty(value="票折来源(0:经销商RB,1:分销商RB,2:OTCRB,3:医院RB,4:活动RB,5:补差RB)")
	protected String discountSource; 
	
	@ApiModelProperty(value="商务分区")
	protected String businessDivision; 
	
	@ApiModelProperty(value="BU")
	protected String bu; 
	
	@ApiModelProperty(value="省区")
	protected String province; 
	
	@ApiModelProperty(value="商业名称")
	protected String commerceName; 
	
	@ApiModelProperty(value="商业编号")
	protected String commerceCode; 
	
	@ApiModelProperty(value="商业级别")
	protected String commerceLevel; 
	
	@ApiModelProperty(value="产品名称")
	protected String productName; 
	
	@ApiModelProperty(value="产品编号")
	protected String productCode; 
	
	@ApiModelProperty(value="产品通用名")
	protected String commonName; 
	
	@ApiModelProperty(value="终端名称")
	protected String teminalName; 
	
	@ApiModelProperty(value="可折金额")
	protected Double discountPrice; 
	
	@ApiModelProperty(value="货主ID")
	protected String ownerId; 
	
	@ApiModelProperty(value="是否确认(0未确认,1确认)")
	protected String isAffirm; 
	
	
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
	
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}
	
	/**
	 * 返回 序列号
	 * @return
	 */
	public String getSerialNum() {
		return this.serialNum;
	}
	
	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}
	
	/**
	 * 返回 票折编号
	 * @return
	 */
	public String getDiscountCode() {
		return this.discountCode;
	}
	
	public void setDiscountDate(java.util.Date discountDate) {
		this.discountDate = discountDate;
	}
	
	/**
	 * 返回 票折日期
	 * @return
	 */
	public java.util.Date getDiscountDate() {
		return this.discountDate;
	}
	
	public void setDiscountSource(String discountSource) {
		this.discountSource = discountSource;
	}
	
	/**
	 * 返回 票折来源(0:经销商RB,1:分销商RB,2:OTCRB,3:医院RB,4:活动RB,5:补差RB)
	 * @return
	 */
	public String getDiscountSource() {
		return this.discountSource;
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
	
	public void setBu(String bu) {
		this.bu = bu;
	}
	
	/**
	 * 返回 BU
	 * @return
	 */
	public String getBu() {
		return this.bu;
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
	
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	/**
	 * 返回 产品编号
	 * @return
	 */
	public String getProductCode() {
		return this.productCode;
	}
	
	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}
	
	/**
	 * 返回 产品通用名
	 * @return
	 */
	public String getCommonName() {
		return this.commonName;
	}
	
	public void setTeminalName(String teminalName) {
		this.teminalName = teminalName;
	}
	
	/**
	 * 返回 终端名称
	 * @return
	 */
	public String getTeminalName() {
		return this.teminalName;
	}
	
	public void setDiscountPrice(Double discountPrice) {
		this.discountPrice = discountPrice;
	}
	
	/**
	 * 返回 可折金额
	 * @return
	 */
	public Double getDiscountPrice() {
		return this.discountPrice;
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
	
	public void setIsAffirm(String isAffirm) {
		this.isAffirm = isAffirm;
	}
	
	/**
	 * 返回 是否确认(0未确认,1确认)
	 * @return
	 */
	public String getIsAffirm() {
		return this.isAffirm;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("serialNum", this.serialNum) 
		.append("discountCode", this.discountCode) 
		.append("discountDate", this.discountDate) 
		.append("discountSource", this.discountSource) 
		.append("businessDivision", this.businessDivision) 
		.append("bu", this.bu) 
		.append("province", this.province) 
		.append("commerceName", this.commerceName) 
		.append("commerceCode", this.commerceCode) 
		.append("commerceLevel", this.commerceLevel) 
		.append("productName", this.productName) 
		.append("productCode", this.productCode) 
		.append("commonName", this.commonName) 
		.append("teminalName", this.teminalName) 
		.append("discountPrice", this.discountPrice) 
		.append("ownerId", this.ownerId) 
		.append("isAffirm", this.isAffirm) 
		.toString();
	}
}