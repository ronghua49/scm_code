package com.winway.scm.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

/** 药品运输记录 */
public class ScmGspDrugtransportVo {
	
	@ApiModelProperty(value = "货主Id")
	private String  ownerId;
	@ApiModelProperty(value = "发货日期起")
	private Date billStartDate;
	@ApiModelProperty(value = "发货日期止")
	private Date billEndDate;
	@ApiModelProperty(value = "收货单位")
	private String  receivingunit;
	@ApiModelProperty(value = "商品类型:1:普通药品,2特殊药品(必传)")
	private String itemtype;
	
	@ApiModelProperty(value = "发货单号")
	private String billno;
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "发货日期")
	private Date billdate;
	@ApiModelProperty(value = "发货地址")
	private String address;
	@ApiModelProperty(value = "药品件数")
	private String orderpcs;
	@ApiModelProperty(value = "承运单位")
	private String shipvendorcode;
	@ApiModelProperty(value = "货单号")
	private String goodsno;
	@ApiModelProperty(value = "委托经办人")
	private String agent;
	@ApiModelProperty(value = "驾驶员")
	private String driver;
	@ApiModelProperty(value = "车牌号")
	private String platenumber;
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "到货日期")
	private Date arrivaldate;
	@ApiModelProperty(value = "运输方式")
	private String delivertype;
	@ApiModelProperty(value = "备注")
	private String remark;
	@ApiModelProperty(value="页码")
	private Integer  page;
	@ApiModelProperty(value="条数")
	private Integer pagesize;
	@ApiModelProperty(value="总条数")
	private Integer  total;

	@ApiModelProperty(value="类名")
	private String  className;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
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

	public String getReceivingunit() {
		return receivingunit;
	}

	public void setReceivingunit(String receivingunit) {
		this.receivingunit = receivingunit;
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

	public Date getBilldate() {
		return billdate;
	}

	public void setBilldate(Date billdate) {
		this.billdate = billdate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOrderpcs() {
		return orderpcs;
	}

	public void setOrderpcs(String orderpcs) {
		this.orderpcs = orderpcs;
	}

	public String getShipvendorcode() {
		return shipvendorcode;
	}

	public void setShipvendorcode(String shipvendorcode) {
		this.shipvendorcode = shipvendorcode;
	}

	public String getGoodsno() {
		return goodsno;
	}

	public void setGoodsno(String goodsno) {
		this.goodsno = goodsno;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getPlatenumber() {
		return platenumber;
	}

	public void setPlatenumber(String platenumber) {
		this.platenumber = platenumber;
	}

	public Date getArrivaldate() {
		return arrivaldate;
	}

	public void setArrivaldate(Date arrivaldate) {
		this.arrivaldate = arrivaldate;
	}

	public String getDelivertype() {
		return delivertype;
	}

	public void setDelivertype(String delivertype) {
		this.delivertype = delivertype;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
