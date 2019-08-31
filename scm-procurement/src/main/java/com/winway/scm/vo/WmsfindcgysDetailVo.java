package com.winway.scm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**WMS采购验收单明细*/
@ApiModel(value = "WmsfindcgysDetailVo",description = "WMS采购验收单明细") 
public class WmsfindcgysDetailVo {
	@ApiModelProperty(value="验收结果")
	private String results;
	
	@ApiModelProperty(value="产品编码")
	private String itemcode;
	
	@ApiModelProperty(value="产品名称")
	private String itemname;
	
	@ApiModelProperty(value="供应商")
	private String vendorname;
	
	@ApiModelProperty(value="规格")
	private String sizecode;
	
	@ApiModelProperty(value="单位")
	private String stockunit;
	
	@ApiModelProperty(value="验收人")
	private String qcopcode;
	
	@ApiModelProperty(value="数量")
	private String qty;
	
	@ApiModelProperty(value="批号")
	private String lotno;
	
	@ApiModelProperty(value="生产日期")
	private String yielddate;
	
	@ApiModelProperty(value="有效期")
	private String expdate;
	
	@ApiModelProperty(value="进价")
	private String price;
	
	@ApiModelProperty(value="药品类型")
	private String linetype;
	
	@ApiModelProperty(value="批准文号")
	private String pch;
	
	
	@Override
	public String toString() {
		return "WmsfindcgysDetailVo [results=" + results + ", itemcode=" + itemcode + ", itemname=" + itemname
				+ ", vendorname=" + vendorname + ", sizecode=" + sizecode + ", stockunit=" + stockunit + ", qcopcode="
				+ qcopcode + ", qty=" + qty + ", lotno=" + lotno + ", yielddate=" + yielddate + ", expdate=" + expdate
				+ ", price=" + price + ", linetype=" + linetype + ", pch=" + pch + "]";
	}


	public String getResults() {
		return results;
	}


	public void setResults(String results) {
		this.results = results;
	}


	public String getItemcode() {
		return itemcode;
	}


	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}


	public String getItemname() {
		return itemname;
	}


	public void setItemname(String itemname) {
		this.itemname = itemname;
	}


	public String getVendorname() {
		return vendorname;
	}


	public void setVendorname(String vendorname) {
		this.vendorname = vendorname;
	}


	public String getSizecode() {
		return sizecode;
	}


	public void setSizecode(String sizecode) {
		this.sizecode = sizecode;
	}


	public String getStockunit() {
		return stockunit;
	}


	public void setStockunit(String stockunit) {
		this.stockunit = stockunit;
	}


	public String getQcopcode() {
		return qcopcode;
	}


	public void setQcopcode(String qcopcode) {
		this.qcopcode = qcopcode;
	}


	public String getQty() {
		return qty;
	}


	public void setQty(String qty) {
		this.qty = qty;
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


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public String getLinetype() {
		return linetype;
	}


	public void setLinetype(String linetype) {
		this.linetype = linetype;
	}


	public String getPch() {
		return pch;
	}


	public void setPch(String pch) {
		this.pch = pch;
	}
	
	
	 
}
