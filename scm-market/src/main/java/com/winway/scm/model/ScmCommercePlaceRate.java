package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 商品渠道毛利率管理
 * <pre> 
 * 描述：商品渠道毛利率管理 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-06-27 15:55:06
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmCommercePlaceRate",description = "商品渠道毛利率管理") 
public class ScmCommercePlaceRate extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="year")
	protected Integer year; 
	
	@ApiModelProperty(value="商务分区")
	protected String businessDivisionCode; 
	
	@ApiModelProperty(value="商务分区")
	protected String businessDivisionName; 
	
	@ApiModelProperty(value="省区")
	protected String provinceCode; 
	
	@ApiModelProperty(value="省区")
	protected String provinceName; 
	
	@ApiModelProperty(value="产品系列")
	protected String prodCatCode; 
	
	@ApiModelProperty(value="产品系列名称")
	protected String prodCatName; 
	
	@ApiModelProperty(value="品规")
	protected String prodCode; 
	
	@ApiModelProperty(value="品规名称")
	protected String prodName; 
	
	@ApiModelProperty(value="商业毛利率（%）")
	protected Double commerceProfitRate; 
	
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
	
	@ApiModelProperty(value="上年商业毛利率(%)")
	protected Double commercePreProfitRate; 
	
	@ApiModelProperty(value="备注")
	protected String remarks; 
	
	
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
	
	public void setBusinessDivisionCode(String businessDivisionCode) {
		this.businessDivisionCode = businessDivisionCode;
	}
	
	/**
	 * 返回 商务分区
	 * @return
	 */
	public String getBusinessDivisionCode() {
		return this.businessDivisionCode;
	}
	
	public void setBusinessDivisionName(String businessDivisionName) {
		this.businessDivisionName = businessDivisionName;
	}
	
	/**
	 * 返回 商务分区
	 * @return
	 */
	public String getBusinessDivisionName() {
		return this.businessDivisionName;
	}
	
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	
	/**
	 * 返回 省区
	 * @return
	 */
	public String getProvinceCode() {
		return this.provinceCode;
	}
	
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
	/**
	 * 返回 省区
	 * @return
	 */
	public String getProvinceName() {
		return this.provinceName;
	}
	
	public void setProdCatCode(String prodCatCode) {
		this.prodCatCode = prodCatCode;
	}
	
	/**
	 * 返回 产品系列
	 * @return
	 */
	public String getProdCatCode() {
		return this.prodCatCode;
	}
	
	public void setProdCatName(String prodCatName) {
		this.prodCatName = prodCatName;
	}
	
	/**
	 * 返回 产品系列名称
	 * @return
	 */
	public String getProdCatName() {
		return this.prodCatName;
	}
	
	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}
	
	/**
	 * 返回 品规
	 * @return
	 */
	public String getProdCode() {
		return this.prodCode;
	}
	
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	
	/**
	 * 返回 品规名称
	 * @return
	 */
	public String getProdName() {
		return this.prodName;
	}
	
	public void setCommerceProfitRate(Double commerceProfitRate) {
		this.commerceProfitRate = commerceProfitRate;
	}
	
	/**
	 * 返回 商业毛利率（%）
	 * @return
	 */
	public Double getCommerceProfitRate() {
		return this.commerceProfitRate;
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
	
	public void setCommercePreProfitRate(Double commercePreProfitRate) {
		this.commercePreProfitRate = commercePreProfitRate;
	}
	
	/**
	 * 返回 上年商业毛利率(%)
	 * @return
	 */
	public Double getCommercePreProfitRate() {
		return this.commercePreProfitRate;
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
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("year", this.year) 
		.append("businessDivisionCode", this.businessDivisionCode) 
		.append("businessDivisionName", this.businessDivisionName) 
		.append("provinceCode", this.provinceCode) 
		.append("provinceName", this.provinceName) 
		.append("prodCatCode", this.prodCatCode) 
		.append("prodCatName", this.prodCatName) 
		.append("prodCode", this.prodCode) 
		.append("prodName", this.prodName) 
		.append("commerceProfitRate", this.commerceProfitRate) 
		.append("createBy", this.createBy) 
		.append("createTime", this.createTime) 
		.append("createName", this.createName) 
		.append("updateBy", this.updateBy) 
		.append("updateTime", this.updateTime) 
		.append("updateName", this.updateName) 
		.append("createOrgId", this.createOrgId) 
		.append("createOrgName", this.createOrgName) 
		.append("COMPANY", this.COMPANY) 
		.append("commercePreProfitRate", this.commercePreProfitRate) 
		.append("remarks", this.remarks) 
		.toString();
	}
}