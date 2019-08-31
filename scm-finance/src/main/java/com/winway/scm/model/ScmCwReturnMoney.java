package com.winway.scm.model;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 商业回款表
 * <pre> 
 * 描述：商业回款表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-23 18:19:25
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmCwReturnMoney",description = "商业回款表") 
public class ScmCwReturnMoney extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="核销状态(0:未核销,1:已核销)")
	protected String verifyType; 
	
	@ApiModelProperty(value="汇款时间")
	protected java.util.Date remittanceDate; 
	
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
	
	@ApiModelProperty(value="汇款方式")
	protected String remittanceType; 
	
	@ApiModelProperty(value="承兑汇票是否验真伪")
	protected String acceptIsTrue; 
	
	@ApiModelProperty(value="承兑汇票到期日")
	protected java.util.Date acceptExpire; 
	
	@ApiModelProperty(value="是否预付款(0:预付款,1赊销)")
	protected String paymentType; 
	
	@ApiModelProperty(value="回款金额")
	protected Double price; 
	
	@ApiModelProperty(value="调整后回款金额")
	protected Double updatePrice; 
	
	@ApiModelProperty(value="备注")
	protected String memo; 
	
	@ApiModelProperty(value="导入人")
	protected String toLeadPersion; 
	
	@ApiModelProperty(value="导入时间")
	protected java.util.Date toLeadDate; 
	
	@ApiModelProperty(value="调整人")
	protected String affirmPersion; 
	
	@ApiModelProperty(value="调整时间")
	protected java.util.Date affirmDate; 
	
	@ApiModelProperty(value="货主ID")
	protected String ownerId; 
	
	@ApiModelProperty(value="匹配商业名称")
	protected String matchCommerceName;
	
	@ApiModelProperty(value="匹配结果")
	protected String matchResult;
	
	@ApiModelProperty(value="是否确认(0:未确认,1:已确认)")
	protected String isAffirm;
	//回款月份
	@ApiModelProperty(value="回款月份")
	protected String getMoth;
	
	@ApiModelProperty(value="剩余金额")
	protected Double balancePrice;
	
	
	public Double getBalancePrice() {
		return balancePrice;
	}

	public void setBalancePrice(Double balancePrice) {
		this.balancePrice = balancePrice;
	}

	public String getGetMoth() {
		this.getMoth=date(this.getRemittanceDate());
		return this.getMoth;
	}

	public String getIsAffirm() {
		return isAffirm;
	}

	public void setIsAffirm(String isAffirm) {
		this.isAffirm = isAffirm;
	}

	public String getMatchCommerceName() {
		return matchCommerceName;
	}

	public void setMatchCommerceName(String matchCommerceName) {
		this.matchCommerceName = matchCommerceName;
	}

	public String getMatchResult() {
		return matchResult;
	}

	public void setMatchResult(String matchResult) {
		this.matchResult = matchResult;
	}

	public void setId(String id) {
		this.id = id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setGetMoth(String getMoth) {
		this.getMoth = getMoth;
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
	 * 返回 核销状态(0:未核销,1:已核销)
	 * @return
	 */
	public String getVerifyType() {
		return this.verifyType;
	}
	
	public void setRemittanceDate(java.util.Date remittanceDate) {
		this.remittanceDate = remittanceDate;
	}
	
	/**
	 * 返回 汇款时间
	 * @return
	 */
	public java.util.Date getRemittanceDate() {
		return this.remittanceDate;
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
	
	public void setAcceptIsTrue(String acceptIsTrue) {
		this.acceptIsTrue = acceptIsTrue;
	}
	
	/**
	 * 返回 承兑汇票是否验真伪
	 * @return
	 */
	public String getAcceptIsTrue() {
		return this.acceptIsTrue;
	}
	
	public void setAcceptExpire(java.util.Date acceptExpire) {
		this.acceptExpire = acceptExpire;
	}
	
	/**
	 * 返回 承兑汇票到期日
	 * @return
	 */
	public java.util.Date getAcceptExpire() {
		return this.acceptExpire;
	}
	
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	
	/**
	 * 返回 是否预付款(0:预付款,1赊销)
	 * @return
	 */
	public String getPaymentType() {
		return this.paymentType;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	/**
	 * 返回 回款金额
	 * @return
	 */
	public Double getPrice() {
		return this.price;
	}
	
	public void setUpdatePrice(Double updatePrice) {
		this.updatePrice = updatePrice;
	}
	
	/**
	 * 返回 调整后回款金额
	 * @return
	 */
	public Double getUpdatePrice() {
		return this.updatePrice;
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
	
	public void setToLeadPersion(String toLeadPersion) {
		this.toLeadPersion = toLeadPersion;
	}
	
	/**
	 * 返回 导入人
	 * @return
	 */
	public String getToLeadPersion() {
		return this.toLeadPersion;
	}
	
	public void setToLeadDate(java.util.Date toLeadDate) {
		this.toLeadDate = toLeadDate;
	}
	
	/**
	 * 返回 导入时间
	 * @return
	 */
	public java.util.Date getToLeadDate() {
		return this.toLeadDate;
	}
	
	public void setAffirmPersion(String affirmPersion) {
		this.affirmPersion = affirmPersion;
	}
	
	/**
	 * 返回 确认人
	 * @return
	 */
	public String getAffirmPersion() {
		return this.affirmPersion;
	}
	
	public void setAffirmDate(java.util.Date affirmDate) {
		this.affirmDate = affirmDate;
	}
	
	/**
	 * 返回 确认时间
	 * @return
	 */
	public java.util.Date getAffirmDate() {
		return this.affirmDate;
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
		.append("remittanceDate", this.remittanceDate) 
		.append("commerceId", this.commerceId) 
		.append("commerceCode", this.commerceCode) 
		.append("commerceName", this.commerceName) 
		.append("businessDivision", this.businessDivision) 
		.append("province", this.province) 
		.append("businessDivisionId", this.businessDivisionId) 
		.append("provinceId", this.provinceId) 
		.append("remittanceType", this.remittanceType) 
		.append("acceptIsTrue", this.acceptIsTrue) 
		.append("acceptExpire", this.acceptExpire) 
		.append("paymentType", this.paymentType) 
		.append("price", this.price) 
		.append("updatePrice", this.updatePrice) 
		.append("memo", this.memo) 
		.append("toLeadPersion", this.toLeadPersion) 
		.append("toLeadDate", this.toLeadDate) 
		.append("affirmPersion", this.affirmPersion) 
		.append("affirmDate", this.affirmDate) 
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