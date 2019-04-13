package com.winway.scm.model;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;


 /**
 * 采购订单补单表
 * <pre> 
 * 描述：采购订单补单表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-26 13:47:44
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
 @ApiModel(value = "ScmCgProReplacementOrder",description = "采购订单补单表") 
public class ScmCgProReplacementOrder extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="合同ID")
	protected String contractId; 
	
	@ApiModelProperty(value="采购ID")
	protected String orderId; 
	
	@ApiModelProperty(value="采购补单号")
	protected String ReplacementOrderCode; 
	
	@ApiModelProperty(value="品种数")
	protected Integer speciesSum; 
	
	@ApiModelProperty(value="数量")
	protected Integer productSum; 
	
	@ApiModelProperty(value="金额")
	protected Double sumPrice; 
	
	@ApiModelProperty(value="币种")
	protected String currency; 
	
	@ApiModelProperty(value="补单日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date orderDate; 
	
	@ApiModelProperty(value="是否推送至WMS")
	protected char isToWMS; 
	
	@ApiModelProperty(value="审批ID")
	protected String approvalId; 
	
	@ApiModelProperty(value="审批状态")
	protected String approvalState; 
	
	@ApiModelProperty(value="货主ID")
	protected String ownerId; 
	
	@ApiModelProperty(value="操作人ID")
	protected String operatorId; 
	
	@ApiModelProperty(value="操作人")
	protected String operatorName; 
	
	@ApiModelProperty(value="合同编号")
	protected String contractCode; 
	
	/**
	*采购补单商品表列表
	*/
	protected List<ScmCgProRepProduct> scmCgProRepProductList=new ArrayList<ScmCgProRepProduct>(); 
	
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
	
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	
	/**
	 * 返回 合同ID
	 * @return
	 */
	public String getContractId() {
		return this.contractId;
	}
	
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	/**
	 * 返回 采购ID
	 * @return
	 */
	public String getOrderId() {
		return this.orderId;
	}
	
	public void setReplacementOrderCode(String ReplacementOrderCode) {
		this.ReplacementOrderCode = ReplacementOrderCode;
	}
	
	/**
	 * 返回 采购补单号
	 * @return
	 */
	public String getReplacementOrderCode() {
		return this.ReplacementOrderCode;
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
	
	public void setSumPrice(Double sumPrice) {
		this.sumPrice = sumPrice;
	}
	
	/**
	 * 返回 金额
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
	
	public void setIsToWMS(char isToWMS) {
		this.isToWMS = isToWMS;
	}
	
	/**
	 * 返回 是否推送至WMS
	 * @return
	 */
	public char getIsToWMS() {
		return this.isToWMS;
	}
	
	public void setApprovalId(String approvalId) {
		this.approvalId = approvalId;
	}
	
	/**
	 * 返回 审批ID
	 * @return
	 */
	public String getApprovalId() {
		return this.approvalId;
	}
	
	public void setApprovalState(String approvalState) {
		this.approvalState = approvalState;
	}
	
	/**
	 * 返回 审批状态
	 * @return
	 */
	public String getApprovalState() {
		return this.approvalState;
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
	
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	
	/**
	 * 返回 操作人ID
	 * @return
	 */
	public String getOperatorId() {
		return this.operatorId;
	}
	
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	
	/**
	 * 返回 操作人
	 * @return
	 */
	public String getOperatorName() {
		return this.operatorName;
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
	
	public void setScmCgProRepProductList(List<ScmCgProRepProduct> scmCgProRepProductList) {
		this.scmCgProRepProductList = scmCgProRepProductList;
	}
	
	/**
	 * 返回 采购补单商品表列表
	 * @return
	 */
	public List<ScmCgProRepProduct> getScmCgProRepProductList() {
		return this.scmCgProRepProductList;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("contractId", this.contractId) 
		.append("orderId", this.orderId) 
		.append("ReplacementOrderCode", this.ReplacementOrderCode) 
		.append("speciesSum", this.speciesSum) 
		.append("productSum", this.productSum) 
		.append("sumPrice", this.sumPrice) 
		.append("currency", this.currency) 
		.append("orderDate", this.orderDate) 
		.append("isToWMS", this.isToWMS) 
		.append("approvalId", this.approvalId) 
		.append("approvalState", this.approvalState) 
		.append("ownerId", this.ownerId) 
		.append("operatorId", this.operatorId) 
		.append("operatorName", this.operatorName) 
		.append("contractCode", this.contractCode) 
		.toString();
	}
}