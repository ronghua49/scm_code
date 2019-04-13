package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 商业资信申请表
 * <pre> 
 * 描述：商业资信申请表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:43
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmXsCreditApply",description = "商业资信申请表") 
public class ScmXsCreditApply extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="商务分区")
	protected String businessDivision; 
	
	@ApiModelProperty(value="省区")
	protected String Province; 
	
	@ApiModelProperty(value="商务分区ID")
	protected String businessDivisionId; 
	
	@ApiModelProperty(value="省区ID")
	protected String provinceId; 
	
	@ApiModelProperty(value="商业编码")
	protected String commerceCode; 
	
	@ApiModelProperty(value="商业名称")
	protected String commerceName; 
	
	@ApiModelProperty(value="购销方式(0:预付款,1赊销)")
	protected String marketWay; 
	
	@ApiModelProperty(value="前年月均销售额(万)")
	protected Integer beforeYearPrice; 
	
	@ApiModelProperty(value="去年月均销售额(万)")
	protected Integer lastYearPrice; 
	
	@ApiModelProperty(value="上次批准资信额(万)")
	protected Integer lastTimePrice; 
	
	@ApiModelProperty(value="系统预测资信额(万)")
	protected Integer sysPrice; 
	
	@ApiModelProperty(value="资信额(万)")
	protected Integer price; 
	
	@ApiModelProperty(value="备注")
	protected String memo; 
	
	@ApiModelProperty(value="汇总ID")
	protected String CreditCollectId; 
	
	@ApiModelProperty(value="年度")
	protected String year;
	
	@ApiModelProperty(value="季度")
	protected String quarter;
	
	@ApiModelProperty(value="审批ID")
	protected String approvalId;
	
	@ApiModelProperty(value="审批状态")
	protected String approvalState;
	
	@ApiModelProperty(value="审批状态中文")
	protected String approvalStateStr;
	
	@ApiModelProperty(value="主表ID")
	protected String masterId;
	
	@ApiModelProperty(value="货主ID")
	protected String ownerId;
	
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
	
	public void setProvince(String Province) {
		this.Province = Province;
	}
	
	/**
	 * 返回 省区
	 * @return
	 */
	public String getProvince() {
		return this.Province;
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
	
	public void setMarketWay(String marketWay) {
		this.marketWay = marketWay;
	}
	
	/**
	 * 返回 购销方式(0:预付款,1赊销)
	 * @return
	 */
	public String getMarketWay() {
		return this.marketWay;
	}
	
	public void setBeforeYearPrice(Integer beforeYearPrice) {
		this.beforeYearPrice = beforeYearPrice;
	}
	
	/**
	 * 返回 前年月均销售额(万)
	 * @return
	 */
	public Integer getBeforeYearPrice() {
		return this.beforeYearPrice;
	}
	
	public void setLastYearPrice(Integer lastYearPrice) {
		this.lastYearPrice = lastYearPrice;
	}
	
	/**
	 * 返回 去年月均销售额(万)
	 * @return
	 */
	public Integer getLastYearPrice() {
		return this.lastYearPrice;
	}
	
	public void setLastTimePrice(Integer lastTimePrice) {
		this.lastTimePrice = lastTimePrice;
	}
	
	/**
	 * 返回 上次批准资信额(万)
	 * @return
	 */
	public Integer getLastTimePrice() {
		return this.lastTimePrice;
	}
	
	public void setSysPrice(Integer sysPrice) {
		this.sysPrice = sysPrice;
	}
	
	/**
	 * 返回 系统预测资信额(万)
	 * @return
	 */
	public Integer getSysPrice() {
		return this.sysPrice;
	}
	
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	/**
	 * 返回 资信额(万)
	 * @return
	 */
	public Integer getPrice() {
		return this.price;
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
	
	public void setCreditCollectId(String CreditCollectId) {
		this.CreditCollectId = CreditCollectId;
	}
	
	/**
	 * 返回 汇总ID
	 * @return
	 */
	public String getCreditCollectId() {
		return this.CreditCollectId;
	}
	
	
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
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

	public String getApprovalStateStr() {
		return approvalStateStr;
	}

	public void setApprovalStateStr(String approvalStateStr) {
		this.approvalStateStr = approvalStateStr;
	}

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("businessDivision", this.businessDivision) 
		.append("Province", this.Province) 
		.append("businessDivisionId", this.businessDivisionId) 
		.append("provinceId", this.provinceId) 
		.append("commerceCode", this.commerceCode) 
		.append("commerceName", this.commerceName) 
		.append("marketWay", this.marketWay) 
		.append("beforeYearPrice", this.beforeYearPrice) 
		.append("lastYearPrice", this.lastYearPrice) 
		.append("lastTimePrice", this.lastTimePrice) 
		.append("sysPrice", this.sysPrice) 
		.append("price", this.price) 
		.append("memo", this.memo) 
		.append("CreditCollectId", this.CreditCollectId) 
		.toString();
	}
}