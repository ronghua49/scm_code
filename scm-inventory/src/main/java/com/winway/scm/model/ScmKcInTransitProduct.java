package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 在途商品
 * <pre> 
 * 描述：在途商品 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-08-17 14:17:53
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmKcInTransitProduct",description = "在途商品") 
public class ScmKcInTransitProduct extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="任务ID")
	protected String taskId; 
	
	@ApiModelProperty(value="离港日期")
	protected java.util.Date etd; 
	
	@ApiModelProperty(value="到港日期")
	protected java.util.Date eta; 
	
	@ApiModelProperty(value="清关公司")
	protected String customsLiquidationCompany; 
	
	@ApiModelProperty(value="物料编码")
	protected String productNum; 
	
	@ApiModelProperty(value="产品名称")
	protected String productName; 
	
	@ApiModelProperty(value="规格")
	protected String productState; 
	
	@ApiModelProperty(value="批号")
	protected String batchNumber; 
	
	@ApiModelProperty(value="出货总件数")
	protected Double packageSum; 
	
	@ApiModelProperty(value="出货成品数")
	protected Double finishedSum; 
	
	@ApiModelProperty(value="柜数")
	protected Double arkNumber; 
	
	@ApiModelProperty(value="精神药物进口许可证")
	protected String productLicence; 
	
	@ApiModelProperty(value="导入时间")
	protected java.util.Date createDate; 
	
	@ApiModelProperty(value="导入人")
	protected String createPersion; 
	
	@ApiModelProperty(value="是否删除(0:未删除,1:已删除)")
	protected String isDelete; 
	
	
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
	
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	/**
	 * 返回 任务ID
	 * @return
	 */
	public String getTaskId() {
		return this.taskId;
	}
	
	public void setEtd(java.util.Date etd) {
		this.etd = etd;
	}
	
	/**
	 * 返回 离港日期
	 * @return
	 */
	public java.util.Date getEtd() {
		return this.etd;
	}
	
	public void setEta(java.util.Date eta) {
		this.eta = eta;
	}
	
	/**
	 * 返回 到港日期
	 * @return
	 */
	public java.util.Date getEta() {
		return this.eta;
	}
	
	public void setCustomsLiquidationCompany(String customsLiquidationCompany) {
		this.customsLiquidationCompany = customsLiquidationCompany;
	}
	
	/**
	 * 返回 清关公司
	 * @return
	 */
	public String getCustomsLiquidationCompany() {
		return this.customsLiquidationCompany;
	}
	
	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}
	
	/**
	 * 返回 物料编码
	 * @return
	 */
	public String getProductNum() {
		return this.productNum;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	/**
	 * 返回 产品名称
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
	
	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}
	
	/**
	 * 返回 批号
	 * @return
	 */
	public String getBatchNumber() {
		return this.batchNumber;
	}
	
	public void setPackageSum(Double packageSum) {
		this.packageSum = packageSum;
	}
	
	/**
	 * 返回 出货总件数
	 * @return
	 */
	public Double getPackageSum() {
		return this.packageSum;
	}
	
	public void setFinishedSum(Double finishedSum) {
		this.finishedSum = finishedSum;
	}
	
	/**
	 * 返回 出货成品数
	 * @return
	 */
	public Double getFinishedSum() {
		return this.finishedSum;
	}
	
	public void setArkNumber(Double arkNumber) {
		this.arkNumber = arkNumber;
	}
	
	/**
	 * 返回 柜数
	 * @return
	 */
	public Double getArkNumber() {
		return this.arkNumber;
	}
	
	public void setProductLicence(String productLicence) {
		this.productLicence = productLicence;
	}
	
	/**
	 * 返回 精神药物进口许可证
	 * @return
	 */
	public String getProductLicence() {
		return this.productLicence;
	}
	
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 返回 导入时间
	 * @return
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	public void setCreatePersion(String createPersion) {
		this.createPersion = createPersion;
	}
	
	/**
	 * 返回 导入人
	 * @return
	 */
	public String getCreatePersion() {
		return this.createPersion;
	}
	
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	
	/**
	 * 返回 是否删除(0:未删除,1:已删除)
	 * @return
	 */
	public String getIsDelete() {
		return this.isDelete;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("taskId", this.taskId) 
		.append("etd", this.etd) 
		.append("eta", this.eta) 
		.append("customsLiquidationCompany", this.customsLiquidationCompany) 
		.append("productNum", this.productNum) 
		.append("productName", this.productName) 
		.append("productState", this.productState) 
		.append("batchNumber", this.batchNumber) 
		.append("packageSum", this.packageSum) 
		.append("finishedSum", this.finishedSum) 
		.append("arkNumber", this.arkNumber) 
		.append("productLicence", this.productLicence) 
		.append("createDate", this.createDate) 
		.append("createPersion", this.createPersion) 
		.append("isDelete", this.isDelete) 
		.toString();
	}
}