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
 * 经销商协议合作条款表
 * <pre> 
 * 描述：经销商协议合作条款表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:44
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmXsDealerClause",description = "经销商协议合作条款表") 
public class ScmXsDealerClause extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="协议汇总ID")
	protected String AgreementSummaryId; 
	
	@ApiModelProperty(value="协议号")
	protected String clauseCode; 
	
	@ApiModelProperty(value="商业名称")
	protected String commerceName; 
	
	@ApiModelProperty(value="职能类型")
	protected String functionType; 
	
	@ApiModelProperty(value="签订协议类型")
	protected String AgreementType; 
	
	@ApiModelProperty(value="协议生效日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	protected java.util.Date AgreementStartDate; 
	
	@ApiModelProperty(value="协议最新变更日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	protected java.util.Date AgreementChangeDate; 
	
	@ApiModelProperty(value="协议生效日期止")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	protected java.util.Date AgreementEndDate; 
	
	@ApiModelProperty(value="付款类型(0:预付款,1赊销)")
	protected String paymentType; 
	
	@ApiModelProperty(value="回款天数")
	protected int returnMoneyDay; 
	
	@ApiModelProperty(value="承诺省份")
	protected String promiseProvince; 
	
	@ApiModelProperty(value="信息直连标准")
	protected int messageStandard; 
	
	@ApiModelProperty(value="流向折扣标准")
	protected int flowStandard; 
	
	@ApiModelProperty(value="储运管理折扣率")
	protected int transportationDiscount; 
	
	@ApiModelProperty(value="卸载费支付方(0:甲方,1乙方)")
	protected String unloadPayer; 
	
	@ApiModelProperty(value="分子公司数量")
	protected int subsidiarySum; 
	
	@ApiModelProperty(value="申请备注")
	protected String memo; 
	
	@ApiModelProperty(value="是否可以超额发货")
	protected String isOverfulfil; 
	
	@ApiModelProperty(value="起始天数")
	protected int StardDay; 
	
	@ApiModelProperty(value="截止天数")
	protected int endDay; 
	
	@ApiModelProperty(value="本次申请折扣")
	protected int theApplication; 
	
	@ApiModelProperty(value="是否特殊(:0:没有,1:有)")
	protected String isSpecial; 
	
	@ApiModelProperty(value="上次申请折扣")
	protected int lastTheApplication; 
	
	@ApiModelProperty(value="实际级别")
	protected String actualLevel;

	 @ApiModelProperty(value="录入人")
	 protected String  createPerson;

	 @ApiModelProperty(value="年度")
	 protected String  year;


	 @ApiModelProperty(value="录入时间")
	 @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
	 protected Date createDate;

	 @ApiModelProperty(value="协议付款方式")
	 List<ScmXsDealerPayStyle> scmXsDealerPayStyleList = new ArrayList<>();


	 @ApiModelProperty(value="协议id")
	 protected String  dealerclauseId;




	 public String getYear() {
		 return year;
	 }

	 public void setYear(String year) {
		 this.year = year;
	 }

	 public String getDealerclauseId() {
		 return dealerclauseId;
	 }

	 public void setDealerclauseId(String dealerclauseId) {
		 this.dealerclauseId = dealerclauseId;
	 }

	 public List<ScmXsDealerPayStyle> getScmXsDealerPayStyleList() {
		 return scmXsDealerPayStyleList;
	 }

	 public void setScmXsDealerPayStyleList(List<ScmXsDealerPayStyle> scmXsDealerPayStyleList) {
		 this.scmXsDealerPayStyleList = scmXsDealerPayStyleList;
	 }

	 public String getCreatePerson() {
		 return createPerson;
	 }

	 public void setCreatePerson(String createPerson) {
		 this.createPerson = createPerson;
	 }

	 public Date getCreateDate() {
		 return createDate;
	 }

	 public void setCreateDate(Date createDate) {
		 this.createDate = createDate;
	 }

	 //主数据
	@ApiModelProperty(value="主表数据Id")
	protected String masterId; 
	
	@ApiModelProperty(value="商务分区")
	protected String businessDivision; 
	
	@ApiModelProperty(value="省区")
	protected String Province; 
	
	@ApiModelProperty(value="商务分区ID")
	protected String businessDivisionId; 
	
	@ApiModelProperty(value="省区ID")
	protected String provinceId; 
	
	@ApiModelProperty(value="流程ID")
	protected String approvalId; 
	
	@ApiModelProperty(value="流程状态")
	protected String approvalState; 
	
	@ApiModelProperty(value="货主ID")
	protected String ownerId; 
	
	@ApiModelProperty(value="协议单号")
	protected String agreementSummaryCode; 
	
	@ApiModelProperty(value="上次付款方式")
	protected String lastPaymentType;
	
	@ApiModelProperty(value="商业编号")
	protected String commerceCode;
	
	@ApiModelProperty(value="上次回款天数")
	protected int lastReturnMoneyDay;
	
	@ApiModelProperty(value="季度预付款占比")
	protected double quarterPaymentPercent;
	
	@ApiModelProperty(value="经销商首营ID")
	protected String commerceFirstId;

	/**
	*经销商协议特殊商品表列表
	*/
	protected List<ScmXsAgreementSpecialProduct> scmXsAgreementSpecialProductList=new ArrayList<ScmXsAgreementSpecialProduct>(); 
	/**
	*商业协议分子公司表列表
	*/
	protected List<ScmXsAgreementSubsidiary> scmXsAgreementSubsidiaryList=new ArrayList<ScmXsAgreementSubsidiary>(); 
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getCommerceFirstId() {
		return commerceFirstId;
	}


	public void setCommerceFirstId(String commerceFirstId) {
		this.commerceFirstId = commerceFirstId;
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
	
	public void setAgreementSummaryId(String AgreementSummaryId) {
		this.AgreementSummaryId = AgreementSummaryId;
	}
	
	/**
	 * 返回 协议汇总ID
	 * @return
	 */
	public String getAgreementSummaryId() {
		return this.AgreementSummaryId;
	}
	
	public void setClauseCode(String clauseCode) {
		this.clauseCode = clauseCode;
	}
	
	/**
	 * 返回 协议号
	 * @return
	 */
	public String getClauseCode() {
		return this.clauseCode;
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
	
	public void setFunctionType(String functionType) {
		this.functionType = functionType;
	}
	
	/**
	 * 返回 职能类型
	 * @return
	 */
	public String getFunctionType() {
		return this.functionType;
	}
	
	
	public void setAgreementType(String AgreementType) {
		this.AgreementType = AgreementType;
	}
	
	
	public String getActualLevel() {
		return actualLevel;
	}

	public void setActualLevel(String actualLevel) {
		this.actualLevel = actualLevel;
	}

	/**
	 * 返回 签订协议类型
	 * @return
	 */
	public String getAgreementType() {
		return this.AgreementType;
	}
	
	public void setAgreementStartDate(java.util.Date AgreementStartDate) {
		this.AgreementStartDate = AgreementStartDate;
	}
	
	/**
	 * 返回 协议生效日期
	 * @returnscm/scmFhShipmentsDatail/v1/firstList
	 */
	public java.util.Date getAgreementStartDate() {
		return this.AgreementStartDate;
	}
	
	public void setAgreementChangeDate(java.util.Date AgreementChangeDate) {
		this.AgreementChangeDate = AgreementChangeDate;
	}
	
	/**
	 * 返回 协议最新变更日期
	 * @return
	 */
	public java.util.Date getAgreementChangeDate() {
		return this.AgreementChangeDate;
	}
	
	public void setAgreementEndDate(java.util.Date AgreementEndDate) {
		this.AgreementEndDate = AgreementEndDate;
	}
	
	/**
	 * 返回 协议生效日期止
	 * @return
	 */
	public java.util.Date getAgreementEndDate() {
		return this.AgreementEndDate;
	}
	
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	
	/**
	 * 返回 付款类型(0:预付款,1赊销)
	 * @return
	 */
	public String getPaymentType() {
		return this.paymentType;
	}
	
	public void setReturnMoneyDay(int returnMoneyDay) {
		this.returnMoneyDay = returnMoneyDay;
	}
	
	/**
	 * 返回 回款天数
	 * @return
	 */
	public int getReturnMoneyDay() {
		return this.returnMoneyDay;
	}
	
	public void setPromiseProvince(String promiseProvince) {
		this.promiseProvince = promiseProvince;
	}
	
	/**
	 * 返回 承诺省份
	 * @return
	 */
	public String getPromiseProvince() {
		return this.promiseProvince;
	}
	
	public void setMessageStandard(int messageStandard) {
		this.messageStandard = messageStandard;
	}
	
	/**
	 * 返回 信息直连标准
	 * @return
	 */
	public int getMessageStandard() {
		return this.messageStandard;
	}
	
	public void setFlowStandard(int flowStandard) {
		this.flowStandard = flowStandard;
	}
	
	/**
	 * 返回 流向折扣标准
	 * @return
	 */
	public int getFlowStandard() {
		return this.flowStandard;
	}
	
	public void setTransportationDiscount(int transportationDiscount) {
		this.transportationDiscount = transportationDiscount;
	}
	
	/**
	 * 返回 储运管理折扣率
	 * @return
	 */
	public int getTransportationDiscount() {
		return this.transportationDiscount;
	}
	
	public void setUnloadPayer(String unloadPayer) {
		this.unloadPayer = unloadPayer;
	}
	
	/**
	 * 返回 卸载费支付方(0:甲方,1乙方)
	 * @return
	 */
	public String getUnloadPayer() {
		return this.unloadPayer;
	}
	
	public void setSubsidiarySum(int subsidiarySum) {
		this.subsidiarySum = subsidiarySum;
	}
	
	/**
	 * 返回 分子公司数量
	 * @return
	 */
	public int getSubsidiarySum() {
		return this.subsidiarySum;
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
	
	public void setIsOverfulfil(String isOverfulfil) {
		this.isOverfulfil = isOverfulfil;
	}
	
	/**
	 * 返回 是否可以超额发货
	 * @return
	 */
	public String getIsOverfulfil() {
		return this.isOverfulfil;
	}
	
	public void setStardDay(int StardDay) {
		this.StardDay = StardDay;
	}
	
	/**
	 * 返回 起始天数
	 * @return
	 */
	public int getStardDay() {
		return this.StardDay;
	}
	
	public void setEndDay(int endDay) {
		this.endDay = endDay;
	}
	
	/**
	 * 返回 截止天数
	 * @return
	 */
	public int getEndDay() {
		return this.endDay;
	}
	
	public void setTheApplication(int theApplication) {
		this.theApplication = theApplication;
	}
	
	/**
	 * 返回 本次申请折扣
	 * @return
	 */
	public int getTheApplication() {
		return this.theApplication;
	}
	
	public void setIsSpecial(String isSpecial) {
		this.isSpecial = isSpecial;
	}
	/**
	 * 返回 上次申请折扣
	 * @return
	 */
	public int getLastTheApplication() {
		return lastTheApplication;
	}

	public void setLastTheApplication(int lastTheApplication) {
		this.lastTheApplication = lastTheApplication;
	}
	
	/**
	 * 返回 是否特殊(:0:没有,1:有)
	 * @return
	 */
	public String getIsSpecial() {
		return this.isSpecial;
	}

	public void setScmXsAgreementSpecialProductList(List<ScmXsAgreementSpecialProduct> scmXsAgreementSpecialProductList) {
		this.scmXsAgreementSpecialProductList = scmXsAgreementSpecialProductList;
	}
	
	/**
	 * 返回 经销商协议特殊商品表列表
	 * @return
	 */
	public List<ScmXsAgreementSpecialProduct> getScmXsAgreementSpecialProductList() {
		return this.scmXsAgreementSpecialProductList;
	}

	public void setScmXsAgreementSubsidiaryList(List<ScmXsAgreementSubsidiary> scmXsAgreementSubsidiaryList) {
		this.scmXsAgreementSubsidiaryList = scmXsAgreementSubsidiaryList;
	}
	
	/**
	 * 返回 商业协议分子公司表列表
	 * @return
	 */
	public List<ScmXsAgreementSubsidiary> getScmXsAgreementSubsidiaryList() {
		return this.scmXsAgreementSubsidiaryList;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("AgreementSummaryId", this.AgreementSummaryId) 
		.append("clauseCode", this.clauseCode) 
		.append("commerceName", this.commerceName) 
		.append("functionType", this.functionType) 
		.append("AgreementType", this.AgreementType) 
		.append("AgreementStartDate", this.AgreementStartDate) 
		.append("AgreementChangeDate", this.AgreementChangeDate) 
		.append("AgreementEndDate", this.AgreementEndDate) 
		.append("paymentType", this.paymentType) 
		.append("returnMoneyDay", this.returnMoneyDay) 
		.append("promiseProvince", this.promiseProvince) 
		.append("messageStandard", this.messageStandard) 
		.append("flowStandard", this.flowStandard) 
		.append("transportationDiscount", this.transportationDiscount) 
		.append("unloadPayer", this.unloadPayer) 
		.append("subsidiarySum", this.subsidiarySum) 
		.append("memo", this.memo) 
		.append("isOverfulfil", this.isOverfulfil) 
		.append("StardDay", this.StardDay) 
		.append("endDay", this.endDay) 
		.append("theApplication", this.theApplication) 
		.append("isSpecial", this.isSpecial) 
		.toString();
	}

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public String getBusinessDivision() {
		return businessDivision;
	}

	public void setBusinessDivision(String businessDivision) {
		this.businessDivision = businessDivision;
	}

	public String getProvince() {
		return Province;
	}

	public void setProvince(String province) {
		Province = province;
	}

	public String getBusinessDivisionId() {
		return businessDivisionId;
	}

	public void setBusinessDivisionId(String businessDivisionId) {
		this.businessDivisionId = businessDivisionId;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
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

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getAgreementSummaryCode() {
		return agreementSummaryCode;
	}

	public void setAgreementSummaryCode(String agreementSummaryCode) {
		this.agreementSummaryCode = agreementSummaryCode;
	}

	public String getLastPaymentType() {
		return lastPaymentType;
	}

	public void setLastPaymentType(String lastPaymentType) {
		this.lastPaymentType = lastPaymentType;
	}

	public int getLastReturnMoneyDay() {
		return lastReturnMoneyDay;
	}

	public void setLastReturnMoneyDay(int lastReturnMoneyDay) {
		this.lastReturnMoneyDay = lastReturnMoneyDay;
	}

	public String getCommerceCode() {
		return commerceCode;
	}

	public void setCommerceCode(String commerceCode) {
		this.commerceCode = commerceCode;
	}

	public double getQuarterPaymentPercent() {
		return quarterPaymentPercent;
	}

	public void setQuarterPaymentPercent(double quarterPaymentPercent) {
		this.quarterPaymentPercent = quarterPaymentPercent;
	}

	
}