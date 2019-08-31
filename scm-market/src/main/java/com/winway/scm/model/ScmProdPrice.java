package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 商品价格
 * <pre> 
 * 描述：商品价格 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-06-27 15:55:07
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmProdPrice",description = "商品价格") 
public class ScmProdPrice extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="year")
	protected Integer year; 
	
	@ApiModelProperty(value="prod_code")
	protected String prodCode; 
	
	@ApiModelProperty(value="商品归属（开票方）根据登录者属于哪个货主自动带出")
	protected String prodOwner; 
	
	@ApiModelProperty(value="省份")
	protected String provinceCode; 
	
	@ApiModelProperty(value="省份")
	protected String provinceName; 
	
	@ApiModelProperty(value="商业编码")
	protected String commerceCode; 
	
	@ApiModelProperty(value="商业名称")
	protected String commerceName; 
	
	@ApiModelProperty(value="商业供货价")
	protected Double commercePrice; 
	
	@ApiModelProperty(value="终端供货价")
	protected Double terminalPrice; 
	
	@ApiModelProperty(value="终端标准零售价")
	protected Double terminalSellPrice; 
	
	@ApiModelProperty(value="商业毛利率（（终端供货价-商业供货价）/终端供货价）")
	protected Double commerceRate; 
	
	@ApiModelProperty(value="终端毛利率（（终端标准零售价-终端供货价）/终端标准零售价）")
	protected Double terminalRate; 
	
	@ApiModelProperty(value="备注")
	protected String remarks; 
	
	@ApiModelProperty(value="创建人")
	protected String createBy; 
	
	@ApiModelProperty(value="创建时间")
	protected java.util.Date createTime; 
	
	@ApiModelProperty(value="创建人姓名")
	protected String createName; 
	
	@ApiModelProperty(value="修改人")
	protected String updateBy; 
	
	@ApiModelProperty(value="修改时间")
	protected java.util.Date updateTime; 
	
	@ApiModelProperty(value="修改人姓名")
	protected String updateName; 
	
	@ApiModelProperty(value="创建人所在组织")
	protected String createOrgId; 
	
	@ApiModelProperty(value="创建人所在组织名称")
	protected String createOrgName; 
	
	@ApiModelProperty(value="公司")
	protected String COMPANY; 
	
	@ApiModelProperty(value="商品名称")
	protected String prodName; 
	
	@ApiModelProperty(value="商品系列编码")
	protected String prodCatCode; 
	
	@ApiModelProperty(value="商品系列")
	protected String prodCatName; 
	
	@ApiModelProperty(value="是否全国")
	protected Integer isCountry; 
	
	@ApiModelProperty(value="价格归属 （高端、深度、OTC、商销、民营、院外、代理制）")
	protected String priceOwner; 
	
	
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
	
	public void setYear(Integer year) {
		this.year = year;
	}
	
	/**
	 * 返回 year
	 * @return
	 */
	public Integer getYear() {
		return this.year;
	}
	
	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}
	
	/**
	 * 返回 prod_code
	 * @return
	 */
	public String getProdCode() {
		return this.prodCode;
	}
	
	public void setProdOwner(String prodOwner) {
		this.prodOwner = prodOwner;
	}
	
	/**
	 * 返回 商品归属（开票方）根据登录者属于哪个货主自动带出
	 * @return
	 */
	public String getProdOwner() {
		return this.prodOwner;
	}
	
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	
	/**
	 * 返回 省份
	 * @return
	 */
	public String getProvinceCode() {
		return this.provinceCode;
	}
	
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
	/**
	 * 返回 省份
	 * @return
	 */
	public String getProvinceName() {
		return this.provinceName;
	}
	
	public void setCommerceCode(String commerceCode) {
		this.commerceCode = commerceCode;
	}
	
	/**
	 * 返回 商业编码
	 * @return
	 */
	public String getCommerceCode() {
		return this.commerceCode;
	}
	
	public void setCommerceName(String commerceName) {
		this.commerceName = commerceName;
	}
	
	/**
	 * 返回 商业名称
	 * @return
	 */
	public String getCommerceName() {
		return this.commerceName;
	}
	
	public void setCommercePrice(Double commercePrice) {
		this.commercePrice = commercePrice;
	}
	
	/**
	 * 返回 商业供货价
	 * @return
	 */
	public Double getCommercePrice() {
		return this.commercePrice;
	}
	
	public void setTerminalPrice(Double terminalPrice) {
		this.terminalPrice = terminalPrice;
	}
	
	/**
	 * 返回 终端供货价
	 * @return
	 */
	public Double getTerminalPrice() {
		return this.terminalPrice;
	}
	
	public void setTerminalSellPrice(Double terminalSellPrice) {
		this.terminalSellPrice = terminalSellPrice;
	}
	
	/**
	 * 返回 终端标准零售价
	 * @return
	 */
	public Double getTerminalSellPrice() {
		return this.terminalSellPrice;
	}
	
	public void setCommerceRate(Double commerceRate) {
		this.commerceRate = commerceRate;
	}
	
	/**
	 * 返回 商业毛利率（（终端供货价-商业供货价）/终端供货价）
	 * @return
	 */
	public Double getCommerceRate() {
		return this.commerceRate;
	}
	
	public void setTerminalRate(Double terminalRate) {
		this.terminalRate = terminalRate;
	}
	
	/**
	 * 返回 终端毛利率（（终端标准零售价-终端供货价）/终端标准零售价）
	 * @return
	 */
	public Double getTerminalRate() {
		return this.terminalRate;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	/**
	 * 返回 备注
	 * @return
	 */
	public String getRemarks() {
		return this.remarks;
	}
	
	
	
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	
	
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	
	/**
	 * 返回 创建人姓名
	 * @return
	 */
	public String getCreateName() {
		return this.createName;
	}
	
	
	
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}
	
	/**
	 * 返回 修改人姓名
	 * @return
	 */
	public String getUpdateName() {
		return this.updateName;
	}
	
	
	
	public void setCreateOrgName(String createOrgName) {
		this.createOrgName = createOrgName;
	}
	
	/**
	 * 返回 创建人所在组织名称
	 * @return
	 */
	public String getCreateOrgName() {
		return this.createOrgName;
	}
	
	public void setCOMPANY(String COMPANY) {
		this.COMPANY = COMPANY;
	}
	
	/**
	 * 返回 公司
	 * @return
	 */
	public String getCOMPANY() {
		return this.COMPANY;
	}
	
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	
	/**
	 * 返回 商品名称
	 * @return
	 */
	public String getProdName() {
		return this.prodName;
	}
	
	public void setProdCatCode(String prodCatCode) {
		this.prodCatCode = prodCatCode;
	}
	
	/**
	 * 返回 商品系列编码
	 * @return
	 */
	public String getProdCatCode() {
		return this.prodCatCode;
	}
	
	public void setProdCatName(String prodCatName) {
		this.prodCatName = prodCatName;
	}
	
	/**
	 * 返回 商品系列
	 * @return
	 */
	public String getProdCatName() {
		return this.prodCatName;
	}
	
	public void setIsCountry(Integer isCountry) {
		this.isCountry = isCountry;
	}
	
	/**
	 * 返回 是否全国
	 * @return
	 */
	public Integer getIsCountry() {
		return this.isCountry;
	}
	
	public void setPriceOwner(String priceOwner) {
		this.priceOwner = priceOwner;
	}
	
	/**
	 * 返回 价格归属 （高端、深度、OTC、商销、民营、院外、代理制）
	 * @return
	 */
	public String getPriceOwner() {
		return this.priceOwner;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("year", this.year) 
		.append("prodCode", this.prodCode) 
		.append("prodOwner", this.prodOwner) 
		.append("provinceCode", this.provinceCode) 
		.append("provinceName", this.provinceName) 
		.append("commerceCode", this.commerceCode) 
		.append("commerceName", this.commerceName) 
		.append("commercePrice", this.commercePrice) 
		.append("terminalPrice", this.terminalPrice) 
		.append("terminalSellPrice", this.terminalSellPrice) 
		.append("commerceRate", this.commerceRate) 
		.append("terminalRate", this.terminalRate) 
		.append("remarks", this.remarks) 
		.append("createBy", this.createBy) 
		.append("createTime", this.createTime) 
		.append("createName", this.createName) 
		.append("updateBy", this.updateBy) 
		.append("updateTime", this.updateTime) 
		.append("updateName", this.updateName) 
		.append("createOrgId", this.createOrgId) 
		.append("createOrgName", this.createOrgName) 
		.append("COMPANY", this.COMPANY) 
		.append("prodName", this.prodName) 
		.append("prodCatCode", this.prodCatCode) 
		.append("prodCatName", this.prodCatName) 
		.append("isCountry", this.isCountry) 
		.append("priceOwner", this.priceOwner) 
		.toString();
	}
}