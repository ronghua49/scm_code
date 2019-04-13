package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 采购合同商品表
 * <pre> 
 * 描述：采购合同商品表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-26 13:47:44
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
 @ApiModel(value = "ScmCgContractProduct",description = "采购合同商品表") 
public class ScmCgContractProduct extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="采购合同id")
	protected String contractId; 
	
	@ApiModelProperty(value="合同编号")
	protected String contractCode; 
	
	@ApiModelProperty(value="产品ID")
	protected String productId; 
	
	@ApiModelProperty(value="产品名称")
	protected String ProductName; 
	
	@ApiModelProperty(value="产品编码")
	protected String ProductCode; 
	
	@ApiModelProperty(value="产品通用名称")
	protected String productCname; 
	
	@ApiModelProperty(value="品规")
	protected String productGauge; 
	
	@ApiModelProperty(value="药品类型ID")
	protected String medicineTypeId; 
	
	@ApiModelProperty(value="药品类型")
	protected String medicineType; 
	
	@ApiModelProperty(value="单位")
	protected String unit; 
	
	@ApiModelProperty(value="单价")
	protected Double unitPrice; 
	
	@ApiModelProperty(value="数量")
	protected Integer productSum; 
	
	@ApiModelProperty(value="总金额")
	protected Double sumPrice; 
	
	@ApiModelProperty(value="币种")
	protected String currency; 
	
	@ApiModelProperty(value="批准文号/启用批号")
	protected String approvalNumber; 
	
	@ApiModelProperty(value="件装量")
	protected int achange; 
	
	@ApiModelProperty(value="剩余数量")
	protected int surplusAmount; 
	
	
	
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
	
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	
	/**
	 * 返回 合同编号
	 * @return
	 */
	public String getContractCode() {
		return this.contractCode;
	}
	
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	/**
	 * 返回 采购合同id
	 * @return
	 */
	public String getContractId() {
		return contractId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	/**
	 * 返回 产品ID
	 * @return
	 */
	public String getProductId() {
		return this.productId;
	}
	
	public void setProductName(String ProductName) {
		this.ProductName = ProductName;
	}
	
	/**
	 * 返回 产品名称
	 * @return
	 */
	public String getProductName() {
		return this.ProductName;
	}
	
	public void setProductCode(String ProductCode) {
		this.ProductCode = ProductCode;
	}
	
	/**
	 * 返回 产品编码
	 * @return
	 */
	public String getProductCode() {
		return this.ProductCode;
	}
	
	public void setProductCname(String productCname) {
		this.productCname = productCname;
	}
	
	/**
	 * 返回 产品通用名称
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
	 * @return
	 */
	public String getProductGauge() {
		return this.productGauge;
	}
	
	public void setMedicineTypeId(String medicineTypeId) {
		this.medicineTypeId = medicineTypeId;
	}
	
	/**
	 * 返回 药品类型ID
	 * @return
	 */
	public String getMedicineTypeId() {
		return this.medicineTypeId;
	}
	
	public void setMedicineType(String medicineType) {
		this.medicineType = medicineType;
	}
	
	/**
	 * 返回 药品类型
	 * @return
	 */
	public String getMedicineType() {
		return this.medicineType;
	}
	
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	/**
	 * 返回 单位
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
	 * @return
	 */
	public Double getUnitPrice() {
		return this.unitPrice;
	}
	
	public void setProductSum(Integer productSum) {
		this.productSum = productSum;
	}
	
	/**
	 * 返回 数量
	 * @return
	 */
	public Integer getProductSum() {
		return this.productSum;
	}
	
	public void setSumPrice(Double sumPrice) {
		this.sumPrice = sumPrice;
	}
	
	/**
	 * 返回 总金额
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
	 * @return
	 */
	public String getCurrency() {
		return this.currency;
	}
	
	public void setApprovalNumber(String approvalNumber) {
		this.approvalNumber = approvalNumber;
	}
	
	public int getAchange() {
		return achange;
	}

	public void setAchange(int achange) {
		this.achange = achange;
	}

	public int getSurplusAmount() {
		return surplusAmount;
	}

	public void setSurplusAmount(int surplusAmount) {
		this.surplusAmount = surplusAmount;
	}

	/**
	 * 返回 批准文号/启用批号
	 * @return
	 */
	public String getApprovalNumber() {
		return this.approvalNumber;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("contractCode", this.contractCode) 
		.append("productId", this.productId) 
		.append("ProductName", this.ProductName) 
		.append("ProductCode", this.ProductCode) 
		.append("productCname", this.productCname) 
		.append("productGauge", this.productGauge) 
		.append("medicineTypeId", this.medicineTypeId) 
		.append("medicineType", this.medicineType) 
		.append("unit", this.unit) 
		.append("unitPrice", this.unitPrice) 
		.append("productSum", this.productSum) 
		.append("sumPrice", this.sumPrice) 
		.append("currency", this.currency) 
		.append("approvalNumber", this.approvalNumber) 
		.toString();
	}

/*	public void setContractId(String pk) {
		// TODO Auto-generated method stub
		
	}*/
}