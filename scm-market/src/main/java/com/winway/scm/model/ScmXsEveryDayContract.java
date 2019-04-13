package com.winway.scm.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;


/**
 * 日常合同
 * <pre>
 * 描述：日常合同 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:44
 * 版权：美达开发小组
 * </pre>
 */
@ApiModel(value = "ScmXsEveryDayContract", description = "日常合同")
public class ScmXsEveryDayContract extends BaseModel<String> {

    private static final long serialVersionUID = 1L;

    public static final String UNAPPROVALED_STATE = "0";
    public static final String APPROVALING_STATE = "1";
    public static final String APPROVAL_DONE_STATE = "2";
    public static final String DEL_STATE="1";

    @ApiModelProperty(value = "id")
    protected String id;

    @ApiModelProperty(value = "供货商")
    protected String ownerName;

    @ApiModelProperty(value = "经销商")
    protected String dealer;

    @ApiModelProperty(value = "经销商首营ID")
    protected String commerceFirstId;

    @ApiModelProperty(value = "合同编号")
    protected String contractCode;

    @ApiModelProperty(value = "收货人")
    protected String consignee;

    @ApiModelProperty(value = "联系电话")
    protected String phone;

    @ApiModelProperty(value = "购销方式(0预付款,1赊销)")
    protected String marketingWay;

    @ApiModelProperty(value = "到货地址")
    protected String shipAddress;

    @ApiModelProperty(value = "付款方式")
    protected String payType;

    @ApiModelProperty(value = "签订地址")
    protected String signTheAddress;

    @ApiModelProperty(value = "特求意见")
    protected String specialOpinion;

    @ApiModelProperty(value = "乙方单位")
    protected String YUnit;

    @ApiModelProperty(value = "乙方地址")
    protected String YAddress;

    @ApiModelProperty(value = "乙方开户行")
    protected String YOpenBank;

    @ApiModelProperty(value = "乙方税号")
    protected String YDutyCode;

    @ApiModelProperty(value = "乙方账号")
    protected String YBankCode;

    @ApiModelProperty(value = "乙方电话")
    protected String YPhone;

    @ApiModelProperty(value = "乙方邮编")
    protected String YPostcode;

    @ApiModelProperty(value = "甲方单位")
    protected String JUnit;

    @ApiModelProperty(value = "甲方地址")
    protected String JAddress;

    @ApiModelProperty(value = "甲方开户行")
    protected String JOpenBank;

    @ApiModelProperty(value = "甲方账户")
    protected String JBankCode;

    @ApiModelProperty(value = "甲方税号")
    protected String JDutyCode;

    @ApiModelProperty(value = "甲方电话")
    protected String JPhone;

    @ApiModelProperty(value = "甲方邮编")
    protected String JPostcode;

    @ApiModelProperty(value = "货主ID")
    protected String ownerId;

    @ApiModelProperty(value = "协议ID")
    protected String DealerClauseId;

    @ApiModelProperty(value = "减免合同理由")
    protected Integer reduceContractTime;

    @ApiModelProperty(value = "审批ID")
    protected String approvalId;

    @ApiModelProperty(value = "审批状态")
    protected String approvalState;

    @ApiModelProperty(value = "签订日期")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date dateOfSigning;


    @ApiModelProperty(value = "计划发货时间")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date sendProductDate;

    public Date getSendProductDate() {
        return sendProductDate;
    }

    public void setSendProductDate(Date sendProductDate) {
        this.sendProductDate = sendProductDate;
    }

    @ApiModelProperty(value = "删除状态")
    protected String isDelete="0";

    @ApiModelProperty(value = "协议id")
    protected String agreementSummaryId;

    @ApiModelProperty(value = "创建人")
    protected String entryPeople;

    @ApiModelProperty(value="录入时间")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    protected Date entryDate;

    public String getEntryPeople() {
        return entryPeople;
    }

