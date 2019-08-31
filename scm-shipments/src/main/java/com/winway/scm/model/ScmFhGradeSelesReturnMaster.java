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
 * 当次退货主表
 * 
 * <pre>
 *  
 * 描述：当次退货主表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-13 15:47:33
 * 版权：美达开发小组
 * </pre>
 */
@ApiModel(value = "ScmFhGradeSelesReturnMaster", description = "当次退货主表")
public class ScmFhGradeSelesReturnMaster extends BaseModel<String> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	protected String id;

	@ApiModelProperty(value = "商业", required = true)
	protected String commerceName;

	@ApiModelProperty(value = "商业ID", required = true)
	protected String commerceId;

	@ApiModelProperty(value = "商业首营ID", required = true)
	protected String commerceFirstId;

	@ApiModelProperty(value = "商业编号", required = true)
	protected String commerceCode;

	@ApiModelProperty(value = "发货单号")
	protected String shipmentsCode;

	@ApiModelProperty(value = "审批ID")
	protected String approvalId;

	@ApiModelProperty(value = "审批状态")
	protected String approvalState;

	@ApiModelProperty(value = "货主ID", required = true)
	protected String ownerId;

	@ApiModelProperty(value = "创建人")
	protected String createPersion;

	@ApiModelProperty(value = "创建时间")
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	protected java.util.Date createDate;

	@ApiModelProperty(value = "当次退货编号")
	protected String gradeSelesCode;

	@ApiModelProperty(value = "年份")
	protected String year;

	@ApiModelProperty(value = "单次退货总价格")
	protected Double returnPriceSum = 0.0;

	@ApiModelProperty(value = "品种数")
	protected Long kindSum;

	@ApiModelProperty(value = "退货总数量")
	protected Integer returnTotal;

	@ApiModelProperty(value = "是否推送值SMS 0未推送；1已推送")
	protected String isSMS;

	@ApiModelProperty(value = "sap是否处理(0:未处理,1:已处理)")
	protected String isManageSap;
	
	@ApiModelProperty(value = "wms回执状态(0:未处理,1:已处理)")
	protected String isAffirmWms;
	
	@ApiModelProperty(value = "wms处理时间")
	protected Date wmsAffirmDate;
	
	public Date getWmsAffirmDate() {
		return wmsAffirmDate;
	}

	public void setWmsAffirmDate(Date wmsAffirmDate) {
		this.wmsAffirmDate = wmsAffirmDate;
	}

	public String getIsAffirmWms() {
		return isAffirmWms;
	}

	public void setIsAffirmWms(String isAffirmWms) {
		this.isAffirmWms = isAffirmWms;
	}

	public String getIsManageSap() {
		return isManageSap;
	}

	public void setIsManageSap(String isManageSap) {
		this.isManageSap = isManageSap;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getIsSMS() {
		return isSMS;
	}

	public void setIsSMS(String isSMS) {
		this.isSMS = isSMS;
	}

	public Integer getReturnTotal() {
		return returnTotal;
	}

	public void setReturnTotal(Integer returnTotal) {
		this.returnTotal = returnTotal;
	}

	public Long getKindSum() {
		return kindSum;
	}

	public void setKindSum(Long kindSum) {
		this.kindSum = kindSum;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Double getReturnPriceSum() {
		return returnPriceSum;
	}

	public void setReturnPriceSum(Double returnPriceSum) {
		this.returnPriceSum = returnPriceSum;
	}

	/**
	 * 当次退货列表
	 */
	protected List<ScmFhGradeSelesReturn> scmFhGradeSelesReturnList = new ArrayList<ScmFhGradeSelesReturn>();

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

	public void setCommerceName(String commerceName) {
		this.commerceName = commerceName;
	}

	/**
	 * 返回 商业
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
	 * 返回 商业ID
	 * 
	 * @return
	 */
	public String getCommerceId() {
		return this.commerceId;
	}

	public void setCommerceFirstId(String commerceFirstId) {
		this.commerceFirstId = commerceFirstId;
	}

	/**
	 * 返回 商业首营ID
	 * 
	 * @return
	 */
	public String getCommerceFirstId() {
		return this.commerceFirstId;
	}

	public void setCommerceCode(String commerceCode) {
		this.commerceCode = commerceCode;
	}

	/**
	 * 返回 商业编号
	 * 
	 * @return
	 */
	public String getCommerceCode() {
		return this.commerceCode;
	}

	public void setShipmentsCode(String shipmentsCode) {
		this.shipmentsCode = shipmentsCode;
	}

	/**
	 * 返回 发货单号
	 * 
	 * @return
	 */
	public String getShipmentsCode() {
		return this.shipmentsCode;
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

	public void setGradeSelesCode(String gradeSelesCode) {
		this.gradeSelesCode = gradeSelesCode;
	}

	/**
	 * 返回 当次退货编号
	 * 
	 * @return
	 */
	public String getGradeSelesCode() {
		return this.gradeSelesCode;
	}

	public void setScmFhGradeSelesReturnList(List<ScmFhGradeSelesReturn> scmFhGradeSelesReturnList) {
		this.scmFhGradeSelesReturnList = scmFhGradeSelesReturnList;
	}

	/**
	 * 返回 当次退货列表
	 * 
	 * @return
	 */
	public List<ScmFhGradeSelesReturn> getScmFhGradeSelesReturnList() {
		return this.scmFhGradeSelesReturnList;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).append("commerceName", this.commerceName)
				.append("commerceId", this.commerceId).append("commerceFirstId", this.commerceFirstId)
				.append("commerceCode", this.commerceCode).append("shipmentsCode", this.shipmentsCode)
				.append("approvalId", this.approvalId).append("approvalState", this.approvalState)
				.append("ownerId", this.ownerId).append("createPersion", this.createPersion)
				.append("createDate", this.createDate).append("gradeSelesCode", this.gradeSelesCode).toString();
	}
}