package com.winway.scm.vo;

import java.util.Date;
import java.util.List;

public class ScmKcBreakageMasterToWms {
	private String breakageCode;
	private String createPersion;
	private Date createDate;
	private int varietySum;
	private int productSum;
	private double priceSum;
	private String ownerId;
	private List<ScmKcBreakageToWms> productList;
	public String getBreakageCode() {
		return breakageCode;
	}
	public void setBreakageCode(String breakageCode) {
		this.breakageCode = breakageCode;
	}
	public String getCreatePersion() {
		return createPersion;
	}
	public void setCreatePersion(String createPersion) {
		this.createPersion = createPersion;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getVarietySum() {
		return varietySum;
	}
	public void setVarietySum(int varietySum) {
		this.varietySum = varietySum;
	}
	public int getProductSum() {
		return productSum;
	}
	public void setProductSum(int productSum) {
		this.productSum = productSum;
	}
	public double getPriceSum() {
		return priceSum;
	}
	public void setPriceSum(double priceSum) {
		this.priceSum = priceSum;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public List<ScmKcBreakageToWms> getProductList() {
		return productList;
	}
	public void setProductList(List<ScmKcBreakageToWms> productList) {
		this.productList = productList;
	}
	
}
