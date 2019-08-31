package com.winway.scm.model;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 商业年费信息管理费主表
 * <pre> 
 * 描述：商业年费信息管理费主表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-28 09:38:34
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmCwAnnualFeeMaster",description = "商业年费信息管理费主表") 
public class ScmCwAnnualFeeMaster extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="商务分区")
	protected String businessDivision; 
	
	@ApiModelProperty(value="商务分区ID")
	protected String businessDivisionId; 
	
	@ApiModelProperty(value="省区")
	protected String province; 
	
	@ApiModelProperty(value="省区id")
	protected String provinceId; 
	
	@ApiModelProperty(value="货主ID")
	protected String ownerId; 
	
	@ApiModelProperty(value="申请年度")
	protected String applyYear; 
	
	@ApiModelProperty(value="创建人")
	protected String createpersion; 
	
	@ApiModelProperty(value="创建时间")
	protected java.util.Date createDate; 
	
	@ApiModelProperty(value="审批ID")
	protected String approvalId; 
	
	@ApiModelProperty(value="审批状态")
	protected String approvalState; 
	
	/**
	*年费信息管理费详情列表
	*/
	protected List<ScmCwAnnualFee> scmCwAnnualFeeList=new ArrayList<ScmCwAnnualFee>(); 
	
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
	
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	
	/**
	 * 返回 省区id
	 * @return
	 */
	public String getProvinceId() {
		return this.provinceId;
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
	
	public void setApplyYear(String applyYear) {
		this.applyYear = applyYear;
	}
	
	/**
	 * 返回 申请年度
	 * @return
	 */
	public String getApplyYear() {
		return this.applyYear;
	}
	
	public void setCreatepersion(String createpersion) {
		this.createpersion = createpersion;
	}
	
	/**
	 * 返回 创建人
	 * @return
	 */
	public String getCreatepersion() {
		return this.createpersion;
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

	public void setScmCwAnnualFeeList(List<ScmCwAnnualFee> scmCwAnnualFeeList) {
		this.scmCwAnnualFeeList = scmCwAnnualFeeList;
	}
	
	/**
	 * 返回 年费信息管理费详情列表
	 * @return
	 */
	public List<ScmCwAnnualFee> getScmCwAnnualFeeList() {
		return this.scmCwAnnualFeeList;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("businessDivision", this.businessDivision) 
		.append("businessDivisionId", this.businessDivisionId) 
		.append("province", this.province) 
		.append("provinceId", this.provinceId) 
		.append("ownerId", this.ownerId) 
		.append("applyYear", this.applyYear) 
		.append("createpersion", this.createpersion) 
		.append("createDate", this.createDate) 
		.append("approvalId", this.approvalId) 
		.append("approvalState", this.approvalState) 
		.toString();
	}
}