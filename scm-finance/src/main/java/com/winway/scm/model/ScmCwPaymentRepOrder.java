package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 付款采购订单补单表
 * <pre> 
 * 描述：付款采购订单补单表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-23 18:19:25
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmCwPaymentRepOrder",description = "付款采购订单补单表") 
public class ScmCwPaymentRepOrder extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="付款总表ID")
	protected String paymentId; 
	
	@ApiModelProperty(value="采购补单id")
	protected String replacementOrderId; 
	
	@ApiModelProperty(value="采购补单订单号")
	protected String replacementOrderCode; 
	
	@ApiModelProperty(value="品种数")
	protected Integer speciesSum; 
	
	@ApiModelProperty(value="数量")
	protected Integer productSum; 
	
	@ApiModelProperty(value="金额")
	protected Double price; 
	
	@ApiModelProperty(value="补单日期")
	protected java.util.Date orderDate; 
	
	@ApiModelProperty(value="采购合同单号")
	protected String contractCode; 
	
	
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
	
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	
	/**
	 * 返回 付款总表ID
	 * @return
	 */
	public String getPaymentId() {
		return this.paymentId;
	}
	
	public void setReplacementOrderId(String replacementOrderId) {
		this.replacementOrderId = replacementOrderId;
	}
	
	/**
	 * 返回 采购补单id
	 * @return
	 */
	public String getReplacementOrderId() {
		return this.replacementOrderId;
	}
	
	public void setReplacementOrderCode(String replacementOrderCode) {
		this.replacementOrderCode = replacementOrderCode;
	}
	
	/**
	 * 返回 采购补单订单号
	 * @return
	 */
	public String getReplacementOrderCode() {
		return this.replacementOrderCode;
	}
	
	public void setSpeciesSum(Integer speciesSum) {
		this.speciesSum = speciesSum;
	}
	
	/**
	 * 返回 品种数
	 * @return
	 */
	public Integer getSpeciesSum() {
		return this.speciesSum;
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
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	/**
	 * 返回 金额
	 * @return
	 */
	public Double getPrice() {
		return this.price;
	}
	
	public void setOrderDate(java.util.Date orderDate) {
		this.orderDate = orderDate;
	}
	
	/**
	 * 返回 补单日期
	 * @return
	 */
	public java.util.Date getOrderDate() {
		return this.orderDate;
	}
	
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	
	/**
	 * 返回 采购合同单号
	 * @return
	 */
	public String getContractCode() {
		return this.contractCode;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("paymentId", this.paymentId) 
		.append("replacementOrderId", this.replacementOrderId) 
		.append("replacementOrderCode", this.replacementOrderCode) 
		.append("speciesSum", this.speciesSum) 
		.append("productSum", this.productSum) 
		.append("price", this.price) 
		.append("orderDate", this.orderDate) 
		.append("contractCode", this.contractCode) 
		.toString();
	}
}