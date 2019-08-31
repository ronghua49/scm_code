package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 经销商协议付款方式
 * <pre> 
 * 描述：经销商协议付款方式 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-07-01 15:24:12
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmXsDealerPayStyle",description = "经销商协议付款方式") 
public class ScmXsDealerPayStyle extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="协议折扣条款id")
	protected String masterId; 
	
	@ApiModelProperty(value="本次付款类型")
	protected String paymentType; 
	
	@ApiModelProperty(value="上次付款类型")
	protected String lastPaymentType; 
	
	@ApiModelProperty(value="本次申请折扣")
	protected Double theApplication;
	
	@ApiModelProperty(value="上次申请折扣")
	protected Double lastTheApplication;
	
	@ApiModelProperty(value="起始天数")
	protected Integer StardDay; 
	
	@ApiModelProperty(value="截止天数")
	protected Integer endDay; 
	
	@ApiModelProperty(value="标准折扣")
	protected Double discountStandard; 
	
	
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
	 * 返回 协议折扣条款id
	 * @return
	 */
	public String getMasterId() {
		return this.masterId;
	}
	
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	
	/**
	 * 返回 本次付款类型
	 * @return
	 */
	public String getPaymentType() {
		return this.paymentType;
	}
	
	public void setLastPaymentType(String lastPaymentType) {
		this.lastPaymentType = lastPaymentType;
	}
	
	/**
	 * 返回 上次付款类型
	 * @return
	 */
	public String getLastPaymentType() {
		return this.lastPaymentType;
	}
	
	public void setTheApplication(Double theApplication) {
		this.theApplication = theApplication;
	}
	
	/**
	 * 返回 本次申请折扣
	 * @return
	 */
	public Double getTheApplication() {
		return this.theApplication;
	}
	
	public void setLastTheApplication(Double lastTheApplication) {
		this.lastTheApplication = lastTheApplication;
	}
	
	/**
	 * 返回 上次申请折扣
	 * @return
	 */
	public Double getLastTheApplication() {
		return this.lastTheApplication;
	}
	
	public void setStardDay(Integer StardDay) {
		this.StardDay = StardDay;
	}
	
	/**
	 * 返回 起始天数
	 * @return
	 */
	public Integer getStardDay() {
		return this.StardDay;
	}
	
	public void setEndDay(Integer endDay) {
		this.endDay = endDay;
	}
	
	/**
	 * 返回 截止天数
	 * @return
	 */
	public Integer getEndDay() {
		return this.endDay;
	}
	
	public void setDiscountStandard(Double discountStandard) {
		this.discountStandard = discountStandard;
	}
	
	/**
	 * 返回 标准折扣
	 * @return
	 */
	public Double getDiscountStandard() {
		return this.discountStandard;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("masterId", this.masterId) 
		.append("paymentType", this.paymentType) 
		.append("lastPaymentType", this.lastPaymentType) 
		.append("theApplication", this.theApplication) 
		.append("lastTheApplication", this.lastTheApplication) 
		.append("StardDay", this.StardDay) 
		.append("endDay", this.endDay) 
		.append("discountStandard", this.discountStandard) 
		.toString();
	}
}