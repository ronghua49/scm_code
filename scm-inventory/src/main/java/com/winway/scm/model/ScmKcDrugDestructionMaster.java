package com.winway.scm.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 不合格药品销毁主表
 * <pre> 
 * 描述：不合格药品销毁主表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-13 15:06:38
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmKcDrugDestructionMaster",description = "不合格药品销毁主表") 
public class ScmKcDrugDestructionMaster extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="销毁单号",required = true)
	protected String destructionCode; 
	
	@ApiModelProperty(value="品种数",required = true)
	protected Integer varietySum; 
	
	@ApiModelProperty(value="总金额")
	protected Double priceSum; 
	
	@ApiModelProperty(value="总数量")
	protected Integer productNum; 
	
	@ApiModelProperty(value="销毁方式",required = true)
	protected String destructionMethod; 
	
	@ApiModelProperty(value="销毁时间",required = true)
	protected java.util.Date destructionDate; 
	
	@ApiModelProperty(value="销毁地点",required = true)
	protected String destructionAddress; 
	
	@ApiModelProperty(value="运输工具",required = true)
	protected String conveyance; 
	
	@ApiModelProperty(value="质量部意见",required = true)
	protected String qualityIdea; 
	
	@ApiModelProperty(value="企业负责人意见",required = true)
	protected String companyPrincipalIdea; 
	
	@ApiModelProperty(value="监督人",required = true)
	protected String superintendent; 
	
	@ApiModelProperty(value="销毁人",required = true)
	protected String destructionPersion; 
	
	@ApiModelProperty(value="备注")
	protected String memo; 
	
	@ApiModelProperty(value="创建人")
	protected String createpersion; 
	
	@ApiModelProperty(value="创建时间")
	protected java.util.Date createDate; 
	
	@ApiModelProperty(value="审批状态")
	protected String approvalState; 
	
	@ApiModelProperty(value="审批ID")
	protected String approvalId; 
	
	@ApiModelProperty(value="货主ID",required = true)
	protected String ownerId;

	 @ApiModelProperty(value="是否推送值wMS 0未推送；1已推送")
	 protected String isSMS="0";
	 
	 @ApiModelProperty(value="sap是否处理(0:未处理,1:已处理)")
	 protected String isManageSap="0";

	 //子表首页显示字段

	 @ApiModelProperty(value="药品名称",required = true)
	 protected String productName;

	 @ApiModelProperty(value="药品编号",required = true)
	 protected String productCode;

	 @ApiModelProperty(value="药品规格",required = true)
	 protected String productState;

	 @ApiModelProperty(value="药品数量",required = true)
	 protected Integer productSum;

	 @ApiModelProperty(value="药品单价",required = true)
	 protected Double unitPrice;


	 @ApiModelProperty(value="药品类型",required = true)
	 protected String medicineType;

	 @ApiModelProperty(value="生产厂家",required = true)
	 protected String manufacturer;

	 @ApiModelProperty(value="批号",required = true)
	 protected String batchNum;

	 @ApiModelProperty(value="失效日期",required = true)
	 @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	 protected java.util.Date expiryDate;

	 @ApiModelProperty(value="件装量",required = true)
	 protected Integer aChage;

	 @ApiModelProperty(value="件数",required = true)
	 protected Integer number ;

	 @ApiModelProperty(value="不合格原因",required = true)
	 protected String unqualifiedReason;

	 @ApiModelProperty(value="剂型")
	 protected String agentType;
	 @ApiModelProperty(value="批准文号")
	 protected String approvalCode;
	 @ApiModelProperty(value="经营范围")
	 protected String businessScope;
	 @ApiModelProperty(value="药品类型")
	 protected String medicineClassify;
	 @ApiModelProperty(value="通用名称",required = true)
	 protected String commonName;
	 @ApiModelProperty(value="单位",required = true)
	 protected String unit;

	 public String getIsManageSap() {
		return isManageSap;
	}

	public void setIsManageSap(String isManageSap) {
		this.isManageSap = isManageSap;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getProductName() {
		 return productName;
	 }

	 public void setProductName(String productName) {
		 this.productName = productName;
	 }

	 public String getProductCode() {
		 return productCode;
	 }

	 public void setProductCode(String productCode) {
		 this.productCode = productCode;
	 }

	 public String getProductState() {
		 return productState;
	 }

	 public void setProductState(String productState) {
		 this.productState = productState;
	 }

	 public Integer getProductSum() {
		 return productSum;
	 }

	 public void setProductSum(Integer productSum) {
		 this.productSum = productSum;
	 }

	 public Double getUnitPrice() {
		 return unitPrice;
	 }

	 public void setUnitPrice(Double unitPrice) {
		 this.unitPrice = unitPrice;
	 }

	 public String getMedicineType() {
		 return medicineType;
	 }

	 public void setMedicineType(String medicineType) {
		 this.medicineType = medicineType;
	 }

	 public String getManufacturer() {
		 return manufacturer;
	 }

	 public void setManufacturer(String manufacturer) {
		 this.manufacturer = manufacturer;
	 }

	 public String getBatchNum() {
		 return batchNum;
	 }

	 public void setBatchNum(String batchNum) {
		 this.batchNum = batchNum;
	 }

	 public Date getExpiryDate() {
		 return expiryDate;
	 }

	 public void setExpiryDate(Date expiryDate) {
		 this.expiryDate = expiryDate;
	 }

	 public Integer getaChage() {
		 return aChage;
	 }

	 public void setaChage(Integer aChage) {
		 this.aChage = aChage;
	 }

	 public Integer getNumber() {
		 return number;
	 }

	 public void setNumber(Integer number) {
		 this.number = number;
	 }

	 public String getUnqualifiedReason() {
		 return unqualifiedReason;
	 }

	 public void setUnqualifiedReason(String unqualifiedReason) {
		 this.unqualifiedReason = unqualifiedReason;
	 }

	 public String getAgentType() {
		 return agentType;
	 }

	 public void setAgentType(String agentType) {
		 this.agentType = agentType;
	 }

	 public String getApprovalCode() {
		 return approvalCode;
	 }

	 public void setApprovalCode(String approvalCode) {
		 this.approvalCode = approvalCode;
	 }

	 public String getBusinessScope() {
		 return businessScope;
	 }

	 public void setBusinessScope(String businessScope) {
		 this.businessScope = businessScope;
	 }

	 public String getMedicineClassify() {
		 return medicineClassify;
	 }

	 public void setMedicineClassify(String medicineClassify) {
		 this.medicineClassify = medicineClassify;
	 }

	 public String getCommonName() {
		 return commonName;
	 }

	 public void setCommonName(String commonName) {
		 this.commonName = commonName;
	 }

	 public String getUnit() {
		 return unit;
	 }

	 public void setUnit(String unit) {
		 this.unit = unit;
	 }

	 public String getIsSMS() {
		 return isSMS;
	 }

	 public void setIsSMS(String isSMS) {
		 this.isSMS = isSMS;
	 }

	 /**
	*不合格药品销毁明细表列表
	*/
	protected List<ScmKcDrugDestruction> scmKcDrugDestructionList=new ArrayList<ScmKcDrugDestruction>(); 
	
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
	
	public void setDestructionCode(String destructionCode) {
		this.destructionCode = destructionCode;
	}
	
	/**
	 * 返回 销毁单号
	 * @return
	 */
	public String getDestructionCode() {
		return this.destructionCode;
	}
	
	public void setVarietySum(Integer varietySum) {
		this.varietySum = varietySum;
	}
	
	/**
	 * 返回 品种数
	 * @return
	 */
	public Integer getVarietySum() {
		return this.varietySum;
	}
	
	public void setPriceSum(Double priceSum) {
		this.priceSum = priceSum;
	}
	
	/**
	 * 返回 总金额
	 * @return
	 */
	public Double getPriceSum() {
		return this.priceSum;
	}
	
	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}
	
	/**
	 * 返回 总数量
	 * @return
	 */
	public Integer getProductNum() {
		return this.productNum;
	}
	
	public void setDestructionMethod(String destructionMethod) {
		this.destructionMethod = destructionMethod;
	}
	
	/**
	 * 返回 销毁方式
	 * @return
	 */
	public String getDestructionMethod() {
		return this.destructionMethod;
	}
	
	public void setDestructionDate(java.util.Date destructionDate) {
		this.destructionDate = destructionDate;
	}
	
	/**
	 * 返回 销毁时间
	 * @return
	 */
	public java.util.Date getDestructionDate() {
		return this.destructionDate;
	}
	
	public void setDestructionAddress(String destructionAddress) {
		this.destructionAddress = destructionAddress;
	}
	
	/**
	 * 返回 销毁地点
	 * @return
	 */
	public String getDestructionAddress() {
		return this.destructionAddress;
	}
	
	public void setConveyance(String conveyance) {
		this.conveyance = conveyance;
	}
	
	/**
	 * 返回 运输工具
	 * @return
	 */
	public String getConveyance() {
		return this.conveyance;
	}
	
	public void setQualityIdea(String qualityIdea) {
		this.qualityIdea = qualityIdea;
	}
	
	/**
	 * 返回 质量部意见
	 * @return
	 */
	public String getQualityIdea() {
		return this.qualityIdea;
	}
	
	public void setCompanyPrincipalIdea(String companyPrincipalIdea) {
		this.companyPrincipalIdea = companyPrincipalIdea;
	}
	
	/**
	 * 返回 企业负责人意见
	 * @return
	 */
	public String getCompanyPrincipalIdea() {
		return this.companyPrincipalIdea;
	}
	
	public void setSuperintendent(String superintendent) {
		this.superintendent = superintendent;
	}
	
	/**
	 * 返回 监督人
	 * @return
	 */
	public String getSuperintendent() {
		return this.superintendent;
	}
	
	public void setDestructionPersion(String destructionPersion) {
		this.destructionPersion = destructionPersion;
	}
	
	/**
	 * 返回 销毁人
	 * @return
	 */
	public String getDestructionPersion() {
		return this.destructionPersion;
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

	public void setScmKcDrugDestructionList(List<ScmKcDrugDestruction> scmKcDrugDestructionList) {
		this.scmKcDrugDestructionList = scmKcDrugDestructionList;
	}
	
	/**
	 * 返回 不合格药品销毁明细表列表
	 * @return
	 */
	public List<ScmKcDrugDestruction> getScmKcDrugDestructionList() {
		return this.scmKcDrugDestructionList;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("destructionCode", this.destructionCode) 
		.append("varietySum", this.varietySum) 
		.append("priceSum", this.priceSum) 
		.append("productNum", this.productNum) 
		.append("destructionMethod", this.destructionMethod) 
		.append("destructionDate", this.destructionDate) 
		.append("destructionAddress", this.destructionAddress) 
		.append("conveyance", this.conveyance) 
		.append("qualityIdea", this.qualityIdea) 
		.append("companyPrincipalIdea", this.companyPrincipalIdea) 
		.append("superintendent", this.superintendent) 
		.append("destructionPersion", this.destructionPersion) 
		.append("memo", this.memo) 
		.append("createpersion", this.createpersion) 
		.append("createDate", this.createDate) 
		.append("approvalState", this.approvalState) 
		.append("approvalId", this.approvalId) 
		.append("ownerId", this.ownerId) 
		.toString();
	}
}