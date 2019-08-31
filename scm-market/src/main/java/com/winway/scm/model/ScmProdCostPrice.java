package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 商品成本价管理
 * <pre> 
 * 描述：商品成本价管理 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-06-27 15:55:07
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmProdCostPrice",description = "商品成本价管理") 
public class ScmProdCostPrice extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="状态")
	protected Integer status; 
	
	@ApiModelProperty(value="年")
	protected Integer year; 
	
	@ApiModelProperty(value="品规编码")
	protected String prodCode; 
	
	@ApiModelProperty(value="ERP价格港币")
	protected Double erpPriceHk; 
	
	@ApiModelProperty(value="ERP价格人民币")
	protected Double erpPriceCn; 
	
	@ApiModelProperty(value="海关价格港币")
	protected Double customsPriceHk; 
	
	@ApiModelProperty(value="海关价格人民币")
	protected Double customsPriceCn; 
	
	@ApiModelProperty(value="成本价")
	protected Double costPrice; 
	
	@ApiModelProperty(value="生效日期")
	protected java.util.Date effectDate; 
	
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
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * 返回 状态
	 * @return
	 */
	public Integer getStatus() {
		return this.status;
	}
	
	public void setYear(Integer year) {
		this.year = year;
	}
	
	/**
	 * 返回 年
	 * @return
	 */
	public Integer getYear() {
		return this.year;
	}
	
	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}
	
	/**
	 * 返回 品规编码
	 * @return
	 */
	public String getProdCode() {
		return this.prodCode;
	}
	
	public void setErpPriceHk(Double erpPriceHk) {
		this.erpPriceHk = erpPriceHk;
	}
	
	/**
	 * 返回 ERP价格港币
	 * @return
	 */
	public Double getErpPriceHk() {
		return this.erpPriceHk;
	}
	
	public void setErpPriceCn(Double erpPriceCn) {
		this.erpPriceCn = erpPriceCn;
	}
	
	/**
	 * 返回 ERP价格人民币
	 * @return
	 */
	public Double getErpPriceCn() {
		return this.erpPriceCn;
	}
	
	public void setCustomsPriceHk(Double customsPriceHk) {
		this.customsPriceHk = customsPriceHk;
	}
	
	/**
	 * 返回 海关价格港币
	 * @return
	 */
	public Double getCustomsPriceHk() {
		return this.customsPriceHk;
	}
	
	public void setCustomsPriceCn(Double customsPriceCn) {
		this.customsPriceCn = customsPriceCn;
	}
	
	/**
	 * 返回 海关价格人民币
	 * @return
	 */
	public Double getCustomsPriceCn() {
		return this.customsPriceCn;
	}
	
	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}
	
	/**
	 * 返回 成本价
	 * @return
	 */
	public Double getCostPrice() {
		return this.costPrice;
	}
	
	public void setEffectDate(java.util.Date effectDate) {
		this.effectDate = effectDate;
	}
	
	/**
	 * 返回 生效日期
	 * @return
	 */
	public java.util.Date getEffectDate() {
		return this.effectDate;
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
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("status", this.status) 
		.append("year", this.year) 
		.append("prodCode", this.prodCode) 
		.append("erpPriceHk", this.erpPriceHk) 
		.append("erpPriceCn", this.erpPriceCn) 
		.append("customsPriceHk", this.customsPriceHk) 
		.append("customsPriceCn", this.customsPriceCn) 
		.append("costPrice", this.costPrice) 
		.append("effectDate", this.effectDate) 
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
		.toString();
	}
}