package com.winway.scm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**WMS采购验收单查询VO*/
@ApiModel(value = "WmsfindcgysVo",description = "WMS采购验收") 
public class WmsfindcgysVo {
	@ApiModelProperty(value="验收单号")
	private String billno;
	
	@ApiModelProperty(value="收货状态")
	private String status;
	
	@ApiModelProperty(value="供应商")
	private String vendorname;
	
	@ApiModelProperty(value="品种数")
	private String totpcs;
	
	@ApiModelProperty(value="金额")
	private String totamt;
	
	@ApiModelProperty(value="到货数量")
	private String arrivalqty;
	
	@ApiModelProperty(value="到货日期")
	private String arrivaldate;
	
	@ApiModelProperty(value="验收合格数量")
	private String qualifiedqty;
	
	@ApiModelProperty(value="备注")
	private String remark;
	
	@Override
	public String toString() {
		return "WmsfindcgysVo [billno=" + billno + ", status=" + status + ", vendorname=" + vendorname + ", totpcs="
				+ totpcs + ", totamt=" + totamt + ", arrivalqty=" + arrivalqty + ", arrivaldate=" + arrivaldate
				+ ", qualifiedqty=" + qualifiedqty + ", remark=" + remark + "]";
	}

	public String getBillno() {
		return billno;
	}

	public void setBillno(String billno) {
		this.billno = billno;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getArrivalqty() {
		return arrivalqty;
	}

	public void setArrivalqty(String arrivalqty) {
		this.arrivalqty = arrivalqty;
	}

	public String getArrivaldate() {
		return arrivaldate;
	}

	public void setArrivaldate(String arrivaldate) {
		this.arrivaldate = arrivaldate;
	}

	public String getQualifiedqty() {
		return qualifiedqty;
	}

	public void setQualifiedqty(String qualifiedqty) {
		this.qualifiedqty = qualifiedqty;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
