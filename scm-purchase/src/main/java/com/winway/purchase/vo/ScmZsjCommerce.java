package com.winway.purchase.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.Date;

/**
 * 商业表
 * 
 * <pre>
 *  
 * 描述：商业表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
@ApiModel(value = "ScmZsjCommerce", description = "商业表")
public class ScmZsjCommerce extends BaseModel<String> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	protected String id;

	@ApiModelProperty(value = "商业名称")
	protected String commerceName;

	@ApiModelProperty(value = "档案编号")
	protected String fileCode;

	@ApiModelProperty(value = "企业类别(0药品经营企业,1医疗机构)")
	protected String commerceState;

	@ApiModelProperty(value = "客户编号")
	protected String commerceCode;

	@ApiModelProperty(value = "企业负责人")
	protected String conpanyPersion;

	@ApiModelProperty(value = "联系人")
	protected String contactPersion;

	@ApiModelProperty(value = "联系方式")
	protected String phone;

	@ApiModelProperty(value = "开户行")
	protected String openBank;

	@ApiModelProperty(value = "银行账号")
	protected String bankCode;

	@ApiModelProperty(value = "税号")
	protected String dutyParagraph;

	@ApiModelProperty(value = "商务分区ID")
	protected String businessDivisionId;

	@ApiModelProperty(value = "商务分区")
	protected String businessDivision;

	@ApiModelProperty(value = "省区ID")
	protected String provinceId;

	@ApiModelProperty(value = "省区")
	protected String province;

	@ApiModelProperty(value = "药品质量保证协议有效期")
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date qualityValidity;

	@ApiModelProperty(value = "注册地址")
	protected String registerAddress;

	@ApiModelProperty(value = "印章样式")
	protected String sealStyle;

	@ApiModelProperty(value = "随货单据样式")
	protected String billsStyle;

	@ApiModelProperty(value = "录入人")
	protected String enterPersion;

	@ApiModelProperty(value = "录入日期")
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date enterDate;

	@ApiModelProperty(value = "商业级别")
	protected String commerceLevel;

	@ApiModelProperty(value = "职能类型")
	protected String functionType;

	@ApiModelProperty(value = "认可状态字符串")
	protected String acceptStateStr;

	@ApiModelProperty(value = "认可状态")
	protected String acceptState;

	@ApiModelProperty(value = "企业类型字符串")
	protected String commerceStateStr;

	@ApiModelProperty(value = "法定代表人")
	protected String legalPersion;

	@ApiModelProperty(value = "许可证号")
	protected String licenseKey;

	@ApiModelProperty(value = "有效期")
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date validity;

	@ApiModelProperty(value = "注册号")
	protected String register;

	@ApiModelProperty(value = "营业执照有效期")
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date licenseValidity;

	@ApiModelProperty(value = "注册资本(万元)")
	protected String registerPrice;

	@ApiModelProperty(value = "生产/经营范围")
	protected String managementScope;

	@ApiModelProperty(value = "商业名带首营状态")
	protected String commerceNameStr;

	@ApiModelProperty(value = "认可状态ID")
	protected String acceptStateId;

	@ApiModelProperty(value = "认可时间")
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	protected String effectiveDate;

	@ApiModelProperty(value = "备注")
	protected String memo;

	@ApiModelProperty(value = "责任人")
	protected String dutyOfficer;

	@ApiModelProperty(value = "认可状态总表ID")
	protected String acceptId;

	@ApiModelProperty(value = "审批ID")
	protected String approvalId;

	@ApiModelProperty(value = "审批状态")
	protected String approvalState;

	@ApiModelProperty(value = "审批状态")
	protected String approvalStateStr;

	@ApiModelProperty(value = "货主ID")
	protected String ownerId;

	@ApiModelProperty(value = "操作人名称")
	protected String operatorName;

	@ApiModelProperty(value = "操作人名称")
	protected String commerceFirstId;

	@ApiModelProperty(value = "申请时间")
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date operatorDate;

	@ApiModelProperty(value = "选择类型")
	protected String type;

	@ApiModelProperty(value = "商业id")
	protected String commerceId;

	@ApiModelProperty(value = "v_实际类型")
	protected String actualLevel;

	@ApiModelProperty(value = "星级")
	protected String starLevel;

	@ApiModelProperty(value = "星级")
	protected String newDutyOfficer;

	@ApiModelProperty(value = "许可证状态")
	protected String licenceState;

	@ApiModelProperty(value = "营业执照状态")
	protected String businessLicenceState;

	@ApiModelProperty(value = "企业类型")
	protected String commerceType;

	@ApiModelProperty(value = "gsp编号")
	protected String gsp;

	@ApiModelProperty(value = "委托书编号")
	protected String entrustCode;

	@ApiModelProperty(value = "委托书姓名")
	protected String entrustName;

	@ApiModelProperty(value="是否推送至主数据中心(0:否,1:是)")
	private String isToMasterData;


	//资信字段

	@ApiModelProperty(value="前年月均销售额(万)")
	protected Integer beforeYearPrice;

	@ApiModelProperty(value="去年月均销售额(万)")
	protected Integer lastYearPrice;

	@ApiModelProperty(value="上次批准资信额(万)")
	protected Integer lastTimePrice;

	@ApiModelProperty(value="系统预测资信额(万)")
	protected Integer sysPrice;

	public Integer getBeforeYearPrice() {
		return beforeYearPrice;
	}

	public void setBeforeYearPrice(Integer beforeYearPrice) {
		this.beforeYearPrice = beforeYearPrice;
	}

	public Integer getLastYearPrice() {
		return lastYearPrice;
	}

	public void setLastYearPrice(Integer lastYearPrice) {
		this.lastYearPrice = lastYearPrice;
	}

	public Integer getLastTimePrice() {
		return lastTimePrice;
	}

	public void setLastTimePrice(Integer lastTimePrice) {
		this.lastTimePrice = lastTimePrice;
	}

	public Integer getSysPrice() {
		return sysPrice;
	}

	public void setSysPrice(Integer sysPrice) {
		this.sysPrice = sysPrice;
	}

	public String getIsToMasterData() {
		return isToMasterData;
	}

	public void setIsToMasterData(String isToMasterData) {
		this.isToMasterData = isToMasterData;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getEntrustCode() {
		return entrustCode;
	}

	public void setEntrustCode(String entrustCode) {
		this.entrustCode = entrustCode;
	}

	public String getEntrustName() {
		return entrustName;
	}

	public void setEntrustName(String entrustName) {
		this.entrustName = entrustName;
	}

	public String getGsp() {
		return gsp;
	}

	public void setGsp(String gsp) {
		this.gsp = gsp;
	}

	public String getLicenceState() {
		return licenceState;
	}

	public void setLicenceState(String licenceState) {
		this.licenceState = licenceState;
	}

	public String getBusinessLicenceState() {
		return businessLicenceState;
	}

	public void setBusinessLicenceState(String businessLicenceState) {
		this.businessLicenceState = businessLicenceState;
	}

	public String getCommerceType() {
		return commerceType;
	}

	public void setCommerceType(String commerceType) {
		this.commerceType = commerceType;
	}

	public String getNewDutyOfficer() {
		return newDutyOfficer;
	}

	public void setNewDutyOfficer(String newDutyOfficer) {
		this.newDutyOfficer = newDutyOfficer;
	}

	public String getStarLevel() {
		return starLevel;
	}

	public void setStarLevel(String starLevel) {
		this.starLevel = starLevel;
	}

	public String getDutyOfficer() {
		return dutyOfficer;
	}

	public void setDutyOfficer(String dutyOfficer) {
		this.dutyOfficer = dutyOfficer;
	}

	public String getCommerceId() {
		return commerceId;
	}

	public void setCommerceId(String commerceId) {
		this.commerceId = commerceId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getActualLevel() {
		return actualLevel;
	}

	public void setActualLevel(String actualLevel) {
		this.actualLevel = actualLevel;
	}

	/**
	 * 返回 id
	 * 
	 * @return
	 */
	public String getId() {
		return this.id;
	}

	public String getApprovalStateStr() {
		return approvalStateStr;
	}

	public void setApprovalStateStr(String approvalStateStr) {
		this.approvalStateStr = approvalStateStr;
	}

	public Date getOperatorDate() {
		return operatorDate;
	}

	public String getCommerceFirstId() {
		return commerceFirstId;
	}

	public void setCommerceFirstId(String commerceFirstId) {
		this.commerceFirstId = commerceFirstId;
	}

	public void setOperatorDate(Date operatorDate) {
		this.operatorDate = operatorDate;
	}

	public void setCommerceName(String commerceName) {
		this.commerceName = commerceName;
	}

	public String getCommerceNameStr() {
		return commerceNameStr;
	}

	public void setCommerceNameStr(String commerceNameStr) {
		this.commerceNameStr = commerceNameStr;
	}

	/**
	 * 返回 商业名称
	 * 
	 * @return
	 */
	public String getCommerceName() {
		return this.commerceName;
	}

	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}

	public String getAcceptStateId() {
		return acceptStateId;
	}

	public void setAcceptStateId(String acceptStateId) {
		this.acceptStateId = acceptStateId;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getAcceptId() {
		return acceptId;
	}

	public void setAcceptId(String acceptId) {
		this.acceptId = acceptId;
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

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	/**
	 * 返回 档案编号
	 * 
	 * @return
	 */
	public String getFileCode() {
		return this.fileCode;
	}

	public void setCommerceState(String commerceState) {
		this.commerceState = commerceState;
	}

	/**
	 * 返回 企业类别(0药品经营企业,医疗机构)
	 * 
	 * @return
	 */
	public String getCommerceState() {
		return this.commerceState;
	}

	public void setCommerceCode(String commerceCode) {
		this.commerceCode = commerceCode;
	}

	/**
	 * 返回 客户编号
	 * 
	 * @return
	 */
	public String getCommerceCode() {
		return this.commerceCode;
	}

	public void setConpanyPersion(String conpanyPersion) {
		this.conpanyPersion = conpanyPersion;
	}

	public String getAcceptStateStr() {
		return acceptStateStr;
	}

	public void setAcceptStateStr(String acceptStateStr) {
		this.acceptStateStr = acceptStateStr;
	}

	public String getAcceptState() {
		return acceptState;
	}

	public void setAcceptState(String acceptState) {
		this.acceptState = acceptState;
	}

	public String getCommerceStateStr() {
		return commerceStateStr;
	}

	public void setCommerceStateStr(String commerceStateStr) {
		this.commerceStateStr = commerceStateStr;
	}

	public String getLegalPersion() {
		return legalPersion;
	}

	public void setLegalPersion(String legalPersion) {
		this.legalPersion = legalPersion;
	}

	public String getLicenseKey() {
		return licenseKey;
	}

	public void setLicenseKey(String licenseKey) {
		this.licenseKey = licenseKey;
	}

	public Date getValidity() {
		return validity;
	}

	public void setValidity(Date validity) {
		this.validity = validity;
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	public Date getLicenseValidity() {
		return licenseValidity;
	}

	public void setLicenseValidity(Date licenseValidity) {
		this.licenseValidity = licenseValidity;
	}

	public String getRegisterPrice() {
		return registerPrice;
	}

	public void setRegisterPrice(String registerPrice) {
		this.registerPrice = registerPrice;
	}

	public String getManagementScope() {
		return managementScope;
	}

	public void setManagementScope(String managementScope) {
		this.managementScope = managementScope;
	}

	/**
	 * 返回 企业负责人
	 * 
	 * @return
	 */
	public String getConpanyPersion() {
		return this.conpanyPersion;
	}

	public void setContactPersion(String contactPersion) {
		this.contactPersion = contactPersion;
	}

	/**
	 * 返回 联系人
	 * 
	 * @return
	 */
	public String getContactPersion() {
		return this.contactPersion;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 返回 联系方式
	 * 
	 * @return
	 */
	public String getPhone() {
		return this.phone;
	}

	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}

	/**
	 * 返回 开户行
	 * 
	 * @return
	 */
	public String getOpenBank() {
		return this.openBank;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	/**
	 * 返回 银行账号
	 * 
	 * @return
	 */
	public String getBankCode() {
		return this.bankCode;
	}

	public void setDutyParagraph(String dutyParagraph) {
		this.dutyParagraph = dutyParagraph;
	}

	/**
	 * 返回 税号
	 * 
	 * @return
	 */
	public String getDutyParagraph() {
		return this.dutyParagraph;
	}

	public void setBusinessDivisionId(String businessDivisionId) {
		this.businessDivisionId = businessDivisionId;
	}

	/**
	 * 返回 商务分区ID
	 * 
	 * @return
	 */
	public String getBusinessDivisionId() {
		return this.businessDivisionId;
	}

	public void setBusinessDivision(String businessDivision) {
		this.businessDivision = businessDivision;
	}

	/**
	 * 返回 商务分区
	 * 
	 * @return
	 */
	public String getBusinessDivision() {
		return this.businessDivision;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	/**
	 * 返回 省区ID
	 * 
	 * @return
	 */
	public String getProvinceId() {
		return this.provinceId;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * 返回 省区
	 * 
	 * @return
	 */
	public String getProvince() {
		return this.province;
	}

	public void setQualityValidity(Date qualityValidity) {
		this.qualityValidity = qualityValidity;
	}

	/**
	 * 返回 药品质量保证协议有效期
	 *
	 * @return
	 */
	public Date getQualityValidity() {
		return this.qualityValidity;
	}

	public void setRegisterAddress(String registerAddress) {
		this.registerAddress = registerAddress;
	}

	/**
	 * 返回 注册地址
	 *
	 * @return
	 */
	public String getRegisterAddress() {
		return this.registerAddress;
	}

	public void setSealStyle(String sealStyle) {
		this.sealStyle = sealStyle;
	}

	/**
	 * 返回 印章样式
	 *
	 * @return
	 */
	public String getSealStyle() {
		return this.sealStyle;
	}

	public void setBillsStyle(String billsStyle) {
		this.billsStyle = billsStyle;
	}

	/**
	 * 返回 随货单据样式
	 *
	 * @return
	 */
	public String getBillsStyle() {
		return this.billsStyle;
	}

	public void setEnterPersion(String enterPersion) {
		this.enterPersion = enterPersion;
	}

	/**
	 * 返回 录入人
	 *
	 * @return
	 */
	public String getEnterPersion() {
		return this.enterPersion;
	}

	public void setEnterDate(Date enterDate) {
		this.enterDate = enterDate;
	}

	/**
	 * 返回 录入日期
	 *
	 * @return
	 */
	public Date getEnterDate() {
		return this.enterDate;
	}

	/**
	 * 返回商业级别
	 *
	 * @return
	 */
	public String getCommerceLevel() {
		return commerceLevel;
	}

	public void setCommerceLevel(String commerceLevel) {
		this.commerceLevel = commerceLevel;
	}

	/**
	 * 返回职能类型
	 *
	 * @return
	 */
	public String getFunctionType() {
		return functionType;
	}

	public void setFunctionType(String functionType) {
		this.functionType = functionType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @see Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).append("commerceName", this.commerceName)
				.append("fileCode", this.fileCode).append("commerceState", this.commerceState)
				.append("commerceCode", this.commerceCode).append("conpanyPersion", this.conpanyPersion)
				.append("contactPersion", this.contactPersion).append("phone", this.phone)
				.append("openBank", this.openBank).append("bankCode", this.bankCode)
				.append("dutyParagraph", this.dutyParagraph).append("businessDivisionId", this.businessDivisionId)
				.append("businessDivision", this.businessDivision).append("provinceId", this.provinceId)
				.append("province", this.province).append("qualityValidity", this.qualityValidity)
				.append("registerAddress", this.registerAddress).append("sealStyle", this.sealStyle)
				.append("billsStyle", this.billsStyle).append("enterPersion", this.enterPersion)
				.append("enterDate", this.enterDate).toString();
	}
}