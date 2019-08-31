package com.winway.scm.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;

/**
 * 大合同分配表
 * 
 * <pre>
 *  
 * 描述：大合同分配表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:44
 * 版权：美达开发小组
 * </pre>
 */
@ApiModel(value = "ScmXsBigContractAllot", description = "大合同分配表")
public class ScmXsBigContractAllot extends BaseModel<String> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	protected String id;

	@ApiModelProperty(value = "特求意见")
	protected String specialOpinion;

	@ApiModelProperty(value = "货主ID")
	protected String ownerId;

	@ApiModelProperty(value = "经销商协议ID")
	protected String bigContractId;

	@ApiModelProperty(value = "减免合同次数理由")
	protected String reduceContractTime;

	@ApiModelProperty(value = "分配编号")
	protected String allocationCode;

	@ApiModelProperty(value = "审批ID")
	protected String approvalId;

	@ApiModelProperty(value = "审批状态")
	protected String approvalState;

	@ApiModelProperty(value = "签订日期")
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date dateOfSigning;

	@ApiModelProperty(value = "计划发货时间")
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	protected java.util.Date sendProductDate;

	@ApiModelProperty(value = "经销商名称")
	protected String commerceName;

	@ApiModelProperty(value = "经销商编号")
	protected String commerceCode;

	@ApiModelProperty(value = "创建人")
	protected String createPersion;

	@ApiModelProperty(value = "供应商")
	protected String ownerName;

	@ApiModelProperty(value = "购销方式 预付款0,赊销1")
	protected String marketingWay;

	@ApiModelProperty(value = "创建时间")
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date createDate;

	@ApiModelProperty(value = "经销商首营ID")
	protected String commerceFirstId;

	@ApiModelProperty(value = "药品类型")
	protected String medicineType;
	
	@ApiModelProperty(value = "是否超近三个月平均值2倍 ,0:为超过,1:已超过")
	protected String isoverfuifil;

	public String getIsoverfuifil() {
		return isoverfuifil;
	}

	public void setIsoverfuifil(String isoverfuifil) {
		this.isoverfuifil = isoverfuifil;
	}

	public String getMedicineType() {
		return medicineType;
	}

	public void setMedicineType(String medicineType) {
		this.medicineType = medicineType;
	}

	@ApiModelProperty(value = "总价")
	protected String totalPrice;

	@ApiModelProperty(value = "资信扣减价")
	protected String creditPrice;

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getCreditPrice() {
		return creditPrice;
	}

	public void setCreditPrice(String creditPrice) {
		this.creditPrice = creditPrice;
	}

	/**
	 * 大合同分配商品表列表
	 */
	protected List<ScmXsBigContractAllotProduct> scmXsBigContractAllotProductList = new ArrayList<ScmXsBigContractAllotProduct>();

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCommerceName() {
		return commerceName;
	}

	public void setCommerceName(String commerceName) {
		this.commerceName = commerceName;
	}

	public String getCommerceCode() {
		return commerceCode;
	}

	public String getMarketingWay() {
		return marketingWay;
	}

	public void setMarketingWay(String marketingWay) {
		this.marketingWay = marketingWay;
	}

	public void setCommerceCode(String commerceCode) {
		this.commerceCode = commerceCode;
	}

	public String getCreatePersion() {
		return createPersion;
	}

	public void setCreatePersion(String createPersion) {
		this.createPersion = createPersion;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 返回 id
	 * 
	 * @return
	 */
	public String getId() {
		return this.id;
	}

	public void setSpecialOpinion(String specialOpinion) {
		this.specialOpinion = specialOpinion;
	}

	/**
	 * 返回 特求意见
	 * 
	 * @return
	 */
	public String getSpecialOpinion() {
		return this.specialOpinion;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * 返回 货主ID
	 * 
	 * @return
	 */
	public String getOwnerId() {
		return this.ownerId;
	}

	public String getBigContractId() {
		return bigContractId;
	}

	public void setBigContractId(String bigContractId) {
		this.bigContractId = bigContractId;
	}

	public void setReduceContractTime(String reduceContractTime) {
		this.reduceContractTime = reduceContractTime;
	}

	/**
	 * 返回 减免合同次数理由
	 * 
	 * @return
	 */
	public String getReduceContractTime() {
		return this.reduceContractTime;
	}

	public void setAllocationCode(String allocationCode) {
		this.allocationCode = allocationCode;
	}

	/**
	 * 返回 分配编号
	 * 
	 * @return
	 */
	public String getAllocationCode() {
		return this.allocationCode;
	}

	public void setApprovalId(String approvalId) {
		this.approvalId = approvalId;
	}

	/**
	 * 返回 审批ID
	 * 
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
	 * 
	 * @return
	 */
	public String getApprovalState() {
		return this.approvalState;
	}

	public void setScmXsBigContractAllotProductList(
			List<ScmXsBigContractAllotProduct> scmXsBigContractAllotProductList) {
		this.scmXsBigContractAllotProductList = scmXsBigContractAllotProductList;
	}

	/**
	 * 返回 大合同分配商品表列表
	 * 
	 * @return
	 */
	public List<ScmXsBigContractAllotProduct> getScmXsBigContractAllotProductList() {
		return this.scmXsBigContractAllotProductList;
	}

	/**
	 * 返回 签订日期
	 * 
	 * @return
	 */
	public Date getDateOfSigning() {
		return dateOfSigning;
	}

	public void setDateOfSigning(Date dateOfSigning) {
		this.dateOfSigning = dateOfSigning;
	}

	public java.util.Date getSendProductDate() {
		return sendProductDate;
	}

	public void setSendProductDate(java.util.Date sendProductDate) {
		this.sendProductDate = sendProductDate;
	}

	public String getCommerceFirstId() {
		return commerceFirstId;
	}

	public void setCommerceFirstId(String commerceFirstId) {
		this.commerceFirstId = commerceFirstId;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).append("specialOpinion", this.specialOpinion)
				.append("ownerId", this.ownerId).append("BigContractId", this.bigContractId)
				.append("reduceContractTime", this.reduceContractTime).append("allocationCode", this.allocationCode)
				.append("approvalId", this.approvalId).append("approvalState", this.approvalState)
				.append("dateOfSigning", this.dateOfSigning).toString();
	}

}