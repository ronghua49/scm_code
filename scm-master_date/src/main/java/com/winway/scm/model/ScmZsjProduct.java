package com.winway.scm.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


 /**
 * 商品表
 * <pre> 
 * 描述：商品表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmZsjProduct",description = "商品表") 
public class ScmZsjProduct extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="产品名")
	protected String productName; 
	
	@ApiModelProperty(value="产品系列ID")
	protected String ProductLineId; 
	
	@ApiModelProperty(value="产品系列")
	protected String ProductLineName; 
	
	@ApiModelProperty(value="物料编码")
	protected String productCode; 
	
	@ApiModelProperty(value="通用名称")
	protected String commonName; 
	
	@ApiModelProperty(value="规格")
	protected String productState; 
	
	@ApiModelProperty(value="剂型ID")
	protected String AgentTypeId; 
	
	@ApiModelProperty(value="剂型")
	protected String AgentType; 
	
	@ApiModelProperty(value="单位")
	protected String unit; 
	
	@ApiModelProperty(value="OTC")
	protected String otc;
	
	@ApiModelProperty(value="追溯码")
	protected String traceCode; 
	
	@ApiModelProperty(value="药品类型ID")
	protected String MedicineTypeId; 
	
	@ApiModelProperty(value="药品类型")
	protected String MedicineType; 
	
	@ApiModelProperty(value="药品分类ID")
	protected String medicineClassifyId; 
	
	@ApiModelProperty(value="药品分类")
	protected String medicineClassify; 
	
	@ApiModelProperty(value="贮存条件")
	protected String KeepingCondition; 
	
	@ApiModelProperty(value="建议库区")
	protected String suggestWarehouse; 
	
	@ApiModelProperty(value="供应商Id")
	protected String SupplierId; 
	
	@ApiModelProperty(value="供应商名称")
	protected String supplierName; 
	
	@ApiModelProperty(value="供应商编号")
	protected String supplierCode; 
	
	@ApiModelProperty(value="生产厂商")
	protected String manufacturer; 
	
	@ApiModelProperty(value="存货代码")
	protected String InventoryCode; 
	
	@ApiModelProperty(value="录入人")
	protected String operatorName; 
	
	@ApiModelProperty(value="产品编码")
	protected String productNum; 
	
	@ApiModelProperty(value="录入时间")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected Date operatorDate; 
	
	@ApiModelProperty(value="批准文号/注册证号")
	protected String approvalCode; 
	
	@ApiModelProperty(value="经营范围")
	protected String businessScope; 
	
	@ApiModelProperty(value="首营ID")
	protected String productFirstId; 
	
	@ApiModelProperty(value="商品名带审批状态")
	protected String productNameStr; 
	
	@ApiModelProperty(value="审批ID")
	protected String approvalId; 
	
	@ApiModelProperty(value="审批状态，0：未提交，1：审批中，2：已通过")
	protected String approvalState; 
	
	@ApiModelProperty(value="审批状态中文标识")
	protected String approvalStateStr; 
	
	@ApiModelProperty(value="GMP")
	private String gmp;
	
	@ApiModelProperty(value="供应商")
	private String supplier;
	
	@ApiModelProperty(value="外用")
	private String externalUse;
	
	@ApiModelProperty(value="产地")
	private String placeOfOrigin;
	
	@ApiModelProperty(value="是否推送至主数据中心(0:否,1:是)")
	private String isToMasterData;

	 @ApiModelProperty(value="有效期")
	 private String validityMonth;
	 @ApiModelProperty(value="GMP证书有效期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected Date GMPValidity;
	 //件装量字段
	 private String achage;
	 private String code;
	 //件装量字段列表
	 List<Map<String,String>> achageList = new ArrayList<>();

	 public List<Map<String, String>> getAchageList() {
		 return achageList;
	 }

	 public void setAchageList(List<Map<String, String>> achageList) {
		 this.achageList = achageList;
	 }

	 public String getIsToMasterData() {
		return isToMasterData;
	}

	public void setIsToMasterData(String isToMasterData) {
		this.isToMasterData = isToMasterData;
	}


	public String getAchage() {
		 return achage;
	 }

	 public void setAchage(String achage) {
		 this.achage = achage;
	 }

	 public String getCode() {
		 return code;
	 }

	 public void setCode(String code) {
		 this.code = code;
	 }

	 public String getValidityMonth() {
		 return validityMonth;
	 }

	 public void setValidityMonth(String validityMonth) {
		 this.validityMonth = validityMonth;
	 }

	 public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	/**
	*件装量列表
	*/
	protected List<ScmZsjAchage> scmZsjAchageList=new ArrayList<ScmZsjAchage>(); 
	
	
	
	public String getExternalUse() {
		return externalUse;
	}

	public void setExternalUse(String externalUse) {
		this.externalUse = externalUse;
	}

	public String getPlaceOfOrigin() {
		return placeOfOrigin;
	}

	public void setPlaceOfOrigin(String placeOfOrigin) {
		this.placeOfOrigin = placeOfOrigin;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<ScmZsjAchage> getScmZsjAchageList() {
		return scmZsjAchageList;
	}

	public void setScmZsjAchageList(List<ScmZsjAchage> scmZsjAchageList) {
		this.scmZsjAchageList = scmZsjAchageList;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

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
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getProductNum() {
		return productNum;
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

	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}

	/**
	 * 返回 产品名
	 * @return
	 */
	public String getProductName() {
		return this.productName;
	}
	
	public void setProductLineId(String ProductLineId) {
		this.ProductLineId = ProductLineId;
	}
	
	public String getApprovalCode() {
		return approvalCode;
	}
	
	public String getProductNameStr() {
		return productNameStr;
	}

	public void setProductNameStr(String productNameStr) {
		this.productNameStr = productNameStr;
	}

	public void setApprovalCode(String approvalCode) {
		this.approvalCode = approvalCode;
	}

	public String getBusinessScope() {
		return businessScope;
	}

	public String getGmp() {
		return gmp;
	}

	public void setGmp(String gmp) {
		this.gmp = gmp;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}

	public java.util.Date getGMPValidity() {
		return GMPValidity;
	}

	public void setGMPValidity(java.util.Date gMPValidity) {
		GMPValidity = gMPValidity;
	}
	
	public String getProductFirstId() {
		return productFirstId;
	}

	public void setProductFirstId(String productFirstId) {
		this.productFirstId = productFirstId;
	}

	/**
	 * 返回 产品系列ID
	 * @return
	 */
	public String getProductLineId() {
		return this.ProductLineId;
	}
	
	public void setProductLineName(String ProductLineName) {
		this.ProductLineName = ProductLineName;
	}
	
	/**
	 * 返回 产品系列
	 * @return
	 */
	public String getProductLineName() {
		return this.ProductLineName;
	}
	
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	/**
	 * 返回 物料编码
	 * @return
	 */
	public String getProductCode() {
		return this.productCode;
	}
	
	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}
	
	/**
	 * 返回 通用名称
	 * @return
	 */
	public String getCommonName() {
		return this.commonName;
	}
	
	public void setProductState(String productState) {
		this.productState = productState;
	}
	
	/**
	 * 返回 规格
	 * @return
	 */
	public String getProductState() {
		return this.productState;
	}
	
	public void setAgentTypeId(String AgentTypeId) {
		this.AgentTypeId = AgentTypeId;
	}
	
	/**
	 * 返回 剂型ID
	 * @return
	 */
	public String getAgentTypeId() {
		return this.AgentTypeId;
	}
	
	public void setAgentType(String AgentType) {
		this.AgentType = AgentType;
	}
	
	/**
	 * 返回 剂型
	 * @return
	 */
	public String getAgentType() {
		return this.AgentType;
	}
	
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	/**
	 * 返回 单位
	 * @return
	 */
	public String getUnit() {
		return this.unit;
	}
	
/*	public void setAchange(String Achange) {
		this.Achange = Achange;
	}
	
	*/

	 public String getOtc() {
		 return otc;
	 }

	 public void setOtc(String otc) {
		 this.otc = otc;
	 }

	 /**
	 * 返回 件装量
	 * @return
	 *//*
	public String getAchange() {
		return this.Achange;
	}*/
	

	
	public void setTraceCode(String traceCode) {
		this.traceCode = traceCode;
	}
	
	/**
	 * 返回 追溯码
	 * @return
	 */
	public String getTraceCode() {
		return this.traceCode;
	}
	
	public void setMedicineTypeId(String MedicineTypeId) {
		this.MedicineTypeId = MedicineTypeId;
	}
	
	/**
	 * 返回 药品类型ID
	 * @return
	 */
	public String getMedicineTypeId() {
		return this.MedicineTypeId;
	}
	
	public void setMedicineType(String MedicineType) {
		this.MedicineType = MedicineType;
	}
	
	/**
	 * 返回 药品类型
	 * @return
	 */
	public String getMedicineType() {
		return this.MedicineType;
	}
	
	public void setMedicineClassifyId(String medicineClassifyId) {
		this.medicineClassifyId = medicineClassifyId;
	}
	
	/**
	 * 返回 药品分类ID
	 * @return
	 */
	public String getMedicineClassifyId() {
		return this.medicineClassifyId;
	}
	
	public void setMedicineClassify(String medicineClassify) {
		this.medicineClassify = medicineClassify;
	}
	
	/**
	 * 返回 药品分类
	 * @return
	 */
	public String getMedicineClassify() {
		return this.medicineClassify;
	}
	
	public void setKeepingCondition(String KeepingCondition) {
		this.KeepingCondition = KeepingCondition;
	}
	
	/**
	 * 返回 贮存条件
	 * @return
	 */
	public String getKeepingCondition() {
		return this.KeepingCondition;
	}
	
	public void setSuggestWarehouse(String suggestWarehouse) {
		this.suggestWarehouse = suggestWarehouse;
	}
	
	/**
	 * 返回 建议库区
	 * @return
	 */
	public String getSuggestWarehouse() {
		return this.suggestWarehouse;
	}
	
	public void setSupplierId(String SupplierId) {
		this.SupplierId = SupplierId;
	}
	
	/**
	 * 返回 供应商Id
	 * @return
	 */
	public String getSupplierId() {
		return this.SupplierId;
	}
	
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	/**
	 * 返回 供应商名称
	 * @return
	 */
	public String getSupplierName() {
		return this.supplierName;
	}
	
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	/**
	 * 返回 生产厂商
	 * @return
	 */
	public String getManufacturer() {
		return this.manufacturer;
	}
	
	public void setInventoryCode(String InventoryCode) {
		this.InventoryCode = InventoryCode;
	}
	
	/**
	 * 返回 存货代码
	 * @return
	 */
	public String getInventoryCode() {
		return this.InventoryCode;
	}
	
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	
	/**
	 * 返回 录入人
	 * @return
	 */
	public String getOperatorName() {
		return this.operatorName;
	}
	
	public void setOperatorDate(java.util.Date operatorDate) {
		this.operatorDate = operatorDate;
	}
	
	/**
	 * 返回 录入时间
	 * @return
	 */
	public java.util.Date getOperatorDate() {
		return this.operatorDate;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("productName", this.productName) 
		.append("ProductLineId", this.ProductLineId) 
		.append("ProductLineName", this.ProductLineName) 
		.append("productCode", this.productCode) 
		.append("commonName", this.commonName) 
		.append("productState", this.productState) 
		.append("AgentTypeId", this.AgentTypeId) 
		.append("AgentType", this.AgentType) 
		.append("unit", this.unit) 
		.append("OTC", this.otc)
		.append("traceCode", this.traceCode) 
		.append("MedicineTypeId", this.MedicineTypeId) 
		.append("MedicineType", this.MedicineType) 
		.append("medicineClassifyId", this.medicineClassifyId) 
		.append("medicineClassify", this.medicineClassify) 
		.append("KeepingCondition", this.KeepingCondition) 
		.append("suggestWarehouse", this.suggestWarehouse) 
		.append("SupplierId", this.SupplierId) 
		.append("supplierName", this.supplierName) 
		.append("manufacturer", this.manufacturer) 
		.append("InventoryCode", this.InventoryCode) 
		.append("operatorName", this.operatorName) 
		.append("operatorDate", this.operatorDate) 
		.toString();
	}
}