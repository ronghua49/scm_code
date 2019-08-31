package com.winway.scm.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

/**药品质量复检记录*/
public class ScmGspDrupRecheckVo {
	
	
	
	
	@ApiModelProperty(value = "货主Id")
	private String  ownerId;
	@ApiModelProperty(value = "商品类型:1:普通药品,2特殊药品(必传)")
	private String itemtype;
	@ApiModelProperty(value = "复检单号")
	private String  billno;
	@ApiModelProperty(value = "商品编码")
	private String itemcode;
	@ApiModelProperty(value = "药品类型")
	private String  linetype;
	@ApiModelProperty(value = "产品系列")
	private String fmodel;
	@ApiModelProperty(value = "通用名称")
	private String itemname;
	@ApiModelProperty(value = "规格")
	private String  capacity;
	@ApiModelProperty(value = "剂型")
	private String  ftypes;
	@ApiModelProperty(value = "生产厂商")
	private String  seasonid;
	@ApiModelProperty(value = "复检数量")
	private String  fjqty;
	@ApiModelProperty(value = "单位")
	private String  stockunit;
	@ApiModelProperty(value = "批号")
	private String lotno;
	@ApiModelProperty(value = "生产日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date   yielddate;
	@ApiModelProperty(value = "有效期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date   expdate;
	@ApiModelProperty(value = "批准文号")
	private String  fsymbol;
	@ApiModelProperty(value = "质量情况及责任,")
	private String  zlqkjzr;
	@ApiModelProperty(value = "报告人,")
	private String  reporter;
	@ApiModelProperty(value = "报告日期,")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date  reportdate;
	@ApiModelProperty(value = "复查情况及预防措施,")
	private String  fcqkjyfcs;
	@ApiModelProperty(value = "复核人,")
	private String  reviewer;
	@ApiModelProperty(value = "复核日期,")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date  reviewdate;
	@ApiModelProperty(value = "备注,")
	private String remark;
	@ApiModelProperty(value="页码")
	private Integer  page;
	@ApiModelProperty(value="条数")
	private Integer pagesize;
	@ApiModelProperty(value="总条数")
	private Integer  total;
	
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
	public String getBillno() {
		return billno;
	}
	public void setBillno(String billno) {
		this.billno = billno;
	}
	public String getItemcode() {
		return itemcode;
	}
	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}
	public String getLinetype() {
		return linetype;
	}
	public void setLinetype(String linetype) {
		this.linetype = linetype;
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
	public String getFjqty() {
		return fjqty;
	}
	public void setFjqty(String fjqty) {
		this.fjqty = fjqty;
	}
	public String getStockunit() {
		return stockunit;
	}
	public void setStockunit(String stockunit) {
		this.stockunit = stockunit;
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
	public String getFsymbol() {
		return fsymbol;
	}
	public void setFsymbol(String fsymbol) {
		this.fsymbol = fsymbol;
	}
	public String getZlqkjzr() {
		return zlqkjzr;
	}
	public void setZlqkjzr(String zlqkjzr) {
		this.zlqkjzr = zlqkjzr;
	}
	public String getReporter() {
		return reporter;
	}
	public void setReporter(String reporter) {
		this.reporter = reporter;
	}
	public Date getReportdate() {
		return reportdate;
	}
	public void setReportdate(Date reportdate) {
		this.reportdate = reportdate;
	}
	public String getFcqkjyfcs() {
		return fcqkjyfcs;
	}
	public void setFcqkjyfcs(String fcqkjyfcs) {
		this.fcqkjyfcs = fcqkjyfcs;
	}
	public String getReviewer() {
		return reviewer;
	}
	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}
	public Date getReviewdate() {
		return reviewdate;
	}
	public void setReviewdate(Date reviewdate) {
		this.reviewdate = reviewdate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
