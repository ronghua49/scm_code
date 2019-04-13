package com.winway.scm.model;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.base.model.BaseModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


 /**
 * 商业认可状态总表
 * <pre> 
 * 描述：商业认可状态总表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-18 14:36:01
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmZsjCommerceAcceptStateAgther",description = "商业认可状态总表") 
public class ScmZsjCommerceAcceptStateAgther extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="申请单号")
	protected String approvalCode; 
	
	@ApiModelProperty(value="审批ID")
	protected String approvalId; 
	
	@ApiModelProperty(value="审批状态")
	protected String approvalState; 
	
	@ApiModelProperty(value="货主ID")
	protected String ownerId; 
	
	@ApiModelProperty(value="操作人")
	protected String operatorName; 
	
	@ApiModelProperty(value="操作人ID")
	protected String operatorId; 
	
	@ApiModelProperty(value="商业认可列表")
	protected List<ScmZsjCommerceAcceptState> commerceAcceptStateList; 
	
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
	
	public void setApprovalId(String approvalId) {
		this.approvalId = approvalId;
	}


	public List<ScmZsjCommerceAcceptState> getCommerceAcceptStateList() {
		return commerceAcceptStateList;
	}

	public void setCommerceAcceptStateList(List<ScmZsjCommerceAcceptState> commerceAcceptStateList) {
		this.commerceAcceptStateList = commerceAcceptStateList;
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
	
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	
	/**
	 * 返回 操作人
	 * @return
	 */
	public String getOperatorName() {
		return this.operatorName;
	}
	
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	
	/**
	 * 返回 操作人ID
	 * @return
	 */
	public String getOperatorId() {
		return this.operatorId;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("approvalCode", this.approvalCode) 
		.append("approvalId", this.approvalId) 
		.append("approvalState", this.approvalState) 
		.append("ownerId", this.ownerId) 
		.append("operatorName", this.operatorName) 
		.append("operatorId", this.operatorId) 
		.toString();
	}
}