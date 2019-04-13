package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;


 /**
 * scm_log_modifylog
 * <pre> 
 * 描述：scm_log_modifylog 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-02-23 11:33:08
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmLogModifyLog",description = "scm_log_modifylog") 
public class ScmLogModifyLog extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="主键")
	protected String id; 
	
	@ApiModelProperty(value="修改数据类别")
	protected String category; 
	
	@ApiModelProperty(value="修改人")
	protected String modifier; 
	
	@ApiModelProperty(value="修改数据ID")
	protected String categoryId; 
	
	@ApiModelProperty(value="修改字段内容")
	protected String filedName; 
	
	@ApiModelProperty(value="修改日期时间")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date modifiedTime; 
	
	
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 返回 主键
	 * @return
	 */
	public String getId() {
		return this.id;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	/**
	 * 返回 修改数据类别
	 * @return
	 */
	public String getCategory() {
		return this.category;
	}
	
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	
	/**
	 * 返回 修改人
	 * @return
	 */
	public String getModifier() {
		return this.modifier;
	}
	
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	/**
	 * 返回 修改数据ID
	 * @return
	 */
	public String getCategoryId() {
		return this.categoryId;
	}
	
	public void setFiledName(String filedName) {
		this.filedName = filedName;
	}
	
	/**
	 * 返回 修改字段内容
	 * @return
	 */
	public String getFiledName() {
		return this.filedName;
	}
	
	public void setModifiedTime(java.util.Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	
	/**
	 * 返回 修改日期时间
	 * @return
	 */
	public java.util.Date getModifiedTime() {
		return this.modifiedTime;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("category", this.category) 
		.append("modifier", this.modifier) 
		.append("categoryId", this.categoryId) 
		.append("filedName", this.filedName) 
		.append("modifiedTime", this.modifiedTime) 
		.toString();
	}
}