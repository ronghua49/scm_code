package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;


 /**
 * 商业医疗许可证
 * <pre> 
 * 描述：商业医疗许可证 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmZsjTreatLicence",description = "商业医疗许可证") 
public class ScmZsjTreatLicence extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="首营ID")
	protected String commerceFirstId; 
	
	@ApiModelProperty(value="医疗许可证")
	protected String TreatLicence; 
	
	@ApiModelProperty(value="医疗许可证有效期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date validity; 
	
	
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
	
	public void setTreatLicence(String TreatLicence) {
		this.TreatLicence = TreatLicence;
	}
	
	/**
	 * 返回 医疗许可证
	 * @return
	 */
	public String getTreatLicence() {
		return this.TreatLicence;
	}
	
	public void setValidity(java.util.Date validity) {
		this.validity = validity;
	}
	
	/**
	 * 返回 医疗许可证有效期
	 * @return
	 */
	public java.util.Date getValidity() {
		return this.validity;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("commerceFirstId", this.commerceFirstId) 
		.append("TreatLicence", this.TreatLicence) 
		.append("validity", this.validity) 
		.toString();
	}
}