package com.winway.scm.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;
import com.winway.scm.model.ScmFhShipmentsMessage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 发货明细表和主表视图对象
 * 
 * <pre>
 * 描述：
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-02-15 17:31:50
 * 版权：美达开发小组
 * </pre>
 */
@ApiModel(value = "ScmFhShipmentsDatail", description = "发货明细表")
public class ShipmentsDatailVo extends BaseModel<String> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	protected String id;

	@ApiModelProperty(value = "发货任务ID")
	protected String shipmentsTaskId;

	@ApiModelProperty(value = "类型(0:发货任务,1:剩余任务)")
	protected String type;

	@ApiModelProperty(value = "任务类型(0:集中发货,1:分配,2日常合同)")
	protected String taskType;

	@ApiModelProperty(value = "数据编号")
	protected String dateCode;

	@ApiModelProperty(value = "发货确认时间")
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date shipmentsAffirmDate;

	@ApiModelProperty(value = "付款类型(0:预付款,1赊销)")
	protected String paymentType;

	@ApiModelProperty(value = "集中发货分配月")
	protected String focusShipmentsMonth;

	@ApiModelProperty(value = "是否SMS")
	protected String isSms;

	@ApiModelProperty(value = "客户名称")
	protected String commerceName;

	@ApiModelProperty(value = "商业id")
	protected String commerceId;

	@ApiModelProperty(value = "商品名称")
	protected String productName;

	@ApiModelProperty(value = "药品编号")
	protected String productNum;

	@ApiModelProperty(value = "药品类型")
	protected String medicineType;

	@ApiModelProperty(value = "规格型号")
	protected String productState;

	@ApiModelProperty(value = "件装量")
	protected String aChange;

	@ApiModelProperty(value = "客户类型")
	protected String commercetype;

	@ApiModelProperty(value = "销售类型")
	protected String marketType;

	@ApiModelProperty(value = "批号")
	protected String batchNumber;

	@ApiModelProperty(value = "单价")
	protected Double price;

	@ApiModelProperty(value = "总价")
	protected Double priceSum;

	@ApiModelProperty(value = "发货备注")
	protected String memo;

	@ApiModelProperty(value = "计划量")
	protected Integer planShipmentsSum;

	@ApiModelProperty(value = "实际量")
	protected Integer deliveringAmount;

	@ApiModelProperty(value = "仓库")
	protected String warehouse;

	@ApiModelProperty(value = "仓库编号")
	protected String houstWareCode;

	@ApiModelProperty(value = "当前库存量")
	protected Integer stock;

	@ApiModelProperty(value = "联系人")
	protected String contactPersion;

	@ApiModelProperty(value = "联系电话")
	protected String phone;

	@ApiModelProperty(value = "收货地址")
	protected String shippingAddress;

	@ApiModelProperty(value = "生产日期")
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date dateOfManufacture;

	@ApiModelProperty(value = "主表ID")
	protected String masterId;

	@ApiModelProperty(value = "指令编号")
	protected String directiveCode;

	@ApiModelProperty(value = "货主id")
	protected String ownerId;

	@ApiModelProperty(value = "审批状态")
	protected String approvalState;

	@ApiModelProperty(value = "发货编码")
	protected String shipmentsCode;

	@ApiModelProperty(value = "商务分区")
	protected String businessDivision;
	@ApiModelProperty(value = "省区")
	protected String province;

	@ApiModelProperty(value = "药品系列名称")
	protected String productLineName;

	@ApiModelProperty(value = "系列编码")
	protected String productLineCode;

	@ApiModelProperty(value = "创建人")
	protected String createPersion;

	@ApiModelProperty(value = "通用名")
	protected String commonName;

	@ApiModelProperty(value = "生产厂商")
	protected String manufacturer;

	@ApiModelProperty(value = "退货数量")
	protected Integer returnSum;

	@ApiModelProperty(value = "效期")
	protected String expiryDate;

	@ApiModelProperty(value = "单位")
	protected String unit;
	@ApiModelProperty(value = "剂型")
	protected String AgentType;
	@ApiModelProperty(value = "药品分类")
	protected String medicineClassify;
	@ApiModelProperty(value = "经营范围")
	protected String businessScope;
	@ApiModelProperty(value = "注册证号")
	protected String approvalCode;

	@ApiModelProperty(value = "商品字码")
	protected String code;

	@ApiModelProperty(value = "发货效期月")
	protected String shipmentExpiry;

	@ApiModelProperty(value = "距今效期月")
	protected String nowExpiry;

	public String getShipmentExpiry() {
		return shipmentExpiry;
	}

	public void setShipmentExpiry(String shipmentExpiry) {
		this.shipmentExpiry = shipmentExpiry;
	}

	public String getNowExpiry() {
		return nowExpiry;
	}

	public void setNowExpiry(String nowExpiry) {
		this.nowExpiry = nowExpiry;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getApprovalCode() {
		return approvalCode;
	}

	public void setApprovalCode(String approvalCode) {
		this.approvalCode = approvalCode;
	}

	public String getaChange() {
		return aChange;
	}

	public void setaChange(String aChange) {
		this.aChange = aChange;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getAgentType() {
		return AgentType;
	}

	public void setAgentType(String agentType) {
		AgentType = agentType;
	}

	public String getMedicineClassify() {
		return medicineClassify;
	}

	public void setMedicineClassify(String medicineClassify) {
		this.medicineClassify = medicineClassify;
	}

	public String getBusinessScope() {
		return businessScope;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Integer getReturnSum() {
		return returnSum;
	}

	public void setReturnSum(Integer returnSum) {
		this.returnSum = returnSum;
	}

	public String getCommonName() {
		return commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getHoustWareCode() {
		return houstWareCode;
	}

	public void setHoustWareCode(String houstWareCode) {
		this.houstWareCode = houstWareCode;
	}

	protected List<ScmFhShipmentsMessage> scmFhShipmentsMessageList = new ArrayList<>();

	public List<ScmFhShipmentsMessage> getScmFhShipmentsMessageList() {
		return scmFhShipmentsMessageList;
	}

	public void setScmFhShipmentsMessageList(List<ScmFhShipmentsMessage> scmFhShipmentsMessageList) {
		this.scmFhShipmentsMessageList = scmFhShipmentsMessageList;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 返回 id
	 *
	 * @return
	 */
	public String getId() {
		return this.id;
	}

	public void setShipmentsTaskId(String shipmentsTaskId) {
		this.shipmentsTaskId = shipmentsTaskId;
	}

	/**
	 * 返回 发货任务ID
	 *
	 * @return
	 */
	public String getShipmentsTaskId() {
		return this.shipmentsTaskId;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 返回 类型(0:发货任务,1:剩余任务)
	 *
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
	 *
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
	 *
	 * @return
	 */
	public String getDateCode() {
		return this.dateCode;
	}

	public void setShipmentsAffirmDate(Date shipmentsAffirmDate) {
		this.shipmentsAffirmDate = shipmentsAffirmDate;
	}

	/**
	 * 返回 发货确认时间
	 *
	 * @return
	 */
	public Date getShipmentsAffirmDate() {
		return this.shipmentsAffirmDate;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	/**
	 * 返回 付款类型(0:预付款,1赊销)
	 *
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
	 *
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
	 *
	 * @return
	 */
	public String getIsSms() {
		return this.isSms;
	}

	public void setCommerceName(String commerceName) {
		this.commerceName = commerceName;
	}

	/**
	 * 返回 客户名称
	 *
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
	 *
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
	 *
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
	 *
	 * @return
	 */
	public String getProductNum() {
		return this.productNum;
	}

	public void setMedicineType(String MedicineType) {
		this.medicineType = MedicineType;
	}

	/**
	 * 返回 药品类型
	 *
	 * @return
	 */
	public String getMedicineType() {
		return medicineType;
	}

	public void setProductState(String productState) {
		this.productState = productState;
	}

	/**
	 * 返回 规格型号
	 *
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
	 *
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
	 *
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
	 *
	 * @return
	 */
	public String getMarketType() {
		return this.marketType;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	/**
	 * 返回 批号
	 *
	 * @return
	 */
	public String getBatchNumber() {
		return this.batchNumber;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * 返回 单价
	 *
	 * @return
	 */
	public Double getPrice() {
		return this.price;
	}

	public Double getPriceSum() {
		return priceSum;
	}

	public void setPriceSum(Double priceSum) {
		this.priceSum = priceSum;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * 返回 发货备注
	 *
	 * @return
	 */
	public String getMemo() {
		return this.memo;
	}

	public void setPlanShipmentsSum(Integer planShipmentsSum) {
		this.planShipmentsSum = planShipmentsSum;
	}

	/**
	 * 返回 计划量
	 *
	 * @return
	 */
	public Integer getPlanShipmentsSum() {
		return this.planShipmentsSum;
	}

	public void setDeliveringAmount(Integer deliveringAmount) {
		this.deliveringAmount = deliveringAmount;
	}

	/**
	 * 返回 实际量
	 *
	 * @return
	 */
	public Integer getDeliveringAmount() {
		return this.deliveringAmount;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	/**
	 * 返回 仓库
	 *
	 * @return
	 */
	public String getWarehouse() {
		return this.warehouse;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	/**
	 * 返回 当前库存量
	 *
	 * @return
	 */
	public Integer getStock() {
		return this.stock;
	}

	public void setContactPersion(String contactPersion) {
		this.contactPersion = contactPersion;
	}

	/**
	 * 返回 联系人
	 *
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
	 *
	 * @return
	 */
	public String getPhone() {
		return this.phone;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	/**
	 * 返回 收货地址
	 *
	 * @return
	 */
	public String getShippingAddress() {
		return this.shippingAddress;
	}

	public void setDateOfManufacture(Date dateOfManufacture) {
		this.dateOfManufacture = dateOfManufacture;
	}

	/**
	 * 返回 生产日期
	 *
	 * @return
	 */
	public Date getDateOfManufacture() {
		return this.dateOfManufacture;
	}

	public String getDirectiveCode() {
		return directiveCode;
	}

	public void setDirectiveCode(String directiveCode) {
		this.directiveCode = directiveCode;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public String getApprovalState() {
		return approvalState;
	}

	public void setApprovalState(String approvalState) {
		this.approvalState = approvalState;
	}

	public String getShipmentsCode() {
		return shipmentsCode;
	}

	public void setShipmentsCode(String shipmentsCode) {
		this.shipmentsCode = shipmentsCode;
	}

	public String getCreatePersion() {
		return createPersion;
	}

	public void setCreatePersion(String createPersion) {
		this.createPersion = createPersion;
	}

	public String getProductLineName() {
		return productLineName;
	}

	public void setProductLineName(String productLineName) {
		this.productLineName = productLineName;
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

	/**
	 * 返回 主表ID
	 *
	 * @return
	 */
	public String getMasterId() {
		return this.masterId;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getProductLineCode() {
		return productLineCode;
	}

	public void setProductLineCode(String productLineCode) {
		this.productLineCode = productLineCode;
	}

	/**
	 * @see Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).append("shipmentsTaskId", this.shipmentsTaskId)
				.append("type", this.type).append("taskType", this.taskType).append("dateCode", this.dateCode)
				.append("shipmentsAffirmDate", this.shipmentsAffirmDate).append("paymentType", this.paymentType)
				.append("focusShipmentsMonth", this.focusShipmentsMonth).append("isSms", this.isSms)
				.append("commerceName", this.commerceName).append("commerceId", this.commerceId)
				.append("productName", this.productName).append("productNum", this.productNum)
				.append("MedicineType", this.medicineType).append("productState", this.productState)
				.append("aChange", this.aChange).append("commercetype", this.commercetype)
				.append("marketType", this.marketType).append("batchNumber", this.batchNumber)
				.append("price", this.price).append("memo", this.memo).append("planShipmentsSum", this.planShipmentsSum)
				.append("deliveringAmount", this.deliveringAmount).append("warehouse", this.warehouse)
				.append("stock", this.stock).append("contactPersion", this.contactPersion).append("phone", this.phone)
				.append("shippingAddress", this.shippingAddress).append("dateOfManufacture", this.dateOfManufacture)
				.append("masterId", this.masterId).toString();
	}
}