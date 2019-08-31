package com.winway.scm.vo;

import java.util.Date;

public class ScmKcBreakageToWms {

	private String batchNum;
	private String productName;
	private String medicineType;
	private String productState;
	private String supplier;
	private Date expiryDate;
	private double purchasingCost;
	private int breakageSum;
	private String dutyPersion;
	private String memo;
	public String getBatchNum() {
		return batchNum;
	}
	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getMedicineType() {
		return medicineType;
	}
	public void setMedicineType(String medicineType) {
		this.medicineType = medicineType;
	}
	public String getProductState() {
		return productState;
	}
	public void setProductState(String productState) {
		this.productState = productState;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public double getPurchasingCost() {
		return purchasingCost;
	}
	public void setPurchasingCost(double purchasingCost) {
		this.purchasingCost = purchasingCost;
	}
	public int getBreakageSum() {
		return breakageSum;
	}
	public void setBreakageSum(int breakageSum) {
		this.breakageSum = breakageSum;
	}
	public String getDutyPersion() {
		return dutyPersion;
	}
	public void setDutyPersion(String dutyPersion) {
		this.dutyPersion = dutyPersion;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
}
