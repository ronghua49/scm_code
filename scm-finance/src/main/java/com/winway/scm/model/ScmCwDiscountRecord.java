package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;


 /**
 * 票折记录表
 * <pre> 
 * 描述：票折记录表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-23 18:19:25
 * 版权：美达开发小组
 * </pre>
 */
 /**
 * @author Administrator
 *
 */
@ApiModel(value = "ScmCwDiscountRecord",description = "票折记录表") 
public class ScmCwDiscountRecord extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="票折ID")
	protected String discountId; 
	
	@ApiModelProperty(value="票折编号")
	protected String discountCode; 
	
	@ApiModelProperty(value="发票编号")
	protected String invoiceCode; 
	
	@ApiModelProperty(value="发票iD")
	protected String invoiceId; 
	
	@ApiModelProperty(value="票折商业")
	protected String commerceName; 
	
	@ApiModelProperty(value="折扣金额")
	protected Double discountPrice; 
	
	@ApiModelProperty(value="票折时间")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date discountDate; 
	
	public String getCommerceName() {
		return commerceName;
	}

	public void setCommerceName(String commerceName) {
		this.commerceName = commerceName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
	
	public void setDiscountId(String discountId) {
		this.discountId = discountId;
	}
	
	/**
	 * 返回 票折ID
	 * @return
	 */
	public String getDiscountId() {
		return this.discountId;
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
	
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	
	/**
	 * 返回 发票编号
	 * @return
	 */
	public String getInvoiceCode() {
		return this.invoiceCode;
	}
	
	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}
	
	/**
	 * 返回 发票iD
	 * @return
	 */
	public String getInvoiceId() {
		return this.invoiceId;
	}
	
	public void setDiscountPrice(Double discountPrice) {
		this.discountPrice = discountPrice;
	}
	
	/**
	 * 返回 折扣金额
	 * @return
	 */
	public Double getDiscountPrice() {
		return this.discountPrice;
	}
	
	public void setDiscountDate(java.util.Date discountDate) {
		this.discountDate = discountDate;
	}
	
	/**
	 * 返回 票折时间
	 * @return
	 */
	public java.util.Date getDiscountDate() {
		return this.discountDate;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("discountId", this.discountId) 
		.append("discountCode", this.discountCode) 
		.append("invoiceCode", this.invoiceCode) 
		.append("invoiceId", this.invoiceId) 
		.append("discountPrice", this.discountPrice) 
		.append("discountDate", this.discountDate) 
		.toString();
	}
}