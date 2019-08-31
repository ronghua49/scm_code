package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;


 /**
 * 集中发货设置表
 * <pre> 
 * 描述：集中发货设置表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-02-15 17:31:50
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmFhFocusShipmentsSet",description = "集中发货设置表") 
public class ScmFhFocusShipmentsSet extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="设置编号")
	protected String setCode; 
	
	@ApiModelProperty(value="商务大区")
	protected String businessDivision; 
	
	@ApiModelProperty(value="商务大区id")
	protected String businessDivisionId; 
	
	@ApiModelProperty(value="省区")
	protected String province; 
	
	@ApiModelProperty(value="省区id")
	protected String provinceId; 
	
	@ApiModelProperty(value="商业名称")
	protected String commerceName; 
	
	@ApiModelProperty(value="商业id")
	protected String commerceId; 
	
	@ApiModelProperty(value="产品系列ID")
	protected String ProductLineId; 
	
	@ApiModelProperty(value="产品系列")
	protected String ProductLineName; 
	
	@ApiModelProperty(value="是否启用")
	protected String isStartUsing; 
	
	@ApiModelProperty(value="启用日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date startUsingDate; 
	
	@ApiModelProperty(value="创建人")
	protected String createPersion; 
	
	@ApiModelProperty(value="创建时间")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date createDate; 
	
	@ApiModelProperty(value="货主ID")
	protected String ownerId;
	
	@ApiModelProperty(value="商品名称")
	protected String productName;
	
	@ApiModelProperty(value="商品ID")
	protected String productId;
	
	@ApiModelProperty(value="商品编号")
	protected String productCode;

	 @ApiModelProperty(value="药品类型")
	 protected String medicineType;

	 @ApiModelProperty(value="通用名")
	 protected String commonName;

	 @ApiModelProperty(value="商品规格")
	 protected String productState;

	 @ApiModelProperty(value="单位")
	 protected String unit;

	 @ApiModelProperty(value="剂型")
	 protected String AgentType;
	 @ApiModelProperty(value="生产厂商")
	 protected String manufacturer;
	 @ApiModelProperty(value="批准文号")
	 protected String approvalCode;
	 @ApiModelProperty(value="药品分类")
	 protected String medicineClassify;
	 @ApiModelProperty(value="经营范围")
	 protected String businessScope;


	 public String getMedicineType() {
		 return medicineType;
	 }

	 public void setMedicineType(String medicineType) {
		 this.medicineType = medicineType;
	 }

	 public String getCommonName() {
		 return commonName;
	 }

	 public void setCommonName(String commonName) {
		 this.commonName = commonName;
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

	 public String getAgentType() {
		 return AgentType;
	 }

	 public void setAgentType(String agentType) {
		 AgentType = agentType;
	 }

	 public String getManufacturer() {
		 return manufacturer;
	 }

	 public void setManufacturer(String manufacturer) {
		 this.manufacturer = manufacturer;
	 }

	 public String getApprovalCode() {
		 return approvalCode;
	 }

	 public void setApprovalCode(String approvalCode) {
		 this.approvalCode = approvalCode;
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
	
	public void setSetCode(String setCode) {
		this.setCode = setCode;
	}
	
	/**
	 * 返回 设置编号
	 * @return
	 */
	public String getSetCode() {
		return this.setCode;
	}
	
	public void setBusinessDivision(String businessDivision) {
		this.businessDivision = businessDivision;
	}
	
	/**
	 * 返回 商务大区
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
	 * @return
	 */
	public String getCommerceId() {
		return this.commerceId;
	}
	
	public void setProductLineId(String ProductLineId) {
		this.ProductLineId = ProductLineId;
	}
	
	/**
	 * 返回 产品系列ID
	 * @return
	 */
	public String getProductLineId() {
		return this.ProductLineId;
	}
	
	public void setProductLineName(String ProductLineName) {
		this.ProductLineName = ProductLineName;
	}
	
	/**
	 * 返回 产品系列
	 * @return
	 */
	public String getProductLineName() {
		return this.ProductLineName;
	}
	
	public void setIsStartUsing(String isStartUsing) {
		this.isStartUsing = isStartUsing;
	}
	
	/**
	 * 返回 是否启用
	 * @return
	 */
	public String getIsStartUsing() {
		return this.isStartUsing;
	}
	
	public void setStartUsingDate(java.util.Date startUsingDate) {
		this.startUsingDate = startUsingDate;
	}
	
	/**
	 * 返回 启用日期
	 * @return
	 */
	public java.util.Date getStartUsingDate() {
		return this.startUsingDate;
	}
	
	public void setCreatePersion(String createPersion) {
		this.createPersion = createPersion;
	}
	
	/**
	 * 返回 创建人
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
	 * @return
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("setCode", this.setCode) 
		.append("businessDivision", this.businessDivision) 
		.append("businessDivisionId", this.businessDivisionId) 
		.append("province", this.province) 
		.append("provinceId", this.provinceId) 
		.append("commerceName", this.commerceName) 
		.append("commerceId", this.commerceId) 
		.append("ProductLineId", this.ProductLineId) 
		.append("ProductLineName", this.ProductLineName) 
		.append("isStartUsing", this.isStartUsing) 
		.append("startUsingDate", this.startUsingDate) 
		.append("createPersion", this.createPersion) 
		.append("createDate", this.createDate) 
		.toString();
	}
}