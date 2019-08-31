package com.winway.scm.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

public class ScmFhShipmentsDataiAndMessageVo implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "id")
	protected String id;

	@ApiModelProperty(value = "masterId 主表id")
	protected String masterId;
	@ApiModelProperty(value = "发货任务ID")
	protected String shipmentsTaskId;
	@ApiModelProperty(value = "类型(0:发货任务,1:剩余任务)")
	protected String type;
	@ApiModelProperty(value = "任务类型(0:集中发货,1:分配,2日常合同)")
	protected String taskType;
	@ApiModelProperty(value = "发货任务类型中文")
	protected String typeStr;
	@ApiModelProperty(value = "任务类型中文")
	protected String taskTypeStr;
	@ApiModelProperty(value = "数据编号")
	protected String dateCode;
	@ApiModelProperty(value = "发货确认时间")
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date shipmentsAffirmDate;
	@ApiModelProperty(value = "付款类型(0:预付款,1赊销)")
	protected String paymentType;
	@ApiModelProperty(value = "付款类型中文")
	protected String paymentTypeStr;
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
	@ApiModelProperty(value = "商品编号")
	protected String productNum;
	@ApiModelProperty(value = "商品类型")
	protected String MedicineType;
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

	@ApiModelProperty(value = "备注")
	protected String memo;

	@ApiModelProperty(value = "物流承运商名称")
	protected String commonCarrierName;


	@ApiModelProperty(value = "物流承运商编码")
	protected String commonCarrierCode
			;
	@ApiModelProperty(value = "计划量")
	protected String planShipmentsSum;
	@ApiModelProperty(value = "实际量")
	protected String deliveringAmount;
	@ApiModelProperty(value = "仓库")
	protected String warehouse;
	@ApiModelProperty(value = "当前库存量")
	protected String stock;
	@ApiModelProperty(value = "联系人")
	protected String contactPersion;
	@ApiModelProperty(value = "联系电话")
	protected String phone;
	@ApiModelProperty(value = "收货联系人")
	protected String shippingContactPersion;
	@ApiModelProperty(value = "收获联系电话")
	protected String shippingPhone;
	@ApiModelProperty(value = "收货地址")
	protected String shippingAddress;
	@ApiModelProperty(value = "生产日期")
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	protected String dateOfManufacture;
	@ApiModelProperty(value = "审批状态(0:未提交;1:审批中;2:审批完成,3:被驳回)")
	protected String approvalState;

	@ApiModelProperty(value = "审批id")
	protected String approvalId;
	@ApiModelProperty(value = "审批状态中文")
	protected String approvalStateStr;
	@ApiModelProperty(value = "货主ID")
	protected String ownerId;
	@ApiModelProperty(value = "商务分区")
	protected String businessDivision;
	@ApiModelProperty(value = "商务分区ID")
	protected String businessDivisionId;
	@ApiModelProperty(value = "省区")
	protected String province;
	@ApiModelProperty(value = "省区id")
	protected String provinceId;
	@ApiModelProperty(value = "发货单号")
	protected String shipmentsCode;
	@ApiModelProperty(value = "创建人")
	protected String createPersion;
	@ApiModelProperty(value = "创建时间")
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	protected String createDate;
	@ApiModelProperty(value = "物流发货信息ID")
	protected String shipmentsmessageId;
	@ApiModelProperty(value = "物流承运商")
	protected String logisticsName;
	@ApiModelProperty(value = "物流单号")
	protected String logisticsCode;
	@ApiModelProperty(value = "仓库实际发货日期")
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date actualDeliveryDate;
	@ApiModelProperty(value = "预计到货日期")
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date expectedArrivalDate;
	@ApiModelProperty(value = "实际到货日期")
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date actualDateOfArrival;
	@ApiModelProperty(value = "配送确认到货日期")
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date confirmArrivalDate;
	@ApiModelProperty(value = "年份")
	protected String year;
	@ApiModelProperty(value = "配送确认备注")
	protected String confirmMemo;
	@ApiModelProperty(value = "实收件数")
	protected String officialReceiptsSum;
	@ApiModelProperty(value = "异常情况")
	protected String exceptionCase;
	@ApiModelProperty(value = "说明")
	protected String explain;
	@ApiModelProperty(value = "协议到货天使")
	protected String agreementDays;
	@ApiModelProperty(value = "延误天数")
	protected String dalayDays;
	@ApiModelProperty(value = "运输方式")
	protected String transportationMethod;
	@ApiModelProperty(value = "委托经办人")
	protected String entrustPersion;
	@ApiModelProperty(value = "驾驶员")
	protected String driver;
	@ApiModelProperty(value = "车牌号")
	protected String licencePlate;
	@ApiModelProperty(value = "是否传入WMS")
	protected String isToWms;
	@ApiModelProperty(value = "传入WMS时间")
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date toWmsDate;
	@ApiModelProperty(value = "医药注册号")
	protected String approvalCode;

	@ApiModelProperty(value = "件数")
	protected String packageSum;
	
	@ApiModelProperty(value = "存货代码")
	protected String InventoryCode;
	
	@ApiModelProperty(value = "指令编号")
	protected String directiveCode;
	
	@ApiModelProperty(value = "月份")
	protected String month;

	@ApiModelProperty(value = "商业编码")
	protected String commerceCode;

	@ApiModelProperty(value = "商业效期")
	protected String expiryDate;

	@ApiModelProperty(value = "退货数量")
	protected Integer returnSum;

	@ApiModelProperty(value = "厂商")
	protected String manufacturer;

	@ApiModelProperty(value = "通用名")
	protected String commonName;

	@ApiModelProperty(value = "仓库类型(0:整件,1:散件)")
	protected String warehouseType;
	
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "失效日期")
	protected Date loseDate;
	
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "任务生成时间")
	protected Date taskCreateDate;

	@ApiModelProperty(value = "单位")
	protected String unit;
	
	@ApiModelProperty(value = "剂型")
	protected String AgentType;
	
	@ApiModelProperty(value = "药品分类")
	protected String medicineClassify;

	@ApiModelProperty(value = "经营范围")
	protected String businessScope;

	@ApiModelProperty(value = "是否原箱数据")
	protected String xtlx;

	public String getXtlx() {
		return xtlx;
	}

	public void setXtlx(String xtlx) {
		this.xtlx = xtlx;
	}

	public String getCommonCarrierName() {
		return commonCarrierName;
	}

	public void setCommonCarrierName(String commonCarrierName) {
		this.commonCarrierName = commonCarrierName;
	}

	public String getCommonCarrierCode() {
		return commonCarrierCode;
	}

	public void setCommonCarrierCode(String commonCarrierCode) {
		this.commonCarrierCode = commonCarrierCode;
	}

	public String getUnit() {
		return unit;
	}
	
	public Date getTaskCreateDate() {
		return taskCreateDate;
	}

	public void setTaskCreateDate(Date taskCreateDate) {
		this.taskCreateDate = taskCreateDate;
	}

	public String getApprovalId() {
		return approvalId;
	}

	public void setApprovalId(String approvalId) {
		this.approvalId = approvalId;
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

	public String getWarehouseType() {
		return warehouseType;
	}

	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
	}

	public Date getLoseDate() {
		return loseDate;
	}

	public void setLoseDate(Date loseDate) {
		this.loseDate = loseDate;
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

	public Integer getReturnSum() {
		return returnSum;
	}

	public void setReturnSum(Integer returnSum) {
		this.returnSum = returnSum;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShipmentsTaskId() {
		return shipmentsTaskId;
	}

	public void setShipmentsTaskId(String shipmentsTaskId) {
		this.shipmentsTaskId = shipmentsTaskId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getTypeStr() {
		return typeStr;
	}

	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}

	public String getTaskTypeStr() {
		return taskTypeStr;
	}

	public void setTaskTypeStr(String taskTypeStr) {
		this.taskTypeStr = taskTypeStr;
	}

	public String getDateCode() {
		return dateCode;
	}

	public void setDateCode(String dateCode) {
		this.dateCode = dateCode;
	}

	public Date getShipmentsAffirmDate() {
		return shipmentsAffirmDate;
	}

	public void setShipmentsAffirmDate(Date shipmentsAffirmDate) {
		this.shipmentsAffirmDate = shipmentsAffirmDate;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getPaymentTypeStr() {
		return paymentTypeStr;
	}

	public void setPaymentTypeStr(String paymentTypeStr) {
		this.paymentTypeStr = paymentTypeStr;
	}

	public String getFocusShipmentsMonth() {
		return focusShipmentsMonth;
	}

	public void setFocusShipmentsMonth(String focusShipmentsMonth) {
		this.focusShipmentsMonth = focusShipmentsMonth;
	}

	public String getIsSms() {
		return isSms;
	}

	public void setIsSms(String isSms) {
		this.isSms = isSms;
	}

	public String getCommerceName() {
		return commerceName;
	}

	public void setCommerceName(String commerceName) {
		this.commerceName = commerceName;
	}

	public String getCommerceId() {
		return commerceId;
	}

	public void setCommerceId(String commerceId) {
		this.commerceId = commerceId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductNum() {
		return productNum;
	}

	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}

	public String getMedicineType() {
		return MedicineType;
	}

	public void setMedicineType(String medicineType) {
		MedicineType = medicineType;
	}

	public String getProductState() {
		return productState;
	}

	public void setProductState(String productState) {
		this.productState = productState;
	}

	public String getaChange() {
		return aChange;
	}

	public void setaChange(String aChange) {
		this.aChange = aChange;
	}

	public String getCommercetype() {
		return commercetype;
	}

	public void setCommercetype(String commercetype) {
		this.commercetype = commercetype;
	}

	public String getMarketType() {
		return marketType;
	}

	public void setMarketType(String marketType) {
		this.marketType = marketType;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getPlanShipmentsSum() {
		return planShipmentsSum;
	}

	public void setPlanShipmentsSum(String planShipmentsSum) {
		this.planShipmentsSum = planShipmentsSum;
	}

	public String getDeliveringAmount() {
		return deliveringAmount;
	}

	public void setDeliveringAmount(String deliveringAmount) {
		this.deliveringAmount = deliveringAmount;
	}

	public String getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
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

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getDateOfManufacture() {
		return dateOfManufacture;
	}

	public void setDateOfManufacture(String dateOfManufacture) {
		this.dateOfManufacture = dateOfManufacture;
	}

	public String getApprovalState() {
		return approvalState;
	}

	public void setApprovalState(String approvalState) {
		this.approvalState = approvalState;
	}

	public String getApprovalStateStr() {
		return approvalStateStr;
	}

	public void setApprovalStateStr(String approvalStateStr) {
		this.approvalStateStr = approvalStateStr;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getBusinessDivision() {
		return businessDivision;
	}

	public void setBusinessDivision(String businessDivision) {
		this.businessDivision = businessDivision;
	}

	public String getBusinessDivisionId() {
		return businessDivisionId;
	}

	public void setBusinessDivisionId(String businessDivisionId) {
		this.businessDivisionId = businessDivisionId;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
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

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getShipmentsmessageId() {
		return shipmentsmessageId;
	}

	public void setShipmentsmessageId(String shipmentsmessageId) {
		this.shipmentsmessageId = shipmentsmessageId;
	}

	public String getLogisticsName() {
		return logisticsName;
	}

	public void setLogisticsName(String logisticsName) {
		this.logisticsName = logisticsName;
	}

	public String getLogisticsCode() {
		return logisticsCode;
	}

	public void setLogisticsCode(String logisticsCode) {
		this.logisticsCode = logisticsCode;
	}

	public Date getActualDeliveryDate() {
		return actualDeliveryDate;
	}

	public void setActualDeliveryDate(Date actualDeliveryDate) {
		this.actualDeliveryDate = actualDeliveryDate;
	}

	public Date getExpectedArrivalDate() {
		return expectedArrivalDate;
	}

	public void setExpectedArrivalDate(Date expectedArrivalDate) {
		this.expectedArrivalDate = expectedArrivalDate;
	}

	public Date getActualDateOfArrival() {
		return actualDateOfArrival;
	}

	public void setActualDateOfArrival(Date actualDateOfArrival) {
		this.actualDateOfArrival = actualDateOfArrival;
	}

	public Date getConfirmArrivalDate() {
		return confirmArrivalDate;
	}

	public void setConfirmArrivalDate(Date confirmArrivalDate) {
		this.confirmArrivalDate = confirmArrivalDate;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getConfirmMemo() {
		return confirmMemo;
	}

	public void setConfirmMemo(String confirmMemo) {
		this.confirmMemo = confirmMemo;
	}

	public String getOfficialReceiptsSum() {
		return officialReceiptsSum;
	}

	public void setOfficialReceiptsSum(String officialReceiptsSum) {
		this.officialReceiptsSum = officialReceiptsSum;
	}

	public String getExceptionCase() {
		return exceptionCase;
	}

	public void setExceptionCase(String exceptionCase) {
		this.exceptionCase = exceptionCase;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public String getAgreementDays() {
		return agreementDays;
	}

	public void setAgreementDays(String agreementDays) {
		this.agreementDays = agreementDays;
	}

	public String getDalayDays() {
		return dalayDays;
	}

	public void setDalayDays(String dalayDays) {
		this.dalayDays = dalayDays;
	}

	public String getTransportationMethod() {
		return transportationMethod;
	}

	public void setTransportationMethod(String transportationMethod) {
		this.transportationMethod = transportationMethod;
	}

	public String getEntrustPersion() {
		return entrustPersion;
	}

	public void setEntrustPersion(String entrustPersion) {
		this.entrustPersion = entrustPersion;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getLicencePlate() {
		return licencePlate;
	}

	public void setLicencePlate(String licencePlate) {
		this.licencePlate = licencePlate;
	}

	public String getIsToWms() {
		return isToWms;
	}

	public void setIsToWms(String isToWms) {
		this.isToWms = isToWms;
	}

	public Date getToWmsDate() {
		return toWmsDate;
	}

	public void setToWmsDate(Date toWmsDate) {
		this.toWmsDate = toWmsDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getApprovalCode() {
		return approvalCode;
	}

	public void setApprovalCode(String approvalCode) {
		this.approvalCode = approvalCode;
	}

	public String getPackageSum() {
		return packageSum;
	}

	public void setPackageSum(String packageSum) {
		this.packageSum = packageSum;
	}

	public String getInventoryCode() {
		return InventoryCode;
	}

	public void setInventoryCode(String inventoryCode) {
		InventoryCode = inventoryCode;
	}

	public String getDirectiveCode() {
		return directiveCode;
	}

	public void setDirectiveCode(String directiveCode) {
		this.directiveCode = directiveCode;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPriceSum() {
		return priceSum;
	}

	public void setPriceSum(Double priceSum) {
		this.priceSum = priceSum;
	}

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public String getShippingContactPersion() {
		return shippingContactPersion;
	}

	public void setShippingContactPersion(String shippingContactPersion) {
		this.shippingContactPersion = shippingContactPersion;
	}

	public String getShippingPhone() {
		return shippingPhone;
	}

	public void setShippingPhone(String shippingPhone) {
		this.shippingPhone = shippingPhone;
	}

	public String getCommerceCode() {
		return commerceCode;
	}

	public void setCommerceCode(String commerceCode) {
		this.commerceCode = commerceCode;
	}
}
