package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;

import java.util.Date;


/**
 * 发货任务表
 * <pre> 
 * 描述：发货任务表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-02-15 17:31:50
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmFhShipmentsTask",description = "发货任务表") 
public class ScmFhShipmentsTask extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id;

	 @ApiModelProperty(value="货主ID")
	 protected String ownerId;

	@ApiModelProperty(value="类型(0:发货任务,1:剩余任务)")
	protected String type; 
	
	@ApiModelProperty(value="任务类型(0:集中发货,1:分配,2日常合同)")
	protected String taskType; 
	
	@ApiModelProperty(value="数据编号")
	protected String dateCode; 
	
	@ApiModelProperty(value="任务生成时间")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected Date taskCreateDate;
	
	@ApiModelProperty(value="付款类型(0:预付款,1赊销)")
	protected String paymentType; 
	
	@ApiModelProperty(value="集中发货分配月")
	protected String focusShipmentsMonth; 
	
	@ApiModelProperty(value="是否SMS")
	protected String isSms; 
	
	@ApiModelProperty(value="商业名称")
	protected String commerceName; 
	
	@ApiModelProperty(value="商业id")
	protected String commerceId; 
	
	@ApiModelProperty(value="商品名称")
	protected String productName; 
	
	@ApiModelProperty(value="药品编号")
	protected String productNum; 
	
	@ApiModelProperty(value="药品类型")
	protected String MedicineType; 
	
	@ApiModelProperty(value="规格型号")
	protected String productState; 
	
	@ApiModelProperty(value="件装量")
	protected String aChange;

	@ApiModelProperty(value="件数")
	protected  int packageNum;

	public int getPackageNum() {
		return packageNum;
	}

	public void setPackageNum(int packageNum) {
		this.packageNum = packageNum;
	}

	@ApiModelProperty(value="客户类型")
	protected String commercetype; 
	
	@ApiModelProperty(value="销售类型")
	protected String marketType; 
	
	@ApiModelProperty(value="单价")
	protected Double price; 
	
	@ApiModelProperty(value="备注")
	protected String memo; 
	
	@ApiModelProperty(value="计划发货量")
	protected Integer planShipmentsSum; 
	
	@ApiModelProperty(value="联系人")
	protected String contactPersion; 
	
	@ApiModelProperty(value="联系电话")
	protected String phone; 
	@ApiModelProperty(value="总价")
	protected Double sumPrice; 
	@ApiModelProperty(value="合同编号")
	protected String allocationCode; 
	@ApiModelProperty(value="计划发货时间")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date sendProductDate; 
	//非数据库字段
	@ApiModelProperty(value="地址")
	protected String address; 
	@ApiModelProperty(value="商务分区")
	protected String businessDivision; 
	@ApiModelProperty(value="省区")
	protected String province; 
	@ApiModelProperty(value="是否禁售或者停售")
	protected String state;
	
	@ApiModelProperty(value="是否禁止发货")
	protected String banDelivery;
	@ApiModelProperty(value="禁止发货信息")
	protected String banDeliveryMessage;
	
	
	
	public String getBanDelivery() {
		return banDelivery;
	}

	public void setBanDelivery(String banDelivery) {
		this.banDelivery = banDelivery;
	}

	public String getBanDeliveryMessage() {
		return banDeliveryMessage;
	}

	public void setBanDeliveryMessage(String banDeliveryMessage) {
		this.banDeliveryMessage = banDeliveryMessage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
	
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * 返回 类型(0:发货任务,1:剩余任务)
	 * @return
	 */
	public String getType() {
		return this.type;
	}
	
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	
	/**
	 * 返回 任务类型(0:集中发货,1:分配,2日常合同)
	 * @return
	 */
	public String getTaskType() {
		return this.taskType;
	}
	
	public void setDateCode(String dateCode) {
		this.dateCode = dateCode;
	}
	
	/**
	 * 返回 数据编号
	 * @return
	 */
	public String getDateCode() {
		return this.dateCode;
	}
	
	public void setTaskCreateDate(java.util.Date taskCreateDate) {
		this.taskCreateDate = taskCreateDate;
	}
	
	/**
	 * 返回 任务生成时间
	 * @return
	 */
	public java.util.Date getTaskCreateDate() {
		return this.taskCreateDate;
	}
	
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	
	/**
	 * 返回 付款类型(0:预付款,1赊销)
	 * @return
	 */
	public String getPaymentType() {
		return this.paymentType;
	}
	
	public void setFocusShipmentsMonth(String focusShipmentsMonth) {
		this.focusShipmentsMonth = focusShipmentsMonth;
	}
	
	/**
	 * 返回 集中发货分配月
	 * @return
	 */
	public String getFocusShipmentsMonth() {
		return this.focusShipmentsMonth;
	}
	
	public void setIsSms(String isSms) {
		this.isSms = isSms;
	}
	
	/**
	 * 返回 是否SMS
	 * @return
	 */
	public String getIsSms() {
		return this.isSms;
	}
	
	public void setCommerceName(String commerceName) {
		this.commerceName = commerceName;
	}
	
	/**
	 * 返回 商业名称
	 * @return
	 */
	public String getCommerceName() {
		return this.commerceName;
	}
	
	public void setCommerceId(String commerceId) {
		this.commerceId = commerceId;
	}
	
	/**
	 * 返回 商业id
	 * @return
	 */
	public String getCommerceId() {
		return this.commerceId;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	/**
	 * 返回 商品名称
	 * @return
	 */
	public String getProductName() {
		return this.productName;
	}
	
	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}
	
	/**
	 * 返回 药品编号
	 * @return
	 */
	public String getProductNum() {
		return this.productNum;
	}
	
	public void setMedicineType(String MedicineType) {
		this.MedicineType = MedicineType;
	}
	
	/**
	 * 返回 药品类型
	 * @return
	 */
	public String getMedicineType() {
		return this.MedicineType;
	}
	
	public void setProductState(String productState) {
		this.productState = productState;
	}
	
	/**
	 * 返回 规格型号
	 * @return
	 */
	public String getProductState() {
		return this.productState;
	}
	
	public void setAChange(String aChange) {
		this.aChange = aChange;
	}
	
	/**
	 * 返回 件装量
	 * @return
	 */
	public String getAChange() {
		return this.aChange;
	}
	
	public void setCommercetype(String commercetype) {
		this.commercetype = commercetype;
	}
	
	/**
	 * 返回 客户类型
	 * @return
	 */
	public String getCommercetype() {
		return this.commercetype;
	}
	
	public void setMarketType(String marketType) {
		this.marketType = marketType;
	}
	
	/**
	 * 返回 销售类型
	 * @return
	 */
	public String getMarketType() {
		return this.marketType;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	/**
	 * 返回 单价
	 * @return
	 */
	public Double getPrice() {
		return this.price;
	}
	
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	/**
	 * 返回 备注
	 * @return
	 */
	public String getMemo() {
		return this.memo;
	}
	
	public void setPlanShipmentsSum(Integer planShipmentsSum) {
		this.planShipmentsSum = planShipmentsSum;
	}
	
	/**
	 * 返回 计划发货量
	 * @return
	 */
	public Integer getPlanShipmentsSum() {
		return this.planShipmentsSum;
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
	 * 返回 联系电话
	 * @return
	 */
	public String getPhone() {
		return this.phone;
	}
	 /**
	  * 返回 货主ID
	  * @return
	  */
	 public String getOwnerId() {
		 return ownerId;
	 }

	 public void setOwnerId(String ownerId) {
		 this.ownerId = ownerId;
	 }

	 public String getaChange() {
		return aChange;
	}

	public void setaChange(String aChange) {
		this.aChange = aChange;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBusinessDivision() {
		return businessDivision;
	}

	public void setBusinessDivision(String businessDivision) {
		this.businessDivision = businessDivision;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Double getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(Double sumPrice) {
		this.sumPrice = sumPrice;
	}

	public String getAllocationCode() {
		return allocationCode;
	}

	public void setAllocationCode(String allocationCode) {
		this.allocationCode = allocationCode;
	}

	public java.util.Date getSendProductDate() {
		return sendProductDate;
	}

	public void setSendProductDate(java.util.Date sendProductDate) {
		this.sendProductDate = sendProductDate;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("type", this.type) 
		.append("taskType", this.taskType) 
		.append("dateCode", this.dateCode) 
		.append("taskCreateDate", this.taskCreateDate) 
		.append("paymentType", this.paymentType) 
		.append("focusShipmentsMonth", this.focusShipmentsMonth) 
		.append("isSms", this.isSms) 
		.append("commerceName", this.commerceName) 
		.append("commerceId", this.commerceId) 
		.append("productName", this.productName) 
		.append("productNum", this.productNum) 
		.append("MedicineType", this.MedicineType) 
		.append("productState", this.productState) 
		.append("aChange", this.aChange) 
		.append("commercetype", this.commercetype) 
		.append("marketType", this.marketType) 
		.append("price", this.price) 
		.append("memo", this.memo) 
		.append("planShipmentsSum", this.planShipmentsSum) 
		.append("contactPersion", this.contactPersion) 
		.append("phone", this.phone)
		.append("ownerId",this.ownerId)
		.toString();
	}
}