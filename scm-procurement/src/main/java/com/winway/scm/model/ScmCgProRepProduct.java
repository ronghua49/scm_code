package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 采购补单商品表
 * <pre> 
 * 描述：采购补单商品表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-26 13:47:44
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
 @ApiModel(value = "ScmCgProRepProduct",description = "采购补单商品表") 
public class ScmCgProRepProduct extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="补单ID")
	protected String replacementOrderId; 
	
	@ApiModelProperty(value="商品ID")
	protected String productId; 
	
	@ApiModelProperty(value="商品名称")
	protected String productName; 
	
	@ApiModelProperty(value="产品编码")
	protected String productCode; 
	
	@ApiModelProperty(value="商品通用名")
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
	
	@ApiModelProperty(value="总金额")
	protected Double sumPrice; 
	
	@ApiModelProperty(value="币种")
	protected String currency; 
	
	@ApiModelProperty(value="件装量")
	protected String achange; 
	
	@ApiModelProperty(value="采购数量")
	protected Integer productSum; 
	
	@ApiModelProperty(value="合同商品ID")
	protected String contractProductId; 
	
	@ApiModelProperty(value="大合同剩余数量")
	protected int surplusAmount;
	
	public int getSurplusAmount() {
		return surplusAmount;
	}

	public void setSurplusAmount(int surplusAmount) {
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
	 * @return
	 */
	public String getId() {
		return this.id;
	}
	
	public void setReplacementOrderId(String replacementOrderId) {
		this.replacementOrderId = replacementOrderId;
	}
	
	/**
	 * 返回 补单ID
	 * @return
	 */
	public String getReplacementOrderId() {
		return this.replacementOrderId;
	}
	
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	public String getContractProductId() {
		return contractProductId;
	}

	public void setContractProductId(String contractProductId) {
		this.contractProductId = contractProductId;
	}

	/**
	 * 返回 商品ID
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
	
	public void setAchange(String achange) {
		this.achange = achange;
	}
	
	/**
	 * 返回 件装量
	 * @return
	 */
	public String getAchange() {
		return this.achange;
	}
	
	public void setProductSum(Integer productSum) {
		this.productSum = productSum;
	}
	
	/**
	 * 返回 采购数量
	 * @return
	 */
	public Integer getProductSum() {
		return this.productSum;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("ReplacementOrderId", this.replacementOrderId) 
		.append("productId", this.productId) 
		.append("productName", this.productName) 
		.append("productCode", this.productCode) 
		.append("productCname", this.productCname) 
		.append("productGauge", this.productGauge) 
		.append("medicineTypeId", this.medicineTypeId) 
		.append("medicineType", this.medicineType) 
		.append("unit", this.unit) 
		.append("unitPrice", this.unitPrice) 
		.append("sumPrice", this.sumPrice) 
		.append("currency", this.currency) 
		.append("achange", this.achange) 
		.append("productSum", this.productSum) 
		.toString();
	}
}