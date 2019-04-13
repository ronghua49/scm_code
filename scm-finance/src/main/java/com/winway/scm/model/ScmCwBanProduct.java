package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 禁止票折商品表
 * <pre> 
 * 描述：禁止票折商品表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-23 18:19:26
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmCwBanProduct",description = "禁止票折商品表") 
public class ScmCwBanProduct extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="商品ID")
	protected String productId; 
	
	@ApiModelProperty(value="商品编号")
	protected String productCode; 
	
	@ApiModelProperty(value="商品名称")
	protected String productName; 
	
	@ApiModelProperty(value="品规")
	protected String productState; 
	
	@ApiModelProperty(value="设置人")
	protected String setPersion; 
	
	@ApiModelProperty(value="设置时间")
	protected java.util.Date setTime; 
	
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
	
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	/**
	 * 返回 商品ID
	 * @return
	 */
	public String getProductId() {
		return this.productId;
	}
	
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	/**
	 * 返回 商品编号
	 * @return
	 */
	public String getProductCode() {
		return this.productCode;
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
	
	public void setProductState(String productState) {
		this.productState = productState;
	}
	
	/**
	 * 返回 品规
	 * @return
	 */
	public String getProductState() {
		return this.productState;
	}
	
	public void setSetPersion(String setPersion) {
		this.setPersion = setPersion;
	}
	
	/**
	 * 返回 设置人
	 * @return
	 */
	public String getSetPersion() {
		return this.setPersion;
	}
	
	public void setSetTime(java.util.Date setTime) {
		this.setTime = setTime;
	}
	
	/**
	 * 返回 设置时间
	 * @return
	 */
	public java.util.Date getSetTime() {
		return this.setTime;
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
		.append("productId", this.productId) 
		.append("productCode", this.productCode) 
		.append("productName", this.productName) 
		.append("productState", this.productState) 
		.append("setPersion", this.setPersion) 
		.append("setTime", this.setTime) 
		.append("ownerId", this.ownerId) 
		.toString();
	}
}