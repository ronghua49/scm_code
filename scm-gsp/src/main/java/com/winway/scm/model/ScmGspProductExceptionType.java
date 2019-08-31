package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;


 /**
 * 产品异常状态
 * <pre> 
 * 描述：产品异常状态 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-30 14:46:24
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmGspProductExceptionType",description = "产品异常状态") 
public class ScmGspProductExceptionType extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="状态",required = true)
	protected String state; 
	
	@ApiModelProperty(value="年度",required = true)
	protected String year; 
	
	@ApiModelProperty(value="省区",required = true)
	protected String provinces; 
	
	@ApiModelProperty(value="异常类型",required = true)
	protected String exceptionType; 
	
	@ApiModelProperty(value="商品系列ID",required = true)
	protected String productLineId; 
	
	@ApiModelProperty(value="商品系列",required = true)
	protected String productLine; 
	
	@ApiModelProperty(value="商品名称",required = true)
	protected String productName; 
	
	@ApiModelProperty(value="商品编号",required = true)
	protected String productCode; 
	
	@ApiModelProperty(value="商品ID",required = true)
	protected String productId; 
	
	@ApiModelProperty(value="开始时间",required = true)
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date startDate; 
	
	@ApiModelProperty(value="截止时间",required = true)
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date stopDate; 
	
	@ApiModelProperty(value="商务免于考核开始月",required = true)
	protected String businessStartDate; 
	
	@ApiModelProperty(value="商务免于考核截止月",required = true)
	protected String businessStopDate; 
	
	@ApiModelProperty(value="录入人")
	protected String createpersion; 
	
	@ApiModelProperty(value="录入日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date createDate; 
	
	@ApiModelProperty(value="货主ID",required = true)
	protected String ownerId; 
	
	@ApiModelProperty(value="药品类型ID")
	protected String MedicineTypeId; 
	
	@ApiModelProperty(value="药品类型")
	protected String MedicineType; 
	
	@ApiModelProperty(value="通用名称")
	protected String commonName;
	
	@ApiModelProperty(value="剂型ID")
	protected String AgentTypeId; 
	
	@ApiModelProperty(value="剂型")
	protected String AgentType; 
	
	@ApiModelProperty(value="药品分类ID")
	protected String medicineClassifyId; 
	
	@ApiModelProperty(value="药品分类")
	protected String medicineClassify; 
	
	@ApiModelProperty(value="经营范围")
	protected String businessScope; 
	
	@ApiModelProperty(value="规格")
	protected String productState; 
	
	@ApiModelProperty(value="单位")
	protected String unit; 
	
	@ApiModelProperty(value="生产厂商")
	protected String manufacturer; 
	
	public String getMedicineTypeId() {
		return MedicineTypeId;
	}

	public void setMedicineTypeId(String medicineTypeId) {
		MedicineTypeId = medicineTypeId;
	}

	public String getMedicineType() {
		return MedicineType;
	}

	public void setMedicineType(String medicineType) {
		MedicineType = medicineType;
	}

	public String getCommonName() {
		return commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public String getAgentTypeId() {
		return AgentTypeId;
	}

	public void setAgentTypeId(String agentTypeId) {
		AgentTypeId = agentTypeId;
	}

	public String getAgentType() {
		return AgentType;
	}

	public void setAgentType(String agentType) {
		AgentType = agentType;
	}

	public String getMedicineClassifyId() {
		return medicineClassifyId;
	}

	public void setMedicineClassifyId(String medicineClassifyId) {
		this.medicineClassifyId = medicineClassifyId;
	}

	public String getMedicineClassify() {
		return medicineClassify;
	}

	public void setMedicineClassify(String medicineClassify) {
		this.medicineClassify = medicineClassify;
	}

	public String getBusinessScope() {
		return businessScope;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}

	public String getProductState() {
		return productState;
	}

	public void setProductState(String productState) {
		this.productState = productState;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
	
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * 返回 状态
	 * @return
	 */
	public String getState() {
		return this.state;
	}
	
	public void setYear(String year) {
		this.year = year;
	}
	
	/**
	 * 返回 年度
	 * @return
	 */
	public String getYear() {
		return this.year;
	}
	
	public void setProvinces(String provinces) {
		this.provinces = provinces;
	}
	
	/**
	 * 返回 省区
	 * @return
	 */
	public String getProvinces() {
		return this.provinces;
	}
	
	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}
	
	/**
	 * 返回 异常类型
	 * @return
	 */
	public String getExceptionType() {
		return this.exceptionType;
	}
	
	public void setProductLineId(String productLineId) {
		this.productLineId = productLineId;
	}
	
	/**
	 * 返回 商品系列ID
	 * @return
	 */
	public String getProductLineId() {
		return this.productLineId;
	}
	
	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}
	
	/**
	 * 返回 商品系列
	 * @return
	 */
	public String getProductLine() {
		return this.productLine;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	/**
	 * 返回 商品名称
	 * @return
	 */
	public String getProductName() {
		return this.productName;
	}
	
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	/**
	 * 返回 商品编号
	 * @return
	 */
	public String getProductCode() {
		return this.productCode;
	}
	
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	/**
	 * 返回 商品ID
	 * @return
	 */
	public String getProductId() {
		return this.productId;
	}
	
	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * 返回 开始时间
	 * @return
	 */
	public java.util.Date getStartDate() {
		return this.startDate;
	}
	
	public void setStopDate(java.util.Date stopDate) {
		this.stopDate = stopDate;
	}
	
	/**
	 * 返回 截止时间
	 * @return
	 */
	public java.util.Date getStopDate() {
		return this.stopDate;
	}
	
	public void setBusinessStartDate(String businessStartDate) {
		this.businessStartDate = businessStartDate;
	}
	
	/**
	 * 返回 商务免于考核开始月
	 * @return
	 */
	public String getBusinessStartDate() {
		return this.businessStartDate;
	}
	
	public void setBusinessStopDate(String businessStopDate) {
		this.businessStopDate = businessStopDate;
	}
	
	/**
	 * 返回 商务免于考核截止月
	 * @return
	 */
	public String getBusinessStopDate() {
		return this.businessStopDate;
	}
	
	public void setCreatepersion(String createpersion) {
		this.createpersion = createpersion;
	}
	
	/**
	 * 返回 录入人
	 * @return
	 */
	public String getCreatepersion() {
		return this.createpersion;
	}
	
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 返回 录入日期
	 * @return
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
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
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("state", this.state) 
		.append("year", this.year) 
		.append("provinces", this.provinces) 
		.append("exceptionType", this.exceptionType) 
		.append("productLineId", this.productLineId) 
		.append("productLine", this.productLine) 
		.append("productName", this.productName) 
		.append("productCode", this.productCode) 
		.append("productId", this.productId) 
		.append("startDate", this.startDate) 
		.append("stopDate", this.stopDate) 
		.append("businessStartDate", this.businessStartDate) 
		.append("businessStopDate", this.businessStopDate) 
		.append("createpersion", this.createpersion) 
		.append("createDate", this.createDate) 
		.append("ownerId", this.ownerId) 
		.toString();
	}
}