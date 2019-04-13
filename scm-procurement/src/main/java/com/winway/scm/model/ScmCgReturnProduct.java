package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;


 /**
 * 采购退货商品表
 * <pre> 
 * 描述：采购退货商品表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-26 13:47:44
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
 @ApiModel(value = "ScmCgReturnProduct",description = "采购退货商品表") 
public class ScmCgReturnProduct extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="批号")
	protected String batchNum; 
	
	@ApiModelProperty(value="商品ID")
	protected String productId; 
	
	@ApiModelProperty(value="商品名称")
	protected String productName; 
	
	@ApiModelProperty(value="药品类型ID")
	protected String medicineTypeId; 
	
	@ApiModelProperty(value="药品类型")
	protected String medicineType; 
	
	@ApiModelProperty(value="件装量")
	protected String achange; 
	
	@ApiModelProperty(value="单位")
	protected String unit; 
	
	@ApiModelProperty(value="生产厂家")
	protected String manufacturer; 
	
	@ApiModelProperty(value="生产日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date producedDate; 
	
	@ApiModelProperty(value="有效期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date validity; 
	
	@ApiModelProperty(value="仓库ID")
	protected String getMoneyWarehouseId; 
	
	@ApiModelProperty(value="仓库")
	protected String getMoneyWarehouse; 
	
	@ApiModelProperty(value="入库日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date inboundDate; 
	
	@ApiModelProperty(value="退货数量")
	protected Integer returnNumber; 
	
	@ApiModelProperty(value="退货日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date returnDate; 
	
	@ApiModelProperty(value="单价")
	protected Double unitPrice; 
	
	@ApiModelProperty(value="退货总金额")
	protected Double sumPrice; 
	
	@ApiModelProperty(value="退货原因ID")
	protected String returnRequestCauseId; 
	
	@ApiModelProperty(value="退货原因")
	protected String returnRequestCause; 
	
	@ApiModelProperty(value="备注")
	protected String memo; 
	
	@ApiModelProperty(value="退货表ID")
	protected String proReturnId; 
	
	@ApiModelProperty(value="退货单Id")
	protected String returnId; 
	
	@ApiModelProperty(value="退货单号")
	protected String returnCode; 
	
	
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
	
	public void setMedicineTypeId(String medicineTypeId) {
		this.medicineTypeId = medicineTypeId;
	}
	
	/**
	 * 返回 药品类型ID
	 * @return
	 */
	public String getMedicineTypeId() {
		return this.medicineTypeId;
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
	
	public void setAchange(String achange) {
		this.achange = achange;
	}
	
	/**
	 * 返回 件装量
	 * @return
	 */
	public String getAchange() {
		return this.achange;
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
	
	public void setProducedDate(java.util.Date producedDate) {
		this.producedDate = producedDate;
	}
	
	/**
	 * 返回 生产日期
	 * @return
	 */
	public java.util.Date getProducedDate() {
		return this.producedDate;
	}
	
	public void setValidity(java.util.Date validity) {
		this.validity = validity;
	}
	
	/**
	 * 返回 有效期
	 * @return
	 */
	public java.util.Date getValidity() {
		return this.validity;
	}
	
	public void setGetMoneyWarehouseId(String getMoneyWarehouseId) {
		this.getMoneyWarehouseId = getMoneyWarehouseId;
	}
	
	/**
	 * 返回 仓库ID
	 * @return
	 */
	public String getGetMoneyWarehouseId() {
		return this.getMoneyWarehouseId;
	}
	
	public void setGetMoneyWarehouse(String getMoneyWarehouse) {
		this.getMoneyWarehouse = getMoneyWarehouse;
	}
	
	/**
	 * 返回 仓库
	 * @return
	 */
	public String getGetMoneyWarehouse() {
		return this.getMoneyWarehouse;
	}
	
	public void setInboundDate(java.util.Date inboundDate) {
		this.inboundDate = inboundDate;
	}
	
	/**
	 * 返回 入库日期
	 * @return
	 */
	public java.util.Date getInboundDate() {
		return this.inboundDate;
	}
		
	public Integer getReturnNumber() {
		return returnNumber;
	}
	
	/**
	 * 返回 退货数量
	 * @return
	 */
	public void setReturnNumber(Integer returnNumber) {
		this.returnNumber = returnNumber;
	}

	public void setReturnDate(java.util.Date returnDate) {
		this.returnDate = returnDate;
	}
	
	/**
	 * 返回 退货日期
	 * @return
	 */
	public java.util.Date getReturnDate() {
		return this.returnDate;
	}
	
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	/**
	 * 返回 单价
	 * @return
	 */
	public Double getUnitPrice() {
		return this.unitPrice;
	}
	
	public void setSumPrice(Double sumPrice) {
		this.sumPrice = sumPrice;
	}
	
	/**
	 * 返回 退货总金额
	 * @return
	 */
	public Double getSumPrice() {
		return this.sumPrice;
	}
	
	public void setReturnRequestCauseId(String returnRequestCauseId) {
		this.returnRequestCauseId = returnRequestCauseId;
	}
	
	/**
	 * 返回 退货原因ID
	 * @return
	 */
	public String getReturnRequestCauseId() {
		return this.returnRequestCauseId;
	}
	
	public void setReturnRequestCause(String returnRequestCause) {
		this.returnRequestCause = returnRequestCause;
	}
	
	/**
	 * 返回 退货原因
	 * @return
	 */
	public String getReturnRequestCause() {
		return this.returnRequestCause;
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
	
	public void setProReturnId(String proReturnId) {
		this.proReturnId = proReturnId;
	}
	
	/**
	 * 返回 退货表ID
	 * @return
	 */
	public String getProReturnId() {
		return this.proReturnId;
	}
	
	public void setReturnId(String returnId) {
		this.returnId = returnId;
	}
	
	/**
	 * 返回 退货单Id
	 * @return
	 */
	public String getReturnId() {
		return this.returnId;
	}
	
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	
	/**
	 * 返回 退货单号
	 * @return
	 */
	public String getReturnCode() {
		return this.returnCode;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("batchNum", this.batchNum) 
		.append("productId", this.productId) 
		.append("productName", this.productName) 
		.append("medicineTypeId", this.medicineTypeId) 
		.append("medicineType", this.medicineType) 
		.append("achange", this.achange) 
		.append("unit", this.unit) 
		.append("manufacturer", this.manufacturer) 
		.append("producedDate", this.producedDate) 
		.append("validity", this.validity) 
		.append("getMoneyWarehouseId", this.getMoneyWarehouseId) 
		.append("getMoneyWarehouse", this.getMoneyWarehouse) 
		.append("inboundDate", this.inboundDate) 
		.append("returnDate", this.returnDate) 
		.append("unitPrice", this.unitPrice) 
		.append("sumPrice", this.sumPrice) 
		.append("returnRequestCauseId", this.returnRequestCauseId) 
		.append("returnRequestCause", this.returnRequestCause) 
		.append("memo", this.memo) 
		.append("proReturnId", this.proReturnId) 
		.append("returnId", this.returnId) 
		.append("returnCode", this.returnCode) 
		.toString();
	}
}