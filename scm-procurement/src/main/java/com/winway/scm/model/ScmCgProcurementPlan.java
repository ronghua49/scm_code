package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;


 /**
 * 采购计划表
 * <pre> 
 * 描述：采购计划表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-26 13:47:44
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
 @ApiModel(value = "ScmCgProcurementPlan",description = "采购计划表") 
public class ScmCgProcurementPlan extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="商品编码")
	protected String productCode; 
	
	@ApiModelProperty(value="供应商")
	protected String supplier; 
	
	@ApiModelProperty(value="产品品规")
	protected String productCname; 
	
	@ApiModelProperty(value="存货代码")
	protected String inventoryCode; 
	
	@ApiModelProperty(value="月份")
	protected String month; 
	
	@ApiModelProperty(value="采购数量")
	protected Integer unitPrice; 
	
	@ApiModelProperty(value="创建时间")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date createDate; 
	
	@ApiModelProperty(value="创建人")
	protected String createPersion; 
	
	@ApiModelProperty(value="备注")
	protected String memo; 
	
	@ApiModelProperty(value="是否确认")
	protected char isAffirm; 
	
	@ApiModelProperty(value="供应商是否匹配成功")
	protected char isSupplierMate; 
	
	@ApiModelProperty(value="供应商ID")
	protected String supplierId; 
	
	@ApiModelProperty(value="商品是否匹配成功")
	protected char isProductMate; 
	
	@ApiModelProperty(value="商品ID")
	protected String ProcurementContractId; 
	
	@ApiModelProperty(value="货主ID")
	protected String approvalNumber; 
	
	@ApiModelProperty(value="操作人ID")
	protected String operatorId; 
	
	@ApiModelProperty(value="操作人")
	protected String operatorName; 
	
	
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 返回 id
	 * @return
	 */
	public String getId() {
		return this.id;
	}
	
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	/**
	 * 返回 商品编码
	 * @return
	 */
	public String getProductCode() {
		return this.productCode;
	}
	
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	
	/**
	 * 返回 供应商
	 * @return
	 */
	public String getSupplier() {
		return this.supplier;
	}
	
	public void setProductCname(String productCname) {
		this.productCname = productCname;
	}
	
	/**
	 * 返回 产品品规
	 * @return
	 */
	public String getProductCname() {
		return this.productCname;
	}
	
	public void setInventoryCode(String inventoryCode) {
		this.inventoryCode = inventoryCode;
	}
	
	/**
	 * 返回 存货代码
	 * @return
	 */
	public String getInventoryCode() {
		return this.inventoryCode;
	}
	
	public void setMonth(String month) {
		this.month = month;
	}
	
	/**
	 * 返回 月份
	 * @return
	 */
	public String getMonth() {
		return this.month;
	}
	
	public void setUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	/**
	 * 返回 采购数量
	 * @return
	 */
	public Integer getUnitPrice() {
		return this.unitPrice;
	}
	
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 返回 创建时间
	 * @return
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	public void setCreatePersion(String createPersion) {
		this.createPersion = createPersion;
	}
	
	/**
	 * 返回 创建人
	 * @return
	 */
	public String getCreatePersion() {
		return this.createPersion;
	}
	
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	/**
	 * 返回 备注
	 * @return
	 */
	public String getMemo() {
		return this.memo;
	}
	
	public void setIsAffirm(char isAffirm) {
		this.isAffirm = isAffirm;
	}
	
	/**
	 * 返回 是否确认
	 * @return
	 */
	public char getIsAffirm() {
		return this.isAffirm;
	}
	
	public void setIsSupplierMate(char isSupplierMate) {
		this.isSupplierMate = isSupplierMate;
	}
	
	/**
	 * 返回 供应商是否匹配成功
	 * @return
	 */
	public char getIsSupplierMate() {
		return this.isSupplierMate;
	}
	
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	
	/**
	 * 返回 供应商ID
	 * @return
	 */
	public String getSupplierId() {
		return this.supplierId;
	}
	
	public void setIsProductMate(char isProductMate) {
		this.isProductMate = isProductMate;
	}
	
	/**
	 * 返回 商品是否匹配成功
	 * @return
	 */
	public char getIsProductMate() {
		return this.isProductMate;
	}
	
	public void setProcurementContractId(String ProcurementContractId) {
		this.ProcurementContractId = ProcurementContractId;
	}
	
	/**
	 * 返回 商品ID
	 * @return
	 */
	public String getProcurementContractId() {
		return this.ProcurementContractId;
	}
	
	public void setApprovalNumber(String approvalNumber) {
		this.approvalNumber = approvalNumber;
	}
	
	/**
	 * 返回 货主ID
	 * @return
	 */
	public String getApprovalNumber() {
		return this.approvalNumber;
	}
	
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	
	/**
	 * 返回 操作人ID
	 * @return
	 */
	public String getOperatorId() {
		return this.operatorId;
	}
	
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	
	/**
	 * 返回 操作人
	 * @return
	 */
	public String getOperatorName() {
		return this.operatorName;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("productCode", this.productCode) 
		.append("supplier", this.supplier) 
		.append("productCname", this.productCname) 
		.append("inventoryCode", this.inventoryCode) 
		.append("month", this.month) 
		.append("unitPrice", this.unitPrice) 
		.append("createDate", this.createDate) 
		.append("createPersion", this.createPersion) 
		.append("memo", this.memo) 
		.append("isAffirm", this.isAffirm) 
		.append("isSupplierMate", this.isSupplierMate) 
		.append("supplierId", this.supplierId) 
		.append("isProductMate", this.isProductMate) 
		.append("ProcurementContractId", this.ProcurementContractId) 
		.append("approvalNumber", this.approvalNumber) 
		.append("operatorId", this.operatorId) 
		.append("operatorName", this.operatorName) 
		.toString();
	}
}