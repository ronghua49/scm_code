package com.winway.scm.vo;

import java.util.Date;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "ScmWmsNearlyEffective", description = "近效期返回数据")
public class ScmWmsNearlyEffective {

	private String dtrowid;
	private String itemcode;
	private String aliasname;
	private String locname;
	private String capacity;
	private String ftypes;
	private String ffactory;
	private String stockunit;
	private int stockqty;
	private String lotno;
	private Date yielddate;
	private Date expdate;
	private String branchid;
	private String vendorid;
	public String getDtrowid() {
		return dtrowid;
	}
	public void setDtrowid(String dtrowid) {
		this.dtrowid = dtrowid;
	}
	public String getItemcode() {
		return itemcode;
	}
	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}
	public String getAliasname() {
		return aliasname;
	}
	public void setAliasname(String aliasname) {
		this.aliasname = aliasname;
	}
	public String getLocname() {
		return locname;
	}
	public void setLocname(String locname) {
		this.locname = locname;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public String getFtypes() {
		return ftypes;
	}
	public void setFtypes(String ftypes) {
		this.ftypes = ftypes;
	}
	public String getFfactory() {
		return ffactory;
	}
	public void setFfactory(String ffactory) {
		this.ffactory = ffactory;
	}
	public String getStockunit() {
		return stockunit;
	}
	public void setStockunit(String stockunit) {
		this.stockunit = stockunit;
	}
	public int getStockqty() {
		return stockqty;
	}
	public void setStockqty(int stockqty) {
		this.stockqty = stockqty;
	}
	public String getLotno() {
		return lotno;
	}
	public void setLotno(String lotno) {
		this.lotno = lotno;
	}
	public Date getYielddate() {
		return yielddate;
	}
	public void setYielddate(Date yielddate) {
		this.yielddate = yielddate;
	}
	public Date getExpdate() {
		return expdate;
	}
	public void setExpdate(Date expdate) {
		this.expdate = expdate;
	}
	public String getBranchid() {
		return branchid;
	}
	public void setBranchid(String branchid) {
		this.branchid = branchid;
	}
	public String getVendorid() {
		return vendorid;
	}
	public void setVendorid(String vendorid) {
		this.vendorid = vendorid;
	}
	
}
