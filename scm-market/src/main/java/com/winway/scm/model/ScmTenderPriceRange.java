package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 中标价区间管理
 * <pre> 
 * 描述：中标价区间管理 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-06-25 11:09:05
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmTenderPriceRange",description = "中标价区间管理") 
public class ScmTenderPriceRange extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="year")
	protected Integer year; 
	
	@ApiModelProperty(value="商品归属 TX、BFHN")
	protected String prodOwner; 
	
	@ApiModelProperty(value="产品系列")
	protected String prodCatCode; 
	
	@ApiModelProperty(value="产品系列名称")
	protected String prodCatName; 
	
	@ApiModelProperty(value="品规")
	protected String prodCode; 
	
	@ApiModelProperty(value="品规名称")
	protected String prodName; 
	
	@ApiModelProperty(value="中标价上限")
	protected Double priceMax; 
	
	@ApiModelProperty(value="中标价下限")
	protected Double priceMin; 
	
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
	
	public void setProdOwner(String prodOwner) {
		this.prodOwner = prodOwner;
	}
	
	/**
	 * 返回 商品归属 TX、BFHN
	 * @return
	 */
	public String getProdOwner() {
		return this.prodOwner;
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
	
	public void setPriceMax(Double priceMax) {
		this.priceMax = priceMax;
	}
	
	/**
	 * 返回 中标价上限
	 * @return
	 */
	public Double getPriceMax() {
		return this.priceMax;
	}
	
	public void setPriceMin(Double priceMin) {
		this.priceMin = priceMin;
	}
	
	/**
	 * 返回 中标价下限
	 * @return
	 */
	public Double getPriceMin() {
		return this.priceMin;
	}
	
	
	/**
	 * 返回 创建人
	 * @return
	 */
	
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * 返回 创建时间
	 * @return
	 */
	
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
	
	
	/**
	 * 返回 修改人
	 * @return
	 */
	
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
	
	
	/**
	 * 返回 创建人所在组织
	 * @return
	 */
	
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
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("year", this.year) 
		.append("prodOwner", this.prodOwner) 
		.append("prodCatCode", this.prodCatCode) 
		.append("prodCatName", this.prodCatName) 
		.append("prodCode", this.prodCode) 
		.append("prodName", this.prodName) 
		.append("priceMax", this.priceMax) 
		.append("priceMin", this.priceMin) 
		.append("createBy", this.createBy) 
		.append("createTime", this.createTime) 
		.append("createName", this.createName) 
		.append("updateBy", this.updateBy) 
		.append("updateTime", this.updateTime) 
		.append("updateName", this.updateName) 
		.append("createOrgId", this.createOrgId) 
		.append("createOrgName", this.createOrgName) 
		.append("COMPANY", this.COMPANY) 
		.toString();
	}
}