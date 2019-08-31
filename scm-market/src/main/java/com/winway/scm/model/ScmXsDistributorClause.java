package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;


 /**
 * 分销商协议合作条款表
 * <pre> 
 * 描述：分销商协议合作条款表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:43
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmXsDistributorClause",description = "分销商协议合作条款表") 
public class ScmXsDistributorClause extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="商业ID")
	protected String commerceId; 
	
	@ApiModelProperty(value="协议号")
	protected String clauseCode; 
	
	@ApiModelProperty(value="商业名称")
	protected String commerceName; 
	
	@ApiModelProperty(value="级别")
	protected String commerceLevel; 
	
	@ApiModelProperty(value="职能类型")
	protected String functionType; 
	
	@ApiModelProperty(value="是否分子公司")
	protected String isSubsidiary; 
	
	@ApiModelProperty(value="星级")
	protected String starLevel; 
	
	@ApiModelProperty(value="信息管理费")
	protected Double messageCost; 
	
	@ApiModelProperty(value="是否促销折让")
	protected String isPromotional; 
	
	@ApiModelProperty(value="协议起始日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date startDate; 
	
	@ApiModelProperty(value="协议截止日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date endDate; 
	
	@ApiModelProperty(value="汇总表ID")
	protected String distributorAgreementId; 
	
	@ApiModelProperty(value="省级平台")
	protected String provincePlatformId; 
	
	@ApiModelProperty(value="省级平台")
	protected String provincePlatform; 
	
	@ApiModelProperty(value="上游商业")
	protected String upCommerceId; 
	
	@ApiModelProperty(value="上游商业")
	protected String upCommerce; 
	
	@ApiModelProperty(value="备注")
	protected String memo; 
	
	@ApiModelProperty(value="商务分区")
	protected String businessDivision; 
	
	@ApiModelProperty(value="商务分区ID")
	protected String businessDivisionId; 
	
	@ApiModelProperty(value="省区ID")
	protected String provinceId; 
	
	@ApiModelProperty(value="省区")
	protected String province; 
	
	@ApiModelProperty(value="审批ID")
	protected String approvalId; 
	
	@ApiModelProperty(value="审批状态")
	protected String approvalState; 
	
	@ApiModelProperty(value="货主ID")
	protected String ownerId; 
	
	@ApiModelProperty(value="审批状态中文")
	protected String approvalStateStr; 
	
	@ApiModelProperty(value="商业编号")
	protected String commerceCode; 
	
	@ApiModelProperty(value="商业认可状态")
	protected String scm_zsj_commerceState; 
	
	@ApiModelProperty(value="积分")
	protected int integral; 
	
	@ApiModelProperty(value="编号")
	protected String agreementSummaryCode; 
	
	@ApiModelProperty(value="年度")
	protected String year; 

	@ApiModelProperty(value="认可状态")
	protected String acceptState; 
	
	@ApiModelProperty(value="认可状态中文")
	protected String acceptStateStr; 
	
	@ApiModelProperty(value="开始年")
	protected String startYear; 
	
	@ApiModelProperty(value="结束年")
	protected String endYear;

	 @ApiModelProperty(value="是否签合")
	 protected String isSign;

	 @ApiModelProperty(value="注册地址")
	 protected String registerAddress;

	 @ApiModelProperty(value="商务大区code")
	 protected String businessCode;

	 @ApiModelProperty(value="省区code")
	 protected String provinceCode;

	 public String getBusinessCode() {
		 return businessCode;
	 }

	 public void setBusinessCode(String businessCode) {
		 this.businessCode = businessCode;
	 }

	 public String getProvinceCode() {
		 return provinceCode;
	 }

	 public void setProvinceCode(String provinceCode) {
		 this.provinceCode = provinceCode;
	 }

	 public String getIsSign() {
		 return isSign;
	 }

	 public void setIsSign(String isSign) {
		 this.isSign = isSign;
	 }

	 public String getRegisterAddress() {
		 return registerAddress;
	 }

	 public void setRegisterAddress(String registerAddress) {
		 this.registerAddress = registerAddress;
	 }

	 public String getStartYear() {
		return startYear;
	}

	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}

	public String getEndYear() {
		return endYear;
	}

	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}
	
	public String getProvincePlatform() {
		return provincePlatform;
	}

	public void setProvincePlatform(String provincePlatform) {
		this.provincePlatform = provincePlatform;
	}

	public String getUpCommerce() {
		return upCommerce;
	}

	public void setUpCommerce(String upCommerce) {
		this.upCommerce = upCommerce;
	}

	public String getAcceptState() {
		return acceptState;
	}

	public void setAcceptState(String acceptState) {
		this.acceptState = acceptState;
	}

	public String getAcceptStateStr() {
		return acceptStateStr;
	}

	public void setAcceptStateStr(String acceptStateStr) {
		this.acceptStateStr = acceptStateStr;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getAgreementSummaryCode() {
		return agreementSummaryCode;
	}

	public void setAgreementSummaryCode(String agreementSummaryCode) {
		this.agreementSummaryCode = agreementSummaryCode;
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
	
	public void setClauseCode(String clauseCode) {
		this.clauseCode = clauseCode;
	}
	
	/**
	 * 返回 协议号
	 * @return
	 */
	public String getClauseCode() {
		return this.clauseCode;
	}
	
	public void setCommerceName(String commerceName) {
		this.commerceName = commerceName;
	}
	
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * 返回 商业名称
	 * @return
	 */
	public String getCommerceName() {
		return this.commerceName;
	}
	
	public void setCommerceLevel(String commerceLevel) {
		this.commerceLevel = commerceLevel;
	}
	
	/**
	 * 返回 级别
	 * @return
	 */
	public String getCommerceLevel() {
		return this.commerceLevel;
	}
	
	public void setFunctionType(String functionType) {
		this.functionType = functionType;
	}
	
	/**
	 * 返回 职能类型
	 * @return
	 */
	public String getFunctionType() {
		return this.functionType;
	}
	
	public void setIsSubsidiary(String isSubsidiary) {
		this.isSubsidiary = isSubsidiary;
	}
	
	/**
	 * 返回 是否分子公司
	 * @return
	 */
	public String getIsSubsidiary() {
		return this.isSubsidiary;
	}
	
	public void setStarLevel(String starLevel) {
		this.starLevel = starLevel;
	}
	
	/**
	 * 返回 星级
	 * @return
	 */
	public String getStarLevel() {
		return this.starLevel;
	}
	
	public void setMessageCost(Double messageCost) {
		this.messageCost = messageCost;
	}
	
	public String getCommerceCode() {
		return commerceCode;
	}

	public void setCommerceCode(String commerceCode) {
		this.commerceCode = commerceCode;
	}

	public String getScm_zsj_commerceState() {
		return scm_zsj_commerceState;
	}

	public void setScm_zsj_commerceState(String scm_zsj_commerceState) {
		this.scm_zsj_commerceState = scm_zsj_commerceState;
	}

	public int getIntegral() {
		return integral;
	}

	public void setIntegral(int integral) {
		this.integral = integral;
	}

	/**
	 * 返回 信息管理费
	 * @return
	 */
	public Double getMessageCost() {
		return this.messageCost;
	}
	
	public void setIsPromotional(String isPromotional) {
		this.isPromotional = isPromotional;
	}
	
	/**
	 * 返回 是否促销折让
	 * @return
	 */
	public String getIsPromotional() {
		return this.isPromotional;
	}
	
	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * 返回 协议起始日期
	 * @return
	 */
	public java.util.Date getStartDate() {
		return this.startDate;
	}
	
	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * 返回 协议截止日期
	 * @return
	 */
	public java.util.Date getEndDate() {
		return this.endDate;
	}
	
	public void setProvincePlatformId(String provincePlatformId) {
		this.provincePlatformId = provincePlatformId;
	}
	
	/**
	 * 返回 省级平台
	 * @return
	 */
	public String getProvincePlatformId() {
		return this.provincePlatformId;
	}
	
	public void setUpCommerceId(String upCommerceId) {
		this.upCommerceId = upCommerceId;
	}
	
	/**
	 * 返回 上游商业
	 * @return
	 */
	public String getUpCommerceId() {
		return this.upCommerceId;
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
	
	public void setDistributorAgreementId(String distributorAgreementId) {
		this.distributorAgreementId = distributorAgreementId;
	}
	
	/**
	 * 返回 汇总表ID
	 * @return
	 */
	public String getDistributorAgreementId() {
		return this.distributorAgreementId;
	}
	
	public String getBusinessDivision() {
		return businessDivision;
	}

	public void setBusinessDivision(String businessDivision) {
		this.businessDivision = businessDivision;
	}

	public String getBusinessDivisionId() {
		return businessDivisionId;
	}

	public void setBusinessDivisionId(String businessDivisionId) {
		this.businessDivisionId = businessDivisionId;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
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

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getApprovalStateStr() {
		return approvalStateStr;
	}

	public void setApprovalStateStr(String approvalStateStr) {
		this.approvalStateStr = approvalStateStr;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("commerceId", this.commerceId) 
		.append("clauseCode", this.clauseCode) 
		.append("commerceName", this.commerceName) 
		.append("commerceLevel", this.commerceLevel) 
		.append("functionType", this.functionType) 
		.append("isSubsidiary", this.isSubsidiary) 
		.append("starLevel", this.starLevel) 
		.append("messageCost", this.messageCost) 
		.append("isPromotional", this.isPromotional) 
		.append("startDate", this.startDate) 
		.append("endDate", this.endDate) 
		.append("provincePlatformId", this.provincePlatformId) 
		.append("upCommerceId", this.upCommerceId) 
		.append("memo", this.memo) 
		.append("distributorAgreementId", this.distributorAgreementId) 
		.toString();
	}
}