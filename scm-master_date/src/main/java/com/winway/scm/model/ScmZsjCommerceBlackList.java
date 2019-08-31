package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;


 /**
 * 商业黑名单表
 * <pre> 
 * 描述：商业黑名单表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-18 14:36:01
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmZsjCommerceBlackList",description = "商业黑名单表") 
public class ScmZsjCommerceBlackList extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="申请单号")
	protected String approvalCode; 
	
	@ApiModelProperty(value="商务分区ID")
	protected String businessDivisionId; 
	
	@ApiModelProperty(value="商务分区")
	protected String businessDivision; 
	
	@ApiModelProperty(value="省区ID")
	protected String provinceId; 
	
	@ApiModelProperty(value="省区")
	protected String province; 
	
	@ApiModelProperty(value="商业编号")
	protected String commerceCode; 
	
	@ApiModelProperty(value="商业名称")
	protected String commerceName; 
	
	@ApiModelProperty(value="分类(0:窜货,1:虚假流向)")
	protected String classify; 

	@ApiModelProperty(value="处罚原因")
	protected String penaltyReason; 
	
	@ApiModelProperty(value="禁销品种")
	protected String bannedBreed; 
	
	@ApiModelProperty(value="申请原因")
	protected String approvalMemo; 
	
	@ApiModelProperty(value="审批ID")
	protected String approvalId; 
	
	@ApiModelProperty(value="审批状态")
	protected String approvalState; 
	
	@ApiModelProperty(value="货主ID")
	protected String ownerId; 
	
	@ApiModelProperty(value="申请日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date approvalDate; 
	
	@ApiModelProperty(value="操作人")
	protected String operatorName;

	 public String getOperatorName() {
		 return operatorName;
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
	
	public void setApprovalCode(String approvalCode) {
		this.approvalCode = approvalCode;
	}
	
	/**
	 * 返回 申请单号
	 * @return
	 */
	public String getApprovalCode() {
		return this.approvalCode;
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
	
	public void setClassify(String classify) {
		this.classify = classify;
	}
	
	/**
	 * 返回 分类(0:窜货,1:虚假流向)
	 * @return
	 */
	public String getClassify() {
		return this.classify;
	}
	
	public void setPenaltyReason(String penaltyReason) {
		this.penaltyReason = penaltyReason;
	}
	
	/**
	 * 返回 处罚原因
	 * @return
	 */
	public String getPenaltyReason() {
		return this.penaltyReason;
	}
	
	public void setBannedBreed(String bannedBreed) {
		this.bannedBreed = bannedBreed;
	}
	
	/**
	 * 返回 禁销品种
	 * @return
	 */
	public String getBannedBreed() {
		return this.bannedBreed;
	}
	
	public void setApprovalMemo(String approvalMemo) {
		this.approvalMemo = approvalMemo;
	}
	
	/**
	 * 返回 申请原因
	 * @return
	 */
	public String getApprovalMemo() {
		return this.approvalMemo;
	}
	
	public void setApprovalId(String approvalId) {
		this.approvalId = approvalId;
	}
	
	/**
	 * 返回 审批ID
	 * @return
	 */
	public String getApprovalId() {
		return this.approvalId;
	}
	
	public void setApprovalState(String approvalState) {
		this.approvalState = approvalState;
	}
	
	/**
	 * 返回 审批状态
	 * @return
	 */
	public String getApprovalState() {
		return this.approvalState;
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
	
	public void setApprovalDate(java.util.Date approvalDate) {
		this.approvalDate = approvalDate;
	}
	
	/**
	 * 返回 申请日期
	 * @return
	 */
	public java.util.Date getApprovalDate() {
		return this.approvalDate;
	}
	
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("approvalCode", this.approvalCode) 
		.append("businessDivisionId", this.businessDivisionId) 
		.append("businessDivision", this.businessDivision) 
		.append("provinceId", this.provinceId) 
		.append("province", this.province) 
		.append("commerceCode", this.commerceCode) 
		.append("commerceName", this.commerceName) 
		.append("classify", this.classify) 
		.append("penaltyReason", this.penaltyReason) 
		.append("bannedBreed", this.bannedBreed) 
		.append("approvalMemo", this.approvalMemo) 
		.append("approvalId", this.approvalId) 
		.append("approvalState", this.approvalState) 
		.append("ownerId", this.ownerId) 
		.append("approvalDate", this.approvalDate) 
		.append("operatorName", this.operatorName) 
		.toString();
	}
}