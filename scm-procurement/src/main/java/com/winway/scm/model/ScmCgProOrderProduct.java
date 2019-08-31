package com.winway.scm.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


/**
 * 采购订单商品表
 * <pre>
 * 描述：采购订单商品表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-26 13:47:44
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
@ApiModel(value = "ScmCgProOrderProduct", description = "采购订单商品表")
public class ScmCgProOrderProduct extends BaseModel<String> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    protected String id;

    @ApiModelProperty(value = "订单ID")
    protected String proOrderId;

    @ApiModelProperty(value = "商品ID")
    protected String productId;

    @ApiModelProperty(value = "商品名称")
    protected String productName;

    @ApiModelProperty(value = "产品编码")
    protected String productCode;

    @ApiModelProperty(value = "商品通用名")
    protected String productCname;

    @ApiModelProperty(value = "品规")
    protected String productGauge;

    @ApiModelProperty(value = "药品类型ID")
    protected String MedicineTypeId;

    @ApiModelProperty(value = "药品类型")
    protected String MedicineType;

    @ApiModelProperty(value = "单位")
    protected String unit;

    @ApiModelProperty(value = "单价")
    protected Double unitPrice;

    @ApiModelProperty(value = "总金额")
    protected Double sumPrice;

    @ApiModelProperty(value = "币种")
    protected String currency;

    @ApiModelProperty(value = "件装量")
    protected String achage;

    @ApiModelProperty(value = "采购数量")
    protected Integer productSum;

    @ApiModelProperty(value="已拒数量",required = true)
    protected Integer rejectionTotal=0;

    @ApiModelProperty(value = "采购订单号")
    protected String procurementCode;

    @ApiModelProperty(value = "合同商品ID")
    protected String contractProductId;

    @ApiModelProperty(value = "大合同剩余数量")
    protected Integer surplusAmount;

    @ApiModelProperty(value = "生产厂商")
    protected  String manufacturer;
    @ApiModelProperty(value = "经营范围")
    protected  String businessScope;

    @ApiModelProperty(value = "药品分类")
    protected  String medicineClassify;

    @ApiModelProperty(value = "批准文号")
    protected  String approvalCode;

    @ApiModelProperty(value = " 剂型")
    protected  String AgentType;
    
    @ApiModelProperty(value = "产品子码")
    protected  String code;

    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }

    public String getAgentType() {
        return AgentType;
    }

    public void setAgentType(String agentType) {
        AgentType = agentType;
    }

    public Integer getRejectionTotal() {
        return rejectionTotal;
    }

    public void setRejectionTotal(Integer rejectionTotal) {
        this.rejectionTotal = rejectionTotal;
    }


    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Integer getSurplusAmount() {
        return surplusAmount;
    }

    public void setSurplusAmount(Integer surplusAmount) {
        this.surplusAmount = surplusAmount;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
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

    public void setProOrderId(String proOrderId) {
        this.proOrderId = proOrderId;
    }

    /**
     * 返回 订单ID
     *
     * @return
     */
    public String getProOrderId() {
        return this.proOrderId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * 返回 商品ID
     *
     * @return
     */
    public String getProductId() {
        return this.productId;
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

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * 返回 产品编码
     *
     * @return
     */
    public String getProductCode() {
        return this.productCode;
    }

    public void setProductCname(String productCname) {
        this.productCname = productCname;
    }

    /**
     * 返回 商品通用名
     *
     * @return
     */
    public String getProductCname() {
        return this.productCname;
    }

    public void setProductGauge(String productGauge) {
        this.productGauge = productGauge;
    }

    /**
     * 返回 品规
     *
     * @return
     */
    public String getProductGauge() {
        return this.productGauge;
    }

    public void setMedicineTypeId(String MedicineTypeId) {
        this.MedicineTypeId = MedicineTypeId;
    }


    public String getContractProductId() {
        return contractProductId;
    }

    public void setContractProductId(String contractProductId) {
        this.contractProductId = contractProductId;
    }

    /**
     * 返回 药品类型ID
     *
     * @return
     */
    public String getMedicineTypeId() {
        return this.MedicineTypeId;
    }

    public void setMedicineType(String MedicineType) {
        this.MedicineType = MedicineType;
    }

    /**
     * 返回 药品类型
     *
     * @return
     */
    public String getMedicineType() {
        return this.MedicineType;
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

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * 返回 单价
     *
     * @return
     */
    public Double getUnitPrice() {
        return this.unitPrice;
    }

    public void setSumPrice(Double sumPrice) {
        this.sumPrice = sumPrice;
    }

    /**
     * 返回 总金额
     *
     * @return
     */
    public Double getSumPrice() {
        return this.sumPrice;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * 返回 币种
     *
     * @return
     */
    public String getCurrency() {
        return this.currency;
    }


    public String getAchage() {
		return achage;
	}

	public void setAchage(String achage) {
		this.achage = achage;
	}

	public void setProductSum(Integer productSum) {
        this.productSum = productSum;
    }

    /**
     * 返回 采购数量
     *
     * @return
     */
    public Integer getProductSum() {
        return this.productSum;
    }

    public void setProcurementCode(String procurementCode) {
        this.procurementCode = procurementCode;
    }

    /**
     * 返回 采购订单号
     *
     * @return
     */
    public String getProcurementCode() {
        return this.procurementCode;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", this.id)
                .append("proOrderId", this.proOrderId)
                .append("productId", this.productId)
                .append("productName", this.productName)
                .append("productCode", this.productCode)
                .append("productCname", this.productCname)
                .append("productGauge", this.productGauge)
                .append("MedicineTypeId", this.MedicineTypeId)
                .append("MedicineType", this.MedicineType)
                .append("unit", this.unit)
                .append("unitPrice", this.unitPrice)
                .append("sumPrice", this.sumPrice)
                .append("currency", this.currency)
                .append("Achange", this.achage)
                .append("productSum", this.productSum)
                .append("procurementCode", this.procurementCode)
                .toString();
    }
}