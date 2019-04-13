package com.winway.scm.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;


 /**
 * 集中发货合同
 * <pre> 
 * 描述：集中发货合同 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:44
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmXsFocusSend",description = "集中发货合同") 
public class ScmXsFocusSend extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="集中发货编号")
	protected String focusSendCode; 
	
	@ApiModelProperty(value="特求意见")
	protected String specialOpinion; 
	
	@ApiModelProperty(value="货主ID")
	protected String ownerId; 
	
	@ApiModelProperty(value="协议ID")
	protected String dealerClauseId; 
	
	@ApiModelProperty(value="减免合同次数理由")
	protected String reduceContractTime; 
	
	@ApiModelProperty(value="审批ID")
	protected String approvalId; 
	
	@ApiModelProperty(value="审批状态")
	protected String approvalState; 
	
	@ApiModelProperty(value="供货商")
	protected String ownerName; 
	
	@ApiModelProperty(value="商业首营ID")
	protected String commerceFirstId; 
	
	@ApiModelProperty(value="商业名称")
	protected String commerceName; 
	
	@ApiModelProperty(value="商业编号")
	protected String commerceCode; 
	
	@ApiModelProperty(value="购销方式")
	protected String marketingWay; 
	
	@ApiModelProperty(value="签订时间")
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date dateOfSigning; 
	
	
	/**
	*集中发货商品表列表
	*/
	protected List<ScmXsFocusSendProduct> scmXsFocusSendProductList=new ArrayList<ScmXsFocusSendProduct>(); 
	
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
	
	
	public void setSpecialOpinion(String specialOpinion) {
		this.specialOpinion = specialOpinion;
	}
	
	/**
	 * 返回 特求意见
	 * @return
	 */
	public String getSpecialOpinion() {
		return this.specialOpinion;
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
	
	public String getFocusSendCode() {
		return focusSendCode;
	}

	public void setFocusSendCode(String focusSendCode) {
		this.focusSendCode = focusSendCode;
	}


	public String getDealerClauseId() {
		return dealerClauseId;
	}

	public void setDealerClauseId(String dealerClauseId) {
		this.dealerClauseId = dealerClauseId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setReduceContractTime(String reduceContractTime) {
		this.reduceContractTime = reduceContractTime;
	}
	
	/**
	 * 返回 减免合同次数理由
	 * @return
	 */
	public String getReduceContractTime() {
		return this.reduceContractTime;
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

	public void setScmXsFocusSendProductList(List<ScmXsFocusSendProduct> scmXsFocusSendProductList) {
		this.scmXsFocusSendProductList = scmXsFocusSendProductList;
	}
	
	/**
	 * 返回 集中发货商品表列表
	 * @return
	 */
	public List<ScmXsFocusSendProduct> getScmXsFocusSendProductList() {
		return this.scmXsFocusSendProductList;
	}
	
	
	
	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getCommerceFirstId() {
		return commerceFirstId;
	}

	public void setCommerceFirstId(String commerceFirstId) {
		this.commerceFirstId = commerceFirstId;
	}

	public String getCommerceName() {
		return commerceName;
	}

	public void setCommerceName(String commerceName) {
		this.commerceName = commerceName;
	}

	public String getCommerceCode() {
		return commerceCode;
	}

	public void setCommerceCode(String commerceCode) {
		this.commerceCode = commerceCode;
	}

	public String getMarketingWay() {
		return marketingWay;
	}

	public void setMarketingWay(String marketingWay) {
		this.marketingWay = marketingWay;
	}

	public Date getDateOfSigning() {
		return dateOfSigning;
	}

	public void setDateOfSigning(Date dateOfSigning) {
		this.dateOfSigning = dateOfSigning;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("FocusSendCode", this.focusSendCode) 
		.append("specialOpinion", this.specialOpinion) 
		.append("ownerId", this.ownerId) 
		.append("BigContractId", this.dealerClauseId) 
		.append("reduceContractTime", this.reduceContractTime) 
		.append("approvalId", this.approvalId) 
		.append("approvalState", this.approvalState) 
		.toString();
	}
}