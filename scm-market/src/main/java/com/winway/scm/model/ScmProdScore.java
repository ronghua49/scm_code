package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 商品积分管理
 * <pre> 
 * 描述：商品积分管理 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-06-27 15:55:07
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmProdScore",description = "商品积分管理") 
public class ScmProdScore extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="year")
	protected Integer year; 
	
	@ApiModelProperty(value="month")
	protected Integer month; 
	
	@ApiModelProperty(value="大区编码")
	protected String ucOrgBuId; 
	
	@ApiModelProperty(value="大区名称")
	protected String ucOrgBuName; 
	
	@ApiModelProperty(value="子大区编码")
	protected String ucOrgSubareaId; 
	
	@ApiModelProperty(value="子大区名称")
	protected String ucOrgSubareaName; 
	
	@ApiModelProperty(value="办事处编码")
	protected String ucOrgAgencyId; 
	
	@ApiModelProperty(value="办事处名称")
	protected String ucOrgAgencyName; 
	
	@ApiModelProperty(value="产品系列")
	protected String prodCatCode; 
	
	@ApiModelProperty(value="产品系列名称")
	protected String prodCatName; 
	
	@ApiModelProperty(value="品规")
	protected String prodCode; 
	
	@ApiModelProperty(value="品规名称")
	protected String prodName; 
	
	@ApiModelProperty(value="商品積分计算：本省商业供货价*系数（1-1/1.16*5%）系数可由财务配置")
	protected Double prodScore; 
	
	@ApiModelProperty(value="商品积分调整 -默认值为商品积分")
	protected Double prodScoreChange; 
	
	@ApiModelProperty(value="remarks")
	protected String remarks; 
	
	@ApiModelProperty(value="创建人")
	protected String createBy; 
	
	@ApiModelProperty(value="创建时间")
	protected java.util.Date createTime; 
	
	@ApiModelProperty(value="创建人姓名")
	protected String createName; 
	
	@ApiModelProperty(value="修改人")
	protected String updateBy; 
	
	@ApiModelProperty(value="修改时间")
	protected java.util.Date updateTime; 
	
	@ApiModelProperty(value="修改人姓名")
	protected String updateName; 
	
	@ApiModelProperty(value="创建人所在组织")
	protected String createOrgId; 
	
	@ApiModelProperty(value="创建人所在组织名称")
	protected String createOrgName; 
	
	@ApiModelProperty(value="公司")
	protected String COMPANY; 
	
	
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
	
	public void setYear(Integer year) {
		this.year = year;
	}
	
	/**
	 * 返回 year
	 * @return
	 */
	public Integer getYear() {
		return this.year;
	}
	
	public void setMonth(Integer month) {
		this.month = month;
	}
	
	/**
	 * 返回 month
	 * @return
	 */
	public Integer getMonth() {
		return this.month;
	}
	
	public void setUcOrgBuId(String ucOrgBuId) {
		this.ucOrgBuId = ucOrgBuId;
	}
	
	/**
	 * 返回 大区编码
	 * @return
	 */
	public String getUcOrgBuId() {
		return this.ucOrgBuId;
	}
	
	public void setUcOrgBuName(String ucOrgBuName) {
		this.ucOrgBuName = ucOrgBuName;
	}
	
	/**
	 * 返回 大区名称
	 * @return
	 */
	public String getUcOrgBuName() {
		return this.ucOrgBuName;
	}
	
	public void setUcOrgSubareaId(String ucOrgSubareaId) {
		this.ucOrgSubareaId = ucOrgSubareaId;
	}
	
	/**
	 * 返回 子大区编码
	 * @return
	 */
	public String getUcOrgSubareaId() {
		return this.ucOrgSubareaId;
	}
	
	public void setUcOrgSubareaName(String ucOrgSubareaName) {
		this.ucOrgSubareaName = ucOrgSubareaName;
	}
	
	/**
	 * 返回 子大区名称
	 * @return
	 */
	public String getUcOrgSubareaName() {
		return this.ucOrgSubareaName;
	}
	
	public void setUcOrgAgencyId(String ucOrgAgencyId) {
		this.ucOrgAgencyId = ucOrgAgencyId;
	}
	
	/**
	 * 返回 办事处编码
	 * @return
	 */
	public String getUcOrgAgencyId() {
		return this.ucOrgAgencyId;
	}
	
	public void setUcOrgAgencyName(String ucOrgAgencyName) {
		this.ucOrgAgencyName = ucOrgAgencyName;
	}
	
	/**
	 * 返回 办事处名称
	 * @return
	 */
	public String getUcOrgAgencyName() {
		return this.ucOrgAgencyName;
	}
	
	public void setProdCatCode(String prodCatCode) {
		this.prodCatCode = prodCatCode;
	}
	
	/**
	 * 返回 产品系列
	 * @return
	 */
	public String getProdCatCode() {
		return this.prodCatCode;
	}
	
	public void setProdCatName(String prodCatName) {
		this.prodCatName = prodCatName;
	}
	
	/**
	 * 返回 产品系列名称
	 * @return
	 */
	public String getProdCatName() {
		return this.prodCatName;
	}
	
	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}
	
	/**
	 * 返回 品规
	 * @return
	 */
	public String getProdCode() {
		return this.prodCode;
	}
	
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	
	/**
	 * 返回 品规名称
	 * @return
	 */
	public String getProdName() {
		return this.prodName;
	}
	
	public void setProdScore(Double prodScore) {
		this.prodScore = prodScore;
	}
	
	/**
	 * 返回 商品積分计算：本省商业供货价*系数（1-1/1.16*5%）系数可由财务配置
	 * @return
	 */
	public Double getProdScore() {
		return this.prodScore;
	}
	
	public void setProdScoreChange(Double prodScoreChange) {
		this.prodScoreChange = prodScoreChange;
	}
	
	/**
	 * 返回 商品积分调整 -默认值为商品积分
	 * @return
	 */
	public Double getProdScoreChange() {
		return this.prodScoreChange;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	/**
	 * 返回 remarks
	 * @return
	 */
	public String getRemarks() {
		return this.remarks;
	}
	
	
	
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	
	
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	
	/**
	 * 返回 创建人姓名
	 * @return
	 */
	public String getCreateName() {
		return this.createName;
	}
	
	
	
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}
	
	/**
	 * 返回 修改人姓名
	 * @return
	 */
	public String getUpdateName() {
		return this.updateName;
	}
	
	
	public void setCreateOrgName(String createOrgName) {
		this.createOrgName = createOrgName;
	}
	
	/**
	 * 返回 创建人所在组织名称
	 * @return
	 */
	public String getCreateOrgName() {
		return this.createOrgName;
	}
	
	public void setCOMPANY(String COMPANY) {
		this.COMPANY = COMPANY;
	}
	
	/**
	 * 返回 公司
	 * @return
	 */
	public String getCOMPANY() {
		return this.COMPANY;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("year", this.year) 
		.append("month", this.month) 
		.append("ucOrgBuId", this.ucOrgBuId) 
		.append("ucOrgBuName", this.ucOrgBuName) 
		.append("ucOrgSubareaId", this.ucOrgSubareaId) 
		.append("ucOrgSubareaName", this.ucOrgSubareaName) 
		.append("ucOrgAgencyId", this.ucOrgAgencyId) 
		.append("ucOrgAgencyName", this.ucOrgAgencyName) 
		.append("prodCatCode", this.prodCatCode) 
		.append("prodCatName", this.prodCatName) 
		.append("prodCode", this.prodCode) 
		.append("prodName", this.prodName) 
		.append("prodScore", this.prodScore) 
		.append("prodScoreChange", this.prodScoreChange) 
		.append("remarks", this.remarks) 
		.append("createBy", this.createBy) 
		.append("createTime", this.createTime) 
		.append("createName", this.createName) 
		.append("updateBy", this.updateBy) 
		.append("updateTime", this.updateTime) 
		.append("updateName", this.updateName) 
		.append("createOrgId", this.createOrgId) 
		.append("createOrgName", this.createOrgName) 
		.append("COMPANY", this.COMPANY) 
		.toString();
	}
}