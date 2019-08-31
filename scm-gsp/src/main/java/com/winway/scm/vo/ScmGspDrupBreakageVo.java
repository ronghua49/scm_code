package com.winway.scm.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

/**不合格药品报损记录*/
public class ScmGspDrupBreakageVo {


	@ApiModelProperty(value = "报损单号")
	private String billno;
	@ApiModelProperty(value = "药品类型")
	private String  linetype;
	@ApiModelProperty(value = "商品编码")
	private String  itemcode;
	@ApiModelProperty(value = "产品系列")
	private String  fmodel;
	@ApiModelProperty(value = "通用名称")
	private String  itemname;
	@ApiModelProperty(value = "规格")
	private String  capacity;
	@ApiModelProperty(value = "剂型")
	private String  ftypes;
	@ApiModelProperty(value = "生产厂商")
	private String  seasonid;
	@ApiModelProperty(value = "交易仓库")
	private String  branchcode;
	@ApiModelProperty(value = "报损数量")
	private String  bsqty;
	@ApiModelProperty(value = "单位")
	private String stockunit;
	@ApiModelProperty(value = "批号")
	private String lotno;
	@ApiModelProperty(value = "生产日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date  yielddate;
	@ApiModelProperty(value = "有效期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date expdate;
	@ApiModelProperty(value = "批准文号")
	private String  fsymbol;
	@ApiModelProperty(value = "供货企业")
	private String supplyunit;
	@ApiModelProperty(value = "不合格品来源")
	private String  bhgply;
	@ApiModelProperty(value = "不合格品原因")
	private String  bhgpyy;
	@ApiModelProperty(value = "不合格品责任人")
	private String  bhgpzrr;
	@ApiModelProperty(value = "报损原因")
	private String  bsyy;
	@ApiModelProperty(value = "单价,")
	private String  price;
	@ApiModelProperty(value = "报损金额")
	private String  amt;
	@ApiModelProperty(value = "过账日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date  gzdate;
	@ApiModelProperty(value = "凭证日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date pzdate;
	@ApiModelProperty(value = "异动类型")
	private String  ydlx;
	@ApiModelProperty(value = "处理方式")
	private String  clfs;
	@ApiModelProperty(value = "申请人")
	private String sqr;
	@ApiModelProperty(value = "申请时间")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date  billdate;
	@ApiModelProperty(value = "报损完成时间")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date bswcdate;
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

	@ApiModelProperty(value = "序号")
	private Long i;

	public Long getI() {
		return i;
	}

	public void setI(Long i) {
		this.i = i;
	}

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
	public String getBranchcode() {
		return branchcode;
	}
	public void setBranchcode(String branchcode) {
		this.branchcode = branchcode;
	}
	public String getBsqty() {
		return bsqty;
	}
	public void setBsqty(String bsqty) {
		this.bsqty = bsqty;
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
	public String getSupplyunit() {
		return supplyunit;
	}
	public void setSupplyunit(String supplyunit) {
		this.supplyunit = supplyunit;
	}
	public String getBhgply() {
		return bhgply;
	}
	public void setBhgply(String bhgply) {
		this.bhgply = bhgply;
	}
	public String getBhgpyy() {
		return bhgpyy;
	}
	public void setBhgpyy(String bhgpyy) {
		this.bhgpyy = bhgpyy;
	}
	public String getBhgpzrr() {
		return bhgpzrr;
	}
	public void setBhgpzrr(String bhgpzrr) {
		this.bhgpzrr = bhgpzrr;
	}
	public String getBsyy() {
		return bsyy;
	}
	public void setBsyy(String bsyy) {
		this.bsyy = bsyy;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getAmt() {
		return amt;
	}
	public void setAmt(String amt) {
		this.amt = amt;
	}
	public Date getGzdate() {
		return gzdate;
	}
	public void setGzdate(Date gzdate) {
		this.gzdate = gzdate;
	}
	public Date getPzdate() {
		return pzdate;
	}
	public void setPzdate(Date pzdate) {
		this.pzdate = pzdate;
	}
	public String getYdlx() {
		return ydlx;
	}
	public void setYdlx(String ydlx) {
		this.ydlx = ydlx;
	}
	public String getClfs() {
		return clfs;
	}
	public void setClfs(String clfs) {
		this.clfs = clfs;
	}
	public String getSqr() {
		return sqr;
	}
	public void setSqr(String sqr) {
		this.sqr = sqr;
	}
	public Date getBilldate() {
		return billdate;
	}
	public void setBilldate(Date billdate) {
		this.billdate = billdate;
	}
	public Date getBswcdate() {
		return bswcdate;
	}
	public void setBswcdate(Date bswcdate) {
		this.bswcdate = bswcdate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
