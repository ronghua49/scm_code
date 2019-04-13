package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;


 /**
 * 商业认可状态暂存表
 * <pre> 
 * 描述：商业认可状态暂存表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-18 14:36:01
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmZsjCommerceAcceptStateTS",description = "商业认可状态暂存表") 
public class ScmZsjCommerceAcceptStateTS extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="主商业ID")
	protected String commerceId; 
	
	@ApiModelProperty(value="主商业编码")
	protected String commerceCode; 
	
	@ApiModelProperty(value="认可状态(0:认可,1:考察中,2:终止)")
	protected String acceptState; 
	
	@ApiModelProperty(value="生效日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date effectiveDate; 
	
	@ApiModelProperty(value="备注")
	protected String memo; 
	
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
	
	public void setCommerceId(String commerceId) {
		this.commerceId = commerceId;
	}
	
	/**
	 * 返回 主商业ID
	 * @return
	 */
	public String getCommerceId() {
		return this.commerceId;
	}
	
	public void setCommerceCode(String commerceCode) {
		this.commerceCode = commerceCode;
	}
	
	/**
	 * 返回 主商业编码
	 * @return
	 */
	public String getCommerceCode() {
		return this.commerceCode;
	}
	
	public void setAcceptState(String acceptState) {
		this.acceptState = acceptState;
	}
	
	/**
	 * 返回 认可状态(0:认可,1:考察中,2:终止)
	 * @return
	 */
	public String getAcceptState() {
		return this.acceptState;
	}
	
	public void setEffectiveDate(java.util.Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	
	/**
	 * 返回 生效日期
	 * @return
	 */
	public java.util.Date getEffectiveDate() {
		return this.effectiveDate;
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
		.append("commerceId", this.commerceId) 
		.append("commerceCode", this.commerceCode) 
		.append("acceptState", this.acceptState) 
		.append("effectiveDate", this.effectiveDate) 
		.append("memo", this.memo) 
		.append("ownerId", this.ownerId) 
		.toString();
	}
}