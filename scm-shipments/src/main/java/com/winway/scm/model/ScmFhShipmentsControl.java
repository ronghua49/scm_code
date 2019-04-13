package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 发货控制
 * <pre> 
 * 描述：发货控制 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-02-15 17:31:50
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmFhShipmentsControl",description = "发货控制") 
public class ScmFhShipmentsControl extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="省区")
	protected String province; 
	
	@ApiModelProperty(value="省区id")
	protected String provinceId; 
	
	@ApiModelProperty(value="商务大区")
	protected String businessDivision; 
	
	@ApiModelProperty(value="商务大区id")
	protected String businessDivisionId; 
	
	@ApiModelProperty(value="经销商")
	protected String commerce; 
	
	@ApiModelProperty(value="经销商ID")
	protected String commerceId; 
	
	@ApiModelProperty(value="产品系列ID")
	protected String ProductLineId; 
	
	@ApiModelProperty(value="产品系列")
	protected String ProductLineName; 
	
	@ApiModelProperty(value="药品编号")
	protected String productNum; 
	
	@ApiModelProperty(value="药品品规")
	protected String productState; 
	
	@ApiModelProperty(value="禁止发货药品名称")
	protected String productName; 
	
	@ApiModelProperty(value="添加时间")
	protected java.util.Date createDate; 
	
	@ApiModelProperty(value="添加人")
	protected String createPersion; 
	
	@ApiModelProperty(value="货主ID")
	protected String ownerId; 
	
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
	
	public void setProvince(String province) {
		this.province = province;
	}
	
	/**
	 * 返回 省区
	 * @return
	 */
	public String getProvince() {
		return this.province;
	}
	
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	
	/**
	 * 返回 省区id
	 * @return
	 */
	public String getProvinceId() {
		return this.provinceId;
	}
	
	public void setBusinessDivision(String businessDivision) {
		this.businessDivision = businessDivision;
	}
	
	/**
	 * 返回 商务大区
	 * @return
	 */
	public String getBusinessDivision() {
		return this.businessDivision;
	}
	
	public void setBusinessDivisionId(String businessDivisionId) {
		this.businessDivisionId = businessDivisionId;
	}
	
	/**
	 * 返回 商务大区id
	 * @return
	 */
	public String getBusinessDivisionId() {
		return this.businessDivisionId;
	}
	
	public void setCommerce(String commerce) {
		this.commerce = commerce;
	}
	
	/**
	 * 返回 经销商
	 * @return
	 */
	public String getCommerce() {
		return this.commerce;
	}
	
	public void setCommerceId(String commerceId) {
		this.commerceId = commerceId;
	}
	
	/**
	 * 返回 经销商ID
	 * @return
	 */
	public String getCommerceId() {
		return this.commerceId;
	}
	
	public void setProductLineId(String ProductLineId) {
		this.ProductLineId = ProductLineId;
	}
	
	/**
	 * 返回 产品系列ID
	 * @return
	 */
	public String getProductLineId() {
		return this.ProductLineId;
	}
	
	public void setProductLineName(String ProductLineName) {
		this.ProductLineName = ProductLineName;
	}
	
	/**
	 * 返回 产品系列
	 * @return
	 */
	public String getProductLineName() {
		return this.ProductLineName;
	}
	
	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}
	
	/**
	 * 返回 药品编号
	 * @return
	 */
	public String getProductNum() {
		return this.productNum;
	}
	
	public void setProductState(String productState) {
		this.productState = productState;
	}
	
	/**
	 * 返回 药品品规
	 * @return
	 */
	public String getProductState() {
		return this.productState;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	/**
	 * 返回 禁止发货药品名称
	 * @return
	 */
	public String getProductName() {
		return this.productName;
	}
	
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 返回 添加时间
	 * @return
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	public void setCreatePersion(String createPersion) {
		this.createPersion = createPersion;
	}
	
	/**
	 * 返回 添加人
	 * @return
	 */
	public String getCreatePersion() {
		return this.createPersion;
	}
	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("province", this.province) 
		.append("provinceId", this.provinceId) 
		.append("businessDivision", this.businessDivision) 
		.append("businessDivisionId", this.businessDivisionId) 
		.append("commerce", this.commerce) 
		.append("commerceId", this.commerceId) 
		.append("ProductLineId", this.ProductLineId) 
		.append("ProductLineName", this.ProductLineName) 
		.append("productNum", this.productNum) 
		.append("productState", this.productState) 
		.append("productName", this.productName) 
		.append("createDate", this.createDate) 
		.append("createPersion", this.createPersion) 
		.toString();
	}
}