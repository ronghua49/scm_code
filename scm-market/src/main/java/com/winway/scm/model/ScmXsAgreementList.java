package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 经销商协议名单申请表
 * <pre> 
 * 描述：经销商协议名单申请表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 15:01:16
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmXsAgreementList",description = "经销商协议名单申请表") 
public class ScmXsAgreementList extends BaseModel<String>{

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="商业首营ID")
	protected String commerceFirstId; 
	
	@ApiModelProperty(value="年度")
	protected String year; 
	
	@ApiModelProperty(value="商业编码")
	protected String commerceCode; 
	
	@ApiModelProperty(value="商业分类")
	protected String commerceClassify; 
	
	@ApiModelProperty(value="商业级别")
	protected String commerceLevel; 
	
	@ApiModelProperty(value="申请备注")
	protected String memo; 
	
	@ApiModelProperty(value="是否签约(0:未签约,1已签约)")
	protected String isSign; 
	
	@ApiModelProperty(value="协议名单主表ID")
	protected String masterId; 
	
	@ApiModelProperty(value="商业名称")
	protected String commerceName; 
	
	@ApiModelProperty(value="操作类型")
	protected String type; 
	
	@ApiModelProperty(value="上年度是否签约(0:未签约,1已签约)")
	protected String lastYear; 
	
	@ApiModelProperty(value="发货量")
	protected String shipments; 
	
	@ApiModelProperty(value="操作类型")
	protected String allocating; 
	
	@ApiModelProperty(value="操作类型")
	protected String sales; 
	
	//主表	
	@ApiModelProperty(value="商务分区")
	protected String businessDivision; 
	
	@ApiModelProperty(value="商务分区ID")
	protected String businessDivisionId; 
	
	@ApiModelProperty(value="货主ID")
	protected String ownerId; 
	
	@ApiModelProperty(value="审批ID")
	protected String approvalId; 
	
	@ApiModelProperty(value="审批状态")
	protected String approvalState; 
	
	@ApiModelProperty(value="申请单号")
	protected String MasterCode; 
		
	@ApiModelProperty(value="v_认可状态(0:认可,1:考察中,2:终止)")
	protected String acceptState; 
	
	@ApiModelProperty(value="是否生效")
	protected String isEffect;  
	
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
	
	public void setCommerceFirstId(String commerceFirstId) {
		this.commerceFirstId = commerceFirstId;
	}
	
	/**
	 * 返回 商业首营ID
	 * @return
	 */
	public String getCommerceFirstId() {
		return this.commerceFirstId;
	}
	
	public void setYear(String year) {
		this.year = year;
	}
	
	/**
	 * 返回 年度
	 * @return
	 */
	public String getYear() {
		return this.year;
	}
	
	public void setCommerceCode(String commerceCode) {
		this.commerceCode = commerceCode;
	}
	
	/**
	 * 返回 商业编码
	 * @return
	 */
	public String getCommerceCode() {
		return this.commerceCode;
	}
	
	public void setCommerceClassify(String commerceClassify) {
		this.commerceClassify = commerceClassify;
	}
	
	/**
	 * 返回 商业分类
	 * @return
	 */
	public String getCommerceClassify() {
		return this.commerceClassify;
	}
	
	public void setCommerceLevel(String commerceLevel) {
		this.commerceLevel = commerceLevel;
	}
	
	/**
	 * 返回 商业级别
	 * @return
	 */
	public String getCommerceLevel() {
		return this.commerceLevel;
	}
	
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	/**
	 * 返回 申请备注
	 * @return
	 */
	public String getMemo() {
		return this.memo;
	}
	
	public void setIsSign(String isSign) {
		this.isSign = isSign;
	}
	
	/**
	 * 返回 是否签约(0:未签约,1已签约)
	 * @return
	 */
	public String getIsSign() {
		return this.isSign;
	}
	
	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}
	
	/**
	 * 返回 协议名单主表ID
	 * @return
	 */
	public String getMasterId() {
		return this.masterId;
	}
	
	public void setCommerceName(String commerceName) {
		this.commerceName = commerceName;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 返回 商业名称
	 * @return
	 */
	public String getCommerceName() {
		return this.commerceName;
	}
	
	
	public String getLastYear() {
		return lastYear;
	}
	public void setLastYear(String lastYear) {
		this.lastYear = lastYear;
	}
	public String getShipments() {
		return shipments;
	}
	public void setShipments(String shipments) {
		this.shipments = shipments;
	}
	public String getAllocating() {
		return allocating;
	}
	public void setAllocating(String allocating) {
		this.allocating = allocating;
	}
	public String getSales() {
		return sales;
	}
	public void setSales(String sales) {
		this.sales = sales;
	}
//主表
	
	public String getBusinessDivision() {
		return businessDivision;
	}

	public void setBusinessDivision(String businessDivision) {
		this.businessDivision = businessDivision;
	}

	public String getBusinessDivisionId() {
		return businessDivisionId;
	}

	public void setBusinessDivisionId(String businessDivisionId) {
		this.businessDivisionId = businessDivisionId;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
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

	public String getMasterCode() {
		return MasterCode;
	}

	public void setMasterCode(String masterCode) {
		MasterCode = masterCode;
	}

	public String getAcceptState() {
		return acceptState;
	}

	public void setAcceptState(String acceptState) {
		this.acceptState = acceptState;
	}
	
	
	
	public String getIsEffect() {
		return isEffect;
	}

	public void setIsEffect(String isEffect) {
		this.isEffect = isEffect;
	}
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("commerceFirstId", this.commerceFirstId) 
		.append("year", this.year) 
		.append("commerceCode", this.commerceCode) 
		.append("commerceClassify", this.commerceClassify) 
		.append("commerceLevel", this.commerceLevel) 
		.append("memo", this.memo) 
		.append("isSign", this.isSign) 
		.append("masterId", this.masterId) 
		.append("commerceName", this.commerceName) 
		.toString();
	}

	

}