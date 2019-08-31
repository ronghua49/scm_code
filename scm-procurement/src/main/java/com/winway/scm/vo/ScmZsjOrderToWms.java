package com.winway.scm.vo;

import java.util.Date;
import java.util.List;

public class ScmZsjOrderToWms {
	private String procurementCode;
	private String supplierCode;
	private String supplier;
	private String contractCode;
	private int speciesSum;
	private int productSum;
	private double sumPrice;
	private String currency;
	private Date orderDate;
	private String getTicket;
	private String ownerId;
	private String type;
	private Date deliveryDate;
	private String getMoneyWarehouse;
	private List<ScmZsjOrderProductToWms> orderProduct;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getProcurementCode() {
		return procurementCode;
	}
	public void setProcurementCode(String procurementCode) {
		this.procurementCode = procurementCode;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getContractCode() {
		return contractCode;
	}
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	public int getSpeciesSum() {
		return speciesSum;
	}
	public void setSpeciesSum(int speciesSum) {
		this.speciesSum = speciesSum;
	}
	public int getProductSum() {
		return productSum;
	}
	public void setProductSum(int productSum) {
		this.productSum = productSum;
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
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getGetTicket() {
		return getTicket;
	}
	public void setGetTicket(String getTicket) {
		this.getTicket = getTicket;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getGetMoneyWarehouse() {
		return getMoneyWarehouse;
	}
	public void setGetMoneyWarehouse(String getMoneyWarehouse) {
		this.getMoneyWarehouse = getMoneyWarehouse;
	}
	public List<ScmZsjOrderProductToWms> getOrderProduct() {
		return orderProduct;
	}
	public void setOrderProduct(List<ScmZsjOrderProductToWms> orderProduct) {
		this.orderProduct = orderProduct;
	}
	
}
