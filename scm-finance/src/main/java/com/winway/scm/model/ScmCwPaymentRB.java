package com.winway.scm.model;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;


 /**
 * 经销商RB支付申请总表
 * <pre> 
 * 描述：经销商RB支付申请总表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-09 18:18:05
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmCwPaymentRB",description = "经销商RB支付申请总表") 
public class ScmCwPaymentRB extends BaseModel<String>{

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
	
	@ApiModelProperty(value="区间(0:上半年,1:下半年)")
	protected String section; 
	
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
	
	/**
	*经销商RB支付付款折扣列表
	*/
	protected List<ScmCwPPaymentDiscount> scmCwPPaymentDiscountList=new ArrayList<ScmCwPPaymentDiscount>(); 
	/**
	*经销商RB支付储运折扣列表
	*/
	protected List<ScmCwPStorageDiscount> scmCwPStorageDiscountList=new ArrayList<ScmCwPStorageDiscount>(); 
	/**
	*经销商RB支付年费信息管理费列表
	*/
	protected List<ScmCwPAnnualFee> scmCwPAnnualFeeList=new ArrayList<ScmCwPAnnualFee>(); 
	
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
	
	public void setSection(String section) {
		this.section = section;
	}
	
	/**
	 * 返回 区间(0:上半年,1:下半年)
	 * @return
	 */
	public String getSection() {
		return this.section;
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

	public void setScmCwPPaymentDiscountList(List<ScmCwPPaymentDiscount> scmCwPPaymentDiscountList) {
		this.scmCwPPaymentDiscountList = scmCwPPaymentDiscountList;
	}
	
	/**
	 * 返回 经销商RB支付付款折扣列表
	 * @return
	 */
	public List<ScmCwPPaymentDiscount> getScmCwPPaymentDiscountList() {
		return this.scmCwPPaymentDiscountList;
	}

	public void setScmCwPStorageDiscountList(List<ScmCwPStorageDiscount> scmCwPStorageDiscountList) {
		this.scmCwPStorageDiscountList = scmCwPStorageDiscountList;
	}
	
	/**
	 * 返回 经销商RB支付储运折扣列表
	 * @return
	 */
	public List<ScmCwPStorageDiscount> getScmCwPStorageDiscountList() {
		return this.scmCwPStorageDiscountList;
	}

	public void setScmCwPAnnualFeeList(List<ScmCwPAnnualFee> scmCwPAnnualFeeList) {
		this.scmCwPAnnualFeeList = scmCwPAnnualFeeList;
	}
	
	/**
	 * 返回 经销商RB支付年费信息管理费列表
	 * @return
	 */
	public List<ScmCwPAnnualFee> getScmCwPAnnualFeeList() {
		return this.scmCwPAnnualFeeList;
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
		.append("section", this.section) 
		.append("ownerId", this.ownerId) 
		.append("createPsersion", this.createPsersion) 
		.append("createDate", this.createDate) 
		.append("approvalState", this.approvalState) 
		.append("approvalId", this.approvalId) 
		.toString();
	}
}