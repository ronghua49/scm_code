package com.winway.scm.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

/**药品不合格品库存台帐*/
public class ScmGspNonconformingstockVo {

	@ApiModelProperty(value = "不合格品来源单号")
	private String lybillno;
	@ApiModelProperty(value = "库存状态(0:全部,1:不合格,2:待销毁)")
	private String state;
	@ApiModelProperty(value = "不合格品入库单号")
	private String rkbillno;
	@ApiModelProperty(value = "药品类型")
	private String linetype;
	@ApiModelProperty(value = "商品编码")
	private String  itemcode;
	@ApiModelProperty(value = "产品系列")
	private String fmodel;
	@ApiModelProperty(value = "通用名称")
	private String  itemname;
	@ApiModelProperty(value = "规格")
	private String  capacity;
	@ApiModelProperty(value = "剂型")
	private String  ftypes;
	@ApiModelProperty(value = "生产厂商")
	private String  seasonid;
	@ApiModelProperty(value = "不合格品数量")
	private String  bhgqty;
	@ApiModelProperty(value = "单位")
	private String  stockunit;
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
	@ApiModelProperty(value = "不合格品来源")
	private String  bhgly;
	@ApiModelProperty(value = "不合格品原因")
	private String  bhgyy;
	@ApiModelProperty(value = "不合格品责任人")
	private String  bhgexecutor;
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
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
	public String getLybillno() {
		return lybillno;
	}
	public void setLybillno(String lybillno) {
		this.lybillno = lybillno;
	}
	public String getRkbillno() {
		return rkbillno;
	}
	public void setRkbillno(String rkbillno) {
		this.rkbillno = rkbillno;
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
	public String getBhgqty() {
		return bhgqty;
	}
	public void setBhgqty(String bhgqty) {
		this.bhgqty = bhgqty;
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
	public String getBhgly() {
		return bhgly;
	}
	public void setBhgly(String bhgly) {
		this.bhgly = bhgly;
	}
	public String getBhgyy() {
		return bhgyy;
	}
	public void setBhgyy(String bhgyy) {
		this.bhgyy = bhgyy;
	}
	public String getBhgexecutor() {
		return bhgexecutor;
	}
	public void setBhgexecutor(String bhgexecutor) {
		this.bhgexecutor = bhgexecutor;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
