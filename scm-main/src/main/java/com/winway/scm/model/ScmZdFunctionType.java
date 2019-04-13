package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;


 /**
 * scm_zd_functiontype
 * <pre> 
 * 描述：scm_zd_functiontype 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-16 10:34:19
 * 版权：美达项目组
 * </pre>
 */
 @ApiModel(value = "ScmZdFunctionType",description = "scm_zd_functiontype") 
public class ScmZdFunctionType extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="商业级别id")
	protected String commerceLevelId; 
	
	@ApiModelProperty(value="职能类型")
	protected String functionType; 
	
	@ApiModelProperty(value="备注")
	protected String memo; 
	
	@ApiModelProperty(value="创建人")
	protected String createPersion; 
	
	@ApiModelProperty(value="创建时间")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date createDate; 
	
	@ApiModelProperty(value="编码")
	protected String code; 
	
	
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
	
	public void setCommerceLevelId(String commerceLevelId) {
		this.commerceLevelId = commerceLevelId;
	}
	
	/**
	 * 返回 商业级别id
	 * @return
	 */
	public String getCommerceLevelId() {
		return this.commerceLevelId;
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
	
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * 返回 code
	 * @return
	 */
	public String getCode() {
		return this.code;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("commerceLevelId", this.commerceLevelId) 
		.append("functionType", this.functionType) 
		.append("memo", this.memo) 
		.append("createPersion", this.createPersion) 
		.append("createDate", this.createDate) 
		.append("code", this.code) 
		.toString();
	}
}