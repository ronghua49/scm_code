package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 分销商RB汇总
 * <pre> 
 * 描述：分销商RB汇总 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-14 16:38:31
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmCwDistributorRbCollect",description = "分销商RB汇总") 
public class ScmCwDistributorRbCollect extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="主表ID")
	protected String masterId; 
	
	@ApiModelProperty(value="商务分区")
	protected String businessDivision; 
	
	@ApiModelProperty(value="省区")
	protected String province; 
	
	@ApiModelProperty(value="商务分区ID")
	protected String businessDivisionId; 
	
	@ApiModelProperty(value="省区ID")
	protected String provinceId; 
	
	@ApiModelProperty(value="商业名称")
	protected String commerceName; 
	
	@ApiModelProperty(value="商业编号")
	protected String commerceCode; 
	
	@ApiModelProperty(value="商业ID")
	protected String commerceId; 
	
	@ApiModelProperty(value="本次计算RB")
	protected Double thisCalculationRb; 
	
	@ApiModelProperty(value="扣除")
	protected Double deduct; 
	
	@ApiModelProperty(value="本次实发")
	protected Double theRealHair; 
	
	@ApiModelProperty(value="付款方式")
	protected String paymentMethod; 
	
	@ApiModelProperty(value="收款商业编号")
	protected String getMoneyCommerceCode; 
	
	@ApiModelProperty(value="收款商业ID")
	protected String getMoneyCommerceId; 
	
	@ApiModelProperty(value="收款商业")
	protected String getMoneyCommerce; 
	
	@ApiModelProperty(value="备注")
	protected String memo; 
	
	
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
	 * 返回 省区ID
	 * @return
	 */
	public String getProvinceId() {
		return this.provinceId;
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
	
	public void setDeduct(Double deduct) {
		this.deduct = deduct;
	}
	
	/**
	 * 返回 扣除
	 * @return
	 */
	public Double getDeduct() {
		return this.deduct;
	}
	
	public void setTheRealHair(Double theRealHair) {
		this.theRealHair = theRealHair;
	}
	
	/**
	 * 返回 本次实发
	 * @return
	 */
	public Double getTheRealHair() {
		return this.theRealHair;
	}
	
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	/**
	 * 返回 付款方式
	 * @return
	 */
	public String getPaymentMethod() {
		return this.paymentMethod;
	}
	
	public void setGetMoneyCommerceCode(String getMoneyCommerceCode) {
		this.getMoneyCommerceCode = getMoneyCommerceCode;
	}
	
	/**
	 * 返回 收款商业编号
	 * @return
	 */
	public String getGetMoneyCommerceCode() {
		return this.getMoneyCommerceCode;
	}
	
	public void setGetMoneyCommerceId(String getMoneyCommerceId) {
		this.getMoneyCommerceId = getMoneyCommerceId;
	}
	
	/**
	 * 返回 收款商业ID
	 * @return
	 */
	public String getGetMoneyCommerceId() {
		return this.getMoneyCommerceId;
	}
	
	public void setGetMoneyCommerce(String getMoneyCommerce) {
		this.getMoneyCommerce = getMoneyCommerce;
	}
	
	/**
	 * 返回 收款商业
	 * @return
	 */
	public String getGetMoneyCommerce() {
		return this.getMoneyCommerce;
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
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("masterId", this.masterId) 
		.append("businessDivision", this.businessDivision) 
		.append("province", this.province) 
		.append("businessDivisionId", this.businessDivisionId) 
		.append("provinceId", this.provinceId) 
		.append("commerceName", this.commerceName) 
		.append("commerceCode", this.commerceCode) 
		.append("commerceId", this.commerceId) 
		.append("thisCalculationRb", this.thisCalculationRb) 
		.append("deduct", this.deduct) 
		.append("theRealHair", this.theRealHair) 
		.append("paymentMethod", this.paymentMethod) 
		.append("getMoneyCommerceCode", this.getMoneyCommerceCode) 
		.append("getMoneyCommerceId", this.getMoneyCommerceId) 
		.append("getMoneyCommerce", this.getMoneyCommerce) 
		.append("memo", this.memo) 
		.toString();
	}
}