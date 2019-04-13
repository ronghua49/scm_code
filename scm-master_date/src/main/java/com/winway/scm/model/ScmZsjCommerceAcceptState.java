package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;


 /**
 * 商业认可状态表
 * <pre> 
 * 描述：商业认可状态表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-18 14:36:01
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmZsjCommerceAcceptState",description = "商业认可状态表") 
public class ScmZsjCommerceAcceptState extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="总表ID")
	protected String agtherId; 
	
	@ApiModelProperty(value="商业ID")
	protected String commerceId; 
	
	@ApiModelProperty(value="认可状态(0:认可,1:考察中,2:终止)")
	protected String acceptState; 
	
	@ApiModelProperty(value="开始日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	protected java.util.Date startDate; 
	
	@ApiModelProperty(value="结束日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	protected java.util.Date endDate; 
	
	@ApiModelProperty(value="备注")
	protected String memo; 
	
	@ApiModelProperty(value="是否有效 0:无效,1:有效")
	protected String isEffect; 
	
	@ApiModelProperty(value="v_认可状态中文")
	protected String acceptStateStr;
	@ApiModelProperty(value="v_认可状态(0:认可,1:考察中,2:终止)")
	protected String approvalCode;
	@ApiModelProperty(value="v_审批ID")
	protected String approvalId;
	@ApiModelProperty(value="v_审批状态")
	protected String approvalState;
	@ApiModelProperty(value="v_审批状态中文(0:未提交,1:审批中,2:已通过)")
	protected String approvalStateStr;
	@ApiModelProperty(value="v_货主ID")
	protected String ownerId;
	@ApiModelProperty(value="v_操作人")
	protected String operatorName;
	@ApiModelProperty(value="v_操作人ID")
	protected String operatorId;
	
	@ApiModelProperty(value="v_商务分区ID")
	protected String businessDivisionId;
	@ApiModelProperty(value="v_商务分区")
	protected String businessDivision;
	@ApiModelProperty(value="v_省区ID")
	protected String provinceId;
	@ApiModelProperty(value="v_省区")
	protected String province;
	@ApiModelProperty(value="v_商业名称")
	protected String commerceName;
	@ApiModelProperty(value="v_商业编号")
	protected String commerceCode;
	@ApiModelProperty(value="v_责任人")
	protected String dutyOfficer;
	@ApiModelProperty(value="v_开户日期")
	protected String enterDate;
	
	public String getIsEffect() {
		return isEffect;
	}

	public void setIsEffect(String isEffect) {
		this.isEffect = isEffect;
	}

	public String getBusinessDivisionId() {
		return businessDivisionId;
	}

	public void setBusinessDivisionId(String businessDivisionId) {
		this.businessDivisionId = businessDivisionId;
	}

	public String getBusinessDivision() {
		return businessDivision;
	}

	public void setBusinessDivision(String businessDivision) {
		this.businessDivision = businessDivision;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCommerceName() {
		return commerceName;
	}

	public void setCommerceName(String commerceName) {
		this.commerceName = commerceName;
	}

	public String getDutyOfficer() {
		return dutyOfficer;
	}

	public void setDutyOfficer(String dutyOfficer) {
		this.dutyOfficer = dutyOfficer;
	}

	public String getEnterDate() {
		return enterDate;
	}

	public void setEnterDate(String enterDate) {
		this.enterDate = enterDate;
	}

	public String getAcceptStateStr() {
		return acceptStateStr;
	}

	public void setAcceptStateStr(String acceptStateStr) {
		this.acceptStateStr = acceptStateStr;
	}

	public String getApprovalCode() {
		return approvalCode;
	}

	public void setApprovalCode(String approvalCode) {
		this.approvalCode = approvalCode;
	}

	public String getApprovalId() {
		return approvalId;
	}

	public void setApprovalId(String approvalId) {
		this.approvalId = approvalId;
	}

	public String getApprovalState() {
		return approvalState;
	}

	public void setApprovalState(String approvalState) {
		this.approvalState = approvalState;
	}

	public String getApprovalStateStr() {
		return approvalStateStr;
	}

	public void setApprovalStateStr(String approvalStateStr) {
		this.approvalStateStr = approvalStateStr;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
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
	
	public void setAgtherId(String agtherId) {
		this.agtherId = agtherId;
	}
	
	/**
	 * 返回 总表ID
	 * @return
	 */
	public String getAgtherId() {
		return this.agtherId;
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
	
	public java.util.Date getEndDate() {
		return endDate;
	}

	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	public java.util.Date getStartDate() {
		return startDate;
	}

	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
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
		.append("agtherId", this.agtherId) 
		.append("commerceId", this.commerceId) 
		.append("commerceCode", this.commerceCode) 
		.append("acceptState", this.acceptState) 
		.append("startDate", this.startDate) 
		.append("memo", this.memo) 
		.toString();
	}
}