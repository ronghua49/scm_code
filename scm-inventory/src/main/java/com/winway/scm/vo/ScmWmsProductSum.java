package com.winway.scm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "ScmWmsProductSum", description = "产品库存总量")
public class ScmWmsProductSum {
	@ApiModelProperty(value = "产品系列")
	private String fmodel;
	@ApiModelProperty(value = "产品名称")
	private String locname;
	@ApiModelProperty(value = "规格")
	private String capacity;
	@ApiModelProperty(value = "产品代码")
	private String itemcode;
	@ApiModelProperty(value = "存货代码")
	private String chdm;
	@ApiModelProperty(value = "库存日期")
	private String stockdate;
	@ApiModelProperty(value = "可用数量（a）")
	private String stockqty;
	@ApiModelProperty(value = "可用件数")
	private String setbooknum;
	@ApiModelProperty(value = "批号")
	private String lotno;
	@ApiModelProperty(value = "在途有药检数量（b）")
	private String ztyyjsl;
	@ApiModelProperty(value = "在途无药检数量（c）")
	private String ztwyjsl;
	@ApiModelProperty(value = "安全库存标准数量")
	private String aqkcbzsl;
	@ApiModelProperty(value = "均月发货数量（d）")
	private String jyfhsl;
	@ApiModelProperty(value = "现存货量可用月数（e=(a+b+c)/d）")
	private String xchlkyys;
	public String getFmodel() {
		return fmodel;
	}
	public void setFmodel(String fmodel) {
		this.fmodel = fmodel;
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
	public String getItemcode() {
		return itemcode;
	}
	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}
	public String getChdm() {
		return chdm;
	}
	public void setChdm(String chdm) {
		this.chdm = chdm;
	}
	public String getStockdate() {
		return stockdate;
	}
	public void setStockdate(String stockdate) {
		this.stockdate = stockdate;
	}
	public String getStockqty() {
		return stockqty;
	}
	public void setStockqty(String stockqty) {
		this.stockqty = stockqty;
	}
	public String getSetbooknum() {
		return setbooknum;
	}
	public void setSetbooknum(String setbooknum) {
		this.setbooknum = setbooknum;
	}
	public String getLotno() {
		return lotno;
	}
	public void setLotno(String lotno) {
		this.lotno = lotno;
	}
	public String getZtyyjsl() {
		return ztyyjsl;
	}
	public void setZtyyjsl(String ztyyjsl) {
		this.ztyyjsl = ztyyjsl;
	}
	public String getZtwyjsl() {
		return ztwyjsl;
	}
	public void setZtwyjsl(String ztwyjsl) {
		this.ztwyjsl = ztwyjsl;
	}
	public String getAqkcbzsl() {
		return aqkcbzsl;
	}
	public void setAqkcbzsl(String aqkcbzsl) {
		this.aqkcbzsl = aqkcbzsl;
	}
	public String getJyfhsl() {
		return jyfhsl;
	}
	public void setJyfhsl(String jyfhsl) {
		this.jyfhsl = jyfhsl;
	}
	public String getXchlkyys() {
		return xchlkyys;
	}
	public void setXchlkyys(String xchlkyys) {
		this.xchlkyys = xchlkyys;
	}
	
}
