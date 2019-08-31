package com.winway.scm.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


/**
 * 药品复检通知单
 * <pre>
 * 描述：药品复检通知单 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-24 12:00:21
 * 版权：美达开发小组
 * </pre>
 */
@ApiModel(value = "ScmGspReviewCondition", description = "药品复检通知单")
public class ScmGspReviewCondition extends BaseModel<String> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    protected String id;

    @ApiModelProperty(value = "产品名称", required = true)
    protected String productName;

    @ApiModelProperty(value = "通用名", required = true)
    protected String commonName;

    @ApiModelProperty(value = "规格", required = true)
    protected String productState;

    @ApiModelProperty(value = "单位", required = true)
    protected String unit;

    @ApiModelProperty(value = "批号", required = true)
    protected String batchNumber;

    @ApiModelProperty(value = "数量", required = true)
    protected Integer productSum;

    @ApiModelProperty(value = "药品类型", required = true)
    protected String medicineType;

    @ApiModelProperty(value = "供应商", required = true)
    protected String supplierName;

    @ApiModelProperty(value = "生产厂家", required = true)
    protected String manufacturer;

    @ApiModelProperty(value = "生产日期", required = true)
    protected java.util.Date parturitionDate;

    @ApiModelProperty(value = "有效期", required = true)
    protected java.util.Date validity;

    @ApiModelProperty(value = "质量情况及责任", required = true)
    protected String qualityCondition;

    @ApiModelProperty(value = "复查情况及预防措施", required = true)
    protected String reviewCondition;

    @ApiModelProperty(value = "创建人")
    protected String createPersion;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    protected java.util.Date createDate;

    @ApiModelProperty(value = "货主ID", required = true)
    protected String ownerId;

    @ApiModelProperty(value = "审批ID")
    protected String approvalId;

    @ApiModelProperty(value = "审批状态(0:未提交,1:审批中,2:已通过,3被驳回)")
    protected String approvalState;

    @ApiModelProperty(value = "复检单号")
    protected String reviewCode;


    @ApiModelProperty(value="商品编号")
    protected String productCode;

    @ApiModelProperty(value="剂型")
    protected String agentType;
    @ApiModelProperty(value="批准文号")
    protected String approvalCode;
    @ApiModelProperty(value="经营范围")
    protected String businessScope;
    @ApiModelProperty(value="药品类型")
    protected String medicineClassify;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
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

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getReviewCode() {
        return reviewCode;
    }

    public void setReviewCode(String reviewCode) {
        this.reviewCode = reviewCode;
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

    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 返回 产品名称
     *
     * @return
     */
    public String getProductName() {
        return this.productName;
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

    public void setProductSum(Integer productSum) {
        this.productSum = productSum;
    }

    /**
     * 返回 数量
     *
     * @return
     */
    public Integer getProductSum() {
        return this.productSum;
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

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    /**
     * 返回 供应商
     *
     * @return
     */
    public String getSupplierName() {
        return this.supplierName;
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

    public void setParturitionDate(java.util.Date parturitionDate) {
        this.parturitionDate = parturitionDate;
    }

    /**
     * 返回 生产日期
     *
     * @return
     */
    public java.util.Date getParturitionDate() {
        return this.parturitionDate;
    }

    public void setValidity(java.util.Date validity) {
        this.validity = validity;
    }

    /**
     * 返回 有效期
     *
     * @return
     */
    public java.util.Date getValidity() {
        return this.validity;
    }

    public void setQualityCondition(String qualityCondition) {
        this.qualityCondition = qualityCondition;
    }

    /**
     * 返回 质量情况及责任
     *
     * @return
     */
    public String getQualityCondition() {
        return this.qualityCondition;
    }

    public void setReviewCondition(String reviewCondition) {
        this.reviewCondition = reviewCondition;
    }

    /**
     * 返回 复查情况及预防措施
     *
     * @return
     */
    public String getReviewCondition() {
        return this.reviewCondition;
    }

    public void setCreatePersion(String createPersion) {
        this.createPersion = createPersion;
    }

    /**
     * 返回 创建人
     *
     * @return
     */
    public String getCreatePersion() {
        return this.createPersion;
    }

    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 返回 创建时间
     *
     * @return
     */
    public java.util.Date getCreateDate() {
        return this.createDate;
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
     * 返回 审批状态(0:未提交,1:审批中,2:已通过,3被驳回)
     *
     * @return
     */
    public String getApprovalState() {
        return this.approvalState;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", this.id)
                .append("productName", this.productName)
                .append("productState", this.productState)
                .append("unit", this.unit)
                .append("batchNumber", this.batchNumber)
                .append("productSum", this.productSum)
                .append("medicineType", this.medicineType)
                .append("supplierName", this.supplierName)
                .append("manufacturer", this.manufacturer)
                .append("parturitionDate", this.parturitionDate)
                .append("validity", this.validity)
                .append("qualityCondition", this.qualityCondition)
                .append("reviewCondition", this.reviewCondition)
                .append("createPersion", this.createPersion)
                .append("createDate", this.createDate)
                .append("ownerId", this.ownerId)
                .append("approvalId", this.approvalId)
                .append("approvalState", this.approvalState)
                .toString();
    }
}