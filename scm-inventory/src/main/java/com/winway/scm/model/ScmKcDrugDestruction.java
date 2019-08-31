package com.winway.scm.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 不合格药品销毁明细表
 * <pre> 
 * 描述：不合格药品销毁明细表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-13 15:06:37
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmKcDrugDestruction",description = "不合格药品销毁明细表") 
public class ScmKcDrugDestruction extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="主表ID")
	protected String masterId; 
	
	@ApiModelProperty(value="药品名称",required = true)
	protected String productName; 
	
	@ApiModelProperty(value="药品编号",required = true)
	protected String productCode; 
	
	@ApiModelProperty(value="药品规格",required = true)
	protected String productState; 
	
	@ApiModelProperty(value="药品数量",required = true)
	protected Integer productSum; 
	
	@ApiModelProperty(value="药品单价",required = true)
	protected Double unitPrice; 
	
	@ApiModelProperty(value="药品总价",required = true)
	protected Double priceSum; 
	
	@ApiModelProperty(value="药品类型",required = true)
	protected String medicineType; 
	
	@ApiModelProperty(value="生产厂家",required = true)
	protected String manufacturer; 
	
	@ApiModelProperty(value="批号",required = true)
	protected String batchNum; 
	
	@ApiModelProperty(value="失效日期",required = true)
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	protected java.util.Date expiryDate;

	 @ApiModelProperty(value="件装量",required = true)
	 protected Integer aChage;

	 @ApiModelProperty(value="件数",required = true)
	 protected Integer number ;

	 @ApiModelProperty(value="不合格原因",required = true)
	 protected String unqualifiedReason;

	 @ApiModelProperty(value="销毁方式",required = true)
	 protected String destroyWay;


	 @ApiModelProperty(value="剂型")
	 protected String agentType;
	 @ApiModelProperty(value="批准文号")
	 protected String approvalCode;
	 @ApiModelProperty(value="经营范围")
	 protected String businessScope;
	 @ApiModelProperty(value="药品类型")
	 protected String medicineClassify;
	 @ApiModelProperty(value="通用名称",required = true)
	 protected String commonName;
	 @ApiModelProperty(value="单位",required = true)
	 protected String unit;



	 public String getAgentType() {
		 return agentType;
	 }

	 public void setAgentType(String agentType) {
		 this.agentType = agentType;
	 }

	 public String getApprovalCode() {
		 return approvalCode;
	 }

	 public void setApprovalCode(String approvalCode) {
		 this.approvalCode = approvalCode;
	 }

	 public String getBusinessScope() {
		 return businessScope;
	 }

	 public void setBusinessScope(String businessScope) {
		 this.businessScope = businessScope;
	 }

	 public String getMedicineClassify() {
		 return medicineClassify;
	 }

	 public void setMedicineClassify(String medicineClassify) {
		 this.medicineClassify = medicineClassify;
	 }

	 public String getCommonName() {
		 return commonName;
	 }

	 public void setCommonName(String commonName) {
		 this.commonName = commonName;
	 }

	 public String getUnit() {
		 return unit;
	 }

	 public void setUnit(String unit) {
		 this.unit = unit;
	 }

	 public String getUnqualifiedReason() {
		 return unqualifiedReason;
	 }

	 public void setUnqualifiedReason(String unqualifiedReason) {
		 this.unqualifiedReason = unqualifiedReason;
	 }

	 public String getDestroyWay() {
		 return destroyWay;
	 }

	 public void setDestroyWay(String destroyWay) {
		 this.destroyWay = destroyWay;
	 }

	 public Integer getaChage() {
		 return aChage;
	 }

	 public void setaChage(Integer aChage) {
		 this.aChage = aChage;
	 }

	 public Integer getNumber() {
		 return number;
	 }

	 public void setNumber(Integer number) {
		 this.number = number;
	 }

	 public Double getPriceSum() {
		return priceSum;
	}

	public void setPriceSum(Double priceSum) {
		this.priceSum = priceSum;
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
	
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	/**
	 * 返回 药品编号
	 * @return
	 */
	public String getProductCode() {
		return this.productCode;
	}
	
	public void setProductState(String productState) {
		this.productState = productState;
	}
	
	/**
	 * 返回 药品规格
	 * @return
	 */
	public String getProductState() {
		return this.productState;
	}
	
	public void setProductSum(Integer productSum) {
		this.productSum = productSum;
	}
	
	/**
	 * 返回 药品数量
	 * @return
	 */
	public Integer getProductSum() {
		return this.productSum;
	}
	
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	/**
	 * 返回 药品单价
	 * @return
	 */
	public Double getUnitPrice() {
		return this.unitPrice;
	}
	
	public void setMedicineType(String medicineType) {
		this.medicineType = medicineType;
	}
	
	/**
	 * 返回 药品类型
	 * @return
	 */
	public String getMedicineType() {
		return this.medicineType;
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
	
	public void setExpiryDate(java.util.Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	/**
	 * 返回 失效日期
	 * @return
	 */
	public java.util.Date getExpiryDate() {
		return this.expiryDate;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("masterId", this.masterId) 
		.append("productName", this.productName) 
		.append("productCode", this.productCode) 
		.append("productState", this.productState) 
		.append("productSum", this.productSum) 
		.append("unitPrice", this.unitPrice) 
		.append("medicineType", this.medicineType) 
		.append("manufacturer", this.manufacturer) 
		.append("batchNum", this.batchNum) 
		.append("expiryDate", this.expiryDate) 
		.toString();
	}
}