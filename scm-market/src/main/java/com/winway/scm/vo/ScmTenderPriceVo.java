package com.winway.scm.vo;

import com.winway.scm.model.ScmTenderPrice;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 中标价视图
 *
 * @Author: FangGan
 * @Email: libayiv@163.com
 * @Date: 2019/8/20 17:52
 **/
@ApiModel(value = "ScmTenderPriceVo", description = "中标价视图管理")
public class ScmTenderPriceVo extends ScmTenderPrice implements Serializable {

    private static final long serialVersionUID = 4419806742779944660L;

    @ApiModelProperty(value = "招标项目名")
    private String tenderName;

    @ApiModelProperty(value = "招标年份")
    private String tenderYear;

    @ApiModelProperty(value = "招标生效日期")
    private LocalDate tenderStartDate;

    @ApiModelProperty(value = "招标失效日期")
    private LocalDate tenderEndDate;

    @ApiModelProperty(value = "招标性质")
    private String tenderProperty;

    @ApiModelProperty(value = "商品名")
    private String productName;

    @ApiModelProperty(value = "产品系列Id")
    private String productLineId;

    @ApiModelProperty(value = "商品通用名")
    private String commonName;

    @ApiModelProperty(value = "剂型")
    private String agentType;

    @ApiModelProperty(value = "品规")
    private String productState;

    @ApiModelProperty(value = "包装单位")
    private String unit;


    public String getTenderName() {
        return tenderName;
    }

    public void setTenderName(String tenderName) {
        this.tenderName = tenderName;
    }

    public String getTenderYear() {
        return tenderYear;
    }

    public void setTenderYear(String tenderYear) {
        this.tenderYear = tenderYear;
    }

    public LocalDate getTenderStartDate() {
        return tenderStartDate;
    }

    public void setTenderStartDate(LocalDate tenderStartDate) {
        this.tenderStartDate = tenderStartDate;
    }

    public LocalDate getTenderEndDate() {
        return tenderEndDate;
    }

    public void setTenderEndDate(LocalDate tenderEndDate) {
        this.tenderEndDate = tenderEndDate;
    }

    public String getTenderProperty() {
        return tenderProperty;
    }

    public void setTenderProperty(String tenderProperty) {
        this.tenderProperty = tenderProperty;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductLineId() {
        return productLineId;
    }

    public void setProductLineId(String productLineId) {
        this.productLineId = productLineId;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getAgentType() {
        return agentType;
    }

    public void setAgentType(String agentType) {
        this.agentType = agentType;
    }

    public String getProductState() {
        return productState;
    }

    public void setProductState(String productState) {
        this.productState = productState;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
