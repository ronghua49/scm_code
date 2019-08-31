package com.winway.scm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**WMS采购入库单明细查询VO*/
@ApiModel(value = "WmsfindcgrkVo",description = "WMS采购入库单明细查询VO") 
public class WmsfindcgrkDetailVo {
	@ApiModelProperty(value="序号")
	private String dtrowid;
	
	@ApiModelProperty(value="商品编码")
	private String itemcode;
	
	@ApiModelProperty(value="通用名称")
	private String aliasname;
	
	@ApiModelProperty(value="产品名称")
	private String locname;
	
	@ApiModelProperty(value="规格")
	private String capacity;
	
	@ApiModelProperty(value="剂型")
	private String ftypes;
	
	@ApiModelProperty(value="生产厂家")
	private String ffactory;
	
	@ApiModelProperty(value="单位")
	private String stockunit;
	
	@ApiModelProperty(value="数量")
	private String stockqty;
	
	@ApiModelProperty(value="批号")
	private String lotno;
	
	@ApiModelProperty(value="生产日期")
	private String yielddate;
	
	@ApiModelProperty(value="有效期")
	private String expdate;
	
	@ApiModelProperty(value="仓库")
	private String branchid;
	
	@ApiModelProperty(value="供应商")
	private String vendorid;
	
	@ApiModelProperty(value="入库时间")
	private String arrivedate;
	
	public String getArrivedate() {
		return arrivedate;
	}

	public void setArrivedate(String arrivedate) {
		this.arrivedate = arrivedate;
	}

	@Override
	public String toString() {
		return "WmsfindcgrkDetailVo [dtrowid=" + dtrowid + ", itemcode=" + itemcode + ", aliasname=" + aliasname
				+ ", locname=" + locname + ", capacity=" + capacity + ", ftypes=" + ftypes + ", ffactory=" + ffactory
				+ ", stockunit=" + stockunit + ", stockqty=" + stockqty + ", lotno=" + lotno + ", yielddate="
				+ yielddate + ", expdate=" + expdate + ", branchid=" + branchid + ", vendorid=" + vendorid + "]";
	}

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

	public String getStockqty() {
		return stockqty;
	}

	public void setStockqty(String stockqty) {
		this.stockqty = stockqty;
	}

	public String getLotno() {
		return lotno;
	}

	public void setLotno(String lotno) {
		this.lotno = lotno;
	}

	public String getYielddate() {
		return yielddate;
	}

	public void setYielddate(String yielddate) {
		this.yielddate = yielddate;
	}

	public String getExpdate() {
		return expdate;
	}

	public void setExpdate(String expdate) {
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
