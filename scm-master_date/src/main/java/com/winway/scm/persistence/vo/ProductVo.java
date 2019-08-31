package com.winway.scm.persistence.vo;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.base.model.BaseModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 商品基础信息表
 * 
 * <pre>
* 描述：商品基础信息表 实体对象
* 构建组：x7
* 作者:贺军
* 邮箱:jun.he@winwayworld.com
* 日期:2018-11-01 10:44:03
* 版权：深圳万威科技有限公司
 * </pre>
 */
@ApiModel(value = "Product", description = "商品基础信息表")
public class ProductVo extends BaseModel<String> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键")
	protected String id;

	@ApiModelProperty(value = "产品编码")
	protected String prodCode;

	@ApiModelProperty(value = "产品名称")
	protected String prodName;

	@ApiModelProperty(value = "产品系列编码")
	protected String prodCatCode;

	@ApiModelProperty(value = "物料编码")
	protected String materialCode;

	@ApiModelProperty(value = "产品通用名称")
	protected String prodGenName;

	@ApiModelProperty(value = "产品规格")
	protected String prodSize;

	@ApiModelProperty(value = "剂型")
	protected String prodDosage;

	@ApiModelProperty(value = "单位")
	protected String prodUnit;

	@ApiModelProperty(value = "件装量")
	protected Integer prodPakeNum;

	@ApiModelProperty(value = "OTC")
	protected String OTC;

	@ApiModelProperty(value = "SUPERVISE_CODE")
	protected Integer superviseCode;

	@ApiModelProperty(value = "药品类型")
	protected String drugType;

	@ApiModelProperty(value = "药品分类")
	protected String drugCategory;

	@ApiModelProperty(value = "存储条件")
	protected String storageRange;

	@ApiModelProperty(value = "供应商")
	protected String customId;

	@ApiModelProperty(value = "生产产商")
	protected String producer;

	@ApiModelProperty(value = "建议库区")
	protected String storeId;

	@ApiModelProperty(value = "存货代码")
	protected String inventoryCode;

	@ApiModelProperty(value = "供应商id")
	protected String supplierId;

	@ApiModelProperty(value = "供应商名称")
	protected String supplierName;

	@ApiModelProperty(value = "药品类型id")
	protected String drugTypeId;

	@ApiModelProperty(value = "药品分类id")
	protected String drugCategoryId;

	@ApiModelProperty(value = "剂型ID")
	protected String prodDosageId;

	@ApiModelProperty(value = "FLOW_ID")
	protected String flowId;

	@ApiModelProperty(value = "状态")
	protected Integer status;

	@ApiModelProperty(value = "IS_DELETE")
	protected Integer isDelete;

	@ApiModelProperty(value = "产品系列名称")
	protected String prodCatName;

	@ApiModelProperty(value = "产品通用名称")
	protected String prodNormalName;

	@ApiModelProperty(value = "产品件装量")
	protected List<ProductAchange> achangeList;

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getProdNormalName() {
		return prodNormalName;
	}

	public void setProdNormalName(String prodNormalName) {
		this.prodNormalName = prodNormalName;
	}

	public String getProdCode() {
		return prodCode;
	}

	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}

	public String getProdCatName() {
		return prodCatName;
	}

	public void setProdCatName(String prodCatName) {
		this.prodCatName = prodCatName;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getId() {
		return id;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	/**
	 * 返回 产品名称
	 * 
	 * @return
	 */
	public String getProdName() {
		return this.prodName;
	}

	public String getProdCatCode() {
		return prodCatCode;
	}

	public void setProdCatCode(String prodCatCode) {
		this.prodCatCode = prodCatCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	/**
	 * 返回 物料编码
	 * 
	 * @return
	 */
	public String getMaterialCode() {
		return this.materialCode;
	}

	public void setProdGenName(String prodGenName) {
		this.prodGenName = prodGenName;
	}

	/**
	 * 返回 产品通用名称
	 * 
	 * @return
	 */
	public String getProdGenName() {
		return this.prodGenName;
	}

	public void setProdSize(String prodSize) {
		this.prodSize = prodSize;
	}

	/**
	 * 返回 产品规格
	 * 
	 * @return
	 */
	public String getProdSize() {
		return this.prodSize;
	}

	public void setProdDosage(String prodDosage) {
		this.prodDosage = prodDosage;
	}

	/**
	 * 返回 剂型
	 * 
	 * @return
	 */
	public String getProdDosage() {
		return this.prodDosage;
	}

	public void setProdUnit(String prodUnit) {
		this.prodUnit = prodUnit;
	}

	/**
	 * 返回 单位
	 * 
	 * @return
	 */
	public String getProdUnit() {
		return this.prodUnit;
	}

	public void setProdPakeNum(Integer prodPakeNum) {
		this.prodPakeNum = prodPakeNum;
	}

	/**
	 * 返回 件装量
	 * 
	 * @return
	 */
	public Integer getProdPakeNum() {
		return this.prodPakeNum;
	}

	public void setOTC(String OTC) {
		this.OTC = OTC;
	}

	/**
	 * 返回 OTC
	 * 
	 * @return
	 */
	public String getOTC() {
		return this.OTC;
	}

	public void setSuperviseCode(Integer superviseCode) {
		this.superviseCode = superviseCode;
	}

	/**
	 * 返回 SUPERVISE_CODE
	 * 
	 * @return
	 */
	public Integer getSuperviseCode() {
		return this.superviseCode;
	}

	public void setDrugType(String drugType) {
		this.drugType = drugType;
	}

	/**
	 * 返回 药品类型
	 * 
	 * @return
	 */
	public String getDrugType() {
		return this.drugType;
	}

	public void setDrugCategory(String drugCategory) {
		this.drugCategory = drugCategory;
	}

	/**
	 * 返回 药品分类
	 * 
	 * @return
	 */
	public String getDrugCategory() {
		return this.drugCategory;
	}

	public void setStorageRange(String storageRange) {
		this.storageRange = storageRange;
	}

	/**
	 * 返回 存储条件
	 * 
	 * @return
	 */
	public String getStorageRange() {
		return this.storageRange;
	}

	public void setCustomId(String customId) {
		this.customId = customId;
	}

	/**
	 * 返回 供应商
	 * 
	 * @return
	 */
	public String getCustomId() {
		return this.customId;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	/**
	 * 返回 生产产商
	 * 
	 * @return
	 */
	public String getProducer() {
		return this.producer;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	/**
	 * 返回 建议库区
	 * 
	 * @return
	 */
	public String getStoreId() {
		return this.storeId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	/**
	 * 返回 FLOW_ID
	 * 
	 * @return
	 */
	public String getFlowId() {
		return this.flowId;
	}

	public String getInventoryCode() {
		return inventoryCode;
	}

	public void setInventoryCode(String inventoryCode) {
		this.inventoryCode = inventoryCode;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getDrugTypeId() {
		return drugTypeId;
	}

	public void setDrugTypeId(String drugTypeId) {
		this.drugTypeId = drugTypeId;
	}

	public String getDrugCategoryId() {
		return drugCategoryId;
	}

	public void setDrugCategoryId(String drugCategoryId) {
		this.drugCategoryId = drugCategoryId;
	}

	public String getProdDosageId() {
		return prodDosageId;
	}

	public void setProdDosageId(String prodDosageId) {
		this.prodDosageId = prodDosageId;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 返回 状态
	 * 
	 * @return
	 */
	public Integer getStatus() {
		return this.status;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public List<ProductAchange> getAchangeList() {
		return achangeList;
	}

	public void setAchangeList(List<ProductAchange> achangeList) {
		this.achangeList = achangeList;
	}

	/**
	 * 返回 所属分公司（用于多组织数据）
	 * 
	 * @return
	 */
	public String getCompany() {
		return this.company;
	}

	/**
	 * @see Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).append("prodName", this.prodName)
				.append("prodCatCode", this.prodCatCode).append("materialCode", this.materialCode)
				.append("prodGenName", this.prodGenName).append("prodSize", this.prodSize)
				.append("prodDosage", this.prodDosage).append("prodUnit", this.prodUnit)
				.append("prodPakeNum", this.prodPakeNum).append("OTC", this.OTC)
				.append("superviseCode", this.superviseCode).append("drugType", this.drugType)
				.append("drugCategory", this.drugCategory).append("storageRange", this.storageRange)
				.append("customId", this.customId).append("producer", this.producer).append("storeId", this.storeId)
				.append("flowId", this.flowId).append("status", this.status).append("isDelete", this.isDelete)
				.append("createBy", this.createBy).append("createTime", this.createTime)
				.append("createName", this.createName).append("updateBy", this.updateBy)
				.append("updateName", this.updateName).append("updateTime", this.updateTime)
				.append("createOrgId", this.createOrgId).append("createOrgName", this.createOrgName)
				.append("company", this.company).toString();
	}
}