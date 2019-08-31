package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;


 /**
 * scm_kc_weekinventory
 * <pre> 
 * 描述：scm_kc_weekinventory 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-07-08 15:15:30
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmKcWeekInventory",description = "scm_kc_weekinventory") 
public class ScmKcWeekInventory extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="年度")
	protected String year; 
	
	@ApiModelProperty(value="月度")
	protected String month; 
	
	@ApiModelProperty(value="周次")
	protected Integer week; 
	
	@ApiModelProperty(value="开始日期")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	protected java.util.Date startDate; 
	
	@ApiModelProperty(value="截止日期")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	protected java.util.Date stopDate; 
	
	@ApiModelProperty(value="商业编码")
	protected String commerceCode; 
	
	@ApiModelProperty(value="商业名称")
	protected String commerceName; 
	
	@ApiModelProperty(value="产品编号")
	protected String productCode; 
	
	@ApiModelProperty(value="产品名称")
	protected String productName; 
	
	@ApiModelProperty(value="库存日期")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	protected java.util.Date inventoryDate; 
	
	@ApiModelProperty(value="批号")
	protected String batchNum; 
	
	@ApiModelProperty(value="上周库存")
	protected Integer upWeekInventory; 
	
	@ApiModelProperty(value="本周库存")
	protected Integer thisWeekInventory; 
	
	@ApiModelProperty(value="创建人")
	protected String createPersion; 
	
	@ApiModelProperty(value="创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	protected java.util.Date createDate; 
	
	@ApiModelProperty(value="有效期")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	protected java.util.Date expiryDate; 
	
	@ApiModelProperty(value="备注")
	protected String memo;
	
	@ApiModelProperty(value="货主ID")
	protected String ownerId;

	 @ApiModelProperty(value = "商务分区")
	 private String businessDivision;
	 @ApiModelProperty(value = "省区")
	 private String province;
	 @ApiModelProperty(value = "商务分区ID")
	 private String businessDivisionId;
	 @ApiModelProperty(value = "省区ID")
	 private String provinceId;

	 @ApiModelProperty(value = "产品系列")
	 private String ProductLineName;

	 @ApiModelProperty(value = "产品系列id")
	 private String productLineId;


	 @ApiModelProperty(value="单位")
	 protected String unit;


	 @ApiModelProperty(value="货主ID")
	 protected String commonName;



	 @ApiModelProperty(value="规格")
	 protected String productState;

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

	 public String getCommonName() {
		 return commonName;
	 }

	 public void setCommonName(String commonName) {
		 this.commonName = commonName;
	 }

	 public String getProductLineName() {
		 return ProductLineName;
	 }

	 public void setProductLineName(String productLineName) {
		 ProductLineName = productLineName;
	 }

	 public String getProductLineId() {
		 return productLineId;
	 }

	 public void setProductLineId(String productLineId) {
		 this.productLineId = productLineId;
	 }

	 public String getBusinessDivision() {
		 return businessDivision;
	 }

	 public void setBusinessDivision(String businessDivision) {
		 this.businessDivision = businessDivision;
	 }

	 public String getProvince() {
		 return province;
	 }

	 public void setProvince(String province) {
		 this.province = province;
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

	 public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public java.util.Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(java.util.Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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
	
	public void setMonth(String month) {
		this.month = month;
	}
	
	/**
	 * 返回 月度
	 * @return
	 */
	public String getMonth() {
		return this.month;
	}
	
	public void setWeek(Integer week) {
		this.week = week;
	}
	
	/**
	 * 返回 周次
	 * @return
	 */
	public Integer getWeek() {
		return this.week;
	}
	
	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * 返回 开始日期
	 * @return
	 */
	public java.util.Date getStartDate() {
		return this.startDate;
	}
	
	public void setStopDate(java.util.Date stopDate) {
		this.stopDate = stopDate;
	}
	
	/**
	 * 返回 截止日期
	 * @return
	 */
	public java.util.Date getStopDate() {
		return this.stopDate;
	}
	
	public void setCommerceCode(String commerceCode) {
		this.commerceCode = commerceCode;
	}
	
	/**
	 * 返回 商业编码
	 * @return
	 */
	public String getCommerceCode() {
		return this.commerceCode;
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
	
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	/**
	 * 返回 产品编号
	 * @return
	 */
	public String getProductCode() {
		return this.productCode;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	/**
	 * 返回 产品名称
	 * @return
	 */
	public String getProductName() {
		return this.productName;
	}
	
	public void setInventoryDate(java.util.Date inventoryDate) {
		this.inventoryDate = inventoryDate;
	}
	
	/**
	 * 返回 库存日期
	 * @return
	 */
	public java.util.Date getInventoryDate() {
		return this.inventoryDate;
	}
	
	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}
	
	/**
	 * 返回 批号
	 * @return
	 */
	public String getBatchNum() {
		return this.batchNum;
	}
	
	public void setUpWeekInventory(Integer upWeekInventory) {
		this.upWeekInventory = upWeekInventory;
	}
	
	/**
	 * 返回 上周库存
	 * @return
	 */
	public Integer getUpWeekInventory() {
		return this.upWeekInventory;
	}
	
	public void setThisWeekInventory(Integer thisWeekInventory) {
		this.thisWeekInventory = thisWeekInventory;
	}
	
	/**
	 * 返回 本周库存
	 * @return
	 */
	public Integer getThisWeekInventory() {
		return this.thisWeekInventory;
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
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("year", this.year) 
		.append("month", this.month) 
		.append("week", this.week) 
		.append("startDate", this.startDate) 
		.append("stopDate", this.stopDate) 
		.append("commerceCode", this.commerceCode) 
		.append("commerceName", this.commerceName) 
		.append("productCode", this.productCode) 
		.append("productName", this.productName) 
		.append("inventoryDate", this.inventoryDate) 
		.append("batchNum", this.batchNum) 
		.append("upWeekInventory", this.upWeekInventory) 
		.append("thisWeekInventory", this.thisWeekInventory) 
		.append("createPersion", this.createPersion) 
		.append("createDate", this.createDate) 
		.toString();
	}
}