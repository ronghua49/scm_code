package com.winway.scm.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 入库单商品表
 * <pre> 
 * 描述：入库单商品表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-06-28 14:54:14
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
 @ApiModel(value = "ScmCgGodownentry",description = "入库单商品表") 
public class ScmCgGodownentry extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="入库单号")
	protected String godownCode; 
	
	@ApiModelProperty(value="产品编码")
	protected String itemcode; 
	
	@ApiModelProperty(value="产品名称")
	protected String itemname; 
	
	@ApiModelProperty(value="规格")
	protected String sizecode; 
	
	@ApiModelProperty(value="单位")
	protected String stockunit; 
	
	@ApiModelProperty(value="验收人")
	protected String qcopcode; 
	
	@ApiModelProperty(value="数量")
	protected String qty; 
	
	@ApiModelProperty(value="批号")
	protected String lotno; 
	
	@ApiModelProperty(value="生产日期")
	protected String yielddate; 
	
	@ApiModelProperty(value="有效期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	protected java.util.Date expdate; 
	
	@ApiModelProperty(value="进价")
	protected Double price; 
	
	@ApiModelProperty(value="药品类型")
	protected String linetype; 
	
	@ApiModelProperty(value="批准文号")
	protected String pch; 
	
	@ApiModelProperty(value="主表ID")
	protected String masterId; 
	
	@ApiModelProperty(value="采购订/补单编号")
	protected String billid; 
	
	public String getBillid() {
		return billid;
	}

	public void setBillid(String billid) {
		this.billid = billid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 返回 id
	 * @return
	 */
	public String getId() {
		return this.id;
	}
	
	public void setGodownCode(String godownCode) {
		this.godownCode = godownCode;
	}
	
	/**
	 * 返回 入库单号
	 * @return
	 */
	public String getGodownCode() {
		return this.godownCode;
	}
	
	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}
	
	/**
	 * 返回 产品编码
	 * @return
	 */
	public String getItemcode() {
		return this.itemcode;
	}
	
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	
	/**
	 * 返回 产品名称
	 * @return
	 */
	public String getItemname() {
		return this.itemname;
	}
	
	public void setSizecode(String sizecode) {
		this.sizecode = sizecode;
	}
	
	/**
	 * 返回 规格
	 * @return
	 */
	public String getSizecode() {
		return this.sizecode;
	}
	
	public void setStockunit(String stockunit) {
		this.stockunit = stockunit;
	}
	
	/**
	 * 返回 单位
	 * @return
	 */
	public String getStockunit() {
		return this.stockunit;
	}
	
	public void setQcopcode(String qcopcode) {
		this.qcopcode = qcopcode;
	}
	
	/**
	 * 返回 验收人
	 * @return
	 */
	public String getQcopcode() {
		return this.qcopcode;
	}
	
	public void setQty(String qty) {
		this.qty = qty;
	}
	
	/**
	 * 返回 数量
	 * @return
	 */
	public String getQty() {
		return this.qty;
	}
	
	public void setLotno(String lotno) {
		this.lotno = lotno;
	}
	
	/**
	 * 返回 批号
	 * @return
	 */
	public String getLotno() {
		return this.lotno;
	}
	
	public void setYielddate(String yielddate) {
		this.yielddate = yielddate;
	}
	
	/**
	 * 返回 生产日期
	 * @return
	 */
	public String getYielddate() {
		return this.yielddate;
	}
	
	public void setExpdate(java.util.Date expdate) {
		this.expdate = expdate;
	}
	
	/**
	 * 返回 有效期
	 * @return
	 */
	public java.util.Date getExpdate() {
		return this.expdate;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	/**
	 * 返回 进价
	 * @return
	 */
	public Double getPrice() {
		return this.price;
	}
	
	public void setLinetype(String linetype) {
		this.linetype = linetype;
	}
	
	/**
	 * 返回 药品类型
	 * @return
	 */
	public String getLinetype() {
		return this.linetype;
	}
	
	public void setPch(String pch) {
		this.pch = pch;
	}
	
	/**
	 * 返回 批准文号
	 * @return
	 */
	public String getPch() {
		return this.pch;
	}
	
	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}
	
	/**
	 * 返回 主表ID
	 * @return
	 */
	public String getMasterId() {
		return this.masterId;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("godownCode", this.godownCode) 
		.append("itemcode", this.itemcode) 
		.append("itemname", this.itemname) 
		.append("sizecode", this.sizecode) 
		.append("stockunit", this.stockunit) 
		.append("qcopcode", this.qcopcode) 
		.append("qty", this.qty) 
		.append("lotno", this.lotno) 
		.append("yielddate", this.yielddate) 
		.append("expdate", this.expdate) 
		.append("price", this.price) 
		.append("linetype", this.linetype) 
		.append("pch", this.pch) 
		.append("masterId", this.masterId) 
		.toString();
	}
}