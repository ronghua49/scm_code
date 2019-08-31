package com.winway.scm.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 发货明细总表
 * 
 * <pre>
 *  
 * 描述：发货明细总表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-02-15 17:31:50
 * 版权：美达开发小组
 * </pre>
 */
@ApiModel(value = "ScmFhShipmentsDatailMaster", description = "发货明细总表")
public class ScmFhShipmentsDatailMaster extends BaseModel<String> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	protected String id;

	@ApiModelProperty(value = "审批ID")
	protected String approvalId;

	@ApiModelProperty(value = "审批状态")
	protected String approvalState;

	@ApiModelProperty(value = "货主ID")
	protected String ownerId;

	@ApiModelProperty(value = "商务大区")
	protected String businessDivision;

	@ApiModelProperty(value = "商务大区id")
	protected String businessDivisionId;

	@ApiModelProperty(value = "省区")
	protected String province;

	@ApiModelProperty(value = "省区id")
	protected String provinceId;

	@ApiModelProperty(value = "商业名称")
	protected String commerceName;

	@ApiModelProperty(value = "商业id")
	protected String commerceId;

	@ApiModelProperty(value = "发货编号")
	protected String shipmentsCode;

	@ApiModelProperty(value = "通过时间")
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	protected java.util.Date successDate;

	@ApiModelProperty(value = "创建人")
	protected String createPersion;

	@ApiModelProperty(value = "创建时间")
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	protected java.util.Date createDate;

	@ApiModelProperty(value = "发货子表总金额")
	protected Double priceTotal;

	@ApiModelProperty(value = "商务人员")
	protected String dutyOfficer;

	@ApiModelProperty(value = "品种数")
	protected String kindNum;

	@ApiModelProperty(value = "商业首营id")
	protected String commerceFirstId;

	@ApiModelProperty(value = "商业编号")
	protected String commerceCode;

	@ApiModelProperty(value = "是否推送至sap(0:未推送,1:已推送)")
	protected String isToSap;

	@ApiModelProperty(value = "是否推送至wms(0:未推送,1:已推送)")
	protected String isToWms;

	@ApiModelProperty(value = "WMS处理状态(0:未确认,1:已确认,2:wms取消,3:已完成)")
	protected String isAffirmWms;
	
	@ApiModelProperty(value = "是否可开票（0：不可开，1可开，2已开票.3开票中）")
	protected String canInvoice;
	
	public String getCanInvoice() {
		return canInvoice;
	}

	public void setCanInvoice(String canInvoice) {
		this.canInvoice = canInvoice;
	}

	public String getIsToSap() {
		return isToSap;
	}

	public void setIsToSap(String isToSap) {
		this.isToSap = isToSap;
	}

	public String getIsToWms() {
		return isToWms;
	}

	public void setIsToWms(String isToWms) {
		this.isToWms = isToWms;
	}

	public String getIsAffirmWms() {
		return isAffirmWms;
	}

	public void setIsAffirmWms(String isAffirmWms) {
		this.isAffirmWms = isAffirmWms;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCommerceFirstId() {
		return commerceFirstId;
	}

	public void setCommerceFirstId(String commerceFirstId) {
		this.commerceFirstId = commerceFirstId;
	}

	public String getCommerceCode() {
		return commerceCode;
	}

	public void setCommerceCode(String commerceCode) {
		this.commerceCode = commerceCode;
	}

	public String getKindNum() {
		return kindNum;
	}

	public void setKindNum(String kindNum) {
		this.kindNum = kindNum;
	}

	public Double getPriceTotal() {
		return priceTotal;
	}

	public void setPriceTotal(Double priceTotal) {
		this.priceTotal = priceTotal;
	}

	public String getDutyOfficer() {
		return dutyOfficer;
	}

	public void setDutyOfficer(String dutyOfficer) {
		this.dutyOfficer = dutyOfficer;
	}

	/**
	 * 发货明细表列表
	 */
	protected List<ScmFhShipmentsDatail> scmFhShipmentsDatailList = new ArrayList<ScmFhShipmentsDatail>();

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 返回 id
	 * 
	 * @return
	 */
	public String getId() {
		return this.id;
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

	public void setBusinessDivision(String businessDivision) {
		this.businessDivision = businessDivision;
	}

	/**
	 * 返回 商务大区
	 * 
	 * @return
	 */
	public String getBusinessDivision() {
		return this.businessDivision;
	}

	public void setBusinessDivisionId(String businessDivisionId) {
		this.businessDivisionId = businessDivisionId;
	}

	/**
	 * 返回 商务大区id
	 * 
	 * @return
	 */
	public String getBusinessDivisionId() {
		return this.businessDivisionId;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * 返回 省区
	 * 
	 * @return
	 */
	public String getProvince() {
		return this.province;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	/**
	 * 返回 省区id
	 * 
	 * @return
	 */
	public String getProvinceId() {
		return this.provinceId;
	}

	public void setCommerceName(String commerceName) {
		this.commerceName = commerceName;
	}

	/**
	 * 返回 商业名称
	 * 
	 * @return
	 */
	public String getCommerceName() {
		return this.commerceName;
	}

	public void setCommerceId(String commerceId) {
		this.commerceId = commerceId;
	}

	/**
	 * 返回 商业id
	 * 
	 * @return
	 */
	public String getCommerceId() {
		return this.commerceId;
	}

	public void setShipmentsCode(String shipmentsCode) {
		this.shipmentsCode = shipmentsCode;
	}

	/**
	 * 返回 发货编号
	 * 
	 * @return
	 */
	public String getShipmentsCode() {
		return this.shipmentsCode;
	}

	public void setSuccessDate(java.util.Date successDate) {
		this.successDate = successDate;
	}

	/**
	 * 返回 通过时间
	 * 
	 * @return
	 */
	public java.util.Date getSuccessDate() {
		return this.successDate;
	}

	public void setCreatePersion(String createPersion) {
		this.createPersion = createPersion;
	}

	/**
	 * 返回 创建人
	 * 
	 * @return
	 */
	public String getCreatePersion() {
		return this.createPersion;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 返回 创建时间
	 * 
	 * @return
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}

	public void setScmFhShipmentsDatailList(List<ScmFhShipmentsDatail> scmFhShipmentsDatailList) {
		this.scmFhShipmentsDatailList = scmFhShipmentsDatailList;
	}

	/**
	 * 返回 发货明细表列表
	 * 
	 * @return
	 */
	public List<ScmFhShipmentsDatail> getScmFhShipmentsDatailList() {
		return this.scmFhShipmentsDatailList;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).append("approvalId", this.approvalId)
				.append("approvalState", this.approvalState).append("ownerId", this.ownerId)
				.append("businessDivision", this.businessDivision).append("businessDivisionId", this.businessDivisionId)
				.append("province", this.province).append("provinceId", this.provinceId)
				.append("commerceName", this.commerceName).append("commerceId", this.commerceId)
				.append("shipmentsCode", this.shipmentsCode).append("successDate", this.successDate)
				.append("createPersion", this.createPersion).append("createDate", this.createDate).toString();
	}
}