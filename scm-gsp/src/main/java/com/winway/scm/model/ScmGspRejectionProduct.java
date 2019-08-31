package com.winway.scm.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;

import java.util.Date;


/**
 * 药品拒收商品表
 * <pre> 
 * 描述：药品拒收商品表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-24 12:00:21
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmGspRejectionProduct",description = "药品拒收商品表") 
public class ScmGspRejectionProduct extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="主表ID")
	protected String masterId; 
	
	@ApiModelProperty(value="商品编码",required = true)
	protected String productCode; 
	
	@ApiModelProperty(value="商品名称",required = true)
	protected String productName; 
	
	@ApiModelProperty(value="通用名",required = true)
	protected String commonName; 
	
	@ApiModelProperty(value="药品类型",required = true)
	protected String medicineType; 
	
	@ApiModelProperty(value="规格",required = true)
	protected String productState; 
	
	@ApiModelProperty(value="单位",required = true)
	protected String unit; 
	
	@ApiModelProperty(value="生产厂商",required = true)
	protected String manufacturer;

	@ApiModelProperty(value="采购数量",required = true)
	protected Integer purchaseSum;

	@ApiModelProperty(value="已拒数量",required = true)
	protected Integer rejectionTotal;

	@ApiModelProperty(value="拒收数量",required = true)
	protected Integer rejectionSum; 
	
	@ApiModelProperty(value="单价",required = true)
	protected Double price; 
	
	@ApiModelProperty(value="金额",required = true)
	protected Double priceSum; 
	
	@ApiModelProperty(value="拒收原因",required = true)
	protected String rejectionCause; 
	
	@ApiModelProperty(value="备注")
	protected String memo;


	//首页主表字段

	 @ApiModelProperty(value="供应商",required = true)
	 protected String supplierName;

	 @ApiModelProperty(value="供应商ID",required = true)
	 protected String supplierId;

	 @ApiModelProperty(value="供应商编号",required = true)
	 protected String supplierCode;

	 @ApiModelProperty(value="收货仓库",required = true)
	 protected String getMoneyWarehouse;

	 @ApiModelProperty(value="收货仓库编号",required = true)
	 protected String getMoneyWarehouseCode;

	 @ApiModelProperty(value="采购合同编号",required = true)
	 protected String contractCode;

	 @ApiModelProperty(value="采购订单编号",required = true)
	 protected String procurementCode;

	 @ApiModelProperty(value="货主ID",required = true)
	 protected String ownerId;

	 @ApiModelProperty(value="审批ID")
	 protected String approvalId;

	 @ApiModelProperty(value="审批状态")
	 protected String approvalState;

	 @ApiModelProperty(value="创建人")
	 protected String createPersion;

	 @ApiModelProperty(value="拒收单号")
	 protected String rejectionCode;

	 @ApiModelProperty(value="创建时间")
	 @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	 protected java.util.Date createDate;

	@ApiModelProperty(value="到货日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date deliveryDate;
	@ApiModelProperty(value="剂型")
	protected String agentType;
	@ApiModelProperty(value="批准文号")
	protected String approvalCode;
	@ApiModelProperty(value="经营范围")
	protected String businessScope;
	@ApiModelProperty(value="药品类型")
	protected String medicineClassify;

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

	public Integer getRejectionTotal() {
		return rejectionTotal;
	}

	public void setRejectionTotal(Integer rejectionTotal) {
		this.rejectionTotal = rejectionTotal;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getGetMoneyWarehouse() {
		return getMoneyWarehouse;
	}

	public void setGetMoneyWarehouse(String getMoneyWarehouse) {
		this.getMoneyWarehouse = getMoneyWarehouse;
	}

	public String getGetMoneyWarehouseCode() {
		return getMoneyWarehouseCode;
	}

	public void setGetMoneyWarehouseCode(String getMoneyWarehouseCode) {
		this.getMoneyWarehouseCode = getMoneyWarehouseCode;
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public String getProcurementCode() {
		return procurementCode;
	}

	public void setProcurementCode(String procurementCode) {
		this.procurementCode = procurementCode;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
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

	public String getCreatePersion() {
		return createPersion;
	}

	public void setCreatePersion(String createPersion) {
		this.createPersion = createPersion;
	}

	public String getRejectionCode() {
		return rejectionCode;
	}

	public void setRejectionCode(String rejectionCode) {
		this.rejectionCode = rejectionCode;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
	
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	/**
	 * 返回 商品编码
	 * @return
	 */
	public String getProductCode() {
		return this.productCode;
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
	
	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}
	
	/**
	 * 返回 通用名
	 * @return
	 */
	public String getCommonName() {
		return this.commonName;
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
	 * 返回 生产厂商
	 * @return
	 */
	public String getManufacturer() {
		return this.manufacturer;
	}
	
	public void setPurchaseSum(Integer purchaseSum) {
		this.purchaseSum = purchaseSum;
	}
	
	/**
	 * 返回 采购数量
	 * @return
	 */
	public Integer getPurchaseSum() {
		return this.purchaseSum;
	}
	
	public void setRejectionSum(Integer rejectionSum) {
		this.rejectionSum = rejectionSum;
	}
	
	/**
	 * 返回 拒收数量
	 * @return
	 */
	public Integer getRejectionSum() {
		return this.rejectionSum;
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
	 * 返回 金额
	 * @return
	 */
	public Double getPriceSum() {
		return this.priceSum;
	}
	
	public void setRejectionCause(String rejectionCause) {
		this.rejectionCause = rejectionCause;
	}
	
	/**
	 * 返回 拒收原因
	 * @return
	 */
	public String getRejectionCause() {
		return this.rejectionCause;
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
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("masterId", this.masterId) 
		.append("productCode", this.productCode) 
		.append("productName", this.productName) 
		.append("commonName", this.commonName) 
		.append("medicineType", this.medicineType) 
		.append("productState", this.productState) 
		.append("unit", this.unit) 
		.append("manufacturer", this.manufacturer) 
		.append("purchaseSum", this.purchaseSum) 
		.append("rejectionSum", this.rejectionSum) 
		.append("price", this.price) 
		.append("priceSum", this.priceSum) 
		.append("rejectionCause", this.rejectionCause) 
		.append("memo", this.memo) 
		.toString();
	}
}