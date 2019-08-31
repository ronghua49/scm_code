package com.winway.scm.vo;

import io.swagger.annotations.ApiModelProperty;

public class ScmCwDistributorAgreementVo {

	@ApiModelProperty(value = "id")
	protected String id;

	@ApiModelProperty(value = "商业ID")
	protected String commerceId;

	@ApiModelProperty(value = "协议号")
	protected String clauseCode;

	@ApiModelProperty(value = "商业名称")
	protected String commerceName;

	@ApiModelProperty(value = "商业编号")
	protected String commerceCode;

	@ApiModelProperty(value = "信息管理费")
	protected Double messageCost;

	@ApiModelProperty(value = "是否促销折让")
	protected String isPromotional;

	@ApiModelProperty(value = "省级平台id")
	protected String provincePlatformId;
	
	@ApiModelProperty(value = "省级平台编号")
	protected String provincePlatformCode;

	@ApiModelProperty(value = "省级平台")
	protected String provincePlatform;

	@ApiModelProperty(value = "上游商业id")
	protected String upCommerceId;
	
	@ApiModelProperty(value = "上游商业编号")
	protected String upCommerceCode;

	@ApiModelProperty(value = "上游商业")
	protected String upCommerce;

	@ApiModelProperty(value = "备注")
	protected String memo;

	@ApiModelProperty(value = "审批状态")
	protected String approvalState;

	@ApiModelProperty(value = "货主ID")
	protected String ownerId;

	@ApiModelProperty(value = "认可状态")
	protected String acceptState;

	@ApiModelProperty(value = "认可状态中文")
	protected String acceptStateStr;

	@ApiModelProperty(value = "开始年")
	protected String startYear;

	@ApiModelProperty(value = "结束年")
	protected String endYear;
	
	@ApiModelProperty(value = "积分")
	protected int integral;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProvincePlatformCode() {
		return provincePlatformCode;
	}

	public void setProvincePlatformCode(String provincePlatformCode) {
		this.provincePlatformCode = provincePlatformCode;
	}

	public String getUpCommerceCode() {
		return upCommerceCode;
	}

	public void setUpCommerceCode(String upCommerceCode) {
		this.upCommerceCode = upCommerceCode;
	}

	public int getIntegral() {
		return integral;
	}

	public void setIntegral(int integral) {
		this.integral = integral;
	}

	public String getCommerceCode() {
		return commerceCode;
	}

	public void setCommerceCode(String commerceCode) {
		this.commerceCode = commerceCode;
	}

	public String getCommerceId() {
		return commerceId;
	}

	public void setCommerceId(String commerceId) {
		this.commerceId = commerceId;
	}

	public String getClauseCode() {
		return clauseCode;
	}

	public void setClauseCode(String clauseCode) {
		this.clauseCode = clauseCode;
	}

	public String getCommerceName() {
		return commerceName;
	}

	public void setCommerceName(String commerceName) {
		this.commerceName = commerceName;
	}

	public Double getMessageCost() {
		return messageCost;
	}

	public void setMessageCost(Double messageCost) {
		this.messageCost = messageCost;
	}

	public String getIsPromotional() {
		return isPromotional;
	}

	public void setIsPromotional(String isPromotional) {
		this.isPromotional = isPromotional;
	}

	public String getProvincePlatformId() {
		return provincePlatformId;
	}

	public void setProvincePlatformId(String provincePlatformId) {
		this.provincePlatformId = provincePlatformId;
	}

	public String getProvincePlatform() {
		return provincePlatform;
	}

	public void setProvincePlatform(String provincePlatform) {
		this.provincePlatform = provincePlatform;
	}

	public String getUpCommerceId() {
		return upCommerceId;
	}

	public void setUpCommerceId(String upCommerceId) {
		this.upCommerceId = upCommerceId;
	}

	public String getUpCommerce() {
		return upCommerce;
	}

	public void setUpCommerce(String upCommerce) {
		this.upCommerce = upCommerce;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

}
