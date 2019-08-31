package com.winway.scm.model;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 库存调拨总表
 * <pre> 
 * 描述：库存调拨总表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-23 17:00:46
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmKcAllotMaster",description = "库存调拨总表") 
public class ScmKcAllotMaster extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="单号",required = true)
	protected String allotCode; 
	
	@ApiModelProperty(value="出库库区",required = true)
	protected String outboundArea; 
	
	@ApiModelProperty(value="出库编号",required = true)
	protected String outboundCode; 
	
	@ApiModelProperty(value="出库时间",required = true)
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	protected java.util.Date outboundDate; 
	
	@ApiModelProperty(value="品种数",required = true)
	protected Integer varietySum; 
	
	@ApiModelProperty(value="总金额",required = true)
	protected Double priceSum; 
	
	@ApiModelProperty(value="入库库区",required = true)
	protected String storageArea; 
	
	@ApiModelProperty(value="入库编号",required = true)
	protected String storageCode; 
	
	@ApiModelProperty(value="入库时间",required = true)
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	protected java.util.Date storageDate; 
	
	@ApiModelProperty(value="通过时间",required = true)
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	protected java.util.Date successDate; 
	
	@ApiModelProperty(value="申请人")
	protected String createPersion; 
	
	@ApiModelProperty(value="申请时间")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	protected java.util.Date createDate;

	 @ApiModelProperty(value="审批ID")
	 protected String approvalId;

	 @ApiModelProperty(value="审批状态")
	 protected String approvalState;

	 @ApiModelProperty(value="货主id")
	 protected String ownerId;

	 @ApiModelProperty(value="是否推送值SMS 0未推送；1已推送")
	 protected String isToWms;
	 
	 @ApiModelProperty(value="WMS确认状态(0:未确认,1:已出库,2已入库,3:已完成)")
	 protected String isAffirmWms;
	 
	 @ApiModelProperty(value="sap是否处理(0:未处理,1:已处理)")
	 protected String isManageSap;
	 
	 @ApiModelProperty(value="sap处理节点(0:未处理,1已发货,2已收货,3:已完成)")
	 protected String sapManageType;
	 
	 public String getIsManageSap() {
		return isManageSap;
	}

	public void setIsManageSap(String isManageSap) {
		this.isManageSap = isManageSap;
	}

	public String getSapManageType() {
		return sapManageType;
	}

	public void setSapManageType(String sapManageType) {
		this.sapManageType = sapManageType;
	}

	public String getIsToWms() {
		return isToWms;
	}

	public void setIsToWms(String isToWms) {
		this.isToWms = isToWms;
	}

	public String getIsAffirmWms() {
		return isAffirmWms;
	}

	public void setIsAffirmWms(String isAffirmWms) {
		this.isAffirmWms = isAffirmWms;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getOwnerId() {
		 return ownerId;
	 }

	 public void setOwnerId(String ownerId) {
		 this.ownerId = ownerId;
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

	 public void setApprovalState(String approvalState) {
		 this.approvalState = approvalState;
	 }

	 /**
	*库存调拨商品表列表
	*/
	protected List<ScmKcAllot> scmKcAllotList=new ArrayList<ScmKcAllot>(); 
	
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
	
	public void setAllotCode(String allotCode) {
		this.allotCode = allotCode;
	}
	
	/**
	 * 返回 单号
	 * @return
	 */
	public String getAllotCode() {
		return this.allotCode;
	}
	
	public void setOutboundArea(String outboundArea) {
		this.outboundArea = outboundArea;
	}
	
	/**
	 * 返回 出库库区
	 * @return
	 */
	public String getOutboundArea() {
		return this.outboundArea;
	}
	
	public void setOutboundCode(String outboundCode) {
		this.outboundCode = outboundCode;
	}
	
	/**
	 * 返回 出库编号
	 * @return
	 */
	public String getOutboundCode() {
		return this.outboundCode;
	}
	
	public void setOutboundDate(java.util.Date outboundDate) {
		this.outboundDate = outboundDate;
	}
	
	/**
	 * 返回 出库时间
	 * @return
	 */
	public java.util.Date getOutboundDate() {
		return this.outboundDate;
	}
	
	public void setVarietySum(Integer varietySum) {
		this.varietySum = varietySum;
	}
	
	/**
	 * 返回 品种数
	 * @return
	 */
	public Integer getVarietySum() {
		return this.varietySum;
	}
	
	public void setPriceSum(Double priceSum) {
		this.priceSum = priceSum;
	}
	
	/**
	 * 返回 总金额
	 * @return
	 */
	public Double getPriceSum() {
		return this.priceSum;
	}
	
	public void setStorageArea(String storageArea) {
		this.storageArea = storageArea;
	}
	
	/**
	 * 返回 入库库区
	 * @return
	 */
	public String getStorageArea() {
		return this.storageArea;
	}
	
	public void setStorageCode(String storageCode) {
		this.storageCode = storageCode;
	}
	
	/**
	 * 返回 入库编号
	 * @return
	 */
	public String getStorageCode() {
		return this.storageCode;
	}
	
	public void setStorageDate(java.util.Date storageDate) {
		this.storageDate = storageDate;
	}
	
	/**
	 * 返回 入库时间
	 * @return
	 */
	public java.util.Date getStorageDate() {
		return this.storageDate;
	}
	
	public void setSuccessDate(java.util.Date successDate) {
		this.successDate = successDate;
	}
	
	/**
	 * 返回 通过时间
	 * @return
	 */
	public java.util.Date getSuccessDate() {
		return this.successDate;
	}
	
	public void setCreatePersion(String createPersion) {
		this.createPersion = createPersion;
	}
	
	/**
	 * 返回 申请人
	 * @return
	 */
	public String getCreatePersion() {
		return this.createPersion;
	}
	
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 返回 申请时间
	 * @return
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}

	public void setScmKcAllotList(List<ScmKcAllot> scmKcAllotList) {
		this.scmKcAllotList = scmKcAllotList;
	}
	
	/**
	 * 返回 库存调拨商品表列表
	 * @return
	 */
	public List<ScmKcAllot> getScmKcAllotList() {
		return this.scmKcAllotList;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("allotCode", this.allotCode) 
		.append("outboundArea", this.outboundArea) 
		.append("outboundCode", this.outboundCode) 
		.append("outboundDate", this.outboundDate) 
		.append("varietySum", this.varietySum) 
		.append("priceSum", this.priceSum) 
		.append("storageArea", this.storageArea) 
		.append("storageCode", this.storageCode) 
		.append("storageDate", this.storageDate) 
		.append("successDate", this.successDate) 
		.append("createPersion", this.createPersion) 
		.append("createDate", this.createDate) 
		.toString();
	}
}