package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;


 /**
 * 物流发运信息表
 * <pre> 
 * 描述：物流发运信息表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-02-15 17:31:50
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmFhShipmentsMessage",description = "物流发运信息表") 
public class ScmFhShipmentsMessage extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="发货明细表ID")
	protected String shipmentsDatailId; 
	
	@ApiModelProperty(value="物流承运商")
	protected String logisticsName; 
	
	@ApiModelProperty(value="物流单号")
	protected String logisticsCode; 
	
	@ApiModelProperty(value="仓库实际发货日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date actualDeliveryDate; 
	
	@ApiModelProperty(value="预计到货日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date expectedArrivalDate; 
	
	@ApiModelProperty(value="实际到货日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date actualDateOfArrival; 
	
	@ApiModelProperty(value="配送确认到货日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date confirmArrivalDate; 
	
	@ApiModelProperty(value="配送确认备注")
	protected String confirmMemo; 
	
	@ApiModelProperty(value="实收件数")
	protected Integer officialReceiptsSum; 
	
	@ApiModelProperty(value="异常情况")
	protected String exceptionCase; 
	
	@ApiModelProperty(value="说明")
	protected String explain; 
	
	@ApiModelProperty(value="协议到货天数")
	protected Integer agreementDays; 
	
	@ApiModelProperty(value="延误天数")
	protected Integer dalayDays; 
	
	@ApiModelProperty(value="运输方式")
	protected String transportationMethod; 
	
	@ApiModelProperty(value="委托经办人")
	protected String entrustPersion; 
	
	@ApiModelProperty(value="驾驶员")
	protected String driver; 
	
	@ApiModelProperty(value="车牌号")
	protected String licencePlate; 
	
	@ApiModelProperty(value="是否传入WMS")
	protected String isToWms; 
	
	@ApiModelProperty(value="传入WMS时间")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date toWmsDate; 
	
	
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
	
	public void setShipmentsDatailId(String shipmentsDatailId) {
		this.shipmentsDatailId = shipmentsDatailId;
	}
	
	/**
	 * 返回 发货明细表ID
	 * @return
	 */
	public String getShipmentsDatailId() {
		return this.shipmentsDatailId;
	}
	
	public void setLogisticsName(String logisticsName) {
		this.logisticsName = logisticsName;
	}
	
	/**
	 * 返回 物流承运商
	 * @return
	 */
	public String getLogisticsName() {
		return this.logisticsName;
	}
	
	public void setLogisticsCode(String logisticsCode) {
		this.logisticsCode = logisticsCode;
	}
	
	/**
	 * 返回 物流单号
	 * @return
	 */
	public String getLogisticsCode() {
		return this.logisticsCode;
	}
	
	public void setActualDeliveryDate(java.util.Date actualDeliveryDate) {
		this.actualDeliveryDate = actualDeliveryDate;
	}
	
	/**
	 * 返回 仓库实际发货日期
	 * @return
	 */
	public java.util.Date getActualDeliveryDate() {
		return this.actualDeliveryDate;
	}
	
	public void setExpectedArrivalDate(java.util.Date expectedArrivalDate) {
		this.expectedArrivalDate = expectedArrivalDate;
	}
	
	/**
	 * 返回 预计到货日期
	 * @return
	 */
	public java.util.Date getExpectedArrivalDate() {
		return this.expectedArrivalDate;
	}
	
	public void setActualDateOfArrival(java.util.Date actualDateOfArrival) {
		this.actualDateOfArrival = actualDateOfArrival;
	}
	
	/**
	 * 返回 实际到货日期
	 * @return
	 */
	public java.util.Date getActualDateOfArrival() {
		return this.actualDateOfArrival;
	}
	
	public void setConfirmArrivalDate(java.util.Date confirmArrivalDate) {
		this.confirmArrivalDate = confirmArrivalDate;
	}
	
	/**
	 * 返回 配送确认到货日期
	 * @return
	 */
	public java.util.Date getConfirmArrivalDate() {
		return this.confirmArrivalDate;
	}
	
	public void setConfirmMemo(String confirmMemo) {
		this.confirmMemo = confirmMemo;
	}
	
	/**
	 * 返回 配送确认备注
	 * @return
	 */
	public String getConfirmMemo() {
		return this.confirmMemo;
	}
	
	public void setOfficialReceiptsSum(Integer officialReceiptsSum) {
		this.officialReceiptsSum = officialReceiptsSum;
	}
	
	/**
	 * 返回 实收件数
	 * @return
	 */
	public Integer getOfficialReceiptsSum() {
		return this.officialReceiptsSum;
	}
	
	public void setExceptionCase(String exceptionCase) {
		this.exceptionCase = exceptionCase;
	}
	
	/**
	 * 返回 异常情况
	 * @return
	 */
	public String getExceptionCase() {
		return this.exceptionCase;
	}
	
	public void setExplain(String explain) {
		this.explain = explain;
	}
	
	/**
	 * 返回 说明
	 * @return
	 */
	public String getExplain() {
		return this.explain;
	}
	
	public void setAgreementDays(Integer agreementDays) {
		this.agreementDays = agreementDays;
	}
	
	/**
	 * 返回 协议到货天数
	 * @return
	 */
	public Integer getAgreementDays() {
		return this.agreementDays;
	}
	
	public void setDalayDays(Integer dalayDays) {
		this.dalayDays = dalayDays;
	}
	
	/**
	 * 返回 延误天数
	 * @return
	 */
	public Integer getDalayDays() {
		return this.dalayDays;
	}
	
	public void setTransportationMethod(String transportationMethod) {
		this.transportationMethod = transportationMethod;
	}
	
	/**
	 * 返回 运输方式
	 * @return
	 */
	public String getTransportationMethod() {
		return this.transportationMethod;
	}
	
	public void setEntrustPersion(String entrustPersion) {
		this.entrustPersion = entrustPersion;
	}
	
	/**
	 * 返回 委托经办人
	 * @return
	 */
	public String getEntrustPersion() {
		return this.entrustPersion;
	}
	
	public void setDriver(String driver) {
		this.driver = driver;
	}
	
	/**
	 * 返回 驾驶员
	 * @return
	 */
	public String getDriver() {
		return this.driver;
	}
	
	public void setLicencePlate(String licencePlate) {
		this.licencePlate = licencePlate;
	}
	
	/**
	 * 返回 车牌号
	 * @return
	 */
	public String getLicencePlate() {
		return this.licencePlate;
	}
	
	public void setIsToWms(String isToWms) {
		this.isToWms = isToWms;
	}
	
	/**
	 * 返回 是否传入WMS
	 * @return
	 */
	public String getIsToWms() {
		return this.isToWms;
	}
	
	public void setToWmsDate(java.util.Date toWmsDate) {
		this.toWmsDate = toWmsDate;
	}
	
	/**
	 * 返回 传入WMS时间
	 * @return
	 */
	public java.util.Date getToWmsDate() {
		return this.toWmsDate;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("shipmentsDatailId", this.shipmentsDatailId) 
		.append("logisticsName", this.logisticsName) 
		.append("logisticsCode", this.logisticsCode) 
		.append("actualDeliveryDate", this.actualDeliveryDate) 
		.append("expectedArrivalDate", this.expectedArrivalDate) 
		.append("actualDateOfArrival", this.actualDateOfArrival) 
		.append("confirmArrivalDate", this.confirmArrivalDate) 
		.append("confirmMemo", this.confirmMemo) 
		.append("officialReceiptsSum", this.officialReceiptsSum) 
		.append("exceptionCase", this.exceptionCase) 
		.append("explain", this.explain) 
		.append("agreementDays", this.agreementDays) 
		.append("dalayDays", this.dalayDays) 
		.append("transportationMethod", this.transportationMethod) 
		.append("entrustPersion", this.entrustPersion) 
		.append("driver", this.driver) 
		.append("licencePlate", this.licencePlate) 
		.append("isToWms", this.isToWms) 
		.append("toWmsDate", this.toWmsDate) 
		.toString();
	}
}