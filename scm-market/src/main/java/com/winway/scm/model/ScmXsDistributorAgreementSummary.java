package com.winway.scm.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 省区协议汇总表
 * <pre> 
 * 描述：省区协议汇总表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:43
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmXsDistributorAgreementSummary",description = "省区协议汇总表") 
public class ScmXsDistributorAgreementSummary extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="商务分区")
	protected String businessDivision; 
	
	@ApiModelProperty(value="省区")
	protected String Province; 
	
	@ApiModelProperty(value="商务分区ID")
	protected String businessDivisionId; 
	
	@ApiModelProperty(value="省区ID")
	protected String provinceId; 
	
	@ApiModelProperty(value="流程ID")
	protected String approvalId; 
	
	@ApiModelProperty(value="流程状态")
	protected String approvalState; 
	
	@ApiModelProperty(value="货主ID")
	protected String ownerId; 
	
	@ApiModelProperty(value="协议单号")
	protected String agreementSummaryCode;

	 @ApiModelProperty(value="录入人")
	 protected String  createPerson;

	 @ApiModelProperty(value="录入时间")
	 @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
	 protected Date createDate;


	 public String getCreatePerson() {
		 return createPerson;
	 }

	 public void setCreatePerson(String createPerson) {
		 this.createPerson = createPerson;
	 }

	 public Date getCreateDate() {
		 return createDate;
	 }

	 public void setCreateDate(Date createDate) {
		 this.createDate = createDate;
	 }
	/**
	*分销商协议合作条款表列表
	*/
	protected List<ScmXsDistributorClause> scmXsDistributorClauseList=new ArrayList<ScmXsDistributorClause>(); 
	
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
	
	public void setProvince(String Province) {
		this.Province = Province;
	}
	
	/**
	 * 返回 省区
	 * @return
	 */
	public String getProvince() {
		return this.Province;
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
	
	public void setApprovalId(String approvalId) {
		this.approvalId = approvalId;
	}
	
	/**
	 * 返回 流程ID
	 * @return
	 */
	public String getApprovalId() {
		return this.approvalId;
	}
	
	public void setApprovalState(String approvalState) {
		this.approvalState = approvalState;
	}
	
	/**
	 * 返回 流程状态
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
	
	public void setAgreementSummaryCode(String agreementSummaryCode) {
		this.agreementSummaryCode = agreementSummaryCode;
	}
	
	/**
	 * 返回 协议单号
	 * @return
	 */
	public String getAgreementSummaryCode() {
		return this.agreementSummaryCode;
	}

	public void setScmXsDistributorClauseList(List<ScmXsDistributorClause> scmXsDistributorClauseList) {
		this.scmXsDistributorClauseList = scmXsDistributorClauseList;
	}
	
	/**
	 * 返回 分销商协议合作条款表列表
	 * @return
	 */
	public List<ScmXsDistributorClause> getScmXsDistributorClauseList() {
		return this.scmXsDistributorClauseList;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("businessDivision", this.businessDivision) 
		.append("Province", this.Province) 
		.append("businessDivisionId", this.businessDivisionId) 
		.append("provinceId", this.provinceId) 
		.append("approvalId", this.approvalId) 
		.append("approvalState", this.approvalState) 
		.append("ownerId", this.ownerId) 
		.append("agreementSummaryCode", this.agreementSummaryCode) 
		.toString();
	}
}