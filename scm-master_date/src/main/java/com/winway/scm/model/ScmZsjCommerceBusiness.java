package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;


 /**
 * 商业营业执照信息
 * <pre> 
 * 描述：商业营业执照信息 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmZsjCommerceBusiness",description = "商业营业执照信息") 
public class ScmZsjCommerceBusiness extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="首营ID")
	protected String commerceFirstId; 
	
	@ApiModelProperty(value="注册号")
	protected String register; 
	
	@ApiModelProperty(value="营业执照有效期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date licenseValidity; 
	
	@ApiModelProperty(value="法定代表人")
	protected String legalPersion; 
	
	@ApiModelProperty(value="注册资本(万元)")
	protected String registerPrice; 
	
	@ApiModelProperty(value="企业类型")
	protected String commerceType; 
	
	@ApiModelProperty(value="年度报表有效期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date yearReportValidity; 
	
	
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
	
	public void setCommerceFirstId(String commerceFirstId) {
		this.commerceFirstId = commerceFirstId;
	}
	
	/**
	 * 返回 首营ID
	 * @return
	 */
	public String getCommerceFirstId() {
		return this.commerceFirstId;
	}
	
	public void setRegister(String register) {
		this.register = register;
	}
	
	/**
	 * 返回 注册号
	 * @return
	 */
	public String getRegister() {
		return this.register;
	}
	
	public void setLicenseValidity(java.util.Date licenseValidity) {
		this.licenseValidity = licenseValidity;
	}
	
	/**
	 * 返回 营业执照有效期
	 * @return
	 */
	public java.util.Date getLicenseValidity() {
		return this.licenseValidity;
	}
	
	public void setLegalPersion(String legalPersion) {
		this.legalPersion = legalPersion;
	}
	
	/**
	 * 返回 法定代表人
	 * @return
	 */
	public String getLegalPersion() {
		return this.legalPersion;
	}
	
	public void setRegisterPrice(String registerPrice) {
		this.registerPrice = registerPrice;
	}
	
	/**
	 * 返回 注册资本(万元)
	 * @return
	 */
	public String getRegisterPrice() {
		return this.registerPrice;
	}
	
	public void setCommerceType(String commerceType) {
		this.commerceType = commerceType;
	}
	
	/**
	 * 返回 企业类型
	 * @return
	 */
	public String getCommerceType() {
		return this.commerceType;
	}
	
	public void setYearReportValidity(java.util.Date yearReportValidity) {
		this.yearReportValidity = yearReportValidity;
	}
	
	/**
	 * 返回 年度报表有效期
	 * @return
	 */
	public java.util.Date getYearReportValidity() {
		return this.yearReportValidity;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("commerceFirstId", this.commerceFirstId) 
		.append("register", this.register) 
		.append("licenseValidity", this.licenseValidity) 
		.append("legalPersion", this.legalPersion) 
		.append("registerPrice", this.registerPrice) 
		.append("commerceType", this.commerceType) 
		.append("yearReportValidity", this.yearReportValidity) 
		.toString();
	}
}