package com.winway.scm.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 药品停售通知
 * <pre> 
 * 描述：药品停售通知 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-24 12:00:21
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmGspHaltSales",description = "药品停售通知") 
public class ScmGspHaltSales extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="产品名称",required = true)
	protected String productName; 
	
	@ApiModelProperty(value="规格",required = true)
	protected String productState; 
	
	@ApiModelProperty(value="单位",required = true)
	protected String unit; 
	
	@ApiModelProperty(value="生产厂家",required = true)
	protected String manufacturer; 
	
	@ApiModelProperty(value="库存数量",required = true)
	protected Integer inventorySum; 
	
	@ApiModelProperty(value="药品类型",required = true)
	protected String medicineType; 
	
	@ApiModelProperty(value="批号",required = true)
	protected String batchNumber; 
	
	@ApiModelProperty(value="停售日期",required = true)
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date haltSalesDate; 
	
	@ApiModelProperty(value="停售说明")
	protected String haltSalesState; 
	
	@ApiModelProperty(value="停售备注")
	protected String haltSalesMemo; 
	
	@ApiModelProperty(value="解除停售日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date relieveHaltSalesDate; 
	
	@ApiModelProperty(value="解除停售说明")
	protected String relieveHaltSalesState; 
	
	@ApiModelProperty(value="解除停售备注")
	protected String relieveHaltSalesMemo;
	
	@ApiModelProperty(value="创建人")
	protected String createPersion; 
	
	@ApiModelProperty(value="创建时间")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date createDate; 
	
	@ApiModelProperty(value="审批ID")
	protected String approvalId; 
	
	@ApiModelProperty(value="审批状态")
	protected String approvalState; 
	
	@ApiModelProperty(value="货主ID",required = true)
	protected String ownerId;

	 @ApiModelProperty(value="是否解除停售 0:未解除，1:解除",required = false)
	 protected String state="0";

	 @ApiModelProperty(value="是否推送值SMS 0未推送；1已推送",required = true)
	 protected String isSMS="0";

	 @ApiModelProperty(value="停售单号",required = true)
	 protected String haltCode;


	 @ApiModelProperty(value="剂型")
	 protected String AgentType;

	 @ApiModelProperty(value="经营范围")
	 protected String businessScope;

	 @ApiModelProperty(value="通用名")
	 protected String commonName;

	 @ApiModelProperty(value="药品类型")
	 protected String medicineClassify;

	 @ApiModelProperty(value="药品标号")
	 protected String productCode;
	 @ApiModelProperty(value="批准文号")
	 protected String approvalCode;
	 
	 @ApiModelProperty(value="wms处理状态(0:未处理,1:已停售,2:已解除停售)")
	 protected String isAffirmWms;
	 
	 @ApiModelProperty(value="商品子码")
	 protected String code;

	 public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIsAffirmWms() {
		return isAffirmWms;
	}

	public void setIsAffirmWms(String isAffirmWms) {
		this.isAffirmWms = isAffirmWms;
	}

	public String getHaltCode() {
		 return haltCode;
	 }

	 public void setHaltCode(String haltCode) {
		 this.haltCode = haltCode;
	 }

	 public String getAgentType() {
		 return AgentType;
	 }

	 public void setAgentType(String agentType) {
		 AgentType = agentType;
	 }

	 public String getBusinessScope() {
		 return businessScope;
	 }

	 public void setBusinessScope(String businessScope) {
		 this.businessScope = businessScope;
	 }

	 public String getCommonName() {
		 return commonName;
	 }

	 public void setCommonName(String commonName) {
		 this.commonName = commonName;
	 }

	 public String getMedicineClassify() {
		 return medicineClassify;
	 }

	 public void setMedicineClassify(String medicineClassify) {
		 this.medicineClassify = medicineClassify;
	 }

	 public String getProductCode() {
		 return productCode;
	 }

	 public void setProductCode(String productCode) {
		 this.productCode = productCode;
	 }

	 public String getApprovalCode() {
		 return approvalCode;
	 }

	 public void setApprovalCode(String approvalCode) {
		 this.approvalCode = approvalCode;
	 }

	 public String getIsSMS() {
		 return isSMS;
	 }

	 public void setIsSMS(String isSMS) {
		 this.isSMS = isSMS;
	 }

	 public String getState() {
		 return state;
	 }

	 public void setState(String state) {
		 this.state = state;
	 }

	 public void setId(String id) {
		this.id = id;
	}
	
	
	public String getApprovalId() {
		return approvalId;
	}


	public void setApprovalId(String approvalId) {
		this.approvalId = approvalId;
	}


	public String getApprovalState() {
		return approvalState;
	}


	public void setApprovalState(String approvalState) {
		this.approvalState = approvalState;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	/**
	 * 返回 id
	 * @return
	 */
	public String getId() {
		return this.id;
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
	
	public void setProductState(String productState) {
		this.productState = productState;
	}
	
	/**
	 * 返回 规格
	 * @return
	 */
	public String getProductState() {
		return this.productState;
	}
	
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	/**
	 * 返回 单位
	 * @return
	 */
	public String getUnit() {
		return this.unit;
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
	
	public void setInventorySum(Integer inventorySum) {
		this.inventorySum = inventorySum;
	}
	
	/**
	 * 返回 库存数量
	 * @return
	 */
	public Integer getInventorySum() {
		return this.inventorySum;
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
	
	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}
	
	/**
	 * 返回 批号
	 * @return
	 */
	public String getBatchNumber() {
		return this.batchNumber;
	}
	
	public void setHaltSalesDate(java.util.Date haltSalesDate) {
		this.haltSalesDate = haltSalesDate;
	}
	
	/**
	 * 返回 停售日期
	 * @return
	 */
	public java.util.Date getHaltSalesDate() {
		return this.haltSalesDate;
	}
	
	public void setHaltSalesState(String haltSalesState) {
		this.haltSalesState = haltSalesState;
	}
	
	/**
	 * 返回 停售说明
	 * @return
	 */
	public String getHaltSalesState() {
		return this.haltSalesState;
	}
	
	public void setHaltSalesMemo(String haltSalesMemo) {
		this.haltSalesMemo = haltSalesMemo;
	}
	
	/**
	 * 返回 停售备注
	 * @return
	 */
	public String getHaltSalesMemo() {
		return this.haltSalesMemo;
	}
	
	public void setRelieveHaltSalesDate(java.util.Date relieveHaltSalesDate) {
		this.relieveHaltSalesDate = relieveHaltSalesDate;
	}
	
	/**
	 * 返回 解除停售日期
	 * @return
	 */
	public java.util.Date getRelieveHaltSalesDate() {
		return this.relieveHaltSalesDate;
	}
	
	public void setRelieveHaltSalesState(String relieveHaltSalesState) {
		this.relieveHaltSalesState = relieveHaltSalesState;
	}
	
	/**
	 * 返回 解除停售说明
	 * @return
	 */
	public String getRelieveHaltSalesState() {
		return this.relieveHaltSalesState;
	}

	 public String getRelieveHaltSalesMemo() {
		 return relieveHaltSalesMemo;
	 }

	 public void setRelieveHaltSalesMemo(String relieveHaltSalesMemo) {
		 this.relieveHaltSalesMemo = relieveHaltSalesMemo;
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
		.append("productName", this.productName) 
		.append("productState", this.productState) 
		.append("unit", this.unit) 
		.append("manufacturer", this.manufacturer) 
		.append("inventorySum", this.inventorySum) 
		.append("medicineType", this.medicineType) 
		.append("batchNumber", this.batchNumber) 
		.append("haltSalesDate", this.haltSalesDate) 
		.append("haltSalesState", this.haltSalesState) 
		.append("haltSalesMemo", this.haltSalesMemo) 
		.append("relieveHaltSalesDate", this.relieveHaltSalesDate) 
		.append("relieveHaltSalesState", this.relieveHaltSalesState) 
		.append("relieveRaltSalesMemo", this.relieveHaltSalesMemo)
		.append("createPersion", this.createPersion) 
		.append("createDate", this.createDate) 
		.append("ownerId", this.ownerId) 
		.toString();
	}
}