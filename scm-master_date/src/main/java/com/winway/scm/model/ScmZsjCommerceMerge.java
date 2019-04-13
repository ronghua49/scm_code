package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 商业合并表
 * <pre> 
 * 描述：商业合并表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-18 14:36:01
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmZsjCommerceMerge",description = "商业合并表") 
public class ScmZsjCommerceMerge extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="主商业ID")
	protected String hostCommerceId; 
	
	@ApiModelProperty(value="主商业编码")
	protected String hostCommerceCode; 
	
	@ApiModelProperty(value="主商业名称")
	protected String hostCommerceName; 
	
	@ApiModelProperty(value="副商业ID")
	protected String viceCommerceId; 
	
	@ApiModelProperty(value="副商业编码")
	protected String viceCommerceCode; 
	
	@ApiModelProperty(value="商业名称")
	protected String viceCommerceName; 
	
	@ApiModelProperty(value="申请原因")
	protected String approvalMemo; 
	
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
	
	public void setHostCommerceId(String hostCommerceId) {
		this.hostCommerceId = hostCommerceId;
	}
	
	/**
	 * 返回 主商业ID
	 * @return
	 */
	public String getHostCommerceId() {
		return this.hostCommerceId;
	}
	
	public void setHostCommerceCode(String hostCommerceCode) {
		this.hostCommerceCode = hostCommerceCode;
	}
	
	/**
	 * 返回 主商业编码
	 * @return
	 */
	public String getHostCommerceCode() {
		return this.hostCommerceCode;
	}
	
	public void setHostCommerceName(String hostCommerceName) {
		this.hostCommerceName = hostCommerceName;
	}
	
	/**
	 * 返回 主商业名称
	 * @return
	 */
	public String getHostCommerceName() {
		return this.hostCommerceName;
	}
	
	public void setViceCommerceId(String viceCommerceId) {
		this.viceCommerceId = viceCommerceId;
	}
	
	/**
	 * 返回 副商业ID
	 * @return
	 */
	public String getViceCommerceId() {
		return this.viceCommerceId;
	}
	
	public void setViceCommerceCode(String viceCommerceCode) {
		this.viceCommerceCode = viceCommerceCode;
	}
	
	/**
	 * 返回 副商业编码
	 * @return
	 */
	public String getViceCommerceCode() {
		return this.viceCommerceCode;
	}
	
	public void setViceCommerceName(String viceCommerceName) {
		this.viceCommerceName = viceCommerceName;
	}
	
	/**
	 * 返回 商业名称
	 * @return
	 */
	public String getViceCommerceName() {
		return this.viceCommerceName;
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
		.append("hostCommerceId", this.hostCommerceId) 
		.append("hostCommerceCode", this.hostCommerceCode) 
		.append("hostCommerceName", this.hostCommerceName) 
		.append("viceCommerceId", this.viceCommerceId) 
		.append("viceCommerceCode", this.viceCommerceCode) 
		.append("viceCommerceName", this.viceCommerceName) 
		.append("approvalMemo", this.approvalMemo) 
		.append("approvalId", this.approvalId) 
		.append("approvalState", this.approvalState) 
		.append("ownerId", this.ownerId) 
		.append("operatorName", this.operatorName) 
		.append("operatorId", this.operatorId) 
		.toString();
	}
}