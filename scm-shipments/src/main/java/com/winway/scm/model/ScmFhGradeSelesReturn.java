package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;


 /**
 * 当次退货
 * <pre> 
 * 描述：当次退货 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-13 15:47:33
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmFhGradeSelesReturn",description = "当次退货") 
public class ScmFhGradeSelesReturn extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="主表ID")
	protected String masterId; 
	
	@ApiModelProperty(value="指令编号")
	protected String directiveCode; 
	
	@ApiModelProperty(value="药品名称",required = true)
	protected String productName; 
	
	@ApiModelProperty(value="药品编号",required = true)
	protected String productCode; 
	
	@ApiModelProperty(value="件装量",required = true)
	protected Integer aChange; 
	
	@ApiModelProperty(value="规格型号",required = true)
	protected String productState; 
	
	@ApiModelProperty(value="数量",required = true)
	protected Integer productSum; 
	
	@ApiModelProperty(value="单价",required = true)
	protected Double price; 
	
	@ApiModelProperty(value="退货金额" ,required = true)
	protected Double priceSum; 
	
	@ApiModelProperty(value="批号" ,required = true)
	protected String batchNumber; 
	
	@ApiModelProperty(value="实际发货日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date actualDeliveryDate; 
	
	@ApiModelProperty(value="退货原因",required = true)
	protected String returnMessage; 
	
	@ApiModelProperty(value="备注")
	protected String memo; 
	
	@ApiModelProperty(value="发货表ID",required = true)
	protected String shipmentsId;


	 @ApiModelProperty(value="发货单号",required = true)
	 protected String  shipmentsCode;

	 @ApiModelProperty(value="发货量",required = true)
	 protected Integer deliveringAmount;


	 @ApiModelProperty(value="已退数量",required = true)
	 protected Integer returnSum;


	 @ApiModelProperty(value = "通用名")
	 protected String commonName;

	 @ApiModelProperty(value = "生产厂商")
	 protected String manufacturer;
	 @ApiModelProperty(value="单位")
	 protected String unit;
	 @ApiModelProperty(value="剂型")
	 protected String AgentType;
	 @ApiModelProperty(value="批准文号")
	 protected String approvalCode;
	 @ApiModelProperty(value="药品分类")
	 protected String medicineClassify;
	 @ApiModelProperty(value="经营范围")
	 protected String businessScope;
	 @ApiModelProperty(value="药品类型")
	 protected String medicineType;

	 @ApiModelProperty(value="商品字码")
	 protected String code;
	 
	 @ApiModelProperty(value="仓库编号")
	 protected String houstWareCode;
	 
	 public String getHoustWareCode() {
		return houstWareCode;
	}

	public void setHoustWareCode(String houstWareCode) {
		this.houstWareCode = houstWareCode;
	}

	public String getCode() {
		 return code;
	 }

	 public void setCode(String code) {
		 this.code = code;
	 }

	 public Integer getaChange() {
		return aChange;
	}

	public void setaChange(Integer aChange) {
		this.aChange = aChange;
	}

	public String getMedicineType() {
		return medicineType;
	}

	public void setMedicineType(String medicineType) {
		this.medicineType = medicineType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	 public String getCommonName() {
		 return commonName;
	 }

	 public void setCommonName(String commonName) {
		 this.commonName = commonName;
	 }

	 public String getManufacturer() {
		 return manufacturer;
	 }

	 public void setManufacturer(String manufacturer) {
		 this.manufacturer = manufacturer;
	 }

	 public Integer getReturnSum() {
		 return returnSum;
	 }

	 public void setReturnSum(Integer returnSum) {
		 this.returnSum = returnSum;
	 }

	 public String getShipmentsCode() {
		 return shipmentsCode;
	 }

	 public void setShipmentsCode(String shipmentsCode) {
		 this.shipmentsCode = shipmentsCode;
	 }

	 public Integer getDeliveringAmount() {
		 return deliveringAmount;
	 }

	 public void setDeliveringAmount(Integer deliveringAmount) {
		 this.deliveringAmount = deliveringAmount;
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
	
	public void setDirectiveCode(String directiveCode) {
		this.directiveCode = directiveCode;
	}
	
	/**
	 * 返回 指令编号
	 * @return
	 */
	public String getDirectiveCode() {
		return this.directiveCode;
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
	
	public void setAChange(Integer aChange) {
		this.aChange = aChange;
	}
	
	/**
	 * 返回 件装量
	 * @return
	 */
	public Integer getAChange() {
		return this.aChange;
	}
	
	public void setProductState(String productState) {
		this.productState = productState;
	}
	
	/**
	 * 返回 规格型号
	 * @return
	 */
	public String getProductState() {
		return this.productState;
	}
	
	public void setProductSum(Integer productSum) {
		this.productSum = productSum;
	}
	
	/**
	 * 返回 数量
	 * @return
	 */
	public Integer getProductSum() {
		return this.productSum;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	/**
	 * 返回 单价
	 * @return
	 */
	public Double getPrice() {
		return this.price;
	}
	
	public void setPriceSum(Double priceSum) {
		this.priceSum = priceSum;
	}
	
	/**
	 * 返回 退货金额
	 * @return
	 */
	public Double getPriceSum() {
		return this.priceSum;
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
	
	public void setActualDeliveryDate(java.util.Date actualDeliveryDate) {
		this.actualDeliveryDate = actualDeliveryDate;
	}
	
	/**
	 * 返回 实际发货日期
	 * @return
	 */
	public java.util.Date getActualDeliveryDate() {
		return this.actualDeliveryDate;
	}
	
	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}
	
	/**
	 * 返回 退货原因
	 * @return
	 */
	public String getReturnMessage() {
		return this.returnMessage;
	}
	
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	/**
	 * 返回 备注
	 * @return
	 */
	public String getMemo() {
		return this.memo;
	}
	
	public void setShipmentsId(String shipmentsId) {
		this.shipmentsId = shipmentsId;
	}
	
	/**
	 * 返回 发货表ID
	 * @return
	 */
	public String getShipmentsId() {
		return this.shipmentsId;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("masterId", this.masterId) 
		.append("directiveCode", this.directiveCode) 
		.append("productName", this.productName) 
		.append("productCode", this.productCode) 
		.append("aChange", this.aChange) 
		.append("productState", this.productState) 
		.append("productSum", this.productSum) 
		.append("price", this.price) 
		.append("priceSum", this.priceSum) 
		.append("batchNumber", this.batchNumber) 
		.append("actualDeliveryDate", this.actualDeliveryDate) 
		.append("returnMessage", this.returnMessage) 
		.append("memo", this.memo) 
		.append("shipmentsId", this.shipmentsId) 
		.toString();
	}
}