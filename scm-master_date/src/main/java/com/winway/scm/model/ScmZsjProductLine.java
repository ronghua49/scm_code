package com.winway.scm.model;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;


 /**
 * 产品系列
 * <pre> 
 * 描述：产品系列 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 16:15:42
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmZsjProductLine",description = "产品系列") 
public class ScmZsjProductLine extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="产品系列名称")
	protected String ProductLineName; 
	
	@ApiModelProperty(value="产品系列缩写")
	protected String ProductLineAcronym; 
	
	@ApiModelProperty(value="编码")
	protected String ProductLineCode; 
	
	@ApiModelProperty(value="系列通用名称")
	protected String ProductLineCommon; 
	
	@ApiModelProperty(value="系列英文名")
	protected String ProductLineEname; 
	
	@ApiModelProperty(value="剂型ID")
	protected String AgentTypeId; 
	
	@ApiModelProperty(value="剂型")
	protected String AgentType; 
	
	@ApiModelProperty(value="类型ID")
	protected String MedicineTypeId; 
	
	@ApiModelProperty(value="类型")
	protected String MedicineType; 
	
	@ApiModelProperty(value="商品系列状态(0停用,1启用)")
	protected String ProductLineState; 
	
	@ApiModelProperty(value="创建人")
	protected String CreatePersion; 
	
	@ApiModelProperty(value="创建时间")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected Date CreateDate; 
	
	
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
	
	public void setProductLineName(String ProductLineName) {
		this.ProductLineName = ProductLineName;
	}
	
	/**
	 * 返回 产品系列名称
	 * @return
	 */
	public String getProductLineName() {
		return this.ProductLineName;
	}
	
	public void setProductLineAcronym(String ProductLineAcronym) {
		this.ProductLineAcronym = ProductLineAcronym;
	}
	
	/**
	 * 返回 产品系列缩写
	 * @return
	 */
	public String getProductLineAcronym() {
		return this.ProductLineAcronym;
	}
	
	public void setProductLineCode(String ProductLineCode) {
		this.ProductLineCode = ProductLineCode;
	}
	
	/**
	 * 返回 编码
	 * @return
	 */
	public String getProductLineCode() {
		return this.ProductLineCode;
	}
	
	public void setProductLineCommon(String ProductLineCommon) {
		this.ProductLineCommon = ProductLineCommon;
	}
	
	/**
	 * 返回 系列通用名称
	 * @return
	 */
	public String getProductLineCommon() {
		return this.ProductLineCommon;
	}
	
	public void setProductLineEname(String ProductLineEname) {
		this.ProductLineEname = ProductLineEname;
	}
	
	/**
	 * 返回 系列英文名
	 * @return
	 */
	public String getProductLineEname() {
		return this.ProductLineEname;
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
	
	public void setMedicineTypeId(String MedicineTypeId) {
		this.MedicineTypeId = MedicineTypeId;
	}
	
	/**
	 * 返回 类型ID
	 * @return
	 */
	public String getMedicineTypeId() {
		return this.MedicineTypeId;
	}
	
	public void setMedicineType(String MedicineType) {
		this.MedicineType = MedicineType;
	}
	
	/**
	 * 返回 类型
	 * @return
	 */
	public String getMedicineType() {
		return this.MedicineType;
	}
	
	public void setProductLineState(String ProductLineState) {
		this.ProductLineState = ProductLineState;
	}
	
	/**
	 * 返回 商品系列状态(0停用,1启用)
	 * @return
	 */
	public String getProductLineState() {
		return this.ProductLineState;
	}
	
	
	public String getCreatePersion() {
		return CreatePersion;
	}

	public void setCreatePersion(String createPersion) {
		this.CreatePersion = createPersion;
	}

	public Date getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(Date createDate) {
		this.CreateDate = createDate;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("ProductLineName", this.ProductLineName) 
		.append("ProductLineAcronym", this.ProductLineAcronym) 
		.append("ProductLineCode", this.ProductLineCode) 
		.append("ProductLineCommon", this.ProductLineCommon) 
		.append("ProductLineEname", this.ProductLineEname) 
		.append("AgentTypeId", this.AgentTypeId) 
		.append("AgentType", this.AgentType) 
		.append("MedicineTypeId", this.MedicineTypeId) 
		.append("MedicineType", this.MedicineType) 
		.append("ProductLineState", this.ProductLineState) 
		.append("CreatePersion", this.CreatePersion) 
		.append("CreateDate", this.CreateDate) 
		.toString();
	}
}