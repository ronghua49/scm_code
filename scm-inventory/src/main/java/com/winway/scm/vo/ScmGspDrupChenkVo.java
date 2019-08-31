package com.winway.scm.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

/**药品质量养护检查记录*/
public class ScmGspDrupChenkVo {
	
	@ApiModelProperty(value = "药品名称")
	private String  itemname;
	@ApiModelProperty(value = "药品编码")
	private String  itemcode;
	@ApiModelProperty(value = "通用名")
	private String  generalame;
	@ApiModelProperty(value = "药品类型")
	private String  linetype;
	@ApiModelProperty(value = "规格")
	private String  capacity;
	@ApiModelProperty(value = "单位")
	private String  stockunit;
	@ApiModelProperty(value = "数量")
	private String qty;
	@ApiModelProperty(value = "生产厂商")
	private String  seasonid;
	@ApiModelProperty(value = "有效期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date  expdate;
	@ApiModelProperty(value = "批号")
	private String  lotno;
	@ApiModelProperty(value = "仓库")
	private String  branchcode;
	@ApiModelProperty(value = "养护日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date   conservedate;
	@ApiModelProperty(value = "质量情况")
	private String  qualitysituation;
	@ApiModelProperty(value = "养护员")
	private String  guardian;
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
	@ApiModelProperty(value = "商品类型:1:普通药品,2特殊药品(必传)")
	private String itemtype;
	
	public String getItemtype() {
		return itemtype;
	}
	public void setItemtype(String itemtype) {
		this.itemtype = itemtype;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
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
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public String getItemcode() {
		return itemcode;
	}
	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}
	public String getGeneralame() {
		return generalame;
	}
	public void setGeneralame(String generalame) {
		this.generalame = generalame;
	}
	public String getLinetype() {
		return linetype;
	}
	public void setLinetype(String linetype) {
		this.linetype = linetype;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public String getStockunit() {
		return stockunit;
	}
	public void setStockunit(String stockunit) {
		this.stockunit = stockunit;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getSeasonid() {
		return seasonid;
	}
	public void setSeasonid(String seasonid) {
		this.seasonid = seasonid;
	}
	public Date getExpdate() {
		return expdate;
	}
	public void setExpdate(Date expdate) {
		this.expdate = expdate;
	}
	public String getLotno() {
		return lotno;
	}
	public void setLotno(String lotno) {
		this.lotno = lotno;
	}
	public String getBranchcode() {
		return branchcode;
	}
	public void setBranchcode(String branchcode) {
		this.branchcode = branchcode;
	}
	public Date getConservedate() {
		return conservedate;
	}
	public void setConservedate(Date conservedate) {
		this.conservedate = conservedate;
	}
	public String getQualitysituation() {
		return qualitysituation;
	}
	public void setQualitysituation(String qualitysituation) {
		this.qualitysituation = qualitysituation;
	}
	public String getGuardian() {
		return guardian;
	}
	public void setGuardian(String guardian) {
		this.guardian = guardian;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
}
