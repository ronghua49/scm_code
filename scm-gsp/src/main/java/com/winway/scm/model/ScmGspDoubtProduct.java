package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * Scm_Gsp_DoubtProduct
 * <pre> 
 * 描述：Scm_Gsp_DoubtProduct 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-18 18:21:20
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmGspDoubtProduct",description = "Scm_Gsp_DoubtProduct") 
public class ScmGspDoubtProduct extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="主表ID")
	protected String masterId; 
	
	@ApiModelProperty(value="批准文号",required = true)
	protected String approvalCode; 
	
	@ApiModelProperty(value="通用名称",required = true)
	protected String commonName; 
	
	@ApiModelProperty(value="药品名称",required = true)
	protected String productName; 
	
	@ApiModelProperty(value="生产厂家",required = true)
	protected String manufacturer; 
	
	@ApiModelProperty(value="生产批号",required = true)
	protected String batchNumber; 
	
	@ApiModelProperty(value="用法用量",required = true)
	protected String usageAndDoasge; 
	
	@ApiModelProperty(value="用药起止时间",required = true)
	protected String startStopOfMedication;
	
	@ApiModelProperty(value="用药原因",required = true)
	protected String medicationReason; 
	
	
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
	
	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}
	
	/**
	 * 返回 主表ID
	 * @return
	 */
	public String getMasterId() {
		return this.masterId;
	}
	
	public void setApprovalCode(String approvalCode) {
		this.approvalCode = approvalCode;
	}
	
	/**
	 * 返回 批准文号
	 * @return
	 */
	public String getApprovalCode() {
		return this.approvalCode;
	}
	
	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}
	
	/**
	 * 返回 通用名称
	 * @return
	 */
	public String getCommonName() {
		return this.commonName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	/**
	 * 返回 药品名称
	 * @return
	 */
	public String getProductName() {
		return this.productName;
	}
	
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	/**
	 * 返回 生产厂家
	 * @return
	 */
	public String getManufacturer() {
		return this.manufacturer;
	}
	
	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}
	
	/**
	 * 返回 生产批号
	 * @return
	 */
	public String getBatchNumber() {
		return this.batchNumber;
	}
	
	public void setUsageAndDoasge(String usageAndDoasge) {
		this.usageAndDoasge = usageAndDoasge;
	}
	
	/**
	 * 返回 用法用量
	 * @return
	 */
	public String getUsageAndDoasge() {
		return this.usageAndDoasge;
	}
	
	public void setStartStopOfMedication(String startStopOfMedication) {
		this.startStopOfMedication = startStopOfMedication;
	}
	
	/**
	 * 返回 用药起止时间
	 * @return
	 */
	public String getStartStopOfMedication() {
		return this.startStopOfMedication;
	}
	
	public void setMedicationReason(String medicationReason) {
		this.medicationReason = medicationReason;
	}
	
	/**
	 * 返回 用药原因
	 * @return
	 */
	public String getMedicationReason() {
		return this.medicationReason;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("masterId", this.masterId) 
		.append("approvalCode", this.approvalCode) 
		.append("commonName", this.commonName) 
		.append("productName", this.productName) 
		.append("manufacturer", this.manufacturer) 
		.append("batchNumber", this.batchNumber) 
		.append("usageAndDoasge", this.usageAndDoasge) 
		.append("startStopOfMedication", this.startStopOfMedication) 
		.append("medicationReason", this.medicationReason) 
		.toString();
	}
}