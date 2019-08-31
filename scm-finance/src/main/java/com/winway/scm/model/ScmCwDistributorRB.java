package com.winway.scm.model;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;


 /**
 * 分销商RB申请主表
 * <pre> 
 * 描述：分销商RB申请主表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-14 16:38:31
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmCwDistributorRB",description = "分销商RB申请主表") 
public class ScmCwDistributorRB extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="商务分区")
	protected String businessDivision; 
	
	@ApiModelProperty(value="省区")
	protected String province; 
	
	@ApiModelProperty(value="商务分区ID")
	protected String businessDivisionId; 
	
	@ApiModelProperty(value="省区ID")
	protected String provinceId; 
	
	@ApiModelProperty(value="年份")
	protected String year; 
	
	@ApiModelProperty(value="货主ID")
	protected String ownerId; 
	
	@ApiModelProperty(value="创建人")
	protected String createPsersion; 
	
	@ApiModelProperty(value="创建时间")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date createDate; 
	
	@ApiModelProperty(value="审批状态")
	protected String approvalState; 
	
	@ApiModelProperty(value="审批ID")
	protected String approvalId; 
	
	/**
	*分销商流向管理费列表
	*/
	protected List<ScmCwDistributorFlowCost> scmCwDistributorFlowCostList=new ArrayList<ScmCwDistributorFlowCost>(); 
	/**
	*分销商返利明细列表
	*/
	protected List<ScmCwDistributorRebate> scmCwDistributorRebateList=new ArrayList<ScmCwDistributorRebate>(); 
	/**
	*分销商RB汇总列表
	*/
	protected List<ScmCwDistributorRbCollect> scmCwDistributorRbCollectList=new ArrayList<ScmCwDistributorRbCollect>(); 
	
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
	
	public void setBusinessDivision(String businessDivision) {
		this.businessDivision = businessDivision;
	}
	
	/**
	 * 返回 商务分区
	 * @return
	 */
	public String getBusinessDivision() {
		return this.businessDivision;
	}
	
	public void setProvince(String province) {
		this.province = province;
	}
	
	/**
	 * 返回 省区
	 * @return
	 */
	public String getProvince() {
		return this.province;
	}
	
	public void setBusinessDivisionId(String businessDivisionId) {
		this.businessDivisionId = businessDivisionId;
	}
	
	/**
	 * 返回 商务分区ID
	 * @return
	 */
	public String getBusinessDivisionId() {
		return this.businessDivisionId;
	}
	
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	
	/**
	 * 返回 省区ID
	 * @return
	 */
	public String getProvinceId() {
		return this.provinceId;
	}
	
	public void setYear(String year) {
		this.year = year;
	}
	
	/**
	 * 返回 年份
	 * @return
	 */
	public String getYear() {
		return this.year;
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
	
	public void setCreatePsersion(String createPsersion) {
		this.createPsersion = createPsersion;
	}
	
	/**
	 * 返回 创建人
	 * @return
	 */
	public String getCreatePsersion() {
		return this.createPsersion;
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

	public void setScmCwDistributorFlowCostList(List<ScmCwDistributorFlowCost> scmCwDistributorFlowCostList) {
		this.scmCwDistributorFlowCostList = scmCwDistributorFlowCostList;
	}
	
	/**
	 * 返回 分销商流向管理费列表
	 * @return
	 */
	public List<ScmCwDistributorFlowCost> getScmCwDistributorFlowCostList() {
		return this.scmCwDistributorFlowCostList;
	}

	public void setScmCwDistributorRebateList(List<ScmCwDistributorRebate> scmCwDistributorRebateList) {
		this.scmCwDistributorRebateList = scmCwDistributorRebateList;
	}
	
	/**
	 * 返回 分销商返利明细列表
	 * @return
	 */
	public List<ScmCwDistributorRebate> getScmCwDistributorRebateList() {
		return this.scmCwDistributorRebateList;
	}

	public void setScmCwDistributorRbCollectList(List<ScmCwDistributorRbCollect> scmCwDistributorRbCollectList) {
		this.scmCwDistributorRbCollectList = scmCwDistributorRbCollectList;
	}
	
	/**
	 * 返回 分销商RB汇总列表
	 * @return
	 */
	public List<ScmCwDistributorRbCollect> getScmCwDistributorRbCollectList() {
		return this.scmCwDistributorRbCollectList;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("businessDivision", this.businessDivision) 
		.append("province", this.province) 
		.append("businessDivisionId", this.businessDivisionId) 
		.append("provinceId", this.provinceId) 
		.append("year", this.year) 
		.append("ownerId", this.ownerId) 
		.append("createPsersion", this.createPsersion) 
		.append("createDate", this.createDate) 
		.append("approvalState", this.approvalState) 
		.append("approvalId", this.approvalId) 
		.toString();
	}
}