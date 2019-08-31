package com.winway.scm.model;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 入库单主表
 * <pre> 
 * 描述：入库单主表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-06-28 14:54:15
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
 @ApiModel(value = "ScmCgGodownentryMaster",description = "入库单主表") 
public class ScmCgGodownentryMaster extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="入库单号")
	protected String billno; 
	
	@ApiModelProperty(value="供应商")
	protected String vendorcode; 
	
	@ApiModelProperty(value="品种数")
	protected String totpcs; 
	
	@ApiModelProperty(value="金额")
	protected String totamt; 
	
	@ApiModelProperty(value="入库日期")
	protected String arrivedate; 
	
	@ApiModelProperty(value="入库员")
	protected String rcvoperator; 
	
	@ApiModelProperty(value="仓库")
	protected String branchcode; 
	
	@ApiModelProperty(value="备注")
	protected String remark; 
	
	@ApiModelProperty(value="入库状态")
	protected String status; 
	
	/**
	*入库单商品表列表
	*/
	protected List<ScmCgGodownentry> list=new ArrayList<ScmCgGodownentry>(); 
	
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
	
	public void setBillno(String billno) {
		this.billno = billno;
	}
	
	/**
	 * 返回 入库单号
	 * @return
	 */
	public String getBillno() {
		return this.billno;
	}
	
	public void setVendorcode(String vendorcode) {
		this.vendorcode = vendorcode;
	}
	
	/**
	 * 返回 供应商
	 * @return
	 */
	public String getVendorcode() {
		return this.vendorcode;
	}
	
	public void setTotpcs(String totpcs) {
		this.totpcs = totpcs;
	}
	
	/**
	 * 返回 品种数
	 * @return
	 */
	public String getTotpcs() {
		return this.totpcs;
	}
	
	public void setTotamt(String totamt) {
		this.totamt = totamt;
	}
	
	/**
	 * 返回 金额
	 * @return
	 */
	public String getTotamt() {
		return this.totamt;
	}
	
	public void setArrivedate(String arrivedate) {
		this.arrivedate = arrivedate;
	}
	
	/**
	 * 返回 入库日期
	 * @return
	 */
	public String getArrivedate() {
		return this.arrivedate;
	}
	
	public void setRcvoperator(String rcvoperator) {
		this.rcvoperator = rcvoperator;
	}
	
	/**
	 * 返回 入库员
	 * @return
	 */
	public String getRcvoperator() {
		return this.rcvoperator;
	}
	
	public void setBranchcode(String branchcode) {
		this.branchcode = branchcode;
	}
	
	/**
	 * 返回 仓库
	 * @return
	 */
	public String getBranchcode() {
		return this.branchcode;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**
	 * 返回 备注
	 * @return
	 */
	public String getRemark() {
		return this.remark;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * 返回 入库状态
	 * @return
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * 返回 入库单商品表列表
	 * @return
	 */
	public List<ScmCgGodownentry> getList() {
		return list;
	}
	
	public void setList(List<ScmCgGodownentry> list) {
		this.list = list;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("billno", this.billno) 
		.append("vendorcode", this.vendorcode) 
		.append("totpcs", this.totpcs) 
		.append("totamt", this.totamt) 
		.append("arrivedate", this.arrivedate) 
		.append("rcvoperator", this.rcvoperator) 
		.append("branchcode", this.branchcode) 
		.append("remark", this.remark) 
		.append("status", this.status) 
		.toString();
	}
}