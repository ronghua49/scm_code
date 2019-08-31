package com.winway.scm.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


/**
 * 库存调拨商品表
 * <pre>
 * 描述：库存调拨商品表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-23 17:00:45
 * 版权：美达开发小组
 * </pre>
 */
@ApiModel(value = "ScmKcAllot", description = "库存调拨商品表")
public class ScmKcAllot extends BaseModel<String> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    protected String id;

    @ApiModelProperty(value = "批号", required = true)
    protected String batchNum;

    @ApiModelProperty(value = "商品名称", required = true)
    protected String productName;

    @ApiModelProperty(value = "药品编号", required = true)
    protected String productCode;

    @ApiModelProperty(value = "药品id", required = true)
    protected String productId;

    @ApiModelProperty(value = "药品类型", required = true)
    protected String medicineType;

    @ApiModelProperty(value = "规格", required = true)
    protected String productState;

    @ApiModelProperty(value = "单位", required = true)
    protected String unit;

    @ApiModelProperty(value = "生产厂家", required = true)
    protected String manufacturer;

    @ApiModelProperty(value = "生产日期", required = true)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    protected java.util.Date manufactureDate;

    @ApiModelProperty(value = "有效期", required = true)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    protected java.util.Date expiryDate;

    @ApiModelProperty(value = "入库日期", required = true)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    protected java.util.Date inboundDate;

    @ApiModelProperty(value = "库存数量", required = true)
    protected Integer quantityInStock;

    @ApiModelProperty(value = "调拨数量", required = true)
    protected Integer allotSum;

    @ApiModelProperty(value = "单价", required = true)
    protected Double price;

    @ApiModelProperty(value = "调拨金额", required = true)
    protected Double priceSum;

    @ApiModelProperty(value = "备注", required = true)
    protected String memo;

    @ApiModelProperty(value = "主表ID")
    protected String masterId;
    @ApiModelProperty(value="通用名")
    protected String commonName;
    @ApiModelProperty(value="剂型")
    protected String agentType;
    @ApiModelProperty(value="批准文号")
    protected String approvalCode;
    @ApiModelProperty(value="经营范围")
    protected String businessScope;
    @ApiModelProperty(value="药品类型")
    protected String medicineClassify;

    @ApiModelProperty(value="件装量")
    protected String achage;
    @ApiModelProperty(value="商品字码")
    protected String code;

    public String getAchage() {
        return achage;
    }

    public void setAchage(String achage) {
        this.achage = achage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    public String getMedicineClassify() {
        return medicineClassify;
    }

    public void setMedicineClassify(String medicineClassify) {
        this.medicineClassify = medicineClassify;
    }

    //非数据库字段
    @ApiModelProperty(value = "主表ID")
    protected String wareHost;

    public String getWareHost() {
        return wareHost;
    }

    public void setWareHost(String wareHost) {
        this.wareHost = wareHost;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum;
    }

    /**
     * 返回 批号
     *
     * @return
     */
    public String getBatchNum() {
        return this.batchNum;
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

    public void setMedicineType(String medicineType) {
        this.medicineType = medicineType;
    }

    /**
     * 返回 药品类型
     *
     * @return
     */
    public String getMedicineType() {
        return this.medicineType;
    }

    public void setProductState(String productState) {
        this.productState = productState;
    }

    /**
     * 返回 规格
     *
     * @return
     */
    public String getProductState() {
        return this.productState;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * 返回 单位
     *
     * @return
     */
    public String getUnit() {
        return this.unit;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * 返回 生产厂家
     *
     * @return
     */
    public String getManufacturer() {
        return this.manufacturer;
    }

    public void setManufactureDate(java.util.Date manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    /**
     * 返回 生产日期
     *
     * @return
     */
    public java.util.Date getManufactureDate() {
        return this.manufactureDate;
    }

    public void setExpiryDate(java.util.Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * 返回 有效期
     *
     * @return
     */
    public java.util.Date getExpiryDate() {
        return this.expiryDate;
    }

    public void setInboundDate(java.util.Date inboundDate) {
        this.inboundDate = inboundDate;
    }

    /**
     * 返回 入库日期
     *
     * @return
     */
    public java.util.Date getInboundDate() {
        return this.inboundDate;
    }

    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    /**
     * 返回 库存数量
     *
     * @return
     */
    public Integer getQuantityInStock() {
        return this.quantityInStock;
    }

    public void setAllotSum(Integer allotSum) {
        this.allotSum = allotSum;
    }

    /**
     * 返回 调拨数量
     *
     * @return
     */
    public Integer getAllotSum() {
        return this.allotSum;
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

    public void setPriceSum(Double priceSum) {
        this.priceSum = priceSum;
    }

    /**
     * 返回 调拨金额
     *
     * @return
     */
    public Double getPriceSum() {
        return this.priceSum;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * 返回 备注
     *
     * @return
     */
    public String getMemo() {
        return this.memo;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    /**
     * 返回 主表ID
     *
     * @return
     */
    public String getMasterId() {
        return this.masterId;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", this.id)
                .append("batchNum", this.batchNum)
                .append("productName", this.productName)
                .append("medicineType", this.medicineType)
                .append("productState", this.productState)
                .append("unit", this.unit)
                .append("manufacturer", this.manufacturer)
                .append("manufactureDate", this.manufactureDate)
                .append("expiryDate", this.expiryDate)
                .append("inboundDate", this.inboundDate)
                .append("quantityInStock", this.quantityInStock)
                .append("allotSum", this.allotSum)
                .append("price", this.price)
                .append("priceSum", this.priceSum)
                .append("memo", this.memo)
                .append("masterId", this.masterId)
                .toString();
    }
}