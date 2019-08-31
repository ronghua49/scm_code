package com.winway.scm.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 采购订单表
 * 
 * <pre>
 *  
 * 描述：采购订单表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-26 13:47:44
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
@ApiModel(value = "ScmCgProcurementOrder", description = "采购订单表")
public class ScmCgProcurementOrder extends BaseModel<String> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	protected String id;

	@ApiModelProperty(value = "采购订单号")
	protected String procurementCode;

	@ApiModelProperty(value = "供应商id")
	protected String supplierId;

	@ApiModelProperty(value = "供应商名称")
	protected String supplier;

	@ApiModelProperty(value = "合同编号")
	protected String contractCode;

	@ApiModelProperty(value = "品种数")
	protected Integer speciesSum;

	@ApiModelProperty(value = "数量")
	protected Integer productSum;

	@ApiModelProperty(value = "金额")
	protected Double sumPrice;

	@ApiModelProperty(value = "币种")
	protected String currency;

	@ApiModelProperty(value = "下单日期")
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	protected java.util.Date orderDate;

	@ApiModelProperty(value = "是否推送至WMS")
	protected char isToWMS;

	@ApiModelProperty(value = "WMS处理状态(0:未处理 ,1:已处理)")
	protected String isAffirmWms;

	@ApiModelProperty(value = "审批ID")
	protected String approvalId;

	@ApiModelProperty(value = "sap是否处理(0:未处理,1:已处理)")
	protected char isManageSap;

	@ApiModelProperty(value = "审批状态")
	protected String approvalState;

	@ApiModelProperty(value = "收票方")
	protected String getTicket;

	@ApiModelProperty(value = "货主ID")
	protected String ownerId;

	@ApiModelProperty(value = "操作人ID")
	protected String operatorId;

	@ApiModelProperty(value = "操作人")
	protected String operatorName;

	@ApiModelProperty(value = "采购合同ID")
	protected String contractId;

	@ApiModelProperty(value = "收货仓库")
	protected String getMoneyWarehouse;

	@ApiModelProperty(value = "收货仓库编码")
	protected String warehouseCode;

	@ApiModelProperty(value = "备注")
	protected String memo;

	public String getMemo() {
		return memo;
	}

	public String getIsAffirmWms() {
		return isAffirmWms;
	}

	public void setIsAffirmWms(String isAffirmWms) {
		this.isAffirmWms = isAffirmWms;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public char getIsManageSap() {
		return isManageSap;
	}

	public void setIsManageSap(char isManageSap) {
		this.isManageSap = isManageSap;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@ApiModelProperty(value = "交货日期")
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date deliveryDate;

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	/**
	 * 采购订单商品表列表
	 */
	protected List<ScmCgProOrderProduct> scmCgProOrderProductList = new ArrayList<ScmCgProOrderProduct>();

	/**
	 * 采购订单补单商品表列表
	 */
	protected List<ScmCgProReplacementOrder> scmCgProReplacementOrderList = new ArrayList<ScmCgProReplacementOrder>();

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 返回 id
	 * 
	 * @return
	 */
	public String getId() {
		return this.id;
	}

	public void setProcurementCode(String procurementCode) {
		this.procurementCode = procurementCode;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	/**
	 * 返回 采购订单号
	 * 
	 * @return
	 */
	public String getProcurementCode() {
		return this.procurementCode;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	/**
	 * 返回 供应商id
	 * 
	 * @return
	 */
	public String getSupplierId() {
		return this.supplierId;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	/**
	 * 返回 供应商名称
	 * 
	 * @return
	 */
	public String getSupplier() {
		return this.supplier;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	/**
	 * 返回 合同编号
	 * 
	 * @return
	 */
	public String getContractCode() {
		return this.contractCode;
	}

	public void setSpeciesSum(Integer speciesSum) {
		this.speciesSum = speciesSum;
	}

	/**
	 * 返回 品种数
	 * 
	 * @return
	 */
	public Integer getSpeciesSum() {
		return this.speciesSum;
	}

	public void setProductSum(Integer productSum) {
		this.productSum = productSum;
	}

	/**
	 * 返回 数量
	 * 
	 * @return
	 */
	public Integer getProductSum() {
		return this.productSum;
	}

	public void setSumPrice(Double sumPrice) {
		this.sumPrice = sumPrice;
	}

	/**
	 * 返回 金额
	 * 
	 * @return
	 */
	public Double getSumPrice() {
		return this.sumPrice;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getGetMoneyWarehouse() {
		return getMoneyWarehouse;
	}

	public void setGetMoneyWarehouse(String getMoneyWarehouse) {
		this.getMoneyWarehouse = getMoneyWarehouse;
	}

	/**
	 * 返回 币种
	 * 
	 * @return
	 */
	public String getCurrency() {
		return this.currency;
	}

	public void setOrderDate(java.util.Date orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * 返回 下单日期
	 * 
	 * @return
	 */
	public java.util.Date getOrderDate() {
		return this.orderDate;
	}

	public void setIsToWMS(char isToWMS) {
		this.isToWMS = isToWMS;
	}

	/**
	 * 返回 是否推送至WMS
	 * 
	 * @return
	 */
	public char getIsToWMS() {
		return this.isToWMS;
	}

	public void setApprovalId(String approvalId) {
		this.approvalId = approvalId;
	}

	/**
	 * 返回 审批ID
	 * 
	 * @return
	 */
	public String getApprovalId() {
		return this.approvalId;
	}

	public void setApprovalState(String approvalState) {
		this.approvalState = approvalState;
	}

	/**
	 * 返回 审批状态
	 * 
	 * @return
	 */
	public String getApprovalState() {
		return this.approvalState;
	}

	public void setGetTicket(String getTicket) {
		this.getTicket = getTicket;
	}

	/**
	 * 返回 收票方
	 * 
	 * @return
	 */
	public String getGetTicket() {
		return this.getTicket;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * 返回 货主ID
	 * 
	 * @return
	 */
	public String getOwnerId() {
		return this.ownerId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	/**
	 * 返回 操作人ID
	 * 
	 * @return
	 */
	public String getOperatorId() {
		return this.operatorId;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	/**
	 * 返回 操作人
	 * 
	 * @return
	 */
	public String getOperatorName() {
		return this.operatorName;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	/**
	 * 返回 采购合同ID
	 * 
	 * @return
	 */
	public String getContractId() {
		return this.contractId;
	}

	public void setScmCgProOrderProductList(List<ScmCgProOrderProduct> scmCgProOrderProductList) {
		this.scmCgProOrderProductList = scmCgProOrderProductList;
	}

	/**
	 * 返回 采购订单商品表列表
	 * 
	 * @return
	 */
	public List<ScmCgProOrderProduct> getScmCgProOrderProductList() {
		return this.scmCgProOrderProductList;
	}

	/**
	 * 返回 采购订单补单商品表列表
	 * 
	 * @return
	 */
	public List<ScmCgProReplacementOrder> getScmCgProReplacementOrderList() {
		return scmCgProReplacementOrderList;
	}

	public void setScmCgProReplacementOrderList(List<ScmCgProReplacementOrder> scmCgProReplacementOrderList) {
		this.scmCgProReplacementOrderList = scmCgProReplacementOrderList;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).append("procurementCode", this.procurementCode)
				.append("supplierId", this.supplierId).append("supplier", this.supplier)
				.append("contractCode", this.contractCode).append("speciesSum", this.speciesSum)
				.append("productSum", this.productSum).append("sumPrice", this.sumPrice)
				.append("currency", this.currency).append("orderDate", this.orderDate).append("isToWMS", this.isToWMS)
				.append("approvalId", this.approvalId).append("approvalState", this.approvalState)
				.append("getTicket", this.getTicket).append("ownerId", this.ownerId)
				.append("operatorId", this.operatorId).append("operatorName", this.operatorName)
				.append("contractId", this.contractId).toString();
	}
}