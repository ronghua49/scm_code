package com.winway.scm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "WmsfindcgrkInVo",description = "WMS采购入库前端传参") 
public class WmsfindcgrkInVo {
	@ApiModelProperty(value="供应商(可为空)")  
	private String vendorname;
	@ApiModelProperty(value="采购单号(可为空))")
	private String orderCode;
	@ApiModelProperty(value="到货日期起(可为空)")  
	private String startDate;
	@ApiModelProperty(value="到货日期止(可为空)")  
	private String endDate;
	@ApiModelProperty(value="货主ID(不可为空,sap查询时可为空)") 
	private String consignor;
	@ApiModelProperty(value="页码(不可为空)")
	private Integer page;
	@ApiModelProperty(value="页大小（不可为空）")
	private Integer pagesize;
	public String getVendorname() {
		return vendorname;
	}
	public void setVendorname(String vendorname) {
		this.vendorname = vendorname;
	}
	
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getConsignor() {
		return consignor;
	}
	public void setConsignor(String consignor) {
		this.consignor = consignor;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPagesize() {
		return pagesize;
	}
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}
}
