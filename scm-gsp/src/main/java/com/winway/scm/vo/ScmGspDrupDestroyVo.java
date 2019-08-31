package com.winway.scm.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

/**不合格药品销毁记录*/
public class ScmGspDrupDestroyVo {
	
	@ApiModelProperty(value = "销毁单号")
	private String  xhbillno;
	@ApiModelProperty(value = "销毁日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date  xhbilldate;
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
	@ApiModelProperty(value = "销毁数量")
	private String  xhqty;
	@ApiModelProperty(value = "单位")
	private String stockunit;
	@ApiModelProperty(value = "批号")
	private String  lotno;
	@ApiModelProperty(value = "生产日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date  yielddate;
	@ApiModelProperty(value = "有效期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date   expdate;
	@ApiModelProperty(value = "批准文号")
	private String  fsymbol;
	@ApiModelProperty(value = "报损单号")
	private String  bsbillno;
	@ApiModelProperty(value = "销毁执行人")
	private  String xhexecutor;
	@ApiModelProperty(value = "备注")
	private String  remark;
	@ApiModelProperty(value="页码")
	private Integer  page;
	@ApiModelProperty(value="条数")
	private Integer pagesize;
	@ApiModelProperty(value="总条数")
	private Integer  total;
	@ApiModelProperty(value="货主ID")
	private String ownerId;
	@ApiModelProperty(value="商品类型:1:普通药品,2特殊药品(必传)")
	private String itemtype;
	
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
	public String getXhbillno() {
		return xhbillno;
	}
	public void setXhbillno(String xhbillno) {
		this.xhbillno = xhbillno;
	}
	public Date getXhbilldate() {
		return xhbilldate;
	}
	public void setXhbilldate(Date xhbilldate) {
		this.xhbilldate = xhbilldate;
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
	public String getXhqty() {
		return xhqty;
	}
	public void setXhqty(String xhqty) {
		this.xhqty = xhqty;
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
	public String getBsbillno() {
		return bsbillno;
	}
	public void setBsbillno(String bsbillno) {
		this.bsbillno = bsbillno;
	}
	public String getXhexecutor() {
		return xhexecutor;
	}
	public void setXhexecutor(String xhexecutor) {
		this.xhexecutor = xhexecutor;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
	
}
