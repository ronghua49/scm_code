package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 推送sap开票确认表
 * <pre> 
 * 描述：推送sap开票确认表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-07-05 23:55:02
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmCwToSapDiscount",description = "推送sap开票确认表") 
public class ScmCwToSapDiscount extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="发货指令主表ID")
	protected String shipmentsdatailmasterId; 
	
	@ApiModelProperty(value="发货编号")
	protected String shipmentsCode; 
	
	@ApiModelProperty(value="开票json")
	protected String discountJson; 
	
	@ApiModelProperty(value="是否推送至SAP(0:未推送,1:已推送)")
	protected String isToSap; 
	
	@ApiModelProperty(value="是否确认推送至SAP(0:未确认,1:已确认,2:已取消)")
	protected String isAffirmToSap; 
	
	@ApiModelProperty(value="sap单号")
	protected String sapCode; 
	
	public String getSapCode() {
		return sapCode;
	}

	public void setSapCode(String sapCode) {
		this.sapCode = sapCode;
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
	
	public void setShipmentsdatailmasterId(String shipmentsdatailmasterId) {
		this.shipmentsdatailmasterId = shipmentsdatailmasterId;
	}
	
	/**
	 * 返回 发货指令主表ID
	 * @return
	 */
	public String getShipmentsdatailmasterId() {
		return this.shipmentsdatailmasterId;
	}
	
	public void setShipmentsCode(String shipmentsCode) {
		this.shipmentsCode = shipmentsCode;
	}
	
	/**
	 * 返回 发货编号
	 * @return
	 */
	public String getShipmentsCode() {
		return this.shipmentsCode;
	}
	
	public void setDiscountJson(String discountJson) {
		this.discountJson = discountJson;
	}
	
	/**
	 * 返回 开票json
	 * @return
	 */
	public String getDiscountJson() {
		return this.discountJson;
	}
	
	public void setIsToSap(String isToSap) {
		this.isToSap = isToSap;
	}
	
	/**
	 * 返回 是否推送至SAP(0:未推送,1:已推送)
	 * @return
	 */
	public String getIsToSap() {
		return this.isToSap;
	}
	
	public void setIsAffirmToSap(String isAffirmToSap) {
		this.isAffirmToSap = isAffirmToSap;
	}
	
	/**
	 * 返回 是否确认推送至SAP(0:未确认,1:已确认,2:已取消)
	 * @return
	 */
	public String getIsAffirmToSap() {
		return this.isAffirmToSap;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("shipmentsdatailmasterId", this.shipmentsdatailmasterId) 
		.append("shipmentsCode", this.shipmentsCode) 
		.append("discountJson", this.discountJson) 
		.append("isToSap", this.isToSap) 
		.append("isAffirmToSap", this.isAffirmToSap) 
		.toString();
	}
}