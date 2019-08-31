package com.winway.scm.vo;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**WMS采购入库单查询VO*/
@ApiModel(value = "WmsfindcgrkVo",description = "WMS采购入库单查询VO") 
public class WmsfindcgrkVo {
	@ApiModelProperty(value="入库单号")
	private String billno;
	
	@ApiModelProperty(value="供应商")
	private String vendorname;
	
	@ApiModelProperty(value="品种数")
	private String totpcs;
	
	@ApiModelProperty(value="金额")
	private String totamt;
	
	@ApiModelProperty(value="入库日期")
	private String arrivedate;
	
	@ApiModelProperty(value="入库员")
	private String rcvoperator;
	
	@ApiModelProperty(value="仓库")
	private String branchcode;
	
	@ApiModelProperty(value="备注")
	private String remark;
	
	@ApiModelProperty(value="入库状态")
	private String status;

	@ApiModelProperty(value="入库单详情数组")
	private List<WmsfindcgrkDetailVo> productList = new ArrayList<WmsfindcgrkDetailVo>();
	
	@Override
	public String toString() {
		return "WmsfindcgrkVo [billno=" + billno + ", vendorname=" + vendorname + ", totpcs=" + totpcs + ", totamt="
				+ totamt + ", arrivedate=" + arrivedate + ", rcvoperator=" + rcvoperator + ", branchcode=" + branchcode
				+ ", remark=" + remark + ", status=" + status + "]";
	}

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

	public String getTotpcs() {
		return totpcs;
	}

	public void setTotpcs(String totpcs) {
		this.totpcs = totpcs;
	}

	public String getTotamt() {
		return totamt;
	}

	public void setTotamt(String totamt) {
		this.totamt = totamt;
	}

	public String getArrivedate() {
		return arrivedate;
	}

	public void setArrivedate(String arrivedate) {
		this.arrivedate = arrivedate;
	}

	public String getRcvoperator() {
		return rcvoperator;
	}

	public void setRcvoperator(String rcvoperator) {
		this.rcvoperator = rcvoperator;
	}

	public String getBranchcode() {
		return branchcode;
	}

	public void setBranchcode(String branchcode) {
		this.branchcode = branchcode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<WmsfindcgrkDetailVo> getProductList() {
		return productList;
	}

	public void setProductList(List<WmsfindcgrkDetailVo> productList) {
		this.productList = productList;
	}
	
	
	
	
}
