package com.winway.scm.model;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 药品不良反应事件管理
 * <pre> 
 * 描述：药品不良反应事件管理 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-18 18:21:20
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmGspSideEffects",description = "药品不良反应事件管理") 
public class ScmGspSideEffects extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="患者姓名",required = true)
	protected String patientName; 
	
	@ApiModelProperty(value="性别",required = true)
	protected String gender; 
	
	@ApiModelProperty(value="出生日期",required = true)
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	protected java.util.Date birthDay; 
	
	@ApiModelProperty(value="体重",required = true)
	protected Double weight; 
	
	@ApiModelProperty(value="民族",required = true)
	protected String natin; 
	
	@ApiModelProperty(value="家族药品不良反应")
	protected String familyMedicineSideEffects; 
	
	@ApiModelProperty(value="病例号/门诊号",required = true)
	protected String caseNumber; 
	
	@ApiModelProperty(value="联系电话",required = true)
	protected String phone; 
	
	@ApiModelProperty(value="既往药品不良反应",required = true)
	protected String pastDrugsSideEffects; 
	
	@ApiModelProperty(value="工作单位/住址",required = true)
	protected String address; 
	
	@ApiModelProperty(value="过敏史")
	protected String allergy; 
	
	@ApiModelProperty(value="原患疾病")
	protected String originalDisease; 
	
	@ApiModelProperty(value="不良反应名称",required = true)
	protected String sideEffectsName; 
	
	@ApiModelProperty(value="不良反应时间",required = true)
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date sideEffectsDate; 
	
	@ApiModelProperty(value="不良反应临床表现",required = true)
	protected String sideEffectsClincalFeature; 
	
	@ApiModelProperty(value="不良反应处理情况",required = true)
	protected String sideEffectsHandInformation; 
	
	@ApiModelProperty(value="不良反应结果",required = true)
	protected String sideEffectsResult; 
	
	@ApiModelProperty(value="对原疾病影响",required = true)
	protected String originalDiseaseAffect; 
	
	@ApiModelProperty(value="死亡时间")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date dieDate; 
	
	@ApiModelProperty(value="直接死因")
	protected String directDie; 
	
	@ApiModelProperty(value="省级评价",required = true)
	protected String provincialEvaluatioon; 
	
	@ApiModelProperty(value="国家级评价",required = true)
	protected String nationalAssessment; 
	
	@ApiModelProperty(value="国内不良反应情况")
	protected String domesticSideEffects; 
	
	@ApiModelProperty(value="国外不良反应情况")
	protected String abroadSideEffects; 
	
	@ApiModelProperty(value="其他")
	protected String other; 
	
	@ApiModelProperty(value="备注")
	protected String memo; 
	
	@ApiModelProperty(value="货主ID",required = true)
	protected String ownerId; 
	
	@ApiModelProperty(value="创建时间")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date createDate; 
	
	@ApiModelProperty(value="创建人")
	protected String createPersion; 
	
	@ApiModelProperty(value="审批ID")
	protected String approvalId; 
	
	@ApiModelProperty(value="审批状态(0:未提交,1:审批中,2:已通过,3:被驳回)")
	protected String approvalState; 
	
	/**
	*Scm_Gsp_DoubtProduct列表
	*/
	protected List<ScmGspDoubtProduct> scmGspDoubtProductList=new ArrayList<ScmGspDoubtProduct>(); 
	/**
	*并用药品列表
	*/
	protected List<ScmGspAndUseProduct> scmGspAndUseProductList=new ArrayList<ScmGspAndUseProduct>(); 
	/**
	*相关重要信息列表
	*/
	protected List<ScmGspRelevantImportantMessage> scmGspRelevantImportantMessageList=new ArrayList<ScmGspRelevantImportantMessage>(); 
	
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
	
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	
	/**
	 * 返回 患者姓名
	 * @return
	 */
	public String getPatientName() {
		return this.patientName;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	/**
	 * 返回 性别
	 * @return
	 */
	public String getGender() {
		return this.gender;
	}
	
	public void setBirthDay(java.util.Date birthDay) {
		this.birthDay = birthDay;
	}
	
	/**
	 * 返回 出生日期
	 * @return
	 */
	public java.util.Date getBirthDay() {
		return this.birthDay;
	}
	
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	
	/**
	 * 返回 体重
	 * @return
	 */
	public Double getWeight() {
		return this.weight;
	}
	
	public void setNatin(String natin) {
		this.natin = natin;
	}
	
	/**
	 * 返回 民族
	 * @return
	 */
	public String getNatin() {
		return this.natin;
	}
	
	public void setFamilyMedicineSideEffects(String familyMedicineSideEffects) {
		this.familyMedicineSideEffects = familyMedicineSideEffects;
	}
	
	/**
	 * 返回 家族药品不良反应
	 * @return
	 */
	public String getFamilyMedicineSideEffects() {
		return this.familyMedicineSideEffects;
	}
	
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
	
	/**
	 * 返回 病例号/门诊号
	 * @return
	 */
	public String getCaseNumber() {
		return this.caseNumber;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * 返回 联系电话
	 * @return
	 */
	public String getPhone() {
		return this.phone;
	}
	
	public void setPastDrugsSideEffects(String pastDrugsSideEffects) {
		this.pastDrugsSideEffects = pastDrugsSideEffects;
	}
	
	/**
	 * 返回 既往药品不良反应
	 * @return
	 */
	public String getPastDrugsSideEffects() {
		return this.pastDrugsSideEffects;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * 返回 工作单位/住址
	 * @return
	 */
	public String getAddress() {
		return this.address;
	}
	
	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}
	
	/**
	 * 返回 过敏史
	 * @return
	 */
	public String getAllergy() {
		return this.allergy;
	}
	
	public void setOriginalDisease(String originalDisease) {
		this.originalDisease = originalDisease;
	}
	
	/**
	 * 返回 原患疾病
	 * @return
	 */
	public String getOriginalDisease() {
		return this.originalDisease;
	}
	
	public void setSideEffectsName(String sideEffectsName) {
		this.sideEffectsName = sideEffectsName;
	}
	
	/**
	 * 返回 不良反应名称
	 * @return
	 */
	public String getSideEffectsName() {
		return this.sideEffectsName;
	}
	
	public void setSideEffectsDate(java.util.Date sideEffectsDate) {
		this.sideEffectsDate = sideEffectsDate;
	}
	
	/**
	 * 返回 不良反应时间
	 * @return
	 */
	public java.util.Date getSideEffectsDate() {
		return this.sideEffectsDate;
	}
	
	public void setSideEffectsClincalFeature(String sideEffectsClincalFeature) {
		this.sideEffectsClincalFeature = sideEffectsClincalFeature;
	}
	
	/**
	 * 返回 不良反应临床表现
	 * @return
	 */
	public String getSideEffectsClincalFeature() {
		return this.sideEffectsClincalFeature;
	}
	
	public void setSideEffectsHandInformation(String sideEffectsHandInformation) {
		this.sideEffectsHandInformation = sideEffectsHandInformation;
	}
	
	/**
	 * 返回 不良反应处理情况
	 * @return
	 */
	public String getSideEffectsHandInformation() {
		return this.sideEffectsHandInformation;
	}
	
	public void setSideEffectsResult(String sideEffectsResult) {
		this.sideEffectsResult = sideEffectsResult;
	}
	
	/**
	 * 返回 不良反应结果
	 * @return
	 */
	public String getSideEffectsResult() {
		return this.sideEffectsResult;
	}
	
	public void setOriginalDiseaseAffect(String originalDiseaseAffect) {
		this.originalDiseaseAffect = originalDiseaseAffect;
	}
	
	/**
	 * 返回 对原疾病影响
	 * @return
	 */
	public String getOriginalDiseaseAffect() {
		return this.originalDiseaseAffect;
	}
	
	public void setDieDate(java.util.Date dieDate) {
		this.dieDate = dieDate;
	}
	
	/**
	 * 返回 死亡时间
	 * @return
	 */
	public java.util.Date getDieDate() {
		return this.dieDate;
	}
	
	public void setDirectDie(String directDie) {
		this.directDie = directDie;
	}
	
	/**
	 * 返回 直接死因
	 * @return
	 */
	public String getDirectDie() {
		return this.directDie;
	}
	
	public void setProvincialEvaluatioon(String provincialEvaluatioon) {
		this.provincialEvaluatioon = provincialEvaluatioon;
	}
	
	/**
	 * 返回 省级评价
	 * @return
	 */
	public String getProvincialEvaluatioon() {
		return this.provincialEvaluatioon;
	}
	
	public void setNationalAssessment(String nationalAssessment) {
		this.nationalAssessment = nationalAssessment;
	}
	
	/**
	 * 返回 国家级评价
	 * @return
	 */
	public String getNationalAssessment() {
		return this.nationalAssessment;
	}
	
	public void setDomesticSideEffects(String domesticSideEffects) {
		this.domesticSideEffects = domesticSideEffects;
	}
	
	/**
	 * 返回 国内不良反应情况
	 * @return
	 */
	public String getDomesticSideEffects() {
		return this.domesticSideEffects;
	}
	
	public void setAbroadSideEffects(String abroadSideEffects) {
		this.abroadSideEffects = abroadSideEffects;
	}
	
	/**
	 * 返回 国外不良反应情况
	 * @return
	 */
	public String getAbroadSideEffects() {
		return this.abroadSideEffects;
	}
	
	public void setOther(String other) {
		this.other = other;
	}
	
	/**
	 * 返回 其他
	 * @return
	 */
	public String getOther() {
		return this.other;
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
	
	public void setCreatePersion(String createPersion) {
		this.createPersion = createPersion;
	}
	
	/**
	 * 返回 创建人
	 * @return
	 */
	public String getCreatePersion() {
		return this.createPersion;
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
	 * 返回 审批状态(0:未提交,1:审批中,2:已通过,3:被驳回)
	 * @return
	 */
	public String getApprovalState() {
		return this.approvalState;
	}

	public void setScmGspDoubtProductList(List<ScmGspDoubtProduct> scmGspDoubtProductList) {
		this.scmGspDoubtProductList = scmGspDoubtProductList;
	}
	
	/**
	 * 返回 Scm_Gsp_DoubtProduct列表
	 * @return
	 */
	public List<ScmGspDoubtProduct> getScmGspDoubtProductList() {
		return this.scmGspDoubtProductList;
	}

	public void setScmGspAndUseProductList(List<ScmGspAndUseProduct> scmGspAndUseProductList) {
		this.scmGspAndUseProductList = scmGspAndUseProductList;
	}
	
	/**
	 * 返回 并用药品列表
	 * @return
	 */
	public List<ScmGspAndUseProduct> getScmGspAndUseProductList() {
		return this.scmGspAndUseProductList;
	}

	public void setScmGspRelevantImportantMessageList(List<ScmGspRelevantImportantMessage> scmGspRelevantImportantMessageList) {
		this.scmGspRelevantImportantMessageList = scmGspRelevantImportantMessageList;
	}
	
	/**
	 * 返回 相关重要信息列表
	 * @return
	 */
	public List<ScmGspRelevantImportantMessage> getScmGspRelevantImportantMessageList() {
		return this.scmGspRelevantImportantMessageList;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("patientName", this.patientName) 
		.append("gender", this.gender) 
		.append("birthDay", this.birthDay) 
		.append("weight", this.weight) 
		.append("natin", this.natin) 
		.append("familyMedicineSideEffects", this.familyMedicineSideEffects) 
		.append("caseNumber", this.caseNumber) 
		.append("phone", this.phone) 
		.append("pastDrugsSideEffects", this.pastDrugsSideEffects) 
		.append("address", this.address) 
		.append("allergy", this.allergy) 
		.append("originalDisease", this.originalDisease) 
		.append("sideEffectsName", this.sideEffectsName) 
		.append("sideEffectsDate", this.sideEffectsDate) 
		.append("sideEffectsClincalFeature", this.sideEffectsClincalFeature) 
		.append("sideEffectsHandInformation", this.sideEffectsHandInformation) 
		.append("sideEffectsResult", this.sideEffectsResult) 
		.append("originalDiseaseAffect", this.originalDiseaseAffect) 
		.append("dieDate", this.dieDate) 
		.append("directDie", this.directDie) 
		.append("provincialEvaluatioon", this.provincialEvaluatioon) 
		.append("nationalAssessment", this.nationalAssessment) 
		.append("domesticSideEffects", this.domesticSideEffects) 
		.append("abroadSideEffects", this.abroadSideEffects) 
		.append("other", this.other) 
		.append("memo", this.memo) 
		.append("ownerId", this.ownerId) 
		.append("createDate", this.createDate) 
		.append("createPersion", this.createPersion) 
		.append("approvalId", this.approvalId) 
		.append("approvalState", this.approvalState) 
		.toString();
	}
}