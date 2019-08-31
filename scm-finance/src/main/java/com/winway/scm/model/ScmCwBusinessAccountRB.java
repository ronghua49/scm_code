package com.winway.scm.model;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;


 /**
 * 经销商RB核算总表
 * <pre> 
 * 描述：经销商RB核算总表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-09 18:18:05
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmCwBusinessAccountRB",description = "经销商RB核算总表") 
public class ScmCwBusinessAccountRB extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="商务分区")
	protected String businessDivision; 
	
	@ApiModelProperty(value="省区")
	protected String province; 
	
	@ApiModelProperty(value="商务分区ID")
	protected String businessDivisionId; 
	
	@ApiModelProperty(value="省区ID")
	protected String provinceId; 
	
	@ApiModelProperty(value="年份")
	protected String year; 
	
	@ApiModelProperty(value="季度")
	protected String quarter; 
	
	@ApiModelProperty(value="税点")
	protected Double taxPoint; 
	
	@ApiModelProperty(value="货主ID")
	protected String ownerId; 
	
	@ApiModelProperty(value="创建人")
	protected String createPsersion; 
	
	@ApiModelProperty(value="创建时间")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date createDate; 
	
	@ApiModelProperty(value="审批状态")
	protected String approvalState; 
	
	@ApiModelProperty(value="审批ID")
	protected String approvalId; 
	
	@ApiModelProperty(value="财务确认(0:未确认,1:已确认)")
	protected String financeAffirm; 
	
	/**
	*经销商RB核算付款折扣列表
	*/
	protected List<ScmCwBAPaymentDiscount> scmCwBAPaymentDiscountList=new ArrayList<ScmCwBAPaymentDiscount>(); 
	/**
	*经销商RB核算储运折扣列表
	*/
	protected List<ScmCwBAStorageDiscount> scmCwBAStorageDiscountList=new ArrayList<ScmCwBAStorageDiscount>(); 
	
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
	
	public void setYear(String year) {
		this.year = year;
	}
	
	/**
	 * 返回 年份
	 * @return
	 */
	public String getYear() {
		return this.year;
	}
	
	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}
	
	/**
	 * 返回 季度
	 * @return
	 */
	public String getQuarter() {
		return this.quarter;
	}
	
	public void setTaxPoint(Double taxPoint) {
		this.taxPoint = taxPoint;
	}
	
	/**
	 * 返回 税点
	 * @return
	 */
	public Double getTaxPoint() {
		return this.taxPoint;
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
	
	public void setCreatePsersion(String createPsersion) {
		this.createPsersion = createPsersion;
	}
	
	/**
	 * 返回 创建人
	 * @return
	 */
	public String getCreatePsersion() {
		return this.createPsersion;
	}
	
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 返回 创建时间
	 * @return
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
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
	
	public void setFinanceAffirm(String financeAffirm) {
		this.financeAffirm = financeAffirm;
	}
	
	/**
	 * 返回 财务确认(0:未确认,1:已确认)
	 * @return
	 */
	public String getFinanceAffirm() {
		return this.financeAffirm;
	}

	public void setScmCwBAPaymentDiscountList(List<ScmCwBAPaymentDiscount> scmCwBAPaymentDiscountList) {
		this.scmCwBAPaymentDiscountList = scmCwBAPaymentDiscountList;
	}
	
	/**
	 * 返回 经销商RB核算付款折扣列表
	 * @return
	 */
	public List<ScmCwBAPaymentDiscount> getScmCwBAPaymentDiscountList() {
		return this.scmCwBAPaymentDiscountList;
	}

	public void setScmCwBAStorageDiscountList(List<ScmCwBAStorageDiscount> scmCwBAStorageDiscountList) {
		this.scmCwBAStorageDiscountList = scmCwBAStorageDiscountList;
	}
	
	/**
	 * 返回 经销商RB核算储运折扣列表
	 * @return
	 */
	public List<ScmCwBAStorageDiscount> getScmCwBAStorageDiscountList() {
		return this.scmCwBAStorageDiscountList;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("businessDivision", this.businessDivision) 
		.append("province", this.province) 
		.append("businessDivisionId", this.businessDivisionId) 
		.append("provinceId", this.provinceId) 
		.append("year", this.year) 
		.append("quarter", this.quarter) 
		.append("taxPoint", this.taxPoint) 
		.append("ownerId", this.ownerId) 
		.append("createPsersion", this.createPsersion) 
		.append("createDate", this.createDate) 
		.append("approvalState", this.approvalState) 
		.append("approvalId", this.approvalId) 
		.append("financeAffirm", this.financeAffirm) 
		.toString();
	}
}