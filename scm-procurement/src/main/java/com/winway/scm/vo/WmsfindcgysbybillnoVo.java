package com.winway.scm.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**WMS采购验收单查询详情VO*/
@ApiModel(value = "WmsfindcgysbybillnoVo",description = "WMS采购验收单查询返回") 
public class WmsfindcgysbybillnoVo {
	@ApiModelProperty(value="验收单号")
	private String billno;
	
	@ApiModelProperty(value="供应商")
	private String vendorname;
	
	@ApiModelProperty(value="验收日期")
	private String checkdate;
	
	@ApiModelProperty(value="采购人")
	private String buyerid;
	
	@ApiModelProperty(value="验收状态")
	private String status;
	
	@ApiModelProperty(value="备注")
	private String remark;
	
	@ApiModelProperty(value="验收商品列表")
	private List<WmsfindcgysDetailVo> list;
	
	
	
	public String getBillno() {
		return billno;
	}
	public void setBillno(String billno) {
		this.billno = billno;
	}
	public String getVendorname() {
		return vendorname;
	}
	public void setVendorname(String vendorname) {
		this.vendorname = vendorname;
	}
	public String getCheckdate() {
		return checkdate;
	}
	public void setCheckdate(String checkdate) {
		this.checkdate = checkdate;
	}
	public String getBuyerid() {
		return buyerid;
	}
	public void setBuyerid(String buyerid) {
		this.buyerid = buyerid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public List<WmsfindcgysDetailVo> getList() {
		return list;
	}
	public void setList(List<WmsfindcgysDetailVo> list) {
		this.list = list;
	}
	
}
