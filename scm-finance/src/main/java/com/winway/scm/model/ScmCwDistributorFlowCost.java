package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 分销商流向管理费
 * <pre> 
 * 描述：分销商流向管理费 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-14 16:38:31
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmCwDistributorFlowCost",description = "分销商流向管理费") 
public class ScmCwDistributorFlowCost extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="主表ID")
	protected String masterId; 
	
	@ApiModelProperty(value="商务分区")
	protected String businessDivision; 
	
	@ApiModelProperty(value="省区")
	protected String province; 
	
	@ApiModelProperty(value="商务分区ID")
	protected String businessDivisionId; 
	
	@ApiModelProperty(value="省区ID")
	protected String provinceId; 
	
	@ApiModelProperty(value="商业名称")
	protected String commerceName; 
	
	@ApiModelProperty(value="商业编号")
	protected String commerceCode; 
	
	@ApiModelProperty(value="商业ID")
	protected String commerceId; 
	
	@ApiModelProperty(value="认可状态")
	protected String acceptState; 
	
	@ApiModelProperty(value="签订类型")
	protected String signType; 
	
	@ApiModelProperty(value="流向管理评分")
	protected Integer flowManagementGrade; 
	
	@ApiModelProperty(value="系统计算")
	protected Double systemCount; 
	
	@ApiModelProperty(value="本次申请")
	protected Double thisTimeApply; 
	
	@ApiModelProperty(value="备注")
	protected String memo; 
	
	
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
	
	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}
	
	/**
	 * 返回 主表ID
	 * @return
	 */
	public String getMasterId() {
		return this.masterId;
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
	
	public void setCommerceCode(String commerceCode) {
		this.commerceCode = commerceCode;
	}
	
	/**
	 * 返回 商业编号
	 * @return
	 */
	public String getCommerceCode() {
		return this.commerceCode;
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
	
	public void setAcceptState(String acceptState) {
		this.acceptState = acceptState;
	}
	
	/**
	 * 返回 认可状态
	 * @return
	 */
	public String getAcceptState() {
		return this.acceptState;
	}
	
	public void setSignType(String signType) {
		this.signType = signType;
	}
	
	/**
	 * 返回 签订类型
	 * @return
	 */
	public String getSignType() {
		return this.signType;
	}
	
	public void setFlowManagementGrade(Integer flowManagementGrade) {
		this.flowManagementGrade = flowManagementGrade;
	}
	
	/**
	 * 返回 流向管理评分
	 * @return
	 */
	public Integer getFlowManagementGrade() {
		return this.flowManagementGrade;
	}
	
	public void setSystemCount(Double systemCount) {
		this.systemCount = systemCount;
	}
	
	/**
	 * 返回 系统计算
	 * @return
	 */
	public Double getSystemCount() {
		return this.systemCount;
	}
	
	public void setThisTimeApply(Double thisTimeApply) {
		this.thisTimeApply = thisTimeApply;
	}
	
	/**
	 * 返回 本次申请
	 * @return
	 */
	public Double getThisTimeApply() {
		return this.thisTimeApply;
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
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("masterId", this.masterId) 
		.append("businessDivision", this.businessDivision) 
		.append("province", this.province) 
		.append("businessDivisionId", this.businessDivisionId) 
		.append("provinceId", this.provinceId) 
		.append("commerceName", this.commerceName) 
		.append("commerceCode", this.commerceCode) 
		.append("commerceId", this.commerceId) 
		.append("acceptState", this.acceptState) 
		.append("signType", this.signType) 
		.append("flowManagementGrade", this.flowManagementGrade) 
		.append("systemCount", this.systemCount) 
		.append("thisTimeApply", this.thisTimeApply) 
		.append("memo", this.memo) 
		.toString();
	}
}