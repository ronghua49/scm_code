package com.winway.scm.model;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 大合同分配商品表
 * <pre> 
 * 描述：大合同分配商品表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:44
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmXsBigContractAllotProduct",description = "大合同分配商品表") 
public class ScmXsBigContractAllotProduct extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="大合同分配ID")
	protected String bigContractAllotId; 
	
	@ApiModelProperty(value="数量")
	protected Integer theNumber; 
	
	@ApiModelProperty(value="件数")
	protected Integer packageNum; 
	
	@ApiModelProperty(value="含税单价")
	protected Double unitPrice; 
	
	@ApiModelProperty(value="总价")
	protected Double sumPrice; 
	
	@ApiModelProperty(value="大合同商品汇总ID")
	protected String bigContractProductId;
	
	@ApiModelProperty(value="药品类型")
	protected String medicineType;
	
	@ApiModelProperty(value="是否慢销品  0非  1是")
	protected String isSMS;
	
	@ApiModelProperty(value="SMS剩余任务量")
	protected int smscurrentMonthSales; 
	
	@ApiModelProperty(value="规格")
	protected String productState;
	
	@ApiModelProperty(value="通用名称")
	protected String commonName;
	
	@ApiModelProperty(value="商品名称")
	protected String productName;
	
	@ApiModelProperty(value="建议本次申请量")
	protected int suggestApplyNum; 
	
	@ApiModelProperty(value="商品编号")
	protected String productCode;
	
	@ApiModelProperty(value="件装量")
	protected int aChange; 
	
	@ApiModelProperty(value="上次发货时间")
	protected Date lastShipmentDate;
	
	@ApiModelProperty(value="药品供应状态")
	protected String suppyType; 
	
	@ApiModelProperty(value="单位")
	protected String unit;
	
	public String getCommonName() {
		return commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getMedicineType() {
		return medicineType;
	}

	public void setMedicineType(String medicineType) {
		this.medicineType = medicineType;
	}


	public int getSmscurrentMonthSales() {
		return smscurrentMonthSales;
	}

	public String getIsSMS() {
		return isSMS;
	}

	public void setIsSMS(String isSMS) {
		this.isSMS = isSMS;
	}

	public String getUnit() {
		return unit;
	}

	public void setSmscurrentMonthSales(int smscurrentMonthSales) {
		this.smscurrentMonthSales = smscurrentMonthSales;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getProductState() {
		return productState;
	}



	public void setProductState(String productState) {
		this.productState = productState;
	}



	public String getProductName() {
		return productName;
	}



	public void setProductName(String productName) {
		this.productName = productName;
	}



	public int getSuggestApplyNum() {
		return suggestApplyNum;
	}



	public void setSuggestApplyNum(int suggestApplyNum) {
		this.suggestApplyNum = suggestApplyNum;
	}



	public String getProductCode() {
		return productCode;
	}



	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}



	public Date getLastShipmentDate() {
		return lastShipmentDate;
	}



	public void setLastShipmentDate(Date lastShipmentDate) {
		this.lastShipmentDate = lastShipmentDate;
	}



	public String getSuppyType() {
		return suppyType;
	}



	public void setSuppyType(String suppyType) {
		this.suppyType = suppyType;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	/**
	 * 返回 id
	 * @return
	 */
	public String getId() {
		return this.id;
	}
	


	
	public void setTheNumber(Integer theNumber) {
		this.theNumber = theNumber;
	}
	
	/**
	 * 返回 数量
	 * @return
	 */
	public Integer getTheNumber() {
		return this.theNumber;
	}
	
	public void setPackageNum(Integer packageNum) {
		this.packageNum = packageNum;
	}
	
	/**
	 * 返回 件数
	 * @return
	 */
	public Integer getPackageNum() {
		return this.packageNum;
	}
	
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	/**
	 * 返回 含税单价
	 * @return
	 */
	public Double getUnitPrice() {
		return this.unitPrice;
	}
	
	public void setSumPrice(Double sumPrice) {
		this.sumPrice = sumPrice;
	}
	
	/**
	 * 返回 总价
	 * @return
	 */
	public Double getSumPrice() {
		return this.sumPrice;
	}
	
	public int getaChange() {
		return aChange;
	}

	public void setaChange(int aChange) {
		this.aChange = aChange;
	}

	public String getBigContractAllotId() {
		return bigContractAllotId;
	}

	public void setBigContractAllotId(String bigContractAllotId) {
		this.bigContractAllotId = bigContractAllotId;
	}

	public String getBigContractProductId() {
		return bigContractProductId;
	}

	public void setBigContractProductId(String bigContractProductId) {
		this.bigContractProductId = bigContractProductId;
	}

	@Override
	public String toString() {
		return "ScmXsBigContractAllotProduct [id=" + id + ", bigContractAllotId=" + bigContractAllotId + ", theNumber="
				+ theNumber + ", packageNum=" + packageNum + ", unitPrice=" + unitPrice + ", sumPrice=" + sumPrice
				+ ", bigContractProductId=" + bigContractProductId + ", medicineType=" + medicineType
				+ ", smscurrentMonthSales=" + smscurrentMonthSales + ", productState=" + productState + ", productName="
				+ productName + ", suggestApplyNum=" + suggestApplyNum + ", productCode=" + productCode + ", aChange="
				+ aChange + ", lastShipmentDate=" + lastShipmentDate + ", suppyType=" + suppyType + "]";
	}

}