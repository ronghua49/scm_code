package com.winway.scm.model;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;


 /**
 * 采购退货表
 * <pre> 
 * 描述：采购退货表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-26 13:47:44
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
 @ApiModel(value = "ScmCgProcurementReturn",description = "采购退货表") 
public class ScmCgProcurementReturn extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="退货单号")
	protected String returnCode; 
	
	@ApiModelProperty(value="供应商id")
	protected String supplierId; 
	
	@ApiModelProperty(value="供应商")
	protected String supplier; 
	
	@ApiModelProperty(value="仓库ID")
	protected String getMoneyWarehouseId; 
	
	@ApiModelProperty(value="仓库")
	protected String getMoneyWarehouse; 
	
	@ApiModelProperty(value="品种数")
	protected Integer speciesSum; 
	
	@ApiModelProperty(value="退货总金额")
	protected Double sumPrice; 
	
	@ApiModelProperty(value="退货日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date returnDate; 
	
	@ApiModelProperty(value="审批ID")
	protected String approvalId; 
	
	@ApiModelProperty(value="审批状态")
	protected char approvalState; 
	
	@ApiModelProperty(value="货主ID")
	protected String ownerId; 
	
	@ApiModelProperty(value="操作人ID")
	protected String operatorId; 
	
	@ApiModelProperty(value="操作人")
	protected String operatorName; 
	
	@ApiModelProperty(value="采购订单ID")
	protected String procurementId; 
	
	@ApiModelProperty(value="采购订单号")
	protected String procurementCode; 
	
	/**
	*采购退货商品表列表
	*/
	protected List<ScmCgReturnProduct> scmCgReturnProductList=new ArrayList<ScmCgReturnProduct>(); 
	
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
	
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	
	/**
	 * 返回 退货单号
	 * @return
	 */
	public String getReturnCode() {
		return this.returnCode;
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
	
	public void setGetMoneyWarehouseId(String getMoneyWarehouseId) {
		this.getMoneyWarehouseId = getMoneyWarehouseId;
	}
	
	/**
	 * 返回 仓库ID
	 * @return
	 */
	public String getGetMoneyWarehouseId() {
		return this.getMoneyWarehouseId;
	}
	
	public void setGetMoneyWarehouse(String getMoneyWarehouse) {
		this.getMoneyWarehouse = getMoneyWarehouse;
	}
	
	/**
	 * 返回 仓库
	 * @return
	 */
	public String getGetMoneyWarehouse() {
		return this.getMoneyWarehouse;
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
	
	public void setSumPrice(Double sumPrice) {
		this.sumPrice = sumPrice;
	}
	
	/**
	 * 返回 退货总金额
	 * @return
	 */
	public Double getSumPrice() {
		return this.sumPrice;
	}
	
	public void setReturnDate(java.util.Date returnDate) {
		this.returnDate = returnDate;
	}
	
	/**
	 * 返回 退货日期
	 * @return
	 */
	public java.util.Date getReturnDate() {
		return this.returnDate;
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
	
	public void setApprovalState(char approvalState) {
		this.approvalState = approvalState;
	}
	
	/**
	 * 返回 审批状态
	 * @return
	 */
	public char getApprovalState() {
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
	
	public void setProcurementId(String procurementId) {
		this.procurementId = procurementId;
	}
	
	/**
	 * 返回 采购订单ID
	 * @return
	 */
	public String getProcurementId() {
		return this.procurementId;
	}
	
	public void setProcurementCode(String procurementCode) {
		this.procurementCode = procurementCode;
	}
	
	/**
	 * 返回 采购订单号
	 * @return
	 */
	public String getProcurementCode() {
		return this.procurementCode;
	}

	public void setScmCgReturnProductList(List<ScmCgReturnProduct> scmCgReturnProductList) {
		this.scmCgReturnProductList = scmCgReturnProductList;
	}
	
	/**
	 * 返回 采购退货商品表列表
	 * @return
	 */
	public List<ScmCgReturnProduct> getScmCgReturnProductList() {
		return this.scmCgReturnProductList;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("returnCode", this.returnCode) 
		.append("supplierId", this.supplierId) 
		.append("supplier", this.supplier) 
		.append("getMoneyWarehouseId", this.getMoneyWarehouseId) 
		.append("getMoneyWarehouse", this.getMoneyWarehouse) 
		.append("speciesSum", this.speciesSum) 
		.append("sumPrice", this.sumPrice) 
		.append("returnDate", this.returnDate) 
		.append("approvalId", this.approvalId) 
		.append("approvalState", this.approvalState) 
		.append("ownerId", this.ownerId) 
		.append("operatorId", this.operatorId) 
		.append("operatorName", this.operatorName) 
		.append("procurementId", this.procurementId) 
		.append("procurementCode", this.procurementCode) 
		.toString();
	}
}