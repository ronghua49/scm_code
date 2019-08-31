package com.winway.scm.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;

/**
 * 不合格产品报损商品表
 * 
 * <pre>
 *  
 * 描述：不合格产品报损商品表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-23 17:00:46
 * 版权：美达开发小组
 * </pre>
 */
@ApiModel(value = "ScmKcBreakage", description = "不合格产品报损商品表")
public class ScmKcBreakage extends BaseModel<String> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	protected String id;

	@ApiModelProperty(value = "主表ID")
	protected String masterId;

	@ApiModelProperty(value = "商品名称", required = true)
	protected String productName;

	@ApiModelProperty(value = "商品编号", required = true)
	protected String productCode;

	@ApiModelProperty(value = "药品类型", required = true)
	protected String medicineType;

	@ApiModelProperty(value = "批号", required = true)
	protected String batchNum;

	@ApiModelProperty(value = "规格", required = true)
	protected String productState;

	@ApiModelProperty(value = "供应商", required = true)
	protected String supplier;

	@ApiModelProperty(value = "供应商编号", required = true)
	protected String supplierCode;

	@ApiModelProperty(value = "供应商ID", required = true)
	protected String supplierId;

	@ApiModelProperty(value = "有效期", required = true)
	protected java.util.Date expiryDate;

	@ApiModelProperty(value = "进货价", required = true)
	protected Double purchasingCost;

	@ApiModelProperty(value = "报损数量", required = true)
	protected Integer breakageSum;

	@ApiModelProperty(value = "责任人", required = true)
	protected String dutyPersion;

	@ApiModelProperty(value = "备注")
	protected String memo;

	@ApiModelProperty(value = "系列")
	protected String lineId;

	@ApiModelProperty(value = "通用名称")
	protected String commonName;

	@ApiModelProperty(value = "单位")
	protected String unit;

	@ApiModelProperty(value = "剂型")
	protected String AgentType;

	@ApiModelProperty(value = "生产厂商")
	protected String manufacturer;

	@ApiModelProperty(value = "批准文号/注册证号")
	protected String approvalCode;

	@ApiModelProperty(value = "药品分类")
	protected String medicineClassify;

	@ApiModelProperty(value = "经营范围")
	protected String businessScope;
	
	@ApiModelProperty(value = "申请原因")
	protected String applyCause;
	
	@ApiModelProperty(value = "仓库名称")
	protected String warehouseName;

	@ApiModelProperty(value = "商品字码")
	protected String code;
	@ApiModelProperty(value = "件装量")
	protected String achage;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAchage() {
		return achage;
	}

	public void setAchage(String achage) {
		this.achage = achage;
	}

	public String getCommonName() {
		return commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public String getUnit() {
		return unit;
	}

	public String getApplyCause() {
		return applyCause;
	}

	public void setApplyCause(String applyCause) {
		this.applyCause = applyCause;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getLineId() {
		return lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
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
	 * 返回 商品编号
	 * 
	 * @return
	 */
	public String getProductCode() {
		return this.productCode;
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

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	/**
	 * 返回 供应商
	 * 
	 * @return
	 */
	public String getSupplier() {
		return this.supplier;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	/**
	 * 返回 供应商编号
	 * 
	 * @return
	 */
	public String getSupplierCode() {
		return this.supplierCode;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	/**
	 * 返回 供应商ID
	 * 
	 * @return
	 */
	public String getSupplierId() {
		return this.supplierId;
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

	public void setPurchasingCost(Double purchasingCost) {
		this.purchasingCost = purchasingCost;
	}

	/**
	 * 返回 进货价
	 * 
	 * @return
	 */
	public Double getPurchasingCost() {
		return this.purchasingCost;
	}

	public void setBreakageSum(Integer breakageSum) {
		this.breakageSum = breakageSum;
	}

	/**
	 * 返回 报损数量
	 * 
	 * @return
	 */
	public Integer getBreakageSum() {
		return this.breakageSum;
	}

	public void setDutyPersion(String dutyPersion) {
		this.dutyPersion = dutyPersion;
	}

	/**
	 * 返回 责任人
	 * 
	 * @return
	 */
	public String getDutyPersion() {
		return this.dutyPersion;
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

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).append("masterId", this.masterId)
				.append("productName", this.productName).append("productCode", this.productCode)
				.append("medicineType", this.medicineType).append("batchNum", this.batchNum)
				.append("productState", this.productState).append("supplier", this.supplier)
				.append("supplierCode", this.supplierCode).append("supplierId", this.supplierId)
				.append("expiryDate", this.expiryDate).append("purchasingCost", this.purchasingCost)
				.append("breakageSum", this.breakageSum).append("dutyPersion", this.dutyPersion)
				.append("memo", this.memo).toString();
	}
}