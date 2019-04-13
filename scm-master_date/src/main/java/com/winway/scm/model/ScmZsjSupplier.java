package com.winway.scm.model;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;


 /**
 * 供应商表
 * <pre> 
 * 描述：供应商表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmZsjSupplier",description = "供应商表") 
public class ScmZsjSupplier extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="供应商名称")
	protected String supplierName; 
	
	@ApiModelProperty(value="档案编号")
	protected String fileCode; 
	
	@ApiModelProperty(value="供应商编号")
	protected String supplierCode; 
	
	@ApiModelProperty(value="企业负责人")
	protected String conpanyPersion; 
	
	@ApiModelProperty(value="联系人")
	protected String contactPersion; 
	
	@ApiModelProperty(value="联系方式")
	protected String phone; 
	
	@ApiModelProperty(value="开户行")
	protected String openBank; 
	
	@ApiModelProperty(value="银行账号")
	protected String bankCode; 
	
	@ApiModelProperty(value="税号")
	protected String dutyParagraph; 
	
	@ApiModelProperty(value="药品质量保证协议有效期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date qualityValidity; 
	
	@ApiModelProperty(value="注册地址")
	protected String registerAddress; 
	
	@ApiModelProperty(value="印章样式")
	protected String sealStyle; 
	
	@ApiModelProperty(value="随货单据样式")
	protected String billsStyle; 
	
	@ApiModelProperty(value="录入人")
	protected String enterPersion; 
	
	@ApiModelProperty(value="录入日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date enterDate; 
	
	@ApiModelProperty(value="法定代表人")
	protected String legalPersion;
	
	@ApiModelProperty(value="许可证号")
	protected String licenseKey;
	
	@ApiModelProperty(value="有效期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected Date validity;
	
	@ApiModelProperty(value="注册号")
	protected String register;
	
	@ApiModelProperty(value="营业执照有效期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected Date licenseValidity;
	
	@ApiModelProperty(value="注册资本(万元)")
	protected String registerPrice;
	
	@ApiModelProperty(value="生产/经营范围")
	protected String managementScope;
	
	@ApiModelProperty(value="生产/经营范围")
	protected String supplierNameStr;
	
	@ApiModelProperty(value="审批ID")
	protected String approvalId; 
	
	@ApiModelProperty(value="审批状态，0：未提交，1：审批中，2：已通过")
	protected String approvalState; 

	@ApiModelProperty(value="审批状态")
	protected String approvalStateStr;

	@ApiModelProperty(value="申请人")
	protected String operatorName; 
	
	@ApiModelProperty(value="营业执照注册号")
	protected String registerCode; 
	
	@ApiModelProperty(value="供应商首营ID")
	protected String supplierFirstId; 

	@ApiModelProperty(value="申请时间")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected Date operatorDate; 
	
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
	
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	public String getApprovalId() {
		return approvalId;
	}

	public void setApprovalId(String approvalId) {
		this.approvalId = approvalId;
	}

	public String getApprovalState() {
		return approvalState;
	}
	
	public String getSupplierFirstId() {
		return supplierFirstId;
	}

	public void setSupplierFirstId(String supplierFirstId) {
		this.supplierFirstId = supplierFirstId;
	}

	public String getRegisterCode() {
		return registerCode;
	}

	public void setRegisterCode(String registerCode) {
		this.registerCode = registerCode;
	}

	public void setApprovalState(String approvalState) {
		this.approvalState = approvalState;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public Date getOperatorDate() {
		return operatorDate;
	}

	public void setOperatorDate(Date operatorDate) {
		this.operatorDate = operatorDate;
	}

	/**
	 * 返回 供应商名称
	 * @return
	 */
	public String getSupplierName() {
		return this.supplierName;
	}
	
	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}
	
	/**
	 * 返回 档案编号
	 * @return
	 */
	public String getFileCode() {
		return this.fileCode;
	}
	
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	
	public String getApprovalStateStr() {
		return approvalStateStr;
	}

	public void setApprovalStateStr(String approvalStateStr) {
		this.approvalStateStr = approvalStateStr;
	}

	/**
	 * 返回 供应商编号
	 * @return
	 */
	public String getSupplierCode() {
		return this.supplierCode;
	}
	
	public void setConpanyPersion(String conpanyPersion) {
		this.conpanyPersion = conpanyPersion;
	}
	
	
	public String getLegalPersion() {
		return legalPersion;
	}

	public void setLegalPersion(String legalPersion) {
		this.legalPersion = legalPersion;
	}

	public String getLicenseKey() {
		return licenseKey;
	}

	public void setLicenseKey(String licenseKey) {
		this.licenseKey = licenseKey;
	}

	public Date getValidity() {
		return validity;
	}

	public void setValidity(Date validity) {
		this.validity = validity;
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	public Date getLicenseValidity() {
		return licenseValidity;
	}

	public void setLicenseValidity(Date licenseValidity) {
		this.licenseValidity = licenseValidity;
	}

	public String getRegisterPrice() {
		return registerPrice;
	}

	public void setRegisterPrice(String registerPrice) {
		this.registerPrice = registerPrice;
	}

	public String getManagementScope() {
		return managementScope;
	}

	public void setManagementScope(String managementScope) {
		this.managementScope = managementScope;
	}

	public String getSupplierNameStr() {
		return supplierNameStr;
	}

	public void setSupplierNameStr(String supplierNameStr) {
		this.supplierNameStr = supplierNameStr;
	}

	/**
	 * 返回 企业负责人
	 * @return
	 */
	public String getConpanyPersion() {
		return this.conpanyPersion;
	}
	
	public void setContactPersion(String contactPersion) {
		this.contactPersion = contactPersion;
	}
	
	/**
	 * 返回 联系人
	 * @return
	 */
	public String getContactPersion() {
		return this.contactPersion;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * 返回 联系方式
	 * @return
	 */
	public String getPhone() {
		return this.phone;
	}
	
	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}
	
	/**
	 * 返回 开户行
	 * @return
	 */
	public String getOpenBank() {
		return this.openBank;
	}
	
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	
	/**
	 * 返回 银行账号
	 * @return
	 */
	public String getBankCode() {
		return this.bankCode;
	}
	
	public void setDutyParagraph(String dutyParagraph) {
		this.dutyParagraph = dutyParagraph;
	}
	
	/**
	 * 返回 税号
	 * @return
	 */
	public String getDutyParagraph() {
		return this.dutyParagraph;
	}
	
	public void setQualityValidity(java.util.Date qualityValidity) {
		this.qualityValidity = qualityValidity;
	}
	
	/**
	 * 返回 药品质量保证协议有效期
	 * @return
	 */
	public java.util.Date getQualityValidity() {
		return this.qualityValidity;
	}
	
	public void setRegisterAddress(String registerAddress) {
		this.registerAddress = registerAddress;
	}
	
	/**
	 * 返回 注册地址
	 * @return
	 */
	public String getRegisterAddress() {
		return this.registerAddress;
	}
	
	public void setSealStyle(String sealStyle) {
		this.sealStyle = sealStyle;
	}
	
	/**
	 * 返回 印章样式
	 * @return
	 */
	public String getSealStyle() {
		return this.sealStyle;
	}
	
	public void setBillsStyle(String billsStyle) {
		this.billsStyle = billsStyle;
	}
	
	/**
	 * 返回 随货单据样式
	 * @return
	 */
	public String getBillsStyle() {
		return this.billsStyle;
	}
	
	public void setEnterPersion(String enterPersion) {
		this.enterPersion = enterPersion;
	}
	
	/**
	 * 返回 录入人
	 * @return
	 */
	public String getEnterPersion() {
		return this.enterPersion;
	}
	
	public void setEnterDate(java.util.Date enterDate) {
		this.enterDate = enterDate;
	}
	
	/**
	 * 返回 录入日期
	 * @return
	 */
	public java.util.Date getEnterDate() {
		return this.enterDate;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("supplierName", this.supplierName) 
		.append("fileCode", this.fileCode) 
		.append("supplierCode", this.supplierCode) 
		.append("conpanyPersion", this.conpanyPersion) 
		.append("contactPersion", this.contactPersion) 
		.append("phone", this.phone) 
		.append("openBank", this.openBank) 
		.append("bankCode", this.bankCode) 
		.append("dutyParagraph", this.dutyParagraph) 
		.append("qualityValidity", this.qualityValidity) 
		.append("registerAddress", this.registerAddress) 
		.append("sealStyle", this.sealStyle) 
		.append("billsStyle", this.billsStyle) 
		.append("enterPersion", this.enterPersion) 
		.append("enterDate", this.enterDate) 
		.toString();
	}
}