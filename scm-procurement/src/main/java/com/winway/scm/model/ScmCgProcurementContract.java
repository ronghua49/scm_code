package com.winway.scm.model;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;


 /**
 * SCM_CG_ProcurementContract
 * <pre> 
 * 描述：SCM_CG_ProcurementContract 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-26 13:47:44
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
 @ApiModel(value = "ScmCgProcurementContract",description = "SCM_CG_ProcurementContract") 
public class ScmCgProcurementContract extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="供应商id")
	protected String supplierId; 
	
	@ApiModelProperty(value="供应商")
	protected String supplier; 
	
	@ApiModelProperty(value="供货产品种类数")
	protected Integer speciesSum; 
	
	@ApiModelProperty(value="产品总数量")
	protected Integer productSum; 
	
	@ApiModelProperty(value="产品总金额")
	protected Double sumPrice; 
	
	@ApiModelProperty(value="币种")
	protected String currency; 
	
	@ApiModelProperty(value="付款方式")
	protected String PayType; 
	
	@ApiModelProperty(value="申请日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date filingDate; 
	
	@ApiModelProperty(value="通过日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date successDate; 
	
	@ApiModelProperty(value="审批表ID")
	protected String approvalId; 
	
	@ApiModelProperty(value="审批状态")
	protected String approvalState; 
	
	@ApiModelProperty(value="操作人ID")
	protected String operatorId; 
	
	@ApiModelProperty(value="操作人")
	protected String operatorName; 
	
	@ApiModelProperty(value="货主ID")
	protected String ownerId; 
	
	@ApiModelProperty(value="合同编号")
	protected String contractCode; 
	
	/**
	*采购合同商品表列表
	*/
	protected List<ScmCgContractProduct> scmCgContractProductList=new ArrayList<ScmCgContractProduct>(); 
	
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
	
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	
	/**
	 * 返回 供应商id
	 * @return
	 */
	public String getSupplierId() {
		return this.supplierId;
	}
	
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	
	/**
	 * 返回 供应商
	 * @return
	 */
	public String getSupplier() {
		return this.supplier;
	}
	
	public void setSpeciesSum(Integer speciesSum) {
		this.speciesSum = speciesSum;
	}
	
	/**
	 * 返回 供货产品种类数
	 * @return
	 */
	public Integer getSpeciesSum() {
		return this.speciesSum;
	}
	
	public void setProductSum(Integer productSum) {
		this.productSum = productSum;
	}
	
	/**
	 * 返回 产品总数量
	 * @return
	 */
	public Integer getProductSum() {
		return this.productSum;
	}
	
	public void setSumPrice(Double sumPrice) {
		this.sumPrice = sumPrice;
	}
	
	/**
	 * 返回 产品总金额
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
	
	public void setPayType(String PayType) {
		this.PayType = PayType;
	}
	
	/**
	 * 返回 付款方式
	 * @return
	 */
	public String getPayType() {
		return this.PayType;
	}
	
	public void setFilingDate(java.util.Date filingDate) {
		this.filingDate = filingDate;
	}
	
	/**
	 * 返回 申请日期
	 * @return
	 */
	public java.util.Date getFilingDate() {
		return this.filingDate;
	}
	
	public void setSuccessDate(java.util.Date successDate) {
		this.successDate = successDate;
	}
	
	/**
	 * 返回 通过日期
	 * @return
	 */
	public java.util.Date getSuccessDate() {
		return this.successDate;
	}
	
	public void setApprovalId(String approvalId) {
		this.approvalId = approvalId;
	}
	
	/**
	 * 返回 审批表ID
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

	public void setScmCgContractProductList(List<ScmCgContractProduct> scmCgContractProductList) {
		this.scmCgContractProductList = scmCgContractProductList;
	}
	
	/**
	 * 返回 采购合同商品表列表
	 * @return
	 */
	public List<ScmCgContractProduct> getScmCgContractProductList() {
		return this.scmCgContractProductList;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("supplierId", this.supplierId) 
		.append("supplier", this.supplier) 
		.append("speciesSum", this.speciesSum) 
		.append("productSum", this.productSum) 
		.append("sumPrice", this.sumPrice) 
		.append("currency", this.currency) 
		.append("PayType", this.PayType) 
		.append("filingDate", this.filingDate) 
		.append("successDate", this.successDate) 
		.append("approvalId", this.approvalId) 
		.append("approvalState", this.approvalState) 
		.append("operatorId", this.operatorId) 
		.append("operatorName", this.operatorName) 
		.append("ownerId", this.ownerId) 
		.append("contractCode", this.contractCode)  
		.toString();
	}
}