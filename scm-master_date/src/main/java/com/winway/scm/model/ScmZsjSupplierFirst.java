package com.winway.scm.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;


 /**
 * 供应商首营记录表
 * <pre> 
 * 描述：供应商首营记录表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmZsjSupplierFirst",description = "供应商首营记录表") 
public class ScmZsjSupplierFirst extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="供应商ID")
	protected String supplierId; 
	
	@ApiModelProperty(value="货主ID")
	protected String ownerId; 
	
	@ApiModelProperty(value="审批ID")
	protected String approvalId; 
	
	@ApiModelProperty(value="审批状态")
	protected String approvalState; 
	
	@ApiModelProperty(value="经销商信息")
	protected ScmZsjSupplier scmZsjSupplier; 
	
	@ApiModelProperty(value="申请人")
	protected String operatorName; 
	
	@ApiModelProperty(value="申请时间")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected Date operatorDate; 
	
	@ApiModelProperty(value="药品质量保证协议有效期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected Date qualityValidity; 
	
	@ApiModelProperty(value="联系人")
	protected String contactPersion; 
	
	@ApiModelProperty(value="联系方式")
	protected String phone; 
	
	@ApiModelProperty(value="印章样式")
	protected String sealStyle; 
	
	@ApiModelProperty(value="随货单据样式")
	protected String billsStyle;


	 @ApiModelProperty(value="生产仓库地址拼接")
	 protected String addressStr;

	 public String getAddressStr() {
		 return addressStr;
	 }

	 public void setAddressStr(String addressStr) {
		 this.addressStr = addressStr;
	 }

	 /**
	*供应商生产/仓库地址列表
	*/
	protected List<ScmZsjSupplierWarehouse> scmZsjSupplierWarehouseList=new ArrayList<ScmZsjSupplierWarehouse>(); 
	/**
	*供应商许可证信息列表
	*/
	protected List<ScmZsjSupplierLicence> scmZsjSupplierLicenceList=new ArrayList<ScmZsjSupplierLicence>(); 
	/**
	*供应商营业执照信息列表
	*/
	protected List<ScmZsjSupplierBusiness> scmZsjSupplierBusinessList=new ArrayList<ScmZsjSupplierBusiness>(); 
	/**
	*供应商GMP证书信息列表
	*/
	protected List<ScmZsjGMP> scmZsjGMPList=new ArrayList<ScmZsjGMP>(); 
	/**
	*供应商委托书列表
	*/
	protected List<ScmZsjSupplierEntruseBook> scmZsjSupplierEntruseBookList=new ArrayList<ScmZsjSupplierEntruseBook>(); 
	/**
	*收货地址列表
	*/
	protected List<ScmZsjSupplierShipAddress> scmZsjSupplierShipAddressList=new ArrayList<ScmZsjSupplierShipAddress>(); 
	
	/**
	*承诺书列表
	*/
	protected List<ScmZsjSupplierPromiseBook> scmZsjSupplierPromiseBookList = new ArrayList<ScmZsjSupplierPromiseBook>();

	/**
	 * 附件
	 */
	protected List<ScmZsjAccessory> scmZsjAccessoryList = new ArrayList<ScmZsjAccessory>();

	 @ApiModelProperty(value="是否首映通过过（0否1是）")
	 protected String isPassed="0";

	 public String getIsPassed() {
		 return isPassed;
	 }

	 public void setIsPassed(String isPassed) {
		 this.isPassed = isPassed;
	 }


	 public List<ScmZsjAccessory> getScmZsjAccessoryList() {
		return scmZsjAccessoryList;
	}

	public void setScmZsjAccessoryList(List<ScmZsjAccessory> scmZsjAccessoryList) {
		this.scmZsjAccessoryList = scmZsjAccessoryList;
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
	
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
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

	public Date getQualityValidity() {
		return qualityValidity;
	}

	public void setQualityValidity(Date qualityValidity) {
		this.qualityValidity = qualityValidity;
	}

	public String getContactPersion() {
		return contactPersion;
	}

	public void setContactPersion(String contactPersion) {
		this.contactPersion = contactPersion;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSealStyle() {
		return sealStyle;
	}

	public void setSealStyle(String sealStyle) {
		this.sealStyle = sealStyle;
	}

	public String getBillsStyle() {
		return billsStyle;
	}

	public void setBillsStyle(String billsStyle) {
		this.billsStyle = billsStyle;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 返回 供应商ID
	 * @return
	 */
	public String getSupplierId() {
		return this.supplierId;
	}
	
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	
	/**
	 * 返回 货主ID
	 * @return
	 */
	public String getOwnerId() {
		return this.ownerId;
	}
	
	public void setApprovalId(String approvalId) {
		this.approvalId = approvalId;
	}
	
	/**
	 * 返回 审批ID
	 * @return
	 */
	public String getApprovalId() {
		return this.approvalId;
	}
	
	public void setApprovalState(String approvalState) {
		this.approvalState = approvalState;
	}
	
	/**
	 * 返回 审批状态
	 * @return
	 */
	public String getApprovalState() {
		return this.approvalState;
	}

	public void setScmZsjSupplierWarehouseList(List<ScmZsjSupplierWarehouse> scmZsjSupplierWarehouseList) {
		this.scmZsjSupplierWarehouseList = scmZsjSupplierWarehouseList;
	}
	
	/**
	 * 返回 供应商生产/仓库地址列表
	 * @return
	 */
	public List<ScmZsjSupplierWarehouse> getScmZsjSupplierWarehouseList() {
		return this.scmZsjSupplierWarehouseList;
	}

	public void setScmZsjSupplierLicenceList(List<ScmZsjSupplierLicence> scmZsjSupplierLicenceList) {
		this.scmZsjSupplierLicenceList = scmZsjSupplierLicenceList;
	}
	
	/**
	 * 返回 供应商许可证信息列表
	 * @return
	 */
	public List<ScmZsjSupplierLicence> getScmZsjSupplierLicenceList() {
		return this.scmZsjSupplierLicenceList;
	}

	public void setScmZsjSupplierBusinessList(List<ScmZsjSupplierBusiness> scmZsjSupplierBusinessList) {
		this.scmZsjSupplierBusinessList = scmZsjSupplierBusinessList;
	}
	
	/**
	 * 返回 供应商营业执照信息列表
	 * @return
	 */
	public List<ScmZsjSupplierBusiness> getScmZsjSupplierBusinessList() {
		return this.scmZsjSupplierBusinessList;
	}

	public void setScmZsjGMPList(List<ScmZsjGMP> scmZsjGMPList) {
		this.scmZsjGMPList = scmZsjGMPList;
	}
	
	/**
	 * 返回 供应商GMP证书信息列表
	 * @return
	 */
	public List<ScmZsjGMP> getScmZsjGMPList() {
		return this.scmZsjGMPList;
	}

	public void setScmZsjSupplierEntruseBookList(List<ScmZsjSupplierEntruseBook> scmZsjSupplierEntruseBookList) {
		this.scmZsjSupplierEntruseBookList = scmZsjSupplierEntruseBookList;
	}
	
	/**
	 * 返回 供应商委托书列表
	 * @return
	 */
	public List<ScmZsjSupplierEntruseBook> getScmZsjSupplierEntruseBookList() {
		return this.scmZsjSupplierEntruseBookList;
	}

	public void setScmZsjSupplierShipAddressList(List<ScmZsjSupplierShipAddress> scmZsjSupplierShipAddressList) {
		this.scmZsjSupplierShipAddressList = scmZsjSupplierShipAddressList;
	}
	
	/**
	 * 返回 收货地址列表
	 * @return
	 */
	public List<ScmZsjSupplierShipAddress> getScmZsjSupplierShipAddressList() {
		return this.scmZsjSupplierShipAddressList;
	}
	
	/**
	 * 返回 承诺书列表
	 * @return
	 */
	public List<ScmZsjSupplierPromiseBook> getScmZsjSupplierPromiseBookList() {
		return scmZsjSupplierPromiseBookList;
	}
	
	public void setScmZsjSupplierPromiseBookList(List<ScmZsjSupplierPromiseBook> scmZsjSupplierPromiseBookList) {
		this.scmZsjSupplierPromiseBookList = scmZsjSupplierPromiseBookList;
	}
    
	public ScmZsjSupplier getScmZsjSupplier() {
		return scmZsjSupplier;
	}

	public void setScmZsjSupplier(ScmZsjSupplier scmZsjSupplier) {
		this.scmZsjSupplier = scmZsjSupplier;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("supplierId", this.supplierId) 
		.append("ownerId", this.ownerId) 
		.append("approvalId", this.approvalId) 
		.append("approvalState", this.approvalState) 
		.toString();
	}

}