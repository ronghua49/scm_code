package com.winway.scm.model;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 药品拒收报告单主表
 * <pre> 
 * 描述：药品拒收报告单主表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-24 12:00:21
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmGspRejectionMaster",description = "药品拒收报告单主表") 
public class ScmGspRejectionMaster extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="供应商",required = true)
	protected String supplierName; 
	
	@ApiModelProperty(value="供应商ID",required = true)
	protected String supplierId; 
	
	@ApiModelProperty(value="供应商编号",required = true)
	protected String supplierCode; 
	
	@ApiModelProperty(value="收货仓库",required = true)
	protected String getMoneyWarehouse; 
	
	@ApiModelProperty(value="收货仓库编号",required = true)
	protected String getMoneyWarehouseCode; 
	
	@ApiModelProperty(value="采购合同编号",required = true)
	protected String contractCode; 
	
	@ApiModelProperty(value="采购订单编号",required = true)
	protected String procurementCode; 
	
	@ApiModelProperty(value="货主ID",required = true)
	protected String ownerId; 
	
	@ApiModelProperty(value="审批ID")
	protected String approvalId; 
	
	@ApiModelProperty(value="审批状态")
	protected String approvalState; 
	
	@ApiModelProperty(value="创建人")
	protected String createPersion; 
	
	@ApiModelProperty(value="拒收单号")
	protected String rejectionCode; 
	
	@ApiModelProperty(value="创建时间")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date createDate;

	 @ApiModelProperty(value="种类数")
	 protected String kindSum;

	 @ApiModelProperty(value="药品数量")
	 protected String totalNum;

	 @ApiModelProperty(value="总金额")
	 protected Double priceSum;

	 @ApiModelProperty(value="是否推送值SMS 0未推送；1已推送",required = true)
	 protected String isSMS="0";
	 
	 @ApiModelProperty(value="WMS确认状态(0:未确认,1:已确认)")
	 protected String isAffirmWms;
	 
	 public String getIsSMS() {
		 return isSMS;
	 }

	 public void setIsSMS(String isSMS) {
		 this.isSMS = isSMS;
	 }

	 public String getKindSum() {
		 return kindSum;
	 }

	 public void setKindSum(String kindSum) {
		 this.kindSum = kindSum;
	 }

	 public String getTotalNum() {
		 return totalNum;
	 }

	 public void setTotalNum(String totalNum) {
		 this.totalNum = totalNum;
	 }

	 public Double getPriceSum() {
		 return priceSum;
	 }

	 public void setPriceSum(Double priceSum) {
		 this.priceSum = priceSum;
	 }

	 /**
	*药品拒收商品表列表
	*/
	protected List<ScmGspRejectionProduct> scmGspRejectionProductList=new ArrayList<ScmGspRejectionProduct>(); 
	
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
	
	
	public String getRejectionCode() {
		return rejectionCode;
	}

	public void setRejectionCode(String rejectionCode) {
		this.rejectionCode = rejectionCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	/**
	 * 返回 供应商
	 * @return
	 */
	public String getSupplierName() {
		return this.supplierName;
	}
	
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	
	/**
	 * 返回 供应商ID
	 * @return
	 */
	public String getSupplierId() {
		return this.supplierId;
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
	
	public void setGetMoneyWarehouse(String getMoneyWarehouse) {
		this.getMoneyWarehouse = getMoneyWarehouse;
	}
	
	/**
	 * 返回 收货仓库
	 * @return
	 */
	public String getGetMoneyWarehouse() {
		return this.getMoneyWarehouse;
	}
	
	public void setGetMoneyWarehouseCode(String getMoneyWarehouseCode) {
		this.getMoneyWarehouseCode = getMoneyWarehouseCode;
	}
	
	/**
	 * 返回 收货仓库编号
	 * @return
	 */
	public String getGetMoneyWarehouseCode() {
		return this.getMoneyWarehouseCode;
	}
	
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	
	/**
	 * 返回 采购合同编号
	 * @return
	 */
	public String getContractCode() {
		return this.contractCode;
	}
	
	public void setProcurementCode(String procurementCode) {
		this.procurementCode = procurementCode;
	}
	
	/**
	 * 返回 采购订单编号
	 * @return
	 */
	public String getProcurementCode() {
		return this.procurementCode;
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
	
	public void setCreatePersion(String createPersion) {
		this.createPersion = createPersion;
	}
	
	/**
	 * 返回 创建人
	 * @return
	 */
	public String getCreatePersion() {
		return this.createPersion;
	}
	
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 返回 创建时间
	 * @return
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}

	public void setScmGspRejectionProductList(List<ScmGspRejectionProduct> scmGspRejectionProductList) {
		this.scmGspRejectionProductList = scmGspRejectionProductList;
	}
	
	/**
	 * 返回 药品拒收商品表列表
	 * @return
	 */
	public List<ScmGspRejectionProduct> getScmGspRejectionProductList() {
		return this.scmGspRejectionProductList;
	}
	
	/**
	 * 返回 WMS确认状态(0:未确认,1:已确认)
	 * @return
	 */
	public String getIsAffirmWms() {
		return isAffirmWms;
	}

	public void setIsAffirmWms(String isAffirmWms) {
		this.isAffirmWms = isAffirmWms;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("supplierName", this.supplierName) 
		.append("supplierId", this.supplierId) 
		.append("supplierCode", this.supplierCode) 
		.append("getMoneyWarehouse", this.getMoneyWarehouse) 
		.append("getMoneyWarehouseCode", this.getMoneyWarehouseCode) 
		.append("contractCode", this.contractCode) 
		.append("procurementCode", this.procurementCode) 
		.append("ownerId", this.ownerId) 
		.append("approvalId", this.approvalId) 
		.append("approvalState", this.approvalState) 
		.append("createPersion", this.createPersion) 
		.append("createDate", this.createDate) 
		.toString();
	}
}