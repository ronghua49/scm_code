package com.winway.scm.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

/**药品进销存查询*/
public class ScmGspDrupReplenishVo {

	@ApiModelProperty(value = "业务类型")
	private String   businesstype;
	@ApiModelProperty(value = "业务单号")
	private String  billno;
	@ApiModelProperty(value = "药品类型")
	private String  linetype;
	@ApiModelProperty(value = "商品编码")
	private String itemcode;
	@ApiModelProperty(value = "产品系列")
	private String  fmodel;
	@ApiModelProperty(value = "通用名称")
	private String  itemname;
	@ApiModelProperty(value = "规格")
	private String  capacity;
	@ApiModelProperty(value = "剂型")
	private String  ftypes;
	@ApiModelProperty(value = "生产厂商")
	private String seasonid;
	@ApiModelProperty(value = "购/销日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date  purchaseSaleDate;
	@ApiModelProperty(value = "供应商编码")
	private String  vendorcode;
	@ApiModelProperty(value = "供应商名称")
	private String  vendorname;
	@ApiModelProperty(value = "客户编码")
	private String  customercode;
	@ApiModelProperty(value = "客户名称")
	private String customername;
	@ApiModelProperty(value = "发货数量")
	private String  fssl;
	@ApiModelProperty(value = "单位")
	private String  stockunit;
	@ApiModelProperty(value = "整件包装数量")
	private String  zjbzsl;
	@ApiModelProperty(value = "批号")
	private String lotno;
	@ApiModelProperty(value = "备注")
	private String  remark;
	@ApiModelProperty(value="页码")
	private Integer  page;
	@ApiModelProperty(value="条数")
	private Integer pagesize;
	@ApiModelProperty(value="总条数")
	private Integer  total;
	@ApiModelProperty(value="货主ID")
	private String  ownerId;
	@ApiModelProperty(value="商品类型:1:普通药品,2特殊药品(必传)")
	private String itemtype;
	@ApiModelProperty(value="购/销日期起")
	private Date purchaseSaleStartDate;
	@ApiModelProperty(value="购/销日期止")
	private Date purchaseSaleEndDate;
	
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getItemtype() {
		return itemtype;
	}
	public void setItemtype(String itemtype) {
		this.itemtype = itemtype;
	}
	public Date getPurchaseSaleStartDate() {
		return purchaseSaleStartDate;
	}
	public void setPurchaseSaleStartDate(Date purchaseSaleStartDate) {
		this.purchaseSaleStartDate = purchaseSaleStartDate;
	}
	public Date getPurchaseSaleEndDate() {
		return purchaseSaleEndDate;
	}
	public void setPurchaseSaleEndDate(Date purchaseSaleEndDate) {
		this.purchaseSaleEndDate = purchaseSaleEndDate;
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
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public String getBusinesstype() {
		return businesstype;
	}
	public void setBusinesstype(String businesstype) {
		this.businesstype = businesstype;
	}
	public String getBillno() {
		return billno;
	}
	public void setBillno(String billno) {
		this.billno = billno;
	}
	public String getLinetype() {
		return linetype;
	}
	public void setLinetype(String linetype) {
		this.linetype = linetype;
	}
	public String getItemcode() {
		return itemcode;
	}
	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}
	
	public String getFmodel() {
		return fmodel;
	}
	public void setFmodel(String fmodel) {
		this.fmodel = fmodel;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
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
	public String getSeasonid() {
		return seasonid;
	}
	public void setSeasonid(String seasonid) {
		this.seasonid = seasonid;
	}
	public Date getPurchaseSaleDate() {
		return purchaseSaleDate;
	}
	public void setPurchaseSaleDate(Date purchaseSaleDate) {
		this.purchaseSaleDate = purchaseSaleDate;
	}
	public String getVendorcode() {
		return vendorcode;
	}
	public void setVendorcode(String vendorcode) {
		this.vendorcode = vendorcode;
	}
	public String getVendorname() {
		return vendorname;
	}
	public void setVendorname(String vendorname) {
		this.vendorname = vendorname;
	}
	public String getCustomercode() {
		return customercode;
	}
	public void setCustomercode(String customercode) {
		this.customercode = customercode;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getFssl() {
		return fssl;
	}
	public void setFssl(String fssl) {
		this.fssl = fssl;
	}
	public String getStockunit() {
		return stockunit;
	}
	public void setStockunit(String stockunit) {
		this.stockunit = stockunit;
	}
	public String getZjbzsl() {
		return zjbzsl;
	}
	public void setZjbzsl(String zjbzsl) {
		this.zjbzsl = zjbzsl;
	}
	public String getLotno() {
		return lotno;
	}
	public void setLotno(String lotno) {
		this.lotno = lotno;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
}
