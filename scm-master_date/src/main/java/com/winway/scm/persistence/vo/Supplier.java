package com.winway.scm.persistence.vo;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 供应商表
 * <pre> 
 * 描述：供应商表 实体对象
 * 构建组：x7
 * 作者:郑勇彬
 * 邮箱:PRD-Yongbin.zheng@Winwayworld.com
 * 日期:2019-06-10 14:15:38
 * 版权：深圳万威科技有限公司
 * </pre>
 */
 @ApiModel(value = "Supplier",description = "供应商表") 
public class Supplier extends BaseModel<String>{

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
	protected java.util.Date qualityValidity;
	
	@ApiModelProperty(value="注册地址")
	protected String registerAddress;
	
	@ApiModelProperty(value="印章样式")
	protected char sealStyle;
	
	@ApiModelProperty(value="随货单据样式")
	protected char billsStyle;
	
	@ApiModelProperty(value="录入人")
	protected String enterPersion;
	
	@ApiModelProperty(value="录入日期")
	protected java.util.Date enterDate;
	
    @ApiModelProperty(value="是否删除")
    private String isDelete;
	
	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public void setId(String id) {
		this.id = id;
	}
	
	
	
	public Supplier() {
		super();
		// TODO Auto-generated constructor stub
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
	
	public void setSealStyle(char sealStyle) {
		this.sealStyle = sealStyle;
	}
	
	/**
	 * 返回 印章样式
	 * @return
	 */
	public char getSealStyle() {
		return this.sealStyle;
	}
	
	public void setBillsStyle(char billsStyle) {
		this.billsStyle = billsStyle;
	}
	
	/**
	 * 返回 随货单据样式
	 * @return
	 */
	public char getBillsStyle() {
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