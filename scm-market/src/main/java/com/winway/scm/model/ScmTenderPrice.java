package com.winway.scm.model;

import com.hotent.base.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.time.LocalDate;


/**
 * 中标价管理
 * <pre>
 * 描述：中标价管理 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-06-25 11:09:04
 * 版权：美达开发小组
 * </pre>
 */
@ApiModel(value = "ScmTenderPrice", description = "中标价管理")
public class ScmTenderPrice extends BaseModel<String> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    protected String id;

    @ApiModelProperty(value = "聚合Id")
    protected String collectId;

    // 遗留字段，中标价申请，审批等流程未使用该字段
    @ApiModelProperty(value = "状态")
    protected Integer status;

    @ApiModelProperty(value = "招标项目编码")
    protected String tenderProjectCode;

    @ApiModelProperty(value = "省区编码")
    protected String provinceCode;

    @ApiModelProperty(value = "省区名称")
    protected String provinceName;

    @ApiModelProperty(value = "地市编码")
    protected String cityCode;

    @ApiModelProperty(value = "地市名称")
    protected String cityName;

    @ApiModelProperty(value = "县区编码")
    protected String areaCode;

    @ApiModelProperty(value = "县区名称")
    protected String areaName;

    @ApiModelProperty(value = "医院（终端编码）")
    protected String terminalCode;

    @ApiModelProperty(value = "医院名称（终端名称）")
    protected String terminalName;

    @ApiModelProperty(value = "执行状态（根据生效/失效时间判断）：10=未执行;20=执行中;40=已终止", allowableValues = "10,20,40")
    protected Integer execStatus;

    @ApiModelProperty(value = "品规编码")
    protected String prodCode;

    @ApiModelProperty(value = "中标名称")
    protected String acceptedName;

    @ApiModelProperty(value = "中标生效时间")
    protected LocalDate acceptedEffectDate;

    @ApiModelProperty(value = "中标失效时间")
    protected LocalDate acceptedEndDate;

    @ApiModelProperty(value = "转换系数")
    protected Double conversionFactor;

    @ApiModelProperty(value = "投标价")
    protected String biddingPrice;

    @ApiModelProperty(value = "中标价")
    protected String tenderPrice;

    @ApiModelProperty(value = "中标分类")
    protected String tenderType;

    @ApiModelProperty(value = "质量层次")
    protected String quality;

    @ApiModelProperty(value = "生产企业")
    protected String producer;

    @ApiModelProperty(value = "投标企业")
    protected String biddingCompany;

    @ApiModelProperty(value = "备注")
    protected String remarks;

    @ApiModelProperty(value = "流程单号")
    protected String flowId;

    @ApiModelProperty(value = "流程状态:-2 废除 -1 已拒绝 0 未提交 1 已通过 2 审批中", allowableValues = "-2, -1, 0, 1, 2")
    protected Integer flowStatus;

    @ApiModelProperty(value = "纯销供货价")
    protected double purePinPrice;

    @ApiModelProperty(value = "商业供货单价")
    protected double supplyPrice;

    @ApiModelProperty(value = "纯销毛利")
    protected double purePinGross;

    /**
     * 标志删除
     */
    protected Integer isDelete;

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public double getPurePinPrice() {
        return purePinPrice;
    }

    public void setPurePinPrice(double purePinPrice) {
        this.purePinPrice = purePinPrice;
    }

    public double getSupplyPrice() {
        return supplyPrice;
    }

    public void setSupplyPrice(double supplyPrice) {
        this.supplyPrice = supplyPrice;
    }

    public double getPurePinGross() {
        return purePinGross;
    }

    public void setPurePinGross(double purePinGross) {
        this.purePinGross = purePinGross;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 返回 主键
     *
     * @return
     */
    public String getId() {
        return this.id;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 返回 聚合Id
     *
     * @return
     */
    public String getCollectId() {
        return collectId;
    }

    public void setCollectId(String collectId) {
        this.collectId = collectId;
    }

    /**
     * 返回 状态
     *
     * @return
     */
    public Integer getStatus() {
        return this.status;
    }

    public void setTenderProjectCode(String tenderProjectCode) {
        this.tenderProjectCode = tenderProjectCode;
    }

    /**
     * 返回 招标项目编码
     *
     * @return
     */
    public String getTenderProjectCode() {
        return this.tenderProjectCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    /**
     * 返回 省区
     *
     * @return
     */
    public String getProvinceCode() {
        return this.provinceCode;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    /**
     * 返回 省区名称
     *
     * @return
     */
    public String getProvinceName() {
        return this.provinceName;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * 返回 地市
     *
     * @return
     */
    public String getCityCode() {
        return this.cityCode;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * 返回 city_name
     *
     * @return
     */
    public String getCityName() {
        return this.cityName;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * 返回 县区
     *
     * @return
     */
    public String getAreaCode() {
        return this.areaCode;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    /**
     * 返回 县区名称
     *
     * @return
     */
    public String getAreaName() {
        return this.areaName;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }

    /**
     * 返回 terminal_code
     *
     * @return
     */
    public String getTerminalCode() {
        return this.terminalCode;
    }

    public void setTerminalName(String terminalName) {
        this.terminalName = terminalName;
    }

    /**
     * 返回 terminal_name
     *
     * @return
     */
    public String getTerminalName() {
        return this.terminalName;
    }

    public void setExecStatus(Integer execStatus) {
        this.execStatus = execStatus;
    }

    /**
     * 返回 执行状态 未执行、执行中、已终止
     *
     * @return
     */
    public Integer getExecStatus() {
        if (this.acceptedEffectDate != null && this.acceptedEndDate != null){
            if (LocalDate.now().isAfter(this.acceptedEffectDate)){//晚于生效日期
                if (LocalDate.now().isBefore(this.acceptedEndDate)) {//早于失效日期
                    this.execStatus = 20;
                } else { // 晚于失效日期
                    this.execStatus = 40;
                }
            } else { //早于生效日期即为未执行
                this.execStatus = 10;
            }
        }
        return this.execStatus;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    /**
     * 返回 品规编码
     *
     * @return
     */
    public String getProdCode() {
        return this.prodCode;
    }

    public void setAcceptedName(String acceptedName) {
        this.acceptedName = acceptedName;
    }

    /**
     * 返回 accepted_name
     *
     * @return
     */
    public String getAcceptedName() {
        return this.acceptedName;
    }

    public void setAcceptedEffectDate(LocalDate acceptedEffectDate) {
        this.acceptedEffectDate = acceptedEffectDate;
    }

    /**
     * 返回 中标生效时间
     *
     * @return
     */
    public LocalDate getAcceptedEffectDate() {
        return this.acceptedEffectDate;
    }

    public void setAcceptedEndDate(LocalDate acceptedEndDate) {
        this.acceptedEndDate = acceptedEndDate;
    }

    /**
     * 返回 中标失效时间
     *
     * @return
     */
    public LocalDate getAcceptedEndDate() {
        return this.acceptedEndDate;
    }

    public void setConversionFactor(Double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    /**
     * 返回 转换系数
     *
     * @return
     */
    public Double getConversionFactor() {
        return this.conversionFactor;
    }

    public void setBiddingPrice(String biddingPrice) {
        this.biddingPrice = biddingPrice;
    }

    /**
     * 返回 bidding_price
     *
     * @return
     */
    public String getBiddingPrice() {
        return this.biddingPrice;
    }

    public void setTenderPrice(String tenderPrice) {
        this.tenderPrice = tenderPrice;
    }

    /**
     * 返回 tender_price
     *
     * @return
     */
    public String getTenderPrice() {
        return this.tenderPrice;
    }

    public void setTenderType(String tenderType) {
        this.tenderType = tenderType;
    }

    /**
     * 返回 中标分类
     *
     * @return
     */
    public String getTenderType() {
        return this.tenderType;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    /**
     * 返回 quality
     *
     * @return
     */
    public String getQuality() {
        return this.quality;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    /**
     * 返回 producer
     *
     * @return
     */
    public String getProducer() {
        return this.producer;
    }

    public void setBiddingCompany(String biddingCompany) {
        this.biddingCompany = biddingCompany;
    }

    /**
     * 返回 bidding_company
     *
     * @return
     */
    public String getBiddingCompany() {
        return this.biddingCompany;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 返回 备注
     *
     * @return
     */
    public String getRemarks() {
        return this.remarks;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    /**
     * 返回 流程单号
     *
     * @return
     */
    public String getFlowId() {
        return this.flowId;
    }

    public void setFlowStatus(Integer flowStatus) {
        this.flowStatus = flowStatus;
    }

    /**
     * 返回 flow_status
     *
     * @return
     */
    public Integer getFlowStatus() {
        return this.flowStatus;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", this.id)
                .append("collectId", this.collectId)
                .append("tenderProjectCode", this.tenderProjectCode)
                .append("status", this.status)
                .append("provinceCode", this.provinceCode)
                .append("provinceName", this.provinceName)
                .append("cityCode", this.cityCode)
                .append("cityName", this.cityName)
                .append("areaCode", this.areaCode)
                .append("areaName", this.areaName)
                .append("terminalCode", this.terminalCode)
                .append("terminalName", this.terminalName)
                .append("execStatus", this.execStatus)
                .append("prodCode", this.prodCode)
                .append("acceptedName", this.acceptedName)
                .append("acceptedEffectDate", this.acceptedEffectDate)
                .append("acceptedEndDate", this.acceptedEndDate)
                .append("conversionFactor", this.conversionFactor)
                .append("biddingPrice", this.biddingPrice)
                .append("tenderPrice", this.tenderPrice)
                .append("tenderType", this.tenderType)
                .append("quality", this.quality)
                .append("producer", this.producer)
                .append("biddingCompany", this.biddingCompany)
                .append("remarks", this.remarks)
                .append("flowId", this.flowId)
                .append("flowStatus", this.flowStatus)
                .toString();
    }
}