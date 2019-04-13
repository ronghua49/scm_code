package com.winway.scm.model;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 付款总表
 * <pre> 
 * 描述：付款总表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-23 18:19:25
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmCwPayment",description = "付款总表") 
public class ScmCwPayment extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="商业ID")
	protected String commerceId; 
	
	@ApiModelProperty(value="商品编号")
	protected String commerceCode; 
	
	@ApiModelProperty(value="商业名称")
	protected String commerceName; 
	
	@ApiModelProperty(value="开户行")
	protected String openBank; 
	
	@ApiModelProperty(value="账号")
	protected String bankCode; 
	
	@ApiModelProperty(value="税号")
	protected String dutyParagraph; 
	
	@ApiModelProperty(value="付款方式")
	protected String paymentType; 
	
	@ApiModelProperty(value="扣款金额")
	protected Double deductMoney; 
	
	@ApiModelProperty(value="付款金额")
	protected Double payment; 
	
	@ApiModelProperty(value="应付款金额")
	protected Double due; 
	
	@ApiModelProperty(value="申请付款时间")
	protected java.util.Date applyPaymentDate; 
	
	@ApiModelProperty(value="外币付款金额")
	protected Double foreignCurrency; 
	
	@ApiModelProperty(value="外币应付款金额")
	protected Double dueForeignCurrency; 
	
	@ApiModelProperty(value="采购员")
	protected String purchasingAgent; 
	
	@ApiModelProperty(value="备注")
	protected String memo; 
	
	@ApiModelProperty(value="货主ID")
	protected String ownerId; 
	
	@ApiModelProperty(value="审批ID")
	protected String approvalId;
	
	@ApiModelProperty(value="审批状态(0:未提交,1:审批中,2:已通过,3:被驳回)")
	protected String approvalState;
	
	/**
	*付款采购订单表列表
	*/
	protected List<ScmCwPaymentOrder> scmCwPaymentOrderList=new ArrayList<ScmCwPaymentOrder>(); 
	/**
	*付款采购订单补单表列表
	*/
	protected List<ScmCwPaymentRepOrder> scmCwPaymentRepOrderList=new ArrayList<ScmCwPaymentRepOrder>(); 
	
	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getApprovalId() {
		return approvalId;
	}


	public void setApprovalId(String approvalId) {
		this.approvalId = approvalId;
	}


	public String getApprovalState() {
		return approvalState;
	}


	public void setApprovalState(String approvalState) {
		this.approvalState = approvalState;
	}


	/**
	 * 返回 id
	 * @return
	 */
	public String getId() {
		return this.id;
	}
	
	public void setCommerceId(String commerceId) {
		this.commerceId = commerceId;
	}
	
	/**
	 * 返回 商业ID
	 * @return
	 */
	public String getCommerceId() {
		return this.commerceId;
	}
	
	public void setCommerceCode(String commerceCode) {
		this.commerceCode = commerceCode;
	}
	
	/**
	 * 返回 商品编号
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
	
	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}
	
	/**
	 * 返回 开户行
	 * @return
	 */
	public String getOpenBank() {
		return this.openBank;
	}
	
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	
	/**
	 * 返回 账号
	 * @return
	 */
	public String getBankCode() {
		return this.bankCode;
	}
	
	public void setDutyParagraph(String dutyParagraph) {
		this.dutyParagraph = dutyParagraph;
	}
	
	/**
	 * 返回 税号
	 * @return
	 */
	public String getDutyParagraph() {
		return this.dutyParagraph;
	}
	
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	
	/**
	 * 返回 付款方式
	 * @return
	 */
	public String getPaymentType() {
		return this.paymentType;
	}
	
	public void setDeductMoney(Double deductMoney) {
		this.deductMoney = deductMoney;
	}
	
	/**
	 * 返回 扣款金额
	 * @return
	 */
	public Double getDeductMoney() {
		return this.deductMoney;
	}
	
	public void setPayment(Double payment) {
		this.payment = payment;
	}
	
	/**
	 * 返回 付款金额
	 * @return
	 */
	public Double getPayment() {
		return this.payment;
	}
	
	public void setDue(Double due) {
		this.due = due;
	}
	
	/**
	 * 返回 应付款金额
	 * @return
	 */
	public Double getDue() {
		return this.due;
	}
	
	public void setApplyPaymentDate(java.util.Date applyPaymentDate) {
		this.applyPaymentDate = applyPaymentDate;
	}
	
	/**
	 * 返回 申请付款时间
	 * @return
	 */
	public java.util.Date getApplyPaymentDate() {
		return this.applyPaymentDate;
	}
	
	public void setForeignCurrency(Double foreignCurrency) {
		this.foreignCurrency = foreignCurrency;
	}
	
	/**
	 * 返回 外币付款金额
	 * @return
	 */
	public Double getForeignCurrency() {
		return this.foreignCurrency;
	}
	
	public void setDueForeignCurrency(Double dueForeignCurrency) {
		this.dueForeignCurrency = dueForeignCurrency;
	}
	
	/**
	 * 返回 外币应付款金额
	 * @return
	 */
	public Double getDueForeignCurrency() {
		return this.dueForeignCurrency;
	}
	
	public void setPurchasingAgent(String purchasingAgent) {
		this.purchasingAgent = purchasingAgent;
	}
	
	/**
	 * 返回 采购员
	 * @return
	 */
	public String getPurchasingAgent() {
		return this.purchasingAgent;
	}
	
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	/**
	 * 返回 备注
	 * @return
	 */
	public String getMemo() {
		return this.memo;
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

	public void setScmCwPaymentOrderList(List<ScmCwPaymentOrder> scmCwPaymentOrderList) {
		this.scmCwPaymentOrderList = scmCwPaymentOrderList;
	}
	
	/**
	 * 返回 付款采购订单表列表
	 * @return
	 */
	public List<ScmCwPaymentOrder> getScmCwPaymentOrderList() {
		return this.scmCwPaymentOrderList;
	}

	public void setScmCwPaymentRepOrderList(List<ScmCwPaymentRepOrder> scmCwPaymentRepOrderList) {
		this.scmCwPaymentRepOrderList = scmCwPaymentRepOrderList;
	}
	
	/**
	 * 返回 付款采购订单补单表列表
	 * @return
	 */
	public List<ScmCwPaymentRepOrder> getScmCwPaymentRepOrderList() {
		return this.scmCwPaymentRepOrderList;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("commerceId", this.commerceId) 
		.append("commerceCode", this.commerceCode) 
		.append("commerceName", this.commerceName) 
		.append("openBank", this.openBank) 
		.append("bankCode", this.bankCode) 
		.append("dutyParagraph", this.dutyParagraph) 
		.append("paymentType", this.paymentType) 
		.append("deductMoney", this.deductMoney) 
		.append("payment", this.payment) 
		.append("due", this.due) 
		.append("applyPaymentDate", this.applyPaymentDate) 
		.append("foreignCurrency", this.foreignCurrency) 
		.append("dueForeignCurrency", this.dueForeignCurrency) 
		.append("purchasingAgent", this.purchasingAgent) 
		.append("memo", this.memo) 
		.append("ownerId", this.ownerId) 
		.toString();
	}
}