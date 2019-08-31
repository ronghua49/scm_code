package com.winway.scm.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
/**药品出库复核记录*/
public class ScmGspdrugReleasereviewrecordVo {
	
	@ApiModelProperty(value="货主ID")
	private String  ownerId;
	
	@ApiModelProperty(value="商品类型:1:普通药品,2特殊药品(必传)")
	private String itemtype;
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value="出库日期起")
	private Date   billStartDate;
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value="出库日期止")
	private Date  billEndDate;
	
	@ApiModelProperty(value="发货单号")
	private  String  billno;
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value="出库日期")
	private  Date  billdate;
	
	@ApiModelProperty(value="药品类型")
	private String linetype;
	
	@ApiModelProperty(value="购货单位")
	private  String  purchaser;
	
	@ApiModelProperty(value="产品系列")
	private String  fmodel;
	
	@ApiModelProperty(value="商品编码")
	private String  itemcode;
	
	@ApiModelProperty(value="通用名称")
	private String   itemname;
	
	@ApiModelProperty(value="规格")
	private String  capacity;
	
	@ApiModelProperty(value="剂型")
	private String   ftypes;
	
	@ApiModelProperty(value="生产厂商")
	private String  seasonid;
	
	@ApiModelProperty(value="出库数量")
	private String   chqty;
	
	@ApiModelProperty(value="单位")
	private String   stockunit;
	
	@ApiModelProperty(value="批号")
	private String   lotno;
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value="生产日期")
	private Date yielddate;
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value="有效期")
	private Date  expdate;
	
	@ApiModelProperty(value="质量状况")
	private String  qualitystatus;
	
	@ApiModelProperty(value="质量状况")
	private String  reviewer;
	
	@ApiModelProperty(value="备注")
	private  String  remark;
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

	public Date getBillStartDate() {
		return billStartDate;
	}

	public void setBillStartDate(Date billStartDate) {
		this.billStartDate = billStartDate;
	}

	public Date getBillEndDate() {
		return billEndDate;
	}

	public void setBillEndDate(Date billEndDate) {
		this.billEndDate = billEndDate;
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

	public Date getBilldate() {
		return billdate;
	}

	public void setBilldate(Date billdate) {
		this.billdate = billdate;
	}

	public String getLinetype() {
		return linetype;
	}

	public void setLinetype(String linetype) {
		this.linetype = linetype;
	}

	public String getPurchaser() {
		return purchaser;
	}

	public void setPurchaser(String purchaser) {
		this.purchaser = purchaser;
	}

	public String getFmodel() {
		return fmodel;
	}

	public void setFmodel(String fmodel) {
		this.fmodel = fmodel;
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

	public String getChqty() {
		return chqty;
	}

	public void setChqty(String chqty) {
		this.chqty = chqty;
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

	public String getQualitystatus() {
		return qualitystatus;
	}

	public void setQualitystatus(String qualitystatus) {
		this.qualitystatus = qualitystatus;
	}

	public String getReviewer() {
		return reviewer;
	}

	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
}
