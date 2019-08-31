package com.winway.scm.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.Objects;


/**
 * 集中发货商品表
 * <pre>
 * 描述：集中发货商品表 实体对象
 * 构建组：x7
 * 作者:荣浩华
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:44
 * 版权：美达开发小组
 * </pre>
 */
@ApiModel(value = "ScmXsDailyContractTask", description = "日常合同发货商品表")
public class ScmXsDailyContractTask extends BaseModel<String> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    protected String id;

    @ApiModelProperty(value = "货主id")
    protected String ownerId;

    @ApiModelProperty(value = "收货人")
    protected String consignee;

    @ApiModelProperty(value = "合同编号")
    protected String contractCode;

    @ApiModelProperty(value = "购销方式(0预付款,1赊销)")
    protected String marketingWay;

    @ApiModelProperty(value = "日常合同id")
    protected String contractId;

    @ApiModelProperty(value = "手机号")
    protected String phone;

    @ApiModelProperty(value = "付款方式")
    protected String payType;

    @ApiModelProperty(value = "特求意见")
    protected String specialOpinion;

    @ApiModelProperty(value = "是否SMS")
    protected String isSMS;

    @ApiModelProperty(value = "商品常用名称")
    protected String commonName;

    @ApiModelProperty(value = "商品名称")
    protected String productName;

    @ApiModelProperty(value = "药品类型")
    protected String MedicineType;

    @ApiModelProperty(value = "药品编号")
    protected String productCode;

    @ApiModelProperty(value = "商品品规")
    protected String productGauge;

    @ApiModelProperty(value = "总价")
    protected Double sumPrice;

    @ApiModelProperty(value = "标准包装数")
    protected Integer Achange;

    @ApiModelProperty(value = "件数")
    protected Integer packageNum;

    public Integer getPackageNum() {
        return packageNum;
    }

    public void setPackageNum(Integer packageNum) {
        this.packageNum = packageNum;
    }

    @ApiModelProperty(value = "含税单价")
    protected Double unitPrice;

    @ApiModelProperty(value = "数量")
    protected Integer theNumber;

    @ApiModelProperty(value = "企业类别(0药品经营企业,1医疗机构)")
    protected String commerceState;

    @ApiModelProperty(value = "职能类型")
    protected String functionType;

    @ApiModelProperty(value = "商业名称")
    protected String commerceName;
    @ApiModelProperty(value = "商业首营ID")
    protected String commerceFirstId;

    @ApiModelProperty(value = "计划发货时间")
    protected String sendProductDate;

    @ApiModelProperty(value = "单位")
    protected String unit;
    @ApiModelProperty(value = "剂型")
    protected String AgentType;

    @ApiModelProperty(value = "厂商")
    protected String manufacturer;

    @ApiModelProperty(value = "批准文号")
    protected String approvalCode;

    @ApiModelProperty(value = "药品分类")
    protected String medicineClassify;
    
    @ApiModelProperty(value = "经营范围")
    protected String businessScope;
    
    @ApiModelProperty(value = "产品子码")
    protected String code;

    public String getUnit() {
        return unit;
    }

    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSendProductDate() {
        return sendProductDate;
    }

    public void setSendProductDate(String sendProductDate) {
        this.sendProductDate = sendProductDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getMarketingWay() {
        return marketingWay;
    }

    public void setMarketingWay(String marketingWay) {
        this.marketingWay = marketingWay;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getSpecialOpinion() {
        return specialOpinion;
    }

    public void setSpecialOpinion(String specialOpinion) {
        this.specialOpinion = specialOpinion;
    }

    public String getIsSMS() {
        return isSMS;
    }

    public void setIsSMS(String isSMS) {
        this.isSMS = isSMS;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getMedicineType() {
        return MedicineType;
    }

    public void setMedicineType(String medicineType) {
        MedicineType = medicineType;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductGauge() {
        return productGauge;
    }

    public void setProductGauge(String productGauge) {
        this.productGauge = productGauge;
    }

    public Double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(Double sumPrice) {
        this.sumPrice = sumPrice;
    }

    public Integer getAchange() {
        return Achange;
    }

    public void setAchange(Integer achange) {
        this.Achange = achange;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getTheNumber() {
        return theNumber;
    }

    public void setTheNumber(Integer theNumber) {
        this.theNumber = theNumber;
    }

    public String getCommerceState() {
        return commerceState;
    }

    public void setCommerceState(String commerceState) {
        this.commerceState = commerceState;
    }

    public String getFunctionType() {
        return functionType;
    }

    public void setFunctionType(String functionType) {
        this.functionType = functionType;
    }

    public String getCommerceName() {
        return commerceName;
    }

    public void setCommerceName(String commerceName) {
        this.commerceName = commerceName;
    }

    public String getCommerceFirstId() {
        return commerceFirstId;
    }

    public void setCommerceFirstId(String commerceFirstId) {
        this.commerceFirstId = commerceFirstId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScmXsDailyContractTask)) return false;
        ScmXsDailyContractTask that = (ScmXsDailyContractTask) o;
        return Objects.equals(getOwnerId(), that.getOwnerId()) &&
                Objects.equals(getConsignee(), that.getConsignee()) &&
                Objects.equals(getContractCode(), that.getContractCode()) &&
                Objects.equals(getMarketingWay(), that.getMarketingWay()) &&
                Objects.equals(getContractId(), that.getContractId()) &&
                Objects.equals(getPhone(), that.getPhone()) &&
                Objects.equals(getPayType(), that.getPayType()) &&
                Objects.equals(getSpecialOpinion(), that.getSpecialOpinion()) &&
                Objects.equals(getIsSMS(), that.getIsSMS()) &&
                Objects.equals(getCommonName(), that.getCommonName()) &&
                Objects.equals(getMedicineType(), that.getMedicineType()) &&
                Objects.equals(getProductCode(), that.getProductCode()) &&
                Objects.equals(getProductGauge(), that.getProductGauge()) &&
                Objects.equals(getSumPrice(), that.getSumPrice()) &&
                Objects.equals(getAchange(), that.getAchange()) &&
                Objects.equals(getUnitPrice(), that.getUnitPrice()) &&
                Objects.equals(getTheNumber(), that.getTheNumber()) &&
                Objects.equals(getCommerceState(), that.getCommerceState()) &&
                Objects.equals(getFunctionType(), that.getFunctionType()) &&
                Objects.equals(getCommerceName(), that.getCommerceName()) &&
                Objects.equals(getCommerceFirstId(), that.getCommerceFirstId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOwnerId(), getConsignee(), getContractCode(), getMarketingWay(), getContractId(), getPhone(), getPayType(), getSpecialOpinion(), getIsSMS(), getCommonName(), getMedicineType(), getProductCode(), getProductGauge(), getSumPrice(), getAchange(), getUnitPrice(), getTheNumber(), getCommerceState(), getFunctionType(), getCommerceName(), getCommerceFirstId());
    }

    @Override
    public String toString() {
        return "ScmXsDailyContractTask{" +
                "ownerId='" + ownerId + '\'' +
                ", consignee='" + consignee + '\'' +
                ", contractCode='" + contractCode + '\'' +
                ", marketingWay='" + marketingWay + '\'' +
                ", contractId='" + contractId + '\'' +
                ", phone='" + phone + '\'' +
                ", payType='" + payType + '\'' +
                ", specialOpinion='" + specialOpinion + '\'' +
                ", isSMS='" + isSMS + '\'' +
                ", commonName='" + commonName + '\'' +
                ", MedicineType='" + MedicineType + '\'' +
                ", productCode='" + productCode + '\'' +
                ", productGauge='" + productGauge + '\'' +
                ", sumPrice=" + sumPrice +
                ", Achange=" + Achange +
                ", unitPrice=" + unitPrice +
                ", theNumber=" + theNumber +
                ", commerceState='" + commerceState + '\'' +
                ", functionType='" + functionType + '\'' +
                ", commerceName='" + commerceName + '\'' +
                ", commerceFirstId='" + commerceFirstId + '\'' +
                '}';
    }


}