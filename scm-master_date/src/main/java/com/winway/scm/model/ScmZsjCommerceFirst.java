package com.winway.scm.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


 /**
 * 商业首营记录表
 * <pre> 
 * 描述：商业首营记录表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmZsjCommerceFirst",description = "商业首营记录表") 
public class ScmZsjCommerceFirst extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="商业ID")
	protected String commerceId; 
	
	@ApiModelProperty(value="货主ID")
	protected String ownerId; 
	
	@ApiModelProperty(value="审批ID")
	protected String approvalId; 
	
	@ApiModelProperty(value="审批状态")
	protected String approvalState; 
	
	@ApiModelProperty(value="申请人")
	protected String operatorName; 
	
	@ApiModelProperty(value="申请时间")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected Date operatorDate; 
	
	/**
	*商业生产/仓库地址列表
	*/
	@ApiModelProperty(value="商业信息")
	protected ScmZsjCommerce scmZsjCommerce; 
	
	protected List<ScmZsjCommerceWarehouse> scmZsjCommerceWarehouseList=new ArrayList<ScmZsjCommerceWarehouse>(); 
	/**
	*商业许可证信息列表
	*/
	protected List<ScmZsjCommerceLicence> scmZsjCommerceLicenceList=new ArrayList<ScmZsjCommerceLicence>(); 
	/**
	*商业营业执照信息列表
	*/
	protected List<ScmZsjCommerceBusiness> scmZsjCommerceBusinessList=new ArrayList<ScmZsjCommerceBusiness>(); 
	/**
	*商业医疗许可证列表
	*/
	protected List<ScmZsjTreatLicence> scmZsjTreatLicenceList=new ArrayList<ScmZsjTreatLicence>(); 
	/**
	*商业GSP证书信息列表
	*/
	protected List<ScmZsjGSP> scmZsjGSPList=new ArrayList<ScmZsjGSP>(); 
	/**
	*商业承诺书列表
	*/
	protected List<ScmZsjCommercePromiseBook> scmZsjCommercePromiseBookList=new ArrayList<ScmZsjCommercePromiseBook>(); 
	/**
	*商业收货地址列表
	*/
	protected List<ScmZsjCommerceShipAddress> scmZsjCommerceShipAddressList=new ArrayList<ScmZsjCommerceShipAddress>(); 
	
	/**
	*商业委托书
	*/
	protected List<ScmZsjCommerceEntruseBook> scmZsjCommerceEntruseBookList = new ArrayList<ScmZsjCommerceEntruseBook>();

	public void setId(String id) {
		this.id = id;
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
	 * 返回 id
	 * @return
	 */
	public String getId() {
		return this.id;
	}
	
	public void setCommerceId(String commerceId) {
		this.commerceId = commerceId;
	}
	
	/**
	 * 返回 商业ID
	 * @return
	 */
	public String getCommerceId() {
		return this.commerceId;
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

	public void setScmZsjCommerceWarehouseList(List<ScmZsjCommerceWarehouse> scmZsjCommerceWarehouseList) {
		this.scmZsjCommerceWarehouseList = scmZsjCommerceWarehouseList;
	}
	
	/**
	 * 返回 商业生产/仓库地址列表
	 * @return
	 */
	public List<ScmZsjCommerceWarehouse> getScmZsjCommerceWarehouseList() {
		return this.scmZsjCommerceWarehouseList;
	}

	public void setScmZsjCommerceLicenceList(List<ScmZsjCommerceLicence> scmZsjCommerceLicenceList) {
		this.scmZsjCommerceLicenceList = scmZsjCommerceLicenceList;
	}
	
	/**
	 * 返回 商业许可证信息列表
	 * @return
	 */
	public List<ScmZsjCommerceLicence> getScmZsjCommerceLicenceList() {
		return this.scmZsjCommerceLicenceList;
	}

	public void setScmZsjCommerceBusinessList(List<ScmZsjCommerceBusiness> scmZsjCommerceBusinessList) {
		this.scmZsjCommerceBusinessList = scmZsjCommerceBusinessList;
	}
	
	/**
	 * 返回 商业营业执照信息列表
	 * @return
	 */
	public List<ScmZsjCommerceBusiness> getScmZsjCommerceBusinessList() {
		return this.scmZsjCommerceBusinessList;
	}

	public void setScmZsjTreatLicenceList(List<ScmZsjTreatLicence> scmZsjTreatLicenceList) {
		this.scmZsjTreatLicenceList = scmZsjTreatLicenceList;
	}
	
	/**
	 * 返回 商业医疗许可证列表
	 * @return
	 */
	public List<ScmZsjTreatLicence> getScmZsjTreatLicenceList() {
		return this.scmZsjTreatLicenceList;
	}

	public void setScmZsjGSPList(List<ScmZsjGSP> scmZsjGSPList) {
		this.scmZsjGSPList = scmZsjGSPList;
	}
	
	/**
	 * 返回 商业GSP证书信息列表
	 * @return
	 */
	public List<ScmZsjGSP> getScmZsjGSPList() {
		return this.scmZsjGSPList;
	}

	public void setScmZsjCommercePromiseBookList(List<ScmZsjCommercePromiseBook> scmZsjCommercePromiseBookList) {
		this.scmZsjCommercePromiseBookList = scmZsjCommercePromiseBookList;
	}
	
	/**
	 * 返回 商业承诺书列表
	 * @return
	 */
	public List<ScmZsjCommercePromiseBook> getScmZsjCommercePromiseBookList() {
		return this.scmZsjCommercePromiseBookList;
	}

	public void setScmZsjCommerceShipAddressList(List<ScmZsjCommerceShipAddress> scmZsjCommerceShipAddressList) {
		this.scmZsjCommerceShipAddressList = scmZsjCommerceShipAddressList;
	}
	
	/**
	 * 返回 商业收货地址列表
	 * @return
	 */
	public List<ScmZsjCommerceShipAddress> getScmZsjCommerceShipAddressList() {
		return this.scmZsjCommerceShipAddressList;
	}
	
	/**
	 * 返回 商业授权书
	 * @return
	 */
	public List<ScmZsjCommerceEntruseBook> getScmZsjCommerceEntruseBookList() {
		return scmZsjCommerceEntruseBookList;
	}

	public void setScmZsjCommerceEntruseBookList(List<ScmZsjCommerceEntruseBook> scmZsjCommerceEntruseBookList) {
		this.scmZsjCommerceEntruseBookList = scmZsjCommerceEntruseBookList;
	}
	
	public ScmZsjCommerce getScmZsjCommerce() {
		return scmZsjCommerce;
	}

	public void setScmZsjCommerce(ScmZsjCommerce scmZsjCommerce) {
		this.scmZsjCommerce = scmZsjCommerce;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("commerceId", this.commerceId) 
		.append("ownerId", this.ownerId) 
		.append("approvalId", this.approvalId) 
		.append("approvalState", this.approvalState) 
		.toString();
	}
}