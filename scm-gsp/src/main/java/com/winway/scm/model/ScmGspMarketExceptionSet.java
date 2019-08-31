package com.winway.scm.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 销售异动商品设置表
 * <pre> 
 * 描述：销售异动商品设置表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-24 12:00:21
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmGspMarketExceptionSet",description = "销售异动商品设置表") 
public class ScmGspMarketExceptionSet extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="商品编码",required = true)
	protected String productCode; 
	
	@ApiModelProperty(value="商品系列",required = true)
	protected String productLine; 
	
	@ApiModelProperty(value="商品名",required = true)
	protected String productName; 
	
	@ApiModelProperty(value="规格",required = true)
	protected String productState; 
	
	@ApiModelProperty(value="销售预警数量",required = true)
	protected Integer warningNum; 
	
	@ApiModelProperty(value="创建时间")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date createDate; 
	
	@ApiModelProperty(value="创建人")
	protected String createPersion; 
	
	@ApiModelProperty(value="货主ID",required = true)
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
	
	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}
	
	/**
	 * 返回 商品系列
	 * @return
	 */
	public String getProductLine() {
		return this.productLine;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	/**
	 * 返回 商品名
	 * @return
	 */
	public String getProductName() {
		return this.productName;
	}
	
	public void setProductState(String productState) {
		this.productState = productState;
	}
	
	/**
	 * 返回 规格
	 * @return
	 */
	public String getProductState() {
		return this.productState;
	}
	
	public void setWarningNum(Integer warningNum) {
		this.warningNum = warningNum;
	}
	
	/**
	 * 返回 销售预警数量
	 * @return
	 */
	public Integer getWarningNum() {
		return this.warningNum;
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
	
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	
	/**
	 * 返回 货主ID
	 * @return
	 */
	public String getOwnerId() {
		return this.ownerId;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("productCode", this.productCode) 
		.append("productLine", this.productLine) 
		.append("productName", this.productName) 
		.append("productState", this.productState) 
		.append("warningNum", this.warningNum) 
		.append("createDate", this.createDate) 
		.append("createPersion", this.createPersion) 
		.append("ownerId", this.ownerId) 
		.toString();
	}
}