    public void setEntryPeople(String entryPeople) {
        this.entryPeople = entryPeople;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public String getAgreementSummaryId() {
        return agreementSummaryId;
    }

    public void setAgreementSummaryId(String agreementSummaryId) {
        this.agreementSummaryId = agreementSummaryId;
    }

    /**
     * 日常合同商品表列表
     */
    protected List<ScmXsEveryDayContractProduct> scmXsEveryDayContractProductList = new ArrayList<ScmXsEveryDayContractProduct>();

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

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    /**
     * 返回 供货商
     *
     * @return
     */
    public String getOwnerName() {
        return this.ownerName;
    }

    public void setDealer(String dealer) {
        this.dealer = dealer;
    }

    /**
     * 返回 经销商
     *
     * @return
     */
    public String getDealer() {
        return this.dealer;
    }

    public void setCommerceFirstId(String commerceFirstId) {
        this.commerceFirstId = commerceFirstId;
    }

    /**
     * 返回 经销商首营ID
     *
     * @return
     */
    public String getCommerceFirstId() {
        return this.commerceFirstId;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    /**
     * 返回 合同编号
     *
     * @return
     */
    public String getContractCode() {
        return this.contractCode;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    /**
     * 返回 收货人
     *
     * @return
     */
    public String getConsignee() {
        return this.consignee;
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

    public void setMarketingWay(String marketingWay) {
        this.marketingWay = marketingWay;
    }

    /**
     * 返回 购销方式(0预付款,1赊销)
     *
     * @return
     */
    public String getMarketingWay() {
        return this.marketingWay;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    /**
     * 返回 到货地址
     *
     * @return
     */
    public String getShipAddress() {
        return this.shipAddress;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    /**
     * 返回 付款方式
     *
     * @return
     */
    public String getPayType() {
        return this.payType;
    }

    public void setSignTheAddress(String signTheAddress) {
        this.signTheAddress = signTheAddress;
    }

    /**
     * 返回 签订地址
     *
     * @return
     */
    public String getSignTheAddress() {
        return this.signTheAddress;
    }

    public void setSpecialOpinion(String specialOpinion) {
        this.specialOpinion = specialOpinion;
    }

    /**
     * 返回 特求意见
     *
     * @return
     */
    public String getSpecialOpinion() {
        return this.specialOpinion;
    }

    public void setYUnit(String YUnit) {
        this.YUnit = YUnit;
    }

    /**
     * 返回 乙方单位
     *
     * @return
     */
    public String getYUnit() {
        return this.YUnit;
    }

    public void setYAddress(String YAddress) {
        this.YAddress = YAddress;
    }

    /**
     * 返回 乙方地址
     *
     * @return
     */
    public String getYAddress() {
        return this.YAddress;
    }

    public void setYOpenBank(String YOpenBank) {
        this.YOpenBank = YOpenBank;
    }

    /**
     * 返回 乙方开户行
     *
     * @return
     */
    public String getYOpenBank() {
        return this.YOpenBank;
    }

    public void setYDutyCode(String YDutyCode) {
        this.YDutyCode = YDutyCode;
    }

    /**
     * 返回 乙方税号
     *
     * @return
     */
    public String getYDutyCode() {
        return this.YDutyCode;
    }

    public void setYBankCode(String YBankCode) {
        this.YBankCode = YBankCode;
    }

    /**
     * 返回 乙方账号
     *
     * @return
     */
    public String getYBankCode() {
        return this.YBankCode;
    }

    public void setYPhone(String YPhone) {
        this.YPhone = YPhone;
    }

    /**
     * 返回 乙方电话
     *
     * @return
     */
    public String getYPhone() {
        return this.YPhone;
    }

    public void setYPostcode(String YPostcode) {
        this.YPostcode = YPostcode;
    }

    /**
     * 返回 乙方邮编
     *
     * @return
     */
    public String getYPostcode() {
        return this.YPostcode;
    }

    public void setJUnit(String JUnit) {
        this.JUnit = JUnit;
    }

    /**
     * 返回 甲方单位
     *
     * @return
     */
    public String getJUnit() {
        return this.JUnit;
    }

    public void setJAddress(String JAddress) {
        this.JAddress = JAddress;
    }

    /**
     * 返回 甲方地址
     *
     * @return
     */
    public String getJAddress() {
        return this.JAddress;
    }

    public void setJOpenBank(String JOpenBank) {
        this.JOpenBank = JOpenBank;
    }

    /**
     * 返回 甲方开户行
     *
     * @return
     */
    public String getJOpenBank() {
        return this.JOpenBank;
    }

    public void setJBankCode(String JBankCode) {
        this.JBankCode = JBankCode;
    }

    /**
     * 返回 甲方账户
     *
     * @return
     */
    public String getJBankCode() {
        return this.JBankCode;
    }

    public void setJDutyCode(String JDutyCode) {
        this.JDutyCode = JDutyCode;
    }

    /**
     * 返回 甲方税号
     *
     * @return
     */
    public String getJDutyCode() {
        return this.JDutyCode;
    }

    public void setJPhone(String JPhone) {
        this.JPhone = JPhone;
    }

    /**
     * 返回 甲方电话
     *
     * @return
     */
    public String getJPhone() {
        return this.JPhone;
    }

    public void setJPostcode(String JPostcode) {
        this.JPostcode = JPostcode;
    }

    /**
     * 返回 甲方邮编
     *
     * @return
     */
    public String getJPostcode() {
        return this.JPostcode;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * 返回 货主ID
     *
     * @return
     */
    public String getOwnerId() {
        return this.ownerId;
    }

    public void setDealerClauseId(String DealerClauseId) {
        this.DealerClauseId = DealerClauseId;
    }

    /**
     * 返回 协议ID
     *
     * @return
     */
    public String getDealerClauseId() {
        return this.DealerClauseId;
    }

    public void setReduceContractTime(Integer reduceContractTime) {
        this.reduceContractTime = reduceContractTime;
    }

    /**
     * 返回 减免合同理由
     *
     * @return
     */
    public Integer getReduceContractTime() {
        return this.reduceContractTime;
    }

    public void setApprovalId(String approvalId) {
        this.approvalId = approvalId;
    }

    /**
     * 返回 审批ID
     *
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
     *
     * @return
     */
    public String getApprovalState() {
        return this.approvalState;
    }

    public void setScmXsEveryDayContractProductList(List<ScmXsEveryDayContractProduct> scmXsEveryDayContractProductList) {
        this.scmXsEveryDayContractProductList = scmXsEveryDayContractProductList;
    }

    /**
     * 返回 日常合同商品表列表
     *
     * @return
     */
    public List<ScmXsEveryDayContractProduct> getScmXsEveryDayContractProductList() {
        return this.scmXsEveryDayContractProductList;
    }

    /**
     * 返回签订日期
     *
     * @return
     */
    public Date getDateOfSigning() {
        return dateOfSigning;
    }

    public void setDateOfSigning(Date dateOfSigning) {
        this.dateOfSigning = dateOfSigning;
    }


    /**
     * 返回删除状态
     * @return
     */
    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", this.id)
                .append("ownerName", this.ownerName)
                .append("dealer", this.dealer)
                .append("commerceFirstId", this.commerceFirstId)
                .append("contractCode", this.contractCode)
                .append("consignee", this.consignee)
                .append("phone", this.phone)
                .append("marketingWay", this.marketingWay)
                .append("shipAddress", this.shipAddress)
                .append("payType", this.payType)
                .append("signTheAddress", this.signTheAddress)
                .append("specialOpinion", this.specialOpinion)
                .append("YUnit", this.YUnit)
                .append("YAddress", this.YAddress)
                .append("YOpenBank", this.YOpenBank)
                .append("YDutyCode", this.YDutyCode)
                .append("YBankCode", this.YBankCode)
                .append("YPhone", this.YPhone)
                .append("YPostcode", this.YPostcode)
                .append("JUnit", this.JUnit)
                .append("JAddress", this.JAddress)
                .append("JOpenBank", this.JOpenBank)
                .append("JBankCode", this.JBankCode)
                .append("JDutyCode", this.JDutyCode)
                .append("JPhone", this.JPhone)
                .append("JPostcode", this.JPostcode)
                .append("ownerId", this.ownerId)
                .append("DealerClauseId", this.DealerClauseId)
                .append("reduceContractTime", this.reduceContractTime)
                .append("approvalId", this.approvalId)
                .append("approvalState", this.approvalState)
                .append("dateOfSigning", this.dateOfSigning)
                .toString();
    }


}