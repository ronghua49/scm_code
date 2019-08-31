package com.winway.scm.vo;
import java.io.Serializable;
import java.util.Date;

import com.hotent.base.model.BaseModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


 /**
 * 商品表
 * <pre> 
 * 描述：商品表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmZsjDrogPuchaseRecords",description = "药品采购记录视图") 
public class ScmZsjDrogPuchaseRecords implements Serializable{
	 
	 @ApiModelProperty(value="采购订单商品ID")
	 private String id;
	 @ApiModelProperty(value="采购订单号")
	 private String procurementCode;
	 @ApiModelProperty(value="采购合同")
	 private String contractCode;
	 @ApiModelProperty(value="购货日期")
	 private Date orderDate;
	 @ApiModelProperty(value="药品类型")
	 private String medicineType;
	 @ApiModelProperty(value="商品编码")
	 private String productCode;
	 @ApiModelProperty(value="商品系列")
	 private String productLineName;

	 @ApiModelProperty(value="商品名称")
	 private String productName;

	 @ApiModelProperty(value="药品经营范围")
	 private String businessScope;
	 @ApiModelProperty(value="商品分类")
	 private String medicineClassify;

	 @ApiModelProperty(value="商品系列ID")
	 private String productLineId;
	 @ApiModelProperty(value="通用名")
	 private String commonName;
	 @ApiModelProperty(value="规格")
	 private String productState;
	 @ApiModelProperty(value="剂型")
	 private String agentType;
	 @ApiModelProperty(value="生产厂商")
	 private String manufacturer;
	 @ApiModelProperty(value="供应单位")
	 private String supplier;
	 @ApiModelProperty(value="购货数量")
	 private int productSum;
	 @ApiModelProperty(value="单位")
	 private String unit;
	 @ApiModelProperty(value="批准文号")
	 private String approvalCode;
	 @ApiModelProperty(value="单价")
	 private double unitPrice;
	 @ApiModelProperty(value="总价")
	 private double sumPrice;
	 @ApiModelProperty(value="币种")
	 private String currency;
	 @ApiModelProperty(value="采购员")
	 private String operatorName;
	 @ApiModelProperty(value="货主ID")
	 private String ownerId;

	 public String getProductName() {
		 return productName;
	 }

	 public void setProductName(String productName) {
		 this.productName = productName;
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

	 public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getProcurementCode() {
		return procurementCode;
	}
	public void setProcurementCode(String procurementCode) {
		this.procurementCode = procurementCode;
	}
	public String getContractCode() {
		return contractCode;
	}
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getMedicineType() {
		return medicineType;
	}
	public void setMedicineType(String medicineType) {
		this.medicineType = medicineType;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductLineName() {
		return productLineName;
	}
	public void setProductLineName(String productLineName) {
		this.productLineName = productLineName;
	}
	public String getProductLineId() {
		return productLineId;
	}
	public void setProductLineId(String productLineId) {
		this.productLineId = productLineId;
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
	public String getAgentType() {
		return agentType;
	}
	public void setAgentType(String agentType) {
		this.agentType = agentType;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public int getProductSum() {
		return productSum;
	}
	public void setProductSum(int productSum) {
		this.productSum = productSum;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getApprovalCode() {
		return approvalCode;
	}
	public void setApprovalCode(String approvalCode) {
		this.approvalCode = approvalCode;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public double getSumPrice() {
		return sumPrice;
	}
	public void setSumPrice(double sumPrice) {
		this.sumPrice = sumPrice;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	
}