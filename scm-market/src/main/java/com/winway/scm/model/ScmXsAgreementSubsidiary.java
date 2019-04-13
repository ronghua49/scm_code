package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 商业协议分子公司表
 * <pre> 
 * 描述：商业协议分子公司表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:44
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmXsAgreementSubsidiary",description = "商业协议分子公司表") 
public class ScmXsAgreementSubsidiary extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="子公司ID")
	protected String subsidiaryId; 
	
	@ApiModelProperty(value="协议ID")
	protected String AgreementId; 
	
	@ApiModelProperty(value="子公司名称")
	protected String subsidiaryName; 
	
	
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
	
	public void setSubsidiaryId(String subsidiaryId) {
		this.subsidiaryId = subsidiaryId;
	}
	
	/**
	 * 返回 子公司ID
	 * @return
	 */
	public String getSubsidiaryId() {
		return this.subsidiaryId;
	}
	
	public void setAgreementId(String AgreementId) {
		this.AgreementId = AgreementId;
	}
	
	/**
	 * 返回 协议ID
	 * @return
	 */
	public String getAgreementId() {
		return this.AgreementId;
	}
	
	public void setSubsidiaryName(String subsidiaryName) {
		this.subsidiaryName = subsidiaryName;
	}
	
	/**
	 * 返回 子公司名称
	 * @return
	 */
	public String getSubsidiaryName() {
		return this.subsidiaryName;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("subsidiaryId", this.subsidiaryId) 
		.append("AgreementId", this.AgreementId) 
		.append("subsidiaryName", this.subsidiaryName) 
		.toString();
	}
}