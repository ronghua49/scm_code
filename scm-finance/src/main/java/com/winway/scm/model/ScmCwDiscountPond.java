package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 票折池表
 * <pre> 
 * 描述：票折池表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-23 18:19:25
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmCwDiscountPond",description = "票折池表") 
public class ScmCwDiscountPond extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="票折编号")
	protected String discountCode; 
	
	@ApiModelProperty(value="票折日期")
	protected java.util.Date discountDate; 
	
	@ApiModelProperty(value="票折来源(0:经销商RB,1:分销商RB,2:OTCRB,3:医院RB,4:活动RB,5:补差RB)")
	protected String discountSource; 
	
	@ApiModelProperty(value="商务分区")
	protected String businessDivision; 
	
	@ApiModelProperty(value="省区")
	protected String province; 
	
	@ApiModelProperty(value="商务分区ID")
	protected String businessDivisionId; 
	
	@ApiModelProperty(value="省区id")
	protected String provinceId; 
	
	@ApiModelProperty(value="商业ID")
	protected String commerceId; 
	
	@ApiModelProperty(value="商品编号")
	protected String commerceCode; 
	
	@ApiModelProperty(value="商业名称")
	protected String commerceName; 
	
	@ApiModelProperty(value="商业级别")
	protected String commerceLevel; 
	
	@ApiModelProperty(value="终端名称")
	protected String treminal; 
	
	@ApiModelProperty(value="可折金额")
	protected Double mayDeduction; 
	
	@ApiModelProperty(value="是否冻结")
	protected String isFreeze; 
	
	@ApiModelProperty(value="是否扣减")
	protected String isDeduction; 
	
	@ApiModelProperty(value="扣减金额")
	protected Double deductionPrice; 
	
	@ApiModelProperty(value="已折金额")
	protected Double yetDeduction; 
	
	@ApiModelProperty(value="余额")
	protected Double balance; 
	
	@ApiModelProperty(value="票折商业")
	protected String discountCommerce; 
	
	@ApiModelProperty(value="票折状态:0未票折,1:部分票折,2:全部票折")
	protected String discountType; 
	
	@ApiModelProperty(value="货主ID")
	protected String ownerId; 
	
	
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
	
	public void setTreminal(String treminal) {
		this.treminal = treminal;
	}
	
	/**
	 * 返回 终端名称
	 * @return
	 */
	public String getTreminal() {
		return this.treminal;
	}
	
	public void setMayDeduction(Double mayDeduction) {
		this.mayDeduction = mayDeduction;
	}
	
	/**
	 * 返回 可折金额
	 * @return
	 */
	public Double getMayDeduction() {
		return this.mayDeduction;
	}
	
	public void setIsFreeze(String isFreeze) {
		this.isFreeze = isFreeze;
	}
	
	/**
	 * 返回 是否冻结
	 * @return
	 */
	public String getIsFreeze() {
		return this.isFreeze;
	}
	
	public void setIsDeduction(String isDeduction) {
		this.isDeduction = isDeduction;
	}
	
	/**
	 * 返回 是否扣减
	 * @return
	 */
	public String getIsDeduction() {
		return this.isDeduction;
	}
	
	public void setDeductionPrice(Double deductionPrice) {
		this.deductionPrice = deductionPrice;
	}
	
	/**
	 * 返回 扣减金额
	 * @return
	 */
	public Double getDeductionPrice() {
		return this.deductionPrice;
	}
	
	public void setYetDeduction(Double yetDeduction) {
		this.yetDeduction = yetDeduction;
	}
	
	/**
	 * 返回 已折金额
	 * @return
	 */
	public Double getYetDeduction() {
		return this.yetDeduction;
	}
	
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	/**
	 * 返回 余额
	 * @return
	 */
	public Double getBalance() {
		return this.balance;
	}
	
	public void setDiscountCommerce(String discountCommerce) {
		this.discountCommerce = discountCommerce;
	}
	
	/**
	 * 返回 票折商业
	 * @return
	 */
	public String getDiscountCommerce() {
		return this.discountCommerce;
	}
	
	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}
	
	/**
	 * 返回 票折状态:0未票折,1:部分票折,2:全部票折
	 * @return
	 */
	public String getDiscountType() {
		return this.discountType;
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
		.append("discountCode", this.discountCode) 
		.append("discountDate", this.discountDate) 
		.append("discountSource", this.discountSource) 
		.append("businessDivision", this.businessDivision) 
		.append("province", this.province) 
		.append("businessDivisionId", this.businessDivisionId) 
		.append("provinceId", this.provinceId) 
		.append("commerceId", this.commerceId) 
		.append("commerceCode", this.commerceCode) 
		.append("commerceName", this.commerceName) 
		.append("commerceLevel", this.commerceLevel) 
		.append("treminal", this.treminal) 
		.append("mayDeduction", this.mayDeduction) 
		.append("isFreeze", this.isFreeze) 
		.append("isDeduction", this.isDeduction) 
		.append("deductionPrice", this.deductionPrice) 
		.append("yetDeduction", this.yetDeduction) 
		.append("balance", this.balance) 
		.append("discountCommerce", this.discountCommerce) 
		.append("discountType", this.discountType) 
		.append("ownerId", this.ownerId) 
		.toString();
	}
}