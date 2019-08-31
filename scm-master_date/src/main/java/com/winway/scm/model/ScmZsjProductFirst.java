package com.winway.scm.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


 /**
 * 商品首营记录表
 * <pre> 
 * 描述：商品首营记录表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmZsjProductFirst",description = "商品首营记录表") 
public class ScmZsjProductFirst extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="商品ID")
	protected String productId; 
	
	@ApiModelProperty(value="货主ID")
	protected String ownerId; 
	
	@ApiModelProperty(value="审批ID")
	protected String approvalId; 
	
	@ApiModelProperty(value="审批状态")
	protected String approvalState; 
	
	@ApiModelProperty(value="供应商ID")
	protected String supplierId; 
	
	@ApiModelProperty(value="供应商")
	protected String supplier; 
	
	@ApiModelProperty(value="批准文号/注册证号")
	protected String approvalCode; 
	
	@ApiModelProperty(value="批准文号/注册证号有效期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected Date approvalValidity; 
	
	@ApiModelProperty(value="是否推送至WMS（0：否，1）")
	protected String isToWms; 
	
	@ApiModelProperty(value="GMP证书")
	protected String gmp; 
	
	@ApiModelProperty(value="GMP证书有效期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected Date gmpValidity; 
	
	@ApiModelProperty(value="启用批号")
	protected String startBatch; 
	
	@ApiModelProperty(value="质量标准")
	protected String qualityStandard;

	 public String getQualityStandard() {
		 return qualityStandard;
	 }

	 @ApiModelProperty(value="申请人 ")
	protected String operatorPerson;

	@ApiModelProperty(value="申请时间")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected Date operatorDate;

	@ApiModelProperty(value="商品对象")
	protected ScmZsjProduct scmZsjProduct;

	 @ApiModelProperty(value="档案编码")
	 protected String fileCode;

	 @ApiModelProperty(value="是否首映通过过（0否1是）")
	 protected String isPassed="0";
	 public String getIsPassed() {
		 return isPassed;
	 }

	 public void setIsPassed(String isPassed) {
		 this.isPassed = isPassed;
	 }


	 public String getFileCode() {
		 return fileCode;
	 }

	 public void setFileCode(String fileCode) {
		 this.fileCode = fileCode;
	 }

	 /**
	 * 附件
	 */
	protected List<ScmZsjAccessory> scmZsjAccessoryList = new ArrayList<ScmZsjAccessory>();
	
	public List<ScmZsjAccessory> getScmZsjAccessoryList() {
		return scmZsjAccessoryList;
	}

	public void setScmZsjAccessoryList(List<ScmZsjAccessory> scmZsjAccessoryList) {
		this.scmZsjAccessoryList = scmZsjAccessoryList;
	}

	public String getOperatorPerson() {
		 return operatorPerson;
	}

	 public void setOperatorPerson(String operatorPerson) {
		 this.operatorPerson = operatorPerson;
	 }

	 public Date getOperatorDate() {
		 return operatorDate;
	 }

	 public void setOperatorDate(Date operatorDate) {
		 this.operatorDate = operatorDate;
	 }

	 public ScmZsjProduct getScmZsjProduct() {
		return scmZsjProduct;
	}

	public void setScmZsjProduct(ScmZsjProduct scmZsjProduct) {
		this.scmZsjProduct = scmZsjProduct;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getIsToWms() {
		return isToWms;
	}

	public void setIsToWms(String isToWms) {
		this.isToWms = isToWms;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
	
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	
	/**
	 * 返回 供应商ID
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
	
	public void setApprovalCode(String approvalCode) {
		this.approvalCode = approvalCode;
	}
	
	/**
	 * 返回 批准文号/注册证号
	 * @return
	 */
	public String getApprovalCode() {
		return this.approvalCode;
	}
	
	public void setApprovalValidity(java.util.Date approvalValidity) {
		this.approvalValidity = approvalValidity;
	}
	
	/**
	 * 返回 批准文号/注册证号有效期
	 * @return
	 */
	public java.util.Date getApprovalValidity() {
		return this.approvalValidity;
	}

	public void setQualityStandard(String qualityStandard) {
		this.qualityStandard = qualityStandard;
	}
	

	

	public String getGmp() {
		return gmp;
	}

	public void setGmp(String gmp) {
		this.gmp = gmp;
	}

	public Date getGmpValidity() {
		return gmpValidity;
	}

	public void setGmpValidity(Date gmpValidity) {
		this.gmpValidity = gmpValidity;
	}

	public String getStartBatch() {
		return startBatch;
	}

	public void setStartBatch(String startBatch) {
		this.startBatch = startBatch;
	}


	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("productId", this.productId) 
		.append("ownerId", this.ownerId) 
		.append("approvalId", this.approvalId) 
		.append("approvalState", this.approvalState) 
		.append("supplierId", this.supplierId) 
		.append("supplier", this.supplier) 
		.append("approvalCode", this.approvalCode) 
		.append("approvalValidity", this.approvalValidity) 
		.append("gmp", this.gmp)
		.append("gmpValidity", this.gmpValidity) 
		.append("startBatch", this.startBatch) 
		.append("qualityStandard", this.qualityStandard)
		.toString();
	}
}