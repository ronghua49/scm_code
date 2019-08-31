package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * Scm_Xs_CreditApplyRecord
 * <pre> 
 * 描述：Scm_Xs_CreditApplyRecord 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-07-16 11:39:59
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmXsCreditApplyRecord",description = "Scm_Xs_CreditApplyRecord") 
public class ScmXsCreditApplyRecord extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="商业编号")
	protected String commerceCode; 
	
	@ApiModelProperty(value="商业ID")
	protected String commerceId; 
	
	@ApiModelProperty(value="记录类型(0:大合同分配,1:日常合同,2:集中发货,3:商业回款)")
	protected String recordType; 
	
	@ApiModelProperty(value="数据ID")
	protected String dataId; 
	
	@ApiModelProperty(value="价格")
	protected Double price; 
	
	@ApiModelProperty(value="记录时间")
	protected java.util.Date recordDate; 
	
	@ApiModelProperty(value="集中发货时间")
	protected java.util.Date focusSendDate; 
	
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
	
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	
	/**
	 * 返回 记录类型(0:大合同分配,1:日常合同,2:集中发货,3:商业回款)
	 * @return
	 */
	public String getRecordType() {
		return this.recordType;
	}
	
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}
	
	/**
	 * 返回 数据ID
	 * @return
	 */
	public String getDataId() {
		return this.dataId;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	/**
	 * 返回 价格
	 * @return
	 */
	public Double getPrice() {
		return this.price;
	}
	
	public void setRecordDate(java.util.Date recordDate) {
		this.recordDate = recordDate;
	}
	
	/**
	 * 返回 记录时间
	 * @return
	 */
	public java.util.Date getRecordDate() {
		return this.recordDate;
	}
	
	public void setFocusSendDate(java.util.Date focusSendDate) {
		this.focusSendDate = focusSendDate;
	}
	
	/**
	 * 返回 集中发货时间
	 * @return
	 */
	public java.util.Date getFocusSendDate() {
		return this.focusSendDate;
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
		.append("commerceCode", this.commerceCode) 
		.append("commerceId", this.commerceId) 
		.append("recordType", this.recordType) 
		.append("dataId", this.dataId) 
		.append("price", this.price) 
		.append("recordDate", this.recordDate) 
		.append("focusSendDate", this.focusSendDate) 
		.append("ownerId", this.ownerId) 
		.toString();
	}
}