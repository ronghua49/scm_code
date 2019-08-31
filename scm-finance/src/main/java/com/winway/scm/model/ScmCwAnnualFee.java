package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 年费信息管理费详情
 * <pre> 
 * 描述：年费信息管理费详情 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-28 09:38:33
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmCwAnnualFee",description = "年费信息管理费详情") 
public class ScmCwAnnualFee extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="主表ID")
	protected String masterId; 
	
	@ApiModelProperty(value="商业名称")
	protected String commerceName; 
	
	@ApiModelProperty(value="商业ID")
	protected String commerceId; 
	
	@ApiModelProperty(value="商业编号")
	protected String commerceCode; 
	
	@ApiModelProperty(value="协议签订类型")
	protected String commerceType; 
	
	@ApiModelProperty(value="商业类别")
	protected String commerceClasses; 
	
	@ApiModelProperty(value="信息直连费")
	protected Double messagePrice; 
	
	@ApiModelProperty(value="流向折扣费")
	protected Double flowDirection; 
	
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
	 * 返回 商业编号
	 * @return
	 */
	public String getCommerceCode() {
		return this.commerceCode;
	}
	
	public void setCommerceType(String commerceType) {
		this.commerceType = commerceType;
	}
	
	/**
	 * 返回 协议签订类型
	 * @return
	 */
	public String getCommerceType() {
		return this.commerceType;
	}
	
	public void setCommerceClasses(String commerceClasses) {
		this.commerceClasses = commerceClasses;
	}
	
	/**
	 * 返回 商业类别
	 * @return
	 */
	public String getCommerceClasses() {
		return this.commerceClasses;
	}
	
	public void setMessagePrice(Double messagePrice) {
		this.messagePrice = messagePrice;
	}
	
	/**
	 * 返回 信息直连费
	 * @return
	 */
	public Double getMessagePrice() {
		return this.messagePrice;
	}
	
	public void setFlowDirection(Double flowDirection) {
		this.flowDirection = flowDirection;
	}
	
	/**
	 * 返回 流向折扣费
	 * @return
	 */
	public Double getFlowDirection() {
		return this.flowDirection;
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
		.append("commerceName", this.commerceName) 
		.append("commerceId", this.commerceId) 
		.append("commerceCode", this.commerceCode) 
		.append("commerceType", this.commerceType) 
		.append("commerceClasses", this.commerceClasses) 
		.append("messagePrice", this.messagePrice) 
		.append("flowDirection", this.flowDirection) 
		.append("memo", this.memo) 
		.toString();
	}
}