package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 商业责任人表
 * <pre> 
 * 描述：商业责任人表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-18 14:36:01
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmZsjCommerceDutyOfficer",description = "商业责任人表") 
public class ScmZsjCommerceDutyOfficer extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="商业ID")
	protected String commerceId; 
	
	@ApiModelProperty(value="商业编码")
	protected String commerceCode; 
	
	@ApiModelProperty(value="责任人ID")
	protected String dutyOfficerId; 
	
	@ApiModelProperty(value="责任人")
	protected String dutyOfficer;

	 @ApiModelProperty(value="新责任人")
	 protected String newDutyOfficer;

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

	 @ApiModelProperty(value="是否生效(0:未生效，1:生效)")
	 protected String iseffect="1";

	 @ApiModelProperty(value="备注")
	 protected String memo;

	 public String getNewDutyOfficer() {
		 return newDutyOfficer;
	 }

	 public void setNewDutyOfficer(String newDutyOfficer) {
		 this.newDutyOfficer = newDutyOfficer;
	 }

	 public String getMemo() {
		 return memo;
	 }

	 public void setMemo(String memo) {
		 this.memo = memo;
	 }

	 public String getIseffect() {
		 return iseffect;
	 }

	 public void setIseffect(String iseffect) {
		 this.iseffect = iseffect;
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
	 * 返回 商业编码
	 * @return
	 */
	public String getCommerceCode() {
		return this.commerceCode;
	}
	
	public void setDutyOfficerId(String dutyOfficerId) {
		this.dutyOfficerId = dutyOfficerId;
	}
	
	/**
	 * 返回 责任人ID
	 * @return
	 */
	public String getDutyOfficerId() {
		return this.dutyOfficerId;
	}
	
	public void setDutyOfficer(String dutyOfficer) {
		this.dutyOfficer = dutyOfficer;
	}
	
	/**
	 * 返回 责任人
	 * @return
	 */
	public String getDutyOfficer() {
		return this.dutyOfficer;
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
		.append("commerceId", this.commerceId) 
		.append("commerceCode", this.commerceCode) 
		.append("dutyOfficerId", this.dutyOfficerId) 
		.append("dutyOfficer", this.dutyOfficer) 
		.append("approvalId", this.approvalId) 
		.append("approvalState", this.approvalState) 
		.append("ownerId", this.ownerId) 
		.append("operatorName", this.operatorName) 
		.append("operatorId", this.operatorId) 
		.toString();
	}
}