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
 @ApiModel(value = "ScmZsjDrogPuchaseReturn",description = "药品采购退货视图") 
public class ScmZsjDrogPuchaseReturn implements Serializable{
	 
	 @ApiModelProperty(value="采购退货商品ID")
	 private String id;
	 @ApiModelProperty(value="采购退货单")
	 private String returnCode;
	 @ApiModelProperty(value="采购退货时间")
	 private Date returnDate;
	 @ApiModelProperty(value="商品编号")
	 private String productCode;
	 @ApiModelProperty(value="药品类型")
	 private String medicineType;
	 @ApiModelProperty(value="商品系列")
	 private String productLineName;
	 @ApiModelProperty(value="商品系列ID")
	 private String productLineId;
	 @ApiModelProperty(value="商品通用名称")
	 private String commonName;
	 @ApiModelProperty(value="规格")
	 private String productState;
	 @ApiModelProperty(value="剂型")
	 private String agentType;
	 @ApiModelProperty(value="生产厂商")
	 private String manufacturer;
	 @ApiModelProperty(value="供应单位")
	 private String supplier;
	 @ApiModelProperty(value="批号")
	 private String batchNum;
	 @ApiModelProperty(value="生产日期")
	 private String producedDate;
	 @ApiModelProperty(value="有效期")
	 private String validity;
	 @ApiModelProperty(value="退货数量")
	 private String returnNumber;
	 @ApiModelProperty(value="单位")
	 private String unit;
	 @ApiModelProperty(value="单价")
	 private String unitPrice;
	 @ApiModelProperty(value="总价")
	 private String sumPrice;
	 @ApiModelProperty(value="退货原因")
	 private String returnRequestCause;
	 @ApiModelProperty(value="申请人")
	 private String operatorName;
	 @ApiModelProperty(value="备注")
	 private String memo;
	 @ApiModelProperty(value="货主id")
	 private String ownerId;

	 public String getOwnerId() {
		 return ownerId;
	 }

	 public void setOwnerId(String ownerId) {
		 this.ownerId = ownerId;
	 }

	 public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getMedicineType() {
		return medicineType;
	}
	public void setMedicineType(String medicineType) {
		this.medicineType = medicineType;
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
	public String getBatchNum() {
		return batchNum;
	}
	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}
	public String getProducedDate() {
		return producedDate;
	}
	public void setProducedDate(String producedDate) {
		this.producedDate = producedDate;
	}
	public String getValidity() {
		return validity;
	}
	public void setValidity(String validity) {
		this.validity = validity;
	}
	public String getReturnNumber() {
		return returnNumber;
	}
	public void setReturnNumber(String returnNumber) {
		this.returnNumber = returnNumber;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getSumPrice() {
		return sumPrice;
	}
	public void setSumPrice(String sumPrice) {
		this.sumPrice = sumPrice;
	}
	public String getReturnRequestCause() {
		return returnRequestCause;
	}
	public void setReturnRequestCause(String returnRequestCause) {
		this.returnRequestCause = returnRequestCause;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
